package net.minecraft4455.mysticrpg.core.biomes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.world.biome.BiomeGenBase;

public class MysticBiomes {

	public static BiomeGenBase YirawiaPlains;
	
	public static void init() {
		
		YirawiaPlains = new YirawiaPlains(40);
		GameRegistry.removeBiome(YirawiaPlains);
		
	}
	
}
