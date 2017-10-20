package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelGarlicGarland extends ModelBase
{
  public ModelRenderer garlicC;
  public ModelRenderer garlicA;
  public ModelRenderer garlicE;
  public ModelRenderer garlicD;
  public ModelRenderer garlicB;
  public ModelRenderer string3;
  public ModelRenderer string4;
  public ModelRenderer string1;
  public ModelRenderer string2;
  public ModelRenderer garlic1;
  public ModelRenderer garlic2;
  public ModelRenderer garlic3;
  public ModelRenderer garlic4;
  public ModelRenderer garlic1_1;
  public ModelRenderer garlic2_1;
  public ModelRenderer garlic3_1;
  public ModelRenderer garlic4_1;
  public ModelRenderer garlic1_2;
  public ModelRenderer garlic2_2;
  public ModelRenderer garlic3_2;
  public ModelRenderer garlic4_2;
  public ModelRenderer garlic1_3;
  public ModelRenderer garlic2_3;
  public ModelRenderer garlic3_3;
  public ModelRenderer garlic4_3;
  public ModelRenderer garlic1_4;
  public ModelRenderer garlic2_4;
  public ModelRenderer garlic3_4;
  public ModelRenderer garlic4_4;
  
  public ModelGarlicGarland()
  {
    field_78090_t = 32;
    field_78089_u = 32;
    garlic4 = new ModelRenderer(this, 0, 23);
    garlic4.func_78793_a(0.0F, 0.0F, 0.0F);
    garlic4.func_78790_a(-2.0F, 7.0F, -2.0F, 4, 1, 4, 0.0F);
    garlic3 = new ModelRenderer(this, 0, 13);
    garlic3.func_78793_a(0.0F, 0.0F, 0.0F);
    garlic3.func_78790_a(-3.5F, 4.0F, -3.5F, 7, 3, 7, 0.0F);
    garlicC = new ModelRenderer(this, 0, 0);
    garlicC.func_78793_a(0.0F, 0.0F, 7.0F);
    garlicC.func_78790_a(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
    garlic1_2 = new ModelRenderer(this, 0, 3);
    garlic1_2.func_78793_a(0.0F, 0.0F, 0.0F);
    garlic1_2.func_78790_a(-1.5F, 2.0F, -1.5F, 3, 1, 3, 0.0F);
    garlicD = new ModelRenderer(this, 0, 0);
    garlicD.func_78793_a(2.5F, 1.5F, 7.0F);
    garlicD.func_78790_a(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
    garlic3_4 = new ModelRenderer(this, 0, 13);
    garlic3_4.func_78793_a(0.0F, 0.0F, 0.0F);
    garlic3_4.func_78790_a(-3.5F, 4.0F, -3.5F, 7, 3, 7, 0.0F);
    garlic1 = new ModelRenderer(this, 0, 3);
    garlic1.func_78793_a(0.0F, 0.0F, 0.0F);
    garlic1.func_78790_a(-1.5F, 2.0F, -1.5F, 3, 1, 3, 0.0F);
    garlic1_3 = new ModelRenderer(this, 0, 3);
    garlic1_3.func_78793_a(0.0F, 0.0F, 0.0F);
    garlic1_3.func_78790_a(-1.5F, 2.0F, -1.5F, 3, 1, 3, 0.0F);
    garlic4_1 = new ModelRenderer(this, 0, 23);
    garlic4_1.func_78793_a(0.0F, 0.0F, 0.0F);
    garlic4_1.func_78790_a(-2.0F, 7.0F, -2.0F, 4, 1, 4, 0.0F);
    garlic1_1 = new ModelRenderer(this, 0, 3);
    garlic1_1.func_78793_a(0.0F, 0.0F, 0.0F);
    garlic1_1.func_78790_a(-1.5F, 2.0F, -1.5F, 3, 1, 3, 0.0F);
    string2 = new ModelRenderer(this, 6, 0);
    string2.func_78793_a(-3.0F, 1.8F, 7.0F);
    string2.func_78790_a(0.0F, -0.5F, -0.5F, 4, 1, 1, -0.4F);
    setRotateAngle(string2, 0.0F, 0.0F, -0.5462881F);
    garlic3_3 = new ModelRenderer(this, 0, 13);
    garlic3_3.func_78793_a(0.0F, 0.0F, 0.0F);
    garlic3_3.func_78790_a(-3.5F, 4.0F, -3.5F, 7, 3, 7, 0.0F);
    garlicE = new ModelRenderer(this, 0, 0);
    garlicE.func_78793_a(5.0F, 0.0F, 7.0F);
    garlicE.func_78790_a(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
    garlic1_4 = new ModelRenderer(this, 0, 3);
    garlic1_4.func_78793_a(0.0F, 0.0F, 0.0F);
    garlic1_4.func_78790_a(-1.5F, 2.0F, -1.5F, 3, 1, 3, 0.0F);
    garlic2_4 = new ModelRenderer(this, 0, 7);
    garlic2_4.func_78793_a(0.0F, 0.0F, 0.0F);
    garlic2_4.func_78790_a(-2.5F, 3.0F, -2.5F, 5, 1, 5, 0.0F);
    garlic2 = new ModelRenderer(this, 0, 7);
    garlic2.func_78793_a(0.0F, 0.0F, 0.0F);
    garlic2.func_78790_a(-2.5F, 3.0F, -2.5F, 5, 1, 5, 0.0F);
    string3 = new ModelRenderer(this, 6, 0);
    string3.func_78793_a(-0.4F, -0.3F, 7.0F);
    string3.func_78790_a(0.0F, -0.5F, -0.5F, 4, 1, 1, -0.4F);
    setRotateAngle(string3, 0.0F, 0.0F, 0.5462881F);
    garlic4_3 = new ModelRenderer(this, 0, 23);
    garlic4_3.func_78793_a(0.0F, 0.0F, 0.0F);
    garlic4_3.func_78790_a(-2.0F, 7.0F, -2.0F, 4, 1, 4, 0.0F);
    string1 = new ModelRenderer(this, 6, 0);
    string1.func_78793_a(-5.4F, -0.3F, 7.0F);
    string1.func_78790_a(0.0F, -0.5F, -0.5F, 4, 1, 1, -0.4F);
    setRotateAngle(string1, 0.0F, 0.0F, 0.5462881F);
    garlic2_1 = new ModelRenderer(this, 0, 7);
    garlic2_1.func_78793_a(0.0F, 0.0F, 0.0F);
    garlic2_1.func_78790_a(-2.5F, 3.0F, -2.5F, 5, 1, 5, 0.0F);
    string4 = new ModelRenderer(this, 6, 0);
    string4.func_78793_a(2.0F, 1.8F, 7.0F);
    string4.func_78790_a(0.0F, -0.5F, -0.5F, 4, 1, 1, -0.4F);
    setRotateAngle(string4, 0.0F, 0.0F, -0.5462881F);
    garlic3_2 = new ModelRenderer(this, 0, 13);
    garlic3_2.func_78793_a(0.0F, 0.0F, 0.0F);
    garlic3_2.func_78790_a(-3.5F, 4.0F, -3.5F, 7, 3, 7, 0.0F);
    garlicA = new ModelRenderer(this, 0, 0);
    garlicA.func_78793_a(-5.0F, 0.0F, 7.0F);
    garlicA.func_78790_a(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
    garlic4_4 = new ModelRenderer(this, 0, 23);
    garlic4_4.func_78793_a(0.0F, 0.0F, 0.0F);
    garlic4_4.func_78790_a(-2.0F, 7.0F, -2.0F, 4, 1, 4, 0.0F);
    garlicB = new ModelRenderer(this, 0, 0);
    garlicB.func_78793_a(-2.5F, 1.5F, 7.0F);
    garlicB.func_78790_a(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
    garlic4_2 = new ModelRenderer(this, 0, 23);
    garlic4_2.func_78793_a(0.0F, 0.0F, 0.0F);
    garlic4_2.func_78790_a(-2.0F, 7.0F, -2.0F, 4, 1, 4, 0.0F);
    garlic2_3 = new ModelRenderer(this, 0, 7);
    garlic2_3.func_78793_a(0.0F, 0.0F, 0.0F);
    garlic2_3.func_78790_a(-2.5F, 3.0F, -2.5F, 5, 1, 5, 0.0F);
    garlic2_2 = new ModelRenderer(this, 0, 7);
    garlic2_2.func_78793_a(0.0F, 0.0F, 0.0F);
    garlic2_2.func_78790_a(-2.5F, 3.0F, -2.5F, 5, 1, 5, 0.0F);
    garlic3_1 = new ModelRenderer(this, 0, 13);
    garlic3_1.func_78793_a(0.0F, 0.0F, 0.0F);
    garlic3_1.func_78790_a(-3.5F, 4.0F, -3.5F, 7, 3, 7, 0.0F);
    garlicC.func_78792_a(garlic4);
    garlicC.func_78792_a(garlic3);
    garlicE.func_78792_a(garlic1_2);
    garlicB.func_78792_a(garlic3_4);
    garlicC.func_78792_a(garlic1);
    garlicD.func_78792_a(garlic1_3);
    garlicA.func_78792_a(garlic4_1);
    garlicA.func_78792_a(garlic1_1);
    garlicD.func_78792_a(garlic3_3);
    garlicB.func_78792_a(garlic1_4);
    garlicB.func_78792_a(garlic2_4);
    garlicC.func_78792_a(garlic2);
    garlicD.func_78792_a(garlic4_3);
    garlicA.func_78792_a(garlic2_1);
    garlicE.func_78792_a(garlic3_2);
    garlicB.func_78792_a(garlic4_4);
    garlicE.func_78792_a(garlic4_2);
    garlicD.func_78792_a(garlic2_3);
    garlicE.func_78792_a(garlic2_2);
    garlicA.func_78792_a(garlic3_1);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    double SCALE = 0.21D;
    GL11.glPushMatrix();
    GL11.glTranslatef(garlicC.field_82906_o, garlicC.field_82908_p, garlicC.field_82907_q);
    GL11.glTranslatef(garlicC.field_78800_c * f5, garlicC.field_78797_d * f5, garlicC.field_78798_e * f5);
    
    GL11.glScaled(0.21D, 0.21D, 0.21D);
    GL11.glTranslatef(-garlicC.field_82906_o, -garlicC.field_82908_p, -garlicC.field_82907_q);
    GL11.glTranslatef(-garlicC.field_78800_c * f5, -garlicC.field_78797_d * f5, -garlicC.field_78798_e * f5);
    
    garlicC.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(garlicD.field_82906_o, garlicD.field_82908_p, garlicD.field_82907_q);
    GL11.glTranslatef(garlicD.field_78800_c * f5, garlicD.field_78797_d * f5, garlicD.field_78798_e * f5);
    
    GL11.glScaled(0.21D, 0.21D, 0.21D);
    GL11.glTranslatef(-garlicD.field_82906_o, -garlicD.field_82908_p, -garlicD.field_82907_q);
    GL11.glTranslatef(-garlicD.field_78800_c * f5, -garlicD.field_78797_d * f5, -garlicD.field_78798_e * f5);
    
    garlicD.func_78785_a(f5);
    GL11.glPopMatrix();
    string2.func_78785_a(f5);
    GL11.glPushMatrix();
    GL11.glTranslatef(garlicE.field_82906_o, garlicE.field_82908_p, garlicE.field_82907_q);
    GL11.glTranslatef(garlicE.field_78800_c * f5, garlicE.field_78797_d * f5, garlicE.field_78798_e * f5);
    
    GL11.glScaled(0.21D, 0.21D, 0.21D);
    GL11.glTranslatef(-garlicE.field_82906_o, -garlicE.field_82908_p, -garlicE.field_82907_q);
    GL11.glTranslatef(-garlicE.field_78800_c * f5, -garlicE.field_78797_d * f5, -garlicE.field_78798_e * f5);
    
    garlicE.func_78785_a(f5);
    GL11.glPopMatrix();
    string3.func_78785_a(f5);
    string1.func_78785_a(f5);
    string4.func_78785_a(f5);
    GL11.glPushMatrix();
    GL11.glTranslatef(garlicA.field_82906_o, garlicA.field_82908_p, garlicA.field_82907_q);
    GL11.glTranslatef(garlicA.field_78800_c * f5, garlicA.field_78797_d * f5, garlicA.field_78798_e * f5);
    
    GL11.glScaled(0.21D, 0.21D, 0.21D);
    GL11.glTranslatef(-garlicA.field_82906_o, -garlicA.field_82908_p, -garlicA.field_82907_q);
    GL11.glTranslatef(-garlicA.field_78800_c * f5, -garlicA.field_78797_d * f5, -garlicA.field_78798_e * f5);
    
    garlicA.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(garlicB.field_82906_o, garlicB.field_82908_p, garlicB.field_82907_q);
    GL11.glTranslatef(garlicB.field_78800_c * f5, garlicB.field_78797_d * f5, garlicB.field_78798_e * f5);
    
    GL11.glScaled(0.21D, 0.21D, 0.21D);
    GL11.glTranslatef(-garlicB.field_82906_o, -garlicB.field_82908_p, -garlicB.field_82907_q);
    GL11.glTranslatef(-garlicB.field_78800_c * f5, -garlicB.field_78797_d * f5, -garlicB.field_78798_e * f5);
    
    garlicB.func_78785_a(f5);
    GL11.glPopMatrix();
  }
  
  private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
}
