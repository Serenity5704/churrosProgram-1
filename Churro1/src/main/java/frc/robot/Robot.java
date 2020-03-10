
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.buttons.JSBAdapter;
import frc.robot.event.EventHandler;
import frc.robot.event.customevents.sequence.AutonEvent;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  //declare instance class variable
  private static Robot instance;

  //initialize event handler (runs on it's own thread)
  public static EventHandler eHandler=new EventHandler();

  //initialize motors
  private Spark fr=new Spark(0);
  private Spark br=new Spark(1);
  private Spark bl=new Spark(2);
  private Spark fl=new Spark(3);
  private Spark arm=new Spark(6);
  private Spark iotake=new Spark(5);
  private Spark climber=new Spark(4);

  //initialize joystick
  Joystick j=new Joystick(0);

  //initialize button adapter
  JSBAdapter jsbAdapter=new JSBAdapter(j);

  //declare differential drive
  DifferentialDrive drive;

  /**
   * This function is run when the robot is 
   *first started up and should be used
   * for any initialization code.
   */if(timer.getmatchTime() > 12){
  @Override
  public void robotInit() {
    //set the instance class variable to be equal to this instance so that this instance may be accessed in a static way
    instance=this;
    //start the event handler thread
    eHandler.start();
  }

  /**Called when auton starts
   * 
   */
  @Override
  public void autonomousInit() {
    //set differential drive to null so that it doesn't interfear with autonomous drive processes
    drive=null;
    //run the autonomous event sequence (look in AutonEvent.java for details)
    eHandler.triggerEvent(new AutonEvent());
  }

  @Override
  public void autonomousPeriodic() {
  }

  /**Called at beginning of teleop
   * 
   */
  @Override
  public void teleopInit() {
    //initialize differential drive
    drive=new DifferentialDrive(fl, fr);
  }

  /**Called periodically during teleop
   * 
   */
  @Override
  public void teleopPeriodic() {
    double x=j.getX();
    if (Math.abs(x)<.17){
      x=0;
    }
    drive.arcadeDrive(x*-1, j.getY());
    
    jsbAdapter.update();

    // climber
    if (j.getPOV() == 0) {
      climber.set(0.3);
    } else if (j.getPOV()==180){
      climber.set(-0.3);
    } else {
      climber.set(0);
    }
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  /**Returns the current instance of the robot so that all methods and visible properties are effectively able to be statically accessed from anywhere
   * 
   * @return
   */
  public static Robot getInstance(){
    return instance;
  }

  //----------------------
  //Motor access functions
  //----------------------

  public Spark getIOtake(){
    return iotake;
  }

  public Spark getArm(){
    return arm;
  }

  public Spark getFrontDriveLeft(){
    return fl;
  }
  public Spark getFrontDriveRight(){
    return br;
  }
  public Spark getBackDriveLeft(){
    return bl;
  }
  public Spark getBackDriveRight(){
    return br;
  }
  public Spark getClimber(){
    return climber;
  }

}
