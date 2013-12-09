/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.hotmail.benjozork.chatplus;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatPlus extends JavaPlugin implements Listener {
    
public static void main(String[] args) {
//Do Nothing
}
    
    private Logger log = Logger.getLogger("Minecraft");
    Boolean paused = false;
    Boolean nocaps = false;
    String[] victims;
    
    public void setValue(Boolean theVal) {
    paused = theVal; // change the value
    }
    public Boolean isChatPaused() {
        if (paused == true) {
            return true;
        }
        else {
            return false;
        }
    }    
    
    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        log.info("[ChatPlus] Enabled successfully.");
        this.saveDefaultConfig();
    }
    
    @Override
    public void onDisable() {
        log.info("[ChatPlus] Disabled successfully.");
    }
    
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if (paused == true) {
            //Permissions/Op check
            if (e.getPlayer().isOp() || e.getPlayer().hasPermission("chatplus.bypassPause")) {
            }
            else {
                //Event cancelled
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(ChatColor.DARK_RED + "You cannot talk right now!");
                }
            } 
       if (nocaps == true) {
            String firstCharacter = e.getMessage().substring(0, 1).toUpperCase();
            e.setMessage(firstCharacter + e.getMessage().substring(1).toLowerCase());
            
        }
        String path = "chatcontrol." + e.getPlayer().getName();
        if (this.getConfig().getString(path) != null) {
            String vname = this.getConfig().getString(path);
            Player victim = Bukkit.getServer().getPlayer(vname);
            e.setCancelled(true);
            Bukkit.getServer().broadcastMessage("<" + victim.getDisplayName() + ">" + " " + e.getMessage());
        } 
     }
 
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("cp")) {
            if(args.length == 0) {
                //Displays the help page
                        sender.sendMessage(ChatColor.BLUE + "ChatPlus 1.0 by" + ChatColor.LIGHT_PURPLE + " benjozork");
                        sender.sendMessage(ChatColor.GOLD + "/cp pause - Turns on/off the chat");
                        sender.sendMessage(ChatColor.GOLD + "/cp help - Displays this message");
                        sender.sendMessage(ChatColor.GOLD + "/cp nocaps - Turns on/off message LowerCasing");
                        sender.sendMessage(ChatColor.AQUA + "NYI commands");
                        sender.sendMessage(ChatColor.GOLD + "/cp nocmd - Block command usage");
            }
            if (args.length > 1) {
                //Too many arguments
               sender.sendMessage("Too many arguments!");
               return false;
            }
            if (args.length == 1) {
                switch (args[0]) {
                    case "pause":
                        if (paused == true) {
                            paused = false;
                            Bukkit.getServer().broadcastMessage("[" + ChatColor.GOLD + "ChatPlus" + ChatColor.WHITE + "]" + ChatColor.AQUA + " Chat is no longer paused!");
                        }
                        else {
                            paused = true;
                            Bukkit.getServer().broadcastMessage("[" + ChatColor.GOLD + "ChatPlus" + ChatColor.WHITE + "]" + ChatColor.AQUA + " Chat is now paused!");
                        }
                        break;
                    case "nocaps":
                         if (nocaps == true) {
                            nocaps = false;
                            Bukkit.getServer().broadcastMessage("[" + ChatColor.GOLD + "ChatPlus" + ChatColor.WHITE + "]" + ChatColor.AQUA + " LowerCasing off!");
                        }
                        else {
                            nocaps = true;
                            Bukkit.getServer().broadcastMessage("[" + ChatColor.GOLD + "ChatPlus" + ChatColor.WHITE + "]" + ChatColor.AQUA + " LowerCasing on!");
                        }
                    break;
                    case "help":
                        //Displays help page
                        sender.sendMessage(ChatColor.BLUE + "ChatPlus 1.0 by" + ChatColor.LIGHT_PURPLE + " benjozork");
                        sender.sendMessage(ChatColor.GOLD + "/cp pause - Turns on/off the chat");
                        sender.sendMessage(ChatColor.GOLD + "/cp help - Displays this message");
                        sender.sendMessage(ChatColor.GOLD + "/cp nocaps - Turns on/off message LowerCasing");
                        sender.sendMessage(ChatColor.AQUA + "NYI commands");
                        sender.sendMessage(ChatColor.GOLD + "/cp nocmd - Block command usage");
                        break;
                    default:
                        sender.sendMessage(ChatColor.DARK_RED + "Unknown command! Type in " + ChatColor.AQUA + "/cp help");
                        break;
                }
               return true;
        }
        return true;
    }
    return false;
    }  
    
}