package me.itsmyunderscore.commands;

/*
 * This plugin has been created by ItsMYunderscore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2023.
 */


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import me.itsmyunderscore.ChatFilter;
import me.itsmyunderscore.config.Lang;
import me.itsmyunderscore.utils.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;


public abstract class AbstractCommand implements CommandExecutor {
    private ChatFilter main;
    private final String name;
    private final String permission;
    private Map<String, Subcommand> subcommands = Maps.newHashMap();

    protected AbstractCommand(ChatFilter main, String name, String permission) {
        this.main = main;
        this.name = name;
        this.permission = permission;

        main.getCommand(name).setExecutor(this);
    }

    public void addSubcommand(String name, String description, String usage, SubcommandExecutor executor) {
        Subcommand subcommand = new Subcommand(name, description, usage, executor);
        subcommand.setMain(main);
        subcommands.put(name, subcommand);
    }

    public String getUsage() {
        StringBuilder usage = new StringBuilder("§c§lUsage:\n");

        for (String s : subcommands.keySet()) {
            usage.append(subcommands.get(s).getUsage() + "\n");
        }

        return usage.toString();
    }

    public void onExecute(Player sender, String[] args) {
     sender.sendMessage(StringUtil.color(getUsage()));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command is not for console");
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(permission)) {
            sender.sendMessage(StringUtil.color(Lang.NO_PERMISSION));
            return false;
        }

        if (!(args.length > 0)) {
            onExecute(player, args);
            return true;
        }

        if (subcommands.containsKey(args[0])) {
            subcommands.get(args[0]).run(player, args);
            return true;
        }

        onExecute(player, args);

        return true;
    }

}
