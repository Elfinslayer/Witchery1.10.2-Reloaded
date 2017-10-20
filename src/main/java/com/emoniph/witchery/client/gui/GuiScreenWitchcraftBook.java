package com.emoniph.witchery.client.gui;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.crafting.BrazierRecipes;
import com.emoniph.witchery.crafting.BrazierRecipes.BrazierRecipe;
import com.emoniph.witchery.crafting.DistilleryRecipes;
import com.emoniph.witchery.crafting.DistilleryRecipes.DistilleryRecipe;
import com.emoniph.witchery.crafting.KettleRecipes;
import com.emoniph.witchery.crafting.KettleRecipes.KettleRecipe;
import com.emoniph.witchery.infusion.infusions.spirit.InfusedSpiritEffect;
import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.ritual.RiteRegistry;
import com.emoniph.witchery.ritual.RiteRegistry.Ritual;
import com.emoniph.witchery.util.Const;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary.Type;

@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class GuiScreenWitchcraftBook extends GuiScreen
{
  private static final ResourceLocation field_110405_a = new ResourceLocation("textures/gui/book.png");
  public static final ResourceLocation DOUBLE_BOOK_TEXTURE = new ResourceLocation("witchery", "textures/gui/bookDouble.png");
  
  private static final ResourceLocation[] field_110405_b = { new ResourceLocation("witchery", "textures/gui/circle_white_large.png"), new ResourceLocation("witchery", "textures/gui/circle_blue_large.png"), new ResourceLocation("witchery", "textures/gui/circle_red_large.png"), new ResourceLocation("witchery", "textures/gui/circle_white_medium.png"), new ResourceLocation("witchery", "textures/gui/circle_blue_medium.png"), new ResourceLocation("witchery", "textures/gui/circle_red_medium.png"), new ResourceLocation("witchery", "textures/gui/circle_white_small.png"), new ResourceLocation("witchery", "textures/gui/circle_blue_small.png"), new ResourceLocation("witchery", "textures/gui/circle_red_small.png") };
  










  private static final String[] sizes = { "§715x15§0", "§515x15§0", "§415x15§0", "§711x11§0", "§511x11§0", "§411x11§0", "§77x7§0", "§57x7§0", "§47x7§0" };
  
  private final EntityPlayer player;
  
  private final ItemStack itemstack;
  
  private int updateCount;
  private int bookImageWidth = 192;
  private int bookImageHeight = 192;
  private int bookTotalPages = 1;
  private int currPage;
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
  private static final String CURRENT_PAGE_KEY = "CurrentPage";
  
  public GuiScreenWitchcraftBook(EntityPlayer player, ItemStack itemstack) {
    this.player = player;
    this.itemstack = itemstack;
    
    bookTitle = itemstack.func_82833_r();
    

    bookPages = new NBTTagList();
    NBTTagCompound compound; if (ItemsGENERIC.itemBookOven.isMatch(itemstack)) {
      NBTTagCompound compound = new NBTTagCompound();
      compound.func_74778_a("Summary", Witchery.resource("witchery.book.oven1"));
      bookPages.func_74742_a(compound);
      
      compound = new NBTTagCompound();
      compound.func_74778_a("Summary", Witchery.resource("witchery.book.oven2"));
      bookPages.func_74742_a(compound);
      
      compound = new NBTTagCompound();
      compound.func_74778_a("Summary", Witchery.resource("witchery.book.oven3"));
      bookPages.func_74742_a(compound); } else { NBTTagCompound compound;
      if (ItemsGENERIC.itemBookDistilling.isMatch(itemstack))
      {
        compound = new NBTTagCompound();
        String intro = Witchery.resource("witchery.book.distillery1");
        compound.func_74778_a("Summary", intro);
        bookPages.func_74742_a(compound);
        
        for (DistilleryRecipes.DistilleryRecipe recipe : instancerecipes) {
          compound = new NBTTagCompound();
          compound.func_74778_a("Summary", recipe.getDescription());
          bookPages.func_74742_a(compound); } } else { NBTTagCompound compound;
        String anyCircles;
        if (ItemsGENERIC.itemBookCircleMagic.isMatch(itemstack))
        {
          compound = new NBTTagCompound();
          String intro = Witchery.resource("witchery.book.rites1");
          String intro2 = Witchery.resource("witchery.book.rites2");
          anyCircles = Witchery.resource("witchery.book.rites.anycircle");
          compound.func_74778_a("Summary", intro);
          compound.func_74778_a("Summary2", intro2);
          compound.func_74773_a("Circles", new byte[] { 0, 3, 6 });
          bookPages.func_74742_a(compound);
          

          for (RiteRegistry.Ritual ritual : RiteRegistry.instance().getSortedRituals())
            if (ritual.showInBook()) {
              compound = new NBTTagCompound();
              compound.func_74778_a("Summary", ritual.getDescription());
              byte[] circles = ritual.getCircles();
              compound.func_74773_a("Circles", circles);
              if (circles.length == 0) {
                compound.func_74778_a("Summary2", anyCircles);
              } else {
                StringBuilder sb = new StringBuilder();
                for (byte cir : circles) {
                  if (sb.length() > 0) {
                    sb.append(", ");
                  }
                  sb.append(sizes[cir]);
                }
                compound.func_74778_a("Summary2", sb.toString());
              }
              bookPages.func_74742_a(compound);
            }
        } else { NBTTagCompound compound;
          if (ItemsGENERIC.itemBookInfusions.isMatch(itemstack))
          {
            compound = new NBTTagCompound();
            String intro = Witchery.resource("witchery.book.brews1");
            compound.func_74778_a("Summary", intro);
            bookPages.func_74742_a(compound);
            
            for (KettleRecipes.KettleRecipe recipe : instancerecipes)
              if (inBook) {
                compound = new NBTTagCompound();
                compound.func_74778_a("Summary", recipe.getDescription());
                bookPages.func_74742_a(compound);
              }
          } else { NBTTagCompound compound;
            if (ItemsGENERIC.itemBookBurning.isMatch(itemstack))
            {
              compound = new NBTTagCompound();
              String intro = Witchery.resource("witchery.book.burning1");
              compound.func_74778_a("Summary", intro);
              bookPages.func_74742_a(compound);
              
              for (BrazierRecipes.BrazierRecipe recipe : instancerecipes) {
                if (inBook) {
                  compound = new NBTTagCompound();
                  compound.func_74778_a("Summary", recipe.getDescription());
                  bookPages.func_74742_a(compound);
                }
              }
              
              compound = new NBTTagCompound();
              String intro2 = Witchery.resource("witchery.book.burning2");
              compound.func_74778_a("Summary", intro2);
              bookPages.func_74742_a(compound);
              
              for (InfusedSpiritEffect effect : InfusedSpiritEffect.effectList) {
                if ((effect != null) && (effect.isInBook())) {
                  compound = new NBTTagCompound();
                  compound.func_74778_a("Summary", effect.getDescription());
                  bookPages.func_74742_a(compound);
                }
              }
            }
            else if (ItemsGENERIC.itemBookHerbology.isMatch(itemstack))
            {
              NBTTagCompound compound = new NBTTagCompound();
              String intro = Witchery.resource("witchery.book.herbology1");
              compound.func_74778_a("Summary", intro);
              bookPages.func_74742_a(compound);
              
              addPlantPage(BlocksCROP_BELLADONNA, "witchery.book.herbology.belladonna", "witchery:textures/blocks/belladonna_stage_4.png");
              addPlantPage(BlocksEMBER_MOSS, "witchery.book.herbology.embermoss", "witchery:textures/blocks/embermoss.png");
              addPlantPage(BlocksGLINT_WEED, "witchery.book.herbology.glintweed", "witchery:textures/blocks/glintWeed.png");
              addPlantPage(BlocksCROP_MANDRAKE, "witchery.book.herbology.mandrake", "witchery:textures/blocks/mandrake_stage_4.png");
              addPlantPage(BlocksCROP_SNOWBELL, "witchery.book.herbology.snowbell", "witchery:textures/blocks/snowbell_stage_4.png");
              addPlantPage(BlocksSPANISH_MOSS, "witchery.book.herbology.spanishmoss", "witchery:textures/blocks/spanishMoss.png");
              addPlantPage(new ItemStack(BlocksBRAMBLE, 1, 1), "witchery.book.herbology.wildbramble", "witchery:textures/blocks/bramble_wild.png");
              addPlantPage(new ItemStack(BlocksBRAMBLE, 1, 0), "witchery.book.herbology.enderbramble", "witchery:textures/blocks/bramble_ender.png");
              addPlantPage(BlocksVOID_BRAMBLE, "witchery.book.herbology.voidbramble", "witchery:textures/blocks/voidBramble.png");
              addPlantPage(BlocksCROP_ARTICHOKE, "witchery.book.herbology.artichoke", "witchery:textures/blocks/artichoke_stage_4.png");
              addPlantPage(BlocksGRASSPER, "witchery.book.herbology.grassper", "witchery:textures/blocks/grassperIcon.png");
              addPlantPage(BlocksCRITTER_SNARE, "witchery.book.herbology.crittersnare", "witchery:textures/blocks/critterSnare_empty.png");
              addPlantPage(BlocksBLOOD_ROSE, "witchery.book.herbology.bloodrose", "witchery:textures/blocks/bloodrose.png");
              addPlantPage(BlocksWISPY_COTTON, "witchery.book.herbology.somniancotton", "witchery:textures/blocks/somnianCotton.png");
              addPlantPage(BlocksCROP_WOLFSBANE, "witchery.book.herbology.wolfsbane", "witchery:textures/blocks/wolfsbane_stage_7.png");
              addPlantPage(BlocksCROP_GARLIC, "witchery.book.herbology.garlic", "witchery:textures/blocks/garlic_stage_5.png");
              
              addPlantPage(new ItemStack(BlocksSAPLING, 1, 1), "witchery.book.herbology.alder", "witchery:textures/blocks/sapling_alder.png");
              addPlantPage(new ItemStack(BlocksSAPLING, 1, 2), "witchery.book.herbology.hawthorn", "witchery:textures/blocks/sapling_hawthorn.png");
              addPlantPage(new ItemStack(BlocksSAPLING, 1, 0), "witchery.book.herbology.rowan", "witchery:textures/blocks/sapling_rowan.png");
            } else if (ItemsGENERIC.itemBookBiomes.isMatch(itemstack)) {
              NBTTagCompound compound = new NBTTagCompound();
              String intro = Witchery.resource("witchery.book.biomes1");
              compound.func_74778_a("Summary", intro);
              bookPages.func_74742_a(compound);
              
              addBiomes(BiomeDictionary.Type.FOREST);
              addBiomes(BiomeDictionary.Type.PLAINS);
              addBiomes(BiomeDictionary.Type.MOUNTAIN);
              addBiomes(BiomeDictionary.Type.HILLS);
              addBiomes(BiomeDictionary.Type.SWAMP);
              addBiomes(BiomeDictionary.Type.WATER);
              addBiomes(BiomeDictionary.Type.DESERT);
              addBiomes(BiomeDictionary.Type.FROZEN);
              addBiomes(BiomeDictionary.Type.JUNGLE);
              addBiomes(BiomeDictionary.Type.WASTELAND);
              addBiomes(BiomeDictionary.Type.BEACH);
              addBiomes(BiomeDictionary.Type.MUSHROOM);
              addBiomes(BiomeDictionary.Type.MAGICAL);
            } else if (ItemsGENERIC.itemBookWands.isMatch(itemstack)) {
              compound = new NBTTagCompound();
              String intro = Witchery.resource("witchery.book.wands1");
              compound.func_74778_a("Summary", intro);
              bookPages.func_74742_a(compound);
              
              for (SymbolEffect recipe : com.emoniph.witchery.infusion.infusions.symbols.EffectRegistry.instance().getEffects())
                if (recipe.isVisible(player)) {
                  compound = new NBTTagCompound();
                  compound.func_74778_a("Summary", recipe.getDescription());
                  bookPages.func_74742_a(compound);
                }
            }
          }
        } } }
    bookTotalPages = bookPages.func_74745_c();
    
    NBTTagCompound stackCompound = itemstack.func_77978_p();
    if ((stackCompound != null) && (stackCompound.func_74764_b("CurrentPage"))) {
      currPage = Math.min(Math.max(stackCompound.func_74762_e("CurrentPage"), 0), Math.max(bookTotalPages, 1) - 1);
    }
  }
  
  private void addBiomes(BiomeDictionary.Type biomeType) {
    String biomeKey = biomeType.toString().toLowerCase();
    String title = "§n" + Witchery.resource(new StringBuilder().append("witchery.book.biomes.").append(biomeKey).append(".name").toString()) + "§r" + "\n\n" + "§8" + Witchery.resource("witchery.book.biomes.foci") + ": " + Witchery.resource(new StringBuilder().append("witchery.book.biomes.").append(biomeKey).append(".item").toString()) + "§0" + Const.BOOK_NEWLINE;
    
    BiomeGenBase[] biomes = net.minecraftforge.common.BiomeDictionary.getBiomesForType(biomeType);
    int ITEMS_PER_PAGE = 8;
    StringBuilder sb = new StringBuilder();
    
    for (int glowstone = 1; glowstone <= biomes.length; glowstone++) {
      sb.append(glowstone);
      sb.append(" : ");
      sb.append(1field_76791_y);
      sb.append(Const.BOOK_NEWLINE);
      if ((glowstone % 8 == 0) || (glowstone == biomes.length)) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74778_a("Summary", title + Const.BOOK_NEWLINE + sb.toString());
        bookPages.func_74742_a(compound);
        sb = new StringBuilder();
      }
    }
  }
  
  private void addPlantPage(ItemStack plantStack, String descriptionResourceID, String imageResourceID) {
    NBTTagCompound compound = new NBTTagCompound();
    compound.func_74778_a("Summary", "§n" + plantStack.func_82833_r() + "§r");
    compound.func_74778_a("Details", Witchery.resource(descriptionResourceID));
    compound.func_74778_a("Image", imageResourceID);
    bookPages.func_74742_a(compound);
  }
  
  private void addPlantPage(Block plantBlock, String descriptionResourceID, String imageResourceID) {
    NBTTagCompound compound = new NBTTagCompound();
    compound.func_74778_a("Summary", "§n" + plantBlock.func_149732_F() + "§r");
    compound.func_74778_a("Details", Witchery.resource(descriptionResourceID));
    compound.func_74778_a("Image", imageResourceID);
    bookPages.func_74742_a(compound);
  }
  

  private void storeCurrentPage()
  {
    if (itemstack.func_77978_p() == null) {
      itemstack.func_77982_d(new NBTTagCompound());
    }
    itemstack.func_77978_p().func_74768_a("CurrentPage", currPage);
  }
  
  public void func_73876_c()
  {
    super.func_73876_c();
    updateCount += 1;
  }
  
  public void func_73866_w_()
  {
    field_146292_n.clear();
    org.lwjgl.input.Keyboard.enableRepeatEvents(true);
    
    field_146292_n.add(this.buttonDone = new GuiButton(0, field_146294_l / 2 - 100, 4 + bookImageHeight, 200, 20, I18n.func_135052_a("gui.done", new Object[0])));
    
    int i = (field_146294_l - bookImageWidth) / 2;
    byte b0 = 2;
    if (ItemsGENERIC.itemBookCircleMagic.isMatch(itemstack)) {
      field_146292_n.add(this.buttonNextPage = new GuiButtonNextPage(1, i + 180, b0 + 154, true));
      field_146292_n.add(this.buttonPreviousPage = new GuiButtonNextPage(2, i + 110, b0 + 154, false));
      
      field_146292_n.add(this.buttonJumpPage7 = new GuiButtonJumpPage(9, i + 214, b0 + 138, 69, 48, 248));
      field_146292_n.add(this.buttonJumpPage6 = new GuiButtonJumpPage(8, i + 214, b0 + 118, 58, 40, 248));
      field_146292_n.add(this.buttonJumpPage5 = new GuiButtonJumpPage(7, i + 214, b0 + 98, 47, 32, 248));
      field_146292_n.add(this.buttonJumpPage4 = new GuiButtonJumpPage(6, i + 214, b0 + 78, 29, 24, 248));
      field_146292_n.add(this.buttonJumpPage3 = new GuiButtonJumpPage(5, i + 214, b0 + 58, 23, 16, 248));
      field_146292_n.add(this.buttonJumpPage2 = new GuiButtonJumpPage(4, i + 214, b0 + 38, 17, 8, 248));
      field_146292_n.add(this.buttonJumpPage1 = new GuiButtonJumpPage(3, i + 214, b0 + 18, 2, 0, 248));
    } else {
      field_146292_n.add(this.buttonNextPage = new GuiButtonNextPage(1, i + 120, b0 + 154, true));
      field_146292_n.add(this.buttonPreviousPage = new GuiButtonNextPage(2, i + 38, b0 + 154, false));
    }
    updateButtons();
  }
  
  public void func_146281_b()
  {
    org.lwjgl.input.Keyboard.enableRepeatEvents(false);
    sendBookToServer(false);
  }
  
  private void updateButtons() {
    buttonNextPage.field_146125_m = (currPage < bookTotalPages - 1);
    buttonPreviousPage.field_146125_m = (currPage > 0);
  }
  
  private void sendBookToServer(boolean par1) {
    if ((player != null) && (currPage >= 0) && (currPage < 1000) && (player.field_71071_by.field_70461_c >= 0) && (player.field_71071_by.func_70448_g() != null)) {
      Witchery.packetPipeline.sendToServer(new com.emoniph.witchery.network.PacketItemUpdate(player.field_71071_by.field_70461_c, currPage, player.field_71071_by.func_70448_g()));
    }
  }
  
  protected void func_146284_a(GuiButton par1GuiButton)
  {
    if (field_146124_l) {
      if (field_146127_k == 0) {
        field_146297_k.func_147108_a((GuiScreen)null);
      }
      else if (field_146127_k == 1) {
        if (currPage < bookTotalPages - 1) {
          currPage += 1;
          storeCurrentPage();
        }
      } else if (field_146127_k == 2) {
        if (currPage > 0) {
          currPage -= 1;
          storeCurrentPage();
        }
      } else if ((par1GuiButton instanceof GuiButtonJumpPage)) {
        GuiButtonJumpPage but = (GuiButtonJumpPage)par1GuiButton;
        currPage = (nextPage - 1);
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
    org.lwjgl.opengl.GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    
    if (ItemsGENERIC.itemBookCircleMagic.isMatch(itemstack)) {
      field_146297_k.func_110434_K().func_110577_a(DOUBLE_BOOK_TEXTURE);
      bookImageWidth = 256;
      int k = (field_146294_l - bookImageWidth) / 2;
      byte b0 = 2;
      func_73729_b(k, b0, 0, 0, bookImageWidth, bookImageHeight);
      


      String s3 = "";
      

      String s = I18n.func_135052_a("book.pageIndicator", new Object[] { Integer.valueOf(currPage + 1), Integer.valueOf(bookTotalPages) });
      String s1 = "";
      String s2 = "";
      
      if ((bookPages != null) && (currPage >= 0) && (currPage < bookPages.func_74745_c())) {
        NBTTagCompound compound = bookPages.func_150305_b(currPage);
        s1 = compound.func_74779_i("Summary");
        s2 = compound.func_74779_i("Summary2");
        if (compound.func_74764_b("Circles")) {
          byte[] circles = compound.func_74770_j("Circles");
          for (byte circle : circles) {
            field_146297_k.func_110434_K().func_110577_a(field_110405_b[circle]);
            func_73729_b(k, b0, 65388, -36, bookImageWidth, bookImageHeight);
          }
        }
      }
      

      int l = field_146289_q.func_78256_a(s);
      field_146289_q.func_78276_b(s, k - l + bookImageWidth - 16, b0 + 16, 0);
      field_146289_q.func_78279_b(s1, k + 20, b0 + 16, 98, 0);
      
      if (!s2.isEmpty()) {
        int swidth = field_146289_q.func_78256_a(s2);
        
        if (swidth < 90) {
          field_146289_q.func_78279_b(s2, k + bookImageWidth / 4 * 3 - swidth / 2, b0 + 125, 98, 0);
        } else {
          field_146289_q.func_78279_b(s2, k + 142, b0 + 125, 98, 0);
        }
      }
    } else {
      field_146297_k.func_110434_K().func_110577_a(field_110405_a);
      int k = (field_146294_l - bookImageWidth) / 2;
      byte b0 = 2;
      func_73729_b(k, b0, 0, 0, bookImageWidth, bookImageHeight);
      

      String s2 = "";
      

      String s = I18n.func_135052_a("book.pageIndicator", new Object[] { Integer.valueOf(currPage + 1), Integer.valueOf(bookTotalPages) });
      String s1 = "";
      
      boolean hasImage = false;
      if ((bookPages != null) && (currPage >= 0) && (currPage < bookPages.func_74745_c())) {
        NBTTagCompound compound = bookPages.func_150305_b(currPage);
        s1 = compound.func_74779_i("Summary");
        if (compound.func_74764_b("Circles")) {
          byte[] circles = compound.func_74770_j("Circles");
          for (byte circle : circles) {
            field_146297_k.func_110434_K().func_110577_a(field_110405_b[circle]);
            func_73729_b(k, b0, -62, -70, bookImageWidth, bookImageHeight);
          }
        }
        hasImage = compound.func_74764_b("Image");
        if (hasImage) {
          String loc = compound.func_74779_i("Image");
          ResourceLocation location = new ResourceLocation(loc);
          field_146297_k.func_110434_K().func_110577_a(location);
          
          drawTexturedQuadFit(k - 32 + bookImageWidth - 44, b0 + 32, 32.0D, 32.0D, field_73735_i);
        }
        
        if (compound.func_74764_b("Details")) {
          s2 = compound.func_74779_i("Details");
        }
      }
      
      int l = field_146289_q.func_78256_a(s);
      field_146289_q.func_78276_b(s, k - l + bookImageWidth - 44, b0 + 16, 0);
      field_146289_q.func_78279_b(s1, k + 36, b0 + 32, 116 - (hasImage ? 34 : 0), 0);
      if ((s2 != null) && (!s2.isEmpty())) {
        field_146289_q.func_78279_b(s2, k + 36, b0 + 32 + 34, 116, 0);
      }
    }
    
    super.func_73863_a(par1, par2, par3);
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
