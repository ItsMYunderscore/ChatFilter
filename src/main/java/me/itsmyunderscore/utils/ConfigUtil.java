/*
 * This plugin has been created by ItsMYunderscore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2022.
 */

package me.itsmyunderscore.utils;

import me.itsmyunderscore.ChatFilter;
import me.itsmyunderscore.config.Config;
import me.itsmyunderscore.config.ForbiddenWords;
import me.itsmyunderscore.config.Lang;
import org.bukkit.entity.Player;

public class ConfigUtil {
    public static void reload(Player player) {
        if (Config.DB_ENABLED) {
            ChatFilter.getInstance().getDatabaseManager().loadConfigData();
        } else {
            Config.reload();
            ForbiddenWords.reload();
            Lang.reload();
        }



        player.sendMessage(StringUtil.color(Lang.RELOAD));
    }

    public static String createDBURL(String host, int port, String databaseName) {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + databaseName;
        return url;
    }

    public static void save() {
        Config.save();
        ForbiddenWords.save();
        Lang.save();
    }
}
