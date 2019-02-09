/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.drive.LunatecsDrive;
import frc.robot.commands.drive.DriveWithJoysticks;
import frc.robot.Robot;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.command.Subsystem;


public class DriveTrain extends Subsystem {

  private LunatecsDrive drive;

  private WPI_VictorSPX leftFront_V = new WPI_VictorSPX(RobotMap.LEFT_VICTOR_CAN_ID);
  private WPI_TalonSRX leftBack_T   = new WPI_TalonSRX(RobotMap.LEFT_TALON_CAN_ID);

  private WPI_TalonSRX rightFront_T = new WPI_TalonSRX(RobotMap.RIGHT_TALON_CAN_ID);
  private WPI_VictorSPX rightBack_V = new WPI_VictorSPX(RobotMap.RIGHT_VICTOR_CAN_ID);

  private static NeutralMode DRIVE_NEUTRAL_MODE = NeutralMode.Brake;

  public DriveTrain(){
    
    leftFront_V.setNeutralMode(DRIVE_NEUTRAL_MODE);
    leftBack_T.setNeutralMode(DRIVE_NEUTRAL_MODE);

    rightFront_T.setNeutralMode(DRIVE_NEUTRAL_MODE);
    rightBack_V.setNeutralMode(DRIVE_NEUTRAL_MODE);
    

    leftFront_V.follow(leftBack_T);
    rightBack_V.follow(rightFront_T);
    
    drive = new LunatecsDrive(leftBack_T, rightFront_T);
  }

  //TODO Deal with rampup
  
  public void arcadeDrive(double speed, double rotation){
    drive.arcadeDrive(speed, rotation, false);
  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveWithJoysticks());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
