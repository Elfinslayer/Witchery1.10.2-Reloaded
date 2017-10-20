package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityHornedHuntsman;
import com.emoniph.witchery.util.Config;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelHornedAvatar extends ModelBase
{
  ModelRenderer horns;
  ModelRenderer head;
  ModelRenderer body;
  ModelRenderer rightarm;
  ModelRenderer rightleg;
  ModelRenderer chest;
  ModelRenderer rightshin;
  ModelRenderer rightfoot;
  ModelRenderer rightforearm;
  ModelRenderer hips;
  ModelRenderer leftarm;
  ModelRenderer leftforearm;
  ModelRenderer leftleg;
  ModelRenderer leftshin;
  ModelRenderer leftfoot;
  ModelRenderer spear;
  
  public ModelHornedAvatar()
  {
    field_78090_t = 128;
    field_78089_u = 128;
    func_78085_a("spear.shaft", 61, 14);
    func_78085_a("spear.tip1", 60, 8);
    func_78085_a("spear.tip2", 60, 5);
    
    horns = new ModelRenderer(this, 0, 88);
    horns.func_78789_a(-10.0F, -24.0F, 0.0F, 20, 17, 0);
    horns.func_78793_a(0.0F, -16.0F, 0.0F);
    horns.func_78787_b(128, 128);
    horns.field_78809_i = true;
    setRotation(horns, 0.0F, 0.0F, 0.0F);
    head = new ModelRenderer(this, 4, 112);
    head.func_78789_a(-4.0F, -8.0F, -4.0F, 8, 8, 8);
    head.func_78793_a(0.0F, -16.0F, 0.0F);
    head.func_78787_b(128, 128);
    head.field_78809_i = true;
    setRotation(head, 0.0F, 0.0F, 0.0F);
    body = new ModelRenderer(this, 12, 61);
    body.func_78789_a(-6.0F, 0.0F, -3.0F, 12, 8, 6);
    body.func_78793_a(0.0F, -8.0F, 0.0F);
    body.func_78787_b(128, 128);
    body.field_78809_i = true;
    setRotation(body, 0.0F, 0.0F, 0.0F);
    rightarm = new ModelRenderer(this, 72, 50);
    rightarm.func_78789_a(-4.0F, -2.0F, -2.0F, 4, 13, 4);
    rightarm.func_78793_a(-10.0F, -13.0F, 0.0F);
    rightarm.func_78787_b(128, 128);
    rightarm.field_78809_i = true;
    setRotation(rightarm, 0.0F, 0.0F, 0.0F);
    rightleg = new ModelRenderer(this, 72, 0);
    rightleg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 10, 4);
    rightleg.func_78793_a(-4.0F, 3.0F, -1.0F);
    rightleg.func_78787_b(128, 128);
    rightleg.field_78809_i = true;
    setRotation(rightleg, 0.5235988F, 0.0F, 0.0F);
    chest = new ModelRenderer(this, 0, 43);
    chest.func_78789_a(0.0F, 0.0F, 0.0F, 20, 8, 10);
    chest.func_78793_a(-10.0F, -16.0F, -5.0F);
    chest.func_78787_b(128, 128);
    chest.field_78809_i = true;
    setRotation(chest, 0.0F, 0.0F, 0.0F);
    rightshin = new ModelRenderer(this, 68, 14);
    rightshin.func_78789_a(-3.0F, -2.0F, -3.0F, 6, 14, 6);
    rightshin.func_78793_a(-4.0F, 12.0F, 5.0F);
    rightshin.func_78787_b(128, 128);
    rightshin.field_78809_i = true;
    setRotation(rightshin, -0.5235988F, 0.0F, 0.0F);
    rightfoot = new ModelRenderer(this, 69, 34);
    rightfoot.func_78789_a(-2.0F, 0.0F, -5.0F, 4, 3, 7);
    rightfoot.func_78793_a(-4.0F, 21.0F, 0.0F);
    rightfoot.func_78787_b(128, 128);
    rightfoot.field_78809_i = true;
    setRotation(rightfoot, 0.0F, 0.0F, 0.0F);
    rightforearm = new ModelRenderer(this, 68, 67);
    rightforearm.func_78789_a(-3.0F, 0.0F, -3.0F, 6, 14, 6);
    rightforearm.func_78793_a(-12.0F, -3.0F, 0.0F);
    rightforearm.func_78787_b(128, 128);
    rightforearm.field_78809_i = true;
    setRotation(rightforearm, -0.5235988F, 0.0F, 0.0F);
    hips = new ModelRenderer(this, 8, 75);
    hips.func_78789_a(-7.0F, 0.0F, -4.0F, 14, 4, 8);
    hips.func_78793_a(0.0F, 0.0F, 0.0F);
    hips.func_78787_b(128, 128);
    hips.field_78809_i = true;
    setRotation(hips, 0.0F, 0.0F, 0.0F);
    leftarm = new ModelRenderer(this, 72, 50);
    leftarm.func_78789_a(0.0F, -2.0F, -2.0F, 4, 13, 4);
    leftarm.func_78793_a(10.0F, -13.0F, 0.0F);
    leftarm.func_78787_b(128, 128);
    leftarm.field_78809_i = true;
    setRotation(leftarm, 0.0F, 0.0F, 0.0F);
    leftforearm = new ModelRenderer(this, 68, 67);
    leftforearm.func_78789_a(-3.0F, 0.0F, -3.0F, 6, 14, 6);
    leftforearm.func_78793_a(12.0F, -3.0F, 0.0F);
    leftforearm.func_78787_b(128, 128);
    leftforearm.field_78809_i = true;
    setRotation(leftforearm, -0.5235988F, 0.0F, 0.0F);
    leftleg = new ModelRenderer(this, 72, 0);
    leftleg.func_78789_a(-2.0F, 0.0F, -2.0F, 4, 10, 4);
    leftleg.func_78793_a(4.0F, 3.0F, -1.0F);
    leftleg.func_78787_b(128, 128);
    leftleg.field_78809_i = true;
    setRotation(leftleg, 0.5235988F, 0.0F, 0.0F);
    leftshin = new ModelRenderer(this, 68, 14);
    leftshin.func_78789_a(-3.0F, -2.0F, -3.0F, 6, 14, 6);
    leftshin.func_78793_a(4.0F, 12.0F, 5.0F);
    leftshin.func_78787_b(128, 128);
    leftshin.field_78809_i = true;
    setRotation(leftshin, -0.5235988F, 0.0F, 0.0F);
    leftfoot = new ModelRenderer(this, 69, 34);
    leftfoot.func_78789_a(-2.0F, 0.0F, -5.0F, 4, 3, 7);
    leftfoot.func_78793_a(4.0F, 21.0F, 0.0F);
    leftfoot.func_78787_b(128, 128);
    leftfoot.field_78809_i = true;
    setRotation(leftfoot, 0.0F, 0.0F, 0.0F);
    
    head.func_78792_a(horns);
    
    leftleg.func_78792_a(leftshin);
    leftshin.func_78792_a(leftfoot);
    rightleg.func_78792_a(rightshin);
    rightshin.func_78792_a(rightfoot);
    
    rightarm.func_78792_a(rightforearm);
    leftarm.func_78792_a(leftforearm);
    
    spear = new ModelRenderer(this, "spear");
    spear.func_78793_a(-12.0F, 4.0F, -10.0F);
    setRotation(spear, 0.0F, 0.0F, 0.0F);
    spear.field_78809_i = true;
    spear.func_78786_a("shaft", -0.5F, -30.0F, -0.5F, 1, 50, 1);
    spear.func_78786_a("tip1", -1.5F, -35.0F, 0.0F, 3, 6, 0);
    spear.func_78786_a("tip2", 0.0F, -35.0F, -1.5F, 0, 6, 3);
    
    rightforearm.func_78792_a(spear);
    
    horns.func_78793_a(0.0F, 0.0F, 0.0F);
    leftforearm.func_78793_a(2.0F, 10.0F, 0.0F);
    rightforearm.func_78793_a(-2.0F, 10.0F, 0.0F);
    
    leftshin.func_78793_a(0.0F, 10.0F, 0.0F);
    leftshin.field_78795_f = -0.9F;
    
    leftfoot.func_78793_a(0.0F, 10.0F, 0.0F);
    leftfoot.field_78795_f = 0.5F;
    
    rightshin.func_78793_a(0.0F, 10.0F, 0.0F);
    rightshin.field_78795_f = -0.9F;
    
    rightfoot.func_78793_a(0.0F, 10.0F, 0.0F);
    rightfoot.field_78795_f = 0.5F;
    
    spear.func_78793_a(0.0F, 12.0F, 0.0F);
    spear.field_78795_f = 1.5F;
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    head.func_78785_a(f5);
    body.func_78785_a(f5);
    rightarm.func_78785_a(f5);
    rightleg.func_78785_a(f5);
    chest.func_78785_a(f5);
    hips.func_78785_a(f5);
    leftarm.func_78785_a(f5);
    leftleg.func_78785_a(f5);
    
    Minecraft mc = Minecraft.func_71410_x();
    if ((field_71474_y.field_74347_j) && (instancerenderHuntsmanGlintEffect)) {
      float f9 = field_70173_aa;
      field_71446_o.func_110577_a(RES_ITEM_GLINT);
      GL11.glEnable(3042);
      float f10 = 0.5F;
      GL11.glColor4f(f10, f10, f10, 1.0F);
      GL11.glDepthFunc(514);
      GL11.glDepthMask(false);
      
      for (int k = 0; k < 2; k++)
      {
        GL11.glDisable(2896);
        float f11 = 0.76F;
        GL11.glColor4f(0.0F * f11, 0.5F * f11, 0.0F * f11, 1.0F);
        GL11.glBlendFunc(768, 1);
        GL11.glMatrixMode(5890);
        GL11.glLoadIdentity();
        float f12 = f9 * (0.001F + k * 0.003F) * 20.0F;
        float f13 = 0.33333334F;
        GL11.glScalef(f13, f13, f13);
        GL11.glRotatef(30.0F - k * 60.0F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(0.0F, f12, 0.0F);
        GL11.glMatrixMode(5888);
        
        head.func_78785_a(f5);
        body.func_78785_a(f5);
        rightarm.func_78785_a(f5);
        rightleg.func_78785_a(f5);
        chest.func_78785_a(f5);
        hips.func_78785_a(f5);
        leftarm.func_78785_a(f5);
        leftleg.func_78785_a(f5);
      }
      
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glMatrixMode(5890);
      GL11.glDepthMask(true);
      GL11.glLoadIdentity();
      GL11.glMatrixMode(5888);
      GL11.glEnable(2896);
      GL11.glDisable(3042);
      GL11.glDepthFunc(515);
    }
  }
  
  private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    field_78795_f = x;
    field_78796_g = y;
    field_78808_h = z;
  }
  



  public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
  {
    head.field_78796_g = (par4 / 57.295776F);
    head.field_78795_f = (par5 / 57.295776F);
    
    leftleg.field_78795_f = (-1.3F * func_78172_a(par1, 13.0F) * par2 + 0.5F);
    rightleg.field_78795_f = (1.3F * func_78172_a(par1, 13.0F) * par2 + 0.5F);
    
    float angle = -1.5F * func_78172_a(par1, 13.0F) * par2 - 1.0F;
    
    leftshin.field_78795_f = (0.8F * (rightleg.field_78795_f - 0.5F) - 1.0F);
    rightshin.field_78795_f = (0.8F * (leftleg.field_78795_f - 0.5F) - 1.0F);
    
    leftfoot.field_78795_f = Math.max(1.4F * (leftleg.field_78795_f - 0.5F) + 0.5F, 0.2F);
    rightfoot.field_78795_f = Math.max(1.4F * (rightleg.field_78795_f - 0.5F) + 0.5F, 0.2F);
    
    leftleg.field_78796_g = 0.0F;
    rightleg.field_78796_g = 0.0F;
  }
  





  public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4)
  {
    EntityHornedHuntsman entityDemon = (EntityHornedHuntsman)par1EntityLiving;
    int i = entityDemon.getAttackTimer();
    
    if (i > 0) {
      rightarm.field_78795_f = (-2.0F + 1.5F * func_78172_a(i - par4, 10.0F));
    }
    else
    {
      rightarm.field_78795_f = ((-0.2F + 1.5F * func_78172_a(par2, 13.0F)) * par3 * 0.2F);
      leftarm.field_78795_f = ((-0.2F - 1.5F * func_78172_a(par2, 13.0F)) * par3 * 0.2F);
    }
  }
  
  private float func_78172_a(float par1, float par2) {
    return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
  }
}
