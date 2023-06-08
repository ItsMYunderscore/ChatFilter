/*
 * File Name: DatabaseManager.java
 *
 * Copyright (c) $YEAR$ Filip Kukelka also knows as ItsMY_
 * All rights reserved.
 *
 * Date of File Creation: 22/05/2023, 10:19
 *
 *  This file contains original work created by Filip Kukelka.
 *  Unauthorized use, reproduction, or distribution of this file, or any portion
 *  of it, is strictly prohibited without the express written consent of the author.
 *
 */

package me.itsmyunderscore.managers;

import me.itsmyunderscore.config.Config;
import me.itsmyunderscore.config.ForbiddenWords;
import me.itsmyunderscore.config.Lang;
import me.itsmyunderscore.config.Chars;
import me.itsmyunderscore.utils.ConfigUtil;
import me.itsmyunderscore.utils.Message;
import org.bukkit.Bukkit;

import java.sql.*;

public class DatabaseManager {
    private final String DB_URL;
    private final String DB_USER;
    private final String DB_PASSWORD;

    public DatabaseManager() {
        this.DB_URL = ConfigUtil.createDBURL(Config.DB_HOST, Config.DB_PORT, Config.DB_NAME);
        this.DB_USER = Config.DB_USER;
        this.DB_PASSWORD = Config.DB_USER_PASSWORD;
    }

    public void loadConfigData() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Load data into Config class
            PreparedStatement configStmt = conn.prepareStatement("SELECT * FROM options");
            ResultSet configResult = configStmt.executeQuery();
            if (configResult.next()) {
                Config.FILTER_ENABLED = configResult.getBoolean("filter_enabled");
                Config.DEBUG_ENABLED = configResult.getBoolean("filter_debug");
                Config.WORDMANAGER_CMD_ENABLED = configResult.getBoolean("filter_word_manager");
                Config.SAFE_MODE_ENABLED = configResult.getBoolean("safe_mode_enabled");
                Config.ALLOW_CERTAIN_WEBSITES = configResult.getBoolean("safe_mode_allow_my_websites");
            }

            // Load data into ForbiddenWords class
            PreparedStatement forbiddenWordsStmt = conn.prepareStatement("SELECT * FROM forbidden_words");
            ResultSet forbiddenWordsResult = forbiddenWordsStmt.executeQuery();
            ForbiddenWords.FORBIDDEN_WORDS.clear();
            while (forbiddenWordsResult.next()) {
                ForbiddenWords.FORBIDDEN_WORDS.add(forbiddenWordsResult.getString("word"));
            }

            // Load data into Lang class
            PreparedStatement langStmt = conn.prepareStatement("SELECT * FROM lang_phrases");
            ResultSet langResult = langStmt.executeQuery();
            if (langResult.next()) {
                Lang.MSG_SENT = langResult.getString("message_sent");
                Lang.NO_PERMISSION = langResult.getString("no_permission");
                Lang.RELOAD = langResult.getString("reload_completed");
            }

            // Load data into Chars class
            PreparedStatement charsStmt = conn.prepareStatement("SELECT * FROM characters");
            ResultSet charsResult = charsStmt.executeQuery();
            Chars.CHARACTERS.clear();
            while (charsResult.next()) {
                String character = charsResult.getString("character");
                String description = charsResult.getString("description");
                String[] charArray = { character, description };
                Chars.CHARACTERS.add(charArray);
            }

            conn.close();
        } catch (SQLException e) {
            Message.log("DB connection failed - " + e.getMessage());
            Message.log("Shutting down...");
            Bukkit.shutdown();
        }
    }

    public void saveConfigData() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Save data from Config class
            PreparedStatement configStmt = conn.prepareStatement("UPDATE options SET filter_enabled = ?, filter_debug = ?, filter_word_manager = ?, safe_mode_enabled = ?, safe_mode_allow_my_websites = ?");
            configStmt.setBoolean(1, Config.FILTER_ENABLED);
            configStmt.setBoolean(2, Config.DEBUG_ENABLED);
            configStmt.setBoolean(3, Config.WORDMANAGER_CMD_ENABLED);
            configStmt.setBoolean(4, Config.SAFE_MODE_ENABLED);
            configStmt.setBoolean(5, Config.ALLOW_CERTAIN_WEBSITES);
            configStmt.executeUpdate();

            // Save data from ForbiddenWords class
            PreparedStatement forbiddenWordsStmt = conn.prepareStatement("TRUNCATE TABLE forbidden_words");
            forbiddenWordsStmt.executeUpdate();
            for (String word : ForbiddenWords.FORBIDDEN_WORDS) {
                PreparedStatement insertWordStmt = conn.prepareStatement("INSERT INTO forbidden_words (word) VALUES (?)");
                insertWordStmt.setString(1, word);
                insertWordStmt.executeUpdate();
            }

            // Save data from Lang class
            PreparedStatement langStmt = conn.prepareStatement("UPDATE lang_phrases SET message_sent = ?, no_permission = ?, reload_completed = ?");
            langStmt.setString(1, Lang.MSG_SENT);
            langStmt.setString(2, Lang.NO_PERMISSION);
            langStmt.setString(3, Lang.RELOAD);
            langStmt.executeUpdate();

            // Save data from Chars class
            PreparedStatement charsStmt = conn.prepareStatement("TRUNCATE TABLE characters");
            charsStmt.executeUpdate();
            for (String[] charArray : Chars.CHARACTERS) {
                PreparedStatement insertCharStmt = conn.prepareStatement("INSERT INTO characters (character, description) VALUES (?, ?)");
                insertCharStmt.setString(1, charArray[0]);
                insertCharStmt.setString(2, charArray[1]);
                insertCharStmt.executeUpdate();
            }

            conn.close();
        } catch (SQLException e) {
            Message.log("DB connection failed - " + e.getMessage());
            Message.log("Shutting down...");
            Bukkit.shutdown();;
        }
    }

    public void reloadConfigData() {
        loadConfigData();
    }
}
