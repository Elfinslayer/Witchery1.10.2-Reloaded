package com.emoniph.witchery.entity.ai;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.ai.EntityAIWatchClosest;

public class EntityAILookAtTradePlayerGeneric extends EntityAIWatchClosest
{
  private final IMerchant merchant;
  
  public EntityAILookAtTradePlayerGeneric(net.minecraft.entity.EntityLiving entity, IMerchant merchant)
  {
    super(entity, net.minecraft.entity.player.EntityPlayer.class, 8.0F);
    this.merchant = merchant;
  }
  
  public boolean func_75250_a() {
    net.minecraft.entity.player.EntityPlayer customer = merchant.func_70931_l_();
    if (customer != null) {
      field_75334_a = customer;
      return true;
    }
    return false;
  }
}
