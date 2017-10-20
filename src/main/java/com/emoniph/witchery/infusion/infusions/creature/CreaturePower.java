package com.emoniph.witchery.infusion.infusions.creature;

import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.util.Log;
import java.util.ArrayList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class CreaturePower
{
  private final int creaturePowerID;
  private final Class<? extends EntityLiving> creatureType;
  private static final String BEAST_POWER_KEY = "WITCBeastPower";
  private static final String BEAST_POWER_CHARGES_KEY = "WITCBeastPowerCharges";
  protected static final int DEFAULT_CHARGES_PER_SACRIFICE = 10;
  
  public CreaturePower(int creaturePowerID, Class<? extends EntityLiving> creatureType)
  {
    this.creaturePowerID = creaturePowerID;
    this.creatureType = creatureType;
  }
  
  public int getCreaturePowerID() {
    return creaturePowerID;
  }
  
  public int activateCost(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop) {
    return 1;
  }
  


  public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop) {}
  


  public void onUpdate(World world, EntityPlayer player) {}
  


  public void onDamage(World world, EntityPlayer player, LivingHurtEvent event) {}
  


  public void onFalling(World worldObj, EntityPlayer player, LivingFallEvent event) {}
  


  public static int getCreaturePowerID(EntityPlayer player)
  {
    NBTTagCompound nbt = Infusion.getNBT(player);
    return nbt.func_74762_e("WITCBeastPower");
  }
  
  public static void setCreaturePowerID(EntityPlayer playerEntity, int beastPower, int beastCharges) {
    NBTTagCompound nbt = Infusion.getNBT(playerEntity);
    if (beastPower > 0) {
      nbt.func_74768_a("WITCBeastPower", beastPower);
      nbt.func_74768_a("WITCBeastPowerCharges", beastCharges);
    } else {
      if (nbt.func_74764_b("WITCBeastPower")) {
        nbt.func_82580_o("WITCBeastPower");
      }
      
      if (nbt.func_74764_b("WITCBeastPowerCharges")) {
        nbt.func_82580_o("WITCBeastPowerCharges");
      }
    }
  }
  
  public static int getCreaturePowerCharges(EntityPlayer player) {
    NBTTagCompound nbt = Infusion.getNBT(player);
    if ((nbt.func_74764_b("WITCBeastPower")) && (nbt.func_74764_b("WITCBeastPowerCharges"))) {
      return nbt.func_74762_e("WITCBeastPowerCharges");
    }
    return 0;
  }
  
  public static void setCreaturePowerCharges(EntityPlayer player, int charges) {
    NBTTagCompound nbt = Infusion.getNBT(player);
    nbt.func_74768_a("WITCBeastPowerCharges", charges);
  }
  
  public net.minecraft.util.IIcon getPowerBarIcon(World worldObj, EntityPlayer player) {
    return net.minecraft.init.Blocks.field_150435_aG.func_149691_a(0, 0);
  }
  
  public static class Registry
  {
    private static final Registry INSTANCE = new Registry();
    
    public static Registry instance() {
      return INSTANCE;
    }
    
    private ArrayList<CreaturePower> registry = new ArrayList();
    

    private Registry() {}
    
    public void add(CreaturePower power)
    {
      if (creaturePowerID == registry.size() + 1) {
        registry.add(power);
      } else if (creaturePowerID > registry.size() + 1) {
        for (int i = registry.size(); i < creaturePowerID; i++) {
          registry.add(null);
        }
        registry.add(power);
      } else {
        CreaturePower existingPower = (CreaturePower)registry.get(creaturePowerID);
        if (existingPower != null) {
          Log.instance().warning(String.format("Creature power %s at id %d is being overwritten by another creature power %s.", new Object[] { existingPower, Integer.valueOf(creaturePowerID), power }));
        }
        

        registry.set(creaturePowerID, power);
      }
    }
    
    public CreaturePower get(EntityLiving creature) {
      for (CreaturePower power : registry) {
        if ((power != null) && (creatureType == creature.getClass())) {
          return power;
        }
      }
      return null;
    }
    
    public CreaturePower get(int creaturePowerID) {
      return (CreaturePower)registry.get(creaturePowerID - 1);
    }
  }
  
  public int getChargesPerSacrifice()
  {
    return 10;
  }
}
