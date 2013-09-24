package net.minecraft4455.mysticrpg.core.biomes;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft4455.mysticrpg.core.blocks.MysticBlocks;

public class YirawiaDesert extends BiomeGenBase {

    public YirawiaDesert(int id) {

        super(id);
        this.setBiomeName("YirawiaDesert");
        this.setMinMaxHeight(0.1F, 0.2F);
        this.setDisableRain();
        topBlock = (byte) MysticBlocks.MysticSand.blockID;
        fillerBlock = (byte) MysticBlocks.MysticSand.blockID;
        spawnableCaveCreatureList.clear();
        spawnableCreatureList.clear();
        spawnableMonsterList.clear();
        spawnableWaterCreatureList.clear();

    }

}
