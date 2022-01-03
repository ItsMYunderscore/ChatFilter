/*
 * This plugin has been created by ItsMYunderscore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2022.
 */

package me.itsmyunderscore.commands;

import me.itsmyunderscore.config.Config;
import me.itsmyunderscore.config.ForbiddenWords;
import me.itsmyunderscore.utils.ConfigFile;
import me.itsmyunderscore.utils.Filter;
import me.itsmyunderscore.utils.Message;
import me.itsmyunderscore.utils.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicBoolean;

import static me.itsmyunderscore.ChatFilter.getFilter;

public class Filter_cmd implements CommandExecutor {

    private Filter filter;
    private ConfigFile config;
    private String[] usage;
    private String[] wordManagerUsage;

    public Filter_cmd() {
        filter = getFilter();
        config = Config.getConfigFile();

        usage = new String[]{
                StringUtil.color("&8&m----------------------Filter----------------------"),
                StringUtil.color("&a&lNot all commands are fully functional right now!"),
                StringUtil.color("&a&l/filter words - Filtered words manager"),
                StringUtil.color("&8&m--------------------------------------------------")
        };

        wordManagerUsage = new String[]{
                StringUtil.color("&8&m-------------------Word-manager-------------------"),
                StringUtil.color("&a&lNot all commands are fully functional right now!"),
                StringUtil.color("&a&l/filter words add - Adds word to forbidden"),
                StringUtil.color("&8&m--------------------------------------------------")
        };
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.log("This feature is no implemented yet!");
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("filter.filter")) {
            Message.noPermission(player);
            return true;
        } else if (args.length == 0) {
            Message.sendList(player, usage);
            return true;
        } else {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("words")) {
                    if (player.hasPermission("filter.words.list")) {
                        Message.message(player, "&a&lThees are all words that are being filtered:");
                        Message.sendList(player, (String[]) ForbiddenWords.forbidden_WORDS.toArray());
                        return true;
                    } else {
                        Message.noPermission(player);
                        return false;
                    }
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("words")) {
                    if(args[1].equalsIgnoreCase("?") || args[1].equalsIgnoreCase("help")){
                        if (player.hasPermission("filter.words.manage")){
                            Message.sendList(player, wordManagerUsage);
                            return true;
                        }
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        if (player.hasPermission("filter.words.manage")) {
                            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Usage! " + ChatColor.YELLOW + "/filter words add [WORD]");
                            return true;
                        } else {
                            Message.noPermission(player);
                            return false;
                        }
                    }
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("words") && args[1].equalsIgnoreCase("add")) {
                    if (player.hasPermission("filter.words.manage")) {
                        AtomicBoolean isFiltered = new AtomicBoolean(false);    //todo: check for errors
                        ForbiddenWords.forbidden_WORDS.forEach(word -> {
                            if (args[2].contains(word)) {
                                player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[CF - WordManager]" + ChatColor.WHITE + " " + ChatColor.WHITE + "this word is already being filtered!");
                                isFiltered.set(true);
                            }
                        });
                        if (isFiltered.get()) {
                            isFiltered.set(false);
                            return false;
                        } else {
                            ForbiddenWords.forbidden_WORDS.add(args[2]);
                            new ForbiddenWords();
                            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[CF - WordManager]" + ChatColor.WHITE + " " + ChatColor.WHITE + "word added!");
                        }
                    } else {
                        Message.noPermission(player);
                        return false;
                    }
                }
            }

        }

        return false;
    }
}
