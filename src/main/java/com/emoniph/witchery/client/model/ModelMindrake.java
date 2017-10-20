package com.emoniph.witchery.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class ModelMindrake extends ModelBase
{
  ModelRenderer leaves;
  ModelRenderer bodyTop;
  ModelRenderer bodyBottom;
  ModelRenderer legLeft;
  ModelRenderer legRight;
  
  public ModelMindrake()
  {
    field_78090_t = 64;
    field_78089_u = 32;
    
    leaves = new ModelRenderer(this, 0, 0);
    leaves.func_78789_a(-3.0F, 0.0F, 0.0F, 6, 6, 0);
    leaves.func_78793_a(0.0F, 13.0F, 0.0F);
    leaves.func_78787_b(64, 32);
    leaves.field_78809_i = true;
    setRotation(leaves, 0.0F, 0.0F, 0.0F);
    bodyTop = new ModelRenderer(this, 0, 7);
    bodyTop.func_78789_a(-1.5F, 0.0F, -1.5F, 3, 1, 3);
    bodyTop.func_78793_a(0.0F, 19.0F, 0.0F);
    bodyTop.func_78787_b(64, 32);
    bodyTop.field_78809_i = true;
    setRotation(bodyTop, 0.0F, 0.0F, 0.0F);
    bodyBottom = new ModelRenderer(this, 0, 12);
    bodyBottom.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 3, 4);
    bodyBottom.func_78793_a(0.0F, 20.0F, 0.0F);
    bodyBottom.func_78787_b(64, 32);
    bodyBottom.field_78809_i = true;
    setRotation(bodyBottom, 0.0F, 0.0F, 0.0F);
    legLeft = new ModelRenderer(this, 0, 20);
    legLeft.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 1, 1);
    legLeft.func_78793_a(1.0F, 23.0F, 0.0F);
    legLeft.func_78787_b(64, 32);
    legLeft.field_78809_i = true;
    setRotation(legLeft, 0.0F, 0.0F, 0.0F);
    legRight = new ModelRenderer(this, 0, 20);
    legRight.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 1, 1);
    legRight.func_78793_a(-1.0F, 23.0F, 0.0F);
    legRight.func_78787_b(64, 32);
    legRight.field_78809_i = true;
    setRotation(legRight, 0.0F, 0.0F, 0.0F);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    leaves.func_78785_a(f5);
    bodyTop.func_78785_a(f5);
    bodyBottom.func_78785_a(f5);
    legLeft.func_78785_a(f5);
    legRight.func_78785_a(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
  }
}
