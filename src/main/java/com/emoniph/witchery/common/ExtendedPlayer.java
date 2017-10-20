package com.emoniph.witchery.common;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.brewing.potions.PotionBase;
import com.emoniph.witchery.entity.EntityAttackBat;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.network.PacketPipeline;
import com.emoniph.witchery.network.PacketPlayerStyle;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import com.emoniph.witchery.util.TransformCreature;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties
{
  private static final String EXT_PROP_NAME = "WitcheryExtendedPlayer";
  private final EntityPlayer player;
  
  public static final void register(EntityPlayer player)
  {
    player.registerExtendedProperties("WitcheryExtendedPlayer", new ExtendedPlayer(player));
  }
  
  public static final ExtendedPlayer get(EntityPlayer player) {
    return (ExtendedPlayer)player.getExtendedProperties("WitcheryExtendedPlayer");
  }
  


  private Hashtable<Integer, PotionEffect> incurablePotionEffectCache = new Hashtable();
  
  private static final int MAX_SKILL_LEVEL_POTION_BOTTLING = 100;
  
  private int skillLevelPotionBottling;
  private static final int MAX_SKILL_LEVEL_POTION_THROWING = 100;
  private int skillLevelPotionThrowing;
  public static final int MAX_HUMAN_BLOOD = 500;
  private int creatureType;
  private int werewolfLevel;
  private int vampireLevel;
  private int bloodPower;
  private int bloodReserve;
  private int vampireUltimate;
  private int vampireUltimateCharges;
  private int humanBlood;
  private int wolfmanQuestState;
  private int wolfmanQuestCounter;
  private long lastBoneFind;
  private long lastHowl;
  private VampirePower selectedVampirePower = VampirePower.NONE;
  
  private int vampireCooldown;
  private int vampireQuestCounter;
  
  public ExtendedPlayer(EntityPlayer player)
  {
    this.player = player;
  }
  
  private boolean vampVisionActive;
  private String lastPlayerSkin;
  @SideOnly(Side.CLIENT)
  private ThreadDownloadImageData downloadImageSkin;
  public void init(Entity entity, World world) {}
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagCompound props = new NBTTagCompound();
    
    props.func_74768_a("PotionBottling", skillLevelPotionBottling);
    props.func_74768_a("PotionThrowing", skillLevelPotionThrowing);
    props.func_74768_a("CreatureType", creatureType);
    props.func_74768_a("WerewolfLevel", werewolfLevel);
    props.func_74768_a("WolfmanQuestState", wolfmanQuestState);
    props.func_74768_a("WolfmanQuestCounter", wolfmanQuestCounter);
    props.func_74772_a("LastBoneFind", lastBoneFind);
    props.func_74772_a("LastHowl", lastHowl);
    
    NBTTagList nbtChunks = new NBTTagList();
    for (Iterator i$ = visitedChunks.iterator(); i$.hasNext();) { long l = ((Long)i$.next()).longValue();
      NBTTagCompound tag = new NBTTagCompound();
      tag.func_74772_a("Location", l);
      nbtChunks.func_74742_a(tag);
    }
    
    props.func_74782_a("WolfmanQuestChunks", nbtChunks);
    props.func_74768_a("VampireLevel", vampireLevel);
    props.func_74768_a("BloodPower", bloodPower);
    props.func_74768_a("HumanBlood", humanBlood);
    props.func_74768_a("VampireUltimate", vampireUltimate);
    props.func_74768_a("VampireUltimateCharges", vampireUltimateCharges);
    props.func_74768_a("VampireLevelCap", vampireLevelCap);
    props.func_74768_a("VampireQuestCounter", vampireQuestCounter);
    NBTTagList nbtVampireChunks = new NBTTagList();
    for (Iterator i$ = visitedVampireChunks.iterator(); i$.hasNext();) { long l = ((Long)i$.next()).longValue();
      NBTTagCompound tag = new NBTTagCompound();
      tag.func_74772_a("Location", l);
      nbtVampireChunks.func_74742_a(tag);
    }
    
    props.func_74782_a("VampireQuestChunks", nbtVampireChunks);
    props.func_74768_a("BloodReserve", bloodReserve);
    props.func_74757_a("VampireVision", vampVisionActive);
    
    if (cachedInventory != null) {
      props.func_74782_a("CachedInventory2", cachedInventory.func_74737_b());
      props.func_74757_a("CanRestoreInventory", inventoryCanBeRestored);
    }
    
    if (mirrorWorldEntryPoint != null) {
      props.func_74782_a("MirrorWorldEntryPoint", mirrorWorldEntryPoint.toTagNBT());
    }
    
    if (lastPlayerSkin != null) {
      props.func_74778_a("LastPlayerSkin", lastPlayerSkin);
    }
    
    props.func_74772_a("MirrorEscape1", mirrorWorldEscapeCooldown1);
    props.func_74772_a("MirrorEscape2", mirrorWorldEscapeCooldown2);
    
    compound.func_74782_a("WitcheryExtendedPlayer", props);
  }
  
  public void loadNBTData(NBTTagCompound compound)
  {
    if (compound.func_74764_b("WitcheryExtendedPlayer")) {
      NBTTagCompound props = (NBTTagCompound)compound.func_74781_a("WitcheryExtendedPlayer");
      skillLevelPotionBottling = MathHelper.func_76125_a(props.func_74762_e("PotionBottling"), 0, 100);
      
      skillLevelPotionThrowing = MathHelper.func_76125_a(props.func_74762_e("PotionThrowing"), 0, 100);
      
      creatureType = MathHelper.func_76125_a(props.func_74762_e("CreatureType"), 0, 5);
      werewolfLevel = MathHelper.func_76125_a(props.func_74762_e("WerewolfLevel"), 0, 10);
      wolfmanQuestState = MathHelper.func_76125_a(props.func_74762_e("WolfmanQuestState"), 0, QuestState.values().length - 1);
      
      wolfmanQuestCounter = MathHelper.func_76125_a(props.func_74762_e("WolfmanQuestCounter"), 0, 100);
      visitedChunks.clear();
      NBTTagList nbtChunks = props.func_150295_c("WolfmanQuestChunks", 10);
      for (int i = 0; i < nbtChunks.func_74745_c(); i++) {
        visitedChunks.add(Long.valueOf(nbtChunks.func_150305_b(i).func_74763_f("Location")));
      }
      
      lastBoneFind = props.func_74763_f("LastBoneFind");
      lastHowl = props.func_74763_f("LastHowl");
      vampireLevel = MathHelper.func_76125_a(props.func_74762_e("VampireLevel"), 0, 10);
      bloodPower = MathHelper.func_76125_a(props.func_74762_e("BloodPower"), 0, getMaxBloodPower());
      humanBlood = MathHelper.func_76125_a(props.func_74762_e("HumanBlood"), 0, 500);
      vampireUltimate = props.func_74762_e("VampireUltimate");
      vampireUltimateCharges = props.func_74762_e("VampireUltimateCharges");
      vampireLevelCap = props.func_74762_e("VampireLevelCap");
      vampireQuestCounter = props.func_74762_e("VampireQuestCounter");
      NBTTagList nbtVampireChunks = props.func_150295_c("VampireQuestChunks", 10);
      for (int i = 0; i < nbtVampireChunks.func_74745_c(); i++) {
        visitedVampireChunks.add(Long.valueOf(nbtVampireChunks.func_150305_b(i).func_74763_f("Location")));
      }
      bloodReserve = props.func_74762_e("BloodReserve");
      
      vampVisionActive = props.func_74767_n("VampireVision");
      
      if (props.func_74764_b("CachedInventory2")) {
        cachedInventory = props.func_150295_c("CachedInventory2", 10);
        inventoryCanBeRestored = props.func_74767_n("CanRestoreInventory");
      }
      
      if (props.func_74764_b("MirrorWorldEntryPoint")) {
        mirrorWorldEntryPoint = Coord.fromTagNBT(props.func_74775_l("MirrorWorldEntryPoint"));
      }
      
      if (props.func_74764_b("LastPlayerSkin")) {
        lastPlayerSkin = props.func_74779_i("LastPlayerSkin");
      }
      
      mirrorWorldEscapeCooldown1 = props.func_74763_f("MirrorEscape1");
      mirrorWorldEscapeCooldown2 = props.func_74763_f("MirrorEscape2");
    }
  }
  
  public void setOtherPlayerSkin(String username) {
    lastPlayerSkin = username;
    locationSkin = null;
    sync();
  }
  

  public String getOtherPlayerSkin() { return lastPlayerSkin != null ? lastPlayerSkin : ""; }
  
  private ResourceLocation locationSkin;
  private NBTTagList cachedInventory;
  private boolean inventoryCanBeRestored;
  private int vampireLevelCap;
  private static final int DEFAULT_ULTIMATE_CHARGES = 5;
  public int highlightTicks;
  @SideOnly(Side.CLIENT)
  public ResourceLocation getLocationSkin() {
    if (locationSkin == null) {
      setupCustomSkin();
    }
    if (locationSkin != null) {
      return locationSkin;
    }
    return null;
  }
  
  @SideOnly(Side.CLIENT)
  private void setupCustomSkin()
  {
    String ownerName = getOtherPlayerSkin();
    if ((ownerName != null) && (!ownerName.isEmpty())) {
      locationSkin = net.minecraft.client.entity.AbstractClientPlayer.func_110311_f(ownerName);
      downloadImageSkin = getDownloadImageSkin(locationSkin, ownerName);
    } else {
      locationSkin = null;
      downloadImageSkin = null;
    }
  }
  
  @SideOnly(Side.CLIENT)
  private static ThreadDownloadImageData getDownloadImageSkin(ResourceLocation location, String name) {
    TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
    Object object = texturemanager.func_110581_b(location);
    
    if (object == null) {
      object = new ThreadDownloadImageData((java.io.File)null, String.format("http://skins.minecraft.net/MinecraftSkins/%s.png", new Object[] { net.minecraft.util.StringUtils.func_76338_a(name) }), com.emoniph.witchery.client.renderer.RenderReflection.SKIN, new net.minecraft.client.renderer.ImageBufferDownload());
      


      texturemanager.func_110579_a(location, (ITextureObject)object);
    }
    
    return (ThreadDownloadImageData)object;
  }
  
  public ResourceLocation getOtherPlayerSkinLocation()
  {
    return getLocationSkin();
  }
  


  public void cachePlayerInventory()
  {
    inventoryCanBeRestored = true;
  }
  
  public void backupPlayerInventory() {
    NBTTagList nbtInventory = new NBTTagList();
    player.field_71071_by.func_70442_a(nbtInventory);
    cachedInventory = nbtInventory;
  }
  
  public void restorePlayerInventoryFrom(ExtendedPlayer original) {
    if ((original != null) && (cachedInventory != null) && (inventoryCanBeRestored)) {
      player.field_71071_by.func_70443_b(cachedInventory);
      inventoryCanBeRestored = false;
      cachedInventory = null;
    }
  }
  
  public int getSkillPotionBottling() {
    return skillLevelPotionBottling;
  }
  
  public int increaseSkillPotionBottling() {
    skillLevelPotionBottling = Math.min(skillLevelPotionBottling + 1, 100);
    if ((skillLevelPotionBottling == 30) || (skillLevelPotionBottling == 60) || (skillLevelPotionBottling == 90)) {
      ChatUtil.sendTranslated(player, "witchery:brew.skillincrease", new Object[0]);
    }
    return getSkillPotionBottling();
  }
  
  public int getSkillPotionThrowing() {
    return skillLevelPotionThrowing;
  }
  
  public int increaseSkillPotionThrowing() {
    skillLevelPotionThrowing = Math.min(skillLevelPotionThrowing + 1, 100);
    return getSkillPotionBottling();
  }
  
  public int getWerewolfLevel() {
    return werewolfLevel;
  }
  
  public void setWerewolfLevel(int level) {
    if ((werewolfLevel != level) && (level >= 0) && (level <= 10)) {
      werewolfLevel = level;
      wolfmanQuestState = 0;
      wolfmanQuestCounter = 0;
      visitedChunks.clear();
      if ((werewolfLevel == 0) && (!player.field_70170_p.field_72995_K) && (
        (creatureType == 1) || (creatureType == 2))) {
        Shapeshift.INSTANCE.shiftTo(player, TransformCreature.NONE);
      }
      
      sync();
    }
  }
  
  public void increaseWerewolfLevel() {
    if (werewolfLevel < 10) {
      setWerewolfLevel(werewolfLevel + 1);
      Shapeshift.INSTANCE.initCurrentShift(player);
    }
  }
  
  public int getHumanBlood() {
    return humanBlood;
  }
  
  public void setHumanBlood(int blood) {
    if (humanBlood != blood) {
      humanBlood = MathHelper.func_76125_a(blood, 0, 500);
      if (!player.field_70170_p.field_72995_K) {
        Witchery.packetPipeline.sendToAll(new com.emoniph.witchery.network.PacketPartialExtendedPlayerSync(this, player));
      }
    }
  }
  
  public int takeHumanBlood(int quantity, EntityLivingBase attacker) {
    if (!player.func_70608_bn()) {
      quantity = (int)Math.ceil(0.66F * quantity);
    }
    int remainder = Math.max(humanBlood - quantity, 0);
    int taken = humanBlood - remainder;
    setHumanBlood(remainder);
    if (humanBlood < (int)Math.ceil(250.0D)) {
      player.func_70097_a(new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), attacker), 1.0F);
    } else if (!player.func_70608_bn()) {
      player.func_70097_a(new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), attacker), 0.1F);
    }
    return taken;
  }
  
  public void giveHumanBlood(int quantity) {
    if (humanBlood < 500) {
      setHumanBlood(humanBlood + quantity);
    }
  }
  
  public int getVampireLevel() {
    return vampireLevel;
  }
  
  public boolean isVampire() {
    return getVampireLevel() > 0;
  }
  
  public void setVampireLevel(int level) {
    if ((vampireLevel != level) && (level >= 0) && (level <= 10)) {
      vampireLevel = level;
      vampireQuestCounter = 0;
      visitedVampireChunks.clear();
      if ((vampireLevel == 0) && (!player.field_70170_p.field_72995_K)) {
        if (creatureType == 3) {
          Shapeshift.INSTANCE.shiftTo(player, TransformCreature.NONE);
        } else {
          Shapeshift.INSTANCE.initCurrentShift(player);
        }
        bloodPower = 0;
        humanBlood = 50;
        vampireUltimate = 0;
        vampireUltimateCharges = 0;
      } else {
        Shapeshift.INSTANCE.initCurrentShift(player);
      }
      

      selectedVampirePower = VampirePower.NONE;
      
      if (vampireLevel == 1) {
        bloodPower = 125;
      }
      
      if (vampireLevel > 0) {
        humanBlood = 0;
      }
      sync();
    }
  }
  
  public int getMaxBloodPower() {
    return 500 + (getWerewolfLevel() >= 2 ? (int)Math.floor(getVampireLevel() * 0.5D) : getVampireLevel()) * 250;
  }
  
  public int getBloodPower() {
    return bloodPower;
  }
  
  public boolean decreaseBloodPower(int quantity, boolean exact) {
    if (player.field_71075_bZ.field_75098_d)
      return true;
    if (bloodPower >= (exact ? quantity : 1)) {
      setBloodPower(bloodPower - quantity);
      return true;
    }
    return false;
  }
  
  public void increaseBloodPower(int quantity)
  {
    if (bloodPower < getMaxBloodPower()) {
      setBloodPower(bloodPower + quantity);
      if ((instanceallowVampireQuests) && (getVampireLevel() == 1) && (getBloodPower() == getMaxBloodPower()))
      {
        increaseVampireLevel();
      }
    }
  }
  
  public void increaseVampireLevel() {
    if (vampireLevel < 10) {
      setVampireLevel(vampireLevel + 1);
      if (!player.field_70170_p.field_72995_K) {
        ChatUtil.sendTranslated(EnumChatFormatting.GOLD, player, "Your thirst grows stronger!", new Object[0]);
        SoundEffect.RANDOM_LEVELUP.playOnlyTo(player);
      }
    }
  }
  

  public void increaseVampireLevelCap(int levelCap)
  {
    if (levelCap > vampireLevelCap) {
      vampireLevelCap = Math.max(levelCap, 3);
    }
  }
  
  public boolean canIncreaseVampireLevel() {
    return (instanceallowVampireQuests) && (vampireLevel < vampireLevelCap);
  }
  
  public void increaseBloodPower(int quantity, int maxIncrease) {
    if ((bloodPower < getMaxBloodPower()) && (bloodPower < maxIncrease)) {
      setBloodPower(Math.min(bloodPower + quantity, maxIncrease));
    }
  }
  
  public void setBloodPower(int bloodLevel) {
    if (bloodPower != bloodLevel) {
      bloodPower = MathHelper.func_76125_a(bloodLevel, 0, getMaxBloodPower());
      sync();
    }
  }
  
  public static enum VampireUltimate {
    NONE,  STORM,  SWARM,  FARM;
    
    private VampireUltimate() {} }
  
  public VampireUltimate getVampireUltimate() { return VampireUltimate.values()[vampireUltimate]; }
  


  public void setVampireUltimate(VampireUltimate skill)
  {
    setVampireUltimate(skill, 5);
  }
  
  public void setVampireUltimate(VampireUltimate skill, int charges) {
    vampireUltimate = skill.ordinal();
    vampireUltimateCharges = charges;
    sync();
  }
  
  public int getVampireUltimateCharges() {
    return vampireUltimateCharges;
  }
  
  public static enum VampirePower {
    NONE(0, 0, 0),  DRINK(0, 0, 1),  MESMERIZE(50, 0, 2),  SPEED(10, 0, 4),  BAT(50, 1, 7),  ULTIMATE(50, 0, 10);
    
    public final int INITIAL_COST;
    public final int UPKEEP_COST;
    public final int LEVEL_CAP;
    
    private VampirePower(int initialCost, int upkeepCost, int levelCap) { INITIAL_COST = initialCost;
      UPKEEP_COST = upkeepCost;
      LEVEL_CAP = levelCap;
    }
    
    private static int[] levels = { 0, 1, 2, 2, 3, 3, 3, 4, 4, 4, 5 };
  }
  
  public VampirePower getSelectedVampirePower() {
    return selectedVampirePower;
  }
  
  public int getMaxAvailablePowerOrdinal() {
    return VampirePower.levels[vampireLevel];
  }
  
  public void useBloodReserve() {
    int temp = bloodReserve;
    if (bloodPower < getMaxBloodPower()) {
      bloodReserve = 0;
      increaseBloodPower(temp);
    }
  }
  
  public boolean isBloodReserveReady() {
    return bloodReserve > 0;
  }
  
  public void fillBloodReserve(int quantity) {
    bloodReserve = Math.min(bloodReserve + quantity, 250);
    sync();
  }
  
  public int getBloodReserve() {
    return isVampire() ? bloodReserve : 0;
  }
  
  public void setBloodReserve(int blood) {
    bloodReserve = blood;
  }
  
  public boolean isVampireVisionActive() {
    return (vampireLevel >= 2) && (vampVisionActive);
  }
  
  public void toggleVampireVision() {
    vampVisionActive = (!vampVisionActive);
    if (!player.field_70170_p.field_72995_K) {
      if (!vampVisionActive) {
        player.func_82170_o(field_76439_rfield_76415_H);
      } else {
        player.func_70690_d(new PotionEffect(field_76439_rfield_76415_H, 400, 0, true));
      }
    }
  }
  

  public void setSelectedVampirePower(VampirePower power, boolean syncToServer)
  {
    if (selectedVampirePower != power) {
      selectedVampirePower = power;
      
      highlightTicks = (selectedVampirePower != VampirePower.NONE ? 100 : 0);
      
      if ((syncToServer) && (player.field_70170_p.field_72995_K)) {
        Witchery.packetPipeline.sendToServer(new com.emoniph.witchery.network.PacketSelectPlayerAbility(this, false));
      }
    }
  }
  
  public void triggerSelectedVampirePower() {
    if (!player.field_70170_p.field_72995_K) {
      VampirePower power = getSelectedVampirePower();
      if (vampireCooldown <= 0) {
        vampireCooldown = 10;
        switch (1.$SwitchMap$com$emoniph$witchery$common$ExtendedPlayer$VampirePower[power.ordinal()]) {
        case 1: 
          if (!player.func_70093_af()) break;
          toggleVampireVision(); break;
        

        case 2: 
          if (getCreatureType() == TransformCreature.NONE) {
            PotionEffect effect = player.func_70660_b(Potion.field_76424_c);
            int currentLevel = effect == null ? 0 : (int)Math.ceil(Math.log(effect.func_76458_c() + 1) / Math.log(2.0D));
            
            if ((vampireLevel >= 4) && (currentLevel <= Math.ceil((vampireLevel - 3) / 2.0F))) {
              if (decreaseBloodPower(INITIAL_COST, true)) {
                SoundEffect.RANDOM_FIZZ.playOnlyTo(player);
                int level = effect == null ? 2 : (effect.func_76458_c() + 1) * 2;
                int duration = effect == null ? TimeUtil.secsToTicks(10) : effect.func_76459_b() + 60;
                
                player.func_70690_d(new PotionEffect(field_76424_cfield_76415_H, duration, level - 1, true));
                
                player.func_70690_d(new PotionEffect(field_76430_jfield_76415_H, duration, currentLevel + 1, true));
              }
              else {
                SoundEffect.NOTE_SNARE.playOnlyTo(player);
              }
            } else {
              SoundEffect.NOTE_SNARE.playOnlyTo(player);
            }
          } else {
            SoundEffect.NOTE_SNARE.playOnlyTo(player);
          }
          break;
        case 3: 
          if (vampireLevel >= 7) {
            if (getCreatureType() == TransformCreature.NONE) {
              if (decreaseBloodPower(INITIAL_COST, true)) {
                SoundEffect.RANDOM_FIZZ.playOnlyTo(player);
                Shapeshift.INSTANCE.shiftTo(player, TransformCreature.BAT);
              } else {
                SoundEffect.NOTE_SNARE.playOnlyTo(player);
              }
            } else if (getCreatureType() == TransformCreature.BAT) {
              SoundEffect.RANDOM_FIZZ.playOnlyTo(player);
              Shapeshift.INSTANCE.shiftTo(player, TransformCreature.NONE);
            } else {
              SoundEffect.NOTE_SNARE.playOnlyTo(player);
            }
          } else {
            SoundEffect.NOTE_SNARE.playOnlyTo(player);
          }
          break;
        case 4: 
          if ((vampireLevel >= 10) && (vampireUltimateCharges > 0) && (getCreatureType() == TransformCreature.NONE))
          {
            switch (1.$SwitchMap$com$emoniph$witchery$common$ExtendedPlayer$VampireUltimate[getVampireUltimate().ordinal()]) {
            case 1: 
              WorldInfo worldinfo = ((WorldServer)player.field_70170_p).func_72912_H();
              if (!worldinfo.func_76059_o()) {
                int i = (300 + player.field_70170_p.field_73012_v.nextInt(600)) * 20;
                worldinfo.func_76090_f(i);
                worldinfo.func_76069_a(true);
                worldinfo.func_76080_g(i);
                worldinfo.func_76084_b(true);
                SoundEffect.RANDOM_FIZZ.playOnlyTo(player);
                if (!player.field_71075_bZ.field_75098_d) {
                  vampireUltimateCharges -= 1;
                  sync();
                }
              } else {
                SoundEffect.NOTE_SNARE.playOnlyTo(player);
              }
              break;
            
            case 2: 
              for (int i = 0; i < 15; i++) {
                EntityLiving creature = spawnCreature(player.field_70170_p, EntityAttackBat.class, player.field_70165_t, player.field_70163_u + 3.0D + player.field_70170_p.field_73012_v.nextDouble(), player.field_70161_v, 1, 4, ParticleEffect.SMOKE, SoundEffect.WITCHERY_RANDOM_POOF);
                

                if (creature != null) {
                  EntityAttackBat bat = (EntityAttackBat)creature;
                  bat.setOwner(player);
                  bat.func_82236_f(false);
                  NBTTagCompound nbtBat = bat.getEntityData();
                  nbtBat.func_74757_a("WITCNoDrops", true);
                }
              }
              if (!player.field_71075_bZ.field_75098_d) {
                vampireUltimateCharges -= 1;
                sync();
              }
              
              break;
            case 3: 
              boolean done = false;
              if (player.field_71093_bK != instancedimensionDreamID) {
                ChunkCoordinates coords = player.getBedLocation(player.field_71093_bK);
                int dimension = player.field_71093_bK;
                World world = player.field_70170_p;
                if (coords == null) {
                  coords = player.getBedLocation(0);
                  dimension = 0;
                  world = MinecraftServer.func_71276_C().func_71218_a(0);
                  if (coords == null) {
                    coords = world.func_72861_E();
                    
                    while ((world.func_147439_a(field_71574_a, field_71572_b, field_71573_c).func_149721_r()) && (field_71572_b < 255)) {
                      field_71572_b += 1;
                    }
                  }
                }
                
                if (coords != null) {
                  double HOME_DIST = 6.0D;
                  double HOME_DIST_SQ = 36.0D;
                  coords = net.minecraft.init.Blocks.field_150324_C.getBedSpawnPosition(world, field_71574_a, field_71572_b, field_71573_c, null);
                  
                  if (coords != null) {
                    if ((dimension == player.field_71093_bK) && (player.func_70092_e(field_71574_a, player.field_70163_u, field_71573_c) <= 36.0D))
                    {
                      Village village = field_72982_D.func_75550_a(MathHelper.func_76128_c(player.field_70165_t), MathHelper.func_76128_c(player.field_70163_u), MathHelper.func_76128_c(player.field_70161_v), 512);
                      


                      if (village != null) {
                        ChunkCoordinates townPos = village.func_75577_a();
                        if (ItemGeneral.teleportToLocationSafely(player.field_70170_p, field_71574_a + 0.5D, field_71572_b + 1, field_71573_c + 0.5D, dimension, player, true))
                        {

                          done = true;
                        }
                      }
                    }
                    else if (ItemGeneral.teleportToLocationSafely(player.field_70170_p, field_71574_a + 0.5D, field_71572_b + 1, field_71573_c + 0.5D, dimension, player, true))
                    {

                      done = true;
                    }
                  }
                }
              }
              
              if (!done) {
                SoundEffect.NOTE_SNARE.playOnlyTo(player);
              }
              else if (!player.field_71075_bZ.field_75098_d) {
                vampireUltimateCharges -= 1;
                sync();
              }
              
              break;
            default: 
              SoundEffect.NOTE_SNARE.playOnlyTo(player);
            }
            
          } else {
            SoundEffect.NOTE_SNARE.playOnlyTo(player);
          }
          break;
        }
      }
      else
      {
        SoundEffect.NOTE_SNARE.playOnlyTo(player);
      }
    }
  }
  
  public static EntityLiving spawnCreature(World world, Class<? extends EntityLiving> creatureType, double posX, double posY, double posZ, int minRange, int maxRange, ParticleEffect effect, SoundEffect effectSound)
  {
    if (!field_72995_K) {
      int x = MathHelper.func_76128_c(posX);
      int y = MathHelper.func_76128_c(posY);
      int z = MathHelper.func_76128_c(posZ);
      
      int activeRadius = maxRange - minRange;
      int ax = field_73012_v.nextInt(activeRadius * 2 + 1);
      if (ax > activeRadius) {
        ax += minRange * 2;
      }
      
      int nx = x - maxRange + ax;
      
      int az = field_73012_v.nextInt(activeRadius * 2 + 1);
      if (az > activeRadius) {
        az += minRange * 2;
      }
      
      int nz = z - maxRange + az;
      
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
      
      Log.instance().debug("Creature: hy: " + hy + " (" + nx + "," + ny + "," + nz + ")");
      
      if (hy >= 2) {
        try {
          Constructor ctor = creatureType.getConstructor(new Class[] { World.class });
          EntityLiving creature = (EntityLiving)ctor.newInstance(new Object[] { world });
          
          creature.func_70012_b(0.5D + nx, 0.05D + ny + 1.0D, 0.5D + nz, 0.0F, 0.0F);
          world.func_72838_d(creature);
          if (effect != null) {
            effect.send(effectSound, world, 0.5D + nx, 0.05D + ny + 1.0D, 0.5D + nz, 1.0D, field_70131_O, 16);
          }
          
          return creature;
        }
        catch (NoSuchMethodException ex) {}catch (InvocationTargetException ex) {}catch (InstantiationException ex) {}catch (IllegalAccessException ex) {}
      }
    }
    


    return null;
  }
  
  public void tick() {
    if (vampireCooldown > 0) {
      vampireCooldown -= 1;
    }
  }
  
  public void updateWorship() {
    if (cachedWorship >= 0) {
      player.func_70690_d(new PotionEffect(PotionsWORSHIP.field_76415_H, TimeUtil.secsToTicks(60), cachedWorship, true));
      
      cachedWorship = -1;
    }
    processSync();
  }
  
  public boolean cacheIncurablePotionEffect(Collection<PotionEffect> activePotionEffects) {
    boolean cached = false;
    for (PotionEffect activeEffect : activePotionEffects) {
      int potionID = activeEffect.func_76456_a();
      if ((potionID >= 0) && (potionID < Potion.field_76425_a.length) && (Potion.field_76425_a[potionID] != null) && ((Potion.field_76425_a[potionID] instanceof PotionBase)) && (activeEffect.func_76459_b() > 5))
      {
        PotionBase potion = (PotionBase)Potion.field_76425_a[potionID];
        if (!potion.isCurable()) {
          incurablePotionEffectCache.put(Integer.valueOf(activeEffect.func_76456_a()), activeEffect);
          cached = true;
        }
      }
    }
    return cached;
  }
  
  public void clearCachedIncurablePotionEffect(Potion potion) {
    incurablePotionEffectCache.remove(Integer.valueOf(field_76415_H));
  }
  
  public void restoreIncurablePotionEffects() {
    if (incurablePotionEffectCache.size() > 0) {
      Collection<PotionEffect> activeEffectList = player.func_70651_bq();
      for (PotionEffect activeEffect : activeEffectList) {
        incurablePotionEffectCache.remove(Integer.valueOf(activeEffect.func_76456_a()));
      }
      for (PotionEffect restoredEffect : incurablePotionEffectCache.values()) {
        player.func_70690_d(new PotionEffect(restoredEffect));
      }
      incurablePotionEffectCache.clear();
    }
  }
  
  public int cachedWorship = -1;
  
  public void addWorship(int level) {
    cachedWorship = level;
  }
  
  public void sync() {
    if (!player.field_70170_p.field_72995_K) {
      Witchery.packetPipeline.sendTo(new com.emoniph.witchery.network.PacketExtendedPlayerSync(this), player);
    }
  }
  
  public static void loadProxyData(EntityPlayer player) {
    if (player != null) {
      ExtendedPlayer playerEx = get(player);
      playerEx.sync();
    }
  }
  
  public int getCreatureTypeOrdinal() {
    return creatureType;
  }
  
  public TransformCreature getCreatureType() {
    return TransformCreature.values()[creatureType];
  }
  
  public void setCreatureType(TransformCreature type) {
    int ordinalType = type.ordinal();
    setCreatureTypeOrdinal(ordinalType);
  }
  
  public void setCreatureTypeOrdinal(int type) {
    if (type != creatureType) {
      creatureType = type;
      if (!player.field_70170_p.field_72995_K) {
        Witchery.packetPipeline.sendToAll(new PacketPlayerStyle(player));
      }
    }
  }
  
  public long getLastBoneFind() {
    return lastBoneFind;
  }
  
  public void setLastBoneFind(long serverTime) {
    lastBoneFind = serverTime;
  }
  
  public long getLastHowl() {
    return lastHowl;
  }
  
  public void setLastHowl(long serverTime) {
    lastHowl = serverTime;
  }
  
  public static enum QuestState {
    NOT_STATED,  STARTED,  COMPLETE;
    
    private QuestState() {} }
  
  public QuestState getWolfmanQuestState() { return QuestState.values()[wolfmanQuestState]; }
  
  public void setWolfmanQuestState(QuestState state)
  {
    wolfmanQuestState = state.ordinal();
  }
  
  public int getWolfmanQuestCounter() {
    return wolfmanQuestCounter;
  }
  
  public void increaseWolfmanQuestCounter() {
    wolfmanQuestCounter += 1;
    if (wolfmanQuestCounter > 100) {
      wolfmanQuestCounter = 100;
    }
  }
  
  private final List<Long> visitedChunks = new ArrayList();
  
  public boolean storeWolfmanQuestChunk(int x, int z) {
    long location = x << 32 | z & 0xFFFFFFFF;
    if (visitedChunks.contains(Long.valueOf(location))) {
      return false;
    }
    visitedChunks.add(Long.valueOf(location));
    


    return true;
  }
  
  private final List<Long> visitedVampireChunks = new ArrayList();
  boolean getPlayerData;
  
  public boolean storeVampireQuestChunk(int x, int z) { long location = x << 32 | z & 0xFFFFFFFF;
    if (visitedVampireChunks.contains(Long.valueOf(location))) {
      return false;
    }
    visitedVampireChunks.add(Long.valueOf(location));
    


    return true;
  }
  
  public int getVampireQuestCounter() {
    return vampireQuestCounter;
  }
  
  public void increaseVampireQuestCounter() {
    vampireQuestCounter += 1;
    if (vampireQuestCounter > 10000) {
      vampireQuestCounter = 10000;
    }
  }
  
  public void resetVampireQuestCounter() {
    vampireQuestCounter = 0;
  }
  
  boolean resetSleep;
  public void scheduleSync()
  {
    getPlayerData = true;
  }
  
  public void processSync() {
    if (getPlayerData) {
      getPlayerData = false;
      for (Object obj : player.field_70170_p.field_73010_i) {
        EntityPlayer otherPlayer = (EntityPlayer)obj;
        if (otherPlayer != player)
        {

          Witchery.packetPipeline.sendTo(new PacketPlayerStyle(otherPlayer), player);
        }
      }
    }
  }
  
  int cachedSky;
  private Coord mirrorWorldEntryPoint;
  public void checkSleep(boolean start)
  {
    if (start) {
      if ((isVampire()) && (player.field_71083_bS) && (player.field_70170_p.func_72935_r())) {
        resetSleep = true;
        cachedSky = player.field_70170_p.field_73008_k;
        player.field_70170_p.field_73008_k = 4;
      }
    }
    else if (resetSleep) {
      resetSleep = false;
      player.field_70170_p.field_73008_k = cachedSky;
    }
  }
  

  public boolean hasVampireBook()
  {
    for (ItemStack stack : player.field_71071_by.field_70462_a) {
      if ((stack != null) && (stack.func_77973_b() == ItemsVAMPIRE_BOOK)) {
        return stack.func_77960_j() < 9;
      }
    }
    return false;
  }
  

  public void setMirrorWorldEntryPoint(int x, int y, int z)
  {
    mirrorWorldEntryPoint = new Coord(x, y, z);
  }
  
  public Coord getMirrorWorldEntryPoint() {
    return mirrorWorldEntryPoint;
  }
  
  public boolean isMirrorWorldEntryPoint(int x, int y, int z) {
    return (mirrorWorldEntryPoint == null) || (mirrorWorldEntryPoint.isMatch(x, y, z));
  }
  
  static final long COOLDOWN_ESCAPE_1_TICKS = TimeUtil.minsToTicks(5);
  static final long COOLDOWN_ESCAPE_2_TICKS = TimeUtil.minsToTicks(60);
  long mirrorWorldEscapeCooldown1 = Long.MIN_VALUE;
  long mirrorWorldEscapeCooldown2 = Long.MIN_VALUE;
  
  public boolean canEscapeMirrorWorld(int slot) {
    if (slot == 1)
      return player.field_70170_p.func_82737_E() >= mirrorWorldEscapeCooldown1 + COOLDOWN_ESCAPE_1_TICKS;
    if (slot == 2) {
      return player.field_70170_p.func_82737_E() >= mirrorWorldEscapeCooldown2 + COOLDOWN_ESCAPE_2_TICKS;
    }
    return false;
  }
  
  public void escapedMirrorWorld(int slot)
  {
    if (slot == 1) {
      mirrorWorldEscapeCooldown1 = player.field_70170_p.func_82737_E();
    } else if (slot == 2) {
      mirrorWorldEscapeCooldown2 = player.field_70170_p.func_82737_E();
    }
  }
  
  public long getCooldownSecs(int i) {
    if (i == 1)
      return (mirrorWorldEscapeCooldown1 + COOLDOWN_ESCAPE_1_TICKS - player.field_70170_p.func_82737_E()) / 20L;
    if (i == 2) {
      return (mirrorWorldEscapeCooldown2 + COOLDOWN_ESCAPE_2_TICKS - player.field_70170_p.func_82737_E()) / 20L;
    }
    return 0L;
  }
}
