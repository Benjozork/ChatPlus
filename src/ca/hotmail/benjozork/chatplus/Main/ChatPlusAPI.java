package ca.hotmail.benjozork.chatplus.Main;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 *
 * @author Benjozork
 */
public class ChatPlusAPI {

    public boolean isChatPaused() {
        return ChatPlus.getInstance().paused;
    }

    public boolean isLowerCasingEnabled() {
        return ChatPlus.getInstance().nocaps;
    }

    public void setPaused(boolean value) {
        ChatPlus.getInstance().paused = value;
    }

    public void setLowerCasingEnabled(boolean value) {
        ChatPlus.getInstance().nocaps = value;
    }

    public void setJoinMessage(String msg) {
        ChatPlus.getInstance().getConfig().set("messages.join", msg);
    }

    public void setLeaveMessage(String msg) {
        ChatPlus.getInstance().getConfig().set("messages.leave", msg);
    }

    public String getJoinMessage() {
        return ChatPlus.getInstance().getConfig().getString("messages.join");
    }

    public String getLeaveMessage() {
        return ChatPlus.getInstance().getConfig().getString("messages.leave");
    }

    public void displayHelpMessage(Player sender) {
        sender.sendMessage(ChatColor.AQUA + "<" + ChatColor.BLUE + "======" + ChatColor.UNDERLINE + ChatColor.YELLOW +  " ChatPlus " + ChatPlus.getInstance().getDescription().getVersion() + " by" + ChatColor.LIGHT_PURPLE + " benjozork " + ChatColor.BLUE + "======" + ChatColor.AQUA + ">");
        sender.sendMessage(ChatColor.GOLD + "/cp pause -" + ChatColor.GREEN + " Turns on/off the chat");
        sender.sendMessage(ChatColor.GOLD + "/cp set <parameter> <value> -" + ChatColor.GREEN + " Assigns a value to a parameter");
        sender.sendMessage(ChatColor.GOLD + "/cp help -" + ChatColor.GREEN + " Displays this message");
        sender.sendMessage(ChatColor.GOLD + "/cp nocaps -" + ChatColor.GREEN + " Turns on/off message LowerCasing");
        sender.sendMessage(ChatColor.GOLD + "/cp chatas <player> <message> -" + ChatColor.GREEN + " Chat as another player");
        sender.sendMessage(ChatColor.GOLD + "/cp clear -" + ChatColor.GREEN + " Clears the chat");
        sender.sendMessage(ChatColor.GOLD + "/cp join/leave -" + ChatColor.GREEN + " Broadcasts a fake join or leave message ! *trololol*");
    }

    public void displaySetHelpMessage(Player sender) {
        sender.sendMessage(ChatColor.AQUA + "Please use one of these parameters to set:");
        sender.sendMessage(" - " + ChatColor.GOLD + "joinmsg: " + ChatColor.GREEN + "Player join message");
        sender.sendMessage(" - " + ChatColor.GOLD + "leavemsg: " + ChatColor.GREEN + "Player leave message");
    }

    public String processMessageTags(String sourcemsg, Player player) {
        sourcemsg = sourcemsg.replace("%player%", player.getName());
        sourcemsg = sourcemsg.replace("%ip%", player.getAddress().toString());
        sourcemsg = sourcemsg.replace("%displayname%", player.getDisplayName());
        sourcemsg = sourcemsg.replace("%nbplayers%", (ChatPlus.getInstance().getServer().getOnlinePlayers().size() - 1) + "");
        sourcemsg = ChatColor.translateAlternateColorCodes('&', sourcemsg);
        return sourcemsg;
    }

    public String processMessageTagsWithFakePlayer(String sourcemsg, String player) {
        sourcemsg = sourcemsg.replace("%player%", player);
        sourcemsg = sourcemsg.replace("%ip%", ""); 
        sourcemsg = sourcemsg.replace("%displayname%", player);
        sourcemsg = sourcemsg.replace("%nbplayers%", ((ChatPlus.getInstance().getServer().getOnlinePlayers().size() +  1) + ""));
        sourcemsg = ChatColor.translateAlternateColorCodes('&', sourcemsg);
        return sourcemsg;
    }
}
