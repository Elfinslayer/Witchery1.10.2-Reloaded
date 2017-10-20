package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.TimeUtil;
import java.util.ArrayList;
import java.util.Hashtable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.storage.WorldInfo;

public class RiteEclipse extends Rite
{
  public RiteEclipse() {}
  
  public void addSteps(ArrayList<RitualStep> steps, int initialStage)
  {
    steps.add(new StepEclipse(this, initialStage));
  }
  
  private static Hashtable<Integer, Long> lastEclipseTimes = new Hashtable();
  
  private static class StepEclipse extends RitualStep
  {
    private final RiteEclipse rite;
    private int stage;
    
    public StepEclipse(RiteEclipse rite, int initialStage) {
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
      
      if (!field_72995_K) {
        long riteOfEclipseCooldown = TimeUtil.secsToTicks(instanceriteOfEclipseCooldownInSecs);
        EntityPlayer player = ritual.getInitiatingPlayer(world);
        if ((riteOfEclipseCooldown > 0L) && (field_73010_i.size() > 1) && 
          (RiteEclipse.lastEclipseTimes.containsKey(Integer.valueOf(field_73011_w.field_76574_g)))) {
          long lastActivation = ((Long)RiteEclipse.lastEclipseTimes.get(Integer.valueOf(field_73011_w.field_76574_g))).longValue();
          if (world.func_82737_E() < lastActivation + riteOfEclipseCooldown) {
            if (player != null) {
              ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.eclipse.cooldown", new Object[0]);
            }
            return RitualStep.Result.ABORTED_REFUND;
          }
        }
        

        long i = world.func_72912_H().func_76073_f();
        world.func_72912_H().func_76068_b(i - i % 24000L + 18000L);
        RiteEclipse.lastEclipseTimes.put(Integer.valueOf(field_73011_w.field_76574_g), Long.valueOf(world.func_82737_E()));
      }
      return RitualStep.Result.COMPLETED;
    }
  }
}
