package com.emoniph.witchery.integration;

import WayofTime.alchemicalWizardry.api.event.SacrificeKnifeUsedEvent;
import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Log;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class ModHookBloodMagic extends ModHook
{
  public ModHookBloodMagic() {}
  
  public String getModID()
  {
    return "AWWayofTime";
  }
  







  protected void doInit() {}
  







  protected void doPostInit()
  {
    try
    {
      MinecraftForge.EVENT_BUS.register(new EventHooks());
    }
    catch (Throwable ex) {
      Log.instance().debug(String.format("Tried and failed to install hooks for Blood Magic dagger event. %s", new Object[] { ex.toString() }));
    }
  }
  


  protected void doReduceMagicPower(EntityLivingBase entity, float factor)
  {
    IntegrateBloodMagic.reduceMagicPower(entity, factor);
  }
  


  public void boostBloodPowers(EntityPlayer player, float health) { IntegrateBloodMagic.boostBloodPowers(player, health); }
  
  public static class EventHooks {
    public EventHooks() {}
    
    @SubscribeEvent
    public void onSacrificeKnifeUsed(SacrificeKnifeUsedEvent event) {
      ExtendedPlayer playerEx = ExtendedPlayer.get(player);
      if ((playerEx != null) && (playerEx.isVampire())) {
        shouldDrainHealth = false;
        if ((!player.field_70170_p.field_72995_K) && 
          (!playerEx.decreaseBloodPower(healthDrained * 100, true))) {
          shouldFillAltar = false;
        }
      }
    }
  }
  
  private static class IntegrateBloodMagic {
    private IntegrateBloodMagic() {}
    
    public static void reduceMagicPower(EntityLivingBase entity, float factor) {
      if ((entity != null) && ((entity instanceof EntityPlayer))) {
        EntityPlayer player = (EntityPlayer)entity;
        int essence = SoulNetworkHandler.getCurrentEssence(player.func_70005_c_());
        if (Config.instance().isDebugging()) {
          Log.instance().debug(String.format("reduceMagicPower for Blood Magic (%s lp=%d)", new Object[] { player.func_70005_c_(), Integer.valueOf(essence) }));
        }
        
        float reduction;
        
        if (essence <= 5000) {
          reduction = 5000.0F * factor; } else { float reduction;
          if (essence <= 25000) {
            reduction = 25000.0F * factor; } else { float reduction;
            if (essence <= 150000) {
              reduction = 150000.0F * factor; } else { float reduction;
              if (essence <= 1000000) {
                reduction = 1000000.0F * factor; } else { float reduction;
                if (essence <= 10000000) {
                  reduction = 1.0E7F * factor;
                } else
                  reduction = 2.14748365E9F * factor;
              } } } }
        float reduction = Math.max(reduction, 1.0F);
        int newEssence = Math.max((int)(essence - reduction), 0);
        SoulNetworkHandler.setCurrentEssence(player.func_70005_c_(), newEssence);
      }
    }
    
    public static void boostBloodPowers(EntityPlayer player, float health) {
      int LP_PER_LIFE = 100;
      String playerName = player.func_70005_c_();
      int newlevel = SoulNetworkHandler.getCurrentEssence(playerName) + (int)health * 100;
      SoulNetworkHandler.setCurrentEssence(playerName, newlevel);
    }
  }
}
