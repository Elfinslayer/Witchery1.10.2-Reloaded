package com.emoniph.witchery.brewing;

import com.emoniph.witchery.blocks.BlockBaseContainer;
import com.emoniph.witchery.util.BlockUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockPressurePlate.Sensitivity;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;







public class BlockPressurePlateBase
  extends BlockBaseContainer
{
  private BlockPressurePlate.Sensitivity sensitivity;
  private String textureName;
  private Block original;
  
  public BlockPressurePlateBase(Block original, String textureName, BlockPressurePlate.Sensitivity sensitivity)
  {
    super(original.func_149688_o(), TileEntityCursedBlock.class);
    this.textureName = textureName;
    this.sensitivity = sensitivity;
    this.original = original;
    func_149711_c(0.5F);
    func_149672_a(field_149762_H);
    func_149675_a(true);
    func_150063_b(func_150066_d(15));
    registerWithCreateTab = false;
  }
  
  public void replaceButton(World world, int x, int y, int z, ModifiersImpact impactModifiers, NBTTagCompound nbtBrew)
  {
    int meta = world.func_72805_g(x, y, z);
    world.func_147465_d(x, y, z, this, meta, 3);
    world.func_147464_a(x, y, z, this, func_149738_a(world));
    TileEntityCursedBlock tile = (TileEntityCursedBlock)BlockUtil.getTileEntity(world, x, y, z, TileEntityCursedBlock.class);
    if (tile != null) {
      tile.initalise(impactModifiers, nbtBrew);
    }
  }
  
  public void func_149719_a(IBlockAccess world, int x, int y, int z)
  {
    func_150063_b(world.func_72805_g(x, y, z));
  }
  
  protected void func_150063_b(int p_150063_1_) {
    boolean flag = func_150060_c(p_150063_1_) > 0;
    float f = 0.0625F;
    
    if (flag) {
      func_149676_a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
    } else {
      func_149676_a(f, 0.0F, f, 1.0F - f, 0.0625F, 1.0F - f);
    }
  }
  
  public int func_149738_a(World world)
  {
    return 20;
  }
  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
  {
    return null;
  }
  
  public boolean func_149662_c()
  {
    return false;
  }
  
  public boolean func_149686_d()
  {
    return false;
  }
  
  public boolean func_149655_b(IBlockAccess world, int x, int y, int z)
  {
    return true;
  }
  
  public boolean func_149742_c(World world, int x, int y, int z)
  {
    return (World.func_147466_a(world, x, y - 1, z)) || (BlockFence.func_149825_a(world.func_147439_a(x, y - 1, z)));
  }
  

  public void func_149695_a(World world, int x, int y, int z, Block p_149695_5_)
  {
    boolean flag = false;
    
    if ((!World.func_147466_a(world, x, y - 1, z)) && (!BlockFence.func_149825_a(world.func_147439_a(x, y - 1, z))))
    {
      flag = true;
    }
    
    if (flag) {
      func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
      world.func_147468_f(x, y, z);
    }
  }
  
  public void func_149674_a(World world, int x, int y, int z, Random rand)
  {
    if (!field_72995_K) {
      int l = func_150060_c(world.func_72805_g(x, y, z));
      
      if (l > 0) {
        func_150062_a(world, x, y, z, l);
      }
    }
  }
  
  public void func_149670_a(World world, int x, int y, int z, Entity entity)
  {
    if (!field_72995_K) {
      int metadata = world.func_72805_g(x, y, z);
      int l = func_150060_c(metadata);
      
      if (l == 0)
      {
        int i1 = func_150065_e(world, x, y, z);
        boolean flag = l > 0;
        boolean flag1 = i1 > 0;
        
        if (l != i1) {
          int md = func_150066_d(i1);
          

          if (!field_72995_K) {
            TileEntityCursedBlock tile = (TileEntityCursedBlock)BlockUtil.getTileEntity(world, x, y, z, TileEntityCursedBlock.class);
            
            if ((tile != null) && 
              (!tile.applyToEntityAndDestroy(entity))) {
              world.func_147449_b(x, y, z, original);
              world.func_72921_c(x, y, z, md, 2);
              func_150064_a_(world, x, y, z);
              world.func_147458_c(x, y, z, x, y, z);
              if ((!flag1) && (flag)) {
                world.func_72908_a(x + 0.5D, y + 0.1D, z + 0.5D, "random.click", 0.3F, 0.5F);
              } else if ((flag1) && (!flag)) {
                world.func_72908_a(x + 0.5D, y + 0.1D, z + 0.5D, "random.click", 0.3F, 0.6F);
              }
              
              if (flag1) {
                world.func_147464_a(x, y, z, original, func_149738_a(world));
              }
              return;
            }
          }
          

          world.func_72921_c(x, y, z, md, 2);
          func_150064_a_(world, x, y, z);
          world.func_147458_c(x, y, z, x, y, z);
        }
        
        if ((!flag1) && (flag)) {
          world.func_72908_a(x + 0.5D, y + 0.1D, z + 0.5D, "random.click", 0.3F, 0.5F);
        } else if ((flag1) && (!flag)) {
          world.func_72908_a(x + 0.5D, y + 0.1D, z + 0.5D, "random.click", 0.3F, 0.6F);
        }
        
        if (flag1) {
          world.func_147464_a(x, y, z, this, func_149738_a(world));
        }
      }
    }
  }
  
  protected void func_150062_a(World world, int x, int y, int z, int p_150062_5_) {
    int i1 = func_150065_e(world, x, y, z);
    boolean flag = p_150062_5_ > 0;
    boolean flag1 = i1 > 0;
    
    if (p_150062_5_ != i1) {
      int metadata = func_150066_d(i1);
      world.func_72921_c(x, y, z, metadata, 2);
      func_150064_a_(world, x, y, z);
      world.func_147458_c(x, y, z, x, y, z);
    }
    
    if ((!flag1) && (flag)) {
      world.func_72908_a(x + 0.5D, y + 0.1D, z + 0.5D, "random.click", 0.3F, 0.5F);
    } else if ((flag1) && (!flag)) {
      world.func_72908_a(x + 0.5D, y + 0.1D, z + 0.5D, "random.click", 0.3F, 0.6F);
    }
    
    if (flag1) {
      world.func_147464_a(x, y, z, this, func_149738_a(world));
    }
  }
  
  protected AxisAlignedBB func_150061_a(int x, int y, int z) {
    float f = 0.125F;
    return AxisAlignedBB.func_72330_a(x + f, y, z + f, x + 1 - f, y + 0.25D, z + 1 - f);
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int p_149749_6_)
  {
    if (func_150060_c(p_149749_6_) > 0) {
      func_150064_a_(world, x, y, z);
    }
    
    super.func_149749_a(world, x, y, z, block, p_149749_6_);
  }
  
  protected void func_150064_a_(World world, int x, int y, int z) {
    world.func_147459_d(x, y, z, this);
    world.func_147459_d(x, y - 1, z, this);
  }
  
  public int func_149709_b(IBlockAccess world, int x, int y, int z, int p_149709_5_)
  {
    return func_150060_c(world.func_72805_g(x, y, z));
  }
  
  public int func_149748_c(IBlockAccess world, int x, int y, int z, int p_149748_5_)
  {
    return p_149748_5_ == 1 ? func_150060_c(world.func_72805_g(x, y, z)) : 0;
  }
  
  public boolean func_149744_f()
  {
    return true;
  }
  
  public void func_149683_g()
  {
    float f = 0.5F;
    float f1 = 0.125F;
    float f2 = 0.5F;
    func_149676_a(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1, 0.5F + f2);
  }
  
  public int func_149656_h()
  {
    return 1;
  }
  
  protected int func_150066_d(int p_150066_1_) {
    return p_150066_1_ > 0 ? 1 : 0;
  }
  
  protected int func_150060_c(int p_150060_1_) {
    return p_150060_1_ == 1 ? 15 : 0;
  }
  
  protected int func_150065_e(World world, int x, int y, int z) {
    List list = null;
    
    if (sensitivity == BlockPressurePlate.Sensitivity.everything) {
      list = world.func_72839_b((Entity)null, func_150061_a(x, y, z));
    }
    
    if (sensitivity == BlockPressurePlate.Sensitivity.mobs) {
      list = world.func_72872_a(EntityLivingBase.class, func_150061_a(x, y, z));
    }
    
    if (sensitivity == BlockPressurePlate.Sensitivity.players) {
      list = world.func_72872_a(EntityPlayer.class, func_150061_a(x, y, z));
    }
    
    if ((list != null) && (!list.isEmpty())) {
      Iterator iterator = list.iterator();
      
      while (iterator.hasNext()) {
        Entity entity = (Entity)iterator.next();
        
        if (!entity.func_145773_az()) {
          return 15;
        }
      }
    }
    
    return 0;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iconRegister)
  {
    field_149761_L = iconRegister.func_94245_a(textureName);
  }
  
  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
  {
    return new ItemStack(original);
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
  {
    return Item.func_150898_a(original);
  }
}
