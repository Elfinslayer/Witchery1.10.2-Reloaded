package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.EntityLivingBase;

public class PotionDarknessAllergy extends PotionBase
{
  public PotionDarknessAllergy(int id, int color)
  {
    super(id, true, color);
    setIncurable();
  }
  

  public boolean func_76397_a(int duration, int amplifier)
  {
    return duration % 20 == 4;
  }
  
  public void func_76394_a(EntityLivingBase entity, int amplifier)
  {
    int x = net.minecraft.util.MathHelper.func_76128_c(field_70165_t);
    int y = net.minecraft.util.MathHelper.func_76128_c(field_70163_u);
    int z = net.minecraft.util.MathHelper.func_76128_c(field_70161_v);
    int lightLevel = field_70170_p.func_72957_l(x, y, z);
    if (lightLevel < 2 + amplifier * 2) {
      entity.func_70097_a(net.minecraft.util.DamageSource.field_76380_i, 1.0F);
    }
  }
}
