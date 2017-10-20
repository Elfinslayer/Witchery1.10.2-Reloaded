package com.emoniph.witchery.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockStairs;

public class BlockWitchWoodStairs extends BlockStairs
{
  private final int encouragement;
  private final int flammibility;
  
  public BlockWitchWoodStairs(Block baseBlock, int baseMeta, int encouragement, int flammibility)
  {
    super(baseBlock, baseMeta);
    this.flammibility = flammibility;
    this.encouragement = encouragement;
    func_149647_a(com.emoniph.witchery.WitcheryCreativeTab.INSTANCE);
  }
  
  public Block func_149663_c(String blockName)
  {
    com.emoniph.witchery.util.BlockUtil.registerBlock(this, blockName);
    super.func_149663_c(blockName);
    net.minecraft.init.Blocks.field_150480_ab.setFireInfo(this, encouragement, flammibility);
    return this;
  }
}
