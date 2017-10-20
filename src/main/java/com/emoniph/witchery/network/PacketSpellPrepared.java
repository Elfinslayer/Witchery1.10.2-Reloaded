package com.emoniph.witchery.network;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.CommonProxy;
import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class PacketSpellPrepared implements IMessage
{
  private int effectID;
  private int level;
  
  public PacketSpellPrepared() {}
  
  public PacketSpellPrepared(SymbolEffect effect, int level)
  {
    effectID = effect.getEffectID();
    this.level = level;
  }
  
  public void toBytes(ByteBuf buffer)
  {
    buffer.writeInt(effectID);
    buffer.writeInt(level);
  }
  
  public void fromBytes(ByteBuf buffer)
  {
    effectID = buffer.readInt();
    level = buffer.readInt();
  }
  
  public static class Handler implements IMessageHandler<PacketSpellPrepared, IMessage> {
    public Handler() {}
    
    public IMessage onMessage(PacketSpellPrepared message, MessageContext ctx) { EntityPlayer player = Witchery.proxy.getPlayer(ctx);
      player.getEntityData().func_74768_a("WITCSpellEffectID", effectID);
      player.getEntityData().func_74768_a("WITCSpellEffectEnhanced", level);
      SoundEffect.NOTE_HARP.playAtPlayer(field_70170_p, player, 1.0F);
      return null;
    }
  }
}
