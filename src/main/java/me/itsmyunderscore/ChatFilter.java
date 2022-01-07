/*
 * This plugin has been created by ItsMYunderscore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2021.
 */

package me.itsmyunderscore;

import me.itsmyunderscore.commands.CFLang_cmd;
import me.itsmyunderscore.commands.ChatFilter_cmd;
import me.itsmyunderscore.commands.Debug_cmd;
import me.itsmyunderscore.commands.Filter_cmd;
import me.itsmyunderscore.config.Config;
import me.itsmyunderscore.config.DevelopmentConfig;
import me.itsmyunderscore.config.ForbiddenWords;
import me.itsmyunderscore.config.Lang;
import me.itsmyunderscore.events.ChatEvent;
import me.itsmyunderscore.utils.Filter;
import me.itsmyunderscore.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;


public final class ChatFilter extends JavaPlugin {

    private static ChatFilter instance;
    private static Filter filter;

    public static ChatFilter getInstance() {
        return instance;
    }

    public static Filter getFilter() {
        return filter;
    }

    @Override
    public void onEnable() {
        Message.log("[ItsMY_ChatFilter] - Starting");

        instance = this;

        initClasses();
        registerListeners();
        registerCommands();

        startupMessage("Loaded...           Please, watch out for any critical errors");
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private void registerListeners() {
        try {
            PluginManager manager = Bukkit.getPluginManager();

            manager.registerEvents(new ChatEvent(), this);
        } catch (Exception exception) {
            Message.log("Registering failed - 0x01");
            Message.log("This error is critical!");
            Message.log("Contact plugin developer by mailing him on: itsmyunderscore.sg@gmail.com ");
        }
    }

    private void registerCommands() {
        try {
            Objects.requireNonNull(getCommand("filter")).setExecutor(new Filter_cmd());
            Objects.requireNonNull(getCommand("cfdebug")).setExecutor(new Debug_cmd());
            Objects.requireNonNull(getCommand("ChatFilter")).setExecutor(new ChatFilter_cmd());
            Objects.requireNonNull(getCommand("CFLang")).setExecutor(new CFLang_cmd());
        } catch (Exception exception) {
            Message.log("Registering failed or not implemented yet - 0x02");
        }
    }

    private void initClasses() {
        try {
            new Config();
            new ForbiddenWords();
            new DevelopmentConfig();
            new Lang();

            filter = new Filter();
        } catch (Exception exception) {
            Message.log("Failed init - 0x03");
            Message.log("This error is critical! Try deleting all configuration files and restarting server");
            Message.log("Contact plugin developer by mailing him on: itsmyunderscore.sg@gmail.com ");
        }
    }

    public void startupMessage(String s) {
        Message.log("--------------------------------------------");
        Message.log("  ");
        Message.log("[ItsMY_ChatFilter] - " + s);
        Message.log("  ");
        Message.log("--------------------------------------------");
    }
}

