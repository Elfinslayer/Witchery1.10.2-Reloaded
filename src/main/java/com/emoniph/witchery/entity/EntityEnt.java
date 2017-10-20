package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.util.Dye;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityEnt extends EntityMob implements IEntitySelector
{
  private int attackTimer;
  
  public EntityEnt(World par1World)
  {
    super(par1World);
    
    func_70105_a(1.2F, 3.0F);
    
    func_70661_as().func_75491_a(true);
    func_70661_as().func_75495_e(true);
    field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
    field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(this, 1.0D, true));
    field_70714_bg.func_75776_a(3, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
    field_70714_bg.func_75776_a(4, new EntityAIWander(this, 0.6D));
    field_70714_bg.func_75776_a(5, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
    field_70714_bg.func_75776_a(6, new net.minecraft.entity.ai.EntityAILookIdle(this));
    field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
    field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false, true, this));
    field_70728_aV = 25;
  }
  










  public boolean func_82704_a(Entity entity)
  {
    return true;
  }
  
  protected void func_70088_a()
  {
    super.func_70088_a();
    field_70180_af.func_75682_a(17, "");
    field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
  }
  
  public boolean isScreaming()
  {
    return field_70180_af.func_75683_a(16) > 0;
  }
  
  public void setScreaming(boolean par1)
  {
    field_70180_af.func_75692_b(16, Byte.valueOf((byte)(par1 ? 1 : 0)));
  }
  
  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.ent.name");
  }
  

  public boolean func_70650_aV()
  {
    return true;
  }
  
  protected void func_70629_bd()
  {
    super.func_70629_bd();
    
    if ((!field_70170_p.field_72995_K) && (func_70089_S())) {
      if (func_70638_az() != null) {
        setScreaming(true);
      } else {
        setScreaming(false);
      }
    }
  }
  
  protected void func_110147_ax()
  {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(200.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
    func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D);
  }
  
  protected int func_70682_h(int par1)
  {
    return par1;
  }
  
  protected void func_82167_n(Entity par1Entity)
  {
    super.func_82167_n(par1Entity);
  }
  
  EntityPlayer fakePlayer = null;
  
  public void func_70636_d()
  {
    super.func_70636_d();
    
    if (attackTimer > 0) {
      attackTimer -= 1;
    }
    
    if ((!field_70170_p.field_72995_K) && (field_70170_p.field_73012_v.nextInt(300) == 0)) {
      try {
        int i = MathHelper.func_76128_c(field_70165_t);
        int j = MathHelper.func_76128_c(field_70163_u) - 1;
        int k = MathHelper.func_76128_c(field_70161_v);
        
        if (((fakePlayer == null) || (fakePlayer.field_70170_p != field_70170_p)) && 
          ((field_70170_p instanceof WorldServer))) {
          fakePlayer = new net.minecraftforge.common.util.FakePlayer((WorldServer)field_70170_p, new GameProfile(UUID.randomUUID(), "[Minecraft]"));
        }
        


        if (fakePlayer != null) {
          net.minecraft.item.ItemDye.applyBonemeal(Dye.BONE_MEAL.createStack(), field_70170_p, i, j, k, fakePlayer);
        }
      }
      catch (Throwable e) {}
    }
    

    if ((field_70159_w * field_70159_w + field_70179_y * field_70179_y > 2.500000277905201E-7D) && (field_70146_Z.nextInt(5) == 0)) {
      int i = MathHelper.func_76128_c(field_70165_t);
      int j = MathHelper.func_76128_c(field_70163_u - 0.20000000298023224D - field_70129_M);
      int k = MathHelper.func_76128_c(field_70161_v);
      Block l = field_70170_p.func_147439_a(i, j, k);
      
      if (l != net.minecraft.init.Blocks.field_150350_a) {
        field_70170_p.func_72869_a("tilecrack_" + l + "_" + field_70170_p.func_72805_g(i, j, k), field_70165_t + (field_70146_Z.nextFloat() - 0.5D) * field_70130_N, field_70121_D.field_72338_b + 0.1D, field_70161_v + (field_70146_Z.nextFloat() - 0.5D) * field_70130_N, 4.0D * (field_70146_Z.nextFloat() - 0.5D), 0.5D, (field_70146_Z.nextFloat() - 0.5D) * 4.0D);
      }
    }
  }
  




  public boolean func_70686_a(Class par1Class)
  {
    return super.func_70686_a(par1Class);
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
    func_110163_bv();
    field_70180_af.func_75692_b(17, par1Str);
  }
  
  public EntityPlayer getOwnerEntity() {
    return field_70170_p.func_72924_a(getOwnerName());
  }
  
  public boolean func_70652_k(Entity par1Entity)
  {
    attackTimer = 10;
    field_70170_p.func_72960_a(this, (byte)4);
    
    boolean flag = super.func_70652_k(par1Entity);
    

    if (flag) {
      field_70181_x += 0.4000000059604645D;
    }
    
    func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
    return flag;
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
  
  public boolean func_70097_a(DamageSource par1DamageSource, float par2)
  {
    Entity entity = par1DamageSource.func_76346_g();
    par2 = Math.min(par2, 15.0F);
    if ((entity != null) && ((entity instanceof EntityLivingBase)) && ((field_76373_n == "mob") || (field_76373_n == "player")) && (((EntityLivingBase)entity).func_70694_bm() != null) && ((((EntityLivingBase)entity).func_70694_bm().func_77973_b() instanceof net.minecraft.item.ItemAxe)))
    {


      par2 *= 3.0F;
    }
    return super.func_70097_a(par1DamageSource, par2);
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
    return null;
  }
  
  protected String func_70621_aR()
  {
    return "mob.horse.zombie.hit";
  }
  
  protected String func_70673_aS()
  {
    return "mob.horse.zombie.death";
  }
  
  protected void func_145780_a(int par1, int par2, int par3, Block par4)
  {
    func_85030_a("mob.irongolem.walk", 1.0F, 1.0F);
  }
  
  protected void func_70628_a(boolean par1, int par2)
  {
    func_70099_a(ItemsGENERIC.itemBranchEnt.createStack(), 0.0F);
    func_70099_a(new ItemStack(BlocksSAPLING, 1, field_70170_p.field_73012_v.nextInt(3)), 0.0F);
  }
  
  public boolean isPlayerCreated() {
    return (field_70180_af.func_75683_a(16) & 0x1) != 0;
  }
  
  public void setPlayerCreated(boolean par1) {
    func_110163_bv();
    byte b0 = field_70180_af.func_75683_a(16);
    
    if (par1) {
      field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 | 0x1)));
    } else {
      field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 & 0xFFFFFFFE)));
    }
  }
  
  public void func_70645_a(DamageSource par1DamageSource)
  {
    super.func_70645_a(par1DamageSource);
  }
  
  protected boolean func_70692_ba()
  {
    return true;
  }
  

  public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData)
  {
    return super.func_110161_a(par1EntityLivingData);
  }
}
