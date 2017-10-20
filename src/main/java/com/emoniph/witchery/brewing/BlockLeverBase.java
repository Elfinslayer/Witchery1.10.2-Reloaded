package com.emoniph.witchery.brewing;

import com.emoniph.witchery.blocks.BlockBaseContainer;
import com.emoniph.witchery.util.BlockUtil;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;












public class BlockLeverBase
  extends BlockBaseContainer
{
  public BlockLeverBase()
  {
    super(Material.field_151594_q, TileEntityCursedBlock.class);
    registerWithCreateTab = false;
    func_149711_c(0.5F);
    func_149672_a(field_149766_f);
    func_149658_d("lever");
  }
  
  public void replaceButton(World world, int x, int y, int z, ModifiersImpact impactModifiers, NBTTagCompound nbtBrew) {
    int meta = world.func_72805_g(x, y, z);
    world.func_147465_d(x, y, z, this, meta, 3);
    TileEntityCursedBlock tile = (TileEntityCursedBlock)BlockUtil.getTileEntity(world, x, y, z, TileEntityCursedBlock.class);
    if (tile != null) {
      tile.initalise(impactModifiers, nbtBrew);
    }
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
  {
    return Item.func_150898_a(Blocks.field_150442_at);
  }
  
  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
  {
    return new ItemStack(Blocks.field_150442_at);
  }
  

  public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
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
  
  public int func_149645_b()
  {
    return 12;
  }
  

  public boolean func_149707_d(World p_149707_1_, int p_149707_2_, int p_149707_3_, int p_149707_4_, int p_149707_5_)
  {
    ForgeDirection dir = ForgeDirection.getOrientation(p_149707_5_);
    return ((dir == ForgeDirection.DOWN) && (p_149707_1_.isSideSolid(p_149707_2_, p_149707_3_ + 1, p_149707_4_, ForgeDirection.DOWN))) || ((dir == ForgeDirection.UP) && (p_149707_1_.isSideSolid(p_149707_2_, p_149707_3_ - 1, p_149707_4_, ForgeDirection.UP))) || ((dir == ForgeDirection.NORTH) && (p_149707_1_.isSideSolid(p_149707_2_, p_149707_3_, p_149707_4_ + 1, ForgeDirection.NORTH))) || ((dir == ForgeDirection.SOUTH) && (p_149707_1_.isSideSolid(p_149707_2_, p_149707_3_, p_149707_4_ - 1, ForgeDirection.SOUTH))) || ((dir == ForgeDirection.WEST) && (p_149707_1_.isSideSolid(p_149707_2_ + 1, p_149707_3_, p_149707_4_, ForgeDirection.WEST))) || ((dir == ForgeDirection.EAST) && (p_149707_1_.isSideSolid(p_149707_2_ - 1, p_149707_3_, p_149707_4_, ForgeDirection.EAST)));
  }
  





  public boolean func_149742_c(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
  {
    return (p_149742_1_.isSideSolid(p_149742_2_ - 1, p_149742_3_, p_149742_4_, ForgeDirection.EAST)) || (p_149742_1_.isSideSolid(p_149742_2_ + 1, p_149742_3_, p_149742_4_, ForgeDirection.WEST)) || (p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_, p_149742_4_ - 1, ForgeDirection.SOUTH)) || (p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_, p_149742_4_ + 1, ForgeDirection.NORTH)) || (p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_ - 1, p_149742_4_, ForgeDirection.UP)) || (p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_ + 1, p_149742_4_, ForgeDirection.DOWN));
  }
  






  public int func_149660_a(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_)
  {
    int k1 = p_149660_9_ & 0x8;
    int j1 = p_149660_9_ & 0x7;
    byte b0 = -1;
    
    if ((p_149660_5_ == 0) && (p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_ + 1, p_149660_4_, ForgeDirection.DOWN))) {
      b0 = 0;
    }
    
    if ((p_149660_5_ == 1) && (p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_ - 1, p_149660_4_, ForgeDirection.UP))) {
      b0 = 5;
    }
    
    if ((p_149660_5_ == 2) && (p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_, p_149660_4_ + 1, ForgeDirection.NORTH))) {
      b0 = 4;
    }
    
    if ((p_149660_5_ == 3) && (p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_, p_149660_4_ - 1, ForgeDirection.SOUTH))) {
      b0 = 3;
    }
    
    if ((p_149660_5_ == 4) && (p_149660_1_.isSideSolid(p_149660_2_ + 1, p_149660_3_, p_149660_4_, ForgeDirection.WEST))) {
      b0 = 2;
    }
    
    if ((p_149660_5_ == 5) && (p_149660_1_.isSideSolid(p_149660_2_ - 1, p_149660_3_, p_149660_4_, ForgeDirection.EAST))) {
      b0 = 1;
    }
    
    return b0 + k1;
  }
  

  public void func_149689_a(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
  {
    int l = p_149689_1_.func_72805_g(p_149689_2_, p_149689_3_, p_149689_4_);
    int i1 = l & 0x7;
    int j1 = l & 0x8;
    
    if (i1 == invertMetadata(1)) {
      if ((MathHelper.func_76128_c(field_70177_z * 4.0F / 360.0F + 0.5D) & 0x1) == 0) {
        p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 0x5 | j1, 2);
      } else {
        p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 0x6 | j1, 2);
      }
    } else if (i1 == invertMetadata(0)) {
      if ((MathHelper.func_76128_c(field_70177_z * 4.0F / 360.0F + 0.5D) & 0x1) == 0) {
        p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 0x7 | j1, 2);
      } else {
        p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 0x0 | j1, 2);
      }
    }
  }
  
  public static int invertMetadata(int p_149819_0_) {
    switch (p_149819_0_) {
    case 0: 
      return 0;
    case 1: 
      return 5;
    case 2: 
      return 4;
    case 3: 
      return 3;
    case 4: 
      return 2;
    case 5: 
      return 1;
    }
    return -1;
  }
  


  public void func_149695_a(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
  {
    if (func_149820_e(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_)) {
      int l = p_149695_1_.func_72805_g(p_149695_2_, p_149695_3_, p_149695_4_) & 0x7;
      boolean flag = false;
      
      if ((!p_149695_1_.isSideSolid(p_149695_2_ - 1, p_149695_3_, p_149695_4_, ForgeDirection.EAST)) && (l == 1)) {
        flag = true;
      }
      
      if ((!p_149695_1_.isSideSolid(p_149695_2_ + 1, p_149695_3_, p_149695_4_, ForgeDirection.WEST)) && (l == 2)) {
        flag = true;
      }
      
      if ((!p_149695_1_.isSideSolid(p_149695_2_, p_149695_3_, p_149695_4_ - 1, ForgeDirection.SOUTH)) && (l == 3)) {
        flag = true;
      }
      
      if ((!p_149695_1_.isSideSolid(p_149695_2_, p_149695_3_, p_149695_4_ + 1, ForgeDirection.NORTH)) && (l == 4)) {
        flag = true;
      }
      
      if ((!p_149695_1_.isSideSolid(p_149695_2_, p_149695_3_ - 1, p_149695_4_, ForgeDirection.UP)) && (l == 5)) {
        flag = true;
      }
      
      if ((!p_149695_1_.isSideSolid(p_149695_2_, p_149695_3_ - 1, p_149695_4_, ForgeDirection.UP)) && (l == 6)) {
        flag = true;
      }
      
      if ((!p_149695_1_.isSideSolid(p_149695_2_, p_149695_3_ + 1, p_149695_4_, ForgeDirection.DOWN)) && (l == 0)) {
        flag = true;
      }
      
      if ((!p_149695_1_.isSideSolid(p_149695_2_, p_149695_3_ + 1, p_149695_4_, ForgeDirection.DOWN)) && (l == 7)) {
        flag = true;
      }
      
      if (flag) {
        func_149697_b(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_1_.func_72805_g(p_149695_2_, p_149695_3_, p_149695_4_), 0);
        
        p_149695_1_.func_147468_f(p_149695_2_, p_149695_3_, p_149695_4_);
      }
    }
  }
  
  private boolean func_149820_e(World p_149820_1_, int p_149820_2_, int p_149820_3_, int p_149820_4_) {
    if (!func_149742_c(p_149820_1_, p_149820_2_, p_149820_3_, p_149820_4_)) {
      func_149697_b(p_149820_1_, p_149820_2_, p_149820_3_, p_149820_4_, p_149820_1_.func_72805_g(p_149820_2_, p_149820_3_, p_149820_4_), 0);
      
      p_149820_1_.func_147468_f(p_149820_2_, p_149820_3_, p_149820_4_);
      return false;
    }
    return true;
  }
  


  public void func_149719_a(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
  {
    int l = p_149719_1_.func_72805_g(p_149719_2_, p_149719_3_, p_149719_4_) & 0x7;
    float f = 0.1875F;
    
    if (l == 1) {
      func_149676_a(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
    } else if (l == 2) {
      func_149676_a(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
    } else if (l == 3) {
      func_149676_a(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
    } else if (l == 4) {
      func_149676_a(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
    } else if ((l != 5) && (l != 6)) {
      if ((l == 0) || (l == 7)) {
        f = 0.25F;
        func_149676_a(0.5F - f, 0.4F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
      }
    } else {
      f = 0.25F;
      func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
    }
  }
  

  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
  {
    if (field_72995_K) {
      return true;
    }
    int i1 = world.func_72805_g(x, y, z);
    int j1 = i1 & 0x7;
    int k1 = 8 - (i1 & 0x8);
    world.func_72921_c(x, y, z, j1 + k1, 3);
    world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "random.click", 0.3F, k1 > 0 ? 0.6F : 0.5F);
    
    world.func_147459_d(x, y, z, this);
    
    if (j1 == 1) {
      world.func_147459_d(x - 1, y, z, this);
    } else if (j1 == 2) {
      world.func_147459_d(x + 1, y, z, this);
    } else if (j1 == 3) {
      world.func_147459_d(x, y, z - 1, this);
    } else if (j1 == 4) {
      world.func_147459_d(x, y, z + 1, this);
    } else if ((j1 != 5) && (j1 != 6)) {
      if ((j1 == 0) || (j1 == 7)) {
        world.func_147459_d(x, y + 1, z, this);
      }
    } else {
      world.func_147459_d(x, y - 1, z, this);
    }
    
    if (!field_72995_K) {
      TileEntityCursedBlock tile = (TileEntityCursedBlock)BlockUtil.getTileEntity(world, x, y, z, TileEntityCursedBlock.class);
      if ((tile != null) && (nbtEffect != null) && 
        (!tile.applyToEntityAndDestroy(player))) {
        world.func_147465_d(x, y, z, Blocks.field_150442_at, j1 + k1, 3);
      }
    }
    

    return true;
  }
  


  public void func_149749_a(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
  {
    if ((p_149749_6_ & 0x8) > 0) {
      p_149749_1_.func_147459_d(p_149749_2_, p_149749_3_, p_149749_4_, this);
      int i1 = p_149749_6_ & 0x7;
      
      if (i1 == 1) {
        p_149749_1_.func_147459_d(p_149749_2_ - 1, p_149749_3_, p_149749_4_, this);
      } else if (i1 == 2) {
        p_149749_1_.func_147459_d(p_149749_2_ + 1, p_149749_3_, p_149749_4_, this);
      } else if (i1 == 3) {
        p_149749_1_.func_147459_d(p_149749_2_, p_149749_3_, p_149749_4_ - 1, this);
      } else if (i1 == 4) {
        p_149749_1_.func_147459_d(p_149749_2_, p_149749_3_, p_149749_4_ + 1, this);
      } else if ((i1 != 5) && (i1 != 6)) {
        if ((i1 == 0) || (i1 == 7)) {
          p_149749_1_.func_147459_d(p_149749_2_, p_149749_3_ + 1, p_149749_4_, this);
        }
      } else {
        p_149749_1_.func_147459_d(p_149749_2_, p_149749_3_ - 1, p_149749_4_, this);
      }
    }
    
    super.func_149749_a(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
  }
  

  public int func_149709_b(IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_, int p_149709_5_)
  {
    return (p_149709_1_.func_72805_g(p_149709_2_, p_149709_3_, p_149709_4_) & 0x8) > 0 ? 15 : 0;
  }
  

  public int func_149748_c(IBlockAccess p_149748_1_, int p_149748_2_, int p_149748_3_, int p_149748_4_, int p_149748_5_)
  {
    int i1 = p_149748_1_.func_72805_g(p_149748_2_, p_149748_3_, p_149748_4_);
    
    if ((i1 & 0x8) == 0) {
      return 0;
    }
    int j1 = i1 & 0x7;
    return (j1 == 1) && (p_149748_5_ == 5) ? 15 : (j1 == 2) && (p_149748_5_ == 4) ? 15 : (j1 == 3) && (p_149748_5_ == 3) ? 15 : (j1 == 4) && (p_149748_5_ == 2) ? 15 : (j1 == 5) && (p_149748_5_ == 1) ? 15 : (j1 == 6) && (p_149748_5_ == 1) ? 15 : (j1 == 7) && (p_149748_5_ == 0) ? 15 : (j1 == 0) && (p_149748_5_ == 0) ? 15 : 0;
  }
  




  public boolean func_149744_f()
  {
    return true;
  }
}
