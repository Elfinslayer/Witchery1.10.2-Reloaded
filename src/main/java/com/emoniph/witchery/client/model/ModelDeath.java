package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelDeath extends ModelBase
{
  ModelRenderer bipedHead;
  ModelRenderer bipedBody;
  ModelRenderer bipedRightArm;
  ModelRenderer bipedLeftArm;
  ModelRenderer bipedRightLeg;
  ModelRenderer bipedLeftLeg;
  ModelRenderer robe;
  ModelRenderer scythe;
  
  public ModelDeath()
  {
    field_78090_t = 64;
    field_78089_u = 64;
    func_78085_a("scythe.shaft", 58, 5);
    func_78085_a("scythe.blade", 36, 0);
    
    bipedHead = new ModelRenderer(this, 27, 43);
    bipedHead.func_78789_a(-4.0F, -8.0F, -4.0F, 8, 10, 8);
    bipedHead.func_78793_a(0.0F, 0.0F, 0.0F);
    bipedHead.func_78787_b(64, 64);
    bipedHead.field_78809_i = true;
    setRotation(bipedHead, 0.0F, 0.0F, 0.0F);
    bipedBody = new ModelRenderer(this, 16, 16);
    bipedBody.func_78789_a(-4.0F, 0.0F, -2.0F, 8, 12, 4);
    bipedBody.func_78793_a(0.0F, 0.0F, 0.0F);
    bipedBody.func_78787_b(64, 64);
    bipedBody.field_78809_i = true;
    setRotation(bipedBody, 0.0F, 0.0F, 0.0F);
    bipedRightArm = new ModelRenderer(this, 40, 16);
    bipedRightArm.func_78789_a(-3.0F, -2.0F, -2.0F, 4, 12, 4);
    bipedRightArm.func_78793_a(-5.0F, 2.0F, 0.0F);
    bipedRightArm.func_78787_b(64, 64);
    bipedRightArm.field_78809_i = true;
    setRotation(bipedRightArm, 0.0F, 0.0F, 0.0F);
    bipedLeftArm = new ModelRenderer(this, 40, 16);
    bipedLeftArm.func_78789_a(-1.0F, -2.0F, -2.0F, 4, 12, 4);
    bipedLeftArm.func_78793_a(5.0F, 2.0F, 0.0F);
    bipedLeftArm.func_78787_b(64, 64);
    bipedLeftArm.field_78809_i = true;
    setRotation(bipedLeftArm, 0.0F, 0.0F, 0.0F);
    bipedRightLeg = new ModelRenderer(this, 0, 16);
    bipedRightLeg.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 12, 2);
    bipedRightLeg.func_78793_a(-2.0F, 12.0F, 0.0F);
    bipedRightLeg.func_78787_b(64, 64);
    bipedRightLeg.field_78809_i = true;
    setRotation(bipedRightLeg, 0.0F, 0.0F, 0.0F);
    bipedLeftLeg = new ModelRenderer(this, 0, 16);
    bipedLeftLeg.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 12, 2);
    bipedLeftLeg.func_78793_a(2.0F, 12.0F, 0.0F);
    bipedLeftLeg.func_78787_b(64, 64);
    bipedLeftLeg.field_78809_i = true;
    setRotation(bipedLeftLeg, 0.0F, 0.0F, 0.0F);
    robe = new ModelRenderer(this, 0, 33);
    robe.func_78789_a(-4.0F, 0.0F, -2.5F, 8, 23, 5);
    robe.func_78793_a(0.0F, 0.0F, 0.0F);
    robe.func_78787_b(64, 64);
    robe.field_78809_i = true;
    setRotation(robe, 0.0F, 0.0F, 0.0F);
    scythe = new ModelRenderer(this, "scythe");
    scythe.func_78793_a(-6.0F, 10.0F, 0.0F);
    setRotation(scythe, 0.0F, 0.0F, 0.0F);
    scythe.field_78809_i = true;
    scythe.func_78786_a("shaft", -0.5F, -16.0F, -0.5F, 1, 35, 1);
    scythe.func_78786_a("blade", 0.0F, -16.0F, 0.0F, 13, 4, 0);
    bipedRightArm.func_78792_a(scythe);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    bipedHead.func_78785_a(f5);
    bipedBody.func_78785_a(f5);
    bipedRightArm.func_78785_a(f5);
    bipedLeftArm.func_78785_a(f5);
    bipedRightLeg.func_78785_a(f5);
    bipedLeftLeg.func_78785_a(f5);
    GL11.glScalef(1.05F, 1.0F, 1.05F);
    robe.func_78785_a(f5);
  }
  


  public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
  {
    scythe.field_78800_c = -0.8F;
    scythe.field_78798_e = 0.0F;
    scythe.field_78797_d = 8.1F;
    scythe.field_78795_f = 1.5707964F;
    
    bipedHead.field_78796_g = (par4 / 57.295776F);
    bipedHead.field_78795_f = (par5 / 57.295776F);
    
    bipedRightArm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F - 1.5707964F);
    bipedLeftArm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
    bipedRightArm.field_78808_h = 0.0F;
    bipedLeftArm.field_78808_h = 0.0F;
    bipedRightLeg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2);
    bipedLeftLeg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
    bipedRightLeg.field_78796_g = 0.0F;
    bipedLeftLeg.field_78796_g = 0.0F;
    
















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
    
    bipedBody.field_78795_f = 0.0F;
    bipedRightLeg.field_78798_e = 0.1F;
    bipedLeftLeg.field_78798_e = 0.1F;
    bipedRightLeg.field_78797_d = 12.0F;
    bipedLeftLeg.field_78797_d = 12.0F;
    bipedHead.field_78797_d = 0.0F;
    
    bipedRightArm.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
    bipedLeftArm.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
    bipedRightArm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    bipedLeftArm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
  }
}
