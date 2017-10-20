package com.emoniph.witchery.client.model;

import net.minecraft.client.model.ModelRenderer;

public class ModelBloodCrucible extends net.minecraft.client.model.ModelBase
{
  private ModelRenderer right1;
  private ModelRenderer right2;
  private ModelRenderer left1;
  private ModelRenderer left2;
  private ModelRenderer back1;
  private ModelRenderer back2;
  private ModelRenderer front1;
  private ModelRenderer front2;
  private ModelRenderer bottom;
  
  public ModelBloodCrucible()
  {
    field_78090_t = 32;
    field_78089_u = 32;
    left2 = new ModelRenderer(this, 17, 11);
    left2.func_78793_a(0.0F, 0.0F, 0.0F);
    left2.func_78790_a(2.0F, -2.0F, -2.0F, 1, 1, 4, 0.0F);
    right2 = new ModelRenderer(this, 17, 11);
    right2.func_78793_a(0.0F, 0.0F, 0.0F);
    right2.func_78790_a(-3.0F, -2.0F, -2.0F, 1, 1, 4, 0.0F);
    front2 = new ModelRenderer(this, 17, 19);
    front2.func_78793_a(0.0F, 0.0F, 0.0F);
    front2.func_78790_a(-3.0F, -2.0F, -3.0F, 6, 1, 1, 0.0F);
    front1 = new ModelRenderer(this, 0, 17);
    front1.func_78793_a(0.0F, 0.0F, 0.0F);
    front1.func_78790_a(-3.5F, -5.0F, -4.0F, 7, 3, 1, 0.0F);
    right1 = new ModelRenderer(this, 0, 6);
    right1.func_78793_a(0.0F, 0.0F, 0.0F);
    right1.func_78790_a(-4.0F, -5.0F, -3.7F, 1, 3, 7, 0.0F);
    bottom = new ModelRenderer(this, 0, 0);
    bottom.func_78793_a(0.0F, 0.0F, 0.0F);
    bottom.func_78790_a(-2.0F, -1.0F, -2.0F, 4, 1, 4, 0.0F);
    back2 = new ModelRenderer(this, 17, 19);
    back2.func_78793_a(0.0F, 0.0F, 0.0F);
    back2.func_78790_a(-3.0F, -2.0F, 2.0F, 6, 1, 1, 0.0F);
    back1 = new ModelRenderer(this, 0, 17);
    back1.func_78793_a(0.0F, 0.0F, 0.0F);
    back1.func_78790_a(-3.5F, -5.0F, 3.0F, 7, 3, 1, 0.0F);
    left1 = new ModelRenderer(this, 0, 6);
    left1.func_78793_a(0.0F, 0.0F, 0.0F);
    left1.func_78790_a(3.0F, -5.0F, -3.5F, 1, 3, 7, 0.0F);
  }
  
  private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78088_a(net.minecraft.entity.Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    left2.func_78785_a(f5);
    right2.func_78785_a(f5);
    front2.func_78785_a(f5);
    front1.func_78785_a(f5);
    right1.func_78785_a(f5);
    bottom.func_78785_a(f5);
    back2.func_78785_a(f5);
    back1.func_78785_a(f5);
    left1.func_78785_a(f5);
  }
}
