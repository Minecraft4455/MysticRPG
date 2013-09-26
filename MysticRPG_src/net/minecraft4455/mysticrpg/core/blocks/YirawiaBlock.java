package net.minecraft4455.mysticrpg.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft4455.mysticrpg.MysticRPG;
import net.minecraft4455.mysticrpg.core.info.MysticModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class YirawiaBlock extends Block {

    public YirawiaBlock(int id) {

        super(id, Material.rock);
        this.setCreativeTab(MysticRPG.tab);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister icon) {

        blockIcon = icon.registerIcon(MysticModInfo.MODID + ":YirawiaBlock");

    }

}
