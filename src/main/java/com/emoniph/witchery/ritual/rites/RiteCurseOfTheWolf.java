package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockAreaMarker.AreaMarkerRegistry;
import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.entity.EntityVillagerWere;
import com.emoniph.witchery.entity.EntityWitchHunter;
import com.emoniph.witchery.entity.EntityWolfman;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.ritual.RitualStep.SacrificedItem;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.CreatureUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class RiteCurseOfTheWolf extends Rite
{
  private final boolean curse;
  
  public RiteCurseOfTheWolf(boolean curse)
  {
    this.curse = curse;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int intialStage)
  {
    steps.add(new StepCurseCreature(this));
  }
  
  private static class StepCurseCreature extends RitualStep
  {
    private final RiteCurseOfTheWolf rite;
    
    public StepCurseCreature(RiteCurseOfTheWolf rite) {
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
        
        if (!CreatureUtil.isFullMoon(world)) {
          ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.requiresfullmoon", new Object[0]);
          
          return RitualStep.Result.ABORTED_REFUND;
        }
        
        if (!Familiar.hasActiveCurseMasteryFamiliar(curseMasterPlayer)) {
          ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.requirescat", new Object[0]);
          
          return RitualStep.Result.ABORTED_REFUND;
        }
        
        if (covenSize < 6) {
          ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.requiresfullcoven", new Object[0]);
          
          return RitualStep.Result.ABORTED_REFUND;
        }
        
        for (Iterator i$ = sacrificedItems.iterator(); i$.hasNext(); 
            

















































































            goto 713)
        {
          RitualStep.SacrificedItem item = (RitualStep.SacrificedItem)i$.next();
          if ((itemstack.func_77973_b() == ItemsTAGLOCK_KIT) && (itemstack.func_77960_j() == 1))
          {
            net.minecraft.entity.EntityLivingBase entity = ItemsTAGLOCK_KIT.getBoundEntity(world, null, itemstack, Integer.valueOf(1));
            
            if (entity == null) break;
            if (rite.curse) {
              EntityWitchHunter.blackMagicPerformed(curseMasterPlayer);
              
              boolean isImmune = com.emoniph.witchery.item.ItemHunterClothes.isCurseProtectionActive(entity);
              if (!isImmune) {
                isImmune = BlockAreaMarker.AreaMarkerRegistry.instance().isProtectionActive(entity, rite);
              }
              

              if ((!isImmune) && (!ItemsPOPPET.voodooProtectionActivated(curseMasterPlayer, null, entity, 3)))
              {

                if ((entity instanceof EntityPlayer)) {
                  EntityPlayer player = (EntityPlayer)entity;
                  ExtendedPlayer playerEx = ExtendedPlayer.get(player);
                  
                  if ((!instanceallowVampireWolfHybrids) && (playerEx.isVampire())) {
                    ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.hybridsnotallow", new Object[0]);
                  }
                  else if (playerEx.getWerewolfLevel() == 0) {
                    playerEx.setWerewolfLevel(1);
                    ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "witchery.werewolf.infection", new Object[0]);
                    
                    complete = true;
                    cursed = true;
                  } else {
                    ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.alreadyactive", new Object[0]);
                  }
                }
                else if (((entity instanceof EntityVillager)) && (!(entity instanceof EntityVillagerWere)))
                {
                  EntityVillager villager = (EntityVillager)entity;
                  EntityWolfman.convertToCuredVillager(villager, villager.func_70946_n(), field_70956_bz, field_70963_i);
                  complete = true;
                  cursed = true;
                } else {
                  ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.wolfcurse.nothuman", new Object[0]);
                }
              }
              

              if ((isImmune) && 
                (curseMasterPlayer != null)) {
                ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.blackmagicdampening", new Object[0]);
              }
              

            }
            else if ((entity instanceof EntityPlayer)) {
              EntityPlayer player = (EntityPlayer)entity;
              ExtendedPlayer playerEx = ExtendedPlayer.get(player);
              if (playerEx.getWerewolfLevel() > 0) {
                double MAX_RANGE_SQ = 64.0D;
                if ((playerEx.getWerewolfLevel() == 1) || (player.func_70092_e(0.5D + posX, 0.5D + posY, 0.5D + posZ) <= 64.0D)) {
                  if (field_73012_v.nextInt(4) != 0) {
                    playerEx.setWerewolfLevel(0);
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
            else if ((entity instanceof EntityVillagerWere)) {
              EntityVillagerWere villager = (EntityVillagerWere)entity;
              EntityWolfman.convertToCuredVillager(villager, villager.func_70946_n(), field_70956_bz, field_70963_i);
              complete = true;
            } else if ((entity instanceof EntityWolfman)) {
              EntityWolfman villager = (EntityWolfman)entity;
              EntityWolfman.convertToCuredVillager(villager, villager.getFormerProfession(), villager.getWealth(), villager.getBuyingList());
              complete = true;
            } else {
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
