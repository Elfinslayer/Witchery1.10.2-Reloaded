package com.emoniph.witchery.worldgen;

import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Log;
import java.util.Random;
import net.minecraft.world.World;


public class WorldHandlerWickerMan
  implements IWorldGenHandler
{
  private final double chance;
  private final int range;
  
  public WorldHandlerWickerMan(double chance, int range)
  {
    this.chance = chance;
    this.range = range;
  }
  
  public int getExtentX()
  {
    return 6;
  }
  
  public int getExtentZ()
  {
    return 5;
  }
  
  public int getRange()
  {
    return range;
  }
  
  public boolean generate(World world, Random random, int x, int z)
  {
    int direction = random.nextInt(4);
    if ((instancegenerateWickerMen) && (random.nextDouble() < chance)) {
      new ComponentWickerMan(direction, random, x, z).addComponentParts(world, random);
      Log.instance().debug("wickerman " + x + " " + z + " dir=" + direction);
      return true;
    }
    return false;
  }
  
  public void initiate() {}
}
