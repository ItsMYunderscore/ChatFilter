/*
 * This plugin has been created by ItsMYunderscore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2022.
 */

package me.itsmyunderscore.commands;

import me.itsmyunderscore.config.Lang;
import me.itsmyunderscore.utils.Message;
import me.itsmyunderscore.utils.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CFLang_cmd implements CommandExecutor {
    String[] usage;

    public CFLang_cmd() {
        usage = new String[]{
                StringUtil.color("&8&m----------------------CFLang----------------------"),
                StringUtil.color("&4Not all commands are fully functional right now!"),
                StringUtil.color("&f/cflang msg_sent set [PHRASE] - sets phrase that admins sees (filter.see)"),
                StringUtil.color("&f/cflang no_perm set [PHRASE] - sets phrase that sees if he doesn't have the perm."),
                StringUtil.color("&f/cflang reload_msg set [PHRASE] - sets phrase that shows up after reload"),
                StringUtil.color("&f/cflang swearing set [PHRASE] - sets phrase that is sent to player after swearing"),
                StringUtil.color("&f/cflang improper set [PHRASE] - sets phrase that is sent to player after sending improper stuff"),
                StringUtil.color("&8&m--------------------------------------------------")
        };
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Message.log("This feature can not be used in console!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            Message.CF(player, StringUtil.color("&4&lLang messages:"));

            player.sendMessage(Lang.MSG_SENT);
            player.sendMessage(Lang.NO_PERMISSION);
            player.sendMessage(Lang.RELOAD);
            player.sendMessage(Lang.PLAYER_WARNING_SWEARING);
            player.sendMessage(Lang.PLAYER_WARNING_IMPROPER);
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("msg_sent")) {
                player.sendMessage(Lang.MSG_SENT);
                return true;
            } else if (args[0].equalsIgnoreCase("no_perm")) {
                player.sendMessage(Lang.NO_PERMISSION);
                return false;
            } else if (args[0].equalsIgnoreCase("reload_msg")) {
                player.sendMessage(Lang.RELOAD);
                return true;
            } else if (args[0].equalsIgnoreCase("swearing")) {
                player.sendMessage(Lang.PLAYER_WARNING_SWEARING);
                return false;
            } else if (args[0].equalsIgnoreCase("improper")) {
                player.sendMessage(Lang.PLAYER_WARNING_IMPROPER);
                return true;
            } else {
                Message.sendList(player, usage);
                return false;
            }
        } else if (args.length == 2) {
            if (args[1].equalsIgnoreCase("set")) {
                if (args[0].equalsIgnoreCase("msg_sent")) {
                    Message.usage(player, "/cflang msg_sent [PHRASE]");
                    return true;
                } else if (args[0].equalsIgnoreCase("no_perm")) {
                    Message.usage(player, "/cflang no_perm set [PHRASE]");
                    return false;
                } else if (args[0].equalsIgnoreCase("reload_msg")) {
                    Message.usage(player, "/cflang reload_msg set [PHRASE]");
                    return true;
                } else if (args[0].equalsIgnoreCase("swearing")) {
                    Message.usage(player, "/cflang swearing set [PHRASE]");
                    return false;
                } else if (args[0].equalsIgnoreCase("improper")) {
                    Message.usage(player, "/cflang improper set [PHRASE]");
                    return true;
                } else {
                    Message.usage(player, "This phrase doesn't exist!");
                    return false;
                }
            }
        } else if (args.length > 3) {
            if (args[1].equalsIgnoreCase("set")) {
                StringBuilder newMessage = new StringBuilder();
                for (int i = 2; i < args.length; i++) {
                    newMessage.append(" ").append(args[i]);
                }

                if (args[0].equalsIgnoreCase("msg_sent")) {
                    Lang.MSG_SENT = newMessage.toString();
                    Lang.save();
                    player.sendMessage(StringUtil.color(Lang.MSG_SENT));
                    return true;
                } else if (args[0].equalsIgnoreCase("no_perm")) {
                    Lang.NO_PERMISSION = newMessage.toString();
                    Lang.save();
                    player.sendMessage(StringUtil.color(Lang.NO_PERMISSION));
                    return false;
                } else if (args[0].equalsIgnoreCase("reload_msg")) {
                    Lang.RELOAD = newMessage.toString();
                    Lang.save();
                    player.sendMessage(StringUtil.color(Lang.RELOAD));
                    return true;
                } else if (args[0].equalsIgnoreCase("swearing")) {
                    Lang.PLAYER_WARNING_SWEARING = newMessage.toString();
                    Lang.save();
                    player.sendMessage(StringUtil.color(Lang.PLAYER_WARNING_SWEARING));
                    return false;
                } else if (args[0].equalsIgnoreCase("improper")) {
                    Lang.PLAYER_WARNING_IMPROPER = newMessage.toString();
                    Lang.save();
                    player.sendMessage(StringUtil.color(Lang.PLAYER_WARNING_IMPROPER));
                    return true;
                } else {
                    Message.usage(player, "This phrase doesn't exist!");
                    return false;
                }
            }
        }


        return false;
    }
}
