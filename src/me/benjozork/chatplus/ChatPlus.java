package me.benjozork.chatplus;

import me.benjozork.chatplus.commands.CommandCh;
import me.benjozork.chatplus.commands.CommandCp;
import me.benjozork.chatplus.listeners.ChatListener;
import me.benjozork.chatplus.listeners.PlayerListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatPlus extends JavaPlugin implements Listener {

    public static void main(String[] args) {
//Do Nothing
    }

    private static ChatPlus instance;

    private Logger log = Logger.getLogger("Minecraft");
    public Boolean paused = false;
    public Boolean nocaps = false;

    public static ChatPlus getInstance() {
        return instance;
    }

    public HashMap<Player, String> player_channels = new HashMap<>();

    @Override
    public void onEnable() {
        //Initialize metrics
        try {
            MetricsLite metrics = new MetricsLite(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
        }

        //Instance for the API
        instance = this;

        Bukkit.getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

        log.info("[ChatPlus] Enabled successfully.");

        getCommand("cp").setExecutor(new CommandCp());
        getCommand("ch").setExecutor(new CommandCh());

        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        log.info("[ChatPlus] Disabled successfully.");
        saveConfig();
    }
}

