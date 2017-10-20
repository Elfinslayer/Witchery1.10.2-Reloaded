package com.emoniph.witchery.blocks;

import net.minecraft.world.World;

public class BlockLight extends BlockBase
{
  public BlockLight()
  {
    super(net.minecraft.block.material.Material.field_151579_a);
    func_149715_a(1.0F);
    registerWithCreateTab = false;
  }
  
  public int func_149645_b()
  {
    return -1;
  }
  
  public net.minecraft.util.AxisAlignedBB func_149668_a(World world, int x, int y, int z)
  {
    return null;
  }
  
  public boolean func_149662_c()
  {
    return false;
  }
  
  public boolean func_149678_a(int p_149678_1_, boolean p_149678_2_)
  {
    return false;
  }
  
  public void func_149690_a(World world, int x, int y, int z, int side, float p_149690_6_, int p_149690_7_) {}
}
