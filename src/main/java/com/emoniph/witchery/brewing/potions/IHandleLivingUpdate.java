package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public abstract interface IHandleLivingUpdate
{
  public abstract PotionBase getPotion();
  
  public abstract void onLivingUpdate(World paramWorld, EntityLivingBase paramEntityLivingBase, LivingEvent.LivingUpdateEvent paramLivingUpdateEvent, int paramInt1, int paramInt2);
}
