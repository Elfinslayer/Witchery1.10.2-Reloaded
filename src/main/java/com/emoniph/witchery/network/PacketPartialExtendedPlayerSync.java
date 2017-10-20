package com.emoniph.witchery.network;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.CommonProxy;
import com.emoniph.witchery.common.ExtendedPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class PacketPartialExtendedPlayerSync implements IMessage
{
  private int entityId;
  private int blood;
  
  public PacketPartialExtendedPlayerSync() {}
  
  public PacketPartialExtendedPlayerSync(ExtendedPlayer playerEx, EntityPlayer player)
  {
    entityId = player.func_145782_y();
    blood = playerEx.getHumanBlood();
  }
  
  public void toBytes(ByteBuf buffer)
  {
    buffer.writeInt(entityId);
    buffer.writeInt(blood);
  }
  
  public void fromBytes(ByteBuf buffer)
  {
    entityId = buffer.readInt();
    blood = buffer.readInt();
  }
  
  public static class Handler implements cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketPartialExtendedPlayerSync, IMessage> {
    public Handler() {}
    
    public IMessage onMessage(PacketPartialExtendedPlayerSync message, MessageContext ctx) { EntityPlayer player = Witchery.proxy.getPlayer(ctx);
      if (player != null) {
        Entity entity = field_70170_p.func_73045_a(entityId);
        if ((entity instanceof EntityPlayer)) {
          ExtendedPlayer ext = ExtendedPlayer.get((EntityPlayer)entity);
          if (ext != null) {
            ext.setHumanBlood(blood);
          }
        }
      }
      return null;
    }
  }
}
