package com.emoniph.witchery.brewing.action.effect;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.AltarPower;
import com.emoniph.witchery.brewing.BrewItemKey;
import com.emoniph.witchery.brewing.BrewNamePart;
import com.emoniph.witchery.brewing.EffectLevel;
import com.emoniph.witchery.brewing.ModifiersEffect;
import com.emoniph.witchery.brewing.ModifiersImpact;
import com.emoniph.witchery.brewing.ModifiersRitual;
import com.emoniph.witchery.brewing.Probability;
import com.emoniph.witchery.brewing.action.BrewActionEffect;
import com.emoniph.witchery.brewing.action.BrewActionList;
import com.emoniph.witchery.item.ItemBook;
import com.emoniph.witchery.network.PacketPipeline;
import com.emoniph.witchery.util.Coord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.ForgeDirection;

public class BrewActionBiomeChange extends BrewActionEffect
{
  public BrewActionBiomeChange(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, Probability baseProbability, EffectLevel effectLevel)
  {
    super(itemKey, namePart, powerCost, baseProbability, effectLevel);
  }
  
  public void prepareSplashPotion(World world, BrewActionList actionList, ModifiersImpact modifiers)
  {
    super.prepareSplashPotion(world, actionList, modifiers);
    modifiers.setOnlyInstant();
  }
  

  protected void doApplyRitualToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack)
  {
    BiomeGenBase biome = ItemBook.getSelectedBiome(stack.func_77960_j());
    int maxRadius = 16 + modifiers.getStrength() * 16;
    changeBiome(world, new Coord(x, y, z), maxRadius, biome);
  }
  

  protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
  {
    BiomeGenBase biome = ItemBook.getSelectedBiome(actionStack.func_77960_j());
    changeBiome(world, new Coord(x, y, z), 1 + modifiers.getStrength(), biome);
  }
  

  protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack) {}
  

  protected void changeBiome(World world, Coord coord, int radius, BiomeGenBase biome)
  {
    HashMap<ChunkCoord, byte[]> chunkMap = new HashMap();
    drawFilledCircle(world, x, z, radius, chunkMap, biome);
    ArrayList<Chunk> chunks = new ArrayList();
    for (Map.Entry<ChunkCoord, byte[]> entry : chunkMap.entrySet()) {
      Chunk chunk = ((ChunkCoord)entry.getKey()).getChunk(world);
      chunk.func_76616_a((byte[])entry.getValue());
      chunks.add(chunk);
    }
    
    S26PacketMapChunkBulk packet = new S26PacketMapChunkBulk(chunks);
    Witchery.packetPipeline.sendToDimension(packet, world);
    
    for (Chunk chunk : chunks) {
      for (Object tileObj : field_150816_i.values()) {
        TileEntity tile = (TileEntity)tileObj;
        Packet packet2 = tile.func_145844_m();
        if (packet2 != null) {
          world.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
        }
      }
    }
  }
  
  private void drawFilledCircle(World world, int x0, int z0, int radius, HashMap<ChunkCoord, byte[]> chunkMap, BiomeGenBase biome)
  {
    if (radius == 1) {
      drawLine(world, x0, x0, z0, chunkMap, biome);
    } else {
      radius--;
      int x = radius;
      int z = 0;
      int radiusError = 1 - x;
      
      while (x >= z) {
        drawLine(world, -x + x0, x + x0, z + z0, chunkMap, biome);
        drawLine(world, -z + x0, z + x0, x + z0, chunkMap, biome);
        drawLine(world, -x + x0, x + x0, -z + z0, chunkMap, biome);
        drawLine(world, -z + x0, z + x0, -x + z0, chunkMap, biome);
        
        z++;
        
        if (radiusError < 0) {
          radiusError += 2 * z + 1;
        } else {
          x--;
          radiusError += 2 * (z - x + 1);
        }
      }
    }
  }
  
  private void drawLine(World world, int x1, int x2, int z, HashMap<ChunkCoord, byte[]> chunkMap, BiomeGenBase biome)
  {
    for (int x = x1; x <= x2; x++) {
      ChunkCoord coord = new ChunkCoord(x >> 4, z >> 4);
      byte[] map = (byte[])chunkMap.get(coord);
      if (map == null) {
        Chunk chunk = world.func_72938_d(x, z);
        map = (byte[])chunk.func_76605_m().clone();
        chunkMap.put(coord, map);
      }
      
      map[((z & 0xF) << 4 | x & 0xF)] = ((byte)field_76756_M);
      
      if (field_76751_G == 0.0F) {
        int y = world.func_72825_h(x, z);
        if (world.func_147439_a(x, y, z) == net.minecraft.init.Blocks.field_150431_aC) {
          world.func_147468_f(x, y, z);
        }
      }
    }
  }
  
  private static class ChunkCoord {
    public final int X;
    public final int Z;
    
    public ChunkCoord(int x, int z) {
      X = x;
      Z = z;
    }
    
    public boolean equals(Object obj)
    {
      if (obj == this) {
        return true;
      }
      
      if ((obj == null) || (obj.getClass() != getClass())) {
        return false;
      }
      
      ChunkCoord other = (ChunkCoord)obj;
      return (X == X) && (Z == Z);
    }
    
    public int hashCode()
    {
      int result = X ^ X >>> 32;
      result = 31 * result + (Z ^ Z >>> 32);
      return result;
    }
    
    public Chunk getChunk(World world) {
      return world.func_72964_e(X, Z);
    }
  }
}
