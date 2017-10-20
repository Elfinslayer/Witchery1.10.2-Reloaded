package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.client.model.ModelWitchHand;
import com.emoniph.witchery.item.ItemDeathsHand;
import com.emoniph.witchery.util.RenderUtil;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
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
public class RenderDeathsHand implements IItemRenderer
{
  protected ModelWitchHand witchHandModel;
  private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/deathshand.png");
  
  public RenderDeathsHand() {
    witchHandModel = new ModelWitchHand(-0.3F);
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
      GL11.glRotatef((float)ry, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef((float)rz, 0.0F, 0.0F, 1.0F);
      
      GL11.glTranslatef((float)tx, (float)ty, (float)tz);
      
      float SCALE = 1.0F;
      GL11.glScalef(1.0F, 1.0F, 1.0F);
      



      RenderUtil.blend(true);
      
      if ((data.length > 1) && (data[1] != null)) {
        boolean deployed = (data[1] instanceof EntityLivingBase) ? ItemsDEATH_HAND.isDeployed((EntityLivingBase)data[1]) : false;
        if ((data[1] instanceof EntityPlayer)) {
          EntityPlayer player = (EntityPlayer)data[1];
          if (((EntityPlayer)data[1] != func_71410_xfield_71451_h) || (func_71410_xfield_71474_y.field_74320_O != 0) || ((((func_71410_xfield_71462_r instanceof GuiInventory)) || ((func_71410_xfield_71462_r instanceof GuiContainerCreative))) && (field_78727_afield_78735_i == 180.0F))) {
            if (deployed) {
              GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
              GL11.glTranslatef(0.19F, -0.12F, 0.01F);
            }
            if (!player.func_82150_aj()) {
              witchHandModel.render(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, false, deployed);
            }
          } else {
            if (deployed) {
              GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
            }
            if (player.func_82150_aj()) {
              GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.2F);
            }
            
            witchHandModel.render(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, true, deployed);
          }
        } else {
          if (deployed) {
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.2F, -0.12F, 0.01F);
          }
          witchHandModel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, false, deployed);
        }
      }
      
      RenderUtil.blend(false);
      
      GL11.glPopMatrix();
      break;
    }
  }
}
