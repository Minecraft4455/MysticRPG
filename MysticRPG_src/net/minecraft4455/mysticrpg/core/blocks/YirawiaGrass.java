package net.minecraft4455.mysticrpg.core.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft4455.mysticrpg.MysticRPG;
import net.minecraft4455.mysticrpg.core.info.MysticModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class YirawiaGrass extends Block {

    @SideOnly(Side.CLIENT)
    private Icon iconGrassTop;
    @SideOnly(Side.CLIENT)
    private Icon iconSnowSide;
    @SideOnly(Side.CLIENT)
    private Icon iconGrassSideOverlay;

    protected YirawiaGrass(int par1) {

        super(par1, Material.grass);
        this.setTickRandomly(true);
        this.setCreativeTab(MysticRPG.tab);
        this.setStepSound(soundGrassFootstep);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2) {
        return par1 == 1 ? iconGrassTop : par1 == 0 ? MysticBlocks.YirawiaDirt
                .getBlockTextureFromSide(par1) : blockIcon;
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4,
            Random par5Random) {
        if (!par1World.isRemote) {
            if (par1World.getBlockLightValue(par2, par3 + 1, par4) < 4
                    && par1World.getBlockLightOpacity(par2, par3 + 1, par4) > 2) {
                par1World.setBlock(par2, par3, par4,
                        MysticBlocks.YirawiaDirt.blockID);
            } else if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9) {
                for (int l = 0; l < 4; ++l) {
                    int i1 = par2 + par5Random.nextInt(3) - 1;
                    int j1 = par3 + par5Random.nextInt(5) - 3;
                    int k1 = par4 + par5Random.nextInt(3) - 1;
                    par1World.getBlockId(i1, j1 + 1, k1);

                    if (par1World.getBlockId(i1, j1, k1) == MysticBlocks.YirawiaDirt.blockID
                            && par1World.getBlockLightValue(i1, j1 + 1, k1) >= 4
                            && par1World.getBlockLightOpacity(i1, j1 + 1, k1) <= 2) {
                        par1World.setBlock(i1, j1, k1,
                                MysticBlocks.YirawiaDirt.blockID);
                    }
                }
            }
        }
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3) {
        return MysticBlocks.YirawiaDirt.idDropped(0, par2Random, par3);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2,
            int par3, int par4, int par5) {
        if (par5 == 1)
            return iconGrassTop;
        else if (par5 == 0)
            return MysticBlocks.YirawiaDirt.getBlockTextureFromSide(par5);
        else {
            Material material = par1IBlockAccess.getBlockMaterial(par2,
                    par3 + 1, par4);
            return material != Material.snow
                    && material != Material.craftedSnow ? blockIcon
                    : iconSnowSide;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {

        blockIcon = par1IconRegister.registerIcon(MysticModInfo.MODID
                + ":YirawiaGrassSide");
        iconGrassTop = par1IconRegister.registerIcon(MysticModInfo.MODID
                + ":YirawiaGrassTop");

    }

}
