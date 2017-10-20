package com.emoniph.witchery.brewing.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class PotionSunAllergy extends PotionBase implements IHandleLivingUpdate
{
  public PotionSunAllergy(int id, int color)
  {
    super(id, true, color);
    setIncurable();
  }
  

  public void onLivingUpdate(World world, EntityLivingBase entity, net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
  {
    if ((!field_72995_K) && (world.func_72820_D() % 20L == 0L) && 
      (world.func_72935_r())) {
      float f = entity.func_70013_c(1.0F);
      if ((f > 0.5F) && (!entity.func_70090_H()) && (field_73012_v.nextFloat() < f - 0.45F) && (world.func_72937_j(MathHelper.func_76128_c(field_70165_t), MathHelper.func_76128_c(field_70163_u), MathHelper.func_76128_c(field_70161_v))))
      {



        boolean burnEntity = true;
        ItemStack itemstack = entity.func_71124_b(4);
        
        if ((itemstack != null) && (!(entity instanceof EntityPlayer))) {
          if (itemstack.func_77984_f()) {
            itemstack.func_77964_b(itemstack.func_77952_i() + field_73012_v.nextInt(2));
            
            if (itemstack.func_77952_i() >= itemstack.func_77958_k()) {
              entity.func_70669_a(itemstack);
              entity.func_70062_b(4, (ItemStack)null);
            }
          }
          
          burnEntity = false;
        }
        
        if (burnEntity) {
          if ((entity instanceof EntityPlayer)) {
            entity.func_70097_a(net.minecraft.util.DamageSource.field_76380_i, amplifier >= 3 ? 2.0F : 1.0F);
          } else {
            entity.func_70015_d(8);
          }
        }
      }
    }
  }
}
