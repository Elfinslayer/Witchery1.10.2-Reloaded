package com.emoniph.witchery.util;

import net.minecraft.world.World;

public abstract class BlockActionReplaceSphere
{
  public BlockActionReplaceSphere() {}
  
  protected abstract boolean onShouldReplace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, net.minecraft.block.Block paramBlock);
  
  protected abstract void onReplaceBlock(World paramWorld, int paramInt1, int paramInt2, int paramInt3, net.minecraft.block.Block paramBlock);
  
  protected void onComplete() {}
  
  public void replaceBlocks(World world, int x0, int y0, int z0, int radius)
  {
    replaceBlocks(world, x0, y0, z0, x0, y0, z0, radius);
    onComplete();
  }
  
  private void replaceBlocks(World world, int x, int y, int z, int x0, int y0, int z0, int range) {
    double rangeSq = range * range;
    if ((x0 - x) * (x0 - x) + (y0 - y) * (y0 - y) + (z0 - z) * (z0 - z) >= rangeSq) {
      return;
    }
    if (replaceBlock(world, x + 1, y, z)) {
      replaceBlocks(world, x + 1, y, z, x0, y0, z0, range);
    }
    
    if (replaceBlock(world, x - 1, y, z)) {
      replaceBlocks(world, x - 1, y, z, x0, y0, z0, range);
    }
    
    if (replaceBlock(world, x, y, z + 1)) {
      replaceBlocks(world, x, y, z + 1, x0, y0, z0, range);
    }
    
    if (replaceBlock(world, x, y, z - 1)) {
      replaceBlocks(world, x, y, z - 1, x0, y0, z0, range);
    }
    
    if (replaceBlock(world, x, y + 1, z)) {
      replaceBlocks(world, x, y + 1, z, x0, y0, z0, range);
    }
    
    if (replaceBlock(world, x, y - 1, z)) {
      replaceBlocks(world, x, y - 1, z, x0, y0, z0, range);
    }
  }
  
  private boolean replaceBlock(World world, int x, int y, int z) {
    net.minecraft.block.Block block = world.func_147439_a(x, y, z);
    if (onShouldReplace(world, x, y, z, block)) {
      onReplaceBlock(world, x, y, z, block);
      return true;
    }
    return false;
  }
}
