package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityVampire;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;






@SideOnly(Side.CLIENT)
public class ModelVampireArmor
  extends ModelBiped
{
  private ModelRenderer skirtFront;
  private ModelRenderer skirtMiddle;
  private ModelRenderer skirtMiddle2;
  private ModelRenderer skirtMiddle3;
  private ModelRenderer skirtBack;
  private ModelRenderer cloakMain;
  private ModelRenderer cloakLeft;
  private ModelRenderer cloakRight;
  public ModelRenderer hat;
  public ModelRenderer hatBrim;
  public ModelRenderer chest;
  private boolean legs;
  private boolean female;
  private boolean metal;
  
  public ModelVampireArmor(float scale, boolean legs, boolean female, boolean metal)
  {
    super(scale, 0.0F, 64, 96);
    
    this.legs = legs;
    this.female = female;
    this.metal = metal;
    
    skirtBack = new ModelRenderer(this, 26, 32);
    skirtBack.func_78793_a(0.0F, 11.0F, 0.0F);
    skirtBack.func_78790_a(-4.5F, 0.0F, -2.5F, 9, 12, 5, 0.0F);
    
    skirtFront = new ModelRenderer(this, 26, 50);
    skirtFront.func_78793_a(0.0F, 11.0F, 0.0F);
    skirtFront.func_78790_a(-4.5F, 0.0F, -2.5F, 9, 12, 5, 0.0F);
    
    skirtMiddle = new ModelRenderer(this, 26, 68);
    skirtMiddle.func_78793_a(0.0F, 11.0F, 0.0F);
    skirtMiddle.func_78790_a(-4.5F, 0.0F, -2.5F, 9, 12, 5, 0.0F);
    skirtMiddle2 = new ModelRenderer(this, 26, 68);
    skirtMiddle2.func_78793_a(0.0F, 11.0F, 0.0F);
    skirtMiddle2.func_78790_a(-4.5F, 0.0F, -2.5F, 9, 12, 5, 0.0F);
    
    skirtMiddle3 = new ModelRenderer(this, 26, 68);
    skirtMiddle3.func_78793_a(0.0F, 11.0F, 0.0F);
    skirtMiddle3.func_78790_a(-4.5F, 0.0F, -2.5F, 9, 12, 5, 0.0F);
    
    cloakLeft = new ModelRenderer(this, 0, 56);
    cloakLeft.func_78793_a(0.0F, 0.0F, 0.0F);
    cloakLeft.func_78790_a(-3.5F, -8.0F, 4.0F, 7, 7, 1, 0.0F);
    setRotateAngle(cloakLeft, -0.34906584F, 0.5108652F, 0.41086525F);
    
    cloakRight = new ModelRenderer(this, 0, 56);
    cloakRight.func_78793_a(0.0F, 0.0F, 0.0F);
    cloakRight.func_78790_a(-3.5F, -8.0F, 4.0F, 7, 7, 1, 0.0F);
    setRotateAngle(cloakRight, -0.34906584F, -0.5108652F, -0.41086525F);
    
    cloakMain = new ModelRenderer(this, 0, 33);
    cloakMain.func_78793_a(0.0F, 1.0F, 0.0F);
    cloakMain.func_78790_a(-6.0F, 0.0F, 2.5F, 12, 22, 1, 0.0F);
    setRotateAngle(cloakMain, 0.045553092F, 0.0F, 0.0F);
    
    float hatScale = 0.6F;
    
    hatBrim = new ModelRenderer(this, 0, 85);
    hatBrim.func_78793_a(0.0F, 0.0F, 0.0F);
    hatBrim.func_78790_a(-5.0F, -7.0F, -5.0F, 10, 1, 10, hatScale + 0.1F);
    
    hat = new ModelRenderer(this, 0, 67);
    hat.func_78793_a(0.0F, 0.0F, 0.0F);
    hat.func_78790_a(-4.0F, -15.0F, -4.0F, 8, 8, 8, hatScale);
    
    if (!metal) {
      field_78116_c.func_78792_a(hat);
      field_78116_c.func_78792_a(hatBrim);
    }
    
    chest = new ModelRenderer(this, 16, 67);
    chest.func_78790_a(-4.0F, -2.0F, -5.0F, 8, 4, 4, 0.0F);
    chest.func_78793_a(0.0F, 2.0F, 0.0F);
    setRotateAngle(chest, 0.7853982F, 0.0F, 0.0F);
  }
  
  ResourceLocation chain = new ResourceLocation("witchery", "textures/entities/vampirearmor_chain.png");
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    
    if (!metal) {
      field_78114_d.field_78806_j = false;
    }
    
    if (legs) {
      if ((!field_78093_q) && (field_78123_h.field_78806_j) && (female)) {
        skirtBack.func_78785_a(f5);
        skirtFront.func_78785_a(f5);
        skirtMiddle.func_78785_a(f5);
        skirtMiddle2.func_78785_a(f5);
        skirtMiddle3.func_78785_a(f5);
      }
    } else if (field_78115_e.field_78806_j) {
      if (!(entity instanceof EntityVampire)) {
        cloakRight.func_78785_a(f5);
        cloakLeft.func_78785_a(f5);
        cloakMain.func_78785_a(f5);
      }
      if (female) {
        chest.func_78785_a(f5);
      }
      if (metal) {
        GL11.glPushMatrix();
        float scale = 1.06F;
        GL11.glScalef(scale, scale, scale);
        
        Minecraft.func_71410_x().func_110434_K().func_110577_a(chain);
        if (female) {
          chest.func_78785_a(f5);
        }
        
        field_78115_e.func_78785_a(f5);
        
        GL11.glScalef(scale, scale, scale); ModelRenderer 
          tmp243_240 = field_78112_f;243240field_78797_d = ((float)(243240field_78797_d - 0.05D)); ModelRenderer 
          tmp260_257 = field_78113_g;260257field_78797_d = ((float)(260257field_78797_d - 0.05D));
        field_78112_f.func_78785_a(f5);
        field_78113_g.func_78785_a(f5);
        GL11.glPopMatrix();
      }
    }
  }
  
  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  

  public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
  {
    super.func_78087_a(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
    
    field_78112_f.field_78797_d = 2.0F;
    field_78113_g.field_78797_d = 2.0F;
    
    hat.field_78797_d = -0.5F;
    
    skirtBack.field_78795_f = Math.max(field_78123_h.field_78795_f, field_78124_i.field_78795_f);
    skirtMiddle.field_78795_f = (Math.max(field_78123_h.field_78795_f, field_78124_i.field_78795_f) * 0.5F);
    
    skirtFront.field_78795_f = Math.min(field_78123_h.field_78795_f, field_78124_i.field_78795_f);
    skirtMiddle2.field_78795_f = (Math.min(field_78123_h.field_78795_f, field_78124_i.field_78795_f) * 0.5F);
    
    if (field_78117_n) {
      skirtBack.field_78798_e = (skirtFront.field_78798_e = skirtMiddle3.field_78798_e = skirtMiddle.field_78798_e = skirtMiddle2.field_78798_e = 4.0F);
      skirtBack.field_78797_d = (skirtFront.field_78797_d = skirtMiddle3.field_78797_d = skirtMiddle.field_78797_d = skirtMiddle2.field_78797_d = 8.0F);
      
      cloakMain.field_78795_f = 0.6F;
    } else {
      skirtBack.field_78798_e = (skirtFront.field_78798_e = skirtMiddle3.field_78798_e = skirtMiddle.field_78798_e = skirtMiddle2.field_78798_e = 0.0F);
      skirtBack.field_78797_d = (skirtFront.field_78797_d = skirtMiddle3.field_78797_d = skirtMiddle.field_78797_d = skirtMiddle2.field_78797_d = 11.0F);
      
      cloakMain.field_78795_f = 0.045553092F;
      if (p_78087_2_ > 0.1D) {
        ModelRenderer tmp346_343 = cloakMain;346343field_78795_f = ((float)(346343field_78795_f + (p_78087_2_ * 0.8D - 0.1D)));
      }
    }
    
    if (field_78116_c.field_78795_f < -0.15D) {
      cloakLeft.field_78795_f = (field_78116_c.field_78795_f - 0.15F);
      cloakRight.field_78795_f = (field_78116_c.field_78795_f - 0.15F);
    } else {
      cloakLeft.field_78795_f = (cloakRight.field_78795_f = -0.3F);
    }
  }
}
