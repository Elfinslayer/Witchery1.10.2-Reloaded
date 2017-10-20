package com.emoniph.witchery.worldgen;

import com.emoniph.witchery.util.Config;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import net.minecraft.world.World;

public class WorldHandlerClonedStructure
  implements IWorldGenHandler
{
  private final double chance;
  private final int range;
  private final int width;
  private final int height;
  private final int depth;
  Class<? extends ComponentClonedStructure> clazz;
  
  public WorldHandlerClonedStructure(Class<? extends ComponentClonedStructure> clazz, double chance, int range, int width, int height, int depth)
  {
    this.clazz = clazz;
    this.chance = chance;
    this.range = range;
    this.width = width;
    this.height = height;
    this.depth = depth;
  }
  
  public int getExtentX()
  {
    return width;
  }
  
  public int getExtentZ()
  {
    return depth;
  }
  
  public int getRange()
  {
    return range;
  }
  
  public boolean generate(World world, Random random, int x, int z)
  {
    if ((instancegenerateGoblinHuts) && (random.nextDouble() < chance)) {
      int direction = random.nextInt(4);
      try {
        Constructor ctor = clazz.getConstructor(new Class[] { Integer.TYPE, Random.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE });
        ComponentClonedStructure component = (ComponentClonedStructure)ctor.newInstance(new Object[] { Integer.valueOf(direction), random, Integer.valueOf(x), Integer.valueOf(z), Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(depth) });
        component.addComponentParts(world, random);
      }
      catch (NoSuchMethodException ex) {}catch (InvocationTargetException ex) {}catch (InstantiationException ex) {}catch (IllegalAccessException ex) {}
      


      return true;
    }
    return false;
  }
  
  public void initiate() {}
}
