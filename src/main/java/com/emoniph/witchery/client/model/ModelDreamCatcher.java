package com.emoniph.witchery.client.model;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockDreamCatcher.TileEntityDreamCatcher;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.DreamWeave;
import cpw.mods.fml.relauncher.Side;
import java.util.ArrayList;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
public class ModelDreamCatcher extends ModelBase
{
  final ModelRenderer frameLeft;
  final ModelRenderer frameRight;
  final ModelRenderer frameTop;
  final ModelRenderer frameBottom;
  final ModelRenderer[] nets;
  final ModelRenderer decoration;
  
  public ModelDreamCatcher()
  {
    field_78090_t = 32;
    field_78089_u = 32;
    
    frameLeft = new ModelRenderer(this, 0, 2);
    frameLeft.func_78789_a(0.0F, 0.0F, 0.0F, 1, 8, 1);
    frameLeft.func_78793_a(-4.0F, 10.0F, 7.0F);
    frameLeft.func_78787_b(32, 32);
    frameLeft.field_78809_i = true;
    setRotation(frameLeft, 0.0F, 0.0F, 0.0F);
    frameRight = new ModelRenderer(this, 0, 2);
    frameRight.func_78789_a(0.0F, 0.0F, 0.0F, 1, 8, 1);
    frameRight.func_78793_a(3.0F, 10.0F, 7.0F);
    frameRight.func_78787_b(32, 32);
    frameRight.field_78809_i = true;
    setRotation(frameRight, 0.0F, 0.0F, 0.0F);
    frameTop = new ModelRenderer(this, 0, 0);
    frameTop.func_78789_a(0.0F, 0.0F, 0.0F, 6, 1, 1);
    frameTop.func_78793_a(-3.0F, 10.0F, 7.0F);
    frameTop.func_78787_b(32, 32);
    frameTop.field_78809_i = true;
    setRotation(frameTop, 0.0F, 0.0F, 0.0F);
    frameBottom = new ModelRenderer(this, 0, 0);
    frameBottom.func_78789_a(0.0F, 0.0F, 0.0F, 6, 1, 1);
    frameBottom.func_78793_a(-3.0F, 17.0F, 7.0F);
    frameBottom.func_78787_b(32, 32);
    frameBottom.field_78809_i = true;
    setRotation(frameBottom, 0.0F, 0.0F, 0.0F);
    
    nets = new ModelRenderer[ItemsGENERIC.weaves.size()];
    for (int i = 0; i < ItemsGENERIC.weaves.size(); i++) {
      ItemGeneral.DreamWeave weave = (ItemGeneral.DreamWeave)ItemsGENERIC.weaves.get(i);
      nets[i] = new ModelRenderer(this, textureOffsetX, textureOffsetY);
      nets[i].func_78789_a(0.0F, 0.0F, 0.0F, 6, 6, 0);
      nets[i].func_78793_a(-3.0F, 11.0F, 8.0F);
      nets[i].func_78787_b(32, 32);
      nets[i].field_78809_i = true;
      setRotation(nets[i], 0.0F, 0.0F, 0.0F);
    }
    
    decoration = new ModelRenderer(this, 0, 12);
    decoration.func_78789_a(0.0F, 0.0F, 0.0F, 8, 6, 0);
    decoration.func_78793_a(-4.0F, 18.0F, 7.0F);
    decoration.func_78787_b(32, 32);
    decoration.field_78809_i = true;
    setRotation(decoration, 0.0F, 0.0F, 0.0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockDreamCatcher.TileEntityDreamCatcher tileEntity) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    frameLeft.func_78785_a(f5);
    frameRight.func_78785_a(f5);
    frameTop.func_78785_a(f5);
    frameBottom.func_78785_a(f5);
    
    ItemGeneral.DreamWeave weave = tileEntity.getWeave();
    if (weave != null) {
      nets[weaveID].func_78785_a(f5);
    }
    
    decoration.func_78785_a(f5);
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
