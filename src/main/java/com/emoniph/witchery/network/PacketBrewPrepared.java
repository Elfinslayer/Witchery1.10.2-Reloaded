package com.emoniph.witchery.network;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.CommonProxy;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class PacketBrewPrepared implements IMessage
{
  private int brewIndex;
  
  public PacketBrewPrepared() {}
  
  public PacketBrewPrepared(int brewIndex)
  {
    this.brewIndex = brewIndex;
  }
  
  public void toBytes(ByteBuf buffer)
  {
    buffer.writeInt(brewIndex);
  }
  


  public void fromBytes(ByteBuf buffer) { brewIndex = buffer.readInt(); }
  
  public static class Handler implements IMessageHandler<PacketBrewPrepared, IMessage> {
    public Handler() {}
    
    public IMessage onMessage(PacketBrewPrepared message, MessageContext ctx) {
      EntityPlayer player = Witchery.proxy.getPlayer(ctx);
      player.getEntityData().func_74768_a("WITCLastBrewIndex", brewIndex);
      SoundEffect.RANDOM_POP.playAtPlayer(field_70170_p, player, 1.0F);
      return null;
    }
  }
}
