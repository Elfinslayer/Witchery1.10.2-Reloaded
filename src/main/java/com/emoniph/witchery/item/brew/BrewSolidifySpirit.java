package com.emoniph.witchery.item.brew;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.item.ItemGeneral.Brew.BrewResult;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.ParticleEffect;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BrewSolidifySpirit extends com.emoniph.witchery.item.ItemGeneral.Brew
{
  protected Block replacementBlock;
  
  public BrewSolidifySpirit(int subItemID, String unlocalisedName, Block block)
  {
    super(subItemID, unlocalisedName);
    replacementBlock = block;
  }
  
  public boolean isSolidifier()
  {
    return true;
  }
  
  public ItemGeneral.Brew.BrewResult onImpact(World world, EntityLivingBase thrower, MovingObjectPosition mop, boolean enhanced, double brewX, double brewY, double brewZ, AxisAlignedBB brewBounds) {
    if (field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.ENTITY) {
      return ItemGeneral.Brew.BrewResult.DROP_ITEM;
    }
    
    Block blockHit = BlockUtil.getBlock(world, mop);
    int x = field_72311_b;
    int y = field_72312_c;
    int z = field_72309_d;
    if (blockHit != BlocksHOLLOW_TEARS) {
      switch (field_72310_e) {
      case 0: 
        y--;
        break;
      case 1: 
        y++;
        break;
      
      case 2: 
        z--;
        break;
      case 3: 
        z++;
        break;
      case 4: 
        x--;
        break;
      case 5: 
        x++;
      }
      
      
      blockHit = BlockUtil.getBlock(world, x, y, z);
      if (blockHit != BlocksHOLLOW_TEARS) {
        return ItemGeneral.Brew.BrewResult.DROP_ITEM;
      }
    }
    
    SpreadEffect.spread(world, x, y, z, 64, new SpreadEffect(new Block[] { BlocksHOLLOW_TEARS })
    {
      public boolean doEffect(World world, int posX, int posY, int posZ, Block block) {
        ParticleEffect.INSTANT_SPELL.send(com.emoniph.witchery.util.SoundEffect.NONE, world, 0.5D + posX, 1.5D + posY, 0.5D + posZ, 2.0D, 2.0D, 16);
        
        if (replacementBlock == null) {
          world.func_147468_f(posX, posY, posZ);
          Block blockBelow = BlockUtil.getBlock(world, posX, posY - 1, posZ);
          if ((blockBelow != null) && (com.emoniph.witchery.util.BlockProtect.canBreak(blockBelow, world))) {
            world.func_147468_f(posX, posY - 1, posZ);
          }
        } else {
          BlockUtil.setBlock(world, posX, posY, posZ, replacementBlock, 0, 3);
        }
        return true;
      }
      

    });
    return ItemGeneral.Brew.BrewResult.SHOW_EFFECT;
  }
  
  public static abstract class SpreadEffect {
    protected Block[] blocks;
    
    public SpreadEffect(Block... blocksToSpreadOver) { blocks = blocksToSpreadOver; }
    
    public abstract boolean doEffect(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Block paramBlock);
    
    public static void spread(World world, int x0, int y0, int z0, int range, SpreadEffect effect)
    {
      spread(world, x0, y0, z0, x0, y0, z0, range, effect);
    }
    
    private static void spread(World world, int x, int y, int z, int x0, int y0, int z0, int range, SpreadEffect effect) {
      if ((Math.abs(x0 - x) >= range) || (Math.abs(y0 - y) >= range) || (Math.abs(z0 - z) >= range)) {
        return;
      }
      if (checkEffect(world, x + 1, y, z, effect)) {
        spread(world, x + 1, y, z, x0, y0, z0, range, effect);
      }
      
      if (checkEffect(world, x - 1, y, z, effect)) {
        spread(world, x - 1, y, z, x0, y0, z0, range, effect);
      }
      
      if (checkEffect(world, x, y, z + 1, effect)) {
        spread(world, x, y, z + 1, x0, y0, z0, range, effect);
      }
      
      if (checkEffect(world, x, y, z - 1, effect)) {
        spread(world, x, y, z - 1, x0, y0, z0, range, effect);
      }
      
      if (checkEffect(world, x, y + 1, z, effect)) {
        spread(world, x, y + 1, z, x0, y0, z0, range, effect);
      }
      
      if (checkEffect(world, x, y - 1, z, effect)) {
        spread(world, x, y - 1, z, x0, y0, z0, range, effect);
      }
    }
    
    private static boolean checkEffect(World world, int x, int y, int z, SpreadEffect effect) {
      boolean continueSpread = false;
      
      Block foundBlock = BlockUtil.getBlock(world, x, y, z);
      if (foundBlock == null) {
        return continueSpread;
      }
      
      for (Block block : blocks) {
        if (foundBlock == block) {
          continueSpread = effect.doEffect(world, x, y, z, block);
          break;
        }
      }
      
      return continueSpread;
    }
  }
}
