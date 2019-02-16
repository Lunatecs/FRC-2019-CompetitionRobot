/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.wrist.WristWithJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Add your docs here.
 */
public class Wrist extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public TalonSRX intakeWrist = new TalonSRX(RobotMap.INTAKE_WRIST_CONTROLLER_T_ID);
  public NeutralMode WRIST_BRAKE_MODE = NeutralMode.Brake;
  public double DEADZONE = 0.1;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
   // setDefaultCommand(new WristWithJoystick());
  }

  public Wrist(){
    intakeWrist.configFactoryDefault();

    intakeWrist.setNeutralMode(WRIST_BRAKE_MODE);

  }

  public void setWristSpeed(double speed){
    if(Math.abs(speed)> DEADZONE){
      intakeWrist.set(ControlMode.PercentOutput, speed);
    }
  }

  public void setWristPosition(int ticks){
    //WILL, AGAIN, I DON'T WANT TICKS IN THE WORKSHOP
      intakeWrist.set(ControlMode.Position, ticks);

  }

}
