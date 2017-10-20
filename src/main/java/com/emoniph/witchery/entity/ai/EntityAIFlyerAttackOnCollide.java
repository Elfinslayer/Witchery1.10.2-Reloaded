package com.emoniph.witchery.entity.ai;

import java.util.List;
import java.util.Random;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;









public class EntityAIFlyerAttackOnCollide
  extends EntityAIBase
{
  World worldObj;
  EntityCreature attacker;
  int attackTick;
  double speedTowardsTarget;
  boolean longMemory;
  PathEntity entityPathEntity;
  Class classTarget;
  private int field_75445_i;
  private int failedPathFindingPenalty;
  
  public EntityAIFlyerAttackOnCollide(EntityCreature par1EntityCreature, Class par2Class, double par3, boolean par5)
  {
    this(par1EntityCreature, par3, par5);
    classTarget = par2Class;
  }
  
  public EntityAIFlyerAttackOnCollide(EntityCreature par1EntityCreature, double par2, boolean par4)
  {
    attacker = par1EntityCreature;
    worldObj = field_70170_p;
    speedTowardsTarget = par2;
    longMemory = par4;
    func_75248_a(3);
  }
  



  public boolean func_75250_a()
  {
    EntityLivingBase entitylivingbase = attacker.func_70638_az();
    
    if (entitylivingbase == null)
    {
      return false;
    }
    if (!entitylivingbase.func_70089_S())
    {
      return false;
    }
    if ((classTarget != null) && (!classTarget.isAssignableFrom(entitylivingbase.getClass())))
    {
      return false;
    }
    









    return true;
  }
  





  public boolean func_75253_b()
  {
    EntityLivingBase entitylivingbase = attacker.func_70638_az();
    return !longMemory ? false : !attacker.func_70661_as().func_75500_f() ? true : !entitylivingbase.func_70089_S() ? false : entitylivingbase == null ? false : attacker.func_110176_b(MathHelper.func_76128_c(field_70165_t), MathHelper.func_76128_c(field_70163_u), MathHelper.func_76128_c(field_70161_v));
  }
  




  public void func_75249_e()
  {
    attacker.func_70661_as().func_75484_a(entityPathEntity, speedTowardsTarget);
    field_75445_i = 0;
  }
  



  public void func_75251_c()
  {
    attacker.func_70661_as().func_75499_g();
  }
  



  public void func_75246_d()
  {
    EntityLivingBase entitylivingbase = attacker.func_70638_az();
    attacker.func_70671_ap().func_75651_a(entitylivingbase, 30.0F, 30.0F);
    
    if (((longMemory) || (attacker.func_70635_at().func_75522_a(entitylivingbase))) && (--field_75445_i <= 0))
    {
      field_75445_i = (failedPathFindingPenalty + 4 + attacker.func_70681_au().nextInt(7));
      
      double d0 = field_70165_t - attacker.field_70165_t;
      double d1 = field_70163_u - attacker.field_70163_u;
      double d2 = field_70161_v - attacker.field_70161_v;
      double d3 = d0 * d0 + d1 * d1 + d2 * d2;
      d3 = MathHelper.func_76133_a(d3);
      if (isCourseTraversable(field_70165_t, field_70163_u, field_70161_v, d3)) {
        attacker.field_70159_w += d0 / d3 * 0.15D;
        attacker.field_70181_x += d1 / d3 * 0.15D;
        attacker.field_70179_y += d2 / d3 * 0.15D;
        failedPathFindingPenalty = 0;
      } else {
        failedPathFindingPenalty += 10;
      }
      
      attacker.field_70761_aq = (attacker.field_70177_z = -(float)Math.atan2(attacker.field_70159_w, attacker.field_70179_y) * 180.0F / 3.1415927F);
    }
    

















    attackTick = Math.max(attackTick - 1, 0);
    double d0 = attacker.field_70130_N * 2.0F * attacker.field_70130_N * 2.0F + field_70130_N;
    
    if (attacker.func_70092_e(field_70165_t, field_70121_D.field_72338_b, field_70161_v) <= d0)
    {
      if (attackTick <= 0)
      {
        attackTick = 20;
        
        if (attacker.func_70694_bm() != null)
        {
          attacker.func_71038_i();
        }
        
        attacker.func_70652_k(entitylivingbase);
      }
    }
  }
  
  private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
    double d4 = (par1 - attacker.field_70165_t) / par7;
    double d5 = (par3 - attacker.field_70163_u) / par7;
    double d6 = (par5 - attacker.field_70161_v) / par7;
    
    AxisAlignedBB axisalignedbb = attacker.field_70121_D.func_72329_c();
    
    for (int i = 1; i < par7; i++) {
      axisalignedbb.func_72317_d(d4, d5, d6);
      
      if (!attacker.field_70170_p.func_72945_a(attacker, axisalignedbb).isEmpty()) {
        return false;
      }
    }
    
    return true;
  }
}
