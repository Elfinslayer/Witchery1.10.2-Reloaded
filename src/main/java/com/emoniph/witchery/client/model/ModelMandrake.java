package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityMandrake;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

@SideOnly(Side.CLIENT)
public class ModelMandrake extends ModelBase
{
  ModelRenderer head;
  ModelRenderer body;
  ModelRenderer rightarm;
  ModelRenderer leftarm;
  ModelRenderer rightleg;
  ModelRenderer leftleg;
  
  public ModelMandrake()
  {
    field_78090_t = 64;
    field_78089_u = 32;
    
    func_78085_a("head.face", 0, 8);
    func_78085_a("head.leaves", 0, 0);
    func_78085_a("body.bodyChest", 21, 0);
    func_78085_a("body.bodyBelly", 17, 7);
    head = new ModelRenderer(this, "head");
    head.func_78793_a(0.0F, 16.0F, 0.0F);
    setRotation(head, 0.0F, 0.0F, 0.0F);
    head.field_78809_i = true;
    head.func_78786_a("face", -2.0F, -4.0F, -2.0F, 4, 4, 4);
    head.func_78786_a("leaves", -4.0F, -12.0F, 0.0F, 8, 8, 0);
    body = new ModelRenderer(this, "body");
    body.func_78793_a(0.0F, 16.0F, 0.0F);
    setRotation(body, 0.0F, 0.0F, 0.0F);
    body.field_78809_i = true;
    body.func_78786_a("bodyChest", -2.5F, 0.0F, -2.5F, 5, 2, 5);
    body.func_78786_a("bodyBelly", -3.5F, 2.0F, -3.5F, 7, 3, 7);
    rightarm = new ModelRenderer(this, 37, 0);
    rightarm.func_78789_a(-1.0F, 0.0F, -0.5F, 1, 3, 1);
    rightarm.func_78793_a(-2.0F, 17.0F, 0.0F);
    rightarm.func_78787_b(64, 32);
    rightarm.field_78809_i = true;
    setRotation(rightarm, 0.0F, 0.0F, 1.047198F);
    leftarm = new ModelRenderer(this, 37, 0);
    leftarm.func_78789_a(0.0F, 0.0F, -0.5F, 1, 3, 1);
    leftarm.func_78793_a(2.0F, 17.0F, 0.0F);
    leftarm.func_78787_b(64, 32);
    leftarm.field_78809_i = true;
    setRotation(leftarm, 0.0F, 0.0F, -1.047198F);
    rightleg = new ModelRenderer(this, 27, 18);
    rightleg.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 3, 2);
    rightleg.func_78793_a(-1.0F, 21.0F, 0.0F);
    rightleg.func_78787_b(64, 32);
    rightleg.field_78809_i = true;
    setRotation(rightleg, 0.0F, 0.0F, 0.0F);
    leftleg = new ModelRenderer(this, 27, 18);
    leftleg.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 3, 2);
    leftleg.func_78793_a(1.0F, 21.0F, 0.0F);
    leftleg.func_78787_b(64, 32);
    leftleg.field_78809_i = true;
    setRotation(leftleg, 0.0F, 0.0F, 0.0F);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    head.func_78785_a(f5);
    body.func_78785_a(f5);
    rightarm.func_78785_a(f5);
    leftarm.func_78785_a(f5);
    rightleg.func_78785_a(f5);
    leftleg.func_78785_a(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
  }
  
  public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4)
  {
    EntityMandrake entityDemon = (EntityMandrake)par1EntityLiving;
    
    rightarm.field_78795_f = ((-0.2F + 1.5F * func_78172_a(par2, 13.0F)) * par3);
    leftarm.field_78795_f = ((-0.2F - 1.5F * func_78172_a(par2, 13.0F)) * par3);
  }
  
  private float func_78172_a(float par1, float par2) {
    return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
  }
}
