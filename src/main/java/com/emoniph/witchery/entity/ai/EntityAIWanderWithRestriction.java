package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.util.Coord;
import java.util.Random;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.Vec3;




public class EntityAIWanderWithRestriction
  extends EntityAIBase
{
  private EntityCreature entity;
  private double xPosition;
  private double yPosition;
  private double zPosition;
  private double speed;
  private IHomeLocationProvider home;
  
  public EntityAIWanderWithRestriction(EntityCreature creature, double speed, IHomeLocationProvider home)
  {
    entity = creature;
    this.speed = speed;
    func_75248_a(1);
    this.home = home;
  }
  
  public boolean func_75250_a() {
    if (entity.func_70654_ax() >= 100)
      return false;
    if (entity.func_70681_au().nextInt(120) != 0) {
      return false;
    }
    Vec3 vec3 = RandomPositionGenerator.func_75463_a(entity, 10, 7);
    
    if (vec3 == null)
      return false;
    if (Coord.distanceSq(field_72450_a, field_72448_b, field_72449_c, home.getHomeX(), home.getHomeY(), home.getHomeZ()) > home.getHomeRange() * home.getHomeRange()) {
      return false;
    }
    xPosition = field_72450_a;
    yPosition = field_72448_b;
    zPosition = field_72449_c;
    return true;
  }
  

  public boolean func_75253_b()
  {
    return !entity.func_70661_as().func_75500_f();
  }
  
  public void func_75249_e() {
    entity.func_70661_as().func_75492_a(xPosition, yPosition, zPosition, speed);
  }
  
  public static abstract interface IHomeLocationProvider
  {
    public abstract double getHomeX();
    
    public abstract double getHomeY();
    
    public abstract double getHomeZ();
    
    public abstract double getHomeRange();
  }
}
