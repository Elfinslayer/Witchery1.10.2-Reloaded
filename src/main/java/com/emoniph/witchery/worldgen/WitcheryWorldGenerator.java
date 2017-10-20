package com.emoniph.witchery.worldgen;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.util.Config;
import cpw.mods.fml.common.IWorldGenerator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;


public class WitcheryWorldGenerator
  implements IWorldGenerator
{
  private LinkedList<ChunkCoordIntPair> structuresList = new LinkedList();
  private final WorldHandlerCoven covenGen;
  private final WorldHandlerWickerMan wickerManGen;
  private final WorldHandlerShack shackGen;
  private final List<IWorldGenHandler> generators;
  private int midX;
  private int midZ;
  int field_82665_g;
  int field_82666_h = 8;
  
  public WitcheryWorldGenerator() {
    covenGen = new WorldHandlerCoven(1.0D, 400);
    wickerManGen = new WorldHandlerWickerMan(1.0D, 400);
    shackGen = new WorldHandlerShack(1.0D, 400);
    
    IWorldGenHandler goblinHut = new WorldHandlerClonedStructure(ComponentGoblinHut.class, 1.0D, 400, 7, 7, 7);
    generators = Arrays.asList(new IWorldGenHandler[] { wickerManGen, covenGen, shackGen, goblinHut });
    
    field_82665_g = (8 + Math.max(Math.min(instanceworldGenFrequency, 64), 1));
    
    midX = 0;
    midZ = 0;
    for (IWorldGenHandler gen : generators) {
      midX = Math.max(midX, gen.getExtentX() / 2);
      midZ = Math.max(midZ, gen.getExtentZ() / 2);
    }
  }
  

  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
  {
    if (field_73011_w.field_76574_g == 0) {
      generateOverworld(world, field_73012_v, chunkX * 16, chunkZ * 16);
    } else if ((instanceworldGenTwilightForest) && (field_73011_w.func_80007_l().equals("Twilight Forest"))) {
      generateOverworld(world, field_73012_v, chunkX * 16, chunkZ * 16);
    } else if (field_73011_w.field_76574_g == instancedimensionDreamID) {
      generateDreamworld(world, field_73012_v, chunkX * 16, chunkZ * 16);
    }
  }
  
  private void generateDreamworld(World world, Random random, int chunkX, int chunkZ) {
    int flowerPerChunk = 1;
    BiomeGenBase biome = world.func_72807_a(chunkX, chunkZ);
    if (((!BiomeManager.DISALLOWED_BIOMES.contains(Integer.valueOf(field_76756_M))) || (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.JUNGLE))) && (random.nextInt(4) != 0)) {
      for (int j = 0; j < 1; j++)
      {
        int k = chunkX + random.nextInt(16) + 8;
        

        int i1 = chunkZ + random.nextInt(16) + 8;
        int l = random.nextInt(world.func_72976_f(k, i1) + 32);
        new WorldGenFlowers(BlocksWISPY_COTTON).func_76484_a(world, random, k, l, i1);
      }
    }
  }
  
  private void generateOverworld(World world, Random random, int x, int z) {
    boolean gen = false;
    if (!BiomeManager.DISALLOWED_BIOMES.contains(Integer.valueOf(func_72807_amidX, z + midZ).field_76756_M))) {
      Collections.shuffle(generators, random);
      for (IWorldGenHandler generator : generators) {
        if ((nonInRange(world, x, z, generator.getRange())) && (generator.generate(world, random, x, z))) {
          structuresList.add(new ChunkCoordIntPair(x, z));
          gen = true;
          break;
        }
      }
    }
  }
  




  protected boolean nonInRange(World worldObj, int x, int z, int range)
  {
    int par1 = x / 16;
    int par2 = z / 16;
    
    int k = par1;
    int l = par2;
    
    if (par1 < 0)
    {
      par1 -= field_82665_g - 1;
    }
    
    if (par2 < 0)
    {
      par2 -= field_82665_g - 1;
    }
    
    int i1 = par1 / field_82665_g;
    int j1 = par2 / field_82665_g;
    Random random = worldObj.func_72843_D(i1, j1, 10387312);
    i1 *= field_82665_g;
    j1 *= field_82665_g;
    i1 += random.nextInt(field_82665_g - field_82666_h);
    j1 += random.nextInt(field_82665_g - field_82666_h);
    
    return (k == i1) && (l == j1);
  }
  









  public void initiate()
  {
    structuresList.clear();
  }
}
