package com.emoniph.witchery.blocks;

import com.emoniph.witchery.util.ParticleEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockGlintWeed
  extends BlockBaseBush
{
  public BlockGlintWeed()
  {
    super(Material.field_151585_k);
    
    func_149715_a(0.9375F);
    func_149711_c(0.0F);
    func_149672_a(Block.field_149779_h);
    func_149649_H();
    func_149675_a(true);
    
    float f = 0.45F;
    func_149676_a(0.050000012F, 0.0F, 0.050000012F, 0.95F, 1.0F, 0.95F);
  }
  
  public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random)
  {
    if ((!field_72995_K) && (field_73012_v.nextInt(6) == 0)) {
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
  
  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random rand)
  {
    double d0 = x + 0.4F + rand.nextInt(3) * 0.1F;
    double d1 = y + 0.4F + rand.nextInt(3) * 0.1F;
    double d2 = z + 0.4F + rand.nextInt(3) * 0.1F;
    world.func_72869_a(ParticleEffect.FLAME.toString(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
  }
  
  public boolean func_149742_c(World par1World, int par2, int par3, int par4)
  {
    return (super.func_149742_c(par1World, par2, par3, par4)) && (func_149718_j(par1World, par2, par3, par4));
  }
  
  protected boolean func_149854_a(Block block)
  {
    return (block != null) && (block.func_149662_c());
  }
  
  public boolean canBlockSpread(World world, int posX, int posY, int posZ) {
    Block block = world.func_147439_a(posX, posY - 1, posZ);
    return (func_149718_j(world, posX, posY, posZ)) && ((block == Blocks.field_150346_d) || (block == Blocks.field_150349_c) || (block == Blocks.field_150391_bh) || (block == Blocks.field_150354_m) || (block == Blocks.field_150458_ak));
  }
  

  public boolean func_149718_j(World world, int posX, int posY, int posZ)
  {
    Material material = world.func_147439_a(posX, posY - 1, posZ).func_149688_o();
    Material material2 = world.func_147439_a(posX, posY + 1, posZ).func_149688_o();
    return ((material != null) && (material.func_76220_a())) || ((material2 != null) && (material2.func_76220_a()));
  }
}
