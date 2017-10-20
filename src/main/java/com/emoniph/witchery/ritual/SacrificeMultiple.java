package com.emoniph.witchery.ritual;

import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class SacrificeMultiple extends Sacrifice
{
  private final Sacrifice[] sacrifices;
  
  public SacrificeMultiple(Sacrifice... sacrifices)
  {
    this.sacrifices = sacrifices;
  }
  
  public void addDescription(StringBuffer sb)
  {
    for (Sacrifice sacrifice : sacrifices) {
      sacrifice.addDescription(sb);
    }
  }
  
  public boolean isMatch(World world, int posX, int posY, int posZ, int maxDistance, ArrayList<Entity> entities, ArrayList<ItemStack> grassperStacks)
  {
    for (Sacrifice sacrifice : sacrifices) {
      if (!sacrifice.isMatch(world, posX, posY, posZ, maxDistance, entities, grassperStacks)) {
        return false;
      }
    }
    
    return true;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, AxisAlignedBB bounds, int maxDistance)
  {
    for (Sacrifice sacrifice : sacrifices) {
      sacrifice.addSteps(steps, bounds, maxDistance);
    }
  }
}
