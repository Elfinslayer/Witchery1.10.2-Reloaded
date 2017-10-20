package com.emoniph.witchery.entity.ai;

import net.minecraft.entity.ai.EntityAIAttackOnCollide;

public class EntityAIAttackOnCollide2 extends EntityAIAttackOnCollide
{
  private Class clazz;
  
  public EntityAIAttackOnCollide2(net.minecraft.entity.EntityCreature creature, Class classToAttack, double speedTowardsTarget, boolean longMemory)
  {
    super(creature, classToAttack, speedTowardsTarget, longMemory);
    clazz = classToAttack;
  }
  
  public boolean appliesToClass(Class victimClass) {
    return victimClass == clazz;
  }
}
