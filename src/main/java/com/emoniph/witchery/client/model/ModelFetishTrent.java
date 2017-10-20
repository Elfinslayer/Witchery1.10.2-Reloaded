package com.emoniph.witchery.client.model;

import com.emoniph.witchery.blocks.BlockFetish.TileEntityFetish;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;


@SideOnly(Side.CLIENT)
public class ModelFetishTrent
  extends ModelBase
{
  ModelRenderer body;
  ModelRenderer armLeft;
  ModelRenderer armRight;
  ModelRenderer legLeftUpper;
  ModelRenderer legLeftLower;
  ModelRenderer legRightUpper;
  ModelRenderer legRightLower;
  ModelRenderer headdress1;
  ModelRenderer headdress2;
  ModelRenderer headdress3;
  ModelRenderer face;
  
  public ModelFetishTrent()
  {
    field_78090_t = 64;
    field_78089_u = 64;
    
    body = new ModelRenderer(this, 0, 14);
    body.func_78789_a(-3.0F, 0.0F, -3.0F, 6, 9, 6);
    body.func_78793_a(0.0F, 12.0F, 0.0F);
    body.func_78787_b(64, 64);
    body.field_78809_i = true;
    setRotation(body, 0.0F, 0.0F, 0.0F);
    
    face = new ModelRenderer(this, 18, 1);
    face.func_78789_a(-3.0F, 1.0F, -2.9F, 6, 7, 0);
    face.func_78793_a(0.0F, 12.0F, 0.0F);
    face.func_78787_b(64, 64);
    face.field_78809_i = true;
    setRotation(face, 0.0F, 0.0F, 0.0F);
    
    armLeft = new ModelRenderer(this, 0, 0);
    armLeft.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 5, 2);
    armLeft.func_78793_a(2.0F, 13.0F, 0.0F);
    armLeft.func_78787_b(64, 64);
    armLeft.field_78809_i = true;
    setRotation(armLeft, -0.1858931F, 0.0F, -0.7435722F);
    armRight = new ModelRenderer(this, 0, 0);
    armRight.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 5, 2);
    armRight.func_78793_a(-2.0F, 13.0F, 0.0F);
    armRight.func_78787_b(64, 64);
    armRight.field_78809_i = true;
    setRotation(armRight, -0.1858931F, 0.0F, 0.8551081F);
    legLeftUpper = new ModelRenderer(this, 9, 0);
    legLeftUpper.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 5, 2);
    legLeftUpper.func_78793_a(2.0F, 18.0F, 0.0F);
    legLeftUpper.func_78787_b(64, 64);
    legLeftUpper.field_78809_i = true;
    setRotation(legLeftUpper, -0.1487144F, 0.0F, -0.2602503F);
    legLeftLower = new ModelRenderer(this, 11, 8);
    legLeftLower.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 3, 1);
    legLeftLower.func_78793_a(3.0F, 21.0F, -0.5F);
    legLeftLower.func_78787_b(64, 64);
    legLeftLower.field_78809_i = true;
    setRotation(legLeftLower, 0.0743572F, 0.0F, -0.1115358F);
    legRightUpper = new ModelRenderer(this, 9, 0);
    legRightUpper.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 5, 2);
    legRightUpper.func_78793_a(-2.0F, 18.0F, 0.0F);
    legRightUpper.func_78787_b(64, 64);
    legRightUpper.field_78809_i = true;
    setRotation(legRightUpper, 0.1858931F, 0.0F, 0.3346075F);
    legRightLower = new ModelRenderer(this, 11, 8);
    legRightLower.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 3, 1);
    legRightLower.func_78793_a(-3.0F, 21.0F, 0.5F);
    legRightLower.func_78787_b(64, 64);
    legRightLower.field_78809_i = true;
    setRotation(legRightLower, 0.1858931F, 0.0F, 0.2230717F);
    headdress1 = new ModelRenderer(this, 0, 30);
    headdress1.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 5, 2);
    headdress1.func_78793_a(0.0F, 13.0F, 1.0F);
    headdress1.func_78787_b(64, 64);
    headdress1.field_78809_i = true;
    setRotation(headdress1, 0.1115358F, 0.0F, -2.862753F);
    headdress2 = new ModelRenderer(this, 0, 30);
    headdress2.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 5, 2);
    headdress2.func_78793_a(-1.0F, 13.0F, 0.0F);
    headdress2.func_78787_b(64, 64);
    headdress2.field_78809_i = true;
    setRotation(headdress2, 0.3717861F, 0.0F, 2.639681F);
    headdress3 = new ModelRenderer(this, 0, 30);
    headdress3.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 5, 2);
    headdress3.func_78793_a(-1.0F, 13.0F, 0.0F);
    headdress3.func_78787_b(64, 64);
    headdress3.field_78809_i = true;
    setRotation(headdress3, -0.4461433F, 0.0F, 2.862753F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockFetish.TileEntityFetish tile) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    
    body.func_78785_a(f5);
    armLeft.func_78785_a(f5);
    armRight.func_78785_a(f5);
    legLeftUpper.func_78785_a(f5);
    legLeftLower.func_78785_a(f5);
    legRightUpper.func_78785_a(f5);
    legRightLower.func_78785_a(f5);
    headdress1.func_78785_a(f5);
    headdress2.func_78785_a(f5);
    headdress3.func_78785_a(f5);
    
    int colorIndex = 9;
    if (tile != null) {
      int color = tile.getColor();
      if ((color >= 0) && (color <= 15)) {
        colorIndex = color;
      }
    }
    
    GL11.glColor4f(ModelBroom.fleeceColorTable[colorIndex][0], ModelBroom.fleeceColorTable[colorIndex][1], ModelBroom.fleeceColorTable[colorIndex][2], 1.0F);
    
    face.func_78785_a(f5);
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
