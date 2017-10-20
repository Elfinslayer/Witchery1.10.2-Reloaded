package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public abstract interface IHandleLivingDeath
{
  public abstract PotionBase getPotion();
  
  public abstract void onLivingDeath(World paramWorld, EntityLivingBase paramEntityLivingBase, LivingDeathEvent paramLivingDeathEvent, int paramInt);
}
