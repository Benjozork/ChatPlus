package ca.hotmail.benjozork.chatplus.Main;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author Benjozork
 */
public class ChatPlusAPI {

    public boolean isChatPaused() {
        return ChatPlus.getInstance().paused;
    }

    public boolean isLowerCasingEnabled() {
        return ChatPlus.getInstance().nocaps;
    }

    public void setLowerCasingEnabled(boolean value) {
        ChatPlus.getInstance().nocaps = value;
    }

    public void setPaused(boolean value) {
        ChatPlus.getInstance().paused = value;
    }

    public String getJoinMessage() {
        return ChatPlus.getInstance().getConfig().getString("messages.join");
    }

    public void setJoinMessage(String msg) {
        ChatPlus.getInstance().getConfig().set("messages.join", msg);
    }

    public String getLeaveMessage() {
        return ChatPlus.getInstance().getConfig().getString("messages.leave");
    }

    public void setLeaveMessage(String msg) {
        ChatPlus.getInstance().getConfig().set("messages.leave", msg);
    }

    public void displayHelpMessage(Player sender) {
        sender.sendMessage(ChatColor.AQUA + "<" + ChatColor.BLUE + "======" + ChatColor.UNDERLINE + ChatColor.YELLOW + " ChatPlus " + ChatPlus.getInstance().getDescription().getVersion() + " by" + ChatColor.LIGHT_PURPLE + " benjozork " + ChatColor.BLUE + "======" + ChatColor.AQUA + ">");
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
        sender.sendMessage(" - " + ChatColor.GOLD + "pausemsg: " + ChatColor.GREEN + "Chat pause message");
        sender.sendMessage(" - " + ChatColor.GOLD + "clearmsg: " + ChatColor.GREEN + "Chat clear message");
        sender.sendMessage(" - " + ChatColor.GOLD + "nocapsmsg: " + ChatColor.GREEN + "Chat lowercasing message");
    }

    public String processMessageTags(String sourcemsg, Player player, String playername, String method) {
        if (method.startsWith("TWO")) {
            //TODO PvP messages
        } else if (method.startsWith("ONE") || method.startsWith("FAKE")) {
            //Replace %player% with the player name
            if (method.startsWith("FAKE_PLAYER_PROCESS")) {
                sourcemsg = sourcemsg.replace("%player%,", "playername");
            }else if (method.startsWith("ONE_PLAYER_PROCESS")) {
                sourcemsg = sourcemsg.replace("%player%", player.getName());
            }
            //Replace %ip% with player's ip
            if (method.equals("ONE_PLAYER_PROCESS_LEAVE") || method.equals("ONE_PLAYER_PROCESS_JOIN")) {
                sourcemsg = sourcemsg.replace("%ip%", player.getAddress().toString());
            } else if (method.equals("FAKE_PLAYER_PROCESS_LEAVE") || method.equals("FAKE_PLAYER_PROCESS_JOIN")) {
                sourcemsg = sourcemsg.replace("%ip%", "");
            }
            //Replace player's displayname with his displayname
            if (method.equals("ONE_PLAYER_PROCESS_LEAVE") || method.equals("ONE_PLAYER_PROCESS_JOIN")) {
                sourcemsg = sourcemsg.replace("%displayname%", player.getDisplayName());
            } else if (method.equals("FAKE_PLAYER_PROCESS_LEAVE") || method.equals("FAKE_PLAYER_PROCESS_JOIN")) {
                sourcemsg = sourcemsg.replace("%displayname%", playername);
            }
            //Replace %nbplayers% by the number of players on the server
            if (method.equals("ONE_PLAYER_PROCESS_LEAVE")) {
                sourcemsg = sourcemsg.replace("%nbplayers%", (ChatPlus.getInstance().getServer().getOnlinePlayers().size() - 1) + "");
            } else if (method.equals("ONE_PLAYER_PROCESS_JOIN")) {
                sourcemsg = sourcemsg.replace("%nbplayers%", ChatPlus.getInstance().getServer().getOnlinePlayers().size() + "");
            }
            //Replace & codes with ChatColors
            sourcemsg = ChatColor.translateAlternateColorCodes('&', sourcemsg);
            return sourcemsg;
        }else {
            ChatPlus.getInstance().getLogger().severe("ChatPlusAPI: FATAL ERROR: INVALID PROCESSING METHOD !!! ");
            return "";
        }
        return "";
    }
}
