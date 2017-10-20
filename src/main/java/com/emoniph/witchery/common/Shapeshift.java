package com.emoniph.witchery.common;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.potions.WitcheryPotions;
import com.emoniph.witchery.entity.EntityWolfman;
import com.emoniph.witchery.infusion.infusions.InfusionInfernal;
import com.emoniph.witchery.item.ItemHunterClothes;
import com.emoniph.witchery.network.PacketPipeline;
import com.emoniph.witchery.network.PacketPushTarget;
import com.emoniph.witchery.network.PacketSyncEntitySize;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.CreatureUtil;
import com.emoniph.witchery.util.EntitySizeInfo;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import com.emoniph.witchery.util.TransformCreature;
import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;

public class Shapeshift
{
  public static final Shapeshift INSTANCE = new Shapeshift();
  public Shapeshift() {}
  
  public static class StatBoost {
    public final double jump;
    public final double leap;
    public final int health;
    public final float damage;
    public final float resistance;
    public final float speed;
    public int fall;
    public final float damageCap;
    public boolean flying;
    
    public StatBoost(float damage) { jump = 0.0D;
      leap = 0.0D;
      health = 0;
      this.damage = damage;
      resistance = 0.0F;
      speed = 0.0F;
      fall = 0;
      damageCap = 0.0F;
    }
    
    public StatBoost(float speed, double jump, double leap, int health, float damage, float resistance, int fall, float damageCap)
    {
      this.jump = jump;
      this.leap = leap;
      this.health = health;
      this.damage = damage;
      this.resistance = resistance;
      this.speed = speed;
      this.fall = fall;
      this.damageCap = damageCap;
    }
    
    public StatBoost setFlying(boolean active) {
      flying = active;
      fall = -1;
      return this;
    }
  }
  
  public final StatBoost[] boostWolfman = { new StatBoost(0.0F, 0.0D, 0.0D, 0, 0.0F, 0.0F, 0, 4.0F), new StatBoost(0.0F, 0.0D, 0.0D, 0, 0.0F, 0.0F, 0, 4.0F), new StatBoost(0.0F, 0.0D, 0.0D, 0, 0.0F, 0.0F, 0, 3.0F), new StatBoost(0.0F, 0.0D, 0.0D, 0, 0.0F, 0.0F, 0, 3.0F), new StatBoost(0.0F, 0.0D, 0.0D, 0, 0.0F, 0.0F, 0, 3.0F), new StatBoost(0.2F, 0.20000000298023224D, 0.20000000298023224D, 20, 4.0F, 3.0F, 3, 2.0F), new StatBoost(0.2F, 0.30000001192092896D, 0.20000000298023224D, 20, 4.0F, 3.0F, 4, 2.0F), new StatBoost(0.4F, 0.4000000059604645D, 0.4000000059604645D, 20, 5.0F, 4.0F, 5, 2.0F), new StatBoost(0.4F, 0.5D, 0.4000000059604645D, 30, 6.0F, 4.0F, 6, 2.0F), new StatBoost(0.5F, 0.6000000238418579D, 0.6000000238418579D, 40, 7.0F, 5.0F, 7, 2.0F), new StatBoost(0.5F, 0.6000000238418579D, 0.6000000238418579D, 40, 7.0F, 5.0F, 7, 2.0F) };
  








  public final StatBoost[] boostWolf = { new StatBoost(0.0F, 0.0D, 0.0D, 0, 0.0F, 0.0F, 0, 4.0F), new StatBoost(0.5F, 0.20000000298023224D, 0.20000000298023224D, 0, 1.0F, 0.0F, 2, 4.0F), new StatBoost(0.5F, 0.20000000298023224D, 0.20000000298023224D, 0, 1.0F, 0.0F, 2, 3.0F), new StatBoost(0.75F, 0.20000000298023224D, 0.30000001192092896D, 0, 2.0F, 0.0F, 2, 3.0F), new StatBoost(0.75F, 0.20000000298023224D, 0.4000000059604645D, 0, 2.0F, 0.0F, 3, 3.0F), new StatBoost(0.75F, 0.20000000298023224D, 0.5D, 0, 2.0F, 0.0F, 3, 2.0F), new StatBoost(1.0F, 0.20000000298023224D, 0.6000000238418579D, 0, 2.0F, 1.0F, 3, 2.0F), new StatBoost(1.25F, 0.30000001192092896D, 0.699999988079071D, 4, 2.0F, 1.0F, 4, 2.0F), new StatBoost(1.5F, 0.30000001192092896D, 0.800000011920929D, 8, 3.0F, 2.0F, 4, 2.0F), new StatBoost(1.75F, 0.30000001192092896D, 0.8999999761581421D, 12, 3.0F, 3.0F, 5, 2.0F), new StatBoost(1.75F, 0.30000001192092896D, 1.0D, 12, 3.0F, 3.0F, 5, 2.0F) };
  







  public final StatBoost[] boostVampire = { new StatBoost(0.0F), new StatBoost(1.0F), new StatBoost(1.0F), new StatBoost(1.0F), new StatBoost(2.0F), new StatBoost(2.0F), new StatBoost(2.0F), new StatBoost(3.0F), new StatBoost(3.0F), new StatBoost(3.0F), new StatBoost(3.0F) };
  


  public final StatBoost[] boostBat = { new StatBoost(0.0F), new StatBoost(-6.0F).setFlying(true), new StatBoost(-6.0F).setFlying(true), new StatBoost(-6.0F).setFlying(true), new StatBoost(-6.0F).setFlying(true), new StatBoost(-6.0F).setFlying(true), new StatBoost(-6.0F).setFlying(true), new StatBoost(-6.0F).setFlying(true), new StatBoost(-6.0F).setFlying(true), new StatBoost(-6.0F).setFlying(true), new StatBoost(-6.0F).setFlying(true) };
  





  public static final AttributeModifier SPEED_MODIFIER = new AttributeModifier(UUID.fromString("10536417-7AA6-4033-A598-8E934CA77D98"), "witcheryWolfSpeed", 0.5D, 2);
  
  public static final AttributeModifier DAMAGE_MODIFIER = new AttributeModifier(UUID.fromString("46C5271C-193B-4D41-9CAB-D071AAEE9D4A"), "witcheryWolfDamage", 6.0D, 2);
  
  public static final AttributeModifier HEALTH_MODIFIER = new AttributeModifier(UUID.fromString("615920F9-6675-4779-8B18-6A62A3671E94"), "witcheryWolfHealth", 40.0D, 0);
  





  private static Field fieldExperienceValue;
  






  public void initCurrentShift(EntityPlayer player)
  {
    if (!field_70170_p.field_72995_K) {
      ExtendedPlayer playerEx = ExtendedPlayer.get(player);
      EntitySizeInfo sizeInfo = new EntitySizeInfo(player);
      com.emoniph.witchery.brewing.potions.PotionResizing.setEntitySize(player, defaultWidth, defaultHeight);
      field_70138_W = stepSize;
      eyeHeight = eyeHeight;
      
      BaseAttributeMap playerAttributes = player.func_110140_aT();
      StatBoost boost = getStatBoost(player, playerEx);
      
      if (boost != null) {
        applyModifier(SharedMonsterAttributes.field_111263_d, SPEED_MODIFIER, speed, playerAttributes);
        applyModifier(SharedMonsterAttributes.field_111264_e, DAMAGE_MODIFIER, damage, playerAttributes);
        
        applyModifier(SharedMonsterAttributes.field_111267_a, HEALTH_MODIFIER, health, playerAttributes);
      } else {
        removeModifier(SharedMonsterAttributes.field_111263_d, SPEED_MODIFIER, playerAttributes);
        removeModifier(SharedMonsterAttributes.field_111264_e, DAMAGE_MODIFIER, playerAttributes);
        removeModifier(SharedMonsterAttributes.field_111267_a, HEALTH_MODIFIER, playerAttributes);
      }
      
      if (!field_71075_bZ.field_75098_d) {
        field_71075_bZ.field_75101_c = ((boost != null) && (flying));
        if ((!field_71075_bZ.field_75101_c) && (field_71075_bZ.field_75100_b)) {
          field_71075_bZ.field_75100_b = false;
        } else if (field_71075_bZ.field_75101_c) {
          field_71075_bZ.field_75100_b = true;
        }
        
        player.func_71016_p();
      }
      
      Witchery.packetPipeline.sendToAll(new PacketSyncEntitySize(player));
      Witchery.packetPipeline.sendTo(new PacketSyncEntitySize(player), player);
    }
  }
  
  public void updatePlayerState(EntityPlayer player, ExtendedPlayer playerEx) {
    if (playerEx.getCreatureType() == TransformCreature.BAT) {
      if (field_71075_bZ.field_75100_b) {
        field_70143_R = 0.0F;
      }
      
      if ((!field_71075_bZ.field_75101_c) && (!field_71075_bZ.field_75098_d)) {
        field_71075_bZ.field_75101_c = true;
        player.func_71016_p();
      }
    }
  }
  
  public float updateFallState(EntityPlayer player, float distance) {
    ExtendedPlayer playerEx = ExtendedPlayer.get(player);
    StatBoost boost = getStatBoost(player, playerEx);
    if (boost != null) {
      if (fall == -1) {
        return 0.0F;
      }
      return Math.max(0.0F, distance - fall);
    }
    
    return distance;
  }
  
  public float getResistance(EntityPlayer player, ExtendedPlayer playerEx)
  {
    StatBoost boost = getStatBoost(player, playerEx);
    if (boost != null) {
      return resistance;
    }
    return 0.0F;
  }
  
  public float getDamageCap(EntityPlayer player, ExtendedPlayer playerEx)
  {
    StatBoost boost = getStatBoost(player, playerEx);
    if (boost != null) {
      return damageCap;
    }
    return 0.0F;
  }
  
  public void updateJump(EntityPlayer player)
  {
    ExtendedPlayer playerEx = ExtendedPlayer.get(player);
    StatBoost boost = getStatBoost(player, playerEx);
    if (boost != null) {
      field_70181_x += jump;
      if (player.func_70051_ag()) {
        float f = field_70177_z * 0.017453292F;
        field_70159_w -= MathHelper.func_76126_a(f) * leap;
        field_70179_y += MathHelper.func_76134_b(f) * leap;
      }
    }
  }
  
  public void updateChargeDamage(LivingHurtEvent event, EntityPlayer player, ExtendedPlayer playerEx) {
    if (isWolfAnimalForm(playerEx)) {
      if (itemHasDamageAttribute(player.func_70694_bm())) {
        ammount = 2.0F;
      } else {
        StatBoost boost = getStatBoost(player, playerEx);
        if ((boost != null) && (player.func_70051_ag())) {
          ammount += damage;
        }
      }
    }
    if ((playerEx.getVampireLevel() >= 3) && (playerEx.getCreatureType() == TransformCreature.NONE) && (player.func_70093_af()))
    {

      double ACCELERATION = 3.0D;
      Vec3 look = player.func_70040_Z();
      double motionX = field_72450_a * 0.6D * 3.0D;
      double motionY = 0.8999999999999999D;
      double motionZ = field_72449_c * 0.6D * 3.0D;
      if ((entityLiving instanceof EntityPlayer)) {
        EntityPlayer targetPlayer = (EntityPlayer)entityLiving;
        Witchery.packetPipeline.sendTo(new PacketPushTarget(motionX, 0.8999999999999999D, motionZ), targetPlayer);
      } else {
        entityLiving.field_70159_w = motionX;
        entityLiving.field_70181_x = 0.8999999999999999D;
        entityLiving.field_70179_y = motionZ;
      }
    }
  }
  
  private boolean itemHasDamageAttribute(ItemStack item) {
    if (item == null) {
      return false;
    }
    
    Multimap modifiers = item.func_111283_C();
    if (modifiers == null) {
      return false;
    }
    
    boolean hasDamage = modifiers.containsKey(SharedMonsterAttributes.field_111264_e.func_111108_a());
    
    return hasDamage;
  }
  
  public void rendArmor(EntityLivingBase victim, EntityPlayer player, ExtendedPlayer playerEx) {
    if ((playerEx.getCreatureType() == TransformCreature.WOLFMAN) && (playerEx.getWerewolfLevel() >= 9)) {
      int slot = 1 + field_70170_p.field_73012_v.nextInt(4);
      ItemStack armor = victim.func_71124_b(slot);
      if (armor != null) {
        boolean ripOffArmor = !armor.func_77984_f();
        if (!ripOffArmor) {
          int damage = armor.func_77960_j();
          int rendAmount = (int)Math.ceil(armor.func_77958_k() * 0.25F);
          armor.func_77972_a(rendAmount, player);
          if (((victim instanceof EntityPlayer)) && ((armor.func_77973_b() instanceof ItemArmor))) {
            ItemArmor armorItem = (ItemArmor)armor.func_77973_b();
            armorItem.onArmorTick(field_70170_p, (EntityPlayer)victim, armor);
          }
          ripOffArmor = armor.func_77960_j() <= damage;
        }
        

        if ((ripOffArmor) && ((victim instanceof EntityPlayer))) {
          victim.func_70062_b(slot, null);
          EntityItem droppedItem = victim.func_70099_a(armor, 1.0F);
          if (droppedItem != null) {
            field_145804_b = TimeUtil.secsToTicks(5);
          }
        }
      }
    }
  }
  
  public void processCreatureKilled(LivingDeathEvent event, EntityPlayer attacker, ExtendedPlayer playerEx) {
    if ((isWolfAnimalForm(playerEx)) && (playerEx.getWerewolfLevel() >= 4) && (!CreatureUtil.isUndead(entityLiving)))
    {
      ParticleEffect.REDDUST.send(field_70170_p.field_73012_v.nextInt(3) == 0 ? SoundEffect.WITCHERY_MOB_WOLFMAN_EAT : SoundEffect.NONE, entityLiving, 1.0D, 2.0D, 16);
      

      attacker.func_71024_bL().func_75122_a(8, 0.8F);
    }
  }
  
  public void processDigging(BlockEvent.HarvestDropsEvent event, EntityPlayer player, ExtendedPlayer playerEx) {
    if ((playerEx.getCreatureType() == TransformCreature.WOLF) && (playerEx.getWerewolfLevel() >= 3) && 
      (drops.size() == 1) && (drops.get(0) != null) && (((ItemStack)drops.get(0)).func_77973_b() == Item.func_150898_a(net.minecraft.init.Blocks.field_150346_d)))
    {
      long lastFind = playerEx.getLastBoneFind();
      long serverTime = MinecraftServer.func_130071_aq();
      if ((lastFind + TimeUtil.secsToMillisecs(60) < serverTime) && 
        (field_70170_p.field_73012_v.nextInt(20) == 0)) {
        playerEx.setLastBoneFind(serverTime);
        drops.add(new ItemStack(net.minecraft.init.Items.field_151103_aS, field_70170_p.field_73012_v.nextInt(5) == 0 ? 2 : 1));
      }
    }
  }
  



  public void checkForHowling(EntityPlayer player, ExtendedPlayer playerEx)
  {
    if ((playerEx.getWerewolfLevel() == 6) && (isWolfAnimalForm(playerEx)) && (playerEx.getWolfmanQuestState() == ExtendedPlayer.QuestState.STARTED) && (!field_70170_p.func_72935_r()))
    {
      int x = MathHelper.func_76128_c(field_70165_t) >> 4;
      int z = MathHelper.func_76128_c(field_70161_v) >> 4;
      SoundEffect.WITCHERY_MOB_WOLFMAN_HOWL.playAtPlayer(field_70170_p, player, 1.0F);
      if (playerEx.storeWolfmanQuestChunk(x, z)) {
        playerEx.increaseWolfmanQuestCounter();
      } else {
        ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.werewolf.chunkvisited", new Object[0]);
      }
    } else if ((playerEx.getCreatureType() == TransformCreature.WOLF) && (playerEx.getWerewolfLevel() >= 8)) {
      long lastHowl = playerEx.getLastHowl();
      long serverTime = MinecraftServer.func_130071_aq();
      if ((field_71075_bZ.field_75098_d) || (lastHowl + TimeUtil.secsToMillisecs(60) < serverTime)) {
        SoundEffect.WITCHERY_MOB_WOLFMAN_HOWL.playAtPlayer(field_70170_p, player, 1.0F);
        playerEx.setLastHowl(serverTime);
        
        for (int i = 0; i < 2 + field_70170_p.field_73012_v.nextInt(playerEx.getWerewolfLevel() - 7); i++) {
          EntityCreature creature = InfusionInfernal.spawnCreature(field_70170_p, EntityWolf.class, (int)field_70165_t, (int)field_70163_u, (int)field_70161_v, player.func_110144_aD(), 1, 6, ParticleEffect.SMOKE, SoundEffect.NONE);
          

          if (creature != null) {
            creature.func_70690_d(new PotionEffect(PotionsMORTAL_COIL.field_76415_H, TimeUtil.secsToTicks(10)));
            
            EntityWolf wolf = (EntityWolf)creature;
            wolf.func_70903_f(true);
            wolf.func_152115_b(player.func_110124_au().toString());
            if (fieldExperienceValue == null) {
              fieldExperienceValue = ReflectionHelper.findField(EntityLiving.class, new String[] { "experienceValue", "field_70728_aV", "aV" });
            }
            try
            {
              if (fieldExperienceValue != null) {
                fieldExperienceValue.set(wolf, Integer.valueOf(0));
              }
            }
            catch (IllegalAccessException ex) {}
            com.emoniph.witchery.util.EntityUtil.setNoDrops(wolf);
          }
        }
      } else {
        SoundEffect.NOTE_SNARE.playAtPlayer(field_70170_p, player);
      }
    } else if ((playerEx.getCreatureType() == TransformCreature.WOLFMAN) && (playerEx.getWerewolfLevel() >= 7)) {
      long lastHowl = playerEx.getLastHowl();
      long serverTime = MinecraftServer.func_130071_aq();
      if ((field_71075_bZ.field_75098_d) || (lastHowl + TimeUtil.secsToMillisecs(60) < serverTime)) {
        SoundEffect.WITCHERY_MOB_WOLFMAN_HOWL.playAtPlayer(field_70170_p, player, 1.0F);
        playerEx.setLastHowl(serverTime);
        
        double radius = 16.0D;
        java.util.List<EntityLivingBase> entities = field_70170_p.func_72872_a(EntityLivingBase.class, field_70121_D.func_72314_b(radius, radius, radius));
        
        for (EntityLivingBase entity : entities) {
          if ((!CreatureUtil.isWerewolf(entity, true)) && (!CreatureUtil.isVampire(entity))) {
            entity.func_70690_d(new PotionEffect(PotionsPARALYSED.field_76415_H, TimeUtil.secsToTicks(4 + field_70170_p.field_73012_v.nextInt(playerEx.getWerewolfLevel() - 6)), 3));
          }
        }
      }
      else {
        SoundEffect.NOTE_SNARE.playAtPlayer(field_70170_p, player);
      }
    }
  }
  
  public void processWolfInfection(EntityLivingBase entityLiving, EntityPlayer attackingPlayer, ExtendedPlayer playerEx, float health)
  {
    if ((playerEx.getWerewolfLevel() >= 10) && (isWolfAnimalForm(playerEx))) {
      if ((entityLiving instanceof EntityVillager)) {
        if ((health < entityLiving.func_110138_aP() * 0.25F) && (health > 0.0F) && (field_70170_p.field_73012_v.nextInt(4) == 1))
        {
          EntityVillager villager = (EntityVillager)entityLiving;
          EntityWolfman.convertToVillager(villager, villager.func_70946_n(), false, field_70956_bz, field_70963_i);
        }
      }
      else if (((entityLiving instanceof EntityPlayer)) && 
        (instanceallowPlayerToPlayerWolfInfection)) {
        EntityPlayer victim = (EntityPlayer)entityLiving;
        ExtendedPlayer victimEx = ExtendedPlayer.get(victim);
        if ((health < entityLiving.func_110138_aP() * 0.25F) && (health > 0.0F) && (!ItemHunterClothes.isWolfProtectionActive(entityLiving)) && (field_70170_p.field_73012_v.nextInt(4) == 1))
        {

          if ((instanceallowVampireWolfHybrids) || (!playerEx.isVampire()))
          {
            if (victimEx.getWerewolfLevel() == 0) {
              victimEx.setWerewolfLevel(1);
              ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, victim, "witchery.werewolf.infection", new Object[0]);
            }
          }
        }
      }
    }
  }
  
  public void processWolfInfection(EntityLivingBase entityLiving, EntityWolfman attackingEntity, float health)
  {
    if (attackingEntity.isInfectious()) {
      if ((entityLiving instanceof EntityVillager)) {
        if (health < entityLiving.func_110138_aP() * 0.25F) {
          EntityVillager villager = (EntityVillager)entityLiving;
          EntityWolfman.convertToVillager(villager, villager.func_70946_n(), false, field_70956_bz, field_70963_i);
        }
      }
      else if ((entityLiving instanceof EntityPlayer)) {
        EntityPlayer victim = (EntityPlayer)entityLiving;
        ExtendedPlayer victimEx = ExtendedPlayer.get(victim);
        if ((instanceallowVampireWolfHybrids) || (!victimEx.isVampire()))
        {
          if (victimEx.getWerewolfLevel() == 0) {
            victimEx.setWerewolfLevel(1);
            ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, victim, "witchery.werewolf.infection", new Object[0]);
          } }
      }
    }
  }
  
  public boolean isAnimalForm(EntityPlayer player) {
    return isWolfAnimalForm(ExtendedPlayer.get(player));
  }
  
  public boolean isWolfAnimalForm(ExtendedPlayer playerEx) {
    return (playerEx.getCreatureType() == TransformCreature.WOLFMAN) || (playerEx.getCreatureType() == TransformCreature.WOLF);
  }
  
  public boolean isWolfmanAllowed(ExtendedPlayer playerEx)
  {
    return playerEx.getWerewolfLevel() >= 5;
  }
  
  public boolean canControlTransform(ExtendedPlayer playerEx) {
    return playerEx.getWerewolfLevel() >= 2;
  }
  
  public StatBoost getStatBoost(EntityPlayer player, ExtendedPlayer playerEx) {
    TransformCreature creature = playerEx.getCreatureType();
    switch (1.$SwitchMap$com$emoniph$witchery$util$TransformCreature[creature.ordinal()]) {
    case 1: 
      return boostWolf[playerEx.getWerewolfLevel()];
    case 2: 
      return boostWolfman[playerEx.getWerewolfLevel()];
    case 3: 
      return boostBat[playerEx.getVampireLevel()];
    }
    return playerEx.isVampire() ? boostVampire[playerEx.getVampireLevel()] : null;
  }
  

  public void applyModifier(IAttribute attribute, AttributeModifier modifier, double modification, BaseAttributeMap playerAttributes)
  {
    IAttributeInstance attributeInstance = playerAttributes.func_111151_a(attribute);
    AttributeModifier speedModifier = new AttributeModifier(modifier.func_111167_a(), modifier.func_111166_b(), modification, modifier.func_111169_c());
    
    attributeInstance.func_111124_b(speedModifier);
    attributeInstance.func_111121_a(speedModifier);
  }
  
  public void removeModifier(IAttribute attribute, AttributeModifier modifier, BaseAttributeMap playerAttributes) {
    IAttributeInstance attributeInstance = playerAttributes.func_111151_a(attribute);
    attributeInstance.func_111124_b(modifier);
  }
  
  public void shiftTo(EntityPlayer player, TransformCreature creature) {
    ExtendedPlayer.get(player).setCreatureType(creature);
    initCurrentShift(player);
  }
}
