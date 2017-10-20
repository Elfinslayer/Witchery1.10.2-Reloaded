package com.emoniph.witchery.worldgen;

import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Log;
import java.util.Random;
import net.minecraft.world.World;


public class WorldHandlerShack
  implements IWorldGenHandler
{
  private final double chance;
  private final int range;
  
  public WorldHandlerShack(double chance, int range)
  {
    this.chance = chance;
    this.range = range;
  }
  
  public int getExtentX()
  {
    return 7;
  }
  
  public int getExtentZ()
  {
    return 7;
  }
  
  public int getRange()
  {
    return range;
  }
  
  public boolean generate(World world, Random random, int x, int z)
  {
    if ((instancegenerateShacks) && (random.nextDouble() < chance)) {
      int direction = random.nextInt(4);
      new ComponentShack(direction, random, x, z).addComponentParts(world, random);
      Log.instance().debug("shack " + x + " " + z + " dir=" + direction);
      return true;
    }
    return false;
  }
  
  public void initiate() {}
}
