package com.emoniph.witchery.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;

public class EntityAIAttackCloseTargetOnCollide extends EntityAIAttackOnCollide
{
  EntityCreature field_75441_b;
  Class field_75444_h;
  double maxDistance;
  
  public EntityAIAttackCloseTargetOnCollide(EntityCreature par1EntityLiving, Class par2Class, double par3, boolean par4, double maxDistance)
  {
    this(par1EntityLiving, par3, par4, maxDistance);
    field_75444_h = par2Class;
  }
  
  public EntityAIAttackCloseTargetOnCollide(EntityCreature par1EntityLiving, double par2, boolean par3, double maxDistance) {
    super(par1EntityLiving, par2, par3);
    field_75441_b = par1EntityLiving;
    this.maxDistance = maxDistance;
  }
  
  public boolean func_75250_a()
  {
    boolean execute = super.func_75250_a();
    if ((execute) && (!isTargetNearby())) {
      execute = false;
    }
    
    return execute;
  }
  
  protected boolean isTargetNearby() {
    EntityLivingBase entityTarget = field_75441_b != null ? field_75441_b.func_70638_az() : null;
    return (entityTarget != null) && (field_75441_b.func_70068_e(entityTarget) <= maxDistance * maxDistance) && (field_75441_b.func_70661_as().func_75494_a(entityTarget) != null) && ((entityTarget.func_70694_bm() == null) || (entityTarget.func_70694_bm().func_77973_b() != ItemsDEVILS_TONGUE_CHARM));
  }
  
  public boolean func_75253_b()
  {
    boolean execute = super.func_75253_b();
    if ((execute) && (!isTargetNearby())) {
      execute = false;
    }
    return execute;
  }
}
