package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.entity.ai.EntityAIWanderWithRestriction.IHomeLocationProvider;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.item.ItemGeneralContract;
import com.emoniph.witchery.item.ItemTaglockKit;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TameableUtil;
import com.emoniph.witchery.util.TimeUtil;
import java.util.HashMap;
import java.util.Random;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class EntityImp extends EntityTameable implements IMob, IEntitySelector, EntityAIWanderWithRestriction.IHomeLocationProvider
{
  private float field_70926_e;
  private float field_70924_f;
  private boolean field_70928_h;
  private static final int MAX_WANDER_RANGE = 16;
  private int secretsShared;
  private int homeX;
  private int homeY;
  private int homeZ;
  private long lastGiftTime;
  private long powerUpExpiry;
  
  public EntityImp(World par1World)
  {
    super(par1World);
    field_70178_ae = true;
    func_70105_a(0.4F, 1.3F);
    func_70661_as().func_75491_a(true);
    func_70661_as().func_75495_e(true);
    field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
    field_70714_bg.func_75776_a(2, new EntityAILeapAtTarget(this, 0.4F));
    field_70714_bg.func_75776_a(3, new EntityAIAttackOnCollide(this, 1.0D, true));
    field_70714_bg.func_75776_a(4, new com.emoniph.witchery.entity.ai.EntityAIWanderWithRestriction(this, 1.0D, this));
    field_70714_bg.func_75776_a(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    field_70714_bg.func_75776_a(6, new EntityAILookIdle(this));
    field_70715_bh.func_75776_a(1, new EntityAIOwnerHurtByTarget(this));
    field_70715_bh.func_75776_a(2, new EntityAIOwnerHurtTarget(this));
    field_70715_bh.func_75776_a(3, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, true));
    field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, false, true, this));
    func_70903_f(false);
  }
  
  protected void func_110147_ax()
  {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.30000001192092896D);
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0D);
  }
  
  protected void func_70088_a()
  {
    super.func_70088_a();
    field_70180_af.func_75682_a(18, Integer.valueOf(0));
    field_70180_af.func_75682_a(19, Integer.valueOf(0));
  }
  
  private void setAffection(int affection) {
    field_70180_af.func_75692_b(18, Integer.valueOf(affection));
  }
  
  private int getAffection() {
    return field_70180_af.func_75679_c(18);
  }
  
  private void setPowered(boolean powered) {
    if (!field_70170_p.field_72995_K) {
      field_70180_af.func_75692_b(19, Integer.valueOf(powered ? 1 : 0));
    }
  }
  
  public boolean isPowered() {
    return field_70180_af.func_75679_c(19) == 1;
  }
  
  public boolean func_82704_a(Entity target)
  {
    if (!func_70909_n()) {
      return target instanceof EntityPlayer;
    }
    return target == func_70638_az();
  }
  

  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.imp.name");
  }
  

  public boolean func_70650_aV()
  {
    return true;
  }
  
  public void func_70014_b(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70014_b(par1NBTTagCompound);
    
    par1NBTTagCompound.func_74768_a("Affection", getAffection());
    par1NBTTagCompound.func_74768_a("SecretsShared", secretsShared);
    par1NBTTagCompound.func_74772_a("LastGiftTime", lastGiftTime);
    par1NBTTagCompound.func_74772_a("PowerUpUntil2", powerUpExpiry);
    par1NBTTagCompound.func_74768_a("HomeLocX", homeX);
    par1NBTTagCompound.func_74768_a("HomeLocY", homeY);
    par1NBTTagCompound.func_74768_a("HomeLocZ", homeZ);
  }
  
  public void func_70037_a(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70037_a(par1NBTTagCompound);
    
    setAffection(par1NBTTagCompound.func_74762_e("Affection"));
    secretsShared = par1NBTTagCompound.func_74762_e("SecretsShared");
    lastGiftTime = par1NBTTagCompound.func_74763_f("LastGiftTime");
    
    long time = TimeUtil.getServerTimeInTicks();
    
    if (par1NBTTagCompound.func_74764_b("PowerUpUntil2")) {
      powerUpExpiry = par1NBTTagCompound.func_74763_f("PowerUpUntil2");
    } else if (par1NBTTagCompound.func_74764_b("PowerUpUntil")) {
      powerUpExpiry = par1NBTTagCompound.func_74763_f("PowerUpUntil");
      if (powerUpExpiry > 0L) {
        powerUpExpiry = (time + TimeUtil.minsToTicks(60));
      }
    }
    

    if (time < powerUpExpiry) {
      setPowered(true);
    }
    
    homeX = par1NBTTagCompound.func_74762_e("HomeLocX");
    homeY = par1NBTTagCompound.func_74762_e("HomeLocY");
    homeZ = par1NBTTagCompound.func_74762_e("HomeLocZ");
  }
  
  protected String func_70639_aQ()
  {
    return "witchery:mob.imp.laugh";
  }
  

  protected float func_70647_i()
  {
    return isPowered() ? (field_70146_Z.nextFloat() - field_70146_Z.nextFloat()) * 0.2F + 0.7F : (field_70146_Z.nextFloat() - field_70146_Z.nextFloat()) * 0.2F + 1.1F;
  }
  
  protected String func_70621_aR()
  {
    return "witchery:mob.imp.hit";
  }
  
  protected String func_70673_aS()
  {
    return "witchery:mob.imp.death";
  }
  
  protected float func_70599_aP()
  {
    return 0.5F;
  }
  
  public int func_70627_aG()
  {
    return TimeUtil.secsToTicks(40);
  }
  
  public void func_70636_d()
  {
    super.func_70636_d();
    
    if (!field_70170_p.field_72995_K)
    {
      if ((TimeUtil.secondsElapsed(300, field_70173_aa)) && (TameableUtil.hasOwner(this))) {
        EntityLivingBase owner = func_70902_q();
        if ((owner instanceof EntityPlayer)) {
          EntityPlayer player = (EntityPlayer)owner;
          setAffection(Math.max(0, getAffection() - 1));
          if ((getAffection() == 0) && (field_70173_aa > TimeUtil.minsToTicks(60)) && 
            (field_70170_p.field_73012_v.nextDouble() < 0.01D)) {
            ParticleEffect.FLAME.send(SoundEffect.WITCHERY_MOB_IMP_LAUGH, this, 1.0D, 1.0D, 16);
            ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.goodbye", new Object[] { func_70005_c_() });
            func_70106_y();
          }
        }
      }
    }
    

    if ((!field_70170_p.field_72995_K) && (powerUpExpiry > 0L) && (isPowerupExpired())) {
      setPowered(false);
      powerUpExpiry = 0L;
    }
    if (field_70173_aa % 20 == 0)
    {
      if (isPowered()) {
        if (field_70130_N != 0.6D) {
          func_70105_a(0.6F, 1.3F);
        }
        if (!field_70170_p.field_72995_K) {
          func_70691_i(1.0F);
        }
      }
      else if (field_70130_N != 0.4D) {
        func_70105_a(0.4F, 1.3F);
      }
    }
    
    if (field_70173_aa % 400 == 0) {
      func_70691_i(1.0F);
    }
  }
  
  private boolean isPowerupExpired() {
    return TimeUtil.getServerTimeInTicks() >= powerUpExpiry;
  }
  
  public void func_70071_h_()
  {
    super.func_70071_h_();
    if ((field_70170_p.field_72995_K) && (isPowered())) {
      field_70170_p.func_72869_a(ParticleEffect.FLAME.toString(), field_70165_t - field_70130_N * 0.5D + field_70170_p.field_73012_v.nextDouble() * field_70130_N, 0.1D + field_70163_u + field_70170_p.field_73012_v.nextDouble() * 2.0D, field_70161_v - field_70130_N * 0.5D + field_70170_p.field_73012_v.nextDouble() * field_70130_N, 0.0D, 0.0D, 0.0D);
    }
  }
  

  public boolean func_70097_a(DamageSource source, float damage)
  {
    return super.func_70097_a(source, Math.min(damage, isPowered() ? 5.0F : 15.0F));
  }
  
  public boolean func_70652_k(Entity par1Entity)
  {
    return par1Entity.func_70097_a(DamageSource.func_76358_a(this), isPowered() ? 8.0F : 4.0F);
  }
  
  private static final HashMap<Item, Integer> shinies = new HashMap();
  
  static { shinies.put(new ItemStack(Items.field_151045_i).func_77973_b(), Integer.valueOf(8));
    shinies.put(new ItemStack(Items.field_151056_x).func_77973_b(), Integer.valueOf(24));
    shinies.put(new ItemStack(Items.field_151012_L).func_77973_b(), Integer.valueOf(16));
    shinies.put(new ItemStack(Items.field_151048_u).func_77973_b(), Integer.valueOf(16));
    shinies.put(new ItemStack(Items.field_151047_v).func_77973_b(), Integer.valueOf(8));
    shinies.put(new ItemStack(Items.field_151046_w).func_77973_b(), Integer.valueOf(24));
    shinies.put(new ItemStack(Items.field_151166_bC).func_77973_b(), Integer.valueOf(3));
    shinies.put(new ItemStack(Items.field_151043_k).func_77973_b(), Integer.valueOf(1));
    shinies.put(new ItemStack(Items.field_151156_bN).func_77973_b(), Integer.valueOf(16));
    shinies.put(new ItemStack(Items.field_151072_bj).func_77973_b(), Integer.valueOf(1));
    shinies.put(new ItemStack(Items.field_151073_bk).func_77973_b(), Integer.valueOf(4));
    shinies.put(new ItemStack(Items.field_151006_E).func_77973_b(), Integer.valueOf(3));
    shinies.put(new ItemStack(Items.field_151010_B).func_77973_b(), Integer.valueOf(2));
    shinies.put(new ItemStack(Items.field_151013_M).func_77973_b(), Integer.valueOf(2));
    shinies.put(new ItemStack(Items.field_151011_C).func_77973_b(), Integer.valueOf(1));
    shinies.put(new ItemStack(Items.field_151005_D).func_77973_b(), Integer.valueOf(3));
    shinies.put(new ItemStack(Blocks.field_150340_R).func_77973_b(), Integer.valueOf(9));
    shinies.put(new ItemStack(Blocks.field_150475_bE).func_77973_b(), Integer.valueOf(27));
    shinies.put(new ItemStack(Blocks.field_150484_ah).func_77973_b(), Integer.valueOf(72));
    shinies.put(new ItemStack(Blocks.field_150368_y).func_77973_b(), Integer.valueOf(7));
    shinies.put(new ItemStack(Blocks.field_150451_bX).func_77973_b(), Integer.valueOf(5));
  }
  
  private static final int REWARD_AFFECTION_LEVEL = 20;
  private static final long GIFT_DELAY_TICKS = TimeUtil.minsToTicks(3);
  
  private static final ItemStack[] EXTRA_DROPS = { ItemsGENERIC.itemBatWool.createStack(5), ItemsGENERIC.itemDogTongue.createStack(5), ItemsGENERIC.itemToeOfFrog.createStack(2), ItemsGENERIC.itemOwletsWing.createStack(2), ItemsGENERIC.itemBranchEnt.createStack(1), ItemsGENERIC.itemInfernalBlood.createStack(2), ItemsGENERIC.itemCreeperHeart.createStack(2) };
  








  public boolean func_70085_c(EntityPlayer player)
  {
    ItemStack stack = field_71071_by.func_70448_g();
    if (stack == null) {
      return true;
    }
    if (field_70170_p.field_72995_K) {
      return false;
    }
    
    if (func_70909_n()) {
      if (ItemsGENERIC.itemDemonHeart.isMatch(stack)) {
        if (!field_71075_bZ.field_75098_d) {
          field_77994_a -= 1;
          if (field_77994_a <= 0) {
            field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
          }
        }
        if (!field_70170_p.field_72995_K) {
          powerUpExpiry = (TimeUtil.getServerTimeInTicks() + TimeUtil.minsToTicks(60));
          setPowered(true);
          SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(field_70170_p, player, 0.5F, func_70647_i());
          ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.gift.power", new Object[] { func_70005_c_() });
        }
      } else if (ItemsGENERIC.itemIcyNeedle.isMatch(stack)) {
        if (!field_71075_bZ.field_75098_d) {
          field_77994_a -= 1;
          if (field_77994_a <= 0) {
            field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
          }
        }
        if (!field_70170_p.field_72995_K) {
          powerUpExpiry = 0L;
          setPowered(false);
          SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(field_70170_p, player, 0.5F, func_70647_i());
          ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.gift.powerloss", new Object[] { func_70005_c_() });
        }
      } else if (ItemGeneralContract.isBoundContract(stack)) {
        if (!field_70170_p.field_72995_K) {
          SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(field_70170_p, player, 0.5F, func_70647_i());
          if (!isPowered()) {
            if (getAffection() >= 20) {
              long timeNow = TimeUtil.getServerTimeInTicks();
              if ((timeNow > lastGiftTime + GIFT_DELAY_TICKS) || (field_71075_bZ.field_75098_d)) {
                ItemGeneralContract contract = ItemGeneralContract.getContract(stack);
                EntityLivingBase targetEntity = ItemsTAGLOCK_KIT.getBoundEntity(field_70170_p, player, stack, Integer.valueOf(1));
                
                if (targetEntity != null) {
                  if (contract.activate(stack, targetEntity)) {
                    lastGiftTime = timeNow;
                    ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.spell.feelthefire", new Object[] { func_70005_c_(), targetEntity.func_70005_c_() });
                    if (!field_71075_bZ.field_75098_d) {
                      field_77994_a -= 1;
                      if (field_77994_a <= 0) {
                        field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
                      }
                    }
                  } else {
                    ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.spell.failed", new Object[] { func_70005_c_(), targetEntity.func_70005_c_() });
                  }
                } else {
                  String name = ItemsTAGLOCK_KIT.getBoundEntityDisplayName(stack, Integer.valueOf(1));
                  ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.spell.cannotfind", new Object[] { func_70005_c_(), name });
                }
              } else {
                ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.spell.toooften", new Object[] { func_70005_c_() });
              }
            } else {
              ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.spell.notliked", new Object[] { func_70005_c_() });
            }
          } else {
            ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.spell.toomuchpower", new Object[] { func_70005_c_() });
          }
        }
      } else {
        if (!field_70170_p.field_72995_K) {
          Integer affectionBoost = (Integer)shinies.get(stack.func_77973_b());
          if ((affectionBoost != null) && (stack.func_77960_j() == 0)) {
            long timeNow = TimeUtil.getServerTimeInTicks();
            

            if (!field_71075_bZ.field_75098_d) {
              field_77994_a -= 1;
              if (field_77994_a <= 0) {
                field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
              }
            }
            
            int affection = getAffection() + affectionBoost.intValue();
            SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(field_70170_p, player, 0.5F, func_70647_i());
            
            if ((affection >= 20) && ((timeNow > lastGiftTime + GIFT_DELAY_TICKS) || (field_71075_bZ.field_75098_d)) && (field_70146_Z.nextInt(Math.max(1, 10 - Math.max(affection - 20, 0))) == 0))
            {
              lastGiftTime = timeNow;
              affection = 0;
              ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.gift.reciprocate", new Object[] { func_70005_c_() });
              
              ItemStack stackForPlayer = null;
              switch (secretsShared) {
              case 0: 
                stackForPlayer = ItemsGENERIC.itemBrewSoulHunger.createStack();
                secretsShared += 1;
                break;
              case 1: 
                stackForPlayer = ItemsGENERIC.itemBrewSoulFear.createStack();
                secretsShared += 1;
                break;
              case 2: 
                stackForPlayer = ItemsGENERIC.itemBrewSoulAnguish.createStack();
                secretsShared += 1;
                break;
              case 3: 
                stackForPlayer = ItemsGENERIC.itemContractTorment.createStack();
                secretsShared += 1;
                break;
              default: 
                stackForPlayer = EXTRA_DROPS[field_70146_Z.nextInt(EXTRA_DROPS.length)].func_77946_l();
              }
              
              if (stackForPlayer != null) {
                ParticleEffect.INSTANT_SPELL.send(SoundEffect.NOTE_HARP, this, 1.0D, 1.0D, 16);
                field_70170_p.func_72838_d(new EntityItem(field_70170_p, field_70165_t, field_70163_u, field_70161_v, stackForPlayer));
              }
            } else if (timeNow < lastGiftTime + GIFT_DELAY_TICKS) {
              ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.gift.toomany", new Object[] { func_70005_c_() });
            } else {
              ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.gift.like", new Object[] { func_70005_c_() });
            }
            
            setAffection(affection);
          }
          else {
            SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(field_70170_p, player, 0.5F, func_70647_i());
            ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.gift.hate", new Object[] { func_70005_c_() });
          }
        }
        return true;
      }
    } else if (ItemsGENERIC.itemContractOwnership.isMatch(stack)) {
      if (!field_70170_p.field_72995_K)
      {
        EntityLivingBase boundEntity = ItemGeneralContract.getBoundEntity(field_70170_p, player, stack);
        if (boundEntity == player) {
          int EXPERIENCE_NEEDED = 25;
          if ((field_71068_ca >= 25) || (field_71075_bZ.field_75098_d)) {
            if (!field_71075_bZ.field_75098_d) {
              field_77994_a -= 1;
              if (field_77994_a <= 0) {
                field_71071_by.func_70299_a(field_71071_by.field_70461_c, null);
              }
            }
            player.func_82242_a(-25);
            
            func_70903_f(true);
            TameableUtil.setOwner(this, player);
            func_70624_b(null);
            func_70778_a(null);
            homeX = ((int)field_70165_t);
            homeY = ((int)field_70163_u);
            homeZ = ((int)field_70161_v);
            func_110163_bv();
            SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(field_70170_p, player, 0.5F, func_70647_i());
            ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.contract.deal", new Object[] { func_70005_c_() });
            func_94058_c(getDemonName(field_70146_Z));
          } else {
            SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(field_70170_p, player, 0.5F, func_70647_i());
            ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.contract.noxp", new Object[] { func_70005_c_() });
          }
        }
        else if (boundEntity != null) {
          SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(field_70170_p, player, 0.5F, func_70647_i());
          ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.contract.notowners", new Object[] { func_70005_c_() });
        } else {
          SoundEffect.WITCHERY_MOB_IMP_LAUGH.playAtPlayer(field_70170_p, player, 0.5F, func_70647_i());
          ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, player, "entity.witchery.imp.contract.unsigned", new Object[] { func_70005_c_() });
        }
      }
      
      return true;
    }
    
    return super.func_70085_c(player);
  }
  

  public EntityImp createChild(EntityAgeable par1EntityAgeable)
  {
    return null;
  }
  
  public boolean func_70878_b(EntityAnimal par1EntityAnimal)
  {
    return false;
  }
  
  protected boolean func_70692_ba()
  {
    return true;
  }
  
  private static String getDemonName(Random rand) {
    if (rand.nextInt(5) == 0) {
      return DEMON_NAMES[rand.nextInt(DEMON_NAMES.length)];
    }
    return DEMON_NAMES[rand.nextInt(DEMON_NAMES.length)] + " " + DEMON_NAMES[rand.nextInt(DEMON_NAMES.length)];
  }
  

  private static final String[] DEMON_NAMES = { "Ppaironael", "Aethon", "Tyrnak", "Beelzebuth", "Botis", "Moloch", "Taet", "Epnanaet", "Unonom", "Hexpemsazon", "Thayax", "Ethahoat", "Pruslas", "Ahtuxies", "Laripael", "Elxar", "Tarihimal", "Sapanolr", "Sahaminapiel", "Honed", "Oghmus", "Zedeson", "Halmaneop", "Nopoz", "Ekarnahox", "Sacuhatakael", "Ticos", "Arametheus", "Azmodaeus", "Larhepeis", "Topriraiz", "Rarahaimzah", "Tedrahamael", "Osaselael", "Phlegon", "Nelokhiel", "Haristum", "Zul", "Larhepeis", "Aamon", "Tramater", "Ehhbes", "Kra`an", "Quarax", "Hotesiatrem", "Surgat", "Nu`uhn", "Litedabh", "Unonom", "Bolenoz", "Hilopael", "Haristum", "Uhn", "Hiepacth", "Pemcapso", "Ankou", "Pundohien", "Koit", "Montobulus", "Amsaset", "Aropet", "Isnal", "Solael", "Exroh", "Sidragrosam", "Pnecamob", "Malashim", "Beelzebuth", "Ehohit", "Izatap", "Olon", "Assoaz", "Agalierept", "Krakus", "Umlaboor", "Aknrar", "Damaz", "Rhysus", "Pundohien", "Ba`al", "Rasuniolpas", "Anhoor", "Nyarlathotep", "Krakus", "Larhepeis", "Itakup", "Erdok", "Umlaboor", "Ezon", "Krakus", "Glassyalabolas", "Kra`an", "Ehnnat", "Terxor", "Asramel", "Tadal", "Arpzih", "Azmodaeus", "Henbolaron", "Rhysus" };
  









  public double getHomeX()
  {
    return homeX;
  }
  
  public double getHomeY()
  {
    return homeY;
  }
  
  public double getHomeZ()
  {
    return homeZ;
  }
  
  public double getHomeRange()
  {
    return 16.0D;
  }
}
