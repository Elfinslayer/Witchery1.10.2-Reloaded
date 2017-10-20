package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.util.EntityPosition;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PotionRepellAttacker extends PotionBase implements IHandleLivingHurt
{
  public PotionRepellAttacker(int id, int color)
  {
    super(id, color);
  }
  
  public boolean handleAllHurtEvents()
  {
    return false;
  }
  
  public void onLivingHurt(World world, EntityLivingBase entity, LivingHurtEvent event, int amplifier)
  {
    if (!field_72995_K) {
      EntityLivingBase attacker = (source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityLivingBase)) ? (EntityLivingBase)source.func_76346_g() : null;
      

      double MAX_RANGE = 3.0D;
      double MAX_RANGE_SQ = 9.0D;
      if ((attacker != null) && (attacker != entity) && (!source.func_76352_a()) && (attacker.func_70068_e(entity) < 9.0D)) {
        com.emoniph.witchery.util.EntityUtil.pushback(world, attacker, new EntityPosition(entity), 1.0D + amplifier * 0.75D, 0.5D + amplifier * 0.2D);
      }
    }
  }
}
