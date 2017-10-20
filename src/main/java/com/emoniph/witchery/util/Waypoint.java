package com.emoniph.witchery.util;

import com.emoniph.witchery.Witchery;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class Waypoint
{
  public final boolean valid;
  public final double X;
  public final double Y;
  public final double Z;
  public final double D;
  
  public Waypoint(World world, double homeX, double homeY, double homeZ)
  {
    X = homeX;
    Y = homeY;
    Z = homeZ;
    D = field_73011_w.field_76574_g;
    valid = true;
  }
  
  public Waypoint(World world, net.minecraft.world.ChunkPosition pos) {
    X = field_151329_a;
    Y = field_151327_b;
    Z = field_151328_c;
    D = field_73011_w.field_76574_g;
    valid = true;
  }
  
  public Waypoint(World world, net.minecraft.item.ItemStack stack, double homeX, double homeY, double homeZ) {
    if (ItemsGENERIC.itemWaystoneBound.isMatch(stack)) {
      NBTTagCompound nbtWaystone = stack.func_77978_p();
      int x = nbtWaystone.func_74762_e("PosX");
      int z = nbtWaystone.func_74762_e("PosZ");
      
      if (func_72938_dfield_76636_d) {
        X = (x + 0.5D);
        Y = (nbtWaystone.func_74762_e("PosY") + 1.5D);
        Z = (z + 0.5D);
        D = nbtWaystone.func_74762_e("PosD");
        valid = true;
      } else {
        X = homeX;
        Y = homeY;
        Z = homeZ;
        D = field_73011_w.field_76574_g;
        valid = false;
      }
    }
    else if (ItemsGENERIC.itemWaystonePlayerBound.isMatch(stack)) {
      EntityLivingBase entity = ItemsTAGLOCK_KIT.getBoundEntity(world, null, stack, Integer.valueOf(1));
      if (entity != null) {
        X = field_70165_t;
        Y = (field_70163_u + 1.0D);
        Z = field_70161_v;
        D = field_71093_bK;
        valid = true;
      } else {
        X = homeX;
        Y = homeY;
        Z = homeZ;
        D = field_73011_w.field_76574_g;
        valid = false;
      }
    } else if ((stack != null) && (stack.func_77973_b() == ItemsTAGLOCK_KIT)) {
      EntityLivingBase entity = ItemsTAGLOCK_KIT.getBoundEntity(world, null, stack, Integer.valueOf(1));
      if (entity != null) {
        X = field_70165_t;
        Y = (field_70163_u + 1.0D);
        Z = field_70161_v;
        D = field_71093_bK;
        valid = true;
      } else {
        X = homeX;
        Y = homeY;
        Z = homeZ;
        D = field_73011_w.field_76574_g;
        valid = false;
      }
    } else {
      X = homeX;
      Y = homeY;
      Z = homeZ;
      D = field_73011_w.field_76574_g;
      valid = false;
    }
  }
}
