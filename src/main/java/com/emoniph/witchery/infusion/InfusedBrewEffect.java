package com.emoniph.witchery.infusion;

import com.emoniph.witchery.util.TimeUtil;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;




public abstract class InfusedBrewEffect
{
  public static final ArrayList<InfusedBrewEffect> brewList = new ArrayList();
  
  public static final InfusedBrewEffect Soaring = new InfusedBrewSoaringEffect(1, 144000L);
  public static final InfusedBrewEffect Grave = new InfusedBrewGraveEffect(2, 144000L);
  public final int id;
  public final long durationTicks;
  public final int imageMapX;
  public final int imageMapY;
  
  protected InfusedBrewEffect(int id, long durationMS, int imageX, int imageY)
  {
    this.id = id;
    durationTicks = durationMS;
    imageMapX = imageX;
    imageMapY = imageY;
    while (brewList.size() <= id) {
      brewList.add(null);
    }
    brewList.set(id, this);
  }
  
  public void drunk(World world, EntityPlayer player, ItemStack itemstack) {
    setActiveBrew(this, player, true);
    immediateEffect(world, player, itemstack);
  }
  
  public abstract void immediateEffect(World paramWorld, EntityPlayer paramEntityPlayer, ItemStack paramItemStack);
  
  public abstract void regularEffect(World paramWorld, EntityPlayer paramEntityPlayer);
  
  public boolean tryUseEffect(EntityPlayer player, MovingObjectPosition mop) {
    return isActive(player);
  }
  
  public boolean isActive(EntityPlayer player) {
    return getActiveBrew(player) == this;
  }
  
  private static String BREW_TYPE_KEY = "WITCInfusedBrewType";
  private static String BREW_START_KEY = "WITCInfusedBrewStart";
  private static String BREW_MINS_LEFT_KEY = "WITCInfusedBrewMinesLeft";
  
  public static InfusedBrewEffect getActiveBrew(EntityPlayer player) {
    if (player != null) {
      NBTTagCompound nbtPlayer = Infusion.getNBT(player);
      return getActiveBrew(nbtPlayer);
    }
    return null;
  }
  
  public static InfusedBrewEffect getActiveBrew(NBTTagCompound nbtPlayer) {
    if (nbtPlayer != null) {
      int brewID = nbtPlayer.func_74762_e(BREW_TYPE_KEY);
      if (brewID > 0) {
        return (InfusedBrewEffect)brewList.get(brewID);
      }
    }
    return null;
  }
  
  public static long getActiveBrewStartTime(NBTTagCompound nbtPlayer) {
    if (nbtPlayer != null) {
      long startTime = nbtPlayer.func_74763_f(BREW_START_KEY);
      return startTime;
    }
    return 0L;
  }
  
  public static String getMinutesRemaining(World world, NBTTagCompound nbtPlayer, InfusedBrewEffect effect) {
    if (nbtPlayer != null) {
      long minsLeft = nbtPlayer.func_74763_f(BREW_MINS_LEFT_KEY);
      return String.format("%d", new Object[] { Long.valueOf(minsLeft) });
    }
    





    return "";
  }
  
  public static void setActiveBrew(InfusedBrewEffect brew, EntityPlayer player, boolean sync) {
    if (player != null) {
      NBTTagCompound nbtPlayer = Infusion.getNBT(player);
      setActiveBrew(field_70170_p, player, nbtPlayer, brew, sync);
    }
  }
  
  public static void setActiveBrew(World world, EntityPlayer player, NBTTagCompound nbtPlayer, InfusedBrewEffect brew, boolean sync) {
    if ((nbtPlayer != null) && (!field_72995_K))
    {
      nbtPlayer.func_74768_a(BREW_TYPE_KEY, id);
      nbtPlayer.func_74772_a(BREW_START_KEY, TimeUtil.getServerTimeInTicks());
      
      if (sync) {
        Infusion.syncPlayer(world, player);
      }
    }
  }
  
  public static void setActiveBrewInfo(NBTTagCompound nbtPlayer, int brewID, long startTime) {
    nbtPlayer.func_74768_a(BREW_TYPE_KEY, brewID);
    nbtPlayer.func_74772_a(BREW_MINS_LEFT_KEY, startTime);
  }
  
  public static void checkActiveEffects(World world, EntityPlayer player, NBTTagCompound nbtPlayer, boolean sync, long currentTime)
  {
    if ((nbtPlayer != null) && (!field_72995_K)) {
      InfusedBrewEffect activeEffect = getActiveBrew(nbtPlayer);
      if (activeEffect != null) {
        long startTime = nbtPlayer.func_74763_f(BREW_START_KEY);
        if (currentTime > startTime + durationTicks) {
          nbtPlayer.func_82580_o(BREW_START_KEY);
          nbtPlayer.func_82580_o(BREW_TYPE_KEY);
          
          Infusion.syncPlayer(world, player);
          return;
        }
        activeEffect.regularEffect(world, player);
        

        if (sync) {
          Infusion.syncPlayer(world, player);
        }
      }
    }
  }
}
