package com.emoniph.witchery.client.model;

import com.emoniph.witchery.blocks.BlockChalice.TileEntityChalice;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelChalice extends ModelBase
{
  ModelRenderer chalice;
  ModelRenderer liquid;
  
  public ModelChalice()
  {
    field_78090_t = 32;
    field_78089_u = 32;
    func_78085_a("chalice.sideRight", 0, -5);
    func_78085_a("chalice.sideLeft", 0, -5);
    func_78085_a("chalice.sideBack", 0, 0);
    func_78085_a("chalice.sideFront", 0, 0);
    func_78085_a("chalice.sideBottom", -5, 4);
    func_78085_a("chalice.neck", 4, 10);
    func_78085_a("chalice.base", 0, 13);
    
    chalice = new ModelRenderer(this, "chalice");
    chalice.func_78793_a(-1.0F, 23.0F, -1.0F);
    setRotation(chalice, 0.0F, 0.0F, 0.0F);
    chalice.field_78809_i = true;
    chalice.func_78786_a("sideRight", 4.0F, -6.0F, -1.0F, 0, 4, 5);
    chalice.func_78786_a("sideLeft", -1.0F, -6.0F, -1.0F, 0, 4, 5);
    chalice.func_78786_a("sideBack", -1.0F, -6.0F, 4.0F, 5, 4, 0);
    chalice.func_78786_a("sideFront", -1.0F, -6.0F, -1.0F, 5, 4, 0);
    chalice.func_78786_a("sideBottom", -1.0F, -2.0F, -1.0F, 5, 0, 5);
    chalice.func_78786_a("neck", 1.0F, -2.0F, 1.0F, 1, 2, 1);
    chalice.func_78786_a("base", 0.0F, 0.0F, 0.0F, 3, 1, 3);
    liquid = new ModelRenderer(this, -4, 18);
    liquid.func_78789_a(0.0F, 0.0F, 0.0F, 5, 0, 5);
    liquid.func_78793_a(-2.0F, 19.0F, -2.0F);
    liquid.func_78787_b(32, 32);
    liquid.field_78809_i = true;
    setRotation(liquid, 0.0F, 0.0F, 0.0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockChalice.TileEntityChalice tileEntityChalice) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    chalice.func_78785_a(f5);
    if ((tileEntityChalice != null) && (tileEntityChalice.isFilled())) {
      liquid.func_78785_a(f5);
    }
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
