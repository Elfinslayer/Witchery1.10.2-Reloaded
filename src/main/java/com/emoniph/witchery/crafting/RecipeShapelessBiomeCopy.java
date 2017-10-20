package com.emoniph.witchery.crafting;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.item.ItemBook;
import com.emoniph.witchery.util.InvUtil;
import java.util.Arrays;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;

public class RecipeShapelessBiomeCopy extends ShapelessRecipes
{
  public RecipeShapelessBiomeCopy(ItemStack result, ItemStack... ingredients)
  {
    super(result, Arrays.asList(ingredients));
  }
  
  public ItemStack func_77572_b(InventoryCrafting matrix)
  {
    ItemStack result = super.func_77572_b(matrix);
    
    int slot = InvUtil.getSlotContainingItem(matrix, ItemsBIOME_BOOK, 0);
    if (slot != -1) {
      ItemStack stack = matrix.func_70301_a(slot);
      int biomeNumber = ItemBook.getSelectedBiome(stack, 1000);
      result.func_77964_b(biomeNumber);
    }
    
    return result;
  }
}
