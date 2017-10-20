package com.emoniph.witchery.brewing.potions;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;

public class PotionFortune extends PotionBase implements IHandleHarvestDrops
{
  public PotionFortune(int id, int color)
  {
    super(id, color);
  }
  
  public void onHarvestDrops(World world, EntityPlayer player, BlockEvent.HarvestDropsEvent event, int amplifier)
  {
    if ((!world.field_72995_K) && (!isSilkTouching) && (block != null) && (!block.hasTileEntity(blockMetadata)) && (drops.size() > 0))
    {
      ArrayList<net.minecraft.item.ItemStack> drops = block.getDrops(world, x, y, z, blockMetadata, fortuneLevel + (amplifier > 2 ? 2 : 1));
      
      drops.clear();
      drops.addAll(drops);
    }
  }
}
