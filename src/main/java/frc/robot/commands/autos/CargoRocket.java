/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.autos.ResetElevatorAndWristPosition;
import frc.robot.commands.elevator.ElevatorTopOrBottom;
import frc.robot.commands.elevator.ElevatorWithSetPoint;
import frc.robot.commands.wrist.WristSetPosition;
import frc.robot.commands.intake.LaunchCargo;

public class CargoRocket extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CargoRocket(double wristPos, int elevatorPos) {
    addSequential(new WristSetPosition(wristPos));
    //TODO grab the wrist position needed for each

    //Will wait for elevatorwithsetpoint PID to finish, then will launch hatch 
    addSequential(new ElevatorWithSetPoint(elevatorPos));
    //addSequential(new WaitCommand(.1));
    //TODO Change to LaunchCargo when it is created
    addSequential(new LaunchCargo(1));
    //Wait for hatch to clear beak before reset
    //addSequential(new WaitCommand(.5));
    //Raise and lower beak to avoid hitting the beak against rocket while resetting
    addSequential(new ResetElevatorAndWristPosition());
  }

  public CargoRocket(double wristPos, boolean top) {
    addSequential(new WristSetPosition(wristPos));
    //TODO grab the wrist position needed for each

    //Will wait for elevatorwithsetpoint PID to finish, then will launch hatch 
    addSequential(new ElevatorTopOrBottom(top));
    //addSequential(new WaitCommand(.1));
    //TODO Change to LaunchCargo when it is created
    addSequential(new LaunchCargo(1));
    //Wait for hatch to clear beak before reset
    //addSequential(new WaitCommand(.5));
    //Raise and lower beak to avoid hitting the beak against rocket while resetting
    addSequential(new ResetElevatorAndWristPosition());
  }

  public CargoRocket(int elevatorPos) { 
    addSequential(new ElevatorWithSetPoint(elevatorPos));
    //addSequential(new WaitCommand(.1));
    //TODO Change to LaunchCargo when it is created
    addSequential(new LaunchCargo(.75));
  }
}
