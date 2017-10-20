package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import java.util.ArrayList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGeneralContract extends ItemGeneral.SubItem
{
  public ItemGeneralContract(int damageValue, String unlocalizedName)
  {
    super(damageValue, unlocalizedName);
  }
  
  public static boolean isBoundContract(ItemStack stack) {
    if (stack.func_77973_b() == ItemsGENERIC) {
      ItemGeneral.SubItem subItem = (ItemGeneral.SubItem)ItemsGENERIC.subItems.get(Math.max(stack.func_77960_j(), 0));
      if ((subItem instanceof ItemGeneralContract)) {
        return ItemsTAGLOCK_KIT.isTaglockPresent(stack, Integer.valueOf(1));
      }
      return false;
    }
    
    return false;
  }
  
  public static EntityLivingBase getBoundEntity(World world, net.minecraft.entity.player.EntityPlayer player, ItemStack stack)
  {
    EntityLivingBase boundEntity = ItemsTAGLOCK_KIT.getBoundEntity(world, player, stack, Integer.valueOf(1));
    return boundEntity;
  }
  
  public static ItemGeneralContract getContract(ItemStack stack) {
    ItemGeneral.SubItem subItem = (ItemGeneral.SubItem)ItemsGENERIC.subItems.get(stack.func_77960_j());
    if ((subItem instanceof ItemGeneralContract)) {
      return (ItemGeneralContract)subItem;
    }
    return null;
  }
  
  public boolean activate(ItemStack stack, EntityLivingBase targetEntity)
  {
    return false;
  }
}
