package com.emoniph.witchery.client.model;

import net.minecraft.client.model.ModelRenderer;

public class ModelGrassper extends net.minecraft.client.model.ModelBase
{
  ModelRenderer stalkTop;
  ModelRenderer leafRight;
  ModelRenderer leafFront;
  ModelRenderer leafback;
  ModelRenderer leafLeft;
  ModelRenderer petalBackRight;
  ModelRenderer stalkBottom;
  ModelRenderer petalFrontRight;
  ModelRenderer petalBackLeft;
  ModelRenderer petalFrontLeft;
  
  public ModelGrassper()
  {
    field_78090_t = 64;
    field_78089_u = 64;
    
    stalkTop = new ModelRenderer(this, 0, 4);
    stalkTop.func_78789_a(-1.0F, -4.0F, -1.0F, 2, 4, 2);
    stalkTop.func_78793_a(2.0F, 21.0F, 0.0F);
    stalkTop.func_78787_b(64, 64);
    stalkTop.field_78809_i = true;
    setRotation(stalkTop, 0.0F, 0.0F, -0.5235988F);
    leafRight = new ModelRenderer(this, 0, 8);
    leafRight.func_78789_a(0.0F, 0.0F, -4.0F, 8, 0, 8);
    leafRight.func_78793_a(0.0F, 24.0F, 0.0F);
    leafRight.func_78787_b(64, 64);
    leafRight.field_78809_i = true;
    
    leafFront = new ModelRenderer(this, 0, 0);
    leafFront.func_78789_a(-4.0F, 0.0F, -8.0F, 8, 0, 8);
    leafFront.func_78793_a(0.0F, 24.0F, 0.0F);
    leafFront.func_78787_b(64, 64);
    leafFront.field_78809_i = true;
    
    leafback = new ModelRenderer(this, 0, 0);
    leafback.func_78789_a(-4.0F, 0.0F, -8.0F, 8, 0, 8);
    leafback.func_78793_a(0.0F, 24.0F, 0.0F);
    leafback.func_78787_b(64, 64);
    leafback.field_78809_i = true;
    
    leafLeft = new ModelRenderer(this, 0, 8);
    leafLeft.func_78789_a(0.0F, 0.0F, -4.0F, 8, 0, 8);
    leafLeft.func_78793_a(0.0F, 24.0F, 0.0F);
    leafLeft.func_78787_b(64, 64);
    leafLeft.field_78809_i = true;
    
    petalBackRight = new ModelRenderer(this, 0, 0);
    petalBackRight.func_78789_a(-1.0F, -2.0F, 0.0F, 1, 2, 1);
    petalBackRight.func_78793_a(0.0F, 18.0F, 0.0F);
    petalBackRight.func_78787_b(64, 64);
    petalBackRight.field_78809_i = true;
    setRotation(petalBackRight, -0.5235988F, 0.0F, -0.7853982F);
    stalkBottom = new ModelRenderer(this, 0, 10);
    stalkBottom.func_78789_a(-1.0F, -4.0F, -1.0F, 2, 4, 2);
    stalkBottom.func_78793_a(0.0F, 24.0F, 0.0F);
    stalkBottom.func_78787_b(64, 64);
    stalkBottom.field_78809_i = true;
    setRotation(stalkBottom, 0.0F, 0.0F, 0.5235988F);
    petalFrontRight = new ModelRenderer(this, 0, 0);
    petalFrontRight.func_78789_a(-1.0F, -2.0F, -1.0F, 1, 2, 1);
    petalFrontRight.func_78793_a(0.0F, 18.0F, 0.0F);
    petalFrontRight.func_78787_b(64, 64);
    petalFrontRight.field_78809_i = true;
    setRotation(petalFrontRight, 0.5235988F, 0.0F, -0.7853982F);
    petalBackLeft = new ModelRenderer(this, 0, 0);
    petalBackLeft.func_78789_a(0.0F, -2.0F, 0.0F, 1, 2, 1);
    petalBackLeft.func_78793_a(0.0F, 18.0F, 0.0F);
    petalBackLeft.func_78787_b(64, 64);
    petalBackLeft.field_78809_i = true;
    setRotation(petalBackLeft, -0.3490659F, 0.0F, 0.2617994F);
    petalFrontLeft = new ModelRenderer(this, 0, 0);
    petalFrontLeft.func_78789_a(0.0F, -2.0F, -1.0F, 1, 2, 1);
    petalFrontLeft.func_78793_a(0.0F, 18.0F, 0.0F);
    petalFrontLeft.func_78787_b(64, 64);
    petalFrontLeft.field_78809_i = true;
    setRotation(petalFrontLeft, 0.3490659F, 0.0F, 0.2617994F);
    
    setRotation(leafRight, 0.0F, 3.141593F, 0.5235988F);
    setRotation(leafLeft, 0.0F, 0.0F, -0.5235988F);
    setRotation(leafFront, -0.5235988F, 0.0F, 0.0F);
    setRotation(leafback, -0.5235988F, -3.141593F, 0.0F);
  }
  
  public void func_78088_a(net.minecraft.entity.Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    stalkTop.func_78785_a(f5);
    leafRight.func_78785_a(f5);
    leafFront.func_78785_a(f5);
    leafback.func_78785_a(f5);
    leafLeft.func_78785_a(f5);
    petalBackRight.func_78785_a(f5);
    stalkBottom.func_78785_a(f5);
    petalFrontRight.func_78785_a(f5);
    petalBackLeft.func_78785_a(f5);
    petalFrontLeft.func_78785_a(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, net.minecraft.entity.Entity entity) {}
}
