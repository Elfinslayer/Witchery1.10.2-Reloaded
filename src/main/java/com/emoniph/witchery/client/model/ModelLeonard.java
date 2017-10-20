package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityLeonard;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelLeonard extends ModelBase
{
  private final ModelRenderer head;
  private final ModelRenderer snout;
  private final ModelRenderer beard;
  private final ModelRenderer earLeft;
  private final ModelRenderer earRight;
  private final ModelRenderer hornLeft;
  private final ModelRenderer hornMiddle;
  private final ModelRenderer hornRight;
  private final ModelRenderer neck;
  private final ModelRenderer body;
  private final ModelRenderer gownLowerRight;
  private final ModelRenderer rightarm;
  private final ModelRenderer leftarm;
  private final ModelRenderer rightleg;
  private final ModelRenderer leftleg;
  private final ModelRenderer gownLowerLeft;
  
  public ModelLeonard()
  {
    field_78090_t = 64;
    field_78089_u = 64;
    
    neck = new ModelRenderer(this, 48, 0);
    neck.func_78789_a(-2.0F, -1.0F, -2.0F, 4, 2, 4);
    neck.func_78793_a(0.0F, 0.0F, 0.0F);
    neck.func_78787_b(64, 64);
    neck.field_78809_i = true;
    setRotation(neck, 0.1745329F, 0.0F, 0.0F);
    
    head = new ModelRenderer(this, 0, 0);
    head.func_78789_a(-3.0F, -5.0F, -1.0F, 6, 4, 4);
    head.func_78793_a(0.0F, 0.0F, 0.0F);
    head.func_78787_b(64, 64);
    head.field_78809_i = true;
    neck.func_78792_a(head);
    setRotation(head, 0.1745329F, 0.0F, 0.0F);
    snout = new ModelRenderer(this, 16, 2);
    snout.func_78789_a(-2.0F, -5.0F, -7.0F, 4, 4, 7);
    snout.func_78793_a(0.0F, 0.0F, 0.0F);
    snout.func_78787_b(64, 64);
    snout.field_78809_i = true;
    setRotation(snout, 0.1745329F, 0.0F, 0.0F);
    head.func_78792_a(snout);
    beard = new ModelRenderer(this, 0, 10);
    beard.func_78789_a(-2.0F, -0.2F, -7.0F, 4, 2, 2);
    beard.func_78793_a(0.0F, 0.0F, 0.0F);
    beard.func_78787_b(64, 64);
    beard.field_78809_i = true;
    setRotation(beard, -0.0113601F, 0.0F, 0.0F);
    head.func_78792_a(beard);
    earLeft = new ModelRenderer(this, 38, 0);
    earLeft.func_78789_a(3.5F, 1.0F, -0.5F, 1, 3, 1);
    earLeft.func_78793_a(0.0F, 0.0F, 0.0F);
    earLeft.func_78787_b(64, 64);
    earLeft.field_78809_i = true;
    setRotation(earLeft, -0.5129616F, -0.2617994F, -1.180008F);
    head.func_78792_a(earLeft);
    earRight = new ModelRenderer(this, 38, 0);
    earRight.func_78789_a(-4.5F, 1.0F, 0.5F, 1, 3, 1);
    earRight.func_78793_a(0.0F, 0.0F, 0.0F);
    earRight.func_78787_b(64, 64);
    earRight.field_78809_i = true;
    setRotation(earRight, -0.3346075F, 0.0371786F, 1.226894F);
    head.func_78792_a(earRight);
    hornLeft = new ModelRenderer(this, 43, 0);
    hornLeft.func_78789_a(-0.5F, -12.0F, -0.5F, 1, 8, 1);
    hornLeft.func_78793_a(0.0F, 0.0F, 0.0F);
    hornLeft.func_78787_b(64, 64);
    hornLeft.field_78809_i = true;
    setRotation(hornLeft, -0.2268928F, 0.0F, 0.3665191F);
    head.func_78792_a(hornLeft);
    hornMiddle = new ModelRenderer(this, 43, 0);
    hornMiddle.func_78789_a(-0.5F, -10.0F, -0.5F, 1, 6, 1);
    hornMiddle.func_78793_a(0.0F, 0.0F, 0.0F);
    hornMiddle.func_78787_b(64, 64);
    hornMiddle.field_78809_i = true;
    setRotation(hornMiddle, -0.2974289F, 0.0F, 0.0F);
    head.func_78792_a(hornMiddle);
    hornRight = new ModelRenderer(this, 43, 0);
    hornRight.func_78789_a(-0.5F, -12.0F, -0.5F, 1, 8, 1);
    hornRight.func_78793_a(0.0F, 0.0F, 0.0F);
    hornRight.func_78787_b(64, 64);
    hornRight.field_78809_i = true;
    setRotation(hornRight, -0.2268928F, 0.0F, -0.3665191F);
    head.func_78792_a(hornRight);
    
    body = new ModelRenderer(this, 16, 16);
    body.func_78789_a(-4.0F, 0.0F, -2.0F, 8, 12, 4);
    body.func_78793_a(0.0F, 0.0F, 0.0F);
    body.func_78787_b(64, 64);
    body.field_78809_i = true;
    setRotation(body, 0.0F, 0.0F, 0.0F);
    gownLowerRight = new ModelRenderer(this, 0, 33);
    gownLowerRight.func_78789_a(-5.0F, 12.0F, -2.5F, 5, 11, 5);
    gownLowerRight.func_78793_a(0.0F, 0.0F, 0.0F);
    gownLowerRight.func_78787_b(64, 64);
    gownLowerRight.field_78809_i = true;
    setRotation(gownLowerRight, 0.0F, 0.0F, 0.0F);
    rightarm = new ModelRenderer(this, 40, 16);
    rightarm.func_78789_a(-3.0F, -2.0F, -2.0F, 4, 12, 4);
    rightarm.func_78793_a(-5.0F, 2.0F, 0.0F);
    rightarm.func_78787_b(64, 64);
    rightarm.field_78809_i = true;
    setRotation(rightarm, 0.0F, 0.0F, 0.0F);
    leftarm = new ModelRenderer(this, 40, 16);
    leftarm.func_78789_a(-1.0F, -2.0F, -2.0F, 4, 12, 4);
    leftarm.func_78793_a(5.0F, 2.0F, 0.0F);
    leftarm.func_78787_b(64, 64);
    leftarm.field_78809_i = true;
    setRotation(leftarm, 0.0F, 0.0F, 0.0F);
    rightleg = new ModelRenderer(this, 0, 16);
    rightleg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 12, 4);
    rightleg.func_78793_a(-2.0F, 12.0F, 0.0F);
    rightleg.func_78787_b(64, 64);
    rightleg.field_78809_i = true;
    setRotation(rightleg, 0.0F, 0.0F, 0.0F);
    rightleg.func_78792_a(gownLowerRight);
    leftleg = new ModelRenderer(this, 0, 16);
    leftleg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 12, 4);
    leftleg.func_78793_a(2.0F, 12.0F, 0.0F);
    leftleg.func_78787_b(64, 64);
    leftleg.field_78809_i = true;
    setRotation(leftleg, 0.0F, 0.0F, 0.0F);
    gownLowerLeft = new ModelRenderer(this, 21, 33);
    gownLowerLeft.func_78789_a(0.0F, 12.0F, -2.5F, 5, 11, 5);
    gownLowerLeft.func_78793_a(0.0F, 0.0F, 0.0F);
    gownLowerLeft.func_78787_b(64, 64);
    gownLowerLeft.field_78809_i = true;
    setRotation(gownLowerLeft, 0.0F, 0.0F, 0.0F);
    leftleg.func_78792_a(gownLowerLeft);
    
    neck.field_78795_f = 0.1745329F;
    head.field_78795_f = 0.1745329F;
    setRotation(earRight, -0.3346075F, 0.0371786F, 1.226894F);
    gownLowerLeft.func_78793_a(-2.0F, -12.0F, 0.0F);
    gownLowerRight.func_78793_a(2.0F, -12.0F, 0.0F);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    neck.func_78785_a(f5);
    body.func_78785_a(f5);
    rightarm.func_78785_a(f5);
    leftarm.func_78785_a(f5);
    rightleg.func_78785_a(f5);
    leftleg.func_78785_a(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
  {
    neck.field_78796_g = (par4 / 57.295776F);
    neck.field_78795_f = (par5 / 57.295776F);
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
      float f8 = MathHelper.func_76126_a(field_78095_p * 3.1415927F) * -(neck.field_78795_f - 0.7F) * 0.75F;
      rightarm.field_78795_f = ((float)(rightarm.field_78795_f - (f7 * 1.2D + f8)));
      rightarm.field_78796_g += body.field_78796_g * 2.0F;
      rightarm.field_78808_h = (MathHelper.func_76126_a(field_78095_p * 3.1415927F) * -0.4F);
    }
    
    boolean sneaking = false;
    if (sneaking) {
      body.field_78795_f = 0.5F;
      rightarm.field_78795_f += 0.4F;
      leftarm.field_78795_f += 0.4F;
      rightleg.field_78798_e = 4.0F;
      leftleg.field_78798_e = 4.0F;
      rightleg.field_78797_d = 9.0F;
      leftleg.field_78797_d = 9.0F;
      neck.field_78797_d = 1.0F;
    } else {
      body.field_78795_f = 0.0F;
      rightleg.field_78798_e = 0.1F;
      leftleg.field_78798_e = 0.1F;
      rightleg.field_78797_d = 12.0F;
      leftleg.field_78797_d = 12.0F;
      neck.field_78797_d = 0.0F;
    }
    
    rightarm.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
    leftarm.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
    rightarm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    leftarm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    
    boolean shootingBow = false;
    if (shootingBow) {
      float f6 = 0.0F;
      float f7 = 0.0F;
      rightarm.field_78808_h = 0.0F;
      leftarm.field_78808_h = 0.0F;
      rightarm.field_78796_g = (-(0.1F - f6 * 0.6F) + neck.field_78796_g);
      leftarm.field_78796_g = (0.1F - f6 * 0.6F + neck.field_78796_g + 0.4F);
      rightarm.field_78795_f = (-1.5707964F + neck.field_78795_f);
      leftarm.field_78795_f = (-1.5707964F + neck.field_78795_f);
      rightarm.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
      leftarm.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
      rightarm.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
      leftarm.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
      rightarm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
      leftarm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    }
    
    EntityLeonard entityDemon = (EntityLeonard)entity;
    int i = entityDemon.getAttackTimer();
    
    if (i > 0) {
      float di = 10.0F;
      rightarm.field_78795_f = (-2.0F + 1.5F * (Math.abs((i - par4) % 10.0F - di * 0.5F) - di * 0.25F) / (di * 0.25F));
    }
  }
}
