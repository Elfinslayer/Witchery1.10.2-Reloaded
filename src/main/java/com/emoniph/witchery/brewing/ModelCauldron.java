package com.emoniph.witchery.brewing;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class ModelCauldron extends ModelBase
{
  ModelRenderer base;
  ModelRenderer bottomF;
  ModelRenderer bottomB;
  ModelRenderer bottomL;
  ModelRenderer bottomR;
  ModelRenderer sideF;
  ModelRenderer sideB;
  ModelRenderer sideL;
  ModelRenderer sideR;
  ModelRenderer neckF;
  ModelRenderer neckB;
  ModelRenderer neckL;
  ModelRenderer neckR;
  ModelRenderer lipF;
  ModelRenderer lipB;
  ModelRenderer lipL;
  ModelRenderer lipR;
  ModelRenderer legFL;
  ModelRenderer legFR;
  ModelRenderer legBL;
  ModelRenderer legBR;
  
  public ModelCauldron()
  {
    field_78090_t = 64;
    field_78089_u = 64;
    
    base = new ModelRenderer(this, 0, 53);
    base.func_78789_a(-5.0F, 5.0F, -5.0F, 10, 1, 10);
    base.func_78793_a(0.0F, 16.0F, 0.0F);
    base.func_78787_b(64, 64);
    base.field_78809_i = true;
    setRotation(base, 0.0F, 0.0F, 0.0F);
    bottomF = new ModelRenderer(this, 0, 50);
    bottomF.func_78789_a(-5.0F, 4.0F, -6.0F, 10, 1, 1);
    bottomF.func_78793_a(0.0F, 16.0F, 0.0F);
    bottomF.func_78787_b(64, 64);
    bottomF.field_78809_i = true;
    setRotation(bottomF, 0.0F, 0.0F, 0.0F);
    bottomB = new ModelRenderer(this, 0, 50);
    bottomB.func_78789_a(-5.0F, 4.0F, 5.0F, 10, 1, 1);
    bottomB.func_78793_a(0.0F, 16.0F, 0.0F);
    bottomB.func_78787_b(64, 64);
    bottomB.field_78809_i = true;
    setRotation(bottomB, 0.0F, 0.0F, 0.0F);
    bottomL = new ModelRenderer(this, 0, 36);
    bottomL.func_78789_a(5.0F, 4.0F, -6.0F, 1, 1, 12);
    bottomL.func_78793_a(0.0F, 16.0F, 0.0F);
    bottomL.func_78787_b(64, 64);
    bottomL.field_78809_i = true;
    setRotation(bottomL, 0.0F, 0.0F, 0.0F);
    bottomR = new ModelRenderer(this, 0, 36);
    bottomR.func_78789_a(-6.0F, 4.0F, -6.0F, 1, 1, 12);
    bottomR.func_78793_a(0.0F, 16.0F, 0.0F);
    bottomR.func_78787_b(64, 64);
    bottomR.field_78809_i = true;
    setRotation(bottomR, 0.0F, 0.0F, 0.0F);
    sideF = new ModelRenderer(this, 27, 45);
    sideF.func_78789_a(-6.0F, -2.0F, -7.0F, 12, 6, 1);
    sideF.func_78793_a(0.0F, 16.0F, 0.0F);
    sideF.func_78787_b(64, 64);
    sideF.field_78809_i = true;
    setRotation(sideF, 0.0F, 0.0F, 0.0F);
    sideB = new ModelRenderer(this, 27, 45);
    sideB.func_78789_a(-6.0F, -2.0F, 6.0F, 12, 6, 1);
    sideB.func_78793_a(0.0F, 16.0F, 0.0F);
    sideB.func_78787_b(64, 64);
    sideB.field_78809_i = true;
    setRotation(sideB, 0.0F, 0.0F, 0.0F);
    sideL = new ModelRenderer(this, 27, 24);
    sideL.func_78789_a(6.0F, -2.0F, -7.0F, 1, 6, 14);
    sideL.func_78793_a(0.0F, 16.0F, 0.0F);
    sideL.func_78787_b(64, 64);
    sideL.field_78809_i = true;
    setRotation(sideL, 0.0F, 0.0F, 0.0F);
    sideR = new ModelRenderer(this, 27, 24);
    sideR.func_78789_a(-7.0F, -2.0F, -7.0F, 1, 6, 14);
    sideR.func_78793_a(0.0F, 16.0F, 0.0F);
    sideR.func_78787_b(64, 64);
    sideR.field_78809_i = true;
    setRotation(sideR, 0.0F, 0.0F, 0.0F);
    neckF = new ModelRenderer(this, 0, 32);
    neckF.func_78789_a(-5.0F, -4.0F, -6.0F, 10, 2, 1);
    neckF.func_78793_a(0.0F, 16.0F, 0.0F);
    neckF.func_78787_b(64, 64);
    neckF.field_78809_i = true;
    setRotation(neckF, 0.0F, 0.0F, 0.0F);
    neckB = new ModelRenderer(this, 0, 32);
    neckB.func_78789_a(-5.0F, -4.0F, 5.0F, 10, 2, 1);
    neckB.func_78793_a(0.0F, 16.0F, 0.0F);
    neckB.func_78787_b(64, 64);
    neckB.field_78809_i = true;
    setRotation(neckB, 0.0F, 0.0F, 0.0F);
    neckL = new ModelRenderer(this, 0, 17);
    neckL.func_78789_a(5.0F, -4.0F, -6.0F, 1, 2, 12);
    neckL.func_78793_a(0.0F, 16.0F, 0.0F);
    neckL.func_78787_b(64, 64);
    neckL.field_78809_i = true;
    setRotation(neckL, 0.0F, 0.0F, 0.0F);
    neckR = new ModelRenderer(this, 0, 17);
    neckR.func_78789_a(-6.0F, -4.0F, -6.0F, 1, 2, 12);
    neckR.func_78793_a(0.0F, 16.0F, 0.0F);
    neckR.func_78787_b(64, 64);
    neckR.field_78809_i = true;
    setRotation(neckR, 0.0F, 0.0F, 0.0F);
    lipF = new ModelRenderer(this, 27, 21);
    lipF.func_78789_a(-6.0F, -5.0F, -7.0F, 12, 1, 1);
    lipF.func_78793_a(0.0F, 16.0F, 0.0F);
    lipF.func_78787_b(64, 64);
    lipF.field_78809_i = true;
    setRotation(lipF, 0.0F, 0.0F, 0.0F);
    lipB = new ModelRenderer(this, 27, 21);
    lipB.func_78789_a(-6.0F, -5.0F, 6.0F, 12, 1, 1);
    lipB.func_78793_a(0.0F, 16.0F, 0.0F);
    lipB.func_78787_b(64, 64);
    lipB.field_78809_i = true;
    setRotation(lipB, 0.0F, 0.0F, 0.0F);
    lipL = new ModelRenderer(this, 27, 5);
    lipL.func_78789_a(6.0F, -5.0F, -7.0F, 1, 1, 14);
    lipL.func_78793_a(0.0F, 16.0F, 0.0F);
    lipL.func_78787_b(64, 64);
    lipL.field_78809_i = true;
    setRotation(lipL, 0.0F, 0.0F, 0.0F);
    lipR = new ModelRenderer(this, 27, 5);
    lipR.func_78789_a(-7.0F, -5.0F, -7.0F, 1, 1, 14);
    lipR.func_78793_a(0.0F, 16.0F, 0.0F);
    lipR.func_78787_b(64, 64);
    lipR.field_78809_i = true;
    setRotation(lipR, 0.0F, 0.0F, 0.0F);
    legFL = new ModelRenderer(this, 0, 0);
    legFL.func_78789_a(1.5F, 7.5F, -1.5F, 1, 3, 1);
    legFL.func_78793_a(0.0F, 16.0F, 0.0F);
    legFL.func_78787_b(64, 64);
    legFL.field_78809_i = true;
    setRotation(legFL, -0.3490659F, 0.0F, -0.3490659F);
    legFR = new ModelRenderer(this, 0, 0);
    legFR.func_78789_a(-2.5F, 7.5F, -1.5F, 1, 3, 1);
    legFR.func_78793_a(0.0F, 16.0F, 0.0F);
    legFR.func_78787_b(64, 64);
    legFR.field_78809_i = true;
    setRotation(legFR, -0.3490659F, 0.0F, 0.3490659F);
    legBL = new ModelRenderer(this, 0, 0);
    legBL.func_78789_a(1.5F, 7.5F, 0.5F, 1, 3, 1);
    legBL.func_78793_a(0.0F, 16.0F, 0.0F);
    legBL.func_78787_b(64, 64);
    legBL.field_78809_i = true;
    setRotation(legBL, 0.3490659F, 0.0F, -0.3490659F);
    legBR = new ModelRenderer(this, 0, 0);
    legBR.func_78789_a(-2.5F, 7.5F, 0.5F, 1, 3, 1);
    legBR.func_78793_a(0.0F, 16.0F, 0.0F);
    legBR.func_78787_b(64, 64);
    legBR.field_78809_i = true;
    setRotation(legBR, 0.3490659F, 0.0F, 0.3490659F);
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
    base.func_78785_a(f5);
    bottomF.func_78785_a(f5);
    bottomB.func_78785_a(f5);
    bottomL.func_78785_a(f5);
    bottomR.func_78785_a(f5);
    sideF.func_78785_a(f5);
    sideB.func_78785_a(f5);
    sideL.func_78785_a(f5);
    sideR.func_78785_a(f5);
    neckF.func_78785_a(f5);
    neckB.func_78785_a(f5);
    neckL.func_78785_a(f5);
    neckR.func_78785_a(f5);
    lipF.func_78785_a(f5);
    lipB.func_78785_a(f5);
    lipL.func_78785_a(f5);
    lipR.func_78785_a(f5);
    legFL.func_78785_a(f5);
    legFR.func_78785_a(f5);
    legBL.func_78785_a(f5);
    legBR.func_78785_a(f5);
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
  }
}
