/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Limelight extends Subsystem {
  
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  
  //Constructor
  public Limelight(){
    CameraServer.getInstance().startAutomaticCapture();
  }

  //--------Getters--------
  public double getTX(){
    return table.getEntry("tx").getDouble(0.0);
  }

  public double getTY(){
    return table.getEntry("ty").getDouble(0.0);
  }

  public double getArea(){
    return table.getEntry("ta").getDouble(0.0);
  }

  public boolean isValidTarget(){
     double check = table.getEntry("tv").getDouble(0.0);

     if (check == 1) {
       return true;
     } else if (check == 0) {
       return false;
     } else {
       return false;
     }
  }


  //--------Setters--------
  
  //0 = Vision Processor
  //1 = Driver Camera
  public void setCamMode(double value){
    table.getEntry("camMode").setDouble(value);
  }

  //Only use if a camera is plugged into limelight
  //0 = Side-by-Side
  //1 = Secondary Cam Lower-Right corner
  //2 = Primary Cam Lower-Right corner
  public void setStreamMode(double value){
    table.getEntry("stream").setDouble(value);
  }

  //0-9 diffrent pipelines that can be set
  public void setPipeline(double value){
    table.getEntry("pipeline").setDouble(value);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
