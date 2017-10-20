package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityBroom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelBroom
  extends ModelBase
{
  ModelRenderer handle;
  ModelRenderer bristle1;
  ModelRenderer bristle2;
  ModelRenderer bristle3;
  ModelRenderer bristle4;
  ModelRenderer bristle5;
  ModelRenderer bristle6;
  ModelRenderer bristle7;
  ModelRenderer bristle8;
  ModelRenderer bristle9;
  
  public ModelBroom()
  {
    field_78090_t = 32;
    field_78089_u = 32;
    
    handle = new ModelRenderer(this, 24, 0);
    handle.func_78789_a(-1.0F, -10.0F, -1.0F, 2, 24, 2);
    handle.func_78793_a(0.0F, 11.0F, -5.0F);
    handle.func_78787_b(32, 32);
    handle.field_78809_i = true;
    setRotation(handle, 1.570796F, 0.0F, 0.0F);
    bristle1 = new ModelRenderer(this, 0, 0);
    bristle1.func_78789_a(-0.5F, -0.5F, 0.0F, 1, 1, 10);
    bristle1.func_78793_a(-1.0F, 10.0F, 9.0F);
    bristle1.func_78787_b(32, 32);
    bristle1.field_78809_i = true;
    setRotation(bristle1, 0.1858931F, -0.1487144F, 0.0F);
    bristle2 = new ModelRenderer(this, 0, 0);
    bristle2.func_78789_a(-0.5F, -0.5F, 0.0F, 1, 1, 10);
    bristle2.func_78793_a(1.0F, 12.0F, 9.0F);
    bristle2.func_78787_b(32, 32);
    bristle2.field_78809_i = true;
    setRotation(bristle2, -0.1487144F, 0.1858931F, 0.0F);
    bristle3 = new ModelRenderer(this, 0, 12);
    bristle3.func_78789_a(-0.5F, -0.5F, 0.0F, 1, 1, 9);
    bristle3.func_78793_a(1.0F, 10.0F, 9.0F);
    bristle3.func_78787_b(32, 32);
    bristle3.field_78809_i = true;
    setRotation(bristle3, 0.2230717F, 0.1858931F, 0.0F);
    bristle4 = new ModelRenderer(this, 0, 0);
    bristle4.func_78789_a(-0.5F, -0.5F, 0.0F, 1, 1, 10);
    bristle4.func_78793_a(0.0F, 10.0F, 9.0F);
    bristle4.func_78787_b(32, 32);
    bristle4.field_78809_i = true;
    setRotation(bristle4, 0.2230717F, 0.0743572F, 0.0F);
    bristle5 = new ModelRenderer(this, 0, 0);
    bristle5.func_78789_a(-0.5F, -0.5F, 0.0F, 1, 1, 10);
    bristle5.func_78793_a(-1.0F, 12.0F, 9.0F);
    bristle5.func_78787_b(32, 32);
    bristle5.field_78809_i = true;
    setRotation(bristle5, -0.2230717F, -0.1487144F, 0.0F);
    bristle6 = new ModelRenderer(this, 0, 0);
    bristle6.func_78789_a(-0.5F, -0.5F, 0.0F, 1, 1, 10);
    bristle6.func_78793_a(0.0F, 11.0F, 9.0F);
    bristle6.func_78787_b(32, 32);
    bristle6.field_78809_i = true;
    setRotation(bristle6, -0.0371786F, 0.0743572F, 0.0F);
    bristle7 = new ModelRenderer(this, 0, 0);
    bristle7.func_78789_a(-0.5F, -0.5F, 0.0F, 1, 1, 10);
    bristle7.func_78793_a(1.0F, 11.0F, 9.0F);
    bristle7.func_78787_b(32, 32);
    bristle7.field_78809_i = true;
    setRotation(bristle7, -0.0371786F, 0.2230717F, 0.0F);
    bristle8 = new ModelRenderer(this, 0, 12);
    bristle8.func_78789_a(-0.5F, -0.5F, 0.0F, 1, 1, 9);
    bristle8.func_78793_a(-1.0F, 11.0F, 9.0F);
    bristle8.func_78787_b(32, 32);
    bristle8.field_78809_i = true;
    setRotation(bristle8, -0.0743572F, -0.1487144F, 0.0F);
    bristle9 = new ModelRenderer(this, 0, 12);
    bristle9.func_78789_a(-0.5333334F, -0.5F, 0.0F, 1, 1, 9);
    bristle9.func_78793_a(0.0F, 12.0F, 9.0F);
    bristle9.func_78787_b(32, 32);
    bristle9.field_78809_i = true;
    setRotation(bristle9, -0.1858931F, 0.0F, 0.0F);
  }
  
  public static final float[][] fleeceColorTable = { { 1.0F, 1.0F, 1.0F }, { 0.85F, 0.5F, 0.2F }, { 0.7F, 0.3F, 0.85F }, { 0.4F, 0.6F, 0.85F }, { 0.9F, 0.9F, 0.2F }, { 0.5F, 0.8F, 0.1F }, { 0.95F, 0.5F, 0.65F }, { 0.3F, 0.3F, 0.3F }, { 0.6F, 0.6F, 0.6F }, { 0.3F, 0.5F, 0.6F }, { 0.5F, 0.25F, 0.7F }, { 0.2F, 0.3F, 0.7F }, { 0.4F, 0.3F, 0.2F }, { 0.4F, 0.5F, 0.2F }, { 0.6F, 0.2F, 0.2F }, { 0.1F, 0.1F, 0.1F } };
  
















  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    handle.func_78785_a(f5);
    
    if ((entity != null) && ((entity instanceof EntityBroom))) {
      int j = ((EntityBroom)entity).getBrushColor();
      if ((j < 0) || (j > 15)) {
        j = 12;
      }
      
      GL11.glColor3f(fleeceColorTable[j][0], fleeceColorTable[j][1], fleeceColorTable[j][2]);
    }
    


    bristle1.func_78785_a(f5);
    bristle2.func_78785_a(f5);
    bristle3.func_78785_a(f5);
    bristle4.func_78785_a(f5);
    bristle5.func_78785_a(f5);
    bristle6.func_78785_a(f5);
    bristle7.func_78785_a(f5);
    bristle8.func_78785_a(f5);
    bristle9.func_78785_a(f5);
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
