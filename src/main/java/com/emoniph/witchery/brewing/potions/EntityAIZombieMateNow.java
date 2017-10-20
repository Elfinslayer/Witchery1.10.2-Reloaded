package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.util.ParticleEffect;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityAIZombieMateNow extends EntityAIBase
{
  private EntityZombie zombieObj;
  private EntityZombie mate;
  private World worldObj;
  private int matingTimeout;
  private boolean mating;
  private boolean begin;
  
  public EntityAIZombieMateNow(EntityZombie zombie)
  {
    zombieObj = zombie;
    worldObj = field_70170_p;
    func_75248_a(3);
  }
  
  public void beginMating() {
    begin = true;
  }
  
  public boolean func_75250_a() {
    if (!begin) {
      return false;
    }
    EntityZombie zombie = (EntityZombie)com.emoniph.witchery.util.EntityUtil.findNearestEntityWithinAABB(worldObj, EntityZombie.class, zombieObj.field_70121_D.func_72314_b(8.0D, 3.0D, 8.0D), zombieObj);
    

    if ((zombie == null) || (zombie.func_70631_g_())) {
      return false;
    }
    mate = zombie;
    return true;
  }
  

  public void func_75249_e()
  {
    matingTimeout = 600;
    mating = true;
    begin = false;
  }
  
  public void func_75251_c() {
    mate = null;
    mating = false;
    begin = false;
  }
  
  public boolean func_75253_b() {
    boolean keepGoing = matingTimeout >= 0;
    return keepGoing;
  }
  
  public void func_75246_d() {
    matingTimeout -= 1;
    zombieObj.func_70671_ap().func_75651_a(mate, 10.0F, 30.0F);
    
    if (zombieObj.func_70068_e(mate) > 2.25D) {
      zombieObj.func_70661_as().func_75497_a(mate, 1.4D);
    } else if ((matingTimeout == 0) && (mating)) {
      giveBirth();
    }
  }
  



  private void giveBirth()
  {
    ParticleEffect.HEART.send(com.emoniph.witchery.util.SoundEffect.NONE, mate, 1.0D, 2.0D, 8);
    zombieObj.func_82229_g(true);
    mate.func_82229_g(true);
    EntityZombie baby = new EntityZombie(worldObj);
    baby.func_70012_b(mate.field_70165_t, mate.field_70163_u, mate.field_70161_v, 0.0F, 0.0F);
    baby.func_82227_f(true);
    worldObj.func_72838_d(baby);
  }
}
