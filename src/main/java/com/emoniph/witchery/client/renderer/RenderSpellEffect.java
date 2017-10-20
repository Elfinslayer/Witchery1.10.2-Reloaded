package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.entity.EntitySpellEffect;
import com.emoniph.witchery.infusion.infusions.symbols.EffectRegistry;
import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffectProjectile;
import com.emoniph.witchery.util.RenderUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderSpellEffect
  extends Render
{
  private float field_77002_a;
  
  public RenderSpellEffect(float par1)
  {
    field_77002_a = par1;
  }
  
  public void doRenderSpellEffect(EntitySpellEffect effectEntity, double par2, double par4, double par6, float par8, float par9) {
    GL11.glPushMatrix();
    func_110777_b(effectEntity);
    GL11.glTranslatef((float)par2, (float)par4, (float)par6);
    



    RenderUtil.blend(true);
    

    float scale = 1.0F;
    int color = 16711680;
    





    IIcon icon2 = Items.field_151126_ay.func_77617_a(0);
    



    SymbolEffect effect = EffectRegistry.instance().getEffect(effectEntity.getEffectID());
    if ((effect != null) && ((effect instanceof SymbolEffectProjectile))) {
      SymbolEffectProjectile projectileEffect = (SymbolEffectProjectile)effect;
      color = projectileEffect.getColor();
      scale = projectileEffect.getSize();
    }
    
    float f2 = field_77002_a * scale * 0.65F;
    GL11.glScalef(f2 / 1.0F, f2 / 1.0F, f2 / 1.0F);
    
    float red = (color >>> 16 & 0xFF) / 256.0F;
    float green = (color >>> 8 & 0xFF) / 256.0F;
    float blue = (color & 0xFF) / 256.0F;
    GL11.glColor4f(red, green, blue, 0.55F);
    
    Tessellator tessellator = Tessellator.field_78398_a;
    float f3 = icon2.func_94209_e();
    float f4 = icon2.func_94212_f();
    float f5 = icon2.func_94206_g();
    float f6 = icon2.func_94210_h();
    
    float f7 = 1.0F;
    float f8 = 0.5F;
    float f9 = 0.25F;
    GL11.glRotatef(180.0F - field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(-field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
    tessellator.func_78382_b();
    tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
    tessellator.func_78374_a(0.0F - f8, 0.0F - f9, 0.0D, f3, f6);
    tessellator.func_78374_a(f7 - f8, 0.0F - f9, 0.0D, f4, f6);
    tessellator.func_78374_a(f7 - f8, 1.0F - f9, 0.0D, f4, f5);
    tessellator.func_78374_a(0.0F - f8, 1.0F - f9, 0.0D, f3, f5);
    tessellator.func_78381_a();
    














    RenderUtil.blend(false);
    GL11.glPopMatrix();
  }
  
  private static final ResourceLocation RESOURCE_LOCATION = new ResourceLocation("witchery", "textures/entities/spelleffect.png");
  
  protected ResourceLocation getSpellEffectTextures(EntitySpellEffect effect) {
    return TextureMap.field_110576_c;
  }
  
  protected ResourceLocation func_110775_a(Entity par1Entity)
  {
    return getSpellEffectTextures((EntitySpellEffect)par1Entity);
  }
  
  public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
  {
    doRenderSpellEffect((EntitySpellEffect)par1Entity, par2, par4, par6, par8, par9);
  }
}
