package de.guntram.mcmod.flighthelper;

import de.guntram.mcmod.rifttools.ConfigurationProvider;
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
        Mixins.addConfiguration("mixins.rifttools-de-guntram.json");
        ConfigurationHandler confHandler = ConfigurationHandler.getInstance();
        ConfigurationProvider.register("FlightHelper", confHandler);
        confHandler.load(ConfigurationProvider.getSuggestedFile(MODID));
    }

    public static void lockPitch(float pitch) {
        lockedPitch=pitch;
        float yaw=Minecraft.getInstance().player.rotationYaw;
        while (yaw<0) yaw+=360;
        while (yaw>360) yaw-=360;
        if (yaw < 5.0 || yaw > 355.0) {
            yaw=0.0f;
        }
        else if (yaw>85.0 && yaw<95.0) {
            yaw=90.0f;
        }
        else if (yaw>175.0 && yaw<185.0) {
            yaw=180.0f;
        }
        else if (yaw>265.0 && yaw<275.0) {
            yaw=270.0f;
        }
        Minecraft.getInstance().player.rotationYaw=yaw;
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
        if (player==null)
            return;                 // logged out while locked
        float curPitch = player.rotationPitch;
        float delta=lockedPitch-curPitch;
        if (delta>5.0f) delta=5.0f;
        if (delta<-5.0f) delta=-5.0f;
        player.rotationPitch+=delta;
        if (ConfigurationHandler.getAutoUnlock() && delta>-0.01f && delta<0.01f)
            isLocked=false;
    }
}
