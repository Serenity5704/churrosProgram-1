/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.buttons.JSBAdapter;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static Robot instance;

  private Spark fr=new Spark(0);
  private Spark br=new Spark(1);
  private Spark bl=new Spark(2);
  private Spark fl=new Spark(3);
  private Spark arm=new Spark(6);
  private Spark iotake=new Spark(5);
  private Spark climber=new Spark(4);

  Joystick j=new Joystick(0);

  JSBAdapter jsbAdapter=new JSBAdapter(j);

  DifferentialDrive drive;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    drive=new DifferentialDrive(fl, fr);
    instance=this;
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    drive.arcadeDrive(j.getX(), j.getY());
    
    jsbAdapter.update();
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  public static Robot getInstance(){
    return instance;
  }

  public Spark getIOtake(){
    return iotake;
  }

  public Spark getArm(){
    return arm;
  }
}
