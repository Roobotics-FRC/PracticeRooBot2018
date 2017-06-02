package org.usfirst.frc.team4373.robot.commands.teleop;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4373.robot.OI;
import org.usfirst.frc.team4373.robot.subsystems.MecanumDrive;

/**
 * Created by jrr6 on 5/26/17.
 */
public class MecanumDriveCommand extends Command {
    private MecanumDrive drive;

    @Override
    protected void initialize() {
        requires(this.drive = MecanumDrive.getInstance());
    }

    @Override
    protected void execute() {
        double x = OI.getOI().getDriveJoystick().getAxis(0);
        double y = OI.getOI().getDriveJoystick().getAxis(1);
        double rot = OI.getOI().getDriveJoystick().getAxis(2);
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
