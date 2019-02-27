/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * Add your docs here.
 */
public class UltrasonicSensor extends Subsystem {
  private Ultrasonic rangeFinder = new Ultrasonic(RobotMap.ULTRASONIC_ECHO_ID, RobotMap.ULTRASONIC_PING_ID);

  public UltrasonicSensor() {
    rangeFinder.setAutomaticMode(true);
  }

  public double getRange(){
    return rangeFinder.getRangeInches();
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
