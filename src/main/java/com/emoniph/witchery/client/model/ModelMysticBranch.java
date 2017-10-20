package com.emoniph.witchery.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMysticBranch extends ModelBase
{
  ModelRenderer Shape1;
  ModelRenderer Shape2;
  ModelRenderer Shape3;
  ModelRenderer Shape4;
  ModelRenderer Shape5;
  ModelRenderer Shape6;
  
  public ModelMysticBranch()
  {
    field_78090_t = 32;
    field_78089_u = 32;
    
    Shape1 = new ModelRenderer(this, 12, 0);
    Shape1.func_78789_a(0.0F, -4.0F, 0.0F, 1, 4, 1);
    Shape1.func_78793_a(0.0F, -8.0F, 0.0F);
    Shape1.func_78787_b(32, 32);
    Shape1.field_78809_i = true;
    setRotation(Shape1, 0.1858931F, 0.0F, 0.2230717F);
    Shape2 = new ModelRenderer(this, 20, 0);
    Shape2.func_78789_a(-0.5F, -5.2F, -1.2F, 1, 5, 1);
    Shape2.func_78793_a(1.0F, -9.0F, 1.0F);
    Shape2.func_78787_b(32, 32);
    Shape2.field_78809_i = true;
    setRotation(Shape2, -0.2230717F, 0.0F, -0.4089647F);
    Shape3 = new ModelRenderer(this, 4, 0);
    Shape3.func_78789_a(-1.0F, -6.0F, 0.0F, 1, 6, 1);
    Shape3.func_78793_a(1.0F, -4.0F, 0.0F);
    Shape3.func_78787_b(32, 32);
    Shape3.field_78809_i = true;
    setRotation(Shape3, 0.2230717F, -0.0371786F, 0.4089647F);
    Shape4 = new ModelRenderer(this, 8, 0);
    Shape4.func_78789_a(-1.0F, -3.5F, -0.5F, 1, 4, 1);
    Shape4.func_78793_a(1.0F, -4.9F, 1.0F);
    Shape4.func_78787_b(32, 32);
    Shape4.field_78809_i = true;
    setRotation(Shape4, -0.5948578F, 0.0F, -0.4089647F);
    Shape5 = new ModelRenderer(this, 16, 0);
    Shape5.func_78789_a(-0.2F, -4.8F, 0.4F, 1, 5, 1);
    Shape5.func_78793_a(1.0F, -12.0F, -1.0F);
    Shape5.func_78787_b(32, 32);
    Shape5.field_78809_i = true;
    setRotation(Shape5, -0.3717861F, 0.0F, 0.0F);
    Shape6 = new ModelRenderer(this, 0, 0);
    Shape6.func_78789_a(0.0F, -8.0F, 0.0F, 1, 8, 1);
    Shape6.func_78793_a(0.0F, 0.0F, 0.0F);
    Shape6.func_78787_b(32, 32);
    Shape6.field_78809_i = true;
    setRotation(Shape6, 0.0F, 0.0F, 0.0F);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    
    Shape1.func_78785_a(f5);
    Shape2.func_78785_a(f5);
    Shape3.func_78785_a(f5);
    Shape4.func_78785_a(f5);
    Shape5.func_78785_a(f5);
    Shape6.func_78785_a(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
  }
}
