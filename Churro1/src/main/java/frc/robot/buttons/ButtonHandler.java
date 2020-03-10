package frc.robot.buttons;


import java.util.function.BiConsumer;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
/**
 * Handles button presses without commands (specify actions in an extended class)
 */
public abstract class ButtonHandler /*extends Thread*/{
    private boolean enabled;
    private GenericHID buttonInterface;
    private int buttonNo;
    protected static final int POV_UP=0;
    protected static final int POV_RIGHT=90;
    protected static final int POV_DOWN=180;
    protected static final int POV_LEFT=270;
    protected static final int POV_UP_RIGHT=45;
    protected static final int POV_DOWN_RIGHT=135;
    protected static final int POV_DOWN_LEFT=225;
    protected static final int POV_UP_LEFT=315;

    private int POVDeadband=1;

    /**Initializes a button handler with specified numberof buttons
     * 
     * @param buttonInterface
     * @param buttonNo Number of buttons on button interface
     */
    public ButtonHandler(GenericHID buttonInterface,int buttonNo){
        //this.setName(this.getClass().toString());
        this.buttonInterface=buttonInterface;
        this.buttonNo=buttonNo;
        enabled=true;
        //start();
    }

    /*@Override
    public void run(){
        while (enabled){
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            update();
        }
    }*/
    /**Call during a periodic function in order to regocnize button events
     * 
    */
    public void update(){
        for (int i=1; i<=buttonNo; i++){
            if (buttonInterface.getRawButton(i)){
                if (buttonInterface.getRawButtonPressed(i)){
                    buttonPressed(i);
                }
                buttonDown(i);
            } else if (buttonInterface.getRawButtonReleased(i)){
                buttonReleased(i);
            }
        }
        POV(buttonInterface.getPOV());
    }
    
    public final boolean getButtonDown(int no){
        return buttonInterface.getRawButton(no);
    }
    /**
     * Is called in update function when a button is pressed
     * @param no the button that was pressed
     */
    abstract void buttonPressed(int no);
    /**
     * Is called in update function when a button is released
     * @param no
     */
    abstract void buttonReleased(int no);
    /**
     * Is called in update function when a button is down
     * @param no the button that was pressed
     */
    abstract void buttonDown(int no);
    
    public final double getY(){
        return buttonInterface.getY();
    }
    public final double getX(){
        return buttonInterface.getX();
    }

    public final double getZ(){
        return buttonInterface.getRawAxis(2);
    }
    public Joystick getJoystick(){
        return (Joystick) buttonInterface;
    }

    public void simButtonPress(int no){
        buttonPressed(no);
    }

    public void simButtonRelease(int no){
        buttonReleased(no);
    }
    public void disable(){
        enabled=false;
    }

    /**Called on update with current value of POV as argument
     * 
     */
    public void POV(int angle){
    }

    /**Uses preset deadband (default value is 1) to determine if POV is at angle
     * 
     * @param angle
     * @return
     */
    public boolean POVAt(int angle){
        return buttonInterface.getPOV()>angle-POVDeadband && buttonInterface.getPOV()<angle+POVDeadband;
    }

    /**Uses specified deadband to determine if POV is at angle
     * 
     * @param angle
     * @param deadband
     * @return
     */
    public boolean POVAt(int angle,int deadband){
        return buttonInterface.getPOV()>angle-deadband && buttonInterface.getPOV()<angle+deadband;
    }

    /**Sets the deadband to be used in POVAt function when unspecified (default value is one)
     * 
     * @param deadband
     */
    public void setPOVDeadband(int deadband){
        POVDeadband=deadband;
    }
}