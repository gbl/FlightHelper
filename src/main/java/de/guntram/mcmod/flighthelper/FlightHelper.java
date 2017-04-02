package de.guntram.mcmod.flighthelper;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = FlightHelper.MODID, 
        version = FlightHelper.VERSION,
	clientSideOnly = true, 
	// guiFactory = "de.guntram.mcmod.flighthelper.config.XXGuiFactory",
	acceptedMinecraftVersions = "[1.11.2]"
)

public class FlightHelper
{
    static final String MODID="flighthelper";
    static final String VERSION="1.0";
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        ConfigurationHandler confHandler = ConfigurationHandler.getInstance();
        confHandler.load(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(confHandler);
    }
}
