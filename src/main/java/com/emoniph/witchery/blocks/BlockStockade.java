package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.common.CommonProxy;
import com.emoniph.witchery.util.MultiItemBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockStockade
  extends BlockBase
{
  public static final String[] WOOD_TEXTURES = { "log_oak", "log_spruce", "log_birch", "log_jungle", "witchery:log_rowan", "witchery:log_alder", "witchery:log_hawthorn", "log_acacia", "log_big_oak" };
  


  public static final String[] WOOD_NAMES = { "oak", "spruce", "birch", "jungle", "rowan", "alder", "hawthorn", "acacia", "big_oak" };
  

  public static final String[] ICE_TEXTURES = { "ice" };
  
  public static final String[] ICE_NAMES = { "ice" };
  @SideOnly(Side.CLIENT)
  private IIcon[] tree;
  
  public static class ClassItemBlock extends MultiItemBlock { public ClassItemBlock(Block block) { super(); }
    

    protected String[] getNames()
    {
      return field_150939_a).alpha ? BlockStockade.ICE_NAMES : BlockStockade.WOOD_NAMES;
    }
  }
  
  public int func_149692_a(int metadata) {
    if (metadata >= 0) { if (metadata < (alpha ? ICE_NAMES.length : WOOD_NAMES.length)) {}
    } else { metadata = 0;
    }
    return metadata;
  }
  

  @SideOnly(Side.CLIENT)
  private IIcon[] tree_top;
  
  private final boolean alpha;
  
  private boolean tipTexturing;
  public BlockStockade(boolean alpha)
  {
    super(alpha ? Material.field_151588_w : Material.field_151575_d, ClassItemBlock.class);
    
    func_149711_c(25.0F);
    func_149752_b(20.0F);
    
    this.alpha = alpha;
  }
  
  public int func_149645_b()
  {
    return Witchery.proxy.getStockageRenderId();
  }
  
  public void func_149724_b(World world, int x, int y, int z, Entity entity)
  {
    if ((!field_72995_K) && ((entity instanceof EntityLivingBase))) {
      EntityLivingBase living = (EntityLivingBase)entity;
      living.func_70097_a(DamageSource.field_76367_g, 3.0F);
    }
  }
  

  public void func_149743_a(World world, int x, int y, int z, AxisAlignedBB bb, List list, Entity entity)
  {
    boolean connectN = canConnectFenceTo(world, x, y, z - 1);
    boolean connectS = canConnectFenceTo(world, x, y, z + 1);
    boolean connectW = canConnectFenceTo(world, x - 1, y, z);
    boolean connectE = canConnectFenceTo(world, x + 1, y, z);
    float f = 0.375F;
    float f1 = 0.625F;
    float f2 = 0.375F;
    float f3 = 0.625F;
    
    if (connectN) {
      f2 = 0.0F;
    }
    
    if (connectS) {
      f3 = 1.0F;
    }
    
    if ((!connectN) && (!connectS) && (!connectE) && (!connectW)) {
      func_149676_a(0.3F, 0.0F, 0.3F, 0.7F, 0.9F, 0.7F);
      super.func_149743_a(world, x, y, z, bb, list, entity);
    }
    
    if ((connectN) || (connectS)) {
      func_149676_a(0.3F, 0.0F, 0.05F, 0.7F, (connectE) || (connectW) ? 1.0F : 0.9F, 0.95F);
      super.func_149743_a(world, x, y, z, bb, list, entity);
    }
    
    if ((connectE) || (connectW)) {
      func_149676_a(0.05F, 0.0F, 0.3F, 0.55F, (connectN) || (connectS) ? 1.0F : 0.9F, 0.7F);
      super.func_149743_a(world, x, y, z, bb, list, entity);
    }
  }
  
  public void func_149719_a(IBlockAccess world, int x, int y, int z)
  {
    boolean connectN = canConnectFenceTo(world, x, y, z - 1);
    boolean connectS = canConnectFenceTo(world, x, y, z + 1);
    boolean connectW = canConnectFenceTo(world, x - 1, y, z);
    boolean connectE = canConnectFenceTo(world, x + 1, y, z);
    float f = 0.3F;
    float f1 = 0.3F;
    float f2 = 0.7F;
    float f3 = 0.7F;
    
    if ((connectN) || (connectS))
    {
      f1 = 0.05F;
      f3 = 0.95F;
    }
    
    if ((connectE) || (connectW)) {
      f = 0.05F;
      f2 = 0.95F;
    }
    
    func_149676_a(f, 0.0F, f1, f2, ((connectN) || (connectS)) && ((connectW) || (connectE)) ? 1.0F : 0.9F, f3);
  }
  
  public boolean func_149662_c()
  {
    return false;
  }
  
  public boolean func_149686_d()
  {
    return false;
  }
  
  public int func_149701_w()
  {
    return alpha ? 1 : super.func_149701_w();
  }
  
  public boolean func_149655_b(IBlockAccess p_149655_1_, int p_149655_2_, int p_149655_3_, int p_149655_4_)
  {
    return false;
  }
  
  public boolean canConnectFenceTo(IBlockAccess p_149826_1_, int p_149826_2_, int p_149826_3_, int p_149826_4_) {
    Block block = p_149826_1_.func_147439_a(p_149826_2_, p_149826_3_, p_149826_4_);
    return (block == this) || (block == Blocks.field_150396_be) || (block == BlocksPERPETUAL_ICE_FENCE_GATE);
  }
  

  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side)
  {
    if (world.func_147439_a(x, y, z) == this)
    {






      if (side == 1) {
        boolean aboveX = (world.func_147439_a(x + 1, y, z) == this) || (world.func_147439_a(x - 1, y, z) == this);
        boolean aboveZ = (world.func_147439_a(x, y, z + 1) == this) || (world.func_147439_a(x, y, z - 1) == this);
        boolean sideX = (world.func_147439_a(x + 1, y - 1, z) == this) || (world.func_147439_a(x - 1, y - 1, z) == this);
        boolean sideZ = (world.func_147439_a(x, y - 1, z + 1) == this) || (world.func_147439_a(x, y - 1, z - 1) == this);
        
        if ((aboveX) && (sideX) && (aboveZ) && (sideZ))
          return false;
        if ((sideX) && (!aboveX))
          return true;
        if ((sideZ) && (!aboveZ)) {
          return true;
        }
        return false;
      }
      if (side == 0) {
        boolean sideX = (world.func_147439_a(x + 1, y, z) == this) || (world.func_147439_a(x - 1, y, z) == this);
        boolean sideZ = (world.func_147439_a(x, y, z + 1) == this) || (world.func_147439_a(x, y, z - 1) == this);
        boolean aboveX = (world.func_147439_a(x + 1, y + 1, z) == this) || (world.func_147439_a(x - 1, y + 1, z) == this);
        boolean aboveZ = (world.func_147439_a(x, y + 1, z + 1) == this) || (world.func_147439_a(x, y + 1, z - 1) == this);
        
        if ((aboveX) && (sideX) && (aboveZ) && (sideZ))
          return false;
        if ((sideX) && (!aboveX))
          return true;
        if ((sideZ) && (!aboveZ)) {
          return true;
        }
        return false;
      }
    }
    

    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    if (meta >= 0) { if (meta < (alpha ? ICE_TEXTURES.length : WOOD_TEXTURES.length)) {}
    } else { meta = 0;
    }
    return (side == 1) || (side == 0) || (tipTexturing) ? tree_top[meta] : tree[meta];
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item block, CreativeTabs creativeTabs, List list)
  {
    for (int i = 0; i < (alpha ? ICE_TEXTURES.length : WOOD_TEXTURES.length); i++) {
      list.add(new ItemStack(this, 1, i));
    }
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iconRegister)
  {
    tree = new IIcon[alpha ? ICE_TEXTURES.length : WOOD_TEXTURES.length];
    tree_top = new IIcon[alpha ? ICE_TEXTURES.length : WOOD_TEXTURES.length];
    
    if (alpha) {
      for (int i = 0; i < tree.length; i++) {
        tree[i] = iconRegister.func_94245_a(ICE_TEXTURES[i]);
        tree_top[i] = iconRegister.func_94245_a(ICE_TEXTURES[i] + (ICE_TEXTURES[i].equals("ice") ? "" : "_top"));
      }
      
    } else {
      for (int i = 0; i < tree.length; i++) {
        tree[i] = iconRegister.func_94245_a(WOOD_TEXTURES[i]);
        tree_top[i] = iconRegister.func_94245_a(WOOD_TEXTURES[i] + "_top");
      }
    }
  }
  

  public void setTipTexture(boolean b)
  {
    tipTexturing = b;
  }
}
