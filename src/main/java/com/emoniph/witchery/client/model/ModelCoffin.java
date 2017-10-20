package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
public class ModelCoffin extends net.minecraft.client.model.ModelBase
{
  public ModelRenderer sideLeft;
  public ModelRenderer sideRight;
  public ModelRenderer sideEnd;
  public ModelRenderer base;
  public ModelRenderer baseLower;
  public ModelRenderer lid;
  public ModelRenderer lidMid;
  public ModelRenderer lidTop;
  
  public ModelCoffin()
  {
    field_78090_t = 128;
    field_78089_u = 64;
    
    lid = new ModelRenderer(this, 60, 0);
    lid.func_78793_a(-7.0F, -5.0F, 0.0F);
    lid.func_78790_a(-1.0F, 0.0F, -8.0F, 16, 1, 16, 0.0F);
    
    lidTop = new ModelRenderer(this, 67, 35);
    lidTop.func_78793_a(0.0F, 0.0F, 0.0F);
    lidTop.func_78790_a(1.0F, -2.0F, -8.0F, 12, 1, 14, 0.0F);
    
    lidMid = new ModelRenderer(this, 64, 18);
    lidMid.func_78793_a(0.0F, 0.0F, 0.0F);
    lidMid.func_78790_a(0.0F, -1.0F, -8.0F, 14, 1, 15, 0.0F);
    lid.func_78792_a(lidTop);
    lid.func_78792_a(lidMid);
    
    sideEnd = new ModelRenderer(this, 33, 51);
    sideEnd.func_78793_a(0.0F, -4.0F, 0.0F);
    sideEnd.func_78790_a(-6.0F, 0.0F, 6.0F, 12, 7, 1, 0.0F);
    
    sideRight = new ModelRenderer(this, 0, 37);
    sideRight.func_78793_a(0.0F, -4.0F, 0.0F);
    sideRight.func_78790_a(-7.0F, 0.0F, -8.0F, 1, 7, 15, 0.0F);
    
    sideLeft = new ModelRenderer(this, 0, 37);
    sideLeft.field_78809_i = true;
    sideLeft.func_78793_a(0.0F, -4.0F, 0.0F);
    sideLeft.func_78790_a(6.0F, 0.0F, -8.0F, 1, 7, 15, 0.0F);
    
    baseLower = new ModelRenderer(this, 0, 20);
    baseLower.func_78793_a(0.0F, 8.0F, 0.0F);
    baseLower.func_78790_a(-8.0F, 0.0F, -8.0F, 16, 1, 16, 0.0F);
    
    base = new ModelRenderer(this, 0, 0);
    base.func_78793_a(0.0F, 3.0F, 0.0F);
    base.func_78790_a(-7.0F, 0.0F, -8.0F, 14, 5, 15, 0.0F);
  }
  




  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    sideLeft.func_78785_a(f5);
    baseLower.func_78785_a(f5);
    sideEnd.func_78785_a(f5);
    base.func_78785_a(f5);
    sideRight.func_78785_a(f5);
    lid.func_78785_a(f5);
  }
  
  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
}
