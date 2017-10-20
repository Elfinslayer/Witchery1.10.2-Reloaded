package com.emoniph.witchery.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
class GuiButtonNavigate extends GuiButton
{
  private final int type;
  private final ResourceLocation texture;
  
  public GuiButtonNavigate(int commandID, int x, int y, int type, ResourceLocation texture)
  {
    super(commandID, x, y, 23, 13, "");
    this.type = type;
    this.texture = texture;
  }
  
  public void func_146112_a(Minecraft mc, int mouseX, int mouseY)
  {
    if (field_146125_m) {
      boolean mouseOver = (mouseX >= field_146128_h) && (mouseY >= field_146129_i) && (mouseX < field_146128_h + field_146120_f) && (mouseY < field_146129_i + field_146121_g);
      
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      mc.func_110434_K().func_110577_a(texture);
      int k = 0;
      int l = 192;
      
      if (mouseOver) {
        k += 23;
      }
      
      l += 13 * type;
      
      func_73729_b(field_146128_h, field_146129_i, k, l, 23, 13);
    }
  }
}
