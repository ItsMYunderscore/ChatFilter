/*
 * This plugin has been created by ItsMYundercore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2022.
 */

package me.itsmyunderscore.commands;

import me.itsmyunderscore.config.Config;
import me.itsmyunderscore.utils.ConfigFile;
import me.itsmyunderscore.utils.Message;
import me.itsmyunderscore.utils.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.itsmyunderscore.ChatFilter.getFilter;

public class Filter_cmd implements CommandExecutor {

    private Filter filter;
    private ConfigFile config;
    private String[] usage;


    public Filter_cmd() {
        filter = getFilter();
        config = Config.getConfigFile();

        usage = new String[]{
                StringUtil.color("&8&m------------------------------------------------"),
                StringUtil.color("&a&lThis feature is no implemented yet!"),
                StringUtil.color("&8&m------------------------------------------------")
        };
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("filter.filter")) {
                Message.noPermission(player);
                return true;
            }
                Message.sendList(player, usage);
                return true;

        }

        Message.log("This feature is no implemented yet!");
        return false;
    }
}
