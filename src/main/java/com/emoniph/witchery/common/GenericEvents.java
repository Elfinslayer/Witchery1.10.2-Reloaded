package com.emoniph.witchery.common;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockVoidBramble;
import com.emoniph.witchery.brewing.potions.PotionResizing;
import com.emoniph.witchery.brewing.potions.WitcheryPotions;
import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.entity.EntityFollower;
import com.emoniph.witchery.entity.EntityGoblin;
import com.emoniph.witchery.entity.EntityHornedHuntsman;
import com.emoniph.witchery.entity.EntityItemWaystone;
import com.emoniph.witchery.entity.EntityMindrake;
import com.emoniph.witchery.entity.EntityOwl;
import com.emoniph.witchery.entity.EntitySummonedUndead;
import com.emoniph.witchery.entity.EntityToad;
import com.emoniph.witchery.entity.EntityVillageGuard;
import com.emoniph.witchery.entity.EntityVillagerWere;
import com.emoniph.witchery.entity.EntityWitchHunter;
import com.emoniph.witchery.entity.EntityWolfman;
import com.emoniph.witchery.entity.ai.EntityAIDigBlocks;
import com.emoniph.witchery.entity.ai.EntityAISleep;
import com.emoniph.witchery.infusion.InfusedBrewEffect;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.item.ItemCaneSword;
import com.emoniph.witchery.item.ItemDeathsClothes;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.item.ItemGlassGoblet;
import com.emoniph.witchery.item.ItemHunterClothes;
import com.emoniph.witchery.item.ItemMoonCharm;
import com.emoniph.witchery.item.ItemPoppet;
import com.emoniph.witchery.item.ItemTaglockKit;
import com.emoniph.witchery.item.ItemVampireClothes;
import com.emoniph.witchery.item.ItemWitchesClothes;
import com.emoniph.witchery.network.PacketPipeline;
import com.emoniph.witchery.network.PacketPlayerStyle;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.BoltDamageSource;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.CreatureUtil;
import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.EntityUtil.DamageSourceVampireFire;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TameableUtil;
import com.emoniph.witchery.util.TimeUtil;
import com.emoniph.witchery.util.TransformCreature;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayer.EnumStatus;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;

public class GenericEvents
{
  public GenericEvents() {}
  
  @SubscribeEvent
  public void onServerChat(ServerChatEvent event)
  {
    boolean chatMasqueradeAllowed = instanceallowChatMasquerading;
    ExtendedPlayer playerEx = ExtendedPlayer.get(player);
    String disguise; if ((playerEx != null) && (chatMasqueradeAllowed) && 
      (playerEx.getCreatureType() == TransformCreature.PLAYER) && (playerEx.getOtherPlayerSkin() != null) && (!playerEx.getOtherPlayerSkin().isEmpty()))
    {
      disguise = playerEx.getOtherPlayerSkin();
      ChatComponentTranslation comp = new ChatComponentTranslation("chat.type.text", new Object[] { getPlayerChatName(player, disguise), ForgeHooks.newChatWithLinks(message) });
      
      component = comp;
      
      if (!player.field_70170_p.field_72995_K) {
        for (Object otherPlayerObj : player.field_70170_p.field_73010_i) {
          EntityPlayer otherPlayer = (EntityPlayer)otherPlayerObj;
          if ((field_71075_bZ.field_75098_d) && (MinecraftServer.func_71276_C().func_71203_ab().func_152596_g(otherPlayer.func_146103_bH()))) {
            ChatUtil.sendTranslated(EnumChatFormatting.GOLD, otherPlayer, "witchery.rite.mirrormirror.opchatreveal", new Object[] { disguise, player.func_70005_c_() });
          }
        }
      }
    }
  }
  
  private net.minecraft.util.IChatComponent getPlayerChatName(EntityPlayerMP player, String otherName)
  {
    ChatComponentText chatcomponenttext = new ChatComponentText(ScorePlayerTeam.func_96667_a(player.func_96124_cp(), otherName));
    chatcomponenttext.func_150256_b().func_150241_a(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + player.func_70005_c_() + " "));
    return chatcomponenttext;
  }
  
  @SubscribeEvent
  public void onEntityConstructing(EntityEvent.EntityConstructing event) {
    if (((entity instanceof EntityPlayer)) && (ExtendedPlayer.get((EntityPlayer)entity) == null)) {
      ExtendedPlayer.register((EntityPlayer)entity);
    } else if (((entity instanceof EntityVillager)) && (ExtendedVillager.get((EntityVillager)entity) == null))
    {
      ExtendedVillager.register((EntityVillager)entity);
    }
  }
  
  @SubscribeEvent(priority=EventPriority.HIGH)
  public void onEntityJoinWorld(EntityJoinWorldEvent event) {
    if ((entity instanceof EntityLivingBase)) {
      NBTTagCompound nbtData = entity.getEntityData();
      nbtData.func_74776_a("WITCInitialWidth", entity.field_70130_N);
      nbtData.func_74776_a("WITCInitialHeight", entity.field_70131_O);
    }
    
    if ((!entity.field_70170_p.field_72995_K) && ((entity instanceof EntityPlayer))) {
      EntityPlayer player = (EntityPlayer)entity;
      ExtendedPlayer.loadProxyData(player);
      Shapeshift.INSTANCE.initCurrentShift(player);
      Infusion.syncPlayer(world, player);
      
      for (Object obj : world.field_73010_i) {
        EntityPlayer otherPlayer = (EntityPlayer)obj;
        if (otherPlayer != player)
        {

          Witchery.packetPipeline.sendTo(new PacketPlayerStyle(otherPlayer), player);
        }
      }
      

      Witchery.packetPipeline.sendToDimension(new PacketPlayerStyle(player), world.field_73011_w.field_76574_g);
      



      if ((field_71093_bK != instancedimensionDreamID) && (WorldProviderDreamWorld.getPlayerIsSpiritWalking(player)) && (!WorldProviderDreamWorld.getPlayerIsGhost(player)))
      {

        WorldProviderDreamWorld.setPlayerMustAwaken(player, true);
      } else if ((field_71093_bK == instancedimensionDreamID) && (!WorldProviderDreamWorld.getPlayerIsSpiritWalking(player)))
      {
        WorldProviderDreamWorld.changeDimension(player, 0);
        WorldProviderDreamWorld.findTopAndSetPosition(field_70170_p, player);
      }
    } else if ((world.field_73011_w.field_76574_g == instancedimensionDreamID) && (isDisallowedEntity(entity)))
    {
      event.setCanceled(true);
    }
    
    if (((entity instanceof EntityVillager)) && (!(entity instanceof EntityVillagerWere)) && (!(entity instanceof EntityVillageGuard)))
    {
      EntityVillager villager = (EntityVillager)entity;
      field_70714_bg.func_75776_a(1, new EntityAISleep(villager));
    } else if ((entity instanceof EntityZombie)) {
      EntityZombie creature = (EntityZombie)entity;
      field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(creature, EntityFollower.class, 0, false, false, new IEntitySelector()
      {
        public boolean func_82704_a(Entity entity)
        {
          return ((entity instanceof EntityFollower)) && (((EntityFollower)entity).getFollowerType() == 0);
        }
      }));
    }
    else if ((entity instanceof EntitySkeleton)) {
      EntitySkeleton creature = (EntitySkeleton)entity;
      field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(creature, EntityFollower.class, 0, true, false, new IEntitySelector()
      {
        public boolean func_82704_a(Entity entity)
        {
          return ((entity instanceof EntityFollower)) && (((EntityFollower)entity).getFollowerType() == 0);
        }
      }));
    }
    

    if ((entity.field_70170_p.field_72995_K) && ((entity instanceof EntityPlayer))) {
      Witchery.packetPipeline.sendToServer(new com.emoniph.witchery.network.PacketExtendedEntityRequestSyncToClient((EntityLivingBase)entity));
    }
  }
  
  @SubscribeEvent
  public void onPlayerCloneEvent(PlayerEvent.Clone event)
  {
    NBTTagCompound oldPlayerNBT = new NBTTagCompound();
    ExtendedPlayer oldPlayerEx = ExtendedPlayer.get(original);
    oldPlayerEx.saveNBTData(oldPlayerNBT);
    
    ExtendedPlayer newPlayerEx = ExtendedPlayer.get(entityPlayer);
    newPlayerEx.loadNBTData(oldPlayerNBT);
    newPlayerEx.restorePlayerInventoryFrom(oldPlayerEx);
  }
  
  private static boolean isDisallowedEntity(Entity entity) {
    if ((entity instanceof EntityLiving)) {
      Class cls = entity.getClass();
      String packageName = cls.getCanonicalName();
      if ((packageName.startsWith("net.minecraft.entity")) || (packageName.startsWith("com.emoniph.witchery"))) {
        return entity instanceof EntityEnderman;
      }
      return true;
    }
    
    return false;
  }
  
  @SubscribeEvent
  public void onPlayerWakeUpEvent(PlayerWakeUpEvent event)
  {
    World world = entityPlayer.field_70170_p;
    if (!field_72995_K) {
      EntityPlayer player = entityPlayer;
      ExtendedPlayer playerEx = ExtendedPlayer.get(player);
      if ((playerEx.isVampire()) && (player.func_71026_bH())) {
        int x = MathHelper.func_76128_c(field_70165_t);
        int y = MathHelper.func_76128_c(field_70163_u);
        int z = MathHelper.func_76128_c(field_70161_v);
        if (world.func_147439_a(x, y, z) == BlocksCOFFIN) {
          Iterator iterator = field_73010_i.iterator();
          EntityPlayer entityplayer;
          do {
            if (!iterator.hasNext())
            {
              long currentTime = world.func_72820_D() - 11000L;
              

              world.func_72877_b(currentTime);
              
              break;
            }
            entityplayer = (EntityPlayer)iterator.next();
          }
          while (entityplayer.func_71026_bH());
        }
      }
    }
  }
  
  @SubscribeEvent
  public void onPlayerSleepInBed(PlayerSleepInBedEvent event) {
    World world = entityPlayer.field_70170_p;
    EntityPlayer player = entityPlayer;
    if (CreatureUtil.isWerewolf(entityPlayer)) {
      if (!field_72995_K) {
        ChatUtil.sendTranslated(EnumChatFormatting.RED, entityPlayer, "witchery.nosleep.wolf", new Object[0]);
        result = EntityPlayer.EnumStatus.OTHER_PROBLEM;
      }
    } else if (entityPlayer.func_70644_a(PotionsRESIZING)) {
      if (!field_72995_K) {
        ChatUtil.sendTranslated(EnumChatFormatting.RED, entityPlayer, "witchery.nosleep.resized", new Object[0]);
        result = EntityPlayer.EnumStatus.OTHER_PROBLEM;
      }
    } else if ((ExtendedPlayer.get(entityPlayer).isVampire()) && (world.func_147439_a(x, y, z) == BlocksCOFFIN))
    {
      if (!entityPlayer.field_70170_p.func_72935_r()) {
        if (!field_72995_K) {
          ChatUtil.sendTranslated(EnumChatFormatting.RED, entityPlayer, "witchery.nosleep.dayonly", new Object[0]);
          result = EntityPlayer.EnumStatus.OTHER_PROBLEM;
        }
      } else {
        if (!field_72995_K) {
          if ((player.func_70608_bn()) || (!player.func_70089_S())) {
            return;
          }
          
          if (!field_73011_w.func_76569_d()) {
            return;
          }
          
          if (!world.func_72935_r()) {
            result = EntityPlayer.EnumStatus.OTHER_PROBLEM;
            return;
          }
          
          if ((Math.abs(field_70165_t - x) > 3.0D) || (Math.abs(field_70163_u - y) > 2.0D) || (Math.abs(field_70161_v - z) > 3.0D))
          {

            result = EntityPlayer.EnumStatus.TOO_FAR_AWAY;
            return;
          }
          
          double d0 = 8.0D;
          double d1 = 5.0D;
          List list = world.func_72872_a(net.minecraft.entity.monster.EntityMob.class, AxisAlignedBB.func_72330_a(x - d0, y - d1, z - d0, x + d0, y + d1, z + d0));
          


          if (!list.isEmpty()) {
            result = EntityPlayer.EnumStatus.NOT_SAFE;
            return;
          }
        }
        
        if (player.func_70115_ae()) {
          player.func_70078_a((Entity)null);
        }
        
        PotionResizing.setEntitySize(player, 0.2F, 0.2F);
        

        field_70129_M = 0.2F;
        
        if (world.func_72899_e(x, y, z)) {
          int l = world.func_147439_a(x, y, z).getBedDirection(world, x, y, z);
          
          float f1 = 0.5F;
          float f = 0.5F;
          
          switch (l) {
          case 0: 
            f = 0.9F;
            break;
          case 1: 
            f1 = 0.1F;
            break;
          case 2: 
            f = 0.1F;
            break;
          case 3: 
            f1 = 0.9F;
          }
          
          field_71079_bU = 0.0F;
          field_71089_bV = 0.0F;
          
          switch (l) {
          case 0: 
            field_71089_bV = -1.8F;
            break;
          case 1: 
            field_71079_bU = 1.8F;
            break;
          case 2: 
            field_71089_bV = 1.8F;
            break;
          case 3: 
            field_71079_bU = -1.8F;
          }
          
          player.func_70107_b(x + f1, y + 0.9375F, z + f);
        }
        else {
          player.func_70107_b(x + 0.5F, y + 0.9375F, z + 0.5F);
        }
        


        field_71083_bS = true;
        field_71076_b = 0;
        field_71081_bT = new ChunkCoordinates(x, y, z);
        field_70159_w = (player.field_70179_y = player.field_70181_x = 0.0D);
        
        if (!field_72995_K) {
          world.func_72854_c();
        }
        
        result = EntityPlayer.EnumStatus.OK;
        return;
      }
    }
  }
  







  @SubscribeEvent
  public void onLivingDrops(LivingDropsEvent event)
  {
    if ((!event.isCanceled()) && (entityLiving != null) && (!entityLiving.field_70170_p.field_72995_K)) {
      if (((entityLiving instanceof EntityLiving)) && 
        (EntityUtil.isNoDrops((EntityLiving)entityLiving))) {
        event.setCanceled(true);
        return;
      }
      

      if ((entityLiving instanceof EntityHorse)) {
        EntityHorse horse = (EntityHorse)entityLiving;
        NBTTagCompound nbtHorse = horse.getEntityData();
        if ((nbtHorse != null) && (nbtHorse.func_74767_n("WITCIsBinky"))) {
          drops.clear();
          drops.add(new EntityItem(field_70170_p, field_70165_t, field_70163_u, field_70161_v, ItemsGENERIC.itemBinkyHead.createStack()));
        }
      }
    }
  }
  
  @SubscribeEvent
  public void onItemToss(ItemTossEvent event)
  {
    if ((!event.isCanceled()) && (!player.field_70170_p.field_72995_K) && 
      (entityItem != null) && (entityItem.func_92059_d() != null)) {
      if (entityItem.func_92059_d().func_77973_b() == ItemsSEEDS_MINDRAKE) {
        entityItem.lifespan = TimeUtil.secsToTicks(3);
        NBTTagCompound nbtItem = entityItem.getEntityData();
        nbtItem.func_74778_a("WITCThrower", player.func_70005_c_());
      } else if ((ItemsGENERIC.itemWaystone.isMatch(entityItem.func_92059_d())) || (ItemsGENERIC.itemWaystoneBound.isMatch(entityItem.func_92059_d())) || (ItemsGENERIC.itemAttunedStone.isMatch(entityItem.func_92059_d())) || (ItemsGENERIC.itemSubduedSpirit.isMatch(entityItem.func_92059_d())) || (ItemsGENERIC.itemWaystonePlayerBound.isMatch(entityItem.func_92059_d())))
      {




        EntityUtil.spawnEntityInWorld(entity.field_70170_p, new EntityItemWaystone(entityItem));
        event.setCanceled(true);
      }
    }
  }
  
  @SubscribeEvent
  public void onItemExpireEvent(ItemExpireEvent event)
  {
    if ((!event.isCanceled()) && (!entityItem.field_70170_p.field_72995_K) && 
      (entityItem != null) && (entityItem.func_92059_d() != null) && (entityItem.func_92059_d().func_77973_b() == ItemsSEEDS_MINDRAKE))
    {
      for (int i = 0; i < entityItem.func_92059_d().field_77994_a; i++) {
        EntityMindrake mindrake = new EntityMindrake(entityItem.field_70170_p);
        mindrake.func_70012_b(entityItem.field_70165_t, entityItem.field_70163_u, entityItem.field_70161_v, 0.0F, 0.0F);
        
        NBTTagCompound nbtItem = entityItem.getEntityData();
        if (nbtItem.func_74764_b("WITCThrower")) {
          String thrower = nbtItem.func_74779_i("WITCThrower");
          if ((thrower != null) && (!thrower.isEmpty())) {
            mindrake.func_110163_bv();
            mindrake.func_70903_f(true);
            TameableUtil.setOwnerByUsername(mindrake, thrower);
          }
        }
        ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_POP, mindrake, 1.0D, 1.0D, 16);
        entityItem.field_70170_p.func_72838_d(mindrake);
      }
    }
  }
  
  @SubscribeEvent(priority=EventPriority.HIGH)
  public void onPlayerDrops(PlayerDropsEvent event) {
    int ticks;
    if ((!entityPlayer.field_70170_p.field_72995_K) && (!event.isCanceled()) && 
      (ExtendedPlayer.get(entityPlayer).isVampire())) {
      ticks = TimeUtil.minsToTicks(MathHelper.func_76125_a(instancevampireDeathItemKeepAliveMins, 5, 30));
      
      for (EntityItem item : drops) {
        lifespan = ticks;
      }
    }
  }
  

  @SubscribeEvent(priority=EventPriority.HIGH)
  public void onEntityInteract(EntityInteractEvent event)
  {
    PotionEffect effect = entityPlayer.func_70660_b(PotionsPARALYSED);
    if ((effect != null) && (effect.func_76458_c() >= 4)) {
      event.setCanceled(true);
      return;
    }
    

    ExtendedPlayer playerEx = ExtendedPlayer.get(entityPlayer);
    ExtendedPlayer.VampirePower power = playerEx.getSelectedVampirePower();
    if (power != ExtendedPlayer.VampirePower.NONE) {
      if ((power == ExtendedPlayer.VampirePower.DRINK) && ((target instanceof EntityLivingBase))) {
        if (!entityPlayer.field_70170_p.field_72995_K) {
          float RANGE = ((EntityLivingBase)target).func_70644_a(PotionsPARALYSED) ? 2.1F : 1.3F;
          
          if (target.func_70092_e(entityPlayer.field_70165_t, target.field_70163_u, entityPlayer.field_70161_v) <= RANGE * RANGE)
          {

            int drinkAmount = ItemVampireClothes.isDrinkBoostActive(entityPlayer) ? 15 : 10;
            if (CreatureUtil.isWerewolf(target, true)) {
              entityPlayer.func_70097_a(new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), entityPlayer), 4.0F);
              

              ParticleEffect.FLAME.send(SoundEffect.WITCHERY_RANDOM_DRINK, entityPlayer.field_70170_p, target.field_70165_t, target.field_70163_u + target.field_70131_O * 0.8D, target.field_70161_v, 0.5D, 0.2D, 16);

            }
            else if ((target instanceof EntityVillageGuard)) {
              EntityVillageGuard target = (EntityVillageGuard)target;
              playerEx.increaseBloodPower(target.takeBlood(playerEx.getCreatureType() == TransformCreature.NONE ? drinkAmount : 2, entityPlayer));
              

              ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, entityPlayer.field_70170_p, field_70165_t, field_70163_u + field_70131_O * 0.8D, field_70161_v, 0.5D, 0.2D, 16);
              

              checkForBloodDrinkingWitnesses(entityPlayer, target);
            } else if ((target instanceof EntityVillager)) {
              EntityVillager target = (EntityVillager)target;
              ExtendedVillager villagerEx = ExtendedVillager.get(target);
              playerEx.increaseBloodPower(villagerEx.takeBlood(playerEx.getCreatureType() == TransformCreature.NONE ? drinkAmount : 2, entityPlayer));
              

              ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, entityPlayer.field_70170_p, field_70165_t, field_70163_u + field_70131_O * 0.8D, field_70161_v, 0.5D, 0.2D, 16);
              

              checkForBloodDrinkingWitnesses(entityPlayer, target);
              if (playerEx.getVampireLevel() == 2) {
                if ((instanceallowVampireQuests) && (villagerEx.getBlood() >= 250) && (villagerEx.getBlood() <= 280))
                {
                  if (playerEx.getVampireQuestCounter() >= 5) {
                    playerEx.increaseVampireLevel();
                  } else {
                    SoundEffect.NOTE_PLING.playOnlyTo(entityPlayer, 1.0F, 1.0F);
                    playerEx.increaseVampireQuestCounter();
                  }
                } else if (villagerEx.getBlood() < 240) {
                  playerEx.resetVampireQuestCounter();
                }
              } else if ((playerEx.getVampireLevel() == 8) && (playerEx.canIncreaseVampireLevel()) && 
                (villagerIsInCage(target))) {
                if ((villagerEx.getBlood() >= 250) && (villagerEx.getBlood() <= 280)) {
                  if (playerEx.getVampireQuestCounter() >= 5) {
                    playerEx.increaseVampireLevel();
                  } else {
                    SoundEffect.NOTE_PLING.playOnlyTo(entityPlayer, 1.0F, 1.0F);
                    playerEx.increaseVampireQuestCounter();
                  }
                } else if (villagerEx.getBlood() < 240) {
                  playerEx.resetVampireQuestCounter();
                }
              }
            }
            else if ((target instanceof EntityPlayer)) {
              EntityPlayer target = (EntityPlayer)target;
              playerEx.increaseBloodPower(ExtendedPlayer.get(target).takeHumanBlood(playerEx.getCreatureType() == TransformCreature.NONE ? drinkAmount : 2, entityPlayer));
              

              ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, entityPlayer.field_70170_p, field_70165_t, field_70163_u + field_70131_O * 0.8D, field_70161_v, 0.5D, 0.2D, 16);

            }
            else if ((target instanceof net.minecraft.entity.passive.IAnimals)) {
              EntityLivingBase target = (EntityLivingBase)target;
              target.func_70097_a(new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), entityPlayer), 1.0F);
              
              playerEx.increaseBloodPower(2, (int)Math.ceil(playerEx.getMaxBloodPower() * 0.25F));
              ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, entityPlayer.field_70170_p, field_70165_t, field_70163_u + field_70131_O * 0.8D, field_70161_v, 0.5D, 0.2D, 16);
            }
          }
          

          event.setCanceled(true);
        }
      } else if (power == ExtendedPlayer.VampirePower.MESMERIZE) {
        if (!entityPlayer.field_70170_p.field_72995_K) {
          if ((entityPlayer.func_70093_af()) && (playerEx.getVampireLevel() >= 2)) {
            playerEx.toggleVampireVision();
          } else if ((playerEx.getCreatureType() == TransformCreature.NONE) && (playerEx.getVampireLevel() >= 2))
          {
            if ((((target instanceof EntityVillager)) && (!(target instanceof EntityVillagerWere))) || ((target instanceof EntityPlayer)) || ((target instanceof EntityVillageGuard)))
            {

              EntityLivingBase victim = (EntityLivingBase)target;
              if (!victim.func_70644_a(PotionsPARALYSED)) {
                if (playerEx.decreaseBloodPower(MESMERIZEINITIAL_COST, true))
                {
                  victim.func_70690_d(new PotionEffect(PotionsPARALYSED.field_76415_H, TimeUtil.secsToTicks(5 + playerEx.getVampireLevel() / 2 + Math.max(0, (playerEx.getVampireLevel() - 4) / 2) + (ItemVampireClothes.isMezmeriseBoostActive(entityPlayer) ? 3 : 0)), playerEx.getVampireLevel() >= 8 ? 5 : 4));
                  






                  SoundEffect.WITCHERY_RANDOM_HYPNOSIS.playAtPlayer(entity.field_70170_p, entityPlayer, 0.5F, 1.0F);
                }
                else {
                  SoundEffect.NOTE_SNARE.playOnlyTo(entityPlayer, 1.0F, 0.5F);
                }
              } else {
                SoundEffect.NOTE_SNARE.playOnlyTo(entityPlayer, 1.0F, 0.5F);
              }
            } else {
              SoundEffect.NOTE_SNARE.playOnlyTo(entityPlayer, 1.0F, 0.5F);
            }
          } else {
            SoundEffect.NOTE_SNARE.playOnlyTo(entityPlayer, 1.0F, 0.5F);
          }
          event.setCanceled(true);
        }
      } else {
        event.setCanceled(true);
      }
    }
    
    if (event.isCanceled()) {
      return;
    }
    

    if ((target != null) && (!target.field_70170_p.field_72995_K) && ((target instanceof EntityLiving)) && 
      (com.emoniph.witchery.brewing.potions.PotionEnslaved.isMobEnslavedBy((EntityLiving)target, entityPlayer))) {
      EntityPlayer player = entityPlayer;
      EntityLiving creature = (EntityLiving)target;
      ItemStack heldObject = player.func_70694_bm();
      if ((ItemsGENERIC.itemGraveyardDust.isMatch(heldObject)) && ((creature instanceof EntitySummonedUndead)))
      {
        float maxHealth = creature.func_110138_aP() + 2.0F;
        if (maxHealth <= 50.0F) {
          IAttributeInstance attribute = creature.func_110148_a(SharedMonsterAttributes.field_111267_a);
          
          if (attribute != null) {
            attribute.func_111128_a(maxHealth);
            creature.func_70606_j(maxHealth);
            creature.func_110163_bv();
            Witchery.packetPipeline.sendToAllAround(new com.emoniph.witchery.network.PacketParticles(ParticleEffect.INSTANT_SPELL, SoundEffect.MOB_SILVERFISH_KILL, creature, 0.5D, 1.0D), com.emoniph.witchery.util.TargetPointUtil.from(creature, 16.0D));
            

            if (!field_71075_bZ.field_75098_d) {
              field_77994_a -= 1;
              if ((player instanceof EntityPlayerMP)) {
                ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
              }
            }
          }
        }
      } else if ((heldObject != null) && (((creature instanceof EntityZombie)) || ((creature instanceof EntityPigZombie)) || ((creature instanceof EntitySkeleton))))
      {
        if ((heldObject.func_77973_b() instanceof ItemArmor)) {
          ItemArmor armor = (ItemArmor)heldObject.func_77973_b();
          if (creature.func_71124_b(4 - field_77881_a) == null) {
            creature.func_70062_b(4 - field_77881_a, heldObject.func_77979_a(1));
            creature.func_110163_bv();
            if ((player instanceof EntityPlayerMP)) {
              ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
            }
          }
        } else if (((heldObject.func_77973_b() instanceof ItemSword)) && 
          (creature.func_71124_b(0) == null)) {
          creature.func_70062_b(0, heldObject.func_77979_a(1));
          creature.func_110163_bv();
          if ((player instanceof EntityPlayerMP)) {
            ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
          }
        }
      }
    }
    



    if ((target != null) && (!target.field_70170_p.field_72995_K) && ((target instanceof EntityVillager))) {
      EntityVillager villager = (EntityVillager)target;
      ItemStack heldObject = entityPlayer.func_70694_bm();
      if ((!villager.func_70631_g_()) && (heldObject != null) && (heldObject.func_77973_b() == Items.field_151027_R) && (entityPlayer.func_70093_af()))
      {
        Village village = field_70954_d;
        if (village != null) {
          int rep = village.func_82684_a(entityPlayer.func_70005_c_());
          if (rep >= 10) {
            if (village.func_75562_e() > 8) {
              List list = entity.field_70170_p.func_72872_a(EntityVillageGuard.class, AxisAlignedBB.func_72330_a(func_75577_afield_71574_a - village.func_75568_b(), func_75577_afield_71572_b - 4, func_75577_afield_71573_c - village.func_75568_b(), func_75577_afield_71574_a + village.func_75568_b(), func_75577_afield_71572_b + 4, func_75577_afield_71573_c + village.func_75568_b()));
              






              int numGuards = list.size();
              if (numGuards < MathHelper.func_76128_c(village.func_75562_e() * 0.25D)) {
                int villagerNumTrades = field_70963_i == null ? 1 : field_70963_i.size();
                
                if ((!CreatureUtil.isWerewolf(target, true)) && (target.field_70170_p.field_73012_v.nextInt(villagerNumTrades * 2 + 1) == 0))
                {
                  villager.func_85030_a("mob.villager.yew", 1.0F, (field_70170_p.field_73012_v.nextFloat() - field_70170_p.field_73012_v.nextFloat()) * 0.2F + 1.0F);
                  
                  ChatUtil.sendTranslated(EnumChatFormatting.GREEN, entityPlayer, "witchery.village.villageracceptsguardduty", new Object[0]);
                  
                  EntityVillageGuard.createFrom(villager);
                } else {
                  ChatUtil.sendTranslated(EnumChatFormatting.RED, entityPlayer, "witchery.village.villagerrefusesguardduty", new Object[0]);
                  
                  villager.func_85030_a("mob.villager.no", 1.0F, (field_70170_p.field_73012_v.nextFloat() - field_70170_p.field_73012_v.nextFloat()) * 0.2F + 1.0F);
                }
              }
              else {
                ChatUtil.sendTranslated(EnumChatFormatting.BLUE, entityPlayer, "witchery.village.toomanyguards", new Object[0]);
                
                SoundEffect.NOTE_SNARE.playOnlyTo(entityPlayer);
              }
            } else {
              ChatUtil.sendTranslated(EnumChatFormatting.BLUE, entityPlayer, "witchery.village.villagetoosmall", new Object[0]);
              
              SoundEffect.NOTE_SNARE.playOnlyTo(entityPlayer);
            }
          } else {
            ChatUtil.sendTranslated(EnumChatFormatting.BLUE, entityPlayer, "witchery.village.reptoolow", new Object[0]);
            
            SoundEffect.NOTE_SNARE.playOnlyTo(entityPlayer);
          }
        }
      }
    }
    

    if ((!entity.field_70170_p.field_72995_K) && (target != null) && ((target instanceof EntityWolf))) {
      EntityWolf wolf = (EntityWolf)target;
      if ((playerEx.getWerewolfLevel() == 7) && (playerEx.getWolfmanQuestState() == ExtendedPlayer.QuestState.STARTED) && (playerEx.getCreatureType() == TransformCreature.WOLF) && (!wolf.func_70909_n()) && (!wolf.func_70919_bu()))
      {
        if (field_70170_p.field_73012_v.nextInt(3) == 0) {
          wolf.func_70903_f(true);
          wolf.func_70778_a((net.minecraft.pathfinding.PathEntity)null);
          wolf.func_70624_b((EntityLivingBase)null);
          wolf.func_70907_r().func_75270_a(true);
          wolf.func_70606_j(20.0F);
          wolf.func_152115_b(entityPlayer.func_110124_au().toString());
          playTameEffect(wolf, true);
          field_70170_p.func_72960_a(wolf, (byte)7);
          playerEx.increaseWolfmanQuestCounter();
        } else {
          playTameEffect(wolf, false);
          field_70170_p.func_72960_a(wolf, (byte)6);
          if (field_70170_p.field_73012_v.nextInt(10) == 0) {
            wolf.func_70916_h(true);
            wolf.func_70624_b(entityPlayer);
          }
        }
      }
    }
    
    ItemStack heldStack = entityPlayer.func_70694_bm();
    if (heldStack != null)
    {
      if (heldStack.func_77973_b() == ItemsTAGLOCK_KIT) {
        ItemsTAGLOCK_KIT.onEntityInteract(entityPlayer.field_70170_p, entityPlayer, heldStack, event);
        
        if (event.isCanceled()) {
          return;
        }
      }
      

      if (heldStack.func_77973_b() == ItemsBLOOD_GOBLET) {
        ItemsBLOOD_GOBLET.onEntityInteract(entityPlayer.field_70170_p, entityPlayer, heldStack, event);
        
        if (event.isCanceled()) {
          return;
        }
      }
      

      if ((ItemsGENERIC.itemWoodenStake.isMatch(heldStack)) && (instanceallowStakingVampires))
      {
        if ((target instanceof EntityPlayer)) {
          EntityPlayer victim = (EntityPlayer)target;
          if ((ExtendedPlayer.get(victim).isVampire()) && (field_71083_bS)) {
            ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, field_70170_p, target.field_70165_t, target.field_70163_u, target.field_70161_v, target.field_70130_N, target.field_70131_O, 16);
            

            EntityUtil.instantDeath(victim, entityPlayer);
            if (!entityPlayer.field_71075_bZ.field_75098_d) {
              field_77994_a -= 1;
            }
            event.setCanceled(true);
            return;
          }
        }
      }
    }
  }
  
  private boolean villagerIsInCage(EntityVillager target) {
    int ogX = MathHelper.func_76128_c(field_70165_t);
    int ogY = MathHelper.func_76128_c(field_70163_u);
    int ogZ = MathHelper.func_76128_c(field_70161_v);
    
    if (isCaged(field_70170_p, ogX, ogY, ogZ))
      return true;
    if (isCaged(field_70170_p, ogX + 1, ogY, ogZ))
      return true;
    if (isCaged(field_70170_p, ogX, ogY, ogZ + 1))
      return true;
    if (isCaged(field_70170_p, ogX - 1, ogY, ogZ))
      return true;
    if (isCaged(field_70170_p, ogX, ogY, ogZ - 1))
      return true;
    if (isCaged(field_70170_p, ogX + 1, ogY, ogZ + 1))
      return true;
    if (isCaged(field_70170_p, ogX + 1, ogY, ogZ - 1))
      return true;
    if (isCaged(field_70170_p, ogX - 1, ogY, ogZ + 1))
      return true;
    if (isCaged(field_70170_p, ogX - 1, ogY, ogZ - 1)) {
      return true;
    }
    return false;
  }
  
  private boolean isCaged(World world, int x, int y, int z) {
    int count = 0;
    Block bars = Blocks.field_150411_aY;
    count += (world.func_147439_a(x + 1, y, z) == bars ? 1 : 0);
    count += (world.func_147439_a(x, y, z + 1) == bars ? 1 : 0);
    count += (world.func_147439_a(x - 1, y, z) == bars ? 1 : 0);
    count += (world.func_147439_a(x, y, z - 1) == bars ? 1 : 0);
    count += (world.func_147439_a(x + 1, y, z + 1) == bars ? 1 : 0);
    count += (world.func_147439_a(x - 1, y, z + 1) == bars ? 1 : 0);
    count += (world.func_147439_a(x + 1, y, z - 1) == bars ? 1 : 0);
    count += (world.func_147439_a(x - 1, y, z - 1) == bars ? 1 : 0);
    
    y++;
    
    count += (world.func_147439_a(x + 1, y, z) == bars ? 1 : 0);
    count += (world.func_147439_a(x, y, z + 1) == bars ? 1 : 0);
    count += (world.func_147439_a(x - 1, y, z) == bars ? 1 : 0);
    count += (world.func_147439_a(x, y, z - 1) == bars ? 1 : 0);
    count += (world.func_147439_a(x + 1, y, z + 1) == bars ? 1 : 0);
    count += (world.func_147439_a(x - 1, y, z + 1) == bars ? 1 : 0);
    count += (world.func_147439_a(x + 1, y, z - 1) == bars ? 1 : 0);
    count += (world.func_147439_a(x - 1, y, z - 1) == bars ? 1 : 0);
    
    if (count < 15) {
      return false;
    }
    
    count = 0;
    
    y++;
    
    count += (!BlockUtil.isReplaceableBlock(world, x + 1, y, z) ? 1 : 0);
    count += (!BlockUtil.isReplaceableBlock(world, x, y, z + 1) ? 1 : 0);
    count += (!BlockUtil.isReplaceableBlock(world, x - 1, y, z) ? 1 : 0);
    count += (!BlockUtil.isReplaceableBlock(world, x, y, z - 1) ? 1 : 0);
    count += (!BlockUtil.isReplaceableBlock(world, x + 1, y, z + 1) ? 1 : 0);
    count += (!BlockUtil.isReplaceableBlock(world, x - 1, y, z + 1) ? 1 : 0);
    count += (!BlockUtil.isReplaceableBlock(world, x + 1, y, z - 1) ? 1 : 0);
    count += (!BlockUtil.isReplaceableBlock(world, x - 1, y, z - 1) ? 1 : 0);
    count += (!BlockUtil.isReplaceableBlock(world, x, y, z) ? 1 : 0);
    
    if (count < 9) {
      return false;
    }
    
    return true;
  }
  
  private void checkForBloodDrinkingWitnesses(EntityPlayer player, EntityLivingBase victim) {
    AxisAlignedBB bounds = field_70121_D.func_72314_b(16.0D, 8.0D, 16.0D);
    List<EntityVillageGuard> guards = field_70170_p.func_72872_a(EntityVillageGuard.class, bounds);
    for (EntityVillageGuard guard : guards) {
      if ((!guard.func_70644_a(PotionsPARALYSED)) && (guard.func_70635_at().func_75522_a(victim))) {
        guard.func_70624_b(player);
      }
    }
  }
  
  @SubscribeEvent
  public void onPlayerInteract(PlayerInteractEvent event) {
    PotionEffect effect = entityPlayer.func_70660_b(PotionsPARALYSED);
    if ((effect != null) && (effect.func_76458_c() >= 4)) {
      event.setCanceled(true);
      return;
    }
    
    ExtendedPlayer playerEx = ExtendedPlayer.get(entityPlayer);
    if (playerEx.getSelectedVampirePower() != ExtendedPlayer.VampirePower.NONE) {
      if ((action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) || (action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)) {
        switch (3.$SwitchMap$com$emoniph$witchery$common$ExtendedPlayer$VampirePower[playerEx.getSelectedVampirePower().ordinal()]) {
        case 1: 
        case 2: 
        case 3: 
        case 4: 
          Witchery.packetPipeline.sendToServer(new com.emoniph.witchery.network.PacketSelectPlayerAbility(playerEx, true));
          break;
        }
        
        
        event.setCanceled(true);
      }
    }
    else if (entityPlayer.field_70170_p.field_72995_K) {
      if (((action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) || (action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)) && 
        (entityPlayer.field_70125_A == -90.0F) && (entityPlayer.func_70093_af())) {
        Witchery.packetPipeline.sendToServer(new com.emoniph.witchery.network.PacketHowl());
      }
      
    }
    else if ((action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) && (!entityPlayer.field_71075_bZ.field_75098_d))
    {

      if ((playerEx.isVampire()) && (world.func_147439_a(x, y, z) == BlocksGARLIC_GARLAND))
      {
        entityPlayer.func_70015_d(1);
        event.setCanceled(true);
      } else if ((playerEx.getCreatureType() == TransformCreature.WOLF) && (playerEx.getWerewolfLevel() >= 3) && (entityPlayer.func_70093_af()))
      {
        Block block = world.func_147439_a(x, y, z);
        if ((block == Blocks.field_150349_c) || (block == Blocks.field_150354_m) || (block == Blocks.field_150346_d) || (block == Blocks.field_150391_bh) || (block == Blocks.field_150351_n))
        {

          EntityAIDigBlocks.tryHarvestBlock(world, x, y, z, entityPlayer, entityPlayer);
          
          event.setCanceled(true);
        }
      } else if ((playerEx.getVampireLevel() >= 6) && (playerEx.getCreatureType() == TransformCreature.NONE) && (entityPlayer.func_70093_af()) && ((entityPlayer.func_70694_bm() == null) || (!entityPlayer.func_70694_bm().func_77973_b().func_150897_b(Blocks.field_150348_b))) && (entityPlayer.func_71024_bL().func_75116_a() > 0))
      {




        Block block = world.func_147439_a(x, y, z);
        if ((block == Blocks.field_150348_b) || (block == Blocks.field_150424_aL) || (block == Blocks.field_150347_e)) {
          EntityAIDigBlocks.tryHarvestBlock(world, x, y, z, entityPlayer, entityPlayer);
          
          entityPlayer.func_71020_j(10.0F);
          event.setCanceled(true);
        }
      }
    }
  }
  

  private void playTameEffect(EntityTameable entity, boolean tamed)
  {
    String s = "heart";
    
    if (!tamed) {
      s = "smoke";
    }
    
    for (int i = 0; i < 7; i++) {
      double d0 = field_70170_p.field_73012_v.nextGaussian() * 0.02D;
      double d1 = field_70170_p.field_73012_v.nextGaussian() * 0.02D;
      double d2 = field_70170_p.field_73012_v.nextGaussian() * 0.02D;
      field_70170_p.func_72869_a(s, field_70165_t + field_70170_p.field_73012_v.nextFloat() * field_70130_N * 2.0F - field_70130_N, field_70163_u + 0.5D + field_70170_p.field_73012_v.nextFloat() * field_70131_O, field_70161_v + field_70170_p.field_73012_v.nextFloat() * field_70130_N * 2.0F - field_70130_N, d0, d1, d2);
    }
  }
  



  @SubscribeEvent
  public void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
  {
    if ((!entity.field_70170_p.field_72995_K) && ((entity instanceof EntityPlayer))) {
      EntityPlayer player = (EntityPlayer)entity;
      ExtendedPlayer playerEx = ExtendedPlayer.get(player);
      
      Shapeshift.INSTANCE.updatePlayerState(player, playerEx);
      playerEx.tick();
      if (playerEx.isVampire()) {
        int prevHunger = func_71024_bLfield_75124_e;
        int hunger = player.func_71024_bL().func_75116_a();
        if (prevHunger < hunger) {
          player.func_71024_bL().func_75122_a(-player.func_71024_bL().func_75116_a(), 0.0F);
        }
      }
      
      if (entity.field_70173_aa % 40 == 1) {
        if (playerEx.getWerewolfLevel() > 0) {
          boolean isFullMoon = CreatureUtil.isFullMoon(field_70170_p);
          
          switch (3.$SwitchMap$com$emoniph$witchery$util$TransformCreature[playerEx.getCreatureType().ordinal()]) {
          case 1: 
          case 2: 
            boolean isWolfman = playerEx.getCreatureType() == TransformCreature.WOLFMAN;
            if ((!isFullMoon) && (!field_71071_by.func_146028_b(ItemsMOON_CHARM)) && (!ItemMoonCharm.isWolfsbaneActive(player, playerEx)))
            {
              Shapeshift.INSTANCE.shiftTo(player, TransformCreature.NONE);
              ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_FIZZ, player, 1.5D, 1.5D, 16);
            } else {
              updateWerewolfEffects(player, isWolfman);
            }
            break;
          case 3: 
            if ((isFullMoon) && (!field_71071_by.func_146028_b(ItemsMOON_CHARM)) && (!ItemMoonCharm.isWolfsbaneActive(player, playerEx)))
            {
              Shapeshift.INSTANCE.shiftTo(player, TransformCreature.WOLF);
              ParticleEffect.EXPLODE.send(SoundEffect.WITCHERY_MOB_WOLFMAN_HOWL, player, 1.5D, 1.5D, 16);
              
              updateWerewolfEffects(player, false);
            }
            
            break;
          }
          
        }
        
        if (playerEx.isVampire()) {
          if (player.func_70090_H()) {
            player.func_70050_g(300);
          }
          
          if ((playerEx.getCreatureType() == TransformCreature.BAT) && 
            (!playerEx.decreaseBloodPower(BATUPKEEP_COST, true))) {
            Shapeshift.INSTANCE.shiftTo(player, TransformCreature.NONE);
          }
          

          if ((playerEx.getVampireLevel() == 3) && (!field_70170_p.func_72935_r())) {
            if ((playerEx.getVampireQuestCounter() >= 300) || ((playerEx.getVampireQuestCounter() >= 10) && (field_71075_bZ.field_75098_d)))
            {
              if (playerEx.canIncreaseVampireLevel()) {
                playerEx.increaseVampireLevel();
              }
            }
            else if (instanceallowVampireQuests) {
              playerEx.increaseVampireQuestCounter();
            }
          }
          

          if ((playerEx.getVampireLevel() == 7) && (playerEx.canIncreaseVampireLevel())) {
            Village closestVillage = field_70170_p.field_72982_D.func_75550_a(MathHelper.func_76128_c(field_70165_t), MathHelper.func_76128_c(field_70163_u), MathHelper.func_76128_c(field_70161_v), 32);
            

            if ((closestVillage != null) && (playerEx.storeVampireQuestChunk(func_75577_afield_71574_a >> 4, func_75577_afield_71573_c >> 4)))
            {

              if (playerEx.getVampireQuestCounter() >= 3) {
                playerEx.increaseVampireLevel();
              } else {
                playerEx.increaseVampireQuestCounter();
                SoundEffect.NOTE_PLING.playOnlyTo(player, 1.0F, 1.0F);
              }
            }
          }
          
          if (playerEx.isVampireVisionActive()) {
            player.func_70690_d(new PotionEffect(field_76439_rfield_76415_H, 400, 0, true));
          }
          
          if (player.func_70644_a(Potion.field_76436_u)) {
            player.func_82170_o(field_76436_ufield_76415_H);
          }
          
          if ((player.func_70027_ad()) && (player.func_70644_a(Potion.field_76426_n))) {
            player.func_70097_a(EntityUtil.DamageSourceVampireFire.SOURCE, 2.0F);
          }
          
          while ((player.func_71024_bL().func_75116_a() < 20) && (playerEx.decreaseBloodPower(5, true))) {
            player.func_71024_bL().func_75122_a(1, 4.0F);
          }
          
          if ((playerEx.getBloodPower() == 0) && (player.func_71024_bL().func_75116_a() == 0)) {
            player.func_70690_d(new PotionEffect(field_76437_tfield_76415_H, TimeUtil.secsToTicks(10), 8, true));
            
            player.func_70690_d(new PotionEffect(field_76421_dfield_76415_H, TimeUtil.secsToTicks(10), 1, true));
            
            player.func_70690_d(new PotionEffect(field_76419_ffield_76415_H, TimeUtil.secsToTicks(10), 1, true));
          }
          

          if ((CreatureUtil.isInSunlight(player)) && (!field_71075_bZ.field_75098_d)) {
            if ((playerEx.getBloodPower() == 0) && (field_70173_aa > 400)) {
              EntityUtil.instantDeath(player, null);
            }
            if (playerEx.getVampireLevel() >= 5) {
              playerEx.decreaseBloodPower(60, false);
              player.func_70690_d(new PotionEffect(field_76437_tfield_76415_H, TimeUtil.secsToTicks(10), 3, false));
              
              player.func_70690_d(new PotionEffect(field_76421_dfield_76415_H, TimeUtil.secsToTicks(10), 0, true));
              
              player.func_70690_d(new PotionEffect(field_76419_ffield_76415_H, TimeUtil.secsToTicks(10), 0, true));
            }
            else {
              playerEx.setBloodPower(0);
            }
            
            if (playerEx.getBloodPower() == 0) {
              player.func_70015_d(5);
            }
          }
        }
        else {
          playerEx.giveHumanBlood(2);
        }
      }
    }
  }
  
  public static void updateWerewolfEffects(EntityPlayer player, boolean isWolfman) {
    player.func_70690_d(new PotionEffect(field_76439_rfield_76415_H, 400, 0, true));
    if (player.func_70644_a(Potion.field_76436_u)) {
      player.func_82170_o(field_76436_ufield_76415_H);
    }
    

    for (int slot = isWolfman ? 0 : 1; slot <= 4; slot++) {
      ItemStack stack = player.func_71124_b(slot);
      if ((stack != null) && (stack.func_77973_b() != ItemsMOON_CHARM) && (
        (field_71070_bA == null) || (field_71070_bA.field_75152_c == 0) || (slot != 0))) {
        player.func_70099_a(stack, 1.0F);
        player.func_70062_b(slot, null);
      }
    }
  }
  
  @SubscribeEvent
  public void onLivingFall(LivingFallEvent event)
  {
    if ((!entity.field_70170_p.field_72995_K) && ((entity instanceof EntityPlayer))) {
      distance = Shapeshift.INSTANCE.updateFallState((EntityPlayer)entity, distance);
    }
  }
  
  @SubscribeEvent
  public void onHarvestDrops(BlockEvent.HarvestDropsEvent event) {
    if ((!world.field_72995_K) && (harvester != null) && (!event.isCanceled())) {
      ExtendedPlayer playerEx = ExtendedPlayer.get(harvester);
      Shapeshift.INSTANCE.processDigging(event, harvester, playerEx);
    }
  }
  
  @SubscribeEvent(priority=EventPriority.HIGHEST)
  public void onLivingHurt(LivingHurtEvent event) {
    if ((!entityLiving.field_70170_p.field_72995_K) && (!event.isCanceled()))
    {
      checkForChargeDamage(event);
      
      if ((entityLiving instanceof EntityPlayer)) {
        EntityPlayer player = (EntityPlayer)entityLiving;
        float playerHealth = player.func_110143_aJ();
        
        ExtendedPlayer playerEx = ExtendedPlayer.get(player);
        if ((source == DamageSource.field_76369_e) && (playerEx.isVampire())) {
          event.setCanceled(true);
          return;
        }
        


        boolean wolfForm = (playerEx.getWerewolfLevel() > 0) && ((playerEx.getCreatureType() == TransformCreature.WOLF) || (playerEx.getCreatureType() == TransformCreature.WOLFMAN));
        

        if ((wolfForm) && (source != DamageSource.field_76380_i) && (source != DamageSource.field_76368_d) && (source != DamageSource.field_76369_e) && (source != DamageSource.field_76379_h))
        {
          if (!source.func_76347_k()) {
            float damageReduction = Shapeshift.INSTANCE.getResistance(player, playerEx);
            ammount = Math.max(0.0F, ammount - damageReduction);
          }
          
          if (!CreatureUtil.isWerewolf(source.func_76364_f())) {
            if (!CreatureUtil.isSilverDamage(source)) {
              ammount = Math.max(Math.min(ammount, Shapeshift.INSTANCE.getDamageCap(player, playerEx)), 0.5F);
            }
            else
            {
              ammount += 5.0F;
            }
          }
          
          if (ammount <= 0.0F) {
            event.setCanceled(true);
            return;
          }
        }
        

        if ((ItemDeathsClothes.isFullSetWorn(player)) && (player.func_70694_bm() != null) && (player.func_70694_bm().func_77973_b() == ItemsDEATH_HAND))
        {
          ammount = Math.min(ammount, 7.0F);
        }
        



        float healthAfterDamage = EntityUtil.getHealthAfterDamage(event, playerHealth, player);
        

        if ((field_71093_bK == instancedimensionDreamID) || (WorldProviderDreamWorld.getPlayerIsGhost(player)))
        {
          if ((healthAfterDamage <= 0.0F) && (!field_71075_bZ.field_75098_d)) {
            event.setCanceled(true);
            event.setResult(cpw.mods.fml.common.eventhandler.Event.Result.DENY);
            WorldProviderDreamWorld.setPlayerMustAwaken(player, true);
            return;
          }
        }
        

        dropItemsOnHit(player);
        
        boolean ignoreProtection = (wolfForm) || (((source instanceof BoltDamageSource)) && (source).isPoweredDraining));
        
        boolean hasHunterSet = ItemHunterClothes.isFullSetWorn(player, false);
        

        if ((hasHunterSet) && (source.func_82725_o()) && (field_70170_p.field_73012_v.nextDouble() < 0.25D)) {
          event.setCanceled(true);
          return;
        }
        
        if ((((source instanceof EntityDamageSource)) || (source.func_94541_c())) && 
          (!ignoreProtection))
        {
          ItemStack hat = player.func_71124_b(4);
          if ((hat != null) && (hat.func_77973_b() == ItemsBABAS_HAT) && 
            (field_71093_bK != instancedimensionTormentID)) {
            int TELEPORT_COST = 5;
            double TELEPORT_CHANCE = 0.25D;
            int TELEPORT_DISTANCE = 6;
            if ((field_70170_p.field_73012_v.nextDouble() < 0.25D) && (Infusion.aquireEnergy(field_70170_p, player, 5, true)))
            {
              BlockVoidBramble.teleportRandomly(field_70170_p, MathHelper.func_76128_c(field_70165_t), MathHelper.func_76128_c(field_70163_u), MathHelper.func_76128_c(field_70161_v), player, 6);
              


              event.setCanceled(true);
              return;
            }
          }
        }
        


        if ((source instanceof EntityDamageSource))
        {
          EntityDamageSource entitySource = (EntityDamageSource)source;
          

          ItemStack belt = player.func_71124_b(2);
          if ((belt != null) && (belt.func_77973_b() == ItemsBARK_BELT) && (!CreatureUtil.isWoodenDamage(source)))
          {
            int currentLevel = Math.min(ItemsBARK_BELT.getChargeLevel(belt), ItemsBARK_BELT.getMaxChargeLevel(player));
            
            if (currentLevel > 0) {
              World world = field_70170_p;
              Random rand = field_73012_v;
              int cost = (currentLevel > 1) && (rand.nextDouble() < 0.25D) ? 2 : 1;
              ItemsBARK_BELT.setChargeLevel(belt, Math.max(currentLevel - cost, 0));
              event.setCanceled(true);
              
              for (int i = 0; i < cost; i++) {
                double dx = 1.0D * (rand.nextInt(2) == 0 ? -1 : 1);
                double dy = 1.0D * (rand.nextInt(2) == 0 ? -1 : 1);
                EntityItem item = new EntityItem(world, field_70165_t + dx, field_70163_u + 1.5D, field_70161_v + dy, new ItemStack(Items.field_151055_y));
                
                field_145804_b = 60;
                lifespan = 60;
                
                world.func_72838_d(item);
              }
              return;
            }
          }
          

          double MOB_SPAWN_CHANCE = 0.25D;
          if ((player.func_70694_bm() != null) && (player.func_70694_bm().func_77973_b() == ItemsHUNTSMANS_SPEAR) && (player.func_70632_aY()) && (field_70170_p.field_73012_v.nextDouble() < 0.25D) && (entitySource.func_76346_g() != null) && ((entitySource.func_76346_g() instanceof EntityLivingBase)))
          {



            EntityLivingBase living = (EntityLivingBase)entitySource.func_76346_g();
            if (living.func_70089_S()) {
              EntityWolf wolf = new EntityWolf(field_70170_p);
              wolf.func_70012_b(field_70165_t, field_70163_u, field_70161_v, field_70125_A, field_70759_as);
              
              wolf.func_70624_b(living);
              wolf.func_70784_b(living);
              wolf.func_70916_h(true);
              wolf.func_70690_d(new PotionEffect(field_82731_vfield_76415_H, 12000, 1));
              field_70170_p.func_72838_d(wolf);
            }
          }
          

          boolean louseUsed = false;
          for (int i = 0; i < InventoryPlayer.func_70451_h(); i++) {
            ItemStack stack = field_71071_by.func_70301_a(i);
            if ((stack != null) && (stack.func_77973_b() == ItemsPARASYTIC_LOUSE) && (stack.func_77960_j() > 0))
            {
              List list = Items.field_151068_bn.func_77834_f(stack.func_77960_j());
              
              if ((list != null) && (!list.isEmpty())) {
                PotionEffect effect = new PotionEffect((PotionEffect)list.get(0));
                if ((isPotionAggressive(effect.func_76456_a())) && (source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityLivingBase)))
                {
                  ((EntityLivingBase)source.func_76346_g()).func_70690_d(effect);
                } else { if (effect.func_76456_a() != field_76428_lfield_76415_H) {
                    continue;
                  }
                  player.func_70690_d(effect);
                }
                
                player.func_70097_a(DamageSource.field_76376_m, 1.0F);
                stack.func_77964_b(0);
                louseUsed = true;
                break;
              } else {
                stack.func_77964_b(0);
              }
            }
          }
          

          if ((!louseUsed) && (ItemsBITING_BELT.isBeltWorn(player))) {
            ItemStack stack = field_71071_by.func_70440_f(1);
            if ((stack != null) && (stack.func_77942_o())) {
              boolean done = false;
              if (stack.func_77978_p().func_74764_b("WITCPotion")) {
                int potion = stack.func_77978_p().func_74762_e("WITCPotion");
                
                List list = Items.field_151068_bn.func_77834_f(potion);
                if ((list != null) && (!list.isEmpty())) {
                  PotionEffect effect = new PotionEffect((PotionEffect)list.get(0));
                  if ((!player.func_82165_m(effect.func_76456_a())) && (effect.func_76456_a() != field_76428_lfield_76415_H))
                  {
                    done = true;
                    if ((isPotionAggressive(effect.func_76456_a())) && (source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityLivingBase)))
                    {

                      ((EntityLivingBase)source.func_76346_g()).func_70690_d(effect);
                    } else {
                      player.func_70690_d(effect);
                    }
                    player.func_70097_a(DamageSource.field_76376_m, 1.0F);
                    stack.func_77978_p().func_82580_o("WITCPotion");
                    if (stack.func_77978_p().func_82582_d()) {
                      stack.func_77982_d(null);
                    }
                  }
                }
              }
              
              if ((!done) && (stack.func_77978_p().func_74764_b("WITCPotion2"))) {
                int potion = stack.func_77978_p().func_74762_e("WITCPotion2");
                
                List list = Items.field_151068_bn.func_77834_f(potion);
                if ((list != null) && (!list.isEmpty())) {
                  PotionEffect effect = new PotionEffect((PotionEffect)list.get(0));
                  if ((!player.func_82165_m(effect.func_76456_a())) && (effect.func_76456_a() != field_76428_lfield_76415_H))
                  {
                    if ((isPotionAggressive(effect.func_76456_a())) && (source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityLivingBase)))
                    {

                      ((EntityLivingBase)source.func_76346_g()).func_70690_d(effect);
                    } else {
                      player.func_70690_d(effect);
                    }
                    player.func_70097_a(DamageSource.field_76376_m, 1.0F);
                    stack.func_77978_p().func_82580_o("WITCPotion2");
                    if (stack.func_77978_p().func_82582_d()) {
                      stack.func_77982_d(null);
                    }
                  }
                }
              }
            }
          }
          

          checkForRendArmor(event);
          

          if ((!ignoreProtection) && (!playerEx.isVampire())) {
            ItemStack vampiricPoppetStack = ItemPoppet.findBoundPoppetInWorld(ItemsPOPPET.vampiricPoppet, player, 66, true, false);
            
            if (vampiricPoppetStack != null)
            {
              EntityWitchHunter.blackMagicPerformed(player);
              EntityLivingBase targetEntity = ItemsTAGLOCK_KIT.getBoundEntity(field_70170_p, player, vampiricPoppetStack, Integer.valueOf(2));
              
              if ((targetEntity != null) && (!ItemsPOPPET.voodooProtectionActivated(player, vampiricPoppetStack, targetEntity, true, false)))
              {


                if (!ItemHunterClothes.isFullSetWorn(targetEntity, false))
                {
                  if ((targetEntity instanceof EntityPlayer)) {
                    targetEntity.func_70097_a(source, ammount);
                    event.setCanceled(true);
                  }
                  else if (((targetEntity instanceof EntityLiving)) && (targetEntity.func_70089_S())) {
                    targetEntity.func_70097_a(source, Math.min(ammount, 15.0F));
                    
                    if (!targetEntity.func_70089_S()) {
                      ItemsTAGLOCK_KIT.clearTaglock(vampiricPoppetStack, Integer.valueOf(2));
                    }
                    event.setCanceled(true);
                  }
                  
                  return;
                }
              }
            }
          }
          

          if (!louseUsed) {
            for (int i = 0; i < InventoryPlayer.func_70451_h(); i++) {
              ItemStack stack = field_71071_by.func_70301_a(i);
              if ((stack != null) && (stack.func_77973_b() == ItemsPARASYTIC_LOUSE) && (stack.func_77960_j() > 0))
              {
                List list = Items.field_151068_bn.func_77834_f(stack.func_77960_j());
                
                if ((list != null) && (!list.isEmpty())) {
                  PotionEffect effect = new PotionEffect((PotionEffect)list.get(0));
                  if (effect.func_76456_a() == field_76428_lfield_76415_H) {
                    player.func_70690_d(effect);
                  }
                  
                  player.func_70097_a(DamageSource.field_76376_m, 1.0F);
                  stack.func_77964_b(0);
                  louseUsed = true;
                  break;
                }
                stack.func_77964_b(0);
              }
            }
          }
          


          if ((!louseUsed) && (ItemsBITING_BELT.isBeltWorn(player))) {
            ItemStack stack = field_71071_by.func_70440_f(1);
            if ((stack != null) && (stack.func_77942_o())) {
              boolean done = false;
              if (stack.func_77978_p().func_74764_b("WITCPotion")) {
                int potion = stack.func_77978_p().func_74762_e("WITCPotion");
                
                List list = Items.field_151068_bn.func_77834_f(potion);
                if ((list != null) && (!list.isEmpty())) {
                  PotionEffect effect = new PotionEffect((PotionEffect)list.get(0));
                  if ((!player.func_82165_m(effect.func_76456_a())) && (effect.func_76456_a() == field_76428_lfield_76415_H))
                  {
                    done = true;
                    player.func_70690_d(effect);
                    player.func_70097_a(DamageSource.field_76376_m, 1.0F);
                    stack.func_77978_p().func_82580_o("WITCPotion");
                    if (stack.func_77978_p().func_82582_d()) {
                      stack.func_77982_d(null);
                    }
                  }
                }
              }
              
              if ((!done) && (stack.func_77978_p().func_74764_b("WITCPotion2"))) {
                int potion = stack.func_77978_p().func_74762_e("WITCPotion2");
                
                List list = Items.field_151068_bn.func_77834_f(potion);
                if ((list != null) && (!list.isEmpty())) {
                  PotionEffect effect = new PotionEffect((PotionEffect)list.get(0));
                  if ((!player.func_82165_m(effect.func_76456_a())) && (effect.func_76456_a() == field_76428_lfield_76415_H))
                  {
                    player.func_70690_d(effect);
                    player.func_70097_a(DamageSource.field_76376_m, 1.0F);
                    stack.func_77978_p().func_82580_o("WITCPotion2");
                    if (stack.func_77978_p().func_82582_d()) {
                      stack.func_77982_d(null);
                    }
                  }
                }
              }
            }
          }
        }
        

        if ((healthAfterDamage <= 0.0F) && (!wolfForm) && (!playerEx.isVampire())) {
          Log.instance().debug(String.format("player terminal damage", new Object[0]));
          if ((source.field_76373_n.equals(field_76379_hfield_76373_n)) || (source.field_76373_n.equals(field_82729_pfield_76373_n)))
          {
            ItemsPOPPET.cancelEventIfPoppetFound(player, ItemsPOPPET.earthPoppet, event, false);
          }
          else if ((source.func_76347_k()) || (source.func_94541_c())) {
            ItemsPOPPET.cancelEventIfPoppetFound(player, ItemsPOPPET.firePoppet, event, true);
            
            if (event.isCanceled()) {
              player.func_70690_d(new PotionEffect(field_76426_nfield_76415_H, 60, 0));
            }
          }
          else if (source.field_76373_n.equals(field_76369_efield_76373_n)) {
            ItemsPOPPET.cancelEventIfPoppetFound(player, ItemsPOPPET.waterPoppet, event, true);
            
            if (event.isCanceled()) {
              player.func_70690_d(new PotionEffect(field_76427_ofield_76415_H, 60, 0));
            }
          }
          else if (source.field_76373_n.equals(field_76366_ffield_76373_n)) {
            ItemsPOPPET.cancelEventIfPoppetFound(player, ItemsPOPPET.foodPoppet, event, true);
            
            if (event.isCanceled()) {
              player.func_70690_d(new PotionEffect(field_76443_yfield_76415_H, 60, 0));
            }
          }
          

          if (!event.isCanceled())
          {
            ItemsPOPPET.cancelEventIfPoppetFound(player, ItemsPOPPET.deathPoppet, event, true, ignoreProtection);
            
            if (event.isCanceled()) {
              if ((player.func_70027_ad()) || (source.func_76347_k()) || (source.func_94541_c())) {
                player.func_70690_d(new PotionEffect(field_76426_nfield_76415_H, 120, 0));
              }
              else if (source.field_76373_n.equals(field_76369_efield_76373_n)) {
                player.func_70690_d(new PotionEffect(field_76427_ofield_76415_H, 120, 0));
              }
              else if (source.field_76373_n.equals(field_76366_ffield_76373_n)) {
                player.func_70690_d(new PotionEffect(field_76443_yfield_76415_H, 120, 0));
              }
            }
          }
        }
        

        if ((!event.isCanceled()) && (healthAfterDamage <= 2.0F) && 
          (source.field_76373_n.equals(field_76366_ffield_76373_n))) {
          ItemsPOPPET.cancelEventIfPoppetFound(player, ItemsPOPPET.foodPoppet, event, true);
          
          if (event.isCanceled()) {
            player.func_70690_d(new PotionEffect(field_76443_yfield_76415_H, 60, 0));
          }
        }
        


        com.emoniph.witchery.familiar.Familiar.handlePlayerHurt(event, player);
        checkForWolfInfection(event, healthAfterDamage);
        
        ItemsPOPPET.checkForArmorProtection(player);
      } else {
        if (((entityLiving instanceof EntityGoblin)) && 
          (source == DamageSource.field_76379_h)) {
          event.setCanceled(true);
          return;
        }
        

        if (((entityLiving instanceof EntityVillager)) && 
          (source != null) && (source.func_76346_g() != null) && (
          ((source.func_76346_g() instanceof EntityVillageGuard)) || ((source.func_76346_g() instanceof EntityWitchHunter))))
        {
          event.setCanceled(true);
          return;
        }
        


        if ((Config.instance().isReduceZombeVillagerDamageActive()) && ((entityLiving instanceof EntityVillager)))
        {
          if ((source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityZombie))) {
            ammount = 0.5F;
          }
        }
        
        checkForRendArmor(event);
        checkForWolfInfection(event, EntityUtil.getHealthAfterDamage(event, entityLiving.func_110143_aJ(), entityLiving));
      }
    }
  }
  
  public void checkForRendArmor(LivingHurtEvent event)
  {
    if ((source.field_76373_n.equals("player")) && (source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityPlayer)))
    {
      EntityPlayer attackingPlayer = (EntityPlayer)source.func_76346_g();
      ExtendedPlayer playerEx = ExtendedPlayer.get(attackingPlayer);
      Shapeshift.INSTANCE.rendArmor(entityLiving, attackingPlayer, playerEx);
    }
  }
  
  public void checkForWolfInfection(LivingHurtEvent event, float health) {
    if (!event.isCanceled()) {
      if ((source.field_76373_n.equals("player")) && (source.func_76364_f() != null) && ((source.func_76364_f() instanceof EntityPlayer)))
      {
        EntityPlayer attackingPlayer = (EntityPlayer)source.func_76346_g();
        ExtendedPlayer playerEx = ExtendedPlayer.get(attackingPlayer);
        Shapeshift.INSTANCE.processWolfInfection(entityLiving, attackingPlayer, playerEx, health);
      } else if ((source.field_76373_n.equals("mob")) && ((source.func_76364_f() instanceof EntityWolfman)))
      {
        Shapeshift.INSTANCE.processWolfInfection(entityLiving, (EntityWolfman)source.func_76364_f(), health);
      }
    }
  }
  
  public void checkForChargeDamage(LivingHurtEvent event)
  {
    if ((source.field_76373_n.equals("player")) && (source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityPlayer)))
    {
      EntityPlayer attackingPlayer = (EntityPlayer)source.func_76346_g();
      ExtendedPlayer playerEx = ExtendedPlayer.get(attackingPlayer);
      Shapeshift.INSTANCE.updateChargeDamage(event, attackingPlayer, playerEx);
    }
  }
  
  private static boolean isPotionAggressive(int potionID) {
    return (potionID == field_76419_ffield_76415_H) || (potionID == field_76421_dfield_76415_H) || (potionID == field_76436_ufield_76415_H) || (potionID == field_82731_vfield_76415_H) || (potionID == field_76437_tfield_76415_H) || (potionID == field_76438_sfield_76415_H);
  }
  

  private static void dropItemsOnHit(EntityPlayer player)
  {
    for (int i = 0; i < field_71071_by.field_70462_a.length; i++) {
      ItemStack stack = field_71071_by.field_70462_a[i];
      if (ItemsGENERIC.itemBatBall.isMatch(stack)) {
        player.func_71019_a(stack, true);
        field_71071_by.field_70462_a[i] = null;
      }
    }
  }
  
  @SubscribeEvent(priority=EventPriority.HIGH)
  public void onLivingDeath(LivingDeathEvent event) {
    if ((entityLiving.field_70170_p.field_72995_K) && (!event.isCanceled())) {
      if ((entityLiving instanceof EntityPlayer)) {
        EntityPlayer player = (EntityPlayer)entityLiving;
        ExtendedPlayer playerEx = ExtendedPlayer.get(player);
        if (playerEx.isVampire()) {
          event.setCanceled(true);
          player.func_70606_j(1.0F);
          return;
        }
      }
    } else if ((!entityLiving.field_70170_p.field_72995_K) && ((!event.isCancelable()) || (!event.isCanceled())))
    {
      if ((entityLiving instanceof EntityPlayer)) {
        EntityPlayer player = (EntityPlayer)entityLiving;
        ExtendedPlayer playerEx = ExtendedPlayer.get(player);
        if (playerEx.isVampire())
        {
          if (player.func_110143_aJ() > 0.0F) {
            event.setCanceled(true);
            return;
          }
          if (!CreatureUtil.checkForVampireDeath(player, source)) {
            event.setCanceled(true);
            return;
          }
        }
      }
      
      dropExtraItemsFromNBT(event);
      

      Entity attacker = source.func_76346_g();
      if ((attacker != null) && ((attacker instanceof EntityPlayer))) {
        EntityPlayer player = (EntityPlayer)attacker;
        ExtendedPlayer playerEx = ExtendedPlayer.get(player);
        if (((entity instanceof EntityHornedHuntsman)) && (playerEx.getWolfmanQuestState() == ExtendedPlayer.QuestState.STARTED))
        {
          playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.COMPLETE);
        }
        
        if (playerEx.hasVampireBook()) {
          boolean dropPage = ((entityLiving instanceof IBossDisplayData)) || (((!(entityLiving instanceof EntityPigZombie)) && (!(entityLiving instanceof EntityEnderman))) || ((field_70170_p.field_73012_v.nextDouble() < 0.09D) || ((com.emoniph.witchery.brewing.potions.PotionParalysis.isVillager(entityLiving)) && (field_70170_p.field_73012_v.nextDouble() < 0.1D)) || ((entityLiving.func_70662_br()) && (field_70170_p.field_73012_v.nextDouble() < 0.02D))));
          





          if (dropPage) {
            EntityItem entityItem = new EntityItem(entityLiving.field_70170_p, entityLiving.field_70165_t, entityLiving.field_70163_u + 1.0D, entityLiving.field_70161_v, ItemsGENERIC.itemVampireBookPage.createStack());
            

            entityLiving.field_70170_p.func_72838_d(entityItem);
          }
        }
      }
      

      Entity entitySource = source.func_76364_f();
      if ((entitySource != null) && ((entitySource instanceof EntityPlayer))) {
        EntityPlayer player = (EntityPlayer)entitySource;
        ExtendedPlayer playerEx = ExtendedPlayer.get(player);
        boolean hasArthana = (field_71071_by.func_70448_g() != null) && (field_71071_by.func_70448_g().func_77973_b() == ItemsARTHANA);
        
        boolean hasCaneSword = (field_71071_by.func_70448_g() != null) && (field_71071_by.func_70448_g().func_77973_b() == ItemsCANE_SWORD) && (ItemsCANE_SWORD.isDrawn(player)) && (playerEx.isVampire());
        

        ItemStack itemstack = null;
        
        Shapeshift.INSTANCE.processCreatureKilled(event, player, playerEx);
        
        if ((playerEx.getWerewolfLevel() == 5) && (Shapeshift.INSTANCE.isWolfAnimalForm(playerEx)) && (playerEx.getWolfmanQuestState() == ExtendedPlayer.QuestState.STARTED))
        {
          if (((entity instanceof net.minecraft.entity.monster.IMob)) && (!field_70122_E)) {
            playerEx.increaseWolfmanQuestCounter();
          }
        } else if ((playerEx.getWerewolfLevel() == 8) && (playerEx.getCreatureType() == TransformCreature.WOLFMAN) && (playerEx.getWolfmanQuestState() == ExtendedPlayer.QuestState.STARTED))
        {

          if ((entity instanceof EntityPigZombie)) {
            playerEx.increaseWolfmanQuestCounter();
          }
        } else if ((playerEx.getWerewolfLevel() == 9) && (Shapeshift.INSTANCE.isWolfAnimalForm(playerEx)) && (playerEx.getWolfmanQuestState() == ExtendedPlayer.QuestState.STARTED))
        {
          if (((entity instanceof EntityVillager)) || ((entity instanceof EntityPlayer))) {
            playerEx.increaseWolfmanQuestCounter();
          }
        }
        
        if ((playerEx.getVampireLevel() == 5) && (playerEx.canIncreaseVampireLevel()) && 
          ((entity instanceof net.minecraft.entity.monster.EntityBlaze))) {
          if (playerEx.getVampireQuestCounter() >= 19) {
            playerEx.increaseVampireLevel();
          } else {
            playerEx.increaseVampireQuestCounter();
          }
        }
        

        int baseLooting = net.minecraft.enchantment.EnchantmentHelper.func_77519_f(player);
        double lootingFactor = 1.0D + baseLooting;
        double halfLooting = 1.0D + baseLooting / 2;
        
        if (InfusedBrewEffect.getActiveBrew(player) == InfusedBrewEffect.Grave) {
          float maxHealth = player.func_110138_aP();
          if ((entityLiving instanceof EntityPlayer)) {
            player.func_71024_bL().func_75122_a(20, 0.9F);
            player.func_70691_i(maxHealth * 0.6F);
          } else if ((entityLiving instanceof EntityVillager)) {
            player.func_71024_bL().func_75122_a(20, 0.9F);
            player.func_70691_i(maxHealth * 0.4F);
          } else if ((entityLiving instanceof net.minecraft.entity.passive.EntityAnimal)) {
            player.func_71024_bL().func_75122_a(8, 0.8F);
            player.func_70691_i(maxHealth * 0.1F);
          }
        }
        
        ItemsBLOOD_GOBLET.handleCreatureDeath(field_70170_p, player, entityLiving);
        
        boolean allowDrops = !EntityUtil.isNoDrops(entityLiving);
        if (allowDrops) {
          if ((entityLiving instanceof EntityVillager)) {
            ExtendedVillager villagerEx = ExtendedVillager.get((EntityVillager)entityLiving);
            playerEx.fillBloodReserve(villagerEx.getBlood());
          } else if ((entityLiving instanceof EntityVillageGuard)) {
            EntityVillageGuard guard = (EntityVillageGuard)entityLiving;
            playerEx.fillBloodReserve(guard.getBlood());
          } else if ((entityLiving instanceof EntityPlayer)) {
            ExtendedPlayer targetEx = ExtendedPlayer.get((EntityPlayer)entityLiving);
            playerEx.fillBloodReserve(targetEx.getHumanBlood());
          } else if ((entityLiving instanceof EntitySkeleton)) {
            EntitySkeleton skeleton = (EntitySkeleton)entityLiving;
            if ((hasArthana) && (skeleton.func_82202_m() == 0) && (entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min(0.05D * lootingFactor, 1.0D)))
            {


              itemstack = new ItemStack(Items.field_151144_bL, 1, 0);
            } else if ((hasArthana) && (entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min(0.04D * lootingFactor, 1.0D)))
            {

              itemstack = ItemsGENERIC.itemSpectralDust.createStack();
            }
          } else if ((entityLiving instanceof EntityZombie)) {
            if ((hasArthana) && (entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min(0.02D * lootingFactor, 1.0D)))
            {

              itemstack = new ItemStack(Items.field_151144_bL, 1, 2);
            } else if ((hasArthana) && (entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min(0.03D * lootingFactor, 1.0D)))
            {

              itemstack = ItemsGENERIC.itemSpectralDust.createStack();
            }
          } else if ((entityLiving instanceof EntityCreeper)) {
            if ((hasArthana) && (entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min(0.01D * lootingFactor, 1.0D)))
            {

              itemstack = new ItemStack(Items.field_151144_bL, 1, 4);
            } else if (entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min((hasArthana ? 0.08D : 0.02D) * lootingFactor, 1.0D))
            {
              itemstack = ItemsGENERIC.itemCreeperHeart.createStack();
            }
          } else if ((entityLiving instanceof EntityDemon)) {
            if ((hasArthana) && (entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min(0.33D * halfLooting, 1.0D)))
            {

              itemstack = ItemsGENERIC.itemDemonHeart.createStack();
            }
          } else if ((entityLiving instanceof EntityPlayer)) {
            if ((hasArthana) && (entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min(0.1D * halfLooting, 1.0D)))
            {

              EntityPlayer victim = (EntityPlayer)entityLiving;
              itemstack = new ItemStack(Items.field_151144_bL, 1, 3);
              NBTTagCompound tag = itemstack.func_77978_p();
              if (tag == null) {
                tag = new NBTTagCompound();
                itemstack.func_77982_d(tag);
              }
              tag.func_74778_a("SkullOwner", victim.func_70005_c_());
            }
          } else if ((entityLiving instanceof net.minecraft.entity.passive.EntityBat)) {
            if (field_70170_p.field_73012_v.nextDouble() <= (hasArthana ? 0.75D : baseLooting > 0 ? 1.0D : 0.33D))
            {
              itemstack = ItemsGENERIC.itemBatWool.createStack();
            }
          } else if ((entityLiving instanceof EntityWolf)) {
            if (field_70170_p.field_73012_v.nextDouble() <= (hasArthana ? 0.75D : baseLooting > 0 ? 1.0D : 0.33D))
            {
              itemstack = ItemsGENERIC.itemDogTongue.createStack();
            }
            
            if (field_70170_p.field_73012_v.nextInt(12) <= Math.min(baseLooting, 3)) {
              entityLiving.func_70099_a(new ItemStack(BlocksWOLFHEAD, 1, 0), 0.0F);
            }
          } else if ((entityLiving instanceof EntityOwl)) {
            if (!((EntityOwl)entityLiving).isTemp()) if (field_70170_p.field_73012_v.nextDouble() <= (hasArthana ? 0.5D : baseLooting > 0 ? 1.0D : 0.2D))
              {

                itemstack = ItemsGENERIC.itemOwletsWing.createStack();
              }
          } else if ((entityLiving instanceof EntitySheep)) {
            if ((CreatureUtil.isWerewolf(entitySource, false)) && (!((EntitySheep)entityLiving).func_70631_g_()))
            {
              if (entityLiving.field_70170_p.field_73012_v.nextInt(4) != 0) {
                itemstack = ItemsGENERIC.itemMuttonRaw.createStack();
              }
            }
          } else if ((entityLiving instanceof EntityToad)) {
            if (!((EntityToad)entityLiving).isTemp()) if (field_70170_p.field_73012_v.nextDouble() <= (hasArthana ? 0.5D : baseLooting > 0 ? 1.0D : 0.2D))
              {

                itemstack = ItemsGENERIC.itemToeOfFrog.createStack();
              }
          } else {
            try {
              Class theClass = entityLiving.getClass();
              if (theClass != null) {
                String name = theClass.getSimpleName();
                if ((name != null) && (!name.isEmpty())) {
                  String upperName = name.toUpperCase(java.util.Locale.ROOT);
                  if ((upperName.contains("WOLF")) || (name.contains("Dog")) || (name.contains("Fox"))) {
                    if (field_70170_p.field_73012_v.nextDouble() <= (hasArthana ? 0.75D : baseLooting > 0 ? 1.0D : 0.33D))
                    {

                      itemstack = ItemsGENERIC.itemDogTongue.createStack();
                    }
                    
                    if (((upperName.contains("WOLF")) || (name.contains("Dog"))) && (field_70170_p.field_73012_v.nextInt(12) <= Math.min(baseLooting, 3)))
                    {
                      entityLiving.func_70099_a(new ItemStack(BlocksWOLFHEAD, 1, 0), 0.0F);
                    }
                  }
                  else if ((upperName.contains("FIREBAT")) || (name.contains("Bat"))) {
                    if (field_70170_p.field_73012_v.nextDouble() <= (hasArthana ? 0.75D : baseLooting > 0 ? 1.0D : 0.33D))
                    {

                      itemstack = ItemsGENERIC.itemBatWool.createStack();
                    }
                  }
                }
              }
            } catch (Exception e) {
              Log.instance().debug(String.format("Exception occurred while determining dead creature type: %s", new Object[] { e.toString() }));
            }
          }
        }
        


        if (itemstack != null) {
          EntityItem entityItem = new EntityItem(entityLiving.field_70170_p, entityLiving.field_70165_t, entityLiving.field_70163_u + 1.0D, entityLiving.field_70161_v, itemstack);
          

          entityLiving.field_70170_p.func_72838_d(entityItem);
        }
      }
    }
  }
  
  private void dropExtraItemsFromNBT(LivingDeathEvent event) {
    if (!entityLiving.field_70170_p.field_72995_K) {
      NBTTagCompound nbtEntityData = entityLiving.getEntityData();
      if (nbtEntityData.func_74764_b("WITCExtraDrops")) {
        NBTTagList nbtExtraDrops = nbtEntityData.func_150295_c("WITCExtraDrops", 10);
        
        for (int i = 0; i < nbtExtraDrops.func_74745_c(); i++) {
          NBTTagCompound nbtTag = nbtExtraDrops.func_150305_b(i);
          ItemStack extraStack = ItemStack.func_77949_a(nbtTag);
          if (extraStack != null) {
            EntityItem entityItem = new EntityItem(entityLiving.field_70170_p, entityLiving.field_70165_t, entityLiving.field_70163_u + 1.0D, entityLiving.field_70161_v, extraStack);
            

            entityLiving.field_70170_p.func_72838_d(entityItem);
          }
        }
      }
    }
  }
}
