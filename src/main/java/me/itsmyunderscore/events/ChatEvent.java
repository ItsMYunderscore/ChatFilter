/*
 * This plugin has been created by ItsMYunderscore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2021.
 */

package me.itsmyunderscore.events;

import me.itsmyunderscore.config.ForbiddenWords;
import me.itsmyunderscore.config.Lang;
import me.itsmyunderscore.utils.Filter;
import me.itsmyunderscore.utils.Message;
import me.itsmyunderscore.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static me.itsmyunderscore.ChatFilter.getFilter;

public class ChatEvent implements Listener {

    private final Filter filter = getFilter();

    @EventHandler
    public void onChatFilter(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        Message.debug("Event registered");

        if (!filter.isActive()) return;
        Message.debug("Filter is active");

        if (event.isCancelled()) return;
        Message.debug("Event is not canceled");

        if (player.hasPermission("filter.bypass")) return;
        Message.debug("Player is not bypassed");

        String filteredWord = null;

        Message.debug("Checking for links etc. ");

        if (regex(event.getMessage())) {
            player.sendMessage(StringUtil.color(Lang.PLAYER_WARNING_IMPROPER));
            event.setCancelled(true);
        }

        for (String word : ForbiddenWords.FORBIDDEN_WORDS) {
            Message.debug("Checking for this " + word);
            if (message.toLowerCase().contains(word)) {
                filteredWord = message;
                StringBuilder selectedWord = new StringBuilder();
                Message.debug("Word found");

                for (int i = 0; i < word.length(); i++) {
                    selectedWord.append("*");
                }

                Message.debug("Word censored");
                event.setMessage(message.replace(word, selectedWord.toString()));
                player.sendMessage(StringUtil.color(Lang.PLAYER_WARNING_SWEARING));
                Message.debug("msg replaced");

                break;
            }
        }

        if (filteredWord != null) {
            String finalFilteredWord = filteredWord;
            Bukkit.getOnlinePlayers().forEach(online -> {
                if (online.hasPermission("filter.see")) {
                    Message.message(online, StringUtil.color(Lang.MSG_SENT.replace("%PLAYER%", player.getName()).replace("%WORD%", finalFilteredWord)));
                }
            });
        }
    }

    private boolean regex(String msg) {
        Pattern pattern = Pattern.compile("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}");
        Pattern pattern1 = Pattern.compile("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");

        Matcher matcher = pattern.matcher(msg);
        Matcher matcher1 = pattern1.matcher(msg);

        return matcher.find() || matcher1.find();
    }


}
