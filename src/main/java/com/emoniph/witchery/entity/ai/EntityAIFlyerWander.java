package com.emoniph.witchery.entity.ai;

import java.util.Random;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityAIFlyerWander extends net.minecraft.entity.ai.EntityAIBase
{
  private double xPosition;
  private double yPosition;
  private double zPosition;
  private double speed;
  World worldObj;
  public int courseChangeCooldown;
  public double waypointX;
  public double waypointY;
  public double waypointZ;
  public double fleeDistance;
  EntityLiving living;
  
  public EntityAIFlyerWander(EntityLiving par1EntityCreature, double par2, double fleeDistance)
  {
    living = par1EntityCreature;
    worldObj = living.field_70170_p;
    speed = par2;
    this.fleeDistance = fleeDistance;
    func_75248_a(1);
  }
  


  public boolean func_75250_a()
  {
    boolean isTame = ((living instanceof EntityTameable)) && (((EntityTameable)living).func_70909_n());
    if ((!isTame) && (living.field_70170_p.func_72890_a(living, fleeDistance) != null))
      return true;
    if (living.func_70654_ax() >= 100)
      return false;
    if ((living.func_70681_au().nextInt(living.field_70170_p.field_73011_w.isDaytime() ? 300 : 100) != 0) || (((living instanceof EntityTameable)) && (((EntityTameable)living).func_70906_o()))) {
      return false;
    }
    return true;
  }
  



  public boolean func_75253_b()
  {
    return (((living instanceof EntityTameable)) && (!((EntityTameable)living).func_70906_o())) || (living.func_70681_au().nextInt(40) != 0);
  }
  




  public void func_75249_e() {}
  



  public void func_75246_d()
  {
    double d0 = waypointX - living.field_70165_t;
    double d1 = waypointY - living.field_70163_u;
    double d2 = waypointZ - living.field_70161_v;
    double d3 = d0 * d0 + d1 * d1 + d2 * d2;
    
    if ((d3 < 1.0D) || (d3 > 3600.0D)) {
      float distance = ((living instanceof EntityTameable)) && (((EntityTameable)living).func_70909_n()) ? 2.0F : 6.0F;
      waypointX = (living.field_70165_t + (worldObj.field_73012_v.nextFloat() * 8.0F - 4.0F) * distance);
      waypointY = (living.field_70163_u + (worldObj.field_73012_v.nextFloat() * 2.0F - 1.0F) * distance);
      waypointZ = (living.field_70161_v + (worldObj.field_73012_v.nextFloat() * 8.0F - 4.0F) * distance);
    }
    
    if (courseChangeCooldown-- <= 0) {
      courseChangeCooldown += worldObj.field_73012_v.nextInt(2) + 2;
      d3 = net.minecraft.util.MathHelper.func_76133_a(d3);
      
      if (isCourseTraversable(waypointX, waypointY, waypointZ, d3)) {
        living.field_70159_w += d0 / d3 * 0.1D;
        living.field_70181_x += d1 / d3 * 0.1D;
        living.field_70179_y += d2 / d3 * 0.1D;
      } else {
        waypointX = living.field_70165_t;
        waypointY = living.field_70163_u;
        waypointZ = living.field_70161_v;
      }
    }
    
    living.field_70761_aq = (living.field_70177_z = -(float)Math.atan2(living.field_70159_w, living.field_70179_y) * 180.0F / 3.1415927F);
  }
  
  private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
    double d4 = (par1 - living.field_70165_t) / par7;
    double d5 = (par3 - living.field_70163_u) / par7;
    double d6 = (par5 - living.field_70161_v) / par7;
    
    AxisAlignedBB axisalignedbb = living.field_70121_D.func_72329_c();
    
    for (int i = 1; i < par7; i++) {
      axisalignedbb.func_72317_d(d4, d5, d6);
      
      if (!living.field_70170_p.func_72945_a(living, axisalignedbb).isEmpty()) {
        return false;
      }
    }
    
    return true;
  }
}
