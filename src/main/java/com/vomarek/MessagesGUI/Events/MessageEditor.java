package com.vomarek.MessagesGUI.Events;

import com.vomarek.MessagesGUI.Groups.Group;
import com.vomarek.MessagesGUI.MessagesGUI;
import com.vomarek.MessagesGUI.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MessageEditor implements Listener {
    private MessagesGUI plugin;

    public MessageEditor(MessagesGUI plugin) {
        this.plugin = plugin;
    }


    /*
     * This is where join messages are changed to custom ones
     */
    @EventHandler
    public void JoinMessage(PlayerJoinEvent event) {

        event.setJoinMessage(null);

        Group group = Util.getGroup(event.getPlayer());

        if (group == null) return;

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(Util.replace(event.getPlayer(), group.getJoinMessage()));
        }

        Bukkit.getConsoleSender().sendMessage(Util.replace(event.getPlayer(), group.getJoinMessage()));
    }


    /*
     * This is where leave messages are changed to custom ones
     */
    @EventHandler
    public void LeaveMessage(PlayerQuitEvent event) {


        event.setQuitMessage(null);


        Group group = Util.getGroup(event.getPlayer());

        if (group == null) return;

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(Util.replace(event.getPlayer(), group.getLeaveMessage()));
        }

        Bukkit.getConsoleSender().sendMessage(Util.replace(event.getPlayer(), group.getLeaveMessage()));
    }


    /*
     * This is where death messages are changed to custom ones
     */
    @EventHandler
    public void DeathMessage(PlayerDeathEvent event) {

        event.setDeathMessage(null);

        Group group = Util.getGroup(event.getEntity());

        if (group == null) return;

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(Util.replace(event.getEntity(), group.getDeathMessage()));
        }

        Bukkit.getConsoleSender().sendMessage(Util.replace(event.getEntity(), group.getDeathMessage()));
    }
}
