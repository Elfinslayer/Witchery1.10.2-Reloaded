package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;

public abstract interface IHandleLivingJump
{
  public abstract PotionBase getPotion();
  
  public abstract void onLivingJump(World paramWorld, EntityLivingBase paramEntityLivingBase, LivingEvent.LivingJumpEvent paramLivingJumpEvent, int paramInt);
}
