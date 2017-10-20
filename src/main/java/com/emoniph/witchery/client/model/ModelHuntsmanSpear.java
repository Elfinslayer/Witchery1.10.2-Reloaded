package com.emoniph.witchery.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class ModelHuntsmanSpear extends ModelBase
{
  ModelRenderer shaft;
  ModelRenderer headFront;
  ModelRenderer headSide;
  
  public ModelHuntsmanSpear()
  {
    field_78090_t = 64;
    field_78089_u = 64;
    
    shaft = new ModelRenderer(this, 0, 0);
    shaft.func_78789_a(-0.5F, -18.0F, -0.5F, 1, 32, 1);
    shaft.func_78793_a(0.0F, 0.0F, 0.0F);
    shaft.func_78787_b(64, 64);
    shaft.field_78809_i = true;
    setRotation(shaft, 0.0F, 0.0F, 0.0F);
    headFront = new ModelRenderer(this, 6, 3);
    headFront.func_78789_a(-1.5F, -6.0F, 0.0F, 3, 6, 0);
    headFront.func_78793_a(0.0F, -17.0F, 0.0F);
    headFront.func_78787_b(64, 64);
    headFront.field_78809_i = true;
    setRotation(headFront, 0.0F, 0.0F, 0.0F);
    headSide = new ModelRenderer(this, 6, 0);
    headSide.func_78789_a(0.0F, -6.0F, -1.5F, 0, 6, 3);
    headSide.func_78793_a(0.0F, -17.0F, 0.0F);
    headSide.func_78787_b(64, 64);
    headSide.field_78809_i = true;
    setRotation(headSide, 0.0F, 0.0F, 0.0F);
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
    shaft.func_78785_a(f5);
    headFront.func_78785_a(f5);
    headSide.func_78785_a(f5);
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
  }
}
