package me.itsmyunderscore.config;

import me.itsmyunderscore.utils.ConfigFile;
import me.itsmyunderscore.utils.Message;
import me.itsmyunderscore.utils.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;

public class Lang {
    private static ConfigFile langFile;

    public static String MSG_SENT;
    public static String NO_PERMISSION;
    public static String RELOAD;

    public static String PLAYER_WARNING_SWEARING;
    public static String PLAYER_WARNING_IMPROPER;

    public Lang() {
        try {
            ConfigFile config = new ConfigFile("lang.yml");

            MSG_SENT = ChatColor.RED + "" + ChatColor.BOLD + "[CF] " + ChatColor.WHITE + StringUtil.color(config.getString("phrases.msg_sent"));
            NO_PERMISSION = config.getString("phrases.no_permission");
            RELOAD = ChatColor.RED + "" + ChatColor.BOLD + "[CF] " + ChatColor.WHITE + config.getString("phrases.reload");

            PLAYER_WARNING_SWEARING = config.getString("warnings.swearing");
            PLAYER_WARNING_IMPROPER = config.getString("warnings.improper");

            langFile = config;
        } catch (Exception exception) {
            Message.log("Critical error! 1x04");
        }

    }

    public static void reload(){
        ConfigFile config = new ConfigFile("lang.yml");

        MSG_SENT = ChatColor.RED + "" + ChatColor.BOLD + "[CF] " + ChatColor.WHITE + StringUtil.color(config.getString("phrases.msg_sent"));
        NO_PERMISSION = config.getString("phrases.no_permission");
        RELOAD = ChatColor.RED + "" + ChatColor.BOLD + "[CF] " + ChatColor.WHITE + config.getString("phrases.reload");

        PLAYER_WARNING_SWEARING = config.getString("warnings.swearing");
        PLAYER_WARNING_IMPROPER = config.getString("warnings.improper");

        langFile = config;

        Message.debug("Lang reloaded");
    }
}
