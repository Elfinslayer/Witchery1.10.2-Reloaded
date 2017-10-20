package com.emoniph.witchery.client.model;

import com.emoniph.witchery.util.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ModelGoblinMog extends ModelBase
{
  public ModelRenderer bipedBody;
  public ModelRenderer bipedRightArm;
  public ModelRenderer bipedLeftArm;
  public ModelRenderer bipedRightLeg;
  public ModelRenderer bipedLeftLeg;
  public ModelRenderer bipedChest;
  public ModelRenderer bipedSkirt;
  public ModelRenderer bipedHead;
  public int heldItemLeft;
  public int heldItemRight;
  public boolean isSneak;
  public boolean aimedBow;
  
  public ModelGoblinMog()
  {
    this(0.0F);
  }
  
  public ModelGoblinMog(float f) {
    field_78090_t = 64;
    field_78089_u = 64;
    func_78085_a("bipedHead.face", 0, 0);
    func_78085_a("bipedHead.tuskright", 0, 4);
    func_78085_a("bipedHead.tuskleft", 0, 4);
    func_78085_a("bipedHead.nose", 25, 0);
    func_78085_a("bipedHead.lip", 34, 0);
    
    bipedBody = new ModelRenderer(this, 16, 16);
    bipedBody.func_78789_a(-4.0F, 0.0F, -2.0F, 8, 12, 4);
    bipedBody.func_78793_a(0.0F, 0.0F, 0.0F);
    bipedBody.func_78787_b(64, 64);
    bipedBody.field_78809_i = true;
    setRotation(bipedBody, 0.0F, 0.0F, 0.0F);
    bipedRightArm = new ModelRenderer(this, 40, 14);
    bipedRightArm.func_78789_a(-3.0F, -2.0F, -2.0F, 4, 14, 4);
    bipedRightArm.func_78793_a(-5.0F, 2.0F, 0.0F);
    bipedRightArm.func_78787_b(64, 64);
    bipedRightArm.field_78809_i = true;
    setRotation(bipedRightArm, 0.0F, 0.0F, 0.0F);
    bipedLeftArm = new ModelRenderer(this, 40, 14);
    bipedLeftArm.func_78789_a(-1.0F, -2.0F, -2.0F, 4, 14, 4);
    bipedLeftArm.func_78793_a(5.0F, 2.0F, 0.0F);
    bipedLeftArm.func_78787_b(64, 64);
    bipedLeftArm.field_78809_i = true;
    setRotation(bipedLeftArm, 0.0F, 0.0F, 0.0F);
    bipedRightLeg = new ModelRenderer(this, 0, 16);
    bipedRightLeg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 12, 4);
    bipedRightLeg.func_78793_a(-2.0F, 12.0F, 0.0F);
    bipedRightLeg.func_78787_b(64, 64);
    bipedRightLeg.field_78809_i = true;
    setRotation(bipedRightLeg, 0.0F, 0.0F, 0.0F);
    bipedLeftLeg = new ModelRenderer(this, 0, 16);
    bipedLeftLeg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 12, 4);
    bipedLeftLeg.func_78793_a(2.0F, 12.0F, 0.0F);
    bipedLeftLeg.func_78787_b(64, 64);
    bipedLeftLeg.field_78809_i = true;
    setRotation(bipedLeftLeg, 0.0F, 0.0F, 0.0F);
    bipedChest = new ModelRenderer(this, 35, 5);
    bipedChest.func_78789_a(-4.0F, -2.0F, -5.0F, 8, 4, 4);
    bipedChest.func_78793_a(0.0F, 2.0F, 0.0F);
    bipedChest.func_78787_b(64, 64);
    bipedChest.field_78809_i = true;
    setRotation(bipedChest, 0.7853982F, 0.0F, 0.0F);
    bipedSkirt = new ModelRenderer(this, 14, 34);
    bipedSkirt.func_78789_a(-4.5F, 0.0F, -2.5F, 9, 11, 5);
    bipedSkirt.func_78793_a(0.0F, 12.0F, 0.0F);
    bipedSkirt.func_78787_b(64, 64);
    bipedSkirt.field_78809_i = true;
    setRotation(bipedSkirt, 0.0F, 0.0F, 0.0F);
    bipedHead = new ModelRenderer(this, "bipedHead");
    bipedHead.func_78793_a(0.0F, 0.0F, 0.0F);
    setRotation(bipedHead, 0.0F, 0.0F, 0.0F);
    bipedHead.field_78809_i = true;
    bipedHead.func_78786_a("face", -4.0F, -8.0F, -4.0F, 8, 8, 8);
    bipedHead.func_78786_a("tuskright", -2.0F, -4.0F, -5.0F, 1, 2, 1);
    bipedHead.func_78786_a("tuskleft", 1.0F, -4.0F, -5.0F, 1, 2, 1);
    bipedHead.func_78786_a("nose", -1.0F, -6.0F, -6.0F, 2, 3, 2);
    bipedHead.func_78786_a("lip", -2.0F, -2.0F, -6.0F, 4, 1, 2);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
  
  public void func_78088_a(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7)
  {
    func_78087_a(par2, par3, par4, par5, par6, par7, entity);
    
    doRender(par7);
    
    Minecraft mc = Minecraft.func_71410_x();
    if ((field_71474_y.field_74347_j) && (instancerenderHuntsmanGlintEffect)) {
      float f9 = field_70173_aa;
      field_71446_o.func_110577_a(RES_ITEM_GLINT);
      GL11.glEnable(3042);
      float f10 = 0.5F;
      GL11.glColor4f(f10, f10, f10, 1.0F);
      GL11.glDepthFunc(514);
      GL11.glDepthMask(false);
      
      for (int k = 0; k < 2; k++)
      {
        GL11.glDisable(2896);
        float f11 = 0.76F;
        GL11.glColor4f(0.2F * f11, 0.7F * f11, 0.7F * f11, 1.0F);
        GL11.glBlendFunc(768, 1);
        GL11.glMatrixMode(5890);
        GL11.glLoadIdentity();
        float f12 = f9 * (0.001F + k * 0.003F) * 20.0F;
        float f13 = 0.33333334F;
        GL11.glScalef(f13, f13, f13);
        GL11.glRotatef(30.0F - k * 60.0F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(0.0F, f12, 0.0F);
        GL11.glMatrixMode(5888);
        
        doRender(par7);
      }
      
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glMatrixMode(5890);
      GL11.glDepthMask(true);
      GL11.glLoadIdentity();
      GL11.glMatrixMode(5888);
      GL11.glEnable(2896);
      GL11.glDisable(3042);
      GL11.glDepthFunc(515);
    }
  }
  
  private void doRender(float par7) {
    if (field_78091_s) {
      float f6 = 2.0F;
      GL11.glPushMatrix();
      GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
      GL11.glTranslatef(0.0F, 16.0F * par7, 0.0F);
      bipedHead.func_78785_a(par7);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
      GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
      bipedBody.func_78785_a(par7);
      bipedChest.func_78785_a(par7);
      bipedSkirt.func_78785_a(par7);
      bipedRightArm.func_78785_a(par7);
      bipedLeftArm.func_78785_a(par7);
      bipedRightLeg.func_78785_a(par7);
      bipedLeftLeg.func_78785_a(par7);
      GL11.glPopMatrix();
    } else {
      bipedHead.func_78785_a(par7);
      bipedChest.func_78785_a(par7);
      bipedBody.func_78785_a(par7);
      bipedSkirt.func_78785_a(par7);
      bipedRightArm.func_78785_a(par7);
      bipedLeftArm.func_78785_a(par7);
      bipedRightLeg.func_78785_a(par7);
      bipedLeftLeg.func_78785_a(par7);
    }
  }
  






  public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
  {
    bipedHead.field_78796_g = (par4 / 57.295776F);
    bipedHead.field_78795_f = (par5 / 57.295776F);
    bipedRightArm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
    bipedLeftArm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
    bipedRightArm.field_78808_h = 0.0F;
    bipedLeftArm.field_78808_h = 0.0F;
    bipedRightLeg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2);
    bipedLeftLeg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
    bipedRightLeg.field_78796_g = 0.0F;
    bipedLeftLeg.field_78796_g = 0.0F;
    
    if (field_78093_q) {
      bipedRightArm.field_78795_f += -0.62831855F;
      bipedLeftArm.field_78795_f += -0.62831855F;
      bipedRightLeg.field_78795_f = -1.2566371F;
      bipedLeftLeg.field_78795_f = -1.2566371F;
      bipedRightLeg.field_78796_g = 0.31415927F;
      bipedLeftLeg.field_78796_g = -0.31415927F;
    }
    
    if (heldItemLeft != 0) {
      bipedLeftArm.field_78795_f = (bipedLeftArm.field_78795_f * 0.5F - 0.31415927F * heldItemLeft);
    }
    
    if (heldItemRight != 0) {
      bipedRightArm.field_78795_f = (bipedRightArm.field_78795_f * 0.5F - 0.31415927F * heldItemRight);
    }
    
    bipedRightArm.field_78796_g = 0.0F;
    bipedLeftArm.field_78796_g = 0.0F;
    


    if (field_78095_p > -9990.0F) {
      float f6 = field_78095_p;
      bipedBody.field_78796_g = (MathHelper.func_76126_a(MathHelper.func_76129_c(f6) * 3.1415927F * 2.0F) * 0.2F);
      bipedRightArm.field_78798_e = (MathHelper.func_76126_a(bipedBody.field_78796_g) * 5.0F);
      bipedRightArm.field_78800_c = (-MathHelper.func_76134_b(bipedBody.field_78796_g) * 5.0F);
      bipedLeftArm.field_78798_e = (-MathHelper.func_76126_a(bipedBody.field_78796_g) * 5.0F);
      bipedLeftArm.field_78800_c = (MathHelper.func_76134_b(bipedBody.field_78796_g) * 5.0F);
      bipedRightArm.field_78796_g += bipedBody.field_78796_g;
      bipedLeftArm.field_78796_g += bipedBody.field_78796_g;
      bipedLeftArm.field_78795_f += bipedBody.field_78796_g;
      f6 = 1.0F - field_78095_p;
      f6 *= f6;
      f6 *= f6;
      f6 = 1.0F - f6;
      float f7 = MathHelper.func_76126_a(f6 * 3.1415927F);
      float f8 = MathHelper.func_76126_a(field_78095_p * 3.1415927F) * -(bipedHead.field_78795_f - 0.7F) * 0.75F;
      bipedRightArm.field_78795_f = ((float)(bipedRightArm.field_78795_f - (f7 * 1.2D + f8)));
      bipedRightArm.field_78796_g += bipedBody.field_78796_g * 2.0F;
      bipedRightArm.field_78808_h = (MathHelper.func_76126_a(field_78095_p * 3.1415927F) * -0.4F);
    }
    
    if (isSneak) {
      bipedBody.field_78795_f = 0.5F;
      bipedRightArm.field_78795_f += 0.4F;
      bipedLeftArm.field_78795_f += 0.4F;
      bipedRightLeg.field_78798_e = 4.0F;
      bipedLeftLeg.field_78798_e = 4.0F;
      bipedRightLeg.field_78797_d = 9.0F;
      bipedLeftLeg.field_78797_d = 9.0F;
      bipedHead.field_78797_d = 1.0F;
    } else {
      bipedBody.field_78795_f = 0.0F;
      bipedRightLeg.field_78798_e = 0.1F;
      bipedLeftLeg.field_78798_e = 0.1F;
      bipedRightLeg.field_78797_d = 12.0F;
      bipedLeftLeg.field_78797_d = 12.0F;
      bipedHead.field_78797_d = 0.0F;
    }
    
    bipedRightArm.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
    bipedLeftArm.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
    bipedRightArm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    bipedLeftArm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    
    if (aimedBow) {
      float f6 = 0.0F;
      float f7 = 0.0F;
      bipedRightArm.field_78808_h = 0.0F;
      bipedLeftArm.field_78808_h = 0.0F;
      bipedRightArm.field_78796_g = (-(0.1F - f6 * 0.6F) + bipedHead.field_78796_g);
      bipedLeftArm.field_78796_g = (0.1F - f6 * 0.6F + bipedHead.field_78796_g + 0.4F);
      bipedRightArm.field_78795_f = (-1.5707964F + bipedHead.field_78795_f);
      bipedLeftArm.field_78795_f = (-1.5707964F + bipedHead.field_78795_f);
      bipedRightArm.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
      bipedLeftArm.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
      bipedRightArm.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
      bipedLeftArm.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
      bipedRightArm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
      bipedLeftArm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    }
  }
}
