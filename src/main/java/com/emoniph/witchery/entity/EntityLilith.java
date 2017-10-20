package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.brewing.potions.PotionBase;
import com.emoniph.witchery.brewing.potions.WitcheryPotions;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.infusion.infusions.symbols.EffectRegistry;
import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.IHandleDT;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.RandomCollection;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityLilith extends EntityMob implements net.minecraft.entity.boss.IBossDisplayData, IRangedAttackMob, IHandleDT
{
  private int attackTimer;
  
  public EntityLilith(World world)
  {
    super(world);
    
    func_70105_a(0.8F, 2.5F);
    field_70178_ae = true;
    func_70661_as().func_75491_a(true);
    func_70661_as().func_75495_e(true);
    field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
    field_70714_bg.func_75776_a(2, new EntityAIArrowAttack(this, 1.0D, 20, 60, 30.0F));
    field_70714_bg.func_75776_a(3, new EntityAIWander(this, 1.0D));
    field_70714_bg.func_75776_a(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
    field_70714_bg.func_75776_a(5, new net.minecraft.entity.ai.EntityAILookIdle(this));
    field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
    field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    field_70728_aV = 60;
  }
  
  protected void func_70088_a()
  {
    super.func_70088_a();
    field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
    field_70180_af.func_75682_a(17, Integer.valueOf(0));
    field_70180_af.func_75682_a(20, new Integer(0));
    field_70180_af.func_75682_a(21, new Integer(0));
  }
  
  protected void func_110147_ax()
  {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(200.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35D);
    func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(50.0D);
    func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D);
  }
  
  public int func_70658_aO()
  {
    return 8;
  }
  

  public void func_70110_aj() {}
  

  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.lilith.name");
  }
  

  boolean isFriendly = false;
  int weaknessTimer;
  
  public boolean func_70650_aV() {
    return !isFriendly;
  }
  
  protected Entity func_70782_k()
  {
    return isFriendly ? null : super.func_70782_k();
  }
  
  protected void func_70629_bd()
  {
    super.func_70629_bd();
  }
  
  public int getInvulnerableStartTicks() {
    return field_70180_af.func_75679_c(20);
  }
  
  public void setInvulnerableStartTicks(int par1) {
    field_70180_af.func_75692_b(20, Integer.valueOf(par1));
  }
  
  public int getLifetime() {
    return field_70180_af.func_75679_c(21);
  }
  
  public void setLifetime(int par1) {
    field_70180_af.func_75692_b(21, Integer.valueOf(par1));
  }
  
  public void setInvulnerableStart() {
    setInvulnerableStartTicks(150);
    func_70606_j(func_110138_aP() / 4.0F);
  }
  
  protected void func_70619_bc()
  {
    if (getInvulnerableStartTicks() > 0) {
      int i = getInvulnerableStartTicks() - 1;
      
      if (i <= 0) {
        field_70170_p.func_82739_e(1013, (int)field_70165_t, (int)field_70163_u, (int)field_70161_v, 0);
      }
      
      setInvulnerableStartTicks(i);
      
      if (field_70173_aa % 10 == 0) {
        func_70691_i(func_110138_aP() * 0.75F / 15.0F);
      }
    } else {
      super.func_70619_bc();
      
      if ((!field_70170_p.field_72995_K) && (!func_70644_a(PotionsRESIZING))) {
        func_70690_d(new PotionEffect(PotionsRESIZING.field_76415_H, 10000, 3, true));
      }
      setLifetime(getLifetime() + 1);
      

      if (field_70173_aa % 20 == 0) {
        if (weaknessTimer > 0) {
          weaknessTimer -= 1;
        }
        if ((!func_70644_a(PotionsCHILLED)) && (!func_70644_a(Potion.field_76437_t)) && (weaknessTimer == 0))
        {
          func_70691_i(5.0F);
        }
        else if (weaknessTimer == 0) {
          func_70691_i(1.0F);
        }
      }
      


      if ((field_70173_aa % 20 == 0) && (field_70170_p.field_73012_v.nextInt(5) == 0) && ((func_70638_az() != null) || (func_110144_aD() != null)))
      {
        if (!field_70170_p.field_72995_K)
        {
          int R = 32;
          double RY = 16.0D;
          double RSQ = 1024.0D;
          
          AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(field_70165_t - 32.0D, field_70163_u - 16.0D, field_70161_v - 32.0D, field_70165_t + 32.0D, field_70163_u + 16.0D, field_70161_v + 32.0D);
          
          List<EntityPlayer> players = field_70170_p.func_72872_a(EntityPlayer.class, bounds);
          for (EntityPlayer player : players) {
            if (player.func_70644_a(Potion.field_76426_n)) {
              player.func_82170_o(field_76426_nfield_76415_H);
            }
            if (field_70170_p.field_73012_v.nextInt(2) == 0) {
              SoundEffect.MOB_ENDERDRAGON_GROWL.playAtPlayer(field_70170_p, player);
              for (int i = 0; i < 3 + field_70146_Z.nextInt(4); i++) {
                EntitySmallFireball fireball = new EntitySmallFireball(field_70170_p, field_70165_t + field_70146_Z.nextDouble() * 4.0D - 2.0D, field_70163_u + field_70146_Z.nextInt(2) + 14.0D, field_70161_v + field_70146_Z.nextDouble() * 4.0D - 2.0D, 0.0D, -0.2D, 0.0D);
                

                field_70170_p.func_72838_d(fireball);
              }
            }
          }
        }
      }
    }
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
  


  public boolean func_70097_a(DamageSource source, float damage)
  {
    boolean immune = false;
    if (immune) {
      return false;
    }
    if ((source.func_76346_g() != null) && 
      ((source.func_76364_f() instanceof EntityLargeFireball)) && ((source.func_76346_g() instanceof EntityPlayer)))
    {
      weaknessTimer = 10;
    }
    
    return super.func_70097_a(source, Math.min(damage, 12.0F));
  }
  

  public float getCapDT(DamageSource source, float damage)
  {
    return 12.0F;
  }
  
  public void func_70014_b(NBTTagCompound nbtRoot)
  {
    super.func_70014_b(nbtRoot);
    nbtRoot.func_74768_a("Invul", getInvulnerableStartTicks());
    nbtRoot.func_74772_a("Lifetime", getLifetime());
    nbtRoot.func_74757_a("Friendly", isFriendly);
  }
  
  public void func_70037_a(NBTTagCompound nbtRoot)
  {
    super.func_70037_a(nbtRoot);
    setInvulnerableStartTicks(nbtRoot.func_74762_e("Invul"));
    setLifetime(nbtRoot.func_74762_e("Lifetime"));
    isFriendly = nbtRoot.func_74767_n("Friendly");
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
    return isFriendly ? null : "witchery:mob.lilith.say";
  }
  
  protected String func_70621_aR()
  {
    return "witchery:mob.lilith.hit";
  }
  
  protected String func_70673_aS()
  {
    return isFriendly ? "witchery:mob.lilith.hit" : "witchery:mob.lilith.death";
  }
  








  protected void func_70628_a(boolean par1, int par2) {}
  








  public void func_70645_a(DamageSource source)
  {
    if (!field_70170_p.field_72995_K) {
      field_70128_L = false;
      ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, this, 1.0D, 2.0D, 16);
      
      func_70606_j(func_110138_aP());
      isFriendly = true;
      List<Potion> effectsToRemove = new ArrayList();
      Collection<PotionEffect> effects = func_70651_bq();
      for (PotionEffect effect : effects) {
        Potion potion = Potion.field_76425_a[effect.func_76456_a()];
        if (PotionBase.isCurable(potion)) {
          effectsToRemove.add(potion);
        }
      }
      for (Potion potion : effectsToRemove) {
        func_82170_o(field_76415_H);
      }
      



      EntityPlayer player = null;
      if ((source != null) && (source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityPlayer))) {
        player = (EntityPlayer)source.func_76346_g();
        if ((field_71093_bK != field_71093_bK) || (field_70128_L) || (player.func_70068_e(this) > 4096.0D)) {
          player = null;
        }
      }
      double distSq;
      if (player == null) {
        int R = 32;
        double RY = 16.0D;
        double RSQ = 1024.0D;
        
        AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(field_70165_t - 32.0D, field_70163_u - 16.0D, field_70161_v - 32.0D, field_70165_t + 32.0D, field_70163_u + 16.0D, field_70161_v + 32.0D);
        
        List<EntityPlayer> players = field_70170_p.func_72872_a(EntityPlayer.class, bounds);
        distSq = 0.0D;
        for (EntityPlayer player2 : players) {
          if (player == null) {
            distSq = func_70068_e(player2);
            player = player2;
          } else {
            double newDist = func_70068_e(player2);
            if (newDist < distSq) {
              distSq = newDist;
              player = player2;
            }
          }
        }
      }
      
      if (player != null) {
        func_70634_a(field_70165_t - 1.0D + field_70146_Z.nextDouble() * 2.0D, field_70163_u + 0.05D, field_70161_v - 1.0D + field_70146_Z.nextDouble() * 2.0D);
        
        ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, this, 1.0D, 2.0D, 16);
        ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "item.witchery:glassgoblet.lilithquestcomplete", new Object[0]);
        
        SoundEffect.WITCHERY_MOB_LILITH_TALK.playAt(this);
      } else {
        func_70106_y();
      }
    }
  }
  
  protected Item func_146068_u()
  {
    return null;
  }
  
  protected boolean func_70692_ba()
  {
    return false;
  }
  
  protected boolean func_70085_c(EntityPlayer player)
  {
    if ((!field_70170_p.field_72995_K) && 
      (isFriendly)) {
      ItemStack stack = player.func_70694_bm();
      SoundEffect.WITCHERY_MOB_LILITH_TALK.playAt(this, 1.0F);
      boolean vanish = false;
      if (stack == null) {
        ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "item.witchery:glassgoblet.lilithquestcomplete2", new Object[0]);
      }
      else if (stack.func_77973_b() == ItemsBLOOD_GOBLET) {
        if (!ExtendedPlayer.get(player).isVampire()) {
          ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "item.witchery:glassgoblet.lilithquestcompletelife", new Object[0]);
          
          player.func_70062_b(0, null);
          ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, field_70170_p, field_70165_t, field_70163_u + field_70131_O * 0.85D, field_70161_v, 0.8D, 0.8D, 16);
          
          ItemsBLOOD_GOBLET.setBloodOwner(stack, com.emoniph.witchery.item.ItemGlassGoblet.BloodSource.LILITH);
          field_70170_p.func_72838_d(new EntityItem(field_70170_p, field_70165_t, field_70163_u, field_70161_v, stack));
          
          ExtendedPlayer.get(player).setHumanBlood(0);
          vanish = true;
        } else {
          ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "item.witchery:glassgoblet.lilithquestcompletelifefail", new Object[0]);
        }
      }
      else if (stack.func_77973_b() == ItemsSEEDS_GARLIC) {
        if (ExtendedPlayer.get(player).isVampire()) {
          ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "item.witchery:glassgoblet.lilithquestcompletecure", new Object[0]);
          
          player.func_70062_b(0, null);
          ExtendedPlayer.get(player).setVampireLevel(0);
          ParticleEffect.REDDUST.send(SoundEffect.RANDOM_FIZZ, player, 1.0D, 1.5D, 16);
          vanish = true;
        } else {
          ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "item.witchery:glassgoblet.lilithquestcompletecurefail", new Object[0]);
        }
      }
      else if ((stack.func_77973_b() == Item.func_150898_a(net.minecraft.init.Blocks.field_150328_O)) && (stack.func_77960_j() == 0)) {
        ExtendedPlayer playerEx = ExtendedPlayer.get(player);
        if ((playerEx.getVampireLevel() == 6) && (playerEx.canIncreaseVampireLevel())) {
          ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "item.witchery:glassgoblet.lilithquestcompletebatflight", new Object[0]);
          
          player.func_70062_b(0, null);
          playerEx.increaseVampireLevel();
          ParticleEffect.REDDUST.send(SoundEffect.RANDOM_FIZZ, player, 1.0D, 1.5D, 16);
          vanish = true;
        } else {
          ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "item.witchery:glassgoblet.lilithquestcompletebatflightfail", new Object[0]);
        }
      }
      else {
        List enchants = EnchantmentHelper.func_77513_b(field_70170_p.field_73012_v, stack, 40);
        if ((enchants != null) && (enchants.size() > 0)) {
          ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "item.witchery:glassgoblet.lilithquestcompletemagic", new Object[0]);
          
          player.func_70062_b(0, null);
          addEnchantmentsFromList(stack, enchants);
          if (stack.func_77984_f()) {
            stack.func_77964_b(0);
          }
          field_70170_p.func_72838_d(new EntityItem(field_70170_p, field_70165_t, field_70163_u, field_70161_v, stack));
          

          vanish = true;
        } else {
          ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "item.witchery:glassgoblet.lilithquestcomplete2", new Object[0]);
        }
      }
      

      if (vanish) {
        ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, this, 1.0D, 2.0D, 16);
        func_70106_y();
      }
      return true;
    }
    
    return false;
  }
  
  private static void addEnchantmentsFromList(ItemStack stack, List list) {
    boolean flag = stack.func_77973_b() == Items.field_151122_aG;
    
    if (flag) {
      stack.func_150996_a(Items.field_151134_bR);
    }
    
    Map enchants = EnchantmentHelper.func_82781_a(stack);
    
    if (list != null) {
      Iterator iterator = list.iterator();
      
      while (iterator.hasNext()) {
        EnchantmentData enchantmentdata = (EnchantmentData)iterator.next();
        if (flag) {
          Items.field_151134_bR.func_92115_a(stack, enchantmentdata);
        }
        else {
          if (stack.func_77978_p() == null) {
            stack.func_77982_d(new NBTTagCompound());
          }
          
          if (!stack.func_77978_p().func_150297_b("ench", 9)) {
            stack.func_77978_p().func_74782_a("ench", new NBTTagList());
          }
          
          NBTTagList nbttaglist = stack.func_77978_p().func_150295_c("ench", 10);
          
          boolean addEnchant = true;
          
          for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
            NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
            
            if (nbttagcompound.func_74765_d("id") == field_76302_b.field_77352_x) {
              if (nbttagcompound.func_74765_d("lvl") < field_76303_c) {
                nbttagcompound.func_74777_a("lvl", (short)field_76303_c);
              }
              
              addEnchant = false;
              break;
            }
          }
          
          if (addEnchant)
          {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74777_a("id", (short)field_76302_b.field_77352_x);
            nbttagcompound.func_74777_a("lvl", (short)(byte)field_76303_c);
            nbttaglist.func_74742_a(nbttagcompound);
          }
          
          stack.func_77978_p().func_74782_a("ench", nbttaglist);
        }
      }
    }
  }
  

  private static final RandomCollection<SymbolEffect> SPELLS = ;
  
  private static RandomCollection<SymbolEffect> createSpells() {
    RandomCollection<SymbolEffect> spells = new RandomCollection();
    
    EffectRegistry.instance();spells.add(1.0D, EffectRegistry.Ignianima);
    EffectRegistry.instance();spells.add(5.0D, EffectRegistry.Flipendo);
    EffectRegistry.instance();spells.add(1.0D, EffectRegistry.Impedimenta);
    EffectRegistry.instance();spells.add(1.0D, EffectRegistry.Confundus);
    EffectRegistry.instance();spells.add(5.0D, EffectRegistry.Attraho);
    
    return spells;
  }
  
  public void func_82196_d(EntityLivingBase targetEntity, float par2)
  {
    if (field_70170_p.field_73012_v.nextBoolean()) {
      attackTimer = 10;
      field_70170_p.func_72960_a(this, (byte)4);
      
      double d0 = field_70165_t - field_70165_t;
      double d1 = field_70121_D.field_72338_b + field_70131_O / 2.0F - (field_70163_u + field_70131_O / 2.0F);
      
      double d2 = field_70161_v - field_70161_v;
      
      float f1 = net.minecraft.util.MathHelper.func_76129_c(par2) * 0.5F;
      
      if (!field_70170_p.field_72995_K) {
        if (field_70170_p.field_73012_v.nextInt(3) == 0) {
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
        } else {
          field_70170_p.func_72889_a((EntityPlayer)null, 1009, (int)field_70165_t, (int)field_70163_u, (int)field_70161_v, 0);
          
          int count = field_70146_Z.nextInt(10) == 0 ? 9 : 3;
          
          EntitySpellEffect effect = new EntitySpellEffect(field_70170_p, this, d0 + field_70146_Z.nextGaussian() * f1, d1, d2 + field_70146_Z.nextGaussian() * f1, (SymbolEffect)SPELLS.next(), 1);
          

          double d8 = 1.0D;
          field_70165_t = field_70165_t;
          field_70163_u = (field_70163_u + field_70131_O / 2.0F);
          field_70161_v = field_70161_v;
          field_70170_p.func_72838_d(effect);
          effect.setShooter(this);
        }
      }
    }
  }
}
