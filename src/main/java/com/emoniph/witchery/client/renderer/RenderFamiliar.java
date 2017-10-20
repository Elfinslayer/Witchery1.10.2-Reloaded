package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.entity.EntityFamiliar;
import com.emoniph.witchery.util.RenderUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;


@SideOnly(Side.CLIENT)
public class RenderFamiliar
  extends RenderLiving
{
  private static final ResourceLocation pigTextures = new ResourceLocation("textures/entity/pig/pig.png");
  
  public RenderFamiliar(ModelBase par1ModelBase, float par2) {
    super(par1ModelBase, par2);
    field_76989_e = 0.0F;
  }
  
  public void renderLivingFamiliar(EntityFamiliar familiarEntity, double par2, double par4, double par6, float par8, float par9) {
    GL11.glPushMatrix();
    RenderUtil.blend(true);
    if ((familiarEntity != null) && (familiarEntity.getItemIDToFind() != -1)) {
      GL11.glColor4f(0.7F, 0.3F, 1.0F, 0.51F);
    } else {
      GL11.glColor4f(0.5F, 0.5F, 1.0F, 0.51F);
    }
    super.func_76986_a(familiarEntity, par2, par4, par6, par8, par9);
    RenderUtil.blend(false);
    GL11.glPopMatrix();
  }
  
  protected ResourceLocation func_110874_a(EntityOcelot par1EntityOcelot) {
    return pigTextures;
  }
  
  protected void preRenderFamiliar(EntityFamiliar par1EntityFamiliar, float par2) {
    super.func_77041_b(par1EntityFamiliar, par2);
    
    if (par1EntityFamiliar.func_70909_n()) {
      GL11.glScalef(0.8F, 0.8F, 0.8F);
    }
  }
  
  public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
  {
    renderLivingFamiliar((EntityFamiliar)par1EntityLiving, par2, par4, par6, par8, par9);
  }
  
  protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float par2)
  {
    preRenderFamiliar((EntityFamiliar)par1EntityLivingBase, par2);
  }
  
  public void func_76986_a(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
  {
    renderLivingFamiliar((EntityFamiliar)par1EntityLivingBase, par2, par4, par6, par8, par9);
  }
  
  protected ResourceLocation func_110775_a(Entity par1Entity)
  {
    return func_110874_a((EntityFamiliar)par1Entity);
  }
  
  public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
  {
    renderLivingFamiliar((EntityFamiliar)par1Entity, par2, par4, par6, par8, par9);
  }
}
