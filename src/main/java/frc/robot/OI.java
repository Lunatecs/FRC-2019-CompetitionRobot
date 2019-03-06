
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.commands.intake.RaiseBeak;
import frc.robot.commands.intake.RaiseAndGrabHatch;
import frc.robot.button.DoubleJoystickButton;
import frc.robot.button.DoublePOVButton;
import frc.robot.button.LoneJoystickButton;
import frc.robot.button.LonePOVButton;
import frc.robot.commands.autos.HatchRocket;
import frc.robot.commands.climber.DefaultClimberCommand;
import frc.robot.commands.climber.LiftClimber;
import frc.robot.commands.climber.LiftRobot;
import frc.robot.commands.climber.PullRobot;
import frc.robot.commands.autos.AutoDrivetrain;
import frc.robot.commands.autos.CargoRocket;
import frc.robot.commands.elevator.ElevatorWithSetPoint;
import frc.robot.commands.intake.LaunchHatch;
import frc.robot.commands.intake.LowerBeak;
import frc.robot.commands.intake.LowerFoot;
import frc.robot.commands.intake.RaiseFoot;
import frc.robot.commands.intake.PullPistons;
import frc.robot.commands.intake.PushPistons;
import frc.robot.commands.wrist.WristSetPosition;
import frc.robot.commands.intake.Pistons;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Wrist;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  
  
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  public Joystick driverJoystick = new Joystick(RobotMap.DRIVER_JOYSTICK_USB_ID);
  public Joystick operatorJoystick = new Joystick(RobotMap.OPERATOR_JOYSTICK_USB_ID);
  
  JoystickButton driverRedButton = new JoystickButton(driverJoystick, RobotMap.RED_BUTTON_ID); 
  JoystickButton driverGreenButton = new JoystickButton(driverJoystick, RobotMap.GREEN_BUTTON_ID); 

  JoystickButton greenButton = new JoystickButton(operatorJoystick, RobotMap.GREEN_BUTTON_ID);
  JoystickButton redButton = new JoystickButton(operatorJoystick, RobotMap.RED_BUTTON_ID);
  JoystickButton blueButton = new JoystickButton(operatorJoystick, RobotMap.BLUE_BUTTON_ID);
  JoystickButton yellowButton = new JoystickButton(operatorJoystick, RobotMap.YELLOW_BUTTON_ID);
  JoystickButton rightBumperButton = new JoystickButton(operatorJoystick, RobotMap.RIGHT_BUMPER_ID);
  JoystickButton leftBumperButton = new JoystickButton(operatorJoystick, RobotMap.LEFT_BUMPER_ID);

  DoubleJoystickButton leftYellowButton = new DoubleJoystickButton(leftBumperButton, yellowButton);
  DoubleJoystickButton leftRedButton = new DoubleJoystickButton(leftBumperButton, redButton);
  DoubleJoystickButton leftGreenButton = new DoubleJoystickButton(leftBumperButton, greenButton);

  DoubleJoystickButton leftBlueButton = new DoubleJoystickButton(leftBumperButton, blueButton);

  DoubleJoystickButton rightYellowButton = new DoubleJoystickButton(rightBumperButton, yellowButton);
  DoubleJoystickButton rightRedButton = new DoubleJoystickButton(rightBumperButton, redButton);
  DoubleJoystickButton rightGreenButton = new DoubleJoystickButton(rightBumperButton, greenButton);
  DoubleJoystickButton rightBlueButton = new DoubleJoystickButton(rightBumperButton, blueButton);
  
  LoneJoystickButton loneYellowButton = new LoneJoystickButton(yellowButton, leftBumperButton, rightBumperButton);
  LoneJoystickButton loneRedButton = new LoneJoystickButton(redButton, leftBumperButton, rightBumperButton);
  LoneJoystickButton loneGreenButton = new LoneJoystickButton(greenButton, leftBumperButton, rightBumperButton);
  LoneJoystickButton loneBlueButton = new LoneJoystickButton(blueButton, leftBumperButton, rightBumperButton);

  POVButton upPOV = new POVButton(this.operatorJoystick, 0);
  POVButton rightPOV = new POVButton(this.operatorJoystick, 90);
  POVButton downPOV = new POVButton(this.operatorJoystick, 180);

  POVButton driverUpPOV = new POVButton(this.driverJoystick, 0);
  POVButton driverRightPOV = new POVButton(this.driverJoystick, 90);
  POVButton driverDownPOV = new POVButton(this.driverJoystick, 180);
  POVButton driverLeftPOV = new POVButton(this.driverJoystick, 270);


  LonePOVButton loneUpPOV =  new LonePOVButton(upPOV, leftBumperButton, rightBumperButton);
  LonePOVButton loneDownPOV = new LonePOVButton(downPOV,  leftBumperButton, rightBumperButton);
  LonePOVButton loneRightPOV = new LonePOVButton(rightPOV,  leftBumperButton, rightBumperButton);

  DoublePOVButton greenUpPOV = new DoublePOVButton(driverGreenButton, upPOV);
  DoublePOVButton greenDownPOV = new DoublePOVButton(driverGreenButton, downPOV);
  DoublePOVButton greenRightPOV = new DoublePOVButton(driverGreenButton, rightPOV);


  public OI(){
    loneYellowButton.whileActive(new RaiseBeak());
    loneGreenButton.whileActive(new LowerBeak());
    //using for match
    loneRedButton.whenActive(new LaunchHatch());
    loneBlueButton.whileActive(new ElevatorWithSetPoint(3000));
    
    driverRedButton.whileActive(new AutoDrivetrain(12));
    //test pistons
    //loneRedButton.whileActive(new RaiseFoot());
    //loneBlueButton.whileActive(new LowerFoot());

    leftBlueButton.whenActive(new WristSetPosition(Wrist.NINETY_DEGREE));
    
    //test pistons
    //leftYellowButton.whileActive(new PushPistons());
    //leftGreenButton.whileActive(new PullPistons());

    //not using
    //leftYellowButton.whileActive(new ElevatorWithSetPoint(Elevator.ROCKET_UPPER_HATCH));
    //leftRedButton.whileActive(new ElevatorWithSetPoint(Elevator.ROCKET_MIDDLE_HATCH));
    //leftGreenButton.whileActive(new ElevatorWithSetPoint(Elevator.ROCKET_LOWER_HATCH));

    //using for match
    leftYellowButton.whileActive(new HatchRocket(Wrist.ZERO_DEGREE,true));
    leftRedButton.whileActive(new HatchRocket(Wrist.ZERO_DEGREE,Elevator.ROCKET_MIDDLE_HATCH));
    leftGreenButton.whileActive(new HatchRocket(Wrist.ZERO_DEGREE,false));

    rightYellowButton.whileActive(new CargoRocket(282.5, true));
    rightRedButton.whileActive(new CargoRocket(278.5, Elevator.ROCKET_MIDDLE_HATCH));
    rightGreenButton.whileActive(new CargoRocket(278.5, false));
    rightBlueButton.whileActive(new CargoRocket(Elevator.CARGO_SHIP_CARGO));

    this.upPOV.whenActive(new RaiseFoot());
    this.downPOV.whenActive(new LowerFoot());
    this.rightPOV.whenActive(new WristSetPosition(Wrist.ZERO_DEGREE));

   driverUpPOV.whenActive(new LiftRobot());
   driverDownPOV.whenActive(new LiftClimber());
   driverRightPOV.whenActive(new PullRobot());
   driverLeftPOV.whenActive(new DefaultClimberCommand());
  }

  public double getSpeed(){

    return driverJoystick.getRawAxis(RobotMap.LEFT_JOY_Y_ID);
  
  }

  public double getRotation(){

    return driverJoystick.getRawAxis(RobotMap.RIGHT_JOY_X_ID);
  
  }
  
  public double getElevatorSpeed(){

    return this.operatorJoystick.getRawAxis(RobotMap.LEFT_JOY_Y_ID);

  }

  public double getWristSpeed(){

    return this.operatorJoystick.getRawAxis(RobotMap.RIGHT_JOY_Y_ID);

  }

  public double getIntakeCargoSpeed(){
    return this.operatorJoystick.getRawAxis(RobotMap.RIGHT_TRIGGER_ID);
  }

  public double getLaunchCargoSpeed(){
    return this.operatorJoystick.getRawAxis(RobotMap.LEFT_TRIGGER_ID);
  }
  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
