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


//The notes I add help me out, they may be redundant, but they help

public class Intake extends Subsystem {

  //constructor

  //Setting intake motor for wrist
  public Intake() {
    //No need for constant
    

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void setWristSpeed(double speed){
    
  }

  public void setWristPosition(int ticks){
    //WILL, AGAIN, I DON'T WANT TICKS IN THE WORKSHOP

  }

  public void lowerBeak(){
    
  }

  public void raiseBeak(){

  }

  public void pushPistons(){

  }

  public void pullPistons(){

  }

  public void setUpperWheelSpeed(double speed){
    
  } 

  public void setLowerWheelSpeed(double speed){
    
  }

  public void lowerFoot(){
    
  }

  public void raiseFoot(){
    
  }

}
