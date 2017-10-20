package com.emoniph.witchery.ritual;

import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Coord;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public abstract class RitualStep
{
  protected boolean canRelocate = false;
  protected int sourceX;
  
  protected RitualStep(boolean canRelocate) { this.canRelocate = canRelocate; }
  
  public static enum Result
  {
    STARTING,  UPKEEP,  COMPLETED,  ABORTED,  ABORTED_REFUND;
    
    private Result() {}
  }
  
  public static class SacrificedItem { public final ItemStack itemstack;
    public final Coord location;
    
    public SacrificedItem(ItemStack itemstack, Coord location) { this.itemstack = itemstack;
      this.location = location;
    }
  }
  

  protected int sourceY;
  protected int sourceZ;
  public Result run(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
  {
    sourceX = posX;
    sourceZ = posZ;
    sourceY = posY;
    
    if ((canRelocate) && (ritual.getLocation() != null)) {
      Coord l = ritual.getLocation();
      int maxDistance = 50 + 50 * covenSize;
      int maxDistanceSq = maxDistance * maxDistance;
      if (l.distanceSqTo(sourceX, sourceY, sourceZ) > maxDistanceSq) {
        EntityPlayer player = ritual.getInitiatingPlayer(world);
        if (player != null) {
          ChatUtil.sendTranslated(player, "witchery.rite.toofaraway", new Object[0]);
        }
        return Result.ABORTED_REFUND;
      }
      posX = x;
      posY = y;
      posZ = z;
    }
    return process(world, posX, posY, posZ, ticks, ritual);
  }
  
  public abstract Result process(World paramWorld, int paramInt1, int paramInt2, int paramInt3, long paramLong, BlockCircle.TileEntityCircle.ActivatedRitual paramActivatedRitual);
  
  public int getCurrentStage() {
    return 0;
  }
}
