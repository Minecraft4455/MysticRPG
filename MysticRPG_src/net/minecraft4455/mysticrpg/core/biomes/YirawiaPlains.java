package net.minecraft4455.mysticrpg.core.biomes;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft4455.mysticrpg.core.blocks.MysticBlocks;

public class YirawiaPlains extends BiomeGenBase {

    public YirawiaPlains(int id) {

        super(id);
        topBlock = (byte) MysticBlocks.YirawiaGrass.blockID;
        fillerBlock = (byte) MysticBlocks.YirawiaDirt.blockID;
        spawnableCaveCreatureList.clear();
        spawnableCreatureList.clear();
        spawnableMonsterList.clear();
        spawnableWaterCreatureList.clear();
        this.setBiomeName("Yirawia Plains");

    }

}
