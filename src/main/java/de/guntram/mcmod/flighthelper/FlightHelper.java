package de.guntram.mcmod.flighthelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

@Mod(modid = FlightHelper.MODID, 
        version = FlightHelper.VERSION,
	clientSideOnly = true, 
	guiFactory = "de.guntram.mcmod.flighthelper.GuiFactory",
	acceptedMinecraftVersions = "[1.12]"
)

public class FlightHelper
{
    static final String MODID="flighthelper";
    static final String VERSION="1.0";
    private static float lockedPitch;
    private static boolean isLocked;
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        KeyRegistration.init();
        MinecraftForge.EVENT_BUS.register(new KeyHandler());

    }

    @EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        ConfigurationHandler confHandler = ConfigurationHandler.getInstance();
        confHandler.load(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(confHandler);
    }
    
    public static void lockPitch(float pitch) {
        lockedPitch=pitch;
        isLocked=true;
    }
    
    public static void unlockPitch() {
        isLocked=false;
    }
    
    @SubscribeEvent
    public void onRenderTick(RenderTickEvent event) {
        if (!isLocked)
            return;
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        float curPitch = player.rotationPitch;
        float delta=lockedPitch-curPitch;
        if (delta>5.0f) delta=5.0f;
        if (delta<-5.0f) delta=-5.0f;
        player.rotationPitch+=delta;
        if (ConfigurationHandler.getAutoUnlock() && delta>-0.01f && delta<0.01f)
            isLocked=false;
    }
}
