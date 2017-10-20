package com.emoniph.witchery.network;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.CommonProxy;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class PacketSound implements IMessage
{
  private SoundEffect effect;
  private double x;
  private double y;
  private double z;
  private float volume;
  private float pitch;
  
  public PacketSound() {}
  
  public PacketSound(SoundEffect soundEffect, Entity location)
  {
    this(soundEffect, location, -1.0F, -1.0F);
  }
  
  public PacketSound(SoundEffect soundEffect, Entity location, float volume, float pitch) {
    effect = soundEffect;
    x = field_70165_t;
    y = field_70163_u;
    z = field_70161_v;
    this.volume = volume;
    this.pitch = pitch;
  }
  
  public void toBytes(ByteBuf buffer)
  {
    buffer.writeInt(effect.ordinal());
    buffer.writeDouble(x);
    buffer.writeDouble(y);
    buffer.writeDouble(z);
    buffer.writeFloat(volume);
    buffer.writeFloat(pitch);
  }
  
  public void fromBytes(ByteBuf buffer)
  {
    effect = SoundEffect.values()[buffer.readInt()];
    x = buffer.readDouble();
    y = buffer.readDouble();
    z = buffer.readDouble();
    volume = buffer.readFloat();
    pitch = buffer.readFloat();
  }
  
  public static class Handler implements cpw.mods.fml.common.network.simpleimpl.IMessageHandler<PacketSound, IMessage> {
    public Handler() {}
    
    public IMessage onMessage(PacketSound message, MessageContext ctx) { EntityPlayer player = Witchery.proxy.getPlayer(ctx);
      if (volume == -1.0F) {
        volume = 0.5F;
      }
      
      if (pitch == -1.0F) {
        pitch = (0.4F / ((float)field_70170_p.field_73012_v.nextDouble() * 0.4F + 0.8F));
      }
      
      field_70170_p.func_72980_b(x, y, z, effect.toString(), volume, pitch, false);
      return null;
    }
  }
}
