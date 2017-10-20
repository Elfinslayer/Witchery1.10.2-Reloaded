package com.emoniph.witchery.entity;

import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.util.ParticleEffect;
import java.util.Random;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntitySummonedUndead extends EntityMob
{
  private int timeToLive = -1;
  
  public EntitySummonedUndead(World world) { super(world); }
  

  protected void func_70088_a()
  {
    super.func_70088_a();
    field_70180_af.func_75682_a(17, "");
    field_70180_af.func_75682_a(18, Integer.valueOf(Integer.valueOf(0).intValue()));
    field_70180_af.func_75682_a(19, Integer.valueOf(Integer.valueOf(0).intValue()));
  }
  
  public void func_70014_b(NBTTagCompound nbtRoot)
  {
    super.func_70014_b(nbtRoot);
    if (getSummonerName() == null) {
      nbtRoot.func_74778_a("Summoner", "");
    } else {
      nbtRoot.func_74778_a("Summoner", getSummonerName());
    }
    
    nbtRoot.func_74757_a("Obscured", isObscured());
    nbtRoot.func_74768_a("SuicideIn", timeToLive);
  }
  
  public void func_70037_a(NBTTagCompound nbtRoot)
  {
    super.func_70037_a(nbtRoot);
    String s = nbtRoot.func_74779_i("Summoner");
    if (s.length() > 0) {
      setSummoner(s);
    }
    setObscured(nbtRoot.func_74767_n("Obscured"));
    if (nbtRoot.func_74764_b("SuicideIn")) {
      timeToLive = nbtRoot.func_74762_e("SuicideIn");
    } else {
      timeToLive = -1;
    }
  }
  
  public void setTimeToLive(int i) {
    timeToLive = i;
  }
  
  public boolean isTemp() {
    return timeToLive != -1;
  }
  
  protected int func_70682_h(int par1)
  {
    return par1;
  }
  
  public String getSummonerName() {
    return field_70180_af.func_75681_e(17);
  }
  
  public void setSummoner(String par1Str) {
    func_110163_bv();
    field_70180_af.func_75692_b(17, par1Str);
  }
  
  public net.minecraft.entity.player.EntityPlayer getSummoner() {
    return field_70170_p.func_72924_a(getSummonerName());
  }
  
  public EnumCreatureAttribute func_70668_bt()
  {
    return EnumCreatureAttribute.UNDEAD;
  }
  
  protected void func_70628_a(boolean par1, int par2)
  {
    if (!isTemp()) {
      int chance = field_70146_Z.nextInt(Math.max(4 - par2, 2));
      int quantity = chance == 0 ? 1 : 0;
      if (quantity > 0) {
        func_70099_a(ItemsGENERIC.itemSpectralDust.createStack(quantity), 0.0F);
      }
    }
  }
  
  protected void func_70629_bd() {
    super.func_70629_bd();
    if ((field_70170_p != null) && (!field_70128_L) && (!field_70170_p.field_72995_K) && (timeToLive != -1) && ((--timeToLive == 0) || (func_70638_az() == null) || (func_70638_azfield_70128_L))) {
      ParticleEffect.EXPLODE.send(com.emoniph.witchery.util.SoundEffect.NONE, this, 1.0D, 1.0D, 16);
      func_70106_y();
    }
  }
  
  public int func_70627_aG()
  {
    return super.func_70627_aG() * 3;
  }
  
  public boolean isScreaming() {
    return field_70180_af.func_75679_c(18) == 1;
  }
  
  protected void setScreaming(boolean screaming) {
    field_70180_af.func_75692_b(18, Integer.valueOf(Integer.valueOf(screaming ? 1 : 0).intValue()));
  }
  
  public boolean isObscured() {
    return field_70180_af.func_75679_c(19) == 1;
  }
  
  public void setObscured(boolean obscured) {
    field_70180_af.func_75692_b(19, Integer.valueOf(Integer.valueOf(obscured ? 1 : 0).intValue()));
  }
  
  public boolean func_70097_a(DamageSource damageSource, float damage)
  {
    return super.func_70097_a(damageSource, Math.min(damage, 15.0F));
  }
}
