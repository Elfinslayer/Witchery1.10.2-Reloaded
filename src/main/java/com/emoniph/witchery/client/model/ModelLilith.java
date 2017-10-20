package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityLilith;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelLilith extends ModelBase
{
  public ModelRenderer legRight;
  public ModelRenderer legLeft;
  public ModelRenderer bodyChest;
  public ModelRenderer bodyWaist;
  public ModelRenderer skirt1;
  public ModelRenderer skirt2;
  public ModelRenderer bodyShoulders;
  public ModelRenderer armRight;
  public ModelRenderer armLeft;
  public ModelRenderer neck;
  public ModelRenderer head;
  public ModelRenderer legRightLower;
  public ModelRenderer legLeftLower;
  public ModelRenderer armRightLower;
  public ModelRenderer armRightWing;
  public ModelRenderer armLeftLower;
  public ModelRenderer armLeftWing;
  public ModelRenderer head2;
  public ModelRenderer hornRight;
  public ModelRenderer hornLeft;
  public ModelRenderer nose;
  public ModelRenderer toothRight;
  public ModelRenderer toothLeft;
  public ModelRenderer head3;
  
  public ModelLilith()
  {
    field_78090_t = 64;
    field_78089_u = 64;
    neck = new ModelRenderer(this, 24, 0);
    neck.func_78793_a(0.0F, -13.0F, 0.0F);
    neck.func_78790_a(-1.5F, -1.5F, -1.5F, 3, 2, 3, 0.0F);
    legLeftLower = new ModelRenderer(this, 48, 47);
    legLeftLower.field_78809_i = true;
    legLeftLower.func_78793_a(0.0F, 0.0F, 0.0F);
    legLeftLower.func_78790_a(-2.0F, 8.0F, 2.0F, 4, 13, 4, 0.0F);
    hornLeft = new ModelRenderer(this, 52, 30);
    hornLeft.field_78809_i = true;
    hornLeft.func_78793_a(0.0F, 0.0F, 0.0F);
    hornLeft.func_78790_a(1.0F, -12.3F, 0.0F, 6, 10, 0, 0.0F);
    setRotateAngle(hornLeft, -0.18203785F, 0.0F, 0.0F);
    bodyChest = new ModelRenderer(this, 17, 17);
    bodyChest.func_78793_a(0.0F, -9.8F, -1.9F);
    bodyChest.func_78790_a(-4.0F, -1.5F, -1.5F, 8, 3, 3, 0.0F);
    setRotateAngle(bodyChest, 0.7853982F, 0.0F, 0.0F);
    nose = new ModelRenderer(this, 41, 0);
    nose.func_78793_a(0.0F, 0.0F, 0.0F);
    nose.func_78790_a(-0.5F, -3.6F, -4.0F, 1, 2, 1, 0.0F);
    armLeftLower = new ModelRenderer(this, 8, 25);
    armLeftLower.field_78809_i = true;
    armLeftLower.func_78793_a(0.0F, 0.0F, 0.0F);
    armLeftLower.func_78790_a(-0.5F, 9.8F, 0.8F, 3, 13, 3, 0.0F);
    setRotateAngle(armLeftLower, -0.22759093F, 0.0F, 0.0F);
    skirt1 = new ModelRenderer(this, 0, 49);
    skirt1.func_78793_a(0.0F, -0.9F, 0.0F);
    skirt1.func_78790_a(-4.5F, 0.0F, -2.5F, 9, 10, 5, 0.0F);
    
    skirt2 = new ModelRenderer(this, 0, 49);
    skirt2.func_78793_a(0.0F, -0.9F, 0.0F);
    skirt2.func_78790_a(-4.5F, 0.0F, -2.5F, 9, 10, 5, 0.0F);
    
    armLeftWing = new ModelRenderer(this, 0, 13);
    armLeftWing.field_78809_i = true;
    armLeftWing.func_78793_a(0.0F, 0.0F, 0.0F);
    armLeftWing.func_78790_a(1.0F, -19.6F, -12.7F, 0, 30, 4, 0.0F);
    setRotateAngle(armLeftWing, 2.5497515F, 0.17453292F, 0.0F);
    legRightLower = new ModelRenderer(this, 48, 47);
    legRightLower.func_78793_a(0.0F, 0.0F, 0.0F);
    legRightLower.func_78790_a(-2.0F, 8.0F, 2.0F, 4, 13, 4, 0.0F);
    armLeft = new ModelRenderer(this, 0, 0);
    armLeft.field_78809_i = true;
    armLeft.func_78793_a(4.4F, -11.5F, 0.0F);
    armLeft.func_78790_a(-0.5F, -1.5F, -1.5F, 3, 13, 3, 0.0F);
    hornRight = new ModelRenderer(this, 52, 30);
    hornRight.func_78793_a(0.0F, 0.0F, 0.0F);
    hornRight.func_78790_a(-7.0F, -12.3F, 0.0F, 6, 10, 0, 0.0F);
    setRotateAngle(hornRight, -0.18203785F, 0.0F, 0.0F);
    legLeft = new ModelRenderer(this, 36, 30);
    legLeft.field_78809_i = true;
    legLeft.func_78793_a(2.1F, 2.5F, 0.0F);
    legLeft.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F);
    setRotateAngle(legLeft, -0.27314404F, 0.0F, 0.0F);
    bodyShoulders = new ModelRenderer(this, 15, 6);
    bodyShoulders.func_78793_a(0.0F, -12.7F, 0.0F);
    bodyShoulders.func_78790_a(-4.0F, 0.0F, -2.0F, 8, 6, 4, 0.0F);
    armRightLower = new ModelRenderer(this, 8, 25);
    armRightLower.func_78793_a(0.0F, 0.0F, 0.0F);
    armRightLower.func_78790_a(-2.5F, 9.8F, 0.8F, 3, 13, 3, 0.0F);
    setRotateAngle(armRightLower, -0.22759093F, 0.0F, 0.0F);
    head3 = new ModelRenderer(this, 44, 22);
    head3.func_78793_a(0.0F, 0.0F, 0.0F);
    head3.func_78790_a(-2.0F, -4.7F, 5.6F, 4, 4, 4, 0.0F);
    head2 = new ModelRenderer(this, 42, 12);
    head2.func_78793_a(0.0F, 0.0F, 0.0F);
    head2.func_78790_a(-2.5F, -5.5F, 1.0F, 5, 5, 5, 0.0F);
    setRotateAngle(head2, -0.18203785F, 0.0F, 0.0F);
    armRightWing = new ModelRenderer(this, 0, 13);
    armRightWing.func_78793_a(0.0F, 0.0F, 0.0F);
    armRightWing.func_78790_a(-1.0F, -19.6F, -12.7F, 0, 30, 4, 0.0F);
    setRotateAngle(armRightWing, 2.5497515F, -0.17453292F, 0.0F);
    legRight = new ModelRenderer(this, 36, 30);
    legRight.func_78793_a(-2.1F, 2.5F, 0.0F);
    legRight.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F);
    setRotateAngle(legRight, -0.27314404F, 0.0F, 0.0F);
    armRight = new ModelRenderer(this, 0, 0);
    armRight.func_78793_a(-4.5F, -11.5F, 0.0F);
    armRight.func_78790_a(-2.5F, -1.5F, -1.5F, 3, 13, 3, 0.0F);
    toothLeft = new ModelRenderer(this, 20, 0);
    toothLeft.field_78809_i = true;
    toothLeft.func_78793_a(0.0F, 0.0F, 0.0F);
    toothLeft.func_78790_a(0.5F, -1.6F, -3.6F, 1, 3, 1, -0.35F);
    toothRight = new ModelRenderer(this, 20, 0);
    toothRight.func_78793_a(0.0F, 0.0F, 0.0F);
    toothRight.func_78790_a(-1.5F, -1.6F, -3.6F, 1, 3, 1, -0.35F);
    bodyWaist = new ModelRenderer(this, 20, 24);
    bodyWaist.func_78793_a(0.0F, -7.5F, 0.0F);
    bodyWaist.func_78790_a(-3.0F, 0.0F, -1.0F, 6, 10, 2, 0.0F);
    head = new ModelRenderer(this, 40, 0);
    head.func_78793_a(0.0F, -13.5F, 0.0F);
    head.func_78790_a(-3.0F, -6.0F, -3.0F, 6, 6, 6, 0.0F);
    legLeft.func_78792_a(legLeftLower);
    head.func_78792_a(hornLeft);
    head.func_78792_a(nose);
    armLeft.func_78792_a(armLeftLower);
    armLeft.func_78792_a(armLeftWing);
    legRight.func_78792_a(legRightLower);
    head.func_78792_a(hornRight);
    armRight.func_78792_a(armRightLower);
    head2.func_78792_a(head3);
    head.func_78792_a(head2);
    armRight.func_78792_a(armRightWing);
    head.func_78792_a(toothLeft);
    head.func_78792_a(toothRight);
  }
  
  private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    neck.func_78785_a(f5);
    bodyChest.func_78785_a(f5);
    skirt1.func_78785_a(f5);
    skirt2.func_78785_a(f5);
    armLeft.func_78785_a(f5);
    legLeft.func_78785_a(f5);
    bodyShoulders.func_78785_a(f5);
    legRight.func_78785_a(f5);
    armRight.func_78785_a(f5);
    bodyWaist.func_78785_a(f5);
    head.func_78785_a(f5);
  }
  
  public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
  {
    head.field_78796_g = (par4 / 57.295776F);
    head.field_78795_f = (par5 / 57.295776F);
    armRight.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
    armLeft.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
    armRight.field_78808_h = 0.0F;
    armLeft.field_78808_h = 0.0F;
    legRight.field_78795_f = Math.max(MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2 - 0.27314404F, -0.8F);
    
    legLeft.field_78795_f = Math.max(MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2 - 0.27314404F, -0.8F);
    
    legRight.field_78796_g = 0.0F;
    legLeft.field_78796_g = 0.0F;
    
    skirt1.field_78795_f = Math.min(legRight.field_78795_f, legLeft.field_78795_f);
    skirt2.field_78795_f = Math.max(Math.max(legRight.field_78795_f, legLeft.field_78795_f), 0.2F);
    
    if (field_78093_q) {
      armRight.field_78795_f += -0.62831855F;
      armLeft.field_78795_f += -0.62831855F;
      legRight.field_78795_f = -1.2566371F;
      legLeft.field_78795_f = -1.2566371F;
      legRight.field_78796_g = 0.31415927F;
      legLeft.field_78796_g = -0.31415927F;
    }
    
    armRight.field_78796_g = 0.0F;
    armLeft.field_78796_g = 0.0F;
    























    armRight.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
    armLeft.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
    armRight.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    armLeft.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    


















    EntityLilith entityDemon = (EntityLilith)entity;
    int i = entityDemon.getAttackTimer();
    
    if (i > 0) {
      float di = 10.0F;
      armRight.field_78795_f = (-2.0F + 1.5F * (Math.abs((i - par4) % 10.0F - di * 0.5F) - di * 0.25F) / (di * 0.25F));
    }
  }
}
