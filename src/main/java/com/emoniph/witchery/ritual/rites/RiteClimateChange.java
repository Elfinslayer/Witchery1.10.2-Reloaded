package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class RiteClimateChange extends com.emoniph.witchery.ritual.Rite
{
  protected final int radius;
  
  public static enum WeatherChange
  {
    NONE,  SUN,  RAIN,  THUNDER;
    
    private WeatherChange() {} }
  
  public RiteClimateChange(int radius) { this.radius = radius; }
  

  public void addSteps(ArrayList<RitualStep> steps, int intialStage)
  {
    steps.add(new StepClimateChange(this, intialStage));
  }
  
  private static class StepClimateChange extends RitualStep
  {
    private final RiteClimateChange rite;
    private int stage = 0;
    private boolean activated;
    
    public StepClimateChange(RiteClimateChange rite, int initialStage) {
      super();
      this.rite = rite;
      stage = initialStage;
    }
    
    public int getCurrentStage()
    {
      return (byte)stage;
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
    
    public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
    {
      if (!activated) {
        if (ticks % 20L != 0L) {
          return RitualStep.Result.STARTING;
        }
        activated = true;
        SoundEffect.RANDOM_FIZZ.playAt(world, posX, posY, posZ);
      }
      

      if (!field_72995_K)
      {

        net.minecraft.entity.player.EntityPlayer player = ritual.getInitiatingPlayer(world);
        
        if (!instanceallowBiomeChanging) {
          SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
          if (player != null) {
            ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.disabled", new Object[0]);
          }
          return RitualStep.Result.ABORTED_REFUND;
        }
        
        BiomeGenBase biome = world.func_72807_a(posX, posZ);
        if ((field_73011_w.field_76574_g == 1) || (field_73011_w.field_76574_g == -1) || (biome == BiomeGenBase.field_76779_k) || (biome == BiomeGenBase.field_76778_j)) {
          SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
          if (player != null) {
            ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.wrongdimension", new Object[0]);
          }
          return RitualStep.Result.ABORTED_REFUND;
        }
        
        if (covenSize < 4) {
          SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
          if (player != null) {
            ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.coventoosmall", new Object[0]);
          }
          return RitualStep.Result.ABORTED_REFUND;
        }
        
        if (ticks % 20L == 0L) {
          stage += 1;
          
          if (stage < 5) {
            ParticleEffect.INSTANT_SPELL.send(SoundEffect.NONE, world, 0.5D + posX, 1.0D + posY, 0.5D + posZ, stage * 1.5F, stage * 1.1F, 16);
          }
          else if (stage == 5) {
            ParticleEffect.HUGE_EXPLOSION.send(SoundEffect.NONE, world, 0.5D + posX, 1.0D + posY, 0.5D + posZ, stage * 2.0F, stage * 1.5F, 16);
            
            double RADIUS = 8.0D;
            java.util.List items = world.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a(posX - 8.0D, posY - 2, posZ - 8.0D, posX + 8.0D, posY + 2, posZ + 8.0D));
            

            BiomeDictionary.Type biomeType = BiomeDictionary.Type.END;
            RiteClimateChange.WeatherChange weather = RiteClimateChange.WeatherChange.NONE;
            int glowstone = 0;
            for (Object obj : items) {
              EntityItem item = (EntityItem)obj;
              ItemStack stack = item.func_92059_d();
              if (stack.func_77969_a(new ItemStack(Blocks.field_150345_g, 1, 0))) {
                biomeType = BiomeDictionary.Type.FOREST;
              } else if (stack.func_77969_a(new ItemStack(Blocks.field_150329_H, 1, 1))) {
                biomeType = BiomeDictionary.Type.PLAINS;
              } else if (stack.func_77969_a(new ItemStack(Blocks.field_150343_Z))) {
                biomeType = BiomeDictionary.Type.MOUNTAIN;
              } else if (stack.func_77969_a(new ItemStack(Blocks.field_150348_b))) {
                biomeType = BiomeDictionary.Type.HILLS;
              } else if (stack.func_77969_a(new ItemStack(Items.field_151123_aH))) {
                biomeType = BiomeDictionary.Type.SWAMP;
              } else if (stack.func_77969_a(new ItemStack(Items.field_151131_as))) {
                biomeType = BiomeDictionary.Type.WATER;
              } else if (stack.func_77969_a(new ItemStack(Blocks.field_150434_aF))) {
                biomeType = BiomeDictionary.Type.DESERT;
                weather = RiteClimateChange.WeatherChange.SUN;
              } else if (stack.func_77969_a(ItemsGENERIC.itemIcyNeedle.createStack())) {
                biomeType = BiomeDictionary.Type.FROZEN;
                weather = RiteClimateChange.WeatherChange.RAIN;
              } else if (stack.func_77969_a(new ItemStack(Blocks.field_150345_g, 1, 3))) {
                biomeType = BiomeDictionary.Type.JUNGLE;
              } else if (stack.func_77969_a(new ItemStack(Blocks.field_150424_aL))) {
                biomeType = BiomeDictionary.Type.WASTELAND;
              } else if (stack.func_77969_a(new ItemStack(Blocks.field_150354_m))) {
                biomeType = BiomeDictionary.Type.BEACH;
              } else if (stack.func_77969_a(new ItemStack(Blocks.field_150337_Q))) {
                biomeType = BiomeDictionary.Type.MUSHROOM;
              } else if (stack.func_77969_a(new ItemStack(Items.field_151144_bL))) {
                biomeType = BiomeDictionary.Type.MAGICAL;
              } else { if (stack.func_77973_b() != Items.field_151114_aO) continue;
                glowstone += field_77994_a;
              }
              

              world.func_72900_e(item);
              ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_POP, item, 0.5D, 1.0D, 16);
            }
            
            if (biomeType == BiomeDictionary.Type.END) {
              SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
              
              if (player != null) {
                ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.missingbiomefoci", new Object[0]);
              }
              return RitualStep.Result.ABORTED_REFUND;
            }
            
            BiomeGenBase[] biomes = BiomeDictionary.getBiomesForType(biomeType);
            if ((biomes == null) || (biomes.length == 0)) {
              SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
              
              if (player != null) {
                ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "witchery.rite.missingbiomefoci", new Object[0]);
              }
              return RitualStep.Result.ABORTED_REFUND;
            }
            


            int biomeID = 0field_76756_M;
            
            int maxRadius = rite.radius * (covenSize - 3);
            
            HashMap<ChunkCoord, byte[]> chunkMap = new HashMap();
            
            drawFilledCircle(world, posX, posZ, maxRadius, chunkMap, weather, biomeID);
            
            ArrayList chunks = new ArrayList();
            
            for (Map.Entry<ChunkCoord, byte[]> entry : chunkMap.entrySet()) {
              Chunk chunk = ((ChunkCoord)entry.getKey()).getChunk(world);
              chunk.func_76616_a((byte[])entry.getValue());
              

              chunks.add(chunk);
            }
            

            S26PacketMapChunkBulk packet = new S26PacketMapChunkBulk(chunks);
            Witchery.packetPipeline.sendToDimension(packet, world);
            







            for (Object obj : chunks) {
              Chunk chunk = (Chunk)obj;
              for (Object tileObj : field_150816_i.values()) {
                TileEntity tile = (TileEntity)tileObj;
                net.minecraft.network.Packet packet2 = tile.func_145844_m();
                if (packet2 != null) {
                  world.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
                }
              }
            }
            

            if ((world instanceof WorldServer))
            {
              WorldInfo worldinfo = ((WorldServer)world).func_72912_H();
              int i = (300 + field_73012_v.nextInt(600)) * 20;
              switch (RiteClimateChange.1.$SwitchMap$com$emoniph$witchery$ritual$rites$RiteClimateChange$WeatherChange[weather.ordinal()]) {
              case 1: 
                if ((world.func_72896_J()) || (world.func_72911_I())) {
                  worldinfo.func_76080_g(0);
                  worldinfo.func_76090_f(0);
                  worldinfo.func_76084_b(false);
                  worldinfo.func_76069_a(false);
                }
                break;
              case 2: 
                if ((!world.func_72896_J()) && (!world.func_72911_I())) {
                  worldinfo.func_76080_g(i);
                  worldinfo.func_76090_f(i);
                  worldinfo.func_76084_b(true);
                  worldinfo.func_76069_a(false);
                }
                break;
              case 3: 
                if (!world.func_72911_I()) {
                  worldinfo.func_76080_g(i);
                  worldinfo.func_76090_f(i);
                  worldinfo.func_76084_b(true);
                  worldinfo.func_76069_a(true);
                }
                
                break;
              }
              
            }
            
            return RitualStep.Result.COMPLETED;
          }
          
          return RitualStep.Result.UPKEEP;
        }
        return RitualStep.Result.UPKEEP;
      }
      
      return RitualStep.Result.COMPLETED;
    }
    
    private static byte[] rotateMatrix(byte[] matrix, int n) {
      byte[] ret = new byte[matrix.length];
      
      for (int i = 0; i < matrix.length / n; i++) {
        for (int j = 0; j < n; j++) {
          ret[(j * n + i)] = matrix[(i * n + n - j)];
        }
      }
      
      return ret;
    }
    
    protected void drawFilledCircle(World world, int x0, int z0, int radius, HashMap<ChunkCoord, byte[]> chunkMap, RiteClimateChange.WeatherChange weather, int biomeID) {
      int x = radius;
      int z = 0;
      int radiusError = 1 - x;
      
      while (x >= z) {
        drawLine(world, -x + x0, x + x0, z + z0, chunkMap, weather, biomeID);
        drawLine(world, -z + x0, z + x0, x + z0, chunkMap, weather, biomeID);
        drawLine(world, -x + x0, x + x0, -z + z0, chunkMap, weather, biomeID);
        drawLine(world, -z + x0, z + x0, -x + z0, chunkMap, weather, biomeID);
        
        z++;
        
        if (radiusError < 0) {
          radiusError += 2 * z + 1;
        } else {
          x--;
          radiusError += 2 * (z - x + 1);
        }
      }
    }
    
    protected void drawLine(World world, int x1, int x2, int z, HashMap<ChunkCoord, byte[]> chunkMap, RiteClimateChange.WeatherChange weather, int biomeID) {
      for (int x = x1; x <= x2; x++) {
        ChunkCoord coord = new ChunkCoord(x >> 4, z >> 4);
        byte[] map = (byte[])chunkMap.get(coord);
        if (map == null) {
          Chunk chunk = world.func_72938_d(x, z);
          map = (byte[])chunk.func_76605_m().clone();
          chunkMap.put(coord, map);
        }
        
        map[((z & 0xF) << 4 | x & 0xF)] = ((byte)biomeID);
        
        if (weather == RiteClimateChange.WeatherChange.SUN) {
          int y = world.func_72825_h(x, z);
          if (world.func_147439_a(x, y, z) == Blocks.field_150433_aE) {
            world.func_147468_f(x, y, z);
          }
        }
      }
    }
  }
}
