package com.emoniph.witchery.client.model;

import com.emoniph.witchery.util.RenderUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelCrystalBall
  extends ModelBase
{
  ModelRenderer baseBottom;
  ModelRenderer baseMiddle;
  ModelRenderer baseTop;
  ModelRenderer globeInner;
  ModelRenderer globeMiddle;
  ModelRenderer globeOuter;
  
  public ModelCrystalBall()
  {
    field_78090_t = 32;
    field_78089_u = 32;
    
    baseBottom = new ModelRenderer(this, 0, 25);
    baseBottom.func_78789_a(0.0F, 0.0F, 0.0F, 6, 1, 6);
    baseBottom.func_78793_a(-3.0F, 23.0F, -3.0F);
    baseBottom.func_78787_b(32, 32);
    baseBottom.field_78809_i = true;
    setRotation(baseBottom, 0.0F, 0.0F, 0.0F);
    baseMiddle = new ModelRenderer(this, 0, 20);
    baseMiddle.func_78789_a(0.0F, 0.0F, 0.0F, 4, 1, 4);
    baseMiddle.func_78793_a(-2.0F, 22.0F, -2.0F);
    baseMiddle.func_78787_b(32, 32);
    baseMiddle.field_78809_i = true;
    setRotation(baseMiddle, 0.0F, 0.0F, 0.0F);
    baseTop = new ModelRenderer(this, 0, 17);
    baseTop.func_78789_a(0.0F, 0.0F, 0.0F, 2, 1, 2);
    baseTop.func_78793_a(-1.0F, 21.0F, -1.0F);
    baseTop.func_78787_b(32, 32);
    baseTop.field_78809_i = true;
    setRotation(baseTop, 0.0F, 0.0F, 0.0F);
    globeInner = new ModelRenderer(this, 4, 0);
    globeInner.func_78789_a(0.0F, 0.0F, 0.0F, 2, 2, 2);
    globeInner.func_78793_a(-1.0F, 17.0F, -1.0F);
    globeInner.func_78787_b(32, 32);
    globeInner.field_78809_i = true;
    setRotation(globeInner, 0.0F, 0.0F, 0.0F);
    globeMiddle = new ModelRenderer(this, 12, 0);
    globeMiddle.func_78789_a(0.0F, 0.0F, 0.0F, 4, 4, 4);
    globeMiddle.func_78793_a(-2.0F, 16.0F, -2.0F);
    globeMiddle.func_78787_b(32, 32);
    globeMiddle.field_78809_i = true;
    setRotation(globeMiddle, 0.0F, 0.0F, 0.0F);
    globeOuter = new ModelRenderer(this, 8, 8);
    globeOuter.func_78789_a(0.0F, 0.0F, 0.0F, 6, 6, 6);
    globeOuter.func_78793_a(-3.0F, 15.0F, -3.0F);
    globeOuter.func_78787_b(32, 32);
    globeOuter.field_78809_i = true;
    setRotation(globeOuter, 0.0F, 0.0F, 0.0F);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, TileEntity tile) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    baseBottom.func_78785_a(f5);
    baseMiddle.func_78785_a(f5);
    baseTop.func_78785_a(f5);
    




    RenderUtil.blend(true);
    if ((tile != null) && (tile.func_145831_w() != null)) {
      long time = tile.func_145831_w().func_72820_D();
      long scale = 100L - Math.abs(time % 160L - 80L);
      
      GL11.glColor3f(0.01F * (float)scale, 0.01F * (float)scale, 0.01F * (float)scale);
    }
    globeInner.func_78785_a(f5);
    GL11.glColor3f(0.8F, 0.8F, 1.0F);
    globeMiddle.func_78785_a(f5);
    globeOuter.func_78785_a(f5);
    
    RenderUtil.blend(false);
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
  }
}
