package com.emoniph.witchery.util;

import net.minecraft.world.World;

public class CircleUtil
{
  private CircleUtil() {}
  
  public static boolean isSmallCircle(World world, int x, int y, int z, net.minecraft.block.Block block) {
    int[][] circle = { { x, z - 2 }, { x + 1, z - 2 }, { x + 2, z - 1 }, { x + 2, z }, { x + 2, z + 1 }, { x + 1, z + 2 }, { x, z + 2 }, { x - 1, z + 2 }, { x - 2, z + 1 }, { x - 2, z }, { x - 2, z - 1 }, { x - 1, z - 2 } };
    











    for (int[] coord : circle) {
      if (world.func_147439_a(coord[0], y, coord[1]) != block) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean isMediumCircle(World world, int x, int y, int z, net.minecraft.block.Block block) {
    int[][] circle = { { x, z - 4 }, { x + 1, z - 4 }, { x + 2, z - 4 }, { x + 3, z - 3 }, { x + 4, z - 2 }, { x + 4, z - 1 }, { x + 4, z }, { x + 4, z + 1 }, { x + 4, z + 2 }, { x + 3, z + 3 }, { x + 2, z + 4 }, { x + 1, z + 4 }, { x, z + 4 }, { x - 1, z + 4 }, { x - 2, z + 4 }, { x - 3, z + 3 }, { x - 4, z + 2 }, { x - 4, z + 1 }, { x - 4, z }, { x - 4, z - 1 }, { x - 4, z - 2 }, { x - 3, z - 3 }, { x - 2, z - 4 }, { x - 1, z - 4 } };
    

























    for (int[] coord : circle) {
      if (world.func_147439_a(coord[0], y, coord[1]) != block) {
        return false;
      }
    }
    return true;
  }
}
