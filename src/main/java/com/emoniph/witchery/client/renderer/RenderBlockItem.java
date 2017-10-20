package com.emoniph.witchery.client.renderer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

public class RenderBlockItem implements net.minecraftforge.client.IItemRenderer
{
  protected TileEntitySpecialRenderer render;
  protected TileEntity dummytile;
  
  public RenderBlockItem(TileEntitySpecialRenderer render, TileEntity dummy)
  {
    this.render = render;
    dummytile = dummy;
  }
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
  {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, net.minecraftforge.client.IItemRenderer.ItemRendererHelper helper)
  {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
  {
    if (type == IItemRenderer.ItemRenderType.ENTITY) {
      org.lwjgl.opengl.GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
    }
    render.func_147500_a(dummytile, 0.0D, 0.0D, 0.0D, 0.0F);
  }
}
