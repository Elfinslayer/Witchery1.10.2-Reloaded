package com.emoniph.witchery.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class ModelEarmuffs extends ModelBiped
{
  private ModelRenderer earRight;
  private ModelRenderer earLeft;
  private ModelRenderer bandLeft;
  private ModelRenderer bandTop;
  private ModelRenderer bandRight;
  
  public ModelEarmuffs()
  {
    field_78090_t = 64;
    field_78089_u = 64;
    
    bandTop = new ModelRenderer(this, 46, 38);
    bandTop.func_78789_a(-4.0F, -10.0F, -0.5F, 8, 1, 1);
    bandTop.func_78793_a(0.0F, 0.0F, 0.0F);
    bandTop.func_78787_b(64, 64);
    bandTop.field_78809_i = true;
    setRotation(bandTop, 0.0F, 0.0F, 0.0F);
    
    earRight = new ModelRenderer(this, 33, 32);
    earRight.func_78789_a(-6.0F, -6.0F, -2.0F, 2, 4, 4);
    earRight.func_78793_a(0.0F, 0.0F, 0.0F);
    earRight.func_78787_b(64, 64);
    earRight.field_78809_i = true;
    setRotation(earRight, 0.0F, 0.0F, 0.0F);
    

    earLeft = new ModelRenderer(this, 33, 32);
    earLeft.func_78789_a(4.0F, -6.0F, -2.0F, 2, 4, 4);
    earLeft.func_78793_a(0.0F, 0.0F, 0.0F);
    earLeft.func_78787_b(64, 64);
    earLeft.field_78809_i = true;
    setRotation(earLeft, 0.0F, 0.0F, 0.0F);
    

    bandLeft = new ModelRenderer(this, 46, 32);
    bandLeft.func_78789_a(4.0F, -10.0F, -0.5F, 1, 4, 1);
    bandLeft.func_78793_a(0.0F, 0.0F, 0.0F);
    bandLeft.func_78787_b(64, 64);
    bandLeft.field_78809_i = true;
    setRotation(bandLeft, 0.0F, 0.0F, 0.0F);
    

    bandRight = new ModelRenderer(this, 46, 32);
    bandRight.func_78789_a(-5.0F, -10.0F, -0.5F, 1, 4, 1);
    bandRight.func_78793_a(0.0F, 0.0F, 0.0F);
    bandRight.func_78787_b(64, 64);
    bandRight.field_78809_i = true;
    setRotation(bandRight, 0.0F, 0.0F, 0.0F);
    
    field_78116_c.func_78792_a(earRight);
    field_78116_c.func_78792_a(earLeft);
    field_78116_c.func_78792_a(bandLeft);
    field_78116_c.func_78792_a(bandRight);
    field_78116_c.func_78792_a(bandTop);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
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
