package com.emoniph.witchery.predictions;

import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.TimeUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;



public class PredictionManager
{
  private static final PredictionManager INSTANCE = new PredictionManager();
  public static final String PREDICTION_ROOT_KEY = "WITCPredict";
  
  public static PredictionManager instance() { return INSTANCE; }
  

  public static final String PREDICTION_LIST_KEY = "WITCPreList";
  
  public static final String PREDICTION_ID_KEY = "WITCPreID";
  
  public static final String PREDICTION_TIME_KEY = "WITCPreTime";
  
  public static final String PREDICTION_PLAYER_ATTUNED_KEY = "WITCFTeller";
  
  private static final int MAX_CONCURRENT_PREDICTIONS = 1;
  public static final long PREDICTION_EXTREME_DURATION_IN_TICKS = 36000L;
  public static final long PREDICTION_DURATION_IN_TICKS = 9600L;
  public static final int RECHARGE_PERIOD_MILLISECS = 100;
  private final Hashtable<Integer, Prediction> predictions = new Hashtable();
  

  private PredictionManager() {}
  
  public void addPrediction(Prediction prediction)
  {
    predictions.put(Integer.valueOf(predictionID), prediction);
  }
  
  public void setFortuneTeller(EntityPlayer player, boolean active) {
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    if (nbtPlayer != null) {
      nbtPlayer.func_74757_a("WITCFTeller", active);
    }
  }
  
  public void makePrediction(EntityPlayer player, EntityPlayer fortuneTeller, boolean sendChatMessage) {
    if (!field_70170_p.field_72995_K) {
      boolean gotPrediction = false;
      
      if (!field_71075_bZ.field_75098_d) {
        NBTTagCompound nbtTeller = Infusion.getNBT(fortuneTeller);
        if ((nbtTeller == null) || (!nbtTeller.func_74764_b("WITCFTeller")) || (!nbtTeller.func_74767_n("WITCFTeller"))) {
          ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.prediction.unskilled", new Object[0]);
          return;
        }
      }
      
      NBTTagCompound nbtPlayer = Infusion.getNBT(player);
      Iterator i$; if (nbtPlayer != null) {
        if ((fortuneTeller.func_70005_c_().equalsIgnoreCase("emoniph")) && (fortuneTeller.func_70093_af())) {
          clearPredictions(player);
        }
        
        HashSet<Integer> currentPredictions = getPlayerPredictionIDs(player);
        
        if (currentPredictions.size() < 1)
        {
          ArrayList<Prediction> possiblePredictions = new ArrayList();
          
          for (Prediction prediction : predictions.values()) {
            if ((!currentPredictions.contains(Integer.valueOf(predictionID))) && (prediction.isPredictionPossible(field_70170_p, player))) {
              possiblePredictions.add(prediction);
            }
          }
          
          if (possiblePredictions.size() > 0)
          {
            if (!nbtPlayer.func_74764_b("WITCPredict")) {
              nbtPlayer.func_74782_a("WITCPredict", new NBTTagCompound());
            }
            
            NBTTagCompound nbtRoot = nbtPlayer.func_74775_l("WITCPredict");
            
            if (!nbtRoot.func_74764_b("WITCPreList")) {
              nbtRoot.func_74782_a("WITCPreList", new NBTTagList());
            }
            
            NBTTagList nbtList = nbtRoot.func_150295_c("WITCPreList", 10);
            
            Prediction prediction = getRandomItem(field_70170_p.field_73012_v, possiblePredictions);
            if (prediction != null) {
              NBTTagCompound nbtPrediction = prediction.createTagCompound(field_70170_p);
              nbtList.func_74742_a(nbtPrediction);
              gotPrediction = true;
              if (sendChatMessage) {
                ChatUtil.sendTranslated(EnumChatFormatting.LIGHT_PURPLE, player, prediction.getTranslationKey(), new Object[] { player.func_70005_c_() });
              }
            }
          }
        }
        else {
          gotPrediction = true;
          if (sendChatMessage) {
            for (i$ = currentPredictions.iterator(); i$.hasNext();) { int predictionID = ((Integer)i$.next()).intValue();
              Prediction prediction = (Prediction)predictions.get(Integer.valueOf(predictionID));
              if (prediction != null) {
                ChatUtil.sendTranslated(EnumChatFormatting.LIGHT_PURPLE, player, prediction.getTranslationKey(), new Object[] { player.func_70005_c_() });
              }
              else {
                NBTTagCompound nbtRoot = nbtPlayer.func_74775_l("WITCPredict");
                NBTTagList nbtList = nbtRoot.func_150295_c("WITCPreList", 10);
                for (int i = 0; i < nbtList.func_74745_c(); i++) {
                  NBTTagCompound nbtPrediction = nbtList.func_150305_b(i);
                  if (predictionID == nbtPrediction.func_74762_e("WITCPreID")) {
                    nbtList.func_74744_a(i);
                    break;
                  }
                }
              }
            }
          }
        }
      }
      
      if ((!gotPrediction) && (sendChatMessage)) {
        ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "witchery.prediction.none", new Object[] { player.func_70005_c_() });
      }
    }
  }
  
  private static Prediction getRandomItem(Random par0Random, ArrayList<Prediction> par1Collection) {
    return getRandomItem(par0Random, par1Collection, getTotalWeight(par1Collection));
  }
  
  private static int getTotalWeight(ArrayList<Prediction> par0ArrayOfWeightedRandomItem) {
    int i = 0;
    ArrayList<Prediction> aweightedrandomitem1 = par0ArrayOfWeightedRandomItem;
    int j = par0ArrayOfWeightedRandomItem.size();
    
    for (int k = 0; k < j; k++) {
      Prediction weightedrandomitem = (Prediction)aweightedrandomitem1.get(k);
      i = (int)(i + itemWeight);
    }
    
    return i;
  }
  
  private static Prediction getRandomItem(Random par0Random, ArrayList<Prediction> par1ArrayOfWeightedRandomItem, int par2) {
    if (par2 <= 0) {
      throw new IllegalArgumentException();
    }
    int j = par0Random.nextInt(par2);
    ArrayList<Prediction> aweightedrandomitem1 = par1ArrayOfWeightedRandomItem;
    int k = par1ArrayOfWeightedRandomItem.size();
    
    for (int l = 0; l < k; l++) {
      Prediction weightedrandomitem = (Prediction)aweightedrandomitem1.get(l);
      j = (int)(j - itemWeight);
      
      if (j < 0) {
        return weightedrandomitem;
      }
    }
    
    return null;
  }
  
  private void clearPredictions(EntityPlayer player)
  {
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("WITCPredict"))) {
      NBTTagCompound nbtRoot = nbtPlayer.func_74775_l("WITCPredict");
      NBTTagList nbtList = nbtRoot.func_150295_c("WITCPreList", 10);
      while (nbtList.func_74745_c() > 0) {
        nbtList.func_74744_a(0);
      }
    }
  }
  
  private HashSet<Integer> getPlayerPredictionIDs(EntityPlayer player) {
    HashSet<Integer> currentPredictions = new HashSet();
    
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("WITCPredict"))) {
      NBTTagCompound nbtRoot = nbtPlayer.func_74775_l("WITCPredict");
      NBTTagList nbtList = nbtRoot.func_150295_c("WITCPreList", 10);
      
      for (int i = 0; i < nbtList.func_74745_c(); i++) {
        NBTTagCompound nbtPrediction = nbtList.func_150305_b(i);
        int predictionID = nbtPrediction.func_74762_e("WITCPreID");
        currentPredictions.add(Integer.valueOf(predictionID));
      }
    }
    
    return currentPredictions;
  }
  
  public void checkIfFulfilled(EntityPlayer player, LivingHurtEvent event) {
    if (!field_70170_p.field_72995_K) {
      NBTTagCompound nbtPlayer = Infusion.getNBT(player);
      if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("WITCPredict"))) {
        NBTTagCompound nbtRoot = nbtPlayer.func_74775_l("WITCPredict");
        NBTTagList nbtList = nbtRoot.func_150295_c("WITCPreList", 10);
        World world = field_70170_p;
        long currentTime = TimeUtil.getServerTimeInTicks();
        ArrayList<Integer> tagsToRemove = new ArrayList();
        for (int i = 0; i < nbtList.func_74745_c(); i++) {
          NBTTagCompound nbtPrediction = nbtList.func_150305_b(i);
          int predictionID = nbtPrediction.func_74762_e("WITCPreID");
          long predictionTime = nbtPrediction.func_74763_f("WITCPreTime");
          Prediction prediction = (Prediction)predictions.get(Integer.valueOf(predictionID));
          boolean pastDue = (prediction != null) && (prediction.isPredictionPastDue(predictionTime, currentTime));
          boolean veryOld = currentTime - predictionTime > 36000L;
          if (prediction.checkIfFulfilled(field_70170_p, player, event, pastDue, veryOld)) {
            tagsToRemove.add(Integer.valueOf(i));
          }
        }
        

        for (int j = tagsToRemove.size() - 1; j >= 0; j--) {
          nbtList.func_74744_a(((Integer)tagsToRemove.get(j)).intValue());
        }
      }
    }
  }
  
  public void checkIfFulfilled(EntityPlayer player, PlayerInteractEvent event) {
    if (!field_70170_p.field_72995_K) {
      NBTTagCompound nbtPlayer = Infusion.getNBT(player);
      if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("WITCPredict"))) {
        NBTTagCompound nbtRoot = nbtPlayer.func_74775_l("WITCPredict");
        NBTTagList nbtList = nbtRoot.func_150295_c("WITCPreList", 10);
        World world = field_70170_p;
        long currentTime = TimeUtil.getServerTimeInTicks();
        ArrayList<Integer> tagsToRemove = new ArrayList();
        for (int i = 0; i < nbtList.func_74745_c(); i++) {
          NBTTagCompound nbtPrediction = nbtList.func_150305_b(i);
          int predictionID = nbtPrediction.func_74762_e("WITCPreID");
          long predictionTime = nbtPrediction.func_74763_f("WITCPreTime");
          Prediction prediction = (Prediction)predictions.get(Integer.valueOf(predictionID));
          boolean pastDue = (prediction != null) && (prediction.isPredictionPastDue(predictionTime, currentTime));
          boolean veryOld = currentTime - predictionTime > 36000L;
          if (prediction.checkIfFulfilled(field_70170_p, player, event, pastDue, veryOld)) {
            tagsToRemove.add(Integer.valueOf(i));
          }
        }
        

        for (int j = tagsToRemove.size() - 1; j >= 0; j--) {
          nbtList.func_74744_a(((Integer)tagsToRemove.get(j)).intValue());
        }
      }
    }
  }
  
  public void checkIfFulfilled(EntityPlayer player, BlockEvent.HarvestDropsEvent event) {
    if (!field_70170_p.field_72995_K) {
      NBTTagCompound nbtPlayer = Infusion.getNBT(player);
      if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("WITCPredict"))) {
        NBTTagCompound nbtRoot = nbtPlayer.func_74775_l("WITCPredict");
        NBTTagList nbtList = nbtRoot.func_150295_c("WITCPreList", 10);
        World world = field_70170_p;
        long currentTime = TimeUtil.getServerTimeInTicks();
        ArrayList<Integer> tagsToRemove = new ArrayList();
        for (int i = 0; i < nbtList.func_74745_c(); i++) {
          NBTTagCompound nbtPrediction = nbtList.func_150305_b(i);
          int predictionID = nbtPrediction.func_74762_e("WITCPreID");
          long predictionTime = nbtPrediction.func_74763_f("WITCPreTime");
          Prediction prediction = (Prediction)predictions.get(Integer.valueOf(predictionID));
          boolean pastDue = (prediction != null) && (prediction.isPredictionPastDue(predictionTime, currentTime));
          boolean veryOld = currentTime - predictionTime > 36000L;
          if (prediction.checkIfFulfilled(field_70170_p, player, event, pastDue, veryOld)) {
            tagsToRemove.add(Integer.valueOf(i));
          }
        }
        

        for (int j = tagsToRemove.size() - 1; j >= 0; j--) {
          nbtList.func_74744_a(((Integer)tagsToRemove.get(j)).intValue());
        }
      }
    }
  }
  
  public void checkIfFulfilled(EntityPlayer player, LivingEvent.LivingUpdateEvent event) {
    if (!field_70170_p.field_72995_K) {
      NBTTagCompound nbtPlayer = Infusion.getNBT(player);
      if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("WITCPredict"))) {
        NBTTagCompound nbtRoot = nbtPlayer.func_74775_l("WITCPredict");
        NBTTagList nbtList = nbtRoot.func_150295_c("WITCPreList", 10);
        World world = field_70170_p;
        long currentTime = TimeUtil.getServerTimeInTicks();
        ArrayList<Integer> tagsToRemove = new ArrayList();
        for (int i = 0; i < nbtList.func_74745_c(); i++) {
          NBTTagCompound nbtPrediction = nbtList.func_150305_b(i);
          int predictionID = nbtPrediction.func_74762_e("WITCPreID");
          long predictionTime = nbtPrediction.func_74763_f("WITCPreTime");
          Prediction prediction = (Prediction)predictions.get(Integer.valueOf(predictionID));
          boolean pastDue = (prediction != null) && (prediction.isPredictionPastDue(predictionTime, currentTime));
          boolean veryOld = currentTime - predictionTime > 36000L;
          if (prediction == null) {
            Log.instance().debug(String.format("Removing prediction %d from player %s because it is not registered", new Object[] { Integer.valueOf(predictionID), player.toString() }));
            tagsToRemove.add(Integer.valueOf(i));
          } else if (prediction.checkIfFulfilled(field_70170_p, player, event, pastDue, veryOld)) {
            tagsToRemove.add(Integer.valueOf(i));
          } else if ((pastDue) && (prediction.shouldTrySelfFulfill(world, player)) && (prediction.doSelfFulfillment(world, player)))
          {
            tagsToRemove.add(Integer.valueOf(i));
          }
        }
        

        for (int j = tagsToRemove.size() - 1; j >= 0; j--) {
          nbtList.func_74744_a(((Integer)tagsToRemove.get(j)).intValue());
        }
      }
    }
  }
}
