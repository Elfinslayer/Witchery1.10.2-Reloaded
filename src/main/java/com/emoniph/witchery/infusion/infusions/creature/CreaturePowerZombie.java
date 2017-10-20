package com.emoniph.witchery.infusion.infusions.creature;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class CreaturePowerZombie extends CreaturePower
{
  public CreaturePowerZombie(int powerID)
  {
    super(powerID, EntityZombie.class);
  }
  
  public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop)
  {
    if (!field_72995_K) {
      player.func_70690_d(new PotionEffect(field_76429_mfield_76415_H, 600, 1));
      player.func_70690_d(new PotionEffect(field_76420_gfield_76415_H, 600, 0));
    }
  }
}
