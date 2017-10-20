package com.emoniph.witchery.network;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.CommonProxy;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class PacketPushTarget
  implements IMessage
{
  private double motionX;
  private double motionY;
  private double motionZ;
  
  public PacketPushTarget() {}
  
  public PacketPushTarget(double motionX, double motionY, double motionZ)
  {
    this.motionX = motionX;
    this.motionY = motionY;
    this.motionZ = motionZ;
  }
  
  public void toBytes(ByteBuf buffer)
  {
    buffer.writeDouble(motionX);
    buffer.writeDouble(motionY);
    buffer.writeDouble(motionZ);
  }
  
  public void fromBytes(ByteBuf buffer)
  {
    motionX = buffer.readDouble();
    motionY = buffer.readDouble();
    motionZ = buffer.readDouble();
  }
  
  public static class Handler implements IMessageHandler<PacketPushTarget, IMessage> {
    public Handler() {}
    
    public IMessage onMessage(PacketPushTarget message, MessageContext ctx) { EntityPlayer player = Witchery.proxy.getPlayer(ctx);
      field_70159_w = motionX;
      field_70181_x = motionY;
      field_70179_y = motionZ;
      return null;
    }
  }
}
