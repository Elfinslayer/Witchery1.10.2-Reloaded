package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityWingedMonkey;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelMonkey extends ModelBase
{
  private ModelRenderer tail;
  private ModelRenderer armLeft;
  private ModelRenderer legRight;
  private ModelRenderer bodyShoulder;
  private ModelRenderer body;
  private ModelRenderer legLeft;
  private ModelRenderer head;
  private ModelRenderer armRight;
  private ModelRenderer wingRight;
  private ModelRenderer wingLeft;
  private ModelRenderer headFace;
  private ModelRenderer headNose;
  private ModelRenderer headEarLeft;
  private ModelRenderer headEarRight;
  
  public ModelMonkey()
  {
    field_78090_t = 64;
    field_78089_u = 32;
    headEarRight = new ModelRenderer(this, 18, 14);
    headEarRight.func_78793_a(0.0F, 0.0F, 0.0F);
    headEarRight.func_78790_a(-4.5F, -2.5F, -1.5F, 2, 3, 1, 0.0F);
    tail = new ModelRenderer(this, 18, 23);
    
    tail.func_78790_a(-0.5F, -7.8F, -0.5F, 1, 8, 1, 0.0F);
    tail.func_78793_a(0.0F, 18.5F, 5.3F);
    setRotateAngle(tail, -0.63739425F, 0.0F, 0.0F);
    armRight = new ModelRenderer(this, 0, 19);
    armRight.func_78793_a(-3.5F, 14.0F, 0.0F);
    armRight.func_78790_a(-2.0F, -1.1F, -1.0F, 2, 11, 2, 0.0F);
    setRotateAngle(armRight, -0.18203785F, 0.0F, 0.0F);
    legRight = new ModelRenderer(this, 9, 25);
    legRight.func_78793_a(-1.4F, 19.0F, 4.7F);
    legRight.func_78790_a(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
    bodyShoulder = new ModelRenderer(this, 32, 0);
    bodyShoulder.func_78793_a(0.0F, 16.0F, 1.0F);
    bodyShoulder.func_78790_a(-3.5F, -3.0F, -3.0F, 7, 5, 7, 0.0F);
    setRotateAngle(bodyShoulder, 1.5707964F, 0.0F, 0.0F);
    wingRight = new ModelRenderer(this, 28, 25);
    wingRight.func_78793_a(-1.0F, 14.0F, 2.5F);
    wingRight.func_78790_a(-12.0F, -0.5F, -3.0F, 12, 1, 6, 0.0F);
    setRotateAngle(wingRight, -0.68294734F, 0.3642502F, 0.5462881F);
    headEarLeft = new ModelRenderer(this, 18, 14);
    headEarLeft.field_78809_i = true;
    headEarLeft.func_78793_a(0.0F, 0.0F, 0.0F);
    headEarLeft.func_78790_a(2.5F, -2.5F, -1.5F, 2, 3, 1, 0.0F);
    headFace = new ModelRenderer(this, 5, 12);
    headFace.func_78793_a(0.0F, 0.0F, 0.0F);
    headFace.func_78790_a(-2.5F, -3.5F, -3.5F, 5, 5, 1, 0.0F);
    body = new ModelRenderer(this, 36, 12);
    body.func_78793_a(0.0F, 15.8F, 2.0F);
    body.func_78790_a(-2.5F, -2.0F, -3.0F, 5, 7, 5, 0.0F);
    setRotateAngle(body, 0.59184116F, 0.0F, 0.0F);
    legLeft = new ModelRenderer(this, 9, 25);
    legLeft.field_78809_i = true;
    legLeft.func_78793_a(1.4F, 19.0F, 4.7F);
    legLeft.func_78790_a(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
    armLeft = new ModelRenderer(this, 0, 19);
    armLeft.field_78809_i = true;
    armLeft.func_78793_a(4.0F, 14.0F, 0.0F);
    armLeft.func_78790_a(-0.5F, -1.0F, -1.0F, 2, 11, 2, 0.0F);
    setRotateAngle(armLeft, -0.18203785F, 0.0F, 0.0F);
    wingLeft = new ModelRenderer(this, 28, 25);
    wingLeft.field_78809_i = true;
    wingLeft.func_78793_a(1.0F, 14.0F, 2.5F);
    wingLeft.func_78790_a(0.0F, -0.5F, -3.0F, 12, 1, 6, 0.0F);
    setRotateAngle(wingLeft, -0.68294734F, -0.3642502F, -0.5462881F);
    head = new ModelRenderer(this, 0, 0);
    head.func_78793_a(0.0F, 12.0F, -1.5F);
    head.func_78790_a(-3.0F, -4.0F, -3.0F, 6, 6, 5, 0.0F);
    headNose = new ModelRenderer(this, 9, 19);
    headNose.func_78793_a(0.0F, 0.0F, 0.0F);
    headNose.func_78790_a(-2.0F, -1.5F, -4.5F, 4, 3, 1, 0.0F);
    head.func_78792_a(headEarRight);
    head.func_78792_a(headEarLeft);
    head.func_78792_a(headFace);
    head.func_78792_a(headNose);
  }
  
  private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  

  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    EntityWingedMonkey entitybat = (EntityWingedMonkey)entity;
    

    armRight.field_78808_h = 0.0F;
    armLeft.field_78808_h = 0.0F;
    
    legRight.field_78808_h = 0.0F;
    legLeft.field_78808_h = 0.0F;
    
    boolean landed = (field_70181_x == 0.0D) && (field_70159_w == 0.0D) && (field_70179_y == 0.0D) && (ModelOwl.isLanded(entity));
    if (landed) {
      setRotateAngle(wingLeft, -0.68294734F, -0.3642502F, -0.5462881F);
      setRotateAngle(wingRight, -0.68294734F, 0.3642502F, 0.5462881F);
      
      armLeft.field_78795_f = -0.18203785F;
      armRight.field_78795_f = -0.18203785F;
      legLeft.field_78795_f = 0.0F;
      legRight.field_78795_f = 0.0F;
      wingLeft.field_78797_d = 14.0F;
      wingRight.field_78797_d = 14.0F;
    } else {
      wingRight.field_78808_h = (MathHelper.func_76134_b(f2 * 0.5F) * 3.1415927F * 0.2F * 2.0F + 0.2F);
      wingRight.field_78795_f = 0.0F;
      wingRight.field_78797_d = 12.0F;
      wingLeft.field_78808_h = (-wingRight.field_78808_h);
      wingLeft.field_78795_f = 0.0F;
      wingLeft.field_78797_d = 12.0F;
      
      armLeft.field_78795_f = 0.2F;
      armRight.field_78795_f = 0.2F;
      legLeft.field_78795_f = 0.1F;
      legRight.field_78795_f = 0.1F;
      
      armRight.field_78808_h += MathHelper.func_76134_b(f2 * 0.09F) * 0.05F + 0.05F;
      armLeft.field_78808_h -= MathHelper.func_76134_b(f2 * 0.09F) * 0.05F + 0.05F;
      armRight.field_78795_f += MathHelper.func_76126_a(f2 * 0.067F) * 0.05F;
      armLeft.field_78795_f -= MathHelper.func_76126_a(f2 * 0.067F) * 0.05F;
      


      legRight.field_78795_f += MathHelper.func_76126_a(f2 * 0.067F) * 0.05F;
      legLeft.field_78795_f -= MathHelper.func_76126_a(f2 * 0.067F) * 0.05F;
    }
    

    if (entitybat.func_70906_o()) {
      legRight.field_78795_f = -1.3F;
      legRight.field_78798_e = 2.0F;
      legRight.field_78797_d = 21.0F;
      legLeft.field_78795_f = -1.3F;
      legLeft.field_78798_e = 2.0F;
      legLeft.field_78797_d = 21.0F;
      body.field_78795_f = 0.1F;
      body.field_78797_d = 17.0F;
      tail.func_78793_a(0.0F, 18.5F, 5.3F);
      tail.field_78798_e = 4.0F;
      tail.field_78797_d = 20.0F;
      setRotateAngle(tail, -0.9F, 0.0F, 0.0F);
      

      armRight.field_78808_h += MathHelper.func_76134_b(f2 * 0.09F) * 0.05F + 0.05F;
      armLeft.field_78808_h -= MathHelper.func_76134_b(f2 * 0.09F) * 0.05F + 0.05F;
      armRight.field_78795_f += MathHelper.func_76126_a(f2 * 0.067F) * 0.05F;
      armLeft.field_78795_f -= MathHelper.func_76126_a(f2 * 0.067F) * 0.05F;
      
      if (!landed) {
        legRight.field_78795_f = 0.0F;
        legLeft.field_78795_f = 0.0F;
      }
    }
    else {
      body.field_78795_f = 0.59184116F;
      body.func_78793_a(0.0F, 15.8F, 2.0F);
      legRight.func_78793_a(-1.4F, 19.0F, 4.7F);
      legLeft.func_78793_a(1.4F, 19.0F, 4.7F);
      legRight.field_78795_f = 0.0F;
      legLeft.field_78795_f = 0.0F;
      
      tail.func_78793_a(0.0F, 18.5F, 5.3F);
      setRotateAngle(tail, -0.63739425F, 0.0F, 0.0F);
    }
    



    if (f1 > 0.1D) {
      ModelRenderer tmp763_760 = tail;763760field_78795_f = ((float)(763760field_78795_f + (-f1 - 0.1D)));
      tail.field_78808_h += 5.0F * MathHelper.func_76134_b(f2 * 0.09F) * 0.05F + 0.05F;
    }
    else {
      tail.field_78808_h += 3.0F * MathHelper.func_76134_b(f2 * 0.09F) * 0.05F + 0.05F;
    }
    
    head.field_78796_g = (f3 / 57.295776F);
    head.field_78795_f = (f4 / 57.295776F);
    
    int i = entitybat.getAttackTimer();
    
    if (i > 0) {
      float di = 10.0F;
      armRight.field_78795_f = (-2.0F + 1.5F * (Math.abs((i - f3) % 10.0F - di * 0.5F) - di * 0.25F) / (di * 0.25F));
    }
    

    tail.func_78785_a(f5);
    armRight.func_78785_a(f5);
    legRight.func_78785_a(f5);
    bodyShoulder.func_78785_a(f5);
    wingRight.func_78785_a(f5);
    body.func_78785_a(f5);
    legLeft.func_78785_a(f5);
    armLeft.func_78785_a(f5);
    wingLeft.func_78785_a(f5);
    head.func_78785_a(f5);
  }
}
