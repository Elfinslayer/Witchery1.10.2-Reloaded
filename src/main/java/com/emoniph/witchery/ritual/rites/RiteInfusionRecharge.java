package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockAltar.TileEntityAltar;
import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.common.IPowerSource;
import com.emoniph.witchery.common.PowerSources;
import com.emoniph.witchery.common.PowerSources.RelativePowerSource;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteInfusionRecharge extends com.emoniph.witchery.ritual.Rite
{
  private final float upkeepPowerCost;
  private final int charges;
  private final int radius;
  private final int ticksToLive;
  
  public RiteInfusionRecharge(int charges, int radius, float upkeepPowerCost, int ticksToLive)
  {
    this.charges = charges;
    this.radius = radius;
    this.upkeepPowerCost = upkeepPowerCost;
    this.ticksToLive = ticksToLive;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int intialStage)
  {
    steps.add(new StepInfusePlayers(this, intialStage));
  }
  
  private static class StepInfusePlayers extends RitualStep
  {
    private final RiteInfusionRecharge rite;
    private boolean activated = false;
    protected int ticksSoFar;
    
    public StepInfusePlayers(RiteInfusionRecharge rite, int ticksSoFar) {
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
      int r;
      if (!field_72995_K) {
        if (rite.upkeepPowerCost > 0.0F) {
          IPowerSource powerSource = getPowerSource(world, posX, posY, posZ);
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
        

        r = rite.radius;
        AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - r, posY, posZ - r, posX + r, posY + 1, posZ + r);
        
        for (Object obj : world.func_72872_a(EntityPlayer.class, bounds)) {
          EntityPlayer player = (EntityPlayer)obj;
          if (Coord.distance(field_70165_t, field_70163_u, field_70161_v, posX, posY, posZ) <= r) {
            int currentEnergy = Infusion.getCurrentEnergy(player);
            int maxEnergy = Infusion.getMaxEnergy(player);
            if (currentEnergy < maxEnergy) {
              Infusion.setCurrentEnergy(player, Math.min(currentEnergy + rite.charges, maxEnergy));
              ParticleEffect.INSTANT_SPELL.send(SoundEffect.NOTE_PLING, player, 1.0D, 2.0D, 8);
            }
          }
        }
      }
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
