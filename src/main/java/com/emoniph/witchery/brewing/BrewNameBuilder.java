package com.emoniph.witchery.brewing;

import com.emoniph.witchery.Witchery;
import java.util.ArrayList;
import net.minecraft.util.StatCollector;




public class BrewNameBuilder
{
  int dispersalExtent;
  int dispersalDuration;
  int strength;
  int durationModifier;
  int totalStrength;
  int totalDuration;
  boolean inverted;
  private boolean terse;
  
  public BrewNameBuilder(boolean terse)
  {
    this.terse = terse;
  }
  
  private static class Part {
    String base;
    long duration;
    
    private Part(String base, long duration) {
      this.base = base;
      this.duration = duration;
    }
    
    public String toString(boolean splash, boolean terse) {
      long modDuration = (splash) && (duration > 0L) ? duration / 2L : duration;
      if ((!terse) && (modDuration > 0L)) {
        return base + " [" + BrewNameBuilder.ticksToElapsedTime((int)modDuration) + "]";
      }
      return base;
    }
  }
  

  private static String ticksToElapsedTime(int p_76337_0_)
  {
    int j = p_76337_0_ / 20;
    int k = j / 60;
    j %= 60;
    return k + ":" + j;
  }
  
  ArrayList<Part> parts = new ArrayList();
  ArrayList<String> prefixes = new ArrayList();
  ArrayList<String> postfixes = new ArrayList();
  boolean removePowerCeiling;
  
  public void append(String text, String invertedText, long duration, long invertedDuration) {
    StringBuilder builder = new StringBuilder();
    if (inverted) {
      builder.append(invertedText);
    } else {
      builder.append(text);
    }
    inverted = false;
    
    if ((!terse) && (strength > 0)) {
      builder.append(" ");
      builder.append(StatCollector.func_74838_a("potion.potency." + strength));
    }
    strength = 0;
    
    parts.add(new Part(builder.toString(), duration * (durationModifier + 1), null));
    durationModifier = 0;
  }
  
  public void appendPrefix(String text) {
    prefixes.add(text);
    
    if ((!terse) && (dispersalExtent > 0)) {
      prefixes.add(StatCollector.func_74838_a("potion.potency." + dispersalExtent));
    }
    dispersalExtent = 0;
    
    if ((!terse) && (dispersalDuration > 0)) {
      prefixes.add(String.format("[%s %s]", new Object[] { Witchery.resource("witchery:brew.lifetime"), StatCollector.func_74838_a("potion.potency." + dispersalDuration) }));
    }
    dispersalDuration = 0;
  }
  
  public void appendPostfix(String text) {
    postfixes.add(text);
  }
  
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    for (String text : prefixes) {
      builder.append(text);
      builder.append(" ");
    }
    
    if (terse) {
      builder.append(Witchery.resource("witchery:brew.potion"));
      builder.append(" ");
    } else {
      builder.append("\n");
    }
    
    if (parts.size() > 0) {
      for (int i = 0; i < parts.size(); i++) {
        builder.append(((Part)parts.get(i)).toString(prefixes.size() > 0, terse));
        builder.append(terse ? " " : i < parts.size() - 1 ? " & " : i < parts.size() - 2 ? ", " : "\n");
      }
    } else {
      builder.append(Witchery.resource("witchery:brew.potionwater"));
      if (!terse) {
        builder.append("\n");
      }
    }
    
    for (String text : postfixes) {
      builder.append(text);
      builder.append(" ");
    }
    
    builder.trimToSize();
    
    return builder.toString();
  }
  
  public void addStrength(int strength2) {
    if ((totalStrength < 7) || (removePowerCeiling)) {
      strength += strength2;
      totalStrength += strength2;
    }
  }
  
  public void addDuration(int duration) {
    if ((totalDuration < 7) || (removePowerCeiling)) {
      durationModifier += duration;
      totalDuration += duration;
    }
  }
}
