package net.minecraft4455.mysticrpg.core.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft4455.mysticrpg.MysticRPG;
import net.minecraft4455.mysticrpg.core.blocks.MysticBlocks;
import net.minecraft4455.mysticrpg.core.info.MysticModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NatureScroll extends Item {

    public NatureScroll(int id) {

        super(id);
        this.setCreativeTab(MysticRPG.tab);
        this.setMaxStackSize(1);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister icon) {

        itemIcon = icon.registerIcon(MysticModInfo.MODID + ":NatureScroll");

    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack,
            EntityPlayer par2EntityPlayer, World par3World, int X, int Y,
            int Z, int par7, float par8, float par9, float par10) {
        if (!par3World.isRemote) {
            int direction = MathHelper
                    .floor_double(par2EntityPlayer.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3;
            if (direction == 1 || direction == 3) {
                for (int y = 1; y < 5; y++) {
                    for (int z = -1; z < 2; z++) {
                        if (par3World.getBlockId(X, Y + y, Z + z) != 0) {
                            par2EntityPlayer
                                    .addChatMessage("No room for a portal.");
                            return false;
                        }
                    }
                }

                par3World.setBlock(X, Y + 2, Z,
                        MysticBlocks.YirawiaBlock.blockID);
                par3World.setBlock(X + 1, Y + 2, Z,
                        MysticBlocks.YirawiaBlock.blockID);
                par3World.setBlock(X + 2, Y + 3, Z,
                        MysticBlocks.YirawiaBlock.blockID);
                par3World.setBlock(X - 1, Y + 3, Z,
                        MysticBlocks.YirawiaBlock.blockID);
                par3World.setBlock(X + 2, Y + 4, Z,
                        MysticBlocks.YirawiaBlock.blockID);
                par3World.setBlock(X - 1, Y + 4, Z,
                        MysticBlocks.YirawiaBlock.blockID);
                par3World.setBlock(X + 2, Y + 5, Z,
                        MysticBlocks.YirawiaBlock.blockID);
                par3World.setBlock(X - 1, Y + 5, Z,
                        MysticBlocks.YirawiaBlock.blockID);
                par3World.setBlock(X, Y + 6, Z,
                        MysticBlocks.YirawiaBlock.blockID);
                par3World.setBlock(X + 1, Y + 6, Z,
                        MysticBlocks.YirawiaBlock.blockID);
                par3World.setBlock(X, Y + 3, Z, MysticBlocks.ViriaFire.blockID);
            } else {
                for (int y = 1; y < 5; y++) {
                    for (int x = -1; x < 2; x++) {
                        if (par3World.getBlockId(X + x, Y + y, Z) != 0) {
                            par2EntityPlayer
                                    .addChatMessage("No room for a portal.");
                            return false;
                        }
                    }
                }
                par3World.setBlock(X, Y + 2, Z,
                        MysticBlocks.YirawiaBlock.blockID);
                par3World.setBlock(X + 1, Y + 2, Z,
                        MysticBlocks.YirawiaBlock.blockID);
                par3World.setBlock(X + 2, Y + 3, Z,
                        MysticBlocks.YirawiaBlock.blockID);
                par3World.setBlock(X - 1, Y + 3, Z,
                        MysticBlocks.YirawiaBlock.blockID);
                par3World.setBlock(X + 2, Y + 4, Z,
                        MysticBlocks.YirawiaBlock.blockID);
                par3World.setBlock(X - 1, Y + 4, Z,
                        MysticBlocks.YirawiaBlock.blockID);
                par3World.setBlock(X + 2, Y + 5, Z,
                        MysticBlocks.YirawiaBlock.blockID);
                par3World.setBlock(X - 1, Y + 5, Z,
                        MysticBlocks.YirawiaBlock.blockID);
                par3World.setBlock(X, Y + 6, Z,
                        MysticBlocks.YirawiaBlock.blockID);
                par3World.setBlock(X + 1, Y + 6, Z,
                        MysticBlocks.YirawiaBlock.blockID);
                par3World.setBlock(X, Y + 3, Z, MysticBlocks.ViriaFire.blockID);
            }
            return true;
        }
        return true;
    }
}
