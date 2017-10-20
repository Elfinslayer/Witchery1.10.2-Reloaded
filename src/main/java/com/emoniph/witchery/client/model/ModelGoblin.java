package com.emoniph.witchery.client.model;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.entity.EntityGoblin;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelGoblin extends ModelBase
{
  public ModelRenderer bipedHead;
  public ModelRenderer bipedBody;
  public ModelRenderer bipedRightArm;
  public ModelRenderer bipedLeftArm;
  public ModelRenderer bipedRightLeg;
  public ModelRenderer bipedLeftLeg;
  public int heldItemLeft;
  public int heldItemRight;
  public boolean isSneak;
  public boolean aimedBow;
  
  public ModelGoblin()
  {
    this(0.0F);
  }
  
  public ModelGoblin(float scale) {
    field_78090_t = 64;
    field_78089_u = 32;
    
    func_78085_a("head.face", 0, 0);
    func_78085_a("head.nose1", 34, 3);
    func_78085_a("head.nose2", 34, 0);
    func_78085_a("head.nose3", 33, 9);
    func_78085_a("head.earTipLeft", 46, 0);
    func_78085_a("head.earInnerLeft", 39, 0);
    func_78085_a("head.earInnerRight", 39, 0);
    func_78085_a("head.earTipRight", 46, 0);
    
    bipedHead = new ModelRenderer(this, "head");
    bipedHead.func_78793_a(0.0F, 11.0F, 0.0F);
    setRotation(bipedHead, 0.0F, 0.0F, 0.0F);
    bipedHead.field_78809_i = true;
    bipedHead.func_78786_a("face", -4.0F, -8.0F, -4.0F, 8, 8, 8);
    bipedHead.func_78786_a("nose1", -0.5F, -6.0F, -5.0F, 1, 3, 1);
    bipedHead.func_78786_a("nose2", -0.5F, -5.0F, -6.0F, 1, 1, 1);
    bipedHead.func_78786_a("nose3", -0.5F, -4.0F, -7.0F, 1, 2, 2);
    bipedHead.func_78786_a("earTipLeft", 6.0F, -7.0F, 0.0F, 2, 2, 1);
    bipedHead.func_78786_a("earInnerLeft", 4.0F, -7.0F, 0.0F, 2, 3, 1);
    bipedHead.func_78786_a("earInnerRight", -6.0F, -7.0F, 0.0F, 2, 3, 1);
    bipedHead.func_78786_a("earTipRight", -8.0F, -7.0F, 0.0F, 2, 2, 1);
    bipedBody = new ModelRenderer(this, 16, 16);
    bipedBody.func_78790_a(-4.0F, 0.0F, -2.0F, 8, 7, 4, scale);
    bipedBody.func_78793_a(0.0F, 11.0F, 0.0F);
    bipedBody.func_78787_b(64, 32);
    bipedBody.field_78809_i = true;
    setRotation(bipedBody, 0.0F, 0.0F, 0.0F);
    bipedRightArm = new ModelRenderer(this, 40, 16);
    bipedRightArm.func_78790_a(-3.0F, -3.0F, -2.0F, 4, 12, 4, scale);
    bipedRightArm.func_78793_a(-5.0F, 12.0F, 0.0F);
    bipedRightArm.func_78787_b(64, 32);
    bipedRightArm.field_78809_i = true;
    setRotation(bipedRightArm, 0.0F, 0.0F, 0.0F);
    bipedLeftArm = new ModelRenderer(this, 40, 16);
    bipedLeftArm.func_78790_a(-1.0F, -3.0F, -2.0F, 4, 12, 4, scale);
    bipedLeftArm.func_78793_a(5.0F, 12.0F, 0.0F);
    bipedLeftArm.func_78787_b(64, 32);
    bipedLeftArm.field_78809_i = true;
    setRotation(bipedLeftArm, 0.0F, 0.0F, 0.0F);
    bipedRightLeg = new ModelRenderer(this, 0, 16);
    bipedRightLeg.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 6, 4, scale);
    bipedRightLeg.func_78793_a(-2.0F, 18.0F, 0.0F);
    bipedRightLeg.func_78787_b(64, 32);
    bipedRightLeg.field_78809_i = true;
    setRotation(bipedRightLeg, 0.0F, 0.0F, 0.0F);
    bipedLeftLeg = new ModelRenderer(this, 0, 16);
    bipedLeftLeg.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 6, 4, scale);
    bipedLeftLeg.func_78793_a(2.0F, 18.0F, 0.0F);
    bipedLeftLeg.func_78787_b(64, 32);
    bipedLeftLeg.field_78809_i = true;
    setRotation(bipedLeftLeg, 0.0F, 0.0F, 0.0F);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
    func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
    
    if (field_78091_s) {
      float f6 = 2.0F;
      GL11.glPushMatrix();
      GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
      GL11.glTranslatef(0.0F, 16.0F * par7, 0.0F);
      bipedHead.func_78785_a(par7);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
      GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
      bipedBody.func_78785_a(par7);
      bipedRightArm.func_78785_a(par7);
      bipedLeftArm.func_78785_a(par7);
      bipedRightLeg.func_78785_a(par7);
      bipedLeftLeg.func_78785_a(par7);
      GL11.glPopMatrix();
    } else {
      bipedHead.func_78785_a(par7);
      bipedBody.func_78785_a(par7);
      bipedRightArm.func_78785_a(par7);
      bipedLeftArm.func_78785_a(par7);
      bipedRightLeg.func_78785_a(par7);
      bipedLeftLeg.func_78785_a(par7);
    }
  }
  





  public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
  {
    bipedHead.field_78796_g = (par4 / 57.295776F);
    bipedHead.field_78795_f = (par5 / 57.295776F);
    bipedRightArm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
    bipedLeftArm.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
    bipedRightArm.field_78808_h = 0.0F;
    bipedLeftArm.field_78808_h = 0.0F;
    bipedRightLeg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2);
    bipedLeftLeg.field_78795_f = (MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
    bipedRightLeg.field_78796_g = 0.0F;
    bipedLeftLeg.field_78796_g = 0.0F;
    
    if (field_78093_q) {
      bipedRightArm.field_78795_f += -0.62831855F;
      bipedLeftArm.field_78795_f += -0.62831855F;
      bipedRightLeg.field_78795_f = -1.2566371F;
      bipedLeftLeg.field_78795_f = -1.2566371F;
      bipedRightLeg.field_78796_g = 0.31415927F;
      bipedLeftLeg.field_78796_g = -0.31415927F;
    }
    
    if (heldItemLeft != 0) {
      bipedLeftArm.field_78795_f = (bipedLeftArm.field_78795_f * 0.5F - 0.31415927F * heldItemLeft);
    }
    
    if (heldItemRight != 0) {
      bipedRightArm.field_78795_f = (bipedRightArm.field_78795_f * 0.5F - 0.31415927F * heldItemRight);
      if ((par7Entity != null) && ((par7Entity instanceof EntityGoblin))) {
        EntityGoblin goblin = (EntityGoblin)par7Entity;
        if (goblin.isWorking()) {
          if ((goblin.func_70694_bm() != null) && (goblin.func_70694_bm().func_77973_b() == ItemsKOBOLDITE_PICKAXE)) {
            ModelRenderer tmp336_333 = bipedRightArm;336333field_78795_f = ((float)(336333field_78795_f - field_70173_aa % 6 * 0.3D));
          } else {
            ModelRenderer tmp366_363 = bipedRightArm;366363field_78795_f = ((float)(366363field_78795_f - field_70173_aa % 20 * 0.1D));
          }
        }
      }
    }
    
    bipedRightArm.field_78796_g = 0.0F;
    bipedLeftArm.field_78796_g = 0.0F;
    


    if (field_78095_p > -9990.0F) {
      float f6 = field_78095_p;
      bipedBody.field_78796_g = (MathHelper.func_76126_a(MathHelper.func_76129_c(f6) * 3.1415927F * 2.0F) * 0.2F);
      bipedRightArm.field_78798_e = (MathHelper.func_76126_a(bipedBody.field_78796_g) * 5.0F);
      bipedRightArm.field_78800_c = (-MathHelper.func_76134_b(bipedBody.field_78796_g) * 5.0F);
      bipedLeftArm.field_78798_e = (-MathHelper.func_76126_a(bipedBody.field_78796_g) * 5.0F);
      bipedLeftArm.field_78800_c = (MathHelper.func_76134_b(bipedBody.field_78796_g) * 5.0F);
      bipedRightArm.field_78796_g += bipedBody.field_78796_g;
      bipedLeftArm.field_78796_g += bipedBody.field_78796_g;
      bipedLeftArm.field_78795_f += bipedBody.field_78796_g;
      f6 = 1.0F - field_78095_p;
      f6 *= f6;
      f6 *= f6;
      f6 = 1.0F - f6;
      float f7 = MathHelper.func_76126_a(f6 * 3.1415927F);
      float f8 = MathHelper.func_76126_a(field_78095_p * 3.1415927F) * -(bipedHead.field_78795_f - 0.7F) * 0.75F;
      bipedRightArm.field_78795_f = ((float)(bipedRightArm.field_78795_f - (f7 * 1.2D + f8)));
      bipedRightArm.field_78796_g += bipedBody.field_78796_g * 2.0F;
      bipedRightArm.field_78808_h = (MathHelper.func_76126_a(field_78095_p * 3.1415927F) * -0.4F);
    }
    
    boolean isWorshipping = (par7Entity != null) && ((par7Entity instanceof EntityGoblin)) && (((EntityGoblin)par7Entity).isWorshipping());
    
    if ((isSneak) || (isWorshipping)) {
      bipedBody.field_78795_f = 0.5F;
      bipedRightArm.field_78795_f -= 2.2F;
      bipedLeftArm.field_78795_f -= 2.2F;
      bipedRightLeg.field_78798_e = 3.0F;
      bipedLeftLeg.field_78798_e = 3.0F;
      




      bipedHead.field_78795_f = 0.5F;
      bipedRightLeg.field_78797_d = 18.0F;
      bipedLeftLeg.field_78797_d = 18.0F;
      bipedHead.field_78797_d = 13.0F;
      bipedBody.field_78797_d = 13.0F;
    } else {
      bipedBody.field_78795_f = 0.0F;
      bipedRightLeg.field_78798_e = 0.1F;
      bipedLeftLeg.field_78798_e = 0.1F;
      



      bipedRightLeg.field_78797_d = 18.0F;
      bipedLeftLeg.field_78797_d = 18.0F;
      bipedHead.field_78797_d = 11.0F;
      bipedBody.field_78797_d = 11.0F;
    }
    
    bipedRightArm.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
    bipedLeftArm.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
    bipedRightArm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    bipedLeftArm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    
    if (aimedBow) {
      float f6 = 0.0F;
      float f7 = 0.0F;
      bipedRightArm.field_78808_h = 0.0F;
      bipedLeftArm.field_78808_h = 0.0F;
      bipedRightArm.field_78796_g = (-(0.1F - f6 * 0.6F) + bipedHead.field_78796_g);
      bipedLeftArm.field_78796_g = (0.1F - f6 * 0.6F + bipedHead.field_78796_g + 0.4F);
      bipedRightArm.field_78795_f = (-1.5707964F + bipedHead.field_78795_f);
      bipedLeftArm.field_78795_f = (-1.5707964F + bipedHead.field_78795_f);
      bipedRightArm.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
      bipedLeftArm.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
      bipedRightArm.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
      bipedLeftArm.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
      bipedRightArm.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
      bipedLeftArm.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    }
  }
}
