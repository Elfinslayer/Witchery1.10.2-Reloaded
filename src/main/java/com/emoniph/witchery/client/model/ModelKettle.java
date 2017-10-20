package com.emoniph.witchery.client.model;

import com.emoniph.witchery.blocks.BlockKettle.TileEntityKettle;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelKettle extends ModelBase
{
  ModelRenderer sideFront;
  ModelRenderer sideBack;
  ModelRenderer sideLeft;
  ModelRenderer sideRight;
  ModelRenderer sideBottom;
  ModelRenderer crossbar;
  ModelRenderer[] liquid;
  ModelRenderer chainLF;
  ModelRenderer chainLB;
  ModelRenderer chainRF;
  ModelRenderer chainRB;
  ModelRenderer bottle1;
  ModelRenderer bottle2;
  private int ticks;
  
  public ModelKettle()
  {
    field_78090_t = 64;
    field_78089_u = 64;
    
    func_78085_a("bottle1.bottle1Body", 52, 5);
    func_78085_a("bottle1.bottle1Neck", 60, 3);
    func_78085_a("bottle1.bottle1Top", 56, 0);
    
    func_78085_a("bottle2.bottle2Body", 52, 5);
    func_78085_a("bottle2.bottle2Neck", 60, 3);
    func_78085_a("bottle2.bottle2Top", 56, 0);
    
    sideFront = new ModelRenderer(this, 0, 0);
    sideFront.func_78789_a(0.0F, 0.0F, 0.0F, 9, 6, 1);
    sideFront.func_78793_a(-5.0F, 18.0F, -5.0F);
    sideFront.func_78787_b(64, 64);
    sideFront.field_78809_i = true;
    setRotation(sideFront, 0.0F, 0.0F, 0.0F);
    sideBack = new ModelRenderer(this, 0, 0);
    sideBack.func_78789_a(0.0F, 0.0F, 0.0F, 9, 6, 1);
    sideBack.func_78793_a(-4.0F, 18.0F, 4.0F);
    sideBack.func_78787_b(64, 64);
    sideBack.field_78809_i = true;
    setRotation(sideBack, 0.0F, 0.0F, 0.0F);
    sideLeft = new ModelRenderer(this, 0, 0);
    sideLeft.func_78789_a(0.0F, 0.0F, 0.0F, 9, 6, 1);
    sideLeft.func_78793_a(-5.0F, 18.0F, 5.0F);
    sideLeft.func_78787_b(64, 64);
    sideLeft.field_78809_i = true;
    setRotation(sideLeft, 0.0F, 1.570796F, 0.0F);
    sideRight = new ModelRenderer(this, 0, 0);
    sideRight.func_78789_a(0.0F, 0.0F, 0.0F, 9, 6, 1);
    sideRight.func_78793_a(4.0F, 18.0F, 4.0F);
    sideRight.func_78787_b(64, 64);
    sideRight.field_78809_i = true;
    setRotation(sideRight, 0.0F, 1.570796F, 0.0F);
    sideBottom = new ModelRenderer(this, 13, 0);
    sideBottom.func_78789_a(0.0F, 0.0F, 0.0F, 8, 1, 8);
    sideBottom.func_78793_a(-4.0F, 23.0F, -4.0F);
    sideBottom.func_78787_b(64, 64);
    sideBottom.field_78809_i = true;
    setRotation(sideBottom, 0.0F, 0.0F, 0.0F);
    crossbar = new ModelRenderer(this, 0, 10);
    
    crossbar.func_78789_a(-4.0F, 0.0F, 0.0F, 24, 2, 2);
    crossbar.func_78793_a(-8.0F, 8.05F, -1.0F);
    crossbar.func_78787_b(64, 64);
    crossbar.field_78809_i = true;
    setRotation(crossbar, 0.0F, 0.0F, 0.0F);
    liquid = new ModelRenderer[8];
    for (int i = 0; i < liquid.length; i++) {
      liquid[i] = new ModelRenderer(this, i < 4 ? i * 16 - 8 : (i - 4) * 16 - 8, i < 4 ? 16 : 32);
      liquid[i].func_78789_a(0.0F, 0.0F, 0.0F, 8, 0, 8);
      liquid[i].func_78793_a(-4.0F, 20.0F, -4.0F);
      liquid[i].func_78787_b(64, 64);
      liquid[i].field_78809_i = true;
      setRotation(liquid[i], 0.0F, 0.0F, 0.0F);
    }
    chainLF = new ModelRenderer(this, 0, 15);
    chainLF.func_78789_a(0.0F, -0.5F, 0.0F, 11, 1, 0);
    chainLF.func_78793_a(0.0F, 9.0F, 0.0F);
    chainLF.func_78787_b(64, 64);
    chainLF.field_78809_i = true;
    
    chainLB = new ModelRenderer(this, 0, 15);
    chainLB.func_78789_a(0.0F, -0.5F, 0.0F, 11, 1, 0);
    chainLB.func_78793_a(0.0F, 9.0F, 0.0F);
    chainLB.func_78787_b(64, 64);
    chainLB.field_78809_i = true;
    
    chainRF = new ModelRenderer(this, 0, 15);
    chainRF.func_78789_a(0.0F, -0.5F, 0.0F, 11, 1, 0);
    chainRF.func_78793_a(0.0F, 9.0F, 0.0F);
    chainRF.func_78787_b(64, 64);
    chainRF.field_78809_i = true;
    
    chainRB = new ModelRenderer(this, 0, 15);
    chainRB.func_78789_a(0.0F, -0.5F, 0.0F, 11, 1, 0);
    chainRB.func_78793_a(0.0F, 9.0F, 0.0F);
    chainRB.func_78787_b(64, 64);
    chainRB.field_78809_i = true;
    
    chainRB.field_78809_i = false;
    
    setRotation(chainRB, 0.0F, -0.4F, 1.1F);
    setRotation(chainLB, 0.0F, 0.4F, 1.1F);
    setRotation(chainRF, 0.0F, 0.4F, 2.05F);
    setRotation(chainLF, 0.0F, -2.75F, -1.1F);
    
    bottle1 = new ModelRenderer(this, "bottle1");
    bottle1.func_78793_a(-4.0F, 13.0F, -6.0F);
    setRotation(bottle1, 0.0F, 0.0F, 0.0F);
    bottle1.field_78809_i = true;
    bottle1.func_78786_a("bottle1Body", 0.0F, 2.0F, 0.0F, 3, 3, 3);
    bottle1.func_78786_a("bottle1Neck", 1.0F, 1.0F, 1.0F, 1, 1, 1);
    bottle1.func_78786_a("bottle1Top", 0.5F, 0.0F, 0.5F, 2, 1, 2);
    
    bottle2 = new ModelRenderer(this, "bottle2");
    bottle2.func_78793_a(0.0F, 13.0F, -6.0F);
    setRotation(bottle2, 0.0F, 0.0F, 0.0F);
    bottle2.field_78809_i = true;
    bottle2.func_78786_a("bottle2Body", 0.0F, 2.0F, 0.0F, 3, 3, 3);
    bottle2.func_78786_a("bottle2Neck", 1.0F, 1.0F, 1.0F, 1, 1, 1);
    bottle2.func_78786_a("bottle2Top", 0.5F, 0.0F, 0.5F, 2, 1, 2);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockKettle.TileEntityKettle kettleTileEntity)
  {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    sideFront.func_78785_a(f5);
    sideBack.func_78785_a(f5);
    sideLeft.func_78785_a(f5);
    sideRight.func_78785_a(f5);
    sideBottom.func_78785_a(f5);
    
    if ((kettleTileEntity != null) && (kettleTileEntity.func_145831_w() != null)) {
      int posX = MathHelper.func_76128_c(field_145851_c);
      int posY = MathHelper.func_76128_c(field_145848_d);
      int posZ = MathHelper.func_76128_c(field_145849_e);
      if (!kettleTileEntity.func_145831_w().func_147439_a(posX, posY + 1, posZ).func_149688_o().func_76220_a()) {
        crossbar.func_78785_a(f5);
      }
      
      chainLF.func_78785_a(f5);
      chainLB.func_78785_a(f5);
      chainRF.func_78785_a(f5);
      chainRB.func_78785_a(f5);
      
      int bottles = kettleTileEntity.bottleCount();
      if (bottles > 0) {
        bottle1.func_78785_a(f5);
        
        if (bottles > 1) {
          bottle2.func_78785_a(f5);
        }
      }
      

      setRotation(chainRB, 0.0F, -0.4F, 1.1F);
      setRotation(chainLB, 0.0F, 0.4F, 1.1F);
      setRotation(chainRF, 0.0F, 0.4F, 2.05F);
      setRotation(chainLF, 0.0F, -2.75F, -1.1F);
      
      if (kettleTileEntity.isFilled()) {
        if (ticks >= 79) {
          ticks = 0;
        }
        ticks += 1;
        
        int color = 0;
        float factor = 1.0F;
        if (kettleTileEntity.isRuined()) {
          color = -8429824;
          GL11.glColor4f(1.0F, 0.5F, 0.2F, 0.5F);
        } else if (kettleTileEntity.isReady()) {
          color = kettleTileEntity.getLiquidColor();
        } else if (kettleTileEntity.isBrewing()) {
          color = kettleTileEntity.getLiquidColor();
          factor = 0.5F;
        }
        if (color == 0) {
          color = -13148989;
          factor = 1.0F;
        }
        float red = (color >>> 16 & 0xFF) / 256.0F * factor;
        float green = (color >>> 8 & 0xFF) / 256.0F * factor;
        float blue = (color & 0xFF) / 256.0F * factor;
        GL11.glColor4f(red, green, blue, 1.0F);
        
        liquid[((int)Math.floor(ticks / 20))].func_78785_a(f5);
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
