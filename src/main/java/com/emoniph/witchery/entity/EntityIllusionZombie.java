package com.emoniph.witchery.entity;

import com.emoniph.witchery.util.SoundEffect;

public class EntityIllusionZombie extends EntityIllusion
{
  public EntityIllusionZombie(net.minecraft.world.World world)
  {
    super(world);
  }
  
  protected SoundEffect getFakeLivingSound()
  {
    return SoundEffect.MOB_ZOMBIE_SAY;
  }
}
