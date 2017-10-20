package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.EntityCreature;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class RiteCallCreatures extends Rite
{
  private final float radius;
  private final List<Class<? extends EntityCreature>> creatureTypes;
  
  public RiteCallCreatures(float radius, Class<? extends EntityCreature>[] creatureTypes)
  {
    this.radius = radius;
    this.creatureTypes = Arrays.asList(creatureTypes);
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int initialStage)
  {
    steps.add(new StepCallCreatures(this, initialStage));
  }
  
  private static class StepCallCreatures extends RitualStep
  {
    private final RiteCallCreatures rite;
    private int stage = 0;
    
    public StepCallCreatures(RiteCallCreatures rite, int stage) {
      super();
      this.rite = rite;
      this.stage = stage;
    }
    
    private void allure(World world, double posX, double posY, double posZ, int quad) {
      try {
        float r = 128.0F;
        float dy = 10.0F;
        AxisAlignedBB bounds = null;
        
        switch (quad) {
        case 0: 
          bounds = AxisAlignedBB.func_72330_a(posX, posY - 10.0D, posZ - 128.0D, posX + 128.0D, posY, posZ);
          break;
        case 1: 
          bounds = AxisAlignedBB.func_72330_a(posX - 128.0D, posY - 10.0D, posZ - 128.0D, posX, posY, posZ);
          break;
        case 2: 
          bounds = AxisAlignedBB.func_72330_a(posX, posY - 10.0D, posZ, posX + 128.0D, posY, posZ + 128.0D);
          break;
        case 3: 
          bounds = AxisAlignedBB.func_72330_a(posX - 128.0D, posY - 10.0D, posZ, posX, posY, posZ + 128.0D);
          break;
        case 4: 
          bounds = AxisAlignedBB.func_72330_a(posX - 128.0D, posY + 1.0D, posZ - 128.0D, posX, posY + 10.0D, posZ);
          break;
        case 5: 
          bounds = AxisAlignedBB.func_72330_a(posX, posY + 1.0D, posZ, posX + 128.0D, posY + 10.0D, posZ + 128.0D);
          break;
        case 6: 
          bounds = AxisAlignedBB.func_72330_a(posX - 128.0D, posY + 1.0D, posZ, posX, posY + 10.0D, posZ + 128.0D);
          break;
        case 7: 
        default: 
          bounds = AxisAlignedBB.func_72330_a(posX, posY + 1.0D, posZ - 128.0D, posX + 128.0D, posY + 10.0D, posZ);
        }
        
        
        int count = 0;
        int minDistanceSq = 32;
        for (Iterator i$ = world.func_72872_a(EntityCreature.class, bounds).iterator(); i$.hasNext(); 
            




            count >= 2)
        {
          Object obj = i$.next();
          EntityCreature creature = (EntityCreature)obj;
          if ((rite.creatureTypes.contains(creature.getClass())) && (creature.func_70092_e(posX, posY, posZ) > 32.0D) && (!com.emoniph.witchery.brewing.potions.PotionEnderInhibition.isActive(creature, 0)))
          {

            com.emoniph.witchery.item.ItemGeneral.teleportToLocation(world, posX - 2.0D + field_73012_v.nextInt(5), posY, posZ - 2.0D + field_73012_v.nextInt(5), field_73011_w.field_76574_g, creature, true);
            count++;
          }
        }
      }
      catch (Exception e)
      {
        Log.instance().debug(String.format("Exception occurred alluring with a ritual! %s", new Object[] { e.toString() }));
      }
    }
    
    public int getCurrentStage()
    {
      return stage;
    }
    
    public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
    {
      if (ticks % 60L != 0L) {
        return RitualStep.Result.STARTING;
      }
      
      if (!field_72995_K)
      {
        if (covenSize < 3) {
          net.minecraft.entity.player.EntityPlayer player = ritual.getInitiatingPlayer(world);
          SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
          if (player != null) {
            ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.coventoosmall", new Object[0]);
          }
          return RitualStep.Result.ABORTED_REFUND;
        }
        
        allure(world, posX, posY, posZ, ++stage % 8);
      }
      return stage < 250 ? RitualStep.Result.UPKEEP : RitualStep.Result.COMPLETED;
    }
  }
}
