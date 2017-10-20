package com.emoniph.witchery.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.pathfinding.PathNavigate;



public class EntityAISitAndStay
  extends EntityAIBase
{
  private EntityTameable theEntity;
  
  public EntityAISitAndStay(EntityTameable par1EntityTameable)
  {
    theEntity = par1EntityTameable;
    func_75248_a(5);
  }
  



  public boolean func_75250_a()
  {
    return theEntity.func_70906_o();
  }
  




















  public void func_75249_e()
  {
    theEntity.func_70661_as().func_75499_g();
  }
  
  public void func_75251_c() {}
}
