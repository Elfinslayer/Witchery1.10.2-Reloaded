package com.emoniph.witchery.brewing.potions;

import java.util.Random;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class PotionOverheating extends PotionBase implements IHandleLivingUpdate
{
  public PotionOverheating(int id, int color)
  {
    super(id, true, color);
  }
  
  public void postContructInitialize()
  {
    setPermenant();
    setIncurable();
  }
  
  public void onLivingUpdate(World world, EntityLivingBase entity, net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
  {
    if ((!field_72995_K) && (world.func_82737_E() % 5L == 3L) && 
      (!entity.func_70027_ad())) if (field_73012_v.nextInt(amplifier > 0 ? 25 : amplifier > 1 ? 20 : 30) == 0) {
        int x = net.minecraft.util.MathHelper.func_76128_c(field_70165_t);
        int z = net.minecraft.util.MathHelper.func_76128_c(field_70161_v);
        BiomeGenBase biome = world.func_72807_a(x, z);
        if ((field_76750_F >= 1.5D) && ((!biome.func_76738_d()) || (!world.func_72896_J())) && (!entity.func_70090_H()))
        {
          entity.func_70015_d(Math.min(field_73012_v.nextInt(amplifier < 3 ? 2 : amplifier) + 1, 4));
        }
      }
  }
}
