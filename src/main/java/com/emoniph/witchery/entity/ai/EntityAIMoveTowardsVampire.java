package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.common.ExtendedPlayer;
import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;



public class EntityAIMoveTowardsVampire
  extends EntityAIBase
{
  private EntityCreature theEntity;
  private EntityLivingBase targetEntity;
  private double movePosX;
  private double movePosY;
  private double movePosZ;
  private double speed;
  private float maxTargetDistance;
  private float minTargetDistance;
  
  public EntityAIMoveTowardsVampire(EntityCreature par1EntityCreature, double par2, float min, float max)
  {
    theEntity = par1EntityCreature;
    speed = par2;
    minTargetDistance = min;
    maxTargetDistance = max;
    
    func_75248_a(1);
  }
  
  private EntityLivingBase getDistanceSqToPartner() {
    double R = maxTargetDistance;
    AxisAlignedBB bb = AxisAlignedBB.func_72330_a(theEntity.field_70165_t - R, theEntity.field_70163_u - R, theEntity.field_70161_v - R, theEntity.field_70165_t + R, theEntity.field_70163_u + R, theEntity.field_70161_v + R);
    
    List<EntityPlayer> mogs = theEntity.field_70170_p.func_72872_a(EntityPlayer.class, bb);
    double minDistance = Double.MAX_VALUE;
    EntityLivingBase target = null;
    for (EntityPlayer player : mogs) {
      if (ExtendedPlayer.get(player).getVampireLevel() >= 8) {
        double distance = theEntity.func_70068_e(player);
        if (distance < minDistance) {
          minDistance = distance;
          target = player;
        }
      }
    }
    return target;
  }
  
  public boolean func_75250_a()
  {
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
    
    return true;
  }
  
  public boolean func_75253_b()
  {
    if (theEntity.field_70173_aa % 20 == 0) {
      theEntity.func_70661_as().func_75492_a(targetEntity.field_70165_t, targetEntity.field_70163_u, targetEntity.field_70161_v, speed);
    }
    return true;
  }
  
  public void func_75251_c() {
    targetEntity = null;
  }
  
  public void func_75249_e() {
    theEntity.func_70661_as().func_75492_a(targetEntity.field_70165_t, targetEntity.field_70163_u, targetEntity.field_70161_v, speed);
  }
}
