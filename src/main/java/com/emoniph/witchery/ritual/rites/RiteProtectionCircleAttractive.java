package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.util.Coord;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;


public class RiteProtectionCircleAttractive
  extends RiteProtectionCircle
{
  public RiteProtectionCircleAttractive(int radius, float upkeepPowerCost, int ticksToLive)
  {
    super(radius, upkeepPowerCost, ticksToLive);
  }
  
  protected void update(World world, int posX, int posY, int posZ, int radius, long ticks)
  {
    attract(world, posX, posY, posZ, radius);
  }
  
  private void attract(World world, int posX, int posY, int posZ, float radius) {
    AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - radius, posY - radius, posZ - radius, posX + radius, posY + radius, posZ + radius);
    List list = world.func_72872_a(EntityCreature.class, bounds);
    
    for (Iterator iterator = list.iterator(); iterator.hasNext();) {
      Entity entity = (Entity)iterator.next();
      if (Coord.distance(field_70165_t, field_70163_u, field_70161_v, posX, posY, posZ) < radius) {
        pull(world, entity, posX, posY, posZ, radius);
      }
    }
  }
  
  private void pull(World world, Entity entity, int posX, int posY, int posZ, float radius) {
    if ((!(entity instanceof EntityPlayer)) && (!(entity instanceof EntityDragon)))
    {
      double distance = Coord.distance(field_70165_t + field_70159_w, field_70163_u + field_70181_x, field_70161_v + field_70179_y, posX, posY, posZ);
      if (distance >= radius - 1.0F)
      {
        Entity entity2 = entity;
        double d = posX - field_70165_t;
        double d1 = posY - field_70163_u;
        double d2 = posZ - field_70161_v;
        double d4 = d * d + d1 * d1 + d2 * d2;
        d4 *= d4;
        if (d4 <= Math.pow(6.0D, 4.0D)) {
          double d5 = -(d * 0.01999999955296516D / d4) * Math.pow(6.0D, 3.0D);
          double d6 = -(d1 * 0.01999999955296516D / d4) * Math.pow(6.0D, 3.0D);
          double d7 = -(d2 * 0.01999999955296516D / d4) * Math.pow(6.0D, 3.0D);
          if (d5 > 0.0D) {
            d5 = 0.22D;
          } else if (d5 < 0.0D) {
            d5 = -0.22D;
          }
          if (d6 > 0.2D) {
            d6 = 0.12D;
          } else if (d6 < -0.1D) {
            d6 = 0.12D;
          }
          if (d7 > 0.0D) {
            d7 = 0.22D;
          } else if (d7 < 0.0D) {
            d7 = -0.22D;
          }
          


          Vec3 vec = Vec3.func_72443_a(d5, d6, d7);
          vec.func_72442_b(180.0F);
          field_70159_w = field_72450_a;
          field_70181_x = 0.0D;
          field_70179_y = field_72449_c;
        }
      }
    }
  }
}
