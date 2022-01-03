/*
 * This plugin has been created by ItsMYunderscore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2021.
 */

package me.itsmyunderscore.utils;

import org.bukkit.ChatColor;

public class StringUtil {

    public static String color(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
