package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteSetNBT extends Rite
{
  private final int radius;
  private final String nbtKey;
  private final int nbtValue;
  private final int nbtCovenBonus;
  
  public RiteSetNBT(int radius, String nbtKey, int value, int covenMemberBonus)
  {
    this.radius = radius;
    this.nbtKey = nbtKey;
    nbtValue = value;
    nbtCovenBonus = covenMemberBonus;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int intialStage)
  {
    steps.add(new StepSetNBT(this));
  }
  
  private static class StepSetNBT extends RitualStep
  {
    private final RiteSetNBT rite;
    
    public StepSetNBT(RiteSetNBT rite) {
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
          if (Coord.distance(field_70165_t, field_70163_u, field_70161_v, posX, posY, posZ) <= r) {
            NBTTagCompound nbtPlayer = Infusion.getNBT(player);
            if (nbtPlayer != null) {
              nbtPlayer.func_74768_a(rite.nbtKey, rite.nbtValue + covenSize * rite.nbtCovenBonus);
              bound = true;
            }
          }
        }
        if (bound) {
          ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_FIZZ, world, posX, posY, posZ, 3.0D, 3.0D, 16);
        } else {
          return RitualStep.Result.ABORTED_REFUND;
        }
      }
      return RitualStep.Result.COMPLETED;
    }
  }
}
