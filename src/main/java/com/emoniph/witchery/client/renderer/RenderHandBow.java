package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.client.model.ModelHandBow;
import com.emoniph.witchery.item.ItemGeneral.BoltType;
import com.emoniph.witchery.item.ItemHandBow;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class RenderHandBow implements IItemRenderer
{
  protected ModelHandBow model;
  private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/handbow.png");
  
  public RenderHandBow() {
    model = new ModelHandBow();
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
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object... data)
  {
    switch (1.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[type.ordinal()]) {
    case 1: 
    case 2: 
      GL11.glPushMatrix();
      
      ItemGeneral.BoltType boltType = ItemHandBow.getLoadedBoltType(stack);
      Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE_URL);
      
      GL11.glRotatef((float)rx, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef((float)ry, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef((float)rz, 0.0F, 0.0F, 1.0F);
      
      GL11.glTranslatef((float)tx - 0.03F, (float)ty - 0.13F, (float)tz + 0.13F);
      
      float SCALE = (float)scale;
      GL11.glScalef(SCALE, SCALE, SCALE);
      
      if ((data.length > 1) && (data[1] != null)) {
        if ((data[1] instanceof EntityPlayer)) {
          EntityPlayer player = (EntityPlayer)data[1];
          int useCount = player.func_71052_bv() > 0 ? ItemsCROSSBOW_PISTOL.func_77626_a(stack) - player.func_71052_bv() : 0;
          
          if (((EntityPlayer)data[1] != func_71410_xfield_71451_h) || (func_71410_xfield_71474_y.field_74320_O != 0) || ((((func_71410_xfield_71462_r instanceof GuiInventory)) || ((func_71410_xfield_71462_r instanceof net.minecraft.client.gui.inventory.GuiContainerCreative))) && (field_78727_afield_78735_i == 180.0F)))
          {

            renderModel(player, boltType, useCount);
          } else {
            if (player.func_71052_bv() > 0) {
              GL11.glRotatef(-25.0F, 0.0F, 1.0F, 0.0F);
              GL11.glTranslatef(0.0F, 0.1F, 0.0F);
            }
            
            GL11.glTranslatef(0.2F, 0.1F, 0.0F);
            renderModel(player, boltType, useCount);
          }
        } else {
          renderModel((Entity)data[1], boltType, -1);
        }
      }
      
      GL11.glPopMatrix();
      break;
    }
    
  }
  

  private void renderModel(Entity player, ItemGeneral.BoltType boltType, int useCount)
  {
    if (boltType != null) {
      useCount = 100;
    } else if ((!player.func_70093_af()) || (useCount == -1)) {
      useCount = 0;
    }
    model.render(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, boltType, useCount);
  }
  








































  private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
}
