package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.entity.ai.EntityAIFlyerFlyToWaypoint;
import com.emoniph.witchery.entity.ai.EntityAIFlyerFlyToWaypoint.CarryRequirement;
import com.emoniph.witchery.entity.ai.EntityAIFlyerFollowOwner;
import com.emoniph.witchery.entity.ai.EntityAIFlyerLand;
import com.emoniph.witchery.entity.ai.EntityAIFlyingTempt;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TameableUtil;
import com.emoniph.witchery.util.Waypoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class EntityWingedMonkey extends EntityFlyingTameable
{
  private int attackTimer;
  public EntityAIFlyingTempt aiTempt;
  private int timeToLive = -1;
  
  private static final ItemStack[] TEMPTATIONS = { new ItemStack(Items.field_151147_al), new ItemStack(Items.field_151082_bd) };
  
  public EntityWingedMonkey(World world)
  {
    super(world);
    func_70105_a(0.6F, 0.8F);
    func_70661_as().func_75495_e(true);
    field_70714_bg.func_75776_a(1, new com.emoniph.witchery.entity.ai.EntityAISitAndStay(this));
    field_70714_bg.func_75776_a(2, new EntityAIFlyerFlyToWaypoint(this, EntityAIFlyerFlyToWaypoint.CarryRequirement.ENTITY_LIVING));
    field_70714_bg.func_75776_a(3, new com.emoniph.witchery.entity.ai.EntityAIFlyerAttackOnCollide(this, 1.0D, true));
    field_70714_bg.func_75776_a(4, this.aiTempt = new EntityAIFlyingTempt(this, 0.6D, TEMPTATIONS, true));
    field_70714_bg.func_75776_a(5, new EntityAIFlyerFollowOwner(this, 1.0D, 14.0F, 5.0F));
    field_70714_bg.func_75776_a(8, new com.emoniph.witchery.entity.ai.EntityAIFlyerMate(this, 0.8D));
    field_70714_bg.func_75776_a(9, new EntityAIFlyerLand(this, 0.8D, true));
    field_70714_bg.func_75776_a(10, new com.emoniph.witchery.entity.ai.EntityAIFlyerWander(this, 0.8D, 10.0D));
    field_70714_bg.func_75776_a(11, new net.minecraft.entity.ai.EntityAIWatchClosest(this, EntityPlayer.class, 10.0F, 0.2F));
    field_70715_bh.func_75776_a(1, new EntityAIOwnerHurtByTarget(this));
    field_70715_bh.func_75776_a(2, new EntityAIOwnerHurtTarget(this));
    field_70715_bh.func_75776_a(3, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, true));
  }
  
  public int func_70658_aO()
  {
    return super.func_70658_aO() + 5;
  }
  
  public void func_70014_b(NBTTagCompound nbtRoot)
  {
    super.func_70014_b(nbtRoot);
    
    nbtRoot.func_74774_a("FeatherColor", (byte)getFeatherColor());
    nbtRoot.func_74768_a("SuicideIn", timeToLive);
  }
  

  public void func_70037_a(NBTTagCompound nbtRoot)
  {
    super.func_70037_a(nbtRoot);
    
    if (nbtRoot.func_74764_b("FeatherColor")) {
      setFeatherColor(nbtRoot.func_74771_c("FeatherColor"));
    }
    
    if (nbtRoot.func_74764_b("SuicideIn")) {
      timeToLive = nbtRoot.func_74762_e("SuicideIn");
    } else {
      timeToLive = -1;
    }
  }
  
  public void func_70071_h_()
  {
    super.func_70071_h_();
  }
  
  protected void func_70088_a()
  {
    super.func_70088_a();
    field_70180_af.func_75682_a(18, Byte.valueOf((byte)0));
    field_70180_af.func_75682_a(21, Byte.valueOf((byte)(field_70170_p.field_73012_v.nextInt(100) == 0 ? 0 : field_70170_p.field_73012_v.nextInt(15) + 1)));
  }
  

  protected int func_70682_h(int par1)
  {
    return par1;
  }
  
  protected void func_110147_ax()
  {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
    
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.30000001192092896D);
    
    func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
  }
  
  protected boolean func_70692_ba()
  {
    return false;
  }
  
  public boolean func_70650_aV()
  {
    return true;
  }
  
  public void func_70629_bd()
  {
    func_70661_as().func_75499_g();
    super.func_70629_bd();
    if ((field_70170_p != null) && (!field_70128_L) && (!field_70170_p.field_72995_K) && (timeToLive != -1) && ((--timeToLive == 0) || (func_70638_az() == null) || (func_70638_azfield_70128_L)))
    {
      ParticleEffect.EXPLODE.send(SoundEffect.NONE, this, 1.0D, 1.0D, 16);
      func_70106_y();
    }
  }
  
  public void func_70636_d()
  {
    super.func_70636_d();
    
    if (attackTimer > 0) {
      attackTimer -= 1;
    }
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
  
  protected void func_70628_a(boolean par1, int par2)
  {
    if (!isTemp()) {
      int var3 = field_70146_Z.nextInt(3) + field_70146_Z.nextInt(1 + par2);
      
      for (int var4 = 0; var4 < var3; var4++) {
        func_70099_a(new ItemStack(Items.field_151008_G), 0.0F);
      }
    }
  }
  
  public int func_70627_aG()
  {
    return super.func_70627_aG() * 2;
  }
  
  protected String func_70639_aQ()
  {
    return "witchery:mob.monkey.say";
  }
  
  protected String func_70621_aR()
  {
    return "witchery:mob.monkey.hit";
  }
  
  protected String func_70673_aS()
  {
    return "witchery:mob.monkey.death";
  }
  
  public boolean func_70652_k(Entity par1Entity)
  {
    attackTimer = 10;
    field_70170_p.func_72960_a(this, (byte)4);
    return par1Entity.func_70097_a(DamageSource.func_76358_a(this), 2.0F);
  }
  
  public boolean func_70097_a(DamageSource par1DamageSource, float par2)
  {
    if (func_85032_ar()) {
      return false;
    }
    func_70904_g(false);
    return super.func_70097_a(par1DamageSource, par2);
  }
  

  public double func_70042_X()
  {
    return field_70153_n != null ? -field_70153_n.field_70131_O * 0.6D : 0.0D;
  }
  
  public boolean shouldRiderSit()
  {
    return false;
  }
  
  public boolean func_70085_c(EntityPlayer player)
  {
    if (isTemp()) {
      return true;
    }
    
    ItemStack itemstack = field_71071_by.func_70448_g();
    
    if (func_70909_n()) {
      if ((TameableUtil.isOwner(this, player)) && (!func_70877_b(itemstack)))
      {
        if ((itemstack != null) && (itemstack.func_77973_b() == Items.field_151100_aR)) {
          if (!field_70170_p.field_72995_K) {
            int i = net.minecraft.block.BlockColored.func_150032_b(itemstack.func_77960_j());
            setFeatherColor(i);
            
            if (!field_71075_bZ.field_75098_d) {
              field_77994_a -= 1;
            }
            
            if (field_77994_a <= 0) {
              field_71071_by.func_70299_a(field_71071_by.field_70461_c, (ItemStack)null);
            }
          }
        } else {
          if ((itemstack != null) && ((itemstack.func_77973_b() == Items.field_151057_cb) || (itemstack.func_77973_b() == ItemsPOLYNESIA_CHARM) || (itemstack.func_77973_b() == ItemsDEVILS_TONGUE_CHARM)))
          {

            return false; }
          if ((ItemsGENERIC.itemWaystonePlayerBound.isMatch(itemstack)) || (ItemsGENERIC.itemWaystoneBound.isMatch(itemstack)))
          {
            waypoint = itemstack.func_77946_l();
            homeX = 0.0D;
            homeY = 0.0D;
            homeZ = 0.0D;
            Waypoint wp = getWaypoint();
            homeX = X;
            homeY = Y;
            homeZ = Z;
            ItemsGENERIC.bindToLocation(field_70170_p, (int)field_70165_t, (int)field_70163_u, (int)field_70161_v, field_70170_p.field_73011_w.field_76574_g, field_70170_p.field_73011_w.func_80007_l(), waypoint);
            

            if ((!field_70170_p.field_72995_K) && (func_70906_o())) {
              func_70904_g(false);
            }
            player.func_70078_a(this);
            
            ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_ORB, this, 1.0D, 1.0D, 16);
          } else if ((itemstack != null) && (ItemsTAGLOCK_KIT.isTaglockPresent(itemstack, Integer.valueOf(1)))) {
            waypoint = itemstack.func_77946_l();
            homeX = field_70165_t;
            homeY = field_70163_u;
            homeZ = field_70161_v;
            ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_ORB, this, 1.0D, 1.0D, 16);
            if (!field_71075_bZ.field_75098_d) {
              field_77994_a -= 1;
            }
          } else if ((func_70068_e(player) < 9.0D) && 
            (!field_70170_p.field_72995_K)) {
            func_70904_g(!func_70906_o());
          }
        }
        
        return true;
      }
      if ((TameableUtil.isOwner(this, player)) && 
        (func_70877_b(itemstack)) && (func_110143_aJ() < func_110138_aP())) {
        if (!field_70170_p.field_72995_K) {
          func_70691_i(10.0F);
          if (!field_71075_bZ.field_75098_d) {
            field_77994_a -= 1;
          }
          
          if (field_77994_a <= 0) {
            field_71071_by.func_70299_a(field_71071_by.field_70461_c, (ItemStack)null);
          }
        }
        
        return true;
      }
      

      return super.func_70085_c(player);
    }
    if ((itemstack != null) && ((itemstack.func_77973_b() == Items.field_151147_al) || (itemstack.func_77973_b() == Items.field_151082_bd)) && (player.func_70068_e(this) < 9.0D))
    {

      if (!field_71075_bZ.field_75098_d) {
        field_77994_a -= 1;
      }
      
      if (field_77994_a <= 0) {
        field_71071_by.func_70299_a(field_71071_by.field_70461_c, (ItemStack)null);
      }
      
      if (!field_70170_p.field_72995_K) {
        if (field_70146_Z.nextInt(3) == 0) {
          func_70903_f(true);
          setTameSkin(1 + field_70170_p.field_73012_v.nextInt(3));
          TameableUtil.setOwner(this, player);
          func_110163_bv();
          func_70908_e(true);
          func_70904_g(true);
          field_70170_p.func_72960_a(this, (byte)7);
        } else {
          func_70908_e(false);
          field_70170_p.func_72960_a(this, (byte)6);
        }
      }
      
      return true;
    }
    if (!func_70877_b(itemstack)) {
      return super.func_70085_c(player);
    }
    
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public net.minecraft.util.IIcon func_70620_b(ItemStack stack, int pass)
  {
    return stack.func_77973_b().func_77623_v() ? stack.func_77973_b().getIcon(stack, pass) : stack.func_77954_c();
  }
  
  public EntityWingedMonkey spawnBabyAnimal(EntityAgeable par1EntityAgeable)
  {
    EntityWingedMonkey entityocelot = new EntityWingedMonkey(field_70170_p);
    
    if (func_70909_n())
    {

      entityocelot.func_110163_bv();
      entityocelot.setTameSkin(getTameSkin());
      entityocelot.setFeatherColor(getFeatherColor());
    }
    
    return entityocelot;
  }
  
  public boolean func_70877_b(ItemStack itemstack)
  {
    return (itemstack != null) && ((itemstack.func_77973_b() == Items.field_151147_al) || (itemstack.func_77973_b() == Items.field_151082_bd));
  }
  
  public boolean func_70878_b(EntityAnimal par1EntityAnimal)
  {
    if (par1EntityAnimal == this)
      return false;
    if (!func_70909_n())
      return false;
    if (!(par1EntityAnimal instanceof EntityWingedMonkey)) {
      return false;
    }
    EntityWingedMonkey entityocelot = (EntityWingedMonkey)par1EntityAnimal;
    return entityocelot.func_70909_n();
  }
  
  public int getFeatherColor()
  {
    return field_70180_af.func_75683_a(21) & 0xF;
  }
  
  public void setFeatherColor(int par1) {
    byte b0 = field_70180_af.func_75683_a(16);
    field_70180_af.func_75692_b(21, Byte.valueOf((byte)(b0 & 0xF0 | par1 & 0xF)));
  }
  
  public int getTameSkin() {
    return field_70180_af.func_75683_a(18);
  }
  
  public void setTameSkin(int par1) {
    field_70180_af.func_75692_b(18, Byte.valueOf((byte)par1));
  }
  
  public boolean func_70601_bi()
  {
    if (field_70170_p.field_73012_v.nextInt(3) == 0) {
      return false;
    }
    if ((field_70170_p.func_72855_b(field_70121_D)) && (field_70170_p.func_72945_a(this, field_70121_D).isEmpty()) && (!field_70170_p.func_72953_d(field_70121_D)))
    {

      int i = MathHelper.func_76128_c(field_70165_t);
      int j = MathHelper.func_76128_c(field_70121_D.field_72338_b);
      int k = MathHelper.func_76128_c(field_70161_v);
      
      if (j < 63) {
        return false;
      }
      
      Block block = field_70170_p.func_147439_a(i, j - 1, k);
      
      if ((block == net.minecraft.init.Blocks.field_150349_c) || ((block != null) && (block.isLeaves(field_70170_p, i, j - 1, k)))) {
        return true;
      }
    }
    
    return false;
  }
  

  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.wingedmonkey.name");
  }
  

  public EntityAgeable func_90011_a(EntityAgeable par1EntityAgeable)
  {
    return spawnBabyAnimal(par1EntityAgeable);
  }
  
  public void setTimeToLive(int i) {
    timeToLive = i;
  }
  
  public boolean isTemp() {
    return timeToLive != -1;
  }
}
