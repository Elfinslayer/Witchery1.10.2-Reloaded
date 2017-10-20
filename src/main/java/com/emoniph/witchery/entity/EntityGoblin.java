package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.entity.ai.EntityAIAvoidEntityConditionally;
import com.emoniph.witchery.entity.ai.EntityAIAvoidEntityConditionally.IAvoidEntities;
import com.emoniph.witchery.entity.ai.EntityAIDigBlocks;
import com.emoniph.witchery.entity.ai.EntityAIMoveIndoorsLeashAware;
import com.emoniph.witchery.entity.ai.EntityAIWorship;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.TimeUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.command.IEntitySelector;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Tuple;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.village.Village;
import net.minecraft.village.VillageCollection;
import net.minecraft.world.World;

public class EntityGoblin extends EntityAgeable implements IMerchant, INpc, IEntitySelector, EntityAIAvoidEntityConditionally.IAvoidEntities
{
  private int randomTickDivider;
  private boolean isMating;
  Village villageObj;
  private EntityPlayer buyingPlayer;
  private MerchantRecipeList buyingList;
  private int timeUntilReset;
  private boolean needsInitilization;
  private int wealth;
  private String lastBuyingPlayer;
  private boolean isLookingForHome;
  private float field_82191_bN;
  public static final Map villagersSellingList = new HashMap();
  public static final Map blacksmithSellingList = new HashMap();
  private EntityAIWorship aiWorship;
  private boolean preventDespawn;
  private static final double KOBOLDITE_HARVEST_CHANCE = 0.02D;
  private boolean testingLeashRange;
  
  public EntityGoblin(World par1World) {
    this(par1World, 0);
  }
  

  public EntityGoblin(World par1World, int par2)
  {
    super(par1World);
    setProfession(field_70146_Z.nextInt(4));
    func_70105_a(0.6F, 0.95F);
    func_70661_as().func_75498_b(true);
    func_70661_as().func_75491_a(true);
    field_70714_bg.func_75776_a(0, new net.minecraft.entity.ai.EntityAISwimming(this));
    field_70714_bg.func_75776_a(1, this.aiWorship = new EntityAIWorship(this, TimeUtil.secsToTicks(30) + field_70146_Z.nextInt(10)));
    
    field_70714_bg.func_75776_a(2, new com.emoniph.witchery.entity.ai.EntityAIPickUpBlocks(this, 24.0D));
    field_70714_bg.func_75776_a(2, new com.emoniph.witchery.entity.ai.EntityAIDropOffBlocks(this, 24.0D));
    field_70714_bg.func_75776_a(2, new EntityAIDigBlocks(this, 16.0D, 0.02D));
    field_70714_bg.func_75776_a(3, new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, 0.6D, 0.6D));
    field_70714_bg.func_75776_a(3, new EntityAIAvoidEntityConditionally(this, EntityVillageGuard.class, 12.0F, 0.8D, 0.8D, this));
    
    field_70714_bg.func_75776_a(4, new EntityAIAttackOnCollide(this, 1.0D, true));
    field_70714_bg.func_75776_a(4, new com.emoniph.witchery.entity.ai.EntityAITradePlayerGeneric(this, this));
    field_70714_bg.func_75776_a(4, new com.emoniph.witchery.entity.ai.EntityAILookAtTradePlayerGeneric(this, this));
    field_70714_bg.func_75776_a(5, new EntityAIMoveIndoorsLeashAware(this));
    
    field_70714_bg.func_75776_a(5, new EntityAIRestrictOpenDoor(this));
    field_70714_bg.func_75776_a(6, new EntityAIOpenDoor(this, true));
    field_70714_bg.func_75776_a(7, new EntityAIMoveTowardsRestriction(this, 0.6D));
    field_70714_bg.func_75776_a(8, new com.emoniph.witchery.entity.ai.EntityAIGoblinMate(this));
    
    field_70714_bg.func_75776_a(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
    field_70714_bg.func_75776_a(9, new EntityAIWatchClosest2(this, EntityGoblin.class, 5.0F, 0.02F));
    field_70714_bg.func_75776_a(9, new EntityAIWander(this, 0.6D));
    field_70714_bg.func_75776_a(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
    field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, true));
    field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, true, true, this));
  }
  

  public boolean func_82704_a(Entity entity)
  {
    double R = 8.0D;
    if ((entity instanceof EntityVillager)) {
      return field_70170_p.func_72872_a(EntityGoblin.class, AxisAlignedBB.func_72330_a(field_70165_t - 8.0D, field_70163_u - 8.0D, field_70161_v - 8.0D, field_70165_t + 8.0D, field_70163_u + 8.0D, field_70161_v + 8.0D)).size() >= 3;
    }
    

    return true;
  }
  
  public boolean shouldAvoid()
  {
    double R = 8.0D;
    return field_70170_p.func_72872_a(EntityGoblin.class, AxisAlignedBB.func_72330_a(field_70165_t - 8.0D, field_70163_u - 8.0D, field_70161_v - 8.0D, field_70165_t + 8.0D, field_70163_u + 8.0D, field_70161_v + 8.0D)).size() >= 3;
  }
  

  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.goblin.name");
  }
  

  protected void func_110147_ax()
  {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4D);
    func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D);
  }
  
  public boolean func_70652_k(Entity targetEntity)
  {
    float f = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
    int i = 0;
    
    if ((targetEntity instanceof EntityLivingBase)) {
      f += EnchantmentHelper.func_77512_a(this, (EntityLivingBase)targetEntity);
      i += EnchantmentHelper.func_77507_b(this, (EntityLivingBase)targetEntity);
    }
    
    boolean flag = targetEntity.func_70097_a(DamageSource.func_76358_a(this), f);
    
    if (flag) {
      if (i > 0) {
        targetEntity.func_70024_g(-MathHelper.func_76126_a(field_70177_z * 3.1415927F / 180.0F) * i * 0.5F, 0.1D, MathHelper.func_76134_b(field_70177_z * 3.1415927F / 180.0F) * i * 0.5F);
        
        field_70159_w *= 0.6D;
        field_70179_y *= 0.6D;
      }
      
      int j = EnchantmentHelper.func_90036_a(this);
      
      if (j > 0) {
        targetEntity.func_70015_d(j * 4);
      }
      
      if ((targetEntity instanceof EntityLivingBase)) {
        EnchantmentHelper.func_151384_a((EntityLivingBase)targetEntity, this);
      }
      
      EnchantmentHelper.func_151385_b(this, targetEntity);
    }
    
    return flag;
  }
  
  public boolean func_70650_aV()
  {
    return true;
  }
  
  protected void func_70629_bd()
  {
    if (--randomTickDivider <= 0) {
      field_70170_p.field_72982_D.func_75551_a(MathHelper.func_76128_c(field_70165_t), MathHelper.func_76128_c(field_70163_u), MathHelper.func_76128_c(field_70161_v));
      
      randomTickDivider = (70 + field_70146_Z.nextInt(50));
      villageObj = field_70170_p.field_72982_D.func_75550_a(MathHelper.func_76128_c(field_70165_t), MathHelper.func_76128_c(field_70163_u), MathHelper.func_76128_c(field_70161_v), 32);
      


      if (villageObj == null) {
        func_110177_bN();
      } else {
        preventDespawn = true;
        ChunkCoordinates chunkcoordinates = villageObj.func_75577_a();
        func_110171_b(field_71574_a, field_71572_b, field_71573_c, (int)(villageObj.func_75568_b() * 0.6F));
        

        if (isLookingForHome) {
          isLookingForHome = false;
          villageObj.func_82683_b(5);
        }
      }
    }
    
    if ((!isTrading()) && (timeUntilReset > 0)) {
      timeUntilReset -= 1;
      
      if (timeUntilReset <= 0) {
        if (needsInitilization) {
          if (buyingList.size() > 1) {
            Iterator iterator = buyingList.iterator();
            
            while (iterator.hasNext()) {
              MerchantRecipe merchantrecipe = (MerchantRecipe)iterator.next();
              
              if (merchantrecipe.func_82784_g()) {
                merchantrecipe.func_82783_a(field_70146_Z.nextInt(6) + field_70146_Z.nextInt(6) + 2);
              }
            }
          }
          
          addDefaultEquipmentAndRecipies(1);
          needsInitilization = false;
          
          if ((villageObj != null) && (lastBuyingPlayer != null)) {
            field_70170_p.func_72960_a(this, (byte)14);
            villageObj.func_82688_a(lastBuyingPlayer, 1);
          }
        }
        
        func_70690_d(new PotionEffect(field_76428_lfield_76415_H, 200, 0));
      }
    }
    
    super.func_70629_bd();
  }
  

  protected void func_110159_bB()
  {
    try
    {
      testingLeashRange = true;
      if (func_110167_bD()) {
        preventDespawn = true;
      }
      super.func_110159_bB();
    } finally {
      testingLeashRange = false;
    }
  }
  
  public float func_70032_d(Entity par1Entity)
  {
    float distance = super.func_70032_d(par1Entity);
    if ((testingLeashRange) && (distance < 9.0F)) {
      distance *= 0.5F;
    }
    return distance;
  }
  
  public void func_70071_h_()
  {
    super.func_70071_h_();
    
    if (!field_70170_p.field_72995_K) {
      setBesideClimbableBlock(field_70123_F);
    }
  }
  
  public boolean func_70617_f_()
  {
    return super.func_70617_f_();
  }
  
  public boolean isWorking()
  {
    return field_70180_af.func_75683_a(18) == 1;
  }
  
  public void setWorking(boolean par1) {
    byte b0 = field_70180_af.func_75683_a(18);
    if (((par1) && (b0 != 1)) || ((!par1) && (b0 == 1))) {
      field_70180_af.func_75692_b(18, Byte.valueOf((byte)(par1 ? 1 : 0)));
    }
  }
  
  public boolean isWorshipping() {
    return field_70180_af.func_75683_a(18) == 2;
  }
  
  public void setWorshipping(boolean worshiping) {
    byte b0 = field_70180_af.func_75683_a(18);
    if (((worshiping) && (b0 != 2)) || ((!worshiping) && (b0 == 2))) {
      field_70180_af.func_75692_b(18, Byte.valueOf((byte)(worshiping ? 2 : 0)));
    }
  }
  
  public void beginWorship(TileEntity tile) {
    aiWorship.begin(tile);
  }
  
  public boolean isBesideClimbableBlock() {
    return (field_70180_af.func_75683_a(17) & 0x1) != 0;
  }
  
  public void setBesideClimbableBlock(boolean par1) {
    byte b0 = field_70180_af.func_75683_a(17);
    
    if (par1) {
      b0 = (byte)(b0 | 0x1);
    } else {
      b0 = (byte)(b0 & 0xFFFFFFFE);
    }
    
    field_70180_af.func_75692_b(17, Byte.valueOf(b0));
  }
  
  public boolean func_70085_c(EntityPlayer player)
  {
    ItemStack stack = field_71071_by.func_70448_g();
    boolean heldSpawnEgg = (stack != null) && (stack.func_77973_b() == Items.field_151063_bx);
    
    if ((!heldSpawnEgg) && (func_70089_S()) && (!isTrading()) && (!func_70631_g_()) && (!player.func_70093_af())) {
      if (func_110167_bD()) {
        if (func_70694_bm() == null) {
          if ((stack != null) && ((stack.func_77973_b() instanceof net.minecraft.item.ItemPickaxe))) {
            func_70062_b(0, stack);
            player.func_70062_b(0, null);
          }
        } else {
          if (!field_70170_p.field_72995_K) {
            ItemStack goblinItem = func_70694_bm();
            if (!field_71071_by.func_70441_a(goblinItem)) {
              func_70099_a(goblinItem, 1.0F);
            } else if ((player instanceof EntityPlayerMP)) {
              ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
            }
          }
          func_70062_b(0, null);
        }
      } else if ((!isWorking()) && (!isWorshipping()) && (villageObj != null) && 
        (!field_70170_p.field_72995_K)) {
        func_70932_a_(player);
        player.func_71030_a(this, func_70005_c_());
      }
      

      return true;
    }
    return super.func_70085_c(player);
  }
  

  protected void func_70088_a()
  {
    super.func_70088_a();
    field_70180_af.func_75682_a(16, Integer.valueOf(0));
    field_70180_af.func_75682_a(17, Byte.valueOf((byte)0));
    field_70180_af.func_75682_a(18, Byte.valueOf((byte)0));
    field_70180_af.func_75682_a(19, Byte.valueOf((byte)0));
  }
  

  public void func_70014_b(NBTTagCompound nbtRoot)
  {
    super.func_70014_b(nbtRoot);
    nbtRoot.func_74768_a("Profession", getProfession());
    nbtRoot.func_74768_a("Riches", wealth);
    nbtRoot.func_74757_a("Worshipping", isWorshipping());
    
    if (buyingList != null) {
      nbtRoot.func_74782_a("Offers", buyingList.func_77202_a());
    }
    
    nbtRoot.func_74757_a("PreventDespawn", preventDespawn);
  }
  
  public void func_70037_a(NBTTagCompound nbtRoot)
  {
    super.func_70037_a(nbtRoot);
    setProfession(nbtRoot.func_74762_e("Profession"));
    wealth = nbtRoot.func_74762_e("Riches");
    if ((nbtRoot.func_74767_n("Worshipping")) && (!field_70170_p.field_72995_K)) {
      setWorshipping(true);
    }
    
    if (nbtRoot.func_150297_b("Offers", 10)) {
      NBTTagCompound nbttagcompound1 = nbtRoot.func_74775_l("Offers");
      buyingList = new MerchantRecipeList(nbttagcompound1);
    }
    
    preventDespawn = nbtRoot.func_74767_n("PreventDespawn");
  }
  
  protected float func_70647_i()
  {
    return 1.2F;
  }
  
  protected boolean func_70692_ba()
  {
    return (!instancegoblinDespawnBlock) && (villageObj == null) && (!preventDespawn) && (!isWorshipping());
  }
  
  protected String func_70639_aQ()
  {
    return isTrading() ? "witchery:mob.goblin.haggle" : "witchery:mob.goblin.idle";
  }
  
  protected String func_70621_aR()
  {
    return "witchery:mob.goblin.hit";
  }
  
  protected String func_70673_aS()
  {
    return "witchery:mob.goblin.death";
  }
  
  public void setProfession(int par1) {
    field_70180_af.func_75692_b(16, Integer.valueOf(par1));
  }
  
  public int getProfession() {
    return field_70180_af.func_75679_c(16);
  }
  
  public boolean isMating() {
    return isMating;
  }
  
  public void setMating(boolean par1) {
    isMating = par1;
  }
  
  public void func_70604_c(EntityLivingBase par1EntityLivingBase)
  {
    super.func_70604_c(par1EntityLivingBase);
    
    if ((villageObj != null) && (par1EntityLivingBase != null)) {
      villageObj.func_75575_a(par1EntityLivingBase);
      
      if ((par1EntityLivingBase instanceof EntityPlayer))
      {

        if (func_70631_g_())
        {
          villageObj.func_82688_a(par1EntityLivingBase.func_70005_c_(), -3);
        }
        



        if (func_70089_S()) {
          field_70170_p.func_72960_a(this, (byte)13);
        }
      }
    }
  }
  
  public void func_70645_a(DamageSource par1DamageSource)
  {
    if (villageObj != null) {
      Entity entity = par1DamageSource.func_76346_g();
      
      if (entity != null) {
        if (!(entity instanceof EntityPlayer))
        {

          if ((entity instanceof IMob))
            villageObj.func_82692_h();
        }
      } else if (entity == null) {
        EntityPlayer entityplayer = field_70170_p.func_72890_a(this, 16.0D);
        
        if (entityplayer != null) {
          villageObj.func_82692_h();
        }
      }
    }
    
    super.func_70645_a(par1DamageSource);
  }
  
  public boolean func_70601_bi()
  {
    int i = MathHelper.func_76128_c(field_70165_t);
    int j = MathHelper.func_76128_c(field_70121_D.field_72338_b);
    int k = MathHelper.func_76128_c(field_70161_v);
    return (field_70170_p.func_147439_a(i, j - 1, k) == Blocks.field_150349_c) && (field_70170_p.func_72883_k(i, j, k) > 8) && (super.func_70601_bi());
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
    field_70757_a = (-func_70627_aG());
    func_85030_a("witchery:mob.goblin.yes", func_70599_aP(), func_70647_i());
    
    if (par1MerchantRecipe.func_77393_a((MerchantRecipe)buyingList.get(buyingList.size() - 1))) {
      timeUntilReset = 40;
      needsInitilization = true;
      
      if (buyingPlayer != null) {
        lastBuyingPlayer = buyingPlayer.func_70005_c_();
      } else {
        lastBuyingPlayer = null;
      }
    }
    
    if (par1MerchantRecipe.func_77394_a().func_77973_b() == Items.field_151166_bC) {
      wealth += func_77394_afield_77994_a;
    }
  }
  
  public void func_110297_a_(ItemStack par1ItemStack)
  {
    if ((!field_70170_p.field_72995_K) && (field_70757_a > -func_70627_aG() + 20)) {
      field_70757_a = (-func_70627_aG());
      
      if (par1ItemStack != null) {
        func_85030_a("witchery:mob.goblin.yes", func_70599_aP(), func_70647_i());
      } else {
        func_85030_a("witchery:mob.goblin.no", func_70599_aP(), func_70647_i());
      }
    }
  }
  
  public MerchantRecipeList func_70934_b(EntityPlayer par1EntityPlayer)
  {
    if (buyingList == null) {
      addDefaultEquipmentAndRecipies(1);
    }
    
    return buyingList;
  }
  
  private float adjustProbability(float par1) {
    float f1 = par1 + field_82191_bN;
    return f1 > 0.9F ? 0.9F - (f1 - 0.9F) : f1;
  }
  
  private void addDefaultEquipmentAndRecipies(int par1) {
    if (buyingList != null) {
      field_82191_bN = (MathHelper.func_76129_c(buyingList.size()) * 0.2F);
    } else {
      field_82191_bN = 0.0F;
    }
    
    MerchantRecipeList merchantrecipelist = new MerchantRecipeList();
    

    boolean shuffle = true;
    switch (getProfession()) {
    case 0: 
      addItemToSwapForAnEmerald(merchantrecipelist, Items.field_151015_O, field_70146_Z, adjustProbability(0.9F));
      addItemToSwapForAnEmerald(merchantrecipelist, Item.func_150898_a(Blocks.field_150325_L), field_70146_Z, adjustProbability(0.5F));
      
      addItemToSwapForAnEmerald(merchantrecipelist, Items.field_151076_bf, field_70146_Z, adjustProbability(0.5F));
      addItemToSwapForAnEmerald(merchantrecipelist, Items.field_151101_aQ, field_70146_Z, adjustProbability(0.4F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151025_P, field_70146_Z, adjustProbability(0.9F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151127_ba, field_70146_Z, adjustProbability(0.3F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151034_e, field_70146_Z, adjustProbability(0.3F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151106_aX, field_70146_Z, adjustProbability(0.3F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151097_aZ, field_70146_Z, adjustProbability(0.3F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151033_d, field_70146_Z, adjustProbability(0.3F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151077_bg, field_70146_Z, adjustProbability(0.3F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151032_g, field_70146_Z, adjustProbability(0.5F));
      
      if (field_70146_Z.nextFloat() < adjustProbability(0.5F)) {
        merchantrecipelist.add(new MerchantRecipe(new ItemStack(Blocks.field_150351_n, 10), new ItemStack(Items.field_151166_bC), new ItemStack(Items.field_151145_ak, 4 + field_70146_Z.nextInt(2), 0)));
      }
      

      break;
    case 1: 
    case 2: 
      shuffle = false;
      if (buyingList == null) {
        merchantrecipelist.add(new MerchantRecipe(ItemsGENERIC.itemKobolditeDust.createStack(9), new ItemStack(Items.field_151074_bl, 5), ItemsGENERIC.itemKobolditeNugget.createStack()));

      }
      else if (buyingList.size() == 1) {
        merchantrecipelist.add(new MerchantRecipe(ItemsGENERIC.itemKobolditeDust.createStack(16), new ItemStack(Items.field_151043_k), ItemsGENERIC.itemKobolditeNugget.createStack(2)));

      }
      else if (buyingList.size() == 2) {
        merchantrecipelist.add(new MerchantRecipe(ItemsGENERIC.itemKobolditeNugget.createStack(9), new ItemStack(Items.field_151166_bC), ItemsGENERIC.itemKobolditeIngot.createStack()));
      }
      

      break;
    case 3: 
      addItemToSwapForAnEmerald(merchantrecipelist, Items.field_151044_h, field_70146_Z, adjustProbability(0.7F));
      addItemToSwapForAnEmerald(merchantrecipelist, Items.field_151042_j, field_70146_Z, adjustProbability(0.5F));
      addItemToSwapForAnEmerald(merchantrecipelist, Items.field_151043_k, field_70146_Z, adjustProbability(0.5F));
      addItemToSwapForAnEmerald(merchantrecipelist, Items.field_151045_i, field_70146_Z, adjustProbability(0.5F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151040_l, field_70146_Z, adjustProbability(0.5F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151048_u, field_70146_Z, adjustProbability(0.5F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151036_c, field_70146_Z, adjustProbability(0.3F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151056_x, field_70146_Z, adjustProbability(0.3F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151035_b, field_70146_Z, adjustProbability(0.5F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151046_w, field_70146_Z, adjustProbability(0.5F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151037_a, field_70146_Z, adjustProbability(0.2F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151047_v, field_70146_Z, adjustProbability(0.2F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151019_K, field_70146_Z, adjustProbability(0.2F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151012_L, field_70146_Z, adjustProbability(0.2F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151167_ab, field_70146_Z, adjustProbability(0.2F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151175_af, field_70146_Z, adjustProbability(0.2F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151028_Y, field_70146_Z, adjustProbability(0.2F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151161_ac, field_70146_Z, adjustProbability(0.2F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151030_Z, field_70146_Z, adjustProbability(0.2F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151163_ad, field_70146_Z, adjustProbability(0.2F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151165_aa, field_70146_Z, adjustProbability(0.2F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151173_ae, field_70146_Z, adjustProbability(0.2F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151029_X, field_70146_Z, adjustProbability(0.1F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151020_U, field_70146_Z, adjustProbability(0.1F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151023_V, field_70146_Z, adjustProbability(0.1F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151022_W, field_70146_Z, adjustProbability(0.1F));
      break;
    case 4: 
      addItemToSwapForAnEmerald(merchantrecipelist, Items.field_151044_h, field_70146_Z, adjustProbability(0.7F));
      addItemToSwapForAnEmerald(merchantrecipelist, Items.field_151147_al, field_70146_Z, adjustProbability(0.5F));
      addItemToSwapForAnEmerald(merchantrecipelist, Items.field_151082_bd, field_70146_Z, adjustProbability(0.5F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151141_av, field_70146_Z, adjustProbability(0.1F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151027_R, field_70146_Z, adjustProbability(0.3F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151021_T, field_70146_Z, adjustProbability(0.3F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151024_Q, field_70146_Z, adjustProbability(0.3F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151026_S, field_70146_Z, adjustProbability(0.3F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151157_am, field_70146_Z, adjustProbability(0.3F));
      addItemToBuyOrSell(merchantrecipelist, Items.field_151083_be, field_70146_Z, adjustProbability(0.3F));
    }
    
    if (merchantrecipelist.isEmpty()) {
      merchantrecipelist.add(new MerchantRecipe(getItemStackToSwapForAnEmerald(Items.field_151043_k, field_70146_Z), Items.field_151166_bC));
    }
    

    if (shuffle) {
      Collections.shuffle(merchantrecipelist);
    }
    
    if (buyingList == null) {
      buyingList = new MerchantRecipeList();
    }
    
    for (int l = 0; (l < par1) && (l < merchantrecipelist.size()); l++) {
      buyingList.func_77205_a((MerchantRecipe)merchantrecipelist.get(l));
    }
  }
  

  @SideOnly(Side.CLIENT)
  public void func_70930_a(MerchantRecipeList par1MerchantRecipeList) {}
  

  public static void addItemToSwapForAnEmerald(MerchantRecipeList buyList, Item item, Random rand, float probability)
  {
    if (rand.nextFloat() < probability) {
      if (rand.nextInt(3) == 0) {
        buyList.add(new MerchantRecipe(getItemStackToSwapForAnEmerald(item, rand), ItemsGENERIC.itemKobolditeDust.createStack()));
      }
      else {
        buyList.add(new MerchantRecipe(getItemStackToSwapForAnEmerald(item, rand), Items.field_151166_bC));
      }
    }
  }
  
  private static ItemStack getItemStackToSwapForAnEmerald(Item item, Random rand) {
    return new ItemStack(item, getQuantityToSwapForAnEmerald(item, rand), 0);
  }
  
  private static int getQuantityToSwapForAnEmerald(Item item, Random rand) {
    Tuple tuple = (Tuple)villagersSellingList.get(item);
    return ((Integer)tuple.func_76341_a()).intValue() >= ((Integer)tuple.func_76340_b()).intValue() ? ((Integer)tuple.func_76341_a()).intValue() : tuple == null ? 1 : ((Integer)tuple.func_76341_a()).intValue() + rand.nextInt(((Integer)tuple.func_76340_b()).intValue() - ((Integer)tuple.func_76341_a()).intValue());
  }
  



  public static void addItemToBuyOrSell(MerchantRecipeList list, Item item, Random rand, float probability)
  {
    if (rand.nextFloat() < probability) {
      int i = quantityToBuyOrSell(item, rand);
      ItemStack itemstack1;
      ItemStack itemstack;
      ItemStack itemstack1;
      if (i < 0)
      {
        ItemStack itemstack = ItemsGENERIC.itemKobolditeNugget.createStack(1);
        itemstack1 = new ItemStack(item, -i, 0);
      }
      else {
        itemstack = ItemsGENERIC.itemKobolditeNugget.createStack(i);
        itemstack1 = new ItemStack(item, 1, 0);
      }
      
      list.add(new MerchantRecipe(itemstack, itemstack1));
    }
  }
  
  private static int quantityToBuyOrSell(Item item, Random rand) {
    Tuple tuple = (Tuple)blacksmithSellingList.get(item);
    return ((Integer)tuple.func_76341_a()).intValue() >= ((Integer)tuple.func_76340_b()).intValue() ? ((Integer)tuple.func_76341_a()).intValue() : tuple == null ? 1 : ((Integer)tuple.func_76341_a()).intValue() + rand.nextInt(((Integer)tuple.func_76340_b()).intValue() - ((Integer)tuple.func_76341_a()).intValue());
  }
  




  @SideOnly(Side.CLIENT)
  public void func_70103_a(byte par1)
  {
    if (par1 == 12) {
      generateRandomParticles("heart");
    } else if (par1 == 13) {
      generateRandomParticles("angryVillager");
    } else if (par1 == 14) {
      generateRandomParticles("happyVillager");
    } else {
      super.func_70103_a(par1);
    }
  }
  
  public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData)
  {
    par1EntityLivingData = super.func_110161_a(par1EntityLivingData);
    int trade = field_70146_Z.nextInt(5);
    setProfession(trade);
    return par1EntityLivingData;
  }
  
  @SideOnly(Side.CLIENT)
  private void generateRandomParticles(String par1Str) {
    for (int i = 0; i < 5; i++) {
      double d0 = field_70146_Z.nextGaussian() * 0.02D;
      double d1 = field_70146_Z.nextGaussian() * 0.02D;
      double d2 = field_70146_Z.nextGaussian() * 0.02D;
      field_70170_p.func_72869_a(par1Str, field_70165_t + field_70146_Z.nextFloat() * field_70130_N * 2.0F - field_70130_N, field_70163_u + 1.0D + field_70146_Z.nextFloat() * field_70131_O, field_70161_v + field_70146_Z.nextFloat() * field_70130_N * 2.0F - field_70130_N, d0, d1, d2);
    }
  }
  

  public void setLookingForHome()
  {
    isLookingForHome = true;
  }
  
  public EntityGoblin createChild(EntityAgeable par1EntityAgeable)
  {
    EntityGoblin entityvillager = new EntityGoblin(field_70170_p);
    entityvillager.func_110161_a((IEntityLivingData)null);
    return entityvillager;
  }
  
  static {
    villagersSellingList.put(Items.field_151044_h, new Tuple(Integer.valueOf(16), Integer.valueOf(24)));
    villagersSellingList.put(Items.field_151042_j, new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
    villagersSellingList.put(Items.field_151043_k, new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
    villagersSellingList.put(Items.field_151045_i, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
    villagersSellingList.put(Items.field_151121_aF, new Tuple(Integer.valueOf(24), Integer.valueOf(36)));
    villagersSellingList.put(Items.field_151122_aG, new Tuple(Integer.valueOf(11), Integer.valueOf(13)));
    villagersSellingList.put(Items.field_151164_bB, new Tuple(Integer.valueOf(1), Integer.valueOf(1)));
    villagersSellingList.put(Items.field_151079_bi, new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
    villagersSellingList.put(Items.field_151061_bv, new Tuple(Integer.valueOf(2), Integer.valueOf(3)));
    villagersSellingList.put(Items.field_151147_al, new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
    villagersSellingList.put(Items.field_151082_bd, new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
    villagersSellingList.put(Items.field_151076_bf, new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
    villagersSellingList.put(Items.field_151101_aQ, new Tuple(Integer.valueOf(9), Integer.valueOf(13)));
    villagersSellingList.put(Items.field_151014_N, new Tuple(Integer.valueOf(34), Integer.valueOf(48)));
    villagersSellingList.put(Items.field_151081_bc, new Tuple(Integer.valueOf(30), Integer.valueOf(38)));
    villagersSellingList.put(Items.field_151080_bb, new Tuple(Integer.valueOf(30), Integer.valueOf(38)));
    villagersSellingList.put(Items.field_151015_O, new Tuple(Integer.valueOf(18), Integer.valueOf(22)));
    villagersSellingList.put(Item.func_150898_a(Blocks.field_150325_L), new Tuple(Integer.valueOf(14), Integer.valueOf(22)));
    
    villagersSellingList.put(Items.field_151078_bh, new Tuple(Integer.valueOf(36), Integer.valueOf(64)));
    
    blacksmithSellingList.put(Items.field_151033_d, new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
    blacksmithSellingList.put(Items.field_151097_aZ, new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
    blacksmithSellingList.put(Items.field_151040_l, new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
    blacksmithSellingList.put(Items.field_151048_u, new Tuple(Integer.valueOf(12), Integer.valueOf(14)));
    blacksmithSellingList.put(Items.field_151036_c, new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
    blacksmithSellingList.put(Items.field_151056_x, new Tuple(Integer.valueOf(9), Integer.valueOf(12)));
    blacksmithSellingList.put(Items.field_151035_b, new Tuple(Integer.valueOf(7), Integer.valueOf(9)));
    blacksmithSellingList.put(Items.field_151046_w, new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
    blacksmithSellingList.put(Items.field_151037_a, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
    blacksmithSellingList.put(Items.field_151047_v, new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
    blacksmithSellingList.put(Items.field_151019_K, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
    blacksmithSellingList.put(Items.field_151012_L, new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
    blacksmithSellingList.put(Items.field_151167_ab, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
    blacksmithSellingList.put(Items.field_151175_af, new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
    blacksmithSellingList.put(Items.field_151028_Y, new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
    blacksmithSellingList.put(Items.field_151161_ac, new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
    blacksmithSellingList.put(Items.field_151030_Z, new Tuple(Integer.valueOf(10), Integer.valueOf(14)));
    blacksmithSellingList.put(Items.field_151163_ad, new Tuple(Integer.valueOf(16), Integer.valueOf(19)));
    blacksmithSellingList.put(Items.field_151165_aa, new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
    blacksmithSellingList.put(Items.field_151173_ae, new Tuple(Integer.valueOf(11), Integer.valueOf(14)));
    blacksmithSellingList.put(Items.field_151029_X, new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
    blacksmithSellingList.put(Items.field_151020_U, new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
    blacksmithSellingList.put(Items.field_151023_V, new Tuple(Integer.valueOf(11), Integer.valueOf(15)));
    blacksmithSellingList.put(Items.field_151022_W, new Tuple(Integer.valueOf(9), Integer.valueOf(11)));
    blacksmithSellingList.put(Items.field_151025_P, new Tuple(Integer.valueOf(-4), Integer.valueOf(-2)));
    blacksmithSellingList.put(Items.field_151127_ba, new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
    blacksmithSellingList.put(Items.field_151034_e, new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
    blacksmithSellingList.put(Items.field_151106_aX, new Tuple(Integer.valueOf(-10), Integer.valueOf(-7)));
    blacksmithSellingList.put(Item.func_150898_a(Blocks.field_150359_w), new Tuple(Integer.valueOf(-5), Integer.valueOf(-3)));
    
    blacksmithSellingList.put(Item.func_150898_a(Blocks.field_150342_X), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
    
    blacksmithSellingList.put(Items.field_151027_R, new Tuple(Integer.valueOf(4), Integer.valueOf(5)));
    blacksmithSellingList.put(Items.field_151021_T, new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
    blacksmithSellingList.put(Items.field_151024_Q, new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
    blacksmithSellingList.put(Items.field_151026_S, new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
    blacksmithSellingList.put(Items.field_151141_av, new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
    blacksmithSellingList.put(Items.field_151062_by, new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
    blacksmithSellingList.put(Items.field_151137_ax, new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
    blacksmithSellingList.put(Items.field_151111_aL, new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
    blacksmithSellingList.put(Items.field_151113_aN, new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
    blacksmithSellingList.put(Item.func_150898_a(Blocks.field_150426_aN), new Tuple(Integer.valueOf(-3), Integer.valueOf(-1)));
    
    blacksmithSellingList.put(Items.field_151157_am, new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
    blacksmithSellingList.put(Items.field_151083_be, new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
    blacksmithSellingList.put(Items.field_151077_bg, new Tuple(Integer.valueOf(-8), Integer.valueOf(-6)));
    blacksmithSellingList.put(Items.field_151061_bv, new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
    blacksmithSellingList.put(Items.field_151032_g, new Tuple(Integer.valueOf(-12), Integer.valueOf(-8)));
  }
}
