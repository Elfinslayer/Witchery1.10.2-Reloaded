package com.emoniph.witchery.network;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.CommonProxy;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.common.ExtendedPlayer.VampirePower;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;



public class PacketSelectPlayerAbility
  implements IMessage
{
  private byte vampirePower;
  private boolean trigger;
  
  public PacketSelectPlayerAbility() {}
  
  public PacketSelectPlayerAbility(ExtendedPlayer playerEx, boolean trigger)
  {
    vampirePower = ((byte)playerEx.getSelectedVampirePower().ordinal());
    this.trigger = trigger;
  }
  
  public void toBytes(ByteBuf buffer)
  {
    buffer.writeByte(vampirePower);
    buffer.writeBoolean(trigger);
  }
  
  public void fromBytes(ByteBuf buffer)
  {
    vampirePower = buffer.readByte();
    trigger = buffer.readBoolean();
  }
  
  public static class Handler implements IMessageHandler<PacketSelectPlayerAbility, IMessage> {
    public Handler() {}
    
    public IMessage onMessage(PacketSelectPlayerAbility message, MessageContext ctx) { EntityPlayer player = Witchery.proxy.getPlayer(ctx);
      ExtendedPlayer playerEx = ExtendedPlayer.get(player);
      if (playerEx != null) {
        playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.values()[vampirePower], false);
        if (trigger) {
          playerEx.triggerSelectedVampirePower();
        }
      }
      return null;
    }
  }
}
