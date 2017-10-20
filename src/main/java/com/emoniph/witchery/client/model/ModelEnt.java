package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityEnt;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelEnt extends ModelBase
{
  ModelRenderer ArmLeft;
  ModelRenderer ArmRight;
  ModelRenderer Body;
  ModelRenderer Face;
  ModelRenderer Leg8;
  ModelRenderer Leg6;
  ModelRenderer Leg4;
  ModelRenderer Leg2;
  ModelRenderer Leg7;
  ModelRenderer Leg5;
  ModelRenderer Leg3;
  ModelRenderer Leg1;
  ModelRenderer LeavesBase;
  ModelRenderer LeavesTop;
  ModelRenderer LeavesBaseInner;
  ModelRenderer LeavesTopInner;
  
  public ModelEnt()
  {
    field_78090_t = 256;
    field_78089_u = 256;
    
    ArmLeft = new ModelRenderer(this, 82, 0);
    ArmLeft.func_78789_a(0.0F, -22.0F, -3.0F, 6, 24, 6);
    ArmLeft.func_78793_a(8.0F, -4.0F, 0.0F);
    ArmLeft.func_78787_b(256, 256);
    ArmLeft.field_78809_i = true;
    setRotation(ArmLeft, 0.0F, 0.0F, 0.0F);
    ArmRight = new ModelRenderer(this, 82, 0);
    ArmRight.func_78789_a(-6.0F, -22.0F, -3.0F, 6, 24, 6);
    ArmRight.func_78793_a(-8.0F, -4.0F, 0.0F);
    ArmRight.func_78787_b(256, 256);
    ArmRight.field_78809_i = true;
    setRotation(ArmRight, 0.0F, 0.0F, 0.0F);
    Body = new ModelRenderer(this, 0, 50);
    Body.func_78789_a(-8.0F, -46.0F, -8.0F, 16, 48, 16);
    Body.func_78793_a(0.0F, 20.0F, 0.0F);
    Body.func_78787_b(256, 256);
    Body.field_78809_i = true;
    setRotation(Body, 0.0F, 0.0F, 0.0F);
    
    Face = new ModelRenderer(this, 0, 116);
    Face.func_78789_a(-8.0F, -46.0F, -9.0F, 16, 24, 16);
    Face.func_78793_a(0.0F, 20.0F, 0.0F);
    Face.func_78787_b(256, 256);
    Face.field_78809_i = true;
    setRotation(Face, 0.0F, 0.0F, 0.0F);
    
    Leg8 = new ModelRenderer(this, 18, 0);
    Leg8.func_78789_a(-3.0F, -1.0F, -1.0F, 16, 2, 2);
    Leg8.func_78793_a(4.0F, 20.0F, -1.0F);
    Leg8.func_78787_b(256, 256);
    Leg8.field_78809_i = true;
    setRotation(Leg8, 0.0F, 0.5759587F, 0.1919862F);
    Leg6 = new ModelRenderer(this, 18, 0);
    Leg6.func_78789_a(-3.0F, -1.0F, -1.0F, 16, 2, 2);
    Leg6.func_78793_a(4.0F, 20.0F, 0.0F);
    Leg6.func_78787_b(256, 256);
    Leg6.field_78809_i = true;
    setRotation(Leg6, 0.0F, 0.2792527F, 0.1919862F);
    Leg4 = new ModelRenderer(this, 18, 0);
    Leg4.func_78789_a(-3.0F, -1.0F, -1.0F, 16, 2, 2);
    Leg4.func_78793_a(4.0F, 20.0F, 1.0F);
    Leg4.func_78787_b(256, 256);
    Leg4.field_78809_i = true;
    setRotation(Leg4, 0.0F, -0.2792527F, 0.1919862F);
    Leg2 = new ModelRenderer(this, 18, 0);
    Leg2.func_78789_a(-3.0F, -1.0F, -1.0F, 16, 2, 2);
    Leg2.func_78793_a(4.0F, 20.0F, 2.0F);
    Leg2.func_78787_b(256, 256);
    Leg2.field_78809_i = true;
    setRotation(Leg2, 0.0F, -0.5759587F, 0.1919862F);
    Leg7 = new ModelRenderer(this, 18, 0);
    Leg7.func_78789_a(-13.0F, -1.0F, -1.0F, 16, 2, 2);
    Leg7.func_78793_a(-4.0F, 20.0F, -1.0F);
    Leg7.func_78787_b(256, 256);
    Leg7.field_78809_i = true;
    setRotation(Leg7, 0.0F, -0.5759587F, -0.1919862F);
    Leg5 = new ModelRenderer(this, 18, 0);
    Leg5.func_78789_a(-13.0F, -1.0F, -1.0F, 16, 2, 2);
    Leg5.func_78793_a(-4.0F, 20.0F, 0.0F);
    Leg5.func_78787_b(256, 256);
    Leg5.field_78809_i = true;
    setRotation(Leg5, 0.0F, -0.2792527F, -0.1919862F);
    Leg3 = new ModelRenderer(this, 18, 0);
    Leg3.func_78789_a(-13.0F, -1.0F, -1.0F, 16, 2, 2);
    Leg3.func_78793_a(-4.0F, 20.0F, 1.0F);
    Leg3.func_78787_b(256, 256);
    Leg3.field_78809_i = true;
    setRotation(Leg3, 0.0F, 0.2792527F, -0.1919862F);
    Leg1 = new ModelRenderer(this, 18, 0);
    Leg1.func_78789_a(-13.0F, -1.0F, -1.0F, 16, 2, 2);
    Leg1.func_78793_a(-4.0F, 20.0F, 2.0F);
    Leg1.func_78787_b(256, 256);
    Leg1.field_78809_i = true;
    setRotation(Leg1, 0.0F, 0.5759587F, -0.1919862F);
    LeavesBase = new ModelRenderer(this, 0, 180);
    LeavesBase.func_78789_a(0.0F, 0.0F, 0.0F, 60, 16, 60);
    LeavesBase.func_78793_a(-30.0F, -42.0F, -30.0F);
    LeavesBase.func_78787_b(256, 256);
    LeavesBase.field_78809_i = true;
    setRotation(LeavesBase, 0.0F, 0.0F, 0.0F);
    LeavesTop = new ModelRenderer(this, 56, 130);
    LeavesTop.func_78789_a(0.0F, 0.0F, 0.0F, 32, 16, 32);
    LeavesTop.func_78793_a(-16.0F, -58.0F, -16.0F);
    LeavesTop.func_78787_b(256, 256);
    LeavesTop.field_78809_i = true;
    setRotation(LeavesTop, 0.0F, 0.0F, 0.0F);
    
    LeavesBaseInner = new ModelRenderer(this, 24, 59);
    LeavesBaseInner.func_78789_a(0.0F, 0.0F, 0.0F, 56, 14, 56);
    LeavesBaseInner.func_78793_a(-28.0F, -41.0F, -28.0F);
    LeavesBaseInner.func_78787_b(64, 32);
    LeavesBaseInner.field_78809_i = true;
    setRotation(LeavesBaseInner, 0.0F, 0.0F, 0.0F);
    
    LeavesTopInner = new ModelRenderer(this, 108, 14);
    LeavesTopInner.func_78789_a(0.0F, 0.0F, 0.0F, 28, 14, 28);
    LeavesTopInner.func_78793_a(-14.0F, -57.0F, -14.0F);
    LeavesTopInner.func_78787_b(64, 32);
    LeavesTopInner.field_78809_i = true;
    setRotation(LeavesTopInner, 0.0F, 0.0F, 0.0F);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    ArmLeft.func_78785_a(f5);
    ArmRight.func_78785_a(f5);
    Body.func_78785_a(f5);
    Leg8.func_78785_a(f5);
    Leg6.func_78785_a(f5);
    Leg4.func_78785_a(f5);
    Leg2.func_78785_a(f5);
    Leg7.func_78785_a(f5);
    Leg5.func_78785_a(f5);
    Leg3.func_78785_a(f5);
    Leg1.func_78785_a(f5);
    
    LeavesBaseInner.func_78785_a(f5);
    LeavesTopInner.func_78785_a(f5);
    
    LeavesBase.func_78785_a(f5);
    LeavesTop.func_78785_a(f5);
    
    if ((entity != null) && ((entity instanceof EntityEnt)) && (((EntityEnt)entity).isScreaming())) {
      Face.func_78785_a(f5);
    }
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4)
  {
    EntityEnt entity = (EntityEnt)par1EntityLiving;
    int i = entity.getAttackTimer();
    
    if (i > 0) {
      ArmRight.field_78795_f = (3.0F - 1.3F * func_78172_a(i - par4, 10.0F));
      ArmLeft.field_78795_f = (2.5F - 1.2F * func_78172_a(i - par4, 10.0F));
    } else {
      ArmRight.field_78795_f = 0.0F;
      ArmLeft.field_78795_f = 0.0F;
      ArmRight.field_78808_h = ((-0.2F + 0.1F * func_78172_a(par2, 13.0F)) * par3 - 0.1F);
      ArmLeft.field_78808_h = ((0.2F - 0.1F * func_78172_a(par2, 13.0F)) * par3 + 0.1F);
    }
  }
  
  private float func_78172_a(float par1, float par2) {
    return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
  }
  
  public void func_78087_a(float par1, float par2, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.func_78087_a(par1, par2, f2, f3, f4, f5, entity);
    
    float f6 = 0.7853982F;
    Leg1.field_78808_h = (-f6);
    Leg2.field_78808_h = f6;
    Leg3.field_78808_h = (-f6 * 0.74F);
    Leg4.field_78808_h = (f6 * 0.74F);
    Leg5.field_78808_h = (-f6 * 0.74F);
    Leg6.field_78808_h = (f6 * 0.74F);
    Leg7.field_78808_h = (-f6);
    Leg8.field_78808_h = f6;
    float f7 = -0.0F;
    float f8 = 0.3926991F;
    Leg1.field_78796_g = (f8 * 2.0F + f7);
    Leg2.field_78796_g = (-f8 * 2.0F - f7);
    Leg3.field_78796_g = (f8 * 1.0F + f7);
    Leg4.field_78796_g = (-f8 * 1.0F - f7);
    Leg5.field_78796_g = (-f8 * 1.0F + f7);
    Leg6.field_78796_g = (f8 * 1.0F - f7);
    Leg7.field_78796_g = (-f8 * 2.0F + f7);
    Leg8.field_78796_g = (f8 * 2.0F - f7);
    float f9 = -(MathHelper.func_76134_b(par1 * 0.6662F * 2.0F + 0.0F) * 0.4F) * par2;
    float f10 = -(MathHelper.func_76134_b(par1 * 0.6662F * 2.0F + 3.1415927F) * 0.4F) * par2;
    float f11 = -(MathHelper.func_76134_b(par1 * 0.6662F * 2.0F + 1.5707964F) * 0.4F) * par2;
    float f12 = -(MathHelper.func_76134_b(par1 * 0.6662F * 2.0F + 4.712389F) * 0.4F) * par2;
    float f13 = Math.abs(MathHelper.func_76126_a(par1 * 0.6662F + 0.0F) * 0.4F) * par2;
    float f14 = Math.abs(MathHelper.func_76126_a(par1 * 0.6662F + 3.1415927F) * 0.4F) * par2;
    float f15 = Math.abs(MathHelper.func_76126_a(par1 * 0.6662F + 1.5707964F) * 0.4F) * par2;
    float f16 = Math.abs(MathHelper.func_76126_a(par1 * 0.6662F + 4.712389F) * 0.4F) * par2;
    Leg1.field_78796_g += f9;
    Leg2.field_78796_g += -f9;
    Leg3.field_78796_g += f10;
    Leg4.field_78796_g += -f10;
    Leg5.field_78796_g += f11;
    Leg6.field_78796_g += -f11;
    Leg7.field_78796_g += f12;
    Leg8.field_78796_g += -f12;
    Leg1.field_78808_h += f13;
    Leg2.field_78808_h += -f13;
    Leg3.field_78808_h += f14;
    Leg4.field_78808_h += -f14;
    Leg5.field_78808_h += f15;
    Leg6.field_78808_h += -f15;
    Leg7.field_78808_h += f16;
    Leg8.field_78808_h += -f16;
  }
}
