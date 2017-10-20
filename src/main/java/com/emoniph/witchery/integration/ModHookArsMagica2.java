package com.emoniph.witchery.integration;

import am2.api.ArsMagicaApi;
import am2.api.IExtendedProperties;
import am2.api.enchantment.IAMEnchantmentHelper;
import am2.api.events.ReconstructorRepairEvent;
import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.item.ItemChalk;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class ModHookArsMagica2 extends ModHook
{
  public ModHookArsMagica2() {}
  
  public String getModID()
  {
    return "arsmagica2";
  }
  

  protected void doInit() {}
  

  protected void doPostInit()
  {
    modHooksisAM2Present = true;
    MinecraftForge.EVENT_BUS.register(new EventHooks());
  }
  
  protected void doReduceMagicPower(EntityLivingBase entity, float factor)
  {
    IntegrateAM2.doReduceMagicPower(entity, factor);
  }
  


  protected void makeItemModProof(ItemStack stack) { IntegrateAM2.makeItemModProof(stack); }
  
  private static class IntegrateAM2 {
    private IntegrateAM2() {}
    
    public static void doReduceMagicPower(EntityLivingBase entity, float factor) { IExtendedProperties props = ArsMagicaApi.instance.getExtendedProperties(entity);
      if (props != null) {
        float maxMana = props.getMaxMana();
        float mana = props.getCurrentMana();
        if ((maxMana > 0.0F) && (mana > 0.0F)) {
          float reduction = Math.max(maxMana * factor, 1.0F);
          float newMana = Math.max(mana - reduction, 0.0F);
          props.setCurrentMana(newMana);
        }
      }
    }
    
    public static void makeItemModProof(ItemStack stack) {
      if ((stack.func_77956_u()) && 
        (ArsMagicaApi.instance != null)) {
        IAMEnchantmentHelper helper = ArsMagicaApi.instance.getEnchantHelper();
        if (helper != null) {
          int id = helper.getSoulboundID();
          if ((id >= 0) && (id < Enchantment.field_77331_b.length))
            stack.func_77966_a(Enchantment.field_77331_b[id], 1);
        }
      }
    }
  }
  
  public static class EventHooks {
    public EventHooks() {}
    
    @SubscribeEvent
    public void onReconstructorRepair(ReconstructorRepairEvent event) {
      if ((item != null) && (!event.isCanceled())) {
        Item item = item.func_77973_b();
        if ((item == ItemsPOPPET) || ((item instanceof ItemChalk))) {
          event.setCanceled(true);
        }
      }
    }
  }
}
