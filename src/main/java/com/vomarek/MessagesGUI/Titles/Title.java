package com.vomarek.MessagesGUI.Titles;

import com.vomarek.MessagesGUI.Util.Reflection;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

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
        try {
            Object chatTitle = Reflection.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\": \"" + title + "\"}");
            Object chatSubtitle = Reflection.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\": \"" + subtitle + "\"}");

            Constructor<?> titleConstructor = Reflection.getNMSClass("PacketPlayOutTitle").getConstructor(Reflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], Reflection.getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
            Object titlePacket = titleConstructor.newInstance(Reflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null), chatTitle, fadein, stay, fadeout);
            Object subtitlePacket = titleConstructor.newInstance(Reflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null), chatSubtitle, fadein, stay, fadeout);

            Reflection.sendPacket(player, titlePacket);
            Reflection.sendPacket(player, subtitlePacket);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
