package com.emoniph.witchery.ritual;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public abstract class Rite
{
  protected boolean canRelocate;
  
  public Rite()
  {
    canRelocate = false;
  }
  
  public abstract void addSteps(ArrayList<RitualStep> paramArrayList, int paramInt);
  
  public ArrayList<EntityItem> getItemsInRadius(World world, int x, int y, int z, float radius) {
    float RADIUS_SQ = radius * radius;
    double midX = 0.5D + x;
    double midZ = 0.5D + z;
    ArrayList<EntityItem> resultList = new ArrayList();
    AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(midX - radius, y, midZ - radius, midX + radius, 1.0D + y, midZ + radius);
    List items = world.func_72872_a(EntityItem.class, bounds);
    for (Object obj : items) {
      EntityItem entity = (EntityItem)obj;
      if (entity.func_70092_e(midX, y, midZ) <= RADIUS_SQ) {
        resultList.add(entity);
      }
    }
    return resultList;
  }
  
  public boolean relocatable() {
    return canRelocate;
  }
}
