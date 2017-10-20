package com.emoniph.witchery.util;

import net.minecraft.world.World;

public class EffectSpiral {
  private final ISpiralBlockAction action;
  
  public EffectSpiral(ISpiralBlockAction action) {
    this.action = action;
  }
  
  public void apply(World world, int midX, int midY, int midZ, int dimX, int dimZ)
  {
    action.onSpiralActionStart(world, midX, midY, midZ);
    
    int x = 0;
    int z = 0;
    int dx = 0;
    int dz = -1;
    int t = Math.max(dimX, dimZ);
    int maxI = t * t;
    for (int i = 0; i < maxI; i++) {
      if ((-dimX / 2 <= x) && (x <= dimX / 2) && (-dimZ / 2 <= z) && (z <= dimZ / 2) && 
        (!action.onSpiralBlockAction(world, midX + x, midY, midZ + z))) {
        break;
      }
      
      if ((x == z) || ((x < 0) && (x == -z)) || ((x > 0) && (x == 1 - z))) {
        t = dx;
        dx = -dz;
        dz = t;
      }
      x += dx;
      z += dz;
    }
    
    action.onSpiralActionStop(world, midX, midY, midZ);
  }
}
