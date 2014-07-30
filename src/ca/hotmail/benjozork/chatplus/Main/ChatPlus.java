package ca.hotmail.benjozork.chatplus.Main;

import ca.hotmail.benjozork.chatplus.Commands.CommandCp;

import java.io.IOException;
import java.util.logging.Logger;
import ca.hotmail.benjozork.chatplus.MetricsLite;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
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
        //Initialize metrics
        try {
            MetricsLite metrics = new MetricsLite(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
        }

        //Instance for the API
        instance = this;

        Bukkit.getServer().getPluginManager().registerEvents(new pluginListener(this), this);

        log.info("[ChatPlus] Enabled successfully.");
        getCommand("cp").setExecutor(new CommandCp());
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        log.info("[ChatPlus] Disabled successfully.");
        saveConfig();
    }
}

