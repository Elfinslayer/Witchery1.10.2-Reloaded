package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityHellhound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelHellhound extends ModelBase
{
  public ModelRenderer wolfHeadMain;
  public ModelRenderer wolfBody;
  public ModelRenderer wolfLeg1;
  public ModelRenderer wolfLeg2;
  public ModelRenderer wolfLeg3;
  public ModelRenderer wolfLeg4;
  ModelRenderer wolfTail;
  ModelRenderer wolfMane;
  
  public ModelHellhound()
  {
    field_78090_t = 64;
    field_78089_u = 32;
    wolfBody = new ModelRenderer(this, 18, 17);
    wolfBody.func_78793_a(0.0F, 12.5F, 2.0F);
    wolfBody.func_78789_a(-4.0F, -2.0F, -3.0F, 6, 9, 6);
    setRotateAngle(wolfBody, 1.1838568F, 0.0F, 0.0F);
    wolfTail = new ModelRenderer(this, 9, 19);
    wolfTail.func_78793_a(-1.0F, 12.0F, 8.0F);
    wolfTail.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 6, 2);
    wolfLeg2 = new ModelRenderer(this, 0, 19);
    wolfLeg2.func_78793_a(0.5F, 16.0F, 7.0F);
    wolfLeg2.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 8, 2);
    wolfLeg4 = new ModelRenderer(this, 0, 19);
    wolfLeg4.func_78793_a(0.5F, 16.0F, -4.0F);
    wolfLeg4.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 8, 2);
    wolfMane = new ModelRenderer(this, 22, 0);
    wolfMane.func_78793_a(-1.0F, 14.0F, -3.0F);
    wolfMane.func_78789_a(-4.0F, -3.0F, -3.0F, 8, 8, 9);
    setRotateAngle(wolfMane, 1.5707964F, 0.0F, 0.0F);
    





    wolfLeg3 = new ModelRenderer(this, 0, 19);
    wolfLeg3.func_78793_a(-2.5F, 16.0F, -4.0F);
    wolfLeg3.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 8, 2);
    wolfHeadMain = new ModelRenderer(this, 0, 0);
    wolfHeadMain.func_78793_a(-1.0F, 10.6F, -7.5F);
    wolfHeadMain.func_78789_a(-3.0F, -3.0F, -2.0F, 6, 6, 4);
    setRotateAngle(wolfHeadMain, 0.22759093F, 0.0F, 0.0F);
    wolfLeg1 = new ModelRenderer(this, 0, 19);
    wolfLeg1.func_78793_a(-2.5F, 16.0F, 7.0F);
    wolfLeg1.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 8, 2);
    





    float f = 0.0F;
    



























    wolfHeadMain.func_78784_a(16, 14).func_78790_a(-3.0F, -5.0F, 1.0F, 2, 2, 1, f);
    wolfHeadMain.func_78784_a(16, 14).func_78790_a(1.0F, -5.0F, 1.0F, 2, 2, 1, f);
    wolfHeadMain.func_78784_a(0, 10).func_78790_a(-1.5F, 0.0F, -5.0F, 3, 3, 4, f);
  }
  
  public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
  {
    super.func_78088_a(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
    func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
    
    wolfHeadMain.func_78791_b(p_78088_7_);
    wolfBody.func_78785_a(p_78088_7_);
    wolfLeg1.func_78785_a(p_78088_7_);
    wolfLeg2.func_78785_a(p_78088_7_);
    wolfLeg3.func_78785_a(p_78088_7_);
    wolfLeg4.func_78785_a(p_78088_7_);
    wolfTail.func_78791_b(p_78088_7_);
    wolfMane.func_78785_a(p_78088_7_);
  }
  
  public void func_78086_a(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_)
  {
    EntityHellhound entitywolf = (EntityHellhound)p_78086_1_;
    

    setRotateAngle(wolfTail, entitywolf.isConverting() ? 2.0F : 0.59184116F, 0.0F, 0.0F);
    
    if (p_78086_3_ > 0.1D) {
      ModelRenderer tmp43_40 = wolfTail;4340field_78795_f = ((float)(4340field_78795_f + (1.5F * p_78086_3_ - 0.1D)));
    }
    
    wolfBody.func_78793_a(0.0F, 14.0F, 2.0F);
    wolfBody.func_78793_a(0.0F, 12.5F, 2.0F);
    wolfBody.field_78795_f = 1.5707964F;
    setRotateAngle(wolfBody, 1.1838568F, 0.0F, 0.0F);
    wolfMane.func_78793_a(-1.0F, 14.0F, -3.0F);
    wolfMane.field_78795_f = 1.5707964F;
    wolfTail.func_78793_a(-1.0F, 12.0F, 8.0F);
    wolfLeg1.func_78793_a(-2.5F, 16.0F, 7.0F);
    wolfLeg2.func_78793_a(0.5F, 16.0F, 7.0F);
    wolfLeg3.func_78793_a(-2.5F, 16.0F, -4.0F);
    wolfLeg4.func_78793_a(0.5F, 16.0F, -4.0F);
    wolfLeg1.field_78795_f = (MathHelper.func_76134_b(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_);
    wolfLeg2.field_78795_f = (MathHelper.func_76134_b(p_78086_2_ * 0.6662F + 3.1415927F) * 1.4F * p_78086_3_);
    wolfLeg3.field_78795_f = (MathHelper.func_76134_b(p_78086_2_ * 0.6662F + 3.1415927F) * 1.4F * p_78086_3_);
    wolfLeg4.field_78795_f = (MathHelper.func_76134_b(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_);
    
    wolfTail.func_78793_a(-1.0F, 13.0F, 8.0F);
    wolfHeadMain.field_78808_h = (entitywolf.getInterestedAngle(p_78086_4_) + entitywolf.getShakeAngle(p_78086_4_, 0.0F));
    
    wolfMane.field_78808_h = entitywolf.getShakeAngle(p_78086_4_, -0.08F);
    wolfBody.field_78808_h = entitywolf.getShakeAngle(p_78086_4_, -0.16F);
    wolfTail.field_78808_h = entitywolf.getShakeAngle(p_78086_4_, -0.2F);
  }
  
  public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
  {
    super.func_78087_a(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
    EntityHellhound entitywolf = (EntityHellhound)p_78087_7_;
    wolfHeadMain.field_78795_f = (p_78087_5_ / 57.295776F + (entitywolf.isConverting() ? 0.5F : 0.15F));
    wolfHeadMain.field_78796_g = (p_78087_4_ / 57.295776F);
  }
  

  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
  {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
}
