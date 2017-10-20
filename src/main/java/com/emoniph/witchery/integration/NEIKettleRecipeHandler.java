package com.emoniph.witchery.integration;

import codechicken.nei.PositionedStack;
import com.emoniph.witchery.crafting.KettleRecipes.KettleRecipe;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;

public class NEIKettleRecipeHandler extends codechicken.nei.recipe.TemplateRecipeHandler
{
  public NEIKettleRecipeHandler() {}
  
  public class CachedKettleRecipe extends codechicken.nei.recipe.TemplateRecipeHandler.CachedRecipe
  {
    PositionedStack result;
    
    public CachedKettleRecipe(ItemStack result, KettleRecipes.KettleRecipe recipe)
    {
      super();
      this.result = new PositionedStack(result, 119, 24);
      for (int i = 0; i < inputs.length; i++) {
        if (inputs[i] != null) {
          inputs[i] = new PositionedStack(inputs[i], i < 3 ? 25 : 43, i * 18 % 54 + 6);
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
    transferRects.add(new codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect(new java.awt.Rectangle(84, 23, 24, 18), "witchery_brewing", new Object[0]));
  }
  
  public Class<? extends net.minecraft.client.gui.inventory.GuiContainer> getGuiClass()
  {
    return net.minecraft.client.gui.inventory.GuiCrafting.class;
  }
  
  public String getRecipeName()
  {
    return net.minecraft.util.StatCollector.func_74838_a("tile.witchery:kettle.name");
  }
  
  public void loadCraftingRecipes(String outputId, Object... results)
  {
    if ((outputId.equals("witchery_brewing")) && (getClass() == NEIKettleRecipeHandler.class)) {
      for (KettleRecipes.KettleRecipe recipe : instancerecipes) {
        arecipes.add(new CachedKettleRecipe(output, recipe));
      }
    } else {
      super.loadCraftingRecipes(outputId, results);
    }
  }
  
  public void loadCraftingRecipes(ItemStack result)
  {
    KettleRecipes.KettleRecipe recipe = com.emoniph.witchery.crafting.KettleRecipes.instance().findRecipeFor(result);
    if (recipe != null) {
      arecipes.add(new CachedKettleRecipe(output, recipe));
    }
  }
  

  public void loadUsageRecipes(ItemStack ingredient) {}
  

  public String getGuiTexture()
  {
    return "textures/gui/container/crafting_table.png";
  }
  

  public void drawExtras(int recipe) {}
  

  public String getOverlayIdentifier()
  {
    return "witchery_brewing";
  }
}
