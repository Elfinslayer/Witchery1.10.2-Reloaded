package com.emoniph.witchery.client.gui;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
import com.emoniph.witchery.network.PacketSyncMarkupBook;
import com.emoniph.witchery.util.NBT;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.RegistryNamespaced;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

@SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class GuiScreenMarkupBook extends GuiScreen
{
  private static final ResourceLocation BACKGROUND = new ResourceLocation("witchery:textures/gui/bookSingle.png");
  
  private final EntityPlayer player;
  
  private final ItemStack itemstack;
  private final int meta;
  private int updateCount;
  private int bookImageWidth = 192;
  private int bookImageHeight = 192;
  
  private GuiButtonNavigate buttonTopPage;
  
  private GuiButtonNavigate buttonPreviousPage;
  
  private GuiButtonNavigate buttonNextPage;
  private final List<String> pageStack = new ArrayList();
  
  public GuiScreenMarkupBook(EntityPlayer player, ItemStack itemstack) {
    this.player = player;
    this.itemstack = itemstack;
    meta = (itemstack != null ? itemstack.func_77960_j() : 0);
    
    NBTTagList nbtPageStack = NBT.get(itemstack).func_150295_c("pageStack", 8);
    for (int i = 0; i < nbtPageStack.func_74745_c(); i++) {
      pageStack.add(nbtPageStack.func_150307_f(i));
    }
  }
  
  public void func_73876_c()
  {
    super.func_73876_c();
    updateCount += 1;
  }
  
  public void func_73866_w_()
  {
    Keyboard.enableRepeatEvents(true);
    constructPage();
  }
  
  final List<Element> elements = new ArrayList();
  private NextPage nextPage;
  
  private void constructPage() { String page = pageStack.size() > 0 ? (String)pageStack.get(pageStack.size() - 1) : "toc";
    
    field_146292_n.clear();
    elements.clear();
    
    byte b0 = 2;
    int mid = (field_146294_l - bookImageWidth) / 2;
    field_146292_n.add(this.buttonTopPage = new GuiButtonNavigate(1, mid + 120, b0 + 16, 2, BACKGROUND));
    field_146292_n.add(this.buttonPreviousPage = new GuiButtonNavigate(2, mid + 34, b0 + 16, 1, BACKGROUND));
    field_146292_n.add(this.buttonNextPage = new GuiButtonNavigate(3, mid + 120, b0 + 16, 0, BACKGROUND));
    
    String itemName = Item.field_150901_e.func_148750_c(itemstack.func_77973_b());
    String untranslated = itemName + "." + page;
    StringBuilder markup = new StringBuilder(StatCollector.func_74838_a(untranslated));
    if ((markup == null) || (markup.toString().equals(untranslated))) {
      return;
    }
    
    for (int i = 0; i < markup.length(); i++) {
      char c = markup.charAt(i);
      switch (c) {
      case '[': 
        elements.add(new Element(null));
        ((Element)elements.get(elements.size() - 1)).append(c);
        break;
      case ']': 
        Element e = (Element)elements.get(elements.size() - 1);
        if (tag.toString().equals("template")) {
          String templatePathRoot = Item.field_150901_e.func_148750_c(itemstack.func_77973_b());
          String templatePath = templatePathRoot + "." + attribute;
          String template = StatCollector.func_74838_a(templatePath);
          if (!template.isEmpty()) {
            String[] parms = text.toString().split("\\s");
            Object[] components = new Object[parms.length];
            for (int j = 0; j < parms.length; j++) {
              String[] kv = parms[j].split("=");
              if (kv.length == 2) {
                if (kv[0].matches("stack\\|\\d+")) {
                  StringBuilder stackList = new StringBuilder();
                  for (String stack : kv[1].split(",")) {
                    stackList.append(String.format("[stack=%s]", new Object[] { stack }));
                  }
                  int index = Math.min(Integer.parseInt(kv[0].substring(kv[0].indexOf('|') + 1)), components.length - 1);
                  

                  components[index] = stackList.toString();
                } else if (kv[0].matches("\\d+")) {
                  int index = Math.min(Integer.parseInt(kv[0]), components.length - 1);
                  components[index] = kv[1];
                }
              }
            }
            markup.insert(i + 1, String.format(template, components));
            
            elements.remove(elements.size() - 1);
          }
        }
        
        elements.add(new Element(null));
        break;
      default: 
        if (elements.size() == 0) {
          elements.add(new Element(null));
        }
        ((Element)elements.get(elements.size() - 1)).append(c);
      }
      
    }
    
    nextPage = null;
    for (Element element : elements) {
      NextPage defaultNextPage = element.constructButtons(field_146292_n, itemstack);
      if (defaultNextPage != null) {
        nextPage = defaultNextPage;
      }
    }
    
    updateButtons();
  }
  
  private static class NextPage {
    public final String pageName;
    public final boolean visible;
    
    public NextPage(String attrib, ItemStack book) { int pipeIndex = attrib.indexOf('|');
      if (pipeIndex != -1) {
        pageName = attrib.substring(0, pipeIndex);
        visible = (book.func_77960_j() >= Integer.parseInt(attrib.substring(pipeIndex + 1)));
      } else {
        pageName = attrib;
        visible = true;
      }
    }
  }
  

  public void func_146281_b()
  {
    Keyboard.enableRepeatEvents(false);
    sendBookToServer();
  }
  
  private void updateButtons() {
    buttonNextPage.field_146125_m = ((nextPage != null) && (nextPage.visible));
    buttonPreviousPage.field_146125_m = (pageStack.size() > 0);
    buttonTopPage.field_146125_m = (pageStack.size() > 0);
  }
  
  private void sendBookToServer() {
    if (player != null) {
      Witchery.packetPipeline.sendToServer(new PacketSyncMarkupBook(player.field_71071_by.field_70461_c, pageStack));
    }
  }
  

  protected void func_146284_a(GuiButton button)
  {
    if (field_146124_l) {
      if (field_146127_k == 0) {
        field_146297_k.func_147108_a((GuiScreen)null);
      }
      else if (field_146127_k == 1) {
        if (pageStack.size() > 0) {
          pageStack.remove(pageStack.size() - 1);
          for (int i = pageStack.size() - 1; i >= 0; i--) {
            if (((String)pageStack.get(i)).startsWith("toc/")) {
              break;
            }
            pageStack.remove(i);
          }
        }
        
        constructPage();
      } else if (field_146127_k == 2) {
        if (pageStack.size() > 0) {
          pageStack.remove(pageStack.size() - 1);
          constructPage();
        }
      } else if (field_146127_k == 3)
      {
        pageStack.add(nextPage.pageName);
        constructPage();
      }
      else if (field_146127_k == 4) {
        pageStack.add(nextPage);
        constructPage();
      }
      
      updateButtons();
    }
  }
  


  protected void func_73869_a(char par1, int par2) { super.func_73869_a(par1, par2); }
  
  private static class Element {
    private Element() {}
    
    private static enum Capture { TAG,  ATTRIB,  TEXT;
      
      private Capture() {} }
    private final StringBuilder tag = new StringBuilder();
    private final StringBuilder attribute = new StringBuilder();
    private final StringBuilder text = new StringBuilder();
    
    private Capture capture = Capture.TEXT;
    private static final String FORMAT_CHAR = "§";
    private static final String FORMAT_CLEAR = "§r";
    
    public String toString() { return String.format("tag=%s attribute=%s text=%s", new Object[] { tag, attribute, text }); }
    
    public void append(char c)
    {
      switch (c) {
      case '[': 
        capture = Capture.TAG;
        break;
      case '=': 
        if (capture == Capture.TAG)
          capture = Capture.ATTRIB;
        break;
      
      case '\t': 
      case ' ': 
        if ((capture == Capture.TAG) || (capture == Capture.ATTRIB))
          capture = Capture.TEXT;
        break;
      }
      
      if (capture == Capture.TAG) {
        tag.append(c);
      } else if (capture == Capture.ATTRIB) {
        attribute.append(c);
      } else {
        text.append(c);
      }
    }
    





    private static final Hashtable<String, String> FORMATS = ;
    private GuiButtonUrl button;
    
    private static Hashtable<String, String> getFormats() { Hashtable<String, String> formats = new Hashtable();
      formats.put("black", "§0");
      formats.put("darkblue", "§1");
      formats.put("darkgreen", "§2");
      formats.put("darkaqua", "§3");
      formats.put("darkred", "§4");
      formats.put("darkpurple", "§5");
      formats.put("darkyellow", "§6");
      formats.put("gray", "§7");
      formats.put("darkgray", "§8");
      formats.put("blue", "§9");
      formats.put("green", "§a");
      formats.put("aqua", "§b");
      formats.put("red", "§c");
      formats.put("purple", "§d");
      formats.put("yellow", "§e");
      formats.put("white", "§f");
      formats.put("b", "§l");
      formats.put("s", "§m");
      formats.put("u", "§n");
      formats.put("i", "§o");
      formats.put("h1", "§3§o");
      return formats;
    }
    

    public GuiScreenMarkupBook.NextPage constructButtons(List buttonList, ItemStack stack)
    {
      String tag = this.tag.toString();
      if (tag.equals("url")) {
        String attrib = attribute.toString();
        int pipeIndex = attrib.indexOf('|');
        if (pipeIndex != -1) {
          attrib = attrib.substring(0, pipeIndex);
        }
        button = new GuiButtonUrl(4, 0, 0, attrib, text.toString());
        buttonList.add(button);
      } else if (tag.equals("next")) {
        return new GuiScreenMarkupBook.NextPage(attribute.toString(), stack);
      }
      return null;
    }
    
    public void draw(int[] pos, int marginX, int maxWidth, GuiScreenMarkupBook.RenderState state)
    {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      
      String tag = this.tag.toString();
      if (tag.equals("br")) {
        state.newline(pos);
        return;
      }
      
      if (tag.equals("tab")) {
        int TAB_SPACE = 10;
        if (pos[0] + 10 > maxWidth) {
          state.newline(pos);
        } else {
          pos[0] += 10;
        }
        return;
      }
      
      if (tag.equals("img"))
      {
        String[] parms = attribute.toString().split("\\|");
        int defaultWidth = 32;
        String url = parms.length > 0 ? parms[0] : "";
        String halign = parms.length > 1 ? parms[1] : "left";
        String valign = parms.length > 2 ? parms[2] : "top";
        int width = parms.length > 3 ? parseInt(parms[3], 32) : 32;
        int height = parms.length > 4 ? parseInt(parms[4], width) : width;
        
        if (!url.isEmpty()) {
          ResourceLocation location = new ResourceLocation(url);
          Minecraft.func_71410_x().func_110434_K().func_110577_a(location);
          
          if (halign.equals("right")) {
            pos[0] = (maxWidth - width);
          } else if (halign.equals("center")) {
            pos[0] = (maxWidth / 2 - width / 2);
          }
          
          if (pos[0] + width > maxWidth) {
            state.newline(pos);
          }
          
          int y = pos[1];
          if (lineheight > height) {
            if (valign.equals("bottom")) {
              y += lineheight - height;
            } else if (valign.equals("middle")) {
              y += lineheight / 2 - height / 2;
            }
          }
          
          drawTexturedQuadFit(pos[0] + marginX, y, width, height, zLevel);
          
          pos[0] += width;
          state.adjustLineHeight(height);
        }
        return;
      }
      
      if (tag.equals("url"))
      {
        button.field_146121_g = font.field_78288_b;
        button.field_146120_f = font.func_78256_a(text.toString());
        if (pos[0] + button.field_146120_f > maxWidth) {
          state.newline(pos);
        }
        String[] parms = attribute.toString().split("\\|");
        String url = parms.length > 0 ? parms[0] : "";
        String valign = parms.length > 1 ? parms[1] : "top";
        button.field_146128_h = (pos[0] + marginX);
        int y = pos[1];
        if (lineheight > button.field_146121_g) {
          if (valign.equals("bottom")) {
            y += lineheight - button.field_146121_g;
          } else if (valign.equals("middle")) {
            y += lineheight / 2 - button.field_146121_g / 2;
          }
        }
        button.field_146129_i = y;
        pos[0] += button.field_146120_f;
        return;
      }
      
      if (tag.equals("locked")) {
        return;
      }
      
      if (tag.equals("stack")) {
        String[] parms = attribute.toString().split("\\|");
        
        String name = parms.length > 0 ? parms[0] : "";
        int damage = 0;
        int size = 1;
        int offset = 1;
        if ((parms.length > offset) && (parms[offset].matches("\\d+"))) {
          damage = parseInt(parms[offset], 0);
          offset++;
        }
        if ((parms.length > offset) && (parms[offset].matches("\\d+"))) {
          size = parseInt(parms[offset], 1);
          offset++;
        }
        String halign = parms.length > offset ? parms[offset] : "left";
        offset++;
        String valign = parms.length > offset ? parms[offset] : "top";
        
        if (!name.isEmpty()) {
          boolean empty = name.equals("empty");
          Item item = !empty ? (Item)Item.field_150901_e.func_82594_a(name) : null;
          ItemStack stack = !empty ? new ItemStack(item, size, damage) : null;
          int width = 18;
          int height = 18;
          
          if (halign.equals("right")) {
            pos[0] = (maxWidth - width);
          } else if (halign.equals("center")) {
            pos[0] = (maxWidth / 2 - width / 2);
          }
          
          if (pos[0] + width > maxWidth) {
            state.newline(pos);
          }
          
          int y = pos[1];
          if (lineheight > height) {
            if (valign.equals("bottom")) {
              y += lineheight - height;
            } else if (valign.equals("middle")) {
              y += lineheight / 2 - height / 2;
            }
          }
          
          if (!empty) {
            RenderItem render = new RenderItem();
            GL11.glPushMatrix();
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            RenderHelper.func_74520_c();
            GL11.glEnable(32826);
            GL11.glEnable(2929);
            int x = pos[0] + marginX;
            
            render.func_82406_b(font, Minecraft.func_71410_x().func_110434_K(), stack, x, y);
            
            render.func_77021_b(font, Minecraft.func_71410_x().func_110434_K(), stack, x, y);
            
            RenderHelper.func_74518_a();
            GL11.glPopMatrix();
            
            if ((mouseX >= x) && (mouseY >= y) && (mouseX <= x + width) && (mouseY <= y + height))
            {
              tooltipStack = stack;
            }
            GL11.glDisable(2896);
          }
          
          pos[0] += width;
          state.adjustLineHeight(height);
          
          String[] words = text.toString().split("(?<=\\s)");
          for (String word : words) {
            int textWidth = font.func_78256_a(word);
            if (pos[0] + textWidth > maxWidth) {
              state.newline(pos);
              y = pos[1];
            }
            font.func_78276_b(word, marginX + pos[0], y + (height - font.field_78288_b) / 2, 0);
            
            pos[0] += textWidth;
          }
        }
        return;
      }
      
      if (tag.equals("next"))
      {
        return;
      }
      
      String preText = FORMATS.containsKey(tag) ? (String)FORMATS.get(tag) : "";
      String postText = FORMATS.containsKey(tag) ? "§r" : "";
      
      String[] words = text.toString().split("(?<=\\s)");
      for (String word : words) {
        int width = font.func_78256_a(word);
        if (pos[0] + width > maxWidth) {
          state.newline(pos);
        }
        if ((pos[0] != 0) || (!word.trim().isEmpty()))
        {

          font.func_78276_b(preText + word + postText, marginX + pos[0], pos[1], 0);
          pos[0] += width;
        }
      }
      if (tag.equals("h1")) {
        state.adjustLineHeight((int)Math.ceil(lineheight * 1.5F));
        state.newline(pos);
      }
    }
    
    private int parseInt(String text, int defaultValue) {
      try {
        return Integer.parseInt(text);
      } catch (NumberFormatException ex) {}
      return defaultValue;
    }
    
    public static void drawTexturedQuadFit(double x, double y, double width, double height, double zLevel)
    {
      Tessellator tessellator = Tessellator.field_78398_a;
      tessellator.func_78382_b();
      tessellator.func_78374_a(x + 0.0D, y + height, zLevel, 0.0D, 1.0D);
      tessellator.func_78374_a(x + width, y + height, zLevel, 1.0D, 1.0D);
      tessellator.func_78374_a(x + width, y + 0.0D, zLevel, 1.0D, 0.0D);
      tessellator.func_78374_a(x + 0.0D, y + 0.0D, zLevel, 0.0D, 0.0D);
      tessellator.func_78381_a();
    }
  }
  
  private static class RenderState {
    final FontRenderer font;
    final float zLevel;
    final int mouseX;
    final int mouseY;
    ItemStack tooltipStack;
    int lineheight;
    
    public RenderState(FontRenderer font, float zLevel, int mouseX, int mouseY) {
      this.font = font;
      this.zLevel = zLevel;
      this.mouseX = mouseX;
      this.mouseY = mouseY;
      lineheight = field_78288_b;
    }
    
    public void newline(int[] pos) {
      pos[0] = 0;
      pos[1] += lineheight + 1;
      lineheight = font.field_78288_b;
    }
    
    public void adjustLineHeight(int newHeight) {
      if (newHeight > lineheight) {
        lineheight = newHeight;
      }
    }
  }
  
  public void func_73863_a(int mouseX, int mouseY, float par3)
  {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    
    field_146297_k.func_110434_K().func_110577_a(BACKGROUND);
    int k = (field_146294_l - bookImageWidth) / 2;
    byte b0 = 2;
    func_73729_b(k, b0, 0, 0, bookImageWidth, bookImageHeight);
    
    int maxWidth = 116;
    int marginX = k + 36;
    
    buttonPreviousPage.field_146128_h = marginX;
    buttonPreviousPage.field_146129_i = 16;
    
    buttonTopPage.field_146128_h = (k + bookImageWidth / 2 - buttonTopPage.field_146120_f / 2 - 4);
    buttonTopPage.field_146129_i = 16;
    
    buttonNextPage.field_146128_h = (k + bookImageWidth - buttonNextPage.field_146120_f - 44);
    buttonNextPage.field_146129_i = 16;
    
    int[] pos = { 0, 32 };
    
    RenderState state = new RenderState(field_146289_q, field_73735_i, mouseX, mouseY);
    for (Element element : elements) {
      element.draw(pos, marginX, 116, state);
    }
    
    super.func_73863_a(mouseX, mouseY, par3);
    
    if (tooltipStack != null)
    {
      func_146285_a(tooltipStack, mouseX, mouseY + 16);
    }
  }
  

  protected void func_146285_a(ItemStack stack, int x, int y)
  {
    List list = stack.func_82840_a(field_146297_k.field_71439_g, field_146297_k.field_71474_y.field_82882_x);
    if (list != null) {
      int power = WitcheryBrewRegistry.INSTANCE.getAltarPower(stack);
      if (power >= 0) {
        list.add(String.format(Witchery.resource("witchery.brewing.ingredientpowercost"), new Object[] { Integer.valueOf(power), Integer.valueOf(MathHelper.func_76143_f(1.4D * power)) }));
      }
    }
    

    for (int k = 0; k < list.size(); k++) {
      if (k == 0) {
        list.set(k, func_77953_tfield_77937_e + (String)list.get(k));
      } else {
        list.set(k, EnumChatFormatting.GRAY + (String)list.get(k));
      }
    }
    
    FontRenderer font = stack.func_77973_b().getFontRenderer(stack);
    drawHoveringText(list, x, y, font == null ? field_146289_q : font);
  }
}
