package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.entity.EntityGoblin;
import java.util.List;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityAIPickUpBlocks extends net.minecraft.entity.ai.EntityAIBase
{
  protected final EntityGoblin entity;
  protected final double range;
  
  public EntityAIPickUpBlocks(EntityGoblin entity, double range)
  {
    this.entity = entity;
    this.range = range;
    func_75248_a(7);
  }
  
  public boolean func_75250_a() {
    return (entity != null) && (!entity.isWorshipping()) && (entity.func_70694_bm() == null) && (entity.func_110167_bD()) && (isItemInReachableDistance());
  }
  
  public void func_75249_e() {
    AxisAlignedBB bb = AxisAlignedBB.func_72330_a(entity.field_70165_t - range, entity.field_70163_u - range, entity.field_70161_v - range, entity.field_70165_t + range, entity.field_70163_u + range, entity.field_70161_v + range);
    List<EntityItem> items = entity.field_70170_p.func_72872_a(EntityItem.class, bb);
    double SPEED = 0.6D;
    for (EntityItem item : items) {
      if (entity.func_70661_as().func_75497_a(item, 0.6D)) {
        break;
      }
    }
  }
  
  public boolean func_75253_b() {
    return (entity != null) && (!entity.isWorshipping()) && (entity.func_70694_bm() == null) && (entity.func_110167_bD()) && (isItemInReachableDistance());
  }
  
  public void func_75246_d() {
    if (entity.func_70661_as().func_75500_f()) {
      AxisAlignedBB bb = AxisAlignedBB.func_72330_a(entity.field_70165_t - range, entity.field_70163_u - range, entity.field_70161_v - range, entity.field_70165_t + range, entity.field_70163_u + range, entity.field_70161_v + range);
      List<EntityItem> items = entity.field_70170_p.func_72872_a(EntityItem.class, bb);
      double SPEED = 0.6D;
      for (EntityItem item : items) {
        if (entity.func_70661_as().func_75497_a(item, 0.6D)) {
          break;
        }
      }
    } else {
      double PICKUP_RANGE = 1.5D;
      AxisAlignedBB bb = AxisAlignedBB.func_72330_a(entity.field_70165_t - 1.5D, entity.field_70163_u - 1.5D, entity.field_70161_v - 1.5D, entity.field_70165_t + 1.5D, entity.field_70163_u + 1.5D, entity.field_70161_v + 1.5D);
      List<EntityItem> items = entity.field_70170_p.func_72872_a(EntityItem.class, bb);
      if (!items.isEmpty()) {
        entity.func_70062_b(0, ((EntityItem)items.get(0)).func_92059_d());
        if (!entity.field_70170_p.field_72995_K) {
          ((EntityItem)items.get(0)).func_70106_y();
        }
      }
    }
  }
  
  protected boolean isItemInReachableDistance() {
    AxisAlignedBB bb = AxisAlignedBB.func_72330_a(entity.field_70165_t - range, entity.field_70163_u - range, entity.field_70161_v - range, entity.field_70165_t + range, entity.field_70163_u + range, entity.field_70161_v + range);
    List<EntityItem> items = entity.field_70170_p.func_72872_a(EntityItem.class, bb);
    double SPEED = 0.1D;
    for (EntityItem item : items) {
      if (entity.func_70661_as().func_75494_a(item) != null) {
        return true;
      }
    }
    
    return false;
  }
}
