package me.benjozork.chatplus.commands;

import me.benjozork.chatplus.ChatPlus;
import me.benjozork.chatplus.ChatPlusAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Benjozork
 */
public class CommandCp implements CommandExecutor {

    private ChatPlusAPI cpapi = new ChatPlusAPI();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("chatplus.main") || sender.hasPermission("chatplus.*")) {
            if (label.equalsIgnoreCase("cp")) {

                //If the sender is the console then display message
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ChatColor.RED + "Sorry, ChatPlus does not support console commands yet.");
                    return false;
                }

                //If no arguments are provided then display help page
                if (args.length == 0) {

                    //Displays the help page
                    cpapi.displayHelpMessage((Player) sender);
                    return true;

                    //End of command: no command

                }
                if (args[0].equalsIgnoreCase("pause")) {

                    //Enable/disable chat pausing
                    if (cpapi.isChatPaused()) {
                        cpapi.setPaused(false);
                        Bukkit.getServer().broadcastMessage("[" + ChatColor.GOLD + "ChatPlus" + ChatColor.WHITE + "]" + ChatColor.AQUA + " Chat is no longer paused!");
                        return true;
                    } else {
                        cpapi.setPaused(true);
                        Bukkit.getServer().broadcastMessage("[" + ChatColor.GOLD + "ChatPlus" + ChatColor.WHITE + "]" + ChatColor.AQUA + " Chat is now paused!");
                        return true;
                    }

                    //End of command: pause

                } else if (args[0].equalsIgnoreCase("nocaps")) {

                    //Enable/disbale lowercasing
                    if (cpapi.isLowerCasingEnabled()) {
                        cpapi.setLowerCasingEnabled(false);
                        Bukkit.getServer().broadcastMessage("[" + ChatColor.GOLD + "ChatPlus" + ChatColor.WHITE + "]" + ChatColor.AQUA + " LowerCasing off!");
                        return true;
                    } else {
                        cpapi.setLowerCasingEnabled(true);
                        Bukkit.getServer().broadcastMessage("[" + ChatColor.GOLD + "ChatPlus" + ChatColor.WHITE + "]" + ChatColor.AQUA + " LowerCasing on!");
                        return true;
                    }

                    //End of command: nocaps

                } else if (args[0].equalsIgnoreCase("help")) {
                    //Displays help page
                    cpapi.displayHelpMessage((Player) sender);
                    return true;

                    //End of command: help

                } else if (args[0].equalsIgnoreCase("clear")) {
                    for (int i = 0; i < 100; i++) {
                        Bukkit.broadcastMessage("");
                    }
                    return true;

                    //End of command: clear

                } else if (args[0].equalsIgnoreCase("set")) {

                    //Too few arguments for command: set
                    if (args.length == 1) {
                        cpapi.displaySetHelpMessage((Player) sender);
                        return false;
                    }

                    //If user sets join message
                    if (args[1].equalsIgnoreCase("joinmsg")) {

                        //If user does not specify join message
                        if (args.length == 2) {
                            sender.sendMessage(ChatColor.RED + "Please specify a join message.");
                            return false;
                        }

                        //Build join message
                        StringBuilder strb = new StringBuilder();
                        for (int i = 2; i < args.length; i++) {
                            if (i != 2) {
                                strb.append(" ");
                            }
                            strb.append(args[i]);
                        }

                        cpapi.setJoinMessage(strb.toString());
                        sender.sendMessage("[" + ChatColor.YELLOW + "ChatPlus" + ChatColor.WHITE + "]" + " Join message set.");
                        return true;
                    }

                    //If user sets leave message
                    else if (args[1].equalsIgnoreCase("leavemsg")) {

                        //If user does not specify leave message
                        if (args.length == 2) {
                            sender.sendMessage(ChatColor.RED + "Please specify a leave message.");
                            return false;
                        }

                        //Build leave message
                        StringBuilder strb = new StringBuilder();
                        for (int i = 2; i < args.length; i++) {
                            if (i != 2) {
                                strb.append(" ");
                            }
                            strb.append(args[i]);
                        }

                        cpapi.setLeaveMessage(strb.toString());
                        sender.sendMessage("[" + ChatColor.YELLOW + "ChatPlus" + ChatColor.WHITE + "]" + " Leave message set.");
                        return true;
                    }

                    //If parameter is invalid
                    else {
                        sender.sendMessage(ChatColor.RED + "Invalid paramter. Try /cp set for a list of them.");
                    }

                    //End of command: set

                } else if (args[0].equalsIgnoreCase("join")) {
                    Bukkit.broadcastMessage(cpapi.processMessageTags(ChatPlus.getInstance().getConfig().getString("messages.join"), (Player) sender, args[1], "FAKE_PLAYER_PROCESS_JOIN"));
                    return true;

                    //End of command: join

                } else if (args[0].equalsIgnoreCase("leave")) {
                    Bukkit.broadcastMessage(cpapi.processMessageTags(ChatPlus.getInstance().getConfig().getString("messages.leave"), (Player) sender, args[1], "FAKE_PLAYER_PROCESS_LEAVE"));
                    return true;

                    //End of command: leave

                } else if (args[0].equalsIgnoreCase("chatas")) {

                    //Too few arguments for chatas
                    if (args.length == 1 || args.length == 2) {
                        sender.sendMessage(ChatColor.RED + "Too few arguments for command /cp chatas.");
                        return false;
                    }


                    if (Bukkit.getServer().getPlayer(args[1]) != null) {
                        String dpName = args[1];
                        Player displayPlayer = Bukkit.getServer().getPlayer(dpName);

                        final StringBuilder bldr = new StringBuilder();
                        for (int i = 2; i < args.length; i++) {
                            if (i != 2) {
                                bldr.append(" ");
                            }
                            bldr.append(args[i]);
                        }

                        Bukkit.getServer().broadcastMessage("<" + displayPlayer.getDisplayName() + ">" + " " + bldr.toString());
                        return true;
                    } else {
                        sender.sendMessage(ChatColor.RED + "This player is not online.");
                        return false;
                    }

                    //End of command: chatas

                } else {
                    sender.sendMessage(ChatColor.RED + "Invalid subcommand. Try /cp help for a list of them.");
                }
                return false;
            } else {
                //Will never be executed
                return false;
            }
        }
        return false;
    }
}



