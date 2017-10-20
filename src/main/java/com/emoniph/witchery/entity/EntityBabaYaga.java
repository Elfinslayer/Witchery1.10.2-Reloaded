package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.util.IHandleDT;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityBabaYaga extends EntityMob implements net.minecraft.entity.boss.IBossDisplayData, IRangedAttackMob, IEntitySelector, IOwnable, IHandleDT
{
  private static final UUID field_110184_bp = UUID.fromString("ab0df555-0786-4951-a8df-ca61749f164e");
  private static final AttributeModifier field_110185_bq = new AttributeModifier(field_110184_bp, "Drinking speed penalty", -0.25D, 0).func_111168_a(false);
  

  private static final int[] witchDrops = { ItemsGENERIC.itemSpectralDust.damageValue, ItemsGENERIC.itemBatWool.damageValue, ItemsGENERIC.itemToeOfFrog.damageValue, ItemsGENERIC.itemOwletsWing.damageValue, ItemsGENERIC.itemDogTongue.damageValue, ItemsGENERIC.itemBrewOfVines.damageValue, ItemsGENERIC.itemBrewOfSprouting.damageValue, ItemsGENERIC.itemBrewOfHitchcock.damageValue, ItemsGENERIC.itemBrewOfCursedLeaping.damageValue, ItemsGENERIC.itemBrewOfFrogsTongue.damageValue };
  
  private int witchAttackTimer;
  
  private static final double MAX_HEALTH = 500.0D;
  

  public EntityBabaYaga(World par1World)
  {
    super(par1World);
    func_70661_as().func_75491_a(true);
    field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
    field_70714_bg.func_75776_a(2, new EntityAIArrowAttack(this, 1.0D, 60, 10.0F));
    field_70714_bg.func_75776_a(2, new EntityAIWander(this, 1.0D));
    field_70714_bg.func_75776_a(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    field_70714_bg.func_75776_a(3, new net.minecraft.entity.ai.EntityAILookIdle(this));
    field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
    field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false, true, this));
    field_70728_aV = 70;
  }
  
  protected void func_70088_a()
  {
    super.func_70088_a();
    func_70096_w().func_75682_a(21, Byte.valueOf((byte)0));
    field_70180_af.func_75682_a(17, "");
  }
  
  public void func_70014_b(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70014_b(par1NBTTagCompound);
    
    if (getOwnerName() == null) {
      par1NBTTagCompound.func_74778_a("Owner", "");
    } else {
      par1NBTTagCompound.func_74778_a("Owner", getOwnerName());
    }
  }
  
  public void func_70037_a(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70037_a(par1NBTTagCompound);
    String s = par1NBTTagCompound.func_74779_i("Owner");
    
    if (s.length() > 0) {
      setOwner(s);
    }
  }
  
  public String getOwnerName() {
    return field_70180_af.func_75681_e(17);
  }
  
  public void setOwner(String par1Str) {
    field_70180_af.func_75692_b(17, par1Str);
  }
  
  public EntityPlayer getOwnerEntity() {
    return field_70170_p.func_72924_a(getOwnerName());
  }
  
  public boolean func_82704_a(Entity entity)
  {
    if ((entity != null) && 
      ((entity instanceof EntityPlayer))) {
      String ownerName = getOwnerName();
      boolean isOwned = ((EntityPlayer)entity).func_70005_c_().equalsIgnoreCase(ownerName);
      return !isOwned;
    }
    

    return true;
  }
  
  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.babayaga.name");
  }
  

  public int func_70658_aO()
  {
    return 4;
  }
  
  protected String func_70639_aQ()
  {
    return "witchery:mob.baba.baba_living";
  }
  
  protected String func_70621_aR()
  {
    return "mob.witch.hurt";
  }
  
  protected String func_70673_aS()
  {
    return "witchery:mob.baba.baba_death";
  }
  
  public void setAggressive(boolean par1) {
    func_70096_w().func_75692_b(21, Byte.valueOf((byte)(par1 ? 1 : 0)));
  }
  
  public boolean getAggressive() {
    return func_70096_w().func_75683_a(21) == 1;
  }
  


  protected void func_110147_ax()
  {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(500.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
  }
  

  protected int func_70682_h(int par1)
  {
    return par1;
  }
  


  protected void func_70069_a(float par1) {}
  

  public boolean func_70650_aV()
  {
    return true;
  }
  
  public void func_70636_d()
  {
    if (!field_70170_p.field_72995_K) {
      if (getAggressive()) {
        if (witchAttackTimer-- <= 0) {
          setAggressive(false);
          ItemStack itemstack = func_70694_bm();
          func_70062_b(0, (ItemStack)null);
          
          if ((itemstack != null) && (itemstack.func_77973_b() == Items.field_151068_bn)) {
            List list = Items.field_151068_bn.func_77832_l(itemstack);
            
            if (list != null) {
              Iterator iterator = list.iterator();
              
              while (iterator.hasNext()) {
                PotionEffect potioneffect = (PotionEffect)iterator.next();
                func_70690_d(new PotionEffect(potioneffect));
              }
            }
          }
          
          func_110148_a(SharedMonsterAttributes.field_111263_d).func_111124_b(field_110185_bq);
        }
      } else {
        short short1 = -1;
        
        if ((field_70146_Z.nextFloat() < 0.15F) && (func_70027_ad()) && (!func_70644_a(Potion.field_76426_n))) {
          short1 = 16307;
        } else if ((field_70146_Z.nextFloat() < 0.01F) && (func_110143_aJ() < func_110138_aP())) {
          short1 = 16341;
        } else if ((field_70146_Z.nextFloat() < 0.25F) && (func_70638_az() != null) && (!func_70644_a(Potion.field_76424_c)) && (func_70638_az().func_70068_e(this) > 121.0D))
        {
          short1 = 16274;
        } else if ((field_70146_Z.nextFloat() < 0.25F) && (func_70638_az() != null) && (!func_70644_a(Potion.field_76424_c)) && (func_70638_az().func_70068_e(this) > 121.0D))
        {
          short1 = 16274;
        }
        
        if (short1 > -1) {
          func_70062_b(0, new ItemStack(Items.field_151068_bn, 1, short1));
          witchAttackTimer = (func_70694_bm().func_77988_m() - 20);
          setAggressive(true);
          IAttributeInstance attributeinstance = func_110148_a(SharedMonsterAttributes.field_111263_d);
          attributeinstance.func_111124_b(field_110185_bq);
          attributeinstance.func_111121_a(field_110185_bq);
        }
      }
      
      if (field_70146_Z.nextFloat() < 7.5E-4F) {
        field_70170_p.func_72960_a(this, (byte)15);
      }
      
      if (((func_70661_as().func_75500_f()) || (field_70170_p.field_73012_v.nextDouble() < 0.02D)) && (func_70638_az() != null) && (field_70173_aa - ticksSinceTeleport > 100L))
      {
        ticksSinceTeleport = field_70173_aa;
        teleportToEntity(func_70638_az());
      }
      
      if ((field_70170_p.field_73012_v.nextDouble() < 0.05D) && (func_70638_az() != null) && ((func_70638_azfield_70160_al) || (((func_70638_az() instanceof EntityPlayer)) && (func_70638_azfield_71075_bZ.field_75100_b))) && (!func_70638_az().func_70644_a(Potion.field_76421_d)))
      {


        func_70638_az().func_70690_d(new PotionEffect(field_76421_dfield_76415_H, 200, 5));
      }
      
      EntityPlayer owner = getOwnerEntity();
      if (owner != null) {
        double distance = func_70068_e(owner);
        if ((distance < 64.0D) && (field_70173_aa % 100 == 0)) {
          int l = field_70146_Z.nextInt(3);
          int i1 = witchDrops[field_70146_Z.nextInt(witchDrops.length - 3)];
          for (int j1 = 0; j1 < l; j1++) {
            func_70099_a(new ItemStack(ItemsGENERIC, 1, i1), 0.0F);
          }
        }
        
        if (field_70173_aa > 600) {
          func_70106_y();
          ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, this, 1.0D, 2.0D, 16);
        }
      }
    }
    
    super.func_70636_d();
  }
  
  long ticksSinceTeleport = 0L;
  
  protected boolean teleportToEntity(Entity par1Entity) {
    Vec3 vec3 = Vec3.func_72443_a(field_70165_t - field_70165_t, field_70121_D.field_72338_b + field_70131_O / 2.0F - field_70163_u + par1Entity.func_70047_e(), field_70161_v - field_70161_v);
    
    vec3 = vec3.func_72432_b();
    double d0 = 8.0D;
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
  

  protected float func_70672_c(DamageSource par1DamageSource, float par2)
  {
    par2 = super.func_70672_c(par1DamageSource, par2);
    
    if (par1DamageSource.func_76346_g() == this) {
      par2 = 0.0F;
    }
    
    if (par1DamageSource.func_82725_o()) {
      par2 = (float)(par2 * 0.15D);
    }
    
    return par2;
  }
  
  @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  public void func_70103_a(byte par1)
  {
    if (par1 == 15) {
      for (int i = 0; i < field_70146_Z.nextInt(35) + 10; i++) {
        field_70170_p.func_72869_a("witchMagic", field_70165_t + field_70146_Z.nextGaussian() * 0.12999999523162842D, field_70121_D.field_72337_e + 0.5D + field_70146_Z.nextGaussian() * 0.12999999523162842D, field_70161_v + field_70146_Z.nextGaussian() * 0.12999999523162842D, 0.0D, 0.0D, 0.0D);
      }
      
    }
    else {
      super.func_70103_a(par1);
    }
  }
  
  public boolean func_70097_a(DamageSource source, float damage)
  {
    boolean result = super.func_70097_a(source, Math.min(damage, 15.0F));
    if ((!field_70170_p.field_72995_K) && (source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityLiving))) {
      EntityLiving attacker = (EntityLiving)source.func_76346_g();
      if ((attacker.func_70668_bt() == EnumCreatureAttribute.UNDEAD) || (((attacker instanceof EntityTameable)) && (!((EntityTameable)attacker).func_70909_n())))
      {
        EntityCaveSpider spider = new EntityCaveSpider(field_70170_p);
        spider.func_70012_b(field_70165_t, field_70163_u, field_70161_v, field_70125_A, field_70177_z);
        EntityLivingBase target = func_70638_az();
        
        spider.func_70624_b(target);
        spider.func_70604_c(target);
        spider.func_70784_b(target);
        field_70170_p.func_72838_d(spider);
        
        ParticleEffect.MOB_SPELL.send(SoundEffect.RANDOM_POP, spider, 2.0D, 2.0D, 16);
        attacker.func_70106_y();
      }
    }
    
    if ((!field_70170_p.field_72995_K) && (source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityPlayer))) {
      EntityPlayer player = (EntityPlayer)source.func_76346_g();
      if (!com.emoniph.witchery.util.CreatureUtil.isWoodenDamage(source)) {
        player.func_70097_a(DamageSource.func_76354_b(this, player), damage * 0.25F);
      }
    }
    return result;
  }
  
  public float getCapDT(DamageSource source, float damage)
  {
    return 15.0F;
  }
  

  public void func_70645_a(DamageSource par1DamageSource)
  {
    super.func_70645_a(par1DamageSource);
    if (!field_70170_p.field_72995_K) {
      ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, this, 1.0D, 2.0D, 16);
    }
    func_70106_y();
  }
  
  protected void func_70628_a(boolean par1, int par2)
  {
    int j = field_70146_Z.nextInt(3) + 2;
    
    for (int k = 0; k < j; k++) {
      int l = field_70146_Z.nextInt(3) + 1;
      int i1 = witchDrops[field_70146_Z.nextInt(witchDrops.length)];
      
      if (par2 > 0) {
        l += field_70146_Z.nextInt(par2 + 1);
      }
      
      for (int j1 = 0; j1 < l; j1++) {
        func_70099_a(new ItemStack(ItemsGENERIC, 1, i1), 0.0F);
      }
    }
    
    Enchantment enchantment = Enchantment.field_92090_c[field_70146_Z.nextInt(Enchantment.field_92090_c.length)];
    int k = MathHelper.func_76136_a(field_70146_Z, Math.min(enchantment.func_77319_d() + 2, enchantment.func_77325_b()), enchantment.func_77325_b());
    
    ItemStack itemstack = Items.field_151134_bR.func_92111_a(new EnchantmentData(enchantment, k));
    func_70099_a(itemstack, 0.0F);
    func_70099_a(new ItemStack(ItemsBABAS_HAT), 0.0F);
  }
  
  public void func_82196_d(EntityLivingBase par1EntityLivingBase, float par2)
  {
    if (!getAggressive()) {
      if (field_70170_p.field_73012_v.nextInt(3) == 0) {
        ItemGeneral.SubItem brew = null;
        switch (field_70170_p.field_73012_v.nextInt(12)) {
        case 0: 
        case 1: 
          brew = ItemsGENERIC.itemBrewOfWebs;
          break;
        case 2: 
        case 3: 
          brew = ItemsGENERIC.itemBrewOfThorns;
          break;
        case 4: 
        case 5: 
          brew = ItemsGENERIC.itemBrewOfFrogsTongue;
          break;
        case 6: 
        case 7: 
          brew = ItemsGENERIC.itemBrewOfInk;
          break;
        case 8: 
        case 9: 
          brew = ItemsGENERIC.itemBrewOfHitchcock;
          break;
        case 10: 
          brew = ItemsGENERIC.itemBrewOfBats;
          break;
        case 11: 
          brew = ItemsGENERIC.itemBrewOfWasting;
          break;
        default: 
          return;
        }
        EntityWitchProjectile entitypotion = new EntityWitchProjectile(field_70170_p, this, brew);
        field_70125_A -= -20.0F;
        double d0 = field_70165_t + field_70159_w - field_70165_t;
        double d1 = field_70163_u + par1EntityLivingBase.func_70047_e() - 1.100000023841858D - field_70163_u;
        double d2 = field_70161_v + field_70179_y - field_70161_v;
        float f1 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
        entitypotion.func_70186_c(d0, d1 + f1 * 0.2F, d2, 0.75F, 8.0F);
        field_70170_p.func_72838_d(entitypotion);
      } else {
        EntityPotion entitypotion = new EntityPotion(field_70170_p, this, 32732);
        field_70125_A -= -20.0F;
        double d0 = field_70165_t + field_70159_w - field_70165_t;
        double d1 = field_70163_u + par1EntityLivingBase.func_70047_e() - 1.100000023841858D - field_70163_u;
        double d2 = field_70161_v + field_70179_y - field_70161_v;
        float f1 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
        
        if ((f1 >= 8.0F) && (!par1EntityLivingBase.func_70644_a(Potion.field_76421_d))) {
          entitypotion.func_82340_a(32698);
        } else if ((par1EntityLivingBase.func_110143_aJ() >= 8.0F) && (!par1EntityLivingBase.func_70644_a(Potion.field_76436_u))) {
          entitypotion.func_82340_a(32660);
        } else if ((f1 <= 3.0F) && (!par1EntityLivingBase.func_70644_a(Potion.field_76437_t)) && (field_70146_Z.nextFloat() < 0.25F)) {
          entitypotion.func_82340_a(32696);
        }
        
        entitypotion.func_70186_c(d0, d1 + f1 * 0.2F, d2, 0.75F, 8.0F);
        field_70170_p.func_72838_d(entitypotion);
      }
    }
  }
}
