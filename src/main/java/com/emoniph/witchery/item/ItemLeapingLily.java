package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.ItemColored;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class ItemLeapingLily extends ItemColored
{
  public ItemLeapingLily(Block par1)
  {
    super(par1, false);
  }
  
  public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
    MovingObjectPosition movingobjectposition = func_77621_a(par2World, par3EntityPlayer, true);
    
    if (movingobjectposition == null) {
      return par1ItemStack;
    }
    if (field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
      int i = field_72311_b;
      int j = field_72312_c;
      int k = field_72309_d;
      
      if (!par2World.func_72962_a(par3EntityPlayer, i, j, k)) {
        return par1ItemStack;
      }
      
      if (!par3EntityPlayer.func_82247_a(i, j, k, field_72310_e, par1ItemStack)) {
        return par1ItemStack;
      }
      
      if ((par2World.func_147439_a(i, j, k).func_149688_o() == net.minecraft.block.material.Material.field_151586_h) && (par2World.func_72805_g(i, j, k) == 0) && (par2World.func_147437_c(i, j + 1, k)))
      {
        par2World.func_147449_b(i, j + 1, k, BlocksLEAPING_LILY);
        
        if (!field_71075_bZ.field_75098_d) {
          field_77994_a -= 1;
        }
      }
    }
    
    return par1ItemStack;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_82790_a(ItemStack par1ItemStack, int par2)
  {
    return net.minecraft.init.Blocks.field_150392_bi.func_149741_i(par1ItemStack.func_77960_j());
  }
}
