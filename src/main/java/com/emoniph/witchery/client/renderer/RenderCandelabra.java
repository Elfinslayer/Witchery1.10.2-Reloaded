package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.blocks.BlockCandelabra.TileEntityCandelabra;
import com.emoniph.witchery.client.model.ModelCandelabra;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;


@SideOnly(Side.CLIENT)
public class RenderCandelabra
  extends TileEntitySpecialRenderer
{
  final ModelCandelabra model;
  
  public RenderCandelabra()
  {
    model = new ModelCandelabra();
  }
  
  public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f)
  {
    GL11.glPushMatrix();
    GL11.glTranslatef((float)d, (float)d1, (float)d2);
    BlockCandelabra.TileEntityCandelabra tileEntityCandelabra = (BlockCandelabra.TileEntityCandelabra)tileEntity;
    renderChalice(tileEntityCandelabra, tileEntity.func_145831_w(), field_145851_c, field_145848_d, field_145849_e);
    GL11.glPopMatrix();
  }
  
  private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/candelabra.png");
  
  public void renderChalice(BlockCandelabra.TileEntityCandelabra tileEntityCandelabra, World world, int x, int y, int z) {
    GL11.glPushMatrix();
    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    
    func_147499_a(TEXTURE_URL);
    
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    
    GL11.glTranslatef(0.0F, -1.0F, 0.0F);
    
    model.func_78088_a(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    
    GL11.glPopMatrix();
  }
}
