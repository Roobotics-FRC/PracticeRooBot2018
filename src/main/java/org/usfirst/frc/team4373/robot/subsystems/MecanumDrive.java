package org.usfirst.frc.team4373.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4373.robot.RobotMap;
import org.usfirst.frc.team4373.robot.commands.teleop.MecanumDriveCommand;

/**
 * Modified version of WPILib's RobotDrive mecanum code.
 */

public class MecanumDrive extends Subsystem {

    private CANTalon frontLeftTalon;
    private CANTalon rearLeftTalon;
    private CANTalon frontRightTalon;
    private CANTalon rearRightTalon;

    public static MecanumDrive mecanumDrive = null;

    public static MecanumDrive getInstance() {
        return mecanumDrive == null ? mecanumDrive = new MecanumDrive() : mecanumDrive;
    }

    private MecanumDrive() {
        this.frontLeftTalon = new CANTalon(RobotMap.LEFT_DRIVE_MOTOR_1);
        this.rearLeftTalon = new CANTalon(RobotMap.LEFT_DRIVE_MOTOR_2);
        this.frontRightTalon = new CANTalon(RobotMap.RIGHT_DRIVE_MOTOR_1);
        this.rearRightTalon = new CANTalon(RobotMap.RIGHT_DRIVE_MOTOR_2);
    }

    /**
     * Drive using mecanum to specified coordinates.
     * @param horizontal X-coordinate.
     * @param vertical Y-coordinate.
     * @param rotation Rotation amount.
     * @param gyroAngle Gyro input (for offset correction—currently not used).
     */
    public void mecanumDrive_Cartesian(double horizontal, double vertical,
                                       double rotation, double gyroAngle) {
        double xIn = horizontal;
        double yIn = vertical;
        // Negate y for the joystick.
        yIn = -yIn;
        // Compensate for gyro angle.
        double[] rotated = rotateVector(xIn, yIn, gyroAngle);
        xIn = rotated[0];
        yIn = rotated[1];

        double frontDamp = SmartDashboard.getNumber("Front Damp", 1);
        double rearDamp = SmartDashboard.getNumber("Rear Damp", 1);

        double[] wheelSpeeds = new double[4];
        wheelSpeeds[RobotDrive.MotorType.kFrontLeft.value] = -(xIn + yIn + rotation * frontDamp);
        wheelSpeeds[RobotDrive.MotorType.kFrontRight.value] = -xIn + yIn - rotation * frontDamp;
        wheelSpeeds[RobotDrive.MotorType.kRearLeft.value] = -xIn + yIn + rotation * rearDamp;
        wheelSpeeds[RobotDrive.MotorType.kRearRight.value] = -(xIn + yIn - rotation * rearDamp);

        normalize(wheelSpeeds);
        frontLeftTalon.set(wheelSpeeds[RobotDrive.MotorType.kFrontLeft.value]);
        frontRightTalon.set(wheelSpeeds[RobotDrive.MotorType.kFrontRight.value]);
        rearLeftTalon.set(wheelSpeeds[RobotDrive.MotorType.kRearLeft.value]);
        rearRightTalon.set(wheelSpeeds[RobotDrive.MotorType.kRearRight.value]);
    }

    protected static void normalize(double[] wheelSpeeds) {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);
        for (int i = 1; i < 4; i++) {
            double temp = Math.abs(wheelSpeeds[i]);
            if (maxMagnitude < temp) {
                maxMagnitude = temp;
            }
        }
        if (maxMagnitude > 1.0) {
            for (int i = 0; i < 4; i++) {
                wheelSpeeds[i] = wheelSpeeds[i] / maxMagnitude;
            }
        }
    }

    protected static double[] rotateVector(double horizontal, double vertical, double angle) {
        double cosA = Math.cos(angle * (3.14159 / 180.0));
        double sinA = Math.sin(angle * (3.14159 / 180.0));
        double[] out = new double[2];
        out[0] = horizontal * cosA - vertical * sinA;
        out[1] = horizontal * sinA + vertical * cosA;
        return out;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(MecanumDriveCommand.getInstance());
    }
}
