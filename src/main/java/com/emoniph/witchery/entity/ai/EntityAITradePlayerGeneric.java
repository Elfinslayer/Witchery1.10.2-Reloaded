package com.emoniph.witchery.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAITradePlayerGeneric extends net.minecraft.entity.ai.EntityAIBase
{
  private IMerchant merchant;
  private EntityLiving entity;
  
  public EntityAITradePlayerGeneric(IMerchant merchant, EntityLiving entity)
  {
    this.merchant = merchant;
    this.entity = entity;
    func_75248_a(5);
  }
  
  public boolean func_75250_a() {
    if (!entity.func_70089_S())
      return false;
    if (entity.func_70090_H())
      return false;
    if (!entity.field_70122_E)
      return false;
    if (entity.field_70133_I) {
      return false;
    }
    EntityPlayer entityplayer = merchant.func_70931_l_();
    return entity.func_70068_e(entityplayer) > 16.0D ? false : entityplayer == null ? false : field_71070_bA instanceof net.minecraft.inventory.Container;
  }
  

  public void func_75249_e()
  {
    entity.func_70661_as().func_75499_g();
  }
  
  public void func_75251_c() {
    merchant.func_70932_a_((EntityPlayer)null);
  }
}
