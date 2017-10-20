package com.emoniph.witchery.client.model;

import com.emoniph.witchery.blocks.BlockBeartrap.TileEntityBeartrap;
import com.emoniph.witchery.util.Config;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelBeartrap
  extends ModelBase
{
  public ModelRenderer plate;
  public ModelRenderer base;
  public ModelRenderer armFront;
  public ModelRenderer armBack;
  public ModelRenderer diskLeft;
  public ModelRenderer diskRight;
  public ModelRenderer armRightFront;
  public ModelRenderer armLeftFront;
  public ModelRenderer armTooth1Front;
  public ModelRenderer armTooth2Front;
  public ModelRenderer armTooth3Front;
  public ModelRenderer armTooth4Front;
  public ModelRenderer armTooth5Front;
  public ModelRenderer armRightBack;
  public ModelRenderer armLeftBack;
  public ModelRenderer armTooth1Back;
  public ModelRenderer armTooth2Back;
  public ModelRenderer armTooth3Back;
  public ModelRenderer armTooth4Back;
  public ModelRenderer armTooth5Back;
  
  public ModelBeartrap()
  {
    field_78090_t = 32;
    field_78089_u = 32;
    armTooth4Back = new ModelRenderer(this, 0, 0);
    armTooth4Back.func_78793_a(0.0F, 0.0F, 0.0F);
    armTooth4Back.func_78790_a(1.5F, -2.0F, 6.0F, 1, 1, 1, 0.0F);
    plate = new ModelRenderer(this, 1, 0);
    plate.func_78793_a(0.0F, 24.0F, 0.0F);
    plate.func_78790_a(-2.0F, -1.5F, -2.0F, 4, 1, 4, 0.0F);
    armFront = new ModelRenderer(this, 0, 9);
    armFront.func_78793_a(0.0F, 23.99F, 0.0F);
    armFront.func_78790_a(-4.5F, -1.0F, -7.0F, 9, 1, 1, 0.0F);
    armTooth2Front = new ModelRenderer(this, 0, 0);
    armTooth2Front.func_78793_a(0.0F, 0.0F, 0.0F);
    armTooth2Front.func_78790_a(-2.5F, -2.0F, -7.0F, 1, 1, 1, 0.0F);
    armLeftFront = new ModelRenderer(this, 0, 12);
    armLeftFront.func_78793_a(0.0F, 0.0F, 0.0F);
    armLeftFront.func_78790_a(3.5F, -1.0F, -6.0F, 1, 1, 6, 0.0F);
    armTooth4Front = new ModelRenderer(this, 0, 0);
    armTooth4Front.func_78793_a(0.0F, 0.0F, 0.0F);
    armTooth4Front.func_78790_a(1.5F, -2.0F, -7.0F, 1, 1, 1, 0.0F);
    armLeftBack = new ModelRenderer(this, 0, 12);
    armLeftBack.func_78793_a(0.0F, 0.0F, 0.0F);
    armLeftBack.func_78790_a(3.5F, -1.0F, 0.0F, 1, 1, 6, 0.0F);
    armTooth3Front = new ModelRenderer(this, 0, 0);
    armTooth3Front.func_78793_a(0.0F, 0.0F, 0.0F);
    armTooth3Front.func_78790_a(-0.5F, -2.0F, -7.0F, 1, 1, 1, 0.0F);
    armTooth3Back = new ModelRenderer(this, 0, 0);
    armTooth3Back.func_78793_a(0.0F, 0.0F, 0.0F);
    armTooth3Back.func_78790_a(-0.5F, -2.0F, 6.0F, 1, 1, 1, 0.0F);
    armTooth5Front = new ModelRenderer(this, 0, 0);
    armTooth5Front.func_78793_a(0.0F, 0.0F, 0.0F);
    armTooth5Front.func_78790_a(3.5F, -2.0F, -7.0F, 1, 1, 1, 0.0F);
    base = new ModelRenderer(this, 0, 20);
    base.func_78793_a(0.0F, 23.99F, 0.0F);
    base.func_78790_a(-5.0F, -1.0F, -0.5F, 10, 1, 1, 0.0F);
    armBack = new ModelRenderer(this, 0, 9);
    armBack.func_78793_a(0.0F, 23.99F, 0.0F);
    armBack.func_78790_a(-4.5F, -1.0F, 6.0F, 9, 1, 1, 0.0F);
    diskLeft = new ModelRenderer(this, 19, 3);
    diskLeft.func_78793_a(0.0F, 24.0F, 0.0F);
    diskLeft.func_78790_a(3.7F, -2.0F, -1.0F, 1, 2, 2, 0.0F);
    armTooth2Back = new ModelRenderer(this, 0, 0);
    armTooth2Back.func_78793_a(0.0F, 0.0F, 0.0F);
    armTooth2Back.func_78790_a(-2.5F, -2.0F, 6.0F, 1, 1, 1, 0.0F);
    armTooth1Back = new ModelRenderer(this, 0, 0);
    armTooth1Back.func_78793_a(0.0F, 0.0F, 0.0F);
    armTooth1Back.func_78790_a(-4.5F, -2.0F, 6.0F, 1, 1, 1, 0.0F);
    armTooth5Back = new ModelRenderer(this, 0, 0);
    armTooth5Back.func_78793_a(0.0F, 0.0F, 0.0F);
    armTooth5Back.func_78790_a(3.5F, -2.0F, 6.0F, 1, 1, 1, 0.0F);
    armRightBack = new ModelRenderer(this, 0, 12);
    armRightBack.func_78793_a(0.0F, 0.0F, 0.0F);
    armRightBack.func_78790_a(-4.5F, -1.0F, 0.0F, 1, 1, 6, 0.0F);
    armTooth1Front = new ModelRenderer(this, 0, 0);
    armTooth1Front.func_78793_a(0.0F, 0.0F, 0.0F);
    armTooth1Front.func_78790_a(-4.5F, -2.0F, -7.0F, 1, 1, 1, 0.0F);
    armRightFront = new ModelRenderer(this, 0, 12);
    armRightFront.func_78793_a(0.0F, 0.0F, 0.0F);
    armRightFront.func_78790_a(-4.5F, -1.0F, -6.0F, 1, 1, 6, 0.0F);
    diskRight = new ModelRenderer(this, 19, 3);
    diskRight.func_78793_a(0.0F, 24.0F, 0.0F);
    diskRight.func_78790_a(-4.7F, -2.0F, -1.0F, 1, 2, 2, 0.0F);
    armBack.func_78792_a(armTooth4Back);
    armFront.func_78792_a(armTooth2Front);
    armFront.func_78792_a(armLeftFront);
    armFront.func_78792_a(armTooth4Front);
    armBack.func_78792_a(armLeftBack);
    armFront.func_78792_a(armTooth3Front);
    armBack.func_78792_a(armTooth3Back);
    armFront.func_78792_a(armTooth5Front);
    armBack.func_78792_a(armTooth2Back);
    armBack.func_78792_a(armTooth1Back);
    armBack.func_78792_a(armTooth5Back);
    armBack.func_78792_a(armRightBack);
    armFront.func_78792_a(armTooth1Front);
    armFront.func_78792_a(armRightFront);
  }
  
  private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  

  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockBeartrap.TileEntityBeartrap tile)
  {
    boolean inWorld = (tile != null) && (tile.func_145831_w() != null);
    
    if ((inWorld) && (!tile.isVisibleTo(func_71410_xfield_71439_g))) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, instancemantrapAlpha);
    }
    
    base.func_78785_a(f5);
    diskLeft.func_78785_a(f5);
    diskRight.func_78785_a(f5);
    
    if ((inWorld) && (tile.isSprung())) {
      plate.field_78797_d = 23.8F;
    } else {
      plate.field_78797_d = 23.2F;
    }
    plate.func_78785_a(f5);
    
    if ((inWorld) && (tile.isSprung())) {
      armFront.field_78795_f = -1.2F;
    } else {
      armFront.field_78795_f = 0.0F;
    }
    armFront.func_78785_a(f5);
    
    if ((inWorld) && (tile.isSprung())) {
      armBack.field_78795_f = 1.2F;
    } else {
      armBack.field_78795_f = 0.0F;
    }
    armBack.func_78785_a(f5);
  }
}
