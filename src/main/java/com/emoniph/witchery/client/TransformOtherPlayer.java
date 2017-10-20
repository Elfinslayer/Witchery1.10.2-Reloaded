package com.emoniph.witchery.client;

import com.emoniph.witchery.client.renderer.RenderOtherPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;


@SideOnly(Side.CLIENT)
public class TransformOtherPlayer
{
  private RenderOtherPlayer proxyRenderer = new RenderOtherPlayer();
  

























  public TransformOtherPlayer() {}
  
























  public void syncModelWith(EntityLivingBase entity, boolean frontface) {}
  
























  public void render(World worldObj, EntityLivingBase entity, double x, double y, double z, RendererLivingEntity renderer, float partialTicks, boolean frontface)
  {
    EntityPlayer player = (EntityPlayer)entity;
    proxyRenderer.func_76976_a(RenderManager.field_78727_a);
    float f1 = field_70126_B + (field_70177_z - field_70126_B) * partialTicks;
    

    double d3 = field_70129_M;
    










    proxyRenderer.func_76986_a(player, x, y + d3, z, frontface ? 0.0F : f1, partialTicks);
  }
  






























  protected void renderEquippedItems(ItemRenderer itemRenderer, EntityLivingBase p_77029_1_, float p_77029_2_)
  {
    GL11.glColor3f(1.0F, 1.0F, 1.0F);
    
    ItemStack itemstack = p_77029_1_.func_70694_bm();
    





























































    if ((itemstack != null) && (itemstack.func_77973_b() != null)) {
      Item item = itemstack.func_77973_b();
      GL11.glPushMatrix();
      









      GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
      
      IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, IItemRenderer.ItemRenderType.EQUIPPED);
      
      boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, IItemRenderer.ItemRendererHelper.BLOCK_3D));
      


      if (((item instanceof ItemBlock)) && ((is3D) || (RenderBlocks.func_147739_a(Block.func_149634_a(item).func_149645_b()))))
      {
        float f1 = 0.5F;
        GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
        f1 *= 0.75F;
        GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(-f1, -f1, f1);
      } else if (item == Items.field_151031_f) {
        float f1 = 0.625F;
        GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
        GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(f1, -f1, f1);
        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
      } else if (item.func_77662_d()) {
        float f1 = 0.625F;
        
        if (item.func_77629_n_()) {
          GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
          GL11.glTranslatef(0.0F, -0.125F, 0.0F);
        }
        

        GL11.glScalef(f1, -f1, f1);
        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
      } else {
        float f1 = 0.375F;
        GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
        GL11.glScalef(f1, f1, f1);
        GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
      }
      




      if (itemstack.func_77973_b().func_77623_v()) {
        for (int i = 0; i < itemstack.func_77973_b().getRenderPasses(itemstack.func_77960_j()); i++) {
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
