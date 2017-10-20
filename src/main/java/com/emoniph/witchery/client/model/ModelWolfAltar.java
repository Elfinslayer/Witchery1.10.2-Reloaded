package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelWolfAltar extends ModelBase
{
  public ModelRenderer shape1;
  public ModelRenderer shape1_1;
  public ModelRenderer shape1_2;
  public ModelRenderer bipedBody;
  public ModelRenderer wolfBody;
  public ModelRenderer wolfBody_1;
  public ModelRenderer shape37;
  public ModelRenderer bipedLeftLeg;
  public ModelRenderer bipedLeftArm;
  public ModelRenderer bipedRightLeg;
  public ModelRenderer bipedRightArm;
  public ModelRenderer wolfHeadMain0;
  public ModelRenderer bipedRightLegLower;
  public ModelRenderer shaft;
  public ModelRenderer wolfHeadMain3;
  public ModelRenderer wolfHeadMain1;
  public ModelRenderer wolfHeadMain2;
  public ModelRenderer wolfMane;
  public ModelRenderer wolfHeadMain0_1;
  public ModelRenderer wolfTail;
  public ModelRenderer wolfLeg2;
  public ModelRenderer wolfLeg1;
  public ModelRenderer wolfLeg4;
  public ModelRenderer wolfLeg3;
  public ModelRenderer wolfHeadMain3_1;
  public ModelRenderer wolfHeadMain2_1;
  public ModelRenderer wolfHeadMain1_1;
  public ModelRenderer wolfMane_1;
  public ModelRenderer wolfHeadMain0_2;
  public ModelRenderer wolfTail_1;
  public ModelRenderer wolfLeg2_1;
  public ModelRenderer wolfLeg1_1;
  public ModelRenderer wolfLeg4_1;
  public ModelRenderer wolfLeg3_1;
  public ModelRenderer wolfHeadMain3_2;
  public ModelRenderer wolfHeadMain2_2;
  public ModelRenderer wolfHeadMain1_2;
  
  public ModelWolfAltar()
  {
    field_78090_t = 128;
    field_78089_u = 128;
    wolfHeadMain1_2 = new ModelRenderer(this, 16, 14);
    wolfHeadMain1_2.func_78793_a(0.0F, 0.0F, 0.0F);
    wolfHeadMain1_2.func_78789_a(-3.0F, -4.5F, -10.0F, 2, 2, 1);
    
    bipedBody = new ModelRenderer(this, 16, 16);
    bipedBody.func_78793_a(0.0F, 3.1F, 0.0F);
    bipedBody.func_78789_a(-4.0F, 0.0F, -2.0F, 8, 12, 4);
    wolfHeadMain2_2 = new ModelRenderer(this, 16, 14);
    wolfHeadMain2_2.func_78793_a(0.0F, 0.0F, 0.0F);
    wolfHeadMain2_2.func_78789_a(1.0F, -4.5F, -10.0F, 2, 2, 1);
    
    bipedRightLeg = new ModelRenderer(this, 0, 16);
    bipedRightLeg.field_78809_i = true;
    bipedRightLeg.func_78793_a(0.0F, 0.0F, 0.0F);
    bipedRightLeg.func_78789_a(-4.0F, 7.4F, 8.0F, 4, 6, 4);
    setRotateAngle(bipedRightLeg, -1.0471976F, 0.0F, 0.0F);
    
    wolfMane = new ModelRenderer(this, 21, 0);
    wolfMane.func_78793_a(0.0F, 0.0F, 0.0F);
    wolfMane.func_78789_a(-4.0F, -3.0F, -9.0F, 8, 7, 6);
    setRotateAngle(wolfMane, 0.0F, -0.13665928F, 0.0F);
    
    wolfMane_1 = new ModelRenderer(this, 21, 0);
    wolfMane_1.func_78793_a(0.0F, 0.0F, 0.0F);
    wolfMane_1.func_78789_a(-4.0F, -3.0F, -9.0F, 8, 7, 6);
    
    bipedRightLegLower = new ModelRenderer(this, 18, 0);
    bipedRightLegLower.func_78793_a(0.0F, 0.0F, 0.0F);
    bipedRightLegLower.func_78789_a(-4.0F, 15.7F, -6.2F, 4, 6, 4);
    setRotateAngle(bipedRightLegLower, 0.95609134F, 0.0F, 0.0F);
    
    wolfHeadMain3 = new ModelRenderer(this, 0, 10);
    wolfHeadMain3.func_78793_a(0.0F, 0.0F, 0.0F);
    wolfHeadMain3.func_78789_a(-1.5F, -3.1F, -5.0F, 3, 3, 4);
    
    shape37 = new ModelRenderer(this, 0, 0);
    shape37.func_78793_a(-3.9F, 18.0F, -6.5F);
    shape37.func_78789_a(0.0F, 0.0F, 0.0F, 4, 2, 4);
    wolfLeg3 = new ModelRenderer(this, 0, 18);
    wolfLeg3.func_78793_a(0.0F, 0.0F, 0.0F);
    wolfLeg3.func_78789_a(-2.5F, 4.0F, -8.0F, 2, 8, 2);
    setRotateAngle(wolfLeg3, 0.0F, -0.091106184F, 0.0F);
    
    bipedRightArm = new ModelRenderer(this, 40, 16);
    bipedRightArm.field_78809_i = true;
    bipedRightArm.func_78793_a(0.0F, 2.0F, 0.0F);
    bipedRightArm.func_78789_a(-8.0F, -2.0F, -2.0F, 4, 12, 4);
    setRotateAngle(bipedRightArm, -2.4586453F, 0.4098033F, 0.0F);
    
    shape1_2 = new ModelRenderer(this, 0, 0);
    shape1_2.func_78793_a(-8.0F, 20.0F, -8.0F);
    shape1_2.func_78789_a(0.0F, 0.0F, 0.0F, 16, 1, 16);
    wolfLeg2_1 = new ModelRenderer(this, 0, 18);
    wolfLeg2_1.func_78793_a(0.0F, 0.0F, 0.0F);
    wolfLeg2_1.func_78789_a(0.5F, 4.0F, 3.0F, 2, 8, 2);
    
    wolfHeadMain1_1 = new ModelRenderer(this, 16, 14);
    wolfHeadMain1_1.func_78793_a(0.0F, 0.0F, 0.0F);
    wolfHeadMain1_1.func_78789_a(-3.0F, -4.5F, -10.0F, 2, 2, 1);
    
    wolfLeg4 = new ModelRenderer(this, 0, 18);
    wolfLeg4.func_78793_a(0.0F, 0.0F, 0.0F);
    wolfLeg4.func_78789_a(0.5F, 4.0F, -8.0F, 2, 8, 2);
    setRotateAngle(wolfLeg4, 0.0F, -0.091106184F, 0.0F);
    
    shaft = new ModelRenderer(this, 0, 33);
    shaft.func_78793_a(0.0F, 0.0F, 0.0F);
    shaft.func_78789_a(-6.5F, 8.0F, -17.9F, 1, 1, 40);
    
    wolfHeadMain0_2 = new ModelRenderer(this, 0, 0);
    wolfHeadMain0_2.func_78793_a(0.0F, 0.0F, 0.0F);
    wolfHeadMain0_2.func_78789_a(-3.0F, -2.5F, -13.0F, 6, 6, 4);
    
    bipedLeftLeg = new ModelRenderer(this, 0, 16);
    bipedLeftLeg.field_78809_i = true;
    bipedLeftLeg.func_78793_a(0.0F, 0.0F, 0.0F);
    bipedLeftLeg.func_78789_a(0.0F, 12.0F, -2.0F, 4, 12, 4);
    

    shape1 = new ModelRenderer(this, 0, 0);
    shape1.func_78793_a(-8.0F, 22.0F, -8.0F);
    shape1.func_78789_a(0.0F, 0.0F, 0.0F, 16, 2, 16);
    bipedLeftArm = new ModelRenderer(this, 40, 16);
    bipedLeftArm.field_78809_i = true;
    bipedLeftArm.func_78793_a(0.0F, 2.0F, 0.0F);
    bipedLeftArm.func_78789_a(4.0F, -2.0F, -2.0F, 4, 12, 4);
    setRotateAngle(bipedLeftArm, -0.4553564F, -0.13665928F, 0.0F);
    
    wolfLeg4_1 = new ModelRenderer(this, 0, 18);
    wolfLeg4_1.func_78793_a(0.0F, 0.0F, 0.0F);
    wolfLeg4_1.func_78789_a(0.5F, 4.0F, -8.0F, 2, 8, 2);
    
    wolfHeadMain2_1 = new ModelRenderer(this, 16, 14);
    wolfHeadMain2_1.func_78793_a(0.0F, 0.0F, 0.0F);
    wolfHeadMain2_1.func_78789_a(1.0F, -4.5F, -10.0F, 2, 2, 1);
    
    wolfHeadMain3_2 = new ModelRenderer(this, 0, 10);
    wolfHeadMain3_2.func_78793_a(0.0F, 0.0F, 0.0F);
    wolfHeadMain3_2.func_78789_a(-1.5F, 0.5F, -17.1F, 3, 3, 4);
    
    wolfLeg1_1 = new ModelRenderer(this, 0, 18);
    wolfLeg1_1.func_78793_a(0.0F, 0.0F, 0.0F);
    wolfLeg1_1.func_78789_a(-2.5F, 4.0F, 3.0F, 2, 8, 2);
    
    wolfLeg3_1 = new ModelRenderer(this, 0, 18);
    wolfLeg3_1.func_78793_a(0.0F, 0.0F, 0.0F);
    wolfLeg3_1.func_78789_a(-2.5F, 4.0F, -8.0F, 2, 8, 2);
    
    wolfBody = new ModelRenderer(this, 18, 14);
    wolfBody.func_78793_a(-5.0F, 14.0F, 3.5F);
    wolfBody.func_78789_a(-3.0F, -2.0F, -3.0F, 6, 6, 9);
    setRotateAngle(wolfBody, 0.0F, 0.27314404F, 0.0F);
    wolfLeg2 = new ModelRenderer(this, 0, 18);
    wolfLeg2.func_78793_a(0.0F, 0.0F, 0.0F);
    wolfLeg2.func_78789_a(0.5F, 4.0F, 3.0F, 2, 8, 2);
    
    shape1_1 = new ModelRenderer(this, 0, 0);
    shape1_1.func_78793_a(-7.0F, 21.0F, -7.0F);
    shape1_1.func_78789_a(0.0F, 0.0F, 0.0F, 14, 1, 14);
    wolfTail = new ModelRenderer(this, 9, 18);
    wolfTail.func_78793_a(0.0F, 0.6F, 0.0F);
    wolfTail.func_78789_a(-1.0F, 0.0F, 4.0F, 2, 8, 2);
    setRotateAngle(wolfTail, 0.31869712F, 0.0F, 0.0F);
    
    wolfHeadMain3_1 = new ModelRenderer(this, 0, 10);
    wolfHeadMain3_1.func_78793_a(0.0F, 0.0F, 0.0F);
    wolfHeadMain3_1.func_78789_a(-1.5F, 0.5F, -17.1F, 3, 3, 4);
    
    wolfTail_1 = new ModelRenderer(this, 9, 18);
    wolfTail_1.func_78793_a(0.0F, 0.6F, 0.0F);
    wolfTail_1.func_78789_a(-1.0F, 0.0F, 4.0F, 2, 8, 2);
    setRotateAngle(wolfTail_1, 0.31869712F, 0.0F, 0.0F);
    
    wolfLeg1 = new ModelRenderer(this, 0, 18);
    wolfLeg1.func_78793_a(0.0F, 0.0F, 0.0F);
    wolfLeg1.func_78789_a(-2.5F, 4.0F, 3.0F, 2, 8, 2);
    
    wolfHeadMain1 = new ModelRenderer(this, 16, 14);
    wolfHeadMain1.func_78793_a(0.0F, 0.0F, 0.0F);
    wolfHeadMain1.func_78789_a(-3.0F, -8.0F, 2.0F, 2, 2, 1);
    
    wolfBody_1 = new ModelRenderer(this, 18, 14);
    wolfBody_1.func_78793_a(5.5F, 14.0F, -2.0F);
    wolfBody_1.func_78789_a(-3.0F, -2.0F, -3.0F, 6, 6, 9);
    setRotateAngle(wolfBody_1, 0.0F, 0.31869712F, 0.0F);
    wolfHeadMain2 = new ModelRenderer(this, 16, 14);
    wolfHeadMain2.func_78793_a(0.0F, 0.0F, 0.0F);
    wolfHeadMain2.func_78789_a(1.0F, -8.0F, 2.0F, 2, 2, 1);
    
    wolfHeadMain0 = new ModelRenderer(this, 0, 42);
    wolfHeadMain0.func_78793_a(0.0F, 0.0F, 0.0F);
    wolfHeadMain0.func_78789_a(-3.0F, -6.0F, -2.0F, 6, 6, 5);
    setRotateAngle(wolfHeadMain0, 0.13665928F, 0.13665928F, 0.0F);
    
    wolfHeadMain0_1 = new ModelRenderer(this, 0, 0);
    wolfHeadMain0_1.func_78793_a(0.0F, 0.0F, 0.0F);
    wolfHeadMain0_1.func_78789_a(-3.0F, -2.5F, -13.0F, 6, 6, 4);
    setRotateAngle(wolfHeadMain0_1, -0.22759093F, -0.22759093F, 0.0F);
    
    bipedBody.func_78792_a(bipedLeftLeg);
    wolfHeadMain0_1.func_78792_a(wolfHeadMain1_1);
    bipedRightLeg.func_78792_a(bipedRightLegLower);
    wolfHeadMain0.func_78792_a(wolfHeadMain3);
    wolfBody.func_78792_a(wolfHeadMain0_1);
    bipedBody.func_78792_a(wolfHeadMain0);
    wolfHeadMain0.func_78792_a(wolfHeadMain2);
    wolfHeadMain0.func_78792_a(wolfHeadMain1);
    wolfBody.func_78792_a(wolfLeg1);
    wolfBody_1.func_78792_a(wolfTail_1);
    wolfHeadMain0_1.func_78792_a(wolfHeadMain3_1);
    wolfBody.func_78792_a(wolfTail);
    wolfBody.func_78792_a(wolfLeg2);
    wolfBody_1.func_78792_a(wolfLeg3_1);
    wolfBody_1.func_78792_a(wolfLeg1_1);
    wolfHeadMain0_2.func_78792_a(wolfHeadMain3_2);
    wolfHeadMain0_1.func_78792_a(wolfHeadMain2_1);
    wolfBody_1.func_78792_a(wolfLeg4_1);
    bipedBody.func_78792_a(bipedLeftArm);
    wolfBody_1.func_78792_a(wolfHeadMain0_2);
    bipedRightArm.func_78792_a(shaft);
    wolfBody.func_78792_a(wolfLeg4);
    wolfBody_1.func_78792_a(wolfLeg2_1);
    bipedBody.func_78792_a(bipedRightArm);
    wolfBody.func_78792_a(wolfLeg3);
    wolfBody_1.func_78792_a(wolfMane_1);
    wolfBody.func_78792_a(wolfMane);
    bipedBody.func_78792_a(bipedRightLeg);
    wolfHeadMain0_2.func_78792_a(wolfHeadMain1_2);
    wolfHeadMain0_2.func_78792_a(wolfHeadMain2_2);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    GL11.glPushMatrix();
    GL11.glTranslatef(bipedBody.field_82906_o, bipedBody.field_82908_p, bipedBody.field_82907_q);
    GL11.glTranslatef(bipedBody.field_78800_c * f5, bipedBody.field_78797_d * f5, bipedBody.field_78798_e * f5);
    GL11.glScaled(0.7D, 0.7D, 0.7D);
    GL11.glTranslatef(-bipedBody.field_82906_o, -bipedBody.field_82908_p, -bipedBody.field_82907_q);
    GL11.glTranslatef(-bipedBody.field_78800_c * f5, -bipedBody.field_78797_d * f5, -bipedBody.field_78798_e * f5);
    bipedBody.func_78785_a(f5);
    GL11.glPopMatrix();
    shape37.func_78785_a(f5);
    shape1_2.func_78785_a(f5);
    shape1.func_78785_a(f5);
    GL11.glPushMatrix();
    GL11.glTranslatef(wolfBody.field_82906_o, wolfBody.field_82908_p, wolfBody.field_82907_q);
    GL11.glTranslatef(wolfBody.field_78800_c * f5, wolfBody.field_78797_d * f5, wolfBody.field_78798_e * f5);
    GL11.glScaled(0.5D, 0.5D, 0.5D);
    GL11.glTranslatef(-wolfBody.field_82906_o, -wolfBody.field_82908_p, -wolfBody.field_82907_q);
    GL11.glTranslatef(-wolfBody.field_78800_c * f5, -wolfBody.field_78797_d * f5, -wolfBody.field_78798_e * f5);
    wolfBody.func_78785_a(f5);
    GL11.glPopMatrix();
    shape1_1.func_78785_a(f5);
    GL11.glPushMatrix();
    GL11.glTranslatef(wolfBody_1.field_82906_o, wolfBody_1.field_82908_p, wolfBody_1.field_82907_q);
    GL11.glTranslatef(wolfBody_1.field_78800_c * f5, wolfBody_1.field_78797_d * f5, wolfBody_1.field_78798_e * f5);
    GL11.glScaled(0.5D, 0.5D, 0.5D);
    GL11.glTranslatef(-wolfBody_1.field_82906_o, -wolfBody_1.field_82908_p, -wolfBody_1.field_82907_q);
    GL11.glTranslatef(-wolfBody_1.field_78800_c * f5, -wolfBody_1.field_78797_d * f5, -wolfBody_1.field_78798_e * f5);
    wolfBody_1.func_78785_a(f5);
    GL11.glPopMatrix();
  }
  


  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
  {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
}
