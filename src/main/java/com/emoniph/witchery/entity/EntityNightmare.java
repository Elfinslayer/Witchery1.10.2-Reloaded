package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.command.IEntitySelector;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityNightmare extends EntityMob implements IEntitySelector
{
  private int attackTimer;
  private int defenseTimer;
  
  public EntityNightmare(World par1World)
  {
    super(par1World);
    
    field_70178_ae = true;
    
    func_70105_a(0.6F, 1.8F);
    
    func_70661_as().func_75491_a(true);
    func_70661_as().func_75495_e(true);
    field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
    field_70714_bg.func_75776_a(2, new EntityAIBreakDoor(this));
    field_70714_bg.func_75776_a(3, new EntityAIAttackOnCollide(this, 1.0D, true));
    field_70714_bg.func_75776_a(4, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
    field_70714_bg.func_75776_a(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
    field_70714_bg.func_75776_a(6, new net.minecraft.entity.ai.EntityAIMoveThroughVillage(this, 1.0D, false));
    field_70714_bg.func_75776_a(7, new net.minecraft.entity.ai.EntityAIWander(this, 1.0D));
    field_70714_bg.func_75776_a(8, new net.minecraft.entity.ai.EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    field_70714_bg.func_75776_a(8, new net.minecraft.entity.ai.EntityAILookIdle(this));
    field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
    field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false, true, this));
    field_70728_aV = 25;
  }
  
  public boolean func_82704_a(Entity entity)
  {
    if ((entity instanceof EntityPlayer)) {
      EntityPlayer player = (EntityPlayer)entity;
      String victim = getVictimName();
      return (victim == null) || (victim.isEmpty()) || (player.func_70005_c_().equalsIgnoreCase(victim));
    }
    return false;
  }
  

  public int func_70627_aG()
  {
    return super.func_70627_aG() * 2;
  }
  

  protected void func_70088_a()
  {
    super.func_70088_a();
    field_70180_af.func_75682_a(17, "");
    field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
    field_70180_af.func_75682_a(21, Byte.valueOf((byte)0));
  }
  


  public void func_70110_aj() {}
  

  protected void func_70069_a(float par1) {}
  

  public boolean isScreaming()
  {
    return field_70180_af.func_75683_a(16) > 0;
  }
  
  public void setScreaming(boolean par1)
  {
    field_70180_af.func_75692_b(16, Byte.valueOf((byte)(par1 ? 1 : 0)));
  }
  
  public boolean isDefended()
  {
    return field_70180_af.func_75683_a(21) > 0;
  }
  
  public void setDefended(boolean par1)
  {
    field_70180_af.func_75692_b(21, Byte.valueOf((byte)(par1 ? 1 : 0)));
  }
  
  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.nightmare.name");
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
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
    func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D);
  }
  
  protected void func_70626_be()
  {
    super.func_70626_be();
  }
  
  protected int func_70682_h(int par1)
  {
    return par1;
  }
  
  protected void func_82167_n(Entity par1Entity)
  {
    super.func_82167_n(par1Entity);
  }
  
  public void func_70014_b(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70014_b(par1NBTTagCompound);
    
    if (getVictimName() == null)
    {
      par1NBTTagCompound.func_74778_a("Victim", "");
    }
    else
    {
      par1NBTTagCompound.func_74778_a("Victim", getVictimName());
    }
  }
  
  public void func_70037_a(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70037_a(par1NBTTagCompound);
    String s = par1NBTTagCompound.func_74779_i("Victim");
    
    if (s.length() > 0)
    {
      setVictim(s);
    }
  }
  
  public String getVictimName()
  {
    String s = field_70180_af.func_75681_e(17);
    return s != null ? s : "";
  }
  
  public void setVictim(String par1Str)
  {
    field_70180_af.func_75692_b(17, par1Str);
  }
  
  public void func_70636_d()
  {
    super.func_70636_d();
    
    if (!field_70170_p.field_72995_K) {
      if ((defenseTimer > 0) && 
        (--defenseTimer == 0)) {
        setDefended(false);
      }
      

      if (((!field_70128_L) && (!getVictimName().isEmpty()) && ((func_70638_az() == null) || (func_70638_azfield_70128_L) || (func_70068_e(func_70638_az()) > 256.0D))) || ((field_70170_p.field_73012_v.nextInt(5) == 0) && ((func_70638_az() instanceof EntityPlayer)) && (WorldProviderDreamWorld.getPlayerHasNightmare((EntityPlayer)func_70638_az()) == 0) && (!isWakingNightmare((EntityPlayer)func_70638_az()))))
      {

        ParticleEffect.EXPLODE.send(SoundEffect.NONE, this, 1.0D, 2.0D, 16);
        func_70106_y();
      }
    }
    
    if (attackTimer > 0) {
      attackTimer -= 1;
    }
  }
  
  private boolean isWakingNightmare(EntityPlayer player) {
    NBTTagCompound nbtTag = Infusion.getNBT(player);
    if ((nbtTag != null) && (nbtTag.func_74764_b("witcheryWakingNightmare"))) {
      return nbtTag.func_74762_e("witcheryWakingNightmare") > 0;
    }
    return player.func_70644_a(PotionsWAKING_NIGHTMARE);
  }
  

  @SideOnly(Side.CLIENT)
  public void func_70103_a(byte par1)
  {
    if (par1 == 4) {
      attackTimer = 15;
    } else {
      super.func_70103_a(par1);
    }
  }
  
  public boolean func_70652_k(Entity entity)
  {
    attackTimer = 15;
    field_70170_p.func_72960_a(this, (byte)4);
    if ((entity != null) && ((entity instanceof EntityPlayer))) {
      EntityPlayer player = (EntityPlayer)entity;
      if (!findInInventory(field_71071_by, ItemsGENERIC.itemCharmOfDisruptedDreams)) {
        int index = field_70170_p.field_73012_v.nextInt(field_71071_by.field_70460_b.length);
        if (field_71071_by.field_70460_b[index] != null) {
          Infusion.dropEntityItemWithRandomChoice(player, field_71071_by.field_70460_b[index], true);
          field_71071_by.field_70460_b[index] = null;
        }
      }
    }
    
    float f = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
    if (field_71093_bK != instancedimensionDreamID) {
      f = 0.5F;
    }
    int i = 0;
    
    if ((entity instanceof EntityLivingBase))
    {
      f += EnchantmentHelper.func_77512_a(this, (EntityLivingBase)entity);
      i += EnchantmentHelper.func_77507_b(this, (EntityLivingBase)entity);
    }
    
    boolean flag = entity.func_70097_a(DamageSource.func_76358_a(this), f);
    
    if (flag)
    {
      if (i > 0)
      {
        entity.func_70024_g(-MathHelper.func_76126_a(field_70177_z * 3.1415927F / 180.0F) * i * 0.5F, 0.1D, MathHelper.func_76134_b(field_70177_z * 3.1415927F / 180.0F) * i * 0.5F);
        field_70159_w *= 0.6D;
        field_70179_y *= 0.6D;
      }
      
      int j = EnchantmentHelper.func_90036_a(this);
      
      if (j > 0)
      {
        entity.func_70015_d(j * 4);
      }
    }
    
    return flag;
  }
  
  private boolean findInInventory(InventoryPlayer inventory, ItemGeneral.SubItem item) {
    for (int i = 0; i < field_70462_a.length; i++) {
      ItemStack stack = field_70462_a[i];
      if ((stack != null) && (item.isMatch(stack))) {
        return true;
      }
    }
    return false;
  }
  
  public boolean func_70097_a(DamageSource source, float damage)
  {
    if (isDefended()) {
      return false;
    }
    boolean weakeningWeapon = false;
    if (((source instanceof EntityDamageSource)) && (((EntityDamageSource)source).func_76346_g() != null) && ((((EntityDamageSource)source).func_76346_g() instanceof EntityLivingBase))) {
      EntityLivingBase living = (EntityLivingBase)((EntityDamageSource)source).func_76346_g();
      if ((living.func_70694_bm() != null) && (living.func_70694_bm().func_77973_b() == ItemsHUNTSMANS_SPEAR)) {
        weakeningWeapon = true;
      }
    }
    if ((!field_70170_p.field_72995_K) && (field_70170_p.func_147439_a(MathHelper.func_76128_c(field_70165_t), MathHelper.func_76128_c(field_70163_u), MathHelper.func_76128_c(field_70161_v)) != BlocksFLOWING_SPIRIT))
    {

      defenseTimer = (weakeningWeapon ? 30 : field_71093_bK == instancedimensionDreamID ? 80 : weakeningWeapon ? 40 : 40);
      setDefended(true);
    }
    return super.func_70097_a(source, Math.min(damage, 15.0F));
  }
  

  @SideOnly(Side.CLIENT)
  public int getAttackTimer()
  {
    return attackTimer;
  }
  
  protected String func_70639_aQ()
  {
    return "witchery:mob.nightmare.nightmare_live";
  }
  
  protected String func_70621_aR()
  {
    return "witchery:mob.nightmare.nightmare_dead";
  }
  
  protected String func_70673_aS()
  {
    return "witchery:mob.nightmare.nightmare_hit";
  }
  
  protected void func_70628_a(boolean par1, int par2)
  {
    if (field_71093_bK == instancedimensionDreamID) {
      int chance = field_70146_Z.nextInt(Math.max(10 - par2, 5));
      int quantity = (par2 > 0) && (chance == 0) ? 2 : 1;
      func_70099_a(ItemsGENERIC.itemMellifluousHunger.createStack(quantity), 0.0F);
    }
  }
  
  public void func_70645_a(DamageSource source)
  {
    if ((!field_70170_p.field_72995_K) && (source != null) && (source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityPlayer))) {
      EntityPlayer player = (EntityPlayer)source.func_76346_g();
      String victim = getVictimName();
      if ((victim != null) && (!victim.isEmpty()) && (player.func_70005_c_().equalsIgnoreCase(victim)) && (field_71093_bK == instancedimensionDreamID)) {
        WorldProviderDreamWorld.setPlayerLastNightmareKillNow(player);
      }
    }
    super.func_70645_a(source);
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
