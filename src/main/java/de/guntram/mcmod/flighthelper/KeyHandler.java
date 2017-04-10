package de.guntram.mcmod.flighthelper;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyHandler {
    @SubscribeEvent
    public void keyPressed(final InputEvent.KeyInputEvent e) {
        if (KeyRegistration.unlock.isPressed())
            FlightHelper.unlockPitch();
        else if (KeyRegistration.lockUp.isPressed())
            FlightHelper.lockPitch(ConfigurationHandler.getUpAngle());
        else if (KeyRegistration.lockFront.isPressed())
            FlightHelper.lockPitch(ConfigurationHandler.getFrontAngle());
    }
}
