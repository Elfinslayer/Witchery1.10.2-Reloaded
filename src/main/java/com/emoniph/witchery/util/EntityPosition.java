package com.emoniph.witchery.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;

public class EntityPosition
{
  public final double x;
  public final double y;
  public final double z;
  
  public EntityPosition(int x, int y, int z)
  {
    this(0.5D + x, 0.5D + y, 0.5D + z);
  }
  
  public EntityPosition(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public EntityPosition(BlockPosition position) {
    x = x;
    y = y;
    z = z;
  }
  
  public EntityPosition(Entity entity) {
    this(field_70165_t, field_70163_u, field_70161_v);
  }
  
  public EntityPosition(MovingObjectPosition mop) {
    if (field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.ENTITY) {
      x = field_72308_g.field_70165_t;
      y = field_72308_g.field_70163_u;
      z = field_72308_g.field_70161_v;
    } else if (field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK) {
      x = (field_72311_b + 0.5D);
      y = (field_72312_c + 0.5D);
      z = (field_72309_d + 0.5D);
    } else {
      x = 0.0D;
      y = 0.0D;
      z = 0.0D;
    }
  }
  
  public double getDistanceSqToEntity(Entity entity) {
    double d0 = x - field_70165_t;
    double d1 = y - field_70163_u;
    double d2 = z - field_70161_v;
    return d0 * d0 + d1 * d1 + d2 * d2;
  }
  
  public net.minecraft.util.AxisAlignedBB getBounds(double radius) {
    net.minecraft.util.AxisAlignedBB aabb = net.minecraft.util.AxisAlignedBB.func_72330_a(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius);
    return aabb;
  }
  
  public boolean occupiedBy(Entity entity) {
    return (entity != null) && (field_70165_t == x) && (field_70163_u == y) && (field_70161_v == z);
  }
}
