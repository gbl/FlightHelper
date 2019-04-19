package de.guntram.mcmod.flighthelper;

import de.guntram.mcmod.fabrictools.KeyBindingManager;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class KeyRegistration {
    
    public static KeyBinding lockUp, lockFront, unlock;
    
    public static void registerKeyBindings() {
        final String category="key.categories.flighthelper";
        KeyBindingRegistry.INSTANCE.addCategory(category);
        
        lockUp    = register("flighthelper:lockup",     GLFW.GLFW_KEY_KP_8, category);
        lockFront = register("flighthelper:lockfront",  GLFW.GLFW_KEY_KP_2, category);
        unlock    = register("flighthelper:unlock",     GLFW.GLFW_KEY_KP_5, category);
        KeyBindingManager.register(new KeyHandler());
    }

    private static KeyBinding register (String name, int key, String category) {
        FabricKeyBinding temp;
        KeyBindingRegistry.INSTANCE.register(
                temp=FabricKeyBinding.Builder.create(new Identifier(name), InputUtil.Type.KEYSYM, key, category).build());
        return temp;
    }
}
