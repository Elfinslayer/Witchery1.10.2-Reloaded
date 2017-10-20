package com.emoniph.witchery.client.model;

import com.emoniph.witchery.blocks.BlockFetish.TileEntityFetish;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;


@SideOnly(Side.CLIENT)
public class ModelFetishScarecrow
  extends ModelBase
{
  ModelRenderer poleVertical;
  ModelRenderer poleHorizontal;
  ModelRenderer head;
  ModelRenderer headInner;
  ModelRenderer body;
  ModelRenderer armLeft;
  ModelRenderer armRight;
  ModelRenderer armLeftInner;
  ModelRenderer armRightInner;
  
  public ModelFetishScarecrow()
  {
    field_78090_t = 64;
    field_78089_u = 64;
    
    poleVertical = new ModelRenderer(this, 0, 2);
    poleVertical.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 15, 2);
    poleVertical.func_78793_a(0.0F, 9.0F, 0.0F);
    poleVertical.func_78787_b(64, 64);
    poleVertical.field_78809_i = true;
    setRotation(poleVertical, 0.0F, 0.0F, 0.0F);
    poleHorizontal = new ModelRenderer(this, 0, 0);
    poleHorizontal.func_78789_a(-8.0F, 0.0F, -0.5F, 16, 1, 1);
    poleHorizontal.func_78793_a(0.0F, 13.0F, 0.0F);
    poleHorizontal.func_78787_b(64, 64);
    poleHorizontal.field_78809_i = true;
    setRotation(poleHorizontal, 0.0F, 0.0F, 0.0F);
    
    head = new ModelRenderer(this, 12, 21);
    head.func_78789_a(-2.0F, -4.0F, -2.0F, 4, 5, 4);
    head.func_78793_a(0.0F, 12.0F, 0.0F);
    head.func_78787_b(64, 64);
    head.field_78809_i = true;
    setRotation(head, 0.0F, 0.0F, 0.0F);
    
    headInner = new ModelRenderer(this, 29, 25);
    headInner.func_78789_a(-2.0F, -4.0F, -1.9F, 4, 5, 0);
    headInner.func_78793_a(0.0F, 12.0F, 0.0F);
    headInner.func_78787_b(64, 64);
    headInner.field_78809_i = true;
    setRotation(headInner, 0.0F, 0.0F, 0.0F);
    


    body = new ModelRenderer(this, 8, 2);
    body.func_78789_a(-3.0F, 0.0F, -1.5F, 6, 9, 3);
    body.func_78793_a(0.0F, 12.5F, 0.0F);
    body.func_78787_b(64, 64);
    body.field_78809_i = true;
    setRotation(body, 0.0F, 0.0F, 0.0F);
    
    armLeft = new ModelRenderer(this, 0, 23);
    armLeft.func_78789_a(0.0F, -0.5F, -1.5F, 3, 4, 3);
    armLeft.func_78793_a(3.0F, 13.0F, 0.0F);
    armLeft.func_78787_b(64, 64);
    armLeft.field_78809_i = true;
    setRotation(armLeft, 0.0F, 0.0F, 0.0F);
    
    armLeftInner = new ModelRenderer(this, 29, 25);
    armLeftInner.func_78789_a(2.9F, -0.5F, -1.5F, 0, 4, 3);
    armLeftInner.func_78793_a(3.0F, 13.0F, 0.0F);
    armLeftInner.func_78787_b(64, 64);
    armLeftInner.field_78809_i = true;
    setRotation(armLeftInner, 0.0F, 0.0F, 0.0F);
    
    armRight = new ModelRenderer(this, 0, 23);
    armRight.func_78789_a(-3.0F, -0.5F, -1.5F, 3, 4, 3);
    armRight.func_78793_a(-3.0F, 13.0F, 0.0F);
    armRight.func_78787_b(64, 64);
    armRight.field_78809_i = true;
    setRotation(armRight, 0.0F, 0.0F, 0.0F);
    
    armRightInner = new ModelRenderer(this, 29, 25);
    armRightInner.func_78789_a(-2.9F, -0.5F, -1.5F, 0, 4, 3);
    armRightInner.func_78793_a(-3.0F, 13.0F, 0.0F);
    armRightInner.func_78787_b(64, 64);
    armRightInner.field_78809_i = true;
    setRotation(armRightInner, 0.0F, 0.0F, 0.0F);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockFetish.TileEntityFetish tile) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    poleVertical.func_78785_a(f5);
    poleHorizontal.func_78785_a(f5);
    headInner.func_78785_a(f5);
    armLeftInner.func_78785_a(f5);
    armRightInner.func_78785_a(f5);
    
    int colorIndex = 9;
    float alpha = 1.0F;
    if (tile != null) {
      int color = tile.getColor();
      if ((color >= 0) && (color <= 15)) {
        colorIndex = color;
      }
      if (tile.isSpectral()) {
        alpha = 0.7F;
      }
    }
    
    GL11.glColor4f(ModelBroom.fleeceColorTable[colorIndex][0], ModelBroom.fleeceColorTable[colorIndex][1], ModelBroom.fleeceColorTable[colorIndex][2], alpha);
    



    head.func_78785_a(f5);
    body.func_78785_a(f5);
    armLeft.func_78785_a(f5);
    armRight.func_78785_a(f5);
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
  }
}
