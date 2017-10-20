package com.emoniph.witchery.util;

import com.emoniph.witchery.common.INullSource;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public final class Coord
{
  public final int x;
  public final int y;
  public final int z;
  
  public Coord(int x, int y, int z)
  {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public Coord(int x, int y, int z, ForgeDirection side) {
    this.x = (x + offsetX);
    this.y = (y + offsetY);
    this.z = (z + offsetZ);
  }
  
  public Coord(TileEntity tileEntity) {
    this(field_145851_c, field_145848_d, field_145849_e);
  }
  
  public Coord(Entity entity) {
    this(MathHelper.func_76128_c(field_70165_t), MathHelper.func_76128_c(field_70163_u), MathHelper.func_76128_c(field_70161_v));
  }
  
  public Coord(INullSource entity) {
    this(entity.getPosX(), entity.getPosY(), entity.getPosZ());
  }
  
  public Coord(MovingObjectPosition mop, EntityPosition alternativePos, boolean before) {
    if (mop != null) {
      switch (1.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[field_72313_a.ordinal()]) {
      case 1: 
        if (before) {
          x = (field_72311_b + (field_72310_e == 5 ? 1 : field_72310_e == 4 ? -1 : 0));
          y = (field_72312_c + (field_72310_e == 1 ? 1 : field_72310_e == 0 ? -1 : 0));
          z = (field_72309_d + (field_72310_e == 3 ? 1 : field_72310_e == 2 ? -1 : 0));
        } else {
          x = field_72311_b;
          y = field_72312_c;
          z = field_72309_d;
        }
        break;
      case 2: 
        x = MathHelper.func_76128_c(field_72308_g.field_70165_t);
        y = MathHelper.func_76128_c(field_72308_g.field_70163_u);
        z = MathHelper.func_76128_c(field_72308_g.field_70161_v);
        break;
      case 3: 
      default: 
        if (alternativePos != null) {
          x = MathHelper.func_76128_c(x);
          y = MathHelper.func_76128_c(y);
          z = MathHelper.func_76128_c(z);
        } else {
          x = 0;
          y = 0;
          z = 0;
        }
        break;
      }
    }
    else if (alternativePos != null) {
      x = MathHelper.func_76128_c(x);
      y = MathHelper.func_76128_c(y);
      z = MathHelper.func_76128_c(z);
    } else {
      x = 0;
      y = 0;
      z = 0;
    }
  }
  

  public boolean equals(Object obj)
  {
    if (obj == this) {
      return true;
    }
    
    if ((obj == null) || (obj.getClass() != getClass())) {
      return false;
    }
    
    Coord other = (Coord)obj;
    
    return (x == x) && (y == y) && (z == z);
  }
  
  public boolean isAtPosition(TileEntity tileEntity) {
    return (tileEntity != null) && (x == field_145851_c) && (y == field_145848_d) && (z == field_145849_e);
  }
  
  public Coord north() {
    return north(1);
  }
  
  public Coord north(int n) {
    return new Coord(x, y, z - n);
  }
  
  public Coord south() {
    return south(1);
  }
  
  public Coord south(int n) {
    return new Coord(x, y, z + n);
  }
  
  public Coord east() {
    return east(1);
  }
  
  public Coord east(int n) {
    return new Coord(x + n, y, z);
  }
  
  public Coord west() {
    return west(1);
  }
  
  public Coord west(int n) {
    return new Coord(x - n, y, z);
  }
  
  public Coord northEast() {
    return new Coord(x + 1, y, z - 1);
  }
  
  public Coord northWest() {
    return new Coord(x - 1, y, z - 1);
  }
  
  public Coord southEast() {
    return new Coord(x + 1, y, z + 1);
  }
  
  public Coord southWest() {
    return new Coord(x - 1, y, z + 1);
  }
  
  public Block getBlock(World world) {
    return getBlock(world, 0, 0, 0);
  }
  
  public Block getBlock(World world, int offsetX, int offsetY, int offsetZ) {
    return world.func_147439_a(x + offsetX, y + offsetY, z + offsetZ);
  }
  
  public TileEntity getBlockTileEntity(World world) {
    return getBlockTileEntity(world, 0, 0, 0);
  }
  
  public TileEntity getBlockTileEntity(World world, int offsetX, int offsetY, int offsetZ) {
    return world.func_147438_o(x + offsetX, y + offsetY, z + offsetZ);
  }
  
  public <T> T getTileEntity(IBlockAccess world, Class<T> clazz) {
    return BlockUtil.getTileEntity(world, x, y, z, clazz);
  }
  
  public int getBlockMetadata(World world) {
    return getBlockMetadata(world, 0, 0, 0);
  }
  
  public int getBlockMetadata(World world, int offsetX, int offsetY, int offsetZ) {
    return world.func_72805_g(x + offsetX, y + offsetY, z + offsetZ);
  }
  
  public void setNBT(NBTTagCompound nbtTag, String key) {
    nbtTag.func_74768_a(key + "X", x);
    nbtTag.func_74768_a(key + "Y", y);
    nbtTag.func_74768_a(key + "Z", z);
  }
  
  public static double distance(Coord first, Coord second) {
    double dX = x - x;
    double dY = y - y;
    double dZ = z - z;
    return Math.sqrt(dX * dX + dY * dY + dZ * dZ);
  }
  
  public static double distance(double firstX, double firstY, double firstZ, double secondX, double secondY, double secondZ) {
    double dX = firstX - secondX;
    double dY = firstY - secondY;
    double dZ = firstZ - secondZ;
    return Math.sqrt(dX * dX + dY * dY + dZ * dZ);
  }
  
  public static double distanceSq(double firstX, double firstY, double firstZ, double secondX, double secondY, double secondZ) {
    double dX = firstX - secondX;
    double dY = firstY - secondY;
    double dZ = firstZ - secondZ;
    return dX * dX + dY * dY + dZ * dZ;
  }
  
  public double distanceTo(Coord other) {
    double dX = x - x;
    double dY = y - y;
    double dZ = z - z;
    return Math.sqrt(dX * dX + dY * dY + dZ * dZ);
  }
  
  public double distanceSqTo(Coord other) {
    double dX = x - x;
    double dY = y - y;
    double dZ = z - z;
    return dX * dX + dY * dY + dZ * dZ;
  }
  
  public double distanceSqTo(int x, int y, int z) {
    double dX = x - this.x;
    double dY = y - this.y;
    double dZ = z - this.z;
    return dX * dX + dY * dY + dZ * dZ;
  }
  
  public static Coord createFrom(NBTTagCompound nbtTag, String key) {
    if ((nbtTag.func_74764_b(key + "X")) && (nbtTag.func_74764_b(key + "Y")) && (nbtTag.func_74764_b(key + "Z"))) {
      return new Coord(nbtTag.func_74762_e(key + "X"), nbtTag.func_74762_e(key + "Y"), nbtTag.func_74762_e(key + "Z"));
    }
    return null;
  }
  
  public boolean isWestOf(Coord coord)
  {
    return x < x;
  }
  
  public boolean isNorthOf(Coord coord) {
    return z < z;
  }
  
  public boolean setBlock(World world, Block block) {
    return world.func_147449_b(x, y, z, block);
  }
  
  public boolean setBlock(World world, Block block, int metadata, int flags) {
    return world.func_147465_d(x, y, z, block, metadata, flags);
  }
  
  public void setAir(World world) {
    world.func_147468_f(x, y, z);
  }
  
  public void markBlockForUpdate(World world) {
    world.func_147471_g(x, y, z);
  }
  
  public int getHeading(Coord destination) {
    double dX = x - x;
    double dZ = z - z;
    double yaw = Math.atan2(dZ, dX);
    
    double PI8 = 0.39269908169872414D;
    double PI2 = 1.5707963267948966D;
    if ((yaw > -0.39269908169872414D) && (yaw <= 0.39269908169872414D))
      return 6;
    if ((yaw > 0.39269908169872414D) && (yaw <= 1.1780972450961724D))
      return 7;
    if ((yaw > 1.1780972450961724D) && (yaw <= 1.9634954084936207D))
      return 0;
    if ((yaw > 1.9634954084936207D) && (yaw <= 2.748893571891069D))
      return 1;
    if ((yaw > 2.748893571891069D) || (yaw <= -2.748893571891069D))
      return 2;
    if ((yaw > -2.748893571891069D) && (yaw <= -1.9634954084936207D))
      return 3;
    if ((yaw > -1.9634954084936207D) && (yaw <= -1.1780972450961724D)) {
      return 4;
    }
    return 5;
  }
  

  public String toString()
  {
    return String.format("%d, %d, %d", new Object[] { Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z) });
  }
  
  public NBTTagCompound toTagNBT() {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.func_74768_a("posX", x);
    nbt.func_74768_a("posY", y);
    nbt.func_74768_a("posZ", z);
    return nbt;
  }
  
  public static Coord fromTagNBT(NBTTagCompound nbt) {
    if ((nbt.func_74764_b("posX")) && (nbt.func_74764_b("posY")) && (nbt.func_74764_b("posZ"))) {
      return new Coord(nbt.func_74762_e("posX"), nbt.func_74762_e("posY"), nbt.func_74762_e("posZ"));
    }
    return null;
  }
  
  public boolean isMatch(int x, int y, int z)
  {
    return (this.x == x) && (this.y == y) && (this.z == z);
  }
}
