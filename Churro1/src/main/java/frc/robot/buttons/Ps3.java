package frc.robot.buttons;

import edu.wpi.first.wpilibj.GenericHID;

public class Ps3 extends ButtonHandler {

    public Ps3(GenericHID buttonInterface) {
        super(buttonInterface, 8);
        // TODO Auto-generated constructor stub
    }

    @Override
    void buttonPressed(int no) {
        // TODO Auto-generated method stub
        switch (no){
            case 0:
                //button 0 press
            break;
        }

    }

    @Override
    void buttonReleased(int no) {
        // TODO Auto-generated method stub
        switch (no){
            case 0:
                //button 0 released
                
            break;

        }
    }

    @Override
    void buttonDown(int no) {
        // TODO Auto-generated method stub

    }
    
}