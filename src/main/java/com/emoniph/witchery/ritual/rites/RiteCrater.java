package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.util.BlockProtect;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RiteCrater extends com.emoniph.witchery.ritual.Rite
{
  private final int radius;
  private final int height;
  
  public RiteCrater(int radius, int height)
  {
    this.radius = radius;
    this.height = height;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int intialStage)
  {
    steps.add(new StepCrater(this, intialStage));
  }
  
  private static class StepCrater extends RitualStep
  {
    private final RiteCrater rite;
    private int stage = 0;
    
    public StepCrater(RiteCrater rite, int initialStage) {
      super();
      this.rite = rite;
      stage = initialStage;
    }
    
    public int getCurrentStage()
    {
      return (byte)stage;
    }
    
    public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
    {
      if (ticks % 10L != 0L) {
        return RitualStep.Result.STARTING;
      }
      
      if (!field_72995_K)
      {
        if (++stage == 1) {
          ParticleEffect.PORTAL.send(SoundEffect.RANDOM_FIZZ, world, posX, posY, posZ, 0.5D, 1.0D, 16);
        }
        
        int height = rite.height;
        float radius = rite.radius;
        if (stage <= height) {
          for (int y = 1; y <= stage; y++) {
            float r = radius - (height - stage - 1 + y) * radius / height;
            Log.instance().debug(String.format("Stage: %d, r=%f y=%d", new Object[] { Integer.valueOf(stage), Float.valueOf(r), Integer.valueOf(y) }));
            drawFilledCircle(world, posX, posZ, posY - y, Math.max((int)Math.ceil(r), 1), posY);
          }
        } else {
          return RitualStep.Result.COMPLETED;
        }
        return RitualStep.Result.UPKEEP;
      }
      return RitualStep.Result.COMPLETED;
    }
    
    protected void drawFilledCircle(World world, int x0, int z0, int y, int radius, int height) {
      int x = radius;
      int z = 0;
      int radiusError = 1 - x;
      
      while (x >= z) {
        drawLine(world, -x + x0, x + x0, z + z0, y, x0, z0, radius, height);
        drawLine(world, -z + x0, z + x0, x + z0, y, x0, z0, radius, height);
        drawLine(world, -x + x0, x + x0, -z + z0, y, x0, z0, radius, height);
        drawLine(world, -z + x0, z + x0, -x + z0, y, x0, z0, radius, height);
        
        z++;
        
        if (radiusError < 0) {
          radiusError += 2 * z + 1;
        } else {
          x--;
          radiusError += 2 * (z - x + 1);
        }
      }
    }
    
    protected void drawLine(World world, int x1, int x2, int z, int y, int midX, int midZ, int radius, int midY) {
      int modX1 = (radius > 1) && (field_73012_v.nextInt(5) == 0) ? x1 + 1 : x1;
      int modX2 = (radius > 1) && (field_73012_v.nextInt(5) == 0) ? x2 - 1 : x2;
      boolean edgeZ = (midZ + radius == z) || (midZ - radius == z);
      
      for (int x = modX1; x <= modX2; x++) {
        drawPixel(world, x, z, y, midX, midY, midZ);
      }
      boolean done = true;
    }
    
    protected void drawPixel(World world, int x, int z, int y, int midX, int midY, int midZ) {
      if ((!field_72995_K) && 
        ((x != midX) || (z != midZ)) && ((y < midY - 3) || (Coord.distance(x, midY, z, midX, midY, midZ) > rite.radius - 3 - (midY - y)))) {
        Block blockID = world.func_147439_a(x, y, z);
        int blockMetadata = world.func_72805_g(x, y, z);
        if (BlockProtect.canBreak(x, y, z, world)) {
          world.func_147468_f(x, y, z);
          if ((blockID != Blocks.field_150350_a) && (blockID != Blocks.field_150348_b) && (blockID != Blocks.field_150346_d) && (blockID != Blocks.field_150349_c) && (blockID != Blocks.field_150354_m) && (blockID != Blocks.field_150322_A) && (blockID != Blocks.field_150351_n)) {
            ItemStack stack = new ItemStack(blockID, 1, blockMetadata);
            EntityItem entity = new EntityItem(world, x + 0.5D, y + 0.5D, z + 0.5D, stack);
            world.func_72838_d(entity);
          }
        }
      }
    }
  }
}
