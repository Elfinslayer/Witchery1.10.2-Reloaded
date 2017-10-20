package com.emoniph.witchery.brewing;

import com.emoniph.witchery.util.BlockPosition;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;




public class ModifiersRitual
{
  public int permenance;
  public RitualStatus status = RitualStatus.COMPLETE;
  
  public final ArrayList<BlockPosition> targetLocations = new ArrayList();
  
  public final int covenSize;
  public final int pulses;
  public final BlockPosition cauldronLocation;
  public final boolean leonard;
  public boolean taglockUsed;
  
  public ModifiersRitual(BlockPosition cauldronLocation, int covenSize, int ritualPulses, boolean lennyPresent)
  {
    this.covenSize = covenSize;
    pulses = ritualPulses;
    this.cauldronLocation = cauldronLocation;
    leonard = lennyPresent;
  }
  
  public void setTarget(ItemStack stack) {
    setTarget(BlockPosition.from(stack));
  }
  
  public BlockPosition getTarget() {
    return getTarget(0);
  }
  
  public BlockPosition getTarget(int index) {
    if ((targetLocations.isEmpty()) || (index >= targetLocations.size())) {
      return cauldronLocation;
    }
    return (BlockPosition)targetLocations.get(index);
  }
  
  public void setTarget(BlockPosition target)
  {
    targetLocations.add(target);
  }
  
  public ModifiersRitual setRitualStatus(RitualStatus status) {
    this.status = status;
    return this;
  }
  
  public RitualStatus getRitualStatus() {
    return status;
  }
}
