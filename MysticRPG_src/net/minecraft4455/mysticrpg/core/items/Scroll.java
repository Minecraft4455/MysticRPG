package net.minecraft4455.mysticrpg.core.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft4455.mysticrpg.core.info.MysticModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Scroll extends Item {

    public Scroll(int id) {

        super(id);
        this.setCreativeTab(CreativeTabs.tabMaterials);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister icon) {

        itemIcon = icon.registerIcon(MysticModInfo.MODID + ":Scroll");

    }

}
