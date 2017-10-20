package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.entity.EntityToad;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;

public class RiteRainOfToads extends com.emoniph.witchery.ritual.Rite
{
  private final int minRadius;
  private final int maxRadius;
  private final int bolts;
  
  public RiteRainOfToads(int minRadius, int maxRadius, int bolts)
  {
    this.minRadius = minRadius;
    this.maxRadius = maxRadius;
    this.bolts = bolts;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int initialStage)
  {
    steps.add(new StepRainOfToads(this, initialStage));
  }
  
  private static class StepRainOfToads extends RitualStep
  {
    private final RiteRainOfToads rite;
    private int stage;
    
    public StepRainOfToads(RiteRainOfToads rite, int initialStage) {
      super();
      this.rite = rite;
      stage = initialStage;
    }
    
    public int getCurrentStage()
    {
      return stage;
    }
    
    public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
    {
      if (ticks % 30L != 0L) {
        return RitualStep.Result.STARTING;
      }
      
      if (covenSize < 1) {
        EntityPlayer player = ritual.getInitiatingPlayer(world);
        SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
        if (player != null) {
          ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.coventoosmall", new Object[0]);
        }
        return RitualStep.Result.ABORTED_REFUND;
      }
      
      stage += 1;
      switch (stage) {
      case 1: 
        spawnBolt(world, posX, posY, posZ);
        return RitualStep.Result.STARTING;
      case 2: 
        spawnBolt(world, posX, posY, posZ);
        return RitualStep.Result.STARTING;
      case 3: 
        spawnBolt(world, posX, posY, posZ);
        return RitualStep.Result.STARTING;
      case 4: 
        if (((world instanceof WorldServer)) && (!world.func_72896_J())) {
          WorldInfo worldinfo = ((WorldServer)world).func_72912_H();
          int i = (300 + field_73012_v.nextInt(600)) * 20;
          worldinfo.func_76080_g(i);
          worldinfo.func_76084_b(true);
        }
        spawnBolt(world, posX, posY, posZ);
        return RitualStep.Result.STARTING;
      }
      int activeRadius = rite.maxRadius - rite.minRadius;
      for (int n = 0; n < field_73012_v.nextInt(rite.bolts) + 8; n++) {
        int ax = field_73012_v.nextInt(activeRadius * 2 + 1);
        if (ax > activeRadius) {
          ax += rite.minRadius * 2;
        }
        int x = posX - rite.maxRadius + ax;
        
        int az = field_73012_v.nextInt(activeRadius * 2 + 1);
        if (az > activeRadius) {
          az += rite.minRadius * 2;
        }
        
        int z = posZ - rite.maxRadius + az;
        int y = world.func_72825_h(x, z);
        if (world.func_147437_c(x, y, z)) {
          EntityToad toad = new EntityToad(world);
          toad.func_70012_b(x, y + 8 + field_73012_v.nextInt(7), z, 0.0F, 0.0F);
          toad.setTimeToLive(30, true);
          world.func_72838_d(toad);
        }
      }
      return stage < 200 ? RitualStep.Result.UPKEEP : RitualStep.Result.COMPLETED;
    }
    
    private void spawnBolt(World world, int posX, int posY, int posZ)
    {
      int activeRadius = rite.maxRadius - rite.minRadius;
      int ax = field_73012_v.nextInt(activeRadius * 2 + 1);
      if (ax > activeRadius) {
        ax += rite.minRadius * 2;
      }
      int x = posX - rite.maxRadius + ax;
      
      int az = field_73012_v.nextInt(activeRadius * 2 + 1);
      if (az > activeRadius) {
        az += rite.minRadius * 2;
      }
      
      int z = posZ - rite.maxRadius + az;
      
      EntityLightningBolt bolt = new EntityLightningBolt(world, x, posY, z);
      world.func_72942_c(bolt);
    }
  }
}
