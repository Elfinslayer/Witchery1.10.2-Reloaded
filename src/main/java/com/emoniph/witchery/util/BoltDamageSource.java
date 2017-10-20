package com.emoniph.witchery.util;

import com.emoniph.witchery.entity.EntityBolt;
import net.minecraft.util.EntityDamageSourceIndirect;

public class BoltDamageSource extends EntityDamageSourceIndirect
{
  public final boolean isWooden;
  public final boolean isHoly;
  public final boolean isPoweredDraining;
  
  public BoltDamageSource(EntityBolt bolt, net.minecraft.entity.Entity shooter)
  {
    super("arrow", bolt, shooter);
    func_76349_b();
    isWooden = bolt.isWoodenDamage();
    isPoweredDraining = bolt.isPoweredDraining();
    isHoly = bolt.isHolyDamage();
  }
  
  public String toString()
  {
    return super.toString() + String.format(" Bolt wood=%s holy=%s drain=%s", new Object[] { Boolean.valueOf(isWooden), Boolean.valueOf(isHoly), Boolean.valueOf(isPoweredDraining) });
  }
}
