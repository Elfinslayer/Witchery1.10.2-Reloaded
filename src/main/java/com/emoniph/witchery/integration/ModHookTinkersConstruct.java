package com.emoniph.witchery.integration;

import net.minecraft.entity.EntityLivingBase;

public class ModHookTinkersConstruct extends ModHook
{
  public ModHookTinkersConstruct() {}
  
  public String getModID()
  {
    return "TConstruct";
  }
  
  protected void doInit()
  {
    modHooksisTinkersPresent = true;
  }
  
  protected void doPostInit() {}
  
  protected void doReduceMagicPower(EntityLivingBase entity, float factor) {}
}
