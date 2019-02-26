/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.elevator.ElevatorTopOrBottom;
import frc.robot.commands.elevator.ElevatorWithSetPoint;
import frc.robot.commands.wrist.WristSetPosition;
import frc.robot.commands.intake.RaiseBeak;
import frc.robot.commands.intake.LowerBeak;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Wrist;

public class ResetElevatorAndWristPosition extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ResetElevatorAndWristPosition() {
    //TODO find position that we want both wrist and elevator to reset to
    addSequential(new RaiseBeak());
    //Wait till Beak is raised so it wont hit rocket on way down, may not need
    addSequential(new WaitCommand(.05));
    addSequential(new ElevatorTopOrBottom(false));
    addSequential(new WristSetPosition(Wrist.NINETY_DEGREE));
    //addSequential(new LowerBeak());
  }
}
