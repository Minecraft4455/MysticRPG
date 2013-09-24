package net.minecraft4455.mysticrpg.core.blocks;

import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft4455.mysticrpg.core.info.MysticModInfo;
import net.minecraft4455.mysticrpg.core.items.MysticItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlackMercy extends BlockCrops {
    @SideOnly(Side.CLIENT)
    private Icon[] iconArray;

    public BlackMercy(int par1) {
        super(par1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2) {
        if (par2 < 7) {
            if (par2 == 6) {
                par2 = 5;
            }

            return iconArray[par2 >> 1];
        } else
            return iconArray[3];
    }

    @Override
    protected int getSeedItem() {
        return MysticItems.BlackMercySeed.itemID;
    }

    @Override
    protected int getCropItem() {
        return MysticItems.BlackMercySubstance.itemID;
    }

    @Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3,
            int par4, int par5, float par6, int par7) {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5,
                par6, par7);

        if (!par1World.isRemote) {
            if (par5 >= 7 && par1World.rand.nextInt(50) == 0) {
                this.dropBlockAsItem_do(par1World, par2, par3, par4,
                        new ItemStack(MysticItems.BlackMercySeed));
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
        iconArray = new Icon[4];

        for (int i = 0; i < iconArray.length; ++i) {
            iconArray[i] = par1IconRegister.registerIcon(MysticModInfo.MODID
                    + ":BlackMercy" + "_stage_" + i);
        }
    }
}
