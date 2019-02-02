package de.guntram.mcmod.flighthelper;

import org.dimdev.rift.listener.client.KeybindHandler;

public class KeyHandler implements KeybindHandler {

    @Override
    public void processKeybinds() {
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
