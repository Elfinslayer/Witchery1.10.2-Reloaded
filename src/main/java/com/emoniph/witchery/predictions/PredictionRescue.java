package com.emoniph.witchery.predictions;

import com.emoniph.witchery.entity.EntityOwl;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PredictionRescue extends PredictionAlwaysForced
{
  private final Class<? extends EntityLiving> entityClass;
  
  public PredictionRescue(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey, int regularFulfillmentDurationInTicks, double regularFulfillmentProbability, Class<? extends EntityLiving> entityClass)
  {
    super(id, itemWeight, selfFulfillmentProbabilityPerSec, translationKey, regularFulfillmentDurationInTicks, regularFulfillmentProbability);
    this.entityClass = entityClass;
  }
  
  public boolean checkIfFulfilled(World world, EntityPlayer player, LivingHurtEvent event, boolean isPastDue, boolean veryOld)
  {
    if (!event.isCanceled()) {
      Entity attackingEntity = source.func_76346_g();
      if ((attackingEntity != null) && ((attackingEntity instanceof EntityLivingBase))) {
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
            if (hy >= field_70131_O)
            {
              entity.func_70012_b(0.5D + nx, 0.05D + ny + 1.0D, 0.5D + nz, 0.0F, 0.0F);
              world.func_72838_d(entity);
              
              IEntityLivingData entitylivingData = null;
              entitylivingData = entity.func_110161_a(entitylivingData);
              
              if ((entity instanceof EntityOwl)) {
                ((EntityOwl)entity).setTimeToLive(300);
              }
              
              entity.func_70624_b((EntityLivingBase)attackingEntity);
              if ((entity instanceof EntityCreature)) {
                ((EntityCreature)entity).func_70784_b((EntityLivingBase)attackingEntity);
                ((EntityCreature)entity).func_70604_c((EntityLivingBase)attackingEntity);
              }
              
              ParticleEffect.SMOKE.send(SoundEffect.NONE, entity, 0.5D, 2.0D, 16);
              
              return true;
            }
            return false;
          }
        }
        catch (NoSuchMethodException ex) {}catch (InvocationTargetException ex) {}catch (InstantiationException ex) {}catch (IllegalAccessException ex) {}
        




        Log.instance().debug(String.format("Prediction for rescue by fulfilled as predicted", new Object[] { attackingEntity.toString() }));
        
        return false;
      }
    }
    return false;
  }
}
