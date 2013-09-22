package net.minecraft4455.mysticrpg.core.biomes;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft4455.mysticrpg.core.blocks.MysticBlocks;

public class YirawiaPlains extends BiomeGenBase {

	public YirawiaPlains(int id) {
		
		super(id);
		this.topBlock = (byte) MysticBlocks.YirawiaGrass.blockID;
		this.fillerBlock = (byte) MysticBlocks.YirawiaDirt.blockID;
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.setBiomeName("Yirawia Plains");
		
	}
	
}
