package de.guntram.mcmod.flighthelper;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyRegistration {
    
    @SideOnly(Side.CLIENT)
    public static KeyBinding lockUp, lockFront, unlock;
    
    public static void init() {
        ClientRegistry.registerKeyBinding(lockUp = new KeyBinding("key.lockUp",
                Keyboard.KEY_NUMPAD8, "key.categories.flighthelper"));
        ClientRegistry.registerKeyBinding(lockFront = new KeyBinding("key.lockFront",
                Keyboard.KEY_NUMPAD2, "key.categories.flighthelper"));
        ClientRegistry.registerKeyBinding(unlock = new KeyBinding("key.unlock",
                Keyboard.KEY_NUMPAD5, "key.categories.flighthelper"));
    }
}
