package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockMirror;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.infusion.infusions.symbols.EffectRegistry;
import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
import com.emoniph.witchery.integration.ModHookManager;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.CreatureUtil;
import com.emoniph.witchery.util.IHandleDT;
import com.emoniph.witchery.util.RandomCollection;
import com.emoniph.witchery.util.TransformCreature;
import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityReflection extends EntityMob implements net.minecraft.entity.boss.IBossDisplayData, IRangedAttackMob, IHandleDT
{
  private int attackTimer;
  private boolean freeSpawn;
  private boolean isVampire;
  private int livingTicks = -1;
  private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
  private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.2D, false);
  
  public EntityReflection(World world)
  {
    super(world);
    
    func_70105_a(0.6F, 1.8F);
    field_70178_ae = true;
    func_70661_as().func_75491_a(true);
    func_70661_as().func_75495_e(true);
    field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
    field_70714_bg.func_75776_a(3, new EntityAIWander(this, 1.0D));
    field_70714_bg.func_75776_a(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
    field_70714_bg.func_75776_a(5, new net.minecraft.entity.ai.EntityAILookIdle(this));
    
    field_70715_bh.func_75776_a(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    field_70715_bh.func_75776_a(2, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
    field_70728_aV = 50;
  }
  
  protected void func_70088_a()
  {
    super.func_70088_a();
    field_70180_af.func_75682_a(17, "");
    field_70180_af.func_75682_a(18, Byte.valueOf((byte)0));
  }
  

  private String owner = "";
  
  public String getOwnerSkin() {
    return field_70180_af.func_75681_e(17);
  }
  
  public String getOwnerName() {
    return owner;
  }
  
  public void setOwnerSkin(String skinName) {
    field_70180_af.func_75692_b(17, skinName);
  }
  
  public void setOwner(String par1Str) {
    func_110163_bv();
    owner = par1Str;
  }
  
  public EntityPlayer getOwnerEntity() {
    return field_70170_p.func_72924_a(getOwnerName());
  }
  
  public void setModel(int model) {
    field_70180_af.func_75692_b(18, Byte.valueOf((byte)model));
  }
  
  public int getModel() {
    return field_70180_af.func_75683_a(18);
  }
  
  public void setLifetime(int ticks) {
    livingTicks = ticks;
  }
  
  protected void func_110147_ax()
  {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35D);
    func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(50.0D);
    func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D);
  }
  

  public void func_70110_aj() {}
  

  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    String owner = getOwnerName();
    return (owner == null) || (owner.isEmpty()) ? net.minecraft.util.StatCollector.func_74838_a("entity.witchery.reflection.name") : owner;
  }
  

  public boolean func_70650_aV()
  {
    return true;
  }
  
  protected void func_70629_bd()
  {
    super.func_70629_bd();
  }
  
  protected int func_70682_h(int par1)
  {
    return par1;
  }
  
  protected void func_82167_n(Entity par1Entity)
  {
    super.func_82167_n(par1Entity);
  }
  
  private static enum Task {
    NONE,  MELEE,  RANGED;
    
    private Task() {} }
  private Task task = Task.NONE;
  
  public void func_70636_d()
  {
    super.func_70636_d();
    
    if (attackTimer > 0) {
      attackTimer -= 1;
    }
    
    if ((!field_70170_p.field_72995_K) && (field_70173_aa % 30 == 1))
    {
      if ((!freeSpawn) && (field_71093_bK != instancedimensionMirrorID)) {
        func_70106_y();
        return;
      }
      
      if ((livingTicks > -1) && (--livingTicks == 0)) {
        func_70106_y();
        return;
      }
      
      double R = 10.0D;
      double RY = 8.0D;
      AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(field_70165_t - R, field_70163_u - RY, field_70161_v - R, field_70165_t + R, field_70163_u + RY, field_70161_v + R);
      
      List<EntityPlayer> players = field_70170_p.func_72872_a(EntityPlayer.class, bounds);
      
      EntityPlayer ownerEntity = getOwnerEntity();
      boolean ownerFound = false;
      
      EntityPlayer closest = null;
      double distance = Double.MAX_VALUE;
      for (EntityPlayer player : players) {
        double newDistance = player.func_70068_e(this);
        if ((closest == null) || (newDistance < distance)) {
          closest = player;
          distance = newDistance;
        }
        if (ownerEntity == player) {
          ownerFound = true;
        }
      }
      
      if ((ownerEntity == null) || (!ownerFound)) {
        if (closest != null) {
          setOwner(closest.func_70005_c_());
        } else {
          setOwner("");
        }
      }
      


      boolean resetGear = true;
      String skinName = getOwnerName();
      if (!getOwnerName().isEmpty()) {
        EntityPlayer owner = (ownerEntity == null) || (!ownerFound) ? getOwnerEntity() : ownerEntity;
        if (owner != null)
        {


          for (int slot = 1; slot <= 4; slot++) {
            ItemStack stack = owner.func_71124_b(slot);
            if (stack != null) {
              stack = stack.func_77946_l();
            }
            func_70062_b(slot, stack);
          }
          


          ItemStack bestWeapon = null;
          double bestDamage = 0.0D;
          for (int hot = 0; hot < 9; hot++) {
            ItemStack stack = field_71071_by.func_70301_a(hot);
            if (stack != null) {
              Multimap modifierMap = stack.func_111283_C();
              Iterator itr = modifierMap.get(SharedMonsterAttributes.field_111264_e.func_111108_a()).iterator();
              
              double damage = 0.0D;
              while (itr.hasNext()) {
                AttributeModifier modifier = (AttributeModifier)itr.next();
                if (modifier.func_111169_c() == 0) {
                  damage += modifier.func_111164_d();
                }
              }
              
              if (damage > bestDamage) {
                bestWeapon = stack;
                bestDamage = damage;
              }
            }
          }
          

          ExtendedPlayer playerEx = ExtendedPlayer.get(owner);
          if (playerEx != null) {
            setModel(playerEx.getCreatureType() == TransformCreature.WOLFMAN ? 1 : 0);
            isVampire = playerEx.isVampire();
            if (playerEx.getCreatureType() == TransformCreature.PLAYER) {
              skinName = playerEx.getOtherPlayerSkin();
            }
          }
          


          ItemStack stack = bestWeapon != null ? bestWeapon : owner.func_71124_b(0);
          if (stack != null) {
            stack = stack.func_77946_l();
            Witchery.modHooks.makeItemModProof(stack);
          }
          
          if (getModel() == 1) {
            stack = null;
            func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(6.0D);
          } else {
            func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D);
          }
          func_70062_b(0, stack);
          
          resetGear = false;
          
          if (field_70173_aa % 60 == 1)
          {
            func_70674_bp();
            Iterator effects = owner.func_70651_bq().iterator();
            while (effects.hasNext()) {
              PotionEffect effect = (PotionEffect)effects.next();
              func_70690_d(new PotionEffect(effect));
            }
          }
        }
      }
      
      if (resetGear) {
        for (int slot = 0; slot <= 4; slot++) {
          func_70062_b(slot, null);
        }
      }
      
      setOwnerSkin(skinName);
      

      ItemStack held = func_70694_bm();
      if (held != null) {
        if (held.func_77973_b() == ItemsMYSTIC_BRANCH) {
          if (task == Task.MELEE) {
            field_70714_bg.func_85156_a(aiAttackOnCollide);
          }
          field_70714_bg.func_75776_a(2, aiArrowAttack);
          task = Task.RANGED;
        }
        else if ((held.func_77973_b() == ItemsCROSSBOW_PISTOL) || ((held.func_77973_b() instanceof ItemBow)))
        {
          if (task == Task.MELEE) {
            field_70714_bg.func_85156_a(aiAttackOnCollide);
          }
          field_70714_bg.func_75776_a(2, aiArrowAttack);
          task = Task.RANGED;
        } else {
          if (task == Task.RANGED) {
            field_70714_bg.func_85156_a(aiArrowAttack);
          }
          field_70714_bg.func_75776_a(2, aiAttackOnCollide);
          task = Task.MELEE;
        }
      } else {
        if (task == Task.RANGED) {
          field_70714_bg.func_85156_a(aiArrowAttack);
        }
        field_70714_bg.func_75776_a(2, aiAttackOnCollide);
        task = Task.MELEE;
      }
      
      if ((func_70089_S()) && (func_70638_az() != null) && (func_70661_as().func_75500_f()) && (func_70635_at().func_75522_a(func_70638_az()))) {
        EffectRegistry.instance();castSpell(func_70638_az(), 1.0F, EffectRegistry.Attraho);
      }
    }
    
    if ((!field_70170_p.field_72995_K) && (field_70170_p.field_73012_v.nextDouble() < 0.05D) && (func_70638_az() != null) && ((func_70638_azfield_70160_al) || (((func_70638_az() instanceof EntityPlayer)) && (func_70638_azfield_71075_bZ.field_75100_b))) && (!func_70638_az().func_70644_a(Potion.field_76421_d)))
    {


      func_70638_az().func_70690_d(new PotionEffect(field_76421_dfield_76415_H, 200, 5));
    }
  }
  

  public void func_70645_a(DamageSource p_70645_1_)
  {
    super.func_70645_a(p_70645_1_);
    BlocksMIRROR.demonSlain(field_70170_p, field_70165_t, field_70163_u, field_70161_v);
  }
  
  public boolean func_70097_a(DamageSource source, float damage)
  {
    return super.func_70097_a(source, Math.min(damage, 6.0F));
  }
  
  public float getCapDT(DamageSource source, float damage)
  {
    return 2.0F;
  }
  
  public boolean func_70686_a(Class par1Class)
  {
    return super.func_70686_a(par1Class);
  }
  
  public void func_70014_b(NBTTagCompound nbtRoot)
  {
    super.func_70014_b(nbtRoot);
    nbtRoot.func_74778_a("Owner", getOwnerName());
    nbtRoot.func_74778_a("OwnerSkin", getOwnerSkin());
    nbtRoot.func_74768_a("Model", getModel());
    nbtRoot.func_74757_a("FreeSpawn", freeSpawn);
    nbtRoot.func_74757_a("Vampire", isVampire);
    nbtRoot.func_74768_a("LivingTicks", livingTicks);
  }
  

  public void func_70037_a(NBTTagCompound nbtRoot)
  {
    super.func_70037_a(nbtRoot);
    setOwner(nbtRoot.func_74779_i("Owner"));
    setOwnerSkin(nbtRoot.func_74779_i("OwnerSkin"));
    freeSpawn = nbtRoot.func_74767_n("FreeSpawn");
    livingTicks = nbtRoot.func_74762_e("LivingTicks");
    isVampire = nbtRoot.func_74767_n("Vampire");
    setModel(nbtRoot.func_74762_e("Model"));
  }
  

  public boolean func_70652_k(Entity par1Entity)
  {
    attackTimer = 10;
    
    boolean flag = super.func_70652_k(par1Entity);
    





    return flag;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_70103_a(byte par1)
  {
    if (par1 == 4) {
      attackTimer = 10;
    }
    else {
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
    return "witchery:mob.reflection.say";
  }
  
  protected String func_70621_aR()
  {
    return "witchery:mob.reflection.hit";
  }
  
  protected String func_70673_aS()
  {
    return "witchery:mob.reflection.death";
  }
  
  protected void func_145780_a(int par1, int par2, int par3, Block par4)
  {
    super.func_145780_a(par1, par2, par3, par4);
  }
  
  protected void func_70628_a(boolean par1, int par2)
  {
    func_70099_a(ItemsGENERIC.itemDemonHeart.createStack(), 0.0F);
  }
  


  protected void func_82160_b(boolean p_82160_1_, int p_82160_2_) {}
  

  protected Item func_146068_u()
  {
    return null;
  }
  
  protected boolean func_70692_ba()
  {
    return false;
  }
  
  private static final RandomCollection<SymbolEffect> SPELLS = ;
  
  private static RandomCollection<SymbolEffect> createSpells() {
    RandomCollection<SymbolEffect> spells = new RandomCollection();
    
    EffectRegistry.instance();spells.add(14.0D, EffectRegistry.Ignianima);
    EffectRegistry.instance();spells.add(2.0D, EffectRegistry.Expelliarmus);
    EffectRegistry.instance();spells.add(2.0D, EffectRegistry.Flipendo);
    EffectRegistry.instance();spells.add(2.0D, EffectRegistry.Impedimenta);
    EffectRegistry.instance();spells.add(1.0D, EffectRegistry.Confundus);
    
    return spells;
  }
  

  public void func_82196_d(EntityLivingBase targetEntity, float par2)
  {
    ItemStack held = func_70694_bm();
    if (held == null) {
      return;
    }
    attackTimer = 10;
    field_70170_p.func_72960_a(this, (byte)4);
    
    if (held.func_77973_b() == ItemsMYSTIC_BRANCH) {
      if (field_70170_p.field_73012_v.nextBoolean()) {
        castSpell(targetEntity, par2, (SymbolEffect)SPELLS.next());
      }
    } else if (held.func_77973_b() == ItemsCROSSBOW_PISTOL) {
      EntityBolt entityarrow = new EntityBolt(field_70170_p, this, targetEntity, 1.6F, 14 - field_70170_p.field_73013_u.func_151525_a() * 4);
      
      int i = EnchantmentHelper.func_77506_a(field_77345_tfield_77352_x, func_70694_bm());
      int j = EnchantmentHelper.func_77506_a(field_77344_ufield_77352_x, func_70694_bm());
      entityarrow.setDamage(par2 * 2.0F + field_70146_Z.nextGaussian() * 0.25D + field_70170_p.field_73013_u.func_151525_a() * 0.11F);
      

      if (i > 0) {
        entityarrow.setDamage(entityarrow.getDamage() + i * 0.5D + 0.5D);
      }
      
      if (j > 0) {
        entityarrow.setKnockbackStrength(j);
      }
      
      if ((EnchantmentHelper.func_77506_a(field_77343_vfield_77352_x, func_70694_bm()) > 0) || ((CreatureUtil.isVampire(func_70638_az())) && (field_70170_p.field_73012_v.nextInt(3) == 0)))
      {
        entityarrow.func_70015_d(100);
      }
      
      if (func_70638_az() != null) {
        if (CreatureUtil.isWerewolf(func_70638_az())) {
          entityarrow.setBoltType(4);
        } else if (CreatureUtil.isUndead(func_70638_az())) {
          entityarrow.setBoltType(3);
        } else if (field_70170_p.field_73012_v.nextInt(4) == 0) {
          entityarrow.setBoltType(2);
        }
      }
      
      func_85030_a("random.bow", 1.0F, 1.0F / (func_70681_au().nextFloat() * 0.4F + 0.8F));
      field_70170_p.func_72838_d(entityarrow);
    } else {
      EntityArrow entityarrow = new EntityArrow(field_70170_p, this, targetEntity, 1.6F, 14 - field_70170_p.field_73013_u.func_151525_a() * 3);
      
      int i = EnchantmentHelper.func_77506_a(field_77345_tfield_77352_x, func_70694_bm());
      int j = EnchantmentHelper.func_77506_a(field_77344_ufield_77352_x, func_70694_bm());
      entityarrow.func_70239_b(par2 * 2.0F + field_70146_Z.nextGaussian() * 0.25D + field_70170_p.field_73013_u.func_151525_a() * 0.11F);
      

      if (i > 0) {
        entityarrow.func_70239_b(entityarrow.func_70242_d() + i * 0.5D + 0.5D);
      }
      
      if (j > 0) {
        entityarrow.func_70240_a(j);
      }
      
      if (EnchantmentHelper.func_77506_a(field_77343_vfield_77352_x, func_70694_bm()) > 0) {
        entityarrow.func_70015_d(100);
      }
      
      func_85030_a("random.bow", 1.0F, 1.0F / (func_70681_au().nextFloat() * 0.4F + 0.8F));
      field_70170_p.func_72838_d(entityarrow);
    }
  }
  
  private void castSpell(EntityLivingBase targetEntity, float par2, SymbolEffect spell) {
    double d0 = field_70165_t - field_70165_t;
    double d1 = field_70121_D.field_72338_b + field_70131_O / 2.0F - (field_70163_u + field_70131_O / 2.0F);
    
    double d2 = field_70161_v - field_70161_v;
    
    float f1 = net.minecraft.util.MathHelper.func_76129_c(par2) * 0.5F;
    
    if (!field_70170_p.field_72995_K) {
      field_70170_p.func_72889_a((EntityPlayer)null, 1009, (int)field_70165_t, (int)field_70163_u, (int)field_70161_v, 0);
      
      int count = field_70146_Z.nextInt(10) == 0 ? 9 : 3;
      
      EntitySpellEffect effect = new EntitySpellEffect(field_70170_p, this, d0 + field_70146_Z.nextGaussian() * f1, d1, d2 + field_70146_Z.nextGaussian() * f1, spell, 1);
      

      double d8 = 1.0D;
      field_70165_t = field_70165_t;
      field_70163_u = (field_70163_u + field_70131_O / 2.0F);
      field_70161_v = field_70161_v;
      field_70170_p.func_72838_d(effect);
      effect.setShooter(this);
    }
  }
  

  @SideOnly(Side.CLIENT)
  private ThreadDownloadImageData downloadImageSkin;
  
  @SideOnly(Side.CLIENT)
  private ResourceLocation locationSkin;
  private String lastSkinOwner;
  @SideOnly(Side.CLIENT)
  public ResourceLocation getLocationSkin()
  {
    if ((locationSkin == null) || (!lastSkinOwner.equals(getOwnerName()))) {
      setupCustomSkin();
    }
    if (locationSkin != null) {
      return locationSkin;
    }
    return null;
  }
  
  @SideOnly(Side.CLIENT)
  private void setupCustomSkin()
  {
    String ownerName = getOwnerSkin();
    if ((ownerName != null) && (!ownerName.isEmpty())) {
      locationSkin = net.minecraft.client.entity.AbstractClientPlayer.func_110311_f(ownerName);
      downloadImageSkin = getDownloadImageSkin(locationSkin, ownerName);
      lastSkinOwner = ownerName;
    } else {
      locationSkin = null;
      downloadImageSkin = null;
      lastSkinOwner = "";
    }
  }
  
  @SideOnly(Side.CLIENT)
  public static ThreadDownloadImageData getDownloadImageSkin(ResourceLocation location, String name) {
    TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
    Object object = texturemanager.func_110581_b(location);
    
    if (object == null) {
      object = new ThreadDownloadImageData((File)null, String.format("http://skins.minecraft.net/MinecraftSkins/%s.png", new Object[] { net.minecraft.util.StringUtils.func_76338_a(name) }), com.emoniph.witchery.client.renderer.RenderReflection.SKIN, new ImageBufferDownload());
      


      texturemanager.func_110579_a(location, (ITextureObject)object);
    }
    
    return (ThreadDownloadImageData)object;
  }
  
  public IEntityLivingData func_110161_a(IEntityLivingData data)
  {
    freeSpawn = true;
    return super.func_110161_a(data);
  }
  
  public boolean isVampire() {
    return isVampire;
  }
}
