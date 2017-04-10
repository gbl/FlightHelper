package de.guntram.mcmod.flighthelper;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {

    private static ConfigurationHandler instance;

    private Configuration config;
    private String configFileName;
    private float lockUpAngle, lockFrontAngle;
    private boolean autoUnlock;

    public static ConfigurationHandler getInstance() {
        if (instance==null)
            instance=new ConfigurationHandler();
        return instance;
    }

    public void load(final File configFile) {
        if (config == null) {
            config = new Configuration(configFile);
            configFileName=configFile.getPath();
            loadConfig();
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        // System.out.println("OnConfigChanged for "+event.getModID());
        if (event.getModID().equalsIgnoreCase(FlightHelper.MODID)) {
            loadConfig();
        }
    }
    
    private void loadConfig() {
        lockUpAngle=config.getFloat("Pitch Up", Configuration.CATEGORY_CLIENT, -55f, -90f, 90f, "Pitch when using fireworks and flying up");
        lockFrontAngle=config.getFloat("Pitch Front", Configuration.CATEGORY_CLIENT, -2.2f, -90f, 90f, "Pitch when gliding");
        autoUnlock=config.getBoolean("Don't lock", Configuration.CATEGORY_CLIENT, false, "Set pitch once, but don't lock");
        if (config.hasChanged())
            config.save();
    }
    
    public static Configuration getConfig() {
        return getInstance().config;
    }
    
    public static String getConfigFileName() {
        return getInstance().configFileName;
    }
    
    public static float getUpAngle() {
        return getInstance().lockUpAngle;
    }
    
    public static float getFrontAngle() {
        return getInstance().lockFrontAngle;
    }
    
    public static boolean getAutoUnlock() {
        return getInstance().autoUnlock;
    }
    
}
