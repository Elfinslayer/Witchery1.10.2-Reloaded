package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

@cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
public class ModelSpirit extends ModelBase
{
  ModelRenderer Piece1;
  
  public ModelSpirit()
  {
    field_78090_t = 32;
    field_78089_u = 32;
    func_78085_a("Piece1.Shape1", 2, 5);
    func_78085_a("Piece1.Shape2", 2, 21);
    func_78085_a("Piece1.Shape3", 0, 12);
    func_78085_a("Piece1.Shape4", 6, 0);
    func_78085_a("Piece1.Shape5", 6, 28);
    
    Piece1 = new ModelRenderer(this, "Piece1");
    Piece1.func_78793_a(0.0F, 21.0F, 0.0F);
    setRotation(Piece1, 0.0F, 0.0F, 0.0F);
    Piece1.field_78809_i = true;
    Piece1.func_78786_a("Shape1", -2.5F, -2.0F, -2.5F, 5, 1, 5);
    Piece1.func_78786_a("Shape2", -2.5F, 1.0F, -2.5F, 5, 1, 5);
    Piece1.func_78786_a("Shape3", -3.0F, -1.0F, -3.0F, 6, 2, 6);
    Piece1.func_78786_a("Shape4", -1.5F, -3.0F, -1.5F, 3, 1, 3);
    Piece1.func_78786_a("Shape5", -1.5F, 2.0F, -1.5F, 3, 1, 3);
  }
  



  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    float SCALE = 0.5F;
    GL11.glTranslatef(0.0F, 0.65F, 0.0F);
    GL11.glScalef(SCALE, SCALE, SCALE);
    Piece1.func_78785_a(f5);
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
