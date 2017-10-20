package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
public class ModelGoblinClothes extends ModelBiped
{
  ModelRenderer head;
  ModelRenderer body;
  ModelRenderer rightarm;
  ModelRenderer leftarm;
  ModelRenderer rightleg;
  ModelRenderer leftleg;
  ModelRenderer quiver;
  ModelRenderer arrow1;
  ModelRenderer feathers;
  ModelRenderer arrow2;
  
  public ModelGoblinClothes(float scale)
  {
    super(scale, 0.0F, 64, 32);
    
    field_78090_t = 64;
    field_78089_u = 32;
    
    quiver = new ModelRenderer(this, 33, 0);
    quiver.func_78789_a(-2.0F, -3.0F, 0.0F, 4, 7, 1);
    quiver.func_78793_a(0.0F, 7.0F, 2.0F);
    quiver.func_78787_b(64, 32);
    quiver.field_78809_i = true;
    setRotation(quiver, 0.0F, 0.0F, -0.3490659F);
    arrow1 = new ModelRenderer(this, 44, 4);
    arrow1.func_78789_a(0.5F, -5.0F, 0.0F, 1, 2, 1);
    arrow1.func_78793_a(0.0F, 7.0F, 2.0F);
    arrow1.func_78787_b(64, 32);
    arrow1.field_78809_i = true;
    setRotation(arrow1, 0.0F, 0.0F, -0.3490659F);
    feathers = new ModelRenderer(this, 44, 0);
    feathers.func_78789_a(-2.0F, -7.0F, 0.0F, 4, 2, 1);
    feathers.func_78793_a(0.0F, 7.0F, 2.0F);
    feathers.func_78787_b(64, 32);
    feathers.field_78809_i = true;
    setRotation(feathers, 0.0F, 0.0F, -0.3490659F);
    arrow2 = new ModelRenderer(this, 44, 4);
    arrow2.func_78789_a(-1.5F, -5.0F, 0.0F, 1, 2, 1);
    arrow2.func_78793_a(0.0F, 7.0F, 2.0F);
    arrow2.func_78787_b(64, 32);
    arrow2.field_78809_i = true;
    setRotation(arrow2, 0.0F, 0.0F, -0.3490659F);
    
    field_78115_e.func_78792_a(quiver);
    field_78115_e.func_78792_a(arrow1);
    field_78115_e.func_78792_a(arrow2);
    field_78115_e.func_78792_a(feathers);
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
