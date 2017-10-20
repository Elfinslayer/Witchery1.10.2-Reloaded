package com.emoniph.witchery.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.World;









public class EntityAITemptWithPlayer
  extends EntityAIBase
{
  private EntityCreature temptedEntity;
  private double field_75282_b;
  private double field_75283_c;
  private double field_75280_d;
  private double field_75281_e;
  private double field_75278_f;
  private double field_75279_g;
  private EntityPlayer temptingPlayer;
  private int delayTemptCounter;
  private boolean field_75287_j;
  private boolean field_75286_m;
  
  public EntityAITemptWithPlayer(EntityCreature par1EntityCreature, double par2)
  {
    temptedEntity = par1EntityCreature;
    field_75282_b = par2;
    func_75248_a(3);
  }
  



  public boolean func_75250_a()
  {
    if (delayTemptCounter > 0) {
      delayTemptCounter -= 1;
      return false;
    }
    temptingPlayer = temptedEntity.field_70170_p.func_72890_a(temptedEntity, 10.0D);
    
    if (temptingPlayer == null) {
      return false;
    }
    
    return true;
  }
  



































  public boolean func_75253_b()
  {
    return func_75250_a();
  }
  



  public void func_75249_e()
  {
    field_75283_c = temptingPlayer.field_70165_t;
    field_75280_d = temptingPlayer.field_70163_u;
    field_75281_e = temptingPlayer.field_70161_v;
    field_75287_j = true;
    field_75286_m = temptedEntity.func_70661_as().func_75486_a();
    temptedEntity.func_70661_as().func_75491_a(false);
  }
  



  public void func_75251_c()
  {
    temptingPlayer = null;
    temptedEntity.func_70661_as().func_75499_g();
    delayTemptCounter = 100;
    field_75287_j = false;
    temptedEntity.func_70661_as().func_75491_a(field_75286_m);
  }
  



  public void func_75246_d()
  {
    temptedEntity.func_70671_ap().func_75651_a(temptingPlayer, 30.0F, temptedEntity.func_70646_bf());
    
    if (temptedEntity.func_70068_e(temptingPlayer) < 6.25D) {
      temptedEntity.func_70661_as().func_75499_g();
    } else {
      temptedEntity.func_70661_as().func_75497_a(temptingPlayer, field_75282_b);
    }
  }
  
  public boolean func_75277_f() {
    return field_75287_j;
  }
}
