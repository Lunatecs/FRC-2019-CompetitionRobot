/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.intake.RaiseBeak;
import frc.robot.commands.intake.RaiseAndGrabHatch;
import frc.robot.commands.elevator.ElevatorWithSetPoint;
import frc.robot.commands.intake.LaunchHatch;
import frc.robot.commands.intake.LowerBeak;
import frc.robot.commands.intake.LowerFoot;
import frc.robot.commands.intake.RaiseFoot;
import frc.robot.commands.intake.Pistons;
import frc.robot.subsystems.Elevator;

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
  
  JoystickButton greenButton = new JoystickButton(operatorJoystick, RobotMap.GREEN_BUTTON_ID);
  JoystickButton redButton = new JoystickButton(operatorJoystick, RobotMap.RED_BUTTON_ID);
  JoystickButton blueButton = new JoystickButton(operatorJoystick, RobotMap.BLUE_BUTTON_ID);
  JoystickButton yellowButton = new JoystickButton(operatorJoystick, RobotMap.YELLOW_BUTTON_ID);
  JoystickButton rightBumperButton = new JoystickButton(operatorJoystick, RobotMap.RIGHT_BUMPER_ID);
  JoystickButton leftBumperButton = new JoystickButton(operatorJoystick, RobotMap.LEFT_BUMPER_ID);


  public OI(){
    rightBumperButton.toggleWhenPressed(new RaiseBeak());
    leftBumperButton.toggleWhenPressed(new LowerBeak());
    redButton.toggleWhenPressed(new LaunchHatch());
    blueButton.toggleWhenPressed(new RaiseAndGrabHatch());
    greenButton.whileHeld(new ElevatorWithSetPoint(Elevator.ROCKET_MIDDLE_HATCH));
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
