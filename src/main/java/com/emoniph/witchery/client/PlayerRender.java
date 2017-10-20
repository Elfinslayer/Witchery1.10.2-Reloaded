package com.emoniph.witchery.client;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.brewing.potions.WitcheryPotions;
import com.emoniph.witchery.client.renderer.RenderInfusionEnergyBar;
import com.emoniph.witchery.infusion.InfusedBrewEffect;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.infusion.Infusion.Registry;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePower;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePower.Registry;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerSpeed;
import com.emoniph.witchery.infusion.infusions.symbols.EffectRegistry;
import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
import com.emoniph.witchery.integration.ModHookManager;
import com.emoniph.witchery.item.ItemBrewBag.InventoryBrewBag;
import com.emoniph.witchery.item.ItemWitchesClothes;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.EntitySizeInfo;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.RenderUtil;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class PlayerRender
{
  protected static RenderInfusionEnergyBar infusionEnergyBar;
  protected static RenderInfusionEnergyBar creatureEnergyBar;
  
  public PlayerRender() {}
  
  private boolean remoteViewingActive = true;
  
  public static long ticksSinceActive = 0L;
  public static boolean moveCameraActive = false;
  public static int moveCameraToEntityID = 0;
  
  private static final ResourceLocation RADIAL_LOCATION = new ResourceLocation("witchery", "textures/gui/radial.png");
  
  net.minecraft.client.renderer.EntityRenderer prevRender;
  RenderEntityViewer renderer;
  
  int currentBeastForm = 0;
  
  @SubscribeEvent
  public void onRenderTick(TickEvent.RenderTickEvent event) {
    if (phase == TickEvent.Phase.START) {
      EntityClientPlayerMP player = func_71410_xfield_71439_g;
      Minecraft mc = Minecraft.func_71410_x();
      if ((player != null) && (field_71462_r == null)) {
        if (Minecraft.func_71386_F() - ticksSinceActive > 3000L) {
          moveCameraActive = false;
        }
        remoteViewingActive = moveCameraActive;
        if (remoteViewingActive) {
          for (Iterator i$ = field_70170_p.field_72996_f.iterator(); i$.hasNext(); 
              


              goto 150)
          {
            Object entity = i$.next();
            if ((((Entity)entity).func_145782_y() == moveCameraToEntityID) && ((entity instanceof EntityLivingBase))) {
              EntityLivingBase living = (EntityLivingBase)entity;
              if (field_70128_L) break;
              func_71410_xfield_71451_h = living;
            }
            
          }
        }
        else
        {
          EntitySizeInfo size = new EntitySizeInfo(player);
          PotionEffect shrunk = PotionsRESIZING != null ? player.func_70660_b(PotionsRESIZING) : null;
          if (((shrunk != null) || (!isDefault)) && (!com.emoniph.witchery.integration.ModHookMorph.isMorphed(player, true))) {
            if (renderer == null) {
              renderer = new RenderEntityViewer(mc);
            }
            if (field_71474_y.field_74320_O == 0) {
              if (field_71460_t != renderer) {
                prevRender = field_71460_t;
                field_71460_t = renderer;
              }
            } else if (prevRender != null) {
              field_71460_t = prevRender;
            }
            
            float normalSize = 1.8F;
            float scale = defaultHeight / 1.8F * (shrunk != null ? com.emoniph.witchery.brewing.potions.PotionResizing.getScaleFactor(shrunk.func_76458_c()) : 1.0F);
            
            if (scale < 1.0F) {
              float requiredSize = 1.8F * (1.0F - scale);
              float currentSize = renderer.getOffset();
              if (currentSize < requiredSize) {
                currentSize = Math.min(currentSize + 0.01F, requiredSize);
              } else if (currentSize > requiredSize) {
                currentSize = Math.min(currentSize - 0.01F, requiredSize);
              }
              renderer.setOffset(currentSize);
            } else {
              float requiredSize = -(1.8F * scale - 1.8F);
              float currentSize = renderer.getOffset();
              if (currentSize > requiredSize) {
                currentSize = Math.max(currentSize - 0.01F, requiredSize);
              }
              renderer.setOffset(currentSize);
            }
          } else if ((prevRender != null) && (field_71460_t != prevRender)) {
            if (renderer != null) {
              renderer.setOffset(0.0F);
            }
            field_71460_t = prevRender;
          }
        }
      }
    } else if (phase == TickEvent.Phase.END) {
      EntityClientPlayerMP player = func_71410_xfield_71439_g;
      if ((player != null) && (func_71410_xfield_71462_r == null)) {
        Minecraft mc = Minecraft.func_71410_x();
        
        if (remoteViewingActive) {
          func_71410_xfield_71451_h = player;
        }
        

        ScaledResolution screen = new ScaledResolution(mc, field_71443_c, field_71440_d);
        

        int maxEnergy = Infusion.getMaxEnergy(player);
        if (maxEnergy > 0) {
          if (infusionEnergyBar == null) {
            infusionEnergyBar = new RenderInfusionEnergyBar(true);
          }
          
          double left = instanceguiOnLeft ? 20.0D : screen.func_78326_a() - 20;
          double top = screen.func_78328_b() / 2.0D - 16.0D;
          Infusion infusion = Infusion.Registry.instance().get(player);
          infusionEnergyBar.draw(left, top, Infusion.getCurrentEnergy(player) / maxEnergy, player, infusionID);
        }
        

        int powerID = CreaturePower.getCreaturePowerID(player);
        int charges = CreaturePower.getCreaturePowerCharges(player);
        if (powerID > 0) {
          if (creatureEnergyBar == null) {
            creatureEnergyBar = new RenderInfusionEnergyBar(false);
          }
          
          double left = instanceguiOnLeft ? 30.0D : screen.func_78326_a() - 30;
          
          double top = screen.func_78328_b() / 2.0D - 16.0D;
          creatureEnergyBar.draw(left, top, charges, player, powerID);
        }
        

        ItemStack belt = player.func_71124_b(2);
        if ((belt != null) && (belt.func_77973_b() == ItemsBARK_BELT)) {
          int beltCharges = Math.min(ItemsBARK_BELT.getChargeLevel(belt), ItemsBARK_BELT.getMaxChargeLevel(player));
          drawBarkBeltCharges(player, beltCharges, screen);
        }
        

        drawInfusedBrews(player, screen);
        

        ItemStack stack = player.func_71011_bu();
        if ((stack != null) && (stack.func_77973_b() == ItemsMYSTIC_BRANCH)) {
          byte[] strokes = player.getEntityData().func_74770_j("Strokes");
          
          mc.func_110434_K().func_110577_a(TEXTURE_GRID);
          GL11.glPushMatrix();
          int iconOffset = 0;
          if (instancebranchIconSet == 1) {
            iconOffset = 64;
          }
          try {
            int x = screen.func_78326_a() / 2 - 8;
            int y = screen.func_78328_b() / 2 - 8;
            int DELAY = 8;
            lastY = (lastY == 120 ? 0 : lastY + 1);
            int tempIndex = lastY / 8;
            int imageIndex = tempIndex > 7 ? 15 - tempIndex : tempIndex;
            for (int i = 0; i < strokes.length; i++) {
              x += glyphOffsetX[strokes[i]] * 16;
              y += glyphOffsetY[strokes[i]] * 16;
              
              drawTexturedModalRect(x, y, strokes[i] * 16 + iconOffset, imageIndex * 16, 16, 16);
            }
            SymbolEffect effect = EffectRegistry.instance().getEffect(strokes);
            if (effect != null) {
              String text = effect.getLocalizedName();
              int tx = screen.func_78326_a() / 2 - (int)(getStringWidth(text) / 2.0D);
              int ty = screen.func_78328_b() / 2 + 20;
              drawString(text, tx, ty, 16777215);
            }
          } finally {
            GL11.glPopMatrix();
          }
        } else if ((stack != null) && (stack.func_77973_b() == ItemsBREW_BAG) && (!player.func_70093_af()))
        {
          ItemBrewBag.InventoryBrewBag inv = new ItemBrewBag.InventoryBrewBag(player);
          byte[] strokes = player.getEntityData().func_74770_j("Strokes");
          
          GL11.glPushMatrix();
          try
          {
            int x = screen.func_78326_a() / 2 - 8;
            int y = screen.func_78328_b() / 2 - 8;
            
            if (strokes.length == 0) {
              mc.func_110434_K().func_110577_a(RADIAL_LOCATION);
              
              GL11.glPushMatrix();
              float scale = 0.33333334F;
              GL11.glTranslatef(x - 42 + 5, y - 42 + 5, 0.0F);
              GL11.glScalef(scale, scale, scale);
              int color = com.emoniph.witchery.item.ItemBrewBag.getColor(stack);
              
              float red = (color >>> 16 & 0xFF) / 256.0F;
              float green = (color >>> 8 & 0xFF) / 256.0F;
              float blue = (color & 0xFF) / 256.0F;
              GL11.glColor4f(red, green, blue, 1.0F);
              drawTexturedModalRect(8, 8, 0, 0, 256, 256);
              GL11.glPopMatrix();
            }
            
            drawBrewInSlot(inv, 0, strokes, x + 0, y - 32, 0, -11, 1);
            drawBrewInSlot(inv, 1, strokes, x + 24, y - 24, 23, 6, 0);
            drawBrewInSlot(inv, 2, strokes, x + 32, y - 0, 23, 6, 0);
            drawBrewInSlot(inv, 3, strokes, x + 24, y + 24, 23, 6, 0);
            drawBrewInSlot(inv, 4, strokes, x + 0, y + 32, 0, 19, 1);
            drawBrewInSlot(inv, 5, strokes, x - 24, y + 24, -5, 6, 2);
            drawBrewInSlot(inv, 6, strokes, x - 32, y - 0, -5, 6, 2);
            drawBrewInSlot(inv, 7, strokes, x - 24, y - 24, -5, 6, 2);
          } finally {
            GL11.glPopMatrix();
          }
        }
      }
    }
  }
  
  private void drawInfusedBrews(EntityClientPlayerMP player, ScaledResolution screen) {
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    InfusedBrewEffect effect = InfusedBrewEffect.getActiveBrew(nbtPlayer);
    if (effect != null) {
      String remainingTime = InfusedBrewEffect.getMinutesRemaining(field_70170_p, nbtPlayer, effect);
      if ((remainingTime != null) && (!remainingTime.isEmpty())) {
        Minecraft mc = Minecraft.func_71410_x();
        mc.func_110434_K().func_110577_a(BARK_TEXTURES);
        int tx = screen.func_78326_a() / 2 - 91;
        int screenHeight = screen.func_78328_b();
        int top = screen.func_78328_b() / 2 + 26;
        int screenMid = screenHeight / 2;
        
        int left = instanceguiOnLeft ? 17 : screen.func_78326_a() - 23;
        
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        int ICON_WIDTH = 16;
        int ICON_HEIGHT = 16;
        
        drawTexturedModalRect(left, top, imageMapX, imageMapY, 16, 16);
        
        double width = getStringWidth(remainingTime) / 2.0D;
        drawString(remainingTime, left + 8 - width, top + 10, -285212673);
      }
    }
  }
  
  private static final ResourceLocation BARK_TEXTURES = new ResourceLocation("witchery", "textures/gui/creatures.png");
  
  private void drawBarkBeltCharges(EntityClientPlayerMP player, int beltCharges, ScaledResolution screen) {
    if ((beltCharges > 0) && (!field_71075_bZ.field_75098_d)) {
      Minecraft mc = Minecraft.func_71410_x();
      mc.func_110434_K().func_110577_a(BARK_TEXTURES);
      
      int tx = screen.func_78326_a() / 2 - 91;
      int par2 = screen.func_78328_b();
      int ty = par2 / 2;
      IAttributeInstance attributeinstance = field_71439_g.func_110148_a(net.minecraft.entity.SharedMonsterAttributes.field_111267_a);
      int i2 = par2 - 39;
      float f = (float)attributeinstance.func_111126_e();
      float f1 = field_71439_g.func_110139_bj();
      int j2 = MathHelper.func_76123_f((f + f1) / 2.0F / 10.0F);
      int k2 = Math.max(10 - (j2 - 2), 3);
      int l2 = modHooksisTinkersPresent ? i2 - 10 : i2 - (j2 - 1) * k2 - 10;
      
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      int iconOffsetX = 0;
      
      int ICON_WIDTH = 8;
      int ICON_HEIGHT = 8;
      int iconOffsetY = 248;
      for (int i = 0; i < beltCharges; i++) {
        drawTexturedModalRect(tx + i * 8, l2, 0, 248, 8, 8);
      }
    }
  }
  
  private void drawBrewInSlot(ItemBrewBag.InventoryBrewBag inv, int slot, byte[] strokes, int x, int y, int fx, int fy, int align) {
    ItemStack brew = inv.func_70301_a(slot);
    if ((brew != null) && (
      (strokes.length == 0) || (strokes[0] == com.emoniph.witchery.infusion.infusions.symbols.StrokeSet.Stroke.INDEX_TO_STROKE[slot]))) {
      drawItem(x, y, brew);
      String s = brew.func_82833_r();
      if (s != null) {
        s.trim();
        double fontX = x + fx;
        double fontY = y + fy;
        if (align != 0) {
          double width = getStringWidth(s);
          if (align == 1) {
            fontX -= width / 2.0D;
          } else if (align == 2) {
            fontX -= width;
          }
        }
        
        drawString(s, fontX, fontY, 2013265919);
      }
    }
  }
  
  private static FontRenderer getFontRenderer(ItemStack stack)
  {
    if ((stack != null) && (stack.func_77973_b() != null)) {
      FontRenderer f = stack.func_77973_b().getFontRenderer(stack);
      if (f != null)
        return f;
    }
    return func_71410_xfield_71466_p;
  }
  
  private static void drawItem(int i, int j, ItemStack itemstack) {
    drawItem(i, j, itemstack, getFontRenderer(itemstack));
  }
  
  private static RenderItem drawItems = new RenderItem();
  
  private static void drawItem(int i, int j, ItemStack itemstack, FontRenderer fontRenderer) {
    Minecraft mc = Minecraft.func_71410_x();
    GL11.glEnable(2896);
    GL11.glEnable(2929);
    drawItemsfield_77023_b += 100.0F;
    try {
      drawItems.func_82406_b(fontRenderer, field_71446_o, itemstack, i, j);
      drawItems.func_77021_b(fontRenderer, field_71446_o, itemstack, i, j);
    }
    catch (Exception e) {}
    drawItemsfield_77023_b -= 100.0F;
    GL11.glDisable(2896);
    GL11.glDisable(2929);
  }
  

  public static void drawString(String s, double x, double y, int color)
  {
    RenderHelper.func_74518_a();
    RenderUtil.blend(true);
    RenderUtil.render2d(true);
    
    func_71410_xfield_71466_p.func_78261_a(s, (int)x, (int)y, color);
    
    RenderUtil.render2d(false);
    RenderUtil.blend(false);
  }
  
  public static double getStringWidth(String s)
  {
    GL11.glPushAttrib(262144);
    double val;
    try { val = func_71410_xfield_71466_p.func_78256_a(s);
    } finally {
      GL11.glPopAttrib();
    }
    return val;
  }
  
  private int lastY = 0;
  
  private static final int[] glyphOffsetX = { 0, 0, 1, -1, 1, -1, -1, 1 };
  private static final int[] glyphOffsetY = { -1, 1, 0, 0, -1, 1, -1, 1 };
  
  private static final ResourceLocation TEXTURE_GRID = new ResourceLocation("witchery", "textures/gui/grid.png");
  
  private static void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
    double zLevel = 0.0D;
    float f = 0.00390625F;
    float f1 = 0.00390625F;
    Tessellator tessellator = Tessellator.field_78398_a;
    tessellator.func_78382_b();
    tessellator.func_78374_a(par1 + 0, par2 + par6, zLevel, (par3 + 0) * f, (par4 + par6) * f1);
    
    tessellator.func_78374_a(par1 + par5, par2 + par6, zLevel, (par3 + par5) * f, (par4 + par6) * f1);
    
    tessellator.func_78374_a(par1 + par5, par2 + 0, zLevel, (par3 + par5) * f, (par4 + 0) * f1);
    
    tessellator.func_78374_a(par1 + 0, par2 + 0, zLevel, (par3 + 0) * f, (par4 + 0) * f1);
    
    tessellator.func_78381_a();
  }
  
  private Field fieldAccess = null;
  
  @SubscribeEvent
  public void onClientTick(TickEvent.ClientTickEvent event)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 746	cpw/mods/fml/common/gameevent/TickEvent$ClientTickEvent:phase	Lcpw/mods/fml/common/gameevent/TickEvent$Phase;
    //   4: getstatic 90	cpw/mods/fml/common/gameevent/TickEvent$Phase:START	Lcpw/mods/fml/common/gameevent/TickEvent$Phase;
    //   7: if_acmpne +686 -> 693
    //   10: invokestatic 96	net/minecraft/client/Minecraft:func_71410_x	()Lnet/minecraft/client/Minecraft;
    //   13: astore_2
    //   14: aload_2
    //   15: getfield 100	net/minecraft/client/Minecraft:field_71439_g	Lnet/minecraft/client/entity/EntityClientPlayerMP;
    //   18: astore_3
    //   19: aload_3
    //   20: ifnull +670 -> 690
    //   23: iconst_1
    //   24: istore 4
    //   26: aload_3
    //   27: invokestatic 310	com/emoniph/witchery/infusion/infusions/creature/CreaturePower:getCreaturePowerID	(Lnet/minecraft/entity/player/EntityPlayer;)I
    //   30: istore 5
    //   32: iload 5
    //   34: ifle +43 -> 77
    //   37: invokestatic 749	com/emoniph/witchery/infusion/infusions/creature/CreaturePower$Registry:instance	()Lcom/emoniph/witchery/infusion/infusions/creature/CreaturePower$Registry;
    //   40: iload 5
    //   42: invokevirtual 752	com/emoniph/witchery/infusion/infusions/creature/CreaturePower$Registry:get	(I)Lcom/emoniph/witchery/infusion/infusions/creature/CreaturePower;
    //   45: astore 6
    //   47: aload 6
    //   49: ifnull +28 -> 77
    //   52: aload 6
    //   54: aload_3
    //   55: getfield 120	net/minecraft/client/entity/EntityClientPlayerMP:field_70170_p	Lnet/minecraft/world/World;
    //   58: aload_3
    //   59: invokevirtual 756	com/emoniph/witchery/infusion/infusions/creature/CreaturePower:onUpdate	(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;)V
    //   62: aload 6
    //   64: instanceof 758
    //   67: ifne +7 -> 74
    //   70: iconst_1
    //   71: goto +4 -> 75
    //   74: iconst_0
    //   75: istore 4
    //   77: aload_3
    //   78: invokevirtual 761	net/minecraft/client/entity/EntityClientPlayerMP:func_70694_bm	()Lnet/minecraft/item/ItemStack;
    //   81: ifnull +235 -> 316
    //   84: aload_3
    //   85: invokevirtual 761	net/minecraft/client/entity/EntityClientPlayerMP:func_70694_bm	()Lnet/minecraft/item/ItemStack;
    //   88: invokevirtual 327	net/minecraft/item/ItemStack:func_77973_b	()Lnet/minecraft/item/Item;
    //   91: ifnull +225 -> 316
    //   94: iload 4
    //   96: ifeq +220 -> 316
    //   99: aload_3
    //   100: invokevirtual 761	net/minecraft/client/entity/EntityClientPlayerMP:func_70694_bm	()Lnet/minecraft/item/ItemStack;
    //   103: invokevirtual 327	net/minecraft/item/ItemStack:func_77973_b	()Lnet/minecraft/item/Item;
    //   106: getstatic 331	com/emoniph/witchery/Witchery:Items	Lcom/emoniph/witchery/WitcheryItems;
    //   109: getfield 366	com/emoniph/witchery/WitcheryItems:MYSTIC_BRANCH	Lnet/minecraft/item/Item;
    //   112: if_acmpeq +19 -> 131
    //   115: aload_3
    //   116: invokevirtual 761	net/minecraft/client/entity/EntityClientPlayerMP:func_70694_bm	()Lnet/minecraft/item/ItemStack;
    //   119: invokevirtual 327	net/minecraft/item/ItemStack:func_77973_b	()Lnet/minecraft/item/Item;
    //   122: getstatic 331	com/emoniph/witchery/Witchery:Items	Lcom/emoniph/witchery/WitcheryItems;
    //   125: getfield 440	com/emoniph/witchery/WitcheryItems:BREW_BAG	Lnet/minecraft/item/Item;
    //   128: if_acmpne +188 -> 316
    //   131: aload_3
    //   132: invokevirtual 764	net/minecraft/client/entity/EntityClientPlayerMP:func_71039_bw	()Z
    //   135: ifeq +181 -> 316
    //   138: aload_3
    //   139: getfield 767	net/minecraft/client/entity/EntityClientPlayerMP:field_70159_w	D
    //   142: invokestatic 771	java/lang/Math:abs	(D)D
    //   145: ldc2_w 772
    //   148: dcmpg
    //   149: ifgt +21 -> 170
    //   152: aload_3
    //   153: getfield 776	net/minecraft/client/entity/EntityClientPlayerMP:field_70179_y	D
    //   156: invokestatic 771	java/lang/Math:abs	(D)D
    //   159: ldc2_w 772
    //   162: dcmpg
    //   163: ifgt +7 -> 170
    //   166: iconst_1
    //   167: goto +4 -> 171
    //   170: iconst_0
    //   171: istore 6
    //   173: aload_3
    //   174: getfield 120	net/minecraft/client/entity/EntityClientPlayerMP:field_70170_p	Lnet/minecraft/world/World;
    //   177: aload_3
    //   178: getfield 779	net/minecraft/client/entity/EntityClientPlayerMP:field_70165_t	D
    //   181: invokestatic 783	net/minecraft/util/MathHelper:func_76128_c	(D)I
    //   184: aload_3
    //   185: getfield 786	net/minecraft/client/entity/EntityClientPlayerMP:field_70163_u	D
    //   188: invokestatic 783	net/minecraft/util/MathHelper:func_76128_c	(D)I
    //   191: iconst_2
    //   192: isub
    //   193: aload_3
    //   194: getfield 789	net/minecraft/client/entity/EntityClientPlayerMP:field_70161_v	D
    //   197: invokestatic 783	net/minecraft/util/MathHelper:func_76128_c	(D)I
    //   200: invokevirtual 793	net/minecraft/world/World:func_147439_a	(III)Lnet/minecraft/block/Block;
    //   203: getstatic 799	net/minecraft/init/Blocks:field_150432_aD	Lnet/minecraft/block/Block;
    //   206: if_acmpeq +81 -> 287
    //   209: aload_3
    //   210: getfield 802	net/minecraft/client/entity/EntityClientPlayerMP:field_70122_E	Z
    //   213: ifeq +103 -> 316
    //   216: aload_3
    //   217: invokevirtual 805	net/minecraft/client/entity/EntityClientPlayerMP:func_70090_H	()Z
    //   220: ifne +35 -> 255
    //   223: iload 6
    //   225: ifeq +91 -> 316
    //   228: aload_3
    //   229: dup
    //   230: getfield 767	net/minecraft/client/entity/EntityClientPlayerMP:field_70159_w	D
    //   233: ldc2_w 806
    //   236: dmul
    //   237: putfield 767	net/minecraft/client/entity/EntityClientPlayerMP:field_70159_w	D
    //   240: aload_3
    //   241: dup
    //   242: getfield 776	net/minecraft/client/entity/EntityClientPlayerMP:field_70179_y	D
    //   245: ldc2_w 806
    //   248: dmul
    //   249: putfield 776	net/minecraft/client/entity/EntityClientPlayerMP:field_70179_y	D
    //   252: goto +64 -> 316
    //   255: iload 6
    //   257: ifeq +59 -> 316
    //   260: aload_3
    //   261: dup
    //   262: getfield 767	net/minecraft/client/entity/EntityClientPlayerMP:field_70159_w	D
    //   265: ldc2_w 808
    //   268: dmul
    //   269: putfield 767	net/minecraft/client/entity/EntityClientPlayerMP:field_70159_w	D
    //   272: aload_3
    //   273: dup
    //   274: getfield 776	net/minecraft/client/entity/EntityClientPlayerMP:field_70179_y	D
    //   277: ldc2_w 808
    //   280: dmul
    //   281: putfield 776	net/minecraft/client/entity/EntityClientPlayerMP:field_70179_y	D
    //   284: goto +32 -> 316
    //   287: iload 6
    //   289: ifeq +27 -> 316
    //   292: aload_3
    //   293: dup
    //   294: getfield 767	net/minecraft/client/entity/EntityClientPlayerMP:field_70159_w	D
    //   297: ldc2_w 808
    //   300: dmul
    //   301: putfield 767	net/minecraft/client/entity/EntityClientPlayerMP:field_70159_w	D
    //   304: aload_3
    //   305: dup
    //   306: getfield 776	net/minecraft/client/entity/EntityClientPlayerMP:field_70179_y	D
    //   309: ldc2_w 808
    //   312: dmul
    //   313: putfield 776	net/minecraft/client/entity/EntityClientPlayerMP:field_70179_y	D
    //   316: aload_3
    //   317: invokestatic 812	com/emoniph/witchery/infusion/Infusion:getSinkingCurseLevel	(Lnet/minecraft/entity/player/EntityPlayer;)I
    //   320: istore 6
    //   322: iload 6
    //   324: ifle +88 -> 412
    //   327: aload_3
    //   328: invokevirtual 805	net/minecraft/client/entity/EntityClientPlayerMP:func_70090_H	()Z
    //   331: ifeq +81 -> 412
    //   334: aload_3
    //   335: getfield 815	net/minecraft/client/entity/EntityClientPlayerMP:field_70181_x	D
    //   338: ldc2_w 816
    //   341: dcmpg
    //   342: ifge +41 -> 383
    //   345: aload_3
    //   346: getfield 802	net/minecraft/client/entity/EntityClientPlayerMP:field_70122_E	Z
    //   349: ifne +34 -> 383
    //   352: aload_3
    //   353: dup
    //   354: getfield 815	net/minecraft/client/entity/EntityClientPlayerMP:field_70181_x	D
    //   357: ldc2_w 818
    //   360: ldc2_w 820
    //   363: iload 6
    //   365: iconst_1
    //   366: isub
    //   367: i2d
    //   368: dmul
    //   369: ldc2_w 822
    //   372: invokestatic 826	java/lang/Math:min	(DD)D
    //   375: dadd
    //   376: dmul
    //   377: putfield 815	net/minecraft/client/entity/EntityClientPlayerMP:field_70181_x	D
    //   380: goto +147 -> 527
    //   383: aload_3
    //   384: getfield 802	net/minecraft/client/entity/EntityClientPlayerMP:field_70122_E	Z
    //   387: ifne +140 -> 527
    //   390: aload_3
    //   391: getstatic 832	net/minecraft/block/material/Material:field_151586_h	Lnet/minecraft/block/material/Material;
    //   394: invokevirtual 836	net/minecraft/client/entity/EntityClientPlayerMP:func_70055_a	(Lnet/minecraft/block/material/Material;)Z
    //   397: ifeq +130 -> 527
    //   400: aload_3
    //   401: getfield 815	net/minecraft/client/entity/EntityClientPlayerMP:field_70181_x	D
    //   404: dconst_0
    //   405: dcmpl
    //   406: ifle +121 -> 527
    //   409: goto +118 -> 527
    //   412: iload 6
    //   414: ifle +43 -> 457
    //   417: aload_3
    //   418: getfield 561	net/minecraft/client/entity/EntityClientPlayerMP:field_71075_bZ	Lnet/minecraft/entity/player/PlayerCapabilities;
    //   421: getfield 566	net/minecraft/entity/player/PlayerCapabilities:field_75098_d	Z
    //   424: ifne +103 -> 527
    //   427: aload_3
    //   428: getfield 561	net/minecraft/client/entity/EntityClientPlayerMP:field_71075_bZ	Lnet/minecraft/entity/player/PlayerCapabilities;
    //   431: getfield 839	net/minecraft/entity/player/PlayerCapabilities:field_75101_c	Z
    //   434: ifeq +93 -> 527
    //   437: aload_3
    //   438: getfield 561	net/minecraft/client/entity/EntityClientPlayerMP:field_71075_bZ	Lnet/minecraft/entity/player/PlayerCapabilities;
    //   441: getfield 842	net/minecraft/entity/player/PlayerCapabilities:field_75100_b	Z
    //   444: ifeq +83 -> 527
    //   447: aload_3
    //   448: ldc2_w 843
    //   451: putfield 815	net/minecraft/client/entity/EntityClientPlayerMP:field_70181_x	D
    //   454: goto +73 -> 527
    //   457: aload_3
    //   458: getstatic 849	net/minecraft/potion/Potion:field_76421_d	Lnet/minecraft/potion/Potion;
    //   461: invokevirtual 853	net/minecraft/client/entity/EntityClientPlayerMP:func_70644_a	(Lnet/minecraft/potion/Potion;)Z
    //   464: ifeq +63 -> 527
    //   467: aload_3
    //   468: getfield 561	net/minecraft/client/entity/EntityClientPlayerMP:field_71075_bZ	Lnet/minecraft/entity/player/PlayerCapabilities;
    //   471: getfield 566	net/minecraft/entity/player/PlayerCapabilities:field_75098_d	Z
    //   474: ifne +53 -> 527
    //   477: aload_3
    //   478: getfield 561	net/minecraft/client/entity/EntityClientPlayerMP:field_71075_bZ	Lnet/minecraft/entity/player/PlayerCapabilities;
    //   481: getfield 839	net/minecraft/entity/player/PlayerCapabilities:field_75101_c	Z
    //   484: ifeq +43 -> 527
    //   487: aload_3
    //   488: getfield 561	net/minecraft/client/entity/EntityClientPlayerMP:field_71075_bZ	Lnet/minecraft/entity/player/PlayerCapabilities;
    //   491: getfield 842	net/minecraft/entity/player/PlayerCapabilities:field_75100_b	Z
    //   494: ifeq +33 -> 527
    //   497: aload_3
    //   498: getstatic 849	net/minecraft/potion/Potion:field_76421_d	Lnet/minecraft/potion/Potion;
    //   501: invokevirtual 180	net/minecraft/client/entity/EntityClientPlayerMP:func_70660_b	(Lnet/minecraft/potion/Potion;)Lnet/minecraft/potion/PotionEffect;
    //   504: astore 7
    //   506: aload 7
    //   508: ifnull +19 -> 527
    //   511: aload 7
    //   513: invokevirtual 220	net/minecraft/potion/PotionEffect:func_76458_c	()I
    //   516: iconst_4
    //   517: if_icmple +10 -> 527
    //   520: aload_3
    //   521: ldc2_w 843
    //   524: putfield 815	net/minecraft/client/entity/EntityClientPlayerMP:field_70181_x	D
    //   527: iload 6
    //   529: ifne +59 -> 588
    //   532: aload_3
    //   533: invokestatic 859	com/emoniph/witchery/util/BlockUtil:getBlockMaterial	(Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/block/material/Material;
    //   536: invokevirtual 862	net/minecraft/block/material/Material:func_76224_d	()Z
    //   539: ifeq +49 -> 588
    //   542: aload_3
    //   543: iconst_0
    //   544: invokevirtual 865	net/minecraft/client/entity/EntityClientPlayerMP:func_82169_q	(I)Lnet/minecraft/item/ItemStack;
    //   547: ifnull +41 -> 588
    //   550: aload_3
    //   551: iconst_0
    //   552: invokevirtual 865	net/minecraft/client/entity/EntityClientPlayerMP:func_82169_q	(I)Lnet/minecraft/item/ItemStack;
    //   555: invokevirtual 327	net/minecraft/item/ItemStack:func_77973_b	()Lnet/minecraft/item/Item;
    //   558: getstatic 331	com/emoniph/witchery/Witchery:Items	Lcom/emoniph/witchery/WitcheryItems;
    //   561: getfield 869	com/emoniph/witchery/WitcheryItems:DEATH_FEET	Lcom/emoniph/witchery/item/ItemDeathsClothes;
    //   564: if_acmpne +24 -> 588
    //   567: aload_3
    //   568: getfield 815	net/minecraft/client/entity/EntityClientPlayerMP:field_70181_x	D
    //   571: dconst_0
    //   572: dcmpg
    //   573: ifge +15 -> 588
    //   576: aload_3
    //   577: dup
    //   578: getfield 815	net/minecraft/client/entity/EntityClientPlayerMP:field_70181_x	D
    //   581: ldc2_w 772
    //   584: dadd
    //   585: putfield 815	net/minecraft/client/entity/EntityClientPlayerMP:field_70181_x	D
    //   588: aload_3
    //   589: getfield 802	net/minecraft/client/entity/EntityClientPlayerMP:field_70122_E	Z
    //   592: ifeq +98 -> 690
    //   595: aload_2
    //   596: getfield 202	net/minecraft/client/Minecraft:field_71474_y	Lnet/minecraft/client/settings/GameSettings;
    //   599: getfield 873	net/minecraft/client/settings/GameSettings:field_74314_A	Lnet/minecraft/client/settings/KeyBinding;
    //   602: invokestatic 879	com/emoniph/witchery/util/KeyBindHelper:isKeyBindDown	(Lnet/minecraft/client/settings/KeyBinding;)Z
    //   605: ifeq +85 -> 690
    //   608: aload_3
    //   609: getfield 779	net/minecraft/client/entity/EntityClientPlayerMP:field_70165_t	D
    //   612: invokestatic 783	net/minecraft/util/MathHelper:func_76128_c	(D)I
    //   615: istore 7
    //   617: aload_3
    //   618: getfield 786	net/minecraft/client/entity/EntityClientPlayerMP:field_70163_u	D
    //   621: invokestatic 783	net/minecraft/util/MathHelper:func_76128_c	(D)I
    //   624: istore 8
    //   626: aload_3
    //   627: getfield 789	net/minecraft/client/entity/EntityClientPlayerMP:field_70161_v	D
    //   630: invokestatic 783	net/minecraft/util/MathHelper:func_76128_c	(D)I
    //   633: istore 9
    //   635: aload_3
    //   636: getfield 120	net/minecraft/client/entity/EntityClientPlayerMP:field_70170_p	Lnet/minecraft/world/World;
    //   639: iload 7
    //   641: iload 8
    //   643: iconst_1
    //   644: isub
    //   645: iload 9
    //   647: invokevirtual 793	net/minecraft/world/World:func_147439_a	(III)Lnet/minecraft/block/Block;
    //   650: getstatic 883	com/emoniph/witchery/Witchery:Blocks	Lcom/emoniph/witchery/WitcheryBlocks;
    //   653: getfield 888	com/emoniph/witchery/WitcheryBlocks:LEAPING_LILY	Lnet/minecraft/block/Block;
    //   656: if_acmpne +34 -> 690
    //   659: aload_3
    //   660: ldc_w 890
    //   663: fconst_1
    //   664: ldc_w 891
    //   667: aload_3
    //   668: getfield 120	net/minecraft/client/entity/EntityClientPlayerMP:field_70170_p	Lnet/minecraft/world/World;
    //   671: getfield 895	net/minecraft/world/World:field_73012_v	Ljava/util/Random;
    //   674: invokevirtual 900	java/util/Random:nextDouble	()D
    //   677: d2f
    //   678: ldc_w 891
    //   681: fmul
    //   682: ldc_w 901
    //   685: fadd
    //   686: fdiv
    //   687: invokevirtual 905	net/minecraft/client/entity/EntityClientPlayerMP:func_85030_a	(Ljava/lang/String;FF)V
    //   690: goto +263 -> 953
    //   693: aload_1
    //   694: getfield 746	cpw/mods/fml/common/gameevent/TickEvent$ClientTickEvent:phase	Lcpw/mods/fml/common/gameevent/TickEvent$Phase;
    //   697: getstatic 247	cpw/mods/fml/common/gameevent/TickEvent$Phase:END	Lcpw/mods/fml/common/gameevent/TickEvent$Phase;
    //   700: if_acmpne +253 -> 953
    //   703: invokestatic 96	net/minecraft/client/Minecraft:func_71410_x	()Lnet/minecraft/client/Minecraft;
    //   706: astore_2
    //   707: aload_2
    //   708: getfield 100	net/minecraft/client/Minecraft:field_71439_g	Lnet/minecraft/client/entity/EntityClientPlayerMP;
    //   711: astore_3
    //   712: aload_3
    //   713: ifnull +240 -> 953
    //   716: aload_2
    //   717: getfield 104	net/minecraft/client/Minecraft:field_71462_r	Lnet/minecraft/client/gui/GuiScreen;
    //   720: ifnull +233 -> 953
    //   723: aload_2
    //   724: getfield 104	net/minecraft/client/Minecraft:field_71462_r	Lnet/minecraft/client/gui/GuiScreen;
    //   727: instanceof 907
    //   730: ifeq +223 -> 953
    //   733: aload_3
    //   734: getfield 910	net/minecraft/client/entity/EntityClientPlayerMP:field_71093_bK	I
    //   737: invokestatic 275	com/emoniph/witchery/util/Config:instance	()Lcom/emoniph/witchery/util/Config;
    //   740: getfield 913	com/emoniph/witchery/util/Config:dimensionDreamID	I
    //   743: if_icmpne +210 -> 953
    //   746: aload_3
    //   747: getfield 561	net/minecraft/client/entity/EntityClientPlayerMP:field_71075_bZ	Lnet/minecraft/entity/player/PlayerCapabilities;
    //   750: getfield 566	net/minecraft/entity/player/PlayerCapabilities:field_75098_d	Z
    //   753: ifne +200 -> 953
    //   756: aload_0
    //   757: getfield 78	com/emoniph/witchery/client/PlayerRender:fieldAccess	Ljava/lang/reflect/Field;
    //   760: ifnonnull +137 -> 897
    //   763: ldc_w 915
    //   766: invokevirtual 921	java/lang/Class:getDeclaredFields	()[Ljava/lang/reflect/Field;
    //   769: astore 4
    //   771: aload 4
    //   773: arraylength
    //   774: iconst_3
    //   775: if_icmple +93 -> 868
    //   778: aload 4
    //   780: iconst_3
    //   781: aaload
    //   782: invokevirtual 927	java/lang/reflect/Field:getType	()Ljava/lang/Class;
    //   785: ldc -128
    //   787: if_acmpne +24 -> 811
    //   790: aload 4
    //   792: iconst_3
    //   793: aaload
    //   794: astore 5
    //   796: aload 5
    //   798: iconst_1
    //   799: invokevirtual 930	java/lang/reflect/Field:setAccessible	(Z)V
    //   802: aload_0
    //   803: aload 5
    //   805: putfield 78	com/emoniph/witchery/client/PlayerRender:fieldAccess	Ljava/lang/reflect/Field;
    //   808: goto +60 -> 868
    //   811: aload 4
    //   813: astore 5
    //   815: aload 5
    //   817: arraylength
    //   818: istore 6
    //   820: iconst_0
    //   821: istore 7
    //   823: iload 7
    //   825: iload 6
    //   827: if_icmpge +41 -> 868
    //   830: aload 5
    //   832: iload 7
    //   834: aaload
    //   835: astore 8
    //   837: aload 8
    //   839: invokevirtual 927	java/lang/reflect/Field:getType	()Ljava/lang/Class;
    //   842: ldc -128
    //   844: if_acmpne +18 -> 862
    //   847: aload 8
    //   849: iconst_1
    //   850: invokevirtual 930	java/lang/reflect/Field:setAccessible	(Z)V
    //   853: aload_0
    //   854: aload 8
    //   856: putfield 78	com/emoniph/witchery/client/PlayerRender:fieldAccess	Ljava/lang/reflect/Field;
    //   859: goto +9 -> 868
    //   862: iinc 7 1
    //   865: goto -42 -> 823
    //   868: goto +29 -> 897
    //   871: astore 4
    //   873: invokestatic 937	com/emoniph/witchery/util/Log:instance	()Lcom/emoniph/witchery/util/Log;
    //   876: ldc_w 939
    //   879: iconst_1
    //   880: anewarray 4	java/lang/Object
    //   883: dup
    //   884: iconst_0
    //   885: aload 4
    //   887: invokevirtual 942	java/lang/Exception:toString	()Ljava/lang/String;
    //   890: aastore
    //   891: invokestatic 946	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   894: invokevirtual 950	com/emoniph/witchery/util/Log:debug	(Ljava/lang/String;)V
    //   897: aload_0
    //   898: getfield 78	com/emoniph/witchery/client/PlayerRender:fieldAccess	Ljava/lang/reflect/Field;
    //   901: ifnull +52 -> 953
    //   904: aload_0
    //   905: getfield 78	com/emoniph/witchery/client/PlayerRender:fieldAccess	Ljava/lang/reflect/Field;
    //   908: aload_2
    //   909: getfield 104	net/minecraft/client/Minecraft:field_71462_r	Lnet/minecraft/client/gui/GuiScreen;
    //   912: invokevirtual 953	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   915: checkcast 128	java/util/List
    //   918: astore 4
    //   920: aload 4
    //   922: invokeinterface 955 1 0
    //   927: ifle +10 -> 937
    //   930: aload 4
    //   932: invokeinterface 958 1 0
    //   937: goto +16 -> 953
    //   940: astore 4
    //   942: invokestatic 937	com/emoniph/witchery/util/Log:instance	()Lcom/emoniph/witchery/util/Log;
    //   945: aload 4
    //   947: ldc_w 960
    //   950: invokevirtual 964	com/emoniph/witchery/util/Log:warning	(Ljava/lang/Throwable;Ljava/lang/String;)V
    //   953: return
    // Line number table:
    //   Java source line #433	-> byte code offset #0
    //   Java source line #434	-> byte code offset #10
    //   Java source line #435	-> byte code offset #14
    //   Java source line #436	-> byte code offset #19
    //   Java source line #438	-> byte code offset #23
    //   Java source line #439	-> byte code offset #26
    //   Java source line #440	-> byte code offset #32
    //   Java source line #441	-> byte code offset #37
    //   Java source line #442	-> byte code offset #47
    //   Java source line #443	-> byte code offset #52
    //   Java source line #444	-> byte code offset #62
    //   Java source line #448	-> byte code offset #77
    //   Java source line #449	-> byte code offset #138
    //   Java source line #450	-> byte code offset #173
    //   Java source line #451	-> byte code offset #209
    //   Java source line #452	-> byte code offset #216
    //   Java source line #453	-> byte code offset #223
    //   Java source line #454	-> byte code offset #228
    //   Java source line #455	-> byte code offset #240
    //   Java source line #458	-> byte code offset #255
    //   Java source line #459	-> byte code offset #260
    //   Java source line #460	-> byte code offset #272
    //   Java source line #465	-> byte code offset #287
    //   Java source line #466	-> byte code offset #292
    //   Java source line #467	-> byte code offset #304
    //   Java source line #472	-> byte code offset #316
    //   Java source line #473	-> byte code offset #322
    //   Java source line #474	-> byte code offset #334
    //   Java source line #475	-> byte code offset #352
    //   Java source line #476	-> byte code offset #383
    //   Java source line #479	-> byte code offset #412
    //   Java source line #480	-> byte code offset #417
    //   Java source line #481	-> byte code offset #447
    //   Java source line #485	-> byte code offset #457
    //   Java source line #486	-> byte code offset #467
    //   Java source line #487	-> byte code offset #497
    //   Java source line #488	-> byte code offset #506
    //   Java source line #489	-> byte code offset #520
    //   Java source line #495	-> byte code offset #527
    //   Java source line #496	-> byte code offset #567
    //   Java source line #497	-> byte code offset #576
    //   Java source line #502	-> byte code offset #588
    //   Java source line #503	-> byte code offset #608
    //   Java source line #504	-> byte code offset #617
    //   Java source line #505	-> byte code offset #626
    //   Java source line #506	-> byte code offset #635
    //   Java source line #507	-> byte code offset #659
    //   Java source line #511	-> byte code offset #690
    //   Java source line #512	-> byte code offset #703
    //   Java source line #513	-> byte code offset #707
    //   Java source line #514	-> byte code offset #712
    //   Java source line #515	-> byte code offset #716
    //   Java source line #516	-> byte code offset #733
    //   Java source line #517	-> byte code offset #756
    //   Java source line #519	-> byte code offset #763
    //   Java source line #520	-> byte code offset #771
    //   Java source line #521	-> byte code offset #778
    //   Java source line #522	-> byte code offset #790
    //   Java source line #523	-> byte code offset #796
    //   Java source line #524	-> byte code offset #802
    //   Java source line #525	-> byte code offset #808
    //   Java source line #526	-> byte code offset #811
    //   Java source line #527	-> byte code offset #837
    //   Java source line #528	-> byte code offset #847
    //   Java source line #529	-> byte code offset #853
    //   Java source line #530	-> byte code offset #859
    //   Java source line #526	-> byte code offset #862
    //   Java source line #537	-> byte code offset #868
    //   Java source line #535	-> byte code offset #871
    //   Java source line #536	-> byte code offset #873
    //   Java source line #539	-> byte code offset #897
    //   Java source line #541	-> byte code offset #904
    //   Java source line #542	-> byte code offset #920
    //   Java source line #543	-> byte code offset #930
    //   Java source line #547	-> byte code offset #937
    //   Java source line #545	-> byte code offset #940
    //   Java source line #546	-> byte code offset #942
    //   Java source line #556	-> byte code offset #953
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	954	0	this	PlayerRender
    //   0	954	1	event	TickEvent.ClientTickEvent
    //   13	583	2	minecraft	Minecraft
    //   706	203	2	minecraft	Minecraft
    //   18	650	3	player	EntityClientPlayerMP
    //   711	36	3	player	EntityClientPlayerMP
    //   24	71	4	allowSpeedUp	boolean
    //   769	43	4	fields	Field[]
    //   871	15	4	e	Exception
    //   918	13	4	list	List
    //   940	6	4	e	IllegalAccessException
    //   30	11	5	creaturePowerID	int
    //   794	10	5	field	Field
    //   45	18	6	power	CreaturePower
    //   171	117	6	canGo	boolean
    //   320	208	6	sinkingCurseLevel	int
    //   504	8	7	effect	PotionEffect
    //   615	25	7	x	int
    //   624	18	8	y	int
    //   835	20	8	field	Field
    //   633	13	9	z	int
    // Exception table:
    //   from	to	target	type
    //   763	868	871	java/lang/Exception
    //   904	937	940	java/lang/IllegalAccessException
  }
}
