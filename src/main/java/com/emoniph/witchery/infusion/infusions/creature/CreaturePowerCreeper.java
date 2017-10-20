package com.emoniph.witchery.infusion.infusions.creature;

import com.emoniph.witchery.infusion.Infusion;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class CreaturePowerCreeper extends CreaturePower
{
  private static final float WEB_DAMAGE = 1.0F;
  
  public CreaturePowerCreeper(int powerID)
  {
    super(powerID, net.minecraft.entity.monster.EntityCreeper.class);
  }
  


  private final int explosionRadius = 3;
  
  public int activateCost(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop)
  {
    int baseCost = super.activateCost(world, player, elapsedTicks, mop);
    return elapsedTicks >= 60 ? baseCost * 2 : baseCost;
  }
  
  public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop)
  {
    if (!field_72995_K) {
      getClass();getClass();world.func_72876_a(player, field_70165_t, field_70163_u, field_70161_v, elapsedTicks >= 60 ? 3.0F * 2.0F : 3.0F, world.func_82736_K().func_82766_b("mobGriefing"));
    }
  }
  
  public void onDamage(World world, EntityPlayer player, LivingHurtEvent event)
  {
    if (source.func_76347_k()) {
      StackTraceElement[] stack = Thread.currentThread().getStackTrace();
      for (StackTraceElement element : stack) {
        if (element.getMethodName().equals("onStruckByLightning"))
        {
          int power = Infusion.getNBT(player).func_74762_e("witcheryInfusionCharges");
          Infusion.getNBT(player).func_74768_a("witcheryInfusionCharges", Math.min(power + 25, 200));
          Infusion.syncPlayer(world, player);
          if (event.isCancelable()) {
            event.setCanceled(true);
          }
          return;
        }
      }
    }
  }
}
