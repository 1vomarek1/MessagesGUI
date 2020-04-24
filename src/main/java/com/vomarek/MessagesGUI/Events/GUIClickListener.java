package com.vomarek.MessagesGUI.Events;

import com.vomarek.MessagesGUI.GUI.GroupMenu;
import com.vomarek.MessagesGUI.GUI.MainMenu;
import com.vomarek.MessagesGUI.Groups.Group;
import com.vomarek.MessagesGUI.MessageAwaiting.AwaiterRunnable;
import com.vomarek.MessagesGUI.MessageAwaiting.MessageAwaiter;
import com.vomarek.MessagesGUI.MessagesGUI;
import com.vomarek.MessagesGUI.Titles.Title;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.scheduler.BukkitRunnable;

public class GUIClickListener implements Listener {
    private MessagesGUI plugin;

    public GUIClickListener(MessagesGUI plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void MainMenu(InventoryClickEvent event)  {
        if (!(event.getWhoClicked() instanceof Player)) return;

        if(!(event.getInventory().getHolder() instanceof MainMenu)) return;

        event.setCancelled(true);

        final Player player = (Player) event.getWhoClicked();
        final MainMenu mainMenu = (MainMenu) event.getInventory().getHolder();

        if (event.getClickedInventory() == null || event.getClickedInventory().getType() == null || event.getClickedInventory().getType().equals(InventoryType.PLAYER)) return;

        switch (event.getSlot()) {
            case (45):

                mainMenu.setChangeOrder(!mainMenu.getChangeOrder());

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        player.openInventory(mainMenu.getInventory());
                    }
                }.runTaskLater(plugin, 1);

                break;
            case (48):

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        player.openInventory(mainMenu.previousPage().getInventory());
                    }
                }.runTaskLater(plugin, 1L);

                break;
            case (49):

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        player.closeInventory();
                    }
                }.runTaskLater(plugin, 1);

                break;
            case (50):

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        player.openInventory(mainMenu.nextPage().getInventory());
                    }
                }.runTaskLater(plugin, 1L);

                break;
            case (53):

                new BukkitRunnable() {
                    public void run() {
                        player.closeInventory();
                    }
                }.runTaskLater(plugin, 1L);

                new Title("&3You are creating group", "&fType &ccancel &fto cancel", 0, 2000000000, 0).send(player);

                new MessageAwaiter(new AwaiterRunnable() {
                    public void run() {
                        String message = getMessage();

                        plugin.getGroupManager().createGroup(message);
                        mainMenu.update();

                        new Title("clear", "", 0, 0, 0).send(player);
                        player.openInventory(mainMenu.getInventory());
                    }
                }, player, plugin);

                break;
        }

        if (mainMenu.getGroupAt(event.getSlot()) != null) {
            final Group group = mainMenu.getGroupAt(event.getSlot());

            if (group != null) {


                if (mainMenu.getChangeOrder()) {

                    if (event.getClick().equals(ClickType.LEFT)) {
                        group.setPriority(group.getPriority() + 1);

                        mainMenu.update();

                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                player.openInventory(mainMenu.getInventory());
                            }

                        }.runTaskLater(plugin, 1);

                    } else if (event.getClick().equals(ClickType.RIGHT)) {
                        group.setPriority(group.getPriority() - 1);

                        mainMenu.update();

                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                player.openInventory(mainMenu.getInventory());
                            }

                        }.runTaskLater(plugin, 1);

                    }

                } else {
                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            player.openInventory(new GroupMenu(group, player).getInventory());
                        }

                    }.runTaskLater(plugin, 1);
                }


            }

        }

    }

    @EventHandler
    public void GroupMenu(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;

        if (!(event.getInventory().getHolder() instanceof GroupMenu)) return;

        event.setCancelled(true);

        final Player player = (Player) event.getWhoClicked();
        final GroupMenu groupMenu = (GroupMenu) event.getInventory().getHolder();

        if (event.getClickedInventory() != null && event.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
            if (event.getCurrentItem() != null) {
                if (!event.getCurrentItem().getType().equals(Material.AIR)) {
                    groupMenu.getGroup().setItem(event.getCurrentItem().getType());
                    groupMenu.update();

                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            player.openInventory(groupMenu.getInventory());
                        }
                    }.runTaskLater(plugin, 1);
                }
            }
        }

        if (event.getClickedInventory() == null || event.getClickedInventory().getType() == null || event.getClickedInventory().getType().equals(InventoryType.PLAYER)) return;

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

                    new Title("&3You are editing group", "&fType &ccancel &fto cancel", 0, 2000000000, 0).send(player);

                    new MessageAwaiter(new AwaiterRunnable() {
                        public void run() {
                            String message = getMessage();

                            groupMenu.getGroup().setJoinMessage(message);
                            groupMenu.update();

                            new Title("clear", "", 0, 0, 0).send(player);
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

                    new Title("&3You are editing group", "&fType &ccancel &fto cancel", 0, 2000000000, 0).send(player);

                    new MessageAwaiter(new AwaiterRunnable() {
                        public void run() {
                            String message = getMessage();

                            groupMenu.getGroup().setLeaveMessage(message);
                            groupMenu.update();

                            new Title("clear", "", 0, 0, 0).send(player);
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

                    new Title("&3You are editing group", "&fType &ccancel &fto cancel", 0, 2000000000, 0).send(player);

                    new MessageAwaiter(new AwaiterRunnable() {
                        public void run() {
                            String message = getMessage();

                            groupMenu.getGroup().setDeathMessage(message);
                            groupMenu.update();

                            new Title("clear", "", 0, 0, 0).send(player);
                            player.openInventory(groupMenu.getInventory());
                        }
                    }, player, plugin);
                }

                break;
            case (31):
                if (groupMenu.getPart().equals("Join")) {
                    new BukkitRunnable() {
                        public void run() {
                            player.closeInventory();
                        }
                    }.runTaskLater(plugin, 1L);


                    if (event.getClick().equals(ClickType.MIDDLE)) {
                        groupMenu.getGroup().setIsTitleEnabled(!groupMenu.getGroup().isTitleEnabled());
                        groupMenu.update();

                        new BukkitRunnable() {

                            @Override
                            public void run () {
                                player.openInventory(groupMenu.getInventory());
                            }
                        }.runTaskLater(plugin, 1L);

                        return;
                    }

                    new Title("&3You are editing title", "&fType &ccancel &fto cancel", 0, 2000000000, 0).send(player);

                    if (event.getClick().isLeftClick()) {
                        new MessageAwaiter(new AwaiterRunnable() {
                            public void run() {
                                final String message = getMessage();

                                groupMenu.getGroup().setJoinTitle(new Title(message, groupMenu.getGroup().getJoinTitle().getSubtitle()));
                                groupMenu.update();

                                new Title("clear", "", 0, 0, 0).send(player);
                                player.openInventory(groupMenu.getInventory());
                            }
                        }, player, plugin);
                    } else if (event.getClick().isRightClick()) {
                        new MessageAwaiter(new AwaiterRunnable() {
                            public void run() {
                                final String message = getMessage();

                                groupMenu.getGroup().setJoinTitle(new Title(groupMenu.getGroup().getJoinTitle().getTitle(), message));
                                groupMenu.update();

                                new Title("clear", "", 0, 0, 0).send(player);
                                player.openInventory(groupMenu.getInventory());
                            }
                        }, player, plugin);
                    }

                }
                break;
            case(33):

                if (groupMenu.getPart().equals("Info")) {
                    plugin.getGroupManager().deleteGroup(groupMenu.getGroup());

                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            player.openInventory(new MainMenu(player, plugin).getInventory());
                        }
                    }.runTaskLater(plugin, 1);
                }

                break;
            case 49:

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        player.openInventory(new MainMenu(player, plugin).getInventory());
                    }
                }.runTaskLater(plugin, 1L);

                break;
        }
    }
}
