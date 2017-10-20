package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.client.model.ModelNightmare;
import com.emoniph.witchery.entity.EntityNightmare;
import com.emoniph.witchery.util.RenderUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;



@SideOnly(Side.CLIENT)
public class RenderNightmare
  extends RenderLiving
{
  private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/nightmare.png");
  
  public RenderNightmare() {
    super(new ModelNightmare(), 0.5F);
  }
  
  public void doRenderNightmare(EntityNightmare entity, double par2, double par4, double par6, float par8, float par9) {
    GL11.glPushMatrix();
    


    RenderUtil.blend(true);
    
    GL11.glColor4f(1.0F, 1.0F, 1.0F, entity.isDefended() ? 0.6F : 0.9F);
    
    super.func_76986_a(entity, par2, par4, par6, par8, par9);
    RenderUtil.blend(false);
    GL11.glPopMatrix();
  }
  
  protected void rotateNightmareCorpse(EntityNightmare entity, float par2, float par3, float par4) {
    super.func_77043_a(entity, par2, par3, par4);
  }
  









  public void func_76986_a(EntityLiving entity, double par2, double par4, double par6, float par8, float par9)
  {
    doRenderNightmare((EntityNightmare)entity, par2, par4, par6, par8, par9);
  }
  
  protected void func_77043_a(EntityLivingBase entity, float par2, float par3, float par4)
  {
    rotateNightmareCorpse((EntityNightmare)entity, par2, par3, par4);
  }
  









  public void func_76986_a(EntityLivingBase par1, double par2, double par4, double par6, float par8, float par9)
  {
    doRenderNightmare((EntityNightmare)par1, par2, par4, par6, par8, par9);
  }
  
  public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9)
  {
    doRenderNightmare((EntityNightmare)entity, par2, par4, par6, par8, par9);
  }
  
  protected ResourceLocation func_110775_a(Entity par1Entity)
  {
    return func_110832_a((EntityNightmare)par1Entity);
  }
  
  protected ResourceLocation func_110832_a(EntityNightmare par1Entity) {
    return TEXTURE_URL;
  }
}
