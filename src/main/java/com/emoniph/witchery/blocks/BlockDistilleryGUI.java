package com.emoniph.witchery.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;


@SideOnly(Side.CLIENT)
public class BlockDistilleryGUI
  extends GuiContainer
{
  private static final ResourceLocation IMAGE_URL = new ResourceLocation("witchery", "textures/gui/distiller.png");
  private BlockDistillery.TileEntityDistillery furnaceInventory;
  
  public BlockDistilleryGUI(InventoryPlayer par1InventoryPlayer, BlockDistillery.TileEntityDistillery par2TileEntityFurnace) {
    super(new BlockDistillery.ContainerDistillery(par1InventoryPlayer, par2TileEntityFurnace));
    furnaceInventory = par2TileEntityFurnace;
  }
  
  protected void func_146979_b(int par1, int par2)
  {
    String s = furnaceInventory.func_145818_k_() ? furnaceInventory.func_145825_b() : I18n.func_135052_a(furnaceInventory.func_145825_b(), new Object[0]);
    field_146289_q.func_78276_b(s, field_146999_f / 2 - field_146289_q.func_78256_a(s) / 2, 6, 4210752);
    field_146289_q.func_78276_b(I18n.func_135052_a("container.inventory", new Object[0]), 8, field_147000_g - 96 + 2, 4210752);
  }
  
  protected void func_146976_a(float par1, int par2, int par3)
  {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    field_146297_k.func_110434_K().func_110577_a(IMAGE_URL);
    int k = (field_146294_l - field_146999_f) / 2;
    int l = (field_146295_m - field_147000_g) / 2;
    func_73729_b(k, l, 0, 0, field_146999_f, field_147000_g);
    int brewTime = furnaceInventory.getCookProgressScaled(38);
    
    if (furnaceInventory.powerLevel <= 0) {
      func_73729_b(k + 35, l + 58, 197, 0, 9, 9);
    }
    
    if (brewTime > 0)
    {
      func_73729_b(k + 68, l + 14, 176, 29, brewTime, 35);
      
      int k1 = (800 - furnaceInventory.furnaceCookTime) / 2 % 7;
      
      switch (k1) {
      case 0: 
        brewTime = 29;
        break;
      case 1: 
        brewTime = 24;
        break;
      case 2: 
        brewTime = 20;
        break;
      case 3: 
        brewTime = 16;
        break;
      case 4: 
        brewTime = 11;
        break;
      case 5: 
        brewTime = 6;
        break;
      case 6: 
        brewTime = 0;
      }
      
      if (brewTime > 0) {
        func_73729_b(k + 33, l + 20 + 29 - brewTime, 185, 29 - brewTime, 12, brewTime);
      }
    }
  }
}
