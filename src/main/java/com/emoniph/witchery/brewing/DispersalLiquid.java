package com.emoniph.witchery.brewing;

import com.emoniph.witchery.util.BlockPosition;
import com.emoniph.witchery.util.Coord;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class DispersalLiquid extends Dispersal
{
  public DispersalLiquid() {}
  
  public void onImpactSplashPotion(World world, NBTTagCompound nbtBrew, MovingObjectPosition mop, ModifiersImpact modifiers)
  {
    Coord coord = new Coord(mop, impactPosition, true);
    boolean replaceable = com.emoniph.witchery.util.BlockUtil.isReplaceableBlock(world, x, y, z, thrower);
    
    if (replaceable) {
      coord.setBlock(world, BlocksBREW_LIQUID);
      TileEntityBrewFluid gas = (TileEntityBrewFluid)coord.getTileEntity(world, TileEntityBrewFluid.class);
      if (gas != null) {
        gas.initalise(modifiers, nbtBrew);
      }
    }
  }
  
  public String getUnlocalizedName()
  {
    return "witchery:brew.dispersal.liquid";
  }
  

  public RitualStatus onUpdateRitual(World world, int x, int y, int z, NBTTagCompound nbtBrew, ModifiersRitual modifiers, ModifiersImpact impactModifiers)
  {
    BlockPosition target = modifiers.getTarget();
    World targetWorld = target.getWorld(net.minecraft.server.MinecraftServer.func_71276_C());
    
    int radius = 16 + 8 * extent;
    int maxQuantity = radius;
    int halfQuantity = maxQuantity / 4;
    int height = 100;
    
    double RSQ = radius * radius;
    int i = 0; for (int quantity = halfQuantity + field_73012_v.nextInt(halfQuantity); i < quantity; i++) {
      int ny = 100 + field_73012_v.nextInt(20);
      int nx = x - radius + field_73012_v.nextInt(2 * radius);
      int nz = z - radius + field_73012_v.nextInt(2 * radius);
      if (Coord.distanceSq(x, y, z, nx, y, nz) <= RSQ) {
        com.emoniph.witchery.util.EntityUtil.spawnEntityInWorld(targetWorld, new EntityDroplet(targetWorld, nx, ny, nz, nbtBrew));
      }
    }
    
    return pulses < 10 + lifetime * 5 ? RitualStatus.ONGOING : RitualStatus.COMPLETE;
  }
}
