package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.entity.EntityTreefyd;

public class EntityAITreefydWander extends net.minecraft.entity.ai.EntityAIWander
{
  private final EntityTreefyd treefyd;
  
  public EntityAITreefydWander(EntityTreefyd treefyd, double speed)
  {
    super(treefyd, speed);
    this.treefyd = treefyd;
  }
  
  public boolean func_75250_a()
  {
    return (!treefyd.isSentinal()) && (super.func_75250_a());
  }
  
  public boolean func_75253_b()
  {
    return (!treefyd.isSentinal()) && (super.func_75253_b());
  }
}
