package com.emoniph.witchery.brewing.action;

import com.emoniph.witchery.brewing.AltarPower;
import com.emoniph.witchery.brewing.BrewItemKey;
import com.emoniph.witchery.brewing.BrewNamePart;
import com.emoniph.witchery.brewing.EffectLevel;
import com.emoniph.witchery.brewing.ModifiersEffect;
import com.emoniph.witchery.brewing.ModifiersRitual;
import com.emoniph.witchery.brewing.Probability;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;


public class BrewCurseEffect
  extends BrewPotionEffect
{
  private boolean onlyCurseInverted;
  
  public BrewCurseEffect(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, Probability baseProbability, Potion effect, long baseDuration, EffectLevel effectLevel, boolean onlyCurseInverted)
  {
    super(itemKey, namePart, powerCost, baseProbability, effect, baseDuration, effect, baseDuration, effectLevel);
    
    this.onlyCurseInverted = onlyCurseInverted;
  }
  

  public BrewCurseEffect(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, Probability baseProbability, Potion effect, long baseDuration, Potion invertedEffect, long invertedDuration, EffectLevel effectLevel, boolean onlyCurseInverted)
  {
    super(itemKey, namePart, powerCost, baseProbability, effect, baseDuration, invertedEffect, invertedDuration, effectLevel);
    
    this.onlyCurseInverted = onlyCurseInverted;
  }
  

  public void applyRitualToEntity(World world, EntityLivingBase targetEntity, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack)
  {
    int oldDuration = duration;
    boolean oldNoParticles = noParticles;
    if ((taglockUsed) && ((!onlyCurseInverted) || (inverted))) {
      duration = 10000;
      noParticles = true;
    }
    applyToEntity(world, targetEntity, modifiers, stack);
    duration = oldDuration;
    noParticles = oldNoParticles;
  }
}
