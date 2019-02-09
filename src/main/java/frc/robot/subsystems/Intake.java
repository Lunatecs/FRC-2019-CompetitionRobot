/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


public class Intake extends Subsystem {

  private TalonSRX intakePivot = new TalonSRX(RobotMap.INTAKE_TALON_CAN_ID);
  private AnalogInput ai = new AnalogInput(3);
  private AnalogPotentiometer potentiometer = new AnalogPotentiometer(ai, 1080, 30);
  
  //constructor
  public Intake() {
    //No need for constant
    intakePivot.setNeutralMode(NeutralMode.Brake);
  }

  public void setSpeed(double speed) {
    intakePivot.set(ControlMode.PercentOutput, speed); 
  }

  public double getAngle() { 
    return potentiometer.get();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
