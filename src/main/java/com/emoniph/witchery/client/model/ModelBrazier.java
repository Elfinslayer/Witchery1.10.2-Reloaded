package com.emoniph.witchery.client.model;

import com.emoniph.witchery.blocks.BlockBrazier.TileEntityBrazier;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelBrazier extends ModelBase
{
  ModelRenderer leg1;
  ModelRenderer leg2;
  ModelRenderer leg3;
  ModelRenderer leg4;
  ModelRenderer foot3;
  ModelRenderer foot2;
  ModelRenderer foot1;
  ModelRenderer foot4;
  ModelRenderer ash;
  ModelRenderer panSide1;
  ModelRenderer panSide2;
  ModelRenderer panSide3;
  ModelRenderer panSide4;
  ModelRenderer footBase;
  ModelRenderer panBase;
  
  public ModelBrazier()
  {
    field_78090_t = 64;
    field_78089_u = 64;
    
    leg1 = new ModelRenderer(this, 0, 0);
    leg1.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 11, 1);
    leg1.func_78793_a(0.7F, 10.0F, -0.74F);
    leg1.func_78787_b(64, 64);
    leg1.field_78809_i = true;
    setRotation(leg1, 0.0F, 0.0F, 0.0F);
    leg2 = new ModelRenderer(this, 0, 0);
    leg2.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 11, 1);
    leg2.func_78793_a(-0.7F, 10.0F, -0.7F);
    leg2.func_78787_b(64, 64);
    leg2.field_78809_i = true;
    setRotation(leg2, 0.0F, 0.0F, 0.0F);
    leg3 = new ModelRenderer(this, 0, 0);
    leg3.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 11, 1);
    leg3.func_78793_a(-0.7F, 10.0F, 0.7F);
    leg3.func_78787_b(64, 64);
    leg3.field_78809_i = true;
    setRotation(leg3, 0.0F, 0.0F, 0.0F);
    leg4 = new ModelRenderer(this, 0, 0);
    leg4.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 11, 1);
    leg4.func_78793_a(0.7F, 10.0F, 0.7F);
    leg4.func_78787_b(64, 64);
    leg4.field_78809_i = true;
    setRotation(leg4, 0.0F, 0.0F, 0.0F);
    foot3 = new ModelRenderer(this, 0, 13);
    foot3.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 5, 1);
    foot3.func_78793_a(-0.7F, 21.0F, 0.7F);
    foot3.func_78787_b(64, 64);
    foot3.field_78809_i = true;
    setRotation(foot3, 0.7853982F, 0.0F, 0.7853982F);
    foot2 = new ModelRenderer(this, 0, 13);
    foot2.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 5, 1);
    foot2.func_78793_a(-0.7F, 21.0F, -0.7F);
    foot2.func_78787_b(64, 64);
    foot2.field_78809_i = true;
    setRotation(foot2, -0.7853982F, 0.0F, 0.7853982F);
    foot1 = new ModelRenderer(this, 0, 13);
    foot1.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 5, 1);
    foot1.func_78793_a(0.7F, 21.0F, -0.7F);
    foot1.func_78787_b(64, 64);
    foot1.field_78809_i = true;
    setRotation(foot1, -0.7853982F, 0.0F, -0.7853982F);
    foot4 = new ModelRenderer(this, 0, 13);
    foot4.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 5, 1);
    foot4.func_78793_a(0.7F, 21.0F, 0.7F);
    foot4.func_78787_b(64, 64);
    foot4.field_78809_i = true;
    setRotation(foot4, 0.7853982F, 0.0F, -0.7853982F);
    ash = new ModelRenderer(this, 0, 20);
    ash.func_78789_a(-2.5F, 0.0F, -2.5F, 5, 0, 5);
    ash.func_78793_a(0.0F, 9.7F, 0.0F);
    ash.func_78787_b(64, 64);
    ash.field_78809_i = true;
    setRotation(ash, 0.0F, 0.0F, 0.0F);
    panSide1 = new ModelRenderer(this, 5, 12);
    panSide1.func_78789_a(-0.5F, -0.5F, -3.0F, 1, 1, 6);
    panSide1.func_78793_a(3.0F, 9.5F, 0.0F);
    panSide1.func_78787_b(64, 64);
    panSide1.field_78809_i = true;
    setRotation(panSide1, 0.0F, 0.0F, 0.0F);
    
    panSide2 = new ModelRenderer(this, 4, 26);
    panSide2.func_78789_a(-3.0F, -0.5F, -0.5F, 6, 1, 1);
    panSide2.func_78793_a(0.0F, 9.5F, 3.0F);
    panSide2.func_78787_b(64, 64);
    panSide2.field_78809_i = true;
    setRotation(panSide2, 0.0F, 0.0F, 0.0F);
    panSide4 = new ModelRenderer(this, 4, 26);
    panSide4.func_78789_a(-3.0F, -0.5F, -0.5F, 6, 1, 1);
    panSide4.func_78793_a(0.0F, 9.5F, -3.0F);
    panSide4.func_78787_b(64, 64);
    panSide4.field_78809_i = true;
    setRotation(panSide4, 0.0F, 0.0F, 0.0F);
    
    panSide3 = new ModelRenderer(this, 5, 12);
    panSide3.func_78789_a(-0.5F, -0.5F, -3.0F, 1, 1, 6);
    panSide3.func_78793_a(-3.0F, 9.5F, 0.0F);
    panSide3.func_78787_b(64, 64);
    panSide3.field_78809_i = true;
    setRotation(panSide3, 0.0F, 0.0F, 0.0F);
    
    footBase = new ModelRenderer(this, 6, 0);
    footBase.func_78789_a(-1.5F, -0.5F, -1.5F, 3, 1, 3);
    footBase.func_78793_a(0.0F, 21.0F, 0.0F);
    footBase.func_78787_b(64, 64);
    footBase.field_78809_i = true;
    setRotation(footBase, 0.0F, 0.0F, 0.0F);
    panBase = new ModelRenderer(this, 6, 5);
    panBase.func_78789_a(-3.0F, 0.0F, -3.0F, 6, 1, 6);
    panBase.func_78793_a(0.0F, 9.95F, 0.0F);
    panBase.func_78787_b(64, 64);
    panBase.field_78809_i = true;
    setRotation(panBase, 0.0F, 0.0F, 0.0F);
  }
  

  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockBrazier.TileEntityBrazier tile)
  {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    leg1.func_78785_a(f5);
    leg2.func_78785_a(f5);
    leg3.func_78785_a(f5);
    leg4.func_78785_a(f5);
    foot3.func_78785_a(f5);
    foot2.func_78785_a(f5);
    foot1.func_78785_a(f5);
    foot4.func_78785_a(f5);
    
    panSide1.func_78785_a(f5);
    panSide2.func_78785_a(f5);
    panSide3.func_78785_a(f5);
    panSide4.func_78785_a(f5);
    footBase.func_78785_a(f5);
    panBase.func_78785_a(f5);
    panSide4.field_78795_f = 0.0F;
    panSide2.field_78795_f = 0.0F;
    panSide1.field_78808_h = 0.0F;
    panSide3.field_78808_h = 0.0F;
    if (tile != null) {
      int ingredientCount = tile.getIngredientCount();
      if (ingredientCount > 0) {
        ash.field_78797_d = (9.7F - (ingredientCount - 1) * 0.1F);
        ash.func_78785_a(f5);
      }
    }
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
  }
}
