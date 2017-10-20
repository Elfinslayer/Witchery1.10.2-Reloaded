package com.emoniph.witchery.brewing.action;

import com.emoniph.witchery.brewing.AltarPower;
import com.emoniph.witchery.brewing.BrewItemKey;
import com.emoniph.witchery.brewing.BrewNamePart;
import com.emoniph.witchery.brewing.EffectLevelCounter;
import com.emoniph.witchery.brewing.ModifierYield;
import com.emoniph.witchery.brewing.ModifiersEffect;
import com.emoniph.witchery.brewing.ModifiersImpact;
import com.emoniph.witchery.brewing.ModifiersRitual;
import com.emoniph.witchery.brewing.Probability;
import com.emoniph.witchery.brewing.RitualStatus;
import com.emoniph.witchery.util.BlockPosition;
import java.util.ArrayList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;


public abstract class BrewAction
{
  public final BrewItemKey ITEM_KEY;
  protected final boolean createsSplash;
  protected final AltarPower powerCost;
  protected final int forcedColor;
  protected final Probability baseProbability;
  protected final BrewNamePart namePart;
  private final ArrayList<BrewItemKey> nullifiers = new ArrayList();
  private final ArrayList<BrewItemKey> priorNullifiers = new ArrayList();
  private ModifierYield yieldModifier;
  
  protected BrewAction(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, Probability baseProbability, boolean createsSplash)
  {
    this(itemKey, namePart, powerCost, baseProbability, createsSplash, -1);
  }
  
  protected BrewAction(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, Probability baseProbability, boolean createsSplash, int forcedColor)
  {
    ITEM_KEY = itemKey;
    this.powerCost = powerCost;
    this.createsSplash = createsSplash;
    this.forcedColor = forcedColor;
    this.baseProbability = baseProbability;
    this.namePart = namePart;
  }
  
  public void setYieldModifier(ModifierYield counter) {
    yieldModifier = counter;
  }
  
  public void prepareYield(ModifierYield counter) {
    if (yieldModifier != null) {
      yieldModifier.applyTo(counter);
    }
  }
  
  public final BrewNamePart getNamePart() {
    return namePart;
  }
  
  public final void accumulatePower(AltarPower totalPower) {
    totalPower.accumulate(powerCost);
  }
  
  public final BrewAction addNullifier(BrewItemKey itemKey) {
    return addNullifier(itemKey, true);
  }
  
  public final BrewAction addNullifier(BrewItemKey itemKey, boolean onlyPrior) {
    if (onlyPrior) {
      priorNullifiers.add(itemKey);
    } else {
      nullifiers.add(itemKey);
    }
    return this;
  }
  
  public final boolean createsSplash() {
    return createsSplash;
  }
  
  public final int augmentColor(int color) {
    if (forcedColor == -1) {
      if (color == 0) {
        color = 17;
      }
      color = 37 * color + ITEM_KEY.hashCode();
      return color;
    }
    return forcedColor;
  }
  
  public boolean removeWhenAddedToCauldron(World world)
  {
    return false;
  }
  
  public final void processNullifaction(ArrayList<BrewAction> actionStack, NBTTagList nbtItems) {
    if ((priorNullifiers.size() > 0) && 
      (actionStack.size() > 0) && (priorNullifiers.contains(getsize1ITEM_KEY)))
    {
      actionStack.remove(actionStack.size() - 1);
      nbtItems.func_74744_a(actionStack.size() - 1);
    }
    

    if (nullifiers.size() > 0) {
      for (int i = actionStack.size() - 1; i >= 0; i--) {
        if (nullifiers.contains(getITEM_KEY)) {
          actionStack.remove(i);
          nbtItems.func_74744_a(i);
        }
      }
    }
  }
  
  public boolean triggersRitual() {
    return false;
  }
  
  public boolean canAdd(BrewActionList actionList, boolean isCauldronFull, boolean hasEffects) {
    return true;
  }
  
  public boolean isRitualTargetLocationValid(MinecraftServer server, World world, int x, int y, int z, BlockPosition target, ModifiersRitual modifiers)
  {
    return true;
  }
  

  public abstract boolean augmentEffectLevels(EffectLevelCounter paramEffectLevelCounter);
  
  public abstract void augmentEffectModifiers(ModifiersEffect paramModifiersEffect);
  
  public abstract void prepareSplashPotion(World paramWorld, BrewActionList paramBrewActionList, ModifiersImpact paramModifiersImpact);
  
  public abstract void prepareRitual(World paramWorld, int paramInt1, int paramInt2, int paramInt3, ModifiersRitual paramModifiersRitual, ItemStack paramItemStack);
  
  public abstract RitualStatus updateRitual(MinecraftServer paramMinecraftServer, BrewActionList paramBrewActionList, World paramWorld, int paramInt1, int paramInt2, int paramInt3, ModifiersRitual paramModifiersRitual, ModifiersImpact paramModifiersImpact);
  
  public void applyRitualToEntity(World world, EntityLivingBase targetEntity, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack)
  {
    applyToEntity(world, targetEntity, modifiers, stack);
  }
  

  public abstract void applyToEntity(World paramWorld, EntityLivingBase paramEntityLivingBase, ModifiersEffect paramModifiersEffect, ItemStack paramItemStack);
  

  public void applyRitualToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack)
  {
    applyToBlock(world, x, y, z, side, radius, modifiers, stack);
  }
  
  public abstract void applyToBlock(World paramWorld, int paramInt1, int paramInt2, int paramInt3, ForgeDirection paramForgeDirection, int paramInt4, ModifiersEffect paramModifiersEffect, ItemStack paramItemStack);
  
  public int getDrinkSpeedModifiers()
  {
    return 0;
  }
}
