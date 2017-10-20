package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;

public abstract interface IHandleHarvestDrops
{
  public abstract PotionBase getPotion();
  
  public abstract void onHarvestDrops(World paramWorld, EntityPlayer paramEntityPlayer, BlockEvent.HarvestDropsEvent paramHarvestDropsEvent, int paramInt);
}
