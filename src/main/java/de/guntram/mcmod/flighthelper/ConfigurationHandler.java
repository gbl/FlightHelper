package de.guntram.mcmod.flighthelper;

import de.guntram.mcmod.fabrictools.ConfigChangedEvent;
import de.guntram.mcmod.fabrictools.Configuration;
import de.guntram.mcmod.fabrictools.ModConfigurationHandler;
import java.io.File;

public class ConfigurationHandler implements ModConfigurationHandler {

    private static ConfigurationHandler instance;

    private Configuration config;
    private String configFileName;
    private float lockUpAngle, lockFrontAngle;
    private boolean autoUnlock;
    private boolean snapYaw;

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

    @Override
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equalsIgnoreCase(FlightHelper.MODID)) {
            loadConfig();
        }
    }
    
    private void loadConfig() {
        
        config.migrate("Pitch Up", "flighthelper.config.pitchup");
        config.migrate("Pitch Front", "flighthelper.config.pitchfront");
        config.migrate("Auto unlock", "flighthelper.config.autounlock");

        lockUpAngle=config.getFloat("flighthelper.config.pitchup", Configuration.CATEGORY_CLIENT, -55f, -90f, 90f, "flighthelper.config.tt.pitchup");
        lockFrontAngle=config.getFloat("flighthelper.config.pitchfront", Configuration.CATEGORY_CLIENT, -2.2f, -90f, 90f, "flighthelper.config.tt.pitchfront");
        autoUnlock=config.getBoolean("flighthelper.config.autounlock", Configuration.CATEGORY_CLIENT, false, "flighthelper.config.tt.autounlock");
        snapYaw=config.getBoolean("flighthelper.config.snapyaw", Configuration.CATEGORY_CLIENT, true, "flighthelper.config.tt.snapyaw");
        if (config.hasChanged())
            config.save();
    }
    
    @Override
    public Configuration getConfig() {
        return config;
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
    
    public static boolean getSnapYaw() {
        return getInstance().snapYaw;
    }
}