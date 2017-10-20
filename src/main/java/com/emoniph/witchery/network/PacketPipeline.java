package com.emoniph.witchery.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.Packet;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class PacketPipeline
{
  private SimpleNetworkWrapper CHANNEL;
  
  public PacketPipeline() {}
  
  public void preInit()
  {
    CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel("witchery".toLowerCase());
  }
  
  public void init() {
    CHANNEL.registerMessage(PacketBrewPrepared.Handler.class, PacketBrewPrepared.class, 1, Side.SERVER);
    CHANNEL.registerMessage(PacketParticles.Handler.class, PacketParticles.class, 2, Side.CLIENT);
    CHANNEL.registerMessage(PacketCamPos.Handler.class, PacketCamPos.class, 3, Side.CLIENT);
    CHANNEL.registerMessage(PacketItemUpdate.Handler.class, PacketItemUpdate.class, 4, Side.SERVER);
    CHANNEL.registerMessage(PacketPlayerStyle.Handler.class, PacketPlayerStyle.class, 5, Side.CLIENT);
    CHANNEL.registerMessage(PacketPlayerSync.Handler.class, PacketPlayerSync.class, 6, Side.CLIENT);
    CHANNEL.registerMessage(PacketPushTarget.Handler.class, PacketPushTarget.class, 7, Side.CLIENT);
    CHANNEL.registerMessage(PacketSound.Handler.class, PacketSound.class, 8, Side.CLIENT);
    CHANNEL.registerMessage(PacketSpellPrepared.Handler.class, PacketSpellPrepared.class, 9, Side.SERVER);
    CHANNEL.registerMessage(PacketClearFallDamage.Handler.class, PacketClearFallDamage.class, 10, Side.SERVER);
    CHANNEL.registerMessage(PacketSyncEntitySize.Handler.class, PacketSyncEntitySize.class, 11, Side.CLIENT);
    CHANNEL.registerMessage(PacketSyncMarkupBook.Handler.class, PacketSyncMarkupBook.class, 12, Side.SERVER);
    CHANNEL.registerMessage(PacketExtendedPlayerSync.Handler.class, PacketExtendedPlayerSync.class, 13, Side.CLIENT);
    CHANNEL.registerMessage(PacketHowl.Handler.class, PacketHowl.class, 14, Side.SERVER);
    CHANNEL.registerMessage(PacketExtendedVillagerSync.Handler.class, PacketExtendedVillagerSync.class, 15, Side.CLIENT);
    CHANNEL.registerMessage(PacketSelectPlayerAbility.Handler.class, PacketSelectPlayerAbility.class, 16, Side.SERVER);
    CHANNEL.registerMessage(PacketExtendedEntityRequestSyncToClient.Handler.class, PacketExtendedEntityRequestSyncToClient.class, 17, Side.SERVER);
    CHANNEL.registerMessage(PacketPartialExtendedPlayerSync.Handler.class, PacketPartialExtendedPlayerSync.class, 18, Side.CLIENT);
    CHANNEL.registerMessage(PacketSetClientPlayerFacing.Handler.class, PacketSetClientPlayerFacing.class, 19, Side.CLIENT);
  }
  
  public void sendTo(IMessage message, EntityPlayer player) {
    if ((player instanceof EntityPlayerMP)) {
      CHANNEL.sendTo(message, (EntityPlayerMP)player);
    }
  }
  
  public void sendTo(IMessage message, EntityPlayerMP player) {
    CHANNEL.sendTo(message, player);
  }
  
  public void sendToServer(IMessage message) {
    CHANNEL.sendToServer(message);
  }
  
  public void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint targetPoint) {
    CHANNEL.sendToAllAround(message, targetPoint);
  }
  
  public void sendToAll(IMessage message) {
    CHANNEL.sendToAll(message);
  }
  
  public void sendToDimension(IMessage message, int dimensionId) {
    CHANNEL.sendToDimension(message, dimensionId);
  }
  
  public void sendTo(Packet packet, EntityPlayer player) {
    if ((player instanceof EntityPlayerMP)) {
      EntityPlayerMP mp = (EntityPlayerMP)player;
      field_71135_a.func_147359_a(packet);
    }
  }
  
  public void sendToDimension(Packet packet, World world) {
    for (Object obj : field_73010_i) {
      if ((obj instanceof EntityPlayerMP)) {
        EntityPlayerMP mp = (EntityPlayerMP)obj;
        field_71135_a.func_147359_a(packet);
      }
    }
  }
  
  public void sendToAll(Packet packet) {
    for (WorldServer world : func_71276_Cfield_71305_c) {
      sendToDimension(packet, world);
    }
  }
  
  public void sendToAllAround(Packet packet, World world, NetworkRegistry.TargetPoint point) {
    double RANGE_SQ = range * range;
    for (Object obj : field_73010_i) {
      if ((obj instanceof EntityPlayerMP)) {
        EntityPlayerMP mp = (EntityPlayerMP)obj;
        if (mp.func_70092_e(x, y, z) <= RANGE_SQ) {
          field_71135_a.func_147359_a(packet);
        }
      }
    }
  }
}
