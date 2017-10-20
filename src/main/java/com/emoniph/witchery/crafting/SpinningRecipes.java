package com.emoniph.witchery.crafting;

import java.util.ArrayList;
import net.minecraft.item.ItemStack;

public class SpinningRecipes
{
  private static final SpinningRecipes INSTANCE = new SpinningRecipes();
  

  public static SpinningRecipes instance() { return INSTANCE; }
  
  private SpinningRecipes() {}
  
  public static class SpinningRecipe {
    public final ItemStack fibre;
    public final ItemStack[] modifiers;
    public final ItemStack result;
    
    private SpinningRecipe(ItemStack result, ItemStack fibre, ItemStack[] modifiers) { this.fibre = fibre;
      this.result = result;
      this.modifiers = modifiers;
    }
    
    public ItemStack getResult() {
      return result;
    }
    
    public ArrayList<ItemStack> getMutableModifiersList() {
      ArrayList<ItemStack> available = new ArrayList();
      for (ItemStack item : modifiers) {
        if (item != null) {
          available.add(item);
        }
      }
      return available;
    }
    
    private boolean isMatch(ItemStack fibre, ItemStack[] modifiers) {
      if ((fibre == null) || (!fibre.func_77969_a(this.fibre)) || (field_77994_a < fibrefield_77994_a)) {
        return false;
      }
      
      ArrayList<ItemStack> available = new ArrayList();
      for (ItemStack item : modifiers) {
        if (item != null) {
          available.add(item);
        }
      }
      
      for (ItemStack modifier : this.modifiers) {
        int index = indexOf(available, modifier);
        if (index == -1) {
          return false;
        }
        available.remove(index);
      }
      return true;
    }
    
    private int indexOf(ArrayList<ItemStack> list, ItemStack item) {
      for (int i = 0; i < list.size(); i++) {
        if (((ItemStack)list.get(i)).func_77969_a(item)) {
          return i;
        }
      }
      return -1;
    }
    
    public boolean uses(ItemStack ingredient)
    {
      if (ingredient == null) {
        return false;
      }
      
      if (fibre.func_77969_a(ingredient)) {
        return true;
      }
      
      for (ItemStack item : modifiers) {
        if ((item != null) && (item.func_77969_a(ingredient))) {
          return true;
        }
      }
      
      return false;
    }
  }
  
  public final ArrayList<SpinningRecipe> recipes = new ArrayList();
  



  public SpinningRecipe addRecipe(ItemStack result, ItemStack fibre, ItemStack... modifiers)
  {
    SpinningRecipe recipe = new SpinningRecipe(result, fibre, modifiers, null);
    recipes.add(recipe);
    return recipe;
  }
  
  public SpinningRecipe getRecipe(ItemStack fibre, ItemStack[] modifiers) {
    for (SpinningRecipe recipe : recipes) {
      if (recipe.isMatch(fibre, modifiers)) {
        return recipe;
      }
    }
    return null;
  }
  
  public SpinningRecipe findRecipeFor(ItemStack result) {
    for (SpinningRecipe recipe : recipes) {
      if (recipe.getResult().func_77969_a(result)) {
        return recipe;
      }
    }
    return null;
  }
  
  public SpinningRecipe findRecipeUsing(ItemStack ingredient) {
    for (SpinningRecipe recipe : recipes) {
      if (recipe.uses(ingredient)) {
        return recipe;
      }
    }
    return null;
  }
  
  public SpinningRecipe findRecipeUsingFibre(ItemStack ingredient) {
    for (SpinningRecipe recipe : recipes) {
      if (fibre.func_77969_a(ingredient)) {
        return recipe;
      }
    }
    return null;
  }
}
