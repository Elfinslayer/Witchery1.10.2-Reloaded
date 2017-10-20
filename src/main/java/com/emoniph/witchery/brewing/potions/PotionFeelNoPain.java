package com.emoniph.witchery.brewing.potions;

import java.util.Random;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PotionFeelNoPain extends PotionBase implements IHandleLivingHurt, IHandleLivingUpdate
{
  public PotionFeelNoPain(int id, int color)
  {
    super(id, color);
    setIncurable();
  }
  
  public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
  {
    if ((!field_72995_K) && (world.func_72820_D() % 20L == 2L) && 
      (amplifier > 0) && (!entity.func_70644_a(Potion.field_76431_k)) && (!entity.func_70644_a(PotionsSTOUT_BELLY)) && (field_73012_v.nextInt(5 - Math.min(amplifier, 3)) == 0))
    {

      entity.func_70690_d(new PotionEffect(field_76431_kfield_76415_H, com.emoniph.witchery.util.TimeUtil.secsToTicks(6 + amplifier * 2)));
    }
  }
  


  public boolean handleAllHurtEvents()
  {
    return false;
  }
  
  public void onLivingHurt(World world, EntityLivingBase entity, LivingHurtEvent event, int amplifier)
  {
    if ((!field_72995_K) && ((entity instanceof EntityPlayer)) && ((source.func_76355_l() == "mob") || (source.func_76355_l() == "player") || ((source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityLivingBase)))))
    {


      EntityPlayer player = (EntityPlayer)entity;
      float currentHealth = entity.func_110143_aJ();
      float newHealth = com.emoniph.witchery.util.EntityUtil.getHealthAfterDamage(event, currentHealth, entity);
      float damage = currentHealth - newHealth;
      int food = player.func_71024_bL().func_75116_a();
      if (food > 0) {
        int modifiedDamage = (int)Math.ceil(amplifier > 0 ? Math.max(damage / amplifier, amplifier > 1 ? 1.0F : 2.0F) : Math.max(damage * 2.0F, 3.0F));
        
        int foodPenalty = Math.min(modifiedDamage, food);
        player.func_71024_bL().func_75122_a(-foodPenalty, 2.0F);
        event.setCanceled(true);
      }
    }
  }
}
