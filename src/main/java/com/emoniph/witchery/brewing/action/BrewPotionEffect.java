package com.emoniph.witchery.brewing.action;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.AltarPower;
import com.emoniph.witchery.brewing.BrewItemKey;
import com.emoniph.witchery.brewing.BrewNamePart;
import com.emoniph.witchery.brewing.EffectLevel;
import com.emoniph.witchery.brewing.EffectLevelCounter;
import com.emoniph.witchery.brewing.ModifiersEffect;
import com.emoniph.witchery.brewing.ModifiersImpact;
import com.emoniph.witchery.brewing.ModifiersRitual;
import com.emoniph.witchery.brewing.Probability;
import com.emoniph.witchery.brewing.RitualStatus;
import com.emoniph.witchery.brewing.potions.PotionBase;
import com.emoniph.witchery.brewing.potions.WitcheryPotions;
import com.emoniph.witchery.util.CreatureUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BrewPotionEffect extends BrewAction
{
  private static final int DEFAULT_STRENGTH_CEILING = 10;
  public final Potion potion;
  public final Potion invertedPotion;
  public final int baseDuration;
  public final int invertedDuration;
  public final EffectLevel effectLevel;
  protected int strengthCeiling = 10;
  
  public BrewPotionEffect(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, Probability baseProbability, Potion effect, long baseDuration, EffectLevel effectLevel)
  {
    this(itemKey, namePart, powerCost, baseProbability, effect, baseDuration, effect, baseDuration, effectLevel);
  }
  


  public BrewPotionEffect(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, Probability baseProbability, Potion effect, long baseDuration, Potion invertedEffect, long invertedDuration, EffectLevel effectLevel)
  {
    super(itemKey, namePart.setBaseDuration(baseDuration, invertedDuration), powerCost, baseProbability, false);
    
    potion = effect;
    invertedPotion = invertedEffect;
    this.baseDuration = ((int)baseDuration);
    this.invertedDuration = ((int)invertedDuration);
    this.effectLevel = effectLevel;
  }
  
  public BrewPotionEffect setStrengthCeiling(int ceiling) {
    strengthCeiling = ceiling;
    return this;
  }
  

  public void applyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack)
  {
    if (!disableEntityTarget) {
      if (inverted) {
        if ((!PotionBase.isDebuff(invertedPotion)) || (!protectedFromNegativePotions)) {
          applyPotionEffect(targetEntity, modifiers, invertedPotion, invertedDuration, noParticles, caster, strengthCeiling);
        }
        
      }
      else if ((!PotionBase.isDebuff(potion)) || (!protectedFromNegativePotions)) {
        if (potion == PotionsDOUBLE_JUMP) {
          if ((caster == null) || (!com.emoniph.witchery.familiar.Familiar.hasActiveBrewMasteryFamiliar(caster))) {
            modifiers.reset();
          }
        }
        else if ((potion == PotionsDISEASED) && 
          (CreatureUtil.isImmuneToDisease(targetEntity))) {
          modifiers.reset();
          return;
        }
        
        applyPotionEffect(targetEntity, modifiers, potion, baseDuration, noParticles, caster, strengthCeiling);
      }
      
      modifiers.reset();
    }
  }
  
  public final boolean augmentEffectLevels(EffectLevelCounter totalEffects)
  {
    return totalEffects.tryConsumeLevel(effectLevel);
  }
  
  public static void applyPotionEffect(EntityLivingBase entity, ModifiersEffect modifiers, Potion potion, int duration, boolean noParticles, EntityPlayer thrower)
  {
    applyPotionEffect(entity, modifiers, potion, duration, noParticles, thrower, 10);
  }
  
  public static void applyPotionEffect(EntityLivingBase entity, ModifiersEffect modifiers, Potion potion, int duration, boolean noParticles, EntityPlayer thrower, int strengthCeiling)
  {
    int strength = Math.min(modifiers.getStrength(), strengthCeilingDisabled ? 10 : strengthCeiling);
    
    if (potion.func_76403_b()) {
      potion.func_76402_a(thrower, entity, strength, powerScalingFactor);
    } else {
      entity.func_70690_d(new PotionEffect(field_76415_H, modifiers.getModifiedDuration(duration), strength, noParticles));
    }
  }
  


  public final void prepareSplashPotion(World world, BrewActionList actionList, ModifiersImpact modifiers) {}
  


  public final void applyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack stack) {}
  


  public final void augmentEffectModifiers(ModifiersEffect modifiers) {}
  


  public final void prepareRitual(World world, int x, int y, int z, ModifiersRitual modifiers, ItemStack stack) {}
  


  public final RitualStatus updateRitual(MinecraftServer server, BrewActionList actionList, World world, int x, int y, int z, ModifiersRitual modifiers, ModifiersImpact impactModifiers)
  {
    return RitualStatus.COMPLETE;
  }
}
