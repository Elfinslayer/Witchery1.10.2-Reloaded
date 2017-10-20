package com.emoniph.witchery.infusion;

import com.emoniph.witchery.brewing.potions.PotionEnslaved;
import com.emoniph.witchery.util.CreatureUtil;
import com.emoniph.witchery.util.TimeUtil;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class InfusedBrewGraveEffect extends InfusedBrewEffect
{
  private static final String LAST_USE_TIME_KEY = "WITCBrewGraveTime";
  private static final long COOLDOWN_TICKS = 200L;
  
  public InfusedBrewGraveEffect(int id, long durationMS)
  {
    super(id, durationMS, 16, 16);
  }
  



  public void immediateEffect(World world, EntityPlayer player, ItemStack stack) {}
  


  public void regularEffect(World world, EntityPlayer player) {}
  


  public boolean tryUseEffect(EntityPlayer player, MovingObjectPosition mop)
  {
    if (isActive(player)) {
      NBTTagCompound nbtPlayer = Infusion.getNBT(player);
      long lastUseTicks = nbtPlayer.func_74763_f("WITCBrewGraveTime");
      long currentServerTime = TimeUtil.getServerTimeInTicks();
      if ((currentServerTime - lastUseTicks > 200L) && 
        (mop != null) && (field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.ENTITY) && (CreatureUtil.isUndead(field_72308_g))) {
        EntityLiving living = (EntityLiving)field_72308_g;
        if (!PotionEnslaved.isMobEnslavedBy(living, player)) {
          PotionEnslaved.setEnslaverForMob(living, player);
          nbtPlayer.func_74772_a("WITCBrewGraveTime", currentServerTime);
          return true;
        }
      }
    }
    

    return false;
  }
}
