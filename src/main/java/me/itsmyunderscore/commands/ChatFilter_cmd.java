/*
 * This plugin has been created by ItsMYunderscore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2022.
 */

package me.itsmyunderscore.commands;

import me.itsmyunderscore.utils.Message;
import me.itsmyunderscore.utils.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatFilter_cmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args.length) {
            case 1: {
                if (!(sender instanceof Player) && !args[0].equalsIgnoreCase("changelog")) {
                    Message.log("Chat Filter by ItsMYunderscore");
                    return true;
                }

                Player player = null;
                if (sender instanceof Player) {
                    player = (Player) sender;
                }

                if (args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help")) {
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Use " + ChatColor.YELLOW + "/filter");
                    return true;
                } else if (args[0].equalsIgnoreCase("changelog")) {
                    changeLog(player);
                }
            }
            case 0: {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Chat Filter by " + ChatColor.YELLOW + "ItsMYunderscore");
                    return true;
                } else {
                    Message.log("Chat Filter by ItsMYunderscore");
                    return false;
                }
            }
            default: {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Chat Filter by " + ChatColor.YELLOW + "ItsMYunderscore");
                    return true;
                } else {
                    Message.log("Chat Filter by ItsMYunderscore");
                    return false;
                }
            }
        }
    }

    private void changeLog(Player player) {
        String[] changes = {"1.0.0 - Initial commit", "1.0.1 - /cfdebug - added, /filter - development began", "1.0.2 - /filter - Word manager feature added",
                "1.0.3 - /filter - Settings feature added", "1.0.4 - /chatfilter - development began, /filter - bug fixes", "1.0.5 - /filter - reload feature added",
                "1.0.6 - Lang - added", "1.0.7 - URL & IP filtering added", "1.0.8 - /chatfilter - features added", "1.0.9 - Bug fixes & optimization", "1.1.0- /cflang - feature added"};

        if (player != null) {
            player.sendMessage(StringUtil.color("&8&m---------------------Changelog---------------------"));
            for (String change : changes) {
                player.sendMessage(change);
            }
        } else {
            Message.log("---------------------Changelog---------------------");
            for (String change : changes) {
                Message.log(change);
            }
        }
    }


}
