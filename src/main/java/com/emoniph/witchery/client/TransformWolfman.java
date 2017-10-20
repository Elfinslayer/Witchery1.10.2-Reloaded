package com.emoniph.witchery.client;

import com.emoniph.witchery.client.model.ModelWolfman;
import com.emoniph.witchery.client.renderer.RenderWolfman;
import com.emoniph.witchery.entity.EntityWolfman;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;

@SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class TransformWolfman
{
  private EntityWolfman proxyEntity;
  private RenderWolfman proxyRenderer = new RenderWolfman(new ModelWolfman(), 0.5F);
  

  public TransformWolfman() {}
  
  public EntityLivingBase getModel()
  {
    return proxyEntity;
  }
  
  public void syncModelWith(EntityLivingBase entity, boolean frontface) {
    if (proxyEntity == null) {
      proxyEntity = new EntityWolfman(field_70170_p);
    } else if (proxyEntity.field_70170_p != field_70170_p) {
      proxyEntity.func_70029_a(field_70170_p);
    }
    
    proxyEntity.func_70107_b(field_70165_t, field_70163_u, field_70161_v);
    
    proxyEntity.field_70142_S = field_70142_S;
    proxyEntity.field_70137_T = field_70137_T;
    proxyEntity.field_70136_U = field_70136_U;
    
    proxyEntity.field_70159_w = field_70159_w;
    proxyEntity.field_70181_x = field_70181_x;
    proxyEntity.field_70179_y = field_70179_y;
    
    proxyEntity.field_70701_bs = field_70701_bs;
    proxyEntity.field_70702_br = field_70702_br;
    
    proxyEntity.field_70122_E = field_70122_E;
    
    proxyEntity.field_70169_q = field_70169_q;
    proxyEntity.field_70167_r = field_70167_r;
    proxyEntity.field_70166_s = field_70166_s;
    
    proxyEntity.field_70125_A = field_70125_A;
    proxyEntity.field_70177_z = field_70177_z;
    proxyEntity.field_70759_as = field_70759_as;
    
    proxyEntity.field_70127_C = field_70127_C;
    proxyEntity.field_70126_B = field_70126_B;
    proxyEntity.field_70758_at = field_70758_at;
    
    proxyEntity.field_70754_ba = field_70754_ba;
    
    proxyEntity.field_70721_aZ = field_70721_aZ;
    proxyEntity.field_70722_aY = field_70722_aY;
    proxyEntity.field_82175_bq = field_82175_bq;
    
    proxyEntity.field_70733_aJ = field_70733_aJ;
    proxyEntity.field_70732_aI = field_70732_aI;
    
    proxyEntity.field_70761_aq = (frontface ? 0.0F : field_70761_aq);
    proxyEntity.field_70760_ar = (frontface ? 0.0F : field_70760_ar);
    

    proxyEntity.field_70173_aa = field_70173_aa;
    proxyEntity.field_70128_L = false;
    proxyEntity.field_70160_al = field_70160_al;
    
    proxyEntity.field_70129_M = 0.0F;
    
    proxyEntity.func_70095_a(entity.func_70093_af());
    proxyEntity.func_70031_b(entity.func_70051_ag());
    proxyEntity.func_82142_c(entity.func_82150_aj());
    proxyEntity.func_70062_b(0, entity.func_70694_bm());
    proxyEntity.func_70019_c(entity.func_70113_ah());
    proxyEntity.setSitting(entity.func_70115_ae());
    
    if ((entity instanceof EntityPlayer)) {
      EntityPlayer player = (EntityPlayer)entity;
      proxyEntity.setItemInUse(player.func_71011_bu() == null ? null : player.func_71011_bu().func_77946_l(), player.func_71052_bv());
    }
  }
  
  public void render(World worldObj, EntityLivingBase entity, double x, double y, double z, RendererLivingEntity renderer, float partialTicks, boolean frontface)
  {
    syncModelWith(entity, frontface);
    proxyRenderer.func_76976_a(RenderManager.field_78727_a);
    float f1 = proxyEntity.field_70126_B + (proxyEntity.field_70177_z - proxyEntity.field_70126_B) * partialTicks;
    
    double d3 = 0.0D - proxyEntity.field_70129_M;
    if ((proxyEntity.func_70093_af()) && (!(entity instanceof EntityPlayerSP))) {
      d3 -= 0.125D;
    }
    
    if (proxyEntity.func_70115_ae()) {
      Entity ridden = proxyEntity.field_70154_o;
      d3 += ridden.func_70042_X();
    }
    


    proxyRenderer.func_76986_a(proxyEntity, x, y + d3, z, frontface ? 0.0F : f1, partialTicks);
  }
  

























  protected void renderEquippedItems(ItemRenderer itemRenderer, EntityLivingBase p_77029_1_, float p_77029_2_)
  {
    GL11.glColor3f(1.0F, 1.0F, 1.0F);
    
    ItemStack itemstack = p_77029_1_.func_70694_bm();
    


















































    if ((itemstack != null) && (itemstack.func_77973_b() != null))
    {
      Item item = itemstack.func_77973_b();
      GL11.glPushMatrix();
      









      GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
      
      IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, IItemRenderer.ItemRenderType.EQUIPPED);
      boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D));
      
      if (((item instanceof ItemBlock)) && ((is3D) || (RenderBlocks.func_147739_a(Block.func_149634_a(item).func_149645_b()))))
      {
        float f1 = 0.5F;
        GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
        f1 *= 0.75F;
        GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(-f1, -f1, f1);
      }
      else if (item == Items.field_151031_f)
      {
        float f1 = 0.625F;
        GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
        GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(f1, -f1, f1);
        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
      }
      else if (item.func_77662_d())
      {
        float f1 = 0.625F;
        
        if (item.func_77629_n_())
        {
          GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
          GL11.glTranslatef(0.0F, -0.125F, 0.0F);
        }
        

        GL11.glScalef(f1, -f1, f1);
        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
      }
      else
      {
        float f1 = 0.375F;
        GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
        GL11.glScalef(f1, f1, f1);
        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
      }
      




      if (itemstack.func_77973_b().func_77623_v())
      {
        for (int i = 0; i < itemstack.func_77973_b().getRenderPasses(itemstack.func_77960_j()); i++)
        {
          int j = itemstack.func_77973_b().func_82790_a(itemstack, i);
          float f5 = (j >> 16 & 0xFF) / 255.0F;
          float f2 = (j >> 8 & 0xFF) / 255.0F;
          float f3 = (j & 0xFF) / 255.0F;
          GL11.glColor4f(f5, f2, f3, 1.0F);
          itemRenderer.func_78443_a(p_77029_1_, itemstack, i);
        }
      }
      

      int i = itemstack.func_77973_b().func_82790_a(itemstack, 0);
      float f4 = (i >> 16 & 0xFF) / 255.0F;
      float f5 = (i >> 8 & 0xFF) / 255.0F;
      float f2 = (i & 0xFF) / 255.0F;
      GL11.glColor4f(f4, f5, f2, 1.0F);
      itemRenderer.func_78443_a(p_77029_1_, itemstack, 0);
      

      GL11.glPopMatrix();
    }
  }
}
