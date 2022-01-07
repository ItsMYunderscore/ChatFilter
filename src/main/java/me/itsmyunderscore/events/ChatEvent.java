/*
 * This plugin has been created by ItsMYunderscore.
 * It is prohibited from any use without written agreement with the author.
 *
 * Copyright (c) ItsMYunderscore 2021.
 */

package me.itsmyunderscore.events;

import me.itsmyunderscore.config.Config;
import me.itsmyunderscore.config.ForbiddenWords;
import me.itsmyunderscore.utils.Filter;
import me.itsmyunderscore.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

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
        if (Config.EXEMPT_USERS.contains(player.getName())) return;
        Message.debug("Player is not bypassed");

        String filteredWord = null;
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
                player.sendMessage(ChatColor.RED + "Do not swear in chat!");
                Message.debug("msg replaced");

                break;
            }
        }


        if (filteredWord != null) {
            String finalFilteredWord = filteredWord;
            Bukkit.getOnlinePlayers().forEach(online -> {
                if (online.hasPermission("filter.see")) {
                    Message.message(online, ChatColor.RED + "" + ChatColor.BOLD + "[ChatFilter]" + ChatColor.WHITE + " " + ChatColor.BLUE + player.getName() + ChatColor.WHITE + " just tried to say " + ChatColor.RED + finalFilteredWord);
                }
            });
        }


    }
}
