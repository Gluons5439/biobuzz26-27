# FTC robot code map

This guide describes the repository's default `master` branch at commit `6053fe7` (reviewed September 27, 2026). It is a map of the checked-in source, not proof that an OpMode, control mapping, route, or mechanism is approved for competition or works on the physical robot.

## Team and season context

The GitHub repository description calls this the Gluons' 2026–27 robot code, but the root README still says DECODE and the source contains inherited Decode-era names and field poses. FIRST's Team 5439 page identifies the team as The GLUONS from Chesterfield, Missouri, and lists BIOBUZZ as the 2026 season. That does not establish that the routes in this checkout have been updated or validated for BIOBUZZ.

The repository does not document the current student roster or assign driving, programming, or subsystem roles. In `TeleOp.java`, the local variables named `rishi` and `aaryan` are wrappers around gamepad 1 and gamepad 2; the names are not evidence of who is assigned to drive.

## Where to start in the source

| Area | What it contains | Start here |
| --- | --- | --- |
| OpModes | FTC entry points selectable in the Driver Station | [`opmodes/teleop`](../TeamCode/src/main/java/org/firstinspires/ftc/teamcode/opmodes/teleop), [`opmodes/auto`](../TeamCode/src/main/java/org/firstinspires/ftc/teamcode/opmodes/auto), [`opmodes/testing`](../TeamCode/src/main/java/org/firstinspires/ftc/teamcode/opmodes/testing) |
| Robot coordination | Hardware initialization, periodic updates, alliance state, and reusable commands | [`Mosby.java`](../TeamCode/src/main/java/org/firstinspires/ftc/teamcode/utils/Mosby.java) |
| Mechanism subsystems | Drive, intake, shooter, and turret behavior | [`utils/subsystems`](../TeamCode/src/main/java/org/firstinspires/ftc/teamcode/utils/subsystems) |
| Pedro Pathing | Follower/localizer/mecanum configuration and Java path definitions | [`pedro/Constants.java`](../TeamCode/src/main/java/org/firstinspires/ftc/teamcode/pedro/Constants.java), [`Paths.java`](../TeamCode/src/main/java/org/firstinspires/ftc/teamcode/utils/Paths.java), [`Paths2.java`](../TeamCode/src/main/java/org/firstinspires/ftc/teamcode/utils/Paths2.java) |
| Scarlet | Separate custom follower and its tuning OpModes | [`scarlet`](../TeamCode/src/main/java/org/firstinspires/ftc/teamcode/scarlet) |

`TeleOp.java` binds buttons to commands from `Mosby`, then its loop updates the robot, sends drive input to the drivetrain, and writes trigger input to the intake. When tracing a control, follow both files: the button binding explains *when* a command starts; the command or subsystem explains *what* it does.

## Pathing: Pedro is the match-code path

On `master`, the normal autonomous route code uses Pedro Pathing: `pedro/Constants.java` builds a Pedro `Follower` with a mecanum drive and GoBilda Pinpoint localizer, while `utils/Paths.java` and `Paths2.java` build `PathChain`s used by autonomous OpModes. Main TeleOp also creates and updates this Pedro follower through the drivetrain subsystem.

The project pins Pedro FTC `2.1.0` in [`build.dependencies.gradle`](../build.dependencies.gradle). The Pedro website now advertises Pedro 3, so its current examples may not match this checkout's pinned API. Check the project's dependency version before copying examples.

## Scarlet status

Scarlet is present as a custom single-target follower prototype. Its [`Follower.java`](../TeamCode/src/main/java/org/firstinspires/ftc/teamcode/scarlet/Follower.java) reads position from GoBilda Pinpoint, computes translation and heading corrections toward one target pose, and writes power to four mecanum motors. The code exposes controller choices and constants for tuning.

In the reviewed `master` snapshot, Scarlet is instantiated only by the two tuning OpModes below. The main TeleOp and the match autonomous classes use the separate Pedro implementation; they do not call Scarlet. Treat Scarlet as experimental test code, not as the robot's completed or competition pathing system.

| Scarlet OpMode | Gamepad 1 input | Source behavior |
| --- | --- | --- |
| `ForwardBack` | Triangle | Set target pose to `(25, 0, 0)` |
| `ForwardBack` | Cross | Set target pose to `(0, 0, 0)` |
| `TurnTuner` | D-pad right / left | Hold the current Pinpoint X/Y and target heading `0` / `π/2` |
| `TurnTuner` | Circle / square | Hold current X/Y and target heading `π` / `3π/2` |
| `TurnTuner` | PS button | Set target pose to `(0, 0, 0)` |

These controls are for the Scarlet tuning OpModes only. They are not the main TeleOp controls.

## Main `master` TeleOp controls

This table describes the source bindings in [`TeleOp.java`](../TeamCode/src/main/java/org/firstinspires/ftc/teamcode/opmodes/teleop/TeleOp.java). “Driver 1” means `gamepad1`; “Driver 2” means `gamepad2`.

| Controller | Input | Source behavior |
| --- | --- | --- |
| Driver 1 | Left stick | Drive and strafe; the drivetrain flips the translation signs for red-side alliances. |
| Driver 1 | Right-stick X | Turn; the source scales this axis by `0.9`. |
| Driver 1 | B | Set Pedro's estimated pose and stored pose to a hard-coded alliance pose. This changes software localization; it does not move the robot. |
| Driver 1 | Left bumper | Intended slow-drive input, but the computed `0.2` multiplier is never applied to the Pedro drive command in this snapshot. Slow mode is not implemented by this code path. |
| Either driver | Triggers | Set intake power to `right trigger - left trigger`. The controller with the larger absolute net trigger input wins; if magnitudes tie, gamepad 2 wins. |
| Driver 2 | B | Run `Mosby.prime()`: enable turret aim, clear intake power, and enable the shooter's automatic power/hood behavior. |
| Driver 2 | Right bumper | Run `Mosby.shoot()`: wait for the shooter controller to reach its setpoint, then run the transfer/intake firing sequence. |
| Driver 2 | Left bumper | Run `Mosby.shootWithIntake()`: the alternate firing sequence that feeds with the intake. |
| Driver 2 | A | Cancel the three local prime/shoot command objects and run `Mosby.reset()`. |
| Driver 2 | Y | Call `shooter.autoPower(false, false)`, which turns off automatic power and sets its controller target to zero. |
| Driver 2 | D-pad left / right | Adjust turret `homePos` by `+0.0875` / `-0.0875` per press. |
| Driver 2 | Share | Re-run `Mosby.init(...)` for TeleOp. |

## Other branch: `Test1`

`Test1` is a non-default, experimental branch (tip `fd2629f`, August 29, 2026), not the code described by the main control table. Its TeleOp moves prime/shoot/shoot-with-intake to gamepad 1 X/right bumper/left bumper and puts turret-home adjustment on gamepad 1 D-pad.

Before using that branch, note two source details: its gamepad 1 Y binding constructs `Mosby.reset1()` inside an `InstantCommand` but does not schedule the returned reset command; and its gamepad 1 B pose-angle calculation uses the goal's X coordinate for both `dx` and `dy`. The Limelight relocalization binding is commented out. These are reasons to review the branch itself before testing it, not to assume its controls match `master`.

## Dependencies and further reading

The checked-in dependency files pin FTC SDK `11.1.0`, Pedro FTC `2.1.0`, and SolversLib `0.3.3`. The links below were shared as study/reference material; example code may target an older game or different library version and does not specify Scarlet's intended behavior.

- [Pedro Pathing](https://pedropathing.com/)
- [Seattle Solvers documentation](https://docs.seattlesolvers.com/)
- [GoldenDragons772 / Decode](https://github.com/GoldenDragons772/Decode)
- [MOEbo-Sapiens / MOEbo-Sapiens-Decode](https://github.com/MOEbo-Sapiens/MOEbo-Sapiens-Decode)
- [ApexPathing / main](https://github.com/ApexPathing/main)
- [FTC-23511 / Decode-2026](https://github.com/FTC-23511/Decode-2026)
- [FIRST FTC Team 5439 page](https://ftc-events.firstinspires.org/2025/team/5439)

## Verification boundary

This guide was checked against the named Git commits and source files. It has not been validated by building the Android app, selecting these OpModes on a Robot Controller, or testing a physical robot. Confirm hardware names, route coordinates, alliance poses, button assignments, and all safety behavior with the team before a match.
