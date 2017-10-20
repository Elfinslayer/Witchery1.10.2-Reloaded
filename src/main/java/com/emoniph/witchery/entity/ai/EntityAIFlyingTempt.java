package com.emoniph.witchery.entity.ai;

import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;























public class EntityAIFlyingTempt
  extends EntityAIBase
{
  private EntityCreature temptedEntity;
  private double field_75282_b;
  private double targetX;
  private double targetY;
  private double targetZ;
  private double field_75278_f;
  private double field_75279_g;
  private EntityPlayer temptingPlayer;
  private int delayTemptCounter;
  private boolean isRunning;
  private ItemStack[] breedingFood;
  private boolean scaredByPlayerMovement;
  private boolean field_75286_m;
  
  public EntityAIFlyingTempt(EntityCreature par1EntityCreature, double par2, ItemStack[] par4, boolean par5)
  {
    temptedEntity = par1EntityCreature;
    field_75282_b = par2;
    breedingFood = par4;
    scaredByPlayerMovement = par5;
    func_75248_a(1);
  }
  


  public boolean func_75250_a()
  {
    boolean isTame = ((temptedEntity instanceof EntityTameable)) && (((EntityTameable)temptedEntity).func_70909_n());
    if (isTame)
    {
      return false;
    }
    if (delayTemptCounter > 0) {
      delayTemptCounter -= 1;
      return false;
    }
    temptingPlayer = temptedEntity.field_70170_p.func_72890_a(temptedEntity, 10.0D);
    
    if (temptingPlayer == null) {
      return false;
    }
    ItemStack itemstack = temptingPlayer.func_71045_bC();
    return itemstack == null ? false : isBreedingFood(itemstack);
  }
  

  private boolean isBreedingFood(ItemStack stack)
  {
    for (ItemStack possibleFoods : breedingFood) {
      if (possibleFoods.func_77969_a(stack)) {
        return true;
      }
    }
    return false;
  }
  


  public boolean func_75253_b()
  {
    if (scaredByPlayerMovement) {
      if (temptedEntity.func_70068_e(temptingPlayer) < 36.0D) {
        if (temptingPlayer.func_70092_e(targetX, targetY, targetZ) > 0.010000000000000002D) {
          return false;
        }
        

      }
      else
      {

        targetX = temptingPlayer.field_70165_t;
        targetY = temptingPlayer.field_70163_u;
        targetZ = temptingPlayer.field_70161_v;
      }
      
      field_75278_f = temptingPlayer.field_70125_A;
      field_75279_g = temptingPlayer.field_70177_z;
    }
    
    return func_75250_a();
  }
  





  public void func_75249_e()
  {
    isRunning = true;
  }
  





  public void func_75251_c()
  {
    temptingPlayer = null;
    
    delayTemptCounter = 100;
    isRunning = false;
  }
  



  public void func_75246_d()
  {
    if (temptedEntity.func_70068_e(temptingPlayer) >= 3.0D) {
      double d0 = targetX - temptedEntity.field_70165_t;
      double d1 = targetY - temptedEntity.field_70163_u;
      double d2 = targetZ - temptedEntity.field_70161_v;
      double d3 = d0 * d0 + d1 * d1 + d2 * d2;
      d3 = MathHelper.func_76133_a(d3);
      if (isCourseTraversable(targetX, targetY, targetZ, d3))
      {
        temptedEntity.field_70159_w += d0 / d3 * 0.05D;
        if (temptedEntity.field_70163_u < targetY + 1.0D) {
          temptedEntity.field_70181_x += d1 / d3 * 0.05D + 0.025D;
        } else {
          temptedEntity.field_70181_x += d1 / d3 * 0.05D;
        }
        temptedEntity.field_70179_y += d2 / d3 * 0.05D;
      }
      temptedEntity.field_70761_aq = (temptedEntity.field_70177_z = -(float)Math.atan2(temptedEntity.field_70159_w, temptedEntity.field_70179_y) * 180.0F / 3.1415927F);
    }
  }
  
  private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
    double d4 = (par1 - temptedEntity.field_70165_t) / par7;
    double d5 = (par3 - temptedEntity.field_70163_u) / par7;
    double d6 = (par5 - temptedEntity.field_70161_v) / par7;
    
    AxisAlignedBB axisalignedbb = temptedEntity.field_70121_D.func_72329_c();
    
    for (int i = 1; i < par7; i++) {
      axisalignedbb.func_72317_d(d4, d5, d6);
      
      if (!temptedEntity.field_70170_p.func_72945_a(temptedEntity, axisalignedbb).isEmpty()) {
        return false;
      }
    }
    
    return true;
  }
  


  public boolean isRunning()
  {
    return isRunning;
  }
}
