package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public abstract interface IHandleEnderTeleport
{
  public abstract PotionBase getPotion();
  
  public abstract void onEnderTeleport(World paramWorld, EntityLivingBase paramEntityLivingBase, EnderTeleportEvent paramEnderTeleportEvent, int paramInt);
}
