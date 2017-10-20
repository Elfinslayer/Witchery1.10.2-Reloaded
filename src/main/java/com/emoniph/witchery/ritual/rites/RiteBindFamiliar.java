package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteBindFamiliar extends Rite
{
  private final int radius;
  
  public RiteBindFamiliar(int radius)
  {
    this.radius = radius;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int intialStage)
  {
    steps.add(new StepBindFamiliar(this));
  }
  
  private static class StepBindFamiliar extends RitualStep
  {
    private final RiteBindFamiliar rite;
    
    public StepBindFamiliar(RiteBindFamiliar rite) {
      super();
      this.rite = rite;
    }
    
    public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
    {
      if (ticks % 20L != 0L) {
        return RitualStep.Result.STARTING;
      }
      
      if (!field_72995_K) {
        int r = rite.radius;
        AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - r, posY, posZ - r, posX + r, posY + 1, posZ + r);
        
        boolean bound = false;
        
        ArrayList<EntityPlayer> boundPlayers = new ArrayList();
        
        for (Object obj : world.func_72872_a(EntityTameable.class, bounds)) {
          EntityTameable tameable = (EntityTameable)obj;
          if ((tameable.func_70909_n()) && (Familiar.canBecomeFamiliar(tameable)) && (Coord.distance(field_70165_t, field_70163_u, field_70161_v, posX, posY, posZ) <= r)) {
            EntityLivingBase player = tameable.func_70902_q();
            if ((player != null) && ((player instanceof EntityPlayer)) && (Coord.distance(field_70165_t, field_70163_u, field_70161_v, posX, posY, posZ) <= r) && (!boundPlayers.contains(player))) {
              Familiar.bindToPlayer((EntityPlayer)player, tameable);
              boundPlayers.add((EntityPlayer)player);
              bound = true;
            }
          }
        }
        if (bound) {
          ParticleEffect.HEART.send(SoundEffect.RANDOM_FIZZ, world, posX, posY, posZ, 3.0D, 3.0D, 16);
        } else {
          return RitualStep.Result.ABORTED_REFUND;
        }
      }
      return RitualStep.Result.COMPLETED;
    }
  }
}
