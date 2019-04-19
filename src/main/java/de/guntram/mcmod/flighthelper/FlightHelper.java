package de.guntram.mcmod.flighthelper;

import de.guntram.mcmod.fabrictools.ConfigurationProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class FlightHelper implements ClientModInitializer, ClientTickCallback {
    static final String MODID="flighthelper";
    static final String VERSION="1.0";
    private static float lockedPitch;
    private static boolean isLocked;

    @Override
    public void onInitializeClient() {
        ConfigurationHandler confHandler = ConfigurationHandler.getInstance();
        ConfigurationProvider.register("FlightHelper", confHandler);
        confHandler.load(ConfigurationProvider.getSuggestedFile(MODID));
        KeyRegistration.registerKeyBindings();
        ClientTickCallback.EVENT.register(this);
    }

    public static void lockPitch(float pitch) {
        lockedPitch=pitch;
        float yaw=MinecraftClient.getInstance().player.yaw;
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
        MinecraftClient.getInstance().player.yaw=yaw;
        isLocked=true;
    }
    
    public static void unlockPitch() {
        isLocked=false;
    }
    
    @Override
    public void tick(MinecraftClient mncrft) {
        if (!isLocked)
            return;
        ClientPlayerEntity player = mncrft.player;
        if (player==null)
            return;                 // logged out while locked
        float curPitch = player.pitch;
        float delta=lockedPitch-curPitch;
        if (delta>5.0f) delta=5.0f;
        if (delta<-5.0f) delta=-5.0f;
        player.pitch+=delta;
        if (ConfigurationHandler.getAutoUnlock() && delta>-0.01f && delta<0.01f)
            isLocked=false;
    }
}
