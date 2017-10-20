package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityLordOfTorment;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelLordOfTorment
  extends ModelBase
{
  ModelRenderer head;
  ModelRenderer body;
  ModelRenderer rightarm1;
  ModelRenderer rightarm2;
  ModelRenderer leftarm1;
  ModelRenderer leftarm2;
  ModelRenderer rightleg;
  ModelRenderer leftleg;
  ModelRenderer wingsLeft;
  ModelRenderer wingsRight;
  ModelRenderer hornLeft;
  ModelRenderer hornRight;
  
  public ModelLordOfTorment()
  {
    field_78090_t = 128;
    field_78089_u = 128;
    
    func_78085_a("head.skull", 0, 0);
    func_78085_a("head.beard1", 34, 0);
    func_78085_a("head.beard2", 34, 0);
    func_78085_a("head.beard3", 34, 0);
    func_78085_a("head.beard4", 34, 0);
    func_78085_a("head.beard5", 34, 0);
    func_78085_a("head.beard6", 34, 0);
    
    func_78085_a("head.nose", 40, 0);
    func_78085_a("head.nose2", 40, 6);
    



    head = new ModelRenderer(this, "head");
    head.func_78793_a(0.0F, -6.0F, 0.0F);
    setRotation(head, 0.0F, 0.0F, 0.0F);
    head.field_78809_i = true;
    head.func_78786_a("skull", -4.0F, -8.0F, -4.0F, 8, 8, 8);
    head.func_78786_a("beard1", -3.0F, 0.0F, -4.0F, 1, 7, 1);
    head.func_78786_a("beard2", -2.0F, 0.0F, -5.0F, 1, 5, 1);
    head.func_78786_a("beard3", -1.0F, 0.0F, -4.0F, 1, 9, 1);
    head.func_78786_a("beard4", 0.0F, 0.0F, -5.0F, 1, 6, 1);
    head.func_78786_a("beard5", 1.0F, 0.0F, -4.0F, 1, 4, 1);
    head.func_78786_a("beard6", 2.0F, 0.0F, -5.0F, 1, 8, 1);
    head.func_78786_a("nose", -3.0F, -4.0F, -5.0F, 6, 4, 1);
    head.func_78786_a("nose2", -2.0F, -6.0F, -5.0F, 4, 2, 1);
    







    hornRight = new ModelRenderer(this, 55, 0);
    hornRight.func_78789_a(-2.0F, -15.0F, 0.0F, 1, 9, 1);
    hornRight.func_78793_a(0.0F, -6.0F, 0.0F);
    hornRight.func_78787_b(128, 128);
    hornRight.field_78809_i = true;
    setRotation(hornRight, 0.5948578F, 0.0F, -0.1858931F);
    head.func_78792_a(hornRight);
    
    hornLeft = new ModelRenderer(this, 55, 0);
    hornLeft.func_78789_a(1.0F, -15.0F, 0.0F, 1, 9, 1);
    hornLeft.func_78793_a(0.0F, -6.0F, 0.0F);
    hornLeft.func_78787_b(128, 128);
    hornLeft.field_78809_i = true;
    setRotation(hornLeft, 0.5948578F, 0.0F, 0.1858931F);
    head.func_78792_a(hornLeft);
    
    body = new ModelRenderer(this, 16, 16);
    body.func_78789_a(-4.0F, -6.0F, -2.0F, 8, 14, 4);
    body.func_78793_a(0.0F, 0.0F, 0.0F);
    body.func_78787_b(128, 128);
    body.field_78809_i = true;
    setRotation(body, 0.0F, 0.0F, 0.0F);
    rightarm1 = new ModelRenderer(this, 40, 16);
    rightarm1.func_78789_a(-3.0F, -2.0F, -2.0F, 4, 20, 4);
    rightarm1.func_78793_a(-5.0F, -4.0F, 0.0F);
    rightarm1.func_78787_b(128, 128);
    rightarm1.field_78809_i = true;
    setRotation(rightarm1, 0.0F, 0.0F, 0.0F);
    rightarm2 = new ModelRenderer(this, 40, 16);
    rightarm2.func_78789_a(-3.0F, -2.0F, -2.0F, 4, 20, 4);
    rightarm2.func_78793_a(-5.0F, -4.0F, 0.0F);
    rightarm2.func_78787_b(128, 128);
    rightarm2.field_78809_i = true;
    setRotation(rightarm2, 0.0F, 0.0F, 0.0F);
    leftarm1 = new ModelRenderer(this, 40, 16);
    leftarm1.func_78789_a(-1.0F, -2.0F, -2.0F, 4, 20, 4);
    leftarm1.func_78793_a(5.0F, -4.0F, 0.0F);
    leftarm1.func_78787_b(128, 128);
    leftarm1.field_78809_i = true;
    setRotation(leftarm1, 0.0F, 0.0F, 0.0F);
    leftarm2 = new ModelRenderer(this, 40, 16);
    leftarm2.func_78789_a(-1.0F, -2.0F, -2.0F, 4, 20, 4);
    leftarm2.func_78793_a(5.0F, -4.0F, 0.0F);
    leftarm2.func_78787_b(128, 128);
    leftarm2.field_78809_i = true;
    setRotation(leftarm2, 0.0F, 0.0F, 0.0F);
    rightleg = new ModelRenderer(this, 0, 16);
    rightleg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 15, 4);
    rightleg.func_78793_a(-2.0F, 8.0F, 0.0F);
    rightleg.func_78787_b(128, 128);
    rightleg.field_78809_i = true;
    setRotation(rightleg, 0.0F, 0.0F, 0.0F);
    leftleg = new ModelRenderer(this, 0, 16);
    leftleg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 15, 4);
    leftleg.func_78793_a(2.0F, 8.0F, 0.0F);
    leftleg.func_78787_b(128, 128);
    leftleg.field_78809_i = true;
    setRotation(leftleg, 0.0F, 0.0F, 0.0F);
    
    wingsLeft = new ModelRenderer(this, 0, 42);
    wingsLeft.func_78789_a(-20.0F, -20.0F, 0.0F, 20, 40, 0);
    wingsLeft.func_78793_a(0.0F, 1.0F, 5.0F);
    wingsLeft.func_78787_b(128, 128);
    wingsLeft.field_78809_i = true;
    setRotation(wingsLeft, 0.0F, 0.0F, 0.0F);
    
    wingsRight = new ModelRenderer(this, 0, 82);
    wingsRight.func_78789_a(0.0F, -20.0F, 0.0F, 20, 40, 0);
    wingsRight.func_78793_a(0.0F, 1.0F, 5.0F);
    wingsRight.func_78787_b(128, 128);
    wingsRight.field_78809_i = true;
    setRotation(wingsRight, 0.0F, 0.0F, 0.0F);
    
    hornRight.func_78793_a(0.0F, 0.0F, 0.0F);
    hornLeft.func_78793_a(0.0F, 0.0F, 0.0F);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    


    head.func_78785_a(f5);
    body.func_78785_a(f5);
    rightarm1.func_78785_a(f5);
    rightarm2.func_78785_a(f5);
    leftarm1.func_78785_a(f5);
    leftarm2.func_78785_a(f5);
    rightleg.func_78785_a(f5);
    leftleg.func_78785_a(f5);
    wingsLeft.func_78785_a(f5);
    wingsRight.func_78785_a(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
  {
    head.field_78796_g = (par4 / 57.295776F);
    head.field_78795_f = (par5 / 57.295776F);
    
    rightarm1.field_78795_f = (MathHelper.func_76134_b(3.8077927F) * 2.0F * par2 * 0.5F);
    rightarm2.field_78795_f = (MathHelper.func_76134_b(3.8077927F) * 2.0F * par2 * 0.25F);
    
    leftarm1.field_78795_f = (MathHelper.func_76134_b(0.6662F) * 2.0F * par2 * 0.5F);
    leftarm2.field_78795_f = (MathHelper.func_76134_b(0.6662F) * 2.0F * par2 * 0.25F);
    
    boolean inMotion = (field_70159_w > 0.0D) || (field_70179_y > 0.0D);
    if (inMotion) {
      wingsLeft.field_78796_g = 0.4F;
      wingsRight.field_78796_g = -0.4F;
    } else {
      wingsLeft.field_78796_g = (MathHelper.func_76134_b(3.8077927F) * 2.0F * par2 * 0.5F + MathHelper.func_76134_b(par3 * 0.09F) * 0.3F);
      wingsRight.field_78796_g = (MathHelper.func_76134_b(3.8077927F) * 2.0F * par2 * 0.5F - MathHelper.func_76134_b(par3 * 0.09F) * 0.3F);
    }
    
    rightarm1.field_78796_g = 0.0F;
    rightarm2.field_78796_g = 0.0F;
    
    leftarm1.field_78796_g = 0.0F;
    leftarm2.field_78796_g = 0.0F;
    
    rightarm1.field_78808_h = 0.0F;
    rightarm2.field_78808_h = 0.0F;
    
    leftarm1.field_78808_h = 0.0F;
    leftarm2.field_78808_h = 0.0F;
    
    rightarm1.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
    rightarm2.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
    
    leftarm1.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
    leftarm2.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
    rightarm1.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    rightarm2.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    leftarm1.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    leftarm2.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    

    EntityLordOfTorment entityDemon = (EntityLordOfTorment)entity;
    int i = entityDemon.getAttackTimer();
    
    if (i > 0) {
      rightarm1.field_78795_f = (-1.5F + 0.8F * func_78172_a(i - par4, 15.0F));
      leftarm1.field_78795_f = (-1.5F + 0.8F * func_78172_a(i - par4, 15.0F));
      
      rightarm1.field_78808_h = (-(-1.5F + 1.5F * func_78172_a(i - par4, 15.0F)));
      leftarm1.field_78808_h = (-1.5F + 1.5F * func_78172_a(i - par4, 15.0F));
      



      rightarm2.field_78808_h = (-(-1.0F + 1.5F * func_78172_a(i - par4, 15.0F)));
      leftarm2.field_78808_h = (-1.0F + 1.5F * func_78172_a(i - par4, 15.0F));
    }
  }
  














  private float func_78172_a(float par1, float par2)
  {
    return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
  }
}
