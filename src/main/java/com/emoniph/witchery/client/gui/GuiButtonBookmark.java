package com.emoniph.witchery.client.gui;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.opengl.GL11;

@cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
class GuiButtonBookmark extends GuiButton
{
  public final int nextPage;
  
  public GuiButtonBookmark(int commandID, int x, int y, int pageIndex, String label)
  {
    super(commandID, x, y, 60, 11, label);
    nextPage = pageIndex;
  }
  
  public void func_146112_a(Minecraft mc, int par2, int par3)
  {
    if (field_146125_m) {
      boolean flag = (par2 >= field_146128_h) && (par3 >= field_146129_i) && (par2 < field_146128_h + field_146120_f) && (par3 < field_146129_i + field_146121_g);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      mc.func_110434_K().func_110577_a(GuiScreenWitchcraftBook.DOUBLE_BOOK_TEXTURE);
      
      func_73729_b(field_146128_h, field_146129_i, 26, 'Ü' + (flag ? field_146121_g : 0), field_146120_f, field_146121_g);
      field_71466_p.func_78276_b(field_146126_j, field_146128_h + 2, field_146129_i + 2, 0);
    }
  }
}
