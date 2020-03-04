package frc.robot.event.customevents.sequence;

import frc.robot.event.Event;
import frc.robot.event.EventSequence;
import frc.robot.event.core.DriveEvent;

public class AutonEvent extends EventSequence{

    public AutonEvent() {
        //drive at .5 for 1000 ms and then stop (the second drive event that tells the motors to stop has a 1000 ms delay)
        super(new Event[] {new DriveEvent(.5),new DriveEvent(0, 1000)});
    }
    

    
    

}