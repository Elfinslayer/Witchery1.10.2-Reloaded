package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.util.Coord;
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

public class RiteForestation extends Rite
{
  private final int radius;
  private final int height;
  private final int duration;
  private final Block block;
  private final int metadata;
  
  public RiteForestation(int radius, int height, int duration, Block block, int protoMeta)
  {
    this.radius = radius;
    this.height = height;
    this.duration = duration;
    this.block = block;
    metadata = protoMeta;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int intialStage)
  {
    steps.add(new StepForestation(this, intialStage));
  }
  
  private static class StepForestation extends RitualStep
  {
    private final RiteForestation rite;
    private int stage = 0;
    
    public StepForestation(RiteForestation rite, int initialStage) {
      super();
      this.rite = rite;
      stage = initialStage;
    }
    
    public int getCurrentStage()
    {
      return (byte)stage;
    }
    
    public boolean isAirOrReplaceableBlock(World world, int x, int y, int z) {
      Block blockID = world.func_147439_a(x, y, z);
      if (blockID == Blocks.field_150350_a) {
        return true;
      }
      
      Material block = blockID.func_149688_o();
      if (block == null)
        return false;
      if (block.func_76224_d())
        return false;
      if (block.func_76222_j()) {
        return true;
      }
      return false;
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
          int modradiussq = (modradius + 1) * (modradius + 1);
          posY--;
          int x = posX - modradius + field_73012_v.nextInt(modradius * 2);
          int z = posZ - modradius + field_73012_v.nextInt(modradius * 2);
          int y = -1;
          
          if (Coord.distanceSq(x, 1.0D, z, posX, 1.0D, posZ) > modradiussq) {
            x = posX - modradius + field_73012_v.nextInt(modradius * 2);
            z = posZ - modradius + field_73012_v.nextInt(modradius * 2);
            if (Coord.distanceSq(x, 1.0D, z, posX, 1.0D, posZ) > modradiussq) {
              return RitualStep.Result.UPKEEP;
            }
          }
          
          world.func_72926_e(2005, posX, posY + 2, posZ, 0);
          
          Material material = world.func_147439_a(x, posY, z).func_149688_o();
          if ((material == null) || (!material.func_76220_a()) || (!world.func_147437_c(x, posY + 1, z))) {
            for (int h = 1; h < rite.height; h++) {
              material = world.func_147439_a(x, posY + h, z).func_149688_o();
              if ((material != null) && (material.func_76220_a()) && (isAirOrReplaceableBlock(world, x, posY + h + 1, z))) {
                y = posY + h;
                break;
              }
              
              material = world.func_147439_a(x, posY - h, z).func_149688_o();
              if ((material != null) && (material.func_76220_a()) && (isAirOrReplaceableBlock(world, x, posY - h + 1, z))) {
                y = posY - h;
                break;
              }
            }
          } else {
            y = posY;
          }
          
          if (y != -1) {
            world.func_72926_e(2005, x, y + 1, z, 0);
            

            drawPixel(world, x, z, y, false);
          }
          
          return RitualStep.Result.UPKEEP;
        }
        return RitualStep.Result.COMPLETED;
      }
      
      return RitualStep.Result.COMPLETED;
    }
    























































    private EntityPlayer fakePlayer = null;
    
    protected void drawPixel(World world, int x, int z, int y, boolean lower) {
      Block blockID = world.func_147439_a(x, y, z);
      boolean wasGrass = blockID == Blocks.field_150349_c;
      Material materialAbove = world.func_147439_a(x, y + 1, z).func_149688_o();
      if ((materialAbove != null) && (!materialAbove.func_76220_a()))
      {






















        new MutableBlock(rite.block, rite.metadata).mutate(world, x, y + 1, z, false);
        
        int count = 0;
        if (((fakePlayer == null) || (fakePlayer.field_70170_p != world)) && ((world instanceof WorldServer))) {
          fakePlayer = new FakePlayer((WorldServer)world, new GameProfile(java.util.UUID.randomUUID(), "[Minecraft]"));
        }
        
        ItemDye.applyBonemeal(Dye.BONE_MEAL.createStack(), world, x, y + 1, z, fakePlayer);
        Block saplingBlockID = world.func_147439_a(x, y + 1, z);
        while (((saplingBlockID == Blocks.field_150345_g) || (saplingBlockID == BlocksSAPLING)) && (count++ < 10)) {
          ItemDye.applyBonemeal(Dye.BONE_MEAL.createStack(), world, x, y + 1, z, fakePlayer);
          saplingBlockID = world.func_147439_a(x, y + 1, z);
        }
      }
    }
  }
}
