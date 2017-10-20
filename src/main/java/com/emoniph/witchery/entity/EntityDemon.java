package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
import com.emoniph.witchery.entity.ai.EntityAILookAtDemonicBarginPlayer;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.util.Config;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collections;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

public class EntityDemon extends EntityGolem implements IRangedAttackMob, net.minecraft.entity.IMerchant
{
  private int attackTimer;
  private EntityPlayer buyingPlayer;
  private MerchantRecipeList buyingList;
  
  public EntityDemon(World par1World)
  {
    super(par1World);
    
    func_70105_a(1.0F, 2.9F);
    field_70178_ae = true;
    func_70661_as().func_75491_a(true);
    
    field_70714_bg.func_75776_a(1, new com.emoniph.witchery.entity.ai.EntityAIAttackCloseTargetOnCollide(this, 1.0D, true, 3.0D));
    field_70714_bg.func_75776_a(2, new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F));
    field_70714_bg.func_75776_a(3, new com.emoniph.witchery.entity.ai.EntityAIDemonicBarginPlayer(this));
    field_70714_bg.func_75776_a(4, new EntityAILookAtDemonicBarginPlayer(this));
    field_70714_bg.func_75776_a(5, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
    field_70714_bg.func_75776_a(6, new net.minecraft.entity.ai.EntityAIMoveThroughVillage(this, 0.6D, true));
    field_70714_bg.func_75776_a(7, new EntityAIMoveTowardsRestriction(this, 1.0D));
    field_70714_bg.func_75776_a(8, new net.minecraft.entity.ai.EntityAIWander(this, 0.6D));
    field_70714_bg.func_75776_a(9, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
    field_70714_bg.func_75776_a(10, new net.minecraft.entity.ai.EntityAILookIdle(this));
    field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
    

    field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, true));
    field_70728_aV = 10;
  }
  
  protected void func_70088_a()
  {
    super.func_70088_a();
    field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
    field_70180_af.func_75682_a(17, Integer.valueOf(0));
  }
  
  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.demon.name");
  }
  

  public boolean func_70085_c(EntityPlayer par1EntityPlayer)
  {
    if (field_71093_bK != instancedimensionDreamID) {
      ItemStack itemstack = field_71071_by.func_70448_g();
      boolean flag = (itemstack != null) && ((itemstack.func_77973_b() == Items.field_151063_bx) || (itemstack.func_77973_b() == Items.field_151057_cb));
      
      if ((!flag) && (func_70089_S()) && (!isTrading()) && (!func_70631_g_())) {
        if (!field_70170_p.field_72995_K) {
          func_70932_a_(par1EntityPlayer);
          par1EntityPlayer.func_71030_a(this, func_70005_c_());
        }
        
        return true;
      }
      return super.func_70085_c(par1EntityPlayer);
    }
    
    return super.func_70085_c(par1EntityPlayer);
  }
  

  public boolean func_70650_aV()
  {
    return true;
  }
  
  protected void func_70629_bd()
  {
    super.func_70629_bd();
  }
  
  public boolean func_70097_a(DamageSource par1DamageSource, float par2)
  {
    return super.func_70097_a(par1DamageSource, Math.min(par2, 15.0F));
  }
  
  protected void func_110147_ax()
  {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
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
    
    if ((field_71093_bK == instancedimensionDreamID) && ((field_70170_p.field_73011_w instanceof WorldProviderDreamWorld)) && (!((WorldProviderDreamWorld)field_70170_p.field_73011_w).isDemonicNightmare()))
    {
      func_70106_y();
    }
    
    if (tryEscape == 0) {
      tryEscape = -1;
      field_70170_p.func_72876_a(this, field_70165_t, field_70163_u, field_70161_v, 3.0F, true);
    } else if (tryEscape > 0) {
      tryEscape -= 1;
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
    par1NBTTagCompound.func_74757_a("PlayerCreated", isPlayerCreated());
    
    if (buyingList != null)
    {
      par1NBTTagCompound.func_74782_a("Bargains", buyingList.func_77202_a());
    }
  }
  
  public void func_70037_a(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70037_a(par1NBTTagCompound);
    setPlayerCreated(par1NBTTagCompound.func_74767_n("PlayerCreated"));
    
    if (par1NBTTagCompound.func_74764_b("Bargains"))
    {
      NBTTagCompound nbttagcompound1 = par1NBTTagCompound.func_74775_l("Bargains");
      buyingList = new MerchantRecipeList(nbttagcompound1);
    }
  }
  
  public boolean func_70652_k(Entity par1Entity)
  {
    attackTimer = 10;
    field_70170_p.func_72960_a(this, (byte)4);
    boolean flag = par1Entity.func_70097_a(DamageSource.func_76358_a(this), 7 + field_70146_Z.nextInt(15));
    
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
    return "mob.blaze.breathe";
  }
  
  protected String func_70621_aR()
  {
    return "mob.wither.hurt";
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
    if (par1) {
      int j = field_70146_Z.nextInt(2 + par2);
      
      for (int k = 0; k < j; k++) {
        func_145779_a(Items.field_151064_bs, 1);
      }
    }
  }
  
  protected Item func_146068_u()
  {
    return Items.field_151064_bs;
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
  
  public void func_82196_d(EntityLivingBase targetEntity, float par2)
  {
    if ((targetEntity.func_70694_bm() == null) || (targetEntity.func_70694_bm().func_77973_b() != ItemsDEVILS_TONGUE_CHARM) || (field_70170_p.field_73012_v.nextDouble() < 0.05D)) {
      double d0 = field_70165_t - field_70165_t;
      double d1 = field_70121_D.field_72338_b + field_70131_O / 2.0F - (field_70163_u + field_70131_O / 2.0F);
      double d2 = field_70161_v - field_70161_v;
      
      float f1 = MathHelper.func_76129_c(par2) * 0.5F;
      
      EntityLargeFireball fireballEntity = new EntityLargeFireball(field_70170_p, this, d0 + field_70146_Z.nextGaussian() * f1, d1, d2 + field_70146_Z.nextGaussian() * f1);
      
      double d8 = 1.0D;
      Vec3 vec3 = func_70676_i(1.0F);
      field_70165_t = (field_70165_t + field_72450_a * d8);
      field_70163_u = (field_70163_u + field_70131_O / 2.0F + 0.5D);
      field_70161_v = (field_70161_v + field_72449_c * d8);
      
      if (!field_70170_p.field_72995_K) {
        field_70170_p.func_72889_a((EntityPlayer)null, 1009, (int)field_70165_t, (int)field_70163_u, (int)field_70161_v, 0);
        field_70170_p.func_72838_d(fireballEntity);
      }
    }
  }
  
  public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData)
  {
    func_70690_d(new PotionEffect(field_76430_jfield_76415_H, Integer.MAX_VALUE, 4));
    return super.func_110161_a(par1EntityLivingData);
  }
  








  public void func_70932_a_(EntityPlayer par1EntityPlayer)
  {
    buyingPlayer = par1EntityPlayer;
  }
  
  public EntityPlayer func_70931_l_()
  {
    return buyingPlayer;
  }
  
  public boolean isTrading() {
    return buyingPlayer != null;
  }
  
  public void func_70933_a(MerchantRecipe par1MerchantRecipe)
  {
    par1MerchantRecipe.func_77399_f();
    
    Item itemToBuy = par1MerchantRecipe.func_77394_a().func_77973_b();
    if ((!field_70170_p.field_72995_K) && ((itemToBuy == Items.field_151064_bs) || (itemToBuy == Items.field_151072_bj))) {
      func_85030_a("mob.wither.shoot", func_70599_aP(), func_70647_i());
      tryEscape = 50;
    } else {
      func_85030_a("random.breath", func_70599_aP(), func_70647_i());
    }
    
    if ((func_70931_l_() != null) && (func_70931_l_().func_70694_bm() != null) && (func_70931_l_().func_70694_bm().func_77973_b() == ItemsDEVILS_TONGUE_CHARM)) {
      func_70931_l_().func_70694_bm().func_77972_a(5, func_70931_l_());
      if (func_70931_l_func_70694_bmfield_77994_a <= 0) {
        func_70931_l_().func_71028_bD();
      }
    }
  }
  
  private int tryEscape = -1;
  
  public void func_110297_a_(ItemStack par1ItemStack)
  {
    if ((!field_70170_p.field_72995_K) && (field_70757_a > -func_70627_aG() + 20)) {
      field_70757_a = (-func_70627_aG());
      
      if (par1ItemStack != null) {
        func_85030_a("random.breath", func_70599_aP(), func_70647_i());
      } else {
        func_85030_a("mob.wither.idle", func_70599_aP(), func_70647_i());
      }
    }
  }
  
  protected void func_70785_a(Entity entity, float par2)
  {
    if ((field_70724_aR <= 0) && (par2 < 2.0F) && (field_70121_D.field_72337_e > field_70121_D.field_72338_b) && (field_70121_D.field_72338_b < field_70121_D.field_72337_e))
    {
      field_70724_aR = 20;
      func_70652_k(entity);
    }
    
    super.func_70785_a(entity, par2);
  }
  
  public MerchantRecipeList func_70934_b(EntityPlayer par1EntityPlayer)
  {
    if (buyingList == null) {
      addDefaultEquipmentAndRecipies(field_70146_Z.nextInt(4) + 6);
    }
    
    if ((func_70931_l_() != null) && (func_70931_l_().func_70694_bm() != null) && (func_70931_l_().func_70694_bm().func_77973_b() == ItemsDEVILS_TONGUE_CHARM)) {
      MerchantRecipeList list = new MerchantRecipeList();
      for (Object recipeObj : buyingList) {
        MerchantRecipe recipe = (MerchantRecipe)recipeObj;
        NBTTagCompound nbtTag = recipe.func_77395_g();
        MerchantRecipe recipe2 = new MerchantRecipe(nbtTag);
        ItemStack cost = recipe2.func_77394_a();
        field_77994_a = Math.max(field_77994_a - (cost.func_77973_b() == Items.field_151045_i ? 0 : cost.func_77973_b() == Items.field_151166_bC ? 2 : cost.func_77973_b() == Items.field_151043_k ? 5 : 1), 1);
        list.add(recipe2);
      }
      return list;
    }
    return buyingList;
  }
  
  private Item getCurrency()
  {
    double chance = field_70146_Z.nextDouble();
    
    if (chance < 0.2D)
      return Items.field_151072_bj;
    if (chance < 0.4D)
      return Items.field_151064_bs;
    if (chance < 0.5D)
      return Items.field_151045_i;
    if (chance < 0.75D) {
      return Items.field_151166_bC;
    }
    return Items.field_151043_k;
  }
  
  private ItemStack getPrice(int basePriceInEmeralds)
  {
    Item currency = getCurrency();
    int multiplier = currency == Items.field_151045_i ? 5 : currency == Items.field_151166_bC ? 3 : currency == Items.field_151043_k ? 1 : 4;
    int quantity = Math.max(1, basePriceInEmeralds / multiplier);
    
    return new ItemStack(currency, quantity);
  }
  
  private void addDefaultEquipmentAndRecipies(int par1)
  {
    MerchantRecipeList merchantrecipelist = new MerchantRecipeList();
    
    int STOCK_REDUCTION = -5;
    
    for (int i = 0; i < par1; i++) {
      Enchantment enchantment = Enchantment.field_92090_c[field_70146_Z.nextInt(Enchantment.field_92090_c.length)];
      int k = MathHelper.func_76136_a(field_70146_Z, enchantment.func_77319_d(), enchantment.func_77325_b());
      ItemStack itemstack = Items.field_151134_bR.func_92111_a(new EnchantmentData(enchantment, k));
      int j = 2 + field_70146_Z.nextInt(5 + k * 10) + 3 * k;
      MerchantRecipe recipe = new MerchantRecipe(getPrice(j), itemstack);
      recipe.func_82783_a(-5);
      merchantrecipelist.add(recipe);
    }
    
    if (field_70146_Z.nextDouble() < 0.25D) {
      MerchantRecipe recipe = new MerchantRecipe(getPrice(field_70146_Z.nextInt(3) + 8), ItemsGENERIC.itemSpectralDust.createStack(field_70146_Z.nextInt(4) + 3));
      recipe.func_82783_a(-5);
      merchantrecipelist.add(recipe);
    }
    
    if (field_70146_Z.nextDouble() < 0.25D) {
      MerchantRecipe recipe = new MerchantRecipe(getPrice(field_70146_Z.nextInt(3) + 8), ItemsGENERIC.itemDogTongue.createStack(field_70146_Z.nextInt(4) + 4));
      recipe.func_82783_a(-5);
      merchantrecipelist.add(recipe);
    }
    
    if (field_70146_Z.nextDouble() < 0.15D) {
      MerchantRecipe recipe = new MerchantRecipe(getPrice(field_70146_Z.nextInt(10) + 20), ItemsGENERIC.itemRedstoneSoup.createStack(1));
      recipe.func_82783_a(-5);
      merchantrecipelist.add(recipe);
    }
    
    if (field_70146_Z.nextDouble() < 0.15D) {
      MerchantRecipe recipe = new MerchantRecipe(new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151073_bk, 2));
      recipe.func_82783_a(-5);
      merchantrecipelist.add(recipe);
    }
    
    if (field_70146_Z.nextDouble() < 0.15D) {
      MerchantRecipe recipe = new MerchantRecipe(new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151079_bi, 2));
      recipe.func_82783_a(-5);
      merchantrecipelist.add(recipe);
    }
    

    Collections.shuffle(merchantrecipelist);
    
    Item currencyForHeart = getCurrency();
    MerchantRecipe heartRecipe = new MerchantRecipe(new ItemStack(currencyForHeart, currencyForHeart == Items.field_151043_k ? 30 : 3), ItemsGENERIC.itemDemonHeart.createStack(1));
    heartRecipe.func_82783_a(-5);
    merchantrecipelist.add(field_70146_Z.nextInt(3), heartRecipe);
    
    if (buyingList == null) {
      buyingList = new MerchantRecipeList();
    }
    
    for (int j1 = 0; (j1 < par1) && (j1 < merchantrecipelist.size()); j1++) {
      buyingList.add(merchantrecipelist.get(j1));
    }
  }
  
  @SideOnly(Side.CLIENT)
  public void func_70930_a(MerchantRecipeList par1MerchantRecipeList) {}
}
