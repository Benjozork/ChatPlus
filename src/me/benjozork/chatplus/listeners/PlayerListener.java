package me.benjozork.chatplus.listeners;

import me.benjozork.chatplus.ChatPlus;
import me.benjozork.chatplus.ChatPlusAPI;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Benjozork on 2016-01-18.
 */
public class PlayerListener implements Listener {
    private ChatPlusAPI cpapi = new ChatPlusAPI();
    private ChatPlus main;

    public PlayerListener(ChatPlus main) {
        this.main = main;
    }

    //Join event
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        String msgj = main.getConfig().getString("messages.join");
        String msg = cpapi.processMessageTags(msgj, e.getPlayer(), "", "ONE_PLAYER_PROCESS_JOIN");
        e.setJoinMessage(msg);
    }

    //Leave event
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        String msgl = main.getConfig().getString("messages.leave");
        String msg = cpapi.processMessageTags(msgl, e.getPlayer(), "", "ONE_PLAYER_PROCESS_LEAVE");
        e.setQuitMessage(msg);
    }
}
