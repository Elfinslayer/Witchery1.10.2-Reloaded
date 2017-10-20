package com.emoniph.witchery.infusion.infusions;

import com.emoniph.witchery.brewing.potions.PotionEnslaved;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePower;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePower.Registry;
import com.emoniph.witchery.util.BlockSide;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.lang.reflect.Field;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class InfusionInfernal extends Infusion
{
  private static final int MAX_CHARGES = 20;
  
  public InfusionInfernal(int infusionID)
  {
    super(infusionID);
  }
  
  public IIcon getPowerBarIcon(EntityPlayer player, int index)
  {
    return net.minecraft.init.Blocks.field_150424_aL.func_149691_a(0, 0);
  }
  
  public void onLeftClickEntity(ItemStack itemstack, World world, EntityPlayer player, Entity otherEntity)
  {
    if ((!field_72995_K) && 
      ((otherEntity instanceof EntityLivingBase))) {
      EntityLivingBase entityLivingBase = (EntityLivingBase)otherEntity;
      if (player.func_70093_af()) {
        if (PotionEnslaved.canCreatureBeEnslaved(entityLivingBase)) {
          EntityLiving entityLiving = (EntityLiving)entityLivingBase;
          if (PotionEnslaved.isMobEnslavedBy(entityLiving, player))
          {
            if (consumeCharges(world, player, 1, true)) {
              trySacrificeCreature(world, player, entityLiving);
            }
            
          }
          else if (consumeCharges(world, player, 5, true)) {
            PotionEnslaved.setEnslaverForMob(entityLiving, player);
            com.emoniph.witchery.util.EntityUtil.dropAttackTarget((EntityLiving)otherEntity);
            
            ParticleEffect.SPELL.send(SoundEffect.MOB_ZOMBIE_INFECT, entityLiving, 1.0D, 2.0D, 16);
          }
        }
        else {
          SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
        }
      }
      else {
        int r = 50;
        if (consumeCharges(world, player, 1, true)) {
          int minionCount = 0;
          AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(field_70165_t - 50.0D, field_70163_u - 15.0D, field_70161_v - 50.0D, field_70165_t + 50.0D, field_70163_u + 15.0D, field_70161_v + 50.0D);
          
          for (Object obj : world.func_72872_a(EntityLiving.class, bounds)) {
            EntityLiving nearbyLivingEntity = (EntityLiving)obj;
            if (PotionEnslaved.isMobEnslavedBy(nearbyLivingEntity, player)) {
              minionCount++;
              nearbyLivingEntity.func_70624_b(entityLivingBase);
              if ((nearbyLivingEntity instanceof EntityGhast)) {
                try {
                  EntityGhast ghastEntity = (EntityGhast)nearbyLivingEntity;
                  Field[] fields = EntityGhast.class.getDeclaredFields();
                  
                  Field fieldTargetedEntity = fields[4];
                  fieldTargetedEntity.setAccessible(true);
                  fieldTargetedEntity.set(ghastEntity, entityLivingBase);
                  
                  Field fieldAggroCooldown = fields[5];
                  fieldAggroCooldown.setAccessible(true);
                  fieldAggroCooldown.set(ghastEntity, Integer.valueOf(20000));
                } catch (IllegalAccessException e) {
                  Log.instance().warning(e, "Exception occurred setting ghast target.");
                } catch (Exception e) {
                  Log.instance().debug(String.format("Exception occurred setting ghast target. %s", new Object[] { e.toString() }));
                }
              }
              if ((nearbyLivingEntity instanceof EntityCreature)) {
                EntityCreature nearbyCreatureEntity = (EntityCreature)obj;
                nearbyCreatureEntity.func_70784_b(entityLivingBase);
                nearbyCreatureEntity.func_70604_c(entityLivingBase);
                if (((nearbyCreatureEntity instanceof net.minecraft.entity.monster.EntityZombie)) || ((nearbyCreatureEntity instanceof net.minecraft.entity.monster.EntityCreeper))) {
                  field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(nearbyCreatureEntity, entityLivingBase.getClass(), 1.0D, false));
                }
              }
            }
          }
          
          if (minionCount > 0) {
            ParticleEffect.CRIT.send(SoundEffect.RANDOM_BREATH, entityLivingBase, 0.5D, 2.0D, 16);
          }
        }
      }
    }
  }
  


  private void trySacrificeCreature(World world, EntityPlayer player, EntityLiving creature)
  {
    CreaturePower power = CreaturePower.Registry.instance().get(creature);
    if (power != null) {
      int currentCreaturePowerID = CreaturePower.getCreaturePowerID(player);
      if (currentCreaturePowerID == power.getCreaturePowerID()) {
        int currentCharges = CreaturePower.getCreaturePowerCharges(player);
        CreaturePower.setCreaturePowerCharges(player, MathHelper.func_76128_c(Math.min(currentCharges + power.getChargesPerSacrifice(), 20)));
      } else {
        CreaturePower.setCreaturePowerID(player, power.getCreaturePowerID(), power.getChargesPerSacrifice());
      }
      syncPlayer(world, player);
      creature.func_70097_a(net.minecraft.util.DamageSource.func_76354_b(player, null), creature.func_110143_aJ() + 1.0F);
    } else {
      playFailSound(world, player);
    }
  }
  
  public void onHurt(World worldObj, EntityPlayer player, LivingHurtEvent event)
  {
    int creaturePowerID = CreaturePower.getCreaturePowerID(player);
    if (creaturePowerID > 0) {
      CreaturePower.Registry.instance().get(creaturePowerID).onDamage(field_70170_p, player, event);
    }
  }
  
  public void onFalling(World world, EntityPlayer player, LivingFallEvent event)
  {
    int creaturePowerID = CreaturePower.getCreaturePowerID(player);
    if (creaturePowerID > 0) {
      CreaturePower.Registry.instance().get(creaturePowerID).onFalling(world, player, event);
    }
  }
  
  public void onUsingItemTick(ItemStack itemstack, World world, EntityPlayer player, int countdown)
  {
    int elapsedTicks = getMaxItemUseDuration(itemstack) - countdown;
  }
  
  public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int countdown)
  {
    if (!field_72995_K) {
      int elapsedTicks = getMaxItemUseDuration(itemstack) - countdown;
      double MAX_TARGET_RANGE = 15.0D;
      MovingObjectPosition mop = InfusionOtherwhere.doCustomRayTrace(world, player, true, 15.0D);
      
      if (player.func_70093_af()) {
        if (mop != null) {
          switch (1.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[field_72313_a.ordinal()]) {
          case 1: 
            playFailSound(world, player);
            break;
          case 2: 
            if (BlockSide.TOP.isEqual(field_72310_e)) {
              int minionCount = 0;
              int r = 50;
              AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(field_70165_t - 50.0D, field_70163_u - 15.0D, field_70161_v - 50.0D, field_70165_t + 50.0D, field_70163_u + 15.0D, field_70161_v + 50.0D);
              
              for (Object obj : world.func_72872_a(EntityLiving.class, bounds)) {
                EntityLiving creature = (EntityLiving)obj;
                EntityCreature creature2 = (creature instanceof EntityCreature) ? (EntityCreature)creature : null;
                if (PotionEnslaved.isMobEnslavedBy(creature, player)) {
                  minionCount++;
                  creature.func_70624_b(null);
                  creature.func_70604_c(null);
                  if (creature2 != null) {
                    creature2.func_70784_b(null);
                  }
                  if ((((creature instanceof net.minecraft.entity.monster.EntitySpider)) || (!creature.func_70661_as().func_75492_a(field_72311_b, field_72312_c + 1, field_72309_d, 1.0D))) && 
                    (creature2 != null)) {
                    creature2.func_70778_a(world.func_72844_a(creature, field_72311_b, field_72312_c + 1, field_72309_d, 10.0F, true, false, false, true));
                  }
                }
              }
              

              if (minionCount > 0)
                ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_POP, world, field_72311_b, field_72312_c + 1, field_72309_d, 0.5D, 2.0D, 16);
            }
            break;
          

          }
          
        } else {
          playFailSound(world, player);
        }
      } else {
        int beastPowerID = CreaturePower.getCreaturePowerID(player);
        if (beastPowerID > 0) {
          CreaturePower power = CreaturePower.Registry.instance().get(beastPowerID);
          int chargesRequired = power.activateCost(world, player, elapsedTicks, mop);
          int currentCharges = CreaturePower.getCreaturePowerCharges(player);
          if ((currentCharges - chargesRequired >= 0) && (consumeCharges(world, player, 1, true))) {
            power.onActivate(world, player, elapsedTicks, mop);
            if (!field_71075_bZ.field_75098_d) {
              CreaturePower.setCreaturePowerCharges(player, currentCharges - chargesRequired);
              syncPlayer(world, player);
            }
          } else {
            playFailSound(world, player);
          }
        } else {
          playFailSound(world, player);
        }
      }
    }
  }
}
