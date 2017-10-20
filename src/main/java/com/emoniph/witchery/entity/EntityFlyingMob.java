package com.emoniph.witchery.entity;

import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityFlyingMob extends net.minecraft.entity.monster.EntityMob
{
  public EntityFlyingMob(World par1World)
  {
    super(par1World);
  }
  


  protected void func_70069_a(float par1) {}
  

  protected void func_70064_a(double par1, boolean par3) {}
  

  public void func_70612_e(float par1, float par2)
  {
    if (func_70090_H()) {
      func_70060_a(par1, par2, 0.02F);
      func_70091_d(field_70159_w, field_70181_x, field_70179_y);
      field_70159_w *= 0.800000011920929D;
      field_70181_x *= 0.800000011920929D;
      field_70179_y *= 0.800000011920929D;
    } else if (func_70058_J()) {
      func_70060_a(par1, par2, 0.02F);
      func_70091_d(field_70159_w, field_70181_x, field_70179_y);
      field_70159_w *= 0.5D;
      field_70181_x *= 0.5D;
      field_70179_y *= 0.5D;
    } else {
      float f2 = 0.91F;
      
      if (field_70122_E) {
        f2 = 0.54600006F;
        Block i = field_70170_p.func_147439_a(MathHelper.func_76128_c(field_70165_t), MathHelper.func_76128_c(field_70121_D.field_72338_b) - 1, MathHelper.func_76128_c(field_70161_v));
        

        if (i != net.minecraft.init.Blocks.field_150350_a) {
          f2 = field_149765_K * 0.91F;
        }
      }
      
      float f3 = 0.16277136F / (f2 * f2 * f2);
      func_70060_a(par1, par2, field_70122_E ? 0.1F * f3 : 0.02F);
      f2 = 0.91F;
      
      if (field_70122_E) {
        f2 = 0.54600006F;
        Block j = field_70170_p.func_147439_a(MathHelper.func_76128_c(field_70165_t), MathHelper.func_76128_c(field_70121_D.field_72338_b) - 1, MathHelper.func_76128_c(field_70161_v));
        

        if (j != net.minecraft.init.Blocks.field_150350_a) {
          f2 = field_149765_K * 0.91F;
        }
      }
      


      func_70091_d(field_70159_w, field_70181_x, field_70179_y);
      
      field_70159_w *= f2;
      field_70181_x *= f2;
      field_70179_y *= f2;
    }
    
    field_70722_aY = field_70721_aZ;
    double d0 = field_70165_t - field_70169_q;
    double d1 = field_70161_v - field_70166_s;
    float f4 = MathHelper.func_76133_a(d0 * d0 + d1 * d1) * 4.0F;
    
    if (f4 > 1.0F) {
      f4 = 1.0F;
    }
    
    field_70721_aZ += (f4 - field_70721_aZ) * 0.4F;
    field_70754_ba += field_70721_aZ;
  }
  
  public boolean func_70617_f_()
  {
    return false;
  }
}
