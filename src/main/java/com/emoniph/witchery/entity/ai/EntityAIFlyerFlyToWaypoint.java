package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.entity.EntityFlyingTameable;
import com.emoniph.witchery.entity.EntityWitchProjectile;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.util.Waypoint;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAIFlyerFlyToWaypoint extends net.minecraft.entity.ai.EntityAIBase
{
  private EntityFlyingTameable flyer;
  private CarryRequirement carryRequirement;
  private static final double HIT_RADIUS = 1.0D;
  private static final double HIT_RADIUS_SQ = 1.0D;
  
  public static enum CarryRequirement
  {
    NONE,  HELD_ITEM,  ENTITY_LIVING;
    
    private CarryRequirement() {}
  }
  
  public EntityAIFlyerFlyToWaypoint(EntityFlyingTameable flyer, CarryRequirement carryRestrictions) {
    this.flyer = flyer;
    carryRequirement = carryRestrictions;
    func_75248_a(7);
  }
  
  public boolean func_75250_a() {
    if ((flyer.waypoint != null) && ((flyer.func_70694_bm() != null) || (carryRequirement != CarryRequirement.HELD_ITEM))) {
      return true;
    }
    return false;
  }
  
  public boolean func_75253_b()
  {
    boolean heldItem = flyer.func_70694_bm() != null;
    boolean awayFromHome = (flyer.func_70092_e(flyer.homeX, flyer.field_70163_u, flyer.homeZ) > 1.0D) || (Math.abs(flyer.field_70163_u - flyer.homeY) > 1.0D);
    return ((heldItem) && (carryRequirement == CarryRequirement.HELD_ITEM)) || (flyer.waypoint != null) || (awayFromHome);
  }
  
  public void func_75249_e() {}
  
  public void func_75251_c()
  {
    flyer.waypoint = null;
    flyer.func_70904_g(true);
    if (flyer.field_70153_n != null) {
      flyer.field_70153_n.func_70078_a(null);
    }
    courseTimer = 0;
  }
  
  int courseTimer = 0;
  
  public void func_75246_d() {
    if (!flyer.func_70906_o()) {
      Waypoint waypoint = flyer.getWaypoint();
      if (carryRequirement == CarryRequirement.ENTITY_LIVING) {
        if (flyer.func_70092_e(X, Y, Z) <= 1.0D)
        {
          List<EntityLivingBase> entities = flyer.field_70170_p.func_72872_a(EntityLivingBase.class, flyer.field_70121_D.func_72314_b(1.0D, 1.0D, 1.0D));
          if ((entities != null) && (entities.size() > 1)) {
            if (!flyer.field_70170_p.field_72995_K) {
              for (EntityLivingBase entity : entities) {
                if (entity != flyer) {
                  entity.func_70078_a(flyer);
                }
              }
            }
            flyer.waypoint = null;
            waypoint = flyer.getWaypoint();
          }
          
        }
        
      }
      else if ((flyer.func_70694_bm() != null) && (flyer.func_70092_e(X, Y, Z) <= 1.0D)) {
        if (!flyer.field_70170_p.field_72995_K) {
          ItemStack stack = flyer.func_70694_bm();
          flyer.func_70062_b(0, null);
          
          if (ItemsGENERIC.isBrew(stack)) {
            flyer.field_70170_p.func_72956_a(flyer, "random.bow", 0.5F, 0.4F / (flyer.field_70170_p.field_73012_v.nextFloat() * 0.4F + 0.8F));
            EntityWitchProjectile projectile = new EntityWitchProjectile(flyer.field_70170_p, flyer, (com.emoniph.witchery.item.ItemGeneral.SubItem)ItemsGENERIC.subItems.get(stack.func_77960_j()));
            field_70159_w = 0.0D;
            field_70179_y = 0.0D;
            flyer.field_70170_p.func_72838_d(projectile);
          } else if ((ItemsBREW == stack.func_77973_b()) && (com.emoniph.witchery.brewing.WitcheryBrewRegistry.INSTANCE.isSplash(stack.func_77978_p()))) {
            flyer.field_70170_p.func_72956_a(flyer, "random.bow", 0.5F, 0.4F / (flyer.field_70170_p.field_73012_v.nextFloat() * 0.4F + 0.8F));
            com.emoniph.witchery.brewing.EntityBrew projectile = new com.emoniph.witchery.brewing.EntityBrew(flyer.field_70170_p, flyer, stack, false);
            field_70159_w = 0.0D;
            field_70179_y = 0.0D;
            flyer.field_70170_p.func_72838_d(projectile);
          } else if ((stack.func_77973_b() == net.minecraft.init.Items.field_151068_bn) && (net.minecraft.item.ItemPotion.func_77831_g(stack.func_77960_j()))) {
            flyer.field_70170_p.func_72956_a(flyer, "random.bow", 0.5F, 0.4F / (flyer.field_70170_p.field_73012_v.nextFloat() * 0.4F + 0.8F));
            EntityPotion projectile = new EntityPotion(flyer.field_70170_p, flyer, stack);
            field_70159_w = 0.0D;
            field_70179_y = 0.0D;
            flyer.field_70170_p.func_72838_d(projectile);
          }
          else {
            EntityItem item = new EntityItem(flyer.field_70170_p, flyer.field_70165_t, flyer.field_70163_u, flyer.field_70161_v, stack);
            if (stack.func_77973_b() == ItemsSEEDS_MINDRAKE) {
              lifespan = com.emoniph.witchery.util.TimeUtil.secsToTicks(3);
            }
            flyer.field_70170_p.func_72838_d(item);
          }
        }
        flyer.waypoint = null;
        waypoint = flyer.getWaypoint();
      }
      

      double dX = X - flyer.field_70165_t;
      double dY = Y - flyer.field_70163_u;
      double dZ = Z - flyer.field_70161_v;
      double trajectory = dX * dX + dY * dY + dZ * dZ;
      trajectory = MathHelper.func_76133_a(trajectory);
      
      if ((trajectory >= 128.0D) && (carryRequirement == CarryRequirement.HELD_ITEM)) {
        com.emoniph.witchery.blocks.BlockVoidBramble.teleportRandomly(flyer.field_70170_p, (int)X, (int)Y, (int)Z, flyer, 16);
      }
      
      if (--courseTimer < 0) {
        courseTimer = 0;
      }
      if (courseTimer == 0) {
        if (!isCourseTraversable(X, Y, Z, trajectory))
        {
          double newX = flyer.field_70165_t + (flyer.field_70170_p.field_73012_v.nextDouble() * 4.0D - 2.0D) * 6.0D;
          double newY = flyer.field_70163_u + (flyer.field_70170_p.field_73012_v.nextDouble() * 2.0D - 1.0D) * 4.0D;
          double newZ = flyer.field_70161_v + (flyer.field_70170_p.field_73012_v.nextDouble() * 4.0D - 2.0D) * 6.0D;
          if (flyer.field_70170_p.field_73012_v.nextInt(2) != 0) {
            dX = newX - flyer.field_70165_t;
            dZ = newZ - flyer.field_70161_v;
          }
          if (flyer.func_70092_e(X, Y, Z) <= 1.0D) {
            dY = ((flyer.field_70163_u > Y) && (newY > 0.0D) ? -newY : newY) - flyer.field_70163_u;
          } else {
            dY = newY - flyer.field_70163_u;
          }
          
          trajectory = dX * dX + dY * dY + dZ * dZ;
          trajectory = MathHelper.func_76133_a(trajectory);
        }
        
        double ACCELERATION = 0.2D;
        flyer.field_70159_w += dX / trajectory * 0.2D;
        flyer.field_70179_y += dZ / trajectory * 0.2D;
        


        flyer.field_70181_x += dY / trajectory * 0.2D + (flyer.field_70163_u < Math.min(Y + (carryRequirement == CarryRequirement.HELD_ITEM ? 32 : 32), 255.0D) ? 0.1D : 0.0D);
        
        courseTimer = 10;
      }
      
      flyer.field_70761_aq = (flyer.field_70177_z = -(float)Math.atan2(flyer.field_70159_w, flyer.field_70179_y) * 180.0F / 3.1415927F);
    }
  }
  
  private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
    double d4 = (par1 - flyer.field_70165_t) / par7;
    double d5 = (par3 - flyer.field_70163_u) / par7;
    double d6 = (par5 - flyer.field_70161_v) / par7;
    
    AxisAlignedBB axisalignedbb = flyer.field_70121_D.func_72329_c();
    
    for (int i = 1; i < par7; i++) {
      axisalignedbb.func_72317_d(d4, d5, d6);
      
      if (!flyer.field_70170_p.func_72945_a(flyer, axisalignedbb).isEmpty()) {
        return false;
      }
    }
    
    return true;
  }
}
