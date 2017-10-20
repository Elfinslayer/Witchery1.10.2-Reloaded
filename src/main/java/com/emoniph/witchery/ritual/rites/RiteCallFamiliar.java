package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteCallFamiliar extends Rite
{
  private final int radius;
  
  public RiteCallFamiliar(int radius)
  {
    this.radius = radius;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int intialStage)
  {
    steps.add(new StepCallFamiliar(this));
  }
  
  private static class StepCallFamiliar extends RitualStep
  {
    private final RiteCallFamiliar rite;
    
    public StepCallFamiliar(RiteCallFamiliar rite) {
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
        
        for (Object obj : world.func_72872_a(EntityPlayer.class, bounds)) {
          EntityPlayer player = (EntityPlayer)obj;
          EntityTameable entity = Familiar.getFamiliarEntity(player);
          if (entity != null) {
            ItemGeneral.teleportToLocation(field_70170_p, field_70165_t, field_70163_u, field_70161_v, field_71093_bK, entity, false);
            bound = true;
          } else {
            EntityTameable familiar = Familiar.summonFamiliar(player, 0.5D + posX, 0.001D + posY, 0.5D + posZ);
            if (familiar != null) {
              bound = true;
            }
          }
        }
        if (bound) {
          ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_FIZZ, world, posX, posY, posZ, 1.0D, 2.0D, 16);
        } else {
          return RitualStep.Result.ABORTED_REFUND;
        }
      }
      return RitualStep.Result.COMPLETED;
    }
  }
}
