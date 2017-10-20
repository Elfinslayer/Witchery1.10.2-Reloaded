package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.common.ExtendedVillager;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.village.VillageDoorInfo;
import net.minecraft.world.World;

public class EntityAISleep extends net.minecraft.entity.ai.EntityAIBase
{
  private EntityVillager villager;
  private VillageDoorInfo doorInfo;
  private int insidePosX = -1;
  private int insidePosZ = -1;
  private World world;
  Village village;
  
  public EntityAISleep(EntityVillager villager) {
    this.villager = villager;
    world = field_70170_p;
    func_75248_a(7);
  }
  
  public boolean func_75250_a() {
    long time = world.func_72820_D() % 24000L;
    if ((time < 13000L) || (time >= 23999L) || (villager.field_70737_aN > 0)) {
      return false;
    }
    
    if (villager.func_70681_au().nextInt(50) != 0) {
      return false;
    }
    
    int i = MathHelper.func_76128_c(villager.field_70165_t);
    int j = MathHelper.func_76128_c(villager.field_70163_u);
    int k = MathHelper.func_76128_c(villager.field_70161_v);
    
    Village village = world.field_72982_D.func_75550_a(i, j, k, 14);
    
    if (village == null) {
      return false;
    }
    doorInfo = village.func_75569_c(i, j, k);
    
    float DOOR_DIST = 4.0F;
    boolean inside = villager.func_70092_e(doorInfo.func_75471_a() + 0.5D, doorInfo.func_75473_b(), doorInfo.func_75472_c() + 0.5D) < 16.0D;
    





    if (villager.field_70170_p.func_72937_j(i, j, k)) {
      return false;
    }
    
    int count = 0;
    for (int x = i - 1; x <= i + 1; x++) {
      for (int z = k - 1; z <= k + 1; z++) {
        if ((!villager.field_70170_p.func_72937_j(x, j, z)) && (villager.field_70170_p.func_147439_a(x, j + 1, z).func_149688_o().func_76222_j()))
        {
          count++;
        }
      }
    }
    if (count < 4) {
      return false;
    }
    
    count = 6;
    
    count = 0;
    for (int x = -1; x <= 1; x++) {
      for (int z = -1; z <= 1; z++) {
        if (!world.func_147439_a(x + i, j - 1, z + k).isReplaceable(world, x + i, j - 1, z + k)) {
          count++;
        }
      }
    }
    

    return count >= 6;
  }
  
  public void func_75249_e()
  {
    ExtendedVillager ext = ExtendedVillager.get(villager);
    if (ext != null) {
      ext.setSleeping(true);
    }
  }
  
  public void func_75251_c() {
    village = null;
    ExtendedVillager ext = ExtendedVillager.get(villager);
    if (ext != null) {
      ext.setSleeping(false);
    }
  }
  
  public boolean func_75253_b() {
    long time = world.func_72820_D() % 24000L;
    return (time > 13000L) && (time < 23999L) && (villager.field_70737_aN == 0);
  }
  
  public void func_75246_d() {
    ExtendedVillager ext = ExtendedVillager.get(villager);
    if (ext != null) {
      ext.incrementSleepingTicks();
    }
  }
}
