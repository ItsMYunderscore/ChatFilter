package me.itsmyunderscore.config;

import me.itsmyunderscore.utils.ConfigFile;
import me.itsmyunderscore.utils.Message;

import java.util.List;

public class DevelopmentConfig {

    private static ConfigFile devConfigFile;

    public static List<String> DEVS;

    public static boolean DEVMODE_ENABLED;

    public DevelopmentConfig() {
        try {
            ConfigFile devConfig = new ConfigFile("development_config.yml");

            DEVS = devConfig.getStringList("features.users");
            DEVMODE_ENABLED = devConfig.getBoolean("features.enabled", false);

            devConfigFile = devConfig;
        } catch (Exception exception){
            Message.log("Critical error! 1x03");
        }
    }
}
