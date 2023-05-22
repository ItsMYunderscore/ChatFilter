/*
 * File Name: ChatFilter.java
 *
 * Copyright (c) $YEAR$ Filip Kukelka also knows as ItsMY_
 * All rights reserved.
 *
 * Date of File Creation: 22/05/2023, 09:58
 *
 *  This file contains original work created by Filip Kukelka.
 *  Unauthorized use, reproduction, or distribution of this file, or any portion
 *  of it, is strictly prohibited without the express written consent of the author.
 *
 */

package me.itsmyunderscore;

import lombok.Getter;
import me.itsmyunderscore.commands.CFLang_cmd;
import me.itsmyunderscore.commands.ChatFilter_cmd;
import me.itsmyunderscore.commands.Debug_cmd;
import me.itsmyunderscore.commands.Filter_cmd;
import me.itsmyunderscore.config.Config;
import me.itsmyunderscore.config.ForbiddenWords;
import me.itsmyunderscore.config.Lang;
import me.itsmyunderscore.events.ChatEvent;
import me.itsmyunderscore.managers.DatabaseManager;
import me.itsmyunderscore.utils.ConfigUtil;
import me.itsmyunderscore.utils.Filter;
import me.itsmyunderscore.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

@Getter
public final class ChatFilter extends JavaPlugin {

    private static ChatFilter instance;

    public static ChatFilter getInstance() {
        return instance;
    }

    public static Filter getFilter() {
        return filter;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    private static Filter filter;
    private DatabaseManager databaseManager;


    @Override
    public void onEnable() {
        Message.log("[ItsMY_ChatFilter] - Starting");

        instance = this;

        initClasses();
        registerListeners();
        registerCommands();

        logMessage("Loaded...           Please, watch out for any critical errors");
    }

    @Override
    public void onDisable() {
        instance = null;

        ConfigUtil.save();

        logMessage("Plugin disabled             Thanks for using our plugin");
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
            new Lang();

            if (Config.DB_ENABLED){
                databaseManager = new DatabaseManager();

                databaseManager.loadConfigData();

                Config.save();
                ForbiddenWords.save();
                Lang.save();
            }

            filter = new Filter();
        } catch (Exception exception) {
            Message.log("Failed init - 0x03");
            Message.log("This error is critical! Try deleting all configuration files and restarting server");
            Message.log("Contact plugin developer by mailing him on: itsmyunderscore.sg@gmail.com ");
        }
    }

    private void logMessage(String msg) {
        Message.log("--------------------------------------------");
        Message.log("  ");
        Message.log("[ChatFilter by ItsMY_] - " + msg);
        Message.log("  ");
        Message.log("--------------------------------------------");
    }
}