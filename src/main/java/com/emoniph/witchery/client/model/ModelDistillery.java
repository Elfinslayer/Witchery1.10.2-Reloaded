package com.emoniph.witchery.client.model;

import com.emoniph.witchery.blocks.BlockDistillery.TileEntityDistillery;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

@SideOnly(Side.CLIENT)
public class ModelDistillery
  extends ModelBase
{
  ModelRenderer stillBase;
  ModelRenderer stillMiddle;
  ModelRenderer stillTop;
  ModelRenderer stillBend;
  ModelRenderer stillTube;
  ModelRenderer frameTop;
  ModelRenderer frameLeft;
  ModelRenderer frameRight;
  ModelRenderer frameBase;
  ModelRenderer bottle1;
  ModelRenderer bottle2;
  ModelRenderer bottle3;
  ModelRenderer bottle4;
  
  public ModelDistillery()
  {
    field_78090_t = 64;
    field_78089_u = 64;
    func_78085_a("bottle1.bottle1Body", 52, 26);
    func_78085_a("bottle1.bottle1Neck", 60, 24);
    func_78085_a("bottle1.bottle1Top", 56, 21);
    func_78085_a("bottle2.bottle2Body", 52, 26);
    func_78085_a("bottle2.bottle2Neck", 60, 24);
    func_78085_a("bottle2.bottle2Top", 56, 21);
    func_78085_a("bottle3.bottle3Body", 52, 26);
    func_78085_a("bottle3.bottle3Neck", 60, 24);
    func_78085_a("bottle3.bottle3Top", 56, 21);
    func_78085_a("bottle4.bottle4Body", 52, 26);
    func_78085_a("bottle4.bottle4Neck", 60, 24);
    func_78085_a("bottle4.bottle4Top", 56, 21);
    
    stillBase = new ModelRenderer(this, 0, 16);
    stillBase.func_78789_a(0.0F, 0.0F, 0.0F, 10, 6, 10);
    stillBase.func_78793_a(-5.0F, 18.0F, -2.0F);
    stillBase.func_78787_b(64, 64);
    stillBase.field_78809_i = true;
    setRotation(stillBase, 0.0F, 0.0F, 0.0F);
    stillMiddle = new ModelRenderer(this, 0, 6);
    stillMiddle.func_78789_a(0.0F, 0.0F, 0.0F, 6, 4, 6);
    stillMiddle.func_78793_a(-3.0F, 14.0F, 0.0F);
    stillMiddle.func_78787_b(64, 64);
    stillMiddle.field_78809_i = true;
    setRotation(stillMiddle, 0.0F, 0.0F, 0.0F);
    stillTop = new ModelRenderer(this, 25, 9);
    stillTop.func_78789_a(0.0F, 0.0F, 0.0F, 4, 3, 4);
    stillTop.func_78793_a(-2.0F, 11.0F, 1.0F);
    stillTop.func_78787_b(64, 64);
    stillTop.field_78809_i = true;
    setRotation(stillTop, 0.0F, 0.0F, 0.0F);
    stillBend = new ModelRenderer(this, 0, 0);
    stillBend.func_78789_a(0.0F, 0.0F, 0.0F, 2, 2, 4);
    stillBend.func_78793_a(-1.0F, 9.0F, -1.0F);
    stillBend.func_78787_b(64, 64);
    stillBend.field_78809_i = true;
    setRotation(stillBend, 0.0F, 0.0F, 0.0F);
    stillTube = new ModelRenderer(this, 46, 10);
    stillTube.func_78789_a(-0.5F, -0.5F, 0.0F, 1, 1, 8);
    stillTube.func_78793_a(0.0F, 10.0F, 0.0F);
    stillTube.func_78787_b(64, 64);
    stillTube.field_78809_i = true;
    
    setRotation(stillTube, -2.341978F, 0.0F, 0.0F);
    frameTop = new ModelRenderer(this, 30, 6);
    frameTop.func_78789_a(0.0F, 0.0F, 0.0F, 16, 1, 1);
    frameTop.func_78793_a(-8.0F, 15.0F, -6.0F);
    frameTop.func_78787_b(64, 64);
    frameTop.field_78809_i = true;
    setRotation(frameTop, 0.0F, 0.0F, 0.0F);
    frameLeft = new ModelRenderer(this, 47, 24);
    frameLeft.func_78789_a(0.0F, 0.0F, 0.0F, 1, 7, 1);
    frameLeft.func_78793_a(-8.0F, 16.0F, -6.0F);
    frameLeft.func_78787_b(64, 64);
    frameLeft.field_78809_i = true;
    setRotation(frameLeft, 0.0F, 0.0F, 0.0F);
    frameRight = new ModelRenderer(this, 47, 24);
    frameRight.func_78789_a(0.0F, 0.0F, 0.0F, 1, 7, 1);
    frameRight.func_78793_a(7.0F, 16.0F, -6.0F);
    frameRight.func_78787_b(64, 64);
    frameRight.field_78809_i = true;
    setRotation(frameRight, 0.0F, 0.0F, 0.0F);
    frameBase = new ModelRenderer(this, 22, 0);
    frameBase.func_78789_a(0.0F, 0.0F, 0.0F, 16, 1, 5);
    frameBase.func_78793_a(-8.0F, 23.0F, -8.0F);
    frameBase.func_78787_b(64, 64);
    frameBase.field_78809_i = true;
    setRotation(frameBase, 0.0F, 0.0F, 0.0F);
    bottle1 = new ModelRenderer(this, "bottle1");
    bottle1.func_78793_a(-7.0F, 16.0F, -7.0F);
    setRotation(bottle1, 0.0F, 0.0F, 0.0F);
    bottle1.field_78809_i = true;
    bottle1.func_78786_a("bottle1Body", 0.0F, 2.0F, 0.0F, 3, 3, 3);
    bottle1.func_78786_a("bottle1Neck", 1.0F, 1.0F, 1.0F, 1, 1, 1);
    bottle1.func_78786_a("bottle1Top", 0.5F, 0.0F, 0.5F, 2, 1, 2);
    bottle2 = new ModelRenderer(this, "bottle2");
    bottle2.func_78793_a(-3.3F, 16.0F, -7.0F);
    setRotation(bottle2, 0.0174533F, 0.0F, 0.0F);
    bottle2.field_78809_i = true;
    bottle2.func_78786_a("bottle2Body", 0.0F, 2.0F, 0.0F, 3, 3, 3);
    bottle2.func_78786_a("bottle2Neck", 1.0F, 1.0F, 1.0F, 1, 1, 1);
    bottle2.func_78786_a("bottle2Top", 0.5F, 0.0F, 0.5F, 2, 1, 2);
    bottle3 = new ModelRenderer(this, "bottle3");
    bottle3.func_78793_a(0.4F, 16.0F, -7.0F);
    setRotation(bottle3, 0.0F, 0.0F, 0.0F);
    bottle3.field_78809_i = true;
    bottle3.func_78786_a("bottle3Body", 0.0F, 2.0F, 0.0F, 3, 3, 3);
    bottle3.func_78786_a("bottle3Neck", 1.0F, 1.0F, 1.0F, 1, 1, 1);
    bottle3.func_78786_a("bottle3Top", 0.5F, 0.0F, 0.5F, 2, 1, 2);
    bottle4 = new ModelRenderer(this, "bottle4");
    bottle4.func_78793_a(4.0F, 16.0F, -7.0F);
    setRotation(bottle4, 0.0F, 0.0F, 0.0F);
    bottle4.field_78809_i = true;
    bottle4.func_78786_a("bottle4Body", 0.0F, 2.0F, 0.0F, 3, 3, 3);
    bottle4.func_78786_a("bottle4Neck", 1.0F, 1.0F, 1.0F, 1, 1, 1);
    bottle4.func_78786_a("bottle4Top", 0.5F, 0.0F, 0.5F, 2, 1, 2);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, TileEntity tileEntity) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    stillBase.func_78785_a(f5);
    stillMiddle.func_78785_a(f5);
    stillTop.func_78785_a(f5);
    stillBend.func_78785_a(f5);
    stillTube.func_78785_a(f5);
    frameTop.func_78785_a(f5);
    frameLeft.func_78785_a(f5);
    frameRight.func_78785_a(f5);
    frameBase.func_78785_a(f5);
    
    if ((tileEntity != null) && (tileEntity.func_145831_w() != null)) {
      BlockDistillery.TileEntityDistillery te = (BlockDistillery.TileEntityDistillery)tileEntity;
      ModelRenderer[] bottles = { bottle1, bottle2, bottle3, bottle4 };
      ItemStack jars = te.func_70301_a(2);
      if (jars != null) {
        for (int i = 0; (i < field_77994_a) && (i < bottles.length); i++) {
          bottles[i].func_78785_a(f5);
        }
      }
    }
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
