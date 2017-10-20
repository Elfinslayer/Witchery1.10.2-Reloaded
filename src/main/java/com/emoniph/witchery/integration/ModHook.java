package com.emoniph.witchery.integration;

import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Log;
import cpw.mods.fml.common.Loader;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public abstract class ModHook
{
  public ModHook() {}
  
  protected boolean initialized = false;
  
  public abstract String getModID();
  
  public void init() {
    initialized = ((instanceallowModIntegration) && (Loader.isModLoaded(getModID())));
    if (initialized) {
      doInit();
      Log.instance().debug(String.format("Mod: %s support initialized", new Object[] { getModID() }));
    } else {
      Log.instance().debug(String.format("Mod: %s not found", new Object[] { getModID() }));
    }
  }
  
  protected abstract void doInit();
  
  public void postInit() {
    if (initialized) {
      doPostInit();
      Log.instance().debug(String.format("Mod: %s support post initialized", new Object[] { getModID() }));
    }
  }
  
  protected abstract void doPostInit();
  
  public void reduceMagicPower(EntityLivingBase entity, float factor) {
    if (initialized) {
      doReduceMagicPower(entity, factor);
    }
  }
  
  protected abstract void doReduceMagicPower(EntityLivingBase paramEntityLivingBase, float paramFloat);
  
  public void boostBloodPowers(EntityPlayer player, float health) {}
  
  public boolean canVampireBeKilled(EntityPlayer player)
  {
    return false;
  }
  
  public void tryMakeItemModProof(ItemStack stack) {
    if (initialized) {
      makeItemModProof(stack);
    }
  }
  
  protected void makeItemModProof(ItemStack stack) {}
}
