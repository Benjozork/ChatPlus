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
package ca.hotmail.benjozork.chatplus.Main;

import ca.hotmail.benjozork.chatplus.Commands.CommandCp;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatPlus extends JavaPlugin implements Listener {

    public static void main(String[] args) {
//Do Nothing
    }

    private static ChatPlus instance;

    private Logger log = Logger.getLogger("Minecraft");
    Boolean paused = false;
    Boolean nocaps = false;

    public static ChatPlus getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        log.info("[ChatPlus] Enabled successfully.");
        getCommand("cp").setExecutor(new CommandCp());
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
            if (e.getPlayer().hasPermission("chatplus.bypassPause")) {
            } else {
                //Event cancelled
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.DARK_RED + "You cannot talk right now!");
            }
        }
        if (nocaps == true) {
            e.setMessage(e.getMessage().toLowerCase());
        }
    }
}

