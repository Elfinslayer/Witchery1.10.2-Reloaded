package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;











@SideOnly(Side.CLIENT)
public class ModelGrotesque
  extends ModelBase
{
  public ModelRenderer head;
  
  public ModelGrotesque()
  {
    field_78090_t = 128;
    field_78089_u = 32;
    
    func_78085_a("head.face", 0, 0);
    func_78085_a("head.leftHorn", 0, 16);
    func_78085_a("head.rightHorn", 0, 16);
    func_78085_a("head.leftTusk", 4, 16);
    func_78085_a("head.rightTusk", 4, 16);
    func_78085_a("head.snout", 20, 16);
    func_78085_a("head.bottomLip", 8, 16);
    head = new ModelRenderer(this, "head");
    head.func_78784_a(0, 0);
    head.func_78786_a("face", -4.0F, -8.0F, -4.0F, 8, 8, 8);
    head.func_78786_a("leftHorn", 4.0F, -12.0F, -0.5F, 1, 8, 1);
    head.func_78786_a("rightHorn", -5.0F, -12.0F, -0.5F, 1, 8, 1);
    head.func_78786_a("leftTusk", 1.0F, -4.0F, -5.0F, 1, 2, 1);
    head.func_78786_a("bottomLip", -2.0F, -2.0F, -6.0F, 4, 1, 2);
    head.func_78786_a("snout", -1.0F, -6.0F, -6.0F, 2, 3, 2);
    head.func_78786_a("rightTusk", -2.0F, -4.0F, -5.0F, 1, 2, 1);
    head.func_78793_a(0.0F, -9.0F, 0.0F);
    head.func_78787_b(128, 32);
    head.field_78809_i = true;
  }
  













































  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  


  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    GL11.glTranslatef(0.0F, 0.735F, 0.0F);
    float scale = 1.3F;
    GL11.glScalef(scale, scale, scale);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    



    head.func_78785_a(f5);
  }
  

















  public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
  {
    head.field_78796_g = (par4 / 57.295776F);
    head.field_78795_f = (par5 / 57.295776F);
  }
}
