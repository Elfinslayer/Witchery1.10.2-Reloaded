package com.emoniph.witchery.infusion.infusions;

import com.emoniph.witchery.brewing.potions.PotionEnderInhibition;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.DimensionalLocation;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class InfusionOtherwhere extends Infusion
{
  private static final String RECALL_LOCATON_KEY = "WITCRecall";
  private static final int SAVE_RECALL_POINT_THRESHOLD = 60;
  
  public InfusionOtherwhere(int infusionID)
  {
    super(infusionID);
  }
  
  public IIcon getPowerBarIcon(EntityPlayer player, int index)
  {
    return net.minecraft.init.Blocks.field_150427_aO.func_149691_a(0, 0);
  }
  
  public void onLeftClickEntity(ItemStack itemstack, World world, EntityPlayer player, Entity otherEntity)
  {
    if (field_72995_K) {
      return;
    }
    
    if ((otherEntity instanceof EntityLivingBase)) {
      EntityLivingBase otherLivingEntity = (EntityLivingBase)otherEntity;
      
      if (player.func_70093_af()) {
        DimensionalLocation recallLocation = recallLocation(getNBT(player), "WITCRecall");
        if ((recallLocation != null) && (dimension != instancedimensionDreamID) && (dimension != instancedimensionTormentID) && (dimension != instancedimensionMirrorID) && (field_73011_w.field_76574_g != instancedimensionDreamID) && (field_73011_w.field_76574_g != instancedimensionTormentID) && (field_73011_w.field_76574_g != instancedimensionMirrorID) && (!PotionEnderInhibition.isActive(player, 2)) && (consumeCharges(world, player, 4, false)))
        {







          if (((player instanceof EntityPlayerMP)) && (!isConnectionClosed((EntityPlayerMP)player))) {
            field_70143_R = 0.0F;
            ItemGeneral.teleportToLocation(world, posX, posY, posZ, dimension, player, true);
            
            field_70143_R = 0.0F;
            


            if (!PotionEnderInhibition.isActive(otherLivingEntity, 2)) {
              ItemGeneral.teleportToLocation(world, posX, posY, posZ, dimension, otherLivingEntity, true);
            }
          }
        } else {
          world.func_72956_a(player, "note.snare", 0.5F, 0.4F / ((float)Math.random() * 0.4F + 0.8F));
        }
      }
      else if ((!PotionEnderInhibition.isActive(player, 2)) && (consumeCharges(world, player, 2, true))) {
        double HIKE_HEIGHT = 8.0D;
        
        MovingObjectPosition hitMOP = raytraceUpBlocks(world, player, true, 8.0D);
        double hikeModified = hitMOP == null ? 8.0D : Math.min(field_72312_c - field_70163_u - 2.0D, 8.0D);
        
        MovingObjectPosition hitMOP2 = raytraceUpBlocks(world, otherLivingEntity, true, 8.0D);
        double hikeModified2 = hitMOP2 == null ? 8.0D : Math.min(field_72312_c - field_70163_u - 2.0D, 8.0D);
        
        if (((player instanceof EntityPlayerMP)) && (!isConnectionClosed((EntityPlayerMP)player)) && 
          (hikeModified > 0.0D) && (hikeModified2 > 0.0D)) {
          ItemGeneral.teleportToLocation(world, field_70165_t, field_70163_u + hikeModified, field_70161_v, field_71093_bK, player, true);
          if (!PotionEnderInhibition.isActive(otherLivingEntity, 2)) {
            ItemGeneral.teleportToLocation(world, field_70165_t, field_70163_u + hikeModified2, field_70161_v, field_71093_bK, otherLivingEntity, true);
          }
        }
      }
    }
  }
  


  public void onUsingItemTick(ItemStack itemstack, World world, EntityPlayer player, int countdown)
  {
    int elapsedTicks = getMaxItemUseDuration(itemstack) - countdown;
    if ((player.func_70093_af()) && (elapsedTicks == 60)) {
      if (!field_72995_K) {
        ChatUtil.sendTranslated(EnumChatFormatting.GRAY, player, "witchery.infuse.cansetrecall", new Object[0]);
      }
      field_70170_p.func_72956_a(player, "note.pling", 0.5F, 0.4F / ((float)Math.random() * 0.4F + 0.8F));
    } else if ((!player.func_70093_af()) && (elapsedTicks > 0) && (elapsedTicks % 20 == 0)) {
      int MAX_TELEPORT_DISTANCE = 40 + 20 * (elapsedTicks / 20);
      MovingObjectPosition hitMOP = doCustomRayTrace(world, player, true, MAX_TELEPORT_DISTANCE);
      if (hitMOP != null) {
        field_70170_p.func_72956_a(player, "random.orb", 0.5F, 0.4F / ((float)Math.random() * 0.4F + 0.8F));
        if (!field_72995_K) {
          ChatUtil.sendTranslated(EnumChatFormatting.GRAY, player, "witchery.infuse.canteleport", new Object[0]);
        }
      } else {
        field_70170_p.func_72956_a(player, "random.pop", 0.5F, 0.4F / ((float)Math.random() * 0.4F + 0.8F));
      }
    }
  }
  


  public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int countdown)
  {
    if (field_72995_K) {
      return;
    }
    
    int elapsedTicks = getMaxItemUseDuration(itemstack) - countdown;
    
    if ((player.func_70093_af()) && (elapsedTicks >= 60)) {
      storeLocation(getNBT(player), "WITCRecall", player);
      SoundEffect.RANDOM_FIZZ.playAtPlayer(world, player);
    } else if (player.func_70093_af()) {
      DimensionalLocation recallLocation = recallLocation(getNBT(player), "WITCRecall");
      if ((recallLocation != null) && (dimension != instancedimensionDreamID) && (dimension != instancedimensionTormentID) && (dimension != instancedimensionMirrorID) && (field_73011_w.field_76574_g != instancedimensionDreamID) && (field_73011_w.field_76574_g != instancedimensionTormentID) && (field_73011_w.field_76574_g != instancedimensionMirrorID) && (!PotionEnderInhibition.isActive(player, 2)) && (consumeCharges(world, player, 2, false)))
      {







        if (((player instanceof EntityPlayerMP)) && (!isConnectionClosed((EntityPlayerMP)player))) {
          field_70143_R = 0.0F;
          ItemGeneral.teleportToLocation(world, posX, posY, posZ, dimension, player, true);
          




          Infusion.setCooldown(world, itemstack, 1500);
        }
      } else {
        world.func_72956_a(player, "note.snare", 0.5F, 0.4F / ((float)Math.random() * 0.4F + 0.8F));
      }
    }
    else {
      int MAX_TELEPORT_DISTANCE = 40 + 20 * (elapsedTicks / 20);
      MovingObjectPosition hitMOP = doCustomRayTrace(world, player, true, MAX_TELEPORT_DISTANCE);
      
      if ((hitMOP != null) && (!PotionEnderInhibition.isActive(player, 2)) && (consumeCharges(world, player, 1, false))) {
        ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, player, 0.5D, 2.0D, 16);
        
        teleportEntity(player, hitMOP);
        
        ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, player, 0.5D, 2.0D, 16);
        
        Infusion.setCooldown(world, itemstack, 1500);
      } else {
        world.func_72956_a(player, "note.snare", 0.5F, 0.4F / ((float)Math.random() * 0.4F + 0.8F));
        if ((hitMOP == null) && (!field_72995_K)) {
          ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.infuse.cannotteleport", new Object[0]);
        }
      }
    }
  }
  
  private void storeLocation(NBTTagCompound nbt, String key, EntityPlayer player) {
    DimensionalLocation location = new DimensionalLocation(player);
    location.saveToNBT(nbt, key);
    
    if (!field_70170_p.field_72995_K) {
      ChatUtil.sendTranslated(EnumChatFormatting.GRAY, player, "witchery.infuse.setrecall", new Object[] { field_70170_p.field_73011_w.func_80007_l(), Integer.valueOf(MathHelper.func_76128_c(posX)).toString(), Integer.valueOf(MathHelper.func_76128_c(posY)).toString(), Integer.valueOf(MathHelper.func_76128_c(posZ)).toString() });
    }
  }
  



  private DimensionalLocation recallLocation(NBTTagCompound nbtTag, String key)
  {
    DimensionalLocation location = new DimensionalLocation(nbtTag, key);
    if (!isValid) {
      return null;
    }
    
    return location;
  }
  
  public static void teleportEntity(EntityPlayer entityPlayer, MovingObjectPosition hitMOP) {
    if ((hitMOP != null) && ((entityPlayer instanceof EntityPlayerMP))) {
      EntityPlayerMP player = (EntityPlayerMP)entityPlayer;
      if (!isConnectionClosed(player)) {
        switch (1.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[field_72313_a.ordinal()]) {
        case 1: 
          player.func_70634_a(field_72307_f.field_72450_a, field_72307_f.field_72448_b, field_72307_f.field_72449_c);
          break;
        case 2: 
          double hitx = field_72307_f.field_72450_a;
          double hity = field_72307_f.field_72448_b;
          double hitz = field_72307_f.field_72449_c;
          switch (field_72310_e) {
          case 0: 
            hity -= 2.0D;
            break;
          case 1: 
            break;
          
          case 2: 
            hitz -= 0.5D;
            break;
          case 3: 
            hitz += 0.5D;
            break;
          case 4: 
            hitx -= 0.5D;
            break;
          case 5: 
            hitx += 0.5D;
          }
          
          
          field_70143_R = 0.0F;
          player.func_70634_a(hitx, hity, hitz);
          break;
        }
        
      }
    }
  }
  

  public static MovingObjectPosition doCustomRayTrace(World world, EntityPlayer player, boolean collisionFlag, double reachDistance)
  {
    MovingObjectPosition pickedBlock = raytraceBlocks(world, player, collisionFlag, reachDistance);
    MovingObjectPosition pickedEntity = raytraceEntities(world, player, collisionFlag, reachDistance);
    
    if (pickedBlock == null)
      return pickedEntity;
    if (pickedEntity == null) {
      return pickedBlock;
    }
    Vec3 playerPosition = Vec3.func_72443_a(field_70165_t, field_70163_u + player.func_70047_e(), field_70161_v);
    double dBlock = field_72307_f.func_72438_d(playerPosition);
    double dEntity = field_72307_f.func_72438_d(playerPosition);
    if (dEntity < dBlock) {
      return pickedEntity;
    }
    return pickedBlock;
  }
  


  public static MovingObjectPosition raytraceEntities(World world, EntityPlayer player, boolean collisionFlag, double reachDistance)
  {
    MovingObjectPosition pickedEntity = null;
    Vec3 playerPosition = Vec3.func_72443_a(field_70165_t, field_70163_u + player.func_70047_e(), field_70161_v);
    Vec3 playerLook = player.func_70040_Z();
    
    Vec3 playerViewOffset = Vec3.func_72443_a(field_72450_a + field_72450_a * reachDistance, field_72448_b + field_72448_b * reachDistance, field_72449_c + field_72449_c * reachDistance);
    

    double playerBorder = 1.1D * reachDistance;
    AxisAlignedBB boxToScan = field_70121_D.func_72314_b(playerBorder, playerBorder, playerBorder);
    




    List entitiesHit = world.func_72839_b(player, boxToScan);
    double closestEntity = reachDistance;
    
    if ((entitiesHit == null) || (entitiesHit.isEmpty())) {
      return null;
    }
    
    for (Entity entityHit : entitiesHit) {
      if ((entityHit != null) && (entityHit.func_70067_L()) && (field_70121_D != null)) {
        float border = entityHit.func_70111_Y();
        AxisAlignedBB aabb = field_70121_D.func_72314_b(border, border, border);
        MovingObjectPosition hitMOP = aabb.func_72327_a(playerPosition, playerViewOffset);
        
        if (hitMOP != null) {
          if (aabb.func_72318_a(playerPosition)) {
            if ((0.0D < closestEntity) || (closestEntity == 0.0D)) {
              pickedEntity = new MovingObjectPosition(entityHit);
              if (pickedEntity != null) {
                field_72307_f = field_72307_f;
                closestEntity = 0.0D;
              }
            }
          } else {
            double distance = playerPosition.func_72438_d(field_72307_f);
            
            if ((distance < closestEntity) || (closestEntity == 0.0D)) {
              pickedEntity = new MovingObjectPosition(entityHit);
              field_72307_f = field_72307_f;
              closestEntity = distance;
            }
          }
        }
      }
    }
    return pickedEntity;
  }
  
  private static boolean isConnectionClosed(EntityPlayerMP player) {
    return !field_71135_a.field_147371_a.func_150724_d();
  }
  
  public static MovingObjectPosition raytraceBlocks(World world, EntityPlayer player, boolean collisionFlag, double reachDistance) {
    Vec3 playerPosition = Vec3.func_72443_a(field_70165_t, field_70163_u + player.func_70047_e(), field_70161_v);
    Vec3 playerLook = player.func_70040_Z();
    
    Vec3 playerViewOffset = Vec3.func_72443_a(field_72450_a + field_72450_a * reachDistance, field_72448_b + field_72448_b * reachDistance, field_72449_c + field_72449_c * reachDistance);
    

    return world.func_147447_a(playerPosition, playerViewOffset, collisionFlag, !collisionFlag, false);
  }
  
  private static MovingObjectPosition raytraceUpBlocks(World world, EntityLivingBase player, boolean collisionFlag, double reachDistance) {
    Vec3 playerPosition = Vec3.func_72443_a(field_70165_t, field_70163_u + player.func_70047_e(), field_70161_v);
    Vec3 playerUp = Vec3.func_72443_a(0.0D, 1.0D, 0.0D);
    
    Vec3 playerViewOffset = Vec3.func_72443_a(field_72450_a + field_72450_a * reachDistance, field_72448_b + field_72448_b * reachDistance, field_72449_c + field_72449_c * reachDistance);
    
    return world.func_147447_a(playerPosition, playerViewOffset, collisionFlag, !collisionFlag, false);
  }
}
