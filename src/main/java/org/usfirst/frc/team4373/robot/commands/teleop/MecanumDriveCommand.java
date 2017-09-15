package org.usfirst.frc.team4373.robot.commands.teleop;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4373.robot.OI;
import org.usfirst.frc.team4373.robot.RobotMap;
import org.usfirst.frc.team4373.robot.subsystems.MecanumDrive;

/**
 * Created by jrr6 on 5/26/17.
 */
public class MecanumDriveCommand extends Command {
    private MecanumDrive drive;

    private static MecanumDriveCommand driveCommand = null;

    public static MecanumDriveCommand getInstance() {
        return driveCommand == null ? new MecanumDriveCommand() : driveCommand;
    }

    private MecanumDriveCommand() {
        this.drive = MecanumDrive.getInstance();
        requires(this.drive);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        double x = OI.getOI().getDriveJoystick().getAxis(RobotMap.DRIVE_JOYSTICK_HORIZONTAL_AXIS);
        double y = OI.getOI().getDriveJoystick().getAxis(RobotMap.DRIVE_JOYSTICK_FORWARD_AXIS);
        double rot = OI.getOI().getDriveJoystick().getAxis(RobotMap.DRIVE_JOYSTICK_TWIST_AXIS);
        drive.mecanumDrive_Cartesian(x, y, rot, 0);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {

    }
}
