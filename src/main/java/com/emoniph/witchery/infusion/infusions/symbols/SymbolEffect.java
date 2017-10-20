package com.emoniph.witchery.infusion.infusions.symbols;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.util.Const;
import com.emoniph.witchery.util.TimeUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class SymbolEffect
{
  private final int effectID;
  private final String unlocalisedName;
  private final int chargeCost;
  private final boolean curse;
  private final boolean fallsToEarth;
  private final String knowledgeKey;
  private final boolean isVisible;
  private byte[] defaultStrokes;
  private final int cooldownTicks;
  
  public SymbolEffect(int effectID, String unlocalisedName)
  {
    this(effectID, unlocalisedName, 1, false, false, null, 0, true);
  }
  
  public SymbolEffect(int effectID, String unlocalisedName, int spellCost, boolean curse, boolean fallsToEarth, String knowledgeKey, int cooldown) {
    this(effectID, unlocalisedName, spellCost, curse, fallsToEarth, knowledgeKey, cooldown, true);
  }
  
  public SymbolEffect(int effectID, String unlocalisedName, int spellCost, boolean curse, boolean fallsToEarth, String knowledgeKey, int cooldown, boolean isVisible) {
    this.effectID = effectID;
    this.unlocalisedName = unlocalisedName;
    chargeCost = spellCost;
    this.curse = curse;
    this.fallsToEarth = fallsToEarth;
    this.knowledgeKey = knowledgeKey;
    cooldownTicks = cooldown;
    this.isVisible = isVisible;
  }
  
  public int getEffectID()
  {
    return effectID;
  }
  
  public boolean isCurse() {
    return curse;
  }
  
  public boolean isUnforgivable() {
    return (curse) && (knowledgeKey == null);
  }
  
  public String getLocalizedName() {
    return Witchery.resource(unlocalisedName);
  }
  
  public abstract void perform(World paramWorld, EntityPlayer paramEntityPlayer, int paramInt);
  
  public int getChargeCost(World world, EntityPlayer player, int level) {
    return net.minecraft.util.MathHelper.func_76128_c(Math.pow(2.0D, level - 1) * chargeCost);
  }
  
  public boolean fallsToEarth() {
    return fallsToEarth;
  }
  
  public boolean hasValidInfusion(EntityPlayer player, int infusionID) {
    if (field_71075_bZ.field_75098_d) {
      return true;
    }
    
    if (infusionID <= 0) {
      return false;
    }
    
    if ((isUnforgivable()) && (infusionID != RecipesinfusionBeast.infusionID)) {
      return false;
    }
    
    return true;
  }
  
  public boolean isVisible(EntityPlayer player) {
    return isVisible;
  }
  
  public String getDescription() {
    StringBuffer sb = new StringBuffer();
    
    sb.append("§n");
    sb.append(Witchery.resource(unlocalisedName));
    sb.append("§r");
    sb.append(Const.BOOK_NEWLINE);
    sb.append(Const.BOOK_NEWLINE);
    
    String descKey = unlocalisedName + ".info";
    String description = Witchery.resource(descKey);
    if ((description != null) && (!description.isEmpty()) && (!description.equals(descKey))) {
      sb.append(description);
      sb.append(Const.BOOK_NEWLINE);
      sb.append(Const.BOOK_NEWLINE);
    }
    
    sb.append("§8");
    sb.append(Witchery.resource("witchery.book.wands.strokes"));
    sb.append("§0");
    sb.append(Const.BOOK_NEWLINE);
    int i = 1;
    for (byte stroke : defaultStrokes) {
      sb.append(i++);
      sb.append(": ");
      sb.append(Witchery.resource("witchery.book.wands.stroke." + stroke));
      sb.append(Const.BOOK_NEWLINE);
    }
    
    return sb.toString();
  }
  
  public void setDefaultStrokes(byte[] strokes) {
    defaultStrokes = strokes;
  }
  
  public boolean hasValidKnowledge(EntityPlayer player, NBTTagCompound nbtPlayer) {
    if (field_71075_bZ.field_75098_d) {
      return true;
    }
    if (knowledgeKey != null) {
      if (nbtPlayer.func_74764_b("WITCSpellBook")) {
        NBTTagCompound nbtSpells = nbtPlayer.func_74775_l("WITCSpellBook");
        return nbtSpells.func_74767_n(knowledgeKey);
      }
      return false;
    }
    
    return true;
  }
  
  public void acquireKnowledge(EntityPlayer player)
  {
    if (knowledgeKey != null) {
      NBTTagCompound nbtPlayer = Infusion.getNBT(player);
      if (nbtPlayer != null) {
        if (!nbtPlayer.func_74764_b("WITCSpellBook")) {
          nbtPlayer.func_74782_a("WITCSpellBook", new NBTTagCompound());
        }
        NBTTagCompound nbtSpells = nbtPlayer.func_74775_l("WITCSpellBook");
        nbtSpells.func_74757_a(knowledgeKey, true);
      }
    }
  }
  
  public static String getKnowledge(EntityPlayer player) {
    StringBuilder sb = new StringBuilder();
    NBTTagCompound nbtPlayer = Infusion.getNBT(player);
    if ((nbtPlayer != null) && 
      (nbtPlayer.func_74764_b("WITCSpellBook"))) {
      for (SymbolEffect effect : EffectRegistry.instance().getEffects()) {
        if ((knowledgeKey != null) && (effect.hasValidKnowledge(player, nbtPlayer))) {
          if (sb.length() > 0) {
            sb.append(", ");
          }
          sb.append(effect.getLocalizedName());
        }
      }
    }
    
    return sb.toString();
  }
  
  public long cooldownRemaining(EntityPlayer player, NBTTagCompound nbtPlayer) {
    if ((cooldownTicks > 0) && (knowledgeKey != null) && 
      (nbtPlayer.func_74764_b("WITCSpellBook"))) {
      NBTTagCompound nbtSpells = nbtPlayer.func_74775_l("WITCSpellBook");
      long lastUseTime = nbtSpells.func_74763_f(knowledgeKey + "LastUse");
      long timeNow = TimeUtil.getServerTimeInTicks();
      if (timeNow < lastUseTime + cooldownTicks) {
        return lastUseTime + cooldownTicks - timeNow;
      }
    }
    
    return 0L;
  }
  
  public void setOnCooldown(EntityPlayer player) {
    if ((cooldownTicks > 0) && (knowledgeKey != null) && (!field_71075_bZ.field_75098_d)) {
      NBTTagCompound nbtPlayer = Infusion.getNBT(player);
      if ((nbtPlayer != null) && 
        (nbtPlayer.func_74764_b("WITCSpellBook"))) {
        NBTTagCompound nbtSpells = nbtPlayer.func_74775_l("WITCSpellBook");
        nbtSpells.func_74772_a(knowledgeKey + "LastUse", TimeUtil.getServerTimeInTicks());
      }
    }
  }
  

  public boolean equals(Object obj)
  {
    if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    
    if (obj == this) {
      return true;
    }
    
    SymbolEffect other = (SymbolEffect)obj;
    return effectID == effectID;
  }
  
  public int hashCode()
  {
    int result = 17;
    result = 37 * result + effectID;
    return result;
  }
}
