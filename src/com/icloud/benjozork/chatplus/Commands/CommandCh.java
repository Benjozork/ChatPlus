package com.icloud.benjozork.chatplus.commands;

import com.icloud.benjozork.chatplus.main.ChatPlusAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Benjozork on 2016-01-16.
 */
public class CommandCh implements CommandExecutor {

    private ChatPlusAPI cpapi = new ChatPlusAPI();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("chatplus.channel") || sender.hasPermission("chatplus.*")) {
            if (label.equalsIgnoreCase("ch")) {

                //If the sender is the console then display message
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ChatColor.RED + "Sorry, ChatPLus does not support console commands yet.");
                    return false;
                }

                cpapi.displayChannelHelpMessage((Player) sender);
                return true;
            }
            return false;
        } else {
            return false;
        }
    }
}
