package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.ritual.RitualStep.SacrificedItem;
import com.emoniph.witchery.util.Coord;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteTeleportToWaystone extends RiteTeleportation
{
  public RiteTeleportToWaystone(int radius)
  {
    super(radius);
  }
  
  protected boolean teleport(World world, int posX, int posY, int posZ, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
  {
    if (!field_72995_K) {
      net.minecraft.item.ItemStack waystoneStack = null;
      for (RitualStep.SacrificedItem item : sacrificedItems) {
        if ((ItemsGENERIC.itemWaystoneBound.isMatch(itemstack)) || (ItemsGENERIC.itemWaystonePlayerBound.isMatch(itemstack)))
        {
          waystoneStack = itemstack;
          break;
        }
      }
      
      if (waystoneStack != null) {
        AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - radius, posY - radius, posZ - radius, posX + radius, posY + radius, posZ + radius);
        
        List list = world.func_72872_a(Entity.class, bounds);
        
        boolean sent = false;
        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
          Entity entity = (Entity)iterator.next();
          if ((Coord.distance(field_70165_t, field_70163_u, field_70161_v, posX, posY, posZ) < radius) && (!com.emoniph.witchery.brewing.potions.PotionEnderInhibition.isActive(entity, 1)))
          {
            if (ItemsGENERIC.teleportToLocation(world, waystoneStack, entity, radius, true)) {
              sent = true;
            }
          }
        }
        
        return sent;
      }
    }
    
    return false;
  }
}
