package com.emoniph.witchery.util;

import net.minecraft.item.Item;

public class ItemUtil {
  public ItemUtil() {}
  
  public static void registerItem(Item item, String itemName) { int index = itemName.indexOf(':');
    if (index != -1) {
      itemName = itemName.substring(index + 1);
    }
    cpw.mods.fml.common.registry.GameRegistry.registerItem(item, itemName);
  }
}
