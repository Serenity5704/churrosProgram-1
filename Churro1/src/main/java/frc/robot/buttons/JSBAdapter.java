package frc.robot.buttons;

import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.Robot;

public class JSBAdapter extends ButtonHandler {

    public JSBAdapter(GenericHID buttonInterface) {
        super(buttonInterface, 12);
        // TODO Auto-generated constructor stub
    }

    @Override
    void buttonPressed(int no) {
        // TODO Auto-generated method stub
        switch (no){
            case 1:
                //button 0 press
                Robot.getInstance().getIOtake().set(-.5);
            break;
            case 2:
                Robot.getInstance().getIOtake().set(.5);
            break;
            case 3:
                Robot.getInstance().getArm().set(.5);
            break;
            case 4:
                Robot.getInstance().getArm().set(-.5);
            break;
        }

    }

    @Override
    void buttonReleased(int no) {
        // TODO Auto-generated method stub
        switch (no){
            case 1:
                //button 0 released
                Robot.getInstance().getIOtake().set(0); 
            break;
            case 2:
                Robot.getInstance().getIOtake().set(0);
            break;
            case 3:
                Robot.getInstance().getArm().set(0);
            break;
            case 4:
                Robot.getInstance().getArm().set(0);
            break;
        }

    }

    @Override
    void buttonDown(int no) {
        // TODO Auto-generated method stub

    }
    
}