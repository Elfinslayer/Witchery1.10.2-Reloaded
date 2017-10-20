package com.emoniph.witchery.network;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.CommonProxy;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class PacketSetClientPlayerFacing
  implements IMessage
{
  private float pitch;
  private float yaw;
  
  public PacketSetClientPlayerFacing() {}
  
  public PacketSetClientPlayerFacing(EntityPlayer player)
  {
    pitch = field_70125_A;
    yaw = field_70177_z;
  }
  
  public void toBytes(ByteBuf buffer)
  {
    buffer.writeFloat(pitch);
    buffer.writeFloat(yaw);
  }
  
  public void fromBytes(ByteBuf buffer)
  {
    pitch = buffer.readFloat();
    yaw = buffer.readFloat();
  }
  
  public static class Handler implements IMessageHandler<PacketSetClientPlayerFacing, IMessage> {
    public Handler() {}
    
    public IMessage onMessage(PacketSetClientPlayerFacing message, MessageContext ctx) { EntityPlayer player = Witchery.proxy.getPlayer(ctx);
      player.func_70080_a(field_70165_t, field_70163_u, field_70161_v, yaw, pitch);
      return null;
    }
  }
}
