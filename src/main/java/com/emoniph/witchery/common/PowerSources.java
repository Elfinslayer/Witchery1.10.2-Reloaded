package com.emoniph.witchery.common;

import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.Log;
import cpw.mods.fml.common.FMLCommonHandler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class PowerSources
{
  private static PowerSources INSTANCE_CLIENT;
  private static PowerSources INSTANCE_SERVER;
  
  public static PowerSources instance()
  {
    if (FMLCommonHandler.instance().getEffectiveSide() == cpw.mods.fml.relauncher.Side.SERVER) {
      return INSTANCE_SERVER;
    }
    
    return INSTANCE_CLIENT;
  }
  
  public static void initiate() {
    INSTANCE_CLIENT = new PowerSources();
    INSTANCE_SERVER = new PowerSources();
  }
  
  private PowerSources() {}
  
  public static class RelativePowerSource
  {
    private final IPowerSource powerSource;
    private final double distanceSq;
    private final double rangeSq;
    
    public RelativePowerSource(IPowerSource powerSource, Coord relativeLocation) {
      this.powerSource = powerSource;
      distanceSq = relativeLocation.distanceSqTo(this.powerSource.getLocation());
      double range = powerSource.getRange();
      rangeSq = (range * range);
    }
    
    public boolean equals(Object obj)
    {
      if (obj == this)
        return true;
      if ((obj == null) || (obj.getClass() != getClass())) {
        return false;
      }
      return powerSource == powerSource;
    }
    
    public boolean isInWorld(World world)
    {
      return powerSource.getWorld() == world;
    }
    
    public IPowerSource source() {
      return powerSource;
    }
    
    public boolean isInRange() {
      return distanceSq <= rangeSq;
    }
  }
  
  private final ArrayList<IPowerSource> powerSources = new ArrayList();
  private final ArrayList<INullSource> nullSources = new ArrayList();
  



  public String getDebugData()
  {
    StringBuilder sb = new StringBuilder();
    for (IPowerSource source : powerSources) {
      if (sb.length() > 0) {
        sb.append('\n');
      }
      sb.append(String.format("Altar (%s) [dim=%d] power=%f", new Object[] { source.getLocation(), Integer.valueOf(getWorldfield_73011_w.field_76574_g), Float.valueOf(source.getCurrentPower()) }));
    }
    
    return sb.length() > 0 ? sb.insert(0, "** ALTARS **\n").toString() : "No power sources";
  }
  
  public void registerPowerSource(IPowerSource powerSource) {
    if (!powerSources.contains(powerSource)) {
      try {
        Iterator<IPowerSource> it = powerSources.iterator();
        while (it.hasNext()) {
          IPowerSource source = (IPowerSource)it.next();
          
          if ((source == null) || (source.isPowerInvalid()) || (source.getLocation().equals(powerSource.getLocation())))
          {
            it.remove();
          }
        }
      } catch (Throwable e) {
        Log.instance().warning(e, "Exception occured validating existing power source entries");
      }
      powerSources.add(powerSource);
    }
  }
  
  public void removePowerSource(IPowerSource powerSource) {
    if (powerSources.contains(powerSource)) {
      powerSources.remove(powerSource);
    }
    try {
      Iterator<IPowerSource> it = powerSources.iterator();
      while (it.hasNext()) {
        IPowerSource source = (IPowerSource)it.next();
        
        if ((source == null) || (source.isPowerInvalid())) {
          it.remove();
        } else if (source.getLocation().getBlockTileEntity(source.getWorld()) != source) {
          it.remove();
        }
      }
    } catch (Throwable e) {
      Log.instance().warning(e, "Exception occured removing existing power source entries");
    }
  }
  
  public ArrayList<RelativePowerSource> get(World world, Coord location, int radius) {
    ArrayList<RelativePowerSource> nearbyPowerSources = new ArrayList();
    double radiusSq = radius * radius;
    
    for (IPowerSource registeredSource : powerSources) {
      RelativePowerSource powerSource = new RelativePowerSource(registeredSource, location);
      if ((powerSource.isInWorld(world)) && (powerSource.isInRange())) {
        nearbyPowerSources.add(powerSource);
      }
    }
    
    java.util.Collections.sort(nearbyPowerSources, new java.util.Comparator()
    {
      public int compare(PowerSources.RelativePowerSource a, PowerSources.RelativePowerSource b) {
        return Double.compare(distanceSq, distanceSq);
      }
      
    });
    return nearbyPowerSources;
  }
  
  public void registerNullSource(INullSource nullSource) {
    if (!nullSources.contains(nullSource)) {
      Coord newLocation = new Coord(nullSource);
      try {
        Iterator<INullSource> it = nullSources.iterator();
        while (it.hasNext()) {
          INullSource source = (INullSource)it.next();
          
          if ((source == null) || (source.isPowerInvalid()) || (new Coord(source).equals(newLocation))) {
            it.remove();
          }
        }
      } catch (Throwable e) {
        Log.instance().warning(e, "Exception occured validating existing null source entries");
      }
      nullSources.add(nullSource);
    }
  }
  
  public void removeNullSource(INullSource nullSource) {
    if (nullSources.contains(nullSource)) {
      nullSources.remove(nullSource);
    }
    try
    {
      Iterator<INullSource> it = nullSources.iterator();
      while (it.hasNext()) {
        INullSource source = (INullSource)it.next();
        
        if ((source == null) || (source.isPowerInvalid())) {
          it.remove();
        } else if (new Coord(source).getBlockTileEntity(source.getWorld()) != source) {
          it.remove();
        }
      }
    } catch (Throwable e) {
      Log.instance().warning(e, "Exception occured removing existing null source entries");
    }
  }
  
  public boolean isAreaNulled(World world, int posX, int posY, int posZ) {
    for (INullSource source : nullSources) {
      double rangeSq = source.getRange() * source.getRange();
      if (Coord.distanceSq(posX, posY, posZ, source.getPosX(), source.getPosY(), source.getPosZ()) < rangeSq) {
        return true;
      }
    }
    
    return false;
  }
  
  public static IPowerSource findClosestPowerSource(World world, int posX, int posY, int posZ) {
    List<RelativePowerSource> sources = instance() != null ? instance().get(world, new Coord(posX, posY, posZ), 16) : null;
    
    return (sources != null) && (sources.size() > 0) ? ((RelativePowerSource)sources.get(0)).source() : null;
  }
  
  public static IPowerSource findClosestPowerSource(World world, Coord coord) {
    return findClosestPowerSource(world, x, y, z);
  }
  
  public static IPowerSource findClosestPowerSource(TileEntity tile) {
    return findClosestPowerSource(tile.func_145831_w(), field_145851_c, field_145848_d, field_145849_e);
  }
}
