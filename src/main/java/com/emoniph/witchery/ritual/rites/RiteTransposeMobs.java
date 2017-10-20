package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteTransposeMobs extends com.emoniph.witchery.ritual.Rite
{
  protected final int radius;
  protected final int pulses;
  protected final int minDistance;
  
  public RiteTransposeMobs(int radius, int minDistance, int pulses)
  {
    this.radius = radius;
    this.pulses = pulses;
    this.minDistance = minDistance;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int initialStep)
  {
    steps.add(new StepTeleportation(this, initialStep));
  }
  
  private static class StepTeleportation extends RitualStep
  {
    private final RiteTransposeMobs rite;
    private int step;
    
    public StepTeleportation(RiteTransposeMobs rite, int initialStep) {
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
      if (ticks % 20L != 0L) {
        return RitualStep.Result.STARTING;
      }
      
      step += 1;
      int r = rite.radius;
      AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - r, 1.0D, posZ - r, posX + r, posY - 1, posZ + r);
      for (Object obj : world.func_72872_a(EntityMob.class, bounds)) {
        EntityMob entity = (EntityMob)obj;
        world.func_72900_e(entity);
        field_70128_L = false;
        int activeRadius = rite.radius;
        int ax = field_73012_v.nextInt(activeRadius * 2 + 1);
        if (ax > activeRadius) {
          ax += rite.minDistance * 2;
        }
        int x = posX - rite.radius - rite.minDistance + ax;
        
        int az = field_73012_v.nextInt(activeRadius * 2 + 1);
        if (az > activeRadius) {
          az += rite.minDistance * 2;
        }
        
        int z = posZ - rite.radius - rite.minDistance + az;
        entity.func_70012_b(x, posY, z, 0.0F, 0.0F);
        world.func_72838_d(entity);
        ParticleEffect.PORTAL.send(SoundEffect.RANDOM_FIZZ, entity, 0.5D, 2.0D, 16);
      }
      
      return step >= rite.pulses ? RitualStep.Result.COMPLETED : RitualStep.Result.UPKEEP;
    }
  }
}
