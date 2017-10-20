package com.emoniph.witchery.network;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.potions.PotionResizing;
import com.emoniph.witchery.common.CommonProxy;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;




public class PacketSyncEntitySize
  implements IMessage
{
  private int entityID;
  private float width;
  private float height;
  private float stepSize;
  private float eyeHeight;
  
  public PacketSyncEntitySize() {}
  
  public PacketSyncEntitySize(Entity entity)
  {
    entityID = (entity != null ? entity.func_145782_y() : 0);
    width = field_70130_N;
    height = field_70131_O;
    stepSize = field_70138_W;
    if ((entity instanceof EntityPlayer)) {
      eyeHeight = eyeHeight;
    } else {
      eyeHeight = -1.0F;
    }
  }
  
  public void toBytes(ByteBuf buffer)
  {
    buffer.writeInt(entityID);
    buffer.writeFloat(width);
    buffer.writeFloat(height);
    buffer.writeFloat(stepSize);
    buffer.writeFloat(eyeHeight);
  }
  
  public void fromBytes(ByteBuf buffer)
  {
    entityID = buffer.readInt();
    width = buffer.readFloat();
    height = buffer.readFloat();
    stepSize = buffer.readFloat();
    eyeHeight = buffer.readFloat();
  }
  
  public static class Handler implements IMessageHandler<PacketSyncEntitySize, IMessage> {
    public Handler() {}
    
    public IMessage onMessage(PacketSyncEntitySize message, MessageContext ctx) { EntityPlayer player = Witchery.proxy.getPlayer(ctx);
      for (Object obj : field_70170_p.field_72996_f) {
        Entity entity = (Entity)obj;
        if (entity.func_145782_y() == entityID) {
          PotionResizing.setEntitySize(entity, width, height);
          field_70138_W = stepSize;
          if (((entity instanceof EntityPlayer)) && (eyeHeight != -1.0F)) {}
          

          return null;
        }
      }
      return null;
    }
  }
}
