package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.entity.EntityEnt;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;

public class PotionEnslaved extends PotionBase implements IHandleLivingSetAttackTarget, IHandleLivingUpdate
{
  private static final String ENSLAVER_KEY = "WITCEnslaverName";
  
  public PotionEnslaved(int id, int color)
  {
    super(id, true, color);
  }
  

  public void onLivingSetAttackTarget(World world, EntityLiving entity, LivingSetAttackTargetEvent event, int amplifier)
  {
    if ((target != null) && ((target instanceof EntityPlayer)) && ((entity instanceof EntityLiving))) {
      String enslaverName = getMobEnslaverName(entity);
      if (enslaverName.equals(target.func_70005_c_())) {
        entity.func_70624_b(null);
      }
    }
  }
  

  public static boolean setEnslaverForMob(EntityLiving entity, EntityPlayer player)
  {
    if ((entity == null) || (player == null)) {
      return false;
    }
    String enslaverName = entity.getEntityData().func_74779_i("WITCEnslaverName");
    boolean isEnslaved = (enslaverName != null) && (!enslaverName.isEmpty());
    if ((!isEnslaved) || (!player.func_70005_c_().equals(enslaverName))) {
      entity.getEntityData().func_74778_a("WITCEnslaverName", player.func_70005_c_());
      entity.func_70690_d(new PotionEffect(PotionsENSLAVED.field_76415_H, Integer.MAX_VALUE));
      com.emoniph.witchery.util.EntityUtil.dropAttackTarget(entity);
      return true;
    }
    return false;
  }
  

  public static boolean isMobEnslavedBy(EntityLiving entity, EntityPlayer player)
  {
    return (player != null) && (entity != null) && (entity.getEntityData() != null) && (player.func_70005_c_().equals(entity.getEntityData().func_74779_i("WITCEnslaverName")));
  }
  
  public static boolean canCreatureBeEnslaved(EntityLivingBase entityLiving)
  {
    if ((entityLiving instanceof EntityLiving)) {
      if (((entityLiving instanceof IBossDisplayData)) || ((entityLiving instanceof EntityGolem)) || ((entityLiving instanceof com.emoniph.witchery.entity.EntityDemon)) || ((entityLiving instanceof EntityWitch)) || ((entityLiving instanceof com.emoniph.witchery.entity.EntityImp)) || ((entityLiving instanceof EntityEnt)))
      {

        return false;
      }
      return true;
    }
    
    return false;
  }
  
  public static boolean isMobEnslaved(EntityLiving entity)
  {
    if (entity == null) {
      return false;
    }
    String enslaverName = entity.getEntityData().func_74779_i("WITCEnslaverName");
    return (enslaverName != null) && (!enslaverName.isEmpty());
  }
  
  public static String getMobEnslaverName(EntityLiving entity)
  {
    if (entity == null) {
      return "";
    }
    String enslaverName = entity.getEntityData().func_74779_i("WITCEnslaverName");
    return enslaverName;
  }
  

  public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
  {
    if ((!field_72995_K) && (world.func_82737_E() % 20L == 3L) && ((entity instanceof EntityCreature))) {
      EntityCreature creature = (EntityCreature)entity;
      for (Object obj : field_70715_bh.field_75782_a) {
        EntityAITasks.EntityAITaskEntry task = (EntityAITasks.EntityAITaskEntry)obj;
        if ((field_75733_a instanceof EntityAIEnslaverHurtByTarget)) {
          return;
        }
      }
      field_70715_bh.func_75776_a(1, new EntityAIEnslaverHurtByTarget(creature));
    }
  }
}
