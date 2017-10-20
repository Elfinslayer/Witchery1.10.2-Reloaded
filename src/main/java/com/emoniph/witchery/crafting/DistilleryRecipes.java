package com.emoniph.witchery.crafting;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.util.Const;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;


public class DistilleryRecipes
{
  private static final DistilleryRecipes INSTANCE = new DistilleryRecipes();
  

  public static DistilleryRecipes instance() { return INSTANCE; }
  
  private DistilleryRecipes() {}
  
  public static class DistilleryRecipe {
    public final ItemStack[] inputs;
    public final int jars;
    public final ItemStack[] outputs;
    
    private DistilleryRecipe(ItemStack input1, ItemStack input2, int jars, ItemStack output1, ItemStack output2, ItemStack output3, ItemStack output4) { inputs = new ItemStack[] { input1, input2 };
      this.jars = jars;
      outputs = new ItemStack[] { output1, output2, output3, output4 };
    }
    
    private boolean isMatch(ItemStack input1, ItemStack input2, ItemStack jars) {
      return ((this.jars == 0) || ((jars != null) && (field_77994_a >= this.jars))) && (((isMatch(input1, inputs[0])) && (isMatch(input2, inputs[1]))) || ((isMatch(input1, inputs[1])) && (isMatch(input2, inputs[0]))));
    }
    
    private boolean isMatch(ItemStack a, ItemStack b)
    {
      return ((a == null) && (b == null)) || ((a != null) && (b != null) && (a.func_77973_b() == b.func_77973_b()) && ((!a.func_77981_g()) || (a.func_77960_j() == b.func_77960_j())));
    }
    
    public int getJars()
    {
      return jars;
    }
    
    public ItemStack[] getOutputs() {
      return outputs;
    }
    
    public String getDescription() {
      StringBuffer sb = new StringBuffer();
      
      sb.append(Witchery.resource("witchery.book.distillery.items"));
      sb.append(Const.BOOK_NEWLINE);
      sb.append(Const.BOOK_NEWLINE);
      
      for (ItemStack stack : inputs) {
        if (stack != null) {
          sb.append("§8>§0 ");
          if (stack.func_77973_b() == Item.func_150898_a(Blocks.field_150337_Q)) {
            sb.append(Witchery.resource("witchery.book.mushroomred"));
          } else if (stack.func_77973_b() == Item.func_150898_a(Blocks.field_150338_P)) {
            sb.append(Witchery.resource("witchery.book.mushroombrown"));
          } else if (stack.func_77973_b() == Items.field_151068_bn) {
            List list = Items.field_151068_bn.func_77832_l(stack);
            if ((list != null) && (!list.isEmpty())) {
              PotionEffect effect = (PotionEffect)list.get(0);
              String s = stack.func_82833_r();
              if (effect.func_76458_c() > 0) {
                s = s + " " + StatCollector.func_74838_a(new StringBuilder().append("potion.potency.").append(effect.func_76458_c()).toString()).trim();
              }
              
              if (effect.func_76459_b() > 20) {
                s = s + " (" + Potion.func_76389_a(effect) + ")";
              }
              sb.append(s);
            } else {
              sb.append(stack.func_82833_r());
            }
          } else {
            sb.append(stack.func_82833_r());
          }
          sb.append(Const.BOOK_NEWLINE);
        }
      }
      
      sb.append(String.format("\n§8%s§0 %d\n", new Object[] { Witchery.resource("witchery.book.distillery.jars"), Integer.valueOf(jars) }));
      
      sb.append(Const.BOOK_NEWLINE);
      sb.append(Witchery.resource("witchery.book.distillery.results"));
      sb.append(Const.BOOK_NEWLINE);
      sb.append(Const.BOOK_NEWLINE);
      
      for (ItemStack stack : outputs) {
        if (stack != null) {
          sb.append("§8>§0 ");
          if (stack.func_77973_b() == Item.func_150898_a(Blocks.field_150337_Q)) {
            sb.append(Witchery.resource("witchery.book.mushroomred"));
          } else if (stack.func_77973_b() == Item.func_150898_a(Blocks.field_150338_P)) {
            sb.append(Witchery.resource("witchery.book.mushroombrown"));
          } else if (stack.func_77973_b() == Items.field_151068_bn) {
            List list = Items.field_151068_bn.func_77832_l(stack);
            if ((list != null) && (!list.isEmpty())) {
              PotionEffect effect = (PotionEffect)list.get(0);
              String s = stack.func_82833_r();
              if (effect.func_76458_c() > 0) {
                s = s + " " + StatCollector.func_74838_a(new StringBuilder().append("potion.potency.").append(effect.func_76458_c()).toString()).trim();
              }
              
              if (effect.func_76459_b() > 20) {
                s = s + " (" + Potion.func_76389_a(effect) + ")";
              }
              sb.append(s);
            } else {
              sb.append(stack.func_82833_r());
            }
          } else {
            sb.append(stack.func_82833_r());
          }
          sb.append(Const.BOOK_NEWLINE);
        }
      }
      
      return sb.toString();
    }
    
    public boolean resultsIn(ItemStack result) {
      for (ItemStack stack : outputs) {
        if ((stack != null) && (stack.func_77969_a(result))) {
          return true;
        }
      }
      return false;
    }
    
    public boolean uses(ItemStack ingredient) {
      for (ItemStack stack : inputs) {
        if ((stack != null) && (stack.func_77969_a(ingredient))) {
          return true;
        }
      }
      if ((ItemsGENERIC.itemEmptyClayJar.isMatch(ingredient)) && (jars > 0)) {
        return true;
      }
      return false;
    }
  }
  
  public final ArrayList<DistilleryRecipe> recipes = new ArrayList();
  



  public DistilleryRecipe addRecipe(ItemStack input1, ItemStack input2, int jars, ItemStack output1, ItemStack output2, ItemStack output3, ItemStack output4)
  {
    DistilleryRecipe recipe = new DistilleryRecipe(input1, input2, jars, output1, output2, output3, output4, null);
    recipes.add(recipe);
    return recipe;
  }
  
  public DistilleryRecipe getDistillingResult(ItemStack input1, ItemStack intput2, ItemStack jars) {
    for (DistilleryRecipe recipe : recipes) {
      if (recipe.isMatch(input1, intput2, jars)) {
        return recipe;
      }
    }
    return null;
  }
  
  public DistilleryRecipe findRecipeFor(ItemStack result) {
    for (DistilleryRecipe recipe : recipes) {
      if (recipe.resultsIn(result)) {
        return recipe;
      }
    }
    return null;
  }
  
  public DistilleryRecipe findRecipeUsing(ItemStack ingredient) {
    for (DistilleryRecipe recipe : recipes) {
      if (recipe.uses(ingredient)) {
        return recipe;
      }
    }
    return null;
  }
}
