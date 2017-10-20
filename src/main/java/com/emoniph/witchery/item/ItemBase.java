package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryCreativeTab;
import com.emoniph.witchery.util.ItemUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;



public class ItemBase
  extends Item
{
  protected boolean registerWithCreativeTab = true;
  protected boolean autoGenerateTooltip = false;
  

  public ItemBase() {}
  
  public Item func_77655_b(String itemName)
  {
    ItemUtil.registerItem(this, itemName);
    
    if (registerWithCreativeTab) {
      func_77637_a(WitcheryCreativeTab.INSTANCE);
    }
    
    return super.func_77655_b(itemName);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean moreTips)
  {
    if (autoGenerateTooltip) {
      String localText = Witchery.resource(func_77658_a() + ".tip");
      if (localText != null) {
        for (String s : localText.split("\n")) {
          if (!s.isEmpty()) {
            list.add(s);
          }
        }
      }
    }
  }
}
