package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;





@SideOnly(Side.CLIENT)
public class ModelWitchHand
  extends ModelBase
{
  ModelRenderer wrist;
  ModelRenderer palmUpper;
  ModelRenderer palmLower;
  ModelRenderer finger1Upper;
  ModelRenderer finger2Upper;
  ModelRenderer finger3Upper;
  ModelRenderer finger1Lower;
  ModelRenderer finger2Lower;
  ModelRenderer finger3Lower;
  ModelRenderer rightPalm;
  ModelRenderer rightFingerUpper;
  ModelRenderer rightFingerLower;
  ModelRenderer rightThumbUpper;
  ModelRenderer rightThumbLower;
  ModelRenderer leftPalm;
  ModelRenderer leftFingerUpper;
  ModelRenderer leftFingerLower;
  ModelRenderer leftThumbUpper;
  ModelRenderer leftThumbLower;
  ModelRenderer scythe;
  
  public ModelWitchHand(float scale)
  {
    field_78090_t = 64;
    field_78089_u = 64;
    
    wrist = new ModelRenderer(this, 0, 0);
    wrist.func_78789_a(0.0F, 0.0F, 0.0F, 5, 5, 2);
    wrist.func_78793_a(-3.0F, 0.0F, 0.0F);
    wrist.func_78787_b(64, 64);
    wrist.field_78809_i = true;
    setRotation(wrist, 0.0F, 0.0F, 0.0F);
    palmUpper = new ModelRenderer(this, 0, 7);
    palmUpper.func_78790_a(0.0F, 0.0F, 0.0F, 5, 1, 5, scale);
    palmUpper.func_78793_a(-3.0F, 0.0F, -5.0F);
    palmUpper.func_78787_b(64, 64);
    palmUpper.field_78809_i = true;
    setRotation(palmUpper, 0.0F, 0.0F, 0.0F);
    palmLower = new ModelRenderer(this, 0, 13);
    palmLower.func_78790_a(0.0F, 0.0F, 0.0F, 5, 2, 3, scale);
    palmLower.func_78793_a(-3.0F, 1.0F, -3.0F);
    palmLower.func_78787_b(64, 64);
    palmLower.field_78809_i = true;
    setRotation(palmLower, 0.0F, 0.0F, 0.0F);
    finger1Upper = new ModelRenderer(this, 0, 18);
    finger1Upper.func_78790_a(0.0F, 0.0F, -2.0F, 1, 1, 4, scale);
    finger1Upper.func_78793_a(-3.0F, 1.0F, -7.0F);
    finger1Upper.func_78787_b(64, 64);
    finger1Upper.field_78809_i = true;
    setRotation(finger1Upper, 0.4833219F, 0.0F, 0.0F);
    finger2Upper = new ModelRenderer(this, 6, 19);
    finger2Upper.func_78790_a(0.0F, 0.0F, -2.0F, 1, 1, 4, scale);
    finger2Upper.func_78793_a(-1.0F, 1.0F, -7.0F);
    finger2Upper.func_78787_b(64, 64);
    finger2Upper.field_78809_i = true;
    setRotation(finger2Upper, 0.4833219F, 0.0F, 0.0F);
    finger3Upper = new ModelRenderer(this, 12, 18);
    finger3Upper.func_78790_a(0.0F, 0.0F, -2.0F, 1, 1, 4, scale);
    finger3Upper.func_78793_a(1.0F, 1.0F, -7.0F);
    finger3Upper.func_78787_b(64, 64);
    finger3Upper.field_78809_i = true;
    setRotation(finger3Upper, 0.4833219F, 0.0F, 0.0F);
    finger1Lower = new ModelRenderer(this, 0, 23);
    finger1Lower.func_78790_a(0.0F, 0.0F, -4.0F, 1, 1, 4, scale);
    finger1Lower.func_78793_a(-3.0F, 2.0F, -9.0F);
    finger1Lower.func_78787_b(64, 64);
    finger1Lower.field_78809_i = true;
    setRotation(finger1Lower, 2.044824F, 0.0F, 0.0F);
    finger2Lower = new ModelRenderer(this, 6, 24);
    finger2Lower.func_78790_a(0.0F, 0.0F, -4.0F, 1, 1, 4, scale);
    finger2Lower.func_78793_a(-1.0F, 2.0F, -9.0F);
    finger2Lower.func_78787_b(64, 64);
    finger2Lower.field_78809_i = true;
    setRotation(finger2Lower, 2.044824F, 0.0F, 0.0F);
    finger3Lower = new ModelRenderer(this, 12, 23);
    finger3Lower.func_78790_a(0.0F, 0.0F, -4.0F, 1, 1, 4, scale);
    finger3Lower.func_78793_a(1.0F, 2.0F, -9.0F);
    finger3Lower.func_78787_b(64, 64);
    finger3Lower.field_78809_i = true;
    setRotation(finger3Lower, 2.044824F, 0.0F, 0.0F);
    rightPalm = new ModelRenderer(this, 16, 0);
    rightPalm.func_78790_a(0.0F, 0.0F, 0.0F, 2, 1, 6, scale);
    rightPalm.func_78793_a(2.0F, 0.0F, -5.0F);
    rightPalm.func_78787_b(64, 64);
    rightPalm.field_78809_i = true;
    setRotation(rightPalm, 0.0F, 0.0F, 0.0F);
    rightFingerUpper = new ModelRenderer(this, 20, 7);
    rightFingerUpper.func_78790_a(0.0F, 0.0F, -4.0F, 1, 1, 4, scale);
    rightFingerUpper.func_78793_a(3.0F, 0.0F, -5.0F);
    rightFingerUpper.func_78787_b(64, 64);
    rightFingerUpper.field_78809_i = true;
    setRotation(rightFingerUpper, -0.5205006F, 0.0F, 0.0F);
    rightFingerLower = new ModelRenderer(this, 20, 12);
    rightFingerLower.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 4, scale);
    rightFingerLower.func_78793_a(3.0F, -1.0F, -8.0F);
    rightFingerLower.func_78787_b(64, 64);
    rightFingerLower.field_78809_i = true;
    setRotation(rightFingerLower, -2.732628F, 0.0F, 0.0F);
    rightThumbUpper = new ModelRenderer(this, 22, 17);
    rightThumbUpper.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 3, scale);
    rightThumbUpper.func_78793_a(3.5F, 1.0F, 0.0F);
    rightThumbUpper.func_78787_b(64, 64);
    rightThumbUpper.field_78809_i = true;
    setRotation(rightThumbUpper, -2.7F, -0.7F, -0.669215F);
    rightThumbLower = new ModelRenderer(this, 22, 21);
    rightThumbLower.func_78790_a(0.0F, -1.0F, -1.0F, 1, 1, 3, scale);
    rightThumbLower.func_78793_a(5.0F, 3.0F, -2.0F);
    rightThumbLower.func_78787_b(64, 64);
    rightThumbLower.field_78809_i = true;
    setRotation(rightThumbLower, -1.896109F, 0.2602503F, 0.3717861F);
    leftPalm = new ModelRenderer(this, 16, 0);
    leftPalm.func_78790_a(0.0F, 0.0F, 0.0F, 2, 1, 6, scale);
    leftPalm.func_78793_a(-5.0F, 0.0F, -5.0F);
    leftPalm.func_78787_b(64, 64);
    leftPalm.field_78809_i = true;
    setRotation(leftPalm, 0.0F, 0.0F, 0.0F);
    leftFingerUpper = new ModelRenderer(this, 20, 7);
    leftFingerUpper.func_78790_a(0.0F, 0.0F, -4.0F, 1, 1, 4, scale);
    leftFingerUpper.func_78793_a(-5.0F, 0.0F, -5.0F);
    leftFingerUpper.func_78787_b(64, 64);
    leftFingerUpper.field_78809_i = true;
    setRotation(leftFingerUpper, -0.5205006F, 0.0F, 0.0F);
    leftFingerLower = new ModelRenderer(this, 20, 12);
    leftFingerLower.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 4, scale);
    leftFingerLower.func_78793_a(-5.0F, -1.0F, -8.0F);
    leftFingerLower.func_78787_b(64, 64);
    leftFingerLower.field_78809_i = true;
    setRotation(leftFingerLower, -2.732628F, 0.0F, 0.0F);
    leftThumbUpper = new ModelRenderer(this, 22, 17);
    leftThumbUpper.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 3, scale);
    leftThumbUpper.func_78793_a(-5.0F, 1.0F, 0.0F);
    leftThumbUpper.func_78787_b(64, 64);
    leftThumbUpper.field_78809_i = true;
    setRotation(leftThumbUpper, -1.7F, 0.8F, 0.148711F);
    leftThumbLower = new ModelRenderer(this, 22, 21);
    leftThumbLower.func_78790_a(0.0F, 0.0F, 0.0F, 1, 1, 3, scale);
    leftThumbLower.func_78793_a(-6.0F, 4.0F, -1.0F);
    leftThumbLower.func_78787_b(64, 64);
    leftThumbLower.field_78809_i = true;
    setRotation(leftThumbLower, -2.082002F, 0.0371828F, -0.6320403F);
    
    func_78085_a("scythe.shaft", 58, 5);
    func_78085_a("scythe.blade", 36, 0);
    
    scythe = new ModelRenderer(this, "scythe");
    scythe.func_78793_a(-6.0F, 10.0F, 0.0F);
    setRotation(scythe, 0.0F, 0.0F, 0.0F);
    scythe.field_78809_i = true;
    scythe.func_78786_a("shaft", -0.5F, -16.0F, -0.5F, 1, 35, 1);
    scythe.func_78786_a("blade", 0.0F, -16.0F, 0.0F, 13, 4, 0);
  }
  

  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, boolean firstPerson, boolean deployed)
  {
    rightFingerUpper.func_78793_a(3.0F, 0.0F, -5.0F);
    rightFingerLower.func_78793_a(3.0F, -1.0F, -8.0F);
    
    leftFingerUpper.func_78793_a(-5.0F, 0.0F, -5.0F);
    leftFingerLower.func_78793_a(-5.0F, -1.0F, -8.0F);
    
    if (deployed) {
      rightFingerUpper.func_78793_a(3.0F, 4.0F, -4.0F);
      rightFingerLower.func_78793_a(3.0F, 1.0F, -4.0F);
      
      leftFingerUpper.func_78793_a(-5.0F, 4.0F, -4.0F);
      leftFingerLower.func_78793_a(-5.0F, 1.0F, -4.0F);
    }
    
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    
    wrist.func_78791_b(f5);
    palmUpper.func_78791_b(f5);
    palmLower.func_78791_b(f5);
    finger1Upper.func_78791_b(f5);
    finger2Upper.func_78791_b(f5);
    finger3Upper.func_78791_b(f5);
    finger1Lower.func_78791_b(f5);
    finger2Lower.func_78791_b(f5);
    finger3Lower.func_78791_b(f5);
    




    if (firstPerson) {
      rightPalm.func_78791_b(f5);
      rightFingerUpper.func_78791_b(f5);
      rightFingerLower.func_78791_b(f5);
      rightThumbUpper.func_78791_b(f5);
      rightThumbLower.func_78791_b(f5);
    } else {
      leftPalm.func_78791_b(f5);
      leftFingerUpper.func_78791_b(f5);
      leftFingerLower.func_78791_b(f5);
      leftThumbUpper.func_78791_b(f5);
      leftThumbLower.func_78791_b(f5);
    }
    
    if (deployed) {
      GL11.glScalef(1.2F, 1.2F, 1.2F);
      scythe.field_78808_h = -1.5707964F;
      scythe.field_78800_c = -5.0F;
      scythe.field_78798_e = -3.0F;
      scythe.field_78797_d = 2.0F;
      scythe.field_78795_f = 3.1415927F;
      scythe.field_78796_g = 0.0F;
      if (firstPerson) {
        scythe.field_78796_g = -3.1415927F;
        scythe.field_78800_c = 6.0F;
      }
      
      scythe.func_78791_b(f5);
    }
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
  }
}
