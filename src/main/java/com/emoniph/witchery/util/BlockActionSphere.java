package com.emoniph.witchery.util;

import com.emoniph.witchery.Witchery;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public abstract class BlockActionSphere
{
  public BlockActionSphere() {}
  
  protected abstract void onBlock(World paramWorld, int paramInt1, int paramInt2, int paramInt3);
  
  protected void onComplete() {}
  
  public void drawHollowSphere(World world, int x0, int y0, int z0, int radius)
  {
    if (radius == 1) {
      drawPixel(world, x0, z0, y0);
    } else {
      radius--;
      int x = radius;
      int y = 0;
      int radiusError = 1 - x;
      
      while (x >= y) {
        drawCircle(world, x0, y0, z0, y, x, radiusError);
        y++;
        if (radiusError < 0) {
          radiusError += 2 * y + 1;
        } else {
          x--;
          radiusError += 2 * (y - x + 1);
        }
      }
    }
    
    onComplete();
  }
  
  private boolean drawCircle(World world, int x0, int y0, int z0, int y1, int radius, int error0)
  {
    int x = radius;
    int z = 0;
    int radiusError = error0;
    
    while (x >= z)
    {
      drawPixel(world, x0 + x, z0 + z, y0 + y1);
      drawPixel(world, x0 - x, z0 + z, y0 + y1);
      drawPixel(world, x0 + x, z0 + z, y0 - y1);
      drawPixel(world, x0 - x, z0 + z, y0 - y1);
      drawPixel(world, x0 + x, z0 - z, y0 + y1);
      drawPixel(world, x0 - x, z0 - z, y0 + y1);
      drawPixel(world, x0 + x, z0 - z, y0 - y1);
      drawPixel(world, x0 - x, z0 - z, y0 - y1);
      
      drawPixel(world, x0 + z, z0 + x, y0 + y1);
      drawPixel(world, x0 - z, z0 + x, y0 + y1);
      drawPixel(world, x0 + z, z0 + x, y0 - y1);
      drawPixel(world, x0 - z, z0 + x, y0 - y1);
      drawPixel(world, x0 + z, z0 - x, y0 + y1);
      drawPixel(world, x0 - z, z0 - x, y0 + y1);
      drawPixel(world, x0 + z, z0 - x, y0 - y1);
      drawPixel(world, x0 - z, z0 - x, y0 - y1);
      
      drawPixel(world, x0 + y1, z0 + z, y0 + x);
      drawPixel(world, x0 - y1, z0 + z, y0 + x);
      drawPixel(world, x0 + y1, z0 + z, y0 - x);
      drawPixel(world, x0 - y1, z0 + z, y0 - x);
      drawPixel(world, x0 + y1, z0 - z, y0 + x);
      drawPixel(world, x0 - y1, z0 - z, y0 + x);
      drawPixel(world, x0 + y1, z0 - z, y0 - x);
      drawPixel(world, x0 - y1, z0 - z, y0 - x);
      
      drawPixel(world, x0 + z, z0 + y1, y0 + x);
      drawPixel(world, x0 - z, z0 + y1, y0 + x);
      drawPixel(world, x0 + z, z0 + y1, y0 - x);
      drawPixel(world, x0 - z, z0 + y1, y0 - x);
      drawPixel(world, x0 + z, z0 - y1, y0 + x);
      drawPixel(world, x0 - z, z0 - y1, y0 + x);
      drawPixel(world, x0 + z, z0 - y1, y0 - x);
      drawPixel(world, x0 - z, z0 - y1, y0 - x);
      
      z++;
      
      if (radiusError < 0) {
        radiusError += 2 * z + 1;
      } else {
        x--;
        radiusError += 2 * (z - x + 1);
      }
    }
    
    return true;
  }
  
  public void drawFilledSphere(World world, int x0, int y0, int z0, int radius) {
    if (radius == 1) {
      drawPixel(world, x0, z0, y0);
    } else {
      int radiusSq = radius * radius;
      for (int x = x0 - radius; x <= x0 + radius; x++) {
        for (int z = z0 - radius; z <= z0 + radius; z++) {
          for (int y = y0 - radius; y <= y0 + radius; y++) {
            if (Coord.distanceSq(x, y, z, x0, y0, z0) < radiusSq - 1) {
              drawPixel(world, x, z, y);
            }
          }
        }
      }
    }
    
    onComplete();
  }
  
  private void drawPixel(World world, int x, int z, int y) {
    onBlock(world, x, y, z);
  }
  
  protected void fillWith(World world, int posX, int posY, int posZ, int radius, Block fillBlock, Block edgeBlock)
  {
    fillHalfWithAirY(world, posX, posY, posZ, 1, radius, fillBlock, edgeBlock);
    fillHalfWithAirY(world, posX, posY - 1, posZ, -1, radius, fillBlock, edgeBlock);
  }
  
  private void fillHalfWithAirY(World world, int posX, int posY, int posZ, int dy, int radius, Block fillBlock, Block edgeBlock) {
    for (int y = 0; y <= radius; y++) {
      int realY = posY + y * dy;
      if (world.func_147439_a(posX, realY, posZ) == edgeBlock) {
        break;
      }
      fillSliceWithAir(world, posX, realY, posZ, radius, fillBlock, edgeBlock);
    }
  }
  
  private void fillSliceWithAir(World world, int posX, int posY, int posZ, int radius, Block fillBlock, Block edgeBlock) {
    fillHalfWithAirX(world, posX, posY, posZ, 1, radius, fillBlock, edgeBlock);
    fillHalfWithAirX(world, posX - 1, posY, posZ, -1, radius, fillBlock, edgeBlock);
  }
  
  private void fillHalfWithAirX(World world, int posX, int posY, int posZ, int dx, int radius, Block fillBlock, Block edgeBlock) {
    for (int x = 0; x <= radius; x++) {
      int realX = posX + x * dx;
      if (world.func_147439_a(realX, x, posZ) == edgeBlock) {
        break;
      }
      fillLineWithAir(world, realX, posY, posZ, radius, fillBlock, edgeBlock);
    }
  }
  
  private void fillLineWithAir(World world, int posX, int posY, int posZ, int radius, Block fillBlock, Block edgeBlock) {
    fillHalfWithAirZ(world, posX, posY, posZ, 1, radius, fillBlock, edgeBlock);
    fillHalfWithAirZ(world, posX, posY, posZ - 1, -1, radius, fillBlock, edgeBlock);
  }
  
  private void fillHalfWithAirZ(World world, int posX, int posY, int posZ, int dz, int radius, Block fillBlock, Block edgeBlock) {
    for (int z = 0; z <= radius; z++) {
      int realZ = posZ + z * dz;
      Block foundBlock = world.func_147439_a(posX, posY, realZ);
      if (foundBlock == edgeBlock) {
        break;
      }
      if ((foundBlock != fillBlock) && ((foundBlock == net.minecraft.init.Blocks.field_150355_j) || (foundBlock == net.minecraft.init.Blocks.field_150358_i) || (foundBlock == BlocksBREW_GAS) || (foundBlock == BlocksBREW_LIQUID))) {
        world.func_147449_b(posX, posY, realZ, fillBlock);
      }
    }
  }
}
