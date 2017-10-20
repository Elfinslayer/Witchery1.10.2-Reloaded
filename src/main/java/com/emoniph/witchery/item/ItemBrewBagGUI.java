package com.emoniph.witchery.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ItemBrewBagGUI
  extends GuiContainer
{
  private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("witchery", "textures/gui/generic_48.png");
  private IInventory upperInventory;
  private IInventory lowerInventory;
  private int inventoryRows;
  
  public ItemBrewBagGUI(IInventory inventoryPlayer, IInventory inventoryBag)
  {
    super(new ItemBrewBag.ContainerBrewBag(inventoryPlayer, inventoryBag, null));
    upperInventory = inventoryBag;
    lowerInventory = inventoryPlayer;
    inventoryRows = (inventoryBag.func_70302_i_() / 8);
    field_147000_g = (114 + inventoryRows * 18);
  }
  
  protected void func_146979_b(int par1, int par2)
  {
    field_146289_q.func_78276_b(StatCollector.func_74838_a(upperInventory.func_145825_b()), 8, 6, 4210752);
    field_146289_q.func_78276_b(StatCollector.func_74838_a(lowerInventory.func_145825_b()), 8, field_147000_g - 96 + 2, 4210752);
  }
  
  protected void func_146976_a(float var1, int var2, int var3)
  {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    field_146297_k.field_71446_o.func_110577_a(TEXTURE_LOCATION);
    int var5 = (field_146294_l - field_146999_f) / 2;
    int var6 = (field_146295_m - field_147000_g) / 2;
    func_73729_b(var5, var6, 0, 0, field_146999_f, inventoryRows * 18 + 17);
    func_73729_b(var5, var6 + inventoryRows * 18 + 17, 0, 126, field_146999_f, 96);
  }
}
