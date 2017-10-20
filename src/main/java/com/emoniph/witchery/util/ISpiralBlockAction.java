package com.emoniph.witchery.util;

import net.minecraft.world.World;

public abstract interface ISpiralBlockAction
{
  public abstract void onSpiralActionStart(World paramWorld, int paramInt1, int paramInt2, int paramInt3);
  
  public abstract boolean onSpiralBlockAction(World paramWorld, int paramInt1, int paramInt2, int paramInt3);
  
  public abstract void onSpiralActionStop(World paramWorld, int paramInt1, int paramInt2, int paramInt3);
}
