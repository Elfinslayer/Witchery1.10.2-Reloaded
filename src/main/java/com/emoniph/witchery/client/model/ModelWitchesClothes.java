package com.emoniph.witchery.client.model;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.item.ItemWitchesClothes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;




@SideOnly(Side.CLIENT)
public class ModelWitchesClothes
  extends ModelBiped
{
  ModelRenderer hat;
  ModelRenderer torso;
  ModelRenderer bottomBack;
  ModelRenderer bottomRight;
  ModelRenderer bottomLeft;
  ModelRenderer Shape1;
  ModelRenderer Shape2;
  ModelRenderer Shape3;
  ModelRenderer headRight1;
  ModelRenderer headLeft1;
  ModelRenderer legRightF;
  ModelRenderer legLeftF;
  ModelRenderer legRightB;
  ModelRenderer legLeftB;
  ModelRenderer bodyF;
  ModelRenderer bodyB;
  ModelRenderer armRightF;
  ModelRenderer armLeftF;
  ModelRenderer armRightB;
  ModelRenderer armLeftB;
  ModelRenderer armLeftOut;
  ModelRenderer armRightOut;
  ModelRenderer spikeLowerRight;
  ModelRenderer spikeLowerLeft;
  ModelRenderer spikeUpperLeft;
  ModelRenderer spikeUpperRight;
  ModelRenderer shoulderRight;
  ModelRenderer shoulderLeft;
  private ModelRenderer babasHat;
  
  public ModelWitchesClothes(float scale, boolean shoulders)
  {
    super(scale, 0.0F, 128, 64);
    
    func_78085_a("hat.hatBrim", 0, 49);
    func_78085_a("hat.hatCollar", 0, 36);
    func_78085_a("hat.hatBody", 31, 34);
    func_78085_a("hat.hatPoint", 50, 34);
    

    hat = new ModelRenderer(this, "hat");
    hat.func_78793_a(0.0F, 0.0F, 0.0F);
    setRotation(hat, 0.0F, 0.0F, 0.0F);
    hat.field_78809_i = true;
    hat.func_78786_a("hatBrim", -7.0F, -7.0F, -7.0F, 14, 1, 14);
    hat.func_78786_a("hatCollar", -5.0F, -9.0F, -5.0F, 10, 2, 10);
    hat.func_78786_a("hatBody", -3.0F, -14.0F, -3.0F, 6, 5, 6);
    hat.func_78786_a("hatPoint", -1.0F, -17.0F, -1.0F, 2, 3, 2);
    field_78116_c.func_78792_a(hat);
    











    babasHat = new ModelRenderer(this, 72, 48);
    babasHat.func_78793_a(-7.0F, -8.0F, -7.0F);
    babasHat.func_78790_a(0.0F, 0.0F, 0.0F, 14, 2, 14, 0.52F);
    setRotation(babasHat, 0.0F, 0.0F, 0.0F);
    babasHat.field_78809_i = true;
    field_78116_c.func_78792_a(babasHat);
    
    ModelRenderer modelrenderer = new ModelRenderer(this, 83, 29);
    modelrenderer.func_78793_a(3.75F, -4.0F, 4.0F);
    modelrenderer.func_78790_a(0.0F, 0.0F, 0.0F, 7, 4, 7, 0.4F);
    field_78795_f = -0.05235988F;
    field_78808_h = 0.02617994F;
    babasHat.func_78792_a(modelrenderer);
    
    ModelRenderer modelrenderer1 = new ModelRenderer(this, 83, 40);
    modelrenderer1.func_78793_a(1.75F, -4.0F, 2.0F);
    modelrenderer1.func_78789_a(0.0F, 0.0F, 0.0F, 4, 4, 4);
    field_78795_f = -0.10471976F;
    field_78808_h = 0.05235988F;
    modelrenderer.func_78792_a(modelrenderer1);
    
    ModelRenderer modelrenderer2 = new ModelRenderer(this, 81, 48);
    modelrenderer2.func_78793_a(1.75F, -2.0F, 2.0F);
    modelrenderer2.func_78790_a(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.25F);
    field_78795_f = -0.20943952F;
    field_78808_h = 0.10471976F;
    modelrenderer1.func_78792_a(modelrenderer2);
    

    torso = new ModelRenderer(this, 43, 46);
    torso.func_78790_a(-4.0F, 0.0F, -2.0F, 8, 6, 4, scale);
    torso.func_78793_a(0.0F, 12.0F, 0.0F);
    torso.field_78809_i = true;
    setRotation(torso, 0.0F, 0.0F, 0.0F);
    field_78115_e.func_78792_a(torso);
    
    if (shoulders) {
      Shape1 = new ModelRenderer(this, 61, 32);
      Shape1.func_78790_a(0.0F, 0.0F, 0.0F, 5, 1, 6, scale + 0.1F);
      Shape1.func_78793_a(-9.0F, 0.0F, -3.0F);
      
      Shape1.field_78809_i = true;
      
      Shape2 = new ModelRenderer(this, 61, 39);
      Shape2.func_78790_a(0.0F, 0.0F, 0.0F, 5, 1, 6, scale + 0.1F);
      Shape2.func_78793_a(4.0F, 0.0F, -3.0F);
      
      Shape2.field_78809_i = true;
      

      Shape2.func_78793_a(0.0F, -2.0F, -3.0F);
      Shape1.func_78793_a(-4.0F, -2.0F, -3.0F);
      
      field_78112_f.func_78792_a(Shape1);
      field_78113_g.func_78792_a(Shape2);
    }
    
    headRight1 = new ModelRenderer(this, 124, 0);
    headRight1.func_78789_a(-0.5F, -5.0F, -0.5F, 1, 5, 1);
    headRight1.func_78793_a(-4.0F, 0.0F, 0.0F);
    headRight1.func_78787_b(64, 128);
    headRight1.field_78809_i = true;
    setRotation(headRight1, -0.1487144F, 0.0F, -0.4089647F);
    
    headLeft1 = new ModelRenderer(this, 124, 0);
    headLeft1.func_78789_a(-0.5F, -5.0F, -0.5F, 1, 5, 1);
    headLeft1.func_78793_a(4.0F, 0.0F, 0.0F);
    headLeft1.func_78787_b(64, 128);
    headLeft1.field_78809_i = true;
    setRotation(headLeft1, -0.1487144F, 0.0F, 0.4089647F);
    
    legRightF = new ModelRenderer(this, 95, 0);
    legRightF.func_78789_a(0.0F, 0.0F, 0.0F, 2, 7, 1);
    legRightF.func_78793_a(-4.0F, 13.0F, -3.0F);
    legRightF.func_78787_b(64, 128);
    legRightF.field_78809_i = true;
    setRotation(legRightF, 0.0F, 0.0F, -0.2230717F);
    
    legLeftF = new ModelRenderer(this, 95, 0);
    legLeftF.func_78789_a(0.0F, 0.0F, 0.0F, 2, 7, 1);
    legLeftF.func_78793_a(1.0F, 13.0F, -3.0F);
    legLeftF.func_78787_b(64, 128);
    legLeftF.field_78809_i = true;
    setRotation(legLeftF, 0.0F, 0.0F, 0.1230717F);
    
    legRightB = new ModelRenderer(this, 95, 0);
    legRightB.func_78789_a(0.0F, 0.0F, 0.0F, 2, 7, 1);
    legRightB.func_78793_a(-4.0F, 13.0F, 2.0F);
    legRightB.func_78787_b(64, 128);
    legRightB.field_78809_i = true;
    setRotation(legRightB, 0.0F, 0.0F, -0.2230717F);
    
    legLeftB = new ModelRenderer(this, 95, 0);
    legLeftB.func_78789_a(0.0F, 0.0F, 0.0F, 2, 7, 1);
    legLeftB.func_78793_a(1.0F, 13.0F, 2.0F);
    legLeftB.func_78787_b(64, 128);
    legLeftB.field_78809_i = true;
    setRotation(legLeftB, 0.0F, 0.0F, 0.1230717F);
    
    bodyF = new ModelRenderer(this, 111, 0);
    bodyF.func_78789_a(0.0F, 0.0F, 0.0F, 6, 9, 1);
    bodyF.func_78793_a(-2.5F, 1.0F, -3.1F);
    bodyF.func_78787_b(64, 128);
    bodyF.field_78809_i = true;
    setRotation(bodyF, 0.0F, 0.0F, 0.1487144F);
    
    bodyB = new ModelRenderer(this, 111, 0);
    bodyB.func_78789_a(0.0F, 0.0F, 0.0F, 6, 9, 1);
    bodyB.func_78793_a(-2.5F, 1.0F, 2.1F);
    bodyB.func_78787_b(64, 128);
    bodyB.field_78809_i = true;
    setRotation(bodyB, 0.0F, 0.0F, 0.0887144F);
    
    armRightF = new ModelRenderer(this, 102, 0);
    armRightF.func_78789_a(0.0F, 0.0F, 0.0F, 3, 7, 1);
    armRightF.func_78793_a(-8.0F, 3.0F, -3.0F);
    armRightF.func_78787_b(64, 128);
    armRightF.field_78809_i = true;
    setRotation(armRightF, 0.0F, 0.0F, -0.1487144F);
    
    armLeftF = new ModelRenderer(this, 102, 0);
    armLeftF.func_78789_a(0.0F, 0.0F, 0.0F, 3, 6, 1);
    armLeftF.func_78793_a(5.0F, 2.0F, -3.0F);
    armLeftF.func_78787_b(64, 128);
    armLeftF.field_78809_i = true;
    setRotation(armLeftF, 0.0F, 0.0F, 0.0687144F);
    
    armRightB = new ModelRenderer(this, 102, 0);
    armRightB.func_78789_a(0.0F, 0.0F, 0.0F, 3, 7, 1);
    armRightB.func_78793_a(-8.0F, 3.0F, 2.0F);
    armRightB.func_78787_b(64, 128);
    armRightB.field_78809_i = true;
    setRotation(armRightB, 0.0F, 0.0F, -0.1487144F);
    
    armLeftB = new ModelRenderer(this, 102, 0);
    armLeftB.func_78789_a(0.0F, 0.0F, 0.0F, 3, 6, 1);
    armLeftB.func_78793_a(5.0F, 2.0F, 2.0F);
    armLeftB.func_78787_b(64, 128);
    armLeftB.field_78809_i = true;
    setRotation(armLeftB, 0.0F, 0.0F, 0.0687144F);
    
    armLeftOut = new ModelRenderer(this, 120, 0);
    armLeftOut.func_78789_a(0.0F, 0.0F, 0.0F, 1, 7, 3);
    armLeftOut.func_78793_a(8.0F, 2.0F, -1.5F);
    armLeftOut.func_78787_b(128, 64);
    armLeftOut.field_78809_i = true;
    setRotation(armLeftOut, 0.0371786F, 0.0F, 0.0F);
    armRightOut = new ModelRenderer(this, 120, 0);
    armRightOut.func_78789_a(0.0F, 0.0F, 0.0F, 1, 6, 3);
    armRightOut.func_78793_a(-9.0F, 2.0F, -1.0F);
    armRightOut.func_78787_b(128, 64);
    armRightOut.field_78809_i = true;
    setRotation(armRightOut, -0.1858931F, 0.0F, 0.0F);
    spikeLowerRight = new ModelRenderer(this, 120, 0);
    spikeLowerRight.func_78789_a(-0.5F, -6.0F, -0.5F, 1, 6, 1);
    spikeLowerRight.func_78793_a(-1.0F, 7.0F, 2.0F);
    spikeLowerRight.func_78787_b(128, 64);
    spikeLowerRight.field_78809_i = true;
    setRotation(spikeLowerRight, -0.7807508F, -0.1858931F, 0.0F);
    spikeLowerLeft = new ModelRenderer(this, 120, 0);
    spikeLowerLeft.func_78789_a(-0.5F, -6.0F, -0.5F, 1, 6, 1);
    spikeLowerLeft.func_78793_a(1.0F, 7.0F, 2.0F);
    spikeLowerLeft.func_78787_b(128, 64);
    spikeLowerLeft.field_78809_i = true;
    setRotation(spikeLowerLeft, -0.7807508F, 0.1858931F, 0.0F);
    spikeUpperLeft = new ModelRenderer(this, 120, 0);
    spikeUpperLeft.func_78789_a(-0.5F, -6.0F, -0.5F, 1, 6, 1);
    spikeUpperLeft.func_78793_a(2.0F, 3.0F, 2.0F);
    spikeUpperLeft.func_78787_b(128, 64);
    spikeUpperLeft.field_78809_i = true;
    setRotation(spikeUpperLeft, -0.7807508F, 0.1858931F, 0.0F);
    spikeUpperRight = new ModelRenderer(this, 120, 0);
    spikeUpperRight.func_78789_a(-0.5F, -6.0F, -0.5F, 1, 6, 1);
    spikeUpperRight.func_78793_a(-2.0F, 3.0F, 2.0F);
    spikeUpperRight.func_78787_b(128, 64);
    spikeUpperRight.field_78809_i = true;
    setRotation(spikeUpperRight, -0.7807508F, -0.1858931F, 0.0F);
    shoulderRight = new ModelRenderer(this, 108, 0);
    shoulderRight.func_78789_a(0.0F, 0.0F, 0.0F, 5, 1, 5);
    shoulderRight.func_78793_a(-9.0F, -1.5F, -2.5F);
    shoulderRight.func_78787_b(128, 64);
    shoulderRight.field_78809_i = true;
    setRotation(shoulderRight, 0.0371786F, -0.1115358F, -0.1230717F);
    shoulderLeft = new ModelRenderer(this, 108, 0);
    shoulderLeft.func_78789_a(0.0F, 0.0F, 0.0F, 5, 1, 5);
    shoulderLeft.func_78793_a(4.0F, -2.5F, -1.5F);
    shoulderLeft.func_78787_b(128, 64);
    shoulderLeft.field_78809_i = true;
    setRotation(shoulderLeft, 0.0F, 0.2974289F, 0.1830717F);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    if ((entity != null) && ((entity instanceof EntityLivingBase))) {
      EntityLivingBase living = (EntityLivingBase)entity;
      
      ItemStack hatStack = living.func_71124_b(4);
      if ((hatStack != null) && (field_78116_c.field_78806_j)) {
        boolean baba = hatStack.func_77973_b() == ItemsBABAS_HAT;
        hatfield_78806_j = (!baba);
        babasHat.field_78806_j = baba;
      }
    }
    
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    if ((entity != null) && ((entity instanceof EntityLivingBase))) {
      EntityLivingBase living = (EntityLivingBase)entity;
      


      ItemStack belt = living.func_71124_b(2);
      if ((belt != null) && (belt.func_77973_b() == ItemsBARK_BELT) && (field_78115_e.field_78806_j)) {
        int charge = Math.min(ItemsBARK_BELT.getChargeLevel(belt), ItemsBARK_BELT.getMaxChargeLevel(living));
        
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        
        renderBark(f5, field_78115_e, bodyF, charge >= 1);
        renderBark(f5, field_78115_e, bodyB, charge >= 1);
        charge--;
        renderBark(f5, field_78115_e, spikeLowerLeft, charge >= 1);
        renderBark(f5, field_78115_e, spikeLowerRight, charge >= 1);
        renderBark(f5, field_78115_e, spikeUpperLeft, charge >= 1);
        renderBark(f5, field_78115_e, spikeUpperRight, charge >= 1);
        
        charge--;
        
        ItemStack shoes = living.func_71124_b(1);
        if ((shoes != null) && ((shoes.func_77973_b() instanceof ItemWitchesClothes))) {
          renderBark(f5, field_78123_h, legRightF, (charge >= 1) && (field_78115_e.field_78806_j), true);
          renderBark(f5, field_78123_h, legRightB, (charge >= 1) && (field_78115_e.field_78806_j), true);
          charge--;
          renderBark(f5, field_78124_i, legLeftF, (charge >= 1) && (field_78115_e.field_78806_j), true);
          renderBark(f5, field_78124_i, legLeftB, (charge >= 1) && (field_78115_e.field_78806_j), true);
          charge--;
        }
        
        ItemStack robes = living.func_71124_b(3);
        if ((robes != null) && ((robes.func_77973_b() instanceof ItemWitchesClothes))) {
          renderBark(f5, field_78112_f, armRightF, charge >= 1);
          renderBark(f5, field_78112_f, armRightOut, charge >= 1);
          renderBark(f5, field_78112_f, armRightB, charge >= 1);
          charge--;
          
          renderBark(f5, field_78113_g, armLeftB, charge >= 1);
          renderBark(f5, field_78113_g, armLeftF, charge >= 1);
          renderBark(f5, field_78113_g, armLeftOut, charge >= 1);
          charge--;
        }
        
        ItemStack hat = living.func_71124_b(4);
        if ((hat != null) && ((hat.func_77973_b() instanceof ItemWitchesClothes))) {
          renderBark(f5, field_78112_f, headRight1, charge >= 1);
          renderBark(f5, field_78112_f, shoulderRight, charge >= 1);
          charge--;
          
          renderBark(f5, field_78113_g, headLeft1, charge >= 1);
          renderBark(f5, field_78113_g, shoulderLeft, charge >= 1);
        }
      }
    }
  }
  
  private void renderBark(float f5, ModelRenderer bodyPart, ModelRenderer barkPiece, boolean visible)
  {
    renderBark(f5, bodyPart, barkPiece, visible, false);
  }
  
  private void renderBark(float f5, ModelRenderer bodyPart, ModelRenderer barkPiece, boolean visible, boolean leg) {
    if (visible) {
      GL11.glTranslatef(field_82906_o, field_82908_p, field_82907_q);
      if ((field_78795_f == 0.0F) && (field_78796_g == 0.0F) && (field_78808_h == 0.0F) && (!leg)) {
        if ((field_78800_c == 0.0F) && (field_78797_d == 0.0F) && (field_78798_e == 0.0F)) {
          barkPiece.func_78785_a(f5);
        } else {
          GL11.glTranslatef(field_78800_c * f5, field_78797_d * f5, field_78798_e * f5);
          barkPiece.func_78785_a(f5);
          GL11.glTranslatef(-field_78800_c * f5, -field_78797_d * f5, -field_78798_e * f5);
        }
      } else {
        GL11.glPushMatrix();
        GL11.glTranslatef(field_78800_c * f5, field_78797_d * f5, field_78798_e * f5);
        
        if (field_78808_h != 0.0F) {
          GL11.glRotatef(field_78808_h * 57.295776F, 0.0F, 0.0F, 1.0F);
        }
        
        if (field_78796_g != 0.0F) {
          GL11.glRotatef(field_78796_g * 57.295776F, 0.0F, 1.0F, 0.0F);
        }
        
        if (field_78795_f != 0.0F) {
          GL11.glRotatef(field_78795_f * 57.295776F, 1.0F, 0.0F, 0.0F);
        }
        GL11.glTranslatef(-field_78800_c * f5, -field_78797_d * f5, -field_78798_e * f5);
        if ((field_78117_n) && (leg)) {
          GL11.glTranslatef(0.0F, -3.0F * f5, 4.0F * f5);
          barkPiece.func_78785_a(f5);
        } else {
          barkPiece.func_78785_a(f5);
        }
        
        GL11.glPopMatrix();
      }
      GL11.glTranslatef(-field_82906_o, -field_82908_p, -field_82907_q);
    }
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
}
