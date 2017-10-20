package com.emoniph.witchery.predictions;

import com.emoniph.witchery.entity.ai.EntityAIMateWithPlayer;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.Random;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class PredictionFallInLove extends PredictionAlwaysForced
{
  public PredictionFallInLove(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey, int regularFulfillmentDurationInTicks, double regularFulfillmentProbability)
  {
    super(id, itemWeight, selfFulfillmentProbabilityPerSec, translationKey, regularFulfillmentDurationInTicks, regularFulfillmentProbability);
  }
  
  public boolean checkIfFulfilled(World world, EntityPlayer player, LivingEvent.LivingUpdateEvent event, boolean isPastDue, boolean veryOld)
  {
    if (shouldWeActivate(world, player, isPastDue)) {
      int x = MathHelper.func_76128_c(field_70165_t);
      int y = MathHelper.func_76128_c(field_70163_u);
      int z = MathHelper.func_76128_c(field_70161_v);
      
      int MAX_DISTANCE = 6;
      int MIN_DISTANCE = 4;
      
      int activeRadius = 2;
      int ax = field_73012_v.nextInt(activeRadius * 2 + 1);
      if (ax > activeRadius) {
        ax += 8;
      }
      int nx = x - 6 + ax;
      
      int az = field_73012_v.nextInt(activeRadius * 2 + 1);
      if (az > activeRadius) {
        az += 8;
      }
      
      int nz = z - 6 + az;
      
      int ny = y;
      while ((!world.func_147437_c(nx, ny, nz)) && (ny < y + 8)) {
        ny++;
      }
      

      while ((world.func_147437_c(nx, ny, nz)) && (ny > 0)) {
        ny--;
      }
      


      int hy = 0;
      while ((world.func_147437_c(nx, ny + hy + 1, nz)) && (hy < 6)) {
        hy++;
      }
      
      EntityVillager entity = new EntityVillager(world, 0);
      if ((hy >= field_70131_O) && (world.func_147439_a(nx, ny - 1, nz).func_149721_r()))
      {
        entity.func_70012_b(0.5D + nx, 0.05D + ny + 1.0D, 0.5D + nz, 0.0F, 0.0F);
        world.func_72838_d(entity);
        com.emoniph.witchery.util.Log.instance().debug(String.format("Forcing prediction for lover by %s", new Object[] { entity.toString() }));
        
        EntityAIMateWithPlayer task = new EntityAIMateWithPlayer(entity);
        task.forceTask(player);
        field_70714_bg.func_75776_a(1, task);
        
        ParticleEffect.SMOKE.send(SoundEffect.NONE, entity, 0.5D, 2.0D, 16);
        SoundEffect.WITCHERY_RANDOM_LOVED.playAtPlayer(world, player);
        
        return true;
      }
      return false;
    }
    
    return false;
  }
}
