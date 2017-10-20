package com.emoniph.witchery.network;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.CommonProxy;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
import com.emoniph.witchery.infusion.Infusion;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class PacketPlayerStyle implements IMessage
{
  private String username;
  private int grotesqueTicks;
  private int nightmare;
  private boolean ghost;
  private int creatureType;
  private int blood;
  private String playerSkin;
  
  public PacketPlayerStyle() {}
  
  public PacketPlayerStyle(EntityPlayer player)
  {
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    
    username = player.func_70005_c_();
    grotesqueTicks = (nbtPlayer.func_74764_b("witcheryGrotesque") ? nbtPlayer.func_74762_e("witcheryGrotesque") : 0);
    nightmare = WorldProviderDreamWorld.getPlayerHasNightmare(nbtPlayer);
    ghost = WorldProviderDreamWorld.getPlayerIsGhost(nbtPlayer);
    ExtendedPlayer playerEx = ExtendedPlayer.get(player);
    creatureType = playerEx.getCreatureTypeOrdinal();
    blood = playerEx.getHumanBlood();
    playerSkin = playerEx.getOtherPlayerSkin();
  }
  
  public void toBytes(ByteBuf buffer)
  {
    ByteBufUtils.writeUTF8String(buffer, username);
    buffer.writeInt(grotesqueTicks);
    buffer.writeInt(nightmare);
    buffer.writeBoolean(ghost);
    buffer.writeInt(creatureType);
    buffer.writeInt(blood);
    ByteBufUtils.writeUTF8String(buffer, playerSkin);
  }
  
  public void fromBytes(ByteBuf buffer)
  {
    username = ByteBufUtils.readUTF8String(buffer);
    grotesqueTicks = buffer.readInt();
    nightmare = buffer.readInt();
    ghost = buffer.readBoolean();
    creatureType = buffer.readInt();
    blood = buffer.readInt();
    playerSkin = ByteBufUtils.readUTF8String(buffer);
  }
  
  public static class Handler implements cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketPlayerStyle, IMessage> {
    public Handler() {}
    
    public IMessage onMessage(PacketPlayerStyle message, MessageContext ctx) { EntityPlayer player = Witchery.proxy.getPlayer(ctx);
      EntityPlayer otherPlayer = field_70170_p.func_72924_a(username);
      if (otherPlayer != null) {
        NBTTagCompound nbtOtherPlayer = Infusion.getNBT(otherPlayer);
        if (grotesqueTicks > 0) {
          nbtOtherPlayer.func_74768_a("witcheryGrotesque", grotesqueTicks);
        } else if (nbtOtherPlayer.func_74764_b("witcheryGrotesque")) {
          nbtOtherPlayer.func_82580_o("witcheryGrotesque");
        }
        WorldProviderDreamWorld.setPlayerHasNightmare(nbtOtherPlayer, nightmare > 0, nightmare > 1);
        WorldProviderDreamWorld.setPlayerIsGhost(nbtOtherPlayer, ghost);
        ExtendedPlayer playerEx = ExtendedPlayer.get(otherPlayer);
        playerEx.setCreatureTypeOrdinal(creatureType);
        playerEx.setHumanBlood(blood);
        playerEx.setOtherPlayerSkin(playerSkin);
      }
      return null;
    }
  }
}
