package de.guntram.mcmod.flighthelper;

import de.guntram.mcmod.fabrictools.ConfigurationProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class FlightHelper implements ClientModInitializer {
    static final String MODID="flighthelper";
    static final String MODNAME="FlightHelper";
    private static float lockedPitch;
    private static boolean isLocked;

    @Override
    public void onInitializeClient() {
        ConfigurationHandler confHandler = ConfigurationHandler.getInstance();
        ConfigurationProvider.register(MODNAME, confHandler);
        confHandler.load(ConfigurationProvider.getSuggestedFile(MODID));
        KeyRegistration.registerKeyBindings();
        ClientTickEvents.END_CLIENT_TICK.register((c) -> tick(c));
    }

    public static void lockPitch(float pitch) {
        lockedPitch=pitch;
        if (ConfigurationHandler.getSnapYaw()) {
            float yaw=MinecraftClient.getInstance().player.getYaw();
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
            MinecraftClient.getInstance().player.setYaw(yaw);
        }
        isLocked=true;
    }
    
    public static void unlockPitch() {
        isLocked=false;
    }
    
    public void tick(MinecraftClient client) {
        if (!isLocked)
            return;
        ClientPlayerEntity player = client.player;
        if (player==null)
            return;                 // logged out while locked
        float curPitch = player.getPitch();
        float delta=lockedPitch-curPitch;
        if (delta>5.0f) delta=5.0f;
        if (delta<-5.0f) delta=-5.0f;
        player.setPitch(curPitch+delta);
        if (ConfigurationHandler.getAutoUnlock() && delta>-0.01f && delta<0.01f)
            isLocked=false;
    }
}
