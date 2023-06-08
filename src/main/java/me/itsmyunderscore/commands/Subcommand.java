package me.itsmyunderscore.commands;

/*
 * This plugin has been created by ItsMYunderscore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2023.
 */

import lombok.Getter;
import me.itsmyunderscore.ChatFilter;
import me.itsmyunderscore.utils.StringUtil;
import org.bukkit.entity.Player;

@Getter
public class Subcommand {

    private ChatFilter main;
    private final String name;
    private final String description;
    private final String usage;
    private final SubcommandExecutor executor;

    public Subcommand( String name, String description, String usage, SubcommandExecutor executor ) {
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.executor = executor;
    }

    public void run(Player player, String[] args){
        player.sendMessage(StringUtil.color("§c" + usage));
    }

    public void setMain(ChatFilter main) {
        this.main = main;
    }
}
