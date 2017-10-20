package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.infusion.Infusion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PotionAbsorbMagic extends PotionBase implements IHandleLivingHurt
{
  public PotionAbsorbMagic(int id, int color)
  {
    super(id, color);
  }
  
  public void onLivingHurt(World world, EntityLivingBase entity, LivingHurtEvent event, int amplifier)
  {
    if ((!field_72995_K) && 
      (source.func_82725_o())) {
      float damageAbsorbed = ammount * 0.2F * (amplifier + 1);
      ammount -= damageAbsorbed;
      if ((entity instanceof EntityPlayer)) {
        EntityPlayer player = (EntityPlayer)entity;
        int maxEnergy = Infusion.getMaxEnergy(player);
        if ((maxEnergy > 0) && (damageAbsorbed > 1.0F)) {
          int energy = Infusion.getCurrentEnergy(player);
          Infusion.setCurrentEnergy(player, Math.min(energy + (int)damageAbsorbed, maxEnergy));
        }
      }
    }
  }
  

  public boolean handleAllHurtEvents()
  {
    return false;
  }
}
