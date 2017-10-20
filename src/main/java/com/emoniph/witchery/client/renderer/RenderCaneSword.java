package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.client.model.ModelCaneSword;
import com.emoniph.witchery.item.ItemCaneSword;
import com.emoniph.witchery.util.Config;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class RenderCaneSword implements IItemRenderer
{
  private final ModelCaneSword model;
  private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/canesword.png");
  
  public RenderCaneSword()
  {
    model = new ModelCaneSword();
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
      
      Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE_URL);
      
      GL11.glRotatef((float)rx, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef((float)ry + 70.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef((float)rz - 5.0F, 0.0F, 0.0F, 1.0F);
      
      GL11.glTranslatef((float)tx + 0.35F, (float)ty + 0.0F, (float)tz + 0.85F);
      
      float SCALE = 1.0F;
      GL11.glScalef(1.0F, 1.0F, 1.0F);
      
      if ((data.length > 1) && (data[1] != null)) {
        boolean deployed = (data[1] instanceof EntityLivingBase) ? ItemsCANE_SWORD.isDrawn((EntityLivingBase)data[1]) : false;
        
        if ((data[1] instanceof EntityPlayer)) {
          EntityPlayer player = (EntityPlayer)data[1];
          if (((EntityPlayer)data[1] != func_71410_xfield_71451_h) || (func_71410_xfield_71474_y.field_74320_O != 0) || ((((func_71410_xfield_71462_r instanceof GuiInventory)) || ((func_71410_xfield_71462_r instanceof GuiContainerCreative))) && (field_78727_afield_78735_i == 180.0F)))
          {

            if (deployed) {
              GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
              GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
              GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
              GL11.glTranslatef(0.0F, -0.3F, -0.5F);
            }
            
            renderModel(player, false, deployed, item);
          } else {
            if (deployed) {
              GL11.glTranslatef(0.4F, 0.3F, -0.2F);
              GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
              GL11.glRotatef(1.0F, 0.0F, 0.0F, 1.0F);
              GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
            }
            else
            {
              GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
              GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
              GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
              GL11.glTranslatef(0.0F, -0.5F, -0.9F);
            }
            
            renderModel(player, true, deployed, item);
          }
        } else {
          if (deployed) {
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(0.0F, -0.3F, -0.5F);
          }
          renderModel((Entity)data[1], false, deployed, item);
        }
      }
      
      GL11.glPopMatrix();
      break;
    }
    
  }
  

  private void renderModel(Entity player, boolean firstPerson, boolean deployed, ItemStack item)
  {
    model.render(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, firstPerson, deployed);
    
    Minecraft mc = Minecraft.func_71410_x();
    if ((field_71474_y.field_74347_j) && (instancerender3dGlintEffect) && 
      (item != null) && (item.func_77948_v())) {
      float f9 = field_70173_aa;
      field_71446_o.func_110577_a(RES_ITEM_GLINT);
      GL11.glEnable(3042);
      float f10 = 0.5F;
      GL11.glColor4f(f10, f10, f10, 1.0F);
      GL11.glDepthFunc(514);
      GL11.glDepthMask(false);
      
      for (int k = 0; k < 2; k++) {
        GL11.glDisable(2896);
        float f11 = 0.76F;
        
        GL11.glColor4f(0.5F * f11, 0.25F * f11, 0.8F * f11, 1.0F);
        GL11.glBlendFunc(768, 1);
        GL11.glMatrixMode(5890);
        GL11.glLoadIdentity();
        float f12 = f9 * (0.001F + k * 0.003F) * 20.0F;
        float f13 = 0.33333334F;
        GL11.glScalef(f13, f13, f13);
        GL11.glRotatef(30.0F - k * 60.0F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(0.0F, f12, 0.0F);
        GL11.glMatrixMode(5888);
        model.render(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, firstPerson, deployed);
      }
      
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glMatrixMode(5890);
      GL11.glDepthMask(true);
      GL11.glLoadIdentity();
      GL11.glMatrixMode(5888);
      GL11.glEnable(2896);
      GL11.glDisable(3042);
      GL11.glDepthFunc(515);
    }
  }
  

  private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
}
