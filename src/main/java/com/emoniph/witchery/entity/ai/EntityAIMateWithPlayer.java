package com.emoniph.witchery.entity.ai;

import java.util.Random;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityAIMateWithPlayer extends net.minecraft.entity.ai.EntityAIBase
{
  private EntityVillager villagerObj;
  private EntityPlayer mate;
  private World worldObj;
  private int matingTimeout;
  private boolean forceExecute;
  
  public EntityAIMateWithPlayer(EntityVillager par1EntityVillager)
  {
    villagerObj = par1EntityVillager;
    worldObj = field_70170_p;
    func_75248_a(3);
  }
  
  public void forceTask(EntityPlayer player) {
    forceExecute = true;
    mate = player;
  }
  
  public boolean func_75250_a() {
    if (villagerObj.func_70874_b() != 0)
      return false;
    if ((!forceExecute) && (villagerObj.func_70681_au().nextInt(500) != 0)) {
      return false;
    }
    if (!forceExecute) {
      net.minecraft.entity.Entity entity = worldObj.func_72857_a(EntityPlayer.class, villagerObj.field_70121_D.func_72314_b(16.0D, 3.0D, 16.0D), villagerObj);
      

      if (entity == null) {
        return false;
      }
      mate = ((EntityPlayer)entity);
      return true;
    }
    
    forceExecute = false;
    return true;
  }
  


  public void func_75249_e()
  {
    matingTimeout = 1000;
    villagerObj.func_70947_e(true);
  }
  
  public void func_75251_c() {
    mate = null;
    villagerObj.func_70947_e(false);
  }
  
  public boolean func_75253_b() {
    return (matingTimeout >= 0) && (villagerObj.func_70874_b() == 0);
  }
  
  public void func_75246_d() {
    if (matingTimeout > 0) {
      matingTimeout -= 1;
    }
    
    villagerObj.func_70671_ap().func_75651_a(mate, 10.0F, 30.0F);
    
    if (villagerObj.func_70068_e(mate) > 2.25D) {
      villagerObj.func_70661_as().func_75497_a(mate, 0.3D);
    } else if ((matingTimeout > 0) && (villagerObj.func_70068_e(mate) <= 2.25D)) {
      matingTimeout = 0;
      giveBirth();
    }
    
    if (villagerObj.func_70681_au().nextInt(20) == 0) {
      worldObj.func_72960_a(villagerObj, (byte)12);
    }
  }
  
  private void giveBirth() {
    EntityVillager entityvillager = villagerObj.func_90011_a(villagerObj);
    villagerObj.func_70873_a(6000);
    entityvillager.func_70873_a(41536);
    entityvillager.func_70012_b(villagerObj.field_70165_t, villagerObj.field_70163_u, villagerObj.field_70161_v, 0.0F, 0.0F);
    worldObj.func_72838_d(entityvillager);
    worldObj.func_72960_a(entityvillager, (byte)12);
  }
}
