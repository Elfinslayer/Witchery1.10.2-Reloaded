package com.emoniph.witchery.integration;

import com.emoniph.witchery.blocks.BlockWitchSapling;
import com.emoniph.witchery.util.Log;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.FertilizerType;
import powercrystals.minefactoryreloaded.api.IFactoryFertilizable;

public class MFRFertilizable
  implements IFactoryFertilizable
{
  private Block block;
  private int stages;
  
  public MFRFertilizable(Block block, int stages)
  {
    this.block = block;
    this.stages = stages;
  }
  
  public Block getPlant()
  {
    return block;
  }
  
  public boolean canFertilize(World world, int x, int y, int z, FertilizerType fertilizerType)
  {
    return (fertilizerType == FertilizerType.GrowPlant) && ((stages == 0) || (world.func_72805_g(x, y, z) < stages));
  }
  
  public boolean fertilize(World world, Random rand, int x, int y, int z, FertilizerType fertilizerType)
  {
    Block blockID = world.func_147439_a(x, y, z);
    Log.instance().debug(String.format("Fertilize %d, %d", new Object[] { blockID.func_149739_a(), Integer.valueOf(stages) }));
    if (stages > 0) {
      int meta = world.func_72805_g(x, y, z);
      if (meta < stages) {
        int output = meta + rand.nextInt(3) + 1;
        if (output > stages) {
          output = stages;
        }
        world.func_72921_c(x, y, z, output, 3);
        
        return true;
      }
    } else if ((block instanceof BlockWitchSapling)) {
      ((BlockWitchSapling)block);BlockWitchSapling.growTree(world, x, y, z, field_73012_v);
      return world.func_147439_a(x, y, z) != block;
    }
    return false;
  }
}
