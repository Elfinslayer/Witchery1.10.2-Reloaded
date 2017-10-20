package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;

public abstract interface IHandlePlayerDrops
{
  public abstract PotionBase getPotion();
  
  public abstract void onPlayerDrops(World paramWorld, EntityPlayer paramEntityPlayer, PlayerDropsEvent paramPlayerDropsEvent, int paramInt);
}
