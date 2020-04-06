package com.vomarek.MessagesGUI.Groups;

import com.vomarek.MessagesGUI.MessagesGUI;
import org.bukkit.Material;

import java.util.HashMap;

public class Group {
    private MessagesGUI plugin;

    private String name;

    private HashMap<String, String> messages;

    private Integer priority;

    public Group(String name, MessagesGUI plugin) {
        this.name = name;
        this.plugin = plugin;


        messages = new HashMap<>();


        messages.put("JoinMessage", plugin.getConfigFile().get().getString("Groups." + name + ".JoinMessage", ""));
        messages.put("LeaveMessage", plugin.getConfigFile().get().getString("Groups." + name + ".LeaveMessage", ""));
        messages.put("DeathMessage", plugin.getConfigFile().get().getString("Groups." + name + ".DeathMessage", ""));


        priority = plugin.getConfigFile().get().getInt("Groups." + name + ".Priority", 0);

    }

    public String getName() {
        return name;
    }

    public String getJoinMessage() {
        return messages.getOrDefault("JoinMessage", "");
    }

    public String getLeaveMessage() {
        return messages.getOrDefault("LeaveMessage", "");
    }

    public String getDeathMessage() {
        return messages.getOrDefault("DeathMessage", "");
    }

    public Integer getPriority() {
        return priority;
    }

    public Group setJoinMessage(String message) {
        plugin.getConfigFile().get().set("Groups." + name + ".JoinMessage", message);
        plugin.getConfigFile().save();
        messages.put("JoinMessage", message);
        return this;
    }

    public Group setLeaveMessage(String message) {
        plugin.getConfigFile().get().set("Groups." + name + ".LeaveMessage", message);
        plugin.getConfigFile().save();
        messages.put("LeaveMessage", message);
        return this;
    }

    public Group setDeathMessage(String message) {
        plugin.getConfigFile().get().set("Groups." + name + ".DeathMessage", message);
        plugin.getConfigFile().save();
        messages.put("DeathMessage", message);
        return this;
    }

    public Group setPriority(Integer priority) {
        this.priority = priority;
        plugin.getConfigFile().get().set("Groups." + name + ".Priority", priority);
        plugin.getConfigFile().save();
        plugin.getGroupManager().sortGroups();
        return this;
    }

    public Material getMaterial() {
        return Material.GRASS;
    }
}
