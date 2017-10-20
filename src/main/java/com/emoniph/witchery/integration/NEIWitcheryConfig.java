package com.emoniph.witchery.integration;

import codechicken.nei.api.API;
import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.util.Config;
import cpw.mods.fml.common.Mod;
import net.minecraft.item.ItemStack;

public class NEIWitcheryConfig implements codechicken.nei.api.IConfigureNEI
{
  public NEIWitcheryConfig() {}
  
  public void loadConfig()
  {
    if ((instanceallowModIntegration) && (instanceallowNotEnoughItems)) {
      API.hideItem(new ItemStack(BlocksOVEN_BURNING));
      API.hideItem(new ItemStack(BlocksDISTILLERY_BURNING));
      API.hideItem(new ItemStack(BlocksBARRIER));
      API.hideItem(new ItemStack(BlocksFORCE));
      API.hideItem(new ItemStack(BlocksCIRCLE));
      API.hideItem(new ItemStack(BlocksGLYPH_RITUAL));
      API.hideItem(new ItemStack(BlocksGLYPH_INFERNAL));
      API.hideItem(new ItemStack(BlocksGLYPH_OTHERWHERE));
      API.hideItem(new ItemStack(BlocksCROP_BELLADONNA));
      API.hideItem(new ItemStack(BlocksCROP_MANDRAKE));
      API.hideItem(new ItemStack(BlocksCROP_ARTICHOKE));
      API.hideItem(new ItemStack(BlocksCROP_SNOWBELL));
      API.hideItem(new ItemStack(BlocksCROP_WORMWOOD));
      API.hideItem(new ItemStack(BlocksCROP_MINDRAKE));
      API.hideItem(new ItemStack(BlocksCHALICE));
      API.hideItem(new ItemStack(BlocksCANDELABRA));
      API.hideItem(new ItemStack(BlocksDREAM_CATCHER));
      API.hideItem(new ItemStack(BlocksDOOR_ALDER));
      API.hideItem(new ItemStack(BlocksDOOR_ROWAN));
      API.hideItem(new ItemStack(BlocksPERPETUAL_ICE_DOOR));
      API.hideItem(new ItemStack(BlocksGLOW_GLOBE));
      API.hideItem(new ItemStack(BlocksPLACED_ITEMSTACK));
      API.hideItem(new ItemStack(BlocksDEMON_HEART));
      API.hideItem(new ItemStack(BlocksFORCE));
      API.hideItem(new ItemStack(BlocksWEB));
      API.hideItem(new ItemStack(BlocksVINE));
      API.hideItem(new ItemStack(BlocksCACTUS));
      API.hideItem(new ItemStack(BlocksLILY));
      API.hideItem(new ItemStack(BlocksBREW_GAS));
      API.hideItem(new ItemStack(BlocksBREW_LIQUID));
      API.hideItem(new ItemStack(BlocksBREW));
      API.hideItem(new ItemStack(BlocksSLURP));
      API.hideItem(new ItemStack(ItemsBREW));
      API.hideItem(new ItemStack(ItemsBUCKET_BREW));
      API.hideItem(new ItemStack(BlocksCURSED_BUTTON_STONE));
      API.hideItem(new ItemStack(BlocksCURSED_BUTTON_WOOD));
      API.hideItem(new ItemStack(BlocksCURSED_LEVER));
      API.hideItem(new ItemStack(BlocksCURSED_SNOW_PRESSURE_PLATE));
      API.hideItem(new ItemStack(BlocksCURSED_STONE_PRESSURE_PLATE));
      API.hideItem(new ItemStack(BlocksCURSED_WOODEN_DOOR));
      API.hideItem(new ItemStack(BlocksCURSED_WOODEN_PRESSURE_PLATE));
      API.hideItem(new ItemStack(BlocksCROP_WOLFSBANE));
      API.hideItem(new ItemStack(BlocksCROP_GARLIC));
      API.hideItem(new ItemStack(BlocksWALLGEN));
      API.hideItem(new ItemStack(BlocksLIGHT));
      API.hideItem(new ItemStack(BlocksSHADED_GLASS_ON));
      API.hideItem(new ItemStack(BlocksMIRROR));
      API.hideItem(new ItemStack(BlocksMIRROR_UNBREAKABLE));
      

      API.registerRecipeHandler(new NEIWitchesOvenRecipeHandler());
      API.registerUsageHandler(new NEIWitchesOvenRecipeHandler());
      
      API.registerRecipeHandler(new NEIDistilleryRecipeHandler());
      API.registerUsageHandler(new NEIDistilleryRecipeHandler());
      
      API.registerRecipeHandler(new NEIKettleRecipeHandler());
      API.registerRecipeHandler(new NEICauldronRecipeHandler());
      API.registerUsageHandler(new NEICauldronRecipeHandler());
      
      API.registerRecipeHandler(new NEISpinningWheelRecipeHandler());
      API.registerUsageHandler(new NEISpinningWheelRecipeHandler());
      

















      API.registerHighlightIdentifier(BlocksTRAPPED_PLANT, new NEIHighlightHandler(BlocksTRAPPED_PLANT));
      API.registerHighlightIdentifier(BlocksDOOR_ALDER, new NEIHighlightHandler(BlocksDOOR_ALDER));
      API.registerHighlightIdentifier(BlocksPIT_DIRT, new NEIHighlightHandler(BlocksPIT_DIRT));
      API.registerHighlightIdentifier(BlocksPIT_GRASS, new NEIHighlightHandler(BlocksPIT_GRASS));
    }
  }
  
  public String getName()
  {
    return ((Mod)Witchery.class.getAnnotation(Mod.class)).name();
  }
  
  public String getVersion()
  {
    return ((Mod)Witchery.class.getAnnotation(Mod.class)).version();
  }
}
