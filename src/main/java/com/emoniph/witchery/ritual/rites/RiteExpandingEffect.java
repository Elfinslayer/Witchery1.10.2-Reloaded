package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.entity.EntityWitchHunter;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class RiteExpandingEffect extends com.emoniph.witchery.ritual.Rite
{
  protected final int maxRadius;
  protected final int height;
  protected final boolean curse;
  
  public RiteExpandingEffect(int radius, int height, boolean curse)
  {
    maxRadius = radius;
    this.height = height;
    this.curse = curse;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int intialStage)
  {
    steps.add(new StepExpansion(this, intialStage));
  }
  
  public abstract void doBlockAction(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, EntityPlayer paramEntityPlayer, boolean paramBoolean);
  
  public abstract boolean doRadiusAction(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, EntityPlayer paramEntityPlayer, boolean paramBoolean);
  
  public boolean isComplete(World world, int posX, int posY, int posZ, int radius, EntityPlayer player, long ticks, boolean fullyExpanded, boolean enhanced) { return fullyExpanded; }
  
  private static class StepExpansion
    extends RitualStep
  {
    private final RiteExpandingEffect rite;
    private int stage = 0;
    private boolean activated;
    
    public StepExpansion(RiteExpandingEffect rite, int initialStage) {
      super();
      this.rite = rite;
      stage = initialStage;
    }
    
    public int getCurrentStage()
    {
      return (byte)stage;
    }
    
    public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
    {
      if (!activated) {
        if (ticks % 20L != 0L) {
          return RitualStep.Result.STARTING;
        }
        activated = true;
        SoundEffect.RANDOM_FIZZ.playAt(world, posX, posY, posZ);
      }
      


      if (!field_72995_K)
      {
        if (ticks % 5L == 0L) {
          stage += 1;
          
          if ((stage == 1) && (rite.curse)) {
            EntityWitchHunter.blackMagicPerformed(ritual.getInitiatingPlayer(world));
          }
          
          int height = rite.height;
          float maxRadius = rite.maxRadius + 2 * covenSize;
          
          EntityPlayer player = ritual.getInitiatingPlayer(world);
          
          int currentRadius = stage + 3;
          
          boolean enhanced = (player != null) && (Familiar.hasActiveCurseMasteryFamiliar(player));
          
          if ((currentRadius <= maxRadius) && 
            (!applyCircle(world, posX, posZ, posY, currentRadius, height, player, enhanced))) {
            return RitualStep.Result.ABORTED;
          }
          


          if (stage <= 250) {} return rite.isComplete(world, posX, posY, posZ, currentRadius, player, ticks, currentRadius >= maxRadius, enhanced) ? RitualStep.Result.COMPLETED : RitualStep.Result.UPKEEP;
        }
        return RitualStep.Result.UPKEEP;
      }
      
      return RitualStep.Result.COMPLETED;
    }
    
    protected boolean applyCircle(World world, int x0, int z0, int y0, int radius, int height, EntityPlayer player, boolean enhanced)
    {
      if (!rite.doRadiusAction(world, x0, y0, z0, radius, player, enhanced)) {
        return false;
      }
      
      int x = radius;
      int z = 0;
      int radiusError = 1 - x;
      
      while (x >= z) {
        drawPixel(world, x + x0, z + z0, y0, height, radius, player, enhanced);
        drawPixel(world, z + x0, x + z0, y0, height, radius, player, enhanced);
        drawPixel(world, -x + x0, z + z0, y0, height, radius, player, enhanced);
        drawPixel(world, -z + x0, x + z0, y0, height, radius, player, enhanced);
        drawPixel(world, -x + x0, -z + z0, y0, height, radius, player, enhanced);
        drawPixel(world, -z + x0, -x + z0, y0, height, radius, player, enhanced);
        drawPixel(world, x + x0, -z + z0, y0, height, radius, player, enhanced);
        drawPixel(world, z + x0, -x + z0, y0, height, radius, player, enhanced);
        
        z++;
        
        if (radiusError < 0) {
          radiusError += 2 * z + 1;
        } else {
          x--;
          radiusError += 2 * (z - x + 1);
        }
      }
      
      return true;
    }
    
    protected void drawPixel(World world, int x, int z, int y, int height, int currentRadius, EntityPlayer player, boolean enhanced) {
      for (int i = 0; i < height; i++) {
        if ((world.func_147439_a(x, y + i, z).func_149688_o() != Material.field_151579_a) && (world.func_147437_c(x, y + i + 1, z))) {
          if (rite.curse) {
            ParticleEffect.MOB_SPELL.send(SoundEffect.NONE, world, 0.5D + x, y + i + 1, 0.5D + z, 1.0D, 1.0D, 16);
          } else {
            ParticleEffect.SPELL.send(SoundEffect.NONE, world, 0.5D + x, y + i + 1, 0.5D + z, 1.0D, 1.0D, 16);
          }
          
          rite.doBlockAction(world, x, y + i, z, currentRadius, player, enhanced);
          break;
        }
        if ((i > 0) && (world.func_147439_a(x, y - i, z).func_149688_o() != Material.field_151579_a) && (world.func_147437_c(x, y - i + 1, z))) {
          if (rite.curse) {
            ParticleEffect.MOB_SPELL.send(SoundEffect.NONE, world, 0.5D + x, y - i + 1, 0.5D + z, 1.0D, 1.0D, 32);
          } else {
            ParticleEffect.SPELL.send(SoundEffect.NONE, world, 0.5D + x, y - i + 1, 0.5D + z, 1.0D, 1.0D, 32);
          }
          
          rite.doBlockAction(world, x, y - i, z, currentRadius, player, enhanced);
          break;
        }
      }
    }
  }
}
