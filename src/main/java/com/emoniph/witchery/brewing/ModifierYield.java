package com.emoniph.witchery.brewing;

public class ModifierYield {
  private int bonus;
  private int penalty;
  
  public ModifierYield(int modifier) { if (modifier < 0) {
      penalty = Math.abs(modifier);
    } else if (modifier > 0) {
      bonus = modifier;
    }
  }
  
  public int getYieldModification() {
    return penalty - Math.min(bonus, penalty);
  }
  
  public void applyTo(ModifierYield counter) {
    bonus += bonus;
    penalty += penalty;
  }
}
