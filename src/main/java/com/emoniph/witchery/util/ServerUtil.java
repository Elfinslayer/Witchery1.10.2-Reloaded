package com.emoniph.witchery.util;

import net.minecraft.world.WorldServer;

public class ServerUtil {
  public ServerUtil() {}
  
  public static WorldServer getWorld(int dimension) {
    for (WorldServer world : func_71276_Cfield_71305_c) {
      if (field_73011_w.field_76574_g == dimension) {
        return world;
      }
    }
    return null;
  }
}
