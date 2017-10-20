package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.entity.ai.EntityAIDimensionalFollowOwner;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.familiar.IFamiliar;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TameableUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityToad extends EntityTameable implements IFamiliar
{
  private int timeToLive = -1;
  private boolean poisoned = false;
  
  public EntityToad(World par1World) {
    super(par1World);
    func_70105_a(0.8F, 0.8F);
    func_70661_as().func_75495_e(true);
    func_70661_as().func_75491_a(true);
    field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
    field_70714_bg.func_75776_a(2, new com.emoniph.witchery.entity.ai.EntityAISitAndStay(this));
    



    field_70714_bg.func_75776_a(3, new EntityAIDimensionalFollowOwner(this, 1.0D, 10.0F, 2.0F));
    


    field_70714_bg.func_75776_a(4, new EntityAITempt(this, 1.25D, Items.field_151078_bh, false));
    field_70714_bg.func_75776_a(6, new EntityAIMate(this, 1.0D));
    field_70714_bg.func_75776_a(7, new EntityAIWander(this, 1.0D));
    field_70714_bg.func_75776_a(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    field_70714_bg.func_75776_a(9, new EntityAILookIdle(this));
    func_70903_f(false);
  }
  
  public void setTimeToLive(int i, boolean poisoned) {
    timeToLive = i;
    this.poisoned = poisoned;
  }
  
  public boolean isTemp() {
    return timeToLive != -1;
  }
  
  protected void func_110147_ax()
  {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.20000001192092895D);
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
  }
  
  public int func_70658_aO()
  {
    return super.func_70658_aO() + (isFamiliar() ? 5 : 0);
  }
  
  public int func_70627_aG()
  {
    return super.func_70627_aG() * 2;
  }
  
  public void setMaxHealth(float maxHealth)
  {
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(maxHealth);
    func_70606_j(maxHealth);
    setFamiliar(true);
  }
  
  public EntityLivingBase func_70902_q()
  {
    if ((isFamiliar()) && (!field_70170_p.field_72995_K)) {
      return TameableUtil.getOwnerAccrossDimensions(this);
    }
    return super.func_70902_q();
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
  
  protected void func_70629_bd()
  {
    super.func_70629_bd();
    field_70180_af.func_75692_b(18, Float.valueOf(func_110143_aJ()));
    if ((field_70170_p != null) && (!field_70128_L) && (!field_70170_p.field_72995_K) && (timeToLive != -1) && (--timeToLive == 0))
    {
      func_70106_y();
      
      if (poisoned) {
        AxisAlignedBB axisalignedbb = field_70121_D.func_72314_b(3.0D, 2.0D, 3.0D);
        List list1 = field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
        
        if ((list1 != null) && (!list1.isEmpty()))
        {
          Iterator iterator = list1.iterator();
          
          while (iterator.hasNext())
          {
            EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();
            double d0 = func_70068_e(entitylivingbase);
            
            if (d0 < 9.0D)
            {
              double d1 = 1.0D - Math.sqrt(d0) / 3.0D;
              entitylivingbase.func_70690_d(new PotionEffect(field_76436_ufield_76415_H, 60, 0));
            }
          }
        }
        ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, this, 1.0D, 1.0D, 16);
      }
      ParticleEffect.MOB_SPELL.send(SoundEffect.NONE, this, 0.5D, 0.5D, 16);
    }
  }
  
  protected void func_70088_a()
  {
    super.func_70088_a();
    field_70180_af.func_75682_a(18, new Float(func_110143_aJ()));
    field_70180_af.func_75682_a(19, new Byte((byte)0));
    field_70180_af.func_75682_a(20, new Byte((byte)BlockColored.func_150032_b(field_70170_p != null ? field_70170_p.field_73012_v.nextInt(16) : new Random().nextInt(16))));
    field_70180_af.func_75682_a(26, Byte.valueOf((byte)0));
  }
  
  public boolean isFamiliar()
  {
    return field_70180_af.func_75683_a(26) > 0;
  }
  
  public void setFamiliar(boolean familiar) {
    field_70180_af.func_75692_b(26, Byte.valueOf((byte)(familiar ? 1 : 0)));
  }
  
  protected void func_145780_a(int par1, int par2, int par3, Block par4)
  {
    func_85030_a("mob.slime.small", 0.15F, 1.0F);
  }
  
  public void func_70014_b(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70014_b(par1NBTTagCompound);
    par1NBTTagCompound.func_74774_a("SkinColor", (byte)getSkinColor());
    par1NBTTagCompound.func_74774_a("Familiar", (byte)(isFamiliar() ? 1 : 0));
    par1NBTTagCompound.func_74768_a("SuicideIn", timeToLive);
    par1NBTTagCompound.func_74757_a("Poisonous", poisoned);
  }
  
  public void func_70037_a(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70037_a(par1NBTTagCompound);
    
    if (par1NBTTagCompound.func_74764_b("SkinColor")) {
      setSkinColor(par1NBTTagCompound.func_74771_c("SkinColor"));
    }
    
    if (par1NBTTagCompound.func_74764_b("Familiar")) {
      setFamiliar(par1NBTTagCompound.func_74771_c("Familiar") > 0);
    }
    
    if (par1NBTTagCompound.func_74764_b("SuicideIn")) {
      timeToLive = par1NBTTagCompound.func_74762_e("SuicideIn");
    } else {
      timeToLive = -1;
    }
    
    if (par1NBTTagCompound.func_74764_b("Poisonous")) {
      poisoned = par1NBTTagCompound.func_74767_n("Poisonous");
    } else {
      poisoned = false;
    }
  }
  
  protected String func_70639_aQ()
  {
    return "witchery:mob.toad.toad_croak";
  }
  
  protected String func_70621_aR()
  {
    return "witchery:mob.toad.toad_hurt";
  }
  
  protected String func_70673_aS()
  {
    return "witchery:mob.toad.toad_hurt";
  }
  
  protected float func_70599_aP()
  {
    return 0.4F;
  }
  
  protected Item func_146068_u()
  {
    if (!isTemp()) {
      return Items.field_151123_aH;
    }
    return super.func_146068_u();
  }
  


  public void func_70636_d()
  {
    super.func_70636_d();
  }
  
  public void func_70071_h_()
  {
    field_70178_ae = isFamiliar();
    super.func_70071_h_();
    if ((!func_70906_o()) && (!field_70170_p.field_72995_K) && ((field_70159_w != 0.0D) || (field_70179_y != 0.0D)) && (!func_70090_H())) {
      func_70683_ar().func_75660_a();
    }
  }
  
  public float func_70047_e()
  {
    return field_70131_O * 0.8F;
  }
  
  public int func_70646_bf()
  {
    return func_70906_o() ? 20 : super.func_70646_bf();
  }
  



  public boolean func_70097_a(DamageSource par1DamageSource, float par2)
  {
    if (func_85032_ar()) {
      return false;
    }
    Entity entity = par1DamageSource.func_76346_g();
    if (!isFamiliar())
    {
      func_70904_g(false);
    }
    
    if ((entity != null) && (!(entity instanceof EntityPlayer)) && (!(entity instanceof EntityArrow))) {
      par2 = (par2 + 1.0F) / 2.0F;
    }
    
    return super.func_70097_a(par1DamageSource, par2);
  }
  

  public void func_70903_f(boolean par1)
  {
    super.func_70903_f(par1);
  }
  
  public boolean func_70085_c(EntityPlayer par1EntityPlayer)
  {
    if (isTemp()) {
      return true;
    }
    
    ItemStack itemstack = field_71071_by.func_70448_g();
    
    if (func_70909_n()) {
      if ((TameableUtil.isOwner(this, par1EntityPlayer)) && (isFamiliar()) && (par1EntityPlayer.func_70093_af()) && (func_70906_o())) {
        if (!field_70170_p.field_72995_K) {
          Familiar.dismissFamiliar(par1EntityPlayer, this);
        }
        return true;
      }
      
      if (itemstack != null) {
        if ((itemstack.func_77973_b() == Items.field_151078_bh) && (func_110143_aJ() < func_110138_aP())) {
          if (!field_71075_bZ.field_75098_d) {
            field_77994_a -= 1;
          }
          
          func_70691_i(10.0F);
          
          if (field_77994_a <= 0) {
            field_71071_by.func_70299_a(field_71071_by.field_70461_c, (ItemStack)null);
          }
          
          return true; }
        if (itemstack.func_77973_b() == Items.field_151100_aR) {
          int i = BlockColored.func_150032_b(itemstack.func_77960_j());
          
          if (i != getSkinColor()) {
            setSkinColor(i);
            
            if (!field_71075_bZ.field_75098_d) { if (--field_77994_a <= 0) {
                field_71071_by.func_70299_a(field_71071_by.field_70461_c, (ItemStack)null);
              }
            }
            return true;
          }
        } else if ((itemstack.func_77973_b() == Items.field_151057_cb) || (itemstack.func_77973_b() == ItemsPOLYNESIA_CHARM) || (itemstack.func_77973_b() == ItemsDEVILS_TONGUE_CHARM))
        {
          return false;
        }
      }
      
      if ((TameableUtil.isOwner(this, par1EntityPlayer)) && (!func_70877_b(itemstack))) {
        if (!field_70170_p.field_72995_K)
        {
          func_70904_g(!func_70906_o());
          func_70683_ar().func_75661_b();
          field_70703_bu = false;
          func_70778_a((PathEntity)null);
          func_70784_b((Entity)null);
          func_70624_b((EntityLivingBase)null);
        }
        return true;
      }
    } else if ((itemstack != null) && (itemstack.func_77973_b() == Items.field_151078_bh)) {
      if (!field_71075_bZ.field_75098_d) {
        field_77994_a -= 1;
      }
      
      if (field_77994_a <= 0) {
        field_71071_by.func_70299_a(field_71071_by.field_70461_c, (ItemStack)null);
      }
      
      if (!field_70170_p.field_72995_K) {
        if (field_70146_Z.nextInt(3) == 0) {
          func_70903_f(true);
          func_110163_bv();
          func_70778_a((PathEntity)null);
          func_70624_b((EntityLivingBase)null);
          
          func_70904_g(true);
          TameableUtil.setOwner(this, par1EntityPlayer);
          func_70908_e(true);
          field_70170_p.func_72960_a(this, (byte)7);
        } else {
          func_70908_e(false);
          field_70170_p.func_72960_a(this, (byte)6);
        }
      }
      
      return true;
    }
    
    return super.func_70085_c(par1EntityPlayer);
  }
  
  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.toad.name");
  }
  

  public boolean func_70877_b(ItemStack par1ItemStack)
  {
    return (par1ItemStack != null) && (par1ItemStack.func_77973_b() == Items.field_151078_bh);
  }
  
  public int getSkinColor() {
    return field_70180_af.func_75683_a(20) & 0xF;
  }
  
  public void setSkinColor(int par1) {
    field_70180_af.func_75692_b(20, Byte.valueOf((byte)(par1 & 0xF)));
  }
  
  public EntityToad spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
    EntityToad entity = new EntityToad(field_70170_p);
    
    if (TameableUtil.hasOwner(this)) {
      entity.func_110163_bv();
      entity.setSkinColor(getSkinColor());
    }
    
    return entity;
  }
  
  public boolean func_70878_b(EntityAnimal par1EntityAnimal)
  {
    if (par1EntityAnimal == this)
      return false;
    if (!func_70909_n())
      return false;
    if (!(par1EntityAnimal instanceof EntityToad)) {
      return false;
    }
    EntityToad entity = (EntityToad)par1EntityAnimal;
    return entity.func_70909_n();
  }
  
  public boolean func_70922_bv()
  {
    return field_70180_af.func_75683_a(19) == 1;
  }
  
  protected boolean func_70692_ba()
  {
    return false;
  }
  
  public boolean func_142018_a(EntityLivingBase par1EntityLivingBase, EntityLivingBase par2EntityLivingBase)
  {
    if ((!(par1EntityLivingBase instanceof EntityCreeper)) && (!(par1EntityLivingBase instanceof EntityGhast))) {
      if ((par1EntityLivingBase instanceof EntityToad)) {
        EntityToad entity = (EntityToad)par1EntityLivingBase;
        
        if ((entity.func_70909_n()) && (entity.func_70902_q() == par2EntityLivingBase)) {
          return false;
        }
      }
      
      return (!(par1EntityLivingBase instanceof EntityPlayer)) || (!(par2EntityLivingBase instanceof EntityPlayer)) || (((EntityPlayer)par2EntityLivingBase).func_96122_a((EntityPlayer)par1EntityLivingBase));
    }
    

    return false;
  }
  

  public EntityAgeable func_90011_a(EntityAgeable par1EntityAgeable)
  {
    return spawnBabyAnimal(par1EntityAgeable);
  }
  
  public void clearFamiliar()
  {
    setFamiliar(false);
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
    func_70606_j(10.0F);
  }
}
