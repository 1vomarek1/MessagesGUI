package com.vomarek.MessagesGUI;

import com.vomarek.MessagesGUI.Commands.Commands;
import com.vomarek.MessagesGUI.Events.GUIClickListener;
import com.vomarek.MessagesGUI.Events.MessageEditor;
import com.vomarek.MessagesGUI.Files.Config;
import com.vomarek.MessagesGUI.Groups.GroupManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class MessagesGUI extends JavaPlugin {
    private static MessagesGUI plugin;
    private Config config;
    private GroupManager groupManager;
    private Integer version;

    public void onEnable() {
        plugin = this;

        loadConfigurations();

        groupManager = new GroupManager(this);

        if (!getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[&2MessagesGUI&3] &cYou don't have placeholderAPI installed! You can't use placeholders in your messages! Download it here: https://www.spigotmc.org/resources/placeholderapi.6245/"));
        }

        getVersion();


        getServer().getPluginManager().registerEvents(new MessageEditor(this), this);
        getServer().getPluginManager().registerEvents(new GUIClickListener(this), this);


        getCommand("messagesgui").setExecutor(new Commands(this));


        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[&2MessagesGUI&3] &aMessagesGUI successfully enabled!"));
    }

    private void getVersion() {
        try {
            version = Integer.parseInt(getServer().getBukkitVersion().split("\\.")[1]);
        } catch (Exception e) {
            version = 8;
        }
    }

    private void loadConfigurations() {
        config = new Config(this, "config");
        if (config.isEmpty()) {
            config.saveDefaultConfig();
            config.reload();
        }
    }

    public void onDisable() {}

    public static MessagesGUI getPlugin() {
        return plugin;
    }

    public Config getConfigFile() {
        return config;
    }

    public GroupManager getGroupManager() {
        return groupManager;
    }

    public Integer getSpigotVersion() {
        return version;
    }
}
