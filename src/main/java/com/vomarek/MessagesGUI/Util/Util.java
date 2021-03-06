package com.vomarek.MessagesGUI.Util;

import com.vomarek.MessagesGUI.Groups.Group;
import com.vomarek.MessagesGUI.MessagesGUI;

import java.util.HashMap;
import java.util.Map;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Util {
    public static HashMap<String, Group> cache = new HashMap<>();

    public static Group getGroup(Player player) {
        if (cache.containsKey(player.getName())) return cache.get(player.getName());
        for (Map.Entry<String, Group> group : MessagesGUI.getPlugin().getGroupManager().getGroups().entrySet()) {
            if (player.hasPermission("joinmessagesgui.groups." + ((Group)group.getValue()).getName())) {
                cache.put(player.getName(), group.getValue());
                return group.getValue();
            }
        }
        return null;
    }

    public static String replaceColors(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static String replacePlaceholders(Player player, String input) {
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            return PlaceholderAPI.setPlaceholders(player, input);
        } else {
            return input;
        }
    }

    public static String replace(Player player, String input) {
        return replaceColors(replacePlaceholders(player, input));
    }
}
