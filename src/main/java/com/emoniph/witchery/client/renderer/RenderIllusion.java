package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.entity.EntityIllusion;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderIllusion extends RenderLiving
{
  private final ResourceLocation textures;
  
  public RenderIllusion(ModelBase model, ResourceLocation resource)
  {
    super(model, 0.5F);
    field_76989_e = 0.0F;
    textures = resource;
  }
  
  public void renderLivingIllusion(EntityIllusion illusionEntity, double par2, double par4, double par6, float par8, float par9) {
    if (func_71410_xfield_71439_g.func_70005_c_().equals(illusionEntity.getVictimName())) {
      super.func_76986_a(illusionEntity, par2, par4, par6, par8, par9);
    }
  }
  
  protected void preRenderIllusion(EntityIllusion illusionEntity, float par2) {
    super.func_77041_b(illusionEntity, par2);
  }
  
  public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
  {
    renderLivingIllusion((EntityIllusion)par1EntityLiving, par2, par4, par6, par8, par9);
  }
  
  protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float par2)
  {
    preRenderIllusion((EntityIllusion)par1EntityLivingBase, par2);
  }
  
  public void func_76986_a(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
  {
    renderLivingIllusion((EntityIllusion)par1EntityLivingBase, par2, par4, par6, par8, par9);
  }
  
  protected ResourceLocation func_110775_a(Entity par1Entity)
  {
    return textures;
  }
  
  public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
  {
    renderLivingIllusion((EntityIllusion)par1Entity, par2, par4, par6, par8, par9);
  }
}
