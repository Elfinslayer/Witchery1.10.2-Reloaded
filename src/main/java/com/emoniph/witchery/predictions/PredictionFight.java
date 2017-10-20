package com.emoniph.witchery.predictions;

import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.entity.IOwnable;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PredictionFight extends Prediction
{
  private final Class<? extends EntityLiving> entityClass;
  private final boolean bindTameable;
  
  public PredictionFight(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey, Class<? extends EntityLiving> entityClass, boolean bindTameable)
  {
    super(id, itemWeight, selfFulfillmentProbabilityPerSec, translationKey);
    this.entityClass = entityClass;
    this.bindTameable = bindTameable;
  }
  
  public boolean doSelfFulfillment(World world, EntityPlayer player)
  {
    try {
      int x = MathHelper.func_76128_c(field_70165_t);
      int y = MathHelper.func_76128_c(field_70163_u);
      int z = MathHelper.func_76128_c(field_70161_v);
      
      if (!field_72995_K) {
        int MAX_DISTANCE = 4;
        int MIN_DISTANCE = 2;
        
        int activeRadius = 2;
        int ax = field_73012_v.nextInt(activeRadius * 2 + 1);
        if (ax > activeRadius) {
          ax += 4;
        }
        int nx = x - 4 + ax;
        
        int az = field_73012_v.nextInt(activeRadius * 2 + 1);
        if (az > activeRadius) {
          az += 4;
        }
        
        int nz = z - 4 + az;
        
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
        
        Constructor ctor = entityClass.getConstructor(new Class[] { World.class });
        EntityLiving entity = (EntityLiving)ctor.newInstance(new Object[] { world });
        if (hy >= field_70131_O) {
          boolean bound = false;
          if ((entity instanceof EntityDemon)) {
            ((EntityDemon)entity).setPlayerCreated(true);
          } else if ((bindTameable) && ((entity instanceof EntityTameable))) {
            ((EntityTameable)entity).func_70903_f(true);
            com.emoniph.witchery.util.TameableUtil.setOwner((EntityTameable)entity, player);
            bound = true;
          } else if ((bindTameable) && ((entity instanceof IOwnable))) {
            ((IOwnable)entity).setOwner(player.func_70005_c_());
            bound = true;
          }
          entity.func_70012_b(0.5D + nx, 0.05D + ny + 1.0D, 0.5D + nz, 0.0F, 0.0F);
          world.func_72838_d(entity);
          Log.instance().debug(String.format("Forcing prediction for attack by %s", new Object[] { entity.toString() }));
          
          IEntityLivingData entitylivingData = null;
          entitylivingData = entity.func_110161_a(entitylivingData);
          
          if (!bound) {
            entity.func_70624_b(player);
          }
          
          ParticleEffect.SMOKE.send(com.emoniph.witchery.util.SoundEffect.NONE, entity, 0.5D, 2.0D, 16);
        } else {
          return false;
        }
      }
    }
    catch (NoSuchMethodException ex) {}catch (InvocationTargetException ex) {}catch (InstantiationException ex) {}catch (IllegalAccessException ex) {}
    


    return true;
  }
  
  public boolean checkIfFulfilled(World world, EntityPlayer player, LivingHurtEvent event, boolean isPastDue, boolean veryOld)
  {
    if (!event.isCanceled()) {
      Entity attackingEntity = source.func_76346_g();
      if (attackingEntity != null) {
        boolean attackedByCreature = entityClass.isAssignableFrom(attackingEntity.getClass());
        if (attackedByCreature) {
          Log.instance().debug(String.format("Prediction for attack by %s fulfilled as predicted", new Object[] { attackingEntity.toString() }));
        }
        return attackedByCreature;
      }
    }
    return false;
  }
}
