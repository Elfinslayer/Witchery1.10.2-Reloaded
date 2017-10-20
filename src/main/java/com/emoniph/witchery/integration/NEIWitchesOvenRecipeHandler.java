package com.emoniph.witchery.integration;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Set;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class NEIWitchesOvenRecipeHandler extends TemplateRecipeHandler
{
  public static ArrayList<FuelPair> afuels;
  public static java.util.TreeSet<Integer> efuels;
  public NEIWitchesOvenRecipeHandler() {}
  
  public class SmeltingPair extends codechicken.nei.recipe.TemplateRecipeHandler.CachedRecipe
  {
    PositionedStack ingred;
    PositionedStack result;
    PositionedStack byproduct;
    PositionedStack jar;
    
    public SmeltingPair(ItemStack ingred, ItemStack result, ItemStack byproduct)
    {
      super();
      field_77994_a = 1;
      this.ingred = new PositionedStack(ingred, 51, 6);
      this.result = new PositionedStack(result, 113, 10);
      this.byproduct = new PositionedStack(byproduct, 113, 42);
      jar = new PositionedStack(ItemsGENERIC.itemEmptyClayJar.createStack(), 78, 42);
    }
    
















    public java.util.List<PositionedStack> getIngredients()
    {
      return getCycledIngredients(cycleticks / 48, java.util.Arrays.asList(new PositionedStack[] { ingred }));
    }
    
    public PositionedStack getResult()
    {
      return result;
    }
    
    public PositionedStack getOtherStack()
    {
      
      if ((NEIWitchesOvenRecipeHandler.afuels != null) && (NEIWitchesOvenRecipeHandler.afuels.size() > 0)) {
        return afuelsgetcycleticks / 48 % NEIWitchesOvenRecipeHandler.afuels.size())).stack;
      }
      return null;
    }
    

    public java.util.List<PositionedStack> getOtherStacks()
    {
      ArrayList<PositionedStack> stacks = new ArrayList();
      PositionedStack stack = getOtherStack();
      if (stack != null) {
        stacks.add(stack);
      }
      stacks.add(byproduct);
      stacks.add(jar);
      
      return stacks;
    }
  }
  
  public static class FuelPair
  {
    public PositionedStack stack;
    public int burnTime;
    
    public FuelPair(ItemStack ingred, int burnTime)
    {
      stack = new PositionedStack(ingred, 51, 42);
      this.burnTime = burnTime;
    }
  }
  








  public Class<? extends net.minecraft.client.gui.inventory.GuiContainer> getGuiClass()
  {
    return com.emoniph.witchery.blocks.BlockWitchesOvenGUI.class;
  }
  
  public String getRecipeName()
  {
    return net.minecraft.util.StatCollector.func_74838_a("tile.witchery:witchesovenidle.name");
  }
  


  public void loadCraftingRecipes(String outputId, Object... results)
  {
    if ((outputId.equals("witchery_cooking")) && (getClass() == NEIWitchesOvenRecipeHandler.class)) {
      java.util.Map<ItemStack, ItemStack> recipes = net.minecraft.item.crafting.FurnaceRecipes.func_77602_a().func_77599_b();
      



      arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 0), ItemsGENERIC.itemAshWood.createStack(), ItemsGENERIC.itemExhaleOfTheHornedOne.createStack()));
      arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 1), ItemsGENERIC.itemAshWood.createStack(), ItemsGENERIC.itemHintOfRebirth.createStack()));
      arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 2), ItemsGENERIC.itemAshWood.createStack(), ItemsGENERIC.itemBreathOfTheGoddess.createStack()));
      arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 3), ItemsGENERIC.itemAshWood.createStack(), ItemsGENERIC.itemFoulFume.createStack()));
      arecipes.add(new SmeltingPair(new ItemStack(BlocksSAPLING, 1, 0), ItemsGENERIC.itemAshWood.createStack(), ItemsGENERIC.itemWhiffOfMagic.createStack()));
      arecipes.add(new SmeltingPair(new ItemStack(BlocksSAPLING, 1, 1), ItemsGENERIC.itemAshWood.createStack(), ItemsGENERIC.itemReekOfMisfortune.createStack()));
      arecipes.add(new SmeltingPair(new ItemStack(BlocksSAPLING, 1, 2), ItemsGENERIC.itemAshWood.createStack(), ItemsGENERIC.itemOdourOfPurity.createStack()));
      
      for (Map.Entry<ItemStack, ItemStack> recipe : recipes.entrySet()) {
        ItemStack result = (ItemStack)recipe.getValue();
        ItemStack ingred = new ItemStack(((ItemStack)recipe.getKey()).func_77973_b(), 1, -1);
        ItemStack byproduct = ItemsGENERIC.itemFoulFume.createStack();
        if (ingred.func_77973_b() == Item.func_150898_a(Blocks.field_150345_g)) {
          byproduct = ingred.func_77960_j() == 1 ? ItemsGENERIC.itemHintOfRebirth.createStack() : ingred.func_77960_j() == 2 ? ItemsGENERIC.itemBreathOfTheGoddess.createStack() : ingred.func_77960_j() == 0 ? ItemsGENERIC.itemExhaleOfTheHornedOne.createStack() : ItemsGENERIC.itemFoulFume.createStack();
          


          arecipes.add(new SmeltingPair(ingred, result, byproduct));
        } else if (ingred.func_77973_b() == Item.func_150898_a(BlocksSAPLING)) {
          byproduct = ingred.func_77960_j() == 1 ? ItemsGENERIC.itemReekOfMisfortune.createStack() : ingred.func_77960_j() == 2 ? ItemsGENERIC.itemOdourOfPurity.createStack() : ingred.func_77960_j() == 0 ? ItemsGENERIC.itemWhiffOfMagic.createStack() : ItemsGENERIC.itemFoulFume.createStack();
          


          arecipes.add(new SmeltingPair(ingred, result, byproduct));
        } else if ((ItemsGENERIC.itemAshWood.isMatch(result)) || ((result.func_77973_b() == net.minecraft.init.Items.field_151044_h) && (result.func_77960_j() == 1)) || ((ingred.func_77973_b() instanceof net.minecraft.item.ItemFood))) {
          arecipes.add(new SmeltingPair(ingred, result, byproduct));





        }
        





      }
      





    }
    else
    {





      super.loadCraftingRecipes(outputId, results);
    }
  }
  
  public void loadCraftingRecipes(ItemStack result2)
  {
    java.util.Map<ItemStack, ItemStack> recipes = net.minecraft.item.crafting.FurnaceRecipes.func_77602_a().func_77599_b();
    













    for (Map.Entry<ItemStack, ItemStack> recipe : recipes.entrySet()) {
      ItemStack result = (ItemStack)recipe.getKey();
      if (codechicken.nei.NEIServerUtils.areStacksSameType(result, result2)) {
        ItemStack ingred = new ItemStack(((ItemStack)recipe.getValue()).func_77973_b(), 1, -1);
        ItemStack byproduct = ItemsGENERIC.itemFoulFume.createStack();
        if (ingred.func_77973_b() == Item.func_150898_a(Blocks.field_150345_g)) {
          byproduct = ingred.func_77960_j() == 1 ? ItemsGENERIC.itemHintOfRebirth.createStack() : ingred.func_77960_j() == 2 ? ItemsGENERIC.itemBreathOfTheGoddess.createStack() : ingred.func_77960_j() == 0 ? ItemsGENERIC.itemExhaleOfTheHornedOne.createStack() : ItemsGENERIC.itemFoulFume.createStack();
          


          arecipes.add(new SmeltingPair(ingred, result, byproduct));
        } else if (ingred.func_77973_b() == Item.func_150898_a(BlocksSAPLING)) {
          byproduct = ingred.func_77960_j() == 1 ? ItemsGENERIC.itemReekOfMisfortune.createStack() : ingred.func_77960_j() == 2 ? ItemsGENERIC.itemOdourOfPurity.createStack() : ingred.func_77960_j() == 0 ? ItemsGENERIC.itemWhiffOfMagic.createStack() : ItemsGENERIC.itemFoulFume.createStack();
          


          arecipes.add(new SmeltingPair(ingred, result, byproduct));
        } else if ((ItemsGENERIC.itemAshWood.isMatch(result)) || (ingred.func_77973_b() == net.minecraft.init.Items.field_151044_h) || ((ingred.func_77973_b() instanceof net.minecraft.item.ItemFood))) {
          arecipes.add(new SmeltingPair(ingred, result, byproduct));
        }
      }
    }
    


























    if (ItemsGENERIC.itemExhaleOfTheHornedOne.isMatch(result2)) {
      arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 0), ItemsGENERIC.itemAshWood.createStack(), result2));
    } else if (ItemsGENERIC.itemBreathOfTheGoddess.isMatch(result2)) {
      arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 2), ItemsGENERIC.itemAshWood.createStack(), result2));
    } else if (ItemsGENERIC.itemHintOfRebirth.isMatch(result2)) {
      arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 1), ItemsGENERIC.itemAshWood.createStack(), result2));
    } else if (ItemsGENERIC.itemWhiffOfMagic.isMatch(result2)) {
      arecipes.add(new SmeltingPair(new ItemStack(BlocksSAPLING, 1, 0), ItemsGENERIC.itemAshWood.createStack(), result2));
    } else if (ItemsGENERIC.itemOdourOfPurity.isMatch(result2)) {
      arecipes.add(new SmeltingPair(new ItemStack(BlocksSAPLING, 1, 2), ItemsGENERIC.itemAshWood.createStack(), result2));
    } else if (ItemsGENERIC.itemReekOfMisfortune.isMatch(result2)) {
      arecipes.add(new SmeltingPair(new ItemStack(BlocksSAPLING, 1, 1), ItemsGENERIC.itemAshWood.createStack(), result2));
    } else if (ItemsGENERIC.itemFoulFume.isMatch(result2)) {
      arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150364_r), new ItemStack(net.minecraft.init.Items.field_151044_h, 1), result2));
    }
  }
  

  public void loadUsageRecipes(String inputId, Object... ingredients)
  {
    if ((inputId.equals("fuel")) && (getClass() == NEIWitchesOvenRecipeHandler.class)) {
      loadCraftingRecipes("witchery_cooking", new Object[0]);
    } else {
      super.loadUsageRecipes(inputId, ingredients);
    }
  }
  













  public void loadUsageRecipes(ItemStack ingred)
  {
    java.util.Map<ItemStack, ItemStack> recipes = net.minecraft.item.crafting.FurnaceRecipes.func_77602_a().func_77599_b();
    
    for (Map.Entry<ItemStack, ItemStack> recipe : recipes.entrySet()) {
      ItemStack result = (ItemStack)recipe.getValue();
      if (ingred.func_77973_b() == ((ItemStack)recipe.getKey()).func_77973_b())
      {
        ItemStack byproduct = ItemsGENERIC.itemFoulFume.createStack();
        if (ingred.func_77973_b() == Item.func_150898_a(Blocks.field_150345_g)) {
          byproduct = ingred.func_77960_j() == 2 ? ItemsGENERIC.itemHintOfRebirth.createStack() : ingred.func_77960_j() == 1 ? ItemsGENERIC.itemBreathOfTheGoddess.createStack() : ingred.func_77960_j() == 0 ? ItemsGENERIC.itemExhaleOfTheHornedOne.createStack() : ItemsGENERIC.itemFoulFume.createStack();
          


          arecipes.add(new SmeltingPair(ingred, result, byproduct));
        } else if (ingred.func_77973_b() == Item.func_150898_a(BlocksSAPLING)) {
          byproduct = ingred.func_77960_j() == 2 ? ItemsGENERIC.itemReekOfMisfortune.createStack() : ingred.func_77960_j() == 1 ? ItemsGENERIC.itemOdourOfPurity.createStack() : ingred.func_77960_j() == 0 ? ItemsGENERIC.itemWhiffOfMagic.createStack() : ItemsGENERIC.itemFoulFume.createStack();
          


          arecipes.add(new SmeltingPair(ingred, result, byproduct));
        } else if ((ItemsGENERIC.itemAshWood.isMatch(result)) || (ingred.func_77973_b() == net.minecraft.init.Items.field_151044_h) || ((ingred.func_77973_b() instanceof net.minecraft.item.ItemFood))) {
          arecipes.add(new SmeltingPair(ingred, result, byproduct));
        }
      }
    }
    


    if (ItemsGENERIC.itemEmptyClayJar.isMatch(ingred))
    {
      arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 0), ItemsGENERIC.itemAshWood.createStack(), ItemsGENERIC.itemExhaleOfTheHornedOne.createStack()));
      arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 1), ItemsGENERIC.itemAshWood.createStack(), ItemsGENERIC.itemHintOfRebirth.createStack()));
      arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 2), ItemsGENERIC.itemAshWood.createStack(), ItemsGENERIC.itemBreathOfTheGoddess.createStack()));
      arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 3), ItemsGENERIC.itemAshWood.createStack(), ItemsGENERIC.itemFoulFume.createStack()));
      arecipes.add(new SmeltingPair(new ItemStack(BlocksSAPLING, 1, 0), ItemsGENERIC.itemAshWood.createStack(), ItemsGENERIC.itemWhiffOfMagic.createStack()));
      arecipes.add(new SmeltingPair(new ItemStack(BlocksSAPLING, 1, 1), ItemsGENERIC.itemAshWood.createStack(), ItemsGENERIC.itemReekOfMisfortune.createStack()));
      arecipes.add(new SmeltingPair(new ItemStack(BlocksSAPLING, 1, 2), ItemsGENERIC.itemAshWood.createStack(), ItemsGENERIC.itemOdourOfPurity.createStack()));
      arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150364_r), new ItemStack(net.minecraft.init.Items.field_151044_h, 1, 1), ItemsGENERIC.itemFoulFume.createStack()));
    }
  }
  


























  public String getGuiTexture()
  {
    return "witchery:textures/gui/witchesOven.png";
  }
  

  public void loadTransferRects()
  {
    transferRects.add(new codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect(new java.awt.Rectangle(50, 23, 18, 18), "fuel", new Object[0]));
    transferRects.add(new codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect(new java.awt.Rectangle(74, 9, 24, 18), "witchery_cooking", new Object[0]));
  }
  

  public void drawExtras(int recipe)
  {
    drawProgressBar(51, 25, 176, 0, 14, 14, 48, 7);
    drawProgressBar(74, 9, 176, 14, 24, 16, 48, 0);
  }
  
  private static Set<Item> excludedFuels() {
    Set<Item> efuels = new java.util.HashSet();
    efuels.add(Item.func_150898_a(Blocks.field_150338_P));
    efuels.add(Item.func_150898_a(Blocks.field_150337_Q));
    efuels.add(Item.func_150898_a(Blocks.field_150472_an));
    efuels.add(Item.func_150898_a(Blocks.field_150444_as));
    efuels.add(Item.func_150898_a(Blocks.field_150466_ao));
    efuels.add(Item.func_150898_a(Blocks.field_150447_bR));
    return efuels;
  }
  
  private static void findFuels() {
    afuels = new ArrayList();
    Set<Item> efuels = excludedFuels();
    for (ItemStack item : codechicken.nei.ItemList.items)
      if ((item != null) && (!efuels.contains(item.func_77973_b()))) {
        int burnTime = net.minecraft.tileentity.TileEntityFurnace.func_145952_a(item);
        if (burnTime > 0)
          afuels.add(new FuelPair(item.func_77946_l(), burnTime));
      }
  }
  
  private static void findFuelsOnce() {
    if (afuels == null) {
      findFuels();
    }
  }
  
  public String getOverlayIdentifier()
  {
    return "witchery_cooking";
  }
  
  public TemplateRecipeHandler newInstance()
  {
    findFuelsOnce();
    return super.newInstance();
  }
}
