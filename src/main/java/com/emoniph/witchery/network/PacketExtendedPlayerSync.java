package com.emoniph.witchery.network;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.CommonProxy;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.common.ExtendedPlayer.VampirePower;
import com.emoniph.witchery.common.ExtendedPlayer.VampireUltimate;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class PacketExtendedPlayerSync
  implements IMessage
{
  private int werewolfLevel;
  private int vampireLevel;
  private int bloodLevel;
  private int ultimate;
  private int creatureOrdinal;
  private int selected;
  private int ultimateCharges;
  private int reserveBlood;
  
  public PacketExtendedPlayerSync() {}
  
  public PacketExtendedPlayerSync(ExtendedPlayer extendedPlayer)
  {
    werewolfLevel = extendedPlayer.getWerewolfLevel();
    creatureOrdinal = extendedPlayer.getCreatureTypeOrdinal();
    vampireLevel = extendedPlayer.getVampireLevel();
    bloodLevel = extendedPlayer.getBloodPower();
    selected = extendedPlayer.getSelectedVampirePower().ordinal();
    ultimate = extendedPlayer.getVampireUltimate().ordinal();
    ultimateCharges = extendedPlayer.getVampireUltimateCharges();
    reserveBlood = extendedPlayer.getBloodReserve();
  }
  
  public void toBytes(ByteBuf buffer)
  {
    buffer.writeInt(werewolfLevel);
    buffer.writeInt(creatureOrdinal);
    buffer.writeInt(vampireLevel);
    buffer.writeInt(bloodLevel);
    buffer.writeInt(selected);
    buffer.writeInt(ultimate);
    buffer.writeInt(ultimateCharges);
    buffer.writeInt(reserveBlood);
  }
  
  public void fromBytes(ByteBuf buffer)
  {
    werewolfLevel = buffer.readInt();
    creatureOrdinal = buffer.readInt();
    vampireLevel = buffer.readInt();
    bloodLevel = buffer.readInt();
    selected = buffer.readInt();
    ultimate = buffer.readInt();
    ultimateCharges = buffer.readInt();
    reserveBlood = buffer.readInt();
  }
  
  public static class Handler implements IMessageHandler<PacketExtendedPlayerSync, IMessage> {
    public Handler() {}
    
    public IMessage onMessage(PacketExtendedPlayerSync message, MessageContext ctx) { EntityPlayer player = Witchery.proxy.getPlayer(ctx);
      ExtendedPlayer playerEx = ExtendedPlayer.get(player);
      playerEx.setWerewolfLevel(werewolfLevel);
      playerEx.setCreatureTypeOrdinal(creatureOrdinal);
      playerEx.setVampireLevel(vampireLevel);
      playerEx.setBloodPower(bloodLevel);
      playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.values()[selected], false);
      playerEx.setVampireUltimate(ExtendedPlayer.VampireUltimate.values()[ultimate], ultimateCharges);
      playerEx.setBloodReserve(reserveBlood);
      return null;
    }
  }
}
