package com.emoniph.witchery.item;

import com.emoniph.witchery.WitcheryCreativeTab;
import com.emoniph.witchery.util.ItemUtil;
import net.minecraft.item.ItemPickaxe;

public class ItemKobolditePickaxe extends ItemPickaxe
{
  public ItemKobolditePickaxe()
  {
    super(net.minecraft.item.Item.ToolMaterial.EMERALD);
    func_77637_a(WitcheryCreativeTab.INSTANCE);
  }
  
  public net.minecraft.item.Item func_77655_b(String itemName)
  {
    ItemUtil.registerItem(this, itemName);
    return super.func_77655_b(itemName);
  }
}
