package com.emoniph.witchery.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class BlockAltarGUI extends GuiScreen
{
  private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery:textures/gui/altar.png");
  private final BlockAltar.TileEntityAltar tileEntity;
  private static final int SIZE_OF_TEXTURE_X = 176;
  private static final int SIZE_OF_TEXTURE_Y = 88;
  
  public BlockAltarGUI(BlockAltar.TileEntityAltar tileEntity) { this.tileEntity = tileEntity; }
  




  public void func_73863_a(int x, int y, float f)
  {
    func_146276_q_();
    
    field_146297_k.func_110434_K().func_110577_a(TEXTURE_URL);
    
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    
    int posX = (field_146294_l - 176) / 2;
    int posY = (field_146295_m - 88) / 2;
    
    func_73729_b(posX, posY, 0, 0, 176, 88);
    
    func_73732_a(field_146289_q, "Altar", field_146294_l / 2, field_146295_m / 2 - 20, 16777215);
    
    String power = String.format("%.0f / %.0f (x%d)", new Object[] { Float.valueOf(tileEntity.getCorePower()), Float.valueOf(tileEntity.getCoreMaxPower()), Integer.valueOf(tileEntity.getCoreSpeed()) });
    
    func_73732_a(field_146289_q, power, field_146294_l / 2, field_146295_m / 2, 16777215);
  }
  
  public boolean func_73868_f()
  {
    return false;
  }
  
  protected void func_73869_a(char par1, int par2) {
    if ((par2 == 1) || (par2 == field_146297_k.field_71474_y.field_151445_Q.func_151463_i())) {
      field_146297_k.field_71439_g.func_71053_j();
    }
  }
}
