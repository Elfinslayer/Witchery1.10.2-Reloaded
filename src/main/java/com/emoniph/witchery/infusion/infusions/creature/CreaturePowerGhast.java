package com.emoniph.witchery.infusion.infusions.creature;

import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.util.SoundEffect;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;



public class CreaturePowerGhast
  extends CreaturePower
{
  public CreaturePowerGhast(int powerID)
  {
    super(powerID, EntityGhast.class);
  }
  
  public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop)
  {
    if (!field_72995_K) {
      world.func_72889_a((EntityPlayer)null, 1008, (int)field_70165_t, (int)field_70163_u, (int)field_70161_v, 0);
      float f = 1.0F;
      double motionX = -MathHelper.func_76126_a(field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(field_70125_A / 180.0F * 3.1415927F) * f;
      
      double motionZ = MathHelper.func_76134_b(field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(field_70125_A / 180.0F * 3.1415927F) * f;
      
      double motionY = -MathHelper.func_76126_a(field_70125_A / 180.0F * 3.1415927F) * f;
      EntityLargeFireball entitylargefireball = new EntityLargeFireball(world, player, motionX, motionY, motionZ);
      








      entitylargefireball.func_70012_b(field_70165_t, field_70163_u + player.func_70047_e(), field_70161_v, field_70177_z, field_70125_A);
      
      entitylargefireball.func_70107_b(field_70165_t, field_70163_u + player.func_70047_e(), field_70161_v);
      double d6 = MathHelper.func_76133_a(motionX * motionX + motionY * motionY + motionZ * motionZ);
      field_70232_b = (motionX / d6 * 0.1D);
      field_70233_c = (motionY / d6 * 0.1D);
      field_70230_d = (motionZ / d6 * 0.1D);
      double d8 = 1.0D;
      Vec3 vec3 = player.func_70676_i(1.0F);
      field_70165_t = (field_70165_t + field_72450_a * d8);
      field_70163_u = (field_70163_u + field_70131_O / 2.0F + 0.5D);
      field_70161_v = (field_70161_v + field_72449_c * d8);
      world.func_72838_d(entitylargefireball);
    }
  }
  
  public void onDamage(World world, EntityPlayer player, LivingHurtEvent event)
  {
    if ((source.func_76347_k()) && (event.isCancelable())) {
      int currentEnergy = Infusion.getCurrentEnergy(player);
      if ((currentEnergy >= 3) && (!player.func_82165_m(field_76426_nfield_76415_H))) {
        Infusion.setCurrentEnergy(player, currentEnergy - 3);
        player.func_70690_d(new PotionEffect(field_76426_nfield_76415_H, 200, 0));
        SoundEffect.RANDOM_FIZZ.playAtPlayer(world, player);
      }
    }
  }
}
