package com.emoniph.witchery.worldgen;

import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Log;
import java.util.Random;
import net.minecraft.world.World;


public class WorldHandlerCoven
  implements IWorldGenHandler
{
  private final double chance;
  private final int range;
  
  public WorldHandlerCoven(double chance, int range)
  {
    this.chance = chance;
    this.range = range;
  }
  
  public int getExtentX()
  {
    return 11;
  }
  
  public int getExtentZ()
  {
    return 11;
  }
  
  public int getRange()
  {
    return range;
  }
  
  public boolean generate(World world, Random random, int x, int z)
  {
    if ((instancegenerateCovens) && (random.nextDouble() < chance)) {
      int direction = random.nextInt(4);
      if (!new ComponentCoven(direction, random, x, z).addComponentParts(world, random)) {
        return false;
      }
      
      Log.instance().debug("coven " + x + " " + z + " dir=" + direction);
      
      return true;
    }
    return false;
  }
  
  public void initiate() {}
}
