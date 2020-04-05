package com.vomarek.MessagesGUI;

import com.vomarek.MessagesGUI.Commands.Commands;
import com.vomarek.MessagesGUI.Events.GUIClickListener;
import com.vomarek.MessagesGUI.Events.MessageEditor;
import com.vomarek.MessagesGUI.Files.Config;
import com.vomarek.MessagesGUI.Groups.GroupManager;
import com.vomarek.MessagesGUI.Util.GlowEnchantment;
import com.vomarek.MessagesGUI.Util.Util;
import java.lang.reflect.Field;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
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
        registerGlow();


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

    public void sendTitle(Player player, String title, String subtitle, Integer fadeIn, Integer stay, Integer fadeout) {
        if (version >= 12) {
            player.sendTitle(Util.replace(player, title), Util.replace(player, subtitle), fadeIn, stay, fadeout);
        } else if (player.getLocation().getWorld().isGameRule("sendCommandFeedback")) {

            player.getLocation().getWorld().setGameRuleValue("sendCommandFeedback", "false");

            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "subtitle " + player.getName() + " times " + fadeIn + " " + stay + " " + fadeout + " -s");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "subtitle " + player.getName() + " title [{\"text\": \"" + Util.replace(player, subtitle) + "\"}] -s");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title " + player.getName() + " title [{\"text\": \"" + Util.replace(player, title) + "\"}] -s");

            player.getLocation().getWorld().setGameRuleValue("sendCommandFeedback", "true");
        } else {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "subtitle " + player.getName() + " times " + fadeIn + " " + stay + " " + fadeout + " -s");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "subtitle " + player.getName() + " title [{\"text\": \"" + Util.replace(player, subtitle) + "\"}] -s");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title " + player.getName() + " title [{\"text\": \"" + Util.replace(player, title) + "\"}] -s");
        }
    }

    private void registerGlow() {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, Boolean.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            GlowEnchantment glow = new GlowEnchantment(111);
            Enchantment.registerEnchantment(glow);
        } catch (IllegalArgumentException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
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
}
