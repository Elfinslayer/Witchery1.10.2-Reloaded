package com.emoniph.witchery.util;

import net.minecraft.entity.Entity;

public final class TargetPointUtil {
  public TargetPointUtil() {}
  
  public static cpw.mods.fml.common.network.NetworkRegistry.TargetPoint from(Entity entity, double range) {
    if (entity != null) {
      return new cpw.mods.fml.common.network.NetworkRegistry.TargetPoint(field_71093_bK, field_70165_t, field_70163_u, field_70161_v, range);
    }
    return new cpw.mods.fml.common.network.NetworkRegistry.TargetPoint(0, 0.0D, 0.0D, 0.0D, range);
  }
  
  public static cpw.mods.fml.common.network.NetworkRegistry.TargetPoint from(net.minecraft.world.World world, double x, double y, double z, double range)
  {
    return new cpw.mods.fml.common.network.NetworkRegistry.TargetPoint(field_73011_w.field_76574_g, x, y, z, range);
  }
}
