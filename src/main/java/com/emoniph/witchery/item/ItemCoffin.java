package com.emoniph.witchery.item;

import com.emoniph.witchery.blocks.BlockCoffin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCoffin extends ItemBase
{
  public ItemCoffin() {}
  
  public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
  {
    if (field_72995_K)
      return true;
    if (side != 1) {
      return false;
    }
    y++;
    BlockCoffin coffin = BlocksCOFFIN;
    int i1 = net.minecraft.util.MathHelper.func_76128_c(field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
    byte b0 = 0;
    byte b1 = 0;
    
    if (i1 == 0) {
      b1 = 1;
    }
    
    if (i1 == 1) {
      b0 = -1;
    }
    
    if (i1 == 2) {
      b1 = -1;
    }
    
    if (i1 == 3) {
      b0 = 1;
    }
    
    if ((player.func_82247_a(x, y, z, side, stack)) && (player.func_82247_a(x + b0, y, z + b1, side, stack))) {
      if ((world.func_147437_c(x, y, z)) && (world.func_147437_c(x + b0, y, z + b1)) && (World.func_147466_a(world, x, y - 1, z)) && (World.func_147466_a(world, x + b0, y - 1, z + b1)))
      {

        world.func_147465_d(x, y, z, coffin, i1, 3);
        
        if (world.func_147439_a(x, y, z) == coffin) {
          world.func_147465_d(x + b0, y, z + b1, coffin, i1 + 8, 3);
        }
        
        field_77994_a -= 1;
        return true;
      }
      return false;
    }
    
    return false;
  }
}
