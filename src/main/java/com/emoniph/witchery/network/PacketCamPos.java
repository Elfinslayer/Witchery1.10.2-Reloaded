package com.emoniph.witchery.network;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.CommonProxy;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;


public class PacketCamPos
  implements IMessage
{
  private boolean active;
  private boolean updatePosition;
  private int entityID;
  
  public PacketCamPos() {}
  
  public PacketCamPos(boolean active, boolean updatePosition, Entity entity)
  {
    this.active = active;
    this.updatePosition = updatePosition;
    entityID = (entity != null ? entity.func_145782_y() : 0);
  }
  
  public void toBytes(ByteBuf buffer)
  {
    buffer.writeBoolean(active);
    buffer.writeBoolean(updatePosition);
    buffer.writeInt(entityID);
  }
  
  public void fromBytes(ByteBuf buffer)
  {
    active = buffer.readBoolean();
    updatePosition = buffer.readBoolean();
    entityID = buffer.readInt();
  }
  
  public static class Handler implements IMessageHandler<PacketCamPos, IMessage> {
    public Handler() {}
    
    public IMessage onMessage(PacketCamPos message, MessageContext ctx) { EntityPlayer player = Witchery.proxy.getPlayer(ctx);
      com.emoniph.witchery.client.PlayerRender.moveCameraActive = active;
      if (active) {
        Minecraft.func_71410_x();com.emoniph.witchery.client.PlayerRender.ticksSinceActive = Minecraft.func_71386_F();
        if (updatePosition) {
          com.emoniph.witchery.client.PlayerRender.moveCameraToEntityID = entityID;
        }
      }
      return null;
    }
  }
}
