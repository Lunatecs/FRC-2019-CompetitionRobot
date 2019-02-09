/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.elevator.ElevatorWithJoystick;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private VictorSPX follower = new VictorSPX(RobotMap.ELEVATOR_FOLLOW_CONTROLLER_V_ID);
  public TalonSRX leader = new TalonSRX(RobotMap.ELEVATOR_LEAD_CONTROLLER_T_ID);
  private static NeutralMode ELEVATOR_NEUTRAL_MODE = NeutralMode.Brake;

  // this is for going up
  private static double PEAK_FORWARD = 1.0;

  // this is for going down
  private static double PEAK_BACKWARD = 1.0;
 
  private static int TIMEOUT = 10;
  private static double DEADZONE = .1;
  public static double kP = 0.13;

  public Elevator(){
    leader.configFactoryDefault();
    follower.configFactoryDefault();

    leader.setNeutralMode(ELEVATOR_NEUTRAL_MODE);
    follower.setNeutralMode(ELEVATOR_NEUTRAL_MODE);

    leader.configPeakOutputForward(PEAK_FORWARD,TIMEOUT);
    follower.configPeakOutputForward(PEAK_FORWARD,TIMEOUT);

    leader.configPeakOutputReverse(PEAK_BACKWARD,TIMEOUT);
    follower.configPeakOutputReverse(PEAK_BACKWARD,TIMEOUT);

    leader.setSelectedSensorPosition(0,0,TIMEOUT);
    leader.config_kP(0, kP, TIMEOUT);
    follower.follow(leader);

  }

  public void setSpeed(double speed) {
    if(Math.abs(speed)> DEADZONE){
      leader.set(ControlMode.PercentOutput, speed);
    }
  }

  public void setHeight(int ticks) {
    //Will, I would prefer if we didn't have ticks in the shop, most of us are already sick.
    leader.set(ControlMode.Position, ticks);
  }

  public int getHeight() {
    //Need a (disp)sensor here
    
    return leader.getSelectedSensorPosition(0);
  }

  public void stop() {
    leader.set(ControlMode.PercentOutput,0);
  }


  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ElevatorWithJoystick());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
