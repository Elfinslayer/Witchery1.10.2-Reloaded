package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PotionReflectDamage extends PotionBase implements IHandleLivingHurt
{
  public PotionReflectDamage(int id, int color)
  {
    super(id, color);
  }
  
  public boolean handleAllHurtEvents()
  {
    return false;
  }
  
  public void onLivingHurt(net.minecraft.world.World world, EntityLivingBase entity, LivingHurtEvent event, int amplifier)
  {
    if (!field_72995_K) {
      EntityLivingBase attacker = (source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityLivingBase)) ? (EntityLivingBase)source.func_76346_g() : null;
      

      if ((attacker != null) && (attacker != entity) && ((!source.func_76352_a()) || (amplifier >= 2))) {
        float amount = (float)Math.ceil(ammount * 0.1F * (amplifier + (!source.func_76352_a() ? 1 : 0)));
        
        attacker.func_70097_a(source, amount);
        ammount -= amount;
      }
    }
  }
}
