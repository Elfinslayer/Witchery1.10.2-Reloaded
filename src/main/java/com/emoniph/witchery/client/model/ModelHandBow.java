package com.emoniph.witchery.client.model;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.BoltType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelHandBow extends ModelBase
{
  ModelRenderer stockTop;
  ModelRenderer stockBottom;
  ModelRenderer stockCatch;
  ModelRenderer grip;
  ModelRenderer cross;
  ModelRenderer drawnCrossOuterR;
  ModelRenderer drawnCrossInnerR;
  ModelRenderer drawnCrossOuterL;
  ModelRenderer drawnCrossInnerL;
  ModelRenderer drawnStringInnerR;
  ModelRenderer drawnStringMidR;
  ModelRenderer drawnStringOuterR;
  ModelRenderer drawnStringInnerL;
  ModelRenderer drawnStringMidL;
  ModelRenderer drawnStringOuterL;
  ModelRenderer drawnStringCenter;
  ModelRenderer boltStake;
  ModelRenderer boltDraining;
  ModelRenderer boltHoly;
  ModelRenderer boltSplitting;
  ModelRenderer boltSplitting2;
  ModelRenderer boltSilver;
  
  public ModelHandBow()
  {
    field_78090_t = 64;
    field_78089_u = 32;
    
    stockTop = new ModelRenderer(this, 2, 2);
    stockTop.func_78789_a(-1.0F, 0.0F, -5.0F, 2, 1, 7);
    stockTop.func_78793_a(0.0F, 0.0F, 0.0F);
    stockTop.func_78787_b(64, 32);
    stockTop.field_78809_i = true;
    setRotation(stockTop, 0.0F, 0.0F, 0.0F);
    stockBottom = new ModelRenderer(this, 0, 10);
    stockBottom.func_78789_a(-0.5F, 0.0F, -6.0F, 1, 1, 8);
    stockBottom.func_78793_a(0.0F, 1.0F, 0.0F);
    stockBottom.func_78787_b(64, 32);
    stockBottom.field_78809_i = true;
    setRotation(stockBottom, 0.0F, 0.0F, 0.0F);
    stockCatch = new ModelRenderer(this, 1, 11);
    stockCatch.func_78789_a(-0.5F, 0.0F, 0.0F, 1, 1, 1);
    stockCatch.func_78793_a(0.0F, -1.0F, 1.0F);
    stockCatch.func_78787_b(64, 32);
    stockCatch.field_78809_i = true;
    setRotation(stockCatch, 0.0F, 0.0F, 0.0F);
    grip = new ModelRenderer(this, 0, 3);
    grip.func_78789_a(-0.5F, 0.0F, -1.0F, 1, 3, 2);
    grip.func_78793_a(0.0F, 2.0F, 0.0F);
    grip.func_78787_b(64, 32);
    grip.field_78809_i = true;
    setRotation(grip, 0.0F, 0.0F, 0.0F);
    cross = new ModelRenderer(this, 1, 19);
    cross.func_78789_a(-3.0F, 0.0F, 0.0F, 6, 1, 2);
    cross.func_78793_a(0.0F, 0.0F, -7.0F);
    cross.func_78787_b(64, 32);
    cross.field_78809_i = true;
    setRotation(cross, 0.0F, 0.0F, 0.0F);
    drawnCrossOuterR = new ModelRenderer(this, 0, 14);
    drawnCrossOuterR.func_78789_a(-1.0F, 0.0F, 0.0F, 1, 1, 2);
    drawnCrossOuterR.func_78793_a(-4.0F, 0.0F, -4.0F);
    drawnCrossOuterR.func_78787_b(64, 32);
    drawnCrossOuterR.field_78809_i = true;
    setRotation(drawnCrossOuterR, 0.0F, 0.0F, 0.0F);
    drawnCrossInnerR = new ModelRenderer(this, 0, 14);
    drawnCrossInnerR.func_78789_a(-1.0F, 0.0F, 0.0F, 1, 1, 2);
    drawnCrossInnerR.func_78793_a(-3.0F, 0.0F, -6.0F);
    drawnCrossInnerR.func_78787_b(64, 32);
    drawnCrossInnerR.field_78809_i = true;
    setRotation(drawnCrossInnerR, 0.0F, 0.0F, 0.0F);
    drawnCrossOuterL = new ModelRenderer(this, 0, 14);
    drawnCrossOuterL.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 2);
    drawnCrossOuterL.func_78793_a(4.0F, 0.0F, -4.0F);
    drawnCrossOuterL.func_78787_b(64, 32);
    drawnCrossOuterL.field_78809_i = true;
    setRotation(drawnCrossOuterL, 0.0F, 0.0F, 0.0F);
    drawnCrossInnerL = new ModelRenderer(this, 0, 14);
    drawnCrossInnerL.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 2);
    drawnCrossInnerL.func_78793_a(3.0F, 0.0F, -6.0F);
    drawnCrossInnerL.func_78787_b(64, 32);
    drawnCrossInnerL.field_78809_i = true;
    setRotation(drawnCrossInnerL, 0.0F, 0.0F, 0.0F);
    drawnStringInnerR = new ModelRenderer(this, 0, 0);
    drawnStringInnerR.func_78789_a(-1.0F, 0.0F, 0.0F, 1, 1, 1);
    drawnStringInnerR.func_78793_a(-2.0F, 0.0F, -1.0F);
    drawnStringInnerR.func_78787_b(64, 32);
    drawnStringInnerR.field_78809_i = true;
    setRotation(drawnStringInnerR, 0.0F, 0.0F, 0.0F);
    drawnStringMidR = new ModelRenderer(this, 0, 0);
    drawnStringMidR.func_78789_a(-1.0F, 0.0F, 0.0F, 1, 1, 1);
    drawnStringMidR.func_78793_a(-1.0F, 0.0F, 0.0F);
    drawnStringMidR.func_78787_b(64, 32);
    drawnStringMidR.field_78809_i = true;
    setRotation(drawnStringMidR, 0.0F, 0.0F, 0.0F);
    drawnStringOuterR = new ModelRenderer(this, 0, 0);
    drawnStringOuterR.func_78789_a(-1.0F, 0.0F, 0.0F, 1, 1, 1);
    drawnStringOuterR.func_78793_a(-3.0F, 0.0F, -2.0F);
    drawnStringOuterR.func_78787_b(64, 32);
    drawnStringOuterR.field_78809_i = true;
    setRotation(drawnStringOuterR, 0.0F, 0.0F, 0.0F);
    drawnStringInnerL = new ModelRenderer(this, 0, 0);
    drawnStringInnerL.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 1);
    drawnStringInnerL.func_78793_a(2.0F, 0.0F, -1.0F);
    drawnStringInnerL.func_78787_b(64, 32);
    drawnStringInnerL.field_78809_i = true;
    setRotation(drawnStringInnerL, 0.0F, 0.0F, 0.0F);
    drawnStringMidL = new ModelRenderer(this, 0, 0);
    drawnStringMidL.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 1);
    drawnStringMidL.func_78793_a(1.0F, 0.0F, 0.0F);
    drawnStringMidL.func_78787_b(64, 32);
    drawnStringMidL.field_78809_i = true;
    setRotation(drawnStringMidL, 0.0F, 0.0F, 0.0F);
    drawnStringOuterL = new ModelRenderer(this, 0, 0);
    drawnStringOuterL.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 1);
    drawnStringOuterL.func_78793_a(3.0F, 0.0F, -2.0F);
    drawnStringOuterL.func_78787_b(64, 32);
    drawnStringOuterL.field_78809_i = true;
    setRotation(drawnStringOuterL, 0.0F, 0.0F, 0.0F);
    drawnStringCenter = new ModelRenderer(this, 4, 0);
    drawnStringCenter.func_78789_a(-1.5F, 0.0F, -0.5F, 3, 1, 1);
    drawnStringCenter.func_78793_a(0.0F, -0.1F, 1.0F);
    drawnStringCenter.func_78787_b(64, 32);
    drawnStringCenter.field_78809_i = true;
    setRotation(drawnStringCenter, 0.0F, 0.0174533F, 0.0F);
    
    boltStake = new ModelRenderer(this, 0, 22);
    boltStake.func_78789_a(-0.5F, 0.5F, -6.0F, 1, 1, 9);
    boltStake.func_78793_a(0.0F, -1.0F, -2.0F);
    boltStake.func_78787_b(64, 32);
    boltStake.field_78809_i = true;
    setRotation(boltStake, 0.0F, 0.0F, 0.0F);
    
    boltDraining = new ModelRenderer(this, 20, 22);
    boltDraining.func_78789_a(-0.5F, 0.5F, -6.0F, 1, 1, 9);
    boltDraining.func_78793_a(0.0F, -1.0F, -2.0F);
    boltDraining.func_78787_b(64, 32);
    boltDraining.field_78809_i = true;
    setRotation(boltDraining, 0.0F, 0.0F, 0.0F);
    
    boltHoly = new ModelRenderer(this, 40, 22);
    boltHoly.func_78789_a(-0.5F, 0.5F, -6.0F, 1, 1, 9);
    boltHoly.func_78793_a(0.0F, -1.0F, -2.0F);
    boltHoly.func_78787_b(64, 32);
    boltHoly.field_78809_i = true;
    setRotation(boltHoly, 0.0F, 0.0F, 0.0F);
    
    boltSplitting = new ModelRenderer(this, 20, 12);
    boltSplitting.func_78789_a(-0.5F, 0.5F, -6.0F, 1, 1, 9);
    boltSplitting.func_78793_a(0.0F, -1.0F, -2.0F);
    boltSplitting.func_78787_b(64, 32);
    boltSplitting.field_78809_i = true;
    setRotation(boltSplitting, 0.0F, 0.0F, 0.0F);
    
    boltSplitting2 = new ModelRenderer(this, 17, 11);
    boltSplitting2.func_78789_a(-0.5F, 0.5F, -6.0F, 2, 1, 4);
    boltSplitting2.func_78793_a(-0.5F, -1.5F, -1.0F);
    boltSplitting2.func_78787_b(64, 32);
    boltSplitting2.field_78809_i = true;
    setRotation(boltSplitting2, 0.0F, 0.0F, 0.0F);
    

    boltSilver = new ModelRenderer(this, 40, 12);
    boltSilver.func_78789_a(-0.5F, 0.5F, -6.0F, 1, 1, 9);
    boltSilver.func_78793_a(0.0F, -1.0F, -2.0F);
    boltSilver.func_78787_b(64, 32);
    boltSilver.field_78809_i = true;
    setRotation(boltSplitting, 0.0F, 0.0F, 0.0F);
    
    cross.field_78797_d = -0.3F;
    drawnCrossInnerL.field_78797_d = (drawnCrossInnerR.field_78797_d = -0.15F);
    drawnStringMidL.field_78797_d = (drawnStringMidR.field_78797_d = -0.1F);
    drawnStringInnerL.field_78797_d = (drawnStringInnerR.field_78797_d = -0.05F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, ItemGeneral.BoltType boltType, int useCount) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    stockTop.func_78785_a(f5);
    stockBottom.func_78785_a(f5);
    stockCatch.func_78785_a(f5);
    grip.func_78785_a(f5);
    cross.func_78785_a(f5);
    
    if (useCount > 10) {
      drawnCrossInnerL.field_78798_e = (drawnCrossInnerR.field_78798_e = -6.0F);
      drawnCrossOuterL.field_78798_e = (drawnCrossOuterR.field_78798_e = -4.0F);
      
      drawnStringInnerL.field_78798_e = (drawnStringInnerR.field_78798_e = -1.0F);
      drawnStringMidL.field_78798_e = (drawnStringMidR.field_78798_e = 0.0F);
      drawnStringOuterL.field_78798_e = (drawnStringOuterR.field_78798_e = -2.0F);
      drawnStringCenter.field_78798_e = 1.0F;
    } else if (useCount > 5) {
      drawnCrossInnerL.field_78798_e = (drawnCrossInnerR.field_78798_e = -6.0F);
      drawnCrossOuterL.field_78798_e = (drawnCrossOuterR.field_78798_e = -5.0F);
      
      drawnStringInnerL.field_78798_e = (drawnStringInnerR.field_78798_e = -2.0F);
      drawnStringMidL.field_78798_e = (drawnStringMidR.field_78798_e = -2.0F);
      drawnStringOuterL.field_78798_e = (drawnStringOuterR.field_78798_e = -3.0F);
      
      drawnStringCenter.field_78798_e = -1.0F;
    } else if (useCount == 0) {
      drawnCrossInnerL.field_78798_e = (drawnCrossInnerR.field_78798_e = -7.0F);
      drawnCrossOuterL.field_78798_e = (drawnCrossOuterR.field_78798_e = -6.0F);
      
      drawnStringInnerL.field_78798_e = (drawnStringInnerR.field_78798_e = -4.0F);
      drawnStringMidL.field_78798_e = (drawnStringMidR.field_78798_e = -4.0F);
      drawnStringOuterL.field_78798_e = (drawnStringOuterR.field_78798_e = -4.0F);
      drawnStringCenter.field_78798_e = -3.25F;
    }
    

    drawnCrossOuterR.func_78785_a(f5);
    drawnCrossOuterL.func_78785_a(f5);
    drawnCrossInnerR.func_78785_a(f5);
    drawnCrossInnerL.func_78785_a(f5);
    
    drawnStringInnerR.func_78785_a(f5);
    drawnStringMidR.func_78785_a(f5);
    drawnStringOuterR.func_78785_a(f5);
    drawnStringInnerL.func_78785_a(f5);
    drawnStringMidL.func_78785_a(f5);
    drawnStringOuterL.func_78785_a(f5);
    drawnStringCenter.func_78785_a(f5);
    
    if (boltType == ItemsGENERIC.itemBoltStake) {
      boltStake.func_78785_a(f5);
    } else if (boltType == ItemsGENERIC.itemBoltAntiMagic) {
      boltDraining.func_78785_a(f5);
    } else if (boltType == ItemsGENERIC.itemBoltHoly) {
      boltHoly.func_78785_a(f5);
    } else if (boltType == ItemsGENERIC.itemBoltSilver) {
      boltSilver.func_78785_a(f5);
    } else if (boltType == ItemsGENERIC.itemBoltSplitting) {
      boltSplitting.func_78785_a(f5);
      boltSplitting2.func_78785_a(f5);
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
