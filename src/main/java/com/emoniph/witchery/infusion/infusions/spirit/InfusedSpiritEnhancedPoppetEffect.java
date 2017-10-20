package com.emoniph.witchery.infusion.infusions.spirit;

import java.util.ArrayList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;

public class InfusedSpiritEnhancedPoppetEffect extends InfusedSpiritEffect
{
  public InfusedSpiritEnhancedPoppetEffect(int id, int spirits, int spectres, int banshees, int poltergeists)
  {
    super(id, "enhancedpoppets", spirits, spectres, banshees, poltergeists);
  }
  
  public boolean doUpdateEffect(TileEntity tile, boolean triggered, ArrayList<EntityLivingBase> foundEntities)
  {
    return false;
  }
}
