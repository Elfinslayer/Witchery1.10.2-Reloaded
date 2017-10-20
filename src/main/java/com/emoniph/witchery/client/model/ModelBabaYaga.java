package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelBabaYaga extends ModelVillager
{
  public boolean field_82900_g;
  private ModelRenderer field_82901_h = new ModelRenderer(this).func_78787_b(64, 128);
  private ModelRenderer witchHat;
  private ModelRenderer mortar;
  private ModelRenderer pestle;
  public ModelRenderer bipedCloak;
  
  public ModelBabaYaga(float par1) {
    super(par1, 0.0F, 128, 128);
    bipedCloak = new ModelRenderer(this, 94, 0);
    bipedCloak.func_78787_b(128, 128);
    bipedCloak.func_78789_a(0.0F, 0.0F, 0.0F, 8, 10, 0);
    bipedCloak.field_78795_f = 0.1F;
    


    field_82901_h.func_78793_a(0.0F, -2.0F, 0.0F);
    field_82901_h.func_78784_a(0, 0).func_78790_a(0.0F, 3.0F, -6.75F, 1, 1, 1, -0.15F);
    field_82898_f.func_78792_a(field_82901_h);
    

    witchHat = new ModelRenderer(this).func_78787_b(128, 128);
    witchHat.func_78793_a(-7.0F, -10.03125F, -7.0F);
    witchHat.func_78784_a(0, 98).func_78789_a(0.0F, 0.0F, 0.0F, 14, 2, 14);
    field_78191_a.func_78792_a(witchHat);
    
    ModelRenderer modelrenderer = new ModelRenderer(this).func_78787_b(128, 128);
    modelrenderer.func_78793_a(3.75F, -4.0F, 4.0F);
    modelrenderer.func_78784_a(0, 76).func_78789_a(0.0F, 0.0F, 0.0F, 7, 4, 7);
    field_78795_f = -0.05235988F;
    field_78808_h = 0.02617994F;
    witchHat.func_78792_a(modelrenderer);
    
    ModelRenderer modelrenderer1 = new ModelRenderer(this).func_78787_b(128, 128);
    modelrenderer1.func_78793_a(1.75F, -4.0F, 2.0F);
    modelrenderer1.func_78784_a(0, 87).func_78789_a(0.0F, 0.0F, 0.0F, 4, 4, 4);
    field_78795_f = -0.10471976F;
    field_78808_h = 0.05235988F;
    modelrenderer.func_78792_a(modelrenderer1);
    
    ModelRenderer modelrenderer2 = new ModelRenderer(this).func_78787_b(128, 128);
    modelrenderer2.func_78793_a(1.75F, -2.0F, 2.0F);
    modelrenderer2.func_78784_a(0, 95).func_78790_a(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.25F);
    field_78795_f = -0.20943952F;
    field_78808_h = 0.10471976F;
    modelrenderer1.func_78792_a(modelrenderer2);
    

    func_78085_a("mortar.bottom", 80, 88);
    func_78085_a("mortar.top", 72, 107);
    func_78085_a("pestle.upper", 124, 0);
    func_78085_a("pestle.lower", 116, 13);
    
    mortar = new ModelRenderer(this, "mortar");
    mortar.func_78787_b(128, 128);
    mortar.func_78793_a(-7.0F, 10.0F, -8.0F);
    setRotation(mortar, 0.0F, 0.0F, 0.0F);
    mortar.field_78809_i = true;
    mortar.func_78786_a("bottom", 1.0F, 7.0F, 2.0F, 12, 7, 12);
    mortar.func_78786_a("top", 0.0F, 0.0F, 1.0F, 14, 7, 14);
    pestle = new ModelRenderer(this, "pestle");
    pestle.func_78787_b(128, 128);
    pestle.func_78793_a(-3.0F, 6.0F, -4.0F);
    setRotation(pestle, -1.152537F, -2.305074F, 1.839205F);
    pestle.field_78809_i = true;
    pestle.func_78786_a("upper", -1.0F, -7.0F, 0.0F, 1, 12, 1);
    pestle.func_78786_a("lower", -2.0F, 5.0F, -1.0F, 3, 12, 3);
  }
  

  public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
  {
    GL11.glTranslatef(0.0F, -0.2F, 0.0F);
    super.func_78088_a(par1Entity, par2, par3, par4, par5, par6, par7);
    mortar.func_78785_a(par7);
    pestle.func_78785_a(par7);
    
    bipedCloak.func_78785_a(par7);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
  {
    field_78191_a.field_78796_g = (par4 / 57.295776F);
    field_78191_a.field_78795_f = (par5 / 57.295776F);
    field_78190_c.field_78797_d = 3.0F;
    field_78190_c.field_78798_e = -1.0F;
    field_78190_c.field_78795_f = -0.75F;
    
    bipedCloak.func_78793_a(-3.5F, -0.5F, 3.5F);
    






    field_82898_f.field_82906_o = (field_82898_f.field_82908_p = field_82898_f.field_82907_q = 0.0F);
    float f6 = 0.01F * (par7Entity.func_145782_y() % 10);
    field_82898_f.field_78795_f = (MathHelper.func_76126_a(field_70173_aa * f6) * 4.5F * 3.1415927F / 180.0F);
    field_82898_f.field_78796_g = 0.0F;
    field_82898_f.field_78808_h = (MathHelper.func_76134_b(field_70173_aa * f6) * 2.5F * 3.1415927F / 180.0F);
    
    if (field_82900_g) {
      field_82898_f.field_78795_f = -0.9F;
      field_82898_f.field_82907_q = -0.09375F;
      field_82898_f.field_82908_p = 0.1875F;
    }
    


    pestle.field_78795_f = (-1.152537F + MathHelper.func_76126_a(field_70173_aa * f6) * 4.5F * 3.1415927F / 180.0F);
    pestle.field_78796_g = -2.305074F;
    pestle.field_78808_h = (1.839205F + MathHelper.func_76134_b(field_70173_aa * f6) * 2.5F * 3.1415927F / 180.0F);
  }
}
