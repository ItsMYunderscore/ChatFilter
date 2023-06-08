package me.itsmyunderscore.commands;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface SubcommandExecutor {
    void execute(Player player, String[] args);
}

