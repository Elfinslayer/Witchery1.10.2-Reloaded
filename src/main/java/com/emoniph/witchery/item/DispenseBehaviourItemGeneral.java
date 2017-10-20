package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class DispenseBehaviourItemGeneral
  implements net.minecraft.dispenser.IBehaviorDispenseItem
{
  private final BehaviorDefaultDispenseItem defaultDispenserItemBehavior;
  
  public DispenseBehaviourItemGeneral() { defaultDispenserItemBehavior = new BehaviorDefaultDispenseItem(); }
  
  public ItemStack func_82482_a(net.minecraft.dispenser.IBlockSource block, ItemStack stack) {
    if (ItemsGENERIC.isBrew(stack.func_77960_j())) {
      return new DispenserBehaviorBrew(this, stack).func_82482_a(block, stack);
    }
    return defaultDispenserItemBehavior.func_82482_a(block, stack);
  }
  
  static class DispenserBehaviorBrew extends BehaviorProjectileDispense
  {
    final ItemStack potionItemStack;
    final DispenseBehaviourItemGeneral dispenserPotionBehavior;
    
    DispenserBehaviorBrew(DispenseBehaviourItemGeneral par1DispenserBehaviorPotion, ItemStack par2ItemStack)
    {
      dispenserPotionBehavior = par1DispenserBehaviorPotion;
      potionItemStack = par2ItemStack;
    }
    
    protected net.minecraft.entity.IProjectile func_82499_a(World par1World, IPosition par2IPosition) {
      return new com.emoniph.witchery.entity.EntityWitchProjectile(par1World, par2IPosition.func_82615_a(), par2IPosition.func_82617_b(), par2IPosition.func_82616_c(), (ItemGeneral.SubItem)ItemsGENERIC.subItems.get(potionItemStack.func_77960_j()));
    }
    
    protected float func_82498_a()
    {
      return super.func_82498_a() * 0.5F;
    }
    
    protected float func_82500_b() {
      return super.func_82500_b() * 1.25F;
    }
  }
}
