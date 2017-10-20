package org.usfirst.frc.team4373.robot.commands.teleop;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4373.robot.subsystems.MecanumDrive;

public class OtherCommand extends Command {
    private MecanumDrive drive;

    public OtherCommand() {
        requires(this.drive = MecanumDrive.getInstance());
        setTimeout(5);
    }

    @Override
    protected void initialize() {
        System.out.println("Other Command Started");

    }

    @Override
    protected void execute() {
        this.drive.mecanumDrive_Cartesian(0.1, 0.1, 0.2, 0.0);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {

    }
}
