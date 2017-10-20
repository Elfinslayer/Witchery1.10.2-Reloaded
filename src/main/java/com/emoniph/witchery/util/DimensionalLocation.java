package com.emoniph.witchery.util;

import net.minecraft.nbt.NBTTagCompound;

public class DimensionalLocation
{
  public final int dimension;
  public final double posX;
  public final double posY;
  public final double posZ;
  public final boolean isValid;
  
  public DimensionalLocation(net.minecraft.entity.Entity entity) {
    this(field_71093_bK, field_70165_t, field_70163_u, field_70161_v, true);
  }
  
  public DimensionalLocation(NBTTagCompound nbtTag, String prefix) {
    this(nbtTag.func_74762_e(prefix + "D"), nbtTag.func_74769_h(prefix + "X"), nbtTag.func_74769_h(prefix + "Y"), nbtTag.func_74769_h(prefix + "Z"), (nbtTag.func_74764_b(prefix + "D")) && (nbtTag.func_74764_b(prefix + "X")) && (nbtTag.func_74764_b(prefix + "Y")) && (nbtTag.func_74764_b(prefix + "Z")));
  }
  
  public DimensionalLocation(int dimension, double posX, double posY, double posZ)
  {
    this(dimension, posX, posY, posZ, true);
  }
  
  protected DimensionalLocation(int dimension, double posX, double posY, double posZ, boolean isValid) {
    this.dimension = dimension;
    this.posX = posX;
    this.posY = posY;
    this.posZ = posZ;
    this.isValid = isValid;
  }
  
  public void saveToNBT(NBTTagCompound nbtTag, String prefix) {
    nbtTag.func_74768_a(prefix + "D", dimension);
    nbtTag.func_74780_a(prefix + "X", posX);
    nbtTag.func_74780_a(prefix + "Y", posY);
    nbtTag.func_74780_a(prefix + "Z", posZ);
  }
}
