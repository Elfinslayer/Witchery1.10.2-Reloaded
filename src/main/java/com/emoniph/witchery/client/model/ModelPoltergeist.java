package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityPoltergeist;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelPoltergeist extends ModelBase
{
  ModelRenderer bipedHead;
  ModelRenderer bipedBody;
  ModelRenderer bipedRightArm;
  ModelRenderer bipedRightArm2;
  ModelRenderer bipedLeftArm;
  ModelRenderer bipedLeftArm2;
  ModelRenderer bipedRightLeg;
  ModelRenderer bipedLeftLeg;
  
  public ModelPoltergeist()
  {
    field_78090_t = 64;
    field_78089_u = 32;
    
    bipedHead = new ModelRenderer(this, 0, 0);
    bipedHead.func_78789_a(-4.0F, -8.0F, -3.0F, 8, 8, 6);
    bipedHead.func_78793_a(0.0F, 0.0F, 0.0F);
    bipedHead.func_78787_b(64, 32);
    bipedHead.field_78809_i = true;
    setRotation(bipedHead, 0.0F, 0.0F, 0.0F);
    bipedBody = new ModelRenderer(this, 16, 16);
    bipedBody.func_78789_a(-4.0F, 0.0F, -1.0F, 8, 11, 2);
    bipedBody.func_78793_a(0.0F, 0.0F, 0.0F);
    bipedBody.func_78787_b(64, 32);
    bipedBody.field_78809_i = true;
    setRotation(bipedBody, 0.0F, 0.0F, 0.0F);
    bipedRightArm = new ModelRenderer(this, 40, 0);
    bipedRightArm.func_78789_a(-1.0F, -2.0F, -1.0F, 2, 18, 2);
    bipedRightArm.func_78793_a(-5.0F, 2.0F, 0.0F);
    bipedRightArm.func_78787_b(64, 32);
    bipedRightArm.field_78809_i = true;
    setRotation(bipedRightArm, 0.0F, 0.0F, 0.0F);
    bipedRightArm2 = new ModelRenderer(this, 40, 0);
    bipedRightArm2.func_78789_a(-1.0F, -2.0F, -1.0F, 2, 18, 2);
    bipedRightArm2.func_78793_a(-5.0F, 2.0F, 0.0F);
    bipedRightArm2.func_78787_b(64, 32);
    bipedRightArm2.field_78809_i = true;
    setRotation(bipedRightArm2, 0.0F, 0.0F, 0.0F);
    bipedLeftArm = new ModelRenderer(this, 40, 0);
    bipedLeftArm.func_78789_a(-1.0F, -2.0F, -1.0F, 2, 18, 2);
    bipedLeftArm.func_78793_a(5.0F, 2.0F, 0.0F);
    bipedLeftArm.func_78787_b(64, 32);
    bipedLeftArm.field_78809_i = true;
    setRotation(bipedLeftArm, 0.0F, 0.0F, 0.0F);
    bipedLeftArm2 = new ModelRenderer(this, 40, 0);
    bipedLeftArm2.func_78789_a(-1.0F, -2.0F, -1.0F, 2, 18, 2);
    bipedLeftArm2.func_78793_a(5.0F, 2.0F, 0.0F);
    bipedLeftArm2.func_78787_b(64, 32);
    bipedLeftArm2.field_78809_i = true;
    setRotation(bipedLeftArm2, 0.0F, 0.0F, 0.0F);
    bipedRightLeg = new ModelRenderer(this, 0, 16);
    bipedRightLeg.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 13, 2);
    bipedRightLeg.func_78793_a(-2.0F, 11.0F, 0.0F);
    bipedRightLeg.func_78787_b(64, 32);
    bipedRightLeg.field_78809_i = true;
    setRotation(bipedRightLeg, 0.0F, 0.0F, 0.0F);
    bipedLeftLeg = new ModelRenderer(this, 0, 16);
    bipedLeftLeg.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 13, 2);
    bipedLeftLeg.func_78793_a(2.0F, 11.0F, 0.0F);
    bipedLeftLeg.func_78787_b(64, 32);
    bipedLeftLeg.field_78809_i = true;
    setRotation(bipedLeftLeg, 0.0F, 0.0F, 0.0F);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    bipedHead.func_78785_a(f5);
    bipedBody.func_78785_a(f5);
    bipedRightArm.func_78785_a(f5);
    bipedRightArm2.func_78785_a(f5);
    bipedLeftArm.func_78785_a(f5);
    bipedLeftArm2.func_78785_a(f5);
    bipedRightLeg.func_78785_a(f5);
    bipedLeftLeg.func_78785_a(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
  {
    super.func_78087_a(par1, par2, par3, par4, par5, par6, entity);
    bipedHead.field_78796_g = (par4 / 57.295776F);
    bipedHead.field_78795_f = (par5 / 57.295776F);
    
    bipedRightArm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
    bipedRightArm2.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.25F);
    
    bipedLeftArm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
    bipedLeftArm2.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.25F);
    
    bipedRightArm.field_78796_g = 0.0F;
    bipedRightArm2.field_78796_g = 0.0F;
    
    bipedLeftArm.field_78796_g = 0.0F;
    bipedLeftArm2.field_78796_g = 0.0F;
    
    bipedRightArm.field_78808_h = 0.0F;
    bipedRightArm2.field_78808_h = 0.0F;
    
    bipedLeftArm.field_78808_h = 0.0F;
    bipedLeftArm2.field_78808_h = 0.0F;
    

    bipedRightLeg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2);
    bipedLeftLeg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
    bipedRightLeg.field_78796_g = 0.0F;
    bipedLeftLeg.field_78796_g = 0.0F;
    

































    bipedBody.field_78795_f = 0.0F;
    bipedRightLeg.field_78798_e = 0.1F;
    bipedLeftLeg.field_78798_e = 0.1F;
    bipedRightLeg.field_78797_d = 12.0F;
    bipedLeftLeg.field_78797_d = 12.0F;
    bipedHead.field_78797_d = 0.0F;
    

    bipedRightArm.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
    bipedRightArm2.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
    
    bipedLeftArm.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
    bipedLeftArm2.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
    bipedRightArm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    bipedRightArm2.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    bipedLeftArm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    bipedLeftArm2.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    

    EntityPoltergeist entityDemon = (EntityPoltergeist)entity;
    int i = entityDemon.getAttackTimer();
    
    if (i > 0) {
      bipedRightArm.field_78795_f = (-1.5F + 0.8F * func_78172_a(i - par4, 15.0F));
      bipedLeftArm.field_78795_f = (-1.5F + 0.8F * func_78172_a(i - par4, 15.0F));
      
      bipedRightArm2.field_78795_f = (-1.5F + 0.8F * func_78172_a(i - par4, 15.0F));
      bipedLeftArm2.field_78795_f = (-1.5F + 0.8F * func_78172_a(i - par4, 15.0F));
      
      bipedRightArm.field_78808_h = (-(-1.5F + 1.5F * func_78172_a(i - par4, 15.0F)));
      bipedLeftArm.field_78808_h = (-1.5F + 1.5F * func_78172_a(i - par4, 15.0F));
    }
  }
  




  public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {}
  




  private float func_78172_a(float par1, float par2)
  {
    return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
  }
}
