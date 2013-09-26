package net.minecraft4455.mysticrpg.core.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabsMRPG extends CreativeTabs {

    private static ItemStack display;

    public CreativeTabsMRPG(int id, String label) {
        super(id, label); // The constructor for your tab
    }

    public static void setDisplayStack(ItemStack stack) {
        display = stack;
    }

    @Override
    public ItemStack getIconItemStack() {
        return display;
    }

}
