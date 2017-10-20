package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.client.model.ModelBrewBottle;
import com.emoniph.witchery.util.RenderUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;
















@SideOnly(Side.CLIENT)
public class RenderBrewBottle
  implements IItemRenderer
{
  protected ModelBrewBottle model;
  private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/brewbottle.png");
  
  public RenderBrewBottle() {
    model = new ModelBrewBottle();
  }
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
  {
    switch (1.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[type.ordinal()]) {
    case 1: 
    case 2: 
      return true;
    }
    return false;
  }
  

  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
  {
    return false;
  }
  
  double rx = 100.0D; double ry = -51.0D; double rz = -81.0D;
  double tx = 0.125D; double ty = 0.12D; double tz = -0.85D;
  double scale = 1.0D;
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
  {
    switch (1.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[type.ordinal()]) {
    case 1: 
    case 2: 
      GL11.glPushMatrix();
      
      if ((data.length > 1) && (data[1] != null))
      {
        if ((data[1] instanceof EntityPlayer)) {
          EntityPlayer player = (EntityPlayer)data[1];
          
          GL11.glRotatef((float)rx, 1.0F, 0.0F, 0.0F);
          GL11.glRotatef((float)ry + 10.0F, 0.0F, 1.0F, 0.0F);
          GL11.glRotatef((float)rz, 0.0F, 0.0F, 1.0F);
          
          GL11.glTranslatef((float)tx - 0.05F, (float)ty + 0.2F, (float)tz + 0.1F);
          
          Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE_URL);
          




          RenderUtil.blend(true);
          
          if (((EntityPlayer)data[1] != func_71410_xfield_71451_h) || (func_71410_xfield_71474_y.field_74320_O != 0) || ((((func_71410_xfield_71462_r instanceof GuiInventory)) || ((func_71410_xfield_71462_r instanceof GuiContainerCreative))) && (field_78727_afield_78735_i == 180.0F)))
          {
            float SCALE = 1.3F;
            GL11.glScalef(1.3F, 1.3F, 1.3F);
            model.func_78088_a(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
          }
          else {
            GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(0.3F, 0.05F, 0.0F);
            
            float SCALE = 1.5F;
            GL11.glScalef(1.5F, 1.5F, 1.5F);
            model.func_78088_a(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
          }
          
          RenderUtil.blend(false);
        }
        else {
          Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE_URL);
          
          GL11.glRotatef((float)rx, 1.0F, 0.0F, 0.0F);
          GL11.glRotatef((float)ry, 0.0F, 1.0F, 0.0F);
          GL11.glRotatef((float)rz, 0.0F, 0.0F, 1.0F);
          
          GL11.glTranslatef((float)tx, (float)ty, (float)tz);
          
          float SCALE = (float)scale;
          GL11.glScalef(SCALE, SCALE, SCALE);
          
          model.func_78088_a((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        }
      }
      
      GL11.glPopMatrix();
      break;
    }
  }
}
