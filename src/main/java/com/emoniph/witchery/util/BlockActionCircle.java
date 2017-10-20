package com.emoniph.witchery.util;

import net.minecraft.world.World;

public abstract class BlockActionCircle {
  public BlockActionCircle() {}
  
  public abstract void onBlock(World paramWorld, int paramInt1, int paramInt2, int paramInt3);
  
  public void onComplete() {}
  
  public void processHollowCircle(World world, int x0, int y0, int z0, int radius) { if (radius == 1) {
      drawPixel(world, x0, z0, y0);
    } else {
      radius--;
      int x = radius;
      int z = 0;
      int radiusError = 1 - x;
      
      while (x >= z) {
        drawPixel(world, x + x0, z + z0, y0);
        drawPixel(world, z + x0, x + z0, y0);
        drawPixel(world, -x + x0, z + z0, y0);
        drawPixel(world, -z + x0, x + z0, y0);
        drawPixel(world, -x + x0, -z + z0, y0);
        drawPixel(world, -z + x0, -x + z0, y0);
        drawPixel(world, x + x0, -z + z0, y0);
        drawPixel(world, z + x0, -x + z0, y0);
        
        z++;
        if (radiusError < 0) {
          radiusError += 2 * z + 1;
        } else {
          x--;
          radiusError += 2 * (z - x + 1);
        }
      }
    }
    
    onComplete();
  }
  
  public void processFilledCircle(World world, int x0, int y0, int z0, int radius) {
    if (radius == 1) {
      drawPixel(world, x0, z0, y0);
    } else {
      radius--;
      int x = radius;
      int z = 0;
      int radiusError = 1 - x;
      
      int obsidianMelted = 0;
      while (x >= z) {
        drawLine(world, -x + x0, x + x0, z + z0, y0);
        drawLine(world, -z + x0, z + x0, x + z0, y0);
        drawLine(world, -x + x0, x + x0, -z + z0, y0);
        drawLine(world, -z + x0, z + x0, -x + z0, y0);
        
        z++;
        
        if (radiusError < 0) {
          radiusError += 2 * z + 1;
        } else {
          x--;
          radiusError += 2 * (z - x + 1);
        }
      }
    }
    
    onComplete();
  }
  
  private void drawLine(World world, int x1, int x2, int z, int y) {
    for (int x = x1; x <= x2; x++) {
      drawPixel(world, x, z, y);
    }
  }
  
  private void drawPixel(World world, int x, int z, int y) {
    onBlock(world, x, y, z);
  }
}
