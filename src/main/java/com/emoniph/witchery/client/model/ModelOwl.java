package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityOwl;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelOwl extends ModelBase
{
  ModelRenderer head;
  ModelRenderer body;
  ModelRenderer rightarm;
  ModelRenderer leftarm;
  ModelRenderer rightleg;
  ModelRenderer leftleg;
  
  public ModelOwl()
  {
    field_78090_t = 64;
    field_78089_u = 32;
    
    func_78085_a("head.beak", 30, 0);
    func_78085_a("head.hornRight", 37, 0);
    func_78085_a("head.hornLeft", 37, 0);
    func_78085_a("head.head1", 0, 0);
    
    head = new ModelRenderer(this, "head");
    head.func_78793_a(0.0F, 15.0F, 0.0F);
    setRotation(head, 0.0F, 0.0F, 0.0F);
    head.field_78809_i = true;
    head.func_78786_a("hornRight", -5.0F, -7.0F, -1.0F, 1, 3, 2);
    head.func_78786_a("hornLeft", 4.0F, -7.0F, -1.0F, 1, 3, 2);
    head.func_78786_a("beak", -1.0F, -3.0F, -4.0F, 2, 3, 1);
    head.func_78786_a("head1", -4.0F, -6.0F, -3.0F, 8, 6, 6);
    body = new ModelRenderer(this, 16, 16);
    body.func_78789_a(-3.0F, 0.0F, -2.0F, 6, 8, 4);
    body.func_78793_a(0.0F, 15.0F, 0.0F);
    body.func_78787_b(64, 32);
    body.field_78809_i = true;
    setRotation(body, 0.0F, 0.0F, 0.0F);
    rightarm = new ModelRenderer(this, 40, 16);
    rightarm.func_78789_a(-1.0F, -1.0F, -2.0F, 1, 8, 4);
    rightarm.func_78793_a(-3.0F, 16.0F, 0.0F);
    rightarm.func_78787_b(64, 32);
    rightarm.field_78809_i = true;
    setRotation(rightarm, 0.0F, 0.0F, 0.0F);
    leftarm = new ModelRenderer(this, 40, 16);
    leftarm.func_78789_a(0.0F, -1.0F, -2.0F, 1, 8, 4);
    leftarm.func_78793_a(3.0F, 16.0F, 0.0F);
    leftarm.func_78787_b(64, 32);
    leftarm.field_78809_i = true;
    setRotation(leftarm, 0.0F, 0.0F, 0.0F);
    rightleg = new ModelRenderer(this, 0, 16);
    rightleg.func_78789_a(-1.0F, 0.0F, -2.0F, 2, 1, 4);
    rightleg.func_78793_a(-2.0F, 23.0F, -1.0F);
    rightleg.func_78787_b(64, 32);
    rightleg.field_78809_i = true;
    setRotation(rightleg, 0.0F, 0.0F, 0.0F);
    leftleg = new ModelRenderer(this, 0, 16);
    leftleg.func_78789_a(-1.0F, 0.0F, -2.0F, 2, 1, 4);
    leftleg.func_78793_a(2.0F, 23.0F, -1.0F);
    leftleg.func_78787_b(64, 32);
    leftleg.field_78809_i = true;
    setRotation(leftleg, 0.0F, 0.0F, 0.0F);
    
    rightleg.func_78793_a(-2.0F, 8.0F, -1.0F);
    leftleg.func_78793_a(2.0F, 8.0F, -1.0F);
    
    body.func_78792_a(leftleg);
    body.func_78792_a(rightleg);
  }
  
  public static boolean isLanded(Entity entity) {
    Block block = field_70170_p.func_147439_a(MathHelper.func_76128_c(field_70165_t), (int)(field_70163_u - 0.01D), MathHelper.func_76128_c(field_70161_v));
    Material material = block.func_149688_o();
    if ((material == Material.field_151584_j) || (material.func_76220_a())) {
      return true;
    }
    return false;
  }
  

  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    
    EntityOwl entitybat = (EntityOwl)entity;
    

    if ((field_70181_x == 0.0D) && (field_70159_w == 0.0D) && (field_70179_y == 0.0D) && (isLanded(entity)))
    {
      body.field_78795_f = 0.0F;
      leftarm.field_78808_h = 0.0F;
      rightarm.field_78808_h = 0.0F;
      rightleg.field_78795_f = 0.0F;
      leftleg.field_78795_f = 0.0F;
    }
    else
    {
      float f6 = 57.295776F;
      body.field_78795_f = (0.7853982F + MathHelper.func_76134_b(f2 * 0.1F) * 0.15F);
      body.field_78796_g = 0.0F;
      
      rightleg.field_78795_f = (0.7853982F + MathHelper.func_76134_b(f2 * 0.1F) * 0.15F);
      leftleg.field_78795_f = (0.7853982F + MathHelper.func_76134_b(f2 * 0.1F) * 0.15F);
      
      rightarm.field_78808_h = (MathHelper.func_76134_b(f2 * 0.5F) * 3.1415927F * 0.2F * 2.0F + 1.4F);
      leftarm.field_78808_h = (-rightarm.field_78808_h);
    }
    
    if (entitybat.func_70906_o()) {
      rightleg.field_78796_g = 0.5F;
      leftleg.field_78796_g = (-rightleg.field_78796_g);
    } else {
      rightleg.field_78796_g = 0.1F;
      leftleg.field_78796_g = (-rightleg.field_78796_g);
    }
    
    head.field_78796_g = (f3 / 57.295776F);
    head.field_78795_f = (f4 / 57.295776F);
    


    if (field_78091_s)
    {
      float p6 = 2.0F;
      GL11.glPushMatrix();
      GL11.glScalef(1.5F / p6, 1.5F / p6, 1.5F / p6);
      GL11.glTranslatef(0.0F, 11.0F * f5, 0.0F);
      head.func_78785_a(f5);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glScalef(1.0F / p6, 1.0F / p6, 1.0F / p6);
      GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
      body.func_78785_a(f5);
      rightarm.func_78785_a(f5);
      leftarm.func_78785_a(f5);
      GL11.glPopMatrix();
    }
    else
    {
      head.func_78785_a(f5);
      body.func_78785_a(f5);
      rightarm.func_78785_a(f5);
      leftarm.func_78785_a(f5);
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
