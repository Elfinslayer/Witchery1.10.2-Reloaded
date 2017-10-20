package com.emoniph.witchery.entity.ai;

import net.minecraft.entity.EntityCreature;

public class EntityAIMoveIndoorsLeashAware extends net.minecraft.entity.ai.EntityAIMoveIndoors
{
  private EntityCreature creature;
  
  public EntityAIMoveIndoorsLeashAware(EntityCreature creature) {
    super(creature);
    this.creature = creature;
  }
  
  public boolean func_75250_a()
  {
    return (creature != null) && (!creature.func_110167_bD()) && (super.func_75250_a());
  }
}
