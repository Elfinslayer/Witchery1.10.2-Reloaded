package com.emoniph.witchery.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class ModelTreefyd extends ModelBase
{
  ModelRenderer head;
  ModelRenderer body;
  ModelRenderer ShapeV;
  ModelRenderer leavesH;
  ModelRenderer base;
  ModelRenderer leg3;
  ModelRenderer leg4;
  ModelRenderer leg1;
  ModelRenderer leg2;
  
  public ModelTreefyd()
  {
    field_78090_t = 64;
    field_78089_u = 32;
    func_78085_a("head.face", 0, 24);
    func_78085_a("head.petals", 0, 0);
    func_78085_a("head.tongue", 25, 18);
    
    head = new ModelRenderer(this, "head");
    head.func_78793_a(0.0F, 3.0F, 0.0F);
    setRotation(head, 0.0F, 0.0F, 0.0F);
    head.field_78809_i = true;
    head.func_78786_a("face", -2.0F, -4.0F, -2.0F, 4, 4, 4);
    head.func_78786_a("petals", -5.0F, -7.0F, 0.0F, 10, 10, 0);
    head.func_78786_a("tongue", 0.0F, -3.0F, -6.0F, 0, 10, 4);
    body = new ModelRenderer(this, 16, 14);
    body.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 16, 2);
    body.func_78793_a(0.0F, 3.0F, 0.0F);
    body.func_78787_b(64, 32);
    body.field_78809_i = true;
    setRotation(body, 0.0F, 0.0F, 0.0F);
    ShapeV = new ModelRenderer(this, 40, 6);
    ShapeV.func_78789_a(0.0F, 0.0F, -6.0F, 0, 14, 12);
    ShapeV.func_78793_a(0.0F, 6.0F, 0.0F);
    ShapeV.func_78787_b(64, 32);
    ShapeV.field_78809_i = true;
    setRotation(ShapeV, 0.0F, 0.7853982F, 0.0F);
    leavesH = new ModelRenderer(this, 40, 0);
    leavesH.func_78789_a(-6.0F, 0.0F, 0.0F, 12, 14, 0);
    leavesH.func_78793_a(0.0F, 6.0F, 0.0F);
    leavesH.func_78787_b(64, 32);
    leavesH.field_78809_i = true;
    setRotation(leavesH, 0.0F, 0.7853982F, 0.0F);
    base = new ModelRenderer(this, 15, 6);
    base.func_78789_a(0.0F, 0.0F, 0.0F, 6, 1, 6);
    base.func_78793_a(-3.0F, 19.0F, -3.0F);
    base.func_78787_b(64, 32);
    base.field_78809_i = true;
    setRotation(base, 0.0F, 0.0F, 0.0F);
    leg3 = new ModelRenderer(this, 0, 16);
    leg3.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 4, 4);
    leg3.func_78793_a(-2.0F, 20.0F, -4.0F);
    leg3.func_78787_b(64, 32);
    leg3.field_78809_i = true;
    setRotation(leg3, 0.0F, 0.0F, 0.0F);
    leg4 = new ModelRenderer(this, 0, 16);
    leg4.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 4, 4);
    leg4.func_78793_a(2.0F, 20.0F, -4.0F);
    leg4.func_78787_b(64, 32);
    leg4.field_78809_i = true;
    setRotation(leg4, 0.0F, 0.0F, 0.0F);
    leg1 = new ModelRenderer(this, 0, 16);
    leg1.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 4, 4);
    leg1.func_78793_a(-2.0F, 20.0F, 4.0F);
    leg1.func_78787_b(64, 32);
    leg1.field_78809_i = true;
    setRotation(leg1, 0.0F, 0.0F, 0.0F);
    leg2 = new ModelRenderer(this, 0, 16);
    leg2.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 4, 4);
    leg2.func_78793_a(2.0F, 20.0F, 4.0F);
    leg2.func_78787_b(64, 32);
    leg2.field_78809_i = true;
    setRotation(leg2, 0.0F, 0.0F, 0.0F);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    head.func_78785_a(f5);
    body.func_78785_a(f5);
    ShapeV.func_78785_a(f5);
    leavesH.func_78785_a(f5);
    base.func_78785_a(f5);
    leg3.func_78785_a(f5);
    leg4.func_78785_a(f5);
    leg1.func_78785_a(f5);
    leg2.func_78785_a(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  

  public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
  {
    head.field_78796_g = (par4 / 57.295776F);
    head.field_78795_f = (par5 / 57.295776F);
    leg1.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2);
    leg2.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
    leg3.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
    leg4.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2);
  }
}
