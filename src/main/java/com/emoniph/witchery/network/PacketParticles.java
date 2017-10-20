package com.emoniph.witchery.network;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.CommonProxy;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;





public class PacketParticles
  implements IMessage
{
  private ParticleEffect particleEffect;
  private SoundEffect soundEffect;
  private double x;
  private double y;
  private double z;
  private double width;
  private double height;
  private int color;
  
  public PacketParticles() {}
  
  public PacketParticles(ParticleEffect particleEffect, SoundEffect soundEffect, double x, double y, double z, double width, double height, int color)
  {
    this.particleEffect = particleEffect;
    this.soundEffect = (soundEffect != null ? soundEffect : SoundEffect.NONE);
    this.x = x;
    this.y = y;
    this.z = z;
    this.width = width;
    this.height = height;
    this.color = color;
  }
  
  public PacketParticles(ParticleEffect particleEffect, SoundEffect soundEffect, Entity targetEntity, double width, double height)
  {
    this(particleEffect, soundEffect, field_70165_t, field_70163_u, field_70161_v, width, height, 16777215);
  }
  
  public PacketParticles(ParticleEffect particleEffect, SoundEffect soundEffect, Entity targetEntity, double width, double height, int color)
  {
    this(particleEffect, soundEffect, field_70165_t, field_70163_u, field_70161_v, width, height, color);
  }
  
  public void toBytes(ByteBuf buffer)
  {
    buffer.writeInt(particleEffect.ordinal());
    buffer.writeInt(soundEffect.ordinal());
    buffer.writeDouble(x);
    buffer.writeDouble(y);
    buffer.writeDouble(z);
    buffer.writeDouble(width);
    buffer.writeDouble(height);
    buffer.writeInt(color);
  }
  
  public void fromBytes(ByteBuf buffer)
  {
    int ordinalParticle = buffer.readInt();
    particleEffect = ParticleEffect.values()[ordinalParticle];
    int ordinalSound = buffer.readInt();
    soundEffect = SoundEffect.values()[ordinalSound];
    x = buffer.readDouble();
    y = buffer.readDouble();
    z = buffer.readDouble();
    width = buffer.readDouble();
    height = buffer.readDouble();
    color = buffer.readInt();
  }
  
  public static class Handler implements IMessageHandler<PacketParticles, IMessage> {
    public Handler() {}
    
    public IMessage onMessage(PacketParticles message, MessageContext ctx) { EntityPlayer player = Witchery.proxy.getPlayer(ctx);
      World world = field_70170_p;
      
      double x = x;
      double y = y;
      double z = z;
      double width = width;
      double height = height;
      SoundEffect sound = soundEffect;
      int color = color;
      ParticleEffect particle = particleEffect;
      
      Witchery.proxy.showParticleEffect(world, x, y, z, width, height, sound, color, particle);
      return null;
    }
  }
}
