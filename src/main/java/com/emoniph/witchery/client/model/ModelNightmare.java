package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityNightmare;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelNightmare extends ModelBase
{
  ModelRenderer head;
  ModelRenderer body;
  ModelRenderer leftarm;
  ModelRenderer leg7;
  ModelRenderer leg6;
  ModelRenderer leg5;
  ModelRenderer leg4;
  ModelRenderer leg3;
  ModelRenderer leg2;
  ModelRenderer leg1;
  ModelRenderer rightfingerlittle;
  ModelRenderer rightfingerindex;
  ModelRenderer rightfingerthumb;
  ModelRenderer rightarm;
  ModelRenderer leftfingerlittle;
  ModelRenderer leftfingerindex;
  ModelRenderer leftfingerthumb;
  
  public ModelNightmare()
  {
    field_78090_t = 64;
    field_78089_u = 64;
    
    head = new ModelRenderer(this, 0, 0);
    head.func_78789_a(-4.0F, -8.0F, -3.0F, 8, 8, 6);
    head.func_78793_a(0.0F, -6.0F, 0.0F);
    head.func_78787_b(64, 64);
    head.field_78809_i = true;
    setRotation(head, 0.0F, 0.0F, 0.0F);
    body = new ModelRenderer(this, 16, 16);
    body.func_78789_a(-4.0F, 0.0F, -2.0F, 8, 12, 4);
    body.func_78793_a(0.0F, -6.0F, 0.0F);
    body.func_78787_b(64, 64);
    body.field_78809_i = true;
    setRotation(body, 0.0F, 0.0F, 0.0F);
    leftarm = new ModelRenderer(this, 40, 16);
    leftarm.func_78789_a(-1.0F, -2.0F, -1.0F, 2, 16, 2);
    leftarm.func_78793_a(5.0F, -4.0F, 0.0F);
    leftarm.func_78787_b(64, 64);
    leftarm.field_78809_i = true;
    setRotation(leftarm, 0.0F, 0.0F, 0.0F);
    leg7 = new ModelRenderer(this, 12, 16);
    leg7.func_78789_a(0.0F, 0.0F, 0.0F, 1, 16, 1);
    leg7.func_78793_a(-1.0F, 6.0F, 0.0F);
    leg7.func_78787_b(64, 64);
    leg7.field_78809_i = true;
    setRotation(leg7, 0.0F, 0.0F, 0.0F);
    leg6 = new ModelRenderer(this, 8, 16);
    leg6.func_78789_a(0.0F, 0.0F, 0.0F, 1, 17, 1);
    leg6.func_78793_a(1.0F, 6.0F, -1.0F);
    leg6.func_78787_b(64, 64);
    leg6.field_78809_i = true;
    setRotation(leg6, 0.0F, 0.0F, 0.0F);
    leg5 = new ModelRenderer(this, 4, 16);
    leg5.func_78789_a(0.0F, 0.0F, 0.0F, 1, 12, 1);
    leg5.func_78793_a(-3.0F, 6.0F, 0.0F);
    leg5.func_78787_b(64, 64);
    leg5.field_78809_i = true;
    setRotation(leg5, 0.0F, 0.0F, 0.0F);
    leg4 = new ModelRenderer(this, 8, 16);
    leg4.func_78789_a(0.0F, 0.0F, 0.0F, 1, 14, 1);
    leg4.func_78793_a(-2.0F, 6.0F, -1.0F);
    leg4.func_78787_b(64, 64);
    leg4.field_78809_i = true;
    setRotation(leg4, 0.0F, 0.0F, 0.0F);
    leg3 = new ModelRenderer(this, 12, 16);
    leg3.func_78789_a(0.0F, 0.0F, 0.0F, 1, 11, 1);
    leg3.func_78793_a(0.0F, 6.0F, -1.0F);
    leg3.func_78787_b(64, 64);
    leg3.field_78809_i = true;
    setRotation(leg3, 0.0F, 0.0F, 0.0F);
    leg2 = new ModelRenderer(this, 0, 16);
    leg2.func_78789_a(0.0F, 0.0F, 0.0F, 1, 14, 1);
    leg2.func_78793_a(2.0F, 6.0F, 0.0F);
    leg2.func_78787_b(64, 64);
    leg2.field_78809_i = true;
    setRotation(leg2, 0.0F, 0.0F, 0.0F);
    leg1 = new ModelRenderer(this, 0, 16);
    leg1.func_78789_a(0.0F, 0.0F, 0.0F, 1, 3, 1);
    leg1.func_78793_a(-3.0F, 6.0F, -1.0F);
    leg1.func_78787_b(64, 64);
    leg1.field_78809_i = true;
    setRotation(leg1, 0.0F, 0.0F, 0.0F);
    rightfingerlittle = new ModelRenderer(this, 0, 46);
    rightfingerlittle.func_78789_a(-1.0F, 0.0F, -1.0F, 1, 6, 1);
    rightfingerlittle.func_78793_a(-5.0F, 8.0F, 0.0F);
    rightfingerlittle.func_78787_b(64, 64);
    rightfingerlittle.field_78809_i = true;
    setRotation(rightfingerlittle, -0.148353F, 0.0F, 0.1134464F);
    rightfingerindex = new ModelRenderer(this, 4, 46);
    rightfingerindex.func_78789_a(0.0F, 0.0F, -1.0F, 1, 6, 1);
    rightfingerindex.func_78793_a(-5.0F, 8.0F, 0.0F);
    rightfingerindex.func_78787_b(64, 64);
    rightfingerindex.field_78809_i = true;
    setRotation(rightfingerindex, -0.148353F, 0.0F, -0.1134464F);
    rightfingerthumb = new ModelRenderer(this, 8, 46);
    rightfingerthumb.func_78789_a(-0.5F, 0.0F, 0.0F, 1, 6, 1);
    rightfingerthumb.func_78793_a(-5.0F, 8.0F, 0.0F);
    rightfingerthumb.func_78787_b(64, 64);
    rightfingerthumb.field_78809_i = true;
    setRotation(rightfingerthumb, 0.1396263F, 0.0F, 0.0F);
    rightarm = new ModelRenderer(this, 40, 16);
    rightarm.func_78789_a(-1.0F, -2.0F, -1.0F, 2, 16, 2);
    rightarm.func_78793_a(-5.0F, -4.0F, 0.0F);
    rightarm.func_78787_b(64, 64);
    rightarm.field_78809_i = true;
    setRotation(rightarm, 0.0F, 0.0F, 0.0F);
    leftfingerlittle = new ModelRenderer(this, 8, 53);
    leftfingerlittle.func_78789_a(0.0F, 0.0F, -1.0F, 1, 6, 1);
    leftfingerlittle.func_78793_a(5.0F, 8.0F, 0.0F);
    leftfingerlittle.func_78787_b(64, 64);
    leftfingerlittle.field_78809_i = true;
    setRotation(leftfingerlittle, -0.148353F, 0.0F, -0.1134464F);
    leftfingerindex = new ModelRenderer(this, 4, 53);
    leftfingerindex.func_78789_a(-1.0F, 0.0F, -1.0F, 1, 6, 1);
    leftfingerindex.func_78793_a(5.0F, 8.0F, 0.0F);
    leftfingerindex.func_78787_b(64, 64);
    leftfingerindex.field_78809_i = true;
    setRotation(leftfingerindex, -0.148353F, 0.0F, 0.1134464F);
    leftfingerthumb = new ModelRenderer(this, 0, 53);
    leftfingerthumb.func_78789_a(-0.5F, 0.0F, 0.0F, 1, 6, 1);
    leftfingerthumb.func_78793_a(5.0F, 8.0F, 0.0F);
    leftfingerthumb.func_78787_b(64, 64);
    leftfingerthumb.field_78809_i = true;
    setRotation(leftfingerthumb, 0.1396263F, 0.0F, 0.0F);
    
    leftarm.func_78792_a(leftfingerindex);
    leftarm.func_78792_a(leftfingerlittle);
    leftarm.func_78792_a(leftfingerthumb);
    
    rightarm.func_78792_a(rightfingerindex);
    rightarm.func_78792_a(rightfingerlittle);
    rightarm.func_78792_a(rightfingerthumb);
    
    leftfingerlittle.func_78793_a(0.0F, 12.0F, 0.0F);
    leftfingerindex.func_78793_a(0.0F, 12.0F, 0.0F);
    leftfingerthumb.func_78793_a(0.0F, 12.0F, 0.0F);
    
    rightfingerlittle.func_78793_a(0.0F, 12.0F, 0.0F);
    rightfingerindex.func_78793_a(0.0F, 12.0F, 0.0F);
    rightfingerthumb.func_78793_a(0.0F, 12.0F, 0.0F);
    
    leg1.field_78800_c = -2.0F;
    leg2.field_78800_c = 2.0F;
    leg3.field_78800_c = 1.0F;
    leg4.field_78800_c = -1.0F;
    leg5.field_78800_c = -2.0F;
    leg6.field_78800_c = 3.0F;
    leg7.field_78800_c = 0.0F;
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    head.func_78785_a(f5);
    body.func_78785_a(f5);
    leftarm.func_78785_a(f5);
    leg7.func_78785_a(f5);
    leg6.func_78785_a(f5);
    leg5.func_78785_a(f5);
    leg4.func_78785_a(f5);
    leg3.func_78785_a(f5);
    leg2.func_78785_a(f5);
    leg1.func_78785_a(f5);
    rightarm.func_78785_a(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4)
  {
    EntityNightmare entityDemon = (EntityNightmare)par1EntityLiving;
    int i = entityDemon.getAttackTimer();
    
    if (i > 0) {
      rightarm.field_78795_f = (-1.5F + 0.8F * func_78172_a(i - par4, 15.0F));
      leftarm.field_78795_f = (-1.5F + 0.8F * func_78172_a(i - par4, 15.0F));
      
      rightarm.field_78808_h = (-(-1.5F + 1.5F * func_78172_a(i - par4, 15.0F)));
      leftarm.field_78808_h = (-1.5F + 1.5F * func_78172_a(i - par4, 15.0F));
    } else {
      leftarm.field_78795_f = -0.1F;
      rightarm.field_78795_f = -0.1F;
      
      leftarm.field_78808_h = 0.0F;
      rightarm.field_78808_h = 0.0F;
    }
  }
  
  public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
    super.func_78087_a(par1, par2, par3, par4, par5, par6, entity);
    
    head.field_78796_g = (par4 / 57.295776F);
    head.field_78795_f = (par5 / 57.295776F);
    


    float f6 = 0.01F * (entity.func_145782_y() % 10);
    leg7.field_78795_f = (-1.8F + MathHelper.func_76126_a(field_70173_aa * f6) * 4.5F * 3.1415927F / 180.0F);
    leg7.field_78796_g = -1.7F;
    leg7.field_78808_h = (1.839205F + MathHelper.func_76134_b(field_70173_aa * f6) * 2.5F * 3.1415927F / 180.0F);
    

    f6 = 0.01F * (entity.func_145782_y() % 10);
    leg6.field_78795_f = (-1.8F + MathHelper.func_76126_a(field_70173_aa * f6) * 4.5F * 3.1415927F / 180.0F);
    leg6.field_78796_g = -1.7F;
    leg6.field_78808_h = (1.839205F + MathHelper.func_76134_b(field_70173_aa * f6) * 2.5F * 3.1415927F / 180.0F);
    
    f6 = 0.01F * (entity.func_145782_y() % 12);
    leg5.field_78795_f = (-1.8F + MathHelper.func_76126_a(field_70173_aa * f6) * 4.5F * 3.1415927F / 180.0F);
    leg5.field_78796_g = -1.7F;
    leg5.field_78808_h = (1.839205F + MathHelper.func_76134_b(field_70173_aa * f6) * 2.5F * 3.1415927F / 180.0F);
    
    f6 = 0.01F * (entity.func_145782_y() % 10);
    leg4.field_78795_f = (-1.8F + MathHelper.func_76126_a(field_70173_aa * f6) * 4.5F * 3.1415927F / 180.0F);
    leg4.field_78796_g = -1.7F;
    leg4.field_78808_h = (1.839205F + MathHelper.func_76134_b(field_70173_aa * f6) * 2.5F * 3.1415927F / 180.0F);
    
    f6 = 0.01F * (entity.func_145782_y() % 13);
    leg3.field_78795_f = (-1.8F + MathHelper.func_76126_a(field_70173_aa * f6) * 4.5F * 3.1415927F / 180.0F);
    leg3.field_78796_g = -1.7F;
    leg3.field_78808_h = (1.839205F + MathHelper.func_76134_b(field_70173_aa * f6) * 2.5F * 3.1415927F / 180.0F);
    
    f6 = 0.01F * (entity.func_145782_y() % 12);
    leg2.field_78795_f = (-1.8F + MathHelper.func_76126_a(field_70173_aa * f6) * 4.5F * 3.1415927F / 180.0F);
    leg2.field_78796_g = -1.7F;
    leg2.field_78808_h = (1.839205F + MathHelper.func_76134_b(field_70173_aa * f6) * 2.5F * 3.1415927F / 180.0F);
    
    f6 = 0.01F * (entity.func_145782_y() % 12);
    leg1.field_78795_f = (-1.8F + MathHelper.func_76126_a(field_70173_aa * f6) * 4.5F * 3.1415927F / 180.0F);
    leg1.field_78796_g = -1.7F;
    leg1.field_78808_h = (1.839205F + MathHelper.func_76134_b(field_70173_aa * f6) * 2.5F * 3.1415927F / 180.0F);
  }
  
  private float func_78172_a(float par1, float par2) {
    return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
  }
}
