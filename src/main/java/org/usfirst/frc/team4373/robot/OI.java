package org.usfirst.frc.team4373.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team4373.robot.commands.teleop.OtherCommand;
import org.usfirst.frc.team4373.robot.input.filter.PiecewiseFilter2;
import org.usfirst.frc.team4373.robot.input.hid.RooJoystick;

/**
 * OI encapsulates various inputs and outputs.
 * @author Henry Pitcairn
 */
public class OI {
    private static OI oi = null;

    public static OI getOI() {
        oi = oi == null ? new OI() : oi;
        return oi;
    }

    private RooJoystick driveJoystick;
    private Button button3;

    private OI() {
        this.driveJoystick = new RooJoystick(RobotMap.DRIVE_JOYSTICK_PORT, new PiecewiseFilter2());
        this.button3 = new JoystickButton(driveJoystick, 3);
        button3.whenPressed(new OtherCommand());
    }

    public RooJoystick getDriveJoystick() {
        return this.driveJoystick;
    }
}
