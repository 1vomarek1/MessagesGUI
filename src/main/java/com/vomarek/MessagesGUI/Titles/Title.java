package com.vomarek.MessagesGUI.Titles;

import com.vomarek.MessagesGUI.MessagesGUI;
import com.vomarek.MessagesGUI.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Title {
    private String title;
    private String subtitle;
    private Integer fadein;
    private Integer stay;
    private Integer fadeout;

    public Title (String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadein = 20;
        this.fadeout = 20;
        this.stay = 100;
    }
    public Title (String title, String subtitle, Integer fadein, Integer stay, Integer fadeout) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadein = fadein;
        this.fadeout = fadeout;
        this.stay = stay;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void send(Player player) {
        if (MessagesGUI.getPlugin().getSpigotVersion() >= 9) {
            player.sendTitle(Util.replace(player, title), Util.replace(player, subtitle), fadein, stay, fadeout);
        }
    }

}
