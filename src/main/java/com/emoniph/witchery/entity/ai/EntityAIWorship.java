package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.entity.EntityGoblin;

public class EntityAIWorship extends net.minecraft.entity.ai.EntityAIBase
{
  private final EntityGoblin goblin;
  private final double maxDuration;
  private int currentTick;
  private boolean shouldBegin;
  private int posX;
  private int posY;
  private int posZ;
  
  public EntityAIWorship(EntityGoblin goblin, double maxDuration) {
    this.goblin = goblin;
    this.maxDuration = maxDuration;
    func_75248_a(15);
  }
  
  public boolean func_75250_a()
  {
    return (shouldBegin) || (goblin.isWorshipping());
  }
  
  public void func_75249_e()
  {
    currentTick = 0;
    shouldBegin = false;
    goblin.setWorshipping(true);
    goblin.func_70661_as().func_75492_a(posX, posY, posZ, 0.4D);
  }
  
  public boolean func_75253_b()
  {
    return (currentTick <= maxDuration) || (goblin.field_70170_p.field_73012_v.nextInt(3) == 0);
  }
  
  public void func_75251_c()
  {
    goblin.setWorshipping(false);
  }
  
  public void func_75246_d()
  {
    currentTick += 1;
  }
  
  public void begin(net.minecraft.tileentity.TileEntity tile) {
    if (goblin.field_70170_p.field_73012_v.nextInt(3) != 0) {
      shouldBegin = true;
      posX = field_145851_c;
      posY = field_145848_d;
      posZ = field_145849_e;
    }
  }
}
