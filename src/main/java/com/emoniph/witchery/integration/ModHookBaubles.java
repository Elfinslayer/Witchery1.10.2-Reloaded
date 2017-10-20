package com.emoniph.witchery.integration;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class ModHookBaubles extends ModHook
{
  public ModHookBaubles() {}
  
  public String getModID()
  {
    return "Baubles";
  }
  


  protected void doInit() {}
  


  protected void doPostInit() {}
  


  protected void doReduceMagicPower(EntityLivingBase entity, float factor) {}
  

  public boolean canVampireBeKilled(EntityPlayer player)
  {
    return IntegrateBaubles.canVampireBeVilled(player);
  }
  
  private static final String[] BANNED_ITEMS = { "item.superLavaPendant", "item.lavaPendant", "item.odinRing", "item.aesirRing" };
  

  private static class IntegrateBaubles
  {
    private IntegrateBaubles() {}
    

    public static boolean canVampireBeVilled(EntityPlayer player)
    {
      IInventory inv = baubles.api.BaublesApi.getBaubles(player);
      if (inv == null) {
        return false;
      }
      

      for (int slot = 0; slot < inv.func_70302_i_(); slot++) {
        net.minecraft.item.ItemStack stack = inv.func_70301_a(slot);
        if (stack != null) {
          for (String badItem : ModHookBaubles.BANNED_ITEMS) {
            if (badItem.equals(stack.func_77977_a())) {
              return true;
            }
          }
        }
      }
      
      return false;
    }
  }
}
