package org.firstinspires.ftc.teamcode.opmodes.auto;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.utils.Paths;
import org.firstinspires.ftc.teamcode.utils.Mosby;

@Autonomous(preselectTeleOp="TeleOp")
public class FarBlue extends CommandOpMode {

    Paths paths;

    @Override
    public void initialize() {

        Mosby.init(hardwareMap, Mosby.MatchState.AUTO, Mosby.Alliance.BLUE);
        paths = new Paths(Mosby.drivetrain.follower, Mosby.Alliance.BLUE);

        Mosby.drivetrain.follower.setMaxPower(1);

        schedule(new SequentialCommandGroup(
                Mosby.prime(),
                new WaitUntilCommand(() -> Mosby.shooter.controller.atSetPoint()),
                Mosby.shootWithIntake(),
                Mosby.reset(),

                // human player
                new InstantCommand(() -> Mosby.intake.setPower(1)),
                new InstantCommand(() -> Mosby.drivetrain.follower.setMaxPower(1)),
                new FollowPathCommand(Mosby.drivetrain.follower, paths.CollectHuman),
                new InstantCommand(() -> Mosby.intake.setPower(0)),

                // shoot 1
                new FollowPathCommand(Mosby.drivetrain.follower, paths.shoot1),
                Mosby.prime(),
                new WaitUntilCommand(() -> Mosby.shooter.controller.atSetPoint()),
                //new WaitCommand(100),
                Mosby.shootWithIntake(),
                Mosby.reset(),

                // prep intake
                new InstantCommand(() -> Mosby.intake.setPower(1)),
                new InstantCommand(() -> Mosby.drivetrain.follower.setMaxPower(1)),
                new FollowPathCommand(Mosby.drivetrain.follower, paths.prepintake),

                // collect intake
                new FollowPathCommand(Mosby.drivetrain.follower, paths.collectintake),
                new WaitCommand(60),
                new InstantCommand(() -> Mosby.intake.setPower(0)),

                // shoot 2
                new FollowPathCommand(Mosby.drivetrain.follower, paths.shoot2),
                Mosby.prime(),
                new WaitUntilCommand(() -> Mosby.shooter.controller.atSetPoint()),
                new WaitCommand(100),
                Mosby.shootWithIntake(),
                Mosby.reset(),

                // return 1
                new InstantCommand(() -> Mosby.intake.setPower(1)),
                new InstantCommand(() -> Mosby.drivetrain.follower.setMaxPower(1)),
                new FollowPathCommand(Mosby.drivetrain.follower, paths.reuturn1),
                new WaitCommand(200),
                new InstantCommand(() -> Mosby.intake.setPower(0)),

                // shoot 3
                new FollowPathCommand(Mosby.drivetrain.follower, paths.shoot3),
                Mosby.prime(),
                new WaitUntilCommand(() -> Mosby.shooter.controller.atSetPoint()),
                new WaitCommand(100),
                Mosby.shootWithIntake(),
                Mosby.reset(),

                // return 2
                new InstantCommand(() -> Mosby.intake.setPower(1)),
                new InstantCommand(() -> Mosby.drivetrain.follower.setMaxPower(1)),
                new FollowPathCommand(Mosby.drivetrain.follower, paths.return2),
                new WaitCommand(300),
                new InstantCommand(() -> Mosby.intake.setPower(0)),

                // shoot 4
                new FollowPathCommand(Mosby.drivetrain.follower, paths.shoot4),
                Mosby.prime(),
                new WaitUntilCommand(() -> Mosby.shooter.controller.atSetPoint()),
                new WaitCommand(100),
                Mosby.shootWithIntake(),
                Mosby.reset(),

                // turn everything off
                new FollowPathCommand(Mosby.drivetrain.follower, paths.park34)


        ));
    }

    @Override
    public void run() {
        super.run();

        Mosby.update();

        telemetry.addData("turret angle", Math.toDegrees(Mosby.turret.getAngle()));
        telemetry.addData("setpoint", Mosby.turret.controller.getSetPoint());
        telemetry.addData("goal", Mosby.goal);
        telemetry.addData("shooter current velocity", Mosby.shooter.getVelocity());
        telemetry.addData("shooter set Velocity", Mosby.shooter.power);
        telemetry.update();
    }
}


