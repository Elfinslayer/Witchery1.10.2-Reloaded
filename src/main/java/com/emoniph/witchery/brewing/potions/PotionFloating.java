package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class PotionFloating extends PotionBase
{
  public PotionFloating(int id, int color)
  {
    super(id, color);
  }
  
  public boolean func_76397_a(int duration, int amplifier)
  {
    return true;
  }
  
  public void func_76394_a(EntityLivingBase entity, int amplifier)
  {
    int height = 3 + amplifier;
    int x = net.minecraft.util.MathHelper.func_76128_c(field_70165_t);
    int y = net.minecraft.util.MathHelper.func_76128_c(field_70163_u);
    int z = net.minecraft.util.MathHelper.func_76128_c(field_70161_v);
    boolean isPlayer = entity instanceof net.minecraft.entity.player.EntityPlayer;
    boolean activeOnSide = ((isPlayer) && (field_70170_p.field_72995_K)) || ((!isPlayer) && (!field_70170_p.field_72995_K));
    
    field_70143_R = 0.0F;
    if (activeOnSide) {
      boolean raised = false;
      for (int i = 1; i <= height; i++) {
        if (!field_70170_p.func_147437_c(x, y - i, z)) {
          field_70181_x = 0.25D;
          raised = true;
          break;
        }
      }
      if (!raised) {
        field_70181_x = (field_70170_p.field_73012_v.nextInt(5) == 0 ? -0.05D : 0.0D);
      }
    }
  }
}
