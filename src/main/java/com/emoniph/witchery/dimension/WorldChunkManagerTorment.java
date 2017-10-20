package com.emoniph.witchery.dimension;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class WorldChunkManagerTorment implements IChunkProvider
{
  public static final int NUM_LEVELS = 6;
  public static final int BASE_LEVEL = 10;
  public static final int LEVEL_HEIGHT = 15;
  public static final int MAZE_SIZE = 31;
  private World world;
  
  public WorldChunkManagerTorment(World world)
  {
    this.world = world;
  }
  
  public boolean func_73149_a(int i, int j)
  {
    return true;
  }
  
  public Chunk func_73154_d(int x, int z)
  {
    Chunk chunk = new Chunk(world, x, z);
    byte[] abyte = chunk.func_76605_m();
    
    for (int k = 0; k < abyte.length; k++) {
      abyte[k] = ((byte)field_76778_jfield_76756_M);
    }
    
    for (int y = 0; y < 255; y++)
    {
      int l = y >> 4;
      
      ExtendedBlockStorage extendedblockstorage = chunk.func_76587_i()[l];
      
      if (extendedblockstorage == null) {
        extendedblockstorage = new ExtendedBlockStorage(y, !world.field_73011_w.field_76576_e);
        chunk.func_76587_i()[l] = extendedblockstorage;
      }
      
      for (int _x = 0; _x < 16; _x++) {
        for (int _z = 0; _z < 16; _z++) {
          Block blockId = Blocks.field_150350_a;
          extendedblockstorage.func_150818_a(_x, y & 0xF, _z, blockId);
          extendedblockstorage.func_76654_b(_x, y & 0xF, _z, 0);
        }
      }
    }
    
    return chunk;
  }
  
  public Chunk func_73158_c(int x, int z)
  {
    return func_73154_d(x, z);
  }
  
  public void func_73153_a(IChunkProvider ichunkprovider, int i, int j)
  {
    if ((i == 0) && (j == 0)) {
      for (int slot = 0; slot < 6; slot++) {
        GenerateMaze maze = new GenerateMaze(31, 31, world.field_73012_v);
        maze.display(world, i * 16 + 8 - 31, 10 + slot * 15, j * 16 + 8 - 2, BlocksFORCE, BlocksTORMENT_STONE);
      }
    }
  }
  
  public boolean func_73151_a(boolean flag, IProgressUpdate iprogressupdate)
  {
    return true;
  }
  
  public boolean func_73156_b()
  {
    return false;
  }
  
  public boolean func_73157_c()
  {
    return true;
  }
  
  public String func_73148_d()
  {
    return "TormentChunk";
  }
  

  public List func_73155_a(EnumCreatureType enumcreaturetype, int i, int j, int k)
  {
    return null;
  }
  
  public ChunkPosition func_147416_a(World world, String s, int i, int j, int k)
  {
    return null;
  }
  
  public int func_73152_e()
  {
    return 0;
  }
  
  public void func_82695_e(int i, int j) {}
  
  public void func_104112_b() {}
}
