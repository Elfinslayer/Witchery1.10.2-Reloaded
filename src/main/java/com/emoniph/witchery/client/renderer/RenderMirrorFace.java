package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.client.model.ModelMirrorFace;
import com.emoniph.witchery.entity.EntityMirrorFace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;


@SideOnly(Side.CLIENT)
public class RenderMirrorFace
  extends RenderLiving
{
  private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/mirrorface.png");
  
  public RenderMirrorFace() {
    super(new ModelMirrorFace(), 0.0F);
  }
  
  public void doRenderDemon(EntityMirrorFace entity, double par2, double par4, double par6, float par8, float par9) {
    super.func_76986_a(entity, par2, par4, par6, par8, par9);
  }
  
  protected void rotateDemonCorpse(EntityMirrorFace entity, float par2, float par3, float par4) {
    super.func_77043_a(entity, par2, par3, par4);
  }
  
  public void func_76986_a(EntityLiving entity, double par2, double par4, double par6, float par8, float par9)
  {
    doRenderDemon((EntityMirrorFace)entity, par2, par4, par6, par8, par9);
  }
  
  protected void func_77043_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
  {
    rotateDemonCorpse((EntityMirrorFace)par1EntityLivingBase, par2, par3, par4);
  }
  
  public void func_76986_a(EntityLivingBase par1, double par2, double par4, double par6, float par8, float par9)
  {
    doRenderDemon((EntityMirrorFace)par1, par2, par4, par6, par8, par9);
  }
  
  public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9)
  {
    doRenderDemon((EntityMirrorFace)entity, par2, par4, par6, par8, par9);
  }
  
  protected ResourceLocation func_110775_a(Entity par1Entity)
  {
    return func_110832_a((EntityMirrorFace)par1Entity);
  }
  
  protected ResourceLocation func_110832_a(EntityMirrorFace par1Entity) {
    return TEXTURE_URL;
  }
}
