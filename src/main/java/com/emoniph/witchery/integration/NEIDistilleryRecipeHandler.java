package com.emoniph.witchery.integration;

import codechicken.nei.PositionedStack;
import com.emoniph.witchery.crafting.DistilleryRecipes;
import com.emoniph.witchery.crafting.DistilleryRecipes.DistilleryRecipe;
import java.util.ArrayList;

public class NEIDistilleryRecipeHandler extends codechicken.nei.recipe.TemplateRecipeHandler
{
  public NEIDistilleryRecipeHandler() {}
  
  public class CachedDistillingRecipe extends codechicken.nei.recipe.TemplateRecipeHandler.CachedRecipe
  {
    PositionedStack ingred1;
    PositionedStack ingred2;
    PositionedStack jars;
    
    public CachedDistillingRecipe(net.minecraft.item.ItemStack result, DistilleryRecipes.DistilleryRecipe recipe)
    {
      super();
      ingred1 = new PositionedStack(inputs[0], 43, 5);
      if (inputs[1] != null) {
        ingred2 = new PositionedStack(inputs[1], 43, 23);
      }
      
      if (jars > 0) {
        jars = new PositionedStack(ItemsGENERIC.itemEmptyClayJar.createStack(jars), 43, 43);
      }
      

      if (outputs[0] != null) {
        outputs[0] = new PositionedStack(outputs[0], 105, 5);
      }
      
      if (outputs[1] != null) {
        outputs[1] = new PositionedStack(outputs[1], 123, 5);
      }
      
      if (outputs[2] != null) {
        outputs[2] = new PositionedStack(outputs[2], 105, 23);
      }
      
      if (outputs[3] != null) {
        outputs[3] = new PositionedStack(outputs[3], 123, 23);
      }
    }
    

    public PositionedStack getResult()
    {
      return outputs[0];
    }
    
    public ArrayList<PositionedStack> getIngredients()
    {
      ArrayList<PositionedStack> recipestacks = new ArrayList();
      recipestacks.add(ingred1);
      if (ingred2 != null)
        recipestacks.add(ingred2);
      if (jars != null)
        recipestacks.add(jars);
      for (PositionedStack posStack : outputs) {
        if (posStack != null)
          recipestacks.add(posStack);
      }
      return recipestacks;
    }
    



    PositionedStack[] outputs = new PositionedStack[6];
  }
  
  public Class<? extends net.minecraft.client.gui.inventory.GuiContainer> getGuiClass()
  {
    return com.emoniph.witchery.blocks.BlockDistilleryGUI.class;
  }
  
  public String getRecipeName()
  {
    return net.minecraft.util.StatCollector.func_74838_a("tile.witchery:distilleryidle.name");
  }
  
  public void loadTransferRects()
  {
    transferRects.add(new codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect(new java.awt.Rectangle(63, 4, 39, 35), "witchery_distilling", new Object[0]));
  }
  
  public void loadCraftingRecipes(String outputId, Object... results)
  {
    if ((outputId.equals("witchery_distilling")) && (getClass() == NEIDistilleryRecipeHandler.class)) {
      for (DistilleryRecipes.DistilleryRecipe recipe : instancerecipes) {
        arecipes.add(new CachedDistillingRecipe(outputs[0], recipe));
      }
    } else {
      super.loadCraftingRecipes(outputId, results);
    }
  }
  
  public void loadCraftingRecipes(net.minecraft.item.ItemStack result)
  {
    DistilleryRecipes.DistilleryRecipe recipe = DistilleryRecipes.instance().findRecipeFor(result);
    if (recipe != null) {
      arecipes.add(new CachedDistillingRecipe(result, recipe));
    }
  }
  
  public void loadUsageRecipes(net.minecraft.item.ItemStack ingredient)
  {
    DistilleryRecipes.DistilleryRecipe recipe = DistilleryRecipes.instance().findRecipeUsing(ingredient);
    if (recipe != null) {
      arecipes.add(new CachedDistillingRecipe(ingredient, recipe));
    }
  }
  
  public String getGuiTexture()
  {
    return "witchery:textures/gui/distiller.png";
  }
  
  public void drawExtras(int recipe)
  {
    drawProgressBar(63, 3, 176, 29, 39, 35, 200, 0);
    drawProgressBar(28, 8, 185, -2, 12, 30, 35, 3);
  }
  
  public String getOverlayIdentifier()
  {
    return "witchery_distilling";
  }
}
