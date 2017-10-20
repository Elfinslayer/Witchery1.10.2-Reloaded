package com.emoniph.witchery.network;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.CommonProxy;
import com.emoniph.witchery.common.ExtendedVillager;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class PacketExtendedVillagerSync
  implements IMessage
{
  private int entityId;
  private int blood;
  private boolean sleeping;
  
  public PacketExtendedVillagerSync() {}
  
  public PacketExtendedVillagerSync(ExtendedVillager extendedVillager)
  {
    entityId = extendedVillager.getVillager().func_145782_y();
    blood = extendedVillager.getBlood();
    sleeping = extendedVillager.isSleeping();
  }
  
  public void toBytes(ByteBuf buffer)
  {
    buffer.writeInt(entityId);
    buffer.writeInt(blood);
    buffer.writeBoolean(sleeping);
  }
  
  public void fromBytes(ByteBuf buffer)
  {
    entityId = buffer.readInt();
    blood = buffer.readInt();
    sleeping = buffer.readBoolean();
  }
  
  public static class Handler implements IMessageHandler<PacketExtendedVillagerSync, IMessage> {
    public Handler() {}
    
    public IMessage onMessage(PacketExtendedVillagerSync message, MessageContext ctx) { EntityPlayer player = Witchery.proxy.getPlayer(ctx);
      if (player != null) {
        Entity entity = field_70170_p.func_73045_a(entityId);
        if ((entity instanceof EntityVillager)) {
          ExtendedVillager ext = ExtendedVillager.get((EntityVillager)entity);
          if (ext != null) {
            synced = true;
            ext.setBlood(blood);
            ext.setSleeping(sleeping);
          }
        }
      }
      return null;
    }
  }
}
