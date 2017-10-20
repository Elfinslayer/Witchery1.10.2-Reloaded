package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.util.ParticleEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCandelabra extends BlockBaseContainer
{
  public BlockCandelabra()
  {
    super(Material.field_151574_g, TileEntityCandelabra.class);
    registerWithCreateTab = false;
    
    func_149715_a(1.0F);
    func_149711_c(2.0F);
    func_149672_a(field_149777_j);
    
    func_149676_a(0.1F, 0.0F, 0.1F, 0.9F, 1.0F, 0.9F);
  }
  
  public boolean func_149662_c()
  {
    return false;
  }
  
  public boolean func_149686_d()
  {
    return false;
  }
  
  public int func_149745_a(Random rand)
  {
    return 1;
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
  {
    return ItemsGENERIC;
  }
  
  public int func_149692_a(int par1)
  {
    return ItemsGENERIC.itemCandelabra.damageValue;
  }
  
  public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5)
  {
    func_111046_k(par1World, par2, par3, par4);
  }
  
  private boolean func_111046_k(World par1World, int par2, int par3, int par4) {
    if (!func_149718_j(par1World, par2, par3, par4)) {
      if (!field_72995_K) {
        func_149697_b(par1World, par2, par3, par4, par1World.func_72805_g(par2, par3, par4), 0);
        par1World.func_147468_f(par2, par3, par4);
      }
      return false;
    }
    return true;
  }
  

  public net.minecraft.item.ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
  {
    return ItemsGENERIC.itemCandelabra.createStack();
  }
  
  public boolean func_149718_j(World world, int x, int y, int z)
  {
    Material material = world.func_147439_a(x, y - 1, z).func_149688_o();
    return (!world.func_147437_c(x, y - 1, z)) && (material != null) && (material.func_76218_k()) && (material.func_76220_a());
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
  {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random rand)
  {
    double yMid = y + 1.05D;
    double yOuter = y + 0.9D;
    double mid = 0.5D;
    double near = 0.2D;
    double far = 0.8D;
    
    if (rand.nextInt(4) != 0) {
      world.func_72869_a(ParticleEffect.FLAME.toString(), x + 0.5D, yMid, z + 0.5D, 0.0D, 0.0D, 0.0D);
      world.func_72869_a(ParticleEffect.SMOKE.toString(), x + 0.5D, yMid, z + 0.5D, 0.0D, 0.0D, 0.0D);
    }
    
    if (rand.nextInt(4) != 0) {
      world.func_72869_a(ParticleEffect.FLAME.toString(), x + 0.8D, yOuter, z + 0.5D, 0.0D, 0.0D, 0.0D);
      world.func_72869_a(ParticleEffect.SMOKE.toString(), x + 0.8D, yOuter, z + 0.5D, 0.0D, 0.0D, 0.0D);
    }
    
    if (rand.nextInt(4) != 0) {
      world.func_72869_a(ParticleEffect.FLAME.toString(), x + 0.2D, yOuter, z + 0.5D, 0.0D, 0.0D, 0.0D);
      world.func_72869_a(ParticleEffect.SMOKE.toString(), x + 0.2D, yOuter, z + 0.5D, 0.0D, 0.0D, 0.0D);
    }
    
    if (rand.nextInt(4) != 0) {
      world.func_72869_a(ParticleEffect.FLAME.toString(), x + 0.5D, yOuter, z + 0.8D, 0.0D, 0.0D, 0.0D);
      world.func_72869_a(ParticleEffect.SMOKE.toString(), x + 0.5D, yOuter, z + 0.8D, 0.0D, 0.0D, 0.0D);
    }
    
    if (rand.nextInt(4) != 0) {
      world.func_72869_a(ParticleEffect.FLAME.toString(), x + 0.5D, yOuter, z + 0.2D, 0.0D, 0.0D, 0.0D);
      world.func_72869_a(ParticleEffect.SMOKE.toString(), x + 0.5D, yOuter, z + 0.2D, 0.0D, 0.0D, 0.0D);
    }
  }
  
  public static class TileEntityCandelabra extends TileEntity {
    public TileEntityCandelabra() {}
    
    public boolean canUpdate() { return false; }
  }
}
