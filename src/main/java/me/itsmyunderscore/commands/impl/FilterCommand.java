package me.itsmyunderscore.commands.impl;

/*
 * This plugin has been created by ItsMYunderscore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2023.
 */

import lombok.Getter;
import me.itsmyunderscore.ChatFilter;
import me.itsmyunderscore.commands.AbstractCommand;
import me.itsmyunderscore.config.Config;
import me.itsmyunderscore.config.ForbiddenWords;
import me.itsmyunderscore.utils.*;
import org.bukkit.entity.Player;

public class FilterCommand extends AbstractCommand {
    @Getter
    String[] usage = new String[]{
            StringUtil.color("&8&m----------------------Filter----------------------"),
            StringUtil.color("&4Not all commands are fully functional right now!"),
            StringUtil.color("&f/filter words - Filtered words manager"),
            StringUtil.color("&f/filter settings - Command for settings"),
            StringUtil.color("&8&m--------------------------------------------------")
    };

    public FilterCommand(ChatFilter main) {
        super(main, "filter", "filter.filter");
        addSubcommand("words", "Filtered words manager", "/filter words", this::handleWordsCommand);
        addSubcommand("settings", "Command for settings", "/filter settings", this::handleSettingsCommand);
    }

    @Override
    public void onExecute(Player sender, String[] args) {
        Message.sendList(sender, getUsage());
    }

    private void handleWordsCommand(Player player, String[] args) {
        if (!Config.WORDMANAGER_CMD_ENABLED) {
            Message.CFManager(player, "Word manager is inactive");
            return;
        }

        if (!player.hasPermission("filter.words.list")) {
            Message.noPermission(player);
            return;
        }

        if (args.length == 1) {
            StringBuilder words = new StringBuilder();
            for (String word : ForbiddenWords.FORBIDDEN_WORDS) {
                words.append(word).append(", ");
            }
            Message.message(player, StringUtil.color("&a&lThese are all words that are being filtered:"));
            Message.message(player, words.toString());
        } else if (args.length == 3) {
            if (args[1].equalsIgnoreCase("add")) {
                if (player.hasPermission("filter.words.manage")) {
                    if (ForbiddenWords.FORBIDDEN_WORDS.contains(args[2])) {
                        Message.CFManager(player, "This word is already being filtered!");
                        return;
                    }
                    ForbiddenWords.FORBIDDEN_WORDS.add(args[2]);
                    ForbiddenWords.save();
                    Message.CFManager(player, "Word added!");
                } else {
                    Message.noPermission(player);
                }
            } else if (args[1].equalsIgnoreCase("remove")) {
                if (player.hasPermission("filter.words.manage")) {
                    if (!ForbiddenWords.FORBIDDEN_WORDS.contains(args[2])) {
                        Message.CFManager(player, "This word is not being filtered!");
                        return;
                    }
                    ForbiddenWords.FORBIDDEN_WORDS.remove(args[2]);
                    ForbiddenWords.save();
                    Message.CFManager(player, "Word removed!");
                } else {
                    Message.noPermission(player);
                }
            } else {
                Message.usage(player, "Check /filter words ? for help");
            }
        } else {
            Message.usage(player, "Check /filter words ? for help");
        }
    }

    private void handleSettingsCommand(Player player, String[] args) {
        if (!player.hasPermission("filter.settings")) {
            Message.noPermission(player);
            return;
        }

        if (args.length == 1) {
            Message.isCFActive(player, "Chat Filter", Config.FILTER_ENABLED);
        } else if (args.length == 2 && args[1].equalsIgnoreCase("toggle")) {
            if (Config.FILTER_ENABLED) {
                Config.FILTER_ENABLED = false;
                Config.save();
                Message.isCFActive(player, "Setting changed: Chat Filter", Config.FILTER_ENABLED);
            } else {
                Config.FILTER_ENABLED = true;
                Config.save();
                Message.isCFActive(player, "Setting changed: Chat Filter", Config.FILTER_ENABLED);
            }
        } else {
            Message.usage(player, "Check /filter settings ? for help");
        }
    }
}
