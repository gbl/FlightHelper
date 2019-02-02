package de.guntram.mcmod.flighthelper;

import java.io.File;

public class ConfigurationHandler {

    private static ConfigurationHandler instance;

    private String configFileName;
    private float lockUpAngle, lockFrontAngle;
    private boolean autoUnlock;

    public static ConfigurationHandler getInstance() {
        if (instance==null) {
            instance=new ConfigurationHandler();
            instance.loadConfig();
        }
        return instance;
    }

    public void load(final File configFile) {
        getInstance().loadConfig();
    }

    private void loadConfig() {
        lockUpAngle=-55f;
        lockFrontAngle=-2.2f;
        autoUnlock=false;
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
