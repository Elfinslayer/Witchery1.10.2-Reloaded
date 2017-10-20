package com.emoniph.witchery.integration;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.HarvestType;
import powercrystals.minefactoryreloaded.api.IFactoryHarvestable;

public class MFRHarvestable implements IFactoryHarvestable
{
  private Block source;
  private HarvestType harvestType;
  private int stages;
  
  public MFRHarvestable(Block source, HarvestType harvestType, int stages)
  {
    this.source = source;
    this.harvestType = harvestType;
    this.stages = stages;
  }
  
  public Block getPlant()
  {
    return source;
  }
  
  public HarvestType getHarvestType()
  {
    return harvestType;
  }
  
  public boolean breakBlock()
  {
    return stages == 0;
  }
  
  public boolean canBeHarvested(World world, Map<String, Boolean> harvesterSettings, int x, int y, int z)
  {
    return (stages == 0) || ((stages > 0) && (world.func_72805_g(x, y, z) == stages));
  }
  
  public java.util.List<ItemStack> getDrops(World world, Random rand, Map<String, Boolean> harvesterSettings, int x, int y, int z)
  {
    if ((harvesterSettings.get("silkTouch") != null) && (((Boolean)harvesterSettings.get("silkTouch")).booleanValue()) && (harvestType == HarvestType.TreeLeaf)) {
      ArrayList<ItemStack> drops = new ArrayList();
      drops.add(new ItemStack(source, 1, world.func_72805_g(x, y, z) & 0x3));
      return drops;
    }
    return source.getDrops(world, x, y, z, world.func_72805_g(x, y, z), 0);
  }
  


  public void preHarvest(World world, int x, int y, int z) {}
  

  public void postHarvest(World world, int x, int y, int z)
  {
    if ((stages > 0) && (world.func_72805_g(x, y, z) == stages)) {
      world.func_147468_f(x, y, z);
    }
  }
}
