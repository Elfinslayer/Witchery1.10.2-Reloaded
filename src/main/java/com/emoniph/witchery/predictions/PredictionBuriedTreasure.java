package com.emoniph.witchery.predictions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;

public class PredictionBuriedTreasure extends PredictionAlwaysForced
{
  protected final String chestGenHook;
  
  public PredictionBuriedTreasure(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey, int regularFulfillmentDurationInTicks, double regularFulfillmentProbability, String chestGenHook)
  {
    super(id, itemWeight, selfFulfillmentProbabilityPerSec, translationKey, regularFulfillmentDurationInTicks, regularFulfillmentProbability);
    this.chestGenHook = chestGenHook;
  }
  
  public boolean shouldTrySelfFulfill(World world, EntityPlayer player)
  {
    return false;
  }
  
  public boolean doSelfFulfillment(World world, EntityPlayer player)
  {
    return false;
  }
  
  public boolean checkIfFulfilled(World world, EntityPlayer player, BlockEvent.HarvestDropsEvent event, boolean isPastDue, boolean veryOld)
  {
    if ((!event.isCanceled()) && 
      ((block == Blocks.field_150349_c) || (block == Blocks.field_150346_d) || (block == Blocks.field_150354_m) || (block == Blocks.field_150391_bh)) && (y > 6) && (shouldWeActivate(world, player, isPastDue)))
    {
      if ((!world.func_147437_c(x + 1, y - 1, z)) && (!world.func_147437_c(x - 1, y - 1, z)) && (!world.func_147437_c(x, y - 1, z + 1)) && (!world.func_147437_c(x, y - 1, z - 1)) && (!world.func_147437_c(x, y - 2, z)))
      {



        world.func_147449_b(x, y - 1, z, Blocks.field_150486_ae);
        net.minecraft.tileentity.TileEntity tile = world.func_147438_o(x, y - 1, z);
        if ((tile != null) && ((tile instanceof TileEntityChest))) {
          TileEntityChest chest = (TileEntityChest)tile;
          ChestGenHooks info = ChestGenHooks.getInfo(chestGenHook);
          WeightedRandomChestContent.func_76293_a(field_73012_v, info.getItems(field_73012_v), chest, info.getCount(field_73012_v));
        }
        return true;
      }
    }
    
    return false;
  }
}
