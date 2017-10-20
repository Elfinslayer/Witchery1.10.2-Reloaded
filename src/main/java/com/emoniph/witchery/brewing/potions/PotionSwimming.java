package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.Witchery;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class PotionSwimming extends PotionBase implements IHandleLivingUpdate
{
  public PotionSwimming(int id, int color)
  {
    super(id, color);
  }
  
  public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
  {
    if ((entity instanceof EntityPlayer)) {
      EntityPlayer player = (EntityPlayer)entity;
      if ((field_72995_K) && 
        (player.func_70090_H()) && (!player.func_70644_a(PotionsPARALYSED))) {
        Minecraft minecraft = Minecraft.func_71410_x();
        if (com.emoniph.witchery.util.KeyBindHelper.isKeyBindDown(func_71410_xfield_71474_y.field_74351_w)) {
          amplifier = 3;
          field_70159_w *= (1.15D + 0.03D * amplifier);
          field_70179_y *= (1.15D + 0.03D * amplifier);
        }
        
      }
    }
    else if ((field_72995_K) && (world.func_82737_E() % 10L == 3L) && 
      (entity.func_70090_H()) && (entity.func_70644_a(PotionsPARALYSED))) {
      field_70159_w *= (1.15D + 0.03D * amplifier);
      field_70179_y *= (1.15D + 0.03D * amplifier);
    }
  }
}
