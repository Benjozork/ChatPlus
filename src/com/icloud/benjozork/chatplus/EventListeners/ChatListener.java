package com.icloud.benjozork.chatplus.EventListeners;

import com.icloud.benjozork.chatplus.ChatPlus;
import com.icloud.benjozork.chatplus.ChatPlusAPI;
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
            e.setMessage(e.getMessage().toLowerCase());
        }

    }
}
