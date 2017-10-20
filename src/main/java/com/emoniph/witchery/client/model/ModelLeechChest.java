package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.model.ModelRenderer;

@cpw.mods.fml.relauncher.SideOnly(Side.CLIENT)
public class ModelLeechChest extends net.minecraft.client.model.ModelBase
{
  public ModelRenderer chestBelow;
  public ModelRenderer chestLidBL;
  public ModelRenderer chestLidFR;
  public ModelRenderer chestLidBR;
  public ModelRenderer chestLidFL;
  public ModelRenderer sac1;
  public ModelRenderer sac2;
  public ModelRenderer sac3;
  
  public ModelLeechChest()
  {
    field_78090_t = 64;
    field_78089_u = 64;
    
    chestBelow = new ModelRenderer(this, 0, 0);
    chestBelow.func_78789_a(0.0F, 0.0F, 0.0F, 14, 9, 14);
    chestBelow.func_78793_a(1.0F, 7.0F, 1.0F);
    chestBelow.func_78787_b(64, 64);
    chestBelow.field_78809_i = true;
    setRotation(chestBelow, 0.0F, 0.0F, 0.0F);
    chestLidBL = new ModelRenderer(this, 28, 24);
    chestLidBL.func_78789_a(-6.0F, -5.0F, -6.0F, 6, 5, 6);
    chestLidBL.func_78793_a(14.0F, 7.0F, 14.0F);
    chestLidBL.func_78787_b(64, 64);
    chestLidBL.field_78809_i = true;
    setRotation(chestLidBL, 0.0F, 0.0F, 0.0F);
    chestLidFR = new ModelRenderer(this, 0, 36);
    chestLidFR.func_78789_a(0.0F, -5.0F, 0.0F, 6, 5, 6);
    chestLidFR.func_78793_a(2.0F, 7.0F, 2.0F);
    chestLidFR.func_78787_b(64, 64);
    chestLidFR.field_78809_i = true;
    setRotation(chestLidFR, 0.0F, 0.0F, 0.0F);
    chestLidBR = new ModelRenderer(this, 0, 24);
    chestLidBR.func_78789_a(0.0F, -5.0F, -6.0F, 6, 5, 6);
    chestLidBR.func_78793_a(2.0F, 7.0F, 14.0F);
    chestLidBR.func_78787_b(64, 64);
    chestLidBR.field_78809_i = true;
    setRotation(chestLidBR, 0.0F, 0.0F, 0.0F);
    chestLidFL = new ModelRenderer(this, 28, 36);
    chestLidFL.func_78789_a(-6.0F, -5.0F, 0.0F, 6, 5, 6);
    chestLidFL.func_78793_a(14.0F, 7.0F, 2.0F);
    chestLidFL.func_78787_b(64, 64);
    chestLidFL.field_78809_i = true;
    setRotation(chestLidFL, 0.0F, 0.0F, 0.0F);
    sac1 = new ModelRenderer(this, 0, 8);
    sac1.func_78789_a(0.0F, 0.0F, 0.0F, 2, 3, 1);
    sac1.func_78793_a(3.0F, 8.0F, 0.0F);
    sac1.func_78787_b(64, 64);
    sac1.field_78809_i = true;
    setRotation(sac1, 0.0F, 0.0F, 0.0F);
    sac2 = new ModelRenderer(this, 0, 3);
    sac2.func_78789_a(0.0F, 0.0F, 0.0F, 3, 2, 1);
    sac2.func_78793_a(9.0F, 13.0F, 0.0F);
    sac2.func_78787_b(64, 64);
    sac2.field_78809_i = true;
    setRotation(sac2, 0.0F, 0.0F, 0.0F);
    sac3 = new ModelRenderer(this, 0, 0);
    sac3.func_78789_a(0.0F, 0.0F, 0.0F, 2, 1, 1);
    sac3.func_78793_a(9.0F, 9.0F, 0.0F);
    sac3.func_78787_b(64, 64);
    sac3.field_78809_i = true;
    setRotation(sac3, 0.0F, 0.0F, 0.0F);
  }
  
  public void renderAll(int count) {
    chestLidBL.func_78785_a(0.0625F);
    chestLidFL.func_78785_a(0.0625F);
    chestLidBR.func_78785_a(0.0625F);
    chestLidFR.func_78785_a(0.0625F);
    chestBelow.func_78785_a(0.0625F);
    if (count >= 1) {
      sac1.func_78785_a(0.0625F);
    }
    if (count >= 2) {
      sac2.func_78785_a(0.0625F);
    }
    if (count >= 3) {
      sac3.func_78785_a(0.0625F);
    }
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
}
