package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public abstract interface IHandleLivingAttack
{
  public abstract PotionBase getPotion();
  
  public abstract void onLivingAttack(World paramWorld, EntityLivingBase paramEntityLivingBase, LivingAttackEvent paramLivingAttackEvent, int paramInt);
}
