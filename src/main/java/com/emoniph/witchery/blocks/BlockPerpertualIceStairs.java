package com.emoniph.witchery.blocks;

import com.emoniph.witchery.WitcheryCreativeTab;
import com.emoniph.witchery.util.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockPerpertualIceStairs extends BlockStairs
{
  public BlockPerpertualIceStairs(Block baseBlock, int baseMeta)
  {
    super(baseBlock, baseMeta);
    field_149765_K = 0.98F;
    func_149713_g(3);
    func_149711_c(2.0F);
    func_149752_b(5.0F);
    func_149647_a(WitcheryCreativeTab.INSTANCE);
  }
  
  public Block func_149663_c(String blockName)
  {
    BlockUtil.registerBlock(this, blockName);
    super.func_149663_c(blockName);
    return this;
  }
}
