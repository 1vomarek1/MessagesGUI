package com.vomarek.MessagesGUI.GUI;

import com.vomarek.MessagesGUI.Groups.Group;
import com.vomarek.MessagesGUI.MessagesGUI;
import com.vomarek.MessagesGUI.Util.GlowEnchantment;
import com.vomarek.MessagesGUI.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class MainMenu implements InventoryHolder {
    private MessagesGUI plugin;
    private Inventory inventory;
    private Player player;
    private Integer page;
    private ArrayList<Group> currentGroups;
    private boolean changeOrder;

    public MainMenu (Player player, MessagesGUI plugin) {
        this.plugin = plugin;
        this.player = player;
        page = 0;
        changeOrder = false;
        update();
    }

    public void update() {
        inventory = Bukkit.createInventory(this, 54, "MessagesGUI >> Groups");

        currentGroups = new ArrayList<>();

        try {
            currentGroups.addAll(new ArrayList<>(plugin.getGroupManager().getGroups().values()).subList((page) * 14, (page + 1) * 14));
        } catch (IndexOutOfBoundsException e) {
            currentGroups.addAll(new ArrayList<>(plugin.getGroupManager().getGroups().values()).subList((page) * 14, plugin.getGroupManager().getGroups().size()));
        }

        /*
         * Main menu items
         */

        inventory.setItem(49, createGUIItem(Util.replace(player, "&3Close menu"), new ArrayList<>(Collections.singletonList(Util.replace(player, "&aClick &fto close this menu"))), Material.BARRIER, 1, 0, false));
        inventory.setItem(45, createGUIItem(Util.replace(player,"&3Change priorities"), new ArrayList<>(Arrays.asList(Util.replace(player, changeOrder ? "&aClick &fto stop changing group priorities" : "&aClick &fto change group priorities"))), Material.ANVIL, 1, 0, changeOrder));
        inventory.setItem(53, createGUIItem(Util.replace(player,"&3Create group"), new ArrayList<>(Arrays.asList(Util.replace(player, "&aClick &fto create a group"))), Material.WORKBENCH, 1, 0, false));


        /*
         * Top lane glass
         */
        inventory.setItem(0, createGUIItem(Util.replaceColors("&f"), new ArrayList<>(), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(1, createGUIItem(Util.replaceColors("&f"), new ArrayList<>(), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(2, createGUIItem(Util.replaceColors("&f"), new ArrayList<>(), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(3, createGUIItem(Util.replaceColors("&f"), new ArrayList<>(), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(4, createGUIItem(Util.replaceColors("&f"), new ArrayList<>(), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(5, createGUIItem(Util.replaceColors("&f"), new ArrayList<>(), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(6, createGUIItem(Util.replaceColors("&f"), new ArrayList<>(), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(7, createGUIItem(Util.replaceColors("&f"), new ArrayList<>(), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(8, createGUIItem(Util.replaceColors("&f"), new ArrayList<>(), Material.STAINED_GLASS_PANE, 1, 15, false));


        /*
         * Bottom lane glass
         */
        inventory.setItem(46, createGUIItem(Util.replaceColors("&f"), new ArrayList<>(), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(47, createGUIItem(Util.replaceColors("&f"), new ArrayList<>(), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(48, createGUIItem(Util.replaceColors("&f"), new ArrayList<>(), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(50, createGUIItem(Util.replaceColors("&f"), new ArrayList<>(), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(51, createGUIItem(Util.replaceColors("&f"), new ArrayList<>(), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(52, createGUIItem(Util.replaceColors("&f"), new ArrayList<>(), Material.STAINED_GLASS_PANE, 1, 15, false));

        int i = 19;
        for (Group group : currentGroups) {

            if (i == 25) i += 2;

            /*
             * Add group  to menu
             */
            if (changeOrder) {
                inventory.setItem(i, createGUIItem(Util.replace(player, "&3"+group.getName()), new ArrayList<String>(Arrays.asList(Util.replace(player, "&aLeft Click &fto increase priority"), Util.replace(player, "&aRight Click &fto decrease priority"))), group.getMaterial(), group.getPriority() % 64 == 0 ? 1 : group.getPriority() % 64, 0, false));
            } else {
                inventory.setItem(i, createGUIItem(Util.replace(player, "&3"+group.getName()), new ArrayList<String>(Arrays.asList(Util.replace(player, "&aClick &fto manage this group"))), group.getMaterial(), 1, 0, false));
            }

            i++;
        }

    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public Group getGroupAt(final Integer slot) {
        if (slot >= 19 && slot <= 25) {
            return currentGroups.size() >= slot - 18 ? currentGroups.get(slot - 19) : null;
        } else if (slot >= 28 && slot <= 34) {
            return currentGroups.size() >= slot - 20 ? currentGroups.get(slot - 21) : null;
        }
        return null;
    }

    public boolean getChangeOrder() {
        return changeOrder;
    }

    public MainMenu setChangeOrder(final boolean changeOrder) {
        this.changeOrder = changeOrder;
        update();
        return this;
    }

    public ItemStack createGUIItem(final String name, final ArrayList<String> lore, final Material material, final Integer amount, final Integer data, final boolean enchanted) {
        ItemStack i = new ItemStack(material, amount, data.byteValue());
        ItemMeta iMeta = i.getItemMeta();
        iMeta.setDisplayName(name);
        iMeta.setLore(lore);
        iMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        i.setItemMeta(iMeta);
        if (enchanted)
            i.addUnsafeEnchantment(GlowEnchantment.GLOW_ENCHANTMENT, 1);
        return i;
    }
}
