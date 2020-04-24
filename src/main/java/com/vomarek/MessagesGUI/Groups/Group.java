package com.vomarek.MessagesGUI.Groups;

import com.vomarek.MessagesGUI.MessagesGUI;
import com.vomarek.MessagesGUI.Titles.Title;
import org.bukkit.Material;

import java.util.HashMap;

public class Group {
    private MessagesGUI plugin;
    private String name;
    private HashMap<String, String> messages;
    private Integer priority;
    private Material item;
    private Title JoinTitle;
    private Boolean isTitleEnabled;

    public Group(String name, MessagesGUI plugin) {
        this.name = name;
        this.plugin = plugin;


        messages = new HashMap<>();


        messages.put("JoinMessage", plugin.getConfigFile().get().getString("Groups." + name + ".JoinMessage", ""));
        messages.put("LeaveMessage", plugin.getConfigFile().get().getString("Groups." + name + ".LeaveMessage", ""));
        messages.put("DeathMessage", plugin.getConfigFile().get().getString("Groups." + name + ".DeathMessage", ""));

        isTitleEnabled = plugin.getConfigFile().get().getBoolean("Groups." + name + ".JoinTitle.enabled", true);

        JoinTitle = new Title(plugin.getConfigFile().get().getString("Groups." + name + ".JoinTitle.Title", ""), plugin.getConfigFile().get().getString("Groups." + name + ".JoinTitle.Subtitle", ""));


        priority = plugin.getConfigFile().get().getInt("Groups." + name + ".Priority", 0);

        try {
            item = Material.valueOf(plugin.getConfigFile().get().getString("Groups." + name + ".Item", "GRASS"));
        } catch (Exception e) {
            item = Material.GRASS;
        }

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
        return item;
    }

    public Group setItem(Material type) {
        item = type;
        plugin.getConfigFile().get().set("Groups." + name + ".Item", type.toString());
        plugin.getConfigFile().save();
        return this;
    }

    public Title getJoinTitle() {
        return JoinTitle;
    }

    public Group setJoinTitle(Title title) {
        JoinTitle = title;
        plugin.getConfigFile().get().set("Groups." + name + ".JoinTitle.Title", title.getTitle());
        plugin.getConfigFile().get().set("Groups." + name + ".JoinTitle.Subtitle", title.getSubtitle());
        plugin.getConfigFile().save();
        return this;
    }

    public Boolean isTitleEnabled() {
        return isTitleEnabled;
    }

    public Group setIsTitleEnabled(boolean enabled) {
        isTitleEnabled = enabled;
        plugin.getConfigFile().get().set("Groups." + name + ".JoinTitle.enabled", isTitleEnabled);
        plugin.getConfigFile().save();
        return this;
    }
}
