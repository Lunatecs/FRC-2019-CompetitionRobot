/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

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
  public Joystick controller = new Joystick(RobotMap.CONTROLLER_USB_ID);
  
  JoystickButton greenButton = new JoystickButton(controller, RobotMap.GREEN_BUTTON_ID);
  JoystickButton redButton = new JoystickButton(controller, RobotMap.RED_BUTTON_ID);
  JoystickButton blueButton = new JoystickButton(controller, RobotMap.BLUE_BUTTON_ID);
  JoystickButton yellowButton = new JoystickButton(controller, RobotMap.YELLOW_BUTTON_ID);

  public OI(){
    //Publick Occurences

  }

  public double getSpeed(){

    return controller.getRawAxis(RobotMap.LEFT_AXIS_ID);
  
  }

  public double getRotation(){

    return controller.getRawAxis(RobotMap.RIGHT_AXIS_ID);
  
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
