/*
 * This plugin has been created by ItsMYunderscore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2021.
 */

package me.itsmyunderscore.config;

import me.itsmyunderscore.utils.ConfigFile;
import me.itsmyunderscore.utils.Message;


public class Config {

    public static boolean FILTER_ENABLED;
    public static boolean DEBUG_ENABLED;
    public static boolean WORDMANAGER_CMD_ENABLED;
    public static boolean SAFE_MODE_ENABLED;
    private static ConfigFile configFile;

    public Config() {
        try {
            ConfigFile config = new ConfigFile("config.yml");

            FILTER_ENABLED = config.getBoolean("options.filter.enabled");
            DEBUG_ENABLED = config.getBoolean("options.filter.debug");
            WORDMANAGER_CMD_ENABLED = config.getBoolean("options.filter.word_manager");
            SAFE_MODE_ENABLED = config.getBoolean("options.filter.safe_mode");

            configFile = config;
        } catch (Exception exception) {
            Message.log("Critical error! 1x01");
        }
    }

    public static void save() {
        configFile.set("options.filter.enabled", FILTER_ENABLED);
        configFile.set("options.filter.debug", DEBUG_ENABLED);
        configFile.set("options.filter.word_manager", WORDMANAGER_CMD_ENABLED);
        configFile.set("options.filter.safe_mode", SAFE_MODE_ENABLED);

        configFile.save();
    }

    public static void reload() {
        FILTER_ENABLED = configFile.getBoolean("options.filter.enabled");
        DEBUG_ENABLED = configFile.getBoolean("options.filter.debug");
        WORDMANAGER_CMD_ENABLED = configFile.getBoolean("options.filter.word_manager");
        SAFE_MODE_ENABLED = configFile.getBoolean("options.filter.safe_mode");
    }

    public static ConfigFile getConfigFile() {
        return configFile;
    }

}

