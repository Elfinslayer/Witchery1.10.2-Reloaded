package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntitySummonedUndead;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelSpectre extends ModelBase
{
  ModelRenderer head;
  ModelRenderer body;
  ModelRenderer rightarm;
  ModelRenderer leftarm;
  ModelRenderer robeUpper;
  ModelRenderer robeLower;
  ModelRenderer mouth;
  private final boolean reachingArms;
  
  public ModelSpectre(boolean reachingArms)
  {
    this.reachingArms = reachingArms;
    
    field_78090_t = 64;
    field_78089_u = 32;
    
    head = new ModelRenderer(this, 0, 16);
    head.func_78789_a(-4.0F, -8.0F, -4.0F, 8, 8, 8);
    head.func_78793_a(0.0F, 0.0F, 0.0F);
    head.func_78787_b(64, 32);
    head.field_78809_i = true;
    setRotation(head, 0.0F, 0.0F, 0.0F);
    mouth = new ModelRenderer(this, 56, 0);
    
    mouth.func_78789_a(0.0F, 0.0F, 0.0F, 4, 5, 0);
    mouth.func_78793_a(-2.0F, -4.0F, -4.02F);
    mouth.func_78787_b(64, 32);
    mouth.field_78809_i = true;
    setRotation(mouth, 0.0F, 0.0F, 0.0F);
    
    body = new ModelRenderer(this, 16, 0);
    body.func_78789_a(-4.0F, 0.0F, -2.0F, 8, 10, 4);
    body.func_78793_a(0.0F, 0.0F, 0.0F);
    body.func_78787_b(64, 32);
    body.field_78809_i = true;
    setRotation(body, 0.0F, 0.0F, 0.0F);
    rightarm = new ModelRenderer(this, 0, 0);
    rightarm.func_78789_a(-3.0F, -2.0F, -2.0F, 4, 12, 4);
    rightarm.func_78793_a(-5.0F, 2.0F, 0.0F);
    rightarm.func_78787_b(64, 32);
    rightarm.field_78809_i = true;
    setRotation(rightarm, -1.396263F, 0.0F, 0.0F);
    leftarm = new ModelRenderer(this, 0, 0);
    leftarm.func_78789_a(-1.0F, -2.0F, -2.0F, 4, 12, 4);
    leftarm.func_78793_a(5.0F, 2.0F, 0.0F);
    leftarm.func_78787_b(64, 32);
    leftarm.field_78809_i = true;
    setRotation(leftarm, -1.396263F, 0.0F, 0.0F);
    robeUpper = new ModelRenderer(this, 38, 9);
    robeUpper.func_78789_a(-4.0F, 0.0F, -2.0F, 8, 6, 5);
    robeUpper.func_78793_a(0.0F, 10.0F, 0.0F);
    robeUpper.func_78787_b(64, 32);
    robeUpper.field_78809_i = true;
    setRotation(robeUpper, 0.0F, 0.0F, 0.0F);
    robeLower = new ModelRenderer(this, 32, 20);
    robeLower.func_78789_a(-5.0F, 0.0F, -2.0F, 10, 6, 6);
    robeLower.func_78793_a(0.0F, 16.0F, 0.0F);
    robeLower.func_78787_b(64, 32);
    robeLower.field_78809_i = true;
    setRotation(robeLower, 0.0F, 0.0F, 0.0F);
    
    head.func_78792_a(mouth);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    boolean screaming = (entity != null) && ((entity instanceof EntitySummonedUndead)) && (((EntitySummonedUndead)entity).isScreaming());
    mouth.field_78807_k = (!screaming);
    mouth.func_78793_a(-2.0F, -4.0F, -4.02F);
    head.func_78785_a(f5);
    
    body.func_78785_a(f5);
    rightarm.func_78785_a(f5);
    leftarm.func_78785_a(f5);
    robeUpper.func_78785_a(f5);
    robeLower.func_78785_a(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
    super.func_78087_a(par1, par2, par3, par4, par5, par6, entity);
    
    head.field_78796_g = (par4 / 57.295776F);
    head.field_78795_f = (par5 / 57.295776F);
    
    if (reachingArms) {
      rightarm.field_78795_f = -1.5F;
      leftarm.field_78795_f = -1.5F;
    } else {
      if ((entity != null) && ((entity instanceof EntitySummonedUndead)) && (((EntitySummonedUndead)entity).isScreaming())) {
        rightarm.field_78808_h = 1.0F;
        leftarm.field_78808_h = -1.0F;
      } else {
        rightarm.field_78808_h = 0.0F;
        leftarm.field_78808_h = 0.0F;
      }
      rightarm.field_78795_f = -0.2F;
      leftarm.field_78795_f = -0.2F;
    }
    
    rightarm.field_78796_g = 0.0F;
    
    leftarm.field_78796_g = 0.0F;
    








    rightarm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    leftarm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
  }
}
