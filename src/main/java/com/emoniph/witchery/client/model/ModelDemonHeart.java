package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelDemonHeart extends ModelBase
{
  ModelRenderer Shape1;
  ModelRenderer Shape2;
  ModelRenderer Shape3;
  ModelRenderer Shape4;
  ModelRenderer bigTube1;
  ModelRenderer tube1;
  ModelRenderer tube2;
  ModelRenderer tube3;
  ModelRenderer tube4;
  ModelRenderer tube5;
  
  public ModelDemonHeart()
  {
    field_78090_t = 32;
    field_78089_u = 32;
    
    Shape1 = new ModelRenderer(this, 14, 20);
    Shape1.func_78789_a(0.0F, 0.0F, 0.0F, 5, 8, 4);
    Shape1.func_78793_a(-3.0F, 14.0F, 0.0F);
    Shape1.func_78787_b(32, 32);
    Shape1.field_78809_i = true;
    setRotation(Shape1, 0.0F, 0.0F, 0.0F);
    Shape2 = new ModelRenderer(this, 0, 7);
    Shape2.func_78789_a(0.0F, 0.0F, 0.0F, 3, 8, 6);
    Shape2.func_78793_a(-4.0F, 15.0F, -1.0F);
    Shape2.func_78787_b(32, 32);
    Shape2.field_78809_i = true;
    setRotation(Shape2, 0.0F, 0.0F, 0.0F);
    Shape3 = new ModelRenderer(this, 13, 0);
    Shape3.func_78789_a(0.0F, 0.0F, 0.0F, 1, 6, 4);
    Shape3.func_78793_a(-5.0F, 16.0F, 0.0F);
    Shape3.func_78787_b(32, 32);
    Shape3.field_78809_i = true;
    setRotation(Shape3, 0.0F, 0.0F, 0.0F);
    Shape4 = new ModelRenderer(this, 0, 0);
    Shape4.func_78789_a(0.0F, 0.0F, 0.0F, 2, 1, 2);
    Shape4.func_78793_a(-3.0F, 13.0F, 1.0F);
    Shape4.func_78787_b(32, 32);
    Shape4.field_78809_i = true;
    setRotation(Shape4, 0.0F, 0.0F, 0.0F);
    bigTube1 = new ModelRenderer(this, 3, 3);
    bigTube1.func_78789_a(0.0F, 0.0F, 0.0F, 3, 2, 2);
    bigTube1.func_78793_a(-2.0F, 15.0F, -1.0F);
    bigTube1.func_78787_b(32, 32);
    bigTube1.field_78809_i = true;
    setRotation(bigTube1, 0.0F, 0.3717861F, 0.2230717F);
    tube1 = new ModelRenderer(this, 19, 11);
    tube1.func_78789_a(0.0F, -3.0F, 1.0F, 1, 3, 1);
    tube1.func_78793_a(-3.0F, 14.0F, 1.0F);
    tube1.func_78787_b(32, 32);
    tube1.field_78809_i = true;
    setRotation(tube1, 0.4089647F, 0.6320364F, 0.0F);
    tube2 = new ModelRenderer(this, 19, 11);
    tube2.func_78789_a(0.0F, -3.0F, 1.0F, 1, 3, 1);
    tube2.func_78793_a(-2.0F, 14.0F, 1.0F);
    tube2.func_78787_b(32, 32);
    tube2.field_78809_i = true;
    setRotation(tube2, -0.2974289F, -0.2230717F, -0.3346075F);
    tube3 = new ModelRenderer(this, 19, 11);
    tube3.func_78789_a(0.0F, -3.0F, 1.0F, 1, 3, 1);
    tube3.func_78793_a(1.0F, 13.0F, -0.8F);
    tube3.func_78787_b(32, 32);
    tube3.field_78809_i = true;
    setRotation(tube3, -0.0743572F, 0.1487144F, -0.2602503F);
    tube4 = new ModelRenderer(this, 19, 11);
    tube4.func_78789_a(0.0F, -3.0F, 1.0F, 1, 3, 1);
    tube4.func_78793_a(0.0F, 15.0F, 0.0F);
    tube4.func_78787_b(32, 32);
    tube4.field_78809_i = true;
    setRotation(tube4, 0.2602503F, 0.0F, 0.4089647F);
    tube5 = new ModelRenderer(this, 19, 11);
    tube5.func_78789_a(0.0F, -3.0F, 1.0F, 1, 3, 1);
    tube5.func_78793_a(0.0F, 14.0F, 1.0F);
    tube5.func_78787_b(32, 32);
    tube5.field_78809_i = true;
    setRotation(tube5, -0.2602503F, 0.0F, 0.0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, long ticks) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    tube1.func_78785_a(f5);
    tube2.func_78785_a(f5);
    tube3.func_78785_a(f5);
    tube4.func_78785_a(f5);
    tube5.func_78785_a(f5);
    



    Shape4.func_78785_a(f5);
    
    GL11.glTranslatef(0.0F, 1.0F, 0.0F);
    
    double size = 0.165D * (7.0D + 0.25D * Math.sin(0.25132741228718347D * ticks));
    GL11.glScaled(size, size, size);
    
    GL11.glTranslatef(0.0F, -1.0F, 0.0F);
    bigTube1.func_78785_a(f5);
    Shape1.func_78785_a(f5);
    Shape2.func_78785_a(f5);
    Shape3.func_78785_a(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
  }
}
