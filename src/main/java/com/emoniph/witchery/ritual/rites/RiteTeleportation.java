package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import java.util.ArrayList;
import net.minecraft.world.World;

public abstract class RiteTeleportation
  extends Rite
{
  protected final int radius;
  
  public RiteTeleportation(int radius)
  {
    this.radius = radius;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int intialStage)
  {
    steps.add(new StepTeleportation(this));
  }
  
  protected abstract boolean teleport(World paramWorld, int paramInt1, int paramInt2, int paramInt3, BlockCircle.TileEntityCircle.ActivatedRitual paramActivatedRitual);
  
  private static class StepTeleportation extends RitualStep
  {
    private final RiteTeleportation rite;
    
    public StepTeleportation(RiteTeleportation rite) {
      super();
      this.rite = rite;
    }
    
    public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
    {
      if (ticks % 20L != 0L) {
        return RitualStep.Result.STARTING;
      }
      
      if (rite.teleport(world, posX, posY, posZ, ritual)) {
        return RitualStep.Result.COMPLETED;
      }
      return RitualStep.Result.ABORTED_REFUND;
    }
  }
}
