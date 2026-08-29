package org.firstinspires.ftc.teamcode.utils;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.BezierPoint;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;

public class Paths {

    public PathChain startToScore;
    public PathChain intakeGPP1;
    public PathChain intakeGPP2;
    public PathChain openGate;
    public PathChain scoreGPP;
    public PathChain intakePGP1;
    public PathChain intakePGP2;
    public PathChain scorePGP;
    public PathChain intakePPG1;
    public PathChain intakePPG2;
    public PathChain scorePPG;
    public PathChain park;
    public Pose startPose;
    public Pose shootingPose = new Pose(60,19, Math.toRadians(180));
    public Pose intakeGPP1Pose = new Pose(42, 35, Math.toRadians(180));
    public Pose intakeGPP2Pose = new Pose(21.5, 35, intakeGPP1Pose.getHeading());
    public Pose openGatePose = new Pose(14, 75, intakeGPP1Pose.getHeading());
    public Pose openGateControl = new Pose(28,77);
    public Pose intakePGP1Pose = new Pose(43.5, 60, intakeGPP1Pose.getHeading());
    public Pose intakePGP2Pose = new Pose(20, 60, intakeGPP1Pose.getHeading());
    public Pose scorePGPControl = new Pose(63, 60);
    public Pose intakePPG1Pose = new Pose(46,84, intakeGPP1Pose.getHeading());
    public Pose intakePPG2Pose = new Pose(22, 84, intakeGPP1Pose.getHeading());
    public Pose intakePPG1Control = new Pose(65, 73);
    public Pose parkPose = new Pose(42, 19, intakeGPP1Pose.getHeading());

    public PathChain startToScore1;
    public PathChain shootpre;
    public PathChain intakegpp1;
    public PathChain intakegpp2;
    public PathChain shootgpp;
    public PathChain startpgp;
    public PathChain intakepgp1;
    public PathChain checkIntakepgp1;
    public PathChain intakepgp1again;
    public PathChain gatePickup;
    public PathChain gateCheckBack;
    public PathChain gateCheckForward;
    public PathChain shootGateCheck;
    public PathChain intakepgp2;
    public PathChain intakepgp3;
    public PathChain shootpgp;
    public PathChain park2;

    public Pose shootPose = new Pose(54,19, Math.toRadians(180));
    public Pose intakegpp1Pose = new Pose(43, 34, Math.toRadians(180));
    public Pose intakegpp2Pose = new Pose(17, 34, intakegpp1Pose.getHeading());
    public Pose shootgppPose = new Pose(66,77, Math.toRadians(115));
    //public Pose startpgpPose = new Pose(13, 8, intakeGPP1Pose.getHeading());
    public Pose intakepgp1Pose = new Pose(12, 10, intakeGPP1Pose.getHeading());
    public Pose checkBackintakepgp1Pose = new Pose(15, 10, intakeGPP1Pose.getHeading());
    public Pose gateToPick = new Pose(12, 10, intakeGPP1Pose.getHeading());
    public Pose gateCheck = new Pose(15, 16, intakeGPP1Pose.getHeading());
   //public Pose intakepgp2Pose = new Pose(13,8, intakeGPP1Pose.getHeading());
   //public Pose intakepgp3Pose = new Pose(9,8,intakeGPP1Pose.getHeading());

   public Pose shootpgpPose = new Pose(63, 17, Math.toRadians(115) );

    public Pose park2Pose = new Pose(35, 9.5, intakegpp1Pose.getHeading());



    //CloseZone paths
    public PathChain ClosestartToScore;
    public PathChain CloseintakePPG;
    public PathChain ClosescorePPG;
    public PathChain CloseintakePGP1;
    public PathChain CloseintakePGP2;
    public PathChain ClosescorePGP;
    public PathChain CloseintakeGPP1;
    public PathChain CloseintakeGPP2;
    public PathChain ClosescoreGPP;
    public PathChain Closepark;

    public PathChain turn1;

    public Pose CloseshootPose = new Pose(50,85, Math.toRadians(180));
    public Pose CloseintakePPGPose = new Pose(18, 85, Math.toRadians(180));
    public Pose CloseintakePGPPose = new Pose(19, 54, Math.toRadians(180));
    public Pose CloseIntakePGPControl = new Pose(63, 53);
    public Pose CloseshootPGPPose = new Pose(53,100, Math.toRadians(200));
    public Pose CloseintakeGPP1Pose = new Pose(45,36, Math.toRadians(180));
    public Pose CloseintakeGPP2Pose = new Pose(14,36, Math.toRadians(180));
    public Pose CloseShootGPPPose = new Pose(53,100,Math.toRadians(220));
    public Pose CloseparkPose = new Pose(31, 72, Math.toRadians(180));

    //Farzone finisher

    public PathChain CollectHuman;
    public PathChain shoot1;
    public PathChain prepintake;
    public PathChain collectintake;
    public PathChain shoot2;
    public PathChain reuturn1;
    public PathChain shoot3;
    public PathChain return2;
    public PathChain shoot4;
    public PathChain park34;

    public Pose Collecthum = new Pose(10,9, Math.toRadians(180));
    public Pose shoot = new Pose(48,14, Math.toRadians(180));
    public Pose Collect1 = new Pose(40,36, Math.toRadians(180));
    public Pose Control1 = new Pose(56,34, Math.toRadians(180));
    public Pose Collect2 = new Pose(17,36, Math.toRadians(180));
    public Pose return1  = new Pose(11,14, Math.toRadians(180));
    public Pose returnsagain = new Pose(14,32, Math.toRadians(180));
    public Pose Control2 = new Pose(40,32, Math.toRadians(180));
    public Pose park344 = new Pose(40,25, Math.toRadians(170));











    public Paths(Follower follower, Mosby.Alliance alliance) {
        startPose = Mosby.startPose;
        if (alliance == Mosby.Alliance.RED || alliance == Mosby.Alliance.REDCLOSE) {
            Collecthum = Collecthum.mirror();
            shoot = shoot.mirror();
            Collect1 = Collect1.mirror();
            Control1 = Control1.mirror();
            Collect2 = Collect2.mirror();
            return1 = return1.mirror();
            returnsagain = returnsagain.mirror();
            Control2 = Control2.mirror();
            park344 = park344.mirror();

            shootingPose = shootingPose.mirror();
            intakeGPP1Pose = intakeGPP1Pose.mirror();
            intakeGPP2Pose = intakeGPP2Pose.mirror();
            openGatePose = openGatePose.mirror();
            intakePGP1Pose = intakePGP1Pose.mirror();
            intakePGP2Pose = intakePGP2Pose.mirror();
            intakePPG1Pose = intakePPG1Pose.mirror();
            intakePPG2Pose = intakePPG2Pose.mirror();
            parkPose = parkPose.mirror();
            openGateControl = openGateControl.mirror();
            scorePGPControl = scorePGPControl.mirror();
            intakePPG1Control = intakePPG1Control.mirror();

            shootPose = shootPose.mirror();
            intakegpp1Pose = intakegpp1Pose.mirror();
            intakegpp2Pose = intakegpp2Pose.mirror();
            intakepgp1Pose = intakepgp1Pose.mirror();
            shootpgpPose = shootpgpPose.mirror();
            checkBackintakepgp1Pose = checkBackintakepgp1Pose.mirror();
            gateToPick = gateToPick.mirror();
            gateCheck = gateCheck.mirror();


            CloseshootPose = CloseshootPose.mirror();
            CloseintakePPGPose = CloseintakePPGPose.mirror();
            CloseintakePGPPose = CloseintakePGPPose.mirror();
            CloseIntakePGPControl = CloseIntakePGPControl.mirror();
            CloseshootPGPPose = CloseshootPGPPose.mirror();
            CloseintakeGPP1Pose = CloseintakeGPP1Pose.mirror();
            CloseintakeGPP2Pose = CloseintakeGPP2Pose.mirror();
            CloseShootGPPPose = CloseShootGPPPose.mirror();
            CloseparkPose = CloseparkPose.mirror();







        }
        //farzone demon ball
        CollectHuman = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(startPose, Collecthum)
                )
                .setLinearHeadingInterpolation(startPose.getHeading(), Collecthum.getHeading())
                //.setBrakingStrength(1.22)
                .build();

        shoot1 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(Collecthum, shoot)
                )
                .setLinearHeadingInterpolation(Collecthum.getHeading(), shoot.getHeading())
                //.setBrakingStrength(1.22)
                .build();

        prepintake = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(shoot,Control1, Collect1)
                )
                .setLinearHeadingInterpolation(shoot.getHeading(), Collect1.getHeading())
                //.setBrakingStrength(1.22)
                .build();

        collectintake = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(Collect1, Collect2)
                )
                .setLinearHeadingInterpolation(Collect1.getHeading(), Collect2.getHeading())
                //.setBrakingStrength(1.22)
                .build();
        shoot2 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(Collect2, shoot)
                )
                .setLinearHeadingInterpolation(Collect2.getHeading(), shoot.getHeading())
                //.setBrakingStrength(1.22)
                .build();

        reuturn1 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(shoot, return1)
                )
                .setLinearHeadingInterpolation(shoot.getHeading(), return1.getHeading())
                //.setBrakingStrength(1.22)
                .build();
        shoot3 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(return1, shoot)
                )
                .setLinearHeadingInterpolation(return1.getHeading(), shoot.getHeading())
                //.setBrakingStrength(1.22)
                .build();

        return2 = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(shoot,Control2, returnsagain)
                )
                .setLinearHeadingInterpolation(shoot.getHeading(), returnsagain.getHeading())
                //.setBrakingStrength(1.22)
                .build();

        shoot4 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(returnsagain, shoot)
                )
                .setLinearHeadingInterpolation(returnsagain.getHeading(), shoot.getHeading())
                //.setBrakingStrength(1.22)
                .build();
        park34 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(shoot, park344)
                )
                .setLinearHeadingInterpolation(shoot.getHeading(), park344.getHeading())
                //.setBrakingStrength(1.22)
                .build();




















        // farzone 12 ball

        startToScore = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(startPose, shootingPose)
                )
                .setLinearHeadingInterpolation(startPose.getHeading(), shootingPose.getHeading())
                .setBrakingStrength(1.22)
                .build();

        intakeGPP1 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(shootingPose, intakeGPP1Pose)
                )
                .setLinearHeadingInterpolation(shootingPose.getHeading(), intakeGPP1Pose.getHeading())
                .setBrakingStrength(1.22)
                .build();

        intakeGPP2 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(intakeGPP1Pose, intakeGPP2Pose)
                )
                .setLinearHeadingInterpolation(intakeGPP1Pose.getHeading(), intakeGPP2Pose.getHeading())
                .setBrakingStrength(1.22)
                .build();

        scoreGPP = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(intakeGPP2Pose, shootingPose)
                )
                .setLinearHeadingInterpolation(intakeGPP2Pose.getHeading(),shootingPose.getHeading())
                .setBrakingStrength(1.22)
                .build();

        intakePGP1 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(shootingPose, intakePGP1Pose)
                )
                .setLinearHeadingInterpolation(shootingPose.getHeading(), intakePGP1Pose.getHeading())
                .setBrakingStrength(1.22)
                .build();

        intakePGP2 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(intakePGP1Pose, intakePGP2Pose)
                )
                .setLinearHeadingInterpolation(intakePGP1Pose.getHeading(), intakePGP2Pose.getHeading())
                .setBrakingStrength(1.22)
                .build();

        scorePGP = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                intakePGP2Pose,
                                scorePGPControl,
                                shootingPose
                        )
                )
                .setLinearHeadingInterpolation(intakePGP2Pose.getHeading(),shootingPose.getHeading())
                .setBrakingStrength(1.22)
                .build();

        intakePPG1 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(shootingPose, intakePPG1Pose)
                )
                .setBrakingStrength(3.5)
//                .setTimeoutConstraint(500)
                .setLinearHeadingInterpolation(shootingPose.getHeading(), intakePPG1Pose.getHeading())
                .setBrakingStrength(1.22)
                .build();

        intakePPG2 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(intakePPG1Pose, intakePPG2Pose)
                )
                .setLinearHeadingInterpolation(intakePPG1Pose.getHeading(), intakePPG2Pose.getHeading())
                .setBrakingStrength(1.22)
                .build();

        scorePPG = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(intakePPG2Pose, scorePGPControl, shootingPose)
                )
                .setBrakingStrength(3.5)
                .setLinearHeadingInterpolation(intakePPG2Pose.getHeading(),shootingPose.getHeading())
                .build();

        park = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(shootingPose, parkPose)
                )
                .setLinearHeadingInterpolation(shootingPose.getHeading(),parkPose.getHeading())
                .setBrakingStrength(1.22)
                .build();














// baryons auto


        startToScore1 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(startPose, shootPose)
                )
                .setLinearHeadingInterpolation(startPose.getHeading(), shootPose.getHeading())
                .setBrakingStrength(1.22)
                .build();

        intakegpp1 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(shootPose, intakegpp1Pose)
                )
                .setLinearHeadingInterpolation(shootPose.getHeading(), intakegpp1Pose.getHeading())
                .setBrakingStrength(1.22)
                .build();

        intakegpp2 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(intakegpp1Pose, intakegpp2Pose)
                )
                .setLinearHeadingInterpolation(intakegpp1Pose.getHeading(), intakegpp2Pose.getHeading())
                .setBrakingStrength(1.22)
                .build();

        shootgpp = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(intakegpp2Pose, shootPose)
                )
                .setLinearHeadingInterpolation(intakegpp2Pose.getHeading(),shootPose.getHeading())
                .setBrakingStrength(1.22)
                .build();

        intakepgp1 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(shootPose, intakepgp1Pose)
                )
                .setLinearHeadingInterpolation(shootPose.getHeading(), intakepgp1Pose.getHeading())
                .setBrakingStrength(1.22)
                .build();
        checkIntakepgp1 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(intakepgp1Pose, checkBackintakepgp1Pose)
                )
                .setLinearHeadingInterpolation(intakepgp1Pose.getHeading(), checkBackintakepgp1Pose.getHeading())
                .setBrakingStrength(1.22)
                .build();
        intakepgp1again = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(checkBackintakepgp1Pose, intakepgp1Pose)
                )
                .setLinearHeadingInterpolation(checkBackintakepgp1Pose.getHeading(), intakepgp1Pose.getHeading())
                .setBrakingStrength(1.22)
                .build();



        shootpgp = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(intakepgp1Pose,shootpgpPose)
                )
                .setBrakingStrength(3.5)
//                .setTimeoutConstraint(500)
                .setLinearHeadingInterpolation(intakepgp1Pose.getHeading(), shootPose.getHeading())
                .build();
        gatePickup = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(shootPose, gateToPick)
                )
                .setLinearHeadingInterpolation(shootPose.getHeading(), gateToPick.getHeading())
                .setBrakingStrength(1.22)
                .build();
        gateCheckBack = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(gateToPick, gateCheck)
                )
                .setLinearHeadingInterpolation(gateToPick.getHeading(), gateCheck.getHeading())
                .setBrakingStrength(1.22)
                .build();
        gateCheckForward = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(gateCheck, gateToPick)
                )
                .setLinearHeadingInterpolation(gateCheck.getHeading(), gateToPick.getHeading())
                .setBrakingStrength(1.22)
                .build();
        shootGateCheck = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(gateToPick, shootPose)
                )
                .setLinearHeadingInterpolation(gateToPick.getHeading(), shootPose.getHeading())
                .setBrakingStrength(1.22)
                .build();






        park2 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(shootPose, parkPose)
                )
                .setLinearHeadingInterpolation(shootPose.getHeading(),parkPose.getHeading())
                .setBrakingStrength(1.22)
                .build();


        // Close zone

        ClosestartToScore = follower
                .pathBuilder()
                .addPath(new BezierLine(startPose, CloseshootPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), CloseshootPose.getHeading())
                .setBrakingStrength(1.22)
                .build();


        CloseintakePPG = follower
                .pathBuilder()
                .addPath(new BezierLine(CloseshootPose, CloseintakePPGPose))
                .setLinearHeadingInterpolation(CloseshootPose.getHeading(), CloseintakePPGPose.getHeading())
                .setBrakingStrength(1.22)
                .build();


        ClosescorePPG = follower
                .pathBuilder()
                .addPath(new BezierLine(CloseintakePPGPose, CloseshootPose))
                .setLinearHeadingInterpolation(CloseintakePPGPose.getHeading(), CloseshootPose.getHeading())
                .setBrakingStrength(1.22)
                .build();


        CloseintakePGP1 = follower
                .pathBuilder()
                .addPath(new BezierCurve(CloseshootPose, CloseIntakePGPControl, CloseintakePGPPose))
                .setLinearHeadingInterpolation(CloseshootPose.getHeading(), CloseintakePGPPose.getHeading())
                .setBrakingStrength(1.22)
                .build();


        turn1 = follower
                .pathBuilder()
                .addPath(new BezierPoint(CloseintakePGPPose.getX(), CloseintakePGPPose.getY()))
                .setConstantHeadingInterpolation(CloseintakePGPPose.getHeading())
                .build();


        ClosescorePGP = follower
                .pathBuilder()
                .addPath(new BezierLine(CloseintakePGPPose, CloseshootPGPPose))
                .setLinearHeadingInterpolation(CloseintakePGPPose.getHeading(), CloseshootPGPPose.getHeading())
                .setBrakingStrength(1.22)
                .build();


        CloseintakeGPP1 = follower
                .pathBuilder()
                .addPath(new BezierLine(CloseshootPGPPose, CloseintakeGPP1Pose))
                .setLinearHeadingInterpolation(CloseshootPGPPose.getHeading(), CloseintakeGPP1Pose.getHeading())
                .setBrakingStrength(1.22)
                .build();


        CloseintakeGPP2 = follower
                .pathBuilder()
                .addPath(new BezierLine(CloseintakeGPP1Pose, CloseintakeGPP2Pose))
                .setLinearHeadingInterpolation(CloseintakeGPP1Pose.getHeading(), CloseintakeGPP2Pose.getHeading())
                .setBrakingStrength(1.22)
                .build();


        ClosescoreGPP = follower
                .pathBuilder()
                .addPath(new BezierLine(CloseintakeGPP2Pose, CloseShootGPPPose))
                .setLinearHeadingInterpolation(CloseintakeGPP2Pose.getHeading(), CloseShootGPPPose.getHeading())
                .setBrakingStrength(1.22)
                .build();


        Closepark = follower
                .pathBuilder()
                .addPath(new BezierLine(CloseShootGPPPose, CloseparkPose))
                .setLinearHeadingInterpolation(CloseShootGPPPose.getHeading(), CloseparkPose.getHeading())
                .setBrakingStrength(1.22)
                .build();

    }
}