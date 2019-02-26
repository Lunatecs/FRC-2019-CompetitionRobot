/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class CargoIntake extends Command {

  private boolean applyConstantIntake=false;

  public CargoIntake() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.intake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    double intake = Robot.oi.getIntakeCargoSpeed();
    double launch = Robot.oi.getLaunchCargoSpeed();
    
    if(intake>.2) {
      Robot.intake.setUpperWheelSpeed(-intake);
      Robot.intake.setLowerWheelSpeed(-intake);
      this.applyConstantIntake=true;
    } else if(launch>.2) {
      Robot.intake.setUpperWheelSpeed(launch);
      Robot.intake.setLowerWheelSpeed(launch);
      this.applyConstantIntake=false;
    } else {
      if(applyConstantIntake) {
        Robot.intake.setUpperWheelSpeed(-0.17);
        Robot.intake.setLowerWheelSpeed(-0.25);
      } else {
        Robot.intake.setUpperWheelSpeed(0);
        Robot.intake.setLowerWheelSpeed(0);
      }
    }

    SmartDashboard.putNumber("Intake", intake);
    SmartDashboard.putNumber("Launch", launch);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
