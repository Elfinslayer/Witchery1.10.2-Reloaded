package com.emoniph.witchery.client.model;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.blocks.BlockWitchesOven;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class ModelFumeFunnel
  extends ModelBase
{
  ModelRenderer chimney;
  ModelRenderer chimneyTop;
  ModelRenderer base;
  ModelRenderer body;
  ModelRenderer tubeLeft;
  ModelRenderer tubeRight;
  ModelRenderer pipeTop2;
  ModelRenderer pipeTop3;
  ModelRenderer pipeTop4;
  ModelRenderer pipeTop5;
  ModelRenderer pipeBottom1;
  ModelRenderer pipeBottom2;
  ModelRenderer pipeBottom3;
  ModelRenderer pipeBottom4;
  ModelRenderer pipeTop1;
  ModelRenderer top1;
  ModelRenderer pipeBottom5;
  ModelRenderer top2;
  ModelRenderer filterLeft;
  ModelRenderer filterRight;
  ModelRenderer filterMid;
  ModelRenderer filterCase;
  final boolean filtered;
  
  public ModelFumeFunnel(boolean filtered)
  {
    this.filtered = filtered;
    field_78090_t = 64;
    field_78089_u = 64;
    
    base = new ModelRenderer(this, 0, 51);
    base.func_78789_a(0.0F, 0.0F, 0.0F, 12, 1, 12);
    base.func_78793_a(-6.0F, 23.0F, -6.0F);
    base.func_78787_b(64, 64);
    base.field_78809_i = true;
    setRotation(base, 0.0F, 0.0F, 0.0F);
    body = new ModelRenderer(this, 4, 27);
    body.func_78789_a(0.0F, 0.0F, 0.0F, 10, 11, 10);
    body.func_78793_a(-5.0F, 12.0F, -5.0F);
    body.func_78787_b(64, 64);
    body.field_78809_i = true;
    setRotation(body, 0.0F, 0.0F, 0.0F);
    tubeLeft = new ModelRenderer(this, 1, 18);
    tubeLeft.func_78789_a(0.0F, 0.0F, 0.0F, 5, 2, 2);
    tubeLeft.func_78793_a(-10.0F, 17.0F, -1.0F);
    tubeLeft.func_78787_b(64, 64);
    tubeLeft.field_78809_i = true;
    setRotation(tubeLeft, 0.0F, 0.0F, 0.0F);
    tubeRight = new ModelRenderer(this, 1, 18);
    tubeRight.func_78789_a(0.0F, 1.0F, 0.0F, 5, 2, 2);
    tubeRight.func_78793_a(5.0F, 18.0F, 1.0F);
    tubeRight.func_78787_b(64, 64);
    tubeRight.field_78809_i = true;
    setRotation(tubeRight, 0.0F, 0.0F, 0.0F);
    pipeTop2 = new ModelRenderer(this, 0, 0);
    pipeTop2.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 6);
    pipeTop2.func_78793_a(-4.0F, 8.0F, -3.0F);
    pipeTop2.func_78787_b(64, 64);
    pipeTop2.field_78809_i = true;
    setRotation(pipeTop2, 0.0F, 0.0F, 0.0F);
    pipeTop3 = new ModelRenderer(this, 0, 0);
    pipeTop3.func_78789_a(0.0F, 0.0F, 0.0F, 11, 1, 1);
    pipeTop3.func_78793_a(-3.0F, 8.0F, -3.0F);
    pipeTop3.func_78787_b(64, 64);
    pipeTop3.field_78809_i = true;
    setRotation(pipeTop3, 0.0F, 0.0F, 0.0F);
    pipeTop4 = new ModelRenderer(this, 0, 0);
    pipeTop4.func_78789_a(0.0F, 0.0F, 0.0F, 1, 11, 1);
    pipeTop4.func_78793_a(7.0F, 9.0F, -3.0F);
    pipeTop4.func_78787_b(64, 64);
    pipeTop4.field_78809_i = true;
    setRotation(pipeTop4, 0.0F, 0.0F, 0.0F);
    pipeTop5 = new ModelRenderer(this, 0, 0);
    pipeTop5.func_78789_a(0.0F, 0.0F, 0.0F, 2, 3, 3);
    pipeTop5.func_78793_a(5.0F, 18.0F, -4.0F);
    pipeTop5.func_78787_b(64, 64);
    pipeTop5.field_78809_i = true;
    setRotation(pipeTop5, 0.0F, 0.0F, 0.0F);
    pipeBottom1 = new ModelRenderer(this, 0, 0);
    pipeBottom1.func_78789_a(0.0F, 0.0F, 0.0F, 2, 1, 1);
    pipeBottom1.func_78793_a(-7.0F, 13.0F, -3.0F);
    pipeBottom1.func_78787_b(64, 64);
    pipeBottom1.field_78809_i = true;
    setRotation(pipeBottom1, 0.0F, 0.0F, 0.0F);
    pipeBottom2 = new ModelRenderer(this, 0, 0);
    pipeBottom2.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 4);
    pipeBottom2.func_78793_a(-7.0F, 20.0F, -7.0F);
    pipeBottom2.func_78787_b(64, 64);
    pipeBottom2.field_78809_i = true;
    setRotation(pipeBottom2, 0.0F, 0.0F, 0.0F);
    pipeBottom3 = new ModelRenderer(this, 0, 0);
    pipeBottom3.func_78789_a(0.0F, 0.0F, 0.0F, 5, 1, 1);
    pipeBottom3.func_78793_a(-6.0F, 20.0F, -7.0F);
    pipeBottom3.func_78787_b(64, 64);
    pipeBottom3.field_78809_i = true;
    setRotation(pipeBottom3, 0.0F, 0.0F, 0.0F);
    pipeBottom4 = new ModelRenderer(this, 0, 0);
    pipeBottom4.func_78789_a(0.0F, 0.0F, 0.0F, 1, 3, 1);
    pipeBottom4.func_78793_a(-2.0F, 21.0F, -7.0F);
    pipeBottom4.func_78787_b(64, 64);
    pipeBottom4.field_78809_i = true;
    setRotation(pipeBottom4, 0.0F, 0.0F, 0.0F);
    pipeTop1 = new ModelRenderer(this, 0, 0);
    pipeTop1.func_78789_a(0.0F, 0.0F, 0.0F, 1, 3, 1);
    pipeTop1.func_78793_a(-4.0F, 8.0F, 3.0F);
    pipeTop1.func_78787_b(64, 64);
    pipeTop1.field_78809_i = true;
    setRotation(pipeTop1, 0.0F, 0.0F, 0.0F);
    top1 = new ModelRenderer(this, 0, 51);
    top1.func_78789_a(0.0F, 0.0F, 0.0F, 12, 1, 12);
    top1.func_78793_a(-6.0F, 11.0F, -6.0F);
    top1.func_78787_b(64, 64);
    top1.field_78809_i = true;
    setRotation(top1, 0.0F, 0.0F, 0.0F);
    pipeBottom5 = new ModelRenderer(this, 0, 0);
    pipeBottom5.func_78789_a(0.0F, 0.0F, 0.0F, 1, 7, 1);
    pipeBottom5.func_78793_a(-7.0F, 14.0F, -3.0F);
    pipeBottom5.func_78787_b(64, 64);
    pipeBottom5.field_78809_i = true;
    setRotation(pipeBottom5, 0.0F, 0.0F, 0.0F);
    top2 = new ModelRenderer(this, 37, 55);
    top2.func_78789_a(0.0F, 0.0F, 0.0F, 6, 1, 6);
    top2.func_78793_a(-3.0F, 10.0F, -3.0F);
    top2.func_78787_b(64, 64);
    top2.field_78809_i = true;
    setRotation(top2, 0.0F, 0.0F, 0.0F);
    filterLeft = new ModelRenderer(this, 0, 0);
    filterLeft.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 2);
    filterLeft.func_78793_a(-4.0F, 14.0F, -7.0F);
    filterLeft.func_78787_b(64, 64);
    filterLeft.field_78809_i = true;
    setRotation(filterLeft, 0.0F, 0.0F, 0.0F);
    filterRight = new ModelRenderer(this, 0, 0);
    filterRight.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 2);
    filterRight.func_78793_a(3.0F, 14.0F, -7.0F);
    filterRight.func_78787_b(64, 64);
    filterRight.field_78809_i = true;
    setRotation(filterRight, 0.0F, 0.0F, 0.0F);
    filterMid = new ModelRenderer(this, 24, 0);
    filterMid.func_78789_a(0.0F, 0.0F, 0.0F, 6, 1, 1);
    filterMid.func_78793_a(-3.0F, 14.0F, -7.0F);
    filterMid.func_78787_b(64, 64);
    filterMid.field_78809_i = true;
    setRotation(filterMid, 0.0F, 0.0F, 0.0F);
    filterCase = new ModelRenderer(this, 25, 3);
    filterCase.func_78789_a(0.0F, 0.0F, 0.0F, 4, 3, 2);
    filterCase.func_78793_a(-2.0F, 13.0F, -8.0F);
    filterCase.func_78787_b(64, 64);
    filterCase.field_78809_i = true;
    setRotation(filterCase, 0.0F, 0.0F, 0.0F);
    
    chimney = new ModelRenderer(this, 27, 13);
    chimney.func_78789_a(0.0F, 0.0F, 0.0F, 4, 10, 4);
    chimney.func_78793_a(-2.0F, 14.0F, 3.0F);
    chimney.func_78787_b(64, 128);
    chimney.field_78809_i = true;
    setRotation(chimney, 0.0F, 0.0F, 0.0F);
    chimneyTop = new ModelRenderer(this, 40, 7);
    chimneyTop.func_78789_a(0.0F, 0.0F, 0.0F, 6, 3, 6);
    chimneyTop.func_78793_a(-3.0F, 11.0F, 2.0F);
    chimneyTop.func_78787_b(64, 128);
    chimneyTop.field_78809_i = true;
    setRotation(chimneyTop, 0.0F, 0.0F, 0.0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, TileEntity tile) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    
    boolean validTileEntity = (tile != null) && (tile.func_145831_w() != null);
    
    boolean renderWideBody = true;
    
    if (validTileEntity) {
      int meta = tile.func_145832_p();
      switch (meta) {
      case 2: 
        renderLeftGubbinsIfConnected(tile.func_145831_w(), field_145851_c + 1, field_145848_d, field_145849_e, f5);
        renderRightGubbinsIfConnected(tile.func_145831_w(), field_145851_c - 1, field_145848_d, field_145849_e, f5);
        break;
      case 5: 
        renderLeftGubbinsIfConnected(tile.func_145831_w(), field_145851_c, field_145848_d, field_145849_e + 1, f5);
        renderRightGubbinsIfConnected(tile.func_145831_w(), field_145851_c, field_145848_d, field_145849_e - 1, f5);
        break;
      case 3: 
        renderLeftGubbinsIfConnected(tile.func_145831_w(), field_145851_c - 1, field_145848_d, field_145849_e, f5);
        renderRightGubbinsIfConnected(tile.func_145831_w(), field_145851_c + 1, field_145848_d, field_145849_e, f5);
        break;
      case 4: 
        renderLeftGubbinsIfConnected(tile.func_145831_w(), field_145851_c, field_145848_d, field_145849_e - 1, f5);
        renderRightGubbinsIfConnected(tile.func_145831_w(), field_145851_c, field_145848_d, field_145849_e + 1, f5);
      }
      
      
      Block block = tile.func_145831_w().func_147439_a(field_145851_c, field_145848_d - 1, field_145849_e);
      if (BlockWitchesOven.isOven(block)) {
        chimney.func_78785_a(f5);
        chimneyTop.func_78785_a(f5);
        renderWideBody = false;
      }
    }
    
    if (renderWideBody) {
      base.func_78785_a(f5);
      body.func_78785_a(f5);
      top1.func_78785_a(f5);
      top2.func_78785_a(f5);
      
      if ((filtered) || ((validTileEntity) && (tile.func_145838_q() == BlocksOVEN_FUMEFUNNEL_FILTERED))) {
        filterLeft.func_78785_a(f5);
        filterRight.func_78785_a(f5);
        filterMid.func_78785_a(f5);
        filterCase.func_78785_a(f5);
      }
    }
  }
  
  private void renderLeftGubbinsIfConnected(World world, int xCoord, int yCoord, int zCoord, float f5) {
    Block block = world.func_147439_a(xCoord, yCoord, zCoord);
    if (BlockWitchesOven.isOven(block)) {
      tubeLeft.func_78785_a(f5);
      pipeTop1.func_78785_a(f5);
      pipeTop2.func_78785_a(f5);
      pipeTop3.func_78785_a(f5);
      pipeTop4.func_78785_a(f5);
      pipeTop5.func_78785_a(f5);
    }
  }
  
  private void renderRightGubbinsIfConnected(World world, int xCoord, int yCoord, int zCoord, float f5) {
    Block block = world.func_147439_a(xCoord, yCoord, zCoord);
    if (BlockWitchesOven.isOven(block)) {
      tubeRight.func_78785_a(f5);
      pipeBottom1.func_78785_a(f5);
      pipeBottom2.func_78785_a(f5);
      pipeBottom3.func_78785_a(f5);
      pipeBottom4.func_78785_a(f5);
      pipeBottom5.func_78785_a(f5);
    }
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
  }
}
