package com.emoniph.witchery.brewing.action;

import com.emoniph.witchery.brewing.AltarPower;
import com.emoniph.witchery.brewing.BrewItemKey;
import com.emoniph.witchery.brewing.EffectLevelCounter;
import com.emoniph.witchery.brewing.ModifiersEffect;
import com.emoniph.witchery.brewing.ModifiersImpact;
import com.emoniph.witchery.brewing.ModifiersRitual;
import com.emoniph.witchery.brewing.RitualStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BrewActionRitualRecipe extends BrewAction
{
  private final Recipe[] recipes;
  
  public static class Recipe
  {
    public final ItemStack result;
    public final ItemStack[] ingredients;
    
    public Recipe(ItemStack result, ItemStack... ingredients)
    {
      this.result = result;
      this.ingredients = ingredients;
    }
    
    public Recipe(ItemStack result, ItemStack[] ingredients, ItemStack finalIngredient) {
      this.result = result;
      this.ingredients = ((ItemStack[])Arrays.copyOf(ingredients, ingredients.length + 1));
      this.ingredients[(this.ingredients.length - 1)] = finalIngredient;
    }
    
    private Recipe getExpandedRecipe(BrewItemKey itemKey) {
      return new Recipe(result, ingredients, itemKey.toStack());
    }
  }
  

  private final List<Recipe> expandedRecipes = new ArrayList();
  
  public BrewActionRitualRecipe(BrewItemKey itemKey, AltarPower powerCost, Recipe... recipes) {
    super(itemKey, null, powerCost, new com.emoniph.witchery.brewing.Probability(1.0D), false);
    this.recipes = recipes;
    for (Recipe recipe : recipes) {
      expandedRecipes.add(recipe.getExpandedRecipe(itemKey));
    }
  }
  
  public List<Recipe> getExpandedRecipes() {
    return expandedRecipes;
  }
  


  public final void applyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack) {}
  


  public final void applyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect effectModifiers, ItemStack stack) {}
  


  public final void prepareSplashPotion(World world, BrewActionList actionList, ModifiersImpact modifiers) {}
  


  public final boolean triggersRitual()
  {
    return true;
  }
  
  public final boolean canAdd(BrewActionList actionList, boolean isCauldronFull, boolean hasEffects)
  {
    return (isCauldronFull) && (getRecipeResult(actionList) != null);
  }
  

  public final RitualStatus updateRitual(MinecraftServer server, BrewActionList actionList, World world, int x, int y, int z, ModifiersRitual modifiers, ModifiersImpact impactModifiers)
  {
    ItemStack result = getRecipeResult(actionList);
    if (result != null) {
      EntityItem item = new EntityItem(world, x + 0.5D, y + 1.5D, z + 0.5D, result.func_77946_l());
      field_70159_w = 0.0D;
      field_70181_x = 0.2D;
      field_70179_y = 0.0D;
      com.emoniph.witchery.util.EntityUtil.spawnEntityInWorld(world, item);
      
      return RitualStatus.COMPLETE;
    }
    return RitualStatus.FAILED;
  }
  
  private ItemStack getRecipeResult(BrewActionList actionList)
  {
    for (Recipe recipe : recipes) {
      if (ingredients.length > 0) {
        ArrayList<ItemStack> neededItems = new ArrayList();
        neededItems.addAll(Arrays.asList(ingredients));
        for (BrewAction action : actions) {
          removeFromNeededItems(neededItems, ITEM_KEY);
        }
        if (neededItems.size() == 0) {
          return result;
        }
      } else {
        return result;
      }
    }
    return null;
  }
  
  private void removeFromNeededItems(ArrayList<ItemStack> neededItems, BrewItemKey item) {
    Iterator<ItemStack> iterator = neededItems.iterator();
    while (iterator.hasNext()) {
      ItemStack stack = (ItemStack)iterator.next();
      if ((stack.func_77973_b() == ITEM) && (stack.func_77960_j() == DAMAGE)) {
        iterator.remove();
        return;
      }
    }
  }
  
  public final boolean augmentEffectLevels(EffectLevelCounter totalEffects)
  {
    return true;
  }
  
  public final void augmentEffectModifiers(ModifiersEffect modifiers) {}
  
  public final void prepareRitual(World world, int x, int y, int z, ModifiersRitual modifiers, ItemStack stack) {}
}
