package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.entity.EntityGoblin;
import java.util.List;
import java.util.Random;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;

public class EntityAIGoblinMate extends net.minecraft.entity.ai.EntityAIBase
{
  private EntityGoblin goblinObj;
  private EntityGoblin mate;
  private World worldObj;
  private int matingTimeout;
  Village villageObj;
  
  public EntityAIGoblinMate(EntityGoblin goblin)
  {
    goblinObj = goblin;
    worldObj = field_70170_p;
    func_75248_a(3);
  }
  
  public boolean func_75250_a() {
    if (goblinObj.func_70874_b() != 0)
      return false;
    if (goblinObj.func_70681_au().nextInt(500) != 0) {
      return false;
    }
    villageObj = worldObj.field_72982_D.func_75550_a(MathHelper.func_76128_c(goblinObj.field_70165_t), MathHelper.func_76128_c(goblinObj.field_70163_u), MathHelper.func_76128_c(goblinObj.field_70161_v), 0);
    

    if (villageObj == null)
      return false;
    if (!checkSufficientDoorsPresentForNewVillager()) {
      return false;
    }
    net.minecraft.entity.Entity entity = worldObj.func_72857_a(EntityGoblin.class, goblinObj.field_70121_D.func_72314_b(8.0D, 3.0D, 8.0D), goblinObj);
    

    if (entity == null) {
      return false;
    }
    mate = ((EntityGoblin)entity);
    return mate.func_70874_b() == 0;
  }
  


  public void func_75249_e()
  {
    matingTimeout = 300;
    goblinObj.setMating(true);
  }
  
  public void func_75251_c() {
    villageObj = null;
    mate = null;
    goblinObj.setMating(false);
  }
  
  public boolean func_75253_b() {
    return (matingTimeout >= 0) && (checkSufficientDoorsPresentForNewVillager()) && (goblinObj.func_70874_b() == 0);
  }
  
  public void func_75246_d() {
    matingTimeout -= 1;
    goblinObj.func_70671_ap().func_75651_a(mate, 10.0F, 30.0F);
    
    if (goblinObj.func_70068_e(mate) > 2.25D) {
      goblinObj.func_70661_as().func_75497_a(mate, 0.25D);
    } else if ((matingTimeout == 0) && (mate.isMating())) {
      giveBirth();
    }
    
    if (goblinObj.func_70681_au().nextInt(35) == 0) {
      worldObj.func_72960_a(goblinObj, (byte)12);
    }
  }
  
  private boolean checkSufficientDoorsPresentForNewVillager() {
    if (!villageObj.func_82686_i()) {
      return false;
    }
    int i = (int)(villageObj.func_75567_c() * 0.35D);
    return getNumVillagers() < i;
  }
  
  private int getNumVillagers()
  {
    if ((worldObj == null) || (goblinObj == null)) {
      return 0;
    }
    List list = worldObj.func_72872_a(EntityGoblin.class, goblinObj.field_70121_D.func_72314_b(32.0D, 3.0D, 32.0D));
    return list != null ? list.size() : 0;
  }
  
  private void giveBirth()
  {
    EntityGoblin entityvillager = goblinObj.createChild(mate);
    mate.func_70873_a(6000);
    goblinObj.func_70873_a(6000);
    entityvillager.func_70873_a(41536);
    entityvillager.func_70012_b(goblinObj.field_70165_t, goblinObj.field_70163_u, goblinObj.field_70161_v, 0.0F, 0.0F);
    worldObj.func_72838_d(entityvillager);
    worldObj.func_72960_a(entityvillager, (byte)12);
  }
}
