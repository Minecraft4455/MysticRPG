package net.minecraft4455.mysticrpg.core.dimensionregistry.chunkprovider;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.MapGenScatteredFeature;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft4455.mysticrpg.core.blocks.MysticBlocks;

public class ChunkProviderYirawia implements IChunkProvider {

    private Random rand;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorOctaves noiseGen4;
    public NoiseGeneratorOctaves noiseGen5;
    public NoiseGeneratorOctaves noiseGen6;
    public NoiseGeneratorOctaves mobSpawnerNoise;
    private World worldObj;
    private final boolean mapFeaturesEnabled;
    private double[] noiseArray;
    private double[] stoneNoise = new double[256];
    private MapGenBase caveGenerator = new MapGenCaves();
    private MapGenStronghold strongholdGenerator = new MapGenStronghold();
    private MapGenVillage villageGenerator = new MapGenVillage();
    private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
    private MapGenScatteredFeature scatteredFeatureGenerator = new MapGenScatteredFeature();
    private MapGenBase ravineGenerator = new MapGenRavine();
    private BiomeGenBase[] biomesForGeneration;
    double[] noise3;
    double[] noise1;
    double[] noise2;
    double[] noise5;
    double[] noise6;
    float[] parabolicField;
    int[][] field_73219_j = new int[32][32];

    public ChunkProviderYirawia(World par1World, long par2, boolean par4) {
        worldObj = par1World;
        mapFeaturesEnabled = par4;
        rand = new Random(par2);
        noiseGen1 = new NoiseGeneratorOctaves(rand, 16);
        noiseGen2 = new NoiseGeneratorOctaves(rand, 16);
        noiseGen3 = new NoiseGeneratorOctaves(rand, 8);
        noiseGen4 = new NoiseGeneratorOctaves(rand, 4);
        noiseGen5 = new NoiseGeneratorOctaves(rand, 10);
        noiseGen6 = new NoiseGeneratorOctaves(rand, 16);
        mobSpawnerNoise = new NoiseGeneratorOctaves(rand, 8);
    }

    public void generateTerrain(int par1, int par2, byte[] par3ArrayOfByte) {
        byte b0 = 4;
        byte b1 = 16;
        byte b2 = 63;
        int k = b0 + 1;
        byte b3 = 17;
        int l = b0 + 1;
        biomesForGeneration = worldObj.getWorldChunkManager()
                .getBiomesForGeneration(biomesForGeneration, par1 * 4 - 2,
                        par2 * 4 - 2, k + 5, l + 5);
        noiseArray = this.initializeNoiseField(noiseArray, par1 * b0, 0, par2
                * b0, k, b3, l);
        for (int i1 = 0; i1 < b0; ++i1) {
            for (int j1 = 0; j1 < b0; ++j1) {
                for (int k1 = 0; k1 < b1; ++k1) {
                    double d0 = 0.125D;
                    double d1 = noiseArray[((i1 + 0) * l + j1 + 0) * b3 + k1
                            + 0];
                    double d2 = noiseArray[((i1 + 0) * l + j1 + 1) * b3 + k1
                            + 0];
                    double d3 = noiseArray[((i1 + 1) * l + j1 + 0) * b3 + k1
                            + 0];
                    double d4 = noiseArray[((i1 + 1) * l + j1 + 1) * b3 + k1
                            + 0];
                    double d5 = (noiseArray[((i1 + 0) * l + j1 + 0) * b3 + k1
                            + 1] - d1)
                            * d0;
                    double d6 = (noiseArray[((i1 + 0) * l + j1 + 1) * b3 + k1
                            + 1] - d2)
                            * d0;
                    double d7 = (noiseArray[((i1 + 1) * l + j1 + 0) * b3 + k1
                            + 1] - d3)
                            * d0;
                    double d8 = (noiseArray[((i1 + 1) * l + j1 + 1) * b3 + k1
                            + 1] - d4)
                            * d0;
                    for (int l1 = 0; l1 < 8; ++l1) {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;
                        for (int i2 = 0; i2 < 4; ++i2) {
                            int j2 = i2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k1
                                    * 8 + l1;
                            short short1 = 128;
                            j2 -= short1;
                            double d14 = 0.25D;
                            double d15 = (d11 - d10) * d14;
                            double d16 = d10 - d15;
                            for (int k2 = 0; k2 < 4; ++k2) {
                                if ((d16 += d15) > 0.0D) {
                                    par3ArrayOfByte[j2 += short1] = (byte) MysticBlocks.MysticStone.blockID;
                                } else if (k1 * 8 + l1 < b2) {
                                    par3ArrayOfByte[j2 += short1] = (byte) Block.waterStill.blockID;
                                } else {
                                    par3ArrayOfByte[j2 += short1] = 0;
                                }
                            }
                            d10 += d12;
                            d11 += d13;
                        }
                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    public void replaceBlocksForBiome(int par1, int par2,
            byte[] par3ArrayOfByte, BiomeGenBase[] par4ArrayOfBiomeGenBase) {
        byte var5 = 63;
        double var6 = 0.03125D;
        stoneNoise = noiseGen4.generateNoiseOctaves(stoneNoise, par1 * 16,
                par2 * 16, 0, 16, 16, 1, var6 * 2.0D, var6 * 2.0D, var6 * 2.0D);
        for (int var8 = 0; var8 < 16; var8++) {
            for (int var9 = 0; var9 < 16; var9++) {
                BiomeGenBase var10 = par4ArrayOfBiomeGenBase[var9 + var8 * 16];
                float var11 = var10.getFloatTemperature();
                int var12 = (int) (stoneNoise[var8 + var9 * 16] / 3.0D + 3.0D + rand
                        .nextDouble() * 0.25D);
                int var13 = -1;
                byte var14 = var10.topBlock;
                byte var15 = var10.fillerBlock;
                for (int var16 = 127; var16 >= 0; var16--) {
                    int var17 = (var9 * 16 + var8) * 128 + var16;
                    if (var16 <= 0 + rand.nextInt(5)) {
                        par3ArrayOfByte[var17] = (byte) Block.bedrock.blockID;
                    } else {
                        byte var18 = par3ArrayOfByte[var17];
                        if (var18 == 0) {
                            var13 = -1;
                        }
                        /**
                         * Main filler block fill's all the underground,
                         * replaces block stone
                         **/
                        else if (var18 != MysticBlocks.MysticStone.blockID) {
                            if (var13 == -1) {
                                if (var12 == 0) {
                                    var14 = 0;

                                    /** change to custom dirt **/
                                    var15 = (byte) MysticBlocks.YirawiaDirt.blockID;//
                                } else if (var16 >= var5 - 4
                                        && var16 <= var5 + 1) {
                                    var14 = var10.topBlock;
                                    var15 = var10.fillerBlock;
                                }
                                if (var16 < var5 && var14 == 0) {
                                    if (var11 < 0.15F) {
                                        var14 = (byte) Block.ice.blockID;
                                    } else {
                                        var14 = (byte) Block.waterStill.blockID;
                                    }
                                }
                                var13 = var12;
                                if (var16 >= var5 - 1) {
                                    par3ArrayOfByte[var17] = var14;
                                } else {
                                    par3ArrayOfByte[var17] = var15;
                                }
                            } else if (var13 > 0) {
                                var13--;
                                par3ArrayOfByte[var17] = var15;
                                if (var13 == 0
                                        && var15 == MysticBlocks.MysticSand.blockID) {
                                    var13 = rand.nextInt(4);
                                    var15 = (byte) Block.sandStone.blockID;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public Chunk loadChunk(int par1, int par2) {
        return provideChunk(par1, par2);
    }

    @Override
    public Chunk provideChunk(int par1, int par2) {
        rand.setSeed(par1 * 341873128712L + par2 * 132897987541L);
        byte[] var3 = new byte[32768];
        generateTerrain(par1, par2, var3);
        biomesForGeneration = worldObj.getWorldChunkManager()
                .loadBlockGeneratorData(biomesForGeneration, par1 * 16,
                        par2 * 16, 16, 16);
        replaceBlocksForBiome(par1, par2, var3, biomesForGeneration);
        caveGenerator.generate(this, worldObj, par1, par2, var3);
        ravineGenerator.generate(this, worldObj, par1, par2, var3);
        if (mapFeaturesEnabled) {
            mineshaftGenerator.generate(this, worldObj, par1, par2, var3);
            villageGenerator.generate(this, worldObj, par1, par2, var3);
            strongholdGenerator.generate(this, worldObj, par1, par2, var3);
            scatteredFeatureGenerator
                    .generate(this, worldObj, par1, par2, var3);
        }
        Chunk var4 = new Chunk(worldObj, var3, par1, par2);
        byte[] var5 = var4.getBiomeArray();
        for (int var6 = 0; var6 < var5.length; var6++) {
            var5[var6] = (byte) biomesForGeneration[var6].biomeID;
        }
        var4.generateSkylightMap();
        return var4;
    }

    private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2,
            int par3, int par4, int par5, int par6, int par7) {
        if (par1ArrayOfDouble == null) {
            par1ArrayOfDouble = new double[par5 * par6 * par7];
        }
        if (parabolicField == null) {
            parabolicField = new float[25];
            for (int var8 = -2; var8 <= 2; var8++) {
                for (int var9 = -2; var9 <= 2; var9++) {
                    float var10 = 10.0F / MathHelper.sqrt_float(var8 * var8
                            + var9 * var9 + 0.2F);
                    parabolicField[var8 + 2 + (var9 + 2) * 5] = var10;
                }
            }
        }
        double var44 = 684.41200000000003D;
        double var45 = 684.41200000000003D;
        noise5 = noiseGen5.generateNoiseOctaves(noise5, par2, par4, par5, par7,
                1.121D, 1.121D, 0.5D);
        noise6 = noiseGen6.generateNoiseOctaves(noise6, par2, par4, par5, par7,
                200.0D, 200.0D, 0.5D);
        noise3 = noiseGen3.generateNoiseOctaves(noise3, par2, par3, par4, par5,
                par6, par7, var44 / 80.0D, var45 / 160.0D, var44 / 80.0D);
        noise1 = noiseGen1.generateNoiseOctaves(noise1, par2, par3, par4, par5,
                par6, par7, var44, var45, var44);
        noise2 = noiseGen2.generateNoiseOctaves(noise2, par2, par3, par4, par5,
                par6, par7, var44, var45, var44);
        int var12 = 0;
        int var13 = 0;
        for (int var14 = 0; var14 < par5; var14++) {
            for (int var15 = 0; var15 < par7; var15++) {
                float var16 = 0.0F;
                float var17 = 0.0F;
                float var18 = 0.0F;
                byte var19 = 2;
                BiomeGenBase var20 = biomesForGeneration[var14 + 2
                        + (var15 + 2) * (par5 + 5)];
                for (int var21 = -var19; var21 <= var19; var21++) {
                    for (int var22 = -var19; var22 <= var19; var22++) {
                        BiomeGenBase var23 = biomesForGeneration[var14 + var21
                                + 2 + (var15 + var22 + 2) * (par5 + 5)];
                        float var24 = parabolicField[var21 + 2 + (var22 + 2)
                                * 5]
                                / (var23.minHeight + 2.0F);
                        if (var23.minHeight > var20.minHeight) {
                            var24 /= 2.0F;
                        }
                        var16 += var23.maxHeight * var24;
                        var17 += var23.minHeight * var24;
                        var18 += var24;
                    }
                }
                var16 /= var18;
                var17 /= var18;
                var16 = var16 * 0.9F + 0.1F;
                var17 = (var17 * 4.0F - 1.0F) / 8.0F;
                double var47 = noise6[var13] / 8000.0D;
                if (var47 < 0.0D) {
                    var47 = -var47 * 0.3D;
                }
                var47 = var47 * 3.0D - 2.0D;
                if (var47 < 0.0D) {
                    var47 /= 2.0D;
                    if (var47 < -1.0D) {
                        var47 = -1.0D;
                    }
                    var47 /= 1.4D;
                    var47 /= 2.0D;
                } else {
                    if (var47 > 1.0D) {
                        var47 = 1.0D;
                    }
                    var47 /= 8.0D;
                }
                var13++;
                for (int var46 = 0; var46 < par6; var46++) {
                    double var48 = var17;
                    double var26 = var16;
                    var48 += var47 * 0.2D;
                    var48 = var48 * par6 / 16.0D;
                    double var28 = par6 / 2.0D + var48 * 4.0D;
                    double var30 = 0.0D;
                    double var32 = (var46 - var28) * 12.0D * 128.0D / 128.0D
                            / var26;
                    if (var32 < 0.0D) {
                        var32 *= 4.0D;
                    }
                    double var34 = noise1[var12] / 512.0D;
                    double var36 = noise2[var12] / 512.0D;
                    double var38 = (noise3[var12] / 10.0D + 1.0D) / 2.0D;
                    if (var38 < 0.0D) {
                        var30 = var34;
                    } else if (var38 > 1.0D) {
                        var30 = var36;
                    } else {
                        var30 = var34 + (var36 - var34) * var38;
                    }
                    var30 -= var32;
                    if (var46 > par6 - 4) {
                        double var40 = (var46 - (par6 - 4)) / 3.0F;
                        var30 = var30 * (1.0D - var40) + -10.0D * var40;
                    }
                    par1ArrayOfDouble[var12] = var30;
                    var12++;
                }
            }
        }
        return par1ArrayOfDouble;
    }

    @Override
    public boolean chunkExists(int par1, int par2) {
        return true;
    }

    @Override
    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
        net.minecraft.block.BlockSand.fallInstantly = true;
        int var4 = par2 * 16;
        int var5 = par3 * 16;
        BiomeGenBase var6 = worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
        rand.setSeed(worldObj.getSeed());
        long var7 = rand.nextLong() / 2L * 2L + 1L;
        long var9 = rand.nextLong() / 2L * 2L + 1L;
        rand.setSeed(par2 * var7 + par3 * var9 ^ worldObj.getSeed());
        boolean var11 = false;
        if (mapFeaturesEnabled) {
            mineshaftGenerator.generateStructuresInChunk(worldObj, rand, par2,
                    par3);
            var11 = villageGenerator.generateStructuresInChunk(worldObj, rand,
                    par2, par3);
            strongholdGenerator.generateStructuresInChunk(worldObj, rand, par2,
                    par3);
            scatteredFeatureGenerator.generateStructuresInChunk(worldObj, rand,
                    par2, par3);
        }
        if (!var11 && rand.nextInt(4) == 0) {
            int var12 = var4 + rand.nextInt(16) + 8;
            int var13 = rand.nextInt(128);
            int var14 = var5 + rand.nextInt(16) + 8;
            new WorldGenLakes(Block.waterStill.blockID).generate(worldObj,
                    rand, var12, var13, var14);
        }

        var6.decorate(worldObj, rand, var4, var5);
        SpawnerAnimals.performWorldGenSpawning(worldObj, var6, var4 + 8,
                var5 + 8, 16, 16, rand);
        var4 += 8;
        var5 += 8;
        net.minecraft.block.BlockSand.fallInstantly = false;
    }

    @Override
    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
        return true;
    }

    public boolean unload100OldestChunks() {
        return false;
    }

    @Override
    public boolean canSave() {
        return true;
    }

    @Override
    public String makeString() {
        return "RandomLevelSource";
    }

    @Override
    public List<?> getPossibleCreatures(EnumCreatureType par1EnumCreatureType,
            int par2, int par3, int par4) {
        BiomeGenBase var5 = worldObj.getBiomeGenForCoords(par2, par4);
        return var5 == null ? null : var5
                .getSpawnableList(par1EnumCreatureType);
    }

    @Override
    public ChunkPosition findClosestStructure(World par1World, String par2Str,
            int par3, int par4, int par5) {
        return "Stronghold".equals(par2Str) && strongholdGenerator != null ? strongholdGenerator
                .getNearestInstance(par1World, par3, par4, par5) : null;
    }

    @Override
    public int getLoadedChunkCount() {
        return 0;
    }

    @Override
    public boolean unloadQueuedChunks() {
        return false;
    }

    @Override
    public void recreateStructures(int i, int j) {
    }

    @Override
    public void saveExtraData() {

    }
}