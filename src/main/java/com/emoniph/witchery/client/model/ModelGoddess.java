package com.emoniph.witchery.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class ModelGoddess extends ModelBase
{
  ModelRenderer head;
  ModelRenderer cleave;
  ModelRenderer body;
  ModelRenderer rightarm;
  ModelRenderer leftarm;
  ModelRenderer rightleg;
  ModelRenderer leftleg;
  ModelRenderer dressLower;
  ModelRenderer dressMiddle;
  
  public ModelGoddess()
  {
    field_78090_t = 64;
    field_78089_u = 64;
    
    head = new ModelRenderer(this, 0, 0);
    head.func_78789_a(-4.0F, -8.0F, -4.0F, 8, 8, 8);
    head.func_78793_a(0.0F, 0.0F, 0.0F);
    head.func_78787_b(64, 64);
    head.field_78809_i = true;
    setRotation(head, 0.0F, 0.0F, 0.0F);
    cleave = new ModelRenderer(this, 35, 0);
    cleave.func_78789_a(0.0F, -2.0F, -2.0F, 8, 4, 4);
    cleave.func_78793_a(-4.0F, 3.0F, -2.0F);
    cleave.func_78787_b(64, 64);
    cleave.field_78809_i = true;
    setRotation(cleave, -0.7853982F, 0.0F, 0.0F);
    body = new ModelRenderer(this, 16, 16);
    body.func_78789_a(-4.0F, 0.0F, -2.0F, 8, 12, 4);
    body.func_78793_a(0.0F, 0.0F, 0.0F);
    body.func_78787_b(64, 64);
    body.field_78809_i = true;
    setRotation(body, 0.0F, 0.0F, 0.0F);
    rightarm = new ModelRenderer(this, 40, 16);
    rightarm.func_78789_a(-3.0F, -2.0F, -2.0F, 4, 12, 4);
    rightarm.func_78793_a(-5.0F, 2.0F, 0.0F);
    rightarm.func_78787_b(64, 64);
    rightarm.field_78809_i = true;
    setRotation(rightarm, 0.0F, 0.0F, 0.0F);
    leftarm = new ModelRenderer(this, 40, 16);
    leftarm.func_78789_a(-1.0F, -2.0F, -2.0F, 4, 12, 4);
    leftarm.func_78793_a(5.0F, 2.0F, 0.0F);
    leftarm.func_78787_b(64, 64);
    leftarm.field_78809_i = true;
    setRotation(leftarm, 0.0F, 0.0F, 0.0F);
    rightleg = new ModelRenderer(this, 0, 16);
    rightleg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 12, 4);
    rightleg.func_78793_a(-2.0F, 12.0F, 0.0F);
    rightleg.func_78787_b(64, 64);
    rightleg.field_78809_i = true;
    setRotation(rightleg, 0.0F, 0.0F, 0.0F);
    leftleg = new ModelRenderer(this, 0, 16);
    leftleg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 12, 4);
    leftleg.func_78793_a(2.0F, 12.0F, 0.0F);
    leftleg.func_78787_b(64, 64);
    leftleg.field_78809_i = true;
    setRotation(leftleg, 0.0F, 0.0F, 0.0F);
    dressLower = new ModelRenderer(this, 0, 45);
    dressLower.func_78789_a(-6.0F, 0.0F, -4.0F, 12, 7, 8);
    dressLower.func_78793_a(0.0F, 17.0F, 0.0F);
    dressLower.func_78787_b(64, 64);
    dressLower.field_78809_i = true;
    setRotation(dressLower, 0.0F, 0.0F, 0.0F);
    dressMiddle = new ModelRenderer(this, 0, 33);
    dressMiddle.func_78789_a(-5.0F, 0.0F, -3.0F, 10, 5, 6);
    dressMiddle.func_78793_a(0.0F, 12.0F, 0.0F);
    dressMiddle.func_78787_b(64, 64);
    dressMiddle.field_78809_i = true;
    setRotation(dressMiddle, 0.0F, 0.0F, 0.0F);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    head.func_78785_a(f5);
    cleave.func_78785_a(f5);
    body.func_78785_a(f5);
    rightarm.func_78785_a(f5);
    leftarm.func_78785_a(f5);
    rightleg.func_78785_a(f5);
    leftleg.func_78785_a(f5);
    dressLower.func_78785_a(f5);
    dressMiddle.func_78785_a(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
    leftarm.field_78795_f = -0.6F;
    leftarm.field_78808_h = -0.1F;
    leftarm.field_78796_g = -0.6F;
    rightarm.field_78795_f = -0.6F;
    rightarm.field_78808_h = 0.1F;
    rightarm.field_78796_g = 0.6F;
  }
}
