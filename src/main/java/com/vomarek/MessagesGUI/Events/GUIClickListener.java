package com.vomarek.MessagesGUI.Events;

import com.vomarek.MessagesGUI.GUI.GroupMenu;
import com.vomarek.MessagesGUI.MessageAwaiting.AwaiterRunnable;
import com.vomarek.MessagesGUI.MessageAwaiting.MessageAwaiter;
import com.vomarek.MessagesGUI.MessagesGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GUIClickListener implements Listener {
    private MessagesGUI plugin;

    public GUIClickListener(MessagesGUI plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void GroupMenu(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;

        if (!(event.getInventory().getHolder() instanceof GroupMenu)) return;

        event.setCancelled(true);

        if (event.getClickedInventory() == null || event.getClickedInventory().getType() == null || event.getClickedInventory().getType().equals(InventoryType.PLAYER)) return;

        final Player player = (Player) event.getWhoClicked();
        final GroupMenu groupMenu = (GroupMenu) event.getInventory().getHolder();

        switch (event.getSlot()) {
            case 3:

                if (groupMenu.getPart().equals("Join")) return;

                groupMenu.setPart("Join");

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        player.openInventory(groupMenu.getInventory());
                    }

                }.runTaskLater(plugin, 1L);
                break;
            case 4:

                if (groupMenu.getPart().equals("Death"))  return;

                groupMenu.setPart("Death");

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        player.openInventory(groupMenu.getInventory());
                    }

                }.runTaskLater(plugin, 1L);

                break;
            case 5:
                if (groupMenu.getPart().equals("Leave")) return;

                groupMenu.setPart("Leave");

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        player.openInventory(groupMenu.getInventory());
                    }
                }.runTaskLater(plugin, 1L);

                break;
            case 13:

                if (groupMenu.getPart().equals("Info")) return;

                groupMenu.setPart("Info");

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        player.openInventory(groupMenu.getInventory());
                    }

                }.runTaskLater(plugin, 1L);

                break;
            case 29:

                if (groupMenu.getPart().equals("Join")) {

                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            player.closeInventory();
                        }
                    }.runTaskLater(plugin, 1L);

                    plugin.sendTitle(player, "&3You are editing group", "&fType &ccancel &fto cancel", 0, 2000000000, 0);

                    new MessageAwaiter(new AwaiterRunnable() {
                        public void run() {
                            String message = getMessage();

                            groupMenu.getGroup().setJoinMessage(message);
                            groupMenu.update();

                            plugin.sendTitle(player, "", "", 0, 0, 0);
                            player.openInventory(groupMenu.getInventory());
                        }
                    }, player, plugin);

                    break;
                }
                if (groupMenu.getPart().equals("Leave")) {

                    new BukkitRunnable() {
                        public void run() {
                            player.closeInventory();
                        }
                    }.runTaskLater(plugin, 1L);

                    plugin.sendTitle(player, "&3You are editing group", "&fType &ccancel &fto cancel", 0, 2000000000, 0);

                    new MessageAwaiter(new AwaiterRunnable() {
                        public void run() {
                            String message = getMessage();

                            groupMenu.getGroup().setLeaveMessage(message);
                            groupMenu.update();

                            plugin.sendTitle(player, "", "", 0, 0, 0);
                            player.openInventory(groupMenu.getInventory());
                        }
                    }, player, plugin);

                    break;
                }
                if (groupMenu.getPart().equals("Death")) {

                    new BukkitRunnable() {
                        public void run() {
                            player.closeInventory();
                        }
                    }.runTaskLater(plugin, 1L);

                    plugin.sendTitle(player, "&3You are editing group", "&fType &ccancel &fto cancel", 0, 2000000000, 0);

                    new MessageAwaiter(new AwaiterRunnable() {
                        public void run() {
                            String message = getMessage();

                            groupMenu.getGroup().setDeathMessage(message);
                            groupMenu.update();

                            plugin.sendTitle(player, "", "", 0, 0, 0);
                            player.openInventory(groupMenu.getInventory());
                        }
                    }, player, plugin);
                }

                break;
            case 49:

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        player.closeInventory();
                        // TODO: Open main menu
                    }
                }.runTaskLater(plugin, 1L);

                break;
        }
    }
}
