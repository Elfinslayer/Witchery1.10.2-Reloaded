package com.emoniph.witchery.item;

import com.emoniph.witchery.WitcheryCreativeTab;
import com.emoniph.witchery.blocks.BlockWitchCrop;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class ItemWitchSeeds extends ItemSeeds
{
  private final boolean waterPlant;
  
  public ItemWitchSeeds(BlockWitchCrop plantedBlock, ItemStack cropItemStack, Block soilBlock, boolean waterPlant)
  {
    super(plantedBlock, soilBlock);
    
    this.waterPlant = waterPlant;
    func_77656_e(0);
    func_77625_d(64);
    func_77637_a(WitcheryCreativeTab.INSTANCE);
    
    plantedBlock.setSeedItem(new ItemStack(this));
    plantedBlock.setCropItem(cropItemStack);
  }
  
  public Item func_77655_b(String itemName)
  {
    com.emoniph.witchery.util.ItemUtil.registerItem(this, itemName);
    return super.func_77655_b(itemName);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
  {
    if (waterPlant) {
      MovingObjectPosition mop = func_77621_a(world, player, true);
      if ((mop != null) && (field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK) && (field_72310_e == 1)) {
        float f = (float)field_72307_f.field_72450_a - field_72311_b;
        float f1 = (float)field_72307_f.field_72448_b - field_72312_c;
        float f2 = (float)field_72307_f.field_72449_c - field_72309_d;
        stack.func_77943_a(player, world, field_72311_b, field_72312_c, field_72309_d, field_72310_e, f, f1, f2);
      }
    }
    return super.func_77659_a(stack, world, player);
  }
  
  public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
  {
    return waterPlant ? EnumPlantType.Water : super.getPlantType(world, x, y, z);
  }
}
