package com.emoniph.witchery.entity.ai;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;



public class EntityAIFlyerLand
  extends EntityAIBase
{
  private double speed;
  int[] target;
  World worldObj;
  public int courseChangeCooldown;
  public double waypointX;
  public double waypointY;
  public double waypointZ;
  public boolean findTrees;
  EntityLiving living;
  
  public EntityAIFlyerLand(EntityLiving par1EntityCreature, double par2, boolean findTrees)
  {
    living = par1EntityCreature;
    worldObj = living.field_70170_p;
    speed = par2;
    this.findTrees = findTrees;
    func_75248_a(1);
  }
  



  public boolean func_75250_a()
  {
    return (!isLanded()) && (!liquidBelow((int)living.field_70163_u - 1)) && (!liquidBelow((int)living.field_70163_u)) && (worldObj.field_73012_v.nextInt(20) == 0);
  }
  
  private boolean liquidBelow(int y) {
    return worldObj.func_147439_a(MathHelper.func_76128_c(living.field_70165_t), y, MathHelper.func_76128_c(living.field_70161_v)).func_149688_o().func_76224_d();
  }
  


  public boolean func_75253_b()
  {
    boolean cont = (!isLanded()) && (!liquidBelow((int)living.field_70163_u - 1)) && (!liquidBelow((int)living.field_70163_u));
    return cont;
  }
  


  public void func_75249_e()
  {
    courseChangeCooldown = 100;
    int x0 = MathHelper.func_76128_c(living.field_70165_t);
    int y0 = MathHelper.func_76128_c(living.field_70163_u);
    int z0 = MathHelper.func_76128_c(living.field_70161_v);
    
    target = (findTrees ? findTreeTop(x0, y0, z0) : null);
    if (target == null) {
      target = findGround(x0, y0, z0);
    }
    
    if (target != null) {}
  }
  


  public void func_75251_c()
  {
    target = null;
    super.func_75251_c();
  }
  
  private int[] findTreeTop(int x0, int y0, int z0) {
    int RADIUS = 16;
    int Y_RADIUS = 3;
    for (int y = Math.max(y0 - 3, 1); y <= y0 + 3; y++) {
      for (int x = x0 - 16; x <= x0 + 16; x++) {
        for (int z = z0 - 16; z <= z0 + 16; z++) {
          Block blockID = worldObj.func_147439_a(x, y, z);
          if (blockID.func_149688_o() == Material.field_151584_j) {
            for (int y2 = y; y2 < y0 + 10; y2++) {
              if (worldObj.func_147437_c(x, y2, z)) {
                double d0 = x - living.field_70165_t;
                double d1 = y2 - living.field_70163_u;
                double d2 = z - living.field_70161_v;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                d3 = MathHelper.func_76133_a(d3);
                if (isCourseTraversable(x, y2, z, d3)) {
                  return new int[] { x, y2 + 2, z };
                }
              }
            }
          }
        }
      }
    }
    
    return null;
  }
  
  private int[] findGround(int x0, int y0, int z0) {
    for (int y = y0; y > 1; y--) {
      Material material = worldObj.func_147439_a(x0, y, z0).func_149688_o();
      if (material != Material.field_151579_a) {
        if (!material.func_76224_d()) {
          return new int[] { x0, y + 1, z0 };
        }
        for (int i = 0; i < 10; i++) {
          int j = MathHelper.func_76128_c(living.field_70165_t + worldObj.field_73012_v.nextInt(20) - 10.0D);
          int k = MathHelper.func_76128_c(living.field_70121_D.field_72338_b + worldObj.field_73012_v.nextInt(6) - 3.0D);
          int l = MathHelper.func_76128_c(living.field_70161_v + worldObj.field_73012_v.nextInt(20) - 10.0D);
          Block blockID = worldObj.func_147439_a(j, k, l);
          double d0 = j - living.field_70165_t;
          double d1 = k - living.field_70163_u;
          double d2 = l - living.field_70161_v;
          double d3 = d0 * d0 + d1 * d1 + d2 * d2;
          d3 = MathHelper.func_76133_a(d3);
          if (((blockID.func_149688_o() == Material.field_151584_j) || (blockID.func_149688_o().func_76220_a())) && (worldObj.func_147437_c(j, k + 1, l)) && (isCourseTraversable(j, k, l, d3)))
          {
            return new int[] { j, k + 1, l };
          }
        }
      }
    }
    
    return null;
  }
  
  public void func_75246_d()
  {
    if (!isLanded())
    {
      if ((target != null) && (living.func_70092_e(target[0], living.field_70163_u, target[2]) > 1.0D) && (courseChangeCooldown-- > 0)) {
        double d0 = target[0] - living.field_70165_t;
        double d1 = target[1] - living.field_70163_u;
        double d2 = target[2] - living.field_70161_v;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;
        
        d3 = MathHelper.func_76133_a(d3);
        
        if (isCourseTraversable(target[0], target[1], target[2], d3)) {
          living.field_70159_w += d0 / d3 * 0.05D;
          living.field_70181_x += d1 / d3 * 0.05D;
          living.field_70179_y += d2 / d3 * 0.05D;
        }
        

      }
      else if (!liquidBelow((int)(living.field_70163_u - 1.0D))) {
        living.field_70181_x = -0.1D;
      }
      
      living.field_70761_aq = (living.field_70177_z = -(float)Math.atan2(living.field_70159_w, living.field_70179_y) * 180.0F / 3.1415927F);
    }
    


















































    living.field_70761_aq = (living.field_70177_z = -(float)Math.atan2(living.field_70159_w, living.field_70179_y) * 180.0F / 3.1415927F);
  }
  
  private boolean isLanded() {
    Block blockID = worldObj.func_147439_a(MathHelper.func_76128_c(living.field_70165_t), (int)(living.field_70163_u - 0.01D), MathHelper.func_76128_c(living.field_70161_v));
    
    Material material = blockID.func_149688_o();
    if ((material == Material.field_151584_j) || (material.func_76220_a())) {
      return true;
    }
    return false;
  }
  
  private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
    double d4 = (par1 - living.field_70165_t) / par7;
    double d5 = (par3 - living.field_70163_u) / par7;
    double d6 = (par5 - living.field_70161_v) / par7;
    
    AxisAlignedBB axisalignedbb = living.field_70121_D.func_72329_c();
    
    for (int i = 1; i < par7; i++) {
      axisalignedbb.func_72317_d(d4, d5, d6);
      
      if (!worldObj.func_72945_a(living, axisalignedbb).isEmpty()) {
        return false;
      }
    }
    
    return true;
  }
}
