package com.emoniph.witchery.entity.ai;

import java.util.Random;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.village.Village;


public class EntityAIDefendVillageGeneric
  extends EntityAITarget
{
  IVillageGuard defender;
  EntityLivingBase villageAgressorTarget;
  
  public EntityAIDefendVillageGeneric(IVillageGuard guard)
  {
    super(guard.getCreature(), false, true);
    defender = guard;
    func_75248_a(1);
  }
  
  public boolean func_75250_a() {
    Village village = defender.getVillage();
    
    if (village == null) {
      return false;
    }
    villageAgressorTarget = village.func_75571_b(defender.getCreature());
    
    if (!func_75296_a(villageAgressorTarget, false)) {
      if (field_75299_d.func_70681_au().nextInt(20) == 0) {
        villageAgressorTarget = village.func_82685_c(defender.getCreature());
        return func_75296_a(villageAgressorTarget, false);
      }
      return false;
    }
    
    return true;
  }
  

  public void func_75249_e()
  {
    defender.getCreature().func_70624_b(villageAgressorTarget);
    super.func_75249_e();
  }
  
  public static abstract interface IVillageGuard
  {
    public abstract Village getVillage();
    
    public abstract EntityCreature getCreature();
  }
}
