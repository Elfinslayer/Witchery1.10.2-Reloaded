package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockAreaMarker.AreaMarkerRegistry;
import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.ritual.RitualStep.SacrificedItem;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class RiteCurseCreature extends com.emoniph.witchery.ritual.Rite
{
  private final boolean curse;
  private final int level;
  private final String curseType;
  
  public RiteCurseCreature(boolean curse, String curseType, int level)
  {
    this.curse = curse;
    this.level = level;
    this.curseType = curseType;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int intialStage)
  {
    steps.add(new StepCurseCreature(this));
  }
  
  private static class StepCurseCreature extends RitualStep {
    private final RiteCurseCreature rite;
    private static final int CURSE_MASTER_BONUS_LEVELS = 1;
    
    public StepCurseCreature(RiteCurseCreature rite) {
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
        int levelBuff = (curseMasterPlayer != null) && (com.emoniph.witchery.familiar.Familiar.hasActiveCurseMasteryFamiliar(curseMasterPlayer)) ? 1 : 0;
        if (covenSize == 6) {
          levelBuff += 2;
        } else if (covenSize >= 3) {
          levelBuff++;
        }
        
        for (RitualStep.SacrificedItem item : sacrificedItems) {
          if ((itemstack.func_77973_b() == ItemsTAGLOCK_KIT) && (itemstack.func_77960_j() == 1)) {
            EntityLivingBase entity = ItemsTAGLOCK_KIT.getBoundEntity(world, null, itemstack, Integer.valueOf(1));
            if (entity == null) break;
            NBTTagCompound nbtTag = (entity instanceof EntityPlayer) ? Infusion.getNBT(entity) : entity.getEntityData();
            if (nbtTag != null) {
              int currentLevel = nbtTag.func_74764_b(rite.curseType) ? nbtTag.func_74762_e(rite.curseType) : 0;
              if (rite.curse) {
                com.emoniph.witchery.entity.EntityWitchHunter.blackMagicPerformed(curseMasterPlayer);
                
                boolean isImmune = (com.emoniph.witchery.item.ItemHunterClothes.isCurseProtectionActive(entity)) && ((rite.curseType.equals("witcheryCursed")) || (rite.curseType.equals("witcheryWakingNightmare")));
                if (!isImmune) {
                  isImmune = BlockAreaMarker.AreaMarkerRegistry.instance().isProtectionActive(entity, rite);
                }
                
                if (!isImmune) { if (!ItemsPOPPET.voodooProtectionActivated(curseMasterPlayer, null, entity, levelBuff > 0 ? 3 : 1)) {
                    nbtTag.func_74768_a(rite.curseType, Math.max(rite.level + levelBuff, currentLevel));
                    cursed = true;
                    if ((entity instanceof EntityPlayer)) {
                      Infusion.syncPlayer(field_70170_p, (EntityPlayer)entity);
                    }
                  }
                }
                if (isImmune) {
                  if (curseMasterPlayer != null) {
                    ChatUtil.sendTranslated(EnumChatFormatting.RED, curseMasterPlayer, "witchery.rite.blackmagicdampening", new Object[0]);
                  }
                } else {
                  complete = true;
                }
              } else {
                int newLevel = 0;
                if (currentLevel > 0) {
                  if (rite.level + levelBuff > currentLevel) {
                    newLevel = field_73012_v.nextInt(20) == 0 ? currentLevel + 1 : 0;
                  } else if (rite.level + levelBuff < currentLevel) {
                    newLevel = field_73012_v.nextInt(4) == 0 ? 0 : currentLevel + 1;
                  } else {
                    newLevel = field_73012_v.nextInt(4) == 0 ? currentLevel + 1 : 0;
                  }
                }
                if (newLevel == 0) {
                  if (nbtTag.func_74764_b(rite.curseType)) {
                    nbtTag.func_82580_o(rite.curseType);
                  }
                  if (entity.func_70644_a(Potion.field_76436_u)) {
                    entity.func_82170_o(field_76436_ufield_76415_H);
                  }
                  if (entity.func_70644_a(Potion.field_76437_t)) {
                    entity.func_82170_o(field_76437_tfield_76415_H);
                  }
                  if (entity.func_70644_a(Potion.field_76440_q)) {
                    entity.func_82170_o(field_76440_qfield_76415_H);
                  }
                  if (entity.func_70644_a(Potion.field_76419_f)) {
                    entity.func_82170_o(field_76419_ffield_76415_H);
                  }
                  if (entity.func_70644_a(Potion.field_76421_d)) {
                    entity.func_82170_o(field_76421_dfield_76415_H);
                  }
                } else {
                  nbtTag.func_74768_a(rite.curseType, newLevel);
                  cursed = true;
                }
                
                if ((entity instanceof EntityPlayer)) {
                  Infusion.syncPlayer(field_70170_p, (EntityPlayer)entity);
                }
                complete = true;
              }
            }
            
            break;
          }
        }
        

        if (complete) {
          if (cursed) {
            ParticleEffect.FLAME.send(SoundEffect.MOB_ENDERDRAGON_GROWL, world, 0.5D + posX, 0.1D + posY, 0.5D + posZ, 1.0D, 2.0D, 16);
          } else {
            ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_LEVELUP, world, 0.5D + posX, 0.1D + posY, 0.5D + posZ, 1.0D, 2.0D, 16);
          }
        } else {
          return RitualStep.Result.ABORTED_REFUND;
        }
      }
      return RitualStep.Result.COMPLETED;
    }
  }
}
