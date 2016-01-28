package me.benjozork.chatplus.listeners;

import me.benjozork.chatplus.ChatPlus;
import me.benjozork.chatplus.ChatPlusAPI;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by Benjozork on 2014-07-04.
 */
public class ChatListener implements Listener {
    private ChatPlusAPI cpapi = new ChatPlusAPI();
    private ChatPlus main;

    public ChatListener(ChatPlus main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {

        //If pausing is enabled
        if (main.getInstance().paused) {
            //Permissions/Op check
            if (!e.getPlayer().hasPermission("chatplus.exempt.pause")) {
                //Event cancelled
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED + "You cannot talk right now.");
            }
        }

        //If lowercasing is enabled
        if (main.getInstance().nocaps) {
            //Permissions/Op check
            if (!e.getPlayer().hasPermission("chatplus.exempt.nocaps")) {
                //Message toLC
                e.setMessage(e.getMessage().toLowerCase());
            }
        }

        //Color staff/VIP chat
        if (e.getPlayer().hasPermission("chatplus.flag.staff")) {
            e.setMessage(ChatColor.RED + e.getMessage());
        } else if (e.getPlayer().hasPermission("chatplus.flag.vip")) {
            e.setMessage(ChatColor.BLUE + e.getMessage());
        }

    }
}
