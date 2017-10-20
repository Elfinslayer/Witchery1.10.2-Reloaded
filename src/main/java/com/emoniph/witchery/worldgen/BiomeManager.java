package com.emoniph.witchery.worldgen;

import com.emoniph.witchery.util.Log;
import java.util.ArrayList;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;




public class BiomeManager
{
  private BiomeManager() {}
  
  public static final ArrayList DISALLOWED_BIOMES = new ArrayList();
  
  public static void addModBiomes() {
    DISALLOWED_BIOMES.clear();
    
    ArrayList list = new ArrayList();
    
    for (BiomeGenBase biome : BiomeDictionary.getBiomesForType(BiomeDictionary.Type.SWAMP)) {
      if (!list.contains(Integer.valueOf(field_76756_M))) {
        list.add(Integer.valueOf(field_76756_M));
      }
    }
    for (BiomeGenBase biome : BiomeDictionary.getBiomesForType(BiomeDictionary.Type.RIVER)) {
      if (!list.contains(Integer.valueOf(field_76756_M))) {
        list.add(Integer.valueOf(field_76756_M));
      }
    }
    for (BiomeGenBase biome : BiomeDictionary.getBiomesForType(BiomeDictionary.Type.OCEAN)) {
      if (!list.contains(Integer.valueOf(field_76756_M))) {
        list.add(Integer.valueOf(field_76756_M));
      }
    }
    for (BiomeGenBase biome : BiomeDictionary.getBiomesForType(BiomeDictionary.Type.MOUNTAIN)) {
      if (!list.contains(Integer.valueOf(field_76756_M))) {
        list.add(Integer.valueOf(field_76756_M));
      }
    }
    for (BiomeGenBase biome : BiomeDictionary.getBiomesForType(BiomeDictionary.Type.JUNGLE)) {
      if (!list.contains(Integer.valueOf(field_76756_M))) {
        list.add(Integer.valueOf(field_76756_M));
      }
    }
    for (BiomeGenBase biome : BiomeDictionary.getBiomesForType(BiomeDictionary.Type.BEACH)) {
      if (!list.contains(Integer.valueOf(field_76756_M))) {
        list.add(Integer.valueOf(field_76756_M));
      }
    }
    Log.instance().debug("Found " + list.size() + " biomes to ignore for world gen.");
    if (list.size() > 0) {
      DISALLOWED_BIOMES.addAll(list);
    }
  }
  
  public static BiomeGenBase[] biomesWithout(BiomeDictionary.Type... biomesWithout) {
    ArrayList<BiomeGenBase> biomes = new ArrayList();
    for (BiomeGenBase biome : BiomeGenBase.func_150565_n()) {
      if (biome != null) {
        boolean skip = false;
        for (int i = 0; i < biomesWithout.length; i++) {
          if (BiomeDictionary.isBiomeOfType(biome, biomesWithout[i])) {
            skip = true;
            break;
          }
        }
        if (!skip) {
          biomes.add(biome);
        }
      }
    }
    return (BiomeGenBase[])biomes.toArray(new BiomeGenBase[biomes.size()]);
  }
}
