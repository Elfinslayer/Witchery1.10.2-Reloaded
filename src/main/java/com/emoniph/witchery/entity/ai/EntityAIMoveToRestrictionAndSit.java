package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.entity.EntityCovenWitch;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;

public class EntityAIMoveToRestrictionAndSit extends EntityAIBase
{
  private EntityCovenWitch theEntity;
  private double movePosX;
  private double movePosY;
  private double movePosZ;
  private double movementSpeed;
  
  public EntityAIMoveToRestrictionAndSit(EntityCovenWitch p_i2347_1_, double p_i2347_2_)
  {
    theEntity = p_i2347_1_;
    movementSpeed = p_i2347_2_;
    func_75248_a(1);
  }
  
  public boolean func_75250_a()
  {
    if (theEntity.func_110173_bK()) {
      return false;
    }
    ChunkCoordinates chunkcoordinates = theEntity.func_110172_bL();
    Vec3 vec3 = net.minecraft.entity.ai.RandomPositionGenerator.func_75464_a(theEntity, 16, 7, Vec3.func_72443_a(field_71574_a, field_71572_b, field_71573_c));
    


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
    return !theEntity.func_70661_as().func_75500_f();
  }
  
  public void func_75249_e()
  {
    theEntity.func_70661_as().func_75492_a(movePosX, movePosY, movePosZ, movementSpeed);
  }
  

  public void func_75251_c()
  {
    super.func_75251_c();
    if (theEntity.func_110173_bK()) {
      theEntity.standStill();
    }
  }
}
