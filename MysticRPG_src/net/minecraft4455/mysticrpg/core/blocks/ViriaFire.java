package net.minecraft4455.mysticrpg.core.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft4455.mysticrpg.MysticRPG;
import net.minecraft4455.mysticrpg.core.info.MysticModInfo;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ViriaFire extends BlockFire {

    @SideOnly(Side.CLIENT)
    private Icon[] iconArray;

    public ViriaFire(int par1) {
        super(par1);
        setTickRandomly(true);
        setCreativeTab(MysticRPG.tab);
    }

    @Override
    public Icon getIcon(int par1, int par2) {
        return blockIcon;
    }

    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3,
            int par4, Entity par5Entity) {
        par5Entity.attackEntityFrom(DamageSource.onFire, 1.0F);
    }

    @Override
    public void initializeBlock() {
        setBurnRate(Block.planks.blockID, 5, 20);
        setBurnRate(Block.woodDoubleSlab.blockID, 5, 20);
        setBurnRate(Block.woodSingleSlab.blockID, 5, 20);
        setBurnRate(Block.fence.blockID, 5, 20);
        setBurnRate(Block.stairsWoodOak.blockID, 5, 20);
        setBurnRate(Block.stairsWoodBirch.blockID, 5, 20);
        setBurnRate(Block.stairsWoodSpruce.blockID, 5, 20);
        setBurnRate(Block.stairsWoodJungle.blockID, 5, 20);
        setBurnRate(Block.wood.blockID, 5, 5);
        setBurnRate(Block.leaves.blockID, 30, 60);
        setBurnRate(Block.bookShelf.blockID, 30, 20);
        setBurnRate(Block.tnt.blockID, 15, 100);
        setBurnRate(Block.tallGrass.blockID, 60, 100);
        setBurnRate(Block.cloth.blockID, 30, 60);
        setBurnRate(Block.vine.blockID, 15, 100);
    }

    private void setBurnRate(int par1, int par2, int par3) {
        Block.setBurnProperties(par1, par2, par3);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
            int par2, int par3, int par4) {
        return null;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return 3;
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return 0;
    }

    @Override
    public int tickRate(World par1World) {
        return 30;
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4,
            Random par5Random) {
        if (par1World.getGameRules().getGameRuleBooleanValue("doFireTick")) {
            Block base = Block.blocksList[par1World.getBlockId(par2, par3 - 1,
                    par4)];
            boolean flag = base != null
                    && base.isFireSource(par1World, par2, par3 - 1, par4,
                            par1World.getBlockMetadata(par2, par3 - 1, par4),
                            ForgeDirection.UP);
            if (!canPlaceBlockAt(par1World, par2, par3, par4)) {
                par1World.setBlockToAir(par2, par3, par4);
            }
            if (!flag
                    && par1World.isRaining()
                    && (par1World.canLightningStrikeAt(par2, par3, par4)
                            || par1World.canLightningStrikeAt(par2 - 1, par3,
                                    par4)
                            || par1World.canLightningStrikeAt(par2 + 1, par3,
                                    par4)
                            || par1World.canLightningStrikeAt(par2, par3,
                                    par4 - 1) || par1World
                                .canLightningStrikeAt(par2, par3, par4 + 1))) {
                par1World.setBlockToAir(par2, par3, par4);
            } else {
                int l = par1World.getBlockMetadata(par2, par3, par4);
                if (l < 15) {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, l
                            + par5Random.nextInt(3) / 2, 4);
                }
                par1World.scheduleBlockUpdate(par2, par3, par4, blockID,
                        tickRate(par1World) + par5Random.nextInt(10));
                if (!flag && !canNeighborBurn(par1World, par2, par3, par4)) {
                    if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1,
                            par4) || l > 3) {
                        par1World.setBlockToAir(par2, par3, par4);
                    }
                } else if (!flag
                        && !canBlockCatchFire(par1World, par2, par3 - 1, par4,
                                ForgeDirection.UP) && l == 15
                        && par5Random.nextInt(4) == 0) {
                    par1World.setBlockToAir(par2, par3, par4);
                } else {
                    boolean flag1 = par1World.isBlockHighHumidity(par2, par3,
                            par4);
                    byte b0 = 0;
                    if (flag1) {
                        b0 = -50;
                    }
                    tryToCatchBlockOnFire(par1World, par2 + 1, par3, par4,
                            300 + b0, par5Random, l, ForgeDirection.WEST);
                    tryToCatchBlockOnFire(par1World, par2 - 1, par3, par4,
                            300 + b0, par5Random, l, ForgeDirection.EAST);
                    tryToCatchBlockOnFire(par1World, par2, par3 - 1, par4,
                            250 + b0, par5Random, l, ForgeDirection.UP);
                    tryToCatchBlockOnFire(par1World, par2, par3 + 1, par4,
                            250 + b0, par5Random, l, ForgeDirection.DOWN);
                    tryToCatchBlockOnFire(par1World, par2, par3, par4 - 1,
                            300 + b0, par5Random, l, ForgeDirection.SOUTH);
                    tryToCatchBlockOnFire(par1World, par2, par3, par4 + 1,
                            300 + b0, par5Random, l, ForgeDirection.NORTH);
                    for (int i1 = par2 - 1; i1 <= par2 + 1; i1++) {
                        for (int j1 = par4 - 1; j1 <= par4 + 1; j1++) {
                            for (int k1 = par3 - 1; k1 <= par3 + 4; k1++) {
                                if (i1 != par2 || k1 != par3 || j1 != par4) {
                                    int l1 = 100;
                                    if (k1 > par3 + 1) {
                                        l1 += (k1 - (par3 + 1)) * 100;
                                    }
                                    int i2 = getChanceOfNeighborsEncouragingFire(
                                            par1World, i1, k1, j1);
                                    if (i2 > 0) {
                                        int j2 = (i2 + 40 + par1World.difficultySetting * 7)
                                                / (l + 30);
                                        if (flag1) {
                                            j2 /= 2;
                                        }
                                        if (j2 > 0
                                                && par5Random.nextInt(l1) <= j2
                                                && (!par1World.isRaining() || !par1World
                                                        .canLightningStrikeAt(
                                                                i1, k1, j1))
                                                && !par1World
                                                        .canLightningStrikeAt(
                                                                i1 - 1, k1,
                                                                par4)
                                                && !par1World
                                                        .canLightningStrikeAt(
                                                                i1 + 1, k1, j1)
                                                && !par1World
                                                        .canLightningStrikeAt(
                                                                i1, k1, j1 - 1)
                                                && !par1World
                                                        .canLightningStrikeAt(
                                                                i1, k1, j1 + 1)) {
                                            int k2 = l + par5Random.nextInt(5)
                                                    / 4;
                                            if (k2 > 15) {
                                                k2 = 15;
                                            }
                                            par1World.setBlock(i1, k1, j1,
                                                    blockID, k2, 3);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean func_82506_l() {
        return true;
    }

    private void tryToCatchBlockOnFire(World par1World, int par2, int par3,
            int par4, int par5, Random par6Random, int par7, ForgeDirection face) {
        int j1 = 0;
        Block block = Block.blocksList[par1World.getBlockId(par2, par3, par4)];
        if (block != null) {
            j1 = block.getFlammability(par1World, par2, par3, par4,
                    par1World.getBlockMetadata(par2, par3, par4), face);
        }
        if (par6Random.nextInt(par5) < j1) {
            boolean flag = par1World.getBlockId(par2, par3, par4) == Block.tnt.blockID;
            if (par6Random.nextInt(par7 + 10) < 5
                    && !par1World.canLightningStrikeAt(par2, par3, par4)) {
                int k1 = par7 + par6Random.nextInt(5) / 4;
                if (k1 > 15) {
                    k1 = 15;
                }
                par1World.setBlock(par2, par3, par4, blockID, k1, 3);
            } else {
                par1World.setBlockToAir(par2, par3, par4);
            }
            if (flag) {
                Block.tnt.onBlockDestroyedByPlayer(par1World, par2, par3, par4,
                        1);
            }
        }
    }

    private boolean canNeighborBurn(World par1World, int par2, int par3,
            int par4) {
        return canBlockCatchFire(par1World, par2 + 1, par3, par4,
                ForgeDirection.WEST)
                || canBlockCatchFire(par1World, par2 - 1, par3, par4,
                        ForgeDirection.EAST)
                || canBlockCatchFire(par1World, par2, par3 - 1, par4,
                        ForgeDirection.UP)
                || canBlockCatchFire(par1World, par2, par3 + 1, par4,
                        ForgeDirection.DOWN)
                || canBlockCatchFire(par1World, par2, par3, par4 - 1,
                        ForgeDirection.SOUTH)
                || canBlockCatchFire(par1World, par2, par3, par4 + 1,
                        ForgeDirection.NORTH);
    }

    private int getChanceOfNeighborsEncouragingFire(World par1World, int par2,
            int par3, int par4) {
        byte b0 = 0;
        if (!par1World.isAirBlock(par2, par3, par4))
            return 0;
        int l = getChanceToEncourageFire(par1World, par2 + 1, par3, par4, b0,
                ForgeDirection.WEST);
        l = getChanceToEncourageFire(par1World, par2 - 1, par3, par4, l,
                ForgeDirection.EAST);
        l = getChanceToEncourageFire(par1World, par2, par3 - 1, par4, l,
                ForgeDirection.UP);
        l = getChanceToEncourageFire(par1World, par2, par3 + 1, par4, l,
                ForgeDirection.DOWN);
        l = getChanceToEncourageFire(par1World, par2, par3, par4 - 1, l,
                ForgeDirection.SOUTH);
        l = getChanceToEncourageFire(par1World, par2, par3, par4 + 1, l,
                ForgeDirection.NORTH);
        return l;
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    @Deprecated
    public boolean canBlockCatchFire(IBlockAccess par1IBlockAccess, int par2,
            int par3, int par4) {
        return canBlockCatchFire(par1IBlockAccess, par2, par3, par4,
                ForgeDirection.UP);
    }

    @Override
    @Deprecated
    public int getChanceToEncourageFire(World par1World, int par2, int par3,
            int par4, int par5) {
        return getChanceToEncourageFire(par1World, par2, par3, par4, par5,
                ForgeDirection.UP);
    }

    @Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
        return par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4)
                || canNeighborBurn(par1World, par2, par3, par4);
    }

    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3,
            int par4, int par5) {
        if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4)
                && !canNeighborBurn(par1World, par2, par3, par4)) {
            par1World.setBlockToAir(par2, par3, par4);
        }
    }

    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
        if (par1World.getBlockId(par2, par3 - 1, par4) != MysticBlocks.YirawiaBlock.blockID
                || !((YirawiaPortal) MysticBlocks.YirawiaPortal)
                        .tryToCreatePortal(par1World, par2, par3, par4)) {
            if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4)
                    && !canNeighborBurn(par1World, par2, par3, par4)) {
                par1World.setBlockToAir(par2, par3, par4);
            } else {
                par1World.scheduleBlockUpdate(par2, par3, par4, blockID,
                        tickRate(par1World) + par1World.rand.nextInt(10));
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3,
            int par4, Random par5Random) {
        if (par5Random.nextInt(24) == 0) {
            par1World.playSound(par2 + 0.5F, par3 + 0.5F, par4 + 0.5F,
                    "fire.fire", 1.0F + par5Random.nextFloat(),
                    par5Random.nextFloat() * 0.7F + 0.3F, false);
        }
        if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4)
                && !((ViriaFire) MysticBlocks.ViriaFire).canBlockCatchFire(
                        par1World, par2, par3 - 1, par4, ForgeDirection.UP)) {
            if (((ViriaFire) MysticBlocks.ViriaFire).canBlockCatchFire(
                    par1World, par2 - 1, par3, par4, ForgeDirection.EAST)) {
                for (int l = 0; l < 2; l++) {
                    float f = par2 + par5Random.nextFloat() * 0.1F;
                    float f1 = par3 + par5Random.nextFloat();
                    float f2 = par4 + par5Random.nextFloat();
                    par1World.spawnParticle("largesmoke", f, f1, f2, 0.0D,
                            0.0D, 0.0D);
                }
            }
            if (((ViriaFire) MysticBlocks.ViriaFire).canBlockCatchFire(
                    par1World, par2 + 1, par3, par4, ForgeDirection.WEST)) {
                for (int l = 0; l < 2; l++) {
                    float f = par2 + 1 - par5Random.nextFloat() * 0.1F;
                    float f1 = par3 + par5Random.nextFloat();
                    float f2 = par4 + par5Random.nextFloat();
                    par1World.spawnParticle("largesmoke", f, f1, f2, 0.0D,
                            0.0D, 0.0D);
                }
            }
            if (((ViriaFire) MysticBlocks.ViriaFire).canBlockCatchFire(
                    par1World, par2, par3, par4 - 1, ForgeDirection.SOUTH)) {
                for (int l = 0; l < 2; l++) {
                    float f = par2 + par5Random.nextFloat();
                    float f1 = par3 + par5Random.nextFloat();
                    float f2 = par4 + par5Random.nextFloat() * 0.1F;
                    par1World.spawnParticle("largesmoke", f, f1, f2, 0.0D,
                            0.0D, 0.0D);
                }
            }
            if (((ViriaFire) MysticBlocks.ViriaFire).canBlockCatchFire(
                    par1World, par2, par3, par4 + 1, ForgeDirection.NORTH)) {
                for (int l = 0; l < 2; l++) {
                    float f = par2 + par5Random.nextFloat();
                    float f1 = par3 + par5Random.nextFloat();
                    float f2 = par4 + 1 - par5Random.nextFloat() * 0.1F;
                    par1World.spawnParticle("largesmoke", f, f1, f2, 0.0D,
                            0.0D, 0.0D);
                }
            }
            if (((ViriaFire) MysticBlocks.ViriaFire).canBlockCatchFire(
                    par1World, par2, par3 + 1, par4, ForgeDirection.DOWN)) {
                for (int l = 0; l < 2; l++) {
                    float f = par2 + par5Random.nextFloat();
                    float f1 = par3 + 1 - par5Random.nextFloat() * 0.1F;
                    float f2 = par4 + par5Random.nextFloat();
                    par1World.spawnParticle("largesmoke", f, f1, f2, 0.0D,
                            0.0D, 0.0D);
                }
            }
        } else {
            for (int l = 0; l < 3; l++) {
                float f = par2 + par5Random.nextFloat();
                float f1 = par3 + par5Random.nextFloat() * 0.5F + 0.5F;
                float f2 = par4 + par5Random.nextFloat();
                par1World.spawnParticle("largesmoke", f, f1, f2, 0.0D, 0.0D,
                        0.0D);
            }
        }
    }

    @Override
    public boolean canBlockCatchFire(IBlockAccess world, int x, int y, int z,
            ForgeDirection face) {
        Block block = Block.blocksList[world.getBlockId(x, y, z)];
        if (block != null)
            return block.isFlammable(world, x, y, z,
                    world.getBlockMetadata(x, y, z), face);
        return false;
    }

    @Override
    public int getChanceToEncourageFire(World world, int x, int y, int z,
            int oldChance, ForgeDirection face) {
        int newChance = 0;
        Block block = Block.blocksList[world.getBlockId(x, y, z)];
        if (block != null) {
            newChance = block.getFireSpreadSpeed(world, x, y, z,
                    world.getBlockMetadata(x, y, z), face);
        }
        return newChance > oldChance ? newChance : oldChance;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
        iconArray = new Icon[] {
                par1IconRegister.registerIcon(MysticModInfo.MODID
                        + ":ViriaFire_0"),
                par1IconRegister.registerIcon(MysticModInfo.MODID
                        + ":ViriaFire_1") };
    }

    @SideOnly(Side.CLIENT)
    public Icon func_94438_c(int par1) {
        return iconArray[par1];
    }

    @SideOnly(Side.CLIENT)
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
        return iconArray[0];
    }
}