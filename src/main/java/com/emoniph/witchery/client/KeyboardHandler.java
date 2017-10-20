package com.emoniph.witchery.client;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.potions.WitcheryPotions;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.common.ExtendedPlayer.VampirePower;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.lwjgl.input.Keyboard;

public class KeyboardHandler
{
  private final List<KeyInfo> bindings = new ArrayList();
  
  private final KeyInfo JUMP = new KeyInfo(func_71410_xfield_71474_y.field_74314_A, bindings)
  {
    private boolean isJumping;
    private int remainingJumps;
    private boolean clearFall;
    
    protected void onKeyDown(EntityPlayer player, boolean repeated, boolean end) {
      if ((!field_71075_bZ.field_75098_d) && (!end)) {
        if (isJumping) {
          if (remainingJumps > 0) {
            int jumpsLeft = remainingJumps;
            remainingJumps -= 1;
            field_70181_x = 0.42D;
            if (player.func_70644_a(Potion.field_76430_j)) {
              field_70181_x += 0.1D * (1 + player.func_70660_b(Potion.field_76430_j).func_76458_c());
            }
          }
        } else {
          isJumping = field_70160_al;
          if (player.func_70644_a(PotionsDOUBLE_JUMP)) {
            remainingJumps += 1 + player.func_70660_b(PotionsDOUBLE_JUMP).func_76458_c();
          }
        }
      }
      
      if (clearFall) {
        clearFall = false;
        field_70143_R = 0.0F;
        Witchery.packetPipeline.sendToServer(new com.emoniph.witchery.network.PacketClearFallDamage());
      }
    }
    
    protected void onTick(EntityPlayer player, boolean end)
    {
      if (field_70122_E) {
        isJumping = false;
        remainingJumps = 0;
      }
    }
  };
  
  private final KeyInfo HOTBAR1 = new KeyInfo(func_71410_xfield_71474_y.field_151456_ac[0], bindings)
  {
    protected void onKeyDown(EntityPlayer player, boolean repeated, boolean end) {
      if (!end) {
        ExtendedPlayer playerEx = ExtendedPlayer.get(player);
        if ((playerEx.isVampire()) && (!func_71410_xfield_71456_v.func_146158_b().func_146241_e())) {
          int MAXPOWER = playerEx.getMaxAvailablePowerOrdinal();
          if (field_71071_by.field_70461_c == 0) {
            int power = playerEx.getSelectedVampirePower().ordinal();
            
            if (power == MAXPOWER) {
              playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.NONE, true);
            } else {
              playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.values()[(power + 1)], true);
            }
          }
        }
      }
    }
    


    protected void onKeyUp(EntityPlayer player, boolean end) {}
    


    protected void onTick(EntityPlayer player, boolean end) {}
  };
  

  public KeyboardHandler()
  {
    for (int i = 1; i < func_71410_xfield_71474_y.field_151456_ac.length; i++) {
      KeyBinding binding = func_71410_xfield_71474_y.field_151456_ac[i];
      new KeyInfo(binding, bindings)
      {
        protected void onKeyDown(EntityPlayer player, boolean repeated, boolean end) {
          if (!end) {
            ExtendedPlayer playerEx = ExtendedPlayer.get(player);
            if ((playerEx.isVampire()) && (playerEx.getSelectedVampirePower() != ExtendedPlayer.VampirePower.NONE)) {
              playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.NONE, true);
            }
          }
        }
      };
    }
  }
  
  @SubscribeEvent
  public void onTick(TickEvent.ClientTickEvent event) {
    EntityPlayer player;
    if (side == cpw.mods.fml.relauncher.Side.CLIENT) {
      Minecraft mc = Minecraft.func_71410_x();
      player = field_71439_g;
      if (player != null) {
        for (KeyInfo keyInfo : bindings) {
          keyInfo.doTick(player, phase == TickEvent.Phase.END);
        }
      }
    }
  }
  
  private static abstract class KeyInfo {
    private final KeyBinding bind;
    private boolean repeat;
    private boolean down;
    
    public KeyInfo(KeyBinding bind, List<KeyInfo> bindings) {
      this.bind = bind;
      bindings.add(this);
    }
    
    public void doTick(EntityPlayer player, boolean end) {
      int keyCode = bind.func_151463_i();
      boolean newlyDown = keyCode < 0 ? org.lwjgl.input.Mouse.isButtonDown(keyCode + 100) : Keyboard.isKeyDown(keyCode);
      
      if ((newlyDown != down) || ((newlyDown) && (repeat))) {
        if (newlyDown) {
          onKeyDown(player, newlyDown != down, end);
        } else {
          onKeyUp(player, end);
        }
        if (end) {
          down = newlyDown;
        }
      }
      if (end) {
        onTick(player, end);
      }
    }
    
    protected void onKeyDown(EntityPlayer player, boolean repeated, boolean end) {}
    
    protected void onKeyUp(EntityPlayer player, boolean end) {}
    
    protected void onTick(EntityPlayer player, boolean end) {}
  }
}
