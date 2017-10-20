package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
public class ModelVillagerBed extends net.minecraft.client.model.ModelBase
{
  public ModelRenderer base;
  public ModelRenderer head;
  public ModelRenderer legFL;
  public ModelRenderer legFR;
  public ModelRenderer legBL;
  public ModelRenderer legBR;
  
  public ModelVillagerBed()
  {
    field_78090_t = 128;
    field_78089_u = 32;
    legBR = new ModelRenderer(this, 0, 0);
    legBR.func_78793_a(0.0F, 0.0F, 0.0F);
    legBR.func_78790_a(-5.0F, 1.0F, 14.0F, 1, 3, 1, 0.0F);
    base = new ModelRenderer(this, 0, 0);
    base.func_78793_a(0.0F, 0.0F, 0.0F);
    base.func_78790_a(-5.0F, 0.0F, -15.0F, 10, 1, 30, 0.0F);
    head = new ModelRenderer(this, 0, 4);
    head.func_78793_a(0.0F, 0.0F, 0.0F);
    head.func_78790_a(-5.0F, -4.0F, 14.0F, 10, 4, 1, 0.0F);
    legFL = new ModelRenderer(this, 0, 0);
    legFL.func_78793_a(0.0F, 0.0F, 0.0F);
    legFL.func_78790_a(4.0F, 1.0F, -15.0F, 1, 3, 1, 0.0F);
    legBL = new ModelRenderer(this, 0, 0);
    legBL.func_78793_a(0.0F, 0.0F, 0.0F);
    legBL.func_78790_a(4.0F, 1.0F, 14.0F, 1, 3, 1, 0.0F);
    legFR = new ModelRenderer(this, 0, 0);
    legFR.func_78793_a(0.0F, 0.0F, 0.0F);
    legFR.func_78790_a(-5.0F, 1.0F, -15.0F, 1, 3, 1, 0.0F);
    base.func_78792_a(legBR);
    base.func_78792_a(head);
    base.func_78792_a(legFL);
    base.func_78792_a(legBL);
    base.func_78792_a(legFR);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    base.func_78785_a(f5);
  }
  
  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
}
