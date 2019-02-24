/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.wrist;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class WristSetPosition extends PIDCommand {

  private double setPoint = 0.0;
  private boolean isFinished = false;

  public WristSetPosition(double setPoint) {
    super("Wrist",.1,0.0,0.0);
    this.getPIDController().setAbsoluteTolerance(1.0);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.wrist);
    this.setPoint = setPoint;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.isFinished = false;
    this.setSetpoint(setPoint);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    SmartDashboard.putBoolean("On Target", this.getPIDController().onTarget());

    if(this.getPIDController().onTarget()) {
      this.isFinished = true;
    } else {
    
    }
    SmartDashboard.putData(Robot.wrist);
    SmartDashboard.putNumber("Wrist Set Point",this.getSetpoint());

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isFinished;
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

  @Override
  protected double returnPIDInput() {
    return Robot.wrist.getPosition();
  }

  @Override
  protected void usePIDOutput(double output) {
    SmartDashboard.putNumber("Wrist PID Speed", output);
    Robot.wrist.setWristSpeed(-output);
  }

}
