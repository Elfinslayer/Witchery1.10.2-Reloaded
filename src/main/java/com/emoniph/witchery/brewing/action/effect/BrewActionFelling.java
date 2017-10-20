package com.emoniph.witchery.brewing.action.effect;

import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.blocks.BlockCircleGlyph;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BrewActionFelling extends BrewActionEffect
{
  private final int strengthReduction;
  
  public BrewActionFelling(Item axe, int strengthReduction, AltarPower powerCost, EffectLevel effectLevel)
  {
    super(new BrewItemKey(axe, 32767), new BrewNamePart("witchery:brew.felling"), powerCost, new Probability(1.0D), effectLevel);
    
    this.strengthReduction = strengthReduction;
  }
  

  protected void doApplyToBlock(World world, int posX, int posY, int posZ, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack stack)
  {
    int strength = Math.max(modifiers.getStrength() - strengthReduction, 0);
    int BLOCK_RADIUS = Math.max(radius - (strengthReduction - 1) - 1, 1);
    int BLOCK_RADIUS_SQ = BLOCK_RADIUS * BLOCK_RADIUS;
    int blockX = MathHelper.func_76128_c(posX);
    int blockY = MathHelper.func_76128_c(posY);
    int blockZ = MathHelper.func_76128_c(posZ);
    for (int y = blockY - BLOCK_RADIUS; y <= blockY + BLOCK_RADIUS; y++) {
      for (int x = blockX - BLOCK_RADIUS; x <= blockX + BLOCK_RADIUS; x++) {
        for (int z = blockZ - BLOCK_RADIUS; z <= blockZ + BLOCK_RADIUS; z++) {
          if ((Coord.distanceSq(x, y, z, blockX, blockY, blockZ) <= BLOCK_RADIUS_SQ) && 
            (BlockProtect.checkModsForBreakOK(world, x, y, z, caster))) {
            Block block = world.func_147439_a(x, y, z);
            Material material = block.func_149688_o();
            if ((material != null) && (material == Material.field_151575_d) && (block.canSustainLeaves(world, x, y, z)))
            {
              Block blockID = world.func_147439_a(x, y, z);
              if ((!(blockID instanceof BlockCircle)) && (!(blockID instanceof BlockCircleGlyph))) {
                blockID.func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), strength);
                
                world.func_147468_f(x, y, z);
              }
            }
          }
        }
      }
    }
  }
}
