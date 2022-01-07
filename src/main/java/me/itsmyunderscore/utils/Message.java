/*
 * This plugin has been created by ItsMYunderscore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2022.
 */

package me.itsmyunderscore.utils;

import me.itsmyunderscore.config.Config;
import me.itsmyunderscore.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Message {
    public static void noPermission(Player player) {
        message(player, Lang.NO_PERMISSION);
    }

    public static void message(Player player, String string) {
        player.sendMessage(string);
    }

    public static void sendList(Player player, String[] args) {
        player.sendMessage(args);
    }

    public static void log(String message) {
        System.out.println(message);
    }

    public static void debug(String msg) {
        if (Config.DEBUG_ENABLED) {
            Bukkit.getOnlinePlayers().forEach(online -> {
                if (online.hasPermission("filter.see")) {
                    online.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "DEBUG " + ChatColor.YELLOW + msg);
                }
            });

        }
    }

    public static void inDevelopment(Player player, String cmdName) {
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "This feature is currently in development, you are not allowed to use it!");
        log(player.getDisplayName() + " just tried to issue this command: " + cmdName + " which is currently in development.");
    }

    public static void CF(Player player, String msg) {
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[CF] " + ChatColor.WHITE + msg);
    }

    public static void isCFActive(Player player, String msg, boolean isOn) {
        String activity;
        if (isOn) {
            activity = "&aON";
        } else {
            activity = "&4OFF";
        }
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[CF] " + ChatColor.WHITE + msg + " is " + StringUtil.color(activity));
    }

    public static void CFManager(Player player, String msg) {
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[CF - WordManager] " + ChatColor.WHITE + msg);
    }

    public static void usage(Player player, String msg) {
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Usage! " + ChatColor.YELLOW + msg);
    }

}
