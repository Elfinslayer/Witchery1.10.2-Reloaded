package com.emoniph.witchery.brewing;

public class EffectLevelCounter { private int maxLevel;
  private int currentLevel;
  private int effects;
  
  public EffectLevelCounter() {}
  
  public void increaseAvailableLevelIf(EffectLevel incement, EffectLevel ceilingLevel) { if (maxLevel < ceilingLevel.getLevel()) {
      maxLevel += incement.getLevel();
    }
  }
  
  public int remainingCapactiy() {
    return maxLevel - currentLevel;
  }
  
  public int usedCapacity() {
    return currentLevel;
  }
  
  public int getEffectCount() {
    return effects;
  }
  
  public boolean tryConsumeLevel(EffectLevel level) {
    if (canConsumeLevel(level)) {
      currentLevel += level.getLevel();
      effects += 1;
      return true;
    }
    return false;
  }
  
  public boolean canConsumeLevel(EffectLevel level)
  {
    return level.getLevel() + currentLevel <= maxLevel;
  }
  
  public boolean hasEffects() {
    return currentLevel > 0;
  }
  
  public boolean canIncreasePlayerSkill(int currentSkillLevel) {
    if ((currentLevel > maxLevel) || (maxLevel == 0)) {
      return false;
    }
    
    if (currentSkillLevel < 30) {
      return (maxLevel > 1) && (currentLevel > 1);
    }
    
    if (currentSkillLevel < 60) {
      return (maxLevel >= 4) && (currentLevel >= 4);
    }
    
    if (currentSkillLevel < 90) {
      return (maxLevel >= 6) && (currentLevel >= 6);
    }
    
    if (currentSkillLevel != 100) {
      return (maxLevel >= 8) && (currentLevel >= 8);
    }
    
    return false;
  }
}
