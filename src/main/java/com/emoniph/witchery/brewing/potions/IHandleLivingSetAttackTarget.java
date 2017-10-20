package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;

public abstract interface IHandleLivingSetAttackTarget
{
  public abstract PotionBase getPotion();
  
  public abstract void onLivingSetAttackTarget(World paramWorld, EntityLiving paramEntityLiving, LivingSetAttackTargetEvent paramLivingSetAttackTargetEvent, int paramInt);
}
