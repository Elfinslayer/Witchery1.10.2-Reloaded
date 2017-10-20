package com.emoniph.witchery.brewing.action.effect;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.brewing.AltarPower;
import com.emoniph.witchery.brewing.BrewItemKey;
import com.emoniph.witchery.brewing.BrewNamePart;
import com.emoniph.witchery.brewing.EffectLevel;
import com.emoniph.witchery.brewing.ModifiersEffect;
import com.emoniph.witchery.brewing.Probability;
import com.emoniph.witchery.brewing.action.BrewActionEffect;
import com.emoniph.witchery.util.BlockProtect;
import com.emoniph.witchery.util.Coord;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;










public class BrewActionLilify
  extends BrewActionEffect
{
  public BrewActionLilify(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, EffectLevel effectLevel)
  {
    super(itemKey, namePart, powerCost, new Probability(1.0D), effectLevel);
  }
  

  protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
  {
    x += offsetX;
    y += offsetY;
    z += offsetZ;
    
    while (((world.func_147439_a(x, y, z).func_149688_o() != Material.field_151586_h) || (!world.func_147437_c(x, y + 1, z))) && (y < 255)) {
      y++;
    }
    
    if ((world.func_147439_a(x, y, z).func_149688_o() == Material.field_151586_h) && (world.func_147437_c(x, y + 1, z)) && (BlockProtect.checkModsForBreakOK(world, x, y + 1, z, caster)))
    {
      int meta = (modifiers.getStrength() & 0x3) << 2;
      world.func_147465_d(x, y + 1, z, BlocksLILY, meta, 3);
    }
  }
  

  protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
  {
    Coord coord = new Coord(targetEntity);
    doApplyToBlock(world, x, y, z, ForgeDirection.UP, 1, modifiers, actionStack);
  }
}
