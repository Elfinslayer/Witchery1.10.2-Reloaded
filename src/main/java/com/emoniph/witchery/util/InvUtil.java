package com.emoniph.witchery.util;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InvUtil
{
  private InvUtil() {}
  
  public static int getSlotContainingItem(InventoryPlayer inventory, Item item, int damage)
  {
    for (int k = 0; k < field_70462_a.length; k++) {
      ItemStack stack = field_70462_a[k];
      if ((stack != null) && (stack.func_77973_b() == item) && (stack.func_77960_j() == damage)) {
        return k;
      }
    }
    
    return -1;
  }
  
  public static int getSlotContainingItem(InventoryPlayer inventory, Item item) {
    for (int k = 0; k < field_70462_a.length; k++) {
      ItemStack stack = field_70462_a[k];
      if ((stack != null) && (stack.func_77973_b() == item)) {
        return k;
      }
    }
    
    return -1;
  }
  
  public static int getSlotContainingItem(net.minecraft.inventory.IInventory inventory, Item item, int damage) {
    for (int k = 0; k < inventory.func_70302_i_(); k++) {
      ItemStack stack = inventory.func_70301_a(k);
      if ((stack != null) && (stack.func_77973_b() == item) && (stack.func_77960_j() == damage)) {
        return k;
      }
    }
    
    return -1;
  }
  
  public static boolean hasItem(InventoryPlayer inventory, Item item, int damage) {
    return getSlotContainingItem(inventory, item, damage) >= 0;
  }
  
  public static boolean consumeItem(InventoryPlayer inventory, Item item, int damage) {
    int j = getSlotContainingItem(inventory, item, damage);
    
    if (j < 0) {
      return false;
    }
    if (--field_70462_a[j].field_77994_a <= 0) {
      field_70462_a[j] = null;
    }
    
    return true;
  }
  

  public static int getItemStackCount(net.minecraft.inventory.IInventory inv)
  {
    int itemCount = 0;
    if (inv != null) {
      for (int i = 0; i < inv.func_70302_i_(); i++) {
        ItemStack stack = inv.func_70301_a(i);
        if (stack != null) {
          itemCount++;
        }
      }
    }
    return itemCount;
  }
}
