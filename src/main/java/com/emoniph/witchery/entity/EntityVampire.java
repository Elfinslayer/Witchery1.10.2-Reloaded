package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockBloodCrucible.TileEntityBloodCrucible;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.common.ExtendedVillager;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.CreatureUtil;
import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.IHandleDT;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;

public class EntityVampire extends EntityCreature implements IEntitySelector, IHandleDT
{
  private Village villageObj;
  
  public EntityVampire(World world)
  {
    super(world);
    func_70661_as().func_75491_a(true);
    func_70661_as().func_75498_b(true);
    
    field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
    field_70714_bg.func_75776_a(2, new net.minecraft.entity.ai.EntityAIRestrictSun(this));
    field_70714_bg.func_75776_a(3, new net.minecraft.entity.ai.EntityAIFleeSun(this, 1.0D));
    

    field_70714_bg.func_75776_a(8, new EntityAIRestrictOpenDoor(this));
    field_70714_bg.func_75776_a(8, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.2D, false));
    field_70714_bg.func_75776_a(9, new EntityAIOpenDoor(this, true));
    field_70714_bg.func_75776_a(10, new net.minecraft.entity.ai.EntityAIWander(this, 1.0D));
    field_70714_bg.func_75776_a(11, new net.minecraft.entity.ai.EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    field_70714_bg.func_75776_a(12, new net.minecraft.entity.ai.EntityAILookIdle(this));
    field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
    field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityCreature.class, 0, false, true, this));
    

    field_70728_aV = 20;
  }
  
  public boolean func_82704_a(Entity entity) {
    return (((entity instanceof EntityVillager)) && (villageObj != null)) || (((entity instanceof EntityPlayer)) && (!ExtendedPlayer.get((EntityPlayer)entity).isVampire()));
  }
  

  protected void func_70629_bd()
  {
    super.func_70629_bd();
    
    if (!field_70170_p.field_72995_K) {
      if (field_70170_p.func_72935_r()) {
        if (func_70643_av() == null) {
          func_70624_b(null);
        }
        if (field_70173_aa % 100 == 2) {
          villageObj = null;
          damageDone = 0.0F;
          if (func_70092_e(coffinPos.field_71574_a, coffinPos.field_71572_b, coffinPos.field_71573_c) > 16.0D) {
            ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, this, 0.8D, 1.5D, 16);
            EntityUtil.moveToBlockPositionAndUpdate(this, coffinPos.field_71574_a, coffinPos.field_71572_b, coffinPos.field_71573_c, 8);
            
            ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, this, 0.8D, 1.5D, 16);
            func_110171_b(coffinPos.field_71574_a, coffinPos.field_71572_b, coffinPos.field_71573_c, 4);
          }
        }
        if ((field_70173_aa % 20 == 2) && 
          (CreatureUtil.isInSunlight(this))) {
          func_70015_d(2);
        }
        
      }
      else if (damageDone >= 20.0F) {
        if (villageObj != null) {
          func_70624_b(null);
          func_70604_c(null);
          villageObj = null;
          ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, this, 0.8D, 1.5D, 16);
          EntityUtil.moveToBlockPositionAndUpdate(this, coffinPos.field_71574_a, coffinPos.field_71572_b, coffinPos.field_71573_c, 8);
          
          ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, this, 0.8D, 1.5D, 16);
          func_110171_b(coffinPos.field_71574_a, coffinPos.field_71572_b, coffinPos.field_71573_c, 4);
          tryFillBloodCrucible();
        }
        

      }
      else if ((villageObj == null) && (field_70173_aa % 500 == 2)) {
        villageObj = field_70170_p.field_72982_D.func_75550_a(MathHelper.func_76128_c(field_70165_t), MathHelper.func_76128_c(field_70163_u), MathHelper.func_76128_c(field_70161_v), 128);
        

        if (villageObj != null) {
          ChunkCoordinates townPos = villageObj.func_75577_a();
          ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, this, 0.8D, 1.5D, 16);
          EntityUtil.moveToBlockPositionAndUpdate(this, field_71574_a, field_71572_b, field_71573_c, 8);
          
          ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, this, 0.8D, 1.5D, 16);
          func_110171_b(field_71574_a, field_71572_b, field_71573_c, villageObj.func_75568_b());
        }
      }
    }
  }
  

  public void tryFillBloodCrucible()
  {
    int r = 6;
    for (int x = coffinPos.field_71574_a - 6; x <= coffinPos.field_71574_a + 6; x++) {
      for (int z = coffinPos.field_71573_c - 6; z <= coffinPos.field_71573_c + 6; z++) {
        for (int y = coffinPos.field_71572_b - 6; y <= coffinPos.field_71572_b + 6; y++) {
          if (field_70170_p.func_147439_a(x, y, z) == BlocksBLOOD_CRUCIBLE) {
            BlockBloodCrucible.TileEntityBloodCrucible crucible = (BlockBloodCrucible.TileEntityBloodCrucible)BlockUtil.getTileEntity(field_70170_p, x, y, z, BlockBloodCrucible.TileEntityBloodCrucible.class);
            
            if (crucible != null) {
              crucible.increaseBloodLevel();
            }
            return;
          }
        }
      }
    }
  }
  
  public EnumCreatureAttribute func_70668_bt() {
    return EnumCreatureAttribute.UNDEAD;
  }
  

  private ChunkCoordinates coffinPos = new ChunkCoordinates(0, 0, 0);
  
  public void setStalkingArea(int p_110171_1_, int p_110171_2_, int p_110171_3_) {
    coffinPos.func_71571_b(p_110171_1_, p_110171_2_, p_110171_3_);
  }
  
  protected String func_145776_H()
  {
    return "game.hostile.swim";
  }
  
  protected String func_145777_O()
  {
    return "game.hostile.swim.splash";
  }
  
  protected void func_110147_ax()
  {
    super.func_110147_ax();
    func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
    func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4D);
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
  }
  
  protected void func_70088_a()
  {
    super.func_70088_a();
    field_70180_af.func_75682_a(13, new Byte((byte)0));
    field_70180_af.func_75682_a(14, new Integer(500));
  }
  
  public boolean func_70650_aV()
  {
    return true;
  }
  
  protected String func_70639_aQ()
  {
    return "mob.villager.idle";
  }
  
  protected String func_70621_aR()
  {
    return "mob.villager.hit";
  }
  
  protected String func_70673_aS()
  {
    return "mob.villager.death";
  }
  
  protected float func_70647_i()
  {
    return 0.6F;
  }
  
  public void func_70636_d()
  {
    func_82168_bl();
    float f = func_70013_c(1.0F);
    
    if (f > 0.5F) {
      field_70708_bq += 2;
    }
    
    super.func_70636_d();
  }
  
  float damageDone = 0.0F;
  
  public boolean func_70652_k(Entity entity)
  {
    float f = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
    int i = 0;
    
    if ((entity instanceof EntityLivingBase)) {
      f += EnchantmentHelper.func_77512_a(this, (EntityLivingBase)entity);
      i += EnchantmentHelper.func_77507_b(this, (EntityLivingBase)entity);
    }
    boolean flag;
    boolean flag;
    if ((entity instanceof EntityVillager)) {
      ExtendedVillager villagerEx = ExtendedVillager.get((EntityVillager)entity);
      if ((villagerEx != null) && (field_70170_p.field_73012_v.nextInt(10) == 0)) {
        damageDone += 4.0F;
        int taken = villagerEx.takeBlood(30, this);
        if (taken > 0) {
          func_70691_i(4.0F);
          ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, field_70170_p, field_70165_t, field_70163_u + field_70131_O * 0.8D, field_70161_v, 0.5D, 0.2D, 16);
        }
      }
      
      flag = true;
    } else {
      boolean needsBlood = damageDone < 20.0F;
      flag = entity.func_70097_a(DamageSource.func_76358_a(this), f);
      
      if (flag) {
        if (i > 0) {
          entity.func_70024_g(-MathHelper.func_76126_a(field_70177_z * 3.1415927F / 180.0F) * i * 0.5F, 0.1D, MathHelper.func_76134_b(field_70177_z * 3.1415927F / 180.0F) * i * 0.5F);
          
          field_70159_w *= 0.6D;
          field_70179_y *= 0.6D;
        }
        
        int j = EnchantmentHelper.func_90036_a(this);
        
        if (j > 0) {
          entity.func_70015_d(j * 4);
        }
        
        if ((entity instanceof EntityLivingBase)) {
          EnchantmentHelper.func_151384_a((EntityLivingBase)entity, this);
        }
        
        EnchantmentHelper.func_151385_b(this, entity);
      }
    }
    
    return flag;
  }
  
  protected void func_70785_a(Entity p_70785_1_, float p_70785_2_)
  {
    if ((field_70724_aR <= 0) && (p_70785_2_ < 2.0F) && (field_70121_D.field_72337_e > field_70121_D.field_72338_b) && (field_70121_D.field_72338_b < field_70121_D.field_72337_e))
    {
      field_70724_aR = 20;
      func_70652_k(p_70785_1_);
    }
  }
  


  protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {}
  

  public void func_70098_U()
  {
    super.func_70098_U();
    
    if ((field_70154_o instanceof EntityCreature)) {
      EntityCreature entitycreature = (EntityCreature)field_70154_o;
      field_70761_aq = field_70761_aq;
    }
  }
  
  protected String func_146067_o(int p_146067_1_)
  {
    return p_146067_1_ > 4 ? "game.hostile.hurt.fall.big" : "game.hostile.hurt.fall.small";
  }
  
  public boolean func_70097_a(DamageSource source, float damage)
  {
    return super.func_70097_a(source, damage);
  }
  
  public float getCapDT(DamageSource source, float damage)
  {
    return 0.0F;
  }
  
  public void func_70645_a(DamageSource source)
  {
    if (!CreatureUtil.checkForVampireDeath(this, source)) {
      return;
    }
    super.func_70645_a(source);
  }
  
  protected Item func_146068_u()
  {
    return net.minecraft.init.Items.field_151097_aZ;
  }
  
  protected boolean func_70692_ba()
  {
    return false;
  }
  


  protected void func_70600_l(int p_70600_1_) {}
  


  protected void func_82164_bB()
  {
    func_70062_b(1, new ItemStack(ItemsVAMPIRE_BOOTS));
    boolean male = field_70170_p.field_73012_v.nextBoolean();
    if (male) {
      func_70062_b(2, new ItemStack(field_70170_p.field_73012_v.nextInt(3) == 0 ? ItemsVAMPIRE_LEGS_KILT : ItemsVAMPIRE_LEGS));
      

      func_70062_b(3, new ItemStack(field_70170_p.field_73012_v.nextInt(3) == 0 ? ItemsVAMPIRE_COAT_CHAIN : ItemsVAMPIRE_COAT));
    }
    else
    {
      func_70062_b(2, new ItemStack(field_70170_p.field_73012_v.nextInt(4) != 0 ? ItemsVAMPIRE_LEGS_KILT : ItemsVAMPIRE_LEGS));
      

      func_70062_b(3, new ItemStack(field_70170_p.field_73012_v.nextInt(3) == 0 ? ItemsVAMPIRE_COAT_FEMALE_CHAIN : ItemsVAMPIRE_COAT_FEMALE));
    }
  }
  


  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.vampire.name");
  }
  

  public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_)
  {
    p_110161_1_ = super.func_110161_a(p_110161_1_);
    
    func_82164_bB();
    coffinPos.func_71571_b((int)field_70165_t, (int)field_70163_u, (int)field_70161_v);
    
    return p_110161_1_;
  }
  
  public int getGuardType() {
    return field_70180_af.func_75683_a(13);
  }
  
  public void setGuardType(int p_82201_1_) {
    field_70180_af.func_75692_b(13, Byte.valueOf((byte)p_82201_1_));
  }
  

  public void func_70037_a(NBTTagCompound nbtRoot)
  {
    super.func_70037_a(nbtRoot);
    
    if (nbtRoot.func_150297_b("GuardType", 99)) {
      byte b0 = nbtRoot.func_74771_c("GuardType");
      setGuardType(b0);
    }
    


    coffinPos.func_71571_b(nbtRoot.func_74762_e("BaseX"), nbtRoot.func_74762_e("BaseY"), nbtRoot.func_74762_e("BaseZ"));
  }
  
  public void func_70014_b(NBTTagCompound nbtRoot)
  {
    super.func_70014_b(nbtRoot);
    nbtRoot.func_74774_a("GuardType", (byte)getGuardType());
    ChunkCoordinates home = func_110172_bL();
    



    nbtRoot.func_74768_a("BaseX", coffinPos.field_71574_a);
    nbtRoot.func_74768_a("BaseY", coffinPos.field_71572_b);
    nbtRoot.func_74768_a("BaseZ", coffinPos.field_71573_c);
  }
  
  public void func_70062_b(int p_70062_1_, ItemStack p_70062_2_)
  {
    super.func_70062_b(p_70062_1_, p_70062_2_);
  }
  
  public double func_70033_W()
  {
    return super.func_70033_W() - 0.5D;
  }
}
