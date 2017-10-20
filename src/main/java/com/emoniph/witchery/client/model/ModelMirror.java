package com.emoniph.witchery.client.model;

import net.minecraft.client.model.ModelRenderer;

public class ModelMirror extends net.minecraft.client.model.ModelBase
{
  public ModelRenderer backMiddle;
  public ModelRenderer backRight;
  public ModelRenderer backLeft;
  public ModelRenderer frameOuterRight;
  public ModelRenderer frameOuterCurveLowerRight;
  public ModelRenderer frameOuterCurveMidRight;
  public ModelRenderer frameOuterCurveUpperRight;
  public ModelRenderer frameOuterTop;
  public ModelRenderer frameOuterCurveUpperLeft;
  public ModelRenderer frameOuterCurveMidLeft;
  public ModelRenderer frameOuterCurveLowerLeft;
  public ModelRenderer frameOuterLeft;
  public ModelRenderer frameInnerRight;
  public ModelRenderer frameInnerCurveLowerRight;
  public ModelRenderer frameInnerCurveUpperRight;
  public ModelRenderer frameInnerTop;
  public ModelRenderer frameInnerCurveUpperRight_1;
  public ModelRenderer frameInnerCurveLowerLeft;
  public ModelRenderer frameInnerLeft;
  
  public ModelMirror()
  {
    field_78090_t = 32;
    field_78089_u = 32;
    frameInnerRight = new ModelRenderer(this, 5, 5);
    frameInnerRight.func_78793_a(0.0F, 0.0F, 0.0F);
    frameInnerRight.func_78790_a(-6.0F, -2.0F, -1.0F, 1, 10, 1, 0.0F);
    frameOuterCurveLowerLeft = new ModelRenderer(this, 5, 0);
    frameOuterCurveLowerLeft.field_78809_i = true;
    frameOuterCurveLowerLeft.func_78793_a(0.0F, 0.0F, 0.0F);
    frameOuterCurveLowerLeft.func_78790_a(7.0F, -5.0F, -1.0F, 1, 2, 1, 0.0F);
    frameOuterLeft = new ModelRenderer(this, 0, 0);
    frameOuterLeft.field_78809_i = true;
    frameOuterLeft.func_78793_a(0.0F, 0.0F, 0.0F);
    frameOuterLeft.func_78790_a(6.5F, -3.0F, -1.0F, 1, 11, 1, 0.0F);
    backRight = new ModelRenderer(this, 0, 17);
    backRight.func_78793_a(0.0F, 0.0F, 1.0F);
    backRight.func_78790_a(-7.0F, -6.0F, -1.0F, 2, 14, 1, 0.0F);
    backLeft = new ModelRenderer(this, 0, 17);
    backLeft.field_78809_i = true;
    backLeft.func_78793_a(0.0F, 0.0F, 1.0F);
    backLeft.func_78790_a(5.0F, -6.0F, -1.0F, 2, 14, 1, 0.0F);
    frameOuterTop = new ModelRenderer(this, 4, 3);
    frameOuterTop.func_78793_a(0.0F, 0.0F, 0.0F);
    frameOuterTop.func_78790_a(-5.0F, -7.5F, -1.0F, 10, 1, 1, 0.0F);
    frameInnerLeft = new ModelRenderer(this, 5, 5);
    frameInnerLeft.field_78809_i = true;
    frameInnerLeft.func_78793_a(0.0F, 0.0F, 0.0F);
    frameInnerLeft.func_78790_a(5.0F, -2.0F, -1.0F, 1, 10, 1, 0.0F);
    frameInnerCurveLowerLeft = new ModelRenderer(this, 10, 6);
    frameInnerCurveLowerLeft.field_78809_i = true;
    frameInnerCurveLowerLeft.func_78793_a(0.0F, 0.0F, 0.0F);
    frameInnerCurveLowerLeft.func_78790_a(4.0F, -3.0F, -1.0F, 1, 2, 1, 0.0F);
    frameInnerCurveLowerRight = new ModelRenderer(this, 10, 6);
    frameInnerCurveLowerRight.func_78793_a(0.0F, 0.0F, 0.0F);
    frameInnerCurveLowerRight.func_78790_a(-5.0F, -3.0F, -1.0F, 1, 2, 1, 0.0F);
    frameOuterRight = new ModelRenderer(this, 0, 0);
    frameOuterRight.func_78793_a(0.0F, 0.0F, 0.0F);
    frameOuterRight.func_78790_a(-7.5F, -3.0F, -1.0F, 1, 11, 1, 0.0F);
    frameOuterCurveUpperLeft = new ModelRenderer(this, 17, 0);
    frameOuterCurveUpperLeft.field_78809_i = true;
    frameOuterCurveUpperLeft.func_78793_a(0.0F, 0.0F, 0.0F);
    frameOuterCurveUpperLeft.func_78790_a(5.0F, -7.0F, -1.0F, 1, 1, 1, 0.0F);
    frameInnerCurveUpperRight_1 = new ModelRenderer(this, 15, 6);
    frameInnerCurveUpperRight_1.field_78809_i = true;
    frameInnerCurveUpperRight_1.func_78793_a(0.0F, 0.0F, 0.0F);
    frameInnerCurveUpperRight_1.func_78790_a(3.0F, -4.0F, -1.0F, 1, 2, 1, 0.0F);
    frameOuterCurveLowerRight = new ModelRenderer(this, 5, 0);
    frameOuterCurveLowerRight.func_78793_a(0.0F, 0.0F, 0.0F);
    frameOuterCurveLowerRight.func_78790_a(-8.0F, -5.0F, -1.0F, 1, 2, 1, 0.0F);
    frameInnerTop = new ModelRenderer(this, 10, 10);
    frameInnerTop.func_78793_a(0.0F, 0.0F, 0.0F);
    frameInnerTop.func_78790_a(-3.0F, -4.0F, -1.0F, 6, 1, 1, 0.0F);
    frameInnerCurveUpperRight = new ModelRenderer(this, 15, 6);
    frameInnerCurveUpperRight.func_78793_a(0.0F, 0.0F, 0.0F);
    frameInnerCurveUpperRight.func_78790_a(-4.0F, -4.0F, -1.0F, 1, 2, 1, 0.0F);
    frameOuterCurveMidRight = new ModelRenderer(this, 10, 0);
    frameOuterCurveMidRight.func_78793_a(0.0F, 0.0F, 0.0F);
    frameOuterCurveMidRight.func_78790_a(-7.5F, -6.0F, -1.0F, 2, 1, 1, 0.0F);
    frameOuterCurveUpperRight = new ModelRenderer(this, 17, 0);
    frameOuterCurveUpperRight.func_78793_a(0.0F, 0.0F, 0.0F);
    frameOuterCurveUpperRight.func_78790_a(-6.0F, -7.0F, -1.0F, 1, 1, 1, 0.0F);
    frameOuterCurveMidLeft = new ModelRenderer(this, 10, 0);
    frameOuterCurveMidLeft.field_78809_i = true;
    frameOuterCurveMidLeft.func_78793_a(0.0F, 0.0F, 0.0F);
    frameOuterCurveMidLeft.func_78790_a(5.5F, -6.0F, -1.0F, 2, 1, 1, 0.0F);
    backMiddle = new ModelRenderer(this, 7, 16);
    backMiddle.func_78793_a(0.0F, 0.0F, 1.0F);
    backMiddle.func_78790_a(-5.0F, -7.0F, -1.0F, 10, 15, 1, 0.0F);
  }
  
  private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78088_a(net.minecraft.entity.Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    frameInnerRight.func_78785_a(f5);
    frameOuterCurveLowerLeft.func_78785_a(f5);
    frameOuterLeft.func_78785_a(f5);
    backRight.func_78785_a(f5);
    backLeft.func_78785_a(f5);
    frameOuterTop.func_78785_a(f5);
    frameInnerLeft.func_78785_a(f5);
    frameInnerCurveLowerLeft.func_78785_a(f5);
    frameInnerCurveLowerRight.func_78785_a(f5);
    frameOuterRight.func_78785_a(f5);
    frameOuterCurveUpperLeft.func_78785_a(f5);
    frameInnerCurveUpperRight_1.func_78785_a(f5);
    frameOuterCurveLowerRight.func_78785_a(f5);
    frameInnerTop.func_78785_a(f5);
    frameInnerCurveUpperRight.func_78785_a(f5);
    frameOuterCurveMidRight.func_78785_a(f5);
    frameOuterCurveUpperRight.func_78785_a(f5);
    frameOuterCurveMidLeft.func_78785_a(f5);
    backMiddle.func_78785_a(f5);
  }
}
