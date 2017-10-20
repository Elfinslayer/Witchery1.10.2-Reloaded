package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.blocks.BlockCrystalBall.TileEntityCrystalBall;
import com.emoniph.witchery.client.model.ModelCrystalBall;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderCrystalBall
  extends TileEntitySpecialRenderer
{
  final ModelCrystalBall model;
  
  public RenderCrystalBall()
  {
    model = new ModelCrystalBall();
  }
  
  public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f)
  {
    GL11.glPushMatrix();
    GL11.glTranslatef((float)d, (float)d1, (float)d2);
    BlockCrystalBall.TileEntityCrystalBall ball = (BlockCrystalBall.TileEntityCrystalBall)tileEntity;
    renderCrystalBall(ball, ball.func_145831_w(), field_145851_c, field_145848_d, field_145849_e);
    GL11.glPopMatrix();
  }
  
  private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/crystalball.png");
  
  public void renderCrystalBall(BlockCrystalBall.TileEntityCrystalBall ball, World world, int x, int y, int z) {
    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    
    func_147499_a(TEXTURE_URL);
    
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    
    GL11.glTranslatef(0.0F, -1.0F, 0.0F);
    
    model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, ball);
  }
}
