/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.LED;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Wrist;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import edu.wpi.first.wpilibj.Notifier;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static OI oi;
  public static Intake intake;
  public static DriveTrain drive;
  public static Elevator elevator;
  public static Wrist wrist;
  public static LED led;
  public static Limelight limelight;

  private DifferentialDrive aDrive; 

  private WPI_VictorSPX leftFront_V = new WPI_VictorSPX(RobotMap.LEFT_FRONT_DRIVE_V_ID);
  private WPI_TalonSRX leftCenter_T = new WPI_TalonSRX(RobotMap.LEFT_CENTER_DRIVE_T_ID);
  private WPI_VictorSPX leftBack_V  = new WPI_VictorSPX(RobotMap.LEFT_BACK_DRIVE_V_ID);

  private WPI_VictorSPX rightFront_V = new WPI_VictorSPX(RobotMap.RIGHT_FRONT_DRIVE_V_ID);
  private WPI_TalonSRX rightCenter_T = new WPI_TalonSRX(RobotMap.RIGHT_CENTER_DRIVE_T_ID);
  private WPI_VictorSPX rightBack_V = new WPI_VictorSPX(RobotMap.RIGHT_BACK_DRIVE_V_ID);

  private EncoderFollower leftEncoderFollower;
  private EncoderFollower rightEncoderFollower;

  private Notifier followerNotifier;

  private static NeutralMode DRIVE_NEUTRAL_MODE = NeutralMode.Brake;

  private static final String PATH_NAME = "Test1";
  private static final int TICKS_PER_ROTATION = 4096;
  //TODO change wheel diameter to the average of the wheel diameter
  private static final double WHEEL_DIAMETER = 6.375;
  private static final double MAX_VELOCITY = 10;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    oi = new OI();
    intake = new Intake();
    drive = new DriveTrain();
    elevator = new Elevator();
    limelight = new Limelight();
    led = new LED();
    wrist = new Wrist();
    SmartDashboard.putData(drive);
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    leftFront_V.configFactoryDefault();
    leftCenter_T.configFactoryDefault();
    leftBack_V.configFactoryDefault();

    rightFront_V.configFactoryDefault();
    rightCenter_T.configFactoryDefault();
    rightBack_V.configFactoryDefault();

    
    leftFront_V.setNeutralMode(DRIVE_NEUTRAL_MODE);
    leftCenter_T.setNeutralMode(DRIVE_NEUTRAL_MODE);
    leftBack_V.setNeutralMode(DRIVE_NEUTRAL_MODE);

    rightFront_V.setNeutralMode(DRIVE_NEUTRAL_MODE);
    rightCenter_T.setNeutralMode(DRIVE_NEUTRAL_MODE);
    rightBack_V.setNeutralMode(DRIVE_NEUTRAL_MODE);
    

    leftFront_V.follow(leftCenter_T);
    leftBack_V.follow(leftCenter_T);

    rightFront_V.follow(rightCenter_T);
    rightBack_V.follow(rightCenter_T);

    aDrive = new DifferentialDrive(leftCenter_T, rightCenter_T);

    //Get trajectory from the path
    Trajectory leftTrajectory = PathfinderFRC.getTrajectory(PATH_NAME + ".left");
    Trajectory rightTrajectory = PathfinderFRC.getTrajectory(PATH_NAME + ".right");
    
    //C
    leftEncoderFollower = new EncoderFollower(leftTrajectory);
    rightEncoderFollower = new EncoderFollower(rightTrajectory);

    // The first argument is the proportional gain. Usually this will be quite high
    // The second argument is the integral gain. This is unused for motion profiling
    // The third argument is the derivative gain. Tweak this if you are unhappy with the tracking of the trajectory
    // The fourth argument is the velocity ratio. This is 1 over the maximum velocity you provided in the 
    //      trajectory configuration (it translates m/s to a -1 to 1 scale that your motors can read)
    // The fifth argument is your acceleration gain. Tweak this if you want to get to a higher or lower speed quicker    
    leftEncoderFollower.configurePIDVA(1.0, 0.0, 0.0, 1 / MAX_VELOCITY, 0);
    rightEncoderFollower.configurePIDVA(1.0, 0.0, 0.0, 1 / MAX_VELOCITY, 0);
    
    leftEncoderFollower.configureEncoder(leftCenter_T.getSelectedSensorPosition(0), TICKS_PER_ROTATION, WHEEL_DIAMETER);
    rightEncoderFollower.configureEncoder(rightCenter_T.getSelectedSensorPosition(0), TICKS_PER_ROTATION, WHEEL_DIAMETER);
    
    followerNotifier = new Notifier(this::followPath);

    followerNotifier.startPeriodic(leftTrajectory.get(0).dt);
    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  public void followPath(){
    if(leftEncoderFollower.isFinished() || rightEncoderFollower.isFinished()){
      followerNotifier.stop();
    } else {
      double leftSpeed = leftEncoderFollower.calculate(leftCenter_T.getSelectedSensorPosition(0));
      double rightSpeed = rightEncoderFollower.calculate(rightCenter_T.getSelectedSensorPosition(0));
    
      double gyroHeading = gyro.getAngle();
      double desiredHeading = Pathfinder.r2d(leftEncoderFollower.getHeading());

      double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);
      double turn = 0.8 * (-1/80) * angleDifference;

      double leftPathSpeed = (leftSpeed + turn);
      double rightPathSpeed = (rightSpeed - turn);

      aDrive.tankDrive(leftPathSpeed, rightPathSpeed);
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    followerNotifier.stop();
    aDrive.tankDrive(0,0);
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();


  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {

  }
}
