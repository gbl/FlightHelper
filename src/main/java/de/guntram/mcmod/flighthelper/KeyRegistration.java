package de.guntram.mcmod.flighthelper;

import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

public class KeyRegistration {
    
    public static KeyBinding lockUp, lockFront, unlock;
    private static final Logger LOGGER=LogManager.getLogger();
    
    public static void registerKeyBindings() {
        final String category="key.categories.flighthelper";
        KeyBindingRegistry.INSTANCE.addCategory(category);
        
        lockUp    = register("flighthelper:lockup",     GLFW.GLFW_KEY_KP_8, category);
        lockFront = register("flighthelper:lockfront",  GLFW.GLFW_KEY_KP_2, category);
        unlock    = register("flighthelper:unlock",     GLFW.GLFW_KEY_KP_5, category);
        ClientTickCallback.EVENT.register(e->processKeyBinds());
    }

    private static KeyBinding register (String name, int key, String category) {
        FabricKeyBinding temp;
        KeyBindingRegistry.INSTANCE.register(
                temp=FabricKeyBinding.Builder.create(new Identifier(name), InputUtil.Type.KEYSYM, key, category).build());
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
