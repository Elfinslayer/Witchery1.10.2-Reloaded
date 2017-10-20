package com.emoniph.witchery.util;

import net.minecraft.server.MinecraftServer;

public class TimeUtil { public TimeUtil() {}
  
  public static int secsToTicks(int seconds) { return seconds * 20; }
  
  public static int minsToTicks(int minutes)
  {
    return minutes * 1200;
  }
  
  public static boolean secondsElapsed(int seconds, long ticksExisted) {
    return ticksExisted % secsToTicks(seconds) == 0L;
  }
  
  public static boolean ticksElapsed(int ticks, long ticksExisted) {
    return ticksExisted % ticks == 0L;
  }
  
  public static long ticksToSecs(long ticks) {
    return ticks / 20L;
  }
  
  public static long minsToMillisecs(int mins) {
    return mins * 60000;
  }
  
  public static long secsToMillisecs(int secs) {
    return secs * 1000;
  }
  
  public static long getServerTimeInTicks() {
    return MinecraftServer.func_130071_aq() / 50L;
  }
}
