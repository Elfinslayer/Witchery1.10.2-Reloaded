package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

@cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
public class ModelDarkMark extends ModelBase
{
  private ModelRenderer skull;
  
  public ModelDarkMark()
  {
    field_78090_t = 64;
    field_78089_u = 64;
    func_78085_a("skull.top", 0, 0);
    func_78085_a("skull.bottom", 0, 29);
    
    skull = new ModelRenderer(this, "skull");
    skull.func_78793_a(0.0F, 20.0F, 0.0F);
    setRotation(skull, 0.0F, 0.0F, 0.0F);
    skull.field_78809_i = true;
    skull.func_78786_a("top", -8.0F, -12.0F, -8.0F, 16, 12, 16);
    skull.func_78786_a("bottom", -5.0F, 0.0F, -8.0F, 10, 4, 12);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    
    GL11.glScalef(2.0F, 2.0F, 2.0F);
    GL11.glTranslatef(0.0F, -1.0F, 0.0F);
    skull.func_78785_a(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
  {
    skull.field_78796_g = (par4 / 57.295776F);
    skull.field_78795_f = (par5 / 57.295776F);
  }
}
