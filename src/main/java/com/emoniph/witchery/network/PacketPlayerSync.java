package com.emoniph.witchery.network;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.CommonProxy;
import com.emoniph.witchery.infusion.InfusedBrewEffect;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePower;
import com.emoniph.witchery.util.TimeUtil;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class PacketPlayerSync
  implements IMessage
{
  private int infusionID;
  private int curEnergy;
  private int maxEnergy;
  private int creatureID;
  private int creatureCharges;
  private int sinkingCurseLevel;
  private int brewEffect;
  private long brewTime;
  
  public PacketPlayerSync() {}
  
  public PacketPlayerSync(EntityPlayer player)
  {
    infusionID = Infusion.getInfusionID(player);
    curEnergy = Infusion.getCurrentEnergy(player);
    maxEnergy = Infusion.getMaxEnergy(player);
    creatureID = CreaturePower.getCreaturePowerID(player);
    creatureCharges = CreaturePower.getCreaturePowerCharges(player);
    sinkingCurseLevel = Infusion.getSinkingCurseLevel(player);
    
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    InfusedBrewEffect brew = InfusedBrewEffect.getActiveBrew(nbtPlayer);
    long time = InfusedBrewEffect.getActiveBrewStartTime(nbtPlayer);
    brewEffect = (brew != null ? id : 0);
    brewTime = 0L;
    if (brew != null) {
      long remainingTicks = durationTicks - (TimeUtil.getServerTimeInTicks() - time);
      if (remainingTicks > 0L) {
        brewTime = ((int)Math.ceil(remainingTicks / 1200.0D));
      }
    }
  }
  
  public void toBytes(ByteBuf buffer)
  {
    buffer.writeInt(infusionID);
    buffer.writeInt(curEnergy);
    buffer.writeInt(maxEnergy);
    buffer.writeInt(creatureID);
    buffer.writeInt(creatureCharges);
    buffer.writeInt(sinkingCurseLevel);
    buffer.writeInt(brewEffect);
    buffer.writeLong(brewTime);
  }
  
  public void fromBytes(ByteBuf buffer)
  {
    infusionID = buffer.readInt();
    curEnergy = buffer.readInt();
    maxEnergy = buffer.readInt();
    creatureID = buffer.readInt();
    creatureCharges = buffer.readInt();
    sinkingCurseLevel = buffer.readInt();
    brewEffect = buffer.readInt();
    brewTime = buffer.readLong();
  }
  
  public static class Handler implements IMessageHandler<PacketPlayerSync, IMessage> {
    public Handler() {}
    
    public IMessage onMessage(PacketPlayerSync message, MessageContext ctx) { EntityPlayer player = Witchery.proxy.getPlayer(ctx);
      if ((player != null) && (message != null)) {
        Infusion.setEnergy(player, infusionID, curEnergy, maxEnergy);
        CreaturePower.setCreaturePowerID(player, creatureID, creatureCharges);
        Infusion.setSinkingCurseLevel(player, sinkingCurseLevel);
        if (brewEffect > 0) {
          InfusedBrewEffect.setActiveBrewInfo(Infusion.getNBT(player), brewEffect, brewTime);
        }
      }
      return null;
    }
  }
}
