/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.ArrayList;
import java.util.Hashtable;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.event.EventHandler;
import frc.robot.event.EventSequence;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class EventRobot extends TimedRobot {
  private ArrayList<EventSequence> autonSequences;
  private EventSequence autonSequence=null;
  private int index=-1;

  private Hashtable<String, Double> tuningValues;

  public static EventHandler eHandler=new EventHandler();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code. Use super.robotInit() at beginning when overriding so that proper initialization can occur
   */
  @Override
  public void robotInit() {
    eHandler.start();
    tuningValues=new Hashtable<>();
  }

  public double getTuningValue(String key){
    return tuningValues.get(key);
  }

  public void setTuningValue(String key,int value){
    tuningValues.replace(key,(double)value);
  }

  public void setTuningValue(String key,double value){
    tuningValues.replace(key,value);
  }

  public String[] getKeys(){
    String[] keys=new String[tuningValues.keySet().size()];
    tuningValues.keySet().toArray(keys);
    return keys;
  }

  protected void startAuton(){
    if (autonSequence.equals(null)){
      System.err.println("Failed to start auton: no auton initialized.");
    }
    eHandler.triggerEvent(autonSequence);
  }

  protected void stopAuton(){
    try {
      autonSequence.terminate();
    } catch (NullPointerException e){

    }
  }

  protected void setAuton(int index){
    autonSequence=autonSequences.get(index);
    this.index=index;
  }

  protected void addAuton(EventSequence auton){
    autonSequences.add(auton);
  }

  protected void addAuton(EventSequence auton, int index){
    autonSequences.add(index,auton);
  }

  protected int getAutonNo(){
    return index;
  }

}

