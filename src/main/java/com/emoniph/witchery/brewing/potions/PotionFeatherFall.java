package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.EntityLivingBase;

public class PotionFeatherFall extends PotionBase
{
  public PotionFeatherFall(int id, int color) {
    super(id, color);
  }
  
  public boolean func_76397_a(int duration, int amplifier)
  {
    return true;
  }
  
  public void func_76394_a(EntityLivingBase entity, int amplifier)
  {
    int activationDistance = amplifier >= 1 ? 4 : amplifier >= 2 ? 3 : 5;
    int maxFallDistance = amplifier >= 1 ? 5 : amplifier >= 2 ? 4 : amplifier >= 3 ? 3 : 6;
    if ((field_70143_R >= activationDistance) && (field_70181_x < -0.2D)) {
      field_70181_x = -0.2D;
      
      if (field_70143_R > maxFallDistance) {
        field_70143_R = maxFallDistance;
      }
    }
  }
}
