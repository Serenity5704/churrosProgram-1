package frc.robot.event.core;


import edu.wpi.first.wpilibj.Spark;
import frc.robot.Robot;
import frc.robot.event.Event;

public class DriveEvent extends Event{
    //private static double rotationsPerMeter=1;
    Spark fr = Robot.getInstance().getFrontDriveRight();
    Spark br = Robot.getInstance().getBackDriveRight();
    Spark fl = Robot.getInstance().getFrontDriveLeft();
    Spark bl = Robot.getInstance().getBackDriveLeft();

    double power;

    public DriveEvent(double power){
        super();
        this.power=power;
    }

    public DriveEvent(double power,long delay){
        super(delay);
        this.power=power;
    }

    @Override
    public void task(){
        fl.set(power);
        fr.set(power);
        bl.set(power);
        br.set(power);
    }

}