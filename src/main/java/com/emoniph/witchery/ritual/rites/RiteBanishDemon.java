package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.entity.EntityDeath;
import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.entity.EntityImp;
import com.emoniph.witchery.entity.EntityLordOfTorment;
import com.emoniph.witchery.entity.EntityReflection;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteBanishDemon
  extends Rite
{
  private final int radius;
  
  public RiteBanishDemon(int radius)
  {
    this.radius = radius;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int initialStage)
  {
    steps.add(new BanishDemonStep(this, initialStage));
  }
  
  private static class BanishDemonStep extends RitualStep {
    private final RiteBanishDemon rite;
    protected int ticksSoFar;
    
    public BanishDemonStep(RiteBanishDemon rite, int ticksSoFar) {
      super();
      this.rite = rite;
      this.ticksSoFar = ticksSoFar;
    }
    
    public int getCurrentStage()
    {
      return ticksSoFar;
    }
    
    public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
    {
      if (ticks % 20L != 0L) {
        return RitualStep.Result.STARTING;
      }
      SoundEffect.RANDOM_FIZZ.playAt(world, posX, posY, posZ);
      
      AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - rite.radius, posY - rite.radius, posZ - rite.radius, posX + rite.radius, posY + rite.radius, posZ + rite.radius);
      List<EntityLiving> list = world.func_72872_a(EntityLiving.class, bounds);
      for (EntityLiving entity : list) {
        if (((entity instanceof EntityDemon)) || ((entity instanceof EntityDeath)) || ((entity instanceof EntityLordOfTorment)) || ((entity instanceof EntityImp)) || ((entity instanceof EntityReflection)))
        {
          if (Coord.distanceSq(field_70165_t, field_70163_u, field_70161_v, posX, posY, posZ) < rite.radius * rite.radius) {
            entity.func_70106_y();
            ParticleEffect.EXPLODE.send(SoundEffect.NONE, entity, 1.0D, 2.0D, 16);
          }
        }
      }
      
      return RitualStep.Result.COMPLETED;
    }
  }
}
