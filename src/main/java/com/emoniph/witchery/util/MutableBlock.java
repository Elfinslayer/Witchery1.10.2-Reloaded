package com.emoniph.witchery.util;

import net.minecraft.block.Block;

public class MutableBlock
{
  private final Block block;
  private final int metadata;
  private final int newMetadata;
  
  public MutableBlock(Block block) {
    this(block, -1, 0);
  }
  
  public MutableBlock(Block block, int metadata) {
    this(block, metadata, 0);
  }
  
  public MutableBlock(Block block, int metadata, int newMetadata) {
    this.block = block;
    this.metadata = metadata;
    this.newMetadata = newMetadata;
  }
  
  public MutableBlock(String extra) {
    String name = extra;
    int meta = 0;
    int comma = extra.lastIndexOf(',');
    if (comma >= 0) {
      name = extra.substring(0, comma);
      
      String metaString = extra.substring(comma + 1);
      meta = Integer.parseInt(metaString);
    }
    

    block = Block.func_149684_b(name);
    metadata = meta;
    newMetadata = 0;
  }
  
  public boolean equals(Object obj)
  {
    if (obj == this) {
      return true;
    }
    if ((obj == null) || (obj.getClass() != getClass())) {
      return false;
    }
    MutableBlock other = (MutableBlock)obj;
    return (block == block) && ((metadata == -1) || (metadata == -1) || (metadata == metadata));
  }
  
  public void mutate(net.minecraft.world.World world, int posX, int posY, int posZ) {
    mutate(world, posX, posY, posZ, true);
  }
  
  public void mutate(net.minecraft.world.World world, int posX, int posY, int posZ, boolean allowAnyPlacement) {
    try {
      if (metadata != -1) {
        if ((allowAnyPlacement) || (block.func_149742_c(world, posX, posY, posZ))) {
          world.func_147465_d(posX, posY, posZ, block, metadata, 3);
        }
      } else if (newMetadata > 0) {
        if ((allowAnyPlacement) || (block.func_149742_c(world, posX, posY, posZ))) {
          world.func_147465_d(posX, posY, posZ, block, newMetadata, 3);
        }
      }
      else if ((allowAnyPlacement) || (block.func_149742_c(world, posX, posY, posZ))) {
        world.func_147449_b(posX, posY, posZ, block);
      }
    }
    catch (Exception e) {
      Log.instance().debug(String.format("Exception occured mutating a plant %s", new Object[] { e.toString() }));
    }
  }
}
