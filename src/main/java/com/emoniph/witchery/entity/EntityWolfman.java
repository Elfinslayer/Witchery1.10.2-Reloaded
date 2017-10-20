package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.brewing.potions.WitcheryPotions;
import com.emoniph.witchery.common.Shapeshift;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.util.CreatureUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

public class EntityWolfman extends EntityMob implements net.minecraft.command.IEntitySelector
{
  private int formerProfession = -1;
  private int attackTimer;
  private boolean infectious;
  
  public EntityWolfman(World world) {
    super(world);
    func_70661_as().func_75498_b(true);
    func_70661_as().func_75491_a(true);
    field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
    field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAIBreakDoor(this));
    field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
    field_70714_bg.func_75776_a(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
    field_70714_bg.func_75776_a(4, new EntityAIAttackOnCollide(this, EntityWitchHunter.class, 1.0D, true));
    field_70714_bg.func_75776_a(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
    field_70714_bg.func_75776_a(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
    field_70714_bg.func_75776_a(7, new EntityAIWander(this, 1.0D));
    field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
    field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, true));
    field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, this));
    
    field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
    func_70105_a(0.6F, 1.8F);
    field_70728_aV = 20;
  }
  
  public boolean func_82704_a(Entity target)
  {
    if ((target instanceof EntityPlayer)) {
      EntityPlayer player = (EntityPlayer)target;
      return !Shapeshift.INSTANCE.isAnimalForm(player);
    }
    return false;
  }
  
  protected void func_110147_ax()
  {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(80.0D);
  }
  
  protected void func_70088_a()
  {
    super.func_70088_a();
  }
  
  boolean isSitting;
  private MerchantRecipeList buyingList;
  public boolean isSitting()
  {
    return isSitting;
  }
  
  public void setSitting(boolean p_70904_1_)
  {
    isSitting = p_70904_1_;
  }
  

  private int wealth;
  
  public ItemStack itemInUse;
  public int itemInUseCount;
  @SideOnly(Side.CLIENT)
  private ResourceLocation skinOverride;
  public int func_70658_aO()
  {
    int i = super.func_70658_aO() + 10;
    
    if (i > 20) {
      i = 20;
    }
    
    return i;
  }
  
  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.wolfman.name");
  }
  

  protected boolean func_70650_aV()
  {
    return true;
  }
  
  public void func_70636_d()
  {
    if (attackTimer > 0) {
      attackTimer -= 1;
    }
    
    if (!field_70170_p.field_72995_K) {
      if ((formerProfession != -1) && (field_70173_aa % 100 == 3) && (!CreatureUtil.isFullMoon(field_70170_p)) && (!func_70644_a(PotionsWOLFSBANE)))
      {
        convertToVillager(this, formerProfession, infectious, wealth, buyingList);
      } else if ((field_70173_aa % 40 == 4) && 
        (func_70644_a(Potion.field_76436_u))) {
        func_82170_o(field_76436_ufield_76415_H);
      }
    }
    

    super.func_70636_d();
  }
  
  public boolean func_70097_a(DamageSource source, float damage)
  {
    if (CreatureUtil.isSilverDamage(source)) {
      return super.func_70097_a(source, Math.min(damage * 1.5F, 15.0F));
    }
    return super.func_70097_a(source, Math.min(damage, 1.0F));
  }
  

  public boolean func_70652_k(Entity targetEntity)
  {
    attackTimer = 10;
    field_70170_p.func_72960_a(this, (byte)4);
    
    boolean flag = super.func_70652_k(targetEntity);
    
    if (flag) {}
    


    return flag;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_70103_a(byte par1)
  {
    if (par1 == 4) {
      attackTimer = 10;
    } else {
      super.func_70103_a(par1);
    }
  }
  
  @SideOnly(Side.CLIENT)
  public int getAttackTimer() {
    return attackTimer;
  }
  
  public int func_70627_aG()
  {
    return super.func_70627_aG() * 4;
  }
  
  protected String func_70639_aQ()
  {
    return field_70170_p.field_73012_v.nextInt(20) == 0 ? "witchery:mob.wolfman.howl" : "witchery:mob.wolfman.say";
  }
  
  protected String func_70621_aR()
  {
    return "witchery:mob.wolfman.hit";
  }
  
  protected String func_70673_aS()
  {
    return "witchery:mob.wolfman.death";
  }
  
  protected void func_70628_a(boolean p_70628_1_, int fortune) {
    super.func_70628_a(p_70628_1_, fortune);
  }
  
  protected Item func_146068_u()
  {
    return Items.field_151103_aS;
  }
  
  protected void func_70600_l(int p_70600_1_)
  {
    switch (field_70146_Z.nextInt(3)) {
    case 0: 
      func_70099_a(ItemsGENERIC.itemSilverDust.createStack(field_70170_p.field_73012_v.nextInt(3) + 1), 0.0F);
      break;
    case 1: 
      func_145779_a(Items.field_151103_aS, 1);
      break;
    case 2: 
      func_145779_a(Items.field_151116_aA, 1);
    }
    
  }
  


  public void setFormerProfession(int profession, int wealth, MerchantRecipeList buyingList)
  {
    formerProfession = profession;
    this.buyingList = buyingList;
    this.wealth = wealth;
  }
  
  public int getFormerProfession() {
    return formerProfession;
  }
  
  public int getWealth() {
    return wealth;
  }
  
  public MerchantRecipeList getBuyingList() {
    return buyingList;
  }
  
  public void func_70014_b(NBTTagCompound nbtRoot)
  {
    super.func_70014_b(nbtRoot);
    nbtRoot.func_74768_a("FormerProfession", formerProfession);
    nbtRoot.func_74757_a("Infectious", infectious);
    
    nbtRoot.func_74768_a("Riches", wealth);
    
    if (buyingList != null) {
      nbtRoot.func_74782_a("Offers", buyingList.func_77202_a());
    }
  }
  
  public void func_70037_a(NBTTagCompound nbtRoot)
  {
    super.func_70037_a(nbtRoot);
    formerProfession = nbtRoot.func_74762_e("FormerProfession");
    infectious = nbtRoot.func_74767_n("Infectious");
    
    wealth = nbtRoot.func_74762_e("Riches");
    
    if (nbtRoot.func_150297_b("Offers", 10)) {
      NBTTagCompound nbttagcompound1 = nbtRoot.func_74775_l("Offers");
      buyingList = new MerchantRecipeList(nbttagcompound1);
    }
  }
  
  public void setInfectious() {
    infectious = true;
  }
  
  public boolean isInfectious() {
    return infectious;
  }
  
  public void func_70074_a(EntityLivingBase targetEntity)
  {
    super.func_70074_a(targetEntity);
  }
  
  protected boolean func_70692_ba()
  {
    return false;
  }
  
  public static void convertToVillager(EntityLiving target, int profession, boolean infectious, int wealth, MerchantRecipeList buyingList) {
    if ((target != null) && (!field_70170_p.field_72995_K)) {
      EntityVillager entity = new EntityVillagerWere(field_70170_p, profession, infectious);
      entity.func_82149_j(target);
      entity.func_82187_q();
      entity.func_110163_bv();
      field_70170_p.func_72900_e(target);
      field_70170_p.func_72838_d(entity);
      field_70170_p.func_72889_a(null, 1017, (int)field_70165_t, (int)field_70163_u, (int)field_70161_v, 0);
    }
  }
  
  public static void convertToCuredVillager(EntityLiving target, int profession, int wealth, MerchantRecipeList buyingList)
  {
    if ((target != null) && (!field_70170_p.field_72995_K)) {
      EntityVillager entity = new EntityVillager(field_70170_p, profession);
      entity.func_82149_j(target);
      entity.func_82187_q();
      entity.func_110163_bv();
      field_70170_p.func_72900_e(target);
      field_70170_p.func_72838_d(entity);
      field_70170_p.func_72889_a(null, 1017, (int)field_70165_t, (int)field_70163_u, (int)field_70161_v, 0);
    }
  }
  



  public void setItemInUse(ItemStack stack, int itemInUseCount)
  {
    itemInUse = stack;
    this.itemInUseCount = itemInUseCount;
  }
  



  @SideOnly(Side.CLIENT)
  public void setSkinResource(ResourceLocation skinOverride)
  {
    this.skinOverride = skinOverride;
  }
  
  @SideOnly(Side.CLIENT)
  public ResourceLocation getSkinResource() {
    return skinOverride;
  }
}
