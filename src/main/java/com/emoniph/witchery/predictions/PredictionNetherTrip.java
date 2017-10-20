package com.emoniph.witchery.predictions;

import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Log;
import java.util.Random;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class PredictionNetherTrip extends Prediction
{
  public PredictionNetherTrip(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey)
  {
    super(id, itemWeight, selfFulfillmentProbabilityPerSec, translationKey);
  }
  
  public boolean isPredictionPossible(World world, EntityPlayer player)
  {
    try {
      NBTTagCompound nbtPlayer = com.emoniph.witchery.infusion.Infusion.getNBT(player);
      boolean wasInNether = (nbtPlayer != null) && (nbtPlayer.func_74764_b("WITCVisitedNether")) && (nbtPlayer.func_74767_n("WITCVisitedNether"));
      boolean hasBeenToNether = wasInNether;
      return (field_71093_bK != -1) && (hasBeenToNether);
    }
    catch (Throwable e)
    {
      Log.instance().warning(e, "Error occurred while checking if a nether visit has occurred for the nether prediction."); }
    return false;
  }
  

  public boolean doSelfFulfillment(World world2, EntityPlayer player)
  {
    int FALL_DISTANCE = 4;
    int RADIUS = 1;
    
    int x = MathHelper.func_76128_c(field_70165_t);
    int y = MathHelper.func_76128_c(field_70163_u) - 1;
    int z = MathHelper.func_76128_c(field_70161_v);
    
    if ((!field_72995_K) && (field_71093_bK != -1)) {
      ChatUtil.sendTranslated(net.minecraft.util.EnumChatFormatting.LIGHT_PURPLE, player, "witchery.prediction.tothenether.summoned", new Object[0]);
      player.func_70063_aa();
      
      World world = field_70170_p;
      
      int MAX_DISTANCE = 4;
      int MIN_DISTANCE = 2;
      
      int activeRadius = 2;
      int ax = field_73012_v.nextInt(activeRadius * 2 + 1);
      if (ax > activeRadius) {
        ax += 4;
      }
      int nx = x - 4 + ax;
      
      int az = field_73012_v.nextInt(activeRadius * 2 + 1);
      if (az > activeRadius) {
        az += 4;
      }
      
      int nz = z - 4 + az;
      
      int ny = y;
      while ((!world.func_147437_c(nx, ny, nz)) && (ny < y + 8)) {
        ny++;
      }
      

      while ((world.func_147437_c(nx, ny, nz)) && (ny > 0)) {
        ny--;
      }
      


      int hy = 0;
      while ((world.func_147437_c(nx, ny + hy + 1, nz)) && (hy < 6)) {
        hy++;
      }
      
      EntityBlaze entity = new EntityBlaze(world);
      if (hy >= field_70131_O) {
        entity.func_70012_b(nx, ny, nz, 0.0F, 0.0F);
        world.func_72838_d(entity);
      }
      return true;
    }
    return false;
  }
  
  public boolean checkIfFulfilled(World world, EntityPlayer player, LivingEvent.LivingUpdateEvent event, boolean isPastDue, boolean veryOld)
  {
    if (field_71093_bK == -1) {
      Log.instance().debug(String.format("Prediction for got to nether fulfilled as predicted", new Object[0]));
      return true;
    }
    return false;
  }
}
