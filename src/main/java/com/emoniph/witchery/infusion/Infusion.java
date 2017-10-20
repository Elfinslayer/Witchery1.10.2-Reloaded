package com.emoniph.witchery.infusion;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
import com.emoniph.witchery.dimension.WorldProviderTorment;
import com.emoniph.witchery.entity.EntityCovenWitch;
import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.entity.EntityIllusion;
import com.emoniph.witchery.entity.EntityIllusionCreeper;
import com.emoniph.witchery.entity.EntityIllusionSpider;
import com.emoniph.witchery.entity.EntityIllusionZombie;
import com.emoniph.witchery.entity.EntityNightmare;
import com.emoniph.witchery.entity.EntityVillageGuard;
import com.emoniph.witchery.entity.EntityWitchHunter;
import com.emoniph.witchery.entity.ai.EntityAIDigBlocks;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePower;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemHunterClothes;
import com.emoniph.witchery.item.ItemWitchesClothes;
import com.emoniph.witchery.network.PacketPipeline;
import com.emoniph.witchery.network.PacketPlayerStyle;
import com.emoniph.witchery.network.PacketPlayerSync;
import com.emoniph.witchery.predictions.PredictionManager;
import com.emoniph.witchery.ritual.rites.RiteProtectionCircleRepulsive;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.Dye;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;

































































public class Infusion
{
  public static class EventHooks
  {
    public EventHooks() {}
    
    private boolean isBannedSpiritObject(ItemStack stack)
    {
      if (stack != null) {
        Item item = stack.func_77973_b();
        return (item == Items.field_151079_bi) || (item == Items.field_151065_br);
      }
      return false;
    }
    
    @SubscribeEvent(priority=EventPriority.NORMAL)
    public void onEnderTeleport(EnderTeleportEvent event)
    {
      if ((!event.isCanceled()) && (entityLiving != null) && (!entityLiving.field_70170_p.field_72995_K) && 
        ((entityLiving instanceof EntityPlayer)) && (ItemHunterClothes.isFullSetWorn(entityLiving, false)))
      {
        event.setCanceled(true);
      }
    }
    
    @SubscribeEvent(priority=EventPriority.NORMAL)
    public void FillBucket(FillBucketEvent event)
    {
      ItemStack result = attemptFill(world, target);
      if (result != null) {
        result = result;
        event.setResult(Event.Result.ALLOW);
      }
    }
    
    private ItemStack attemptFill(World world, MovingObjectPosition p) {
      Block id = world.func_147439_a(field_72311_b, field_72312_c, field_72309_d);
      
      if (id == BlocksFLOWING_SPIRIT) {
        if (world.func_72805_g(field_72311_b, field_72312_c, field_72309_d) == 0) {
          world.func_147449_b(field_72311_b, field_72312_c, field_72309_d, Blocks.field_150350_a);
          return new ItemStack(ItemsBUCKET_FLOWINGSPIRIT);
        }
      } else if ((id == BlocksHOLLOW_TEARS) && 
        (world.func_72805_g(field_72311_b, field_72312_c, field_72309_d) == 0)) {
        world.func_147449_b(field_72311_b, field_72312_c, field_72309_d, Blocks.field_150350_a);
        return new ItemStack(ItemsBUCKET_HOLLOWTEARS);
      }
      

      return null;
    }
    
    @SubscribeEvent
    public void onLivingDamage(LivingHurtEvent event) {
      if ((entityLiving != null) && (entityLiving.field_70170_p != null) && (!entityLiving.field_70170_p.field_72995_K) && ((entityLiving instanceof EntityPlayer)) && (!event.isCanceled()))
      {

        EntityPlayer player = (EntityPlayer)entityLiving;
        PredictionManager.instance().checkIfFulfilled(player, event);
      }
    }
    
    @SubscribeEvent
    public void onServerChat(ServerChatEvent event)
    {
      if ((player != null) && (!event.isCanceled()) && (!player.field_70170_p.field_72995_K) && (message != null))
      {
        ItemsRUBY_SLIPPERS.trySayTheresNoPlaceLikeHome(player, message);
      }
    }
    
    @SubscribeEvent
    public void onHarvestDrops(BlockEvent.HarvestDropsEvent event) {
      if ((harvester != null) && (harvester.field_70170_p != null) && (!harvester.field_70170_p.field_72995_K)) {
        PredictionManager.instance().checkIfFulfilled(harvester, event);
        PlayerEffects.onHarvestDrops(harvester, event);
        EntityAIDigBlocks.onHarvestDrops(harvester, event);
      }
      
      if ((!world.field_72995_K) && (world.field_73011_w.field_76574_g == instancedimensionDreamID) && (!event.isCanceled()))
      {
        Iterator<ItemStack> iterator = drops.iterator();
        while (iterator.hasNext()) {
          ItemStack stack = (ItemStack)iterator.next();
          if ((stack != null) && (isBannedSpiritObject(stack))) {
            iterator.remove();
          }
        }
      }
    }
    
    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
      if ((entityLiving != null) && (entityLiving.field_70170_p != null) && (!entityLiving.field_70170_p.field_72995_K) && ((entityLiving instanceof EntityPlayer)) && (!event.isCanceled()))
      {

        EntityPlayer player = (EntityPlayer)entityLiving;
        PredictionManager.instance().checkIfFulfilled(player, event);
        PlayerEffects.onInteract(player, event);
      }
    }
    
    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
      long counter = entityLiving.field_70170_p.func_82737_E();
      
      if ((entityLiving instanceof EntityPlayer)) {
        EntityPlayer player = (EntityPlayer)entityLiving;
        
        if (!entityLiving.field_70170_p.field_72995_K) {
          long time = TimeUtil.getServerTimeInTicks();
          if (counter % 4L == 0L) {
            NBTTagCompound nbtPlayer = Infusion.getNBT(player);
            handleBrewGrotesqueEffect(player, nbtPlayer);
            
            WorldProviderDreamWorld.updatePlayerEffects(field_70170_p, player, nbtPlayer, time, counter);
            
            WorldProviderTorment.updatePlayerEffects(field_70170_p, player, nbtPlayer, time, counter);
            

            if (counter % 20L == 0L) {
              handleSyncEffects(player, nbtPlayer);
              handleBrewDepthsEffect(player, nbtPlayer);
              handleCurseEffects(player, nbtPlayer);
              handleSeepingShoesEffect(player, nbtPlayer);
              InfusedBrewEffect.checkActiveEffects(field_70170_p, player, nbtPlayer, counter % 1200L == 0L, time);
            }
            

            if ((counter % 100L == 0L) && (!event.isCanceled())) {
              PredictionManager.instance().checkIfFulfilled(player, event);
              
              if ((instanceallowCovenWitchVisits) && (nbtPlayer.func_74764_b("WITCCoven")) && (field_70170_p.field_73012_v.nextInt(20) == 0))
              {
                ChunkCoordinates coords = player.getBedLocation(field_71093_bK);
                if ((coords != null) && (coords.func_71569_e((int)field_70165_t, (int)field_70163_u, (int)field_70161_v) < 256.0F))
                {

                  NBTTagList nbtCovenList = nbtPlayer.func_150295_c("WITCCoven", 10);
                  
                  if (nbtCovenList.func_74745_c() > 0) {
                    EntityCovenWitch.summonCovenMember(field_70170_p, player, 90);
                  }
                }
              }
            }
          }
          
          PlayerEffects.onUpdate(player, time);
          
          if (counter % 100L == 1L) {
            EntityWitchHunter.handleWitchHunterEffects(player, time);
          }
        }
        
        handleIcySlippersEffect(player);
        
        handleFamiliarFollowerSync(player);

      }
      else if ((!entityLiving.field_70170_p.field_72995_K) && 
        (counter % 20L == 0L)) {
        handleCurseEffects(entityLiving, entityLiving.getEntityData());
      }
      


      if (counter % 100L == 0L) {
        ItemStack belt = entityLiving.func_71124_b(2);
        if ((belt != null) && (belt.func_77973_b() == ItemsBARK_BELT)) {
          Block blockID = entityLiving.field_70170_p.func_147439_a(MathHelper.func_76128_c(entityLiving.field_70165_t), MathHelper.func_76128_c(entityLiving.field_70163_u) - 1, MathHelper.func_76128_c(entityLiving.field_70161_v));
          


          if ((blockID == Blocks.field_150349_c) || (blockID == Blocks.field_150391_bh)) {
            int maxChargeLevel = ItemsBARK_BELT.getMaxChargeLevel(entityLiving);
            int currentChargeLevel = ItemsBARK_BELT.getChargeLevel(belt);
            if (currentChargeLevel < maxChargeLevel) {
              ItemsBARK_BELT.setChargeLevel(belt, Math.min(currentChargeLevel + 1, maxChargeLevel));
              
              entityLiving.field_70170_p.func_72956_a(entityLiving, "witchery:random.wood_creak", 0.5F, (float)(0.8D + 2.0D * entityLiving.field_70170_p.field_73012_v.nextGaussian()));
            }
          }
        }
      }
    }
    


    private void handleSeepingShoesEffect(EntityPlayer player, NBTTagCompound nbtTag)
    {
      if (!field_70122_E) {
        return;
      }
      
      if ((!player.func_70644_a(Potion.field_76436_u)) && (!player.func_70644_a(Potion.field_82731_v))) {
        return;
      }
      
      ItemStack shoes = player.func_71124_b(1);
      if ((shoes == null) || (shoes.func_77973_b() != ItemsSEEPING_SHOES)) {
        return;
      }
      
      boolean poisonRemoved = false;
      if (player.func_70644_a(Potion.field_76436_u)) {
        player.func_82170_o(field_76436_ufield_76415_H);
        poisonRemoved = true;
      }
      
      if (player.func_70644_a(Potion.field_82731_v)) {
        player.func_82170_o(field_82731_vfield_76415_H);
        poisonRemoved = true;
      }
      
      if (poisonRemoved) {
        int x = MathHelper.func_76128_c(field_70165_t);
        int z = MathHelper.func_76128_c(field_70161_v);
        int y = MathHelper.func_76128_c(field_70163_u);
        int RADIUS = 3;
        int RADIUS_SQ = 9;
        for (int dx = x - 3; dx <= x + 3; dx++) {
          for (int dz = z - 3; dz <= z + 3; dz++) {
            for (int dy = y - 1; dy <= y + 1; dy++) {
              if ((Coord.distanceSq(dx, 1.0D, dy, x, 1.0D, dy) <= 9.0D) && (field_70170_p.func_147437_c(dx, dy + 1, dz)) && (!field_70170_p.func_147437_c(dx, dy, dz)))
              {

                ItemDye.applyBonemeal(Dye.BONE_MEAL.createStack(), field_70170_p, dx, dy, dz, player);
              }
            }
          }
        }
      }
    }
    
    private void handleSyncEffects(EntityPlayer player, NBTTagCompound nbtPlayer)
    {
      if ((!field_70170_p.field_72995_K) && (nbtPlayer.func_74764_b("WITCResyncLook"))) {
        long nextSync = nbtPlayer.func_74763_f("WITCResyncLook");
        if (nextSync <= MinecraftServer.func_130071_aq()) {
          nbtPlayer.func_82580_o("WITCResyncLook");
          Witchery.packetPipeline.sendToDimension(new PacketPlayerStyle(player), field_71093_bK);
        }
      }
    }
    
    private void handleFamiliarFollowerSync(EntityPlayer player) {
      if (!field_70170_p.field_72995_K) {
        NBTTagCompound compound = player.getEntityData();
        if (compound.func_74764_b("WITC_LASTPOS")) {
          NBTTagCompound pos = compound.func_74775_l("WITC_LASTPOS");
          int lastDimension = pos.func_74762_e("D");
          if ((lastDimension != field_71093_bK) || (Math.abs(pos.func_74769_h("X") - field_70165_t) > 32.0D) || (Math.abs(pos.func_74769_h("Z") - field_70161_v) > 32.0D))
          {

            if (((lastDimension != field_71093_bK) && (field_71093_bK == -1)) || (lastDimension == -1)) {
              NBTTagCompound nbtPlayer = Infusion.getNBT(player);
              nbtPlayer.func_74757_a("WITCVisitedNether", true);
            }
            
            if (Familiar.hasActiveFamiliar(player)) {
              EntityTameable familiar = Familiar.getFamiliarEntity(player);
              if ((familiar != null) && (!familiar.func_70906_o())) {
                int ipx = MathHelper.func_76128_c(field_70165_t) - 2;
                int j = MathHelper.func_76128_c(field_70161_v) - 2;
                int k = MathHelper.func_76128_c(field_70121_D.field_72338_b) - 2;
                
                boolean done = false;
                for (int l = 0; (l <= 4) && (!done); l++) {
                  for (int i1 = 0; (i1 <= 4) && (!done); i1++) {
                    for (int dy = 0; (dy <= 4) && (!done); dy++) {
                      if ((field_70170_p.func_147439_a(ipx + l, k + dy - 1, j + i1).isSideSolid(field_70170_p, ipx + l, k + dy - 1, j + i1, ForgeDirection.UP)) && (!field_70170_p.func_147439_a(ipx + l, k + dy, j + i1).func_149721_r()) && (!field_70170_p.func_147439_a(ipx + l, k + dy + 1, j + i1).func_149721_r()))
                      {






                        ItemGeneral.teleportToLocation(field_70170_p, 0.5D + ipx + l, k + dy, 0.5D + j + i1, field_71093_bK, familiar, true);
                        


                        done = true;
                      }
                    }
                  }
                }
              }
            }
          }
          
          pos.func_74780_a("X", field_70165_t);
          pos.func_74780_a("Z", field_70161_v);
          pos.func_74768_a("D", field_71093_bK);
        } else {
          NBTTagCompound pos = new NBTTagCompound();
          pos.func_74780_a("X", field_70165_t);
          pos.func_74780_a("Z", field_70161_v);
          pos.func_74768_a("D", field_71093_bK);
          pos.func_74757_a("visitedNether", field_71093_bK == -1);
        }
      }
    }
    
    private void handleIcySlippersEffect(EntityPlayer player) {
      ItemStack shoes = player.func_82169_q(0);
      if ((shoes != null) && (shoes.func_77973_b() == ItemsICY_SLIPPERS)) {
        int k = MathHelper.func_76128_c(field_70163_u - 1.0D);
        for (int i = 0; i < 4; i++) {
          int j = MathHelper.func_76128_c(field_70165_t + (i % 2 * 2 - 1) * 0.5F);
          int l = MathHelper.func_76128_c(field_70161_v + (i / 2 % 2 * 2 - 1) * 0.5F);
          



          Block blockID = field_70170_p.func_147439_a(j, k, l);
          if ((blockID == Blocks.field_150358_i) || (blockID == Blocks.field_150355_j)) {
            field_70170_p.func_147449_b(j, k, l, Blocks.field_150432_aD);
          } else if ((blockID == Blocks.field_150356_k) || (blockID == Blocks.field_150353_l)) {
            field_70170_p.func_147449_b(j, k, l, Blocks.field_150343_Z);
            if (field_70170_p.field_73012_v.nextInt(10) == 0) {
              shoes.func_77972_a(1, player);
            }
          }
        }
      }
    }
    
    private void handleBrewDepthsEffect(EntityPlayer player, NBTTagCompound nbtTag) {
      if (nbtTag.func_74764_b("witcheryDepths")) {
        int timeLeft = nbtTag.func_74762_e("witcheryDepths");
        if (timeLeft > 0) {
          if (!player.func_70644_a(Potion.field_76427_o)) {
            player.func_70690_d(new PotionEffect(field_76427_ofield_76415_H, 6000));
          }
          if (!player.func_70055_a(Material.field_151586_h)) {
            if (!player.func_70644_a(Potion.field_82731_v)) {
              player.func_70690_d(new PotionEffect(field_82731_vfield_76415_H, 100, 1));
            }
          }
          else if (player.func_70644_a(Potion.field_82731_v)) {
            player.func_82170_o(field_82731_vfield_76415_H);
          }
        }
        

        timeLeft--; if (timeLeft <= 0) {
          nbtTag.func_82580_o("witcheryDepths");
          if (player.func_70644_a(Potion.field_76427_o)) {
            player.func_82170_o(field_76427_ofield_76415_H);
          }
          if (player.func_70644_a(Potion.field_76436_u)) {
            player.func_82170_o(field_76436_ufield_76415_H);
          }
        } else {
          nbtTag.func_74768_a("witcheryDepths", timeLeft);
        }
      }
    }
    
    private void handleBrewGrotesqueEffect(EntityPlayer player, NBTTagCompound nbtTag) {
      if (nbtTag.func_74764_b("witcheryGrotesque")) {
        int timeLeft = nbtTag.func_74762_e("witcheryGrotesque");
        Iterator iterator; if (timeLeft > 0) {
          float radius = 4.0F;
          AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(field_70165_t - 4.0D, field_70163_u - 4.0D, field_70161_v - 4.0D, field_70165_t + 4.0D, field_70163_u + 4.0D, field_70161_v + 4.0D);
          

          List list = field_70170_p.func_72872_a(EntityLiving.class, bounds);
          
          for (iterator = list.iterator(); iterator.hasNext();) {
            EntityLiving entity = (EntityLiving)iterator.next();
            boolean victim = (!(entity instanceof EntityDemon)) && (!(entity instanceof IBossDisplayData)) && (!(entity instanceof EntityGolem)) && (!(entity instanceof EntityWitch));
            
            if ((victim) && (Coord.distance(field_70165_t, field_70163_u, field_70161_v, field_70165_t, field_70163_u, field_70161_v) < 4.0D))
            {

              RiteProtectionCircleRepulsive.push(field_70170_p, entity, field_70165_t, field_70163_u, field_70161_v);
            }
          }
        }
        

        timeLeft--; if (timeLeft <= 0) {
          nbtTag.func_82580_o("witcheryGrotesque");
          Witchery.packetPipeline.sendToDimension(new PacketPlayerStyle(player), field_71093_bK);
        } else {
          nbtTag.func_74768_a("witcheryGrotesque", timeLeft);
        }
      }
    }
    
    private void handleCurseEffects(EntityLivingBase entity, NBTTagCompound nbtTag) {
      if ((entity != null) && (nbtTag != null)) {
        if ((!(entity instanceof EntityPlayer)) && (nbtTag.func_74764_b("witcherySinking"))) {
          int level = nbtTag.func_74762_e("witcherySinking");
          if (level > 0) {
            if ((entity.func_70090_H()) || (((entity instanceof EntityPlayer)) && (!field_70122_E))) {
              if (field_70181_x < 0.0D) {
                field_70181_x *= (1.0D + Math.min(0.1D * level, 0.4D));
              } else if (field_70181_x > 0.0D) {
                field_70181_x *= (1.0D - Math.min(0.1D * level, 0.4D));
              }
            }
          } else {
            nbtTag.func_82580_o("witcherySinking");
          }
        }
        
        if (nbtTag.func_74764_b("witcheryCursed")) {
          int level = nbtTag.func_74762_e("witcheryCursed");
          if (level > 0) {
            if ((!entity.func_82165_m(field_76440_qfield_76415_H)) && (!entity.func_82165_m(field_76437_tfield_76415_H)) && (!entity.func_82165_m(field_76419_ffield_76415_H)) && (!entity.func_82165_m(field_76421_dfield_76415_H)) && (!entity.func_82165_m(field_76436_ufield_76415_H)))
            {



              if (field_70170_p.field_73012_v.nextInt(20) == 0) {
                switch (field_70170_p.field_73012_v.nextInt(level >= 2 ? 3 : level >= 3 ? 4 : level >= 4 ? 5 : level >= 5 ? 6 : 2))
                {
                case 0: 
                  entity.func_70690_d(new PotionEffect(field_76419_ffield_76415_H, 600, Math.min(level - 1, 4)));
                  
                  break;
                case 1: 
                  entity.func_70690_d(new PotionEffect(field_76421_dfield_76415_H, 600, Math.min(level - 1, 4)));
                  
                  break;
                case 2: 
                  entity.func_70690_d(new PotionEffect(field_76437_tfield_76415_H, (13 + 2 * level) * 20, Math.min(level - 2, 4)));
                  
                  break;
                case 3: 
                  entity.func_70690_d(new PotionEffect(field_76440_qfield_76415_H, 5 * level * 20));
                  
                  if (level > 5) {
                    entity.func_70690_d(new PotionEffect(field_76439_rfield_76415_H, 5 * level * 20));
                  }
                  







                  break;
                case 5: 
                  if ((entity instanceof EntityPlayer)) {
                    EntityPlayer player = (EntityPlayer)entity;
                    int heldItemIndex = field_71071_by.field_70461_c;
                    if (field_71071_by.field_70462_a[heldItemIndex] != null) {
                      player.func_71019_a(field_71071_by.field_70462_a[heldItemIndex], true);
                      
                      field_71071_by.field_70462_a[heldItemIndex] = null;
                    }
                  } else {
                    ItemStack heldItem = entity.func_70694_bm();
                    if (heldItem != null) {
                      Infusion.dropEntityItemWithRandomChoice(entity, heldItem, true);
                      entity.func_70062_b(0, null);
                    }
                  }
                  break;
                }
              }
            }
          } else {
            nbtTag.func_82580_o("witcheryCursed");
          }
        }
        
        if (nbtTag.func_74764_b("witcheryOverheating")) {
          int level = nbtTag.func_74762_e("witcheryOverheating");
          if (level > 0) {
            World world = field_70170_p;
            if (!entity.func_70027_ad()) if (field_73012_v.nextInt(level > 1 ? 25 : level > 2 ? 20 : 30) == 0) {
                int x = MathHelper.func_76128_c(field_70165_t);
                int z = MathHelper.func_76128_c(field_70161_v);
                BiomeGenBase biome = world.func_72807_a(x, z);
                if ((field_76750_F >= 1.5D) && ((!biome.func_76738_d()) || (!world.func_72896_J())) && (!entity.func_70090_H()))
                {
                  entity.func_70015_d(Math.min(field_73012_v.nextInt(level < 4 ? 2 : level - 1) + 1, 4));
                }
              }
          } else {
            nbtTag.func_82580_o("witcheryOverheating");
          }
        }
        
        if ((nbtTag.func_74764_b("witcheryWakingNightmare")) && ((entity instanceof EntityPlayer))) {
          EntityPlayer player = (EntityPlayer)entity;
          int level = nbtTag.func_74762_e("witcheryWakingNightmare");
          if ((level > 0) && (field_71093_bK != instancedimensionDreamID)) {
            World world = field_70170_p;
            if (field_73012_v.nextInt(level > 2 ? 60 : level > 4 ? 30 : 180) == 0) {
              double R = 16.0D;
              double H = 8.0D;
              AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(field_70165_t - 16.0D, field_70163_u - 8.0D, field_70161_v - 16.0D, field_70165_t + 16.0D, field_70163_u + 8.0D, field_70161_v + 16.0D);
              
              List entities = world.func_72872_a(EntityNightmare.class, bounds);
              boolean doNothing = false;
              for (Object obj : entities) {
                EntityNightmare nightmare = (EntityNightmare)obj;
                if (nightmare.getVictimName().equalsIgnoreCase(player.func_70005_c_())) {
                  doNothing = true;
                  break;
                }
              }
              if (!doNothing) {
                Infusion.spawnCreature(world, EntityNightmare.class, MathHelper.func_76128_c(field_70165_t), MathHelper.func_76128_c(field_70163_u), MathHelper.func_76128_c(field_70161_v), player, 2, 6);
              }
              
            }
          }
          else
          {
            nbtTag.func_82580_o("witcheryWakingNightmare");
          }
        }
        
        if (((entity instanceof EntityPlayer)) && (nbtTag.func_74764_b("witcheryInsanity"))) {
          int level = nbtTag.func_74762_e("witcheryInsanity");
          if (level > 0) {
            World world = field_70170_p;
            int x = MathHelper.func_76128_c(field_70165_t);
            int y = MathHelper.func_76128_c(field_70163_u);
            int z = MathHelper.func_76128_c(field_70161_v);
            if (field_73012_v.nextInt(level > 1 ? 30 : level > 2 ? 25 : 35) == 0) {
              Class<? extends EntityCreature> creatureType = null;
              switch (field_73012_v.nextInt(3)) {
              case 0: 
              default: 
                creatureType = EntityIllusionCreeper.class;
                break;
              case 1: 
                creatureType = EntityIllusionSpider.class;
                break;
              case 2: 
                creatureType = EntityIllusionZombie.class;
              }
              
              int MAX_DISTANCE = 9;
              int MIN_DISTANCE = 4;
              Infusion.spawnCreature(world, creatureType, x, y, z, (EntityPlayer)entity, 4, 9);
            }
            else if ((level >= 4) && (field_73012_v.nextInt(20) == 0)) {
              SoundEffect sound = SoundEffect.NONE;
              switch (field_73012_v.nextInt(3)) {
              case 0: case 2: 
              case 3: 
              default: 
                sound = SoundEffect.RANDOM_EXPLODE;
                break;
              case 1: 
                sound = SoundEffect.MOB_ENDERMAN_IDLE;
              }
              
              
              sound.playOnlyTo((EntityPlayer)entity, 1.0F, 1.0F);
            }
          } else {
            nbtTag.func_82580_o("witcheryInsanity");
          }
        }
      }
    }
    
    @SubscribeEvent(priority=EventPriority.HIGH)
    public void onLivingDeath(LivingDeathEvent event) {
      if ((!entityLiving.field_70170_p.field_72995_K) && (!event.isCanceled())) {
        if ((entityLiving instanceof EntityPlayer)) {
          EntityPlayer player = (EntityPlayer)entity;
          World world = field_70170_p;
          NBTTagCompound nbtTag = Infusion.getNBT(player);
          if (nbtTag.func_74764_b("witcheryDepths")) {
            nbtTag.func_82580_o("witcheryDepths");
          }
          PlayerEffects.onDeath(player);
        }
        Familiar.handleLivingDeath(event);
      }
    }
    
    @SubscribeEvent
    public void onLivingSetAttackTarget(LivingSetAttackTargetEvent event)
    {
      if ((target != null) && ((entityLiving instanceof EntityLiving))) {
        EntityLiving aggressorEntity = (EntityLiving)entityLiving;
        







        if ((target instanceof EntityPlayer))
        {
          EntityPlayer player = (EntityPlayer)target;
          if (player.func_82150_aj())
          {



            if (field_70170_p.func_72846_b(field_70165_t, field_70163_u, field_70161_v, 16.0D) != target)
            {
              aggressorEntity.func_70624_b(null);
            }
          } else if (aggressorEntity.func_70644_a(Potion.field_76440_q)) {
            aggressorEntity.func_70624_b(null);
          } else if ((aggressorEntity instanceof EntityCreeper)) {
            ItemStack stack = field_71071_by.func_70440_f(2);
            if ((stack != null) && (stack.func_77973_b() == ItemsWITCH_ROBES)) {
              aggressorEntity.func_70624_b(null);
            }
          } else if (aggressorEntity.func_70662_br()) {
            if (((aggressorEntity instanceof EntityZombie)) && (ExtendedPlayer.get(player).getVampireLevel() >= 10)) {
              aggressorEntity.func_70624_b(null);
            } else {
              ItemStack stack = field_71071_by.func_70440_f(2);
              if ((stack != null) && (stack.func_77973_b() == ItemsNECROMANCERS_ROBES)) {
                aggressorEntity.func_70624_b(null);
              }
            }
          }
        }
        
        if (((target instanceof EntityVillageGuard)) && ((entityLiving instanceof EntityGolem))) {
          aggressorEntity.func_70624_b(null);
        } else if ((Config.instance().isZombeIgnoreVillagerActive()) && ((target instanceof EntityVillager)) && ((entityLiving instanceof EntityZombie))) {
          aggressorEntity.func_70624_b(null);
        }
      }
    }
    
    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event) {
      if ((entityLiving instanceof EntityPlayer)) {
        EntityPlayer player = (EntityPlayer)entityLiving;
        
        Infusion.Registry.instance().get(player).onFalling(field_70170_p, player, event);
      }
    }
    
    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
      if ((entityLiving instanceof EntityPlayer)) {
        EntityPlayer player = (EntityPlayer)entityLiving;
        

        if ((source.func_76347_k()) && (event.isCancelable()) && (!event.isCanceled()))
        {
          if ((player.func_82169_q(2) != null) && (player.func_82169_q(2).func_77973_b() == ItemsDEATH_ROBE))
          {
            if (!player.func_82165_m(field_76426_nfield_76415_H)) {
              player.func_70690_d(new PotionEffect(field_76426_nfield_76415_H, 100, 0));
            }
            
            event.setCanceled(true);
          }
        }
        
        if (!event.isCanceled()) {
          Infusion.Registry.instance().get(player).onHurt(field_70170_p, player, event);
        }
      }
    }
  }
  
  public static EntityItem dropEntityItemWithRandomChoice(EntityLivingBase entity, ItemStack par1ItemStack, boolean par2)
  {
    if ((par1ItemStack == null) || (entity == null))
      return null;
    if (field_77994_a == 0) {
      return null;
    }
    EntityItem entityitem = new EntityItem(field_70170_p, field_70165_t, field_70163_u - 0.30000001192092896D + entity.func_70047_e(), field_70161_v, par1ItemStack);
    
    field_145804_b = 40;
    float f = 0.1F;
    

    if (par2) {
      float f1 = field_70170_p.field_73012_v.nextFloat() * 0.5F;
      float f2 = field_70170_p.field_73012_v.nextFloat() * 3.1415927F * 2.0F;
      field_70159_w = (-MathHelper.func_76126_a(f2) * f1);
      field_70179_y = (MathHelper.func_76134_b(f2) * f1);
      field_70181_x = 0.20000000298023224D;
    } else {
      f = 0.3F;
      field_70159_w = (-MathHelper.func_76126_a(field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(field_70125_A / 180.0F * 3.1415927F) * f);
      
      field_70179_y = (MathHelper.func_76134_b(field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(field_70125_A / 180.0F * 3.1415927F) * f);
      
      field_70181_x = (-MathHelper.func_76126_a(field_70125_A / 180.0F * 3.1415927F) * f + 0.1F);
      
      f = 0.02F;
      float f1 = field_70170_p.field_73012_v.nextFloat() * 3.1415927F * 2.0F;
      f *= field_70170_p.field_73012_v.nextFloat();
      field_70159_w += Math.cos(f1) * f;
      field_70181_x += (field_70170_p.field_73012_v.nextFloat() - field_70170_p.field_73012_v.nextFloat()) * 0.1F;
      
      field_70179_y += Math.sin(f1) * f;
    }
    
    field_70170_p.func_72838_d(entityitem);
    return entityitem;
  }
  

  public static EntityCreature spawnCreature(World world, Class<? extends EntityCreature> creatureType, EntityLivingBase victim, int minRange, int maxRange, ParticleEffect effect, SoundEffect effectSound)
  {
    int x = MathHelper.func_76128_c(field_70165_t);
    int y = MathHelper.func_76128_c(field_70163_u);
    int z = MathHelper.func_76128_c(field_70161_v);
    return spawnCreature(world, creatureType, x, y, z, victim, minRange, maxRange, effect, effectSound);
  }
  
  public static EntityCreature spawnCreature(World world, Class<? extends EntityCreature> creatureType, int x, int y, int z, EntityPlayer victim, int minRange, int maxRange)
  {
    return spawnCreature(world, creatureType, x, y, z, victim, minRange, maxRange, null, SoundEffect.NONE);
  }
  

  public static EntityCreature spawnCreature(World world, Class<? extends EntityCreature> creatureType, int x, int y, int z, EntityLivingBase victim, int minRange, int maxRange, ParticleEffect effect, SoundEffect effectSound)
  {
    if (!field_72995_K) {
      int activeRadius = maxRange - minRange;
      int ax = field_73012_v.nextInt(activeRadius * 2 + 1);
      if (ax > activeRadius) {
        ax += minRange * 2;
      }
      
      int nx = x - maxRange + ax;
      
      int az = field_73012_v.nextInt(activeRadius * 2 + 1);
      if (az > activeRadius) {
        az += minRange * 2;
      }
      
      int nz = z - maxRange + az;
      
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
      
      Log.instance().debug("Creature: hy: " + hy + " (" + nx + "," + ny + "," + nz + ")");
      
      if (hy >= 2) {
        try {
          Constructor ctor = creatureType.getConstructor(new Class[] { World.class });
          EntityCreature creature = (EntityCreature)ctor.newInstance(new Object[] { world });
          if ((victim instanceof EntityPlayer)) {
            EntityPlayer player = (EntityPlayer)victim;
            if ((creature instanceof EntityIllusion)) {
              ((EntityIllusion)creature).setVictim(player.func_70005_c_());
            } else if ((creature instanceof EntityNightmare)) {
              ((EntityNightmare)creature).setVictim(player.func_70005_c_());
              creature.func_70624_b(victim);
            }
          }
          
          creature.func_70012_b(0.5D + nx, 0.05D + ny + 1.0D, 0.5D + nz, 0.0F, 0.0F);
          world.func_72838_d(creature);
          if (effect != null) {
            effect.send(effectSound, world, 0.5D + nx, 0.05D + ny + 1.0D, 0.5D + nz, 1.0D, field_70131_O, 16);
          }
          
          return creature;
        }
        catch (NoSuchMethodException ex) {}catch (InvocationTargetException ex) {}catch (InstantiationException ex) {}catch (IllegalAccessException ex) {}
      }
    }
    


    return null;
  }
  
  public static boolean isOnCooldown(World world, ItemStack stack) {
    if (!field_72995_K) {
      NBTTagCompound nbtTag = stack.func_77978_p();
      if ((nbtTag != null) && (nbtTag.func_74764_b("WITCCooldown"))) {
        long currentTime = MinecraftServer.func_130071_aq();
        if (currentTime < nbtTag.func_74763_f("WITCCooldown")) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static void setCooldown(World world, ItemStack stack, int milliseconds) {
    if (!field_72995_K) {
      if (!stack.func_77942_o()) {
        stack.func_77982_d(new NBTTagCompound());
      }
      NBTTagCompound nbtTag = stack.func_77978_p();
      if (nbtTag != null) {
        long currentTime = MinecraftServer.func_130071_aq();
        nbtTag.func_74772_a("WITCCooldown", currentTime + milliseconds);
      }
    }
  }
  
  public static final Infusion DEFUSED = new Infusion(0);
  
  public static final String INFUSION_CHARGES_KEY = "witcheryInfusionCharges";
  
  public static final String INFUSION_ID_KEY = "witcheryInfusionID";
  public static final String INFUSION_MAX_CHARGES_KEY = "witcheryInfusionChargesMax";
  public static final String INFUSION_NEXTSYNC = "WITCResyncLook";
  public static final String INFUSION_GROTESQUE = "witcheryGrotesque";
  public static final String INFUSION_DEPTHS = "witcheryDepths";
  public static final String INFUSION_CURSED = "witcheryCursed";
  public static final String INFUSION_INSANITY = "witcheryInsanity";
  public static final String INFUSION_SINKING = "witcherySinking";
  public static final String INFUSION_OVERHEAT = "witcheryOverheating";
  public static final String INFUSION_NIGHTMARE = "witcheryWakingNightmare";
  public final int infusionID;
  protected static final int DEFAULT_CHARGE_COST = 1;
  
  public Infusion(int infusionID)
  {
    this.infusionID = infusionID;
  }
  

  public void onHurt(World worldObj, EntityPlayer player, LivingHurtEvent event) {}
  

  public void onFalling(World world, EntityPlayer player, LivingFallEvent event) {}
  
  public IIcon getPowerBarIcon(EntityPlayer player, int index)
  {
    return Blocks.field_150344_f.func_149691_a(0, 0);
  }
  

  protected boolean consumeCharges(World world, EntityPlayer player, int cost, boolean playFailSound)
  {
    if (field_71075_bZ.field_75098_d) {
      return true;
    }
    
    int charges = getCurrentEnergy(player);
    if (charges - cost < 0) {
      world.func_72956_a(player, "note.snare", 0.5F, 0.4F / ((float)Math.random() * 0.4F + 0.8F));
      clearInfusion(player);
      return false;
    }
    setCurrentEnergy(player, charges - cost);
    

    return true;
  }
  
  public void onUpdate(ItemStack itemstack, World world, EntityPlayer player, int par4, boolean par5) {}
  
  public void onLeftClickEntity(ItemStack itemstack, World world, EntityPlayer player, Entity otherEntity)
  {
    if (!field_72995_K) {
      world.func_72956_a(player, "note.snare", 0.5F, 0.4F / ((float)Math.random() * 0.4F + 0.8F));
    }
  }
  
  public int getMaxItemUseDuration(ItemStack itemstack) {
    return 400;
  }
  
  public void onUsingItemTick(ItemStack itemstack, World world, EntityPlayer player, int countdown) {}
  
  public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int countdown)
  {
    if (!field_72995_K) {
      world.func_72956_a(player, "note.snare", 0.5F, 0.4F / ((float)Math.random() * 0.4F + 0.8F));
    }
  }
  
  public void playSound(World world, EntityPlayer player, String sound) {
    world.func_72956_a(player, sound, 0.5F, 0.4F / ((float)field_73012_v.nextDouble() * 0.4F + 0.8F));
  }
  
  public void playFailSound(World world, EntityPlayer player) {
    playSound(world, player, "note.snare");
  }
  

  public static NBTTagCompound getNBT(Entity player)
  {
    NBTTagCompound entityData = player.getEntityData();
    if (field_70170_p.field_72995_K) {
      return entityData;
    }
    NBTTagCompound persistedData = entityData.func_74775_l("PlayerPersisted");
    if (!entityData.func_74764_b("PlayerPersisted")) {
      entityData.func_74782_a("PlayerPersisted", persistedData);
    }
    return persistedData;
  }
  
  public void infuse(EntityPlayer player, int charges)
  {
    if (!field_70170_p.field_72995_K) {
      NBTTagCompound nbt = getNBT(player);
      nbt.func_74768_a("witcheryInfusionID", infusionID);
      nbt.func_74768_a("witcheryInfusionCharges", charges);
      nbt.func_74768_a("witcheryInfusionChargesMax", charges);
      CreaturePower.setCreaturePowerID(player, 0, 0);
      syncPlayer(field_70170_p, player);
    }
  }
  
  private void clearInfusion(EntityPlayer player) {
    if (!field_70170_p.field_72995_K) {
      NBTTagCompound nbt = getNBT(player);
      

      nbt.func_82580_o("witcheryInfusionCharges");
      
      syncPlayer(field_70170_p, player);
    }
  }
  
  public static void setCurrentEnergy(EntityPlayer player, int currentEnergy) {
    if (!field_70170_p.field_72995_K) {
      NBTTagCompound nbt = getNBT(player);
      nbt.func_74768_a("witcheryInfusionCharges", currentEnergy);
      syncPlayer(field_70170_p, player);
    }
  }
  
  public static void syncPlayer(World world, EntityPlayer player) {
    if (!field_72995_K) {
      Witchery.packetPipeline.sendTo(new PacketPlayerSync(player), player);
    }
  }
  
  public static int getInfusionID(EntityPlayer player) {
    NBTTagCompound nbt = getNBT(player);
    return nbt.func_74764_b("witcheryInfusionID") ? nbt.func_74762_e("witcheryInfusionID") : 0;
  }
  
  public static int getCurrentEnergy(EntityPlayer player) {
    NBTTagCompound nbt = getNBT(player);
    return nbt.func_74764_b("witcheryInfusionCharges") ? nbt.func_74762_e("witcheryInfusionCharges") : 0;
  }
  
  public static int getMaxEnergy(EntityPlayer player) {
    NBTTagCompound nbt = getNBT(player);
    return nbt.func_74764_b("witcheryInfusionChargesMax") ? nbt.func_74762_e("witcheryInfusionChargesMax") : 0;
  }
  
  public static void setEnergy(EntityPlayer player, int infusionID, int currentEnergy, int maxEnergy) {
    if (field_70170_p.field_72995_K) {
      NBTTagCompound nbt = getNBT(player);
      nbt.func_74768_a("witcheryInfusionID", infusionID);
      nbt.func_74768_a("witcheryInfusionCharges", currentEnergy);
      nbt.func_74768_a("witcheryInfusionChargesMax", maxEnergy);
    }
  }
  
  public static void setSinkingCurseLevel(EntityPlayer playerEntity, int sinkingLevel) {
    if (field_70170_p.field_72995_K) {
      NBTTagCompound nbt = getNBT(playerEntity);
      if ((nbt.func_74764_b("witcherySinking")) && (sinkingLevel <= 0)) {
        nbt.func_82580_o("witcherySinking");
      }
      nbt.func_74768_a("witcherySinking", sinkingLevel);
    }
  }
  
  public static int getSinkingCurseLevel(EntityPlayer player) {
    NBTTagCompound nbtTag = getNBT(player);
    return nbtTag.func_74764_b("witcherySinking") ? nbtTag.func_74762_e("witcherySinking") : 0;
  }
  
  public static boolean aquireEnergy(World world, EntityPlayer player, int cost, boolean showMessages) {
    NBTTagCompound nbtPlayer = getNBT(player);
    if (nbtPlayer != null) {
      return aquireEnergy(world, player, nbtPlayer, cost, showMessages);
    }
    return false;
  }
  

  public static boolean aquireEnergy(World world, EntityPlayer player, NBTTagCompound nbtPlayer, int cost, boolean showMessages)
  {
    if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("witcheryInfusionID")) && (nbtPlayer.func_74764_b("witcheryInfusionCharges")))
    {
      if ((field_71075_bZ.field_75098_d) || (nbtPlayer.func_74762_e("witcheryInfusionCharges") >= cost)) {
        if (!field_71075_bZ.field_75098_d) {
          setCurrentEnergy(player, nbtPlayer.func_74762_e("witcheryInfusionCharges") - cost);
        }
        return true;
      }
      if (showMessages) {
        ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.infuse.nocharges", new Object[0]);
        
        SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
      }
      return false;
    }
    
    if (showMessages) {
      ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.infuse.infusionrequired", new Object[0]);
      SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
    }
    return false;
  }
  
  public static class Registry
  {
    private static final Registry INSTANCE = new Registry();
    
    public static Registry instance() {
      return INSTANCE;
    }
    
    private final ArrayList<Infusion> registry = new ArrayList();
    

    private Registry() {}
    
    public void add(Infusion infusion)
    {
      if (infusionID == registry.size() + 1) {
        registry.add(infusion);
      } else if (infusionID > registry.size() + 1) {
        for (int i = registry.size(); i < infusionID; i++) {
          registry.add(null);
        }
        registry.add(infusion);
      } else {
        Infusion existingInfusion = (Infusion)registry.get(infusionID);
        if (existingInfusion != null) {
          Log.instance().warning(String.format("Creature power %s at id %d is being overwritten by another creature power %s.", new Object[] { existingInfusion, Integer.valueOf(infusionID), infusion }));
        }
        



        registry.set(infusionID, infusion);
      }
    }
    
    public Infusion get(EntityPlayer player) {
      int infusionID = Infusion.getInfusionID(player);
      return infusionID > 0 ? (Infusion)registry.get(infusionID - 1) : Infusion.DEFUSED;
    }
    
    public Infusion get(int infusionID) {
      return infusionID > 0 ? (Infusion)registry.get(infusionID - 1) : Infusion.DEFUSED;
    }
  }
}
