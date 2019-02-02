package de.guntram.mcmod.flighthelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.client.settings.KeyBinding;
import org.dimdev.rift.listener.client.KeyBindingAdder;
import org.lwjgl.glfw.GLFW;

public class KeyRegistration implements KeyBindingAdder {
    
    public static KeyBinding lockUp, lockFront, unlock;
    
    @Override
    public Collection<? extends KeyBinding> getKeyBindings() {
        List<KeyBinding> myBindings=new ArrayList();
        
        myBindings.add(lockUp =    new KeyBinding("key.lockUp",     GLFW.GLFW_KEY_KP_8, "key.categories.flighthelper"));
        myBindings.add(lockFront = new KeyBinding("key.lockFront",  GLFW.GLFW_KEY_KP_2, "key.categories.flighthelper"));
        myBindings.add(unlock =    new KeyBinding("key.unlock",     GLFW.GLFW_KEY_KP_5, "key.categories.flighthelper"));
        return myBindings;
    }
}
