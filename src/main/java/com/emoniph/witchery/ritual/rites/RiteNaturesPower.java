package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Dye;
import com.emoniph.witchery.util.MutableBlock;
import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDye;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;

public class RiteNaturesPower extends com.emoniph.witchery.ritual.Rite
{
  private final int radius;
  private final int height;
  private final int duration;
  private final int expanse;
  
  public RiteNaturesPower(int radius, int height, int duration, int expanse)
  {
    this.radius = radius;
    this.height = height;
    this.duration = duration;
    this.expanse = expanse;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int intialStage)
  {
    steps.add(new StepNaturesPower(this, intialStage));
  }
  
  private static class StepNaturesPower extends RitualStep
  {
    private final RiteNaturesPower rite;
    private int stage = 0;
    
    public StepNaturesPower(RiteNaturesPower rite, int initialStage) {
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
      if (ticks % 20L != 0L) {
        return RitualStep.Result.STARTING;
      }
      
      if (!field_72995_K)
      {
        if (++stage < rite.duration + covenSize * 5)
        {
          int modradius = rite.radius + covenSize * 2;
          posY--;
          int x = posX - modradius + field_73012_v.nextInt(modradius * 2);
          int z = posZ - modradius + field_73012_v.nextInt(modradius * 2);
          int y = -1;
          
          world.func_72926_e(2005, posX, posY + 2, posZ, 0);
          
          Material material = world.func_147439_a(x, posY, z).func_149688_o();
          if ((material == null) || (!material.func_76220_a()) || (!world.func_147437_c(x, posY + 1, z))) {
            for (int h = 1; h < rite.height; h++) {
              material = world.func_147439_a(x, posY + h, z).func_149688_o();
              if ((material != null) && (material.func_76220_a()) && (world.func_147437_c(x, posY + h + 1, z))) {
                y = posY + h;
                break;
              }
              
              material = world.func_147439_a(x, posY - h, z).func_149688_o();
              if ((material != null) && (material.func_76220_a()) && ((world.func_147437_c(x, posY - h + 1, z)) || (world.func_147439_a(x, posY - h + 1, z) == Blocks.field_150433_aE))) {
                y = posY - h;
                break;
              }
            }
          } else {
            y = posY;
          }
          
          if (y != -1) {
            world.func_72926_e(2005, x, y + 1, z, 0);
            
            drawFilledCircle(world, x, y, z, rite.expanse + 1);
          }
          
          return RitualStep.Result.UPKEEP;
        }
        return RitualStep.Result.COMPLETED;
      }
      
      return RitualStep.Result.COMPLETED;
    }
    
    protected void drawFilledCircle(World world, int x0, int y, int z0, int radius) {
      int x = radius;
      int z = 0;
      int radiusError = 1 - x;
      
      while (x >= z) {
        drawLine(world, -x + x0, x + x0, z + z0, y, x0, z0, radius);
        drawLine(world, -z + x0, z + x0, x + z0, y, x0, z0, radius);
        drawLine(world, -x + x0, x + x0, -z + z0, y, x0, z0, radius);
        drawLine(world, -z + x0, z + x0, -x + z0, y, x0, z0, radius);
        
        z++;
        
        if (radiusError < 0) {
          radiusError += 2 * z + 1;
        } else {
          x--;
          radiusError += 2 * (z - x + 1);
        }
      }
    }
    
    protected void drawLine(World world, int x1, int x2, int z, int y, int midX, int midZ, int radius) {
      int modX1 = (radius > 1) && (field_73012_v.nextInt(5) == 0) ? x1 + 1 : x1;
      int modX2 = (radius > 1) && (field_73012_v.nextInt(5) == 0) ? x2 - 1 : x2;
      boolean edgeZ = (midZ + radius == z) || (midZ - radius == z);
      
      for (int x = modX1; x <= modX2; x++)
      {
        drawPixel(world, x, z, y, (x == modX1) || (x == modX2) || (edgeZ));
      }
      
      boolean done = true;
    }
    
    private boolean isNeighbourBlockID(World world, int x, int y, int z, Block blockID) {
      if (world.func_147439_a(x + 1, y, z) == blockID) {
        return true;
      }
      
      if (world.func_147439_a(x - 1, y, z) == blockID) {
        return true;
      }
      
      if (world.func_147439_a(x, y, z + 1) == blockID) {
        return true;
      }
      
      if (world.func_147439_a(x, y, z - 1) == blockID) {
        return true;
      }
      
      return false;
    }
    
    private EntityPlayer fakePlayer = null;
    
    protected void drawPixel(World world, int x, int z, int y, boolean lower) {
      Block blockID = world.func_147439_a(x, y, z);
      int meta = world.func_72805_g(x, y, z);
      boolean wasGrass = blockID == Blocks.field_150349_c;
      Material materialAbove = world.func_147439_a(x, y + 1, z).func_149688_o();
      if ((materialAbove != null) && (!materialAbove.func_76220_a())) {
        if (((blockID == Blocks.field_150348_b) || (blockID == Blocks.field_150354_m) || (blockID == Blocks.field_150351_n) || (Config.instance().canReplaceNaturalBlock(blockID, meta))) && (field_73012_v.nextInt(8) != 0))
        {




          if (materialAbove != Material.field_151582_l) if (field_73012_v.nextDouble() <= (isNeighbourBlockID(world, x, y, z, Blocks.field_150355_j) ? 0.7D : 0.02D)) {
              world.func_147449_b(x, y, z, Blocks.field_150355_j);
              break label182; }
          world.func_147449_b(x, y, z, Blocks.field_150349_c);
          label182:
          blockID = Blocks.field_150349_c;
        }
        if ((materialAbove != Material.field_151582_l) && (blockID != Blocks.field_150350_a) && (blockID != Blocks.field_150362_t) && (blockID != BlocksLEAVES) && (field_73012_v.nextInt(4) == 0)) {
          MutableBlock[] blocks = { new MutableBlock(Blocks.field_150345_g, 0), new MutableBlock(Blocks.field_150345_g, 1), new MutableBlock(Blocks.field_150345_g, 2), new MutableBlock(Blocks.field_150345_g, 3), new MutableBlock(BlocksSAPLING, 0), new MutableBlock(BlocksSAPLING, 1), new MutableBlock(BlocksSAPLING, 2), new MutableBlock(BlocksEMBER_MOSS, 0), new MutableBlock(Blocks.field_150329_H, 1), new MutableBlock(Blocks.field_150329_H, 2), new MutableBlock(Blocks.field_150338_P), new MutableBlock(Blocks.field_150337_Q), new MutableBlock(Blocks.field_150328_O), new MutableBlock(Blocks.field_150327_N), new MutableBlock(Blocks.field_150329_H, 1), new MutableBlock(Blocks.field_150329_H, 2), new MutableBlock(Blocks.field_150329_H, 1), new MutableBlock(Blocks.field_150329_H, 2), new MutableBlock(Blocks.field_150329_H, 1), new MutableBlock(Blocks.field_150329_H, 2), new MutableBlock(Blocks.field_150329_H, 1), new MutableBlock(Blocks.field_150329_H, 2), new MutableBlock(Blocks.field_150329_H, 1), new MutableBlock(Blocks.field_150329_H, 2), new MutableBlock(Blocks.field_150329_H, 1), new MutableBlock(Blocks.field_150329_H, 2), new MutableBlock(Blocks.field_150423_aK, 0), new MutableBlock(Blocks.field_150440_ba, 0), new MutableBlock(BlocksGLINT_WEED, 0) };
          









          blocks[field_73012_v.nextInt(blocks.length)].mutate(world, x, y + 1, z, false);
        }
        if (field_73012_v.nextInt(3) == 0) {
          int count = 0;
          if (((fakePlayer == null) || (fakePlayer.field_70170_p != world)) && ((world instanceof WorldServer))) {
            fakePlayer = new FakePlayer((WorldServer)world, new GameProfile(java.util.UUID.randomUUID(), "[Minecraft]"));
          }
          
          ItemDye.applyBonemeal(Dye.BONE_MEAL.createStack(), world, x, y + 1, z, fakePlayer);
          Block saplingBlockID = world.func_147439_a(x, y + 1, z);
          while (((saplingBlockID == Blocks.field_150345_g) || (saplingBlockID == BlocksSAPLING)) && (count++ < 8)) {
            ItemDye.applyBonemeal(Dye.BONE_MEAL.createStack(), world, x, y + 1, z, fakePlayer);
            saplingBlockID = world.func_147439_a(x, y + 1, z);
          }
        }
      }
    }
  }
}
