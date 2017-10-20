package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.util.ParticleEffect;
import java.util.Random;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class PotionLove extends PotionBase implements IHandleLivingUpdate
{
  public PotionLove(int id, int color)
  {
    super(id, color);
  }
  
  public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
  {
    if ((!field_72995_K) && (world.func_82737_E() % 20L == 7L)) {
      if ((entity instanceof EntityAnimal)) {
        EntityAnimal animal = (EntityAnimal)entity;
        if ((animal.func_70874_b() >= 0) && (!animal.func_70880_s())) {
          animal.func_70873_a(0);
          animal.func_146082_f(null);
        }
      } else if ((entity instanceof EntityZombie)) {
        EntityZombie zombie = (EntityZombie)entity;
        if (!zombie.func_70631_g_()) {
          for (Object obj : field_70714_bg.field_75782_a) {
            EntityAITasks.EntityAITaskEntry task = (EntityAITasks.EntityAITaskEntry)obj;
            if ((field_75733_a instanceof EntityAIZombieMateNow)) {
              ((EntityAIZombieMateNow)field_75733_a).beginMating();
              return;
            }
          }
          EntityAIZombieMateNow ai = new EntityAIZombieMateNow(zombie);
          ai.beginMating();
          field_70714_bg.func_75776_a(1, ai);
        }
      } else if ((entity instanceof EntityVillager)) {
        EntityVillager villager = (EntityVillager)entity;
        if ((!villager.func_70631_g_()) && (!villager.func_70941_o())) {
          for (Object obj : field_70714_bg.field_75782_a) {
            EntityAITasks.EntityAITaskEntry task = (EntityAITasks.EntityAITaskEntry)obj;
            if ((field_75733_a instanceof EntityAIVillagerMateNow)) {
              ((EntityAIVillagerMateNow)field_75733_a).beginMating();
              return;
            }
          }
          EntityAIVillagerMateNow ai = new EntityAIVillagerMateNow(villager);
          ai.beginMating();
          field_70714_bg.func_75776_a(1, ai);
        }
      } else if (((entity instanceof net.minecraft.entity.player.EntityPlayer)) && 
        (field_73012_v.nextInt(2) == 0)) {
        ParticleEffect.HEART.send(com.emoniph.witchery.util.SoundEffect.NONE, world, entityLiving.field_70165_t, entityLiving.field_70163_u + 1.0D, entityLiving.field_70161_v, 0.6D, 2.0D, 16);
      }
    }
  }
}
