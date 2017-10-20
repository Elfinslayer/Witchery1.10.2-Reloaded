package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.client.model.ModelVillagerBed;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;















@SideOnly(Side.CLIENT)
public class RenderVillagerBed
{
  private static final ResourceLocation TEXTURE = new ResourceLocation("witchery", "textures/entities/villagerbed.png");
  
  private final ModelVillagerBed model;
  
  public RenderVillagerBed()
  {
    model = new ModelVillagerBed();
  }
  
  public void render(float x, float y, float z, int metadata) {
    GL11.glPushMatrix();
    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    
    func_71410_xfield_71446_o.func_110577_a(TEXTURE);
    

    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    





    GL11.glTranslatef(0.5F, 0.2F, -0.6F);
    























    model.func_78088_a((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    
    GL11.glPopMatrix();
  }
}
