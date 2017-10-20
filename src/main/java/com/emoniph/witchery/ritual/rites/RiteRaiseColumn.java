package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteRaiseColumn extends Rite
{
  private final int radius;
  private final int height;
  
  public RiteRaiseColumn(int radius, int height)
  {
    this.radius = radius;
    this.height = height;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int intialStage)
  {
    steps.add(new StepRaiseColumn(this, intialStage));
  }
  
  private static class StepRaiseColumn extends RitualStep
  {
    private final RiteRaiseColumn rite;
    private int stage = 0;
    
    public StepRaiseColumn(RiteRaiseColumn rite, int initialStage) {
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
      if (!field_72995_K) {
        if (ticks % 5L != 0L) {
          return RitualStep.Result.STARTING;
        }
        
        if (++stage == 1) {
          ParticleEffect.PORTAL.send(SoundEffect.RANDOM_FIZZ, world, posX, posY, posZ, 0.5D, 1.0D, 16);
        }
        
        int height = rite.height;
        int radius = rite.radius + covenSize * 2;
        int AIR_SPACE = rite.radius * 2;
        
        for (int depth = posY + AIR_SPACE; depth >= posY - height; depth--) {
          drawFilledCircle(world, posX, depth, posZ, radius, depth == posY - 1);
        }
        
        AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - radius, posY, posZ - radius, posX + radius, posY + AIR_SPACE, posZ + radius);
        for (Object obj : world.func_72872_a(Entity.class, bounds)) {
          Entity entity = (Entity)obj;
          if (Coord.distanceSq(field_70165_t, posY, field_70161_v, posX, posY, posZ) <= radius * radius) {
            if ((entity instanceof EntityLivingBase)) {
              ((EntityLivingBase)entity).func_70634_a(field_70165_t, field_70163_u + 1.0D, field_70161_v);
            } else {
              field_70145_X = true;
              entity.func_70107_b(field_70165_t, field_70163_u + 1.0D, field_70161_v);
              field_70145_X = false;
            }
          }
        }
        
        if (stage < height - 1) {
          return RitualStep.Result.UPKEEP;
        }
        return RitualStep.Result.COMPLETED;
      }
      
      return RitualStep.Result.COMPLETED;
    }
    
    protected void drawFilledCircle(World world, int x0, int y0, int z0, int radius, boolean topLayer)
    {
      int x = radius;
      int z = 0;
      int radiusError = 1 - x;
      
      while (x >= z) {
        drawLine(world, -x + x0, x + x0, y0, z + z0, topLayer, radius, z0);
        drawLine(world, -z + x0, z + x0, y0, x + z0, topLayer, radius, z0);
        drawLine(world, -x + x0, x + x0, y0, -z + z0, topLayer, radius, z0);
        drawLine(world, -z + x0, z + x0, y0, -x + z0, topLayer, radius, z0);
        
        z++;
        
        if (radiusError < 0) {
          radiusError += 2 * z + 1;
        } else {
          x--;
          radiusError += 2 * (z - x + 1);
        }
      }
    }
    
    protected void drawLine(World world, int x1, int x2, int y, int z, boolean topLayer, int radius, int midZ) {
      for (int x = x1; x <= x2; x++) {
        Block block = BlockUtil.getBlock(world, x, y, z);
        Block highBlock = BlockUtil.getBlock(world, x, y + 1, z);
        Block lowBlock = BlockUtil.getBlock(world, x, y - 1, z);
        
        if ((block != null) && (block != net.minecraft.init.Blocks.field_150350_a) && (!BlockUtil.isImmovableBlock(block)) && (!BlockUtil.isImmovableBlock(highBlock)) && (!BlockUtil.isImmovableBlock(lowBlock))) {
          boolean edgeZ = (midZ + radius == z) || (midZ - radius == z);
          int blockMeta = world.func_72805_g(x, y, z);
          
          if ((topLayer) || ((!edgeZ) && (x != x1) && (x != x2)) || (field_73012_v.nextInt(7) != 0)) {
            if (block.hasTileEntity(0)) {
              TileEntity tileEntity = world.func_147438_o(x, y, z);
              if ((tileEntity != null) && (!BlockUtil.isImmovableBlock(tileEntity))) {
                world.func_147475_p(x, y, z);
                BlockUtil.setBlock(world, x, y + 1, z, block);
                world.func_72921_c(x, y + 1, z, blockMeta, 2);
                tileEntity.func_145829_t();
                world.func_147455_a(x, y + 1, z, tileEntity);
                BlockUtil.setAirBlock(world, x, y, z, 2);
              }
            } else {
              BlockUtil.setBlock(world, x, y + 1, z, block, blockMeta, 2);
              BlockUtil.setAirBlock(world, x, y, z, 2);
            }
          }
        }
      }
    }
  }
}
