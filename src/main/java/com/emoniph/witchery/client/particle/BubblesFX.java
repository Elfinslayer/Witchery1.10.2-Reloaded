package com.emoniph.witchery.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;












public class BubblesFX
  extends EntityFX
{
  public static final ResourceLocation particles = new ResourceLocation("witchery:textures/particle/power.png");
  
  private boolean canMove = false;
  
  public BubblesFX(World world, double x, double y, double z)
  {
    super(world, x, y, z);
    field_70145_X = true;
  }
  
  public void func_70539_a(Tessellator tess, float partialTicks, float par3, float par4, float par5, float par6, float par7)
  {
    func_71410_xfield_71446_o.func_110577_a(particles);
    GL11.glDepthMask(false);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glAlphaFunc(516, 0.003921569F);
    tess.func_78382_b();
    tess.func_78380_c(func_70070_b(partialTicks));
    
    int typeIndex = 16;
    int par1 = typeIndex + field_70546_d * 8 / 20 % 16;
    

    int particleTextureIndexX = par1 % 16;
    int particleTextureIndexY = par1 / 16;
    
    float f6 = particleTextureIndexX / 16.0F;
    float f7 = f6 + 0.0624375F;
    float f8 = particleTextureIndexY / 16.0F;
    float f9 = f8 + 0.0624375F;
    
    float scale = 0.1F * field_70544_f;
    float x = (float)(field_70169_q + (field_70165_t - field_70169_q) * partialTicks - field_70556_an);
    float y = (float)(field_70167_r + (field_70163_u - field_70167_r) * partialTicks - field_70554_ao);
    float z = (float)(field_70166_s + (field_70161_v - field_70166_s) * partialTicks - field_70555_ap);
    tess.func_78369_a(field_70552_h, field_70553_i, field_70551_j, 1.0F);
    tess.func_78374_a(x - par3 * scale - par6 * scale, y - par4 * scale, z - par5 * scale - par7 * scale, f7, f9);
    tess.func_78374_a(x - par3 * scale + par6 * scale, y + par4 * scale, z - par5 * scale + par7 * scale, f7, f8);
    tess.func_78374_a(x + par3 * scale + par6 * scale, y + par4 * scale, z + par5 * scale + par7 * scale, f6, f8);
    tess.func_78374_a(x + par3 * scale - par6 * scale, y - par4 * scale, z + par5 * scale - par7 * scale, f6, f9);
    tess.func_78381_a();
    GL11.glDisable(3042);
    GL11.glDepthMask(true);
    GL11.glAlphaFunc(516, 0.1F);
  }
  
  public void func_70071_h_()
  {
    if (!field_70170_p.field_72995_K) {
      func_70106_y();
    }
    field_70169_q = field_70165_t;
    field_70167_r = field_70163_u;
    field_70166_s = field_70161_v;
    
    if ((field_70546_d++ >= Math.min(field_70547_e, 600)) || (field_70546_d < 0)) {
      func_70106_y();
    } else if (field_70546_d > field_70547_e * 0.9D) {
      field_70145_X = false;
    }
    
    if ((!field_70128_L) && (canMove)) {
      field_70181_x -= 0.04D * field_70545_g;
      func_70091_d(field_70159_w, field_70181_x, field_70179_y);
      
      if (field_70122_E)
      {
        field_70159_w *= 0.699999988079071D;
        field_70179_y *= 0.699999988079071D;
      }
    }
  }
  
  public int func_70537_b()
  {
    return 3;
  }
  
  public BubblesFX setMaxAge(int maxAge) {
    field_70547_e = maxAge;
    return this;
  }
  
  public BubblesFX setGravity(float gravity) {
    field_70545_g = gravity;
    return this;
  }
  
  public BubblesFX setCanMove(boolean canMove) {
    this.canMove = canMove;
    return this;
  }
  
  public BubblesFX setScale(float scale) {
    field_70544_f = scale;
    return this;
  }
}
