/*
 * This plugin has been created by ItsMYundercore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2022.
 */

package me.itsmyunderscore.utils;

import me.itsmyunderscore.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Message {
    public static void noPermission(Player player) {
        message(player, ChatColor.RED + "You do not have permission to use this command!");
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
}
