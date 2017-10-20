package com.emoniph.witchery.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class ModelCandelabra extends ModelBase
{
  ModelRenderer candleLeft;
  ModelRenderer candleRight;
  ModelRenderer candleFront;
  ModelRenderer candleBack;
  ModelRenderer candleMiddle;
  ModelRenderer supportLR;
  ModelRenderer supportFB;
  ModelRenderer sconceLeft;
  ModelRenderer sconceRight;
  ModelRenderer sconceFront;
  ModelRenderer sconceBack;
  ModelRenderer sconceMiddle;
  ModelRenderer baseTop;
  ModelRenderer baseBottom;
  
  public ModelCandelabra()
  {
    field_78090_t = 32;
    field_78089_u = 32;
    
    candleLeft = new ModelRenderer(this, 0, 0);
    candleLeft.func_78789_a(0.0F, 0.0F, 0.0F, 2, 8, 2);
    candleLeft.func_78793_a(-6.0F, 11.0F, -1.0F);
    candleLeft.func_78787_b(32, 32);
    candleLeft.field_78809_i = true;
    setRotation(candleLeft, 0.0F, 0.0F, 0.0F);
    candleRight = new ModelRenderer(this, 0, 0);
    candleRight.func_78789_a(0.0F, 0.0F, 0.0F, 2, 8, 2);
    candleRight.func_78793_a(4.0F, 11.0F, -1.0F);
    candleRight.func_78787_b(32, 32);
    candleRight.field_78809_i = true;
    setRotation(candleRight, 0.0F, 0.0F, 0.0F);
    candleFront = new ModelRenderer(this, 0, 0);
    candleFront.func_78789_a(0.0F, 0.0F, 0.0F, 2, 8, 2);
    candleFront.func_78793_a(-1.0F, 11.0F, -6.0F);
    candleFront.func_78787_b(32, 32);
    candleFront.field_78809_i = true;
    setRotation(candleFront, 0.0F, 0.0F, 0.0F);
    candleBack = new ModelRenderer(this, 0, 0);
    candleBack.func_78789_a(0.0F, 0.0F, 0.0F, 2, 8, 2);
    candleBack.func_78793_a(-1.0F, 11.0F, 4.0F);
    candleBack.func_78787_b(32, 32);
    candleBack.field_78809_i = true;
    setRotation(candleBack, 0.0F, 0.0F, 0.0F);
    candleMiddle = new ModelRenderer(this, 0, 0);
    candleMiddle.func_78789_a(0.0F, 0.0F, 0.0F, 2, 13, 2);
    candleMiddle.func_78793_a(-1.0F, 9.0F, -1.0F);
    candleMiddle.func_78787_b(32, 32);
    candleMiddle.field_78809_i = true;
    setRotation(candleMiddle, 0.0F, 0.0F, 0.0F);
    supportLR = new ModelRenderer(this, 0, 17);
    supportLR.func_78789_a(0.0F, 0.0F, 0.0F, 12, 1, 2);
    supportLR.func_78793_a(-6.0F, 19.0F, -1.0F);
    supportLR.func_78787_b(32, 32);
    supportLR.field_78809_i = true;
    setRotation(supportLR, 0.0F, 0.0F, 0.0F);
    supportFB = new ModelRenderer(this, 0, 4);
    supportFB.func_78789_a(0.0F, 0.0F, 0.0F, 2, 1, 12);
    supportFB.func_78793_a(-1.0F, 19.0F, -6.0F);
    supportFB.func_78787_b(32, 32);
    supportFB.field_78809_i = true;
    setRotation(supportFB, 0.0F, 0.0F, 0.0F);
    sconceLeft = new ModelRenderer(this, 0, 20);
    sconceLeft.func_78789_a(0.0F, 0.0F, 0.0F, 3, 1, 3);
    sconceLeft.func_78793_a(-6.5F, 17.0F, -1.5F);
    sconceLeft.func_78787_b(32, 32);
    sconceLeft.field_78809_i = true;
    setRotation(sconceLeft, 0.0F, 0.0F, 0.0F);
    sconceRight = new ModelRenderer(this, 0, 20);
    sconceRight.func_78789_a(0.0F, 0.0F, 0.0F, 3, 1, 3);
    sconceRight.func_78793_a(3.5F, 17.0F, -1.5F);
    sconceRight.func_78787_b(32, 32);
    sconceRight.field_78809_i = true;
    setRotation(sconceRight, 0.0F, 0.0F, 0.0F);
    sconceFront = new ModelRenderer(this, 0, 20);
    sconceFront.func_78789_a(0.0F, 0.0F, 0.0F, 3, 1, 3);
    sconceFront.func_78793_a(-1.5F, 17.0F, -6.5F);
    sconceFront.func_78787_b(32, 32);
    sconceFront.field_78809_i = true;
    setRotation(sconceFront, 0.0F, 0.0F, 0.0F);
    sconceBack = new ModelRenderer(this, 0, 20);
    sconceBack.func_78789_a(0.0F, 0.0F, 0.0F, 3, 1, 3);
    sconceBack.func_78793_a(-1.5F, 17.0F, 3.5F);
    sconceBack.func_78787_b(32, 32);
    sconceBack.field_78809_i = true;
    setRotation(sconceBack, 0.0F, 0.0F, 0.0F);
    sconceMiddle = new ModelRenderer(this, 0, 20);
    sconceMiddle.func_78789_a(0.0F, 0.0F, 0.0F, 3, 1, 3);
    sconceMiddle.func_78793_a(-1.5F, 15.0F, -1.5F);
    sconceMiddle.func_78787_b(32, 32);
    sconceMiddle.field_78809_i = true;
    setRotation(sconceMiddle, 0.0F, 0.0F, 0.0F);
    baseTop = new ModelRenderer(this, 12, 20);
    baseTop.func_78789_a(0.0F, 0.0F, 0.0F, 3, 1, 3);
    baseTop.func_78793_a(-1.5F, 22.0F, -1.5F);
    baseTop.func_78787_b(32, 32);
    baseTop.field_78809_i = true;
    setRotation(baseTop, 0.0F, 0.0F, 0.0F);
    baseBottom = new ModelRenderer(this, 8, 24);
    baseBottom.func_78789_a(-2.5F, 0.0F, -2.5F, 5, 1, 5);
    baseBottom.func_78793_a(0.0F, 23.0F, 0.0F);
    baseBottom.func_78787_b(32, 32);
    baseBottom.field_78809_i = true;
    setRotation(baseBottom, 0.0F, 0.0F, 0.0F);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    candleLeft.func_78785_a(f5);
    candleRight.func_78785_a(f5);
    candleFront.func_78785_a(f5);
    candleBack.func_78785_a(f5);
    candleMiddle.func_78785_a(f5);
    supportLR.func_78785_a(f5);
    supportFB.func_78785_a(f5);
    sconceLeft.func_78785_a(f5);
    sconceRight.func_78785_a(f5);
    sconceFront.func_78785_a(f5);
    sconceBack.func_78785_a(f5);
    sconceMiddle.func_78785_a(f5);
    baseTop.func_78785_a(f5);
    baseBottom.func_78785_a(f5);
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
