package com.emoniph.witchery.common;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.network.PacketPipeline;
import com.emoniph.witchery.network.PacketPlayerStyle;
import com.emoniph.witchery.util.Config;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import java.util.ArrayList;
import java.util.Collection;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class ServerTickEvents
{
  public ServerTickEvents() {}
  
  public static final ArrayList<ServerTickTask> TASKS = new ArrayList();
  
  @SubscribeEvent
  public void onServerTick(TickEvent.ServerTickEvent event) {
    if ((side == cpw.mods.fml.relauncher.Side.SERVER) && (phase == TickEvent.Phase.START) && 
      (TASKS.size() > 0)) {
      ArrayList<ServerTickTask> completedTasks = new ArrayList();
      for (ServerTickTask task : TASKS) {
        if (task.process()) {
          completedTasks.add(task);
        }
      }
      
      for (ServerTickTask task : completedTasks) {
        TASKS.remove(task);
      }
    }
  }
  
  @SubscribeEvent
  public void onPlayerTick(TickEvent.PlayerTickEvent event)
  {
    if ((side == cpw.mods.fml.relauncher.Side.SERVER) && (!player.field_70170_p.field_72995_K)) {
      if (phase == TickEvent.Phase.START) {
        Collection<PotionEffect> activeEffects = player.func_70651_bq();
        ExtendedPlayer playerExt = ExtendedPlayer.get(player);
        if (playerExt != null) {
          playerExt.updateWorship();
          if (activeEffects.size() > 0) {
            playerExt.cacheIncurablePotionEffect(activeEffects);
          }
          playerExt.checkSleep(true);
        }
      } else if (phase == TickEvent.Phase.END) {
        ExtendedPlayer playerExt = ExtendedPlayer.get(player);
        if (playerExt != null) {
          playerExt.restoreIncurablePotionEffects();
          playerExt.checkSleep(false);
        }
      }
    }
  }
  
  public static abstract class ServerTickTask
  {
    protected final World world;
    
    public ServerTickTask(World world) {
      this.world = world;
    }
    
    public abstract boolean process();
  }
  
  @SubscribeEvent
  public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
    EntityPlayer player = player;
    World world = player.field_70170_p;
    Shapeshift.INSTANCE.initCurrentShift(player);
    Infusion.syncPlayer(world, player);
    
    ExtendedPlayer.get(player).scheduleSync();
    

    Witchery.packetPipeline.sendToDimension(new PacketPlayerStyle(player), field_73011_w.field_76574_g);
    


    if ((field_71093_bK != instancedimensionDreamID) && (WorldProviderDreamWorld.getPlayerIsSpiritWalking(player)) && (!WorldProviderDreamWorld.getPlayerIsGhost(player)))
    {

      WorldProviderDreamWorld.setPlayerMustAwaken(player, true);
    } else if ((field_71093_bK == instancedimensionDreamID) && (!WorldProviderDreamWorld.getPlayerIsSpiritWalking(player)))
    {
      WorldProviderDreamWorld.changeDimension(player, 0);
      WorldProviderDreamWorld.findTopAndSetPosition(field_70170_p, player);
    }
  }
  
  @SubscribeEvent
  public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event)
  {
    if (!player.field_70170_p.field_72995_K) {
      NBTTagCompound nbtPlayer = Infusion.getNBT(player);
      if (nbtPlayer.func_74764_b("WITCPoSpawn")) {
        NBTTagList nbtRestoredEffectList = nbtPlayer.func_150295_c("WITCPoSpawn", 10);
        if (nbtRestoredEffectList.func_74745_c() > 0) {
          for (int i = 0; i < nbtRestoredEffectList.func_74745_c(); i++) {
            PotionEffect restoredEffect = PotionEffect.func_82722_b(nbtRestoredEffectList.func_150305_b(i));
            
            if (!player.func_82165_m(restoredEffect.func_76456_a())) {
              player.func_70690_d(restoredEffect);
            }
          }
        }
        nbtPlayer.func_82580_o("WITCPoSpawn");
      }
      
      EntityPlayer player = player;
      World world = player.field_70170_p;
      
      Shapeshift.INSTANCE.initCurrentShift(player);
      Infusion.syncPlayer(world, player);
      

      ExtendedPlayer.get(player).scheduleSync();
      Witchery.packetPipeline.sendToDimension(new PacketPlayerStyle(player), field_73011_w.field_76574_g);
    }
  }
  
  @SubscribeEvent
  public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
    if (!player.field_70170_p.field_72995_K) {
      EntityPlayer player = player;
      
      if ((player != null) && (field_70170_p != null) && (!field_70170_p.field_72995_K))
      {

        long nextUpdate = net.minecraft.server.MinecraftServer.func_130071_aq() + 1500L;
        ExtendedPlayer.get(player).scheduleSync();
        for (Object obj : field_70170_p.field_73010_i) {
          EntityPlayer otherPlayer = (EntityPlayer)obj;
          NBTTagCompound nbtOtherPlayer = Infusion.getNBT(otherPlayer);
          if (otherPlayer != player)
          {











            nbtOtherPlayer.func_74772_a("WITCResyncLook", nextUpdate);
          }
        }
        



        NBTTagCompound nbtPlayer = Infusion.getNBT(player);
        



        Witchery.packetPipeline.sendToDimension(new PacketPlayerStyle(player), field_70170_p.field_73011_w.field_76574_g);
        




        if ((field_71093_bK != instancedimensionDreamID) && (WorldProviderDreamWorld.getPlayerIsSpiritWalking(player)) && (!WorldProviderDreamWorld.getPlayerIsGhost(player)))
        {

          WorldProviderDreamWorld.setPlayerMustAwaken(player, true);
        }
      }
    }
  }
}
