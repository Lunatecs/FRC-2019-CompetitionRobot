/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.wrist.WristWithJoystick;
import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

/**
 * Add your docs here.
 */
public class Wrist extends Subsystem {

  //TODO change names to their uses ie. cargoship, etc.
  //TODO add offset to potentiometer and change corresponding angles.
  public static final double NINETY_DEGREE = 241.0; //325.0;
  public static final double FORTY_FIVE_DEGREE = 198.0; //280.0;
  public static final double ZERO_DEGREE = 155.0; //240.0;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  //public TalonSRX intakeWrist = new TalonSRX(RobotMap.INTAKE_WRIST_CONTROLLER_T_ID);
  public TalonSRX intakeWrist = new TalonSRX(RobotMap.INTAKE_WRIST_CONTROLLER_T_ID);
  public NeutralMode WRIST_BRAKE_MODE = NeutralMode.Brake;
  public double DEADZONE = 0.1;
  AnalogInput in = new AnalogInput(0);
  AnalogPotentiometer pot = new AnalogPotentiometer(in,1080,0);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
   setDefaultCommand(new WristWithJoystick());
  }

  public Wrist(){
    intakeWrist.configFactoryDefault();

    intakeWrist.setNeutralMode(WRIST_BRAKE_MODE);
    intakeWrist.config_kP(0, 3.0, 10);
   // intakeWrist.config_kI(0, 0.00024,10);
    intakeWrist.config_kD(0, 10.0,10);

    intakeWrist.setSensorPhase(true);
    intakeWrist.configPeakOutputForward(.25, 10);
    intakeWrist.configPeakOutputReverse(-.25, 10);
    //intakeWrist.configContinuousCurrentLimit(amps,10);
    intakeWrist.configVoltageCompSaturation(2.7);
  }

  int currentPos = 0;
  boolean lowVoltage = true;

  public void setWristSpeed(double speed){
    if(Math.abs(speed)> DEADZONE){
      if(lowVoltage) {
        intakeWrist.configVoltageCompSaturation(12.0);
        lowVoltage=false;
      }
      intakeWrist.set(ControlMode.PercentOutput, speed);
      currentPos = intakeWrist.getSelectedSensorPosition(0);
    } else {
     // if(pot.get()>210) {
     if(pot.get()>115) {
      if(!lowVoltage) {
          intakeWrist.configVoltageCompSaturation(2.7);
          lowVoltage=true;
        }
        intakeWrist.set(ControlMode.Position, currentPos);
      } else {
        intakeWrist.set(ControlMode.PercentOutput, 0.0);
      }
    }
    SmartDashboard.putString("Wrist Position (Current/Set)", intakeWrist.getSelectedSensorPosition(0) + "/" + currentPos);
    SmartDashboard.putNumber("Potentiometer Get", pot.get());
    SmartDashboard.putBoolean("Low Valtage", lowVoltage);
    SmartDashboard.putNumber("Wrist Speed", speed);
  }

  public void setWristPosition(int ticks){
    //WILL, AGAIN, I DON'T WANT TICKS IN THE WORKSHOP
      intakeWrist.set(ControlMode.Position, ticks);

  }

  public double getPosition() {
    return pot.get();
  }
}
