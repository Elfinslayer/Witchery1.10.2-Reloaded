package com.emoniph.witchery.integration;

import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.entity.EntityLivingBase;

public class ModHookWaila extends ModHook
{
  public ModHookWaila() {}
  
  public String getModID()
  {
    return "Waila";
  }
  
  protected void doInit()
  {
    FMLInterModComms.sendMessage(getModID(), "register", "com.emoniph.witchery.integration.ModHookWailaRegistrar.callbackRegister");
  }
  
  protected void doPostInit() {}
  
  protected void doReduceMagicPower(EntityLivingBase entity, float factor) {}
}
