package com.emoniph.witchery.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAvoidEntity;










public class EntityAIAvoidEntityConditionally
  extends EntityAIAvoidEntity
{
  private final IAvoidEntities condition;
  
  public EntityAIAvoidEntityConditionally(EntityCreature entity, Class targetClass, float distanceFromEntity, double farSpeed, double nearSpeed, IAvoidEntities condition)
  {
    super(entity, targetClass, distanceFromEntity, farSpeed, nearSpeed);
    this.condition = condition;
  }
  
  public boolean func_75250_a() {
    return (super.func_75250_a()) && (!condition.shouldAvoid());
  }
  
  public static abstract interface IAvoidEntities
  {
    public abstract boolean shouldAvoid();
  }
}
