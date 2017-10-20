package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
import com.emoniph.witchery.util.Config;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class BlockCotton extends BlockBaseBush
{
  public BlockCotton()
  {
    super(net.minecraft.block.material.Material.field_151585_k);
    
    func_149711_c(0.0F);
    func_149672_a(field_149779_h).func_149675_a(true);
  }
  


  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
  {
    if ((field_73011_w.field_76574_g == instancedimensionDreamID) && ((field_73011_w instanceof WorldProviderDreamWorld)) && (((WorldProviderDreamWorld)field_73011_w).isNightmare()))
    {
      ArrayList<ItemStack> ret = new ArrayList();
      
      int count = quantityDropped(metadata, fortune, field_73012_v);
      for (int i = 0; i < count; i++) {
        ret.add(ItemsGENERIC.itemDisturbedCotton.createStack());
      }
      return ret;
    }
    return super.getDrops(world, x, y, z, metadata, fortune);
  }
  
  public boolean canBlockSpread(World world, int posX, int posY, int posZ)
  {
    if ((field_73011_w.field_76574_g == instancedimensionDreamID) && (func_149718_j(world, posX, posY, posZ))) {
      Block blockBelow = world.func_147439_a(posX, posY - 1, posZ);
      return (blockBelow == net.minecraft.init.Blocks.field_150346_d) || (blockBelow == net.minecraft.init.Blocks.field_150349_c);
    }
    return false;
  }
  
  private boolean isBlockMatch(World world, int x, int y, int z, Block block, int metadata) {
    return (world.func_147439_a(x, y, z) == block) && (world.func_72805_g(x, y, z) == metadata);
  }
  
  public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random)
  {
    if ((!field_72995_K) && (field_73012_v.nextInt(6) == 0)) {
      if ((field_73011_w.field_76574_g != instancedimensionDreamID) || ((!isBlockMatch(par1World, par2 + 1, par3 - 1, par4, BlocksFLOWING_SPIRIT, 0)) && (!isBlockMatch(par1World, par2 - 1, par3 - 1, par4, BlocksFLOWING_SPIRIT, 0)) && (!isBlockMatch(par1World, par2, par3 - 1, par4 + 1, BlocksFLOWING_SPIRIT, 0)) && (!isBlockMatch(par1World, par2, par3 - 1, par4 - 1, BlocksFLOWING_SPIRIT, 0))))
      {



        return;
      }
      byte b0 = 4;
      int l = 5;
      



      for (int i1 = par2 - b0; i1 <= par2 + b0; i1++) {
        for (int j1 = par4 - b0; j1 <= par4 + b0; j1++) {
          for (int k1 = par3 - 1; k1 <= par3 + 1; k1++) {
            if (par1World.func_147439_a(i1, k1, j1) == this) {
              l--;
              
              if (l <= 0) {
                return;
              }
            }
          }
        }
      }
      
      i1 = par2 + par5Random.nextInt(3) - 1;
      int j1 = par3 + par5Random.nextInt(2) - par5Random.nextInt(2);
      int k1 = par4 + par5Random.nextInt(3) - 1;
      
      for (int l1 = 0; l1 < 4; l1++) {
        if ((par1World.func_147437_c(i1, j1, k1)) && (canBlockSpread(par1World, i1, j1, k1))) {
          par2 = i1;
          par3 = j1;
          par4 = k1;
        }
        
        i1 = par2 + par5Random.nextInt(3) - 1;
        j1 = par3 + par5Random.nextInt(2) - par5Random.nextInt(2);
        k1 = par4 + par5Random.nextInt(3) - 1;
      }
      
      if ((par1World.func_147437_c(i1, j1, k1)) && (canBlockSpread(par1World, i1, j1, k1))) {
        par1World.func_147465_d(i1, j1, k1, this, 0, 2);
      }
    }
  }
}
