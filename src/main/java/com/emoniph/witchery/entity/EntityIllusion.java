package com.emoniph.witchery.entity;

import com.emoniph.witchery.network.PacketSound;
import com.emoniph.witchery.util.SoundEffect;
import java.util.Random;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityIllusion extends EntityMob
{
  public EntityIllusion(World world)
  {
    super(world);
    field_70178_ae = true;
    field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
    field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(this, 1.0D, false));
    field_70714_bg.func_75776_a(3, new EntityAIWander(this, 0.8D));
    field_70714_bg.func_75776_a(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    field_70714_bg.func_75776_a(5, new EntityAILookIdle(this));
    field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
  }
  

  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.illusion.name");
  }
  
  protected SoundEffect getFakeLivingSound()
  {
    return SoundEffect.NONE;
  }
  
  protected void func_110147_ax()
  {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D);
  }
  
  public boolean func_70650_aV()
  {
    return true;
  }
  
  public int func_70627_aG()
  {
    return super.func_70627_aG() * 2;
  }
  
  public net.minecraft.entity.EntityLivingBase func_70638_az()
  {
    return field_70170_p.func_72924_a(getVictimName());
  }
  
  public int func_82143_as()
  {
    return func_70638_az() == null ? 3 : 3 + (int)(func_110143_aJ() - 1.0F);
  }
  
  protected void func_70088_a()
  {
    super.func_70088_a();
    field_70180_af.func_75682_a(17, "");
    field_70180_af.func_75682_a(18, Byte.valueOf((byte)0));
  }
  
  public void func_70014_b(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70014_b(par1NBTTagCompound);
    
    if (getVictimName() == null) {
      par1NBTTagCompound.func_74778_a("Victim", "");
    } else {
      par1NBTTagCompound.func_74778_a("Victim", getVictimName());
    }
    
    par1NBTTagCompound.func_74768_a("IllusionType", getIllusionType());
  }
  
  public void func_70037_a(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70037_a(par1NBTTagCompound);
    
    String s = par1NBTTagCompound.func_74779_i("Victim");
    
    if (s.length() > 0) {
      setVictim(s);
    }
    
    setIllusionType(par1NBTTagCompound.func_74762_e("IllusionType"));
  }
  
  public String getVictimName() {
    return field_70180_af.func_75681_e(17);
  }
  
  public void setVictim(String par1Str) {
    field_70180_af.func_75692_b(17, par1Str);
  }
  
  public int getIllusionType() {
    return field_70180_af.func_75683_a(18);
  }
  
  public void setIllusionType(int par1) {
    field_70180_af.func_75692_b(18, Byte.valueOf((byte)par1));
  }
  
  private EntityPlayer victimPlayer = null;
  
  public void func_70071_h_()
  {
    super.func_70071_h_();
    if (!field_70170_p.field_72995_K) {
      if (field_70170_p.field_73012_v.nextInt(15) == 0) {
        float newHealth = func_110143_aJ() - 1.0F;
        if (newHealth <= 0.5D) {
          func_70106_y();
        } else {
          func_70606_j(newHealth);
        }
      }
      if (field_70170_p.field_73012_v.nextInt(40) == 0) {
        SoundEffect sound = getFakeLivingSound();
        if (victimPlayer == null) {
          victimPlayer = field_70170_p.func_72924_a(getVictimName());
        }
        if ((victimPlayer != null) && (sound != null) && (sound != SoundEffect.NONE) && (victimPlayer.func_70068_e(this) < 64.0D)) {
          com.emoniph.witchery.Witchery.packetPipeline.sendTo(new PacketSound(sound, this, 1.0F, 1.0F), victimPlayer);
        }
      }
    }
  }
  
  protected String func_70639_aQ()
  {
    return null;
  }
  
  protected String func_70621_aR()
  {
    return null;
  }
  
  protected String func_70673_aS()
  {
    return null;
  }
  
  public boolean func_70652_k(Entity entity)
  {
    return true;
  }
  
  public boolean func_70097_a(DamageSource par1DamageSource, float par2)
  {
    return false;
  }
  
  protected void func_70628_a(boolean par1, int par2) {}
}
