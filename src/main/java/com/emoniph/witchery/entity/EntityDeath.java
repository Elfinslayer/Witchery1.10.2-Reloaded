package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.util.ParticleEffect;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityDeath extends EntityMob implements IBossDisplayData, com.emoniph.witchery.util.IHandleDT
{
  private static final UUID attackingSpeedBoostModifierUUID = UUID.fromString("e942c510-c256-11e3-8a33-0800200c9a66");
  private static final AttributeModifier attackingSpeedBoostModifier = new AttributeModifier(attackingSpeedBoostModifierUUID, "Attacking speed boost", 6.199999809265137D, 0).func_111168_a(false);
  

  private int teleportDelay;
  

  private int stareTimer;
  

  private Entity lastEntityToAttack;
  

  private boolean isAggressive;
  


  public EntityDeath(World par1World)
  {
    super(par1World);
    func_70105_a(0.6F, 1.8F);
    field_70138_W = 1.0F;
    field_70178_ae = true;
    field_70728_aV = 80;
  }
  



  protected int func_70682_h(int par1)
  {
    return par1;
  }
  
  protected boolean func_70692_ba()
  {
    return false;
  }
  
  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.death.name");
  }
  

  protected void func_110147_ax()
  {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1000.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.30000001192092896D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0D);
  }
  
  protected void func_70088_a()
  {
    super.func_70088_a();
    field_70180_af.func_75682_a(16, new Byte((byte)0));
    field_70180_af.func_75682_a(17, new Byte((byte)0));
    field_70180_af.func_75682_a(18, new Byte((byte)0));
  }
  



  public void func_70014_b(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70014_b(par1NBTTagCompound);
  }
  

  public void func_70037_a(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70037_a(par1NBTTagCompound);
  }
  

  protected Entity func_70782_k()
  {
    EntityPlayer entityplayer = field_70170_p.func_72856_b(this, 64.0D);
    
    if (entityplayer != null) {
      if (shouldAttackPlayer(entityplayer)) {
        isAggressive = true;
        
        if (stareTimer == 0) {
          field_70170_p.func_72956_a(entityplayer, "mob.wither.spawn", 1.0F, 1.0F);
        }
        
        if (stareTimer++ == 5) {
          stareTimer = 0;
          setScreaming(true);
          return entityplayer;
        }
      } else {
        stareTimer = 0;
      }
    }
    
    return null;
  }
  


  private boolean shouldAttackPlayer(EntityPlayer par1EntityPlayer)
  {
    ItemStack itemstack = field_71071_by.field_70460_b[3];
    






    Vec3 vec3 = par1EntityPlayer.func_70676_i(1.0F).func_72432_b();
    Vec3 vec31 = Vec3.func_72443_a(field_70165_t - field_70165_t, field_70121_D.field_72338_b + field_70131_O / 2.0F - (field_70163_u + par1EntityPlayer.func_70047_e()), field_70161_v - field_70161_v);
    

    double d0 = vec31.func_72433_c();
    vec31 = vec31.func_72432_b();
    double d1 = vec3.func_72430_b(vec31);
    return d1 > 1.0D - 0.025D / d0;
  }
  









  public void func_70636_d()
  {
    if (field_70173_aa % 20 == 0) {
      func_70691_i(1.0F);
    }
    
    if (lastEntityToAttack != field_70789_a) {
      IAttributeInstance attributeinstance = func_110148_a(SharedMonsterAttributes.field_111263_d);
      attributeinstance.func_111124_b(attackingSpeedBoostModifier);
      
      if (field_70789_a != null) {
        attributeinstance.func_111121_a(attackingSpeedBoostModifier);
      }
    }
    
    lastEntityToAttack = field_70789_a;
    
















































    for (int i = 0; i < 2; i++) {
      field_70170_p.func_72869_a("portal", field_70165_t + (field_70146_Z.nextDouble() - 0.5D) * field_70130_N, field_70163_u + field_70146_Z.nextDouble() * field_70131_O - 0.25D, field_70161_v + (field_70146_Z.nextDouble() - 0.5D) * field_70130_N, (field_70146_Z.nextDouble() - 0.5D) * 2.0D, -field_70146_Z.nextDouble(), (field_70146_Z.nextDouble() - 0.5D) * 2.0D);
    }
    




























    if ((isScreaming()) && (!isAggressive))
    {


      setScreaming(false);
    }
    
    field_70703_bu = false;
    
    if (field_70789_a != null) {
      func_70625_a(field_70789_a, 100.0F, 100.0F);
    }
    
    if ((!field_70170_p.field_72995_K) && (func_70089_S())) {
      if (field_70789_a != null) {
        if ((((field_70789_a instanceof EntityPlayer)) && (shouldAttackPlayer((EntityPlayer)field_70789_a))) || (field_70170_p.field_73012_v.nextInt(100) == 0))
        {
          if (field_70789_a.func_70068_e(this) < 16.0D) {
            teleportRandomly();
          }
          
          teleportDelay = 0;
        } else if ((field_70789_a.func_70068_e(this) > 256.0D) && (teleportDelay++ >= 30) && (teleportToEntity(field_70789_a)))
        {
          teleportDelay = 0;
        }
      } else {
        setScreaming(false);
        teleportDelay = 0;
      }
    }
    
    if ((field_70170_p.field_73012_v.nextDouble() < 0.05D) && (func_70638_az() != null) && ((func_70638_azfield_70160_al) || (((func_70638_az() instanceof EntityPlayer)) && (func_70638_azfield_71075_bZ.field_75100_b))) && (!func_70638_az().func_70644_a(Potion.field_76421_d)))
    {


      func_70638_az().func_70690_d(new PotionEffect(field_76421_dfield_76415_H, 200, 5));
    }
    
    super.func_70636_d();
  }
  
  protected boolean teleportRandomly() {
    double d0 = field_70165_t + (field_70146_Z.nextDouble() - 0.5D) * 32.0D;
    double d1 = field_70163_u + (field_70146_Z.nextInt(64) - 32);
    double d2 = field_70161_v + (field_70146_Z.nextDouble() - 0.5D) * 32.0D;
    return teleportTo(d0, d1, d2);
  }
  
  protected boolean teleportToEntity(Entity par1Entity) {
    Vec3 vec3 = Vec3.func_72443_a(field_70165_t - field_70165_t, field_70121_D.field_72338_b + field_70131_O / 2.0F - field_70163_u + par1Entity.func_70047_e(), field_70161_v - field_70161_v);
    

    vec3 = vec3.func_72432_b();
    double d0 = 16.0D;
    double d1 = field_70165_t + (field_70146_Z.nextDouble() - 0.5D) * 8.0D - field_72450_a * d0;
    double d2 = field_70163_u + (field_70146_Z.nextInt(16) - 8) - field_72448_b * d0;
    double d3 = field_70161_v + (field_70146_Z.nextDouble() - 0.5D) * 8.0D - field_72449_c * d0;
    return teleportTo(d1, d2, d3);
  }
  
  protected boolean teleportTo(double par1, double par3, double par5) {
    double d3 = field_70165_t;
    double d4 = field_70163_u;
    double d5 = field_70161_v;
    field_70165_t = par1;
    field_70163_u = par3;
    field_70161_v = par5;
    boolean flag = false;
    int i = MathHelper.func_76128_c(field_70165_t);
    int j = MathHelper.func_76128_c(field_70163_u);
    int k = MathHelper.func_76128_c(field_70161_v);
    
    if (field_70170_p.func_72899_e(i, j, k)) {
      boolean flag1 = false;
      
      while ((!flag1) && (j > 0)) {
        Block block = field_70170_p.func_147439_a(i, j - 1, k);
        
        if (block.func_149688_o().func_76230_c()) {
          flag1 = true;
        } else {
          field_70163_u -= 1.0D;
          j--;
        }
      }
      
      if (flag1) {
        func_70107_b(field_70165_t, field_70163_u, field_70161_v);
        
        if ((field_70170_p.func_72945_a(this, field_70121_D).isEmpty()) && (!field_70170_p.func_72953_d(field_70121_D)))
        {
          flag = true;
        }
      }
    }
    
    if (!flag) {
      func_70107_b(d3, d4, d5);
      return false;
    }
    short short1 = 128;
    
    for (int l = 0; l < short1; l++) {
      double d6 = l / (short1 - 1.0D);
      float f = (field_70146_Z.nextFloat() - 0.5F) * 0.2F;
      float f1 = (field_70146_Z.nextFloat() - 0.5F) * 0.2F;
      float f2 = (field_70146_Z.nextFloat() - 0.5F) * 0.2F;
      double d7 = d3 + (field_70165_t - d3) * d6 + (field_70146_Z.nextDouble() - 0.5D) * field_70130_N * 2.0D;
      double d8 = d4 + (field_70163_u - d4) * d6 + field_70146_Z.nextDouble() * field_70131_O;
      double d9 = d5 + (field_70161_v - d5) * d6 + (field_70146_Z.nextDouble() - 0.5D) * field_70130_N * 2.0D;
      field_70170_p.func_72869_a("portal", d7, d8, d9, f, f1, f2);
    }
    
    field_70170_p.func_72908_a(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
    func_85030_a("mob.endermen.portal", 1.0F, 1.0F);
    return true;
  }
  

  protected String func_70639_aQ()
  {
    return null;
  }
  
  protected String func_70621_aR()
  {
    return "mob.skeleton.hurt";
  }
  
  protected String func_70673_aS()
  {
    return "mob.skeleton.death";
  }
  
  protected Item func_146068_u()
  {
    return Items.field_151103_aS;
  }
  
  protected void func_70628_a(boolean par1, int par2)
  {
    func_70099_a(new ItemStack(Items.field_151144_bL, 1, 0), 0.0F);
    func_70099_a(new ItemStack(Items.field_151103_aS, 5, 0), 0.0F);
    Enchantment enchantment = Enchantment.field_92090_c[field_70146_Z.nextInt(Enchantment.field_92090_c.length)];
    int k = MathHelper.func_76136_a(field_70146_Z, Math.min(enchantment.func_77319_d() + 2, enchantment.func_77325_b()), enchantment.func_77325_b());
    
    ItemStack itemstack = Items.field_151134_bR.func_92111_a(new net.minecraft.enchantment.EnchantmentData(enchantment, k));
    func_70099_a(itemstack, 0.0F);
    
    if (field_70170_p.field_73012_v.nextInt(4) == 0) {
      ItemStack sword = new ItemStack(Items.field_151048_u);
      EnchantmentHelper.func_77504_a(field_70170_p.field_73012_v, sword, 40);
      sword.func_151001_c(Witchery.resource("item.witchery.swordofdeath.customname"));
      func_70099_a(sword, 0.0F);
    }
    
    switch (field_70170_p.field_73012_v.nextInt(5)) {
    case 0: 
      func_70099_a(new ItemStack(Items.field_151141_av), 0.0F);
      func_70099_a(ItemsGENERIC.itemBinkyHead.createStack(), 0.0F);
      break;
    case 1: 
      func_70099_a(new ItemStack(ItemsDEATH_HOOD), 0.0F);
      break;
    case 2: 
      func_70099_a(new ItemStack(ItemsDEATH_ROBE), 0.0F);
      break;
    case 3: 
      func_70099_a(new ItemStack(ItemsDEATH_FEET), 0.0F);
      break;
    case 4: 
      func_70099_a(new ItemStack(ItemsDEATH_HAND), 0.0F);
    }
    
  }
  
  public float getCapDT(DamageSource source, float damage)
  {
    return 15.0F;
  }
  
  public boolean func_70097_a(DamageSource par1DamageSource, float par2)
  {
    if (func_85032_ar()) {
      return false;
    }
    setScreaming(true);
    
    if (((par1DamageSource instanceof EntityDamageSource)) && ((par1DamageSource.func_76346_g() instanceof EntityPlayer))) {
      isAggressive = true;
    }
    
    if ((par1DamageSource instanceof EntityDamageSourceIndirect)) {
      isAggressive = false;
      
      for (int i = 0; i < 64; i++) {
        if (teleportRandomly()) {
          return true;
        }
      }
      
      return super.func_70097_a(par1DamageSource, Math.min(par2, 15.0F));
    }
    return super.func_70097_a(par1DamageSource, Math.min(par2, 15.0F));
  }
  


  public boolean func_70652_k(Entity par1Entity)
  {
    float f = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
    int i = 0;
    
    if ((par1Entity instanceof EntityLivingBase)) {
      EntityLivingBase living = (EntityLivingBase)par1Entity;
      float maxHealth = living.func_110138_aP();
      f = Math.max(maxHealth * 0.15F, 1.0F);
    }
    
    if ((par1Entity instanceof EntityLivingBase)) {
      f += EnchantmentHelper.func_77512_a(this, (EntityLivingBase)par1Entity);
      i += EnchantmentHelper.func_77507_b(this, (EntityLivingBase)par1Entity);
    }
    
    boolean flag = com.emoniph.witchery.util.EntityUtil.touchOfDeath(par1Entity, this, f);
    

    if (flag) {
      if (i > 0) {
        par1Entity.func_70024_g(-MathHelper.func_76126_a(field_70177_z * 3.1415927F / 180.0F) * i * 0.5F, 0.1D, MathHelper.func_76134_b(field_70177_z * 3.1415927F / 180.0F) * i * 0.5F);
        
        field_70159_w *= 0.6D;
        field_70179_y *= 0.6D;
      }
      
      int j = EnchantmentHelper.func_90036_a(this);
      
      if (j > 0) {
        par1Entity.func_70015_d(j * 4);
      }
    }
    
    return flag;
  }
  

  public void func_70645_a(DamageSource par1DamageSource)
  {
    super.func_70645_a(par1DamageSource);
    if (!field_70170_p.field_72995_K) {
      ParticleEffect.PORTAL.send(com.emoniph.witchery.util.SoundEffect.MOB_ENDERMEN_PORTAL, this, 1.0D, 2.0D, 16);
    }
    func_70106_y();
  }
  
  public boolean isScreaming() {
    return field_70180_af.func_75683_a(18) > 0;
  }
  
  public void setScreaming(boolean par1) {
    field_70180_af.func_75692_b(18, Byte.valueOf((byte)(par1 ? 1 : 0)));
  }
}
