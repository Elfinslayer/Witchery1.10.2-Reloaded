package com.emoniph.witchery.item;

import com.emoniph.witchery.util.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class ItemBucketSpirit extends ItemBase
{
  private Block fluidBlock = net.minecraft.init.Blocks.field_150350_a;
  
  public ItemBucketSpirit()
  {
    func_77625_d(1);
    func_77656_e(0);
  }
  
  public ItemBucketSpirit setFluidBlock(Block fluidBlock) {
    this.fluidBlock = fluidBlock;
    return this;
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player)
  {
    MovingObjectPosition movingobjectposition = func_77621_a(world, player, true);
    if (movingobjectposition == null) {
      return item;
    }
    if (field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
      int x = field_72311_b;
      int y = field_72312_c;
      int z = field_72309_d;
      
      if (!world.func_72962_a(player, x, y, z)) {
        return item;
      }
      
      if (field_72310_e == 0) {
        y--;
      }
      
      if (field_72310_e == 1) {
        y++;
      }
      
      if (field_72310_e == 2) {
        z--;
      }
      
      if (field_72310_e == 3) {
        z++;
      }
      
      if (field_72310_e == 4) {
        x--;
      }
      
      if (field_72310_e == 5) {
        x++;
      }
      
      if (!player.func_82247_a(x, y, z, field_72310_e, item)) {
        return item;
      }
      
      if ((tryPlaceContainedLiquid(world, x, y, z)) && (!field_71075_bZ.field_75098_d)) {
        return new ItemStack(Items.field_151133_ar);
      }
    }
    

    return item;
  }
  
  private boolean tryPlaceContainedLiquid(World world, int x, int y, int z)
  {
    Material material = world.func_147439_a(x, y, z).func_149688_o();
    boolean flag = !material.func_76220_a();
    
    if ((!world.func_147437_c(x, y, z)) && (!flag)) {
      return false;
    }
    
    if ((!field_72995_K) && (flag) && (!material.func_76224_d())) {
      world.func_147480_a(x, y, z, true);
    }
    
    BlockUtil.setBlock(world, x, y, z, fluidBlock, 0, 3);
    
    return true;
  }
}
