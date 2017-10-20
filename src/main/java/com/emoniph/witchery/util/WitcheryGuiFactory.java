package com.emoniph.witchery.util;

import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.IModGuiFactory.RuntimeOptionCategoryElement;
import cpw.mods.fml.client.IModGuiFactory.RuntimeOptionGuiHandler;
import java.util.Set;
import net.minecraft.client.Minecraft;

public class WitcheryGuiFactory implements IModGuiFactory
{
  public WitcheryGuiFactory() {}
  
  public void initialize(Minecraft minecraftInstance) {}
  
  public Class<? extends net.minecraft.client.gui.GuiScreen> mainConfigGuiClass()
  {
    return WitcheryConfigGui.class;
  }
  
  public Set<IModGuiFactory.RuntimeOptionCategoryElement> runtimeGuiCategories()
  {
    return null;
  }
  
  public IModGuiFactory.RuntimeOptionGuiHandler getHandlerFor(IModGuiFactory.RuntimeOptionCategoryElement element)
  {
    return null;
  }
}
