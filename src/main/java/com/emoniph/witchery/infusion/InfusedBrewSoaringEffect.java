package com.emoniph.witchery.infusion;

import net.minecraft.entity.player.EntityPlayer;

public class InfusedBrewSoaringEffect extends InfusedBrewEffect
{
  public InfusedBrewSoaringEffect(int id, long durationMS)
  {
    super(id, durationMS, 16, 0);
  }
  
  public void immediateEffect(net.minecraft.world.World world, EntityPlayer player, net.minecraft.item.ItemStack stack) {}
  
  public void regularEffect(net.minecraft.world.World world, EntityPlayer player) {}
}
