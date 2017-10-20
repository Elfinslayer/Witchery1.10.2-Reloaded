package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.client.model.ModelGrotesque;
import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.ritual.rites.RiteProtectionCircleRepulsive;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent.Post;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import org.lwjgl.opengl.GL11;

public class PotionGrotesque extends PotionBase implements IHandleLivingUpdate, IHandleRenderLiving
{
  @SideOnly(Side.CLIENT)
  private static ModelGrotesque DEMON_HEAD_MODEL;
  @SideOnly(Side.CLIENT)
  private static ResourceLocation TEXTURE;
  
  public PotionGrotesque(int id, int color)
  {
    super(id, color);
  }
  





  @SideOnly(Side.CLIENT)
  public void onLivingRender(World world, EntityLivingBase entity, RenderLivingEvent.Post event, int amplifier)
  {
    if (DEMON_HEAD_MODEL == null) {
      DEMON_HEAD_MODEL = new ModelGrotesque();
    }
    
    if (TEXTURE == null) {
      TEXTURE = new ResourceLocation("witchery", "textures/entities/Demon.png");
    }
    GL11.glPushMatrix();
    Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE);
    ModelOverlayRenderer.renderModel(entity, x, y, z, renderer, DEMON_HEAD_MODEL);
    
    GL11.glPopMatrix();
  }
  
  public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
  {
    if ((!field_72995_K) && (world.func_82737_E() % 5L == 3L)) {
      float radius = 4.0F;
      float radiusSq = 16.0F;
      AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(field_70165_t - 4.0D, field_70163_u - 4.0D, field_70161_v - 4.0D, field_70165_t + 4.0D, field_70163_u + 4.0D, field_70161_v + 4.0D);
      
      java.util.List<EntityLiving> list = world.func_72872_a(EntityLiving.class, bounds);
      for (EntityLiving victim : list) {
        boolean canApply = (entity != victim) && (!(victim instanceof EntityDemon)) && (!(victim instanceof IBossDisplayData)) && (!(victim instanceof EntityGolem));
        
        if ((canApply) && (victim.func_70068_e(entity) < 16.0D)) {
          RiteProtectionCircleRepulsive.push(field_70170_p, victim, field_70165_t, field_70163_u, field_70161_v);
        }
      }
    }
  }
}
