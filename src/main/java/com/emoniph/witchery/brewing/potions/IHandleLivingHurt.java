package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public abstract interface IHandleLivingHurt
{
  public abstract PotionBase getPotion();
  
  public abstract void onLivingHurt(World paramWorld, EntityLivingBase paramEntityLivingBase, LivingHurtEvent paramLivingHurtEvent, int paramInt);
  
  public abstract boolean handleAllHurtEvents();
}
