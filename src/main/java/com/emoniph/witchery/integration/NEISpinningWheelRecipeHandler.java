package com.emoniph.witchery.integration;

import codechicken.nei.PositionedStack;
import java.util.ArrayList;

public class NEISpinningWheelRecipeHandler extends codechicken.nei.recipe.TemplateRecipeHandler
{
  public NEISpinningWheelRecipeHandler() {}
  
  public class CachedSpinningRecipe extends codechicken.nei.recipe.TemplateRecipeHandler.CachedRecipe
  {
    PositionedStack fibre;
    PositionedStack output;
    PositionedStack add1;
    PositionedStack add2;
    PositionedStack add3;
    
    public CachedSpinningRecipe(net.minecraft.item.ItemStack result, com.emoniph.witchery.crafting.SpinningRecipes.SpinningRecipe recipe)
    {
      super();
      fibre = new PositionedStack(fibre, 51, 9);
      output = new PositionedStack(recipe.getResult(), 113, 9);
      
      if ((modifiers.length > 0) && (modifiers[0] != null)) {
        add1 = new PositionedStack(modifiers[0], 51, 42);
      }
      
      if ((modifiers.length > 1) && (modifiers[1] != null)) {
        add2 = new PositionedStack(modifiers[1], 69, 42);
      }
      
      if ((modifiers.length > 2) && (modifiers[2] != null)) {
        add3 = new PositionedStack(modifiers[2], 87, 42);
      }
    }
    
    public PositionedStack getResult()
    {
      return output;
    }
    
    public ArrayList<PositionedStack> getIngredients()
    {
      ArrayList<PositionedStack> recipestacks = new ArrayList();
      recipestacks.add(fibre);
      recipestacks.add(output);
      if (add1 != null) {
        recipestacks.add(add1);
      }
      if (add2 != null) {
        recipestacks.add(add2);
      }
      if (add3 != null) {
        recipestacks.add(add3);
      }
      
      return recipestacks;
    }
  }
  






  public Class<? extends net.minecraft.client.gui.inventory.GuiContainer> getGuiClass()
  {
    return com.emoniph.witchery.blocks.BlockSpinningWheelGUI.class;
  }
  
  public String getRecipeName()
  {
    return net.minecraft.util.StatCollector.func_74838_a("tile.witchery:spinningwheel.name");
  }
  
  public void loadTransferRects()
  {
    transferRects.add(new codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect(new java.awt.Rectangle(74, 9, 24, 18), "witchery_spinning", new Object[0]));
  }
  


  public void loadCraftingRecipes(String outputId, Object... results)
  {
    if ((outputId.equals("witchery_spinning")) && (getClass() == NEISpinningWheelRecipeHandler.class)) {
      for (com.emoniph.witchery.crafting.SpinningRecipes.SpinningRecipe recipe : instancerecipes) {
        arecipes.add(new CachedSpinningRecipe(recipe.getResult(), recipe));
      }
    } else {
      super.loadCraftingRecipes(outputId, results);
    }
  }
  
  public void loadCraftingRecipes(net.minecraft.item.ItemStack result)
  {
    com.emoniph.witchery.crafting.SpinningRecipes.SpinningRecipe recipe = com.emoniph.witchery.crafting.SpinningRecipes.instance().findRecipeFor(result);
    if (recipe != null) {
      arecipes.add(new CachedSpinningRecipe(result, recipe));
    }
  }
  
  public void loadUsageRecipes(net.minecraft.item.ItemStack ingredient)
  {
    com.emoniph.witchery.crafting.SpinningRecipes.SpinningRecipe recipe = com.emoniph.witchery.crafting.SpinningRecipes.instance().findRecipeUsing(ingredient);
    if (recipe != null) {
      arecipes.add(new CachedSpinningRecipe(ingredient, recipe));
    }
  }
  
  public String getGuiTexture()
  {
    return "witchery:textures/gui/spinningwheel.png";
  }
  
  public void drawExtras(int recipe)
  {
    drawProgressBar(51, 25, 176, 0, 14, 14, 48, 7);
  }
  
  public String getOverlayIdentifier()
  {
    return "witchery_spinning";
  }
}
