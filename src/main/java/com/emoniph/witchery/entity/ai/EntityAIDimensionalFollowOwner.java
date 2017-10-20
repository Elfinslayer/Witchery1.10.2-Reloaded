package com.emoniph.witchery.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class EntityAIDimensionalFollowOwner extends EntityAIBase
{
  private EntityTameable thePet;
  private EntityLivingBase theOwner;
  World theWorld;
  private double field_75336_f;
  private PathNavigate petPathfinder;
  private int field_75343_h;
  float maxDist;
  float minDist;
  private boolean field_75344_i;
  
  public EntityAIDimensionalFollowOwner(EntityTameable par1EntityTameable, double par2, float par4, float par5)
  {
    thePet = par1EntityTameable;
    theWorld = field_70170_p;
    field_75336_f = par2;
    petPathfinder = par1EntityTameable.func_70661_as();
    minDist = par4;
    maxDist = par5;
    func_75248_a(3);
  }
  



  public boolean func_75250_a()
  {
    EntityLivingBase entitylivingbase = thePet.func_70902_q();
    



    if (entitylivingbase == null)
    {
      return false;
    }
    if (thePet.func_70906_o())
    {
      return false;
    }
    if ((thePet.field_71093_bK != field_71093_bK) || (thePet.func_70068_e(entitylivingbase) < minDist * minDist))
    {
      return false;
    }
    

    theOwner = entitylivingbase;
    
    return true;
  }
  




  public boolean func_75253_b()
  {
    return (!petPathfinder.func_75500_f()) && (thePet.func_70068_e(theOwner) > maxDist * maxDist) && (!thePet.func_70906_o());
  }
  



  public void func_75249_e()
  {
    field_75343_h = 0;
    field_75344_i = thePet.func_70661_as().func_75486_a();
    thePet.func_70661_as().func_75491_a(false);
  }
  



  public void func_75251_c()
  {
    theOwner = null;
    petPathfinder.func_75499_g();
    thePet.func_70661_as().func_75491_a(field_75344_i);
  }
  



  public void func_75246_d()
  {
    thePet.func_70671_ap().func_75651_a(theOwner, 10.0F, thePet.func_70646_bf());
    
    if (!thePet.func_70906_o())
    {
      if (--field_75343_h <= 0)
      {
        field_75343_h = 10;
        
        if (!petPathfinder.func_75497_a(theOwner, field_75336_f))
        {
          if (!thePet.func_110167_bD())
          {
            if ((thePet.field_71093_bK != theOwner.field_71093_bK) || (thePet.func_70068_e(theOwner) >= 144.0D))
            {
              int i = MathHelper.func_76128_c(theOwner.field_70165_t) - 2;
              int j = MathHelper.func_76128_c(theOwner.field_70161_v) - 2;
              int k = MathHelper.func_76128_c(theOwner.field_70121_D.field_72338_b);
              
              for (int l = 0; l <= 4; l++)
              {
                for (int i1 = 0; i1 <= 4; i1++)
                {
                  if (((l < 1) || (i1 < 1) || (l > 3) || (i1 > 3)) && (theOwner.field_70170_p.func_147439_a(i + l, k - 1, j + i1).isSideSolid(theOwner.field_70170_p, i + l, k - 1, j + i1, ForgeDirection.UP)) && (!theOwner.field_70170_p.func_147439_a(i + l, k, j + i1).func_149721_r()) && (!theOwner.field_70170_p.func_147439_a(i + l, k + 1, j + i1).func_149721_r()))
                  {

                    if (thePet.field_71093_bK == theOwner.field_71093_bK)
                    {

                      thePet.func_70012_b(i + l + 0.5F, k, j + i1 + 0.5F, thePet.field_70177_z, thePet.field_70125_A);
                    }
                    
                    petPathfinder.func_75499_g();
                    return;
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}
