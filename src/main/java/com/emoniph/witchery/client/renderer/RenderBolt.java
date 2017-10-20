package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.entity.EntityBolt;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;


@SideOnly(Side.CLIENT)
public class RenderBolt
  extends Render
{
  public RenderBolt() {}
  
  private static final ResourceLocation arrowTextures = new ResourceLocation("witchery", "textures/entities/bolt.png");
  private static final ResourceLocation arrowTextures2 = new ResourceLocation("witchery", "textures/entities/bolt2.png");
  private static final ResourceLocation arrowTextures3 = new ResourceLocation("witchery", "textures/entities/bolt3.png");
  private static final ResourceLocation arrowTextures4 = new ResourceLocation("witchery", "textures/entities/bolt4.png");
  
  public void renderArrow(EntityBolt par1EntityArrow, double par2, double par4, double par6, float par8, float par9) {
    func_110777_b(par1EntityArrow);
    GL11.glPushMatrix();
    GL11.glTranslatef((float)par2, (float)par4, (float)par6);
    GL11.glRotatef(field_70126_B + (field_70177_z - field_70126_B) * par9 - 90.0F, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(field_70127_C + (field_70125_A - field_70127_C) * par9, 0.0F, 0.0F, 1.0F);
    Tessellator tessellator = Tessellator.field_78398_a;
    byte b0 = 0;
    float f2 = 0.0F;
    float f3 = 0.3F;
    float f4 = (0 + b0 * 10) / 32.0F;
    float f5 = (5 + b0 * 10) / 32.0F;
    

    float f6 = 0.0F;
    float f7 = 0.15625F;
    float f8 = (5 + b0 * 10) / 32.0F;
    float f9 = (10 + b0 * 10) / 32.0F;
    float f10 = 0.05625F;
    GL11.glEnable(32826);
    float f11 = arrowShake - par9;
    
    if (f11 > 0.0F) {
      float f12 = -MathHelper.func_76126_a(f11 * 3.0F) * f11;
      GL11.glRotatef(f12, 0.0F, 0.0F, 1.0F);
    }
    
    GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
    GL11.glScalef(f10, f10, f10);
    GL11.glTranslatef(-3.0F, 0.0F, 0.0F);
    GL11.glNormal3f(f10, 0.0F, 0.0F);
    

    tessellator.func_78382_b();
    tessellator.func_78374_a(-7.0D, -2.0D, -2.0D, f6, f8);
    tessellator.func_78374_a(-7.0D, -2.0D, 2.0D, f7, f8);
    tessellator.func_78374_a(-7.0D, 2.0D, 2.0D, f7, f9);
    tessellator.func_78374_a(-7.0D, 2.0D, -2.0D, f6, f9);
    tessellator.func_78381_a();
    

    GL11.glNormal3f(-f10, 0.0F, 0.0F);
    tessellator.func_78382_b();
    tessellator.func_78374_a(-7.0D, 2.0D, -2.0D, f6, f8);
    tessellator.func_78374_a(-7.0D, 2.0D, 2.0D, f7, f8);
    tessellator.func_78374_a(-7.0D, -2.0D, 2.0D, f7, f9);
    tessellator.func_78374_a(-7.0D, -2.0D, -2.0D, f6, f9);
    tessellator.func_78381_a();
    
    GL11.glTranslatef(0.9F, 0.0F, 0.0F);
    for (int i = 0; i < 4; i++) {
      GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
      GL11.glNormal3f(0.0F, 0.0F, f10);
      tessellator.func_78382_b();
      tessellator.func_78374_a(-8.0D, -2.0D, 0.0D, f2, f4);
      tessellator.func_78374_a(8.0D, -2.0D, 0.0D, f3, f4);
      tessellator.func_78374_a(8.0D, 2.0D, 0.0D, f3, f5);
      tessellator.func_78374_a(-8.0D, 2.0D, 0.0D, f2, f5);
      tessellator.func_78381_a();
    }
    
    GL11.glDisable(32826);
    GL11.glPopMatrix();
  }
  
  protected ResourceLocation getArrowTextures(EntityBolt bolt) {
    if (bolt.isHolyDamage())
      return arrowTextures3;
    if (bolt.isSilverDamage())
      return arrowTextures4;
    if (bolt.isDraining()) {
      return arrowTextures2;
    }
    return arrowTextures;
  }
  

  protected ResourceLocation func_110775_a(Entity par1Entity)
  {
    return getArrowTextures((EntityBolt)par1Entity);
  }
  
  public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
  {
    renderArrow((EntityBolt)par1Entity, par2, par4, par6, par8, par9);
  }
}
