package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.item.ItemPoppet;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.ritual.RitualStep.SacrificedItem;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.ParticleEffect;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RiteCursePoppets extends com.emoniph.witchery.ritual.Rite
{
  private final int level;
  
  public RiteCursePoppets(int level)
  {
    this.level = level;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int intialStage)
  {
    steps.add(new StepCursePoppets(this));
  }
  
  private static class StepCursePoppets extends RitualStep
  {
    private final RiteCursePoppets rite;
    
    public StepCursePoppets(RiteCursePoppets rite) {
      super();
      this.rite = rite;
    }
    
    public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
    {
      if (ticks % 20L != 0L) {
        return RitualStep.Result.STARTING;
      }
      
      if (!field_72995_K)
      {
        boolean complete = false;
        
        net.minecraft.entity.player.EntityPlayer curseMasterPlayer = ritual.getInitiatingPlayer(world);
        boolean curseMaster = (curseMasterPlayer != null) && (com.emoniph.witchery.familiar.Familiar.hasActiveCurseMasteryFamiliar(curseMasterPlayer));
        
        if (curseMaster) {
          Iterator i$ = sacrificedItems.iterator(); if (i$.hasNext()) { RitualStep.SacrificedItem item = (RitualStep.SacrificedItem)i$.next();
            if ((itemstack.func_77973_b() == ItemsTAGLOCK_KIT) && (itemstack.func_77960_j() == 1)) {
              net.minecraft.entity.EntityLivingBase entity = ItemsTAGLOCK_KIT.getBoundEntity(world, null, itemstack, Integer.valueOf(1));
              if ((entity != null) && 
                (!ItemsPOPPET.poppetProtectionActivated(curseMasterPlayer, null, entity, true))) {
                ItemsPOPPET.destroyAntiVoodooPoppets(curseMasterPlayer, entity, 10);
              }
              
              complete = true;
            }
            
          }
        }
        else if (curseMasterPlayer != null) {
          ChatUtil.sendTranslated(curseMasterPlayer, "witchery.rite.requirescursemastery", new Object[0]);
        }
        


        if (complete) {
          ParticleEffect.FLAME.send(com.emoniph.witchery.util.SoundEffect.MOB_ENDERDRAGON_GROWL, world, 0.5D + posX, 0.1D + posY, 0.5D + posZ, 1.0D, 2.0D, 16);
        }
        else {
          return RitualStep.Result.ABORTED_REFUND;
        }
      }
      return RitualStep.Result.COMPLETED;
    }
  }
}
