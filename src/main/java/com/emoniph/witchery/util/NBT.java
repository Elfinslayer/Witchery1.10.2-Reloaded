package com.emoniph.witchery.util;

import net.minecraft.nbt.NBTTagCompound;

public class NBT
{
  public NBT() {}
  
  public static NBTTagCompound get(net.minecraft.item.ItemStack stack)
  {
    if (!stack.func_77942_o()) {
      stack.func_77982_d(new NBTTagCompound());
    }
    return stack.func_77978_p();
  }
  
  public static NBTTagCompound get(net.minecraft.entity.item.EntityItem entity) {
    return entity.getEntityData();
  }
  
  public static NBTTagCompound get(net.minecraft.entity.EntityLiving entity) {
    return entity.getEntityData();
  }
  
  public static NBTTagCompound get(net.minecraft.entity.player.EntityPlayer player) {
    NBTTagCompound nbtPlayer = player.getEntityData();
    NBTTagCompound nbtPersistant = nbtPlayer.func_74775_l("PlayerPersisted");
    if (!nbtPlayer.func_74764_b("PlayerPersisted")) {
      nbtPlayer.func_74782_a("PlayerPersisted", nbtPersistant);
    }
    return nbtPersistant;
  }
}
