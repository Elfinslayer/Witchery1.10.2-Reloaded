package com.emoniph.witchery.integration;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import powercrystals.minefactoryreloaded.api.ReplacementBlock;

public class MFRPlantable implements powercrystals.minefactoryreloaded.api.IFactoryPlantable
{
  protected Item seedItemID;
  protected Block cropBlockID;
  
  public MFRPlantable(Item seeds, Block plantedBlock)
  {
    seedItemID = seeds;
    cropBlockID = plantedBlock;
  }
  
  public boolean canBePlantedHere(World world, int x, int y, int z, ItemStack stack)
  {
    Block blockID = world.func_147439_a(x, y - 1, z);
    if (world.func_147437_c(x, y, z)) {
      return ((cropBlockID.func_149742_c(world, x, y, z)) && (cropBlockID.func_149718_j(world, x, y, z))) || (((cropBlockID instanceof IPlantable)) && (cropBlockID.canSustainPlant(world, x, y, z, net.minecraftforge.common.util.ForgeDirection.UP, (IPlantable)cropBlockID)));
    }
    

    return false;
  }
  


  public void prePlant(World world, int x, int y, int z, ItemStack stack) {}
  


  public void postPlant(World world, int x, int y, int z, ItemStack stack) {}
  

  public Item getSeed()
  {
    return seedItemID;
  }
  
  public boolean canBePlanted(ItemStack stack, boolean forFermenting)
  {
    return stack.func_77973_b() == seedItemID;
  }
  
  public ReplacementBlock getPlantedBlock(World world, int x, int y, int z, ItemStack stack)
  {
    return new ReplacementBlock(cropBlockID);
  }
}
