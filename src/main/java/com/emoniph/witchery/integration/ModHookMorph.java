package com.emoniph.witchery.integration;

import com.emoniph.witchery.Witchery;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.InvocationTargetException;
import net.minecraft.entity.player.EntityPlayer;

public class ModHookMorph extends ModHook
{
  @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  private static java.lang.reflect.Method methodHasMorph;
  
  public ModHookMorph() {}
  
  public String getModID()
  {
    return "Morph";
  }
  
  protected void doInit()
  {
    modHooksisMorphPresent = true;
  }
  



  protected void doPostInit() {}
  


  protected void doReduceMagicPower(net.minecraft.entity.EntityLivingBase entity, float factor) {}
  


  @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  public static boolean isMorphed(EntityPlayer player, boolean client)
  {
    if (!modHooksisMorphPresent) {
      return false;
    }
    
    if (methodHasMorph == null) {
      try {
        methodHasMorph = Class.forName("morph.common.core.ApiHandler").getDeclaredMethod("hasMorph", new Class[] { String.class, Boolean.TYPE });
      }
      catch (ClassNotFoundException ex) {}catch (NoSuchMethodException ex) {}
    }
    

    if (methodHasMorph != null) {
      try {
        return ((Boolean)methodHasMorph.invoke(null, new Object[] { player.func_70005_c_(), Boolean.valueOf(client) })).booleanValue();
      }
      catch (IllegalAccessException ex) {}catch (InvocationTargetException ex) {}
    }
    
    return false;
  }
}
