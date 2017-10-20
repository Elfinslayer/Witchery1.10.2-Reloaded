package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RiteTransposeOres extends com.emoniph.witchery.ritual.Rite
{
  protected final int radius;
  protected final int pulses;
  protected final Block[] blocks;
  
  public RiteTransposeOres(int radius, int pulses, Block[] blocks)
  {
    this.radius = radius;
    this.pulses = pulses;
    this.blocks = blocks;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int initialStep)
  {
    steps.add(new StepTeleportation(this, initialStep));
  }
  
  private static class StepTeleportation extends RitualStep
  {
    private final RiteTransposeOres rite;
    private int step;
    
    public StepTeleportation(RiteTransposeOres rite, int initialStep) {
      super();
      this.rite = rite;
      step = initialStep;
    }
    
    public int getCurrentStage()
    {
      return step;
    }
    
    public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
    {
      if (ticks % 10L != 0L) {
        return RitualStep.Result.STARTING;
      }
      
      step += 1;
      int r = rite.radius;
      int y = posY - step;
      int blockTypes = covenSize == 6 ? 2 : 1;
      for (int x = posX - r; x <= posX + r; x++) {
        for (int z = posZ - r; z <= posZ + r; z++) {
          Block blockID = world.func_147439_a(x, y, z);
          for (int t = 0; t < blockTypes; t++) {
            if (blockID == rite.blocks[t]) {
              ItemStack stack = new ItemStack(rite.blocks[t]);
              EntityItem entity = new EntityItem(world, posX - r + field_73012_v.nextInt(2 * r + 1), posY + 2, posZ - r + field_73012_v.nextInt(2 * r + 1), stack);
              if (!field_72995_K) {
                world.func_147468_f(x, y, z);
                world.func_72838_d(entity);
              }
            }
          }
        }
      }
      
      return (step >= rite.pulses + 5 * covenSize) || (y <= 2) ? RitualStep.Result.COMPLETED : RitualStep.Result.UPKEEP;
    }
  }
}
