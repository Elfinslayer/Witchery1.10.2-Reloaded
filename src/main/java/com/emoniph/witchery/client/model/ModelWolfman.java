package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityReflection;
import com.emoniph.witchery.entity.EntityWolfman;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelWolfman
  extends ModelBase
{
  public ModelRenderer headMain;
  public ModelRenderer bodyUpper;
  public ModelRenderer legRightUpper;
  public ModelRenderer legLeftUpper;
  public ModelRenderer armLeft;
  public ModelRenderer armRight;
  public ModelRenderer tail;
  public ModelRenderer bodyLower;
  public ModelRenderer legRightLower;
  public ModelRenderer legLeftLower;
  public int heldItemLeft;
  public int heldItemRight;
  public boolean isSneak;
  public boolean aimedBow;
  
  public ModelWolfman()
  {
    this(0.0F);
  }
  
  public ModelWolfman(float scale) {
    field_78090_t = 64;
    field_78089_u = 64;
    
    float headScale = 0.05F;
    headMain = new ModelRenderer(this, 0, 0);
    headMain.func_78790_a(-3.0F, -6.0F, -2.0F, 6, 6, 4, 0.05F);
    headMain.func_78793_a(0.0F, 0.0F, -2.0F);
    
    float f = 0.0F;
    headMain.func_78784_a(16, 14).func_78790_a(-3.0F, -8.0F, 1.0F, 2, 2, 1, 0.0F);
    headMain.func_78784_a(16, 14).func_78790_a(1.0F, -8.0F, 1.0F, 2, 2, 1, 0.0F);
    headMain.func_78784_a(0, 10).func_78790_a(-1.5F, -3.1F, -5.0F, 3, 3, 4, 0.0F);
    
    bodyUpper = new ModelRenderer(this, 0, 35);
    bodyUpper.func_78793_a(0.0F, -0.1F, -2.0F);
    bodyUpper.func_78790_a(-5.0F, 0.0F, -3.9F, 10, 7, 8, scale);
    setRotateAngle(bodyUpper, 0.4098033F, 0.0F, 0.0F);
    bodyLower = new ModelRenderer(this, 3, 50);
    bodyLower.func_78793_a(0.0F, 5.0F, -1.5F);
    bodyLower.func_78790_a(-4.0F, 2.0F, -2.3F, 8, 7, 5, scale);
    bodyUpper.func_78792_a(bodyLower);
    
    tail = new ModelRenderer(this, 55, 52);
    tail.func_78793_a(0.0F, 11.9F, 3.6F);
    tail.func_78790_a(-1.0F, 0.0F, -1.0F, 2, 10, 2, scale);
    setRotateAngle(tail, 0.59184116F, 0.0F, 0.0F);
    
    legLeftUpper = new ModelRenderer(this, 38, 0);
    legLeftUpper.field_78809_i = true;
    legLeftUpper.func_78793_a(2.0F, 12.0F, 0.0F);
    legLeftUpper.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 7, 4, scale);
    setRotateAngle(legLeftUpper, -0.4098033F, 0.0F, 0.0F);
    legLeftLower = new ModelRenderer(this, 38, 13);
    legLeftLower.func_78793_a(0.0F, 0.0F, 0.0F);
    legLeftLower.func_78790_a(-2.0F, 3.5F, 2.0F, 4, 8, 4, scale);
    legLeftUpper.func_78792_a(legLeftLower);
    
    legRightUpper = new ModelRenderer(this, 38, 0);
    legRightUpper.func_78793_a(-2.0F, 12.0F, 0.0F);
    legRightUpper.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 7, 4, scale);
    setRotateAngle(legRightUpper, -0.4098033F, 0.0F, 0.0F);
    legRightLower = new ModelRenderer(this, 38, 13);
    legRightLower.func_78793_a(0.0F, 0.0F, 0.0F);
    legRightLower.func_78790_a(-2.0F, 3.5F, 2.0F, 4, 8, 4, scale);
    legRightUpper.func_78792_a(legRightLower);
    
    armLeft = new ModelRenderer(this, 38, 46);
    armLeft.field_78809_i = true;
    armLeft.func_78793_a(6.0F, 2.0F, 0.0F);
    armLeft.func_78790_a(-1.0F, -2.0F, -2.0F, 4, 14, 4, scale);
    
    armRight = new ModelRenderer(this, 38, 46);
    armRight.func_78793_a(-5.8F, 2.0F, 0.0F);
    armRight.func_78790_a(-3.0F, -2.0F, -2.0F, 4, 14, 4, scale);
  }
  




  public void func_78088_a(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
  {
    func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, entity);
    
    headMain.func_78785_a(p_78088_7_);
    bodyUpper.func_78785_a(p_78088_7_);
    armRight.func_78785_a(p_78088_7_);
    legLeftUpper.func_78785_a(p_78088_7_);
    tail.func_78785_a(p_78088_7_);
    armLeft.func_78785_a(p_78088_7_);
    legRightUpper.func_78785_a(p_78088_7_);
  }
  
  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78086_a(EntityLivingBase entity, float par2, float par3, float par4)
  {
    float i = 0.0F;
    if ((entity instanceof EntityWolfman)) {
      EntityWolfman wolfman = (EntityWolfman)entity;
      i = wolfman.getAttackTimer();
      field_78093_q = wolfman.isSitting();
    } else if ((entity instanceof EntityReflection)) {
      EntityReflection wolfman = (EntityReflection)entity;
      i = wolfman.getAttackTimer();
    }
    


    if (i > 0.0F) {
      armRight.field_78795_f = (-2.0F + 1.5F * interpolateRotation(i - par4, 10.0F));
      armLeft.field_78795_f = (-1.0F + 0.9F * interpolateRotation(i - par4, 10.0F));
    } else {
      armRight.field_78795_f = (MathHelper.func_76134_b(par2 * 0.6662F + 3.1415927F) * 2.0F * par3 * 0.5F);
      armLeft.field_78795_f = (MathHelper.func_76134_b(par2 * 0.6662F) * 2.0F * par3 * 0.5F);
    }
  }
  
  private float interpolateRotation(float par1, float par2) {
    return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
  }
  

  public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
  {
    headMain.field_78796_g = (p_78087_4_ / 57.295776F);
    headMain.field_78795_f = (p_78087_5_ / 57.295776F);
    
    armRight.field_78808_h = 0.0F;
    armLeft.field_78808_h = 0.0F;
    
    legRightUpper.field_78795_f = Math.max(-0.4098033F + MathHelper.func_76134_b(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_, -0.8F);
    
    legLeftUpper.field_78795_f = Math.max(-0.4098033F + MathHelper.func_76134_b(p_78087_1_ * 0.6662F + 3.1415927F) * 1.4F * p_78087_2_, -0.8F);
    




    legRightUpper.field_78796_g = 0.0F;
    legLeftUpper.field_78796_g = 0.0F;
    


    if (field_78093_q) {
      armRight.field_78795_f += -0.62831855F;
      armLeft.field_78795_f += -0.62831855F;
      legRightUpper.field_78795_f = -1.2566371F;
      legLeftUpper.field_78795_f = -1.2566371F;
      legRightUpper.field_78796_g = 0.31415927F;
      legLeftUpper.field_78796_g = -0.31415927F;
    }
    
    if (heldItemLeft != 0) {
      armLeft.field_78795_f = (armLeft.field_78795_f * 0.5F - 0.31415927F * heldItemLeft);
    }
    

    if (heldItemRight != 0) {
      armRight.field_78795_f = (armRight.field_78795_f * 0.5F - 0.31415927F * heldItemRight);
    }
    

    armRight.field_78796_g = 0.0F;
    armLeft.field_78796_g = 0.0F;
    


    if (field_78095_p > -9990.0F) {
      float f6 = field_78095_p;
      bodyUpper.field_78796_g = (MathHelper.func_76126_a(MathHelper.func_76129_c(f6) * 3.1415927F * 2.0F) * 0.2F);
      armRight.field_78798_e = (MathHelper.func_76126_a(bodyUpper.field_78796_g) * 5.0F);
      armRight.field_78800_c = (-MathHelper.func_76134_b(bodyUpper.field_78796_g) * 5.0F);
      armLeft.field_78798_e = (-MathHelper.func_76126_a(bodyUpper.field_78796_g) * 5.0F);
      armLeft.field_78800_c = (MathHelper.func_76134_b(bodyUpper.field_78796_g) * 5.0F);
      armRight.field_78796_g += bodyUpper.field_78796_g;
      armLeft.field_78796_g += bodyUpper.field_78796_g;
      armLeft.field_78795_f += bodyUpper.field_78796_g;
      f6 = 1.0F - field_78095_p;
      f6 *= f6;
      f6 *= f6;
      f6 = 1.0F - f6;
      float f7 = MathHelper.func_76126_a(f6 * 3.1415927F);
      float f8 = MathHelper.func_76126_a(field_78095_p * 3.1415927F) * -(headMain.field_78795_f - 0.7F) * 0.75F;
      
      armRight.field_78795_f = ((float)(armRight.field_78795_f - (f7 * 1.2D + f8)));
      armRight.field_78796_g += bodyUpper.field_78796_g * 2.0F;
      armRight.field_78808_h = (MathHelper.func_76126_a(field_78095_p * 3.1415927F) * -0.4F);
    }
    
    if (isSneak) {
      bodyUpper.field_78795_f = 0.5F;
      armRight.field_78795_f += 0.4F;
      armLeft.field_78795_f += 0.4F;
      legRightUpper.field_78798_e = 4.0F;
      legLeftUpper.field_78798_e = 4.0F;
      legRightUpper.field_78797_d = 9.0F;
      legLeftUpper.field_78797_d = 9.0F;
      headMain.field_78797_d = 0.0F;
    }
    else {
      setRotateAngle(bodyUpper, 0.4098033F, 0.0F, 0.0F);
      

      legRightUpper.field_78798_e = 0.1F;
      legLeftUpper.field_78798_e = 0.1F;
      legRightUpper.field_78797_d = 12.0F;
      legLeftUpper.field_78797_d = 12.0F;
      headMain.field_78797_d = 0.0F;
    }
    
    setRotateAngle(tail, 0.59184116F, 0.0F, 0.0F);
    
    if (p_78087_2_ > 0.1D) {
      ModelRenderer tmp750_747 = tail;750747field_78795_f = ((float)(750747field_78795_f + (p_78087_2_ - 0.1D)));
      tail.field_78808_h += 5.0F * MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
    }
    else {
      tail.field_78808_h += 3.0F * MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
    }
    
    armRight.field_78808_h += MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
    armLeft.field_78808_h -= MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
    armRight.field_78795_f += MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
    armLeft.field_78795_f -= MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
    
    if (aimedBow) {
      float f6 = 0.0F;
      float f7 = 0.0F;
      armRight.field_78808_h = 0.0F;
      armLeft.field_78808_h = 0.0F;
      armRight.field_78796_g = (-(0.1F - f6 * 0.6F) + headMain.field_78796_g);
      armLeft.field_78796_g = (0.1F - f6 * 0.6F + headMain.field_78796_g + 0.4F);
      armRight.field_78795_f = (-1.5707964F + headMain.field_78795_f);
      armLeft.field_78795_f = (-1.5707964F + headMain.field_78795_f);
      armRight.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
      armLeft.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
      armRight.field_78808_h += MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
      armLeft.field_78808_h -= MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
      armRight.field_78795_f += MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
      armLeft.field_78795_f -= MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
    }
  }
}
