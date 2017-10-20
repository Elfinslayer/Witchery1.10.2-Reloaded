package com.emoniph.witchery.predictions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;

public abstract class Prediction
{
  public final int predictionID;
  public final double itemWeight;
  protected final String translationKey;
  protected final double selfFulfillmentProbabilityPerSec;
  
  public Prediction(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey)
  {
    predictionID = id;
    this.itemWeight = itemWeight;
    this.translationKey = translationKey;
    this.selfFulfillmentProbabilityPerSec = selfFulfillmentProbabilityPerSec;
  }
  
  public boolean shouldTrySelfFulfill(World world, EntityPlayer player) {
    return field_73012_v.nextDouble() < selfFulfillmentProbabilityPerSec;
  }
  
  public boolean doSelfFulfillment(World world, EntityPlayer player) {
    return false;
  }
  
  public boolean checkIfFulfilled(World world, EntityPlayer player, LivingHurtEvent event, boolean isPastDue, boolean veryOld) {
    return false;
  }
  
  public boolean checkIfFulfilled(World world, EntityPlayer player, PlayerInteractEvent event, boolean isPastDue, boolean veryOld) {
    return false;
  }
  
  public boolean checkIfFulfilled(World world, EntityPlayer player, LivingEvent.LivingUpdateEvent event, boolean isPastDue, boolean veryOld) {
    return false;
  }
  
  public boolean checkIfFulfilled(World worldObj, EntityPlayer player, BlockEvent.HarvestDropsEvent event, boolean isPastDue, boolean veryOld) {
    return false;
  }
  
  public NBTTagCompound createTagCompound(World world) {
    NBTTagCompound compound = new NBTTagCompound();
    compound.func_74768_a("WITCPreID", predictionID);
    compound.func_74772_a("WITCPreTime", com.emoniph.witchery.util.TimeUtil.getServerTimeInTicks());
    return compound;
  }
  
  public String getTranslationKey() {
    return translationKey;
  }
  
  public boolean isPredictionPastDue(long predictionTime, long currentTime) {
    return currentTime - predictionTime > 9600L;
  }
  
  public boolean isPredictionPossible(World world, EntityPlayer player) {
    return true;
  }
}
