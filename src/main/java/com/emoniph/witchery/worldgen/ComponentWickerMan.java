package com.emoniph.witchery.worldgen;

import com.emoniph.witchery.util.Config;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class ComponentWickerMan extends WitcheryComponent
{
  public static final int DIM_X = 6;
  public static final int DIM_Y = 8;
  public static final int DIM_Z = 5;
  
  public ComponentWickerMan() {}
  
  public ComponentWickerMan(int direction, Random random, int x, int z)
  {
    super(direction, random, x, z, 6, 8, 5);
  }
  
  public boolean addComponentParts(World world, Random random) {
    BiomeGenBase biom = world.func_72807_a(func_74865_a(0, 0), func_74873_b(0, 0));
    int groundAvg = calcGroundHeight(world, field_74887_e);
    
    if (groundAvg < 0) {
      return true;
    }
    
    field_74887_e.func_78886_a(0, groundAvg - field_74887_e.field_78894_e + 8 - 1, 0);
    


    Block wicker = Blocks.field_150407_cf;
    
    Block plant = Blocks.field_150328_O;
    Block groundID; Block undergroundID; if ((field_76756_M == field_76769_dfield_76756_M) || (field_76756_M == field_76786_sfield_76756_M) || (field_76756_M == field_76787_rfield_76756_M)) {
      Block groundID = Blocks.field_150354_m;
      Block undergroundID = Blocks.field_150354_m;
      plant = Blocks.field_150330_I;
    } else {
      groundID = Blocks.field_150349_c;
      undergroundID = Blocks.field_150346_d;
    }
    
    boolean flip = (field_74885_f == 0) || (field_74885_f == 2);
    

    func_74878_a(world, field_74887_e, 1, 1, 0, 4, 7, 4);
    func_74878_a(world, field_74887_e, 0, 1, 2, 5, 7, 2);
    
    func_151549_a(world, field_74887_e, 1, 0, 0, 4, 0, 4, groundID, groundID, false);
    func_151549_a(world, field_74887_e, 0, 0, 2, 5, 0, 2, groundID, groundID, false);
    







    int ground = 1;
    
    place(plant, 0, 1, ground, 0, field_74887_e, world);
    place(plant, 0, 4, ground, 0, field_74887_e, world);
    place(plant, 0, 0, ground, 2, field_74887_e, world);
    place(plant, 0, 5, ground, 2, field_74887_e, world);
    place(plant, 0, 4, ground, 4, field_74887_e, world);
    place(plant, 0, 1, ground, 4, field_74887_e, world);
    
    int horz = 0;
    
    int vert = flip ? 4 : 8;
    int flat = flip ? 8 : 4;
    
    int spawnables = instancestrawmanSpawnerRules.length;
    String spawnable = spawnables > 0 ? instancestrawmanSpawnerRules[field_73012_v.nextInt(spawnables)] : "Zombie";
    setSpawner(2, 0, 2, (spawnable != null) && (!spawnable.isEmpty()) ? spawnable : "Zombie", world);
    
    place(wicker, vert, 2, ground, 2, field_74887_e, world);
    place(wicker, vert, 3, ground, 2, field_74887_e, world);
    
    place(wicker, vert, 2, ground + 1, 2, field_74887_e, world);
    place(wicker, vert, 3, ground + 1, 2, field_74887_e, world);
    
    place(wicker, flat, 1, ground + 2, 2, field_74887_e, world);
    place(wicker, vert, 2, ground + 2, 2, field_74887_e, world);
    place(wicker, vert, 3, ground + 2, 2, field_74887_e, world);
    place(wicker, flat, 4, ground + 2, 2, field_74887_e, world);
    
    place(wicker, vert, 1, ground + 3, 2, field_74887_e, world);
    place(wicker, 0, 2, ground + 3, 2, field_74887_e, world);
    place(wicker, 0, 3, ground + 3, 2, field_74887_e, world);
    place(wicker, vert, 4, ground + 3, 2, field_74887_e, world);
    
    place(wicker, 0, 1, ground + 4, 2, field_74887_e, world);
    place(wicker, 0, 2, ground + 4, 2, field_74887_e, world);
    place(wicker, 0, 3, ground + 4, 2, field_74887_e, world);
    place(wicker, 0, 4, ground + 4, 2, field_74887_e, world);
    
    place(wicker, flat, 2, ground + 5, 2, field_74887_e, world);
    place(wicker, flat, 3, ground + 5, 2, field_74887_e, world);
    
    place(wicker, flat, 2, ground + 6, 2, field_74887_e, world);
    place(wicker, flat, 3, ground + 6, 2, field_74887_e, world);
    
    for (int x = 0; x < 6; x++) {
      for (int z = 0; z < 5; z++) {
        func_151554_b(world, undergroundID, 0, x, 0, z, field_74887_e);
        func_74871_b(world, x, 8, z, field_74887_e);
      }
    }
    
    return true;
  }
}
