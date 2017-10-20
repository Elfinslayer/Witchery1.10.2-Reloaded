package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.item.ItemPoppet;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class RiteBlindness extends RiteExpandingEffect
{
  public RiteBlindness(int radius, int height)
  {
    super(radius, height, true);
  }
  
  public boolean doRadiusAction(World world, int posX, int posY, int posZ, int radius, EntityPlayer player, boolean enhanced)
  {
    double radiusSq = radius * radius;
    double minSq = Math.max(0, (radius - 1) * (radius - 1));
    for (Object obj : field_73010_i) {
      EntityPlayer victim = (EntityPlayer)obj;
      double distanceSq = victim.func_70092_e(0.5D + posX, 0.5D + posY, 0.5D + posZ);
      if ((distanceSq > minSq) && (distanceSq <= radiusSq)) {
        if (ItemsPOPPET.voodooProtectionActivated(player, null, victim, 6))
          return false;
        if (!victim.func_70644_a(Potion.field_76440_q)) {
          victim.func_70690_d(new PotionEffect(field_76440_qfield_76415_H, (enhanced ? 5 : 2) * 'Ұ', 0));
        }
      }
    }
    
    for (Object obj : field_72996_f) {
      if ((obj instanceof EntityLiving)) {
        EntityLiving victim = (EntityLiving)obj;
        double distanceSq = victim.func_70092_e(0.5D + posX, 0.5D + posY, 0.5D + posZ);
        if ((distanceSq > minSq) && (distanceSq <= radiusSq) && 
          (!victim.func_70644_a(Potion.field_76440_q))) {
          victim.func_70690_d(new PotionEffect(field_76440_qfield_76415_H, (enhanced ? 5 : 2) * 'Ұ', 0));
        }
      }
    }
    

    return true;
  }
  
  public void doBlockAction(World world, int posX, int posY, int posZ, int currentRadius, EntityPlayer player, boolean enhanced) {}
}
