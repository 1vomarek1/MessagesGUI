package com.vomarek.MessagesGUI.GUI;

import com.vomarek.MessagesGUI.Groups.Group;
import com.vomarek.MessagesGUI.Util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GroupMenu implements InventoryHolder {
    private Inventory inventory;
    private Player player;
    private Group group;
    private String part;

    public GroupMenu(Group group, Player player) {
        this.player = player;
        this.group = group;
        this.part = "Info";
        update();
    }

    public GroupMenu(Group group, Player player, String part) {
        this.player = player;
        this.group = group;
        this.part = part;
        update();
    }

    public void update() {
        inventory = Bukkit.createInventory(this, 54, "Groups >> " + this.group.getName());

        inventory.setItem(3, createGUIItem(Util.replace(player, "&3Join Settings"), new ArrayList<>(Collections.singletonList(Util.replace(player, "&aClick &fto open join settings"))), Material.DIAMOND_CHESTPLATE, 1, 0, false));
        inventory.setItem(4, createGUIItem(Util.replace(player, "&3Death Settings"), new ArrayList<>(Collections.singletonList(Util.replace(player, "&aClick &fto open death settings"))), Material.DIAMOND_SWORD, 1, 0, false));
        inventory.setItem(5, createGUIItem(Util.replace(player, "&3Leave Settings"), new ArrayList<>(Collections.singletonList(Util.replace(player, "&aClick &fto open leave settings"))), Material.LEATHER_CHESTPLATE, 1, 0, false));
        inventory.setItem(13, createGUIItem(Util.replace(player, "&3Group Settings"), new ArrayList<>(Collections.singletonList(Util.replace(player, "&aClick &fto open group settings"))), Material.SIGN, 1, 0, false));


        /*
         * Main menu item
         */

        inventory.setItem(49, createGUIItem(Util.replace(player, "&3Back to main menu"), new ArrayList<>(Collections.singletonList(Util.replace(player, "&aClick &fto go to main menu"))), Material.IRON_DOOR, 1, 0, false));



        /*
         * Top lane Glass
         */
        inventory.setItem(9, createGUIItem(Util.replaceColors("&f"), new ArrayList<String>(Collections.singletonList("")), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(10, createGUIItem(Util.replaceColors("&f"), new ArrayList<String>(Collections.singletonList("")), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(11, createGUIItem(Util.replaceColors("&f"), new ArrayList<String>(Collections.singletonList("")), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(12, createGUIItem(Util.replaceColors("&f"), new ArrayList<String>(Collections.singletonList("")), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(14, createGUIItem(Util.replaceColors("&f"), new ArrayList<String>(Collections.singletonList("")), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(15, createGUIItem(Util.replaceColors("&f"), new ArrayList<String>(Collections.singletonList("")), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(16, createGUIItem(Util.replaceColors("&f"), new ArrayList<String>(Collections.singletonList("")), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(17, createGUIItem(Util.replaceColors("&f"), new ArrayList<String>(Collections.singletonList("")), Material.STAINED_GLASS_PANE, 1, 15, false));


        /*
         * Bottom lane Glass
         */
        inventory.setItem(45, createGUIItem(Util.replaceColors("&f"), new ArrayList<String>(Collections.singletonList("")), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(46, createGUIItem(Util.replaceColors("&f"), new ArrayList<String>(Collections.singletonList("")), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(47, createGUIItem(Util.replaceColors("&f"), new ArrayList<String>(Collections.singletonList("")), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(48, createGUIItem(Util.replaceColors("&f"), new ArrayList<String>(Collections.singletonList("")), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(50, createGUIItem(Util.replaceColors("&f"), new ArrayList<String>(Collections.singletonList("")), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(51, createGUIItem(Util.replaceColors("&f"), new ArrayList<String>(Collections.singletonList("")), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(52, createGUIItem(Util.replaceColors("&f"), new ArrayList<String>(Collections.singletonList("")), Material.STAINED_GLASS_PANE, 1, 15, false));
        inventory.setItem(53, createGUIItem(Util.replaceColors("&f"), new ArrayList<String>(Collections.singletonList("")), Material.STAINED_GLASS_PANE, 1, 15, false));


        switch (part) {
            case "Info":
                inventory.setItem(13, createGUIItem(Util.replace(player, "&3Group Settings"), new ArrayList<>(Arrays.asList(Util.replace(this.player, "&aOpened"))), Material.SIGN, 1, 0, true));

                inventory.setItem(29, createGUIItem(Util.replace(this.player, "&3Group Item"), new ArrayList<>(Arrays.asList(Util.replace(this.player, "&aClick on item in your inventory &fto change it"))), group.getMaterial(), 1, 0, false));
                inventory.setItem(31, createGUIItem(Util.replace(this.player, "&3Info"), new ArrayList<>(Arrays.asList(Util.replace(this.player, "&aID &f" + group.getName()), Util.replace(player, "&aPriority &f"+group.getPriority()), Util.replace(this.player, "&aPermission &fmessagesgui.group."+group.getName()))), Material.PAPER, 1, 0, false));
                inventory.setItem(33, createGUIItem(Util.replace(this.player, "&3Delete Group"), new ArrayList<>(Arrays.asList(Util.replace(this.player, "&cClick &fto delete this group"))), Material.REDSTONE_BLOCK, 1, 0, false));


                break;
            case "Join":
                inventory.setItem(3, createGUIItem(Util.replace(player, "&3Join Settings"), new ArrayList<>(Arrays.asList(Util.replace(this.player, "&aOpened"))), Material.DIAMOND_CHESTPLATE, 1, 0, true));

                inventory.setItem(29, createGUIItem(Util.replace(player, "&3Join Message"), new ArrayList<>(Arrays.asList(Util.replace(this.player, "&f" + this.group.getJoinMessage()), Util.replace(this.player, "&aClick &fto change join message"))), Material.NAME_TAG, 1, 0, false));
                inventory.setItem(31, createGUIItem(Util.replace(player, "&3Join Title"), new ArrayList<>(Arrays.asList(Util.replace(this.player, group.isTitleEnabled() ? "&aenabled" : "&cdisabled"), Util.replace(this.player, "&f" + group.getJoinTitle().getTitle()), Util.replace(this.player, "&f"+group.getJoinTitle().getSubtitle()), Util.replace(this.player, "&aLeft Click &fto change title"), Util.replace(this.player, "&aRight Click &fto change subtitle"), Util.replace(this.player, "&aMiddle Click &fto change toggle join title"))), Material.PAPER, 1, 0, false));
                inventory.setItem(33, createGUIItem(Util.replace(player, "&3Join Commands"), new ArrayList<>(Arrays.asList(Util.replace(this.player, "&aComing soon!" /* TODO: Commands */), Util.replace(this.player, ""/*+"&aClick &fto change join commands"*/))), Material.COMMAND, 1, 0, false));


                break;
            case "Death":
                inventory.setItem(4, createGUIItem(Util.replace(this.player, "&3Death Settings"), new ArrayList<>(Arrays.asList(Util.replace(this.player, "&aOpened"))), Material.DIAMOND_SWORD, 1, 0, true));

                inventory.setItem(29, createGUIItem(Util.replace(this.player, "&3Death Message"), new ArrayList<>(Arrays.asList(Util.replace(this.player, "&f" + this.group.getDeathMessage()), Util.replace(this.player, "&aClick &fto change death message"))), Material.NAME_TAG, 1, 0, false));
                inventory.setItem(33, createGUIItem(Util.replace(this.player, "&3Death Commands"), new ArrayList<>(Arrays.asList(Util.replace(this.player, "&aComing soon!"), Util.replace(this.player, ""/*+"&aClick &fto change death commands"*/))), Material.COMMAND, 1, 0, false));


                break;
            case "Leave":
                inventory.setItem(5, createGUIItem(Util.replace(this.player, "&3Leave Settings"), new ArrayList<>(Arrays.asList(Util.replace(this.player, "&aOpened"))), Material.LEATHER_CHESTPLATE, 1, 0, true));

                inventory.setItem(29, createGUIItem(Util.replace(this.player, "&3Leave Message"), new ArrayList<>(Arrays.asList(Util.replace(this.player, "&f" + this.group.getLeaveMessage()), Util.replace(this.player, "&aClick &fto change leave message"))), Material.NAME_TAG, 1, 0, false));
                inventory.setItem(33, createGUIItem(Util.replace(this.player, "&3Leave Commands"), new ArrayList<>(Arrays.asList(Util.replace(this.player, "&aComing soon!"), Util.replace(this.player, ""/*+"&aClick &fto change leave commands"*/))), Material.COMMAND, 1, 0, false));


                break;
        }
    }

    public GroupMenu setPart(String part) {
        this.part = part;
        update();
        return this;
    }

    public String getPart() {
        return part;
    }

    public Player getPlayer() {
        return player;
    }

    public Group getGroup() {
        return this.group;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public ItemStack createGUIItem(String name, ArrayList<String> lore, Material material, Integer amount, Integer data, boolean enchanted) {
        ItemStack i = new ItemStack(material, amount, data.byteValue());
        ItemMeta iMeta = i.getItemMeta();
        iMeta.setDisplayName(name);
        iMeta.setLore(lore);
        iMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        i.setItemMeta(iMeta);
        if (enchanted)
            i.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        return i;
    }
}
