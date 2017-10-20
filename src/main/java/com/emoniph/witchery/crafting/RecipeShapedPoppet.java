package com.emoniph.witchery.crafting;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RecipeShapedPoppet implements net.minecraft.item.crafting.IRecipe
{
  final ItemStack prototype;
  final Item[] pattern;
  
  public RecipeShapedPoppet(ItemStack resultPoppet, Item[] pattern)
  {
    prototype = resultPoppet;
    this.pattern = pattern;
  }
  

  public boolean func_77569_a(InventoryCrafting inv, World world)
  {
    for (int i = 0; i < inv.func_70302_i_(); i++) {
      ItemStack stack = inv.func_70301_a(i);
      if ((pattern[i] != null) || (stack != null))
      {
        if ((stack == null) || (pattern[i] == null))
          return false;
        if (stack.func_77973_b() != pattern[i])
          return false;
        if ((stack.func_77973_b() == ItemsTAGLOCK_KIT) && 
          (stack.func_77960_j() != 1))
        {


          return false;
        }
      }
    }
    return true;
  }
  
  public ItemStack func_77572_b(InventoryCrafting inv)
  {
    ItemStack stackPoppet = prototype.func_77946_l();
    ItemStack stackTaglockKit = findTaglockKit(inv);
    if (stackTaglockKit != null) {
      ItemsTAGLOCK_KIT.addTagLockToPoppet(stackTaglockKit, stackPoppet, Integer.valueOf(1));
    }
    return stackPoppet;
  }
  
  private ItemStack findTaglockKit(InventoryCrafting inv) {
    for (int i = 0; i < inv.func_70302_i_(); i++) {
      ItemStack stack = inv.func_70301_a(1);
      if (stack.func_77973_b() == ItemsTAGLOCK_KIT) {
        return stack;
      }
    }
    return null;
  }
  
  public int func_77570_a()
  {
    return pattern.length;
  }
  
  public ItemStack func_77571_b()
  {
    return null;
  }
}
