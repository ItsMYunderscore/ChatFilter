package me.itsmyunderscore.commands.impl;

/*
 * This plugin has been created by ItsMYunderscore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2023.
 */


import me.itsmyunderscore.ChatFilter;
import me.itsmyunderscore.commands.AbstractCommand;
import me.itsmyunderscore.utils.Message;
import me.itsmyunderscore.utils.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatFilterCommand extends AbstractCommand {
    public ChatFilterCommand(ChatFilter main) {
        super(main, "ChatFilter", "");

        addSubcommand("changelog", "Shows changelog", "/ChatFilter changelog", (player, args) -> {
            String[] changes = {"1.0.0 - Initial commit", "1.0.1 - /cfdebug - added, /filter - development began", "1.0.2 - /filter - Word manager feature added",
                    "1.0.3 - /filter - Settings feature added", "1.0.4 - /chatfilter - development began, /filter - bug fixes", "1.0.5 - /filter - reload feature added",
                    "1.0.6 - Lang - added", "1.0.7 - URL & IP filtering added", "1.0.8 - /chatfilter - features added", "1.0.9 - Bug fixes & optimization", "1.1.0 - /cflang - feature added", "1.2.0 - refactoring @ implementation of bruteforceing hidden characters representing letters in messages & added db support"};

            player.sendMessage(StringUtil.color("&8&m---------------------Changelog---------------------"));
            for (String change : changes) {
                player.sendMessage(change);

            }
        });

    }

    @Override
    public void onExecute(Player player, String[] args) {
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Chat Filter by " + ChatColor.YELLOW + "ItsMYunderscore");
    }

}
