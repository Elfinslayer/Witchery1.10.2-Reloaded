package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.entity.EntityDemon;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class EntityAIDemonicBarginPlayer extends EntityAIBase
{
  private EntityDemon trader;
  
  public EntityAIDemonicBarginPlayer(EntityDemon trader)
  {
    this.trader = trader;
    func_75248_a(5);
  }
  



  public boolean func_75250_a()
  {
    if (!trader.func_70089_S())
    {
      return false;
    }
    if (trader.func_70090_H())
    {
      return false;
    }
    if (!trader.field_70122_E)
    {
      return false;
    }
    if (trader.field_70133_I)
    {
      return false;
    }
    

    EntityPlayer entityplayer = trader.func_70931_l_();
    return trader.func_70068_e(entityplayer) > 16.0D ? false : entityplayer == null ? false : field_71070_bA instanceof Container;
  }
  




  public void func_75249_e()
  {
    trader.func_70661_as().func_75499_g();
  }
  



  public void func_75251_c()
  {
    trader.func_70932_a_((EntityPlayer)null);
    trader.field_70715_bh.func_75774_a();
  }
}
