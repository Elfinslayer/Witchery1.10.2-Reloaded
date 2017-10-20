package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelClothesDeath extends ModelBiped
{
  ModelRenderer head;
  ModelRenderer body;
  ModelRenderer rightarm;
  ModelRenderer leftarm;
  ModelRenderer rightleg;
  ModelRenderer leftleg;
  ModelRenderer cowl;
  ModelRenderer robe;
  ModelRenderer rightsleave;
  ModelRenderer leftsleave;
  
  public ModelClothesDeath(float scale)
  {
    super(0.0F, 0.0F, 128, 64);
    
    int off = 56;
    head = new ModelRenderer(this, 0 + off, 0);
    head.func_78790_a(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
    head.func_78793_a(0.0F, 0.0F, 0.0F);
    head.func_78787_b(128, 64);
    head.field_78809_i = true;
    setRotation(head, 0.0F, 0.0F, 0.0F);
    field_78116_c.func_78792_a(head);
    
    cowl = new ModelRenderer(this, 0, 45);
    cowl.func_78790_a(-4.5F, -8.5F, -4.5F, 9, 10, 9, 0.4F);
    cowl.func_78793_a(0.0F, 0.0F, 0.0F);
    cowl.func_78787_b(128, 64);
    cowl.field_78809_i = true;
    setRotation(cowl, 0.0F, 0.0F, 0.0F);
    field_78116_c.func_78792_a(cowl);
    
    body = new ModelRenderer(this, 16 + off, 16);
    body.func_78790_a(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
    body.func_78793_a(0.0F, 0.0F, 0.0F);
    body.func_78787_b(128, 64);
    body.field_78809_i = true;
    setRotation(body, 0.0F, 0.0F, 0.0F);
    field_78115_e.func_78792_a(body);
    
    rightarm = new ModelRenderer(this, 40 + off, 16);
    rightarm.func_78790_a(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.05F);
    rightarm.func_78787_b(128, 64);
    rightarm.field_78809_i = true;
    setRotation(rightarm, 0.0F, 0.0F, 0.0F);
    field_78112_f.func_78792_a(rightarm);
    
    leftarm = new ModelRenderer(this, 40 + off, 16);
    leftarm.func_78790_a(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.05F);
    leftarm.func_78787_b(128, 64);
    leftarm.field_78809_i = true;
    setRotation(leftarm, 0.0F, 0.0F, 0.0F);
    field_78113_g.func_78792_a(leftarm);
    
    rightleg = new ModelRenderer(this, 0 + off, 16);
    rightleg.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.05F);
    rightleg.func_78787_b(128, 64);
    rightleg.field_78809_i = true;
    setRotation(rightleg, 0.0F, 0.0F, 0.0F);
    field_78123_h.func_78792_a(rightleg);
    
    leftleg = new ModelRenderer(this, 0 + off, 16);
    leftleg.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.05F);
    leftleg.func_78787_b(128, 64);
    leftleg.field_78809_i = true;
    setRotation(leftleg, 0.0F, 0.0F, 0.0F);
    field_78124_i.func_78792_a(leftleg);
    
    robe = new ModelRenderer(this, 36, 37);
    robe.func_78790_a(-4.5F, 0.0F, -2.5F, 9, 22, 5, 0.1F);
    robe.func_78793_a(0.0F, 0.0F, 0.0F);
    robe.func_78787_b(128, 64);
    robe.field_78809_i = true;
    setRotation(robe, 0.0F, 0.0F, 0.0F);
    field_78115_e.func_78792_a(robe);
    
    rightsleave = new ModelRenderer(this, 64, 50);
    rightsleave.func_78790_a(-3.0F, -2.0F, -2.0F, 4, 10, 4, 0.1F);
    rightsleave.func_78787_b(128, 64);
    rightsleave.field_78809_i = true;
    setRotation(rightsleave, 0.0F, 0.0F, 0.0F);
    field_78112_f.func_78792_a(rightsleave);
    
    leftsleave = new ModelRenderer(this, 64, 50);
    leftsleave.func_78790_a(-1.0F, -2.0F, -2.0F, 4, 10, 4, 0.1F);
    leftsleave.func_78787_b(128, 64);
    leftsleave.field_78809_i = true;
    setRotation(leftsleave, 0.0F, 0.0F, 0.0F);
    field_78113_g.func_78792_a(leftsleave);
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
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
  }
}
