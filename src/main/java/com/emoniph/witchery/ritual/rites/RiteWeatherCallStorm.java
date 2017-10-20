package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;

public class RiteWeatherCallStorm extends Rite
{
  private final int minRadius;
  private final int maxRadius;
  private final int bolts;
  
  public RiteWeatherCallStorm(int minRadius, int maxRadius, int bolts)
  {
    this.minRadius = minRadius;
    this.maxRadius = maxRadius;
    this.bolts = bolts;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int initialStage)
  {
    steps.add(new StepWeatherCallStorm(this, initialStage));
  }
  
  private static class StepWeatherCallStorm extends RitualStep
  {
    private final RiteWeatherCallStorm rite;
    private int stage;
    
    public StepWeatherCallStorm(RiteWeatherCallStorm rite, int initialStage) {
      super();
      this.rite = rite;
      stage = initialStage;
    }
    
    public int getCurrentStage()
    {
      return stage;
    }
    
    public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
    {
      if (ticks % 30L != 0L) {
        return RitualStep.Result.STARTING;
      }
      
      if (!field_72995_K) {
        stage += 1;
        
        switch (stage) {
        case 1: 
          spawnBolt(world, posX, posY, posZ);
          break;
        case 2: 
          spawnBolt(world, posX, posY, posZ);
          break;
        case 3: 
          spawnBolt(world, posX, posY, posZ);
          spawnBolt(world, posX, posY, posZ);
          break;
        case 4: 
          if (((world instanceof WorldServer)) && (!world.func_72911_I())) {
            WorldInfo worldinfo = ((WorldServer)world).func_72912_H();
            int i = (300 + field_73012_v.nextInt(600)) * 20;
            worldinfo.func_76080_g(i);
            worldinfo.func_76090_f(i);
            worldinfo.func_76084_b(true);
            worldinfo.func_76069_a(true);
          }
          spawnBolt(world, posX, posY, posZ);
          break;
        default: 
          for (int i = 0; i < field_73012_v.nextInt(4); i++) {
            spawnBolt(world, posX, posY, posZ);
            if (i > 0) {
              stage += 1;
            }
          }
        }
      }
      return stage < rite.bolts ? RitualStep.Result.STARTING : RitualStep.Result.COMPLETED;
    }
    
    private void spawnBolt(World world, int posX, int posY, int posZ) {
      int activeRadius = rite.maxRadius - rite.minRadius;
      int ax = field_73012_v.nextInt(activeRadius * 2 + 1);
      if (ax > activeRadius) {
        ax += rite.minRadius * 2;
      }
      int x = posX - rite.maxRadius + ax;
      
      int az = field_73012_v.nextInt(activeRadius * 2 + 1);
      if (az > activeRadius) {
        az += rite.minRadius * 2;
      }
      
      int z = posZ - rite.maxRadius + az;
      
      EntityLightningBolt bolt = new EntityLightningBolt(world, x, posY, z);
      world.func_72942_c(bolt);
    }
  }
}
