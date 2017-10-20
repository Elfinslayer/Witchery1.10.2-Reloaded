package com.emoniph.witchery.brewing;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;


@SideOnly(Side.CLIENT)
public class RenderCauldron
  extends TileEntitySpecialRenderer
{
  final ModelCauldron model;
  
  public RenderCauldron()
  {
    model = new ModelCauldron();
  }
  
  private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/cauldron.png");
  
  public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f)
  {
    TileEntityCauldron cauldron = (TileEntityCauldron)tileEntity;
    
    GL11.glPushMatrix();
    GL11.glTranslatef((float)d, (float)d1, (float)d2);
    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    GL11.glTranslatef(0.0F, -1.0F, 0.0F);
    
    func_147499_a(TEXTURE_URL);
    
    model.func_78088_a(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    
    if (cauldron.isFilled()) {
      func_147499_a(TextureMap.field_110575_b);
      
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(3008);
      
      int color = cauldron.getColor();
      if (color == -1) {
        color = 3432410;
      }
      
      float red = (color >>> 16 & 0xFF) / 256.0F;
      float green = (color >>> 8 & 0xFF) / 256.0F;
      float blue = (color & 0xFF) / 256.0F;
      GL11.glColor4f(red, green, blue, 1.0F);
      

      float w = -0.375F;
      float depth = 1.3F - (float)(cauldron.getPercentFilled() * 0.5D);
      GL11.glTranslatef(w, depth, -w);
      GL11.glRotatef(270.0F, 1.0F, 0.0F, 0.0F);
      
      float s = 0.046875F;
      GL11.glScalef(0.046875F, 0.046875F, 0.046875F);
      
      IIcon icon = BlocksBREW.func_149691_a(0, 0);
      int x = 0;int y = 0;
      int u = 16;int v = 16;
      
      Tessellator tessellator = Tessellator.field_78398_a;
      tessellator.func_78382_b();
      tessellator.func_78380_c(200);
      tessellator.func_78374_a(0.0D, 16.0D, 0.0D, icon.func_94209_e(), icon.func_94210_h());
      tessellator.func_78374_a(16.0D, 16.0D, 0.0D, icon.func_94212_f(), icon.func_94210_h());
      tessellator.func_78374_a(16.0D, 0.0D, 0.0D, icon.func_94212_f(), icon.func_94206_g());
      tessellator.func_78374_a(0.0D, 0.0D, 0.0D, icon.func_94209_e(), icon.func_94206_g());
      tessellator.func_78381_a();
      
      GL11.glEnable(3008);
      GL11.glDisable(3042);
      GL11.glPopMatrix();
    }
    
    GL11.glPopMatrix();
  }
}
