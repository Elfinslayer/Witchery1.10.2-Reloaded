package com.emoniph.witchery.client.model;

import com.emoniph.witchery.blocks.BlockWitchesOven.TileEntityWitchesOven;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelWitchesOven extends ModelBase
{
  ModelRenderer body;
  ModelRenderer lidBottom;
  ModelRenderer lidTop;
  ModelRenderer chimney;
  ModelRenderer chimneyTop;
  ModelRenderer legBackRight;
  ModelRenderer legFrontRight;
  ModelRenderer legBackLeft;
  ModelRenderer legFrontLeft;
  
  public ModelWitchesOven()
  {
    field_78090_t = 64;
    field_78089_u = 64;
    func_78085_a("legBackRight.legBackRightH", 0, 0);
    func_78085_a("legBackRight.legBackRightV", 0, 2);
    func_78085_a("legFrontRight.legFrontRightH", 0, 0);
    func_78085_a("legFrontRight.legFrontRightV", 0, 2);
    func_78085_a("legBackLeft.legBackLeftH", 0, 0);
    func_78085_a("legBackLeft.legBackLeftV", 0, 2);
    func_78085_a("legFrontLeft.legFrontLeftH", 0, 0);
    func_78085_a("legFrontLeft.legFrontLeftV", 0, 2);
    
    body = new ModelRenderer(this, 0, 0);
    body.func_78789_a(0.0F, 1.0F, 0.0F, 12, 8, 12);
    body.func_78793_a(-6.0F, 14.0F, -6.0F);
    body.func_78787_b(64, 64);
    body.field_78809_i = true;
    setRotation(body, 0.0F, 0.0F, 0.0F);
    lidBottom = new ModelRenderer(this, 0, 20);
    lidBottom.func_78789_a(0.0F, 0.0F, 0.0F, 14, 1, 14);
    lidBottom.func_78793_a(-7.0F, 14.0F, -7.0F);
    lidBottom.func_78787_b(64, 64);
    lidBottom.field_78809_i = true;
    setRotation(lidBottom, 0.0F, 0.0F, 0.0F);
    lidTop = new ModelRenderer(this, 8, 35);
    lidTop.func_78789_a(0.0F, 0.0F, 0.0F, 10, 1, 10);
    lidTop.func_78793_a(-5.0F, 13.0F, -5.0F);
    lidTop.func_78787_b(64, 64);
    lidTop.field_78809_i = true;
    setRotation(lidTop, 0.0F, 0.0F, 0.0F);
    chimney = new ModelRenderer(this, 48, 0);
    

    chimney.func_78789_a(0.0F, 0.0F, 0.0F, 4, 13, 4);
    chimney.func_78793_a(-2.0F, 8.0F, 3.0F);
    chimney.func_78787_b(64, 64);
    chimney.field_78809_i = true;
    setRotation(chimney, 0.0F, 0.0F, 0.0F);
    chimneyTop = new ModelRenderer(this, 38, 0);
    chimneyTop.func_78789_a(0.0F, 0.0F, 0.0F, 4, 4, 1);
    
    chimneyTop.func_78793_a(-2.0F, 8.0F, 7.0F);
    chimneyTop.func_78787_b(64, 64);
    chimneyTop.field_78809_i = true;
    setRotation(chimneyTop, 0.0F, 0.0F, 0.0F);
    legBackRight = new ModelRenderer(this, "legBackRight");
    legBackRight.func_78793_a(-5.0F, 21.0F, -7.0F);
    setRotation(legBackRight, 0.0F, 0.0F, 0.0F);
    legBackRight.field_78809_i = true;
    legBackRight.func_78786_a("legBackRightH", -2.0F, 0.0F, 0.0F, 2, 1, 1);
    legBackRight.func_78786_a("legBackRightV", -3.0F, 0.0F, 0.0F, 1, 3, 1);
    legFrontRight = new ModelRenderer(this, "legFrontRight");
    legFrontRight.func_78793_a(-5.0F, 21.0F, 6.0F);
    setRotation(legFrontRight, 0.0F, 0.0F, 0.0F);
    legFrontRight.field_78809_i = true;
    legFrontRight.func_78786_a("legFrontRightH", -2.0F, 0.0F, 0.0F, 2, 1, 1);
    legFrontRight.func_78786_a("legFrontRightV", -3.0F, 0.0F, 0.0F, 1, 3, 1);
    legBackLeft = new ModelRenderer(this, "legBackLeft");
    legBackLeft.func_78793_a(5.0F, 21.0F, -7.0F);
    setRotation(legBackLeft, 0.0F, 0.0F, 0.0F);
    legBackLeft.field_78809_i = true;
    legBackLeft.func_78786_a("legBackLeftH", 0.0F, 0.0F, 0.0F, 2, 1, 1);
    legBackLeft.func_78786_a("legBackLeftV", 2.0F, 0.0F, 0.0F, 1, 3, 1);
    legFrontLeft = new ModelRenderer(this, "legFrontLeft");
    legFrontLeft.func_78793_a(5.0F, 21.0F, 6.0F);
    setRotation(legFrontLeft, 0.0F, 0.0F, 0.0F);
    legFrontLeft.field_78809_i = true;
    legFrontLeft.func_78786_a("legFrontLeftH", 0.0F, 0.0F, 0.0F, 2, 1, 1);
    legFrontLeft.func_78786_a("legFrontLeftV", 2.0F, 0.0F, 0.0F, 1, 3, 1);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockWitchesOven.TileEntityWitchesOven te) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    body.func_78785_a(f5);
    lidBottom.func_78785_a(f5);
    lidTop.func_78785_a(f5);
    chimney.func_78785_a(f5);
    chimneyTop.func_78785_a(f5);
    legBackRight.func_78785_a(f5);
    legFrontRight.func_78785_a(f5);
    legBackLeft.func_78785_a(f5);
    legFrontLeft.func_78785_a(f5);
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
