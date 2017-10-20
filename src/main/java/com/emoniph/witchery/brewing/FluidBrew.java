package com.emoniph.witchery.brewing;

import net.minecraftforge.fluids.FluidStack;

public class FluidBrew extends net.minecraftforge.fluids.Fluid
{
  public FluidBrew(String fluidName) {
    super(fluidName);
  }
  
  public int getColor(FluidStack stack)
  {
    int color = WitcheryBrewRegistry.INSTANCE.getBrewColor(tag);
    return color;
  }
}
