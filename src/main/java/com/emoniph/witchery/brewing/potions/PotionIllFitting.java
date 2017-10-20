package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.entity.EntityFollower;
import com.emoniph.witchery.entity.EntityReflection;
import java.util.Random;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.world.World;

public class PotionIllFitting extends PotionBase
{
  public PotionIllFitting(int id, int color)
  {
    super(id, true, color);
  }
  
  public void postContructInitialize()
  {
    setPermenant();
    setIncurable();
  }
  
  public boolean func_76397_a(int duration, int amplifier)
  {
    if (duration % 15 == 0) {
      switch (amplifier) {
      case 3: 
        return duration <= 60;
      case 2: 
        return duration <= 45;
      case 1: 
        return duration <= 30;
      }
      return duration <= 15;
    }
    
    return false;
  }
  

  public void func_76394_a(EntityLivingBase entity, int amplifier)
  {
    World world = field_70170_p;
    if ((!field_72995_K) && (!isTargetBanned(entity))) {
      int slot = field_73012_v.nextInt(4) + 1;
      net.minecraft.item.ItemStack armorPiece = entity.func_71124_b(slot);
      if (armorPiece != null) {
        entity.func_70062_b(slot, null);
        EntityItem droppedItem = entity.func_70099_a(armorPiece, 0.0F);
        field_145804_b = (5 + 5 * amplifier);
      }
    }
  }
  
  public static boolean isTargetBanned(EntityLivingBase entity) {
    return ((entity instanceof EntityReflection)) || ((entity instanceof EntityFollower));
  }
}
