package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.familiar.Familiar.FamiliarOwner;
import com.emoniph.witchery.item.ItemGeneral;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAIFlyerFollowOwner extends EntityAIBase
{
  private EntityTameable thePet;
  private EntityLivingBase theOwner;
  World theWorld;
  private double field_75336_f;
  private int field_75343_h;
  float maxDist;
  float minDist;
  private boolean field_75344_i;
  
  public EntityAIFlyerFollowOwner(EntityTameable par1EntityTameable, double par2, float par4, float par5)
  {
    thePet = par1EntityTameable;
    theWorld = field_70170_p;
    field_75336_f = par2;
    minDist = par4;
    maxDist = par5;
    func_75248_a(1);
  }
  
  public boolean func_75250_a()
  {
    EntityLivingBase entitylivingbase = thePet.func_70902_q();
    if ((entitylivingbase == null) && (Familiar.couldBeFamiliar(thePet))) {
      entitylivingbase = Familiar.getOwnerForFamiliar(thePet).getCurrentOwner();
    }
    
    if (entitylivingbase == null)
      return false;
    if (thePet.func_70906_o())
      return false;
    if ((thePet.field_71093_bK != field_71093_bK) || (thePet.func_70068_e(entitylivingbase) < minDist * minDist)) {
      return false;
    }
    theOwner = entitylivingbase;
    return true;
  }
  
  public boolean func_75253_b()
  {
    return (thePet.func_70068_e(theOwner) > maxDist * maxDist) && (!thePet.func_70906_o());
  }
  
  public void func_75249_e() {
    field_75343_h = 0;
  }
  
  public void func_75251_c() {
    theOwner = null;
  }
  
  public void func_75246_d() {
    if ((!thePet.func_70906_o()) && 
      (--field_75343_h <= 0)) {
      field_75343_h = 10;
      
      if ((thePet.field_71093_bK != theOwner.field_71093_bK) || (thePet.func_70068_e(theOwner) >= 256.0D)) {
        int x = MathHelper.func_76128_c(theOwner.field_70165_t) - 2;
        int z = MathHelper.func_76128_c(theOwner.field_70161_v) - 2;
        int y = MathHelper.func_76128_c(theOwner.field_70121_D.field_72338_b) - 2;
        
        for (int dx = 0; dx <= 4; dx++) {
          for (int dz = 0; dz <= 4; dz++) {
            for (int dy = 0; dy <= 4; dy++) {
              int newX = x + dz;
              int newY = y + dy;
              int newZ = z + dz;
              if ((theOwner.field_70170_p.func_147439_a(newX, newY - 1, newZ).isSideSolid(theOwner.field_70170_p, newX, newY - 1, newZ, net.minecraftforge.common.util.ForgeDirection.UP)) && (!theOwner.field_70170_p.func_147439_a(newX, newY, newZ).func_149721_r()) && (!theOwner.field_70170_p.func_147439_a(newX, newY + 1, newZ).func_149721_r()))
              {

                ItemGeneral.teleportToLocation(theWorld, 0.5D + newX, 0.01D + newY, 0.5D + newZ, theOwner.field_71093_bK, thePet, true);
                return;
              }
            }
          }
        }
      } else {
        double d0 = theOwner.field_70165_t - thePet.field_70165_t;
        double d1 = theOwner.field_70163_u - thePet.field_70163_u;
        double d2 = theOwner.field_70161_v - thePet.field_70161_v;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;
        d3 = MathHelper.func_76133_a(d3);
        if (isCourseTraversable(theOwner.field_70165_t, theOwner.field_70163_u, theOwner.field_70161_v, d3)) {
          thePet.field_70159_w += d0 / d3 * 0.1D;
          if (thePet.field_70163_u < theOwner.field_70163_u + 2.0D) {
            thePet.field_70181_x += d1 / d3 * 0.1D + 0.1D;
          } else {
            thePet.field_70181_x += d1 / d3 * 0.1D;
          }
          thePet.field_70179_y += d2 / d3 * 0.1D;
        }
        else {
          double newX = thePet.field_70165_t + (thePet.field_70170_p.field_73012_v.nextFloat() * 8.0F - 4.0F) * 6.0F;
          double newY = thePet.field_70163_u + (thePet.field_70170_p.field_73012_v.nextFloat() * 2.0F - 1.0F) * 6.0F;
          double newZ = thePet.field_70161_v + (thePet.field_70170_p.field_73012_v.nextFloat() * 8.0F - 4.0F) * 6.0F;
          d0 = newX - thePet.field_70165_t;
          d1 = newY - thePet.field_70163_u;
          d2 = newZ - thePet.field_70161_v;
          d3 = d0 * d0 + d1 * d1 + d2 * d2;
          d3 = MathHelper.func_76133_a(d3);
          
          thePet.field_70159_w += d0 / d3 * 0.1D;
          thePet.field_70181_x += d1 / d3 * 0.1D + 0.1D;
          thePet.field_70179_y += d2 / d3 * 0.1D;
        }
      }
      

      thePet.field_70761_aq = (thePet.field_70177_z = -(float)Math.atan2(thePet.field_70159_w, thePet.field_70179_y) * 180.0F / 3.1415927F);
    }
  }
  
  private boolean isCourseTraversable(double par1, double par3, double par5, double par7)
  {
    double d4 = (par1 - thePet.field_70165_t) / par7;
    double d5 = (par3 - thePet.field_70163_u) / par7;
    double d6 = (par5 - thePet.field_70161_v) / par7;
    
    AxisAlignedBB axisalignedbb = thePet.field_70121_D.func_72329_c();
    
    for (int i = 1; i < par7; i++) {
      axisalignedbb.func_72317_d(d4, d5, d6);
      
      if (!thePet.field_70170_p.func_72945_a(thePet, axisalignedbb).isEmpty()) {
        return false;
      }
    }
    
    return true;
  }
}
