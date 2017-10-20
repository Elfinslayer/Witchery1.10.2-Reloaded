package com.emoniph.witchery.common;

import com.emoniph.witchery.util.Coord;
import net.minecraft.world.World;

public abstract interface IPowerSource
{
  public abstract World getWorld();
  
  public abstract Coord getLocation();
  
  public abstract boolean isLocationEqual(Coord paramCoord);
  
  public abstract boolean consumePower(float paramFloat);
  
  public abstract float getCurrentPower();
  
  public abstract float getRange();
  
  public abstract int getEnhancementLevel();
  
  public abstract boolean isPowerInvalid();
}
