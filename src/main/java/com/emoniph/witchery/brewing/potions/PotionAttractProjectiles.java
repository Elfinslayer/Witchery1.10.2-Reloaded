package com.emoniph.witchery.brewing.potions;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;


public class PotionAttractProjectiles
  extends PotionBase
{
  public PotionAttractProjectiles(int id, int color)
  {
    super(id, true, color);
  }
  
  public boolean func_76397_a(int duration, int amplifier)
  {
    return true;
  }
  
  public void func_76394_a(EntityLivingBase target, int amplifier)
  {
    World world = field_70170_p;
    double RADIUS = (1.0D + amplifier) * 3.0D;
    double RADIUS_SQ = RADIUS * RADIUS;
    AxisAlignedBB bounds = field_70121_D.func_72314_b(RADIUS, RADIUS, RADIUS);
    List<IProjectile> projectileList = world.func_72872_a(IProjectile.class, bounds);
    for (IProjectile projectile : projectileList)
    {










      if ((projectile instanceof Entity)) {
        Entity arrow = (Entity)projectile;
        double velocitySq = field_70159_w * field_70159_w + field_70181_x * field_70181_x + field_70179_y * field_70179_y;
        double FAST_SQ = 0.25D;
        if (field_70173_aa >= (velocitySq > 0.25D ? 1 : 10))
        {



          double d0 = field_70165_t - field_70165_t;
          double d1 = field_70121_D.field_72338_b + field_70131_O * 0.75D - field_70163_u;
          double d2 = field_70161_v - field_70161_v;
          double d3 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
          
          if (d3 >= 1.0E-7D)
          {








            projectile.func_70186_c(d0, d1, d2, 1.0F, 1.0F);
          }
        }
      }
    }
  }
}
