package com.emoniph.witchery.crafting;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipeShapelessPoppet implements IRecipe
{
  final ItemStack prototype;
  final ItemStack[] pattern;
  
  public RecipeShapelessPoppet(ItemStack resultPoppet, ItemStack... pattern)
  {
    prototype = resultPoppet;
    this.pattern = pattern;
  }
  
  public ItemStack func_77571_b()
  {
    return null;
  }
  



  public boolean func_77569_a(InventoryCrafting par1InventoryCrafting, World par2World)
  {
    ArrayList<ItemStack> arraylist = new ArrayList(java.util.Arrays.asList(pattern));
    
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        ItemStack itemstack = par1InventoryCrafting.func_70463_b(j, i);
        
        if (itemstack != null) {
          boolean flag = false;
          Iterator iterator = arraylist.iterator();
          
          while (iterator.hasNext()) {
            ItemStack itemstack1 = (ItemStack)iterator.next();
            
            if ((itemstack.func_77973_b() == itemstack1.func_77973_b()) && ((itemstack1.func_77960_j() == 32767) || (itemstack.func_77960_j() == itemstack1.func_77960_j())))
            {




              flag = true;
              arraylist.remove(itemstack1);
              break;
            }
          }
          

          if (!flag) {
            return false;
          }
        }
      }
    }
    
    return arraylist.isEmpty();
  }
  
  public ItemStack func_77572_b(InventoryCrafting inv)
  {
    ItemStack newPoppet = prototype.func_77946_l();
    int i = 0; for (int index = 1; i < inv.func_70302_i_(); i++) {
      ItemStack stack = inv.func_70301_a(i);
      if ((stack != null) && (stack.func_77973_b() == ItemsTAGLOCK_KIT)) {
        ItemsTAGLOCK_KIT.addTagLockToPoppet(stack, newPoppet, Integer.valueOf(index++));
      }
    }
    ItemStack recipePoppet = findRecipeItemStack(inv, ItemsPOPPET);
    if (recipePoppet != null) {
      ItemsPOPPET.addDamageToPoppet(recipePoppet, newPoppet);
    }
    return newPoppet;
  }
  
  private ItemStack findRecipeItemStack(InventoryCrafting inv, Item itemToFind) {
    for (int i = 0; i < inv.func_70302_i_(); i++) {
      ItemStack stack = inv.func_70301_a(i);
      if ((stack != null) && (stack.func_77973_b() == itemToFind)) {
        return stack;
      }
    }
    return null;
  }
  
  public int func_77570_a()
  {
    return pattern.length;
  }
}
