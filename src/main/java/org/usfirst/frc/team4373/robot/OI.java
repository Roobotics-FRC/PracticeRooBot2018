package org.usfirst.frc.team4373.robot;

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

    private OI() {
        this.driveJoystick = new RooJoystick(RobotMap.DRIVE_JOYSTICK_PORT, new PiecewiseFilter2());
    }

    public RooJoystick getDriveJoystick() {
        return this.driveJoystick;
    }
}
