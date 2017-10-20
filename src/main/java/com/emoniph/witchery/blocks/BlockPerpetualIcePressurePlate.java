package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryCreativeTab;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.util.BlockUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.BlockPressurePlate.Sensitivity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockPerpetualIcePressurePlate extends BlockPressurePlate
{
  public BlockPerpetualIcePressurePlate(Material material)
  {
    super(material == Material.field_151588_w ? "ice" : "snow", material, BlockPressurePlate.Sensitivity.everything);
    func_149647_a(WitcheryCreativeTab.INSTANCE);
    if (material == Material.field_151588_w) {
      func_149713_g(3);
      func_149711_c(2.0F);
      func_149752_b(5.0F);
    } else {
      func_149711_c(0.2F);
      func_149672_a(field_149773_n);
    }
  }
  
  public Block func_149663_c(String blockName)
  {
    BlockUtil.registerBlock(this, blockName);
    return super.func_149663_c(blockName);
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149701_w()
  {
    return field_149764_J == Material.field_151588_w ? 1 : 0;
  }
  
  protected int func_150065_e(World world, int x, int y, int z)
  {
    if (field_149764_J == Material.field_151588_w) {
      java.util.List<EntityLivingBase> list = world.func_72872_a(EntityLivingBase.class, func_150061_a(x, y, z));
      
      for (EntityLivingBase entity : list) {
        ItemStack footwear = entity.func_71124_b(1);
        if ((footwear != null) && (footwear.func_77973_b() == ItemsICY_SLIPPERS)) {
          return 15;
        }
      }
      return 0;
    }
    return super.func_150065_e(world, x, y, z);
  }
  
  public boolean func_149742_c(World world, int x, int y, int z)
  {
    return (super.func_149742_c(world, x, y, z)) || (world.func_147439_a(x, y - 1, z) == BlocksPERPETUAL_ICE_FENCE);
  }
  
  public void func_149695_a(World world, int x, int y, int z, Block block)
  {
    if (!func_149742_c(world, x, y, z)) {
      func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
      world.func_147468_f(x, y, z);
    }
  }
}
