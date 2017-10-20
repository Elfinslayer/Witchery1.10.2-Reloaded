package com.emoniph.witchery.brewing;

import com.emoniph.witchery.blocks.BlockBaseContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDoorBase extends BlockBaseContainer
{
  @SideOnly(Side.CLIENT)
  private IIcon[] field_150017_a;
  @SideOnly(Side.CLIENT)
  private IIcon[] field_150016_b;
  
  public BlockDoorBase(Material p_i45402_1_)
  {
    super(p_i45402_1_, TileEntityCursedBlock.class);
    registerWithCreateTab = false;
    func_149711_c(3.0F);
    func_149672_a(field_149766_f);
    
    float f = 0.5F;
    float f1 = 1.0F;
    func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
  }
  
  public void replaceButton(World world, int x, int y, int z, ModifiersImpact impactModifiers, net.minecraft.nbt.NBTTagCompound nbtBrew)
  {
    int meta = world.func_72805_g(x, y, z);
    int i1 = ((BlockDoor)net.minecraft.init.Blocks.field_150466_ao).func_150012_g(world, x, y, z);
    if ((i1 & 0x8) != 0) {
      y--;
    }
    world.func_147468_f(x, y, z);
    world.func_147468_f(x, y + 1, z);
    net.minecraft.item.ItemDoor.func_150924_a(world, x, y, z, i1 & 0x3, this);
    
    TileEntityCursedBlock tile = (TileEntityCursedBlock)com.emoniph.witchery.util.BlockUtil.getTileEntity(world, x, y, z, TileEntityCursedBlock.class);
    if (tile != null) {
      tile.initalise(impactModifiers, nbtBrew);
    }
  }
  




  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int p_149691_1_, int p_149691_2_)
  {
    return field_150016_b[0];
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149673_e(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_)
  {
    if ((p_149673_5_ != 1) && (p_149673_5_ != 0)) {
      int i1 = func_150012_g(p_149673_1_, p_149673_2_, p_149673_3_, p_149673_4_);
      int j1 = i1 & 0x3;
      boolean flag = (i1 & 0x4) != 0;
      boolean flag1 = false;
      boolean flag2 = (i1 & 0x8) != 0;
      
      if (flag) {
        if ((j1 == 0) && (p_149673_5_ == 2)) {
          flag1 = !flag1;
        } else if ((j1 == 1) && (p_149673_5_ == 5)) {
          flag1 = !flag1;
        } else if ((j1 == 2) && (p_149673_5_ == 3)) {
          flag1 = !flag1;
        } else if ((j1 == 3) && (p_149673_5_ == 4)) {
          flag1 = !flag1;
        }
      } else {
        if ((j1 == 0) && (p_149673_5_ == 5)) {
          flag1 = !flag1;
        } else if ((j1 == 1) && (p_149673_5_ == 3)) {
          flag1 = !flag1;
        } else if ((j1 == 2) && (p_149673_5_ == 4)) {
          flag1 = !flag1;
        } else if ((j1 == 3) && (p_149673_5_ == 2)) {
          flag1 = !flag1;
        }
        
        if ((i1 & 0x10) != 0) {
          flag1 = !flag1;
        }
      }
      
      return flag2 ? field_150017_a[0] : field_150016_b[0];
    }
    return field_150016_b[0];
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister p_149651_1_)
  {
    field_150017_a = new IIcon[2];
    field_150016_b = new IIcon[2];
    field_150017_a[0] = p_149651_1_.func_94245_a(func_149641_N() + "_upper");
    field_150016_b[0] = p_149651_1_.func_94245_a(func_149641_N() + "_lower");
    field_150017_a[1] = new IconFlipped(field_150017_a[0], true, false);
    field_150016_b[1] = new IconFlipped(field_150016_b[0], true, false);
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean func_149655_b(IBlockAccess p_149655_1_, int p_149655_2_, int p_149655_3_, int p_149655_4_) {
    int l = func_150012_g(p_149655_1_, p_149655_2_, p_149655_3_, p_149655_4_);
    return (l & 0x4) != 0;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public int func_149645_b() {
    return 7;
  }
  
  @SideOnly(Side.CLIENT)
  public AxisAlignedBB func_149633_g(World p_149633_1_, int p_149633_2_, int p_149633_3_, int p_149633_4_)
  {
    func_149719_a(p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
    return super.func_149633_g(p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
  }
  
  public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
  {
    func_149719_a(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
    return super.func_149668_a(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
  }
  
  public void func_149719_a(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
  {
    func_150011_b(func_150012_g(p_149719_1_, p_149719_2_, p_149719_3_, p_149719_4_));
  }
  
  public int func_150013_e(IBlockAccess p_150013_1_, int p_150013_2_, int p_150013_3_, int p_150013_4_) {
    return func_150012_g(p_150013_1_, p_150013_2_, p_150013_3_, p_150013_4_) & 0x3;
  }
  
  public boolean func_150015_f(IBlockAccess p_150015_1_, int p_150015_2_, int p_150015_3_, int p_150015_4_) {
    return (func_150012_g(p_150015_1_, p_150015_2_, p_150015_3_, p_150015_4_) & 0x4) != 0;
  }
  
  private void func_150011_b(int p_150011_1_) {
    float f = 0.1875F;
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
    int j = p_150011_1_ & 0x3;
    boolean flag = (p_150011_1_ & 0x4) != 0;
    boolean flag1 = (p_150011_1_ & 0x10) != 0;
    
    if (j == 0) {
      if (flag) {
        if (!flag1) {
          func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        } else {
          func_149676_a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        }
      } else {
        func_149676_a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
      }
    } else if (j == 1) {
      if (flag) {
        if (!flag1) {
          func_149676_a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        } else {
          func_149676_a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        }
      } else {
        func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
      }
    } else if (j == 2) {
      if (flag) {
        if (!flag1) {
          func_149676_a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        } else {
          func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        }
      } else {
        func_149676_a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      }
    } else if (j == 3) {
      if (flag) {
        if (!flag1) {
          func_149676_a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        } else {
          func_149676_a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
      } else {
        func_149676_a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
      }
    }
  }
  

  public void func_149699_a(World p_149699_1_, int p_149699_2_, int p_149699_3_, int p_149699_4_, EntityPlayer p_149699_5_) {}
  

  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
  {
    if (field_149764_J == Material.field_151573_f) {
      return false;
    }
    int i1 = func_150012_g(world, x, y, z);
    int j1 = i1 & 0x7;
    j1 ^= 0x4;
    
    int offy = y;
    if ((i1 & 0x8) != 0) {
      offy--;
    }
    
    if (!field_72995_K) {
      TileEntityCursedBlock tile = (TileEntityCursedBlock)com.emoniph.witchery.util.BlockUtil.getTileEntity(world, x, offy, z, TileEntityCursedBlock.class);
      
      if ((tile != null) && 
        (!tile.applyToEntityAndDestroy(player))) {
        world.func_147468_f(x, offy, z);
        world.func_147468_f(x, offy + 1, z);
        net.minecraft.item.ItemDoor.func_150924_a(world, x, offy, z, j1, net.minecraft.init.Blocks.field_150466_ao);
        return true;
      }
    }
    

    if ((i1 & 0x8) == 0) {
      world.func_72921_c(x, y, z, j1, 2);
      world.func_147458_c(x, y, z, x, y, z);
    } else {
      world.func_72921_c(x, y - 1, z, j1, 2);
      world.func_147458_c(x, y - 1, z, x, y, z);
    }
    
    world.func_72889_a(player, 1003, x, y, z, 0);
    
    return true;
  }
  

  public void func_150014_a(World p_150014_1_, int p_150014_2_, int p_150014_3_, int p_150014_4_, boolean p_150014_5_)
  {
    int l = func_150012_g(p_150014_1_, p_150014_2_, p_150014_3_, p_150014_4_);
    boolean flag1 = (l & 0x4) != 0;
    
    if (flag1 != p_150014_5_) {
      int i1 = l & 0x7;
      i1 ^= 0x4;
      
      if ((l & 0x8) == 0) {
        p_150014_1_.func_72921_c(p_150014_2_, p_150014_3_, p_150014_4_, i1, 2);
        p_150014_1_.func_147458_c(p_150014_2_, p_150014_3_, p_150014_4_, p_150014_2_, p_150014_3_, p_150014_4_);
      }
      else {
        p_150014_1_.func_72921_c(p_150014_2_, p_150014_3_ - 1, p_150014_4_, i1, 2);
        p_150014_1_.func_147458_c(p_150014_2_, p_150014_3_ - 1, p_150014_4_, p_150014_2_, p_150014_3_, p_150014_4_);
      }
      

      p_150014_1_.func_72889_a((EntityPlayer)null, 1003, p_150014_2_, p_150014_3_, p_150014_4_, 0);
    }
  }
  
  public void func_149695_a(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
  {
    int l = p_149695_1_.func_72805_g(p_149695_2_, p_149695_3_, p_149695_4_);
    
    if ((l & 0x8) == 0) {
      boolean flag = false;
      
      if (p_149695_1_.func_147439_a(p_149695_2_, p_149695_3_ + 1, p_149695_4_) != this) {
        p_149695_1_.func_147468_f(p_149695_2_, p_149695_3_, p_149695_4_);
        flag = true;
      }
      
      if (!World.func_147466_a(p_149695_1_, p_149695_2_, p_149695_3_ - 1, p_149695_4_)) {
        p_149695_1_.func_147468_f(p_149695_2_, p_149695_3_, p_149695_4_);
        flag = true;
        
        if (p_149695_1_.func_147439_a(p_149695_2_, p_149695_3_ + 1, p_149695_4_) == this) {
          p_149695_1_.func_147468_f(p_149695_2_, p_149695_3_ + 1, p_149695_4_);
        }
      }
      
      if (flag) {
        if (!field_72995_K) {
          func_149697_b(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, l, 0);
        }
      } else {
        boolean flag1 = (p_149695_1_.func_72864_z(p_149695_2_, p_149695_3_, p_149695_4_)) || (p_149695_1_.func_72864_z(p_149695_2_, p_149695_3_ + 1, p_149695_4_));
        

        if (((flag1) || (p_149695_5_.func_149744_f())) && (p_149695_5_ != this)) {
          func_150014_a(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, flag1);
        }
      }
    } else {
      if (p_149695_1_.func_147439_a(p_149695_2_, p_149695_3_ - 1, p_149695_4_) != this) {
        p_149695_1_.func_147468_f(p_149695_2_, p_149695_3_, p_149695_4_);
      }
      
      if (p_149695_5_ != this) {
        func_149695_a(p_149695_1_, p_149695_2_, p_149695_3_ - 1, p_149695_4_, p_149695_5_);
      }
    }
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return field_149764_J == Material.field_151573_f ? Items.field_151139_aw : (p_149650_1_ & 0x8) != 0 ? null : Items.field_151135_aq;
  }
  

  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
  {
    return new ItemStack(field_149764_J == Material.field_151573_f ? Items.field_151139_aw : Items.field_151135_aq);
  }
  
  public MovingObjectPosition func_149731_a(World p_149731_1_, int p_149731_2_, int p_149731_3_, int p_149731_4_, Vec3 p_149731_5_, Vec3 p_149731_6_)
  {
    func_149719_a(p_149731_1_, p_149731_2_, p_149731_3_, p_149731_4_);
    return super.func_149731_a(p_149731_1_, p_149731_2_, p_149731_3_, p_149731_4_, p_149731_5_, p_149731_6_);
  }
  
  public boolean func_149742_c(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
  {
    return p_149742_3_ < p_149742_1_.func_72800_K() - 1;
  }
  


  public int func_149656_h()
  {
    return 1;
  }
  
  public int func_150012_g(IBlockAccess p_150012_1_, int p_150012_2_, int p_150012_3_, int p_150012_4_) {
    int l = p_150012_1_.func_72805_g(p_150012_2_, p_150012_3_, p_150012_4_);
    boolean flag = (l & 0x8) != 0;
    int j1;
    int i1;
    int j1;
    if (flag) {
      int i1 = p_150012_1_.func_72805_g(p_150012_2_, p_150012_3_ - 1, p_150012_4_);
      j1 = l;
    } else {
      i1 = l;
      j1 = p_150012_1_.func_72805_g(p_150012_2_, p_150012_3_ + 1, p_150012_4_);
    }
    
    boolean flag1 = (j1 & 0x1) != 0;
    return i1 & 0x7 | (flag ? 8 : 0) | (flag1 ? 16 : 0);
  }
  
  @SideOnly(Side.CLIENT)
  public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
    return field_149764_J == Material.field_151573_f ? Items.field_151139_aw : Items.field_151135_aq;
  }
  
  public void func_149681_a(World p_149681_1_, int p_149681_2_, int p_149681_3_, int p_149681_4_, int p_149681_5_, EntityPlayer p_149681_6_)
  {
    if ((field_71075_bZ.field_75098_d) && ((p_149681_5_ & 0x8) != 0) && (p_149681_1_.func_147439_a(p_149681_2_, p_149681_3_ - 1, p_149681_4_) == this))
    {
      p_149681_1_.func_147468_f(p_149681_2_, p_149681_3_ - 1, p_149681_4_);
    }
  }
}
