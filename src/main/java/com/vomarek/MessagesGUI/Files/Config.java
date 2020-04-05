package com.vomarek.MessagesGUI.Files;

import com.vomarek.MessagesGUI.MessagesGUI;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Config {
    private String name;

    private Plugin plugin;

    private YamlConfiguration configuration;

    private File file;

    public Config(MessagesGUI plugin, String name) {
        this.plugin = (Plugin)plugin;
        this.name = name;
    }

    public Config save() {
        if (this.configuration == null || this.file == null)
            return this;
        try {
            if (this.configuration.getConfigurationSection("").getKeys(true).size() != 0)
                this.configuration.save(this.file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return this;
    }

    public YamlConfiguration get() {
        if (this.configuration == null)
            reload();
        return this.configuration;
    }

    public Config saveDefaultConfig() {
        this.file = new File(this.plugin.getDataFolder(), this.name);
        this.plugin.saveResource(this.name + ".yml", false);
        return this;
    }

    public Config reload() {
        if (this.file == null)
            this.file = new File(this.plugin.getDataFolder(), this.name + ".yml");
        this.configuration = YamlConfiguration.loadConfiguration(this.file);
        try {
            Reader defConfigStream = new InputStreamReader(this.plugin.getResource(this.name + ".yml"), "UTF8");
            if (defConfigStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                this.configuration.setDefaults((Configuration)defConfig);
            }
        } catch (UnsupportedEncodingException|NullPointerException unsupportedEncodingException) {}
        return this;
    }

    public Config copyDefaults(boolean force) {
        get().options().copyDefaults(force);
        return this;
    }

    public Config set(String key, Object value) {
        get().set(key, value);
        return this;
    }

    public Object get(String key) {
        return get().get(key);
    }

    public boolean isEmpty() {
        if (this.configuration == null) reload();
        return this.configuration.getConfigurationSection("").getKeys(true).isEmpty();
    }
}
