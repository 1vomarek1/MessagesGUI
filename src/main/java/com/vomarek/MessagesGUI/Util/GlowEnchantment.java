package com.vomarek.MessagesGUI.Util;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class GlowEnchantment extends Enchantment {
    public static final GlowEnchantment GLOW_ENCHANTMENT = new GlowEnchantment(111);

    public GlowEnchantment(int id) {
        super(id);
    }

    public String getName() {
        return null;
    }

    public int getMaxLevel() {
        return 0;
    }

    public int getStartLevel() {
        return 0;
    }

    public EnchantmentTarget getItemTarget() {
        return null;
    }

    public boolean isTreasure() {
        return false;
    }

    public boolean isCursed() {
        return false;
    }

    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    public boolean canEnchantItem(ItemStack item) {
        return false;
    }
}
