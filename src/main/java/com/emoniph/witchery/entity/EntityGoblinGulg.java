package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.entity.ai.EntityAIMoveTowardsEntityClass;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.util.IHandleDT;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityGoblinGulg extends EntityMob implements net.minecraft.entity.boss.IBossDisplayData, IHandleDT
{
  private int attackTimer;
  
  public EntityGoblinGulg(World world)
  {
    super(world);
    
    func_70105_a(0.8F, 1.8F);
    field_70178_ae = true;
    func_70661_as().func_75491_a(true);
    func_70661_as().func_75495_e(true);
    
    field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
    field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(this, 1.0D, true));
    field_70714_bg.func_75776_a(3, new EntityAIMoveTowardsEntityClass(this, EntityGoblinMog.class, 1.0D, 6.0F, 64.0F));
    field_70714_bg.func_75776_a(4, new EntityAIMoveTowardsTarget(this, 1.0D, 48.0F));
    field_70714_bg.func_75776_a(5, new net.minecraft.entity.ai.EntityAIWander(this, 1.0D));
    field_70714_bg.func_75776_a(6, new net.minecraft.entity.ai.EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
    field_70714_bg.func_75776_a(7, new net.minecraft.entity.ai.EntityAILookIdle(this));
    
    field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
    field_70715_bh.func_75776_a(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    field_70728_aV = 35;
  }
  
  protected void func_70088_a()
  {
    super.func_70088_a();
    field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
    field_70180_af.func_75682_a(17, Integer.valueOf(0));
    field_70180_af.func_75682_a(20, new Integer(0));
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(400.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35D);
    func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(50.0D);
    func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D);
  }
  
  public int func_70658_aO() {
    return 8;
  }
  

  public void func_70110_aj() {}
  
  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.goblingulg.name");
  }
  

  public boolean func_70650_aV()
  {
    return true;
  }
  
  public void func_70604_c(EntityLivingBase entity)
  {
    if ((!(entity instanceof EntityGoblinMog)) && (!(entity instanceof EntityGoblin)) && (!(entity instanceof EntityGoblinGulg))) {
      super.func_70604_c(entity);
    }
  }
  
  protected void func_70629_bd()
  {
    super.func_70629_bd();
  }
  
  public int func_82212_n() {
    return field_70180_af.func_75679_c(20);
  }
  
  public void func_82215_s(int par1) {
    field_70180_af.func_75692_b(20, Integer.valueOf(par1));
  }
  
  public void func_82206_m() {
    func_82215_s(150);
    func_70606_j(func_110138_aP() / 4.0F);
  }
  


  protected void func_70619_bc()
  {
    if (func_82212_n() > 0) {
      int i = func_82212_n() - 1;
      
      if (i <= 0) {
        field_70170_p.func_82739_e(1013, (int)field_70165_t, (int)field_70163_u, (int)field_70161_v, 0);
      }
      
      func_82215_s(i);
      
      if (field_70173_aa % 10 == 0) {
        func_70691_i(20.0F);
      }
    } else {
      super.func_70619_bc();
      
      if (field_70173_aa % 20 == 0) {
        func_70691_i(2.0F);
      }
      
      if ((!field_70170_p.field_72995_K) && (func_70661_as().func_75500_f()) && (func_70638_az() != null) && (field_70173_aa - ticksSinceTeleport > 200L)) {
        ticksSinceTeleport = field_70173_aa;
        teleportToEntity(func_70638_az());
      }
    }
  }
  
  long ticksSinceTeleport = 0L;
  private static final double INVULNRABLE = 9.0D;
  
  protected boolean teleportToEntity(Entity par1Entity) { Vec3 vec3 = Vec3.func_72443_a(field_70165_t - field_70165_t, field_70121_D.field_72338_b + field_70131_O / 2.0F - field_70163_u + par1Entity.func_70047_e(), field_70161_v - field_70161_v);
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
    
    if (field_70170_p.func_72899_e(i, j, k))
    {
      boolean flag1 = false;
      
      while ((!flag1) && (j > 0))
      {
        Block block = field_70170_p.func_147439_a(i, j - 1, k);
        
        if (block.func_149688_o().func_76230_c())
        {
          flag1 = true;
        }
        else
        {
          field_70163_u -= 1.0D;
          j--;
        }
      }
      
      if (flag1)
      {
        func_70107_b(field_70165_t, field_70163_u, field_70161_v);
        
        if ((field_70170_p.func_72945_a(this, field_70121_D).isEmpty()) && (!field_70170_p.func_72953_d(field_70121_D)))
        {
          flag = true;
        }
      }
    }
    
    if (!flag)
    {
      func_70107_b(d3, d4, d5);
      return false;
    }
    

    short short1 = 128;
    
    for (int l = 0; l < short1; l++)
    {
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
  

  protected int func_70682_h(int par1)
  {
    return par1;
  }
  

  protected void func_82167_n(Entity par1Entity)
  {
    super.func_82167_n(par1Entity);
  }
  
  public void func_70636_d()
  {
    super.func_70636_d();
    
    if (attackTimer > 0) {
      attackTimer -= 1;
    }
  }
  

  private static final double PERCENT_20 = 36.0D;
  
  private static final double PERCENT_50 = 81.0D;
  
  private static final double PERCENT_80 = 256.0D;
  public boolean func_70097_a(DamageSource source, float damage)
  {
    double distance = getDistanceSqToPartner();
    double scale = 1.0D;
    if (distance <= 9.0D)
      return false;
    if (distance <= 36.0D) {
      scale = 0.2D;
    } else if (distance <= 81.0D) {
      scale = 0.5D;
    } else if (distance <= 256.0D) {
      scale = 0.8D;
    }
    return super.func_70097_a(source, (float)Math.min(damage * scale, 15.0D));
  }
  
  public float getCapDT(DamageSource source, float damage)
  {
    return 15.0F;
  }
  
  public boolean func_70652_k(Entity par1Entity)
  {
    attackTimer = 10;
    
    double distance = getDistanceSqToPartner();
    
    double height = 0.0D;
    int force = 4;
    if (distance <= 9.0D) {
      height = 1.0D;
      force = 20;
    } else if (distance <= 36.0D) {
      height = 0.8D;
      force = 15;
    } else if (distance <= 81.0D) {
      height = 0.5D;
      force = 10;
    } else if (distance <= 256.0D) {
      height = 0.2D;
      force = 6;
    }
    
    field_70170_p.func_72960_a(this, (byte)4);
    boolean flag = par1Entity.func_70097_a(DamageSource.func_76358_a(this), 6 + field_70146_Z.nextInt(force));
    
    if (flag) {
      field_70181_x += 0.5D + height;
    }
    
    func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
    return flag;
  }
  
  private double getDistanceSqToPartner() {
    double R = 16.0D;
    AxisAlignedBB bb = AxisAlignedBB.func_72330_a(field_70165_t - 16.0D, field_70163_u - 16.0D, field_70161_v - 16.0D, field_70165_t + 16.0D, field_70163_u + 16.0D, field_70161_v + 16.0D);
    List mogs = field_70170_p.func_72872_a(EntityGoblinMog.class, bb);
    double minDistance = Double.MAX_VALUE;
    for (Object obj : mogs) {
      EntityGoblinMog mog = (EntityGoblinMog)obj;
      double distance = func_70068_e(mog);
      if (distance < minDistance) {
        minDistance = distance;
      }
    }
    return minDistance;
  }
  
  public boolean func_70686_a(Class par1Class)
  {
    return super.func_70686_a(par1Class);
  }
  
  public void func_70014_b(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70014_b(par1NBTTagCompound);
    par1NBTTagCompound.func_74768_a("Invul", func_82212_n());
  }
  

  public void func_70037_a(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70037_a(par1NBTTagCompound);
    func_82215_s(par1NBTTagCompound.func_74762_e("Invul"));
  }
  

  @SideOnly(Side.CLIENT)
  public void func_70103_a(byte par1)
  {
    if (par1 == 4) {
      attackTimer = 10;
      func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
    } else {
      super.func_70103_a(par1);
    }
  }
  
  @SideOnly(Side.CLIENT)
  public int getAttackTimer() {
    return attackTimer;
  }
  
  public float func_70013_c(float par1)
  {
    return 1.0F;
  }
  
  protected String func_70639_aQ()
  {
    return "witchery:mob.goblin.gulg_idle";
  }
  
  protected String func_70621_aR()
  {
    return "mob.horse.zombie.hit";
  }
  
  protected String func_70673_aS()
  {
    return "mob.wither.death";
  }
  
  protected void func_145780_a(int par1, int par2, int par3, Block par4)
  {
    func_85030_a("mob.irongolem.walk", 1.0F, 1.0F);
  }
  
  protected void func_70628_a(boolean par1, int par2)
  {
    func_70099_a(ItemsGENERIC.itemKobolditeNugget.createStack(field_70146_Z.nextInt(3) + 1), 0.0F);
    ItemStack armor = null;
    switch (field_70146_Z.nextInt(4)) {
    case 0: 
      armor = new ItemStack(Items.field_151029_X);
      break;
    case 1: 
      armor = new ItemStack(Items.field_151022_W);
      break;
    case 2: 
      armor = new ItemStack(Items.field_151023_V);
      break;
    case 3: 
      armor = new ItemStack(Items.field_151020_U);
    }
    
    if (armor != null) {
      EnchantmentHelper.func_77504_a(field_70170_p.field_73012_v, armor, 30);
      func_70099_a(armor, 0.0F);
    }
    if (field_70170_p.field_73012_v.nextInt(2) == 0) {
      func_70099_a(new ItemStack(ItemsGULGS_GURDLE), 0.0F);
    }
  }
  
  protected Item func_146068_u()
  {
    return null;
  }
  
  public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData)
  {
    func_110163_bv();
    
    return super.func_110161_a(par1EntityLivingData);
  }
  
  protected boolean func_70692_ba()
  {
    return false;
  }
}
