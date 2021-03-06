package com.vomarek.MessagesGUI.Commands;

import com.vomarek.MessagesGUI.GUI.GroupMenu;
import com.vomarek.MessagesGUI.GUI.MainMenu;
import com.vomarek.MessagesGUI.MessagesGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Commands implements CommandExecutor {
    private MessagesGUI plugin;

    public Commands(MessagesGUI plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {

            if (!sender.hasPermission("joinmessagesgui.admin")) return true;

            final Player player = (Player) sender;
            new BukkitRunnable() {

                @Override
                public void run() {
                    player.openInventory((new MainMenu(player, plugin)).getInventory());
                }
            }.runTaskLater(plugin, 1L);
        }

        return true;
    }
}
