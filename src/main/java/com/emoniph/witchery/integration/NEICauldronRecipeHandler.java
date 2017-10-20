package com.emoniph.witchery.integration;

import codechicken.nei.PositionedStack;
import com.emoniph.witchery.brewing.action.BrewActionRitualRecipe;
import com.emoniph.witchery.brewing.action.BrewActionRitualRecipe.Recipe;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;

public class NEICauldronRecipeHandler extends codechicken.nei.recipe.TemplateRecipeHandler
{
  public NEICauldronRecipeHandler() {}
  
  public class CachedKettleRecipe extends codechicken.nei.recipe.TemplateRecipeHandler.CachedRecipe
  {
    PositionedStack result;
    
    public CachedKettleRecipe(ItemStack result, ItemStack[] recipe)
    {
      super();
      this.result = new PositionedStack(result, 119, 31);
      for (int i = 0; i < recipe.length; i++) {
        if (recipe[i] != null) {
          inputs[i] = new PositionedStack(recipe[i], i * 18 + 10, 6);
        } else {
          inputs[i] = null;
        }
      }
    }
    
    public PositionedStack getResult()
    {
      return result;
    }
    
    public ArrayList<PositionedStack> getIngredients()
    {
      ArrayList<PositionedStack> recipestacks = new ArrayList();
      recipestacks.add(result);
      for (PositionedStack posStack : inputs) {
        if (posStack != null)
          recipestacks.add(posStack);
      }
      return recipestacks;
    }
    

    PositionedStack[] inputs = new PositionedStack[6];
  }
  
  public void loadTransferRects()
  {
    transferRects.add(new codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect(new java.awt.Rectangle(92, 31, 24, 18), "witchery_brewing_plus", new Object[0]));
  }
  
  public Class<? extends net.minecraft.client.gui.inventory.GuiContainer> getGuiClass()
  {
    return net.minecraft.client.gui.inventory.GuiCrafting.class;
  }
  
  public String getRecipeName()
  {
    return net.minecraft.util.StatCollector.func_74838_a("tile.witchery:cauldron.name");
  }
  
  public void loadCraftingRecipes(String outputId, Object... results)
  {
    if ((outputId.equals("witchery_brewing_plus")) && (getClass() == NEICauldronRecipeHandler.class))
    {
      for (BrewActionRitualRecipe ritual : com.emoniph.witchery.brewing.WitcheryBrewRegistry.INSTANCE.getRecipes()) {
        for (BrewActionRitualRecipe.Recipe recipe : ritual.getExpandedRecipes()) {
          arecipes.add(new CachedKettleRecipe(result, ingredients));
        }
        
      }
    } else {
      super.loadCraftingRecipes(outputId, results);
    }
  }
  
  public void loadCraftingRecipes(ItemStack result)
  {
    for (BrewActionRitualRecipe ritual : com.emoniph.witchery.brewing.WitcheryBrewRegistry.INSTANCE.getRecipes()) {
      for (BrewActionRitualRecipe.Recipe recipe : ritual.getExpandedRecipes()) {
        if (result.func_77969_a(result)) {
          arecipes.add(new CachedKettleRecipe(result, ingredients));
        }
      }
    }
  }
  
  public void loadUsageRecipes(ItemStack ingredient)
  {
    for (BrewActionRitualRecipe ritual : com.emoniph.witchery.brewing.WitcheryBrewRegistry.INSTANCE.getRecipes()) {
      for (BrewActionRitualRecipe.Recipe recipe : ritual.getExpandedRecipes()) {
        for (ItemStack stack : ingredients) {
          if (stack.func_77969_a(ingredient)) {
            arecipes.add(new CachedKettleRecipe(result, ingredients));
          }
        }
      }
    }
  }
  

  public String getGuiTexture()
  {
    return "witchery:textures/gui/witchesCauldron.png";
  }
  

  public void drawExtras(int recipe) {}
  

  public String getOverlayIdentifier()
  {
    return "witchery_brewing_plus";
  }
}
