package com.emoniph.witchery.entity.ai;

import java.util.List;
import java.util.Random;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;


public class EntityAIFlyerArrowAttack
  extends EntityAIBase
{
  private final EntityLiving entityHost;
  private final IRangedAttackMob rangedAttackEntityHost;
  private EntityLivingBase attackTarget;
  private int rangedAttackTime;
  private double entityMoveSpeed;
  private int field_75318_f;
  private int field_96561_g;
  private int maxRangedAttackTime;
  private float field_96562_i;
  private float field_82642_h;
  private static final String __OBFID = "CL_00001609";
  private int field_75445_i;
  private int failedPathFindingPenalty;
  
  public EntityAIFlyerArrowAttack(IRangedAttackMob par1IRangedAttackMob, double par2, int par4, float par5)
  {
    this(par1IRangedAttackMob, par2, par4, par4, par5);
  }
  
  public EntityAIFlyerArrowAttack(IRangedAttackMob par1IRangedAttackMob, double par2, int par4, int par5, float par6) {
    rangedAttackTime = -1;
    
    if (!(par1IRangedAttackMob instanceof EntityLivingBase)) {
      throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
    }
    rangedAttackEntityHost = par1IRangedAttackMob;
    entityHost = ((EntityLiving)par1IRangedAttackMob);
    entityMoveSpeed = par2;
    field_96561_g = par4;
    maxRangedAttackTime = par5;
    field_96562_i = par6;
    field_82642_h = (par6 * par6);
    func_75248_a(3);
  }
  
  public boolean func_75250_a()
  {
    EntityLivingBase entitylivingbase = entityHost.func_70638_az();
    
    if (entitylivingbase == null) {
      return false;
    }
    attackTarget = entitylivingbase;
    return true;
  }
  
  public boolean func_75253_b()
  {
    return (func_75250_a()) || (!entityHost.func_70661_as().func_75500_f());
  }
  
  public void func_75251_c() {
    attackTarget = null;
    field_75318_f = 0;
    rangedAttackTime = -1;
    field_75445_i = 0;
  }
  



  public void func_75246_d()
  {
    double d0 = entityHost.func_70092_e(attackTarget.field_70165_t, attackTarget.field_70121_D.field_72338_b, attackTarget.field_70161_v);
    boolean flag = entityHost.func_70635_at().func_75522_a(attackTarget);
    
    if (flag) {
      field_75318_f += 1;
    } else {
      field_75318_f = 0;
    }
    
    if (d0 > field_82642_h)
    {

      if (--field_75445_i <= 0) {
        field_75445_i = (failedPathFindingPenalty + 4 + entityHost.func_70681_au().nextInt(7));
        
        double d = attackTarget.field_70165_t - entityHost.field_70165_t;
        double d1 = attackTarget.field_70163_u - entityHost.field_70163_u;
        double d2 = attackTarget.field_70161_v - entityHost.field_70161_v;
        double d3 = d * d + d1 * d1 + d2 * d2;
        d3 = MathHelper.func_76133_a(d3);
        if (isCourseTraversable(attackTarget.field_70165_t, attackTarget.field_70163_u, attackTarget.field_70161_v, d3)) {
          entityHost.field_70159_w += d / d3 * 0.15D;
          entityHost.field_70181_x += d1 / d3 * 0.15D;
          entityHost.field_70179_y += d2 / d3 * 0.15D;
          failedPathFindingPenalty = 0;
        } else {
          failedPathFindingPenalty += 10;
        }
        
        entityHost.field_70761_aq = (entityHost.field_70177_z = -(float)Math.atan2(entityHost.field_70159_w, entityHost.field_70179_y) * 180.0F / 3.1415927F);
        
        entityHost.func_70661_as().func_75497_a(attackTarget, entityMoveSpeed);
      }
    }
    
    entityHost.func_70671_ap().func_75651_a(attackTarget, 30.0F, 30.0F);
    

    if (--rangedAttackTime == 0) {
      if ((d0 > field_82642_h) || (!flag)) {
        return;
      }
      
      float f = MathHelper.func_76133_a(d0) / field_96562_i;
      float f1 = f;
      
      if (f < 0.1F) {
        f1 = 0.1F;
      }
      
      if (f1 > 1.0F) {
        f1 = 1.0F;
      }
      
      rangedAttackEntityHost.func_82196_d(attackTarget, f1);
      rangedAttackTime = MathHelper.func_76141_d(f * (maxRangedAttackTime - field_96561_g) + field_96561_g);
    }
    else if (rangedAttackTime < 0) {
      float f = MathHelper.func_76133_a(d0) / field_96562_i;
      rangedAttackTime = MathHelper.func_76141_d(f * (maxRangedAttackTime - field_96561_g) + field_96561_g);
    }
  }
  
  private boolean isCourseTraversable(double par1, double par3, double par5, double par7)
  {
    double d4 = (par1 - attackTarget.field_70165_t) / par7;
    double d5 = (par3 - attackTarget.field_70163_u) / par7;
    double d6 = (par5 - attackTarget.field_70161_v) / par7;
    
    AxisAlignedBB axisalignedbb = attackTarget.field_70121_D.func_72329_c();
    
    for (int i = 1; i < par7; i++) {
      axisalignedbb.func_72317_d(d4, d5, d6);
      
      if (!attackTarget.field_70170_p.func_72945_a(attackTarget, axisalignedbb).isEmpty()) {
        return false;
      }
    }
    
    return true;
  }
}
