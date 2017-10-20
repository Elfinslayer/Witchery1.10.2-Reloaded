package com.emoniph.witchery.infusion;

import com.emoniph.witchery.item.ItemGeneral.Drinkable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class InfusedBrew extends ItemGeneral.Drinkable
{
  private final InfusedBrewEffect effect;
  
  public InfusedBrew(int damageValue, String unlocalizedName, InfusedBrewEffect effect)
  {
    super(damageValue, unlocalizedName, 2, new net.minecraft.potion.PotionEffect[0]);
    this.effect = effect;
    potion = true;
  }
  
  public void onDrunk(net.minecraft.world.World world, EntityPlayer player, ItemStack itemstack)
  {
    effect.drunk(world, player, itemstack);
  }
  
  public boolean isInfused()
  {
    return true;
  }
}
