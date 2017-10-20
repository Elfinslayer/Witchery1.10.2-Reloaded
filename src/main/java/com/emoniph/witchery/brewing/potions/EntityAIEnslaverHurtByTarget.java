package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.world.World;

public class EntityAIEnslaverHurtByTarget extends EntityAITarget
{
  EntityCreature enslavedEntity;
  EntityLivingBase enslaversAttacker;
  private int enslaversRevengeTimer;
  
  public EntityAIEnslaverHurtByTarget(EntityCreature enslavedCreature)
  {
    super(enslavedCreature, false);
    enslavedEntity = enslavedCreature;
    func_75248_a(1);
  }
  
  public boolean func_75250_a() {
    if (!enslavedEntity.func_70644_a(PotionsENSLAVED)) {
      return false;
    }
    
    String ownerName = PotionEnslaved.getMobEnslaverName(enslavedEntity);
    if ((ownerName == null) || (ownerName.isEmpty())) {
      return false;
    }
    
    EntityLivingBase enslaver = enslavedEntity.field_70170_p.func_72924_a(ownerName);
    
    if (enslaver == null) {
      return false;
    }
    enslaversAttacker = enslaver.func_70643_av();
    int revengeTimer = enslaver.func_142015_aE();
    if (revengeTimer == enslaversRevengeTimer)
      return false;
    if (enslaversAttacker == null) {
      return false;
    }
    return func_75296_a(enslaversAttacker, false);
  }
  


  public void func_75249_e()
  {
    com.emoniph.witchery.util.EntityUtil.setTarget(field_75299_d, enslaversAttacker);
    String enslaverName = PotionEnslaved.getMobEnslaverName(enslavedEntity);
    EntityLivingBase enslaver = enslavedEntity.field_70170_p.func_72924_a(enslaverName);
    
    if (enslaver != null) {
      enslaversRevengeTimer = enslaver.func_142015_aE();
    }
    
    super.func_75249_e();
  }
}
