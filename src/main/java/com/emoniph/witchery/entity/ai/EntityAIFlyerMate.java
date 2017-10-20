package com.emoniph.witchery.entity.ai;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAIFlyerMate extends EntityAIBase
{
  private EntityAnimal theAnimal;
  World theWorld;
  private EntityAnimal targetMate;
  int spawnBabyDelay;
  int updateDelay = 0;
  double moveSpeed;
  
  public EntityAIFlyerMate(EntityAnimal par1EntityAnimal, double par2) {
    theAnimal = par1EntityAnimal;
    theWorld = field_70170_p;
    moveSpeed = par2;
    func_75248_a(1);
  }
  
  public boolean func_75250_a() {
    if (!theAnimal.func_70880_s()) {
      return false;
    }
    targetMate = getNearbyMate();
    return targetMate != null;
  }
  
  public boolean func_75253_b()
  {
    return (targetMate.func_70089_S()) && (targetMate.func_70880_s()) && (spawnBabyDelay < 60);
  }
  
  public void func_75251_c() {
    targetMate = null;
    spawnBabyDelay = 0;
    updateDelay = 0;
  }
  
  public void func_75246_d() {
    if (--updateDelay <= 0) {
      double d0 = targetMate.field_70165_t - theAnimal.field_70165_t;
      double d1 = targetMate.field_70163_u - theAnimal.field_70163_u;
      double d2 = targetMate.field_70161_v - theAnimal.field_70161_v;
      double d3 = d0 * d0 + d1 * d1 + d2 * d2;
      d3 = MathHelper.func_76133_a(d3);
      if (isCourseTraversable(targetMate.field_70165_t, targetMate.field_70163_u, targetMate.field_70161_v, d3)) {
        theAnimal.field_70159_w += d0 / d3 * 0.25D;
        theAnimal.field_70181_x += d1 / d3 * 0.25D;
        theAnimal.field_70179_y += d2 / d3 * 0.25D;
      }
      updateDelay = 10;
    }
    
    theAnimal.field_70761_aq = (theAnimal.field_70177_z = -(float)Math.atan2(theAnimal.field_70159_w, theAnimal.field_70179_y) * 180.0F / 3.1415927F);
    
    spawnBabyDelay += 1;
    
    if ((spawnBabyDelay >= 60) && (theAnimal.func_70068_e(targetMate) < 9.0D)) {
      spawnBaby();
    }
  }
  
  private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
    double d4 = (par1 - theAnimal.field_70165_t) / par7;
    double d5 = (par3 - theAnimal.field_70163_u) / par7;
    double d6 = (par5 - theAnimal.field_70161_v) / par7;
    
    AxisAlignedBB axisalignedbb = theAnimal.field_70121_D.func_72329_c();
    
    for (int i = 1; i < par7; i++) {
      axisalignedbb.func_72317_d(d4, d5, d6);
      
      if (!theAnimal.field_70170_p.func_72945_a(theAnimal, axisalignedbb).isEmpty()) {
        return false;
      }
    }
    
    return true;
  }
  
  private EntityAnimal getNearbyMate() {
    float f = 8.0F;
    List list = theWorld.func_72872_a(theAnimal.getClass(), theAnimal.field_70121_D.func_72314_b(f, f, f));
    double d0 = Double.MAX_VALUE;
    EntityAnimal entityanimal = null;
    Iterator iterator = list.iterator();
    
    while (iterator.hasNext()) {
      EntityAnimal entityanimal1 = (EntityAnimal)iterator.next();
      
      if ((theAnimal.func_70878_b(entityanimal1)) && (theAnimal.func_70068_e(entityanimal1) < d0)) {
        entityanimal = entityanimal1;
        d0 = theAnimal.func_70068_e(entityanimal1);
      }
    }
    
    return entityanimal;
  }
  
  private void spawnBaby() {
    EntityAgeable entityageable = theAnimal.func_90011_a(targetMate);
    
    if (entityageable != null) {
      theAnimal.func_70873_a(6000);
      targetMate.func_70873_a(6000);
      theAnimal.func_70875_t();
      targetMate.func_70875_t();
      entityageable.func_70873_a(41536);
      entityageable.func_70012_b(theAnimal.field_70165_t, theAnimal.field_70163_u, theAnimal.field_70161_v, 0.0F, 0.0F);
      theWorld.func_72838_d(entityageable);
      Random random = theAnimal.func_70681_au();
      
      for (int i = 0; i < 7; i++) {
        double d0 = random.nextGaussian() * 0.02D;
        double d1 = random.nextGaussian() * 0.02D;
        double d2 = random.nextGaussian() * 0.02D;
        theWorld.func_72869_a("heart", theAnimal.field_70165_t + random.nextFloat() * theAnimal.field_70130_N * 2.0F - theAnimal.field_70130_N, theAnimal.field_70163_u + 0.5D + random.nextFloat() * theAnimal.field_70131_O, theAnimal.field_70161_v + random.nextFloat() * theAnimal.field_70130_N * 2.0F - theAnimal.field_70130_N, d0, d1, d2);
      }
      


      theWorld.func_72838_d(new EntityXPOrb(theWorld, theAnimal.field_70165_t, theAnimal.field_70163_u, theAnimal.field_70161_v, random.nextInt(7) + 1));
    }
  }
}
