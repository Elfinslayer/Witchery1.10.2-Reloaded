package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.ritual.RitualStep.SacrificedItem;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class RiteRemoveVampirism extends Rite
{
  public RiteRemoveVampirism() {}
  
  public void addSteps(ArrayList<RitualStep> steps, int intialStage)
  {
    steps.add(new StepCurseCreature(this));
  }
  
  private static class StepCurseCreature extends RitualStep
  {
    private final RiteRemoveVampirism rite;
    
    public StepCurseCreature(RiteRemoveVampirism rite) {
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
        boolean cursed = false;
        
        EntityPlayer curseMasterPlayer = ritual.getInitiatingPlayer(world);
        
        if (!Familiar.hasActiveCurseMasteryFamiliar(curseMasterPlayer)) {
          ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.requirescat", new Object[0]);
          
          return RitualStep.Result.ABORTED_REFUND;
        }
        
        if (covenSize < 6) {
          ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.requiresfullcoven", new Object[0]);
          
          return RitualStep.Result.ABORTED_REFUND;
        }
        
        for (Iterator i$ = sacrificedItems.iterator(); i$.hasNext(); 
            

























            goto 327)
        {
          RitualStep.SacrificedItem item = (RitualStep.SacrificedItem)i$.next();
          if ((itemstack.func_77973_b() == ItemsTAGLOCK_KIT) && (itemstack.func_77960_j() == 1))
          {
            net.minecraft.entity.EntityLivingBase entity = ItemsTAGLOCK_KIT.getBoundEntity(world, null, itemstack, Integer.valueOf(1));
            
            if (entity == null) break;
            if ((entity instanceof EntityPlayer)) {
              EntityPlayer player = (EntityPlayer)entity;
              ExtendedPlayer playerEx = ExtendedPlayer.get(player);
              if (playerEx.isVampire()) {
                double MAX_RANGE_SQ = 64.0D;
                if (player.func_70092_e(0.5D + posX, 0.5D + posY, 0.5D + posZ) <= 64.0D) {
                  if (field_73012_v.nextInt(4) != 0) {
                    playerEx.setVampireLevel(0);
                  } else {
                    cursed = true;
                  }
                  complete = true;
                } else {
                  ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.toofar", new Object[0]);
                }
              }
              else {
                ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.notactive", new Object[0]);
              }
            }
            else {
              ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.notactive", new Object[0]);
            }
          }
        }
        



        if (complete) {
          if (cursed) {
            ParticleEffect.FLAME.send(SoundEffect.MOB_ENDERDRAGON_GROWL, world, 0.5D + posX, 0.1D + posY, 0.5D + posZ, 1.0D, 2.0D, 16);
          }
          else {
            ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_LEVELUP, world, 0.5D + posX, 0.1D + posY, 0.5D + posZ, 1.0D, 2.0D, 16);
          }
        }
        else {
          return RitualStep.Result.ABORTED_REFUND;
        }
      }
      return RitualStep.Result.COMPLETED;
    }
  }
}
