package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.util.BlockUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayer.EnumStatus;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockCoffin extends BlockBaseContainer
{
  private static final int[][] DIRECTIONS = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
  
  public BlockCoffin() {
    super(Material.field_151575_d, TileEntityCoffin.class);
    registerWithCreateTab = false;
    func_149711_c(1.0F);
    func_149649_H();
    setupBounds();
  }
  
  public static class TileEntityCoffin extends TileEntity {
    private boolean open;
    public float lidAngle;
    public float prevLidAngle;
    
    public TileEntityCoffin() {}
    
    public void func_145845_h() { prevLidAngle = lidAngle;
      
      if ((open) && (lidAngle == 0.0F)) {
        double d1 = field_145851_c + 0.5D;
        double d0 = field_145849_e + 0.5D;
        field_145850_b.func_72908_a(d1, field_145848_d + 0.5D, d0, "random.chestopen", 0.5F, field_145850_b.field_73012_v.nextFloat() * 0.1F + 0.9F);
      }
      

      if (((!open) && (lidAngle > 0.0F)) || ((open) && (lidAngle < 1.0F))) {
        float f = 0.1F;
        float f1 = lidAngle;
        
        if (open) {
          lidAngle += 0.1F;
        } else {
          lidAngle -= 0.1F;
        }
        
        if (lidAngle > 1.0F) {
          lidAngle = 1.0F;
        }
        
        float f2 = 0.5F;
        
        if ((lidAngle < f2) && (f1 >= f2)) {
          double d0 = field_145851_c + 0.5D;
          double d2 = field_145849_e + 0.5D;
          
          field_145850_b.func_72908_a(d0, field_145848_d + 0.5D, d2, "random.chestclosed", 0.5F, field_145850_b.field_73012_v.nextFloat() * 0.1F + 0.9F);
        }
        

        if (lidAngle < 0.0F) {
          lidAngle = 0.0F;
        }
      }
    }
    
    public net.minecraft.network.Packet func_145844_m()
    {
      NBTTagCompound nbtTag = new NBTTagCompound();
      func_145841_b(nbtTag);
      nbtTag.func_74776_a("Angle", lidAngle);
      nbtTag.func_74776_a("AnglePrev", prevLidAngle);
      return new S35PacketUpdateTileEntity(field_145851_c, field_145848_d, field_145849_e, 1, nbtTag);
    }
    
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
    {
      super.onDataPacket(net, packet);
      NBTTagCompound nbtTag = packet.func_148857_g();
      func_145839_a(nbtTag);
      lidAngle = nbtTag.func_74760_g("Angle");
      prevLidAngle = nbtTag.func_74760_g("AnglePrev");
      field_145850_b.func_147479_m(field_145851_c, field_145848_d, field_145849_e);
    }
    
    public void func_145841_b(NBTTagCompound nbtRoot)
    {
      super.func_145841_b(nbtRoot);
      nbtRoot.func_74757_a("Opened", open);
    }
    
    public void func_145839_a(NBTTagCompound nbtRoot)
    {
      super.func_145839_a(nbtRoot);
      open = nbtRoot.func_74767_n("Opened");
    }
  }
  
  public static int getDirection(int meta) {
    return meta & 0x3;
  }
  
  public boolean isBed(IBlockAccess world, int x, int y, int z, EntityLivingBase player)
  {
    return true;
  }
  

  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
  {
    if (field_72995_K) {
      return true;
    }
    int i1 = world.func_72805_g(x, y, z);
    
    int origX = x;int origZ = z;
    if (!isBlockHeadOfBed(i1)) {
      int j1 = getDirection(i1);
      x += DIRECTIONS[j1][0];
      z += DIRECTIONS[j1][1];
      
      if (world.func_147439_a(x, y, z) != this) {
        return true;
      }
      
      i1 = world.func_72805_g(x, y, z);
    } else {
      int j1 = getDirection(i1);
      origX -= DIRECTIONS[j1][0];
      origZ -= DIRECTIONS[j1][1];
    }
    
    if (player.func_70093_af()) {
      TileEntityCoffin tile = (TileEntityCoffin)BlockUtil.getTileEntity(world, x, y, z, TileEntityCoffin.class);
      if (tile != null) {
        if ((open) && (isBedOccupied(i1))) {
          EntityPlayer entityplayer1 = null;
          Iterator iterator = field_73010_i.iterator();
          
          while (iterator.hasNext()) {
            EntityPlayer entityplayer2 = (EntityPlayer)iterator.next();
            
            if (entityplayer2.func_70608_bn()) {
              ChunkCoordinates chunkcoordinates = field_71081_bT;
              
              if ((field_71574_a == x) && (field_71572_b == y) && (field_71573_c == z))
              {
                entityplayer1 = entityplayer2;
              }
            }
          }
          
          if (entityplayer1 != null) {
            return true;
          }
        }
        
        if ((world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN)) || (world.isSideSolid(origX, y + 1, origZ, ForgeDirection.DOWN)))
        {
          return true;
        }
        
        open = (!open);
        if (!isBlockHeadOfBed(i1)) {
          int j1 = getDirection(i1);
          
          TileEntityCoffin tile2 = (TileEntityCoffin)BlockUtil.getTileEntity(world, x + DIRECTIONS[j1][0], y, z + DIRECTIONS[j1][1], TileEntityCoffin.class);
          
          if (tile2 != null) {
            open = open;
            world.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
          }
        }
        else {
          int j1 = getDirection(i1);
          
          TileEntityCoffin tile2 = (TileEntityCoffin)BlockUtil.getTileEntity(world, x - DIRECTIONS[j1][0], y, z - DIRECTIONS[j1][1], TileEntityCoffin.class);
          
          if (tile2 != null) {
            open = open;
            world.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
          }
        }
        world.func_147471_g(x, y, z);
      }
      return true;
    }
    
    TileEntityCoffin tile = (TileEntityCoffin)BlockUtil.getTileEntity(world, x, y, z, TileEntityCoffin.class);
    
    if (tile != null) {
      if (!open) {
        player.func_146105_b(new ChatComponentTranslation("witchery.nosleep.closedcoffin", new Object[0]));
        
        return true;
      }
    } else {
      return true;
    }
    
    if ((field_73011_w.func_76567_e()) && (world.func_72807_a(x, z) != net.minecraft.world.biome.BiomeGenBase.field_76778_j)) {
      if (isBedOccupied(i1)) {
        EntityPlayer entityplayer1 = null;
        Iterator iterator = field_73010_i.iterator();
        
        while (iterator.hasNext()) {
          EntityPlayer entityplayer2 = (EntityPlayer)iterator.next();
          
          if (entityplayer2.func_70608_bn()) {
            ChunkCoordinates chunkcoordinates = field_71081_bT;
            
            if ((field_71574_a == x) && (field_71572_b == y) && (field_71573_c == z))
            {
              entityplayer1 = entityplayer2;
            }
          }
        }
        
        if (entityplayer1 != null) {
          player.func_146105_b(new ChatComponentTranslation("tile.bed.occupied", new Object[0]));
          
          return true;
        }
        
        setBedOccupied(world, x, y, z, false);
      }
      
      EntityPlayer.EnumStatus enumstatus = player.func_71018_a(x, y, z);
      
      if (enumstatus == EntityPlayer.EnumStatus.OK) {
        setBedOccupied(world, x, y, z, true);
        return true;
      }
      if (enumstatus == EntityPlayer.EnumStatus.NOT_POSSIBLE_NOW) {
        player.func_146105_b(new ChatComponentTranslation("tile.bed.noSleep", new Object[0]));
      }
      else if (enumstatus == EntityPlayer.EnumStatus.NOT_SAFE) {
        player.func_146105_b(new ChatComponentTranslation("tile.bed.notSafe", new Object[0]));
      }
      

      return true;
    }
    
    double d2 = x + 0.5D;
    double d0 = y + 0.5D;
    double d1 = z + 0.5D;
    world.func_147468_f(x, y, z);
    int k1 = getDirection(i1);
    x += DIRECTIONS[k1][0];
    z += DIRECTIONS[k1][1];
    
    if (world.func_147439_a(x, y, z) == this) {
      world.func_147468_f(x, y, z);
      d2 = (d2 + x + 0.5D) / 2.0D;
      d0 = (d0 + y + 0.5D) / 2.0D;
      d1 = (d1 + z + 0.5D) / 2.0D;
    }
    
    world.func_72885_a((Entity)null, x + 0.5F, y + 0.5F, z + 0.5F, 5.0F, true, true);
    return true;
  }
  


  public int func_149645_b()
  {
    return -1;
  }
  
  public boolean func_149686_d()
  {
    return false;
  }
  
  public boolean func_149662_c()
  {
    return false;
  }
  
  public void func_149719_a(IBlockAccess world, int x, int y, int z)
  {
    super.func_149719_a(world, x, y, z);
  }
  


  public void func_149695_a(World world, int x, int y, int z, Block block)
  {
    int l = world.func_72805_g(x, y, z);
    int i1 = getDirection(l);
    
    if (isBlockHeadOfBed(l)) {
      if (world.func_147439_a(x - DIRECTIONS[i1][0], y, z - DIRECTIONS[i1][1]) != this) {
        world.func_147468_f(x, y, z);
      }
    } else if (world.func_147439_a(x + DIRECTIONS[i1][0], y, z + DIRECTIONS[i1][1]) != this) {
      world.func_147468_f(x, y, z);
      
      if (!field_72995_K) {
        func_149697_b(world, x, y, z, l, 0);
      }
    }
  }
  
  public Item func_149650_a(int meta, Random rand, int p_149650_3_)
  {
    return isBlockHeadOfBed(meta) ? Item.func_150899_d(0) : ItemsCOFFIN;
  }
  


  private void setupBounds() {}
  

  public void func_149743_a(World world, int x, int y, int z, AxisAlignedBB mask, List boxes, Entity entity)
  {
    TileEntityCoffin tile = (TileEntityCoffin)BlockUtil.getTileEntity(world, x, y, z, TileEntityCoffin.class);
    if ((tile != null) && (!open)) {
      func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      super.func_149743_a(world, x, y, z, mask, boxes, entity);
      return;
    }
    
    int meta = world.func_72805_g(x, y, z);
    float baseHeight = 0.4375F;
    float wallThick = 0.05F;
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, baseHeight, 1.0F);
    super.func_149743_a(world, x, y, z, mask, boxes, entity);
    
    int direction = getDirection(meta);
    
    boolean head = isBlockHeadOfBed(meta);
    
    boolean n = true;boolean s = true;boolean e = true;boolean w = true;
    boolean n1 = false;boolean s1 = false;boolean e1 = false;boolean w1 = false;
    
    switch (direction) {
    case 0: 
      n = !head;
      s = head;
      e1 = true;
      break;
    case 1: 
      e = !head;
      w = head;
      s1 = true;
      break;
    case 2: 
      s = !head;
      n = head;
      w1 = true;
      break;
    case 3: 
      w = !head;
      e = head;
      n1 = true;
    }
    
    
    if (n) {
      func_149676_a(0.0F, baseHeight, 0.0F, 1.0F, n1 ? 2.0F : 1.0F, wallThick);
      super.func_149743_a(world, x, y, z, mask, boxes, entity);
    }
    
    if (s) {
      func_149676_a(0.0F, baseHeight, 1.0F - wallThick, 1.0F, s1 ? 2.0F : 1.0F, 1.0F);
      super.func_149743_a(world, x, y, z, mask, boxes, entity);
    }
    
    if (w) {
      func_149676_a(0.0F, baseHeight, 0.0F, wallThick, w1 ? 2.0F : 1.0F, 1.0F);
      super.func_149743_a(world, x, y, z, mask, boxes, entity);
    }
    
    if (e) {
      func_149676_a(1.0F - wallThick, baseHeight, 0.0F, 1.0F, e1 ? 2.0F : 1.0F, 1.0F);
      super.func_149743_a(world, x, y, z, mask, boxes, entity);
    }
    
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
    setupBounds();
  }
  
  @SideOnly(Side.CLIENT)
  public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
    return AxisAlignedBB.func_72330_a(x + field_149759_B, y + field_149760_C, z + field_149754_D, x + field_149755_E, y + 1.0D, z + field_149757_G);
  }
  
  public static boolean isBlockHeadOfBed(int meta)
  {
    return (meta & 0x8) != 0;
  }
  
  public static boolean isBedOccupied(int meta) {
    return (meta & 0x4) != 0;
  }
  
  public static void setBedOccupied(World world, int x, int y, int z, boolean p_149979_4_) {
    int l = world.func_72805_g(x, y, z);
    
    if (p_149979_4_) {
      l |= 0x4;
    } else {
      l &= 0xFFFFFFFB;
    }
    
    world.func_72921_c(x, y, z, l, 3);
  }
  
  public static ChunkCoordinates func_149977_a(World world, int x, int y, int z, int p_149977_4_) {
    int i1 = world.func_72805_g(x, y, z);
    int j1 = BlockDirectional.func_149895_l(i1);
    
    for (int k1 = 0; k1 <= 1; k1++) {
      int l1 = x - DIRECTIONS[j1][0] * k1 - 1;
      int i2 = z - DIRECTIONS[j1][1] * k1 - 1;
      int j2 = l1 + 2;
      int k2 = i2 + 2;
      
      for (int l2 = l1; l2 <= j2; l2++) {
        for (int i3 = i2; i3 <= k2; i3++) {
          if ((World.func_147466_a(world, l2, y - 1, i3)) && (!world.func_147439_a(l2, y, i3).func_149688_o().func_76218_k()) && (!world.func_147439_a(l2, y + 1, i3).func_149688_o().func_76218_k()))
          {

            if (p_149977_4_ <= 0) {
              return new ChunkCoordinates(l2, y, i3);
            }
            
            p_149977_4_--;
          }
        }
      }
    }
    
    return null;
  }
  

  public void func_149690_a(World world, int x, int y, int z, int p_149690_5_, float p_149690_6_, int p_149690_7_)
  {
    if (!isBlockHeadOfBed(p_149690_5_)) {
      super.func_149690_a(world, x, y, z, p_149690_5_, p_149690_6_, 0);
    }
  }
  
  public int func_149656_h()
  {
    return 1;
  }
  
  @SideOnly(Side.CLIENT)
  public Item func_149694_d(World world, int x, int y, int z)
  {
    return ItemsCOFFIN;
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player)
  {
    if ((field_71075_bZ.field_75098_d) && (isBlockHeadOfBed(meta))) {
      int i1 = getDirection(meta);
      x -= DIRECTIONS[i1][0];
      z -= DIRECTIONS[i1][1];
      
      if (world.func_147439_a(x, y, z) == this) {
        world.func_147468_f(x, y, z);
      }
    }
  }
}
