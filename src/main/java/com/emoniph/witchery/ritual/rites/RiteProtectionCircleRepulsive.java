package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.util.Coord;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteProtectionCircleRepulsive
  extends RiteProtectionCircle
{
  public RiteProtectionCircleRepulsive(int radius, float upkeepPowerCost, int ticksTolive)
  {
    super(radius, upkeepPowerCost, ticksTolive);
  }
  
  protected void update(World world, int posX, int posY, int posZ, int radius, long ticks)
  {
    repulse(world, posX, posY, posZ, radius);
  }
  
  private void repulse(World world, int posX, int posY, int posZ, float radius) {
    AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - radius, posY - radius, posZ - radius, posX + radius, posY + radius, posZ + radius);
    List list = world.func_72872_a(EntityCreature.class, bounds);
    
    for (Iterator iterator = list.iterator(); iterator.hasNext();) {
      Entity entity = (Entity)iterator.next();
      if (Coord.distance(field_70165_t, field_70163_u, field_70161_v, posX, posY, posZ) < radius) {
        push(world, entity, posX, posY, posZ);
      }
    }
  }
  
  public static void push(World world, Entity entity, double posX, double posY, double posZ) {
    push(world, entity, posX, posY, posZ, true);
  }
  
  public static void push(World world, Entity entity, double posX, double posY, double posZ, boolean restricted) {
    if ((!restricted) || ((!(entity instanceof EntityPlayer)) && (!(entity instanceof EntityDragon)))) {
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
        field_70159_w += d5;
        field_70181_x += d6;
        field_70179_y += d7;
      }
    }
  }
}
