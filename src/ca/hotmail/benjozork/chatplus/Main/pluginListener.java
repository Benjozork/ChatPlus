package ca.hotmail.benjozork.chatplus.Main;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Benjozork on 2014-07-04.
 */
public class pluginListener implements Listener {
    private ChatPlusAPI cpapi = new ChatPlusAPI();
    ChatPlus main;

    public pluginListener(ChatPlus main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if (main.getInstance().paused) {
            //Permissions/Op check
            if (e.getPlayer().hasPermission("chatplus.bypassPause")) {
            } else {
                //Event cancelled
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.DARK_RED + "You cannot talk right now!");
            }
        }
        if (main.getInstance().nocaps) {
            e.setMessage(e.getMessage().toLowerCase());
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        String msgj = main.getConfig().getString("messages.join");
        String msg = cpapi.processMessageTags(msgj, e.getPlayer());
        e.setJoinMessage(msg);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        String msgl = main.getConfig().getString("messages.leave");
        String msg = cpapi.processMessageTags(msgl, e.getPlayer());
        e.setQuitMessage(msg);
    }
}
