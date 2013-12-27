/*
 * Copyright (C) 2013 Benjozork
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ca.hotmail.benjozork.chatplus.Commands;

import ca.hotmail.benjozork.chatplus.Main.ChatPlus;
import ca.hotmail.benjozork.chatplus.Main.ChatPlusAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Benjozork
 */
public class CommandCp implements CommandExecutor {

    private ChatPlusAPI cpapi = new ChatPlusAPI();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("cp") || label.equalsIgnoreCase("chp")) {
            if (args.length == 0) {
                //Displays the help page
                cpapi.displayHelpMessage((Player) sender);
                if (args.length > 3) {
                    //Too many arguments
                    sender.sendMessage("Too many arguments!");
                    return false;
                }
            }
            if (args.length == 1) {
                switch (args[0]) {
                    case "pause":
                        if (cpapi.isChatPaused() == true) {
                            cpapi.setPaused(false);
                            Bukkit.getServer().broadcastMessage("[" + ChatColor.GOLD + "ChatPlus" + ChatColor.WHITE + "]" + ChatColor.AQUA + " Chat is no longer paused!");
                        } else {
                            cpapi.setPaused(true);
                            Bukkit.getServer().broadcastMessage("[" + ChatColor.GOLD + "ChatPlus" + ChatColor.WHITE + "]" + ChatColor.AQUA + " Chat is now paused!");
                        }
                        if (args.length > 2) {
                            //Too many arguments
                            sender.sendMessage("Too many arguments!");
                            return false;
                        }
                        break;
                    case "nocaps":
                        if (cpapi.isLowerCasingEnabled() == true) {
                            cpapi.setLowerCasingEnabled(false);
                            Bukkit.getServer().broadcastMessage("[" + ChatColor.GOLD + "ChatPlus" + ChatColor.WHITE + "]" + ChatColor.AQUA + " LowerCasing off!");
                        } else {
                            cpapi.setLowerCasingEnabled(true);
                            Bukkit.getServer().broadcastMessage("[" + ChatColor.GOLD + "ChatPlus" + ChatColor.WHITE + "]" + ChatColor.AQUA + " LowerCasing on!");
                        }
                        if (args.length > 2) {
                            //Too many arguments
                            sender.sendMessage("Too many arguments!");
                            return false;
                        }
                        break;
                    case "help":
                        //Displays help page
                        cpapi.displayHelpMessage((Player) sender);
                        if (args.length > 2) {
                            //Too many arguments
                            sender.sendMessage("Too many arguments!");
                            return false;
                        }
                        break;
                    case "clear":
                        for (int i = 0; i < 100; i++) {
                            Bukkit.broadcastMessage("");
                        }
                        if (args.length > 2) {
                            //Too many arguments
                            sender.sendMessage("Too many arguments!");
                            return false;
                        }
                        break;
                    case "chatas":
                        sender.sendMessage(ChatColor.DARK_RED + "Too few arguments for command " + ChatColor.AQUA + "chatas");
                        break;
                    default:
                        sender.sendMessage(ChatColor.DARK_RED + "Unknown command! Type in " + ChatColor.AQUA + "/cp help");
                        return false;
                }
            }
            if (args.length >= 3) {
                switch (args[0]) {
                    case "chatas":
                        if (Bukkit.getServer().getPlayer(args[1]) != null) {
                            String dpName = args[1];
                            Player displayPlayer = Bukkit.getServer().getPlayer(dpName);

                            String message;
                            final StringBuilder bldr = new StringBuilder();
                            for (int i = 2; i < args.length; i++) {
                                if (i != 2) {
                                    bldr.append(" ");
                                }
                                bldr.append(args[i]);
                            }
                            Bukkit.getServer().broadcastMessage("<" + displayPlayer.getDisplayName() + ">" + " " + bldr.toString());
                        } else {
                            sender.sendMessage(ChatColor.DARK_RED + "This player is not online...");
                            return false;
                        }
                    break;
                    default:
                        sender.sendMessage(ChatColor.DARK_RED + "Unknown command! Type in " + ChatColor.AQUA + "/cp help");
                }
            }
            return true;
        }
        return false;
    }
}
