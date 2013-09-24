package net.minecraft4455.mysticrpg.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft4455.mysticrpg.core.info.MysticModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class YirawiaDirt extends Block {

    public YirawiaDirt(int id) {

        super(id, Material.ground);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setStepSound(soundGravelFootstep);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister icon) {

        blockIcon = icon.registerIcon(MysticModInfo.MODID + ":YirawiaDirt");

    }

}
