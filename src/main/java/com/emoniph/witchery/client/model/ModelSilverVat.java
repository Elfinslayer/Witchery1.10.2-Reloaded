package com.emoniph.witchery.client.model;

import com.emoniph.witchery.blocks.BlockSilverVat.TileEntitySilverVat;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelRenderer;

@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class ModelSilverVat extends net.minecraft.client.model.ModelBase
{
  public ModelRenderer base;
  public ModelRenderer sideBack;
  public ModelRenderer sideFront;
  public ModelRenderer sideLeft;
  public ModelRenderer sideRight;
  public ModelRenderer spoutLowerLeft;
  public ModelRenderer spoutUpperLeft;
  public ModelRenderer spoutUpperFront;
  public ModelRenderer spoutLowerFront;
  public ModelRenderer spoutUpperRight;
  public ModelRenderer spoutUpperBack;
  public ModelRenderer spoutLowerRight;
  public ModelRenderer silver1;
  public ModelRenderer spoutLowerBack;
  public ModelRenderer silver2;
  public ModelRenderer silver3;
  public ModelRenderer silver4;
  public ModelRenderer silver5;
  public ModelRenderer silver6;
  public ModelRenderer silver7;
  public ModelRenderer silver8;
  private final ModelRenderer[] silver;
  int capacity;
  
  public ModelSilverVat()
  {
    field_78090_t = 64;
    field_78089_u = 32;
    base = new ModelRenderer(this, 0, 19);
    base.func_78793_a(-6.0F, 23.0F, -6.0F);
    base.func_78790_a(0.0F, 0.0F, 0.0F, 12, 1, 12, 0.0F);
    spoutLowerRight = new ModelRenderer(this, 15, 0);
    spoutLowerRight.func_78793_a(-5.2F, 16.0F, -0.5F);
    spoutLowerRight.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
    spoutLowerFront = new ModelRenderer(this, 15, 0);
    spoutLowerFront.func_78793_a(-0.5F, 16.0F, -5.2F);
    spoutLowerFront.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
    silver2 = new ModelRenderer(this, 0, 6);
    silver2.func_78793_a(1.6F, 19.0F, -2.1F);
    silver2.func_78790_a(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
    sideRight = new ModelRenderer(this, 38, 10);
    sideRight.func_78793_a(-7.0F, 16.0F, -6.0F);
    sideRight.func_78790_a(0.0F, 0.0F, 0.0F, 1, 8, 12, 0.0F);
    spoutUpperLeft = new ModelRenderer(this, 15, 3);
    spoutUpperLeft.func_78793_a(4.0F, 14.0F, -1.5F);
    spoutUpperLeft.func_78790_a(0.0F, 0.0F, 0.0F, 4, 2, 3, 0.0F);
    sideFront = new ModelRenderer(this, 34, 0);
    sideFront.func_78793_a(-7.0F, 16.0F, -7.0F);
    sideFront.func_78790_a(0.0F, 0.0F, 0.0F, 14, 8, 1, 0.0F);
    silver7 = new ModelRenderer(this, 0, 22);
    silver7.func_78793_a(-0.5F, 19.0F, 2.0F);
    silver7.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
    spoutUpperRight = new ModelRenderer(this, 15, 3);
    spoutUpperRight.func_78793_a(-8.0F, 14.0F, -1.5F);
    spoutUpperRight.func_78790_a(0.0F, 0.0F, 0.0F, 4, 2, 3, 0.0F);
    sideBack = new ModelRenderer(this, 34, 0);
    sideBack.func_78793_a(-7.0F, 16.0F, 6.0F);
    sideBack.func_78790_a(0.0F, 0.0F, 0.0F, 14, 8, 1, 0.0F);
    spoutUpperFront = new ModelRenderer(this, 15, 9);
    spoutUpperFront.func_78793_a(-1.5F, 14.0F, -8.0F);
    spoutUpperFront.func_78790_a(0.0F, 0.0F, 0.0F, 3, 2, 4, 0.0F);
    spoutLowerLeft = new ModelRenderer(this, 15, 0);
    spoutLowerLeft.func_78793_a(4.2F, 16.0F, -0.5F);
    spoutLowerLeft.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
    silver8 = new ModelRenderer(this, 0, 25);
    silver8.func_78793_a(-1.2F, 19.0F, -0.3F);
    silver8.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
    silver3 = new ModelRenderer(this, 0, 9);
    silver3.func_78793_a(-3.8F, 19.1F, 3.1F);
    silver3.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
    silver6 = new ModelRenderer(this, 0, 19);
    silver6.func_78793_a(-4.6F, 19.1F, -1.6F);
    silver6.func_78790_a(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
    spoutLowerBack = new ModelRenderer(this, 15, 0);
    spoutLowerBack.func_78793_a(-0.5F, 16.0F, 4.2F);
    spoutLowerBack.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
    silver1 = new ModelRenderer(this, 0, 3);
    silver1.func_78793_a(-2.2F, 19.3F, -3.9F);
    silver1.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
    silver4 = new ModelRenderer(this, 0, 13);
    silver4.func_78793_a(-3.4F, 18.8F, 0.9F);
    silver4.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
    spoutUpperBack = new ModelRenderer(this, 15, 9);
    spoutUpperBack.func_78793_a(-1.5F, 14.0F, 4.0F);
    spoutUpperBack.func_78790_a(0.0F, 0.0F, 0.0F, 3, 2, 4, 0.0F);
    sideLeft = new ModelRenderer(this, 38, 10);
    sideLeft.func_78793_a(6.0F, 16.0F, -6.0F);
    sideLeft.func_78790_a(0.0F, 0.0F, 0.0F, 1, 8, 12, 0.0F);
    silver5 = new ModelRenderer(this, 0, 16);
    silver5.func_78793_a(1.6F, 19.0F, -0.1F);
    silver5.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
    
    silver = new ModelRenderer[] { silver1, silver2, silver3, silver4, silver5, silver6, silver7, silver8 };
  }
  


  public void render(net.minecraft.entity.Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockSilverVat.TileEntitySilverVat te)
  {
    base.func_78785_a(f5);
    sideRight.func_78785_a(f5);
    sideFront.func_78785_a(f5);
    sideBack.func_78785_a(f5);
    sideLeft.func_78785_a(f5);
    
    boolean isWorld = (te != null) && (te.func_145831_w() != null);
    
    if ((!isWorld) || (te.func_145831_w().func_147439_a(field_145851_c - 1, field_145848_d, field_145849_e).func_149716_u())) {
      spoutUpperLeft.func_78785_a(f5);
      spoutLowerLeft.func_78785_a(f5);
    }
    
    if ((!isWorld) || (te.func_145831_w().func_147439_a(field_145851_c + 1, field_145848_d, field_145849_e).func_149716_u())) {
      spoutUpperRight.func_78785_a(f5);
      spoutLowerRight.func_78785_a(f5);
    }
    if ((!isWorld) || (te.func_145831_w().func_147439_a(field_145851_c, field_145848_d, field_145849_e - 1).func_149716_u())) {
      spoutUpperFront.func_78785_a(f5);
      spoutLowerFront.func_78785_a(f5);
    }
    if ((!isWorld) || (te.func_145831_w().func_147439_a(field_145851_c, field_145848_d, field_145849_e + 1).func_149716_u())) {
      spoutUpperBack.func_78785_a(f5);
      spoutLowerBack.func_78785_a(f5);
    }
    
    int capacity = isWorld ? 0 : (te.func_70301_a(0) != null) && (func_70301_a0field_77994_a > 0) ? Math.max(func_70301_a0field_77994_a / 8, 1) : 0;
    
    for (int i = 0; i < capacity; i++) {
      silver[i].func_78785_a(f5);
    }
  }
  
  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  

  public void setCapactiy(int i)
  {
    capacity = i;
  }
}
