package org.usfirst.frc.team4373.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4373.robot.input.hid.RooJoystick;
import org.usfirst.frc.team4373.robot.subsystems.MecanumDrive;

/**
 * This is the main robot class.
 */
public class Robot extends IterativeRobot {
    private RobotDrive drive;
    private RooJoystick joystick;

    @Override
    public void robotInit() {
        SmartDashboard.putBoolean("Running", true);
        SmartDashboard.putNumber("Forward Damp", 0);
        SmartDashboard.putNumber("Rear Damp", 0);
        // this.drive = new RobotDrive(RobotMap.LEFT_DRIVE_MOTOR_1, RobotMap.LEFT_DRIVE_MOTOR_2,
        //         RobotMap.RIGHT_DRIVE_MOTOR_1, RobotMap.RIGHT_DRIVE_MOTOR_2);
        MecanumDrive.getInstance();
        this.joystick = OI.getOI().getDriveJoystick();
    }

    @Override
    public void teleopInit() {
        super.teleopInit();
    }

    @Override
    public void autonomousInit() {
        super.autonomousInit();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        // this.drive.mecanumDrive_Cartesian(joystick.getAxis(0),
        // joystick.getAxis(1), joystick.getAxis(2), 0);
    }
}
