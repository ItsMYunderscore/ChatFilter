/*
 * This plugin has been created by ItsMYunderscore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2022.
 */

package me.itsmyunderscore.commands;

import me.itsmyunderscore.config.Config;
import me.itsmyunderscore.config.ForbiddenWords;
import me.itsmyunderscore.utils.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicBoolean;

public class Filter_cmd implements CommandExecutor {

    private final String[] usage;
    private final String[] wordManagerUsage;
    private final String[] settingsUsage;
    private final Filter filter;
    private final ConfigFile config;

    public Filter_cmd() {
        filter = getFilter();
        config = Config.getConfigFile();


        usage = new String[]{
                StringUtil.color("&8&m----------------------Filter----------------------"),
                StringUtil.color("&4Not all commands are fully functional right now!"),
                StringUtil.color("&f/filter words - Filtered words manager"),
                StringUtil.color("&f/filter settings - Command for settings"),
                StringUtil.color("&8&m--------------------------------------------------")
        };

        wordManagerUsage = new String[]{
                StringUtil.color("&8&m-------------------Word-manager-------------------"),
                StringUtil.color("&4Not all commands are fully functional right now!"),
                StringUtil.color("&f/filter words add - Adds word to forbidden"),
                StringUtil.color("&f/filter words remove - Removes word to forbidden"),
                StringUtil.color("&8&m--------------------------------------------------")
        };

        settingsUsage = new String[]{
                StringUtil.color("&8&m---------------------Settings---------------------"),
                StringUtil.color("&4Not all commands are fully functional right now!"),
                StringUtil.color("&f/filter settings toggle - Toggles Chat Filter's activity (on/off)"),
                StringUtil.color("&8&m--------------------------------------------------")
        };
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.log("This feature is only for players!");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("filter.filter")) {
            Message.noPermission(player);
            return true;
        }
        if (args.length == 0) {
            Message.sendList(player, usage);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "words": {
                if (!Config.WORDMANAGER_CMD_ENABLED) {
                    Message.CFManager(player, "Word manager is inactive");
                    return false;
                }
                switch (args.length) {
                    case 1: {
                        if (player.hasPermission("filter.words.list")) {
                            StringBuilder words = new StringBuilder();
                            for (String word : ForbiddenWords.FORBIDDEN_WORDS) {
                                words.append(word).append(", ");
                            }
                            Message.message(player, StringUtil.color("&a&lThese are all words that are being filtered:"));
                            Message.message(player, words.toString());
                            return true;
                        } else {
                            Message.noPermission(player);
                            return false;
                        }
                    }

                    case 2: {
                        if (args[1].equalsIgnoreCase("?") || args[1].equalsIgnoreCase("help")) {
                            if (player.hasPermission("filter.words.manage")) {
                                Message.sendList(player, wordManagerUsage);
                                return true;
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (player.hasPermission("filter.words.manage")) {
                                Message.usage(player, "/filter words add [WORD]");
                                return true;
                            } else {
                                Message.noPermission(player);
                                return false;
                            }
                        } else if (args[1].equalsIgnoreCase("remove")) {
                            if (player.hasPermission("filter.words.manage")) {
                                Message.usage(player, "/filter words remove [WORD]");
                                return true;
                            } else {
                                Message.noPermission(player);
                                return false;
                            }
                        }

                    }

                    case 3:
                        if (args[1].equalsIgnoreCase("add")) {
                            if (player.hasPermission("filter.words.manage")) {
                                AtomicBoolean isFiltered = new AtomicBoolean(false);

                                if (ForbiddenWords.FORBIDDEN_WORDS.contains(args[2])) {
                                    Message.CFManager(player, "This word is already being filtered!");
                                    isFiltered.set(true);
                                }

                                if (isFiltered.get()) {
                                    isFiltered.set(false);
                                    return false;
                                } else {
                                    ForbiddenWords.FORBIDDEN_WORDS.add(args[2]);
                                    ForbiddenWords.save();
                                    Message.CFManager(player, "Word added!");
                                    return true;
                                }
                            } else {
                                Message.noPermission(player);
                                return false;
                            }
                        } else if (args[1].equalsIgnoreCase("remove")) {
                            if (player.hasPermission("filter.words.manage")) {
                                AtomicBoolean isFiltered = new AtomicBoolean(false);

                                if (!ForbiddenWords.FORBIDDEN_WORDS.contains(args[2])) {
                                    Message.CFManager(player, "This word is not being filtered!");
                                    isFiltered.set(true);
                                }

                                if (isFiltered.get()) {
                                    isFiltered.set(false);
                                    return false;
                                } else {
                                    ForbiddenWords.FORBIDDEN_WORDS.remove(args[2]);
                                    ForbiddenWords.save();
                                    Message.CFManager(player, "Word removed!");
                                    return true;
                                }
                            } else {
                                Message.noPermission(player);
                                return false;
                            }
                        }

                    default: {
                        Message.usage(player, "Check /filter words ? for help");
                        return false;
                    }
                }


            }
            case "settings": {
                if (!player.hasPermission("filter.settings")) {
                    Message.noPermission(player);
                    return false;
                }
                switch (args.length) {
                    case 1: {
                        Message.isCFActive(player, "Chat Filter", Config.FILTER_ENABLED);
                        return true;
                    }

                    case 2: {
                        if (args[1].equalsIgnoreCase("?") || args[1].equalsIgnoreCase("help")) {
                            Message.sendList(player, settingsUsage);
                            return true;
                        } else if (args[1].equalsIgnoreCase(("toggle"))) {
                            if (Config.FILTER_ENABLED) {
                                Config.FILTER_ENABLED = false;
                                Config.save();
                                Message.isCFActive(player, "Setting changed: Chat Filter", Config.FILTER_ENABLED);
                                return true;
                            } else {
                                Config.FILTER_ENABLED = true;
                                Config.save();
                                Message.isCFActive(player, "Setting changed: Chat Filter", Config.FILTER_ENABLED);
                                return false;
                            }

                        }
                    }

                    default: {
                        Message.usage(player, "Check /filter settings ? for help");
                        return true;
                    }
                }
            }
            case "reload": {
                if (player.hasPermission("filter.reload")) {
                    ConfigUtil.reload(player);
                    return true;
                } else {
                    Message.noPermission(player);
                    return false;
                }
            }
            default: {
                Message.sendList(player, usage);
                return true;
            }
        }
    }


    public Filter getFilter() {
        return filter;
    }

    public ConfigFile getConfig() {
        return config;
    }
}
