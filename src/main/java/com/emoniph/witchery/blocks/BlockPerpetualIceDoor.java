package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockPerpetualIceDoor extends BlockDoor
{
  public BlockPerpetualIceDoor()
  {
    super(net.minecraft.block.material.Material.field_151588_w);
    func_149649_H();
    
    func_149711_c(2.0F);
    func_149752_b(5.0F);
  }
  
  public Block func_149663_c(String blockName)
  {
    com.emoniph.witchery.util.BlockUtil.registerBlock(this, blockName);
    return super.func_149663_c(blockName);
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149701_w()
  {
    return 1;
  }
  

  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
  {
    ArrayList<ItemStack> ret = new ArrayList();
    
    int count = quantityDropped(metadata, fortune, field_73012_v);
    for (int i = 0; i < count; i++) {
      ret.add(ItemsGENERIC.itemDoorIce.createStack());
    }
    return ret;
  }
  
  public boolean func_149742_c(World world, int x, int y, int z)
  {
    return (super.func_149742_c(world, x, y, z)) || (world.func_147439_a(x, y - 1, z) == BlocksPERPETUAL_ICE);
  }
  

  public void func_149695_a(World world, int x, int y, int z, Block block)
  {
    int l = world.func_72805_g(x, y, z);
    
    if ((l & 0x8) == 0) {
      boolean flag = false;
      
      if (world.func_147439_a(x, y + 1, z) != this) {
        world.func_147468_f(x, y, z);
        flag = true;
      }
      
      if ((!World.func_147466_a(world, x, y - 1, z)) && (!func_149718_j(world, x, y - 1, z))) {
        world.func_147468_f(x, y, z);
        flag = true;
        
        if (world.func_147439_a(x, y + 1, z) == this) {
          world.func_147468_f(x, y + 1, z);
        }
      }
      
      if (flag) {
        if (!field_72995_K) {
          func_149697_b(world, x, y, z, l, 0);
        }
      } else {
        boolean flag1 = (world.func_72864_z(x, y, z)) || (world.func_72864_z(x, y + 1, z));
        

        if (((flag1) || (block.func_149744_f())) && (block != this)) {
          func_150014_a(world, x, y, z, flag1);
        }
      }
    } else {
      if (world.func_147439_a(x, y - 1, z) != this) {
        world.func_147468_f(x, y, z);
      }
      
      if (block != this) {
        func_149695_a(world, x, y - 1, z, block);
      }
    }
  }
}
