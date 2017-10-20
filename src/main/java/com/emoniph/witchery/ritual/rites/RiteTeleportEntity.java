package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockAreaMarker.AreaMarkerRegistry;
import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.ritual.RitualStep.SacrificedItem;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class RiteTeleportEntity extends RiteTeleportation
{
  public RiteTeleportEntity(int radius)
  {
    super(radius);
  }
  
  protected boolean teleport(World world, int posX, int posY, int posZ, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
  {
    if (!field_72995_K) {
      ItemStack taglockStack = null;
      for (RitualStep.SacrificedItem item : sacrificedItems) {
        if ((ItemsTAGLOCK_KIT == itemstack.func_77973_b()) && (itemstack.func_77960_j() == 1)) {
          taglockStack = itemstack;
          break;
        }
      }
      
      if (taglockStack != null) {
        EntityLivingBase entity = ItemsTAGLOCK_KIT.getBoundEntity(world, null, taglockStack, Integer.valueOf(1));
        if ((entity != null) && (field_71093_bK != instancedimensionDreamID) && (field_73011_w.field_76574_g != instancedimensionDreamID)) {
          net.minecraft.entity.player.EntityPlayer player = ritual.getInitiatingPlayer(world);
          boolean isImmune = com.emoniph.witchery.item.ItemHunterClothes.isCurseProtectionActive(entity);
          if (!isImmune) {
            isImmune = BlockAreaMarker.AreaMarkerRegistry.instance().isProtectionActive(entity, this);
          }
          if ((!isImmune) && (!ItemsPOPPET.voodooProtectionActivated(player, null, entity, true, true)) && (!com.emoniph.witchery.brewing.potions.PotionEnderInhibition.isActive(entity, 3)))
          {
            ItemGeneral.teleportToLocation(world, posX, posY, posZ, field_73011_w.field_76574_g, entity, true);
            return true;
          }
          
          if (player != null) {
            if (isImmune) {
              ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.blackmagicdampening", new Object[0]);
            } else {
              ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.voodooprotectionactivated", new Object[0]);
            }
          }
          return false;
        }
      }
    }
    

    return false;
  }
}
