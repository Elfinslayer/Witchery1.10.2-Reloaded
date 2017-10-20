package com.emoniph.witchery.entity;

import com.emoniph.witchery.util.SoundEffect;

public class EntityIllusionSpider extends EntityIllusion
{
  public EntityIllusionSpider(net.minecraft.world.World world)
  {
    super(world);
  }
  
  protected SoundEffect getFakeLivingSound()
  {
    return SoundEffect.MOB_SPIDER_SAY;
  }
}
