package com.emoniph.witchery.client.gui;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.item.ItemBook;
import com.emoniph.witchery.network.PacketItemUpdate;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

@SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class GuiScreenBiomeBook extends GuiScreen
{
  private static final ResourceLocation field_110405_a = new ResourceLocation("textures/gui/book.png");
  
  private final EntityPlayer player;
  
  private final ItemStack itemstack;
  private int updateCount;
  private int bookImageWidth = 192;
  private int bookImageHeight = 192;
  private int pageIndex;
  private NBTTagList bookPages;
  private String bookTitle = "";
  
  private GuiButtonNextPage buttonNextPage;
  
  private GuiButtonNextPage buttonPreviousPage;
  private GuiButton buttonDone;
  private GuiButtonJumpPage buttonJumpPage1;
  private GuiButtonJumpPage buttonJumpPage2;
  private GuiButtonJumpPage buttonJumpPage3;
  private GuiButtonJumpPage buttonJumpPage4;
  private GuiButtonJumpPage buttonJumpPage5;
  private GuiButtonJumpPage buttonJumpPage6;
  private GuiButtonJumpPage buttonJumpPage7;
  ArrayList<BiomeGenBase> biomes = new ArrayList();
  ArrayList<Integer> sections = new ArrayList();
  ArrayList<String> sectionNames = new ArrayList();
  
  public GuiScreenBiomeBook(EntityPlayer player, ItemStack itemstack) {
    this.player = player;
    this.itemstack = itemstack;
    bookTitle = itemstack.func_82833_r();
    
    for (BiomeDictionary.Type biomeType : ItemBook.BIOME_TYPES) {
      addBiomes(biomeType);
    }
    
    pageIndex = ItemBook.getSelectedBiome(itemstack, biomes.size());
  }
  
  private void addBiomes(BiomeDictionary.Type biomeType) {
    BiomeGenBase[] biomesInType = BiomeDictionary.getBiomesForType(biomeType);
    sections.add(Integer.valueOf(biomes.size()));
    sectionNames.add(Witchery.resource("witchery.book.biomes." + biomeType.toString().toLowerCase() + ".name"));
    for (int i = 0; i < biomesInType.length; i++) {
      biomes.add(biomesInType[i]);
    }
  }
  
  private void storeCurrentPage() {
    ItemBook.setSelectedBiome(itemstack, pageIndex);
  }
  
  public void func_73876_c()
  {
    super.func_73876_c();
    updateCount += 1;
  }
  
  public void func_73866_w_()
  {
    field_146292_n.clear();
    Keyboard.enableRepeatEvents(true);
    
    byte b0 = 2;
    int mid = (field_146294_l - bookImageWidth) / 2;
    field_146292_n.add(this.buttonNextPage = new GuiButtonNextPage(1, mid + 120, b0 + 154, true));
    field_146292_n.add(this.buttonPreviousPage = new GuiButtonNextPage(2, mid + 38, b0 + 154, false));
    
    for (int i = sections.size() - 1; i >= 0; i--) {
      GuiButton button = new GuiButtonBookmark(3 + i, mid + 160, 12 * i + 10, ((Integer)sections.get(i)).intValue(), (String)sectionNames.get(i));
      
      field_146124_l = true;
      field_146292_n.add(button);
    }
    updateButtons();
  }
  
  public void func_146281_b()
  {
    Keyboard.enableRepeatEvents(false);
    sendBookToServer(false);
  }
  
  private void updateButtons() {
    buttonNextPage.field_146125_m = (pageIndex < biomes.size() - 1);
    buttonPreviousPage.field_146125_m = (pageIndex > 0);
  }
  
  private void sendBookToServer(boolean par1) {
    if ((player != null) && (pageIndex >= 0) && (pageIndex < 1000) && (player.field_71071_by.field_70461_c >= 0) && (player.field_71071_by.func_70448_g() != null))
    {
      Witchery.packetPipeline.sendToServer(new PacketItemUpdate(player.field_71071_by.field_70461_c, pageIndex, player.field_71071_by.func_70448_g()));
    }
  }
  

  protected void func_146284_a(GuiButton button)
  {
    if (field_146124_l) {
      if (field_146127_k == 0) {
        field_146297_k.func_147108_a((GuiScreen)null);
      }
      else if (field_146127_k == 1) {
        if (pageIndex < biomes.size() - 1) {
          pageIndex += 1;
          storeCurrentPage();
        }
      } else if (field_146127_k == 2) {
        if (pageIndex > 0) {
          pageIndex -= 1;
          storeCurrentPage();
        }
      } else if ((button instanceof GuiButtonBookmark)) {
        GuiButtonBookmark but = (GuiButtonBookmark)button;
        pageIndex = nextPage;
        storeCurrentPage();
      }
      
      updateButtons();
    }
  }
  
  protected void func_73869_a(char par1, int par2)
  {
    super.func_73869_a(par1, par2);
  }
  
  public void func_73863_a(int par1, int par2, float par3)
  {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    
    field_146297_k.func_110434_K().func_110577_a(field_110405_a);
    int k = (field_146294_l - bookImageWidth) / 2;
    byte b0 = 2;
    func_73729_b(k, b0, 0, 0, bookImageWidth, bookImageHeight);
    
    if ((biomes.size() > 0) && (pageIndex >= 0) && (pageIndex < biomes.size())) {
      String pageNumberText = I18n.func_135052_a("book.pageIndicator", new Object[] { Integer.valueOf(pageIndex + 1), Integer.valueOf(biomes.size()) });
      int pageNumberTextWitdh = field_146289_q.func_78256_a(pageNumberText);
      field_146289_q.func_78276_b(pageNumberText, k - pageNumberTextWitdh + bookImageWidth - 44, b0 + 16, 0);
      
      BiomeGenBase biome = (BiomeGenBase)biomes.get(pageIndex);
      int maxWidth = 116;
      int defaultColor = 0;
      b0 = (byte)(b0 + drawSpiltString(field_76791_y, k + 36, b0 + 32, 116, 0));
      b0 = (byte)(b0 + field_146289_q.field_78288_b);
      b0 = (byte)(b0 + drawSpiltString("> " + String.format(Witchery.resource("witchery.biomebook.rainfall"), new Object[] { Float.valueOf(field_76751_G) }), k + 36, b0 + 32, 116, 0));
      
      String temperatureFormat = Witchery.resource(biome.func_76736_e() ? "witchery.biomebook.temperaturehot" : "witchery.biomebook.temperature");
      b0 = (byte)(b0 + drawSpiltString("> " + String.format(temperatureFormat, new Object[] { Float.valueOf(field_76750_F) }), k + 36, b0 + 32, 116, 0));
      
      b0 = (byte)(b0 + drawSpiltString("> " + String.format(Witchery.resource("witchery.biomebook.snows"), new Object[] { toYesNo(biome.func_76746_c()) }), k + 36, b0 + 32, 116, 0));
      
      b0 = (byte)(b0 + drawSpiltString("> " + String.format(Witchery.resource("witchery.biomebook.lightning"), new Object[] { toYesNo(biome.func_76738_d()) }), k + 36, b0 + 32, 116, 0));
    }
    

    super.func_73863_a(par1, par2, par3);
  }
  
  private int drawSpiltString(String text, int x, int y, int maxWidth, int color) {
    int height = field_146289_q.func_78267_b(text, maxWidth);
    field_146289_q.func_78279_b(text, x, y, maxWidth, color);
    return height;
  }
  
  private String toYesNo(boolean val) {
    return Witchery.resource(val ? "witchery.yes" : "witchery.no");
  }
  
  public static void drawTexturedQuadFit(double x, double y, double width, double height, double zLevel) {
    Tessellator tessellator = Tessellator.field_78398_a;
    tessellator.func_78382_b();
    tessellator.func_78374_a(x + 0.0D, y + height, zLevel, 0.0D, 1.0D);
    tessellator.func_78374_a(x + width, y + height, zLevel, 1.0D, 1.0D);
    tessellator.func_78374_a(x + width, y + 0.0D, zLevel, 1.0D, 0.0D);
    tessellator.func_78374_a(x + 0.0D, y + 0.0D, zLevel, 0.0D, 0.0D);
    tessellator.func_78381_a();
  }
  
  static ResourceLocation func_110404_g() {
    return field_110405_a;
  }
}
