package me.itsmyunderscore.commands;

import me.itsmyunderscore.utils.Message;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatFilter_cmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Use " + ChatColor.YELLOW + "/filter");
                    return true;
                }
            }
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Chat Filter by " + ChatColor.YELLOW + "ItsMYunderscore");
            return true;
        } else {
            Message.log("Chat Filter by ItsMYunderscore");
            return false;
        }
    }
}
