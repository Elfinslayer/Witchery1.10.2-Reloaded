package com.emoniph.witchery.common;

import net.minecraft.world.World;

public abstract interface INullSource
{
  public abstract World getWorld();
  
  public abstract int getPosX();
  
  public abstract int getPosY();
  
  public abstract int getPosZ();
  
  public abstract float getRange();
  
  public abstract boolean isPowerInvalid();
}
