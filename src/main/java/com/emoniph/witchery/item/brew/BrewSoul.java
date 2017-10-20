package com.emoniph.witchery.item.brew;

import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
import com.emoniph.witchery.item.ItemGeneral.Drinkable;
import net.minecraft.entity.player.EntityPlayer;

public class BrewSoul extends ItemGeneral.Drinkable
{
  private final SymbolEffect effect;
  
  public BrewSoul(int damageValue, String unlocalizedName, SymbolEffect effect)
  {
    super(damageValue, unlocalizedName, 1, new net.minecraft.potion.PotionEffect[0]);
    this.effect = effect;
    setPotion(true);
  }
  
  public void onDrunk(net.minecraft.world.World world, EntityPlayer player, net.minecraft.item.ItemStack itemstack)
  {
    effect.acquireKnowledge(player);
  }
}
