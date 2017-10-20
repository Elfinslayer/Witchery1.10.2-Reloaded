package com.emoniph.witchery.integration;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryFluids;
import net.minecraft.nbt.NBTTagCompound;

public class ModHookMystCraft extends ModHook
{
  public ModHookMystCraft() {}
  
  public String getModID()
  {
    return "Mystcraft";
  }
  
  protected void doInit()
  {
    removeMystCraftFluid(FluidsFLOWING_SPIRIT.getName());
    removeMystCraftFluid(FluidsHOLLOW_TEARS.getName());
    removeMystCraftFluid(FluidsBREW.getName());
    removeMystCraftFluid(FluidsBREW_LIQUID.getName());
    removeMystCraftFluid(FluidsBREW_GAS.getName());
  }
  
  private void removeMystCraftFluid(String fluid) {
    NBTTagCompound nbtRoot = new NBTTagCompound();
    nbtRoot.func_74782_a("fluidsymbol", new NBTTagCompound());
    NBTTagCompound nbtSymbol = nbtRoot.func_74775_l("fluidsymbol");
    nbtSymbol.func_74778_a("fluidname", fluid);
    nbtSymbol.func_74776_a("rarity", 0.0F);
    nbtSymbol.func_74776_a("grammarweight", 0.0F);
    nbtSymbol.func_74776_a("instabilityPerBlock", 10000.0F);
    cpw.mods.fml.common.event.FMLInterModComms.sendMessage(getModID(), "fluidsymbol", nbtRoot);
  }
  
  protected void doPostInit() {}
  
  protected void doReduceMagicPower(net.minecraft.entity.EntityLivingBase entity, float factor) {}
}
