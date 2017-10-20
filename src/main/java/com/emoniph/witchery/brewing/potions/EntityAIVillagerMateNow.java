package com.emoniph.witchery.brewing.potions;

import java.util.Random;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;

public class EntityAIVillagerMateNow extends net.minecraft.entity.ai.EntityAIBase
{
  private EntityVillager villagerObj;
  private EntityVillager mate;
  private World worldObj;
  private int matingTimeout;
  private boolean begin;
  Village villageObj;
  
  public EntityAIVillagerMateNow(EntityVillager p_i1634_1_)
  {
    villagerObj = p_i1634_1_;
    worldObj = field_70170_p;
    func_75248_a(3);
  }
  
  public void beginMating() {
    begin = true;
  }
  
  public boolean func_75250_a() {
    if ((villagerObj.func_70874_b() != 0) || (!begin)) {
      return false;
    }
    villageObj = worldObj.field_72982_D.func_75550_a(MathHelper.func_76128_c(villagerObj.field_70165_t), MathHelper.func_76128_c(villagerObj.field_70163_u), MathHelper.func_76128_c(villagerObj.field_70161_v), 0);
    


    if (villageObj == null)
      return false;
    if (!checkSufficientDoorsPresentForNewVillager()) {
      return false;
    }
    net.minecraft.entity.Entity entity = worldObj.func_72857_a(EntityVillager.class, villagerObj.field_70121_D.func_72314_b(8.0D, 3.0D, 8.0D), villagerObj);
    

    if (entity == null) {
      return false;
    }
    mate = ((EntityVillager)entity);
    return mate.func_70874_b() == 0;
  }
  


  public void func_75249_e()
  {
    matingTimeout = 300;
    villagerObj.func_70947_e(true);
    begin = false;
  }
  
  public void func_75251_c() {
    villageObj = null;
    mate = null;
    villagerObj.func_70947_e(false);
    begin = false;
  }
  
  public boolean func_75253_b() {
    boolean keepGoing = (matingTimeout >= 0) && (checkSufficientDoorsPresentForNewVillager()) && (villagerObj.func_70874_b() == 0);
    

    return keepGoing;
  }
  
  public void func_75246_d() {
    matingTimeout -= 1;
    villagerObj.func_70671_ap().func_75651_a(mate, 10.0F, 30.0F);
    
    if (villagerObj.func_70068_e(mate) > 2.25D) {
      villagerObj.func_70661_as().func_75497_a(mate, 0.25D);
    } else if ((matingTimeout == 0) && (mate.func_70941_o())) {
      giveBirth();
    }
    
    if (villagerObj.func_70681_au().nextInt(35) == 0) {
      worldObj.func_72960_a(villagerObj, (byte)12);
    }
  }
  
  private boolean checkSufficientDoorsPresentForNewVillager() {
    return true;
  }
  


  private void giveBirth()
  {
    EntityVillager entityvillager = villagerObj.func_90011_a(mate);
    mate.func_70873_a(500);
    villagerObj.func_70873_a(500);
    entityvillager.func_70873_a(41536);
    entityvillager.func_70012_b(villagerObj.field_70165_t, villagerObj.field_70163_u, villagerObj.field_70161_v, 0.0F, 0.0F);
    
    worldObj.func_72838_d(entityvillager);
    worldObj.func_72960_a(entityvillager, (byte)12);
  }
}
