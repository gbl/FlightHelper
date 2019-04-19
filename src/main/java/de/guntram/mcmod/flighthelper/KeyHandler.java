package de.guntram.mcmod.flighthelper;

import de.guntram.mcmod.fabrictools.KeyBindingHandler;

public class KeyHandler implements KeyBindingHandler {

    @Override
    public void processKeyBinds() {
        if (KeyRegistration.unlock.isPressed()) {
            FlightHelper.unlockPitch();
        }
        else if (KeyRegistration.lockUp.isPressed()) {
            FlightHelper.lockPitch(ConfigurationHandler.getUpAngle());
            System.out.println("locking to "+ConfigurationHandler.getUpAngle());
        }
        else if (KeyRegistration.lockFront.isPressed()) {
            FlightHelper.lockPitch(ConfigurationHandler.getFrontAngle());
            System.out.println("locking to "+ConfigurationHandler.getFrontAngle());            
        }
    }
}
