package com.emoniph.witchery.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
class GuiButtonJumpPage extends GuiButton
{
  public final int nextPage;
  public final int iconX;
  public final int iconY;
  
  public GuiButtonJumpPage(int commandID, int x, int y, int pageIndex, int iconX, int iconY)
  {
    super(commandID, x, y, 20, 20, "");
    nextPage = pageIndex;
    this.iconX = iconX;
    this.iconY = iconY;
  }
  
  public void func_146112_a(Minecraft par1Minecraft, int par2, int par3)
  {
    if (field_146125_m) {
      boolean flag = (par2 >= field_146128_h) && (par3 >= field_146129_i) && (par2 < field_146128_h + field_146120_f) && (par3 < field_146129_i + field_146121_g);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      par1Minecraft.func_110434_K().func_110577_a(GuiScreenWitchcraftBook.DOUBLE_BOOK_TEXTURE);
      int k = 3;
      int l = 220;
      
      if (flag) {
        k += 12;
      }
      
      func_73729_b(field_146128_h, field_146129_i, k, l, 9, 24);
      if ((iconX >= 0) && (iconY >= 0)) {
        func_73729_b(field_146128_h, field_146129_i + 9, iconX, iconY, 8, 8);
      }
    }
  }
}
