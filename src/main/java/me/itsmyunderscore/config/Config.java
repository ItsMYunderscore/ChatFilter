/*
 * This plugin has been created by ItsMYunderscore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2022.
 */

package me.itsmyunderscore.config;

import me.itsmyunderscore.utils.ConfigFile;
import me.itsmyunderscore.utils.Message;


public class Config {

    public static boolean FILTER_ENABLED;
    public static boolean DEBUG_ENABLED;
    public static boolean WORDMANAGER_CMD_ENABLED;
    public static boolean SAFE_MODE_ENABLED;
    public static boolean ALLOW_CERTAIN_WEBSITES;
    private static ConfigFile configFile;

    public Config() {
        try {
            ConfigFile config = new ConfigFile("config.yml");

            FILTER_ENABLED = config.getBoolean("options.filter.enabled");
            DEBUG_ENABLED = config.getBoolean("options.filter.debug");
            WORDMANAGER_CMD_ENABLED = config.getBoolean("options.filter.word_manager");
            SAFE_MODE_ENABLED = config.getBoolean("options.filter.safe_mode.enabled");
            ALLOW_CERTAIN_WEBSITES = config.getBoolean("options.filter.safe_mode.allow_my_websites");

            configFile = config;
        } catch (Exception exception) {
            Message.log("Critical error! 1x01");
        }
    }

    public static void save() {
        configFile.set("options.filter.enabled", FILTER_ENABLED);
        configFile.set("options.filter.debug", DEBUG_ENABLED);
        configFile.set("options.filter.word_manager", WORDMANAGER_CMD_ENABLED);
        configFile.set("options.filter.safe_mode.enabled", SAFE_MODE_ENABLED);
        configFile.set("options.filter.safe_mode.allow_my_websites", ALLOW_CERTAIN_WEBSITES);

        configFile.save();
    }

    public static void reload() {
        ConfigFile config = new ConfigFile("config.yml");

        FILTER_ENABLED = config.getBoolean("options.filter.enabled");
        DEBUG_ENABLED = config.getBoolean("options.filter.debug");
        WORDMANAGER_CMD_ENABLED = config.getBoolean("options.filter.word_manager");
        SAFE_MODE_ENABLED = config.getBoolean("options.filter.safe_mode.enabled");
        ALLOW_CERTAIN_WEBSITES = config.getBoolean("options.filter.safe_mode.allow_my_websites");

        configFile = config;

        Message.debug("Config reloaded");
    }

    public static ConfigFile getConfigFile() {
        return configFile;
    }

}

