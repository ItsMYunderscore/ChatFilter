/*
 * This plugin has been created by ItsMYunderscore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2022.
 */

package me.itsmyunderscore.commands;

import me.itsmyunderscore.config.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Debug_cmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        if (!player.hasPermission("filter.debug")) return true;
        if (label.equalsIgnoreCase("cfdebug")) {
            if (Config.DEBUG_ENABLED) {
                Config.DEBUG_ENABLED = false;
                player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "DEBUG " + ChatColor.YELLOW + "Disabled");
            } else {
                Config.DEBUG_ENABLED = true;
                player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "DEBUG " + ChatColor.GREEN + "Enabled");
            }
        }
        Config.save();
        return false;
    }
}
