package de.guntram.mcmod.flighthelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import org.dimdev.rift.listener.client.ClientTickable;
import org.dimdev.riftloader.listener.InitializationListener;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

public class FlightHelper implements InitializationListener, ClientTickable {
    static final String MODID="flighthelper";
    static final String VERSION="1.0";
    private static float lockedPitch;
    private static boolean isLocked;

    @Override
    public void onInitialization() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.riftpatch-de-guntram.json");
    }

    public static void lockPitch(float pitch) {
        lockedPitch=pitch;
        isLocked=true;
    }
    
    public static void unlockPitch() {
        isLocked=false;
    }
    
    @Override
    public void clientTick(Minecraft mncrft) {
        if (!isLocked)
            return;
        EntityPlayerSP player = mncrft.player;
        float curPitch = player.rotationPitch;
        float delta=lockedPitch-curPitch;
        if (delta>5.0f) delta=5.0f;
        if (delta<-5.0f) delta=-5.0f;
        player.rotationPitch+=delta;
        if (ConfigurationHandler.getAutoUnlock() && delta>-0.01f && delta<0.01f)
            isLocked=false;
    }
}
