package com.emoniph.witchery.brewing;

public class BrewNamePartModifier extends BrewNamePart
{
  int dispersalExtent;
  int dispersalDuration;
  int strength;
  int duration;
  boolean inverted;
  boolean removePowerCeiling;
  
  public BrewNamePartModifier(int strength, int duration, boolean invert, int dispersalExtent, int dispersalDuration)
  {
    this(strength, duration, invert, dispersalExtent, dispersalDuration, false);
  }
  
  public BrewNamePartModifier(int strength, int duration, boolean invert, int dispersalExtent, int dispersalDuration, boolean removeCeiling) {
    super("");
    this.strength = strength;
    this.duration = duration;
    inverted = invert;
    this.dispersalExtent = dispersalExtent;
    this.dispersalDuration = dispersalDuration;
    removePowerCeiling = removeCeiling;
  }
  
  public void applyTo(BrewNameBuilder nameBuilder)
  {
    dispersalExtent += dispersalExtent;
    dispersalDuration += dispersalDuration;
    nameBuilder.addStrength(strength);
    nameBuilder.addDuration(duration);
    if (inverted) {
      inverted = true;
    }
    if (removePowerCeiling) {
      removePowerCeiling = true;
    }
  }
}
