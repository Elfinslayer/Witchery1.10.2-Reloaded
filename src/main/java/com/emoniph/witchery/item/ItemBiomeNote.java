package com.emoniph.witchery.item;

import net.minecraft.item.ItemStack;

public class ItemBiomeNote extends ItemBase
{
  public ItemBiomeNote() {
    func_77627_a(true);
    func_77656_e(0);
  }
  
  public String func_77653_i(ItemStack stack)
  {
    String name = super.func_77653_i(stack);
    net.minecraft.world.biome.BiomeGenBase biome = ItemBook.getSelectedBiome(stack.func_77960_j());
    if (biome != null) {
      return String.format(name, new Object[] { field_76791_y });
    }
    return String.format(name, new Object[] { "" }).trim();
  }
}
