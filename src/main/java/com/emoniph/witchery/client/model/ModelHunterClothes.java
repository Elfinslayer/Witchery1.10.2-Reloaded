package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;


@SideOnly(Side.CLIENT)
public class ModelHunterClothes
  extends ModelBiped
{
  ModelRenderer coat;
  ModelRenderer hatBrim;
  ModelRenderer hatTop;
  ModelRenderer hatMid;
  
  public ModelHunterClothes(float scale, boolean shoulders)
  {
    super(scale, 0.0F, 128, 64);
    













    float hatScale = 0.52F;
    hatBrim = new ModelRenderer(this, 0, 50);
    hatBrim.func_78790_a(-6.5F, 0.0F, -6.5F, 13, 1, 13, hatScale - 0.2F);
    hatBrim.func_78793_a(0.0F, -6.0F, 0.0F);
    hatBrim.func_78787_b(128, 64);
    hatBrim.field_78809_i = true;
    setRotation(hatBrim, 0.0F, 0.0F, 0.0F);
    field_78116_c.func_78792_a(hatBrim);
    
    hatMid = new ModelRenderer(this, 40, 52);
    hatMid.func_78790_a(-4.0F, 0.0F, -4.0F, 8, 2, 8, hatScale);
    hatMid.func_78793_a(0.0F, -2.0F, 0.0F);
    hatMid.func_78787_b(128, 64);
    hatMid.field_78809_i = true;
    setRotation(hatMid, 0.0F, 0.0F, 0.0F);
    hatBrim.func_78792_a(hatMid);
    
    hatTop = new ModelRenderer(this, 12, 41);
    hatTop.func_78790_a(-3.5F, 0.0F, -3.5F, 7, 2, 7, hatScale);
    hatTop.func_78793_a(0.0F, -2.0F, 0.0F);
    hatTop.func_78787_b(128, 64);
    hatTop.field_78809_i = true;
    setRotation(hatTop, 0.0F, 0.0F, 0.0F);
    hatMid.func_78792_a(hatTop);
    

    coat = new ModelRenderer(this, 41, 33);
    coat.func_78790_a(-5.5F, 0.0F, -3.0F, 11, 10, 6, -0.3F);
    coat.func_78793_a(0.0F, 12.0F, 0.0F);
    coat.func_78787_b(128, 64);
    coat.field_78809_i = true;
    setRotation(coat, 0.0F, 0.0F, 0.0F);
    field_78115_e.func_78792_a(coat);
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
}
