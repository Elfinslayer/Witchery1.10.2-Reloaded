package com.emoniph.witchery.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class ModelPoppetChest extends ModelBase
{
  ModelRenderer table;
  
  public ModelPoppetChest()
  {
    field_78090_t = 64;
    field_78089_u = 64;
    
    table = new ModelRenderer(this, 0, 0);
    table.func_78789_a(0.0F, 0.0F, 0.0F, 16, 8, 16);
    table.func_78793_a(0.0F, 0.0F, 0.0F);
    table.func_78787_b(64, 64);
    table.field_78809_i = true;
    setRotation(table, 0.0F, 0.0F, 0.0F);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    table.func_78785_a(f5);
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
