package org.firstinspires.ftc.teamcode.utils.subsystems;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.control.PIDFController;
import com.pedropathing.follower.Follower;
import com.pedropathing.ftc.FTCCoordinates;
import com.pedropathing.geometry.PedroCoordinates;
import com.pedropathing.geometry.Pose;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.utils.Mosby;
import org.firstinspires.ftc.teamcode.utils.Storage;

@Configurable
public class Drivetrain extends SubsystemBase {

    public Follower follower;
    public static double slow = .2;
    public PIDFController headingController;

    public static int limelightPipeline = 0;
    public static int limelightMinimumTags = 1;
    public static long limelightMaximumStalenessMs = 150;

    private final Limelight3A limelight;
    private String lastLimelightRelocalization = "not attempted";

    public Drivetrain(HardwareMap hardwareMap) {
        follower = Constants.createFollower(hardwareMap);
        follower.startTeleopDrive(true);

        headingController =
                new PIDFController(follower.constants.coefficientsHeadingPIDF);

        Limelight3A configuredLimelight = null;

        try {
            configuredLimelight =
                    hardwareMap.get(Limelight3A.class, "limelight");

            configuredLimelight.setPollRateHz(100);
            configuredLimelight.pipelineSwitch(limelightPipeline);
            configuredLimelight.start();

        } catch (IllegalArgumentException ignored) {
            // Allows modes without a configured Limelight to still run.
        }

        limelight = configuredLimelight;

        follower.update();
    }

    public void drive(Gamepad gamepad1) {
        double multiplier = gamepad1.left_bumper ? slow : 1;

        if (Storage.alliance == Mosby.Alliance.BLUE
                || Storage.alliance == Mosby.Alliance.BLUECLOSE
                || Storage.alliance == Mosby.Alliance.BLUESQ) {

            follower.setTeleOpDrive(
                    gamepad1.left_stick_y,
                    gamepad1.left_stick_x,
                    -gamepad1.right_stick_x * 0.9,
                    false
            );

        } else {

            follower.setTeleOpDrive(
                    -gamepad1.left_stick_y,
                    -gamepad1.left_stick_x,
                    -gamepad1.right_stick_x * 0.9,
                    false
            );
        }
    }

    public void update() {
        follower.update();
        Storage.pose = follower.getPose();
    }

    public boolean relocalizeFromLimelight() {

        if (limelight == null) {
            lastLimelightRelocalization = "rejected: LL not configured";
            return false;
        }

        LLResult result = limelight.getLatestResult();

        if (result == null) {
            lastLimelightRelocalization = "rejected: no result";
            return false;
        }

        if (!result.isValid()) {
            lastLimelightRelocalization = "rejected: invalid result";
            return false;
        }

        if (result.getStaleness() > limelightMaximumStalenessMs) {
            lastLimelightRelocalization =
                    "rejected: stale (" + result.getStaleness() + " ms)";
            return false;
        }

        if (result.getBotposeTagCount() < limelightMinimumTags) {
            lastLimelightRelocalization =
                    "rejected: too few tags (" +
                            result.getBotposeTagCount() + ")";
            return false;
        }

        Pose3D botpose = result.getBotpose();

        if (botpose == null) {
            lastLimelightRelocalization = "rejected: no botpose";
            return false;
        }

        Pose pedroPose = toPedroPose(botpose);

        follower.setPose(pedroPose);
        Storage.pose = pedroPose;

        lastLimelightRelocalization = String.format(
                "accepted: %.1f, %.1f, %.1f deg",
                pedroPose.getX(),
                pedroPose.getY(),
                Math.toDegrees(pedroPose.getHeading())
        );

        return true;
    }

    public void addLimelightTelemetry(Telemetry telemetry) {

        if (limelight == null) {
            telemetry.addData("LL status", "not configured");
            telemetry.addData(
                    "LL last relocalize",
                    lastLimelightRelocalization
            );
            return;
        }

        LLResult result = limelight.getLatestResult();

        telemetry.addData(
                "LL status",
                result == null
                        ? "no result"
                        : result.isValid()
                        ? "valid"
                        : "invalid"
        );

        telemetry.addData(
                "LL last relocalize",
                lastLimelightRelocalization
        );

        if (result == null) {
            return;
        }

        telemetry.addData(
                "LL staleness",
                "%d ms",
                result.getStaleness()
        );

        telemetry.addData(
                "LL tag count",
                result.getBotposeTagCount()
        );

        if (!result.isValid() || result.getBotposeTagCount() < 1) {
            telemetry.addData(
                    "LL pose",
                    "unavailable: need valid AprilTag pose"
            );
            return;
        }

        Pose3D botpose = result.getBotpose();

        if (botpose == null) {
            telemetry.addData("LL pose", "unavailable");
            return;
        }

        Position position =
                botpose.getPosition().toUnit(DistanceUnit.INCH);

        double llHeading =
                botpose.getOrientation().getYaw(AngleUnit.DEGREES);

        Pose llPedroPose = toPedroPose(botpose);
        Pose pinpointPose = follower.getPose();

        double poseError = Math.hypot(
                llPedroPose.getX() - pinpointPose.getX(),
                llPedroPose.getY() - pinpointPose.getY()
        );

        double headingError = Math.toDegrees(
                normalizeRadians(
                        llPedroPose.getHeading()
                                - pinpointPose.getHeading()
                )
        );

        double goalDistance = Math.hypot(
                llPedroPose.getX() - Mosby.goalShooter.getX(),
                llPedroPose.getY() - Mosby.goalShooter.getY()
        );

        telemetry.addData(
                "LL raw FTC pose",
                "x %.2f  y %.2f  z %.2f in",
                position.x,
                position.y,
                position.z
        );

        telemetry.addData(
                "LL raw yaw",
                "%.2f deg",
                llHeading
        );

        telemetry.addData(
                "LL Pedro pose",
                "x %.2f  y %.2f  h %.1f deg",
                llPedroPose.getX(),
                llPedroPose.getY(),
                Math.toDegrees(llPedroPose.getHeading())
        );

        telemetry.addData(
                "Pinpoint pose",
                "x %.2f  y %.2f  h %.1f deg",
                pinpointPose.getX(),
                pinpointPose.getY(),
                Math.toDegrees(pinpointPose.getHeading())
        );

        telemetry.addData(
                "LL vs Pinpoint",
                "%.2f in, heading %.1f deg",
                poseError,
                headingError
        );

        telemetry.addData(
                "LL -> shooter goal",
                "%.2f in",
                goalDistance
        );

        telemetry.addData(
                "Distance check",
                "LL %.2f / shooter %.2f / error %.2f in",
                goalDistance,
                Mosby.shooter.distance,
                goalDistance - Mosby.shooter.distance
        );

        telemetry.addData(
                "LL field-origin distance",
                "%.2f in",
                Math.hypot(position.x, position.y)
        );
    }

    private static Pose toPedroPose(Pose3D botpose) {

        Position position =
                botpose.getPosition().toUnit(DistanceUnit.INCH);

        double heading =
                botpose.getOrientation().getYaw(AngleUnit.RADIANS);

        return new Pose(
                position.x,
                position.y,
                heading,
                FTCCoordinates.INSTANCE
        ).getAsCoordinateSystem(
                PedroCoordinates.INSTANCE
        );
    }

    private static double normalizeRadians(double angle) {

        while (angle > Math.PI) {
            angle -= 2 * Math.PI;
        }

        while (angle < -Math.PI) {
            angle += 2 * Math.PI;
        }

        return angle;
    }
}