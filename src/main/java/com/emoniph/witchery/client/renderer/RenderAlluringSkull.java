package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.blocks.BlockAlluringSkull.TileEntityAlluringSkull;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;



@SideOnly(Side.CLIENT)
public class RenderAlluringSkull
  extends TileEntitySpecialRenderer
{
  public RenderAlluringSkull() {}
  
  private static final ResourceLocation field_110642_c = new ResourceLocation("witchery", "textures/blocks/alluringSkull.png");
  private static final ResourceLocation field_110640_d = new ResourceLocation("witchery", "textures/blocks/alluringSkull2.png");
  
  private ModelSkeletonHead field_82396_c = new ModelSkeletonHead(0, 0, 64, 32);
  
  public void renderTileEntityAlluringSkullAt(BlockAlluringSkull.TileEntityAlluringSkull par1TileEntitySkull, double par2, double par4, double par6, float par8) {
    func_82393_a((float)par2, (float)par4, (float)par6, par1TileEntitySkull.func_145832_p() & 0x7, par1TileEntitySkull.func_82119_b() * 360 / 16.0F, par1TileEntitySkull.getSkullType());
  }
  
  public void func_82393_a(float par1, float par2, float par3, int par4, float par5, int par6)
  {
    ModelSkeletonHead modelskeletonhead = field_82396_c;
    
    switch (par6) {
    case 0: 
    default: 
      func_147499_a(field_110642_c);
      break;
    case 1: 
      func_147499_a(field_110640_d);
    }
    
    
    GL11.glPushMatrix();
    GL11.glDisable(2884);
    
    if (par4 != 1) {
      switch (par4) {
      case 2: 
        GL11.glTranslatef(par1 + 0.5F, par2 + 0.25F, par3 + 0.74F);
        break;
      case 3: 
        GL11.glTranslatef(par1 + 0.5F, par2 + 0.25F, par3 + 0.26F);
        par5 = 180.0F;
        break;
      case 4: 
        GL11.glTranslatef(par1 + 0.74F, par2 + 0.25F, par3 + 0.5F);
        par5 = 270.0F;
        break;
      case 5: 
      default: 
        GL11.glTranslatef(par1 + 0.26F, par2 + 0.25F, par3 + 0.5F);
        par5 = 90.0F;break;
      }
    } else {
      GL11.glTranslatef(par1 + 0.5F, par2, par3 + 0.5F);
    }
    
    float f4 = 0.0625F;
    GL11.glEnable(32826);
    GL11.glScalef(-1.0F, -1.0F, 1.0F);
    GL11.glEnable(3008);
    modelskeletonhead.func_78088_a((Entity)null, 0.0F, 0.0F, 0.0F, par5, 0.0F, f4);
    GL11.glPopMatrix();
  }
  
  public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
    renderTileEntityAlluringSkullAt((BlockAlluringSkull.TileEntityAlluringSkull)par1TileEntity, par2, par4, par6, par8);
  }
}
