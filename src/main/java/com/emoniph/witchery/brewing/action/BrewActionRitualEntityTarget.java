package com.emoniph.witchery.brewing.action;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockAreaMarker.AreaMarkerRegistry;
import com.emoniph.witchery.brewing.AltarPower;
import com.emoniph.witchery.brewing.BrewItemKey;
import com.emoniph.witchery.brewing.ModifiersEffect;
import com.emoniph.witchery.brewing.ModifiersImpact;
import com.emoniph.witchery.brewing.ModifiersRitual;
import com.emoniph.witchery.brewing.RitualStatus;
import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
import com.emoniph.witchery.item.ItemTaglockKit;
import com.emoniph.witchery.util.EntityPosition;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class BrewActionRitualEntityTarget extends BrewActionRitual
{
  public BrewActionRitualEntityTarget(BrewItemKey itemKey, AltarPower powerCost)
  {
    super(itemKey, powerCost, false);
  }
  

  public RitualStatus updateRitual(MinecraftServer server, BrewActionList actionList, World world, int x, int y, int z, ModifiersRitual modifiers, ModifiersImpact impactModifiers)
  {
    net.minecraft.nbt.NBTTagCompound tag = actionList.getTopItemStack().func_77978_p();
    if (tag == null) {
      return RitualStatus.FAILED;
    }
    
    EntityLivingBase targetEntity = ItemsTAGLOCK_KIT.getBoundEntity(world, null, actionList.getTopItemStack(), Integer.valueOf(1));
    
    if (targetEntity != null) {
      if (!isDistanceAllowed(world, x, y, z, field_70165_t, field_70163_u, field_70161_v, field_71093_bK, covenSize, leonard))
      {
        return RitualStatus.FAILED_DISTANCE;
      }
      
      if (!actionList.isTargetLocationValid(server, world, x, y, z, modifiers.getTarget(), modifiers)) {
        return RitualStatus.FAILED_INVALID_CIRCLES;
      }
      
      ModifiersEffect modifiersEffect = new ModifiersEffect(1.0D, 1.0D, false, new EntityPosition(targetEntity), true, covenSize, com.emoniph.witchery.util.EntityUtil.playerOrFake(world, (EntityPlayer)null));
      
      taglockUsed = true;
      
      boolean isImmune = com.emoniph.witchery.item.ItemHunterClothes.isCurseProtectionActive(targetEntity);
      if (!isImmune) {
        isImmune = BlockAreaMarker.AreaMarkerRegistry.instance().isProtectionActive(targetEntity, null);
      }
      
      if ((!isImmune) && 
        (!ItemsPOPPET.voodooProtectionActivated(null, null, targetEntity, 1))) {
        WitcheryBrewRegistry.INSTANCE.applyRitualToEntity(field_70170_p, targetEntity, actionList.getTagCompound(), modifiers, modifiersEffect);
      }
      

    }
    else
    {
      return RitualStatus.FAILED;
    }
    return RitualStatus.COMPLETE;
  }
}
