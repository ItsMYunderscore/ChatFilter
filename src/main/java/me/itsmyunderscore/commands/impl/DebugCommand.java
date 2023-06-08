package me.itsmyunderscore.commands.impl;

/*
 * This plugin has been created by ItsMYunderscore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2023.
 */

import me.itsmyunderscore.ChatFilter;
import me.itsmyunderscore.commands.AbstractCommand;
import me.itsmyunderscore.config.Config;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class DebugCommand extends AbstractCommand {
    public DebugCommand(ChatFilter main) {
        super(main, "cfdebug", "filter.debug");
    }

    @Override
    public void onExecute(Player player, String[] args) {
        if (Config.DEBUG_ENABLED) {
            Config.DEBUG_ENABLED = false;
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "DEBUG " + ChatColor.YELLOW + "Disabled");
        } else {
            Config.DEBUG_ENABLED = true;
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "DEBUG " + ChatColor.GREEN + "Enabled");
        }
        Config.save();
    }
}
