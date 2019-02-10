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
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

//The notes I add help me out, they may be redundant, but they help


//Three talons
//Three double soleniod 

public class Intake extends Subsystem {

  //constructor

  public TalonSRX upperWheel= new TalonSRX(RobotMap.INTAKE_TOP_CONTROLLER_T_ID),
                  lowerWheel = new TalonSRX(RobotMap.INTAKE_BOTTOM_CONTROLLER_T_ID),
                  intakeWrist = new TalonSRX(RobotMap.INTAKE_WRIST_CONTROLLER_T_ID);

  public DoubleSolenoid footSolenoid = new DoubleSolenoid(0, 0),
                        fangsSolenoid = new DoubleSolenoid(0, 0),
                        beakSolenoid = new DoubleSolenoid(0, 0);

  public NeutralMode WHEELS_BRAKE_MODE = NeutralMode.Brake;
  public NeutralMode WRIST_BRAKE_MODE = NeutralMode.Brake;
  //Setting intake motor for wrist
  public Intake() {
    //No need for constant
    upperWheel.configFactoryDefault();
    lowerWheel.configFactoryDefault();
    intakeWrist.configFactoryDefault();

    upperWheel.setNeutralMode(WHEELS_BRAKE_MODE);
    lowerWheel.setNeutralMode(WHEELS_BRAKE_MODE);
    intakeWrist.setNeutralMode(WRIST_BRAKE_MODE);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void setWristSpeed(double speed){
    intakeWrist.set(ControlMode.PercentOutput, speed);
  }

  public void setWristPosition(int ticks){
    //WILL, AGAIN, I DON'T WANT TICKS IN THE WORKSHOP
    intakeWrist.set(ControlMode.Position, ticks);
  }

  public void lowerBeak(){
    beakSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void raiseBeak(){
    beakSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void pushPistons(){
    fangsSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void pullPistons(){
    fangsSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void setUpperWheelSpeed(double speed){
    upperWheel.set(ControlMode.PercentOutput, speed);
  } 

  public void setLowerWheelSpeed(double speed){
    lowerWheel.set(ControlMode.PercentOutput, speed);
  }

  public void lowerFoot(){
    footSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void raiseFoot(){
    footSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

}
