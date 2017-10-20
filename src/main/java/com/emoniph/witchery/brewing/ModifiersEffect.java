package com.emoniph.witchery.brewing;

import com.emoniph.witchery.util.EntityPosition;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;





public class ModifiersEffect
{
  public int strength;
  public int strengthPenalty;
  public int duration;
  public boolean noParticles;
  public final EffectLevelCounter effectLevel = new EffectLevelCounter();
  
  public boolean inverted;
  
  public boolean disableBlockTarget;
  
  public boolean disableEntityTarget;
  
  public boolean strengthCeilingDisabled;
  
  public boolean powerhCeilingDisabled;
  public final double powerScalingFactor;
  public final double durationScalingFactor;
  public final boolean isGlancing;
  public final EntityPosition impactLocation;
  public final boolean ritualised;
  public final int covenSize;
  public final EntityPlayer caster;
  public int totalStrength;
  public int totalDuration;
  public int permenance;
  public boolean protectedFromNegativePotions;
  
  public ModifiersEffect(double powerScalingFactor, double durationScalingFactor, boolean glancing, EntityPosition position, boolean ritualised, int covenSize, EntityPlayer caster)
  {
    this.powerScalingFactor = powerScalingFactor;
    this.durationScalingFactor = durationScalingFactor;
    isGlancing = glancing;
    impactLocation = position;
    this.ritualised = ritualised;
    this.caster = caster;
    this.covenSize = covenSize;
  }
  
  public ModifiersEffect(double powerScalingFactor, double durationScalingFactor, boolean glancing, EntityPosition position, ModifiersImpact impactModifiers)
  {
    this(powerScalingFactor, durationScalingFactor, glancing, position, ritualised, covenSize, thrower);
  }
  

  private static final int[] covenToMaxStrength = { 1, 1, 2, 2, 3, 3, 4 };
  
  public int getStrength() {
    if (ritualised) {
      return Math.min(Math.max(strength - strengthPenalty, 0), covenToMaxStrength[Math.min(covenSize, covenToMaxStrength.length - 1)]);
    }
    
    return Math.max(strength - strengthPenalty, 0);
  }
  
  public int getModifiedDuration(int ticks)
  {
    return Math.min(MathHelper.func_76143_f(durationScalingFactor * ticks * (duration + 1)), Integer.MAX_VALUE);
  }
  
  public void reset() {
    inverted = false;
    strength = 0;
    duration = 0;
    noParticles = false;
  }
  
  public void increaseStrength(int strength) {
    if ((totalStrength < 7) || (powerhCeilingDisabled)) {
      this.strength += strength;
      totalStrength += strength;
    }
  }
  
  public void increaseDuration(int duration) {
    if ((totalDuration < 7) || (powerhCeilingDisabled)) {
      this.duration += duration;
      totalDuration += duration;
    }
  }
}
