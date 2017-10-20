package com.emoniph.witchery.integration;

import codechicken.nei.api.IHighlightHandler;
import codechicken.nei.api.ItemInfo.Layout;
import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;


public class NEIHighlightHandler
  implements IHighlightHandler
{
  private static final ItemStack yellowPlant = new ItemStack(Blocks.field_150327_N);
  private static final ItemStack redPlant = new ItemStack(Blocks.field_150328_O);
  private static final ItemStack shrubPlant = new ItemStack(Blocks.field_150330_I);
  private static final ItemStack door = new ItemStack(Blocks.field_150466_ao);
  private static final ItemStack dirt = new ItemStack(Blocks.field_150346_d);
  private static final ItemStack grass = new ItemStack(Blocks.field_150349_c);
  private final Block block;
  
  public NEIHighlightHandler(Block block) {
    this.block = block;
  }
  
  public ItemStack identifyHighlight(World world, EntityPlayer player, MovingObjectPosition mop)
  {
    if (block == BlocksTRAPPED_PLANT) {
      if ((mop == null) || (field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY)) {
        return null;
      }
      int foundMeta = world.func_72805_g(field_72311_b, field_72312_c, field_72309_d);
      if ((foundMeta == 5) || (foundMeta == 6) || (foundMeta == 7) || (foundMeta == 4))
        return yellowPlant;
      if ((foundMeta == 1) || (foundMeta == 2) || (foundMeta == 3) || (foundMeta == 0))
        return redPlant;
      if ((foundMeta == 9) || (foundMeta == 10) || (foundMeta == 11) || (foundMeta == 8))
        return shrubPlant;
    } else {
      if (block == BlocksDOOR_ALDER)
        return door;
      if (block == BlocksPIT_DIRT)
        return dirt;
      if (block == BlocksPIT_GRASS)
        return grass;
    }
    return null;
  }
  
  public List<String> handleTextData(ItemStack itemStack, World world, EntityPlayer player, MovingObjectPosition mop, List<String> currenttip, ItemInfo.Layout layout)
  {
    return null;
  }
}
