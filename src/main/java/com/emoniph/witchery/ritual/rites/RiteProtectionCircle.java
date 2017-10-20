package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockAltar.TileEntityAltar;
import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.common.IPowerSource;
import com.emoniph.witchery.common.PowerSources;
import com.emoniph.witchery.common.PowerSources.RelativePowerSource;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class RiteProtectionCircle extends com.emoniph.witchery.ritual.Rite
{
  private final int radius;
  private final float upkeepPowerCost;
  private final int ticksToLive;
  
  public RiteProtectionCircle(int radius, float upkeepPowerCost, int ticksToLive)
  {
    this.radius = radius;
    this.upkeepPowerCost = upkeepPowerCost;
    this.ticksToLive = ticksToLive;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int initialStage)
  {
    steps.add(new ProtectionCircleStep(this, initialStage));
  }
  
  protected abstract void update(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
  
  private static class ProtectionCircleStep
    extends RitualStep
  {
    private final RiteProtectionCircle rite;
    private boolean activated = false;
    protected int ticksSoFar;
    
    public ProtectionCircleStep(RiteProtectionCircle rite, int ticksSoFar) {
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
      if (!activated) {
        if (ticks % 20L != 0L) {
          return RitualStep.Result.STARTING;
        }
        activated = true;
        SoundEffect.RANDOM_FIZZ.playAt(world, sourceX, sourceY, sourceZ);
      }
      

      if (rite.upkeepPowerCost > 0.0F) {
        IPowerSource powerSource = getPowerSource(world, sourceX, sourceY, sourceZ);
        if (powerSource == null) {
          return RitualStep.Result.ABORTED;
        }
        
        powerSourceCoord = powerSource.getLocation();
        
        if (!powerSource.consumePower(rite.upkeepPowerCost)) {
          return RitualStep.Result.ABORTED;
        }
      }
      
      if ((rite.ticksToLive > 0) && 
        (ticks % 20L == 0L) && (++ticksSoFar >= rite.ticksToLive)) {
        return RitualStep.Result.COMPLETED;
      }
      

      rite.update(world, posX, posY, posZ, rite.radius, ticks);
      
      return RitualStep.Result.UPKEEP;
    }
    

    IPowerSource getPowerSource(World world, int posX, int posY, int posZ)
    {
      if ((powerSourceCoord == null) || (field_73012_v.nextInt(5) == 0)) {
        return findNewPowerSource(world, posX, posY, posZ);
      }
      TileEntity tileEntity = powerSourceCoord.getBlockTileEntity(world);
      if (!(tileEntity instanceof BlockAltar.TileEntityAltar)) {
        return findNewPowerSource(world, posX, posY, posZ);
      }
      BlockAltar.TileEntityAltar altarTileEntity = (BlockAltar.TileEntityAltar)tileEntity;
      if (!altarTileEntity.isValid()) {
        return findNewPowerSource(world, posX, posY, posZ);
      }
      return altarTileEntity;
    }
    

    Coord powerSourceCoord;
    
    static final int POWER_SOURCE_RADIUS = 16;
    private IPowerSource findNewPowerSource(World world, int posX, int posY, int posZ)
    {
      List<PowerSources.RelativePowerSource> sources = PowerSources.instance() != null ? PowerSources.instance().get(world, new Coord(posX, posY, posZ), 16) : null;
      return (sources != null) && (sources.size() > 0) ? ((PowerSources.RelativePowerSource)sources.get(0)).source() : null;
    }
  }
}
