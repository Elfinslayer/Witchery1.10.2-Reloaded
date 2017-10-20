package com.emoniph.witchery.brewing.action;

import com.emoniph.witchery.brewing.AltarPower;
import com.emoniph.witchery.brewing.BrewItemKey;
import com.emoniph.witchery.brewing.EffectLevelCounter;
import com.emoniph.witchery.brewing.ModifiersEffect;
import com.emoniph.witchery.brewing.ModifiersImpact;
import com.emoniph.witchery.brewing.ModifiersRitual;
import com.emoniph.witchery.brewing.Probability;
import com.emoniph.witchery.brewing.RitualStatus;
import com.emoniph.witchery.entity.EntityLeonard;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BrewActionRitualSummonMob extends BrewAction
{
  private final Recipe[] recipes;
  
  public static class Recipe
  {
    public final Class<? extends EntityCreature> result;
    public final ItemStack[] ingredients;
    
    public Recipe(Class<? extends EntityCreature> result, ItemStack... ingredients)
    {
      this.result = result;
      this.ingredients = ingredients;
    }
  }
  

  private final List<Recipe> expandedRecipes = new ArrayList();
  
  public BrewActionRitualSummonMob(BrewItemKey itemKey, AltarPower powerCost, Recipe... recipes) {
    super(itemKey, null, powerCost, new Probability(1.0D), false);
    this.recipes = recipes;
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
    Class<? extends EntityCreature> result = getRecipeResult(actionList);
    if (result != null)
    {
      EntityCreature creature = Infusion.spawnCreature(world, result, x, y + 1, z, null, 0, 0, ParticleEffect.EXPLODE, SoundEffect.MOB_WITHER_SPAWN);
      if (creature != null) {
        creature.func_110163_bv();
        if ((creature instanceof EntityLeonard)) {
          EntityLeonard leonard = (EntityLeonard)creature;
          leonard.setInvulnerableStart();
        }
      }
      
      return RitualStatus.COMPLETE;
    }
    return RitualStatus.FAILED;
  }
  
  private Class<? extends EntityCreature> getRecipeResult(BrewActionList actionList)
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
