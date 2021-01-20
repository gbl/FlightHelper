package de.guntram.mcmod.flighthelper;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

public class KeyRegistration {
    
    public static KeyBinding lockUp, lockFront, unlock;
    private static final Logger LOGGER=LogManager.getLogger();
    
    public static void registerKeyBindings() {
        final String category="key.categories.flighthelper";
        
        lockUp    = register("key.flighthelper.lockup",     GLFW.GLFW_KEY_KP_8, category);
        lockFront = register("key.flighthelper.lockfront",  GLFW.GLFW_KEY_KP_2, category);
        unlock    = register("key.flighthelper.unlock",     GLFW.GLFW_KEY_KP_5, category);
        ClientTickEvents.END_CLIENT_TICK.register(e->processKeyBinds());
    }

    private static KeyBinding register (String name, int key, String category) {
        KeyBinding temp=new KeyBinding(name, InputUtil.Type.KEYSYM, key, category);
        KeyBindingHelper.registerKeyBinding(temp);
        return temp;
    }
    
    public static void processKeyBinds() {
        if (KeyRegistration.unlock.wasPressed()) {
            FlightHelper.unlockPitch();
        }
        else if (KeyRegistration.lockUp.wasPressed()) {
            FlightHelper.lockPitch(ConfigurationHandler.getUpAngle());
            LOGGER.info("locking to "+ConfigurationHandler.getUpAngle());
        }
        else if (KeyRegistration.lockFront.wasPressed()) {
            FlightHelper.lockPitch(ConfigurationHandler.getFrontAngle());
            LOGGER.info("locking to "+ConfigurationHandler.getFrontAngle());            
        }
    }
}
