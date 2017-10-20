package com.emoniph.witchery.common;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.potions.PotionResizing;
import com.emoniph.witchery.network.PacketPipeline;
import com.emoniph.witchery.util.TimeUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;

public class ExtendedVillager implements net.minecraftforge.common.IExtendedEntityProperties
{
  private static final String EXT_PROP_NAME = "WitcheryExtendedVillager";
  private final EntityVillager villager;
  
  public static final void register(EntityVillager villager)
  {
    villager.registerExtendedProperties("WitcheryExtendedVillager", new ExtendedVillager(villager));
  }
  
  public static final ExtendedVillager get(EntityVillager villager) {
    return (ExtendedVillager)villager.getExtendedProperties("WitcheryExtendedVillager");
  }
  


  public ExtendedVillager(EntityVillager villager)
  {
    this.villager = villager;
  }
  
  public EntityVillager getVillager() {
    return villager;
  }
  
  public void saveNBTData(NBTTagCompound compound)
  {
    NBTTagCompound props = new NBTTagCompound();
    props.func_74768_a("Blood", blood);
    


    compound.func_74782_a("WitcheryExtendedVillager", props);
  }
  
  public void loadNBTData(NBTTagCompound compound)
  {
    if (compound.func_74764_b("WitcheryExtendedVillager")) {
      NBTTagCompound props = (NBTTagCompound)compound.func_74781_a("WitcheryExtendedVillager");
      blood = net.minecraft.util.MathHelper.func_76125_a(props.func_74762_e("Blood"), 0, 500);
    }
  }
  



  public void init(Entity entity, World world) {}
  


  private int blood = 500;
  
  public void setBlood(int blood) {
    if (this.blood != blood) {
      this.blood = Math.max(Math.min(blood, 500), 0);
      sync();
    } }
  
  private boolean sleeping;
  private int sleepingTicks;
  public int takeBlood(int quantity, EntityLivingBase player) { PotionEffect potionEffect = villager.func_70660_b(PotionsPARALYSED);
    boolean isKnockedOut = (isSleeping()) || ((potionEffect != null) && (potionEffect.func_76458_c() >= 4));
    if (!isKnockedOut) {
      quantity = (int)Math.ceil(0.66F * quantity);
    }
    int remainder = Math.max(blood - quantity, 0);
    int taken = blood - remainder;
    setBlood(remainder);
    if ((player instanceof net.minecraft.entity.player.EntityPlayer)) {
      if (blood < (int)Math.ceil(250.0D)) {
        villager.func_70097_a(new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), player), 1.3F);
      } else if (!isKnockedOut) {
        villager.func_70097_a(new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), player), 0.1F);
      }
    }
    return taken;
  }
  
  public void giveBlood(int quantity) {
    if (blood < 500) {
      setBlood(blood + quantity);
    }
  }
  
  public int getBlood() {
    return blood;
  }
  
  public boolean synced;
  private boolean trySync;
  public void setSleeping(boolean sleeping)
  {
    if (this.sleeping != sleeping) {
      this.sleeping = sleeping;
      if (this.sleeping) {
        PotionResizing.setEntitySize(villager, 0.8F, 1.1F);
      } else {
        PotionResizing.setEntitySize(villager, 0.6F, 1.8F);
        if (sleepingTicks >= TimeUtil.minsToTicks(2)) {
          villager.func_70606_j(villager.func_110138_aP());
        }
        
        if (sleepingTicks > TimeUtil.minsToTicks(1)) {
          int blops = sleepingTicks / TimeUtil.minsToTicks(1);
          giveBlood(50 * blops);
        }
      }
      sleepingTicks = 0;
      sync();
    }
  }
  
  public boolean isSleeping() {
    return sleeping;
  }
  
  public void incrementSleepingTicks() {
    sleepingTicks += 1;
  }
  
  public void sync() {
    if ((!villager.field_70170_p.field_72995_K) && (villager.func_110143_aJ() > 0.0F) && (!villager.field_70128_L)) {
      Witchery.packetPipeline.sendToAll(new com.emoniph.witchery.network.PacketExtendedVillagerSync(this));
    }
  }
  

  public boolean isClientSynced()
  {
    if (villager.field_70170_p.field_72995_K) {
      if (synced)
        return true;
      if (trySync)
      {
        return false;
      }
      trySync = true;
      Witchery.packetPipeline.sendToServer(new com.emoniph.witchery.network.PacketExtendedEntityRequestSyncToClient(villager));
    }
    
    return false;
  }
}
