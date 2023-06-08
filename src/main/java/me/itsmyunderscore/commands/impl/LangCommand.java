package me.itsmyunderscore.commands.impl;

/*
 * This plugin has been created by ItsMYunderscore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2023.
 */

import me.itsmyunderscore.ChatFilter;
import me.itsmyunderscore.commands.AbstractCommand;
import me.itsmyunderscore.config.Lang;
import me.itsmyunderscore.utils.Message;
import me.itsmyunderscore.utils.StringUtil;
import org.bukkit.entity.Player;

public class LangCommand extends AbstractCommand {

    public LangCommand(ChatFilter main) {
        super(main,"cflang", "cflang.use");
        addSubcommand("msg_sent", "Sets the phrase that admins see (filter.see)", "/cflang msg_sent set [PHRASE]", this::setMsgSent);
        addSubcommand("no_perm", "Sets the phrase that is shown if the player doesn't have permission", "/cflang no_perm set [PHRASE]", this::setNoPerm);
        addSubcommand("reload_msg", "Sets the phrase that shows up after reload", "/cflang reload_msg set [PHRASE]", this::setReloadMsg);
        addSubcommand("swearing", "Sets the phrase that is sent to the player after swearing", "/cflang swearing set [PHRASE]", this::setSwearing);
        addSubcommand("improper", "Sets the phrase that is sent to the player after sending improper stuff", "/cflang improper set [PHRASE]", this::setImproper);
    }

    private void setMsgSent(Player player, String[] args) {
        if (args.length == 2) {
            Lang.MSG_SENT = args[1];
            Lang.save();
            player.sendMessage(StringUtil.color(Lang.MSG_SENT));
        } else {
            Message.usage(player, "/cflang msg_sent set [PHRASE]");
        }
    }

    private void setNoPerm(Player player, String[] args) {
        if (args.length == 2) {
            Lang.NO_PERMISSION = args[1];
            Lang.save();
            player.sendMessage(StringUtil.color(Lang.NO_PERMISSION));
        } else {
            Message.usage(player, "/cflang no_perm set [PHRASE]");
        }
    }

    private void setReloadMsg(Player player, String[] args) {
        if (args.length == 2) {
            Lang.RELOAD = args[1];
            Lang.save();
            player.sendMessage(StringUtil.color(Lang.RELOAD));
        } else {
            Message.usage(player, "/cflang reload_msg set [PHRASE]");
        }
    }

    private void setSwearing(Player player, String[] args) {
        if (args.length == 2) {
            Lang.PLAYER_WARNING_SWEARING = args[1];
            Lang.save();
            player.sendMessage(StringUtil.color(Lang.PLAYER_WARNING_SWEARING));
        } else {
            Message.usage(player, "/cflang swearing set [PHRASE]");
        }
    }

    private void setImproper(Player player, String[] args) {
        if (args.length == 2) {
            Lang.PLAYER_WARNING_IMPROPER = args[1];
            Lang.save();
            player.sendMessage(StringUtil.color(Lang.PLAYER_WARNING_IMPROPER));
        } else {
            Message.usage(player, "/cflang improper set [PHRASE]");
        }
    }

    @Override
    public void onExecute(Player player, String[] args) {
        Message.CF(player, StringUtil.color("&4&lLang messages:"));
        player.sendMessage(Lang.MSG_SENT);
        player.sendMessage(Lang.NO_PERMISSION);
        player.sendMessage(Lang.RELOAD);
        player.sendMessage(Lang.PLAYER_WARNING_SWEARING);
        player.sendMessage(Lang.PLAYER_WARNING_IMPROPER);
    }
}
