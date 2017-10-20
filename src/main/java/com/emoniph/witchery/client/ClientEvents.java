package com.emoniph.witchery.client;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.client.model.ModelGrotesque;
import com.emoniph.witchery.client.renderer.RenderOtherPlayer;
import com.emoniph.witchery.client.renderer.RenderVillagerBed;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.common.ExtendedPlayer.VampirePower;
import com.emoniph.witchery.common.ExtendedVillager;
import com.emoniph.witchery.common.Shapeshift;
import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
import com.emoniph.witchery.entity.EntityVillageGuard;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.infusion.infusions.InfusionOtherwhere;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.RenderUtil;
import com.emoniph.witchery.util.TransformCreature;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent.Post;
import net.minecraftforge.client.event.RenderLivingEvent.Pre;
import net.minecraftforge.client.event.RenderLivingEvent.Specials.Pre;
import net.minecraftforge.client.event.RenderPlayerEvent.SetArmorModel;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import org.lwjgl.opengl.GL11;

@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
public class ClientEvents
{
  public ClientEvents() {}
  
  public static class GUIOverlay extends GuiIngame
  {
    public static final GUIOverlay INSTANCE = new GUIOverlay();
    
    private static final ResourceLocation WIDGITS = new ResourceLocation("textures/gui/widgets.png");
    private static final ResourceLocation TEETH = new ResourceLocation("witchery", "textures/items/vteeth.png");
    
    private static final ResourceLocation EYE = new ResourceLocation("witchery", "textures/items/veye.png");
    
    private static final ResourceLocation BAT = new ResourceLocation("witchery", "textures/items/vbat.png");
    
    private static final ResourceLocation RUN = new ResourceLocation("witchery", "textures/items/vspeed.png");
    
    private static final ResourceLocation FIST = new ResourceLocation("witchery", "textures/items/vfist.png");
    
    private static final ResourceLocation STORM = new ResourceLocation("witchery", "textures/items/vstorm.png");
    
    private static final ResourceLocation COFFIN = new ResourceLocation("witchery", "textures/items/vcoffin.png");
    private static final int WHITE = 16777215;
    
    public GUIOverlay() {
      super();
    }
    
    public void renderHotbar(RenderGameOverlayEvent.Pre event) {
      ExtendedPlayer playerEx = ExtendedPlayer.get(field_73839_d.field_71439_g);
      if (playerEx.isVampire()) {
        int width = resolution.func_78326_a();
        int height = resolution.func_78328_b();
        field_73839_d.field_71424_I.func_76320_a("actionBar");
        
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        field_73839_d.field_71446_o.func_110577_a(WIDGITS);
        
        InventoryPlayer inv = field_73839_d.field_71439_g.field_71071_by;
        func_73729_b(width / 2 - 91, height - 22, 0, 0, 182, 22);
        int vpower = playerEx.getSelectedVampirePower().ordinal();
        if (vpower != 0) {
          func_73729_b(width / 2 - 91 - 1 + -vpower * 20, height - 22 - 1, 0, 22, 24, 22);
        } else {
          func_73729_b(width / 2 - 91 - 1 + field_70461_c * 20, height - 22 - 1, 0, 22, 24, 22);
        }
        

        GL11.glDisable(3042);
        GL11.glEnable(32826);
        RenderHelper.func_74520_c();
        
        field_73735_i += 50.0F;
        int x = width / 2 - 90 + -20 + 2;
        int z = height - 16 - 3;
        
        if (playerEx.getVampireLevel() >= 1) {
          GL11.glEnable(3042);
          GL11.glBlendFunc(770, 771);
          
          field_73839_d.field_71446_o.func_110577_a(TEETH);
          func_146110_a(x, z, 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
          
          if (playerEx.getVampireLevel() >= 2) {
            x = width / 2 - 90 + -40 + 2;
            
            field_73839_d.field_71446_o.func_110577_a(EYE);
            func_146110_a(x, z, 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
            
            if (playerEx.getVampireLevel() >= 4) {
              x = width / 2 - 90 + -60 + 2;
              field_73839_d.field_71446_o.func_110577_a(RUN);
              func_146110_a(x, z, 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
              
              if (playerEx.getVampireLevel() >= 7) {
                x = width / 2 - 90 + -80 + 2;
                field_73839_d.field_71446_o.func_110577_a(BAT);
                func_146110_a(x, z, 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
                
                if (playerEx.getVampireLevel() >= 10) {
                  x = width / 2 - 90 + -100 + 2;
                  switch (ClientEvents.1.$SwitchMap$com$emoniph$witchery$common$ExtendedPlayer$VampireUltimate[playerEx.getVampireUltimate().ordinal()]) {
                  case 1: 
                    field_73839_d.field_71446_o.func_110577_a(COFFIN);
                    break;
                  case 2: 
                    field_73839_d.field_71446_o.func_110577_a(STORM);
                    break;
                  case 3: 
                  default: 
                    field_73839_d.field_71446_o.func_110577_a(FIST);
                  }
                  
                  if ((playerEx.getVampireUltimateCharges() == 0) || (playerEx.getVampireUltimate() == com.emoniph.witchery.common.ExtendedPlayer.VampireUltimate.NONE))
                  {

                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.2F);
                    func_146110_a(x, z, 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
                  }
                  else {
                    func_146110_a(x, z, 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
                  }
                }
              }
            }
          }
          

          GL11.glDisable(3042);
        }
        
        field_73735_i -= 50.0F;
        
        for (int i = 0; i < 9; i++) {
          x = width / 2 - 90 + i * 20 + 2;
          z = height - 16 - 3;
          func_73832_a(i, x, z, partialTicks);
        }
        
        RenderHelper.func_74518_a();
        GL11.glDisable(32826);
        field_73839_d.field_71424_I.func_76319_b();
        
        renderToolHightlight(playerEx, width, height);
        
        event.setCanceled(true);
      }
    }
    

    protected void renderToolHightlight(ExtendedPlayer playerEx, int width, int height)
    {
      Minecraft mc = Minecraft.func_71410_x();
      if (field_71474_y.field_92117_D) {
        if (--highlightTicks > 0)
        {


          String name = "";
          switch (ClientEvents.1.$SwitchMap$com$emoniph$witchery$common$ExtendedPlayer$VampirePower[playerEx.getSelectedVampirePower().ordinal()]) {
          case 1: 
            name = Witchery.resource("witchery.vampirepower.feed");
            break;
          case 2: 
            name = Witchery.resource("witchery.vampirepower.eye");
            break;
          case 3: 
            name = Witchery.resource("witchery.vampirepower.speed");
            break;
          case 4: 
            name = Witchery.resource("witchery.vampirepower.bat");
            break;
          case 5: 
            switch (ClientEvents.1.$SwitchMap$com$emoniph$witchery$common$ExtendedPlayer$VampireUltimate[playerEx.getVampireUltimate().ordinal()]) {
            case 4: 
              name = String.format(Witchery.resource("witchery.vampirepower.unone"), new Object[] { Integer.valueOf(playerEx.getVampireUltimateCharges()) });
              
              break;
            case 1: 
              name = String.format(Witchery.resource("witchery.vampirepower.uteleport"), new Object[] { Integer.valueOf(playerEx.getVampireUltimateCharges()) });
              
              break;
            case 3: 
              name = String.format(Witchery.resource("witchery.vampirepower.ubats"), new Object[] { Integer.valueOf(playerEx.getVampireUltimateCharges()) });
              
              break;
            case 2: 
              name = String.format(Witchery.resource("witchery.vampirepower.ustorm"), new Object[] { Integer.valueOf(playerEx.getVampireUltimateCharges()) });
            }
            
            

            break;
          }
          
          

          if (name.equals("")) {
            return;
          }
          
          int opacity = (int)(highlightTicks * 256.0F / 10.0F);
          if (opacity > 255) {
            opacity = 255;
          }
          if (opacity > 0) {
            int y = height - 69;
            if (!field_71442_b.func_78755_b()) {
              y += 14;
            }
            GL11.glPushMatrix();
            GL11.glEnable(3042);
            OpenGlHelper.func_148821_a(770, 771, 1, 0);
            FontRenderer font = RenderManager.field_78727_a.func_78716_a();
            

            if (font != null) {
              int x = (width - font.func_78256_a(name)) / 2;
              font.func_78261_a(name, x, y, 0xFFFFFF | opacity << 24);
            }
            GL11.glDisable(3042);
            GL11.glPopMatrix();
          }
        }
      }
    }
    
    protected void renderExtraInventorySlot(ItemStack itemstack, int p_73832_2_, int p_73832_3_, float p_73832_4_)
    {
      if (itemstack != null) {
        float f1 = field_77992_b - p_73832_4_;
        
        if (f1 > 0.0F) {
          GL11.glPushMatrix();
          float f2 = 1.0F + f1 / 5.0F;
          GL11.glTranslatef(p_73832_2_ + 8, p_73832_3_ + 12, 0.0F);
          GL11.glScalef(1.0F / f2, (f2 + 1.0F) / 2.0F, 1.0F);
          GL11.glTranslatef(-(p_73832_2_ + 8), -(p_73832_3_ + 12), 0.0F);
        }
        
        field_73841_b.func_82406_b(field_73839_d.field_71466_p, field_73839_d.func_110434_K(), itemstack, p_73832_2_, p_73832_3_);
        

        if (f1 > 0.0F) {
          GL11.glPopMatrix();
        }
        
        field_73841_b.func_77021_b(field_73839_d.field_71466_p, field_73839_d.func_110434_K(), itemstack, p_73832_2_, p_73832_3_);
      }
    }
  }
  
  @SubscribeEvent
  public void onMouseEvent(MouseEvent event)
  {
    Minecraft mc = Minecraft.func_71410_x();
    ExtendedPlayer playerEx = ExtendedPlayer.get(field_71439_g);
    if ((playerEx.isVampire()) && 
      (dwheel != 0)) {
      int MAXPOWER = playerEx.getMaxAvailablePowerOrdinal();
      if (field_71439_g.field_71071_by.field_70461_c == 0) {
        int power = playerEx.getSelectedVampirePower().ordinal();
        if (dwheel > 0) {
          if (power == MAXPOWER) {
            playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.NONE, true);
          } else {
            playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.values()[(power + 1)], true);
            event.setCanceled(true);
          }
        }
        else if (power > 0) {
          playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.values()[(power - 1)], true);
          event.setCanceled(true);
        }
      }
      else if ((field_71439_g.field_71071_by.field_70461_c == 8) && 
        (dwheel < 0)) {
        playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.values()[MAXPOWER], true);
      }
    }
  }
  


  private static final ResourceLocation BLOODDROP_BG = new ResourceLocation("witchery:textures/gui/bdropfaded.png");
  
  private static final ResourceLocation BLOODDROP = new ResourceLocation("witchery:textures/gui/bdrop.png");
  
  @SubscribeEvent(priority=EventPriority.LOWEST)
  public void onRenderGameOverlay(RenderGameOverlayEvent.Pre event) {
    switch (type) {
    case HOTBAR: 
      GUIOverlay.INSTANCE.renderHotbar(event);
      break;
    case TEXT: 
      EntityPlayer player = func_71410_xfield_71439_g;
      ExtendedPlayer playerEx = ExtendedPlayer.get(player);
      if (playerEx.isVampire())
      {
        float left = instanceguiOnLeft ? 10.0F : resolution.func_78326_a() - 10;
        float top = resolution.func_78328_b() * 0.5F + 16.0F;
        int maxBlood = playerEx.getMaxBloodPower();
        
        int pscale = 250;
        func_71410_xfield_71446_o.func_110577_a(BLOODDROP_BG);
        for (int j = 0; j < maxBlood / 250; j++) {
          drawTexturedRect(left, top - j * 8, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F);
        }
        
        func_71410_xfield_71446_o.func_110577_a(BLOODDROP);
        int pblood = playerEx.getBloodPower();
        for (j = 0; j < pblood / 250; j++) {
          drawTexturedRect(left, top - j * 8, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F);
        }
        if (pblood % 250 > 0) {
          float remainder = 8.0F * (pblood % 250.0F) / 250.0F;
          drawTexturedRect(left, top - j * 8 + 8.0F - remainder, 0.0F, 8.0F - remainder, 8.0F, remainder, 8.0F, 8.0F);
        }
        

        MovingObjectPosition movingPosition = InfusionOtherwhere.raytraceEntities(func_71410_xfield_71441_e, func_71410_xfield_71439_g, true, 5.0D);
        
        if ((movingPosition != null) && (field_72308_g != null)) {
          int blood = -1;
          if ((field_72308_g instanceof EntityVillager)) {
            EntityVillager villager = (EntityVillager)field_72308_g;
            ExtendedVillager villagerEx = ExtendedVillager.get(villager);
            if (villagerEx.isClientSynced()) {
              blood = villagerEx.getBlood();
            }
          } else if ((field_72308_g instanceof EntityVillageGuard)) {
            EntityVillageGuard guard = (EntityVillageGuard)field_72308_g;
            blood = guard.getBlood();
          }
          else if ((field_72308_g instanceof EntityPlayer)) {
            EntityPlayer targetPlayer = (EntityPlayer)field_72308_g;
            ExtendedPlayer targetPlayerEx = ExtendedPlayer.get(targetPlayer);
            if (!targetPlayerEx.isVampire()) {
              blood = targetPlayerEx.getHumanBlood();
            }
          }
          
          if (blood >= 0) {
            int tscale = 25;
            int percent = (int)(blood / 500.0F * 100.0F);
            float midX = resolution.func_78326_a() / 2;
            float midY = resolution.func_78328_b() / 2;
            
            func_71410_xfield_71446_o.func_110577_a(BLOODDROP_BG);
            
            for (int i = 0; i < 4; i++) {
              drawTexturedRect(midX - 16.0F + i * 8, midY + 10.0F, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F);
            }
            
            func_71410_xfield_71446_o.func_110577_a(BLOODDROP);
            for (i = 0; i < percent / 25; i++) {
              drawTexturedRect(midX - 16.0F + i * 8, midY + 10.0F, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F);
            }
            
            if (percent % 25 > 0) {
              float remainder = 8.0F * (percent % 25.0F) / 25.0F;
              float scale = remainder / 8.0F;
              float offset = 8.0F - remainder;
              drawTexturedRect(midX - 16.0F + i * 8, midY + 10.0F + offset, 0.0F, offset, 8.0F, remainder, 8.0F, 8.0F);
            }
            if (instancehudShowVampireTargetBloodText) {
              float scale = 0.5F;
              GL11.glScalef(scale, scale, scale);
              String text = String.format("%d/%d", new Object[] { Integer.valueOf(blood), Integer.valueOf(500) });
              int width = RenderManager.field_78727_a.func_78716_a().func_78256_a(text);
              RenderManager.field_78727_a.func_78716_a().func_78276_b(text, (int)((resolution.func_78326_a() / 2 - width / 4) / scale), (int)((resolution.func_78328_b() / 2 + 22) / scale), 13369344);
              

              GL11.glScalef(1.0F / scale, 1.0F / scale, 1.0F / scale);
            }
          }
        } }
      break;
    }
    
  }
  


  private static void drawTexturedRect(float p_146110_0_, float p_146110_1_, float p_146110_2_, float p_146110_3_, float p_146110_4_, float p_146110_5_, float p_146110_6_, float p_146110_7_)
  {
    float f4 = 1.0F / p_146110_6_;
    float f5 = 1.0F / p_146110_7_;
    Tessellator tessellator = Tessellator.field_78398_a;
    tessellator.func_78382_b();
    tessellator.func_78374_a(p_146110_0_, p_146110_1_ + p_146110_5_, 0.0D, p_146110_2_ * f4, (p_146110_3_ + p_146110_5_) * f5);
    
    tessellator.func_78374_a(p_146110_0_ + p_146110_4_, p_146110_1_ + p_146110_5_, 0.0D, (p_146110_2_ + p_146110_4_) * f4, (p_146110_3_ + p_146110_5_) * f5);
    

    tessellator.func_78374_a(p_146110_0_ + p_146110_4_, p_146110_1_, 0.0D, (p_146110_2_ + p_146110_4_) * f4, p_146110_3_ * f5);
    
    tessellator.func_78374_a(p_146110_0_, p_146110_1_, 0.0D, p_146110_2_ * f4, p_146110_3_ * f5);
    
    tessellator.func_78381_a();
  }
  
  private static final ModelGrotesque DEMON_HEAD_MODEL = new ModelGrotesque();
  private static final ResourceLocation TEXTURE = new ResourceLocation("witchery", "textures/entities/Demon.png");
  
  @SubscribeEvent
  public void onSetArmorModel(RenderPlayerEvent.SetArmorModel event)
  {
    EntityPlayer player = entityPlayer;
    if ((!player.func_82150_aj()) && (Infusion.getNBT(player).func_74764_b("witcheryGrotesque"))) {
      GL11.glPushMatrix();
      Minecraft mc = Minecraft.func_71410_x();
      mc.func_110434_K().func_110577_a(TEXTURE);
      
      float par9 = partialRenderTick;
      float f6 = field_70127_C + (field_70125_A - field_70127_C) * par9;
      
      float f2 = interpolateRotation(field_70760_ar, field_70761_aq, par9);
      float f3 = interpolateRotation(field_70758_at, field_70759_as, par9);
      
      DEMON_HEAD_MODEL.func_78088_a(entityPlayer, 0.0F, 0.0F, 0.0F, f3 - f2, f6, 0.0625F);
      
      GL11.glPopMatrix();
    }
  }
  
  @SubscribeEvent
  public void onLivingJump(LivingEvent.LivingJumpEvent event) {
    if ((entity instanceof EntityPlayer)) {
      EntityPlayer player = (EntityPlayer)entity;
      Shapeshift.INSTANCE.updateJump(player);
    }
  }
  
  TransformWolf wolf = new TransformWolf();
  TransformWolfman wolfman = new TransformWolfman();
  TransformBat bat = new TransformBat();
  TransformOtherPlayer otherPlayer = new TransformOtherPlayer();
  
  RenderVillagerBed renderBed = new RenderVillagerBed();
  
  @SubscribeEvent(priority=EventPriority.HIGH)
  public void onPlayerPreRender(RenderLivingEvent.Pre event) {
    if ((entity instanceof EntityVillager)) {
      ExtendedVillager ext = ExtendedVillager.get((EntityVillager)entity);
      GL11.glPushMatrix();
      if ((ext != null) && (ext.isSleeping())) {
        GL11.glTranslated(x, y, z);
        
        renderBed.render((float)x, (float)y, (float)z, 0);
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslated(0.5D, -1.25D, 0.0D);
        
        entity.func_70034_d(90.0F);
        
        GL11.glTranslated(-x, -y, -z);
      }
    } else if ((entity instanceof EntityPlayer)) {
      EntityPlayer player = (EntityPlayer)entity;
      if (WorldProviderDreamWorld.getPlayerIsGhost(Infusion.getNBT(player))) {
        RenderUtil.blend(true);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.51F);
      }
      
      ExtendedPlayer playerEx = ExtendedPlayer.get(player);
      int creatureType = playerEx.getCreatureTypeOrdinal();
      if ((creatureType > 0) && (!(renderer instanceof RenderOtherPlayer))) {
        event.setCanceled(true);
        
        PotionEffect pe = player.func_70660_b(PotionsRESIZING);
        if (pe != null) {
          GL11.glPushMatrix();
          GL11.glTranslated(x, y, z);
          float scale = com.emoniph.witchery.brewing.potions.PotionResizing.getModifiedScaleFactor(player, pe.func_76458_c());
          
          GL11.glScalef(scale, scale, scale);
          GL11.glTranslated(-x, -y, -z);
        }
        
        boolean gui = (field_70759_as == field_70177_z) && (field_70758_at == field_70177_z) && (field_78727_afield_78735_i == 180.0F) && (func_71410_xfield_71462_r != null);
        


        float partialTicks = gui ? 0.0F : com.emoniph.witchery.brewing.potions.ModelOverlayRenderer.getRenderPartialTicks();
        
        if (creatureType == 1)
        {
          wolf.render(entity.field_70170_p, entity, x, y, z, renderer, partialTicks, gui);
        }
        else if (creatureType == 2)
        {
          wolfman.render(entity.field_70170_p, entity, x, y, z, renderer, partialTicks, gui);
        }
        else if (creatureType == 3) {
          bat.render(entity.field_70170_p, entity, x, y, z, renderer, partialTicks, gui);
        }
        else if ((creatureType == 4) && (playerEx.getOtherPlayerSkin() != null) && (!playerEx.getOtherPlayerSkin().equals("")))
        {
          otherPlayer.render(entity.field_70170_p, entity, x, y, z, renderer, partialTicks, gui);
        }
        

        if (pe != null) {
          GL11.glPopMatrix();
        }
      }
    }
  }
  
  @SubscribeEvent
  public void onRenderLivingSpecialsPre(RenderLivingEvent.Specials.Pre event) {
    if ((!event.isCanceled()) && 
      (instanceallowNameplateMasquerading) && ((entity instanceof EntityPlayer))) {
      EntityPlayer player = (EntityPlayer)entity;
      ExtendedPlayer playerEx = ExtendedPlayer.get(player);
      if (playerEx.getCreatureType() == TransformCreature.PLAYER) {
        event.setCanceled(true);
        
        GL11.glAlphaFunc(516, 0.1F);
        
        EntityLivingBase p_77033_1_ = entity;
        double p_77033_2_ = x;double p_77033_4_ = y;double p_77033_6_ = z;
        
        RenderManager renderManager = RenderManager.field_78727_a;
        
        if ((Minecraft.func_71382_s()) && (p_77033_1_ != field_78734_h) && (!p_77033_1_.func_98034_c(func_71410_xfield_71439_g)) && (field_70153_n == null))
        {

          float f = 1.6F;
          float f1 = 0.016666668F * f;
          double d3 = p_77033_1_.func_70068_e(field_78734_h);
          float f2 = p_77033_1_.func_70093_af() ? 32.0F : 64.0F;
          
          if (d3 < f2 * f2) {
            String skin = playerEx.getOtherPlayerSkin();
            if ((skin == null) || (skin.isEmpty())) {
              return;
            }
            String s = new ChatComponentText(skin).func_150254_d();
            
            if (p_77033_1_.func_70093_af()) {
              FontRenderer fontrenderer = renderManager.func_78716_a();
              GL11.glPushMatrix();
              GL11.glTranslatef((float)p_77033_2_ + 0.0F, (float)p_77033_4_ + field_70131_O + 0.5F, (float)p_77033_6_);
              
              GL11.glNormal3f(0.0F, 1.0F, 0.0F);
              GL11.glRotatef(-field_78735_i, 0.0F, 1.0F, 0.0F);
              GL11.glRotatef(field_78732_j, 1.0F, 0.0F, 0.0F);
              GL11.glScalef(-f1, -f1, f1);
              GL11.glDisable(2896);
              GL11.glTranslatef(0.0F, 0.25F / f1, 0.0F);
              GL11.glDepthMask(false);
              GL11.glEnable(3042);
              OpenGlHelper.func_148821_a(770, 771, 1, 0);
              Tessellator tessellator = Tessellator.field_78398_a;
              GL11.glDisable(3553);
              tessellator.func_78382_b();
              int i = fontrenderer.func_78256_a(s) / 2;
              tessellator.func_78369_a(0.0F, 0.0F, 0.0F, 0.25F);
              tessellator.func_78377_a(-i - 1, -1.0D, 0.0D);
              tessellator.func_78377_a(-i - 1, 8.0D, 0.0D);
              tessellator.func_78377_a(i + 1, 8.0D, 0.0D);
              tessellator.func_78377_a(i + 1, -1.0D, 0.0D);
              tessellator.func_78381_a();
              GL11.glEnable(3553);
              GL11.glDepthMask(true);
              fontrenderer.func_78276_b(s, -fontrenderer.func_78256_a(s) / 2, 0, 553648127);
              GL11.glEnable(2896);
              GL11.glDisable(3042);
              GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
              GL11.glPopMatrix();
            } else {
              func_96449_a(p_77033_1_, p_77033_2_, p_77033_4_, p_77033_6_, s, f1, d3);
            }
          }
        }
      }
    }
  }
  


  protected void func_96449_a(EntityLivingBase p_96449_1_, double p_96449_2_, double p_96449_4_, double p_96449_6_, String p_96449_8_, float p_96449_9_, double p_96449_10_)
  {
    if (!p_96449_1_.func_70608_bn())
    {
      func_147906_a(p_96449_1_, p_96449_8_, p_96449_2_, p_96449_4_, p_96449_6_, 64);
    }
  }
  
  protected void func_147906_a(Entity p_147906_1_, String p_147906_2_, double p_147906_3_, double p_147906_5_, double p_147906_7_, int p_147906_9_)
  {
    RenderManager renderManager = RenderManager.field_78727_a;
    
    double d3 = p_147906_1_.func_70068_e(field_78734_h);
    
    if (d3 <= p_147906_9_ * p_147906_9_) {
      FontRenderer fontrenderer = renderManager.func_78716_a();
      float f = 1.6F;
      float f1 = 0.016666668F * f;
      GL11.glPushMatrix();
      GL11.glTranslatef((float)p_147906_3_ + 0.0F, (float)p_147906_5_ + field_70131_O + 0.5F, (float)p_147906_7_);
      
      GL11.glNormal3f(0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-field_78735_i, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(field_78732_j, 1.0F, 0.0F, 0.0F);
      GL11.glScalef(-f1, -f1, f1);
      GL11.glDisable(2896);
      GL11.glDepthMask(false);
      GL11.glDisable(2929);
      GL11.glEnable(3042);
      OpenGlHelper.func_148821_a(770, 771, 1, 0);
      Tessellator tessellator = Tessellator.field_78398_a;
      byte b0 = 0;
      
      if (p_147906_2_.equals("deadmau5")) {
        b0 = -10;
      }
      
      GL11.glDisable(3553);
      tessellator.func_78382_b();
      int j = fontrenderer.func_78256_a(p_147906_2_) / 2;
      tessellator.func_78369_a(0.0F, 0.0F, 0.0F, 0.25F);
      tessellator.func_78377_a(-j - 1, -1 + b0, 0.0D);
      tessellator.func_78377_a(-j - 1, 8 + b0, 0.0D);
      tessellator.func_78377_a(j + 1, 8 + b0, 0.0D);
      tessellator.func_78377_a(j + 1, -1 + b0, 0.0D);
      tessellator.func_78381_a();
      GL11.glEnable(3553);
      fontrenderer.func_78276_b(p_147906_2_, -fontrenderer.func_78256_a(p_147906_2_) / 2, b0, 553648127);
      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      fontrenderer.func_78276_b(p_147906_2_, -fontrenderer.func_78256_a(p_147906_2_) / 2, b0, -1);
      GL11.glEnable(2896);
      GL11.glDisable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glPopMatrix();
    }
  }
  
  @SubscribeEvent
  public void onPlayerPostRender(RenderLivingEvent.Post event) {
    if ((entity instanceof EntityVillager)) {
      GL11.glPopMatrix();
    } else if ((entity instanceof EntityPlayer)) {
      EntityPlayer player = (EntityPlayer)entity;
      if (WorldProviderDreamWorld.getPlayerIsGhost(Infusion.getNBT(player))) {
        RenderUtil.blend(false);
      }
    }
  }
  
  @SubscribeEvent
  public void onRenderHand(RenderHandEvent event) {
    Minecraft mc = Minecraft.func_71410_x();
    
    ExtendedPlayer playerEx = ExtendedPlayer.get(field_71439_g);
    TransformCreature creatureType = playerEx.getCreatureType();
    if ((creatureType != TransformCreature.NONE) && ((field_71439_g.func_70694_bm() == null) || ((creatureType != TransformCreature.WOLFMAN) && (creatureType != TransformCreature.PLAYER))))
    {
      event.setCanceled(true);
      renderArm(renderPass, partialTicks, mc, field_71439_g.func_70694_bm() != null, creatureType, playerEx);
    }
  }
  
  public void renderArm(int renderPass, float partialTicks, Minecraft mc, boolean hasItem, TransformCreature creatureType, ExtendedPlayer playerEx)
  {
    GL11.glClear(256);
    
    float farPlaneDistance = field_71474_y.field_151451_c * 16;
    double cameraZoom = 1.0D;
    double cameraYaw = 0.0D;
    double cameraPitch = 0.0D;
    
    if (field_71460_t.field_78532_q <= 0) {
      GL11.glMatrixMode(5889);
      GL11.glLoadIdentity();
      float f1 = 0.07F;
      
      if (field_71474_y.field_74337_g) {
        GL11.glTranslatef(-(renderPass * 2 - 1) * f1, 0.0F, 0.0F);
      }
      
      if (cameraZoom != 1.0D) {
        GL11.glTranslatef((float)cameraYaw, (float)-cameraPitch, 0.0F);
        GL11.glScaled(cameraZoom, cameraZoom, 1.0D);
      }
      
      org.lwjgl.util.glu.Project.gluPerspective(getFOVModifier(partialTicks, field_71460_t, mc), field_71443_c / field_71440_d, 0.05F, farPlaneDistance * 2.0F);
      

      if (field_71442_b.func_78747_a()) {
        float f2 = 0.6666667F;
        GL11.glScalef(1.0F, f2, 1.0F);
      }
      
      GL11.glMatrixMode(5888);
      GL11.glLoadIdentity();
      
      if (field_71474_y.field_74337_g) {
        GL11.glTranslatef((renderPass * 2 - 1) * 0.1F, 0.0F, 0.0F);
      }
      
      GL11.glPushMatrix();
      hurtCameraEffect(partialTicks, mc);
      
      if (field_71474_y.field_74336_f) {
        setupViewBobbing(partialTicks, mc);
      }
      
      if ((field_71474_y.field_74320_O == 0) && (!field_71451_h.func_70608_bn()) && (!field_71474_y.field_74319_N) && (!field_71442_b.func_78747_a()))
      {
        field_71460_t.func_78463_b(partialTicks);
        if ((hasItem) && ((creatureType == TransformCreature.WOLF) || (creatureType == TransformCreature.BAT))) {
          if (field_71439_g.func_71052_bv() == 0) {
            GL11.glTranslatef(-0.4F, 0.1F, 0.0F);
            GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
          }
          field_71460_t.field_78516_c.func_78440_a(partialTicks);
        }
        else if ((creatureType == TransformCreature.WOLF) || (creatureType == TransformCreature.PLAYER) || (creatureType == TransformCreature.WOLFMAN)) {
          renderEmptyHand(mc, partialTicks, creatureType, playerEx);
        }
        

        field_71460_t.func_78483_a(partialTicks);
      }
      
      GL11.glPopMatrix();
      
      if ((field_71474_y.field_74320_O == 0) && (!field_71451_h.func_70608_bn())) {
        field_71460_t.field_78516_c.func_78447_b(partialTicks);
        hurtCameraEffect(partialTicks, mc);
      }
      
      if (field_71474_y.field_74336_f) {
        setupViewBobbing(partialTicks, mc);
      }
    }
  }
  
  private static final ResourceLocation wolfSkin = new ResourceLocation("witchery", "textures/entities/werewolf_man.png");
  
  private void renderEmptyHand(Minecraft mc, float p_78440_1_, TransformCreature creatureType, ExtendedPlayer playerEx)
  {
    float f1 = 1.0F;
    

    EntityClientPlayerMP entityclientplayermp = field_71439_g;
    float f2 = field_70127_C + (field_70125_A - field_70127_C) * p_78440_1_;
    
    GL11.glPushMatrix();
    GL11.glRotatef(f2, 1.0F, 0.0F, 0.0F);
    GL11.glRotatef(field_70126_B + (field_70177_z - field_70126_B) * p_78440_1_, 0.0F, 1.0F, 0.0F);
    

    RenderHelper.func_74519_b();
    GL11.glPopMatrix();
    EntityPlayerSP entityplayersp = entityclientplayermp;
    float f3 = field_71164_i + (field_71155_g - field_71164_i) * p_78440_1_;
    
    float f4 = field_71163_h + (field_71154_f - field_71163_h) * p_78440_1_;
    
    GL11.glRotatef((field_70125_A - f3) * 0.1F, 1.0F, 0.0F, 0.0F);
    GL11.glRotatef((field_70177_z - f4) * 0.1F, 0.0F, 1.0F, 0.0F);
    
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    
    if (!entityclientplayermp.func_82150_aj()) {
      GL11.glPushMatrix();
      float f13 = 0.8F;
      float f5 = entityclientplayermp.func_70678_g(p_78440_1_);
      float f6 = MathHelper.func_76126_a(f5 * 3.1415927F);
      float f7 = MathHelper.func_76126_a(MathHelper.func_76129_c(f5) * 3.1415927F);
      GL11.glTranslatef(-f7 * 0.3F, MathHelper.func_76126_a(MathHelper.func_76129_c(f5) * 3.1415927F * 2.0F) * 0.4F, -f6 * 0.4F);
      
      GL11.glTranslatef(0.8F * f13, -0.75F * f13 - (1.0F - f1) * 0.6F, -0.9F * f13);
      GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
      GL11.glEnable(32826);
      f5 = entityclientplayermp.func_70678_g(p_78440_1_);
      f6 = MathHelper.func_76126_a(f5 * f5 * 3.1415927F);
      f7 = MathHelper.func_76126_a(MathHelper.func_76129_c(f5) * 3.1415927F);
      GL11.glRotatef(f7 * 70.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-f6 * 20.0F, 0.0F, 0.0F, 1.0F);
      
      if (creatureType == TransformCreature.WOLF) {
        float scale = 1.5F;
        
        GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
        GL11.glTranslatef(-0.3F, 0.1F, -0.5F);
        GL11.glScalef(1.0F, 1.0F, 2.0F);
        mc.func_110434_K().func_110577_a(wolfSkin);
      } else if (creatureType == TransformCreature.WOLFMAN) {
        mc.func_110434_K().func_110577_a(wolfSkin);
      } else if (creatureType == TransformCreature.PLAYER) {
        mc.func_110434_K().func_110577_a(playerEx.getLocationSkin());
      }
      
      GL11.glTranslatef(-1.0F, 3.6F, 3.5F);
      GL11.glRotatef(120.0F, 0.0F, 0.0F, 1.0F);
      GL11.glRotatef(200.0F, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
      GL11.glScalef(1.0F, 1.0F, 1.0F);
      GL11.glTranslatef(5.6F, 0.0F, 0.0F);
      Render render = RenderManager.field_78727_a.func_78713_a(field_71439_g);
      RenderPlayer renderplayer = (RenderPlayer)render;
      float f10 = 1.0F;
      GL11.glScalef(f10, f10, f10);
      renderplayer.func_82441_a(field_71439_g);
      GL11.glPopMatrix();
    }
    
    GL11.glDisable(32826);
    RenderHelper.func_74518_a();
  }
  
  private void hurtCameraEffect(float p_78482_1_, Minecraft mc) {
    EntityLivingBase entitylivingbase = field_71451_h;
    float f1 = field_70737_aN - p_78482_1_;
    

    if (entitylivingbase.func_110143_aJ() <= 0.0F) {
      float f2 = field_70725_aQ + p_78482_1_;
      GL11.glRotatef(40.0F - 8000.0F / (f2 + 200.0F), 0.0F, 0.0F, 1.0F);
    }
    
    if (f1 >= 0.0F) {
      f1 /= field_70738_aO;
      f1 = MathHelper.func_76126_a(f1 * f1 * f1 * f1 * 3.1415927F);
      float f2 = field_70739_aP;
      GL11.glRotatef(-f2, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-f1 * 14.0F, 0.0F, 0.0F, 1.0F);
      GL11.glRotatef(f2, 0.0F, 1.0F, 0.0F);
    }
  }
  
  private void setupViewBobbing(float p_78475_1_, Minecraft mc) {
    if ((field_71451_h instanceof EntityPlayer)) {
      EntityPlayer entityplayer = (EntityPlayer)field_71451_h;
      float f1 = field_70140_Q - field_70141_P;
      float f2 = -(field_70140_Q + f1 * p_78475_1_);
      float f3 = field_71107_bF + (field_71109_bG - field_71107_bF) * p_78475_1_;
      
      float f4 = field_70727_aS + (field_70726_aT - field_70727_aS) * p_78475_1_;
      
      GL11.glTranslatef(MathHelper.func_76126_a(f2 * 3.1415927F) * f3 * 0.5F, -Math.abs(MathHelper.func_76134_b(f2 * 3.1415927F) * f3), 0.0F);
      
      GL11.glRotatef(MathHelper.func_76126_a(f2 * 3.1415927F) * f3 * 3.0F, 0.0F, 0.0F, 1.0F);
      GL11.glRotatef(Math.abs(MathHelper.func_76134_b(f2 * 3.1415927F - 0.2F) * f3) * 5.0F, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef(f4, 1.0F, 0.0F, 0.0F);
    }
  }
  
  private float getFOVModifier(float partialTicks, EntityRenderer er, Minecraft mc) {
    if (field_78532_q > 0) {
      return 90.0F;
    }
    EntityLivingBase entityplayer = field_71451_h;
    float f1 = 70.0F;
    
    if (entityplayer.func_110143_aJ() <= 0.0F) {
      float f2 = field_70725_aQ + partialTicks;
      f1 /= ((1.0F - 500.0F / (f2 + 500.0F)) * 2.0F + 1.0F);
    }
    
    Block block = net.minecraft.client.renderer.ActiveRenderInfo.func_151460_a(field_71441_e, entityplayer, partialTicks);
    
    if (block.func_149688_o() == Material.field_151586_h) {
      f1 = f1 * 60.0F / 70.0F;
    }
    
    return f1;
  }
  


  private float interpolateRotation(float par1, float par2, float par3)
  {
    for (float f3 = par2 - par1; f3 < -180.0F; f3 += 360.0F) {}
    


    while (f3 >= 180.0F) {
      f3 -= 360.0F;
    }
    
    return par1 + par3 * f3;
  }
}
