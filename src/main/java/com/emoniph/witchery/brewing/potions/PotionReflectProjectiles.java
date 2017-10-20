package com.emoniph.witchery.brewing.potions;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;


public class PotionReflectProjectiles
  extends PotionBase
{
  public PotionReflectProjectiles(int id, int color)
  {
    super(id, color);
  }
  
  public boolean func_76397_a(int duration, int amplifier)
  {
    return true;
  }
  
  public void func_76394_a(EntityLivingBase entity, int amplifier)
  {
    World world = field_70170_p;
    double RADIUS = 2.0D;
    double RADIUS_SQ = 4.0D;
    AxisAlignedBB bounds = field_70121_D.func_72314_b(2.0D, 2.0D, 2.0D);
    List<IProjectile> projectileList = world.func_72872_a(IProjectile.class, bounds);
    for (IProjectile projectile : projectileList) {
      boolean isArrow = false;
      if ((projectile instanceof EntityArrow)) {
        EntityArrow arrow = (EntityArrow)projectile;
        isArrow = true;
        if (field_70250_c == entity) {
          continue;
        }
      } else if ((projectile instanceof EntityThrowable)) {
        EntityThrowable arrow = (EntityThrowable)projectile;
        if (arrow.func_85052_h() == entity) {
          continue;
        }
      }
      if ((projectile instanceof Entity)) {
        Entity projectileEntity = (Entity)projectile;
        field_70159_w *= -0.25D * (1.0D + amplifier);
        if ((field_70159_w > 0.0D) || (field_70179_y > 0.0D)) {
          field_70181_x *= -0.25D * (1.0D + amplifier);
        }
        field_70179_y *= -0.25D * (1.0D + amplifier);
        if (!isArrow) {}
      }
    }
  }
}
