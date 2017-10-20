package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.BlockFire;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent.Post;
import org.lwjgl.opengl.GL11;

public class PotionHellishAura extends PotionBase implements IHandleRenderLiving
{
  public PotionHellishAura(int id, int color)
  {
    super(id, color);
  }
  
  public boolean func_76397_a(int duration, int amplifier)
  {
    int frequencyFactor = 25;
    int k = frequencyFactor >> amplifier;
    return duration % k == 0;
  }
  
  public void func_76394_a(EntityLivingBase entity, int amplifier)
  {
    World world = field_70170_p;
    if (!field_72995_K) {
      List<EntityLivingBase> entities = world.func_72872_a(EntityLivingBase.class, field_70121_D.func_72314_b(1.5D, 0.0D, 1.5D));
      
      for (EntityLivingBase otherEntity : entities) {
        if (entity != otherEntity) {
          otherEntity.func_70097_a(new EntityDamageSource(field_76370_bfield_76373_n, entity).func_76361_j().func_76348_h(), 1.0F);
          
          ParticleEffect.FLAME.send(SoundEffect.MOB_GHAST_FIREBALL, otherEntity, field_70130_N, field_70131_O, 16);
          
          if (amplifier > 0) {
            otherEntity.func_70015_d(amplifier);
          }
        }
      }
    }
  }
  

  @SideOnly(Side.CLIENT)
  public void onLivingRender(World world, EntityLivingBase entity, RenderLivingEvent.Post event, int amplifier)
  {
    Entity p_76977_1_ = entity;
    double p_76977_2_ = field_70165_t;
    double p_76977_4_ = field_70163_u;
    double p_76977_6_ = field_70161_v;
    

    GL11.glDisable(2896);
    IIcon iicon = Blocks.field_150480_ab.func_149840_c(0);
    IIcon iicon1 = Blocks.field_150480_ab.func_149840_c(1);
    GL11.glPushMatrix();
    


    float f1 = field_70130_N * 1.4F;
    GL11.glScalef(f1, f1, f1);
    
    Tessellator tessellator = Tessellator.field_78398_a;
    float f2 = 0.5F;
    float f3 = 0.0F;
    float f4 = field_70131_O / f1;
    float f5 = (float)(field_70163_u - field_70121_D.field_72338_b);
    GL11.glRotatef(-field_78727_afield_78735_i, 0.0F, 1.0F, 0.0F);
    GL11.glTranslatef(0.0F, 0.0F, -0.3F + (int)f4 * 0.02F);
    
    GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
    float f6 = 0.0F;
    int i = 0;
    tessellator.func_78382_b();
    
    while (f4 > 0.0F) {
      IIcon iicon2 = i % 2 == 0 ? iicon : iicon1;
      field_78727_afield_78724_e.func_110577_a(TextureMap.field_110575_b);
      float f7 = iicon2.func_94209_e();
      float f8 = iicon2.func_94206_g();
      float f9 = iicon2.func_94212_f();
      float f10 = iicon2.func_94210_h();
      
      if (i / 2 % 2 == 0) {
        float f11 = f9;
        f9 = f7;
        f7 = f11;
      }
      
      tessellator.func_78374_a(f2 - f3, 0.0F - f5, f6, f9, f10);
      
      tessellator.func_78374_a(-f2 - f3, 0.0F - f5, f6, f7, f10);
      
      tessellator.func_78374_a(-f2 - f3, 1.4F - f5, f6, f7, f8);
      
      tessellator.func_78374_a(f2 - f3, 1.4F - f5, f6, f9, f8);
      
      f4 -= 0.45F;
      f5 -= 0.45F;
      f2 *= 0.9F;
      f6 += 0.03F;
      i++;
    }
    
    tessellator.func_78381_a();
    GL11.glPopMatrix();
    
    GL11.glEnable(2896);
  }
}
