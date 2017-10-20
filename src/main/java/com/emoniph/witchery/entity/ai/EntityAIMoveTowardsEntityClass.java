package com.emoniph.witchery.entity.ai;

import java.util.Random;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityAIMoveTowardsEntityClass extends net.minecraft.entity.ai.EntityAIBase
{
  private EntityCreature theEntity;
  private EntityLivingBase targetEntity;
  private Class<? extends EntityLiving> targetType;
  private double movePosX;
  private double movePosY;
  private double movePosZ;
  private double speed;
  private float maxTargetDistance;
  private float minTargetDistance;
  
  public EntityAIMoveTowardsEntityClass(EntityCreature par1EntityCreature, Class<? extends EntityLiving> targetType, double par2, float par4, float par5)
  {
    theEntity = par1EntityCreature;
    this.targetType = targetType;
    speed = par2;
    minTargetDistance = par4;
    maxTargetDistance = par5;
    
    func_75248_a(1);
  }
  
  private EntityLiving getDistanceSqToPartner() {
    double R = maxTargetDistance;
    AxisAlignedBB bb = AxisAlignedBB.func_72330_a(theEntity.field_70165_t - R, theEntity.field_70163_u - R, theEntity.field_70161_v - R, theEntity.field_70165_t + R, theEntity.field_70163_u + R, theEntity.field_70161_v + R);
    
    java.util.List mogs = theEntity.field_70170_p.func_72872_a(targetType, bb);
    double minDistance = Double.MAX_VALUE;
    EntityLiving target = null;
    for (Object obj : mogs) {
      EntityLiving mog = (EntityLiving)obj;
      double distance = theEntity.func_70068_e(mog);
      if (distance < minDistance) {
        minDistance = distance;
        target = mog;
      }
    }
    return target;
  }
  
  public boolean func_75250_a() {
    if (theEntity.field_70170_p.field_73012_v.nextInt(20) != 0) {
      return false;
    }
    
    targetEntity = getDistanceSqToPartner();
    
    if (targetEntity == null) {
      return false;
    }
    
    double dist = targetEntity.func_70068_e(theEntity);
    
    if (dist > maxTargetDistance * maxTargetDistance)
      return false;
    if (dist < minTargetDistance * minTargetDistance) {
      return false;
    }
    Vec3 vec3 = RandomPositionGenerator.func_75464_a(theEntity, 16, 7, Vec3.func_72443_a(targetEntity.field_70165_t, targetEntity.field_70163_u, targetEntity.field_70161_v));
    
    if (vec3 == null) {
      return false;
    }
    movePosX = field_72450_a;
    movePosY = field_72448_b;
    movePosZ = field_72449_c;
    return true;
  }
  

  public boolean func_75253_b()
  {
    return (!theEntity.func_70661_as().func_75500_f()) && (targetEntity.func_70089_S()) && (targetEntity.func_70068_e(theEntity) < maxTargetDistance * maxTargetDistance);
  }
  
  public void func_75251_c()
  {
    targetEntity = null;
  }
  
  public void func_75249_e() {
    theEntity.func_70661_as().func_75492_a(movePosX, movePosY, movePosZ, speed);
  }
}
