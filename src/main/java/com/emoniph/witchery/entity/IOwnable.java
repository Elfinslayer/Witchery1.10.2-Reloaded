package com.emoniph.witchery.entity;

import net.minecraft.entity.player.EntityPlayer;

public abstract interface IOwnable
{
  public abstract String getOwnerName();
  
  public abstract void setOwner(String paramString);
  
  public abstract EntityPlayer getOwnerEntity();
}
