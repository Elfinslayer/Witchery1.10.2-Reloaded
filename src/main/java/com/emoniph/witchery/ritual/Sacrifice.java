package com.emoniph.witchery.ritual;

import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public abstract class Sacrifice
{
  public Sacrifice() {}
  
  public abstract boolean isMatch(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, ArrayList<Entity> paramArrayList, ArrayList<ItemStack> paramArrayList1);
  
  protected static double distance(double firstX, double firstY, double firstZ, double secondX, double secondY, double secondZ)
  {
    double dX = firstX - secondX;
    double dY = firstY - secondY;
    double dZ = firstZ - secondZ;
    double distance = Math.sqrt(dX * dX + dY * dY + dZ * dZ);
    return distance;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, AxisAlignedBB bounds, int maxDistance) {}
  
  public void addDescription(StringBuffer sb) {}
}
