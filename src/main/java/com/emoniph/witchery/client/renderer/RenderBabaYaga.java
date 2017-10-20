package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.client.model.ModelBabaYaga;
import com.emoniph.witchery.entity.EntityBabaYaga;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class RenderBabaYaga extends RenderLiving
{
  private static final ResourceLocation TEXTURES = new ResourceLocation("witchery", "textures/entities/babayaga.png");
  private final ModelBabaYaga model;
  
  public RenderBabaYaga() {
    super(new ModelBabaYaga(0.0F), 0.5F);
    model = ((ModelBabaYaga)field_77045_g);
  }
  
  public void func_82412_a(EntityBabaYaga par1EntityBabaYaga, double par2, double par4, double par6, float par8, float par9) {
    ItemStack itemstack = par1EntityBabaYaga.func_70694_bm();
    model.field_82900_g = (itemstack != null);
    net.minecraft.entity.boss.BossStatus.func_82824_a(par1EntityBabaYaga, true);
    super.func_76986_a(par1EntityBabaYaga, par2, par4, par6, par8, par9);
  }
  
  protected ResourceLocation getBabaYagaTextures(EntityBabaYaga par1EntityBabaYaga) {
    return TEXTURES;
  }
  
  protected void func_82411_a(EntityBabaYaga par1EntityBabaYaga, float par2) {
    float f1 = 1.0F;
    GL11.glColor3f(f1, f1, f1);
    super.func_77029_c(par1EntityBabaYaga, par2);
    ItemStack itemstack = par1EntityBabaYaga.func_70694_bm();
    
    if (itemstack != null) {
      GL11.glPushMatrix();
      

      if (field_77045_g.field_78091_s) {
        float f2 = 0.5F;
        GL11.glTranslatef(0.0F, 0.625F, 0.0F);
        GL11.glRotatef(-20.0F, -1.0F, 0.0F, 0.0F);
        GL11.glScalef(f2, f2, f2);
      }
      
      model.field_82898_f.func_78794_c(0.0625F);
      GL11.glTranslatef(-0.0625F, 0.53125F, 0.21875F);
      
      if (((itemstack.func_77973_b() instanceof ItemBlock)) && (RenderBlocks.func_147739_a(Block.func_149634_a(itemstack.func_77973_b()).func_149645_b()))) {
        float f2 = 0.5F;
        GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
        f2 *= 0.75F;
        GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(f2, -f2, f2);
      } else if (itemstack.func_77973_b() == Items.field_151031_f) {
        float f2 = 0.625F;
        GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
        GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(f2, -f2, f2);
        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
      } else if (itemstack.func_77973_b().func_77662_d()) {
        float f2 = 0.625F;
        
        if (itemstack.func_77973_b().func_77629_n_()) {
          GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
          GL11.glTranslatef(0.0F, -0.125F, 0.0F);
        }
        
        func_82410_b();
        GL11.glScalef(f2, -f2, f2);
        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
      } else {
        float f2 = 0.375F;
        GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
        GL11.glScalef(f2, f2, f2);
        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
      }
      
      GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
      field_76990_c.field_78721_f.func_78443_a(par1EntityBabaYaga, itemstack, 0);
      
      if (itemstack.func_77973_b().func_77623_v()) {
        field_76990_c.field_78721_f.func_78443_a(par1EntityBabaYaga, itemstack, 1);
      }
      
      GL11.glPopMatrix();
    }
  }
  
  protected void func_82410_b() {
    GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
  }
  
  protected void func_82409_b(EntityBabaYaga par1EntityBabaYaga, float par2) {
    float f1 = 0.9375F;
    GL11.glScalef(f1, f1, f1);
  }
  
  public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
  {
    func_82412_a((EntityBabaYaga)par1EntityLiving, par2, par4, par6, par8, par9);
  }
  
  protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float par2)
  {
    func_82409_b((EntityBabaYaga)par1EntityLivingBase, par2);
  }
  
  protected void func_77029_c(EntityLivingBase par1EntityLivingBase, float par2)
  {
    func_82411_a((EntityBabaYaga)par1EntityLivingBase, par2);
  }
  
  public void func_76986_a(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
  {
    func_82412_a((EntityBabaYaga)par1EntityLivingBase, par2, par4, par6, par8, par9);
  }
  
  protected ResourceLocation func_110775_a(Entity par1Entity)
  {
    return getBabaYagaTextures((EntityBabaYaga)par1Entity);
  }
  
  public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
  {
    func_82412_a((EntityBabaYaga)par1Entity, par2, par4, par6, par8, par9);
  }
}
