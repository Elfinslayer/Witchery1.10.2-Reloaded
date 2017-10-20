package com.emoniph.witchery.crafting;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.util.Const;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class KettleRecipes
{
  private static final KettleRecipes INSTANCE = new KettleRecipes();
  

  public static KettleRecipes instance() { return INSTANCE; }
  
  private KettleRecipes() {}
  
  public static class KettleRecipe { public final ItemStack[] inputs;
    public final ItemStack output;
    public final float power;
    final int color;
    final int hatBonus;
    final int familiarType;
    final int dimension;
    public final boolean inBook;
    private String unlocalizedName;
    
    private KettleRecipe(ItemStack output, int hatBonus, int familiarType, float power, int color, int dimension, boolean inBook, ItemStack... inputs) { this.inputs = inputs;
      this.output = output;
      this.power = power;
      this.color = color;
      this.hatBonus = hatBonus;
      this.familiarType = familiarType;
      this.dimension = dimension;
      this.inBook = inBook;
    }
    
    public int getColor() {
      return color;
    }
    
    private boolean isMatch(ItemStack[] current, int currentLength, boolean partial, World world) {
      if ((dimension != 0) && (dimension != field_73011_w.field_76574_g)) {
        return false;
      }
      
      if ((!partial) && (currentLength != inputs.length)) {
        return false;
      }
      ArrayList<ItemStack> inputsToFind = new ArrayList(Arrays.asList(inputs));
      for (int j = 0; j < currentLength; j++) {
        ItemStack itemstack = current[j];
        boolean foundOne = false;
        for (int i = 0; i < inputsToFind.size(); i++) {
          ItemStack input = (ItemStack)inputsToFind.get(i);
          if ((itemstack != null) && (input != null) && (itemstack.func_77969_a(input))) {
            inputsToFind.remove(i);
            foundOne = true;
            break;
          }
        }
        if (!foundOne) {
          if (itemstack == null) break;
          return false;
        }
      }
      


      return (inputsToFind.size() == 0) || ((partial) && (inputsToFind.size() < inputs.length));
    }
    
    private boolean allEmpty(ArrayList<ItemStack> items) {
      for (ItemStack stack : items) {
        if (stack != null) {
          return false;
        }
      }
      return true;
    }
    
    public ItemStack getOutput(EntityPlayer player, boolean createCopy) {
      if ((hatBonus > 0) && (player != null) && (field_71071_by != null) && (field_71071_by.func_70440_f(3) != null) && (field_71071_by.func_70440_f(3).func_77973_b() == ItemsWITCH_HAT)) {
        ItemStack stack = output.func_77946_l();
        field_77994_a += hatBonus;
        return stack;
      }
      return createCopy ? output.func_77946_l() : output;
    }
    
    public float getRequiredPower()
    {
      return power;
    }
    
    public String getDescription() {
      StringBuffer sb = new StringBuffer();
      
      sb.append("§n");
      sb.append(output.func_82833_r());
      sb.append("§r");
      sb.append(Const.BOOK_NEWLINE);
      sb.append(Const.BOOK_NEWLINE);
      
      if ((unlocalizedName != null) && (!unlocalizedName.isEmpty())) {
        String localizedName = Witchery.resource(unlocalizedName);
        if (!localizedName.isEmpty()) {
          sb.append(localizedName);
          sb.append(Const.BOOK_NEWLINE);
          sb.append(Const.BOOK_NEWLINE);
        }
      }
      
      for (ItemStack stack : inputs) {
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
              s = s + " " + net.minecraft.util.StatCollector.func_74838_a(new StringBuilder().append("potion.potency.").append(effect.func_76458_c()).toString()).trim();
            }
            
            if (effect.func_76459_b() > 20) {
              s = s + " (" + net.minecraft.potion.Potion.func_76389_a(effect) + ")";
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
      
      if (power > 0.0F) {
        sb.append(String.format("\n§8%s§0 %s\n", new Object[] { Witchery.resource("witchery.book.altarpower"), Integer.valueOf(MathHelper.func_76141_d(power)) }));
      }
      
      return sb.toString();
    }
    

    public KettleRecipe setUnlocalizedName(String unlocalizedName)
    {
      this.unlocalizedName = unlocalizedName;
      return this;
    }
    
    public boolean isBrewableBy(EntityPlayer player) {
      if (familiarType == 0) {
        return true;
      }
      
      if (player == null) {
        return false;
      }
      
      int familiarOfPlayer = com.emoniph.witchery.familiar.Familiar.getActiveFamiliarType(player);
      return familiarOfPlayer == familiarType;
    }
  }
  
  public final ArrayList<KettleRecipe> recipes = new ArrayList();
  



  public KettleRecipe addRecipe(ItemStack output, int hatBonus, int familiarType, float powerRequired, int color, int dimension, boolean inBook, ItemStack... inputs)
  {
    KettleRecipe recipe = new KettleRecipe(output, hatBonus, familiarType, powerRequired, color, dimension, inBook, inputs, null);
    recipes.add(recipe);
    return recipe;
  }
  
  public KettleRecipe addRecipe(ItemStack output, int hatBonus, int familiarType, float powerRequired, int color, int dimension, ItemStack... inputs) {
    return addRecipe(output, hatBonus, familiarType, powerRequired, color, dimension, true, inputs);
  }
  
  public KettleRecipe getResult(ItemStack[] inputs, int length, boolean partial, World world) {
    for (KettleRecipe recipe : recipes) {
      if (recipe.isMatch(inputs, length, partial, world)) {
        return recipe;
      }
    }
    return null;
  }
  








  public int getHatBonus(ItemStack stack)
  {
    for (KettleRecipe recipe : recipes) {
      if (output.func_77969_a(stack)) {
        return hatBonus;
      }
    }
    return 0;
  }
  
  public boolean isBrewableBy(ItemStack stack, EntityPlayer player) {
    for (KettleRecipe recipe : recipes) {
      if (output.func_77969_a(stack)) {
        return recipe.isBrewableBy(player);
      }
    }
    return false;
  }
  
  public KettleRecipe findRecipeFor(ItemStack result) {
    for (KettleRecipe recipe : recipes) {
      if (output.func_77969_a(result)) {
        return recipe;
      }
    }
    return null;
  }
}
