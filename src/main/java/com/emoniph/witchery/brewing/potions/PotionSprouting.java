package com.emoniph.witchery.brewing.potions;

import java.util.Random;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class PotionSprouting extends PotionBase implements IHandleLivingUpdate
{
  public PotionSprouting(int id, int color)
  {
    super(id, color);
  }
  
  public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
  {
    if ((!field_72995_K) && (world.func_82737_E() % 20L == 9L) && (field_73012_v.nextInt(4) == 0)) {
      com.emoniph.witchery.brewing.action.effect.BrewActionSprouting.growBranch(entity, 1 + amplifier);
    }
  }
}
