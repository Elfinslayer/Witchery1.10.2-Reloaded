package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
public class ModelVampire extends ModelBiped
{
  public ModelRenderer bipedHeadNose;
  public ModelRenderer bipedBodyRobe;
  
  public ModelVampire()
  {
    super(0.0F, 0.0F, 64, 64);
    
    field_78116_c = new ModelRenderer(this, 28, 46);
    field_78116_c.func_78793_a(0.0F, 0.0F, 0.0F);
    field_78116_c.func_78790_a(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
    
    bipedHeadNose = new ModelRenderer(this, 52, 46);
    bipedHeadNose.func_78793_a(0.0F, -2.0F, 0.0F);
    bipedHeadNose.func_78790_a(-1.0F, -1.0F, -6.0F, 2, 4, 2, 0.0F);
    
    bipedBodyRobe = new ModelRenderer(this, 0, 38);
    bipedBodyRobe.func_78793_a(0.0F, 0.0F, 0.0F);
    bipedBodyRobe.func_78790_a(-4.0F, 0.0F, -3.0F, 8, 18, 6, 0.5F);
    field_78116_c.func_78792_a(bipedHeadNose);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
  }
  
  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
  {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
  {
    super.func_78087_a(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
    
    field_78114_d.field_78797_d += 0.3F;
  }
}
