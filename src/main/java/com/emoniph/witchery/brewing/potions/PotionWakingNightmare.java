package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.entity.EntityNightmare;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.SoundEffect;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class PotionWakingNightmare extends PotionBase implements IHandleLivingUpdate
{
  public PotionWakingNightmare(int id, int color)
  {
    super(id, true, color);
  }
  
  public void postContructInitialize()
  {
    setPermenant();
    setIncurable();
  }
  
  public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
  {
    if ((!field_72995_K) && (world.func_82737_E() % 20L == 3L) && 
      (field_71093_bK != instancedimensionDreamID)) {
      if (field_73012_v.nextInt(amplifier > 1 ? 60 : amplifier > 3 ? 30 : 180) == 0) {
        double R = 16.0D;
        double H = 8.0D;
        AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(field_70165_t - 16.0D, field_70163_u - 8.0D, field_70161_v - 16.0D, field_70165_t + 16.0D, field_70163_u + 8.0D, field_70161_v + 16.0D);
        
        List<EntityNightmare> entities = world.func_72872_a(EntityNightmare.class, bounds);
        
        boolean doNothing = false;
        for (EntityNightmare nightmare : entities) {
          if (nightmare.getVictimName().equalsIgnoreCase(entity.func_70005_c_())) {
            doNothing = true;
            break;
          }
        }
        if (!doNothing) {
          Infusion.spawnCreature(world, EntityNightmare.class, MathHelper.func_76128_c(field_70165_t), MathHelper.func_76128_c(field_70163_u), MathHelper.func_76128_c(field_70161_v), entity, 2, 6, null, SoundEffect.NONE);
        }
      }
    }
  }
}
