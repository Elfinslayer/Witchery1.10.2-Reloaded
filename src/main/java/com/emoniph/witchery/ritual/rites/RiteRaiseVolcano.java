package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.ritual.RiteRegistry;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.util.BlockProtect;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteRaiseVolcano extends com.emoniph.witchery.ritual.Rite
{
  private final int radius;
  private final int height;
  
  public RiteRaiseVolcano(int radius, int height)
  {
    this.radius = radius;
    this.height = height;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int intialStage)
  {
    steps.add(new StepRaiseVolcano(this, intialStage));
  }
  
  private static class StepRaiseVolcano extends RitualStep
  {
    private final RiteRaiseVolcano rite;
    private int stage = 0;
    
    public StepRaiseVolcano(RiteRaiseVolcano rite, int initialStage) {
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
      if (ticks % 15L != 0L) {
        return RitualStep.Result.STARTING;
      }
      
      if (!field_72995_K)
      {
        if (++stage == 1) {
          boolean lavaFound = false;
          for (int y = posY; (y > 0) && (!lavaFound); y--) {
            Block blockID = world.func_147439_a(posX, y, posZ);
            if ((blockID == Blocks.field_150353_l) && (surroundedByBlocks(world, posX, y, posZ, Blocks.field_150353_l, 2)))
              lavaFound = true; else {
              if (blockID == Blocks.field_150357_h) {
                break;
              }
            }
          }
          if (lavaFound) {
            ParticleEffect.PORTAL.send(SoundEffect.RANDOM_FIZZ, world, posX, posY, posZ, 0.5D, 1.0D, 16);
          } else {
            SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
            RiteRegistry.RiteError("witchery.rite.missinglava", ritual.getInitiatingPlayerName(), world);
            return RitualStep.Result.ABORTED_REFUND;
          }
        }
        
        int height = rite.height + 4 * covenSize;
        float radius = rite.radius + 2 * covenSize;
        if (stage <= height) { float r;
          for (int y = 1; y <= stage; y++) {
            r = radius - (height - stage - 1 + y) * radius / height;
            AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - r, y + posY, posZ - r, posX + r, y + posY, posZ + r);
            
            drawFilledCircle(world, posX, posZ, y + posY - 1, Math.max((int)Math.ceil(r), 1), y, true);
            if (stage == height) {
              int minusY = posY - 1; for (int reduce = 0; minusY > posY - 5; reduce++) {
                drawFilledCircle(world, posX, posZ, minusY, Math.max((int)radius - reduce, 2), y, false);minusY--;
              }
            }
            
            for (Object obj : world.func_72872_a(Entity.class, bounds)) {
              Entity entity = (Entity)obj;
              if (Coord.distance(field_70165_t, field_70163_u, field_70161_v, posX, y + posY, posZ) <= r) {
                Material material = world.func_147439_a((int)field_70165_t, (int)field_70163_u, (int)field_70161_v).func_149688_o();
                if (material.func_76220_a()) {
                  if ((entity instanceof EntityLivingBase)) {
                    ((EntityLivingBase)entity).func_70634_a(field_70165_t, field_70163_u + 1.0D, field_70161_v);
                  } else {
                    entity.func_70107_b(field_70165_t, field_70163_u + 1.0D, field_70161_v);
                  }
                }
              }
            }
          }
        } else if (stage < height * 2) {
          if (stage == height * 2 - 1) {
            world.func_147449_b(posX, posY + stage - height, posZ, Blocks.field_150356_k);
            world.func_147449_b(posX, posY + 1, posZ, Blocks.field_150353_l);
            if (rite.radius == 16) {
              if (field_73012_v.nextInt(4) == 0) {
                world.func_147449_b(posX, posY + 1 + stage - height, posZ, Blocks.field_150356_k);
              }
            } else {
              switch (field_73012_v.nextInt(8)) {
              case 0: 
                world.func_147468_f(posX + 1, posY + height - 1, posZ);
                break;
              case 1: 
                world.func_147468_f(posX, posY + height - 1, posZ + 1);
                break;
              case 2: 
                world.func_147468_f(posX - 1, posY + height - 1, posZ);
                break;
              case 3: 
                world.func_147468_f(posX, posY + height - 1, posZ - 1);
              }
            }
          }
          else
          {
            world.func_147449_b(posX, posY + 1, posZ, Blocks.field_150348_b);
            world.func_147449_b(posX, posY + stage - height, posZ, Blocks.field_150353_l);
          }
        }
        else {
          for (int y = posY; y > 0; y--) {
            Block blockID = world.func_147439_a(posX, y, posZ);
            if ((blockID == Blocks.field_150353_l) || (blockID == Blocks.field_150356_k) || (blockID == Blocks.field_150357_h)) {
              while ((blockID == Blocks.field_150353_l) || (blockID == Blocks.field_150356_k)) {
                setToAirIfLava(world, posX, y, posZ);
                setToAirIfLava(world, posX + 1, y, posZ);
                setToAirIfLava(world, posX - 1, y, posZ);
                setToAirIfLava(world, posX, y, posZ + 1);
                setToAirIfLava(world, posX, y, posZ - 1);
                blockID = world.func_147439_a(posX, --y, posZ);
              }
            }
            
            world.func_147468_f(posX, y, posZ);
          }
          
          return RitualStep.Result.COMPLETED;
        }
        return RitualStep.Result.UPKEEP;
      }
      return RitualStep.Result.COMPLETED;
    }
    
    private boolean surroundedByBlocks(World world, int x, int y, int z, Block blockID, int minCount) {
      int count = 0;
      count += (world.func_147439_a(x, y - 1, z) == blockID ? 1 : 0);
      count += (world.func_147439_a(x - 1, y, z) == blockID ? 1 : 0);
      count += (world.func_147439_a(x + 1, y - 1, z) == blockID ? 1 : 0);
      count += (world.func_147439_a(x, y, z - 1) == blockID ? 1 : 0);
      count += (world.func_147439_a(x, y, z + 1) == blockID ? 1 : 0);
      count += (world.func_147439_a(x, y + 1, z + 1) == blockID ? 1 : 0);
      
      return count >= minCount;
    }
    
    private void setToAirIfLava(World world, int posX, int posY, int posZ) {
      Block blockID = world.func_147439_a(posX, posY, posZ);
      if ((blockID == Blocks.field_150353_l) || (blockID == Blocks.field_150356_k)) {
        world.func_147468_f(posX, posY, posZ);
      }
    }
    
    protected void drawFilledCircle(World world, int x0, int z0, int y, int radius, int height, boolean replaceBlocks) {
      int x = radius;
      int z = 0;
      int radiusError = 1 - x;
      
      while (x >= z) {
        drawLine(world, -x + x0, x + x0, z + z0, y, x0, z0, radius, height, replaceBlocks);
        drawLine(world, -z + x0, z + x0, x + z0, y, x0, z0, radius, height, replaceBlocks);
        drawLine(world, -x + x0, x + x0, -z + z0, y, x0, z0, radius, height, replaceBlocks);
        drawLine(world, -z + x0, z + x0, -x + z0, y, x0, z0, radius, height, replaceBlocks);
        
        z++;
        
        if (radiusError < 0) {
          radiusError += 2 * z + 1;
        } else {
          x--;
          radiusError += 2 * (z - x + 1);
        }
      }
    }
    
    protected void drawLine(World world, int x1, int x2, int z, int y, int midX, int midZ, int radius, int height, boolean replaceBlocks) {
      int modX1 = (radius > 1) && (field_73012_v.nextInt(5) == 0) ? x1 + 1 : x1;
      int modX2 = (radius > 1) && (field_73012_v.nextInt(5) == 0) ? x2 - 1 : x2;
      boolean edgeZ = (midZ + radius == z) || (midZ - radius == z);
      
      for (int x = modX1; x <= modX2; x++) {
        if ((x != midX) || (z != midZ)) {
          drawPixel(world, x, z, y, ((x == modX1) || (x == modX2) || (edgeZ)) && (height < 3), replaceBlocks);
        }
      }
      boolean done = true;
    }
    
    protected void drawPixel(World world, int x, int z, int y, boolean lower, boolean replaceBlocks) {
      if (((replaceBlocks) && (BlockProtect.canBreak(x, y, z, world))) || (world.func_147437_c(x, y, z))) {
        world.func_147449_b(x, y, z, (lower) && (field_73012_v.nextInt(5) != 0) ? Blocks.field_150349_c : Blocks.field_150348_b);
      }
    }
  }
}
