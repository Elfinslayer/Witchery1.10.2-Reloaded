package com.emoniph.witchery;

import com.emoniph.witchery.item.ItemGeneral;
import net.minecraft.creativetab.CreativeTabs;

public final class WitcheryCreativeTab extends CreativeTabs
{
  public static final WitcheryCreativeTab INSTANCE = new WitcheryCreativeTab();
  
  private WitcheryCreativeTab() {
    super("tabWitchery");
  }
  
  public net.minecraft.item.Item func_78016_d()
  {
    return ItemsPOPPET;
  }
  
  public net.minecraft.item.ItemStack func_151244_d()
  {
    return ItemsGENERIC.itemBroomEnchanted.createStack();
  }
}
