package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
public class ModelStatueWolf extends ModelBase
{
  ModelRenderer WolfHead;
  ModelRenderer Body;
  ModelRenderer Mane;
  ModelRenderer Leg1;
  ModelRenderer Leg2;
  ModelRenderer Leg3;
  ModelRenderer Leg4;
  ModelRenderer Tail;
  ModelRenderer Ear1;
  ModelRenderer Ear2;
  ModelRenderer Nose;
  ModelRenderer Shape1;
  
  public ModelStatueWolf()
  {
    field_78090_t = 64;
    field_78089_u = 32;
    
    WolfHead = new ModelRenderer(this, 0, 0);
    WolfHead.func_78789_a(-3.0F, -3.0F, -2.0F, 6, 6, 4);
    WolfHead.func_78793_a(-0.5F, 13.5F, -7.0F);
    WolfHead.func_78787_b(64, 32);
    WolfHead.field_78809_i = true;
    setRotation(WolfHead, 0.0F, 0.0F, 0.0F);
    Body = new ModelRenderer(this, 18, 14);
    Body.func_78789_a(-4.0F, -2.0F, -3.0F, 6, 9, 6);
    Body.func_78793_a(0.5F, 14.0F, 2.0F);
    Body.func_78787_b(64, 32);
    Body.field_78809_i = true;
    setRotation(Body, 1.570796F, 0.0F, 0.0F);
    Mane = new ModelRenderer(this, 21, 0);
    Mane.func_78789_a(-4.0F, -3.0F, -3.0F, 8, 6, 7);
    Mane.func_78793_a(-0.5F, 14.0F, -3.0F);
    Mane.func_78787_b(64, 32);
    Mane.field_78809_i = true;
    setRotation(Mane, 1.570796F, 0.0F, 0.0F);
    Leg1 = new ModelRenderer(this, 0, 18);
    Leg1.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 7, 2);
    Leg1.func_78793_a(-2.0F, 16.0F, 7.0F);
    Leg1.func_78787_b(64, 32);
    Leg1.field_78809_i = true;
    setRotation(Leg1, 0.0F, 0.0F, 0.0F);
    Leg2 = new ModelRenderer(this, 0, 18);
    Leg2.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 7, 2);
    Leg2.func_78793_a(1.0F, 16.0F, 7.0F);
    Leg2.func_78787_b(64, 32);
    Leg2.field_78809_i = true;
    setRotation(Leg2, 0.0F, 0.0F, 0.0F);
    Leg3 = new ModelRenderer(this, 0, 18);
    Leg3.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 7, 2);
    Leg3.func_78793_a(-2.0F, 16.0F, -4.0F);
    Leg3.func_78787_b(64, 32);
    Leg3.field_78809_i = true;
    setRotation(Leg3, 0.0F, 0.0F, 0.0F);
    Leg4 = new ModelRenderer(this, 0, 18);
    Leg4.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 7, 2);
    Leg4.func_78793_a(1.0F, 16.0F, -4.0F);
    Leg4.func_78787_b(64, 32);
    Leg4.field_78809_i = true;
    setRotation(Leg4, 0.0F, 0.0F, 0.0F);
    Tail = new ModelRenderer(this, 9, 18);
    Tail.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 8, 2);
    Tail.func_78793_a(-0.5F, 12.0F, 8.0F);
    Tail.func_78787_b(64, 32);
    Tail.field_78809_i = true;
    setRotation(Tail, 1.130069F, 0.0F, 0.0F);
    Ear1 = new ModelRenderer(this, 16, 14);
    Ear1.func_78789_a(-3.0F, -5.0F, 0.0F, 2, 2, 1);
    Ear1.func_78793_a(-0.5F, 13.5F, -7.0F);
    Ear1.func_78787_b(64, 32);
    Ear1.field_78809_i = true;
    setRotation(Ear1, 0.0F, 0.0F, 0.0F);
    Ear2 = new ModelRenderer(this, 16, 14);
    Ear2.func_78789_a(1.0F, -5.0F, 0.0F, 2, 2, 1);
    Ear2.func_78793_a(-0.5F, 13.5F, -7.0F);
    Ear2.func_78787_b(64, 32);
    Ear2.field_78809_i = true;
    setRotation(Ear2, 0.0F, 0.0F, 0.0F);
    Nose = new ModelRenderer(this, 0, 10);
    Nose.func_78789_a(-2.0F, 0.0F, -5.0F, 3, 3, 4);
    Nose.func_78793_a(0.0F, 13.5F, -7.0F);
    Nose.func_78787_b(64, 32);
    Nose.field_78809_i = true;
    setRotation(Nose, 0.0F, 0.0F, 0.0F);
    
    Shape1 = new ModelRenderer(this, 22, 18);
    Shape1.func_78789_a(0.0F, 0.0F, 0.0F, 8, 1, 13);
    Shape1.func_78793_a(-4.5F, 23.0F, -5.0F);
    Shape1.func_78787_b(64, 32);
    Shape1.field_78809_i = true;
    setRotation(Shape1, 0.0F, 0.0F, 0.0F);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    WolfHead.func_78785_a(f5);
    Body.func_78785_a(f5);
    Mane.func_78785_a(f5);
    Leg1.func_78785_a(f5);
    Leg2.func_78785_a(f5);
    Leg3.func_78785_a(f5);
    Leg4.func_78785_a(f5);
    Tail.func_78785_a(f5);
    Ear1.func_78785_a(f5);
    Ear2.func_78785_a(f5);
    Nose.func_78785_a(f5);
    Shape1.func_78785_a(f5);
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
