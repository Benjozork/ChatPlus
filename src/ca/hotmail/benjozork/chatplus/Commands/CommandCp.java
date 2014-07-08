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
 * @author Benjozork
 */
public class CommandCp implements CommandExecutor {

    private ChatPlusAPI cpapi = new ChatPlusAPI();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("chatplus.main") || sender.hasPermission("chatplus.*")) {
        if (label.equalsIgnoreCase("cp")) {
            if (args.length == 0) {
                //Displays the help page
                cpapi.displayHelpMessage((Player) sender);
                return true;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("pause")) {
                    if (cpapi.isChatPaused() == true) {
                        cpapi.setPaused(false);
                        Bukkit.getServer().broadcastMessage("[" + ChatColor.GOLD + "ChatPlus" + ChatColor.WHITE + "]" + ChatColor.AQUA + " Chat is no longer paused!");
                        return true;
                    } else {
                        cpapi.setPaused(true);
                        Bukkit.getServer().broadcastMessage("[" + ChatColor.GOLD + "ChatPlus" + ChatColor.WHITE + "]" + ChatColor.AQUA + " Chat is now paused!");
                        return true;
                    }
                } else if (args[0].equalsIgnoreCase("nocaps")) {
                    if (cpapi.isLowerCasingEnabled() == true) {
                        cpapi.setLowerCasingEnabled(false);
                        Bukkit.getServer().broadcastMessage("[" + ChatColor.GOLD + "ChatPlus" + ChatColor.WHITE + "]" + ChatColor.AQUA + " LowerCasing off!");
                        return true;
                    } else {
                        cpapi.setLowerCasingEnabled(true);
                        Bukkit.getServer().broadcastMessage("[" + ChatColor.GOLD + "ChatPlus" + ChatColor.WHITE + "]" + ChatColor.AQUA + " LowerCasing on!");
                        return true;
                    }
                } else if (args[0].equalsIgnoreCase("help")) {
                    //Displays help page
                    cpapi.displayHelpMessage((Player) sender);
                    return true;
                } else if (args[0].equalsIgnoreCase("clear")) {
                    for (int i = 0; i < 100; i++) {
                        Bukkit.broadcastMessage("");
                    }
                    return true;
                } else if (args[0].equalsIgnoreCase("chatas")) {
                    sender.sendMessage(ChatColor.DARK_RED + "Too few arguments for command " + ChatColor.AQUA + "chatas");
                    return false;
                } else if (args[0].equalsIgnoreCase("join") || args[0].equalsIgnoreCase("leave")) {
                    sender.sendMessage(ChatColor.DARK_RED + "Too few arguments for command " + ChatColor.AQUA + "join/leave");
                    return false;
                } else if (args[0].equalsIgnoreCase("set")) {
                    cpapi.displaySetHelpMessage((Player) sender);
                } else {
                    sender.sendMessage(ChatColor.DARK_RED + "Unknown command! Type in " + ChatColor.AQUA + "/cp help");
                    return false;
                }
            }
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("join")) {
                Bukkit.broadcastMessage(cpapi.processMessageTagsWithFakePlayer(ChatPlus.getInstance().getConfig().getString("messages.join"), args[1]));
                return true;
            } else if (args[0].equalsIgnoreCase("leave")) {
                Bukkit.broadcastMessage(cpapi.processMessageTagsWithFakePlayer(ChatPlus.getInstance().getConfig().getString("messages.leave"), args[1]));
                return true;
            } else if (args[0].equalsIgnoreCase("set")) {
                sender.sendMessage(ChatColor.DARK_RED + "Please assign a value to that parameter");
                return false;
            }
        }
        if (args.length >= 3) {
            if (args[0].equalsIgnoreCase("chatas")) {
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
                    sender.sendMessage(ChatColor.DARK_RED + "This player is not online...");
                    return false;
                }
            } else if (args[0].equalsIgnoreCase("set")) {
                if (args[1].equalsIgnoreCase("joinmsg")) {
                    StringBuilder strb = new StringBuilder();

                    for (int i = 2; i < args.length; i++) {
                        if (i != 2) {
                            strb.append(" ");
                        }
                        strb.append(args[i]);
                    }
                    cpapi.setJoinMessage(strb.toString());
                    sender.sendMessage("[" + ChatColor.GOLD + "ChatPlus" + ChatColor.WHITE + "]" + ChatColor.AQUA + " Join message set!");
                    return true;
                }
                if (args[1].equalsIgnoreCase("leavemsg")) {
                    StringBuilder strb = new StringBuilder();

                    for (int i = 2; i < args.length; i++) {
                        if (i != 2) {
                            strb.append(" ");
                        }
                        strb.append(args[i]);
                    }
                    cpapi.setLeaveMessage(strb.toString());
                    sender.sendMessage("[" + ChatColor.GOLD + "ChatPlus" + ChatColor.WHITE + "]" + ChatColor.AQUA + " Leave message set!");
                    return true;
                }
            }
        }
        return false;
    }else {
            return false;
        }
    }
}

