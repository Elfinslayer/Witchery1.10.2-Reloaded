package com.emoniph.witchery.blocks;

import net.minecraft.tileentity.TileEntity;

public class TileEntityBase extends TileEntity {
  protected long ticks = 0L;
  
  public TileEntityBase() {}
  
  public void func_145845_h() { super.func_145845_h();
    
    if (ticks == 0L) {
      initiate();
    } else if (ticks >= Long.MAX_VALUE) {
      ticks = 1L;
    }
    
    ticks += 1L;
  }
  
  protected void initiate() {}
  
  public void markBlockForUpdate(boolean notifyNeighbours)
  {
    field_145850_b.func_147471_g(field_145851_c, field_145848_d, field_145849_e);
    if ((notifyNeighbours) && (field_145850_b != null)) {
      field_145850_b.func_147444_c(field_145851_c, field_145848_d, field_145849_e, func_145838_q());
    }
  }
}
