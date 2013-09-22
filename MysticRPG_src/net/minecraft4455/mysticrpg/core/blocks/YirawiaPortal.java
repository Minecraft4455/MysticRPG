package net.minecraft4455.mysticrpg.core.blocks;

import java.util.Random;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft4455.mysticrpg.core.dimensionregistry.MysticDimensions;
import net.minecraft4455.mysticrpg.core.dimensionregistry.teleporter.YirawiaTeleporter;
import net.minecraft4455.mysticrpg.core.info.MysticModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class YirawiaPortal extends BlockBreakable {
	
	public YirawiaPortal(int par1) {
		
		super(par1, MysticModInfo.MODID + ":YirawiaPortal", Material.portal, false);
		this.setTickRandomly(true);
		this.setHardness(-1.0F);
		this.setStepSound(soundGlassFootstep);
		this.setLightValue(0.75F);
		this.setCreativeTab(CreativeTabs.tabBlock);
		
	}

	public void updateTick(World par1World, int par2, int par3, int par4,
			Random par5Random) {
		super.updateTick(par1World, par2, par3, par4, par5Random);
		if (par1World.provider.isSurfaceWorld()
				&& par5Random.nextInt(2000) < par1World.difficultySetting) {
			int l;
			for (l = par3; !par1World.doesBlockHaveSolidTopSurface(par2, l,
					par4) && l > 0; --l) {
				;
			}
			if (l > 0 && !par1World.isBlockNormalCube(par2, l + 1, par4)) {
				Entity entity = ItemMonsterPlacer.spawnCreature(par1World, 57,
						(double) par2 + 0.5D, (double) l + 1.1D,
						(double) par4 + 0.5D);
				if (entity != null) {
					entity.timeUntilPortal = entity.getPortalCooldown();
				}
			}
		}
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {
		return null;
	}

	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess,
			int par2, int par3, int par4) {
		float f;
		float f1;
		if (par1IBlockAccess.getBlockId(par2 - 1, par3, par4) != this.blockID
				&& par1IBlockAccess.getBlockId(par2 + 1, par3, par4) != this.blockID) {
			f = 0.125F;
			f1 = 0.5F;
			this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F,
					0.5F + f1);
		} else {
			f = 0.5F;
			f1 = 0.125F;
			this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F,
					0.5F + f1);
		}
	}

	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean tryToCreatePortal(World par1World, int par2, int par3,
			int par4) {
		byte b0 = 0;
		byte b1 = 0;
		if (par1World.getBlockId(par2 - 1, par3, par4) == MysticBlocks.YirawiaBlock.blockID
				|| par1World.getBlockId(par2 + 1, par3, par4) == MysticBlocks.YirawiaBlock.blockID) {
			b0 = 1;
		}
		if (par1World.getBlockId(par2, par3, par4 - 1) == MysticBlocks.YirawiaBlock.blockID
				|| par1World.getBlockId(par2, par3, par4 + 1) == MysticBlocks.YirawiaBlock.blockID) {
			b1 = 1;
		}
		if (b0 == b1) {
			return false;
		} else {
			if (par1World.getBlockId(par2 - b0, par3, par4 - b1) == 0) {
				par2 -= b0;
				par4 -= b1;
			}
			int l;
			int i1;
			for (l = -1; l <= 2; ++l) {
				for (i1 = -1; i1 <= 3; ++i1) {
					boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;
					if (l != -1 && l != 2 || i1 != -1 && i1 != 3) {
						int j1 = par1World.getBlockId(par2 + b0 * l, par3 + i1,
								par4 + b1 * l);
						if (flag) {
							if (j1 != MysticBlocks.YirawiaBlock.blockID) {
								return false;
							}
						} else if (j1 != 0 && j1 != MysticBlocks.ViriaFire.blockID) {
							return false;
						}
					}
				}
			}
			for (l = 0; l < 2; ++l) {
				for (i1 = 0; i1 < 3; ++i1) {
					par1World.setBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l,
							MysticBlocks.YirawiaPortal.blockID, 0, 2);
				}
			}
			return true;
		}
	}

	public void onNeighborBlockChange(World par1World, int par2, int par3,
			int par4, int par5) {
		byte b0 = 0;
		byte b1 = 1;
		if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID
				|| par1World.getBlockId(par2 + 1, par3, par4) == this.blockID) {
			b0 = 1;
			b1 = 0;
		}
		int i1;
		for (i1 = par3; par1World.getBlockId(par2, i1 - 1, par4) == this.blockID; --i1) {
			;
		}
		if (par1World.getBlockId(par2, i1 - 1, par4) != MysticBlocks.YirawiaBlock.blockID) {
			par1World.setBlockToAir(par2, par3, par4);
		} else {
			int j1;
			for (j1 = 1; j1 < 4
					&& par1World.getBlockId(par2, i1 + j1, par4) == this.blockID; ++j1) {
				;
			}
			if (j1 == 3
					&& par1World.getBlockId(par2, i1 + j1, par4) == MysticBlocks.YirawiaBlock.blockID) {
				boolean flag = par1World.getBlockId(par2 - 1, par3, par4) == this.blockID
						|| par1World.getBlockId(par2 + 1, par3, par4) == this.blockID;
				boolean flag1 = par1World.getBlockId(par2, par3, par4 - 1) == this.blockID
						|| par1World.getBlockId(par2, par3, par4 + 1) == this.blockID;
				if (flag && flag1) {
					par1World.setBlockToAir(par2, par3, par4);
				} else {
					if ((par1World.getBlockId(par2 + b0, par3, par4 + b1) != MysticBlocks.YirawiaBlock.blockID || par1World
							.getBlockId(par2 - b0, par3, par4 - b1) != this.blockID)
							&& (par1World
									.getBlockId(par2 - b0, par3, par4 - b1) != MysticBlocks.YirawiaBlock.blockID || par1World
									.getBlockId(par2 + b0, par3, par4 + b1) != this.blockID)) {
						par1World.setBlockToAir(par2, par3, par4);
					}
				}
			} else {
				par1World.setBlockToAir(par2, par3, par4);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess,
			int par2, int par3, int par4, int par5) {
		if (par1IBlockAccess.getBlockId(par2, par3, par4) == this.blockID) {
			return false;
		} else {
			boolean flag = par1IBlockAccess.getBlockId(par2 - 1, par3, par4) == this.blockID
					&& par1IBlockAccess.getBlockId(par2 - 2, par3, par4) != this.blockID;
			boolean flag1 = par1IBlockAccess.getBlockId(par2 + 1, par3, par4) == this.blockID
					&& par1IBlockAccess.getBlockId(par2 + 2, par3, par4) != this.blockID;
			boolean flag2 = par1IBlockAccess.getBlockId(par2, par3, par4 - 1) == this.blockID
					&& par1IBlockAccess.getBlockId(par2, par3, par4 - 2) != this.blockID;
			boolean flag3 = par1IBlockAccess.getBlockId(par2, par3, par4 + 1) == this.blockID
					&& par1IBlockAccess.getBlockId(par2, par3, par4 + 2) != this.blockID;
			boolean flag4 = flag || flag1;
			boolean flag5 = flag2 || flag3;
			return flag4 && par5 == 4 ? true : (flag4 && par5 == 5 ? true
					: (flag5 && par5 == 2 ? true : flag5 && par5 == 3));
		}
	}

	public int quantityDropped(Random par1Random) {
		return 0;
	}

	public void onEntityCollidedWithBlock(World par1World, int par2, int par3,
			int par4, Entity par5Entity) {
		if ((par5Entity.ridingEntity == null)
				&& (par5Entity.riddenByEntity == null)
				&& ((par5Entity instanceof EntityPlayerMP))) {
			EntityPlayerMP thePlayer = (EntityPlayerMP) par5Entity;
			if (thePlayer.timeUntilPortal > 0) {
				thePlayer.timeUntilPortal = 10;
			} else if (thePlayer.dimension != MysticDimensions.YirawiaID) {
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager()
						.transferPlayerToDimension(
								thePlayer,
								MysticDimensions.YirawiaID,
								new YirawiaTeleporter(thePlayer.mcServer
										.worldServerForDimension(MysticDimensions.YirawiaID)));
			} else {
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager()
						.transferPlayerToDimension(
								thePlayer,
								0,
								new YirawiaTeleporter(thePlayer.mcServer
										.worldServerForDimension(0)));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World par1World, int par2, int par3,
			int par4, Random par5Random) {
		if (par5Random.nextInt(100) == 0) {
			par1World.playSound((double) par2 + 0.5D, (double) par3 + 0.5D,
					(double) par4 + 0.5D, "portal.portal", 0.5F,
					par5Random.nextFloat() * 0.4F + 0.8F, false);
		}
		for (int l = 0; l < 4; ++l) {
			double d0 = (double) ((float) par2 + par5Random.nextFloat());
			double d1 = (double) ((float) par3 + par5Random.nextFloat());
			double d2 = (double) ((float) par4 + par5Random.nextFloat());
			double d3 = 0.0D;
			double d4 = 0.0D;
			double d5 = 0.0D;
			int i1 = par5Random.nextInt(2) * 2 - 1;
			d3 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
			d4 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
			d5 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
			if (par1World.getBlockId(par2 - 1, par3, par4) != this.blockID
					&& par1World.getBlockId(par2 + 1, par3, par4) != this.blockID) {
				d0 = (double) par2 + 0.5D + 0.25D * (double) i1;
				d3 = (double) (par5Random.nextFloat() * 2.0F * (float) i1);
			} else {
				d2 = (double) par4 + 0.5D + 0.25D * (double) i1;
				d5 = (double) (par5Random.nextFloat() * 2.0F * (float) i1);
			}
			par1World.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
		}
	}

	@SideOnly(Side.CLIENT)
	public int idPicked(World par1World, int par2, int par3, int par4) {
		return 0;
	}
}