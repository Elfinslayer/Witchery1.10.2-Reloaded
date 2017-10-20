package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.entity.EntityDemon;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAILookAtDemonicBarginPlayer
  extends EntityAIWatchClosest
{
  private final EntityDemon theMerchant;
  
  public EntityAILookAtDemonicBarginPlayer(EntityDemon trader)
  {
    super(trader, EntityPlayer.class, 8.0F);
    theMerchant = trader;
  }
  



  public boolean func_75250_a()
  {
    if (theMerchant.isTrading())
    {
      field_75334_a = theMerchant.func_70931_l_();
      return true;
    }
    

    return false;
  }
}
