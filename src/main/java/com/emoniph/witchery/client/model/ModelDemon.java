package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityDemon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;



@SideOnly(Side.CLIENT)
public class ModelDemon
  extends ModelBase
{
  ModelRenderer head;
  ModelRenderer body;
  ModelRenderer rightarm;
  ModelRenderer leftarm;
  ModelRenderer rightleg;
  ModelRenderer leftleg;
  ModelRenderer wingRight;
  ModelRenderer wingLeft;
  
  public ModelDemon()
  {
    field_78090_t = 128;
    field_78089_u = 32;
    
    func_78085_a("head.face", 0, 0);
    func_78085_a("head.leftHorn", 0, 16);
    func_78085_a("head.rightHorn", 0, 16);
    func_78085_a("head.leftTusk", 4, 16);
    func_78085_a("head.rightTusk", 4, 16);
    func_78085_a("head.snout", 20, 16);
    func_78085_a("head.bottomLip", 8, 16);
    head = new ModelRenderer(this, "head");
    head.func_78784_a(0, 0);
    head.func_78786_a("face", -4.0F, -8.0F, -4.0F, 8, 8, 8);
    head.func_78786_a("leftHorn", 4.0F, -12.0F, -0.5F, 1, 8, 1);
    head.func_78786_a("rightHorn", -5.0F, -12.0F, -0.5F, 1, 8, 1);
    head.func_78786_a("leftTusk", 1.0F, -4.0F, -5.0F, 1, 2, 1);
    head.func_78786_a("bottomLip", -2.0F, -2.0F, -6.0F, 4, 1, 2);
    head.func_78786_a("snout", -1.0F, -6.0F, -6.0F, 2, 3, 2);
    head.func_78786_a("rightTusk", -2.0F, -4.0F, -5.0F, 1, 2, 1);
    head.func_78793_a(0.0F, -9.0F, 0.0F);
    head.func_78787_b(128, 32);
    head.field_78809_i = true;
    
    body = new ModelRenderer(this, 64, 0);
    body.func_78789_a(-4.0F, 0.0F, -3.0F, 8, 14, 6);
    body.func_78793_a(0.0F, -9.0F, 0.0F);
    body.func_78787_b(128, 32);
    body.field_78809_i = true;
    
    rightarm = new ModelRenderer(this, 48, 0);
    rightarm.func_78789_a(-3.0F, -2.0F, -2.0F, 4, 20, 4);
    rightarm.func_78793_a(-5.0F, -7.0F, 0.0F);
    rightarm.func_78787_b(128, 32);
    rightarm.field_78809_i = true;
    
    leftarm = new ModelRenderer(this, 48, 0);
    leftarm.func_78789_a(-1.0F, -2.0F, -2.0F, 4, 20, 4);
    leftarm.func_78793_a(5.0F, -7.0F, 0.0F);
    leftarm.func_78787_b(128, 32);
    leftarm.field_78809_i = true;
    
    rightleg = new ModelRenderer(this, 32, 0);
    rightleg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 20, 4);
    rightleg.func_78793_a(-2.0F, 4.0F, 0.0F);
    rightleg.func_78787_b(128, 32);
    rightleg.field_78809_i = true;
    
    leftleg = new ModelRenderer(this, 32, 0);
    leftleg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 20, 4);
    leftleg.func_78793_a(2.0F, 4.0F, 0.0F);
    leftleg.func_78787_b(128, 32);
    leftleg.field_78809_i = false;
    

    wingRight = new ModelRenderer(this, 93, 0);
    wingRight.func_78789_a(0.0F, 0.0F, 0.0F, 14, 21, 0);
    wingRight.func_78793_a(1.0F, -8.0F, 3.0F);
    wingRight.func_78787_b(128, 32);
    wingRight.field_78809_i = true;
    setRotation(wingRight, 0.3047198F, -0.6698132F, -0.6283185F);
    
    wingLeft = new ModelRenderer(this, 93, 0);
    wingLeft.func_78789_a(0.0F, 0.0F, 0.0F, 14, 21, 0);
    wingLeft.func_78793_a(-1.0F, -8.0F, 3.0F);
    wingLeft.func_78787_b(128, 32);
    wingLeft.field_78809_i = true;
    
    setRotation(wingLeft, -0.3047198F, 3.811406F, 0.6283185F);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  



  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    head.func_78785_a(f5);
    body.func_78785_a(f5);
    
    rightarm.func_78785_a(f5);
    leftarm.func_78785_a(f5);
    rightleg.func_78785_a(f5);
    leftleg.func_78785_a(f5);
    wingLeft.func_78785_a(f5);
    wingRight.func_78785_a(f5);
  }
  









  public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
  {
    head.field_78796_g = (par4 / 57.295776F);
    head.field_78795_f = (par5 / 57.295776F);
    leftleg.field_78795_f = (-1.5F * func_78172_a(par1, 13.0F) * par2);
    rightleg.field_78795_f = (1.5F * func_78172_a(par1, 13.0F) * par2);
    leftleg.field_78796_g = 0.0F;
    rightleg.field_78796_g = 0.0F;
  }
  





  public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4)
  {
    EntityDemon entityDemon = (EntityDemon)par1EntityLiving;
    int i = entityDemon.getAttackTimer();
    
    if (i > 0) {
      rightarm.field_78795_f = (-2.0F + 1.5F * func_78172_a(i - par4, 10.0F));
    }
    else
    {
      rightarm.field_78795_f = ((-0.2F + 1.5F * func_78172_a(par2, 13.0F)) * par3);
      leftarm.field_78795_f = ((-0.2F - 1.5F * func_78172_a(par2, 13.0F)) * par3);
    }
  }
  
  private float func_78172_a(float par1, float par2) {
    return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
  }
}
