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

import ca.hotmail.benjozork.chatplus.Main.ChatPlus;
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

    public void displayHelpMessage(Player sender) {
        sender.sendMessage(ChatColor.BLUE + "ChatPlus " + ChatPlus.getInstance().getDescription().getVersion() + " by" + ChatColor.LIGHT_PURPLE + " benjozork");
        sender.sendMessage(ChatColor.GOLD + "/cp pause - Turns on/off the chat");
        sender.sendMessage(ChatColor.GOLD + "/cp help - Displays this message");
        sender.sendMessage(ChatColor.GOLD + "/cp nocaps - Turns on/off message LowerCasing");
        sender.sendMessage(ChatColor.GOLD + "/cp chatas <player> <message> - Chat as another player");
        sender.sendMessage(ChatColor.GOLD + "/cp clear - Clears the chat");
        sender.sendMessage(ChatColor.GOLD + "/cp join/leave - Broadcasts a fake join or leave message ! *trololol*");
        sender.sendMessage(ChatColor.AQUA + "NYI commands");
        sender.sendMessage(ChatColor.GOLD + "/cp nocmd - Block command usage");
    }
}