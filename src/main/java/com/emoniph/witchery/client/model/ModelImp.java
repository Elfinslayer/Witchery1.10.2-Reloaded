package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityImp;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelImp
  extends ModelBase
{
  ModelRenderer head;
  ModelRenderer body;
  ModelRenderer rightarm;
  ModelRenderer leftarm;
  ModelRenderer rightleg;
  ModelRenderer leftleg;
  ModelRenderer chest;
  ModelRenderer hornLeft;
  ModelRenderer hornRight;
  ModelRenderer nose;
  ModelRenderer wingRight;
  ModelRenderer wingLeft;
  
  public ModelImp()
  {
    field_78090_t = 64;
    field_78089_u = 64;
    
    head = new ModelRenderer(this, 0, 0);
    head.func_78789_a(-5.0F, -8.0F, -4.0F, 10, 8, 10);
    head.func_78793_a(0.0F, 8.0F, 0.0F);
    head.func_78787_b(64, 64);
    head.field_78809_i = true;
    setRotation(head, 0.0F, 0.0F, 0.0F);
    body = new ModelRenderer(this, 0, 48);
    body.func_78789_a(-4.0F, 0.0F, -4.0F, 8, 9, 7);
    body.func_78793_a(0.0F, 9.0F, 0.0F);
    body.func_78787_b(64, 64);
    body.field_78809_i = true;
    setRotation(body, 0.0F, 0.0F, 0.0F);
    rightarm = new ModelRenderer(this, 41, 0);
    rightarm.func_78789_a(-2.0F, -2.0F, -1.5F, 3, 13, 3);
    rightarm.func_78793_a(-5.0F, 11.0F, 0.0F);
    rightarm.func_78787_b(64, 64);
    rightarm.field_78809_i = true;
    setRotation(rightarm, 0.0F, 0.0F, 0.0F);
    leftarm = new ModelRenderer(this, 41, 0);
    leftarm.func_78789_a(-1.0F, -2.0F, -1.5F, 3, 13, 3);
    leftarm.func_78793_a(5.0F, 11.0F, 0.0F);
    leftarm.func_78787_b(64, 64);
    leftarm.field_78809_i = true;
    setRotation(leftarm, 0.0F, 0.0F, 0.0F);
    rightleg = new ModelRenderer(this, 33, 48);
    rightleg.func_78789_a(-1.5F, 0.0F, -1.5F, 3, 6, 3);
    rightleg.func_78793_a(-1.5F, 18.0F, 0.0F);
    rightleg.func_78787_b(64, 64);
    rightleg.field_78809_i = true;
    setRotation(rightleg, 0.0F, 0.0F, 0.0F);
    leftleg = new ModelRenderer(this, 33, 48);
    leftleg.func_78789_a(-1.5F, 0.0F, -1.5F, 3, 6, 3);
    leftleg.func_78793_a(1.5F, 18.0F, 0.0F);
    leftleg.func_78787_b(64, 64);
    leftleg.field_78809_i = true;
    setRotation(leftleg, 0.0F, 0.0F, 0.0F);
    chest = new ModelRenderer(this, 4, 41);
    chest.func_78789_a(-4.0F, 0.0F, -2.0F, 6, 2, 4);
    chest.func_78793_a(1.0F, 8.0F, 0.0F);
    chest.func_78787_b(64, 64);
    chest.field_78809_i = true;
    setRotation(chest, 0.0F, 0.0F, 0.0F);
    hornLeft = new ModelRenderer(this, 0, 21);
    hornLeft.func_78789_a(-1.0F, -5.0F, -1.0F, 2, 5, 2);
    hornLeft.func_78793_a(4.0F, 2.0F, 0.0F);
    hornLeft.func_78787_b(64, 64);
    hornLeft.field_78809_i = true;
    setRotation(hornLeft, 0.4089647F, 0.0F, 0.7435722F);
    head.func_78792_a(hornLeft);
    hornRight = new ModelRenderer(this, 0, 21);
    hornRight.func_78789_a(-1.0F, -5.0F, -1.0F, 2, 5, 2);
    hornRight.func_78793_a(-4.0F, 2.0F, 0.0F);
    hornRight.func_78787_b(64, 64);
    hornRight.field_78809_i = true;
    setRotation(hornRight, 0.4089647F, 0.0F, -0.7435722F);
    head.func_78792_a(hornRight);
    nose = new ModelRenderer(this, 9, 21);
    nose.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 4, 2);
    nose.func_78793_a(0.0F, 3.0F, -3.0F);
    nose.func_78787_b(64, 64);
    nose.field_78809_i = true;
    setRotation(nose, -0.9666439F, 0.0F, 0.0F);
    head.func_78792_a(nose);
    
    wingRight = new ModelRenderer(this, 23, 21);
    wingRight.func_78789_a(0.0F, 0.0F, 0.0F, 14, 21, 0);
    wingRight.func_78793_a(1.0F, -8.0F, 3.0F);
    wingRight.func_78787_b(128, 32);
    wingRight.field_78809_i = true;
    setRotation(wingRight, 0.3047198F, -0.6698132F, -0.6283185F);
    
    wingLeft = new ModelRenderer(this, 23, 21);
    wingLeft.func_78789_a(0.0F, 0.0F, 0.0F, 14, 21, 0);
    wingLeft.func_78793_a(-1.0F, -8.0F, 3.0F);
    wingLeft.func_78787_b(128, 32);
    wingLeft.field_78809_i = true;
    
    setRotation(wingLeft, -0.3047198F, 3.811406F, 0.6283185F);
    
    wingRight.func_78793_a(-2.0F, 10.0F, -1.0F);
    wingLeft.func_78793_a(2.0F, 10.0F, -1.0F);
    
    leftleg.func_78793_a(1.5F, 18.0F, 0.0F);
    rightleg.func_78793_a(-1.5F, 18.0F, 0.0F);
    chest.func_78793_a(1.0F, 8.0F, 0.0F);
    head.func_78793_a(0.0F, 8.0F, 0.0F);
    hornRight.func_78793_a(-4.0F, -5.0F, 0.0F);
    hornLeft.func_78793_a(4.0F, -5.0F, 0.0F);
    nose.func_78793_a(0.0F, -4.0F, -3.0F);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  



  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    boolean scaled = false;
    if ((entity != null) && ((entity instanceof EntityImp))) {
      EntityImp imp = (EntityImp)entity;
      if (imp.isPowered()) {
        scaled = true;
        GL11.glScalef(1.5F, 1.0F, 1.5F);
      }
    }
    
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    
    leftleg.func_78793_a(1.5F, 18.0F, 0.0F);
    rightleg.func_78793_a(-1.5F, 18.0F, 0.0F);
    chest.func_78793_a(1.0F, 8.0F, 0.0F);
    head.func_78793_a(0.0F, 8.0F, 0.0F);
    hornRight.func_78793_a(-4.0F, -5.0F, 0.0F);
    hornLeft.func_78793_a(4.0F, -5.0F, 0.0F);
    nose.func_78793_a(0.0F, -4.0F, -3.0F);
    
    head.func_78785_a(f5);
    body.func_78785_a(f5);
    rightarm.func_78785_a(f5);
    leftarm.func_78785_a(f5);
    rightleg.func_78785_a(f5);
    leftleg.func_78785_a(f5);
    body.func_78785_a(f5);
    chest.func_78785_a(f5);
    
    wingLeft.func_78785_a(f5);
    wingRight.func_78785_a(f5);
    
    if (scaled) {
      GL11.glScalef(1.0F, 1.0F, 1.0F);
    }
  }
  

  public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
  {
    head.field_78796_g = (par4 / 57.295776F);
    head.field_78795_f = (par5 / 57.295776F);
    rightarm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
    leftarm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
    rightarm.field_78808_h = 0.0F;
    leftarm.field_78808_h = 0.0F;
    rightleg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2);
    leftleg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
    rightleg.field_78796_g = 0.0F;
    leftleg.field_78796_g = 0.0F;
    
    if (field_78093_q) {
      rightarm.field_78795_f += -0.62831855F;
      leftarm.field_78795_f += -0.62831855F;
      rightleg.field_78795_f = -1.2566371F;
      leftleg.field_78795_f = -1.2566371F;
      rightleg.field_78796_g = 0.31415927F;
      leftleg.field_78796_g = -0.31415927F;
    }
    
    rightarm.field_78796_g = 0.0F;
    leftarm.field_78796_g = 0.0F;
    


    if (field_78095_p > -9990.0F) {
      float f6 = field_78095_p;
      body.field_78796_g = (MathHelper.func_76126_a(MathHelper.func_76129_c(f6) * 3.1415927F * 2.0F) * 0.2F);
      rightarm.field_78798_e = (MathHelper.func_76126_a(body.field_78796_g) * 5.0F);
      rightarm.field_78800_c = (-MathHelper.func_76134_b(body.field_78796_g) * 5.0F);
      leftarm.field_78798_e = (-MathHelper.func_76126_a(body.field_78796_g) * 5.0F);
      leftarm.field_78800_c = (MathHelper.func_76134_b(body.field_78796_g) * 5.0F);
      rightarm.field_78796_g += body.field_78796_g;
      leftarm.field_78796_g += body.field_78796_g;
      leftarm.field_78795_f += body.field_78796_g;
      f6 = 1.0F - field_78095_p;
      f6 *= f6;
      f6 *= f6;
      f6 = 1.0F - f6;
      float f7 = MathHelper.func_76126_a(f6 * 3.1415927F);
      float f8 = MathHelper.func_76126_a(field_78095_p * 3.1415927F) * -(head.field_78795_f - 0.7F) * 0.75F;
      rightarm.field_78795_f = ((float)(rightarm.field_78795_f - (f7 * 1.2D + f8)));
      rightarm.field_78796_g += body.field_78796_g * 2.0F;
      rightarm.field_78808_h = (MathHelper.func_76126_a(field_78095_p * 3.1415927F) * -0.4F);
    }
    
    body.field_78795_f = 0.0F;
    rightleg.field_78798_e = 0.1F;
    leftleg.field_78798_e = 0.1F;
    rightleg.field_78797_d = 12.0F;
    leftleg.field_78797_d = 12.0F;
    head.field_78797_d = 0.0F;
    
    rightarm.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
    leftarm.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
    rightarm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    leftarm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
  }
}
