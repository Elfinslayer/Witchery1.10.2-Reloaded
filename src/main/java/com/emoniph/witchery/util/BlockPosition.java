package com.emoniph.witchery.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class BlockPosition
{
  public final int dimension;
  public final int x;
  public final int y;
  public final int z;
  
  public BlockPosition(int dimension, int x, int y, int z)
  {
    this.dimension = dimension;
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public BlockPosition(World world, int x, int y, int z) {
    this(field_73011_w.field_76574_g, x, y, z);
  }
  
  public BlockPosition(World world, Coord coord) {
    this(world, x, y, z);
  }
  
  public BlockPosition(World world, double x, double y, double z) {
    this(field_73011_w.field_76574_g, net.minecraft.util.MathHelper.func_76128_c(x), net.minecraft.util.MathHelper.func_76128_c(y), net.minecraft.util.MathHelper.func_76128_c(z));
  }
  
  public BlockPosition(World world, EntityPosition position) {
    this(world, x, y, z);
  }
  
  public static BlockPosition from(net.minecraft.item.ItemStack stack) {
    NBTTagCompound tag = stack.func_77978_p();
    if ((tag != null) && (tag.func_74764_b("PosX")) && (tag.func_74764_b("PosY")) && (tag.func_74764_b("PosZ")) && (tag.func_74764_b("PosD"))) {
      int newX = tag.func_74762_e("PosX");
      int newY = tag.func_74762_e("PosY");
      int newZ = tag.func_74762_e("PosZ");
      int newD = tag.func_74762_e("PosD");
      return new BlockPosition(newD, newX, newY, newZ);
    }
    return null;
  }
  
  public World getWorld(MinecraftServer server)
  {
    for (net.minecraft.world.WorldServer world : field_71305_c) {
      if (field_73011_w.field_76574_g == dimension) {
        return world;
      }
    }
    return null;
  }
}
