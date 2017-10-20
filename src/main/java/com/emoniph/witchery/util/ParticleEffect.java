package com.emoniph.witchery.util;

import com.emoniph.witchery.network.PacketParticles;
import com.emoniph.witchery.network.PacketPipeline;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public enum ParticleEffect
{
  HUGE_EXPLOSION("hugeexplosion"),  LARGE_EXPLODE("largeexplode"),  WATER_BUBBLE("bubble"),  SUSPENDED("suspended"),  DEPTH_SUSPEND("depthsuspend"), 
  TOWN_AURA("townaura"),  CRIT("crit"),  MAGIC_CRIT("magicCrit"),  SMOKE("smoke"),  MOB_SPELL("mobSpell"), 
  SPELL("spell"),  INSTANT_SPELL("instantSpell"),  NOTE("note"),  PORTAL("portal"),  ENCHANTMENT_TABLE("enchantmenttable"), 
  EXPLODE("explode"),  FLAME("flame"),  LAVA("lava"),  FOOTSTEP("footstep"),  SPLASH("splash"), 
  LARGE_SMOKE("largesmoke"),  CLOUD("cloud"),  REDDUST("reddust"),  SNOWBALL_POOF("snowballpoof"),  DRIP_WATER("dripWater"), 
  DRIP_LAVA("dripLava"),  SNOW_SHOVEL("snowshovel"),  SLIME("slime"),  HEART("heart"),  ICON_CRACK("iconcrack_"), 
  TILE_CRACK("tilecrack_"),  SPELL_COLORED("spell");
  
  final String particleID;
  
  private ParticleEffect(String particleID) {
    this.particleID = particleID;
  }
  
  public String toString()
  {
    return particleID;
  }
  
  public void send(SoundEffect sound, World world, double x, double y, double z, double width, double height, int range)
  {
    send(sound, world, x, y, z, width, height, range, 16777215);
  }
  
  public void send(SoundEffect sound, World world, double x, double y, double z, double width, double height, int range, int color)
  {
    if (!field_72995_K) {
      com.emoniph.witchery.Witchery.packetPipeline.sendToAllAround(new PacketParticles(this, sound, x, y, z, width, height, color), TargetPointUtil.from(world, x, y, z, range));
    }
  }
  

  public void send(SoundEffect sound, Entity entity, double width, double height, int range)
  {
    if (!field_70170_p.field_72995_K) {
      com.emoniph.witchery.Witchery.packetPipeline.sendToAllAround(new PacketParticles(this, sound, entity, width, height), TargetPointUtil.from(entity, range));
    }
  }
  
  public void send(SoundEffect sound, Entity entity, double width, double height, int range, int color)
  {
    if (!field_70170_p.field_72995_K) {
      com.emoniph.witchery.Witchery.packetPipeline.sendToAllAround(new PacketParticles(this, sound, entity, width, height, color), TargetPointUtil.from(entity, range));
    }
  }
  
  public void send(SoundEffect sound, TileEntity tile, double width, double height, int range, int color)
  {
    if (!func_145831_wfield_72995_K) {
      com.emoniph.witchery.Witchery.packetPipeline.sendToAllAround(new PacketParticles(this, sound, 0.5D + field_145851_c, 0.5D + field_145848_d, 0.5D + field_145849_e, width, height, color), TargetPointUtil.from(tile.func_145831_w(), field_145851_c, field_145848_d, field_145849_e, range));
    }
  }
  
  public void send(SoundEffect sound, World world, Coord center, double width, double height, int range)
  {
    send(sound, world, x + 0.5D, y, z + 0.5D, width, height, range);
  }
}
