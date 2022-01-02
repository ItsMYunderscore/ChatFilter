/*
 * This plugin has been created by ItsMYundercore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2021.
 */

package me.itsmyunderscore.config;

import me.itsmyunderscore.utils.ConfigFile;
import me.itsmyunderscore.utils.Message;

import java.util.List;


public class Config {

    private static ConfigFile configFile;

    public static boolean FILTER_ENABLED;

    public static boolean DEBUG_ENABLED;

    public static List<String> EXEMPT_USERS;

    public Config() {
        try {
            ConfigFile config = new ConfigFile("config.yml");

            FILTER_ENABLED = config.getBoolean("options.filter.enabled");
            DEBUG_ENABLED = config.getBoolean("options.filter.debug");
            EXEMPT_USERS = config.getStringList("options.exempt.users");

            configFile = config;
        } catch (Exception exception){
            Message.log("Critical error! 1x01");
        }
    }

    public static ConfigFile getConfigFile() {
        return configFile;
    }
}

