package com.emoniph.witchery.predictions;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;

public class PredictionMultiMine extends PredictionAlwaysForced
{
  protected final Block block;
  protected final ItemStack itemPrototype;
  protected final int minExtra;
  protected final int maxExtra;
  
  public PredictionMultiMine(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey, int regularFulfillmentDurationInTicks, double regularFulfillmentProbability, Block block, ItemStack itemPrototype, int minExtra, int maxExtra)
  {
    super(id, itemWeight, selfFulfillmentProbabilityPerSec, translationKey, regularFulfillmentDurationInTicks, regularFulfillmentProbability);
    this.block = block;
    this.minExtra = minExtra;
    this.maxExtra = maxExtra;
    this.itemPrototype = itemPrototype;
  }
  
  public boolean checkIfFulfilled(World world, net.minecraft.entity.player.EntityPlayer player, BlockEvent.HarvestDropsEvent event, boolean isPastDue, boolean veryOld)
  {
    if ((!event.isCanceled()) && 
      ((block == block) || ((veryOld) && (block == net.minecraft.init.Blocks.field_150348_b))) && (shouldWeActivate(world, player, isPastDue))) {
      int optional = maxExtra - minExtra;
      int totalExtra = minExtra + (optional > 1 ? field_73012_v.nextInt(optional) + 1 : optional);
      for (int i = 0; i < totalExtra; i++) {
        drops.add(itemPrototype.func_77946_l());
      }
      return true;
    }
    
    return false;
  }
}
