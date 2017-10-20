package com.emoniph.witchery.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotClayJar extends Slot
{
  public SlotClayJar(IInventory par2IInventory, int par3, int par4, int par5)
  {
    super(par2IInventory, par3, par4, par5);
  }
  
  public boolean func_75214_a(ItemStack itemstack)
  {
    return (itemstack != null) && (ItemsGENERIC.itemEmptyClayJar.isMatch(itemstack));
  }
  
  public int func_75219_a()
  {
    return 64;
  }
}
