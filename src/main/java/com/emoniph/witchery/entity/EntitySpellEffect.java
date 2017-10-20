package com.emoniph.witchery.entity;

import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.infusion.infusions.symbols.EffectRegistry;
import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffectProjectile;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntitySpellEffect extends Entity
{
  private int xTile = -1;
  private int yTile = -1;
  private int zTile = -1;
  private Block inTile;
  private boolean inGround;
  public EntityLivingBase shootingEntity;
  private int ticksAlive;
  private int ticksInAir;
  private int lifetime = -1;
  private int effectLevel;
  public double accelerationX;
  public double accelerationY;
  public double accelerationZ;
  private int effectID;
  
  public EntitySpellEffect(World par1World) { super(par1World);
    func_70105_a(0.5F, 0.5F);
    field_70145_X = true;
  }
  
  public EntitySpellEffect setLifeTime(int ticks) {
    lifetime = ticks;
    return this;
  }
  
  protected void func_70088_a()
  {
    field_70180_af.func_75682_a(6, Integer.valueOf(0));
    field_70180_af.func_75682_a(15, Integer.valueOf(0));
  }
  
  public void setShooter(EntityLivingBase entity) {
    if (!field_70170_p.field_72995_K) {
      field_70180_af.func_75692_b(15, Integer.valueOf(entity.func_145782_y()));
    }
  }
  
  public int getShooterID() {
    int id = field_70180_af.func_75679_c(15);
    return id;
  }
  
  public boolean isShooter(Entity entity) {
    int idOther = entity.func_145782_y();
    int us = getShooterID();
    return idOther == us;
  }
  

  public void setEffectID(int effectID)
  {
    this.effectID = effectID;
    func_70096_w().func_75692_b(6, Integer.valueOf(effectID));
  }
  
  public int getEffectID() {
    return func_70096_w().func_75679_c(6);
  }
  
  public int getEffectLevel() {
    return effectLevel;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_70112_a(double par1)
  {
    double d1 = field_70121_D.func_72320_b() * 4.0D;
    d1 *= 64.0D;
    return par1 < d1 * d1;
  }
  
  public EntitySpellEffect(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, SymbolEffect effect, int effectLevel)
  {
    super(par1World);
    this.effectLevel = effectLevel;
    func_70105_a(1.0F, 1.0F);
    func_70012_b(par2, par4, par6, field_70177_z, field_70125_A);
    func_70107_b(par2, par4, par6);
    double d6 = MathHelper.func_76133_a(par8 * par8 + par10 * par10 + par12 * par12);
    accelerationX = (par8 / d6 * 0.1D);
    accelerationY = (par10 / d6 * 0.1D);
    accelerationZ = (par12 / d6 * 0.1D);
    setEffectID(effect.getEffectID());
  }
  
  public EntitySpellEffect(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7, SymbolEffect effect, int effectLevel)
  {
    super(par1World);
    shootingEntity = par2EntityLivingBase;
    this.effectLevel = effectLevel;
    func_70105_a(1.0F, 1.0F);
    func_70012_b(field_70165_t, field_70163_u, field_70161_v, field_70177_z, field_70125_A);
    
    func_70107_b(field_70165_t, field_70163_u, field_70161_v);
    field_70129_M = 0.0F;
    field_70159_w = (this.field_70181_x = this.field_70179_y = 0.0D);
    par3 += field_70146_Z.nextGaussian() * 0.4D;
    par5 += field_70146_Z.nextGaussian() * 0.4D;
    par7 += field_70146_Z.nextGaussian() * 0.4D;
    double d3 = MathHelper.func_76133_a(par3 * par3 + par5 * par5 + par7 * par7);
    accelerationX = (par3 / d3 * 0.1D);
    accelerationY = (par5 / d3 * 0.1D);
    accelerationZ = (par7 / d3 * 0.1D);
    setEffectID(effect.getEffectID());
  }
  



  public void func_70071_h_()
  {
    if ((!field_70170_p.field_72995_K) && (((shootingEntity != null) && (shootingEntity.field_70128_L)) || (!field_70170_p.func_72899_e((int)field_70165_t, (int)field_70163_u, (int)field_70161_v))))
    {

      func_70106_y();
    } else {
      super.func_70071_h_();
      
      if (inGround) {
        Block i = field_70170_p.func_147439_a(xTile, yTile, zTile);
        
        if (i == inTile) {
          ticksAlive += 1;
          
          if (ticksAlive == 600) {
            func_70106_y();
          }
          
          return;
        }
        
        inGround = false;
        field_70159_w *= field_70146_Z.nextFloat() * 0.2F;
        field_70181_x *= field_70146_Z.nextFloat() * 0.2F;
        field_70179_y *= field_70146_Z.nextFloat() * 0.2F;
        ticksAlive = 0;
        ticksInAir = 0;
      } else {
        ticksInAir += 1;
        
        if (ticksInAir == 200) {
          func_70106_y();
        }
      }
      
      Vec3 vec3 = Vec3.func_72443_a(field_70165_t, field_70163_u, field_70161_v);
      Vec3 vec31 = Vec3.func_72443_a(field_70165_t + field_70159_w, field_70163_u + field_70181_x, field_70161_v + field_70179_y);
      
      MovingObjectPosition movingobjectposition = field_70170_p.func_72933_a(vec3, vec31);
      vec3 = Vec3.func_72443_a(field_70165_t, field_70163_u, field_70161_v);
      vec31 = Vec3.func_72443_a(field_70165_t + field_70159_w, field_70163_u + field_70181_x, field_70161_v + field_70179_y);
      

      if (movingobjectposition != null) {
        vec31 = Vec3.func_72443_a(field_72307_f.field_72450_a, field_72307_f.field_72448_b, field_72307_f.field_72449_c);
      }
      

      Entity entity = null;
      List list = field_70170_p.func_72839_b(this, field_70121_D.func_72321_a(field_70159_w, field_70181_x, field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
      
      double d0 = 0.0D;
      
      boolean remote = field_70170_p.field_72995_K;
      for (int j = 0; j < list.size(); j++) {
        Entity entity1 = (Entity)list.get(j);
        
        if ((entity1.func_70067_L()) && ((!isShooter(entity1)) || (ticksInAir >= 25))) {
          float f = 0.3F;
          AxisAlignedBB axisalignedbb = field_70121_D.func_72314_b(f, f, f);
          MovingObjectPosition movingobjectposition1 = axisalignedbb.func_72327_a(vec3, vec31);
          
          if (movingobjectposition1 != null) {
            double d1 = vec3.func_72438_d(field_72307_f);
            
            if ((d1 < d0) || (d0 == 0.0D)) {
              entity = entity1;
              d0 = d1;
            }
          }
        }
      }
      
      if (entity != null) {
        movingobjectposition = new MovingObjectPosition(entity);
      }
      
      if ((movingobjectposition != null) || ((lifetime != -1) && (Math.max(--lifetime, 0) == 0))) {
        onImpact(movingobjectposition);
      }
      
      field_70165_t += field_70159_w;
      field_70163_u += field_70181_x;
      field_70161_v += field_70179_y;
      float f1 = MathHelper.func_76133_a(field_70159_w * field_70159_w + field_70179_y * field_70179_y);
      field_70177_z = ((float)(Math.atan2(field_70179_y, field_70159_w) * 180.0D / 3.141592653589793D) + 90.0F);
      
      field_70125_A = ((float)(Math.atan2(f1, field_70181_x) * 180.0D / 3.141592653589793D) - 90.0F);
      while (field_70125_A - field_70127_C < -180.0F) { field_70127_C -= 360.0F;
      }
      

      while (field_70125_A - field_70127_C >= 180.0F) {
        field_70127_C += 360.0F;
      }
      
      while (field_70177_z - field_70126_B < -180.0F) {
        field_70126_B -= 360.0F;
      }
      
      while (field_70177_z - field_70126_B >= 180.0F) {
        field_70126_B += 360.0F;
      }
      
      field_70125_A = (field_70127_C + (field_70125_A - field_70127_C) * 0.2F);
      field_70177_z = (field_70126_B + (field_70177_z - field_70126_B) * 0.2F);
      float f2 = getMotionFactor();
      
      if (func_70090_H()) {
        for (int k = 0; k < 4; k++) {
          float f3 = 0.25F;
          field_70170_p.func_72869_a("bubble", field_70165_t - field_70159_w * f3, field_70163_u - field_70181_x * f3, field_70161_v - field_70179_y * f3, field_70159_w, field_70181_x, field_70179_y);
        }
        

        f2 = 0.8F;
      }
      
      SymbolEffect effect = EffectRegistry.instance().getEffect(getEffectID());
      if (effect == null) {
        func_70106_y();
      }
      else {
        if ((effect.fallsToEarth()) && (getEffectLevel() == 1)) {
          accelerationX *= 0.8D;
          accelerationY *= 0.8D;
          accelerationZ *= 0.8D;
          field_70159_w += accelerationX;
          field_70181_x += accelerationY;
          field_70179_y += accelerationZ;
          field_70181_x -= 0.05D;
        } else {
          field_70159_w += accelerationX;
          field_70181_x += accelerationY;
          field_70179_y += accelerationZ;
          field_70159_w *= f2;
          field_70181_x *= f2;
          field_70179_y *= f2;
        }
        
        field_70170_p.func_72869_a(effect.isCurse() ? ParticleEffect.MOB_SPELL.toString() : ParticleEffect.SLIME.toString(), field_70165_t, field_70163_u + 0.5D, field_70161_v, 0.0D, 0.0D, 0.0D);
        
        if (effect.isCurse()) {
          field_70170_p.func_72869_a(effect.isCurse() ? ParticleEffect.FLAME.toString() : ParticleEffect.SLIME.toString(), field_70165_t, field_70163_u + 0.5D, field_70161_v, 0.0D, 0.0D, 0.0D);
        }
        

        func_70107_b(field_70165_t, field_70163_u, field_70161_v);
      }
    }
  }
  



  protected float getMotionFactor()
  {
    return 0.95F;
  }
  


  protected void onImpact(MovingObjectPosition mop)
  {
    if (!field_70170_p.field_72995_K) {
      SymbolEffect effect = EffectRegistry.instance().getEffect(getEffectID());
      if ((effect != null) && ((effect instanceof SymbolEffectProjectile))) {
        if (effect.isCurse()) {
          ParticleEffect.MOB_SPELL.send(SoundEffect.MOB_ENDERDRAGON_HIT, this, 1.0D, 1.0D, 16);
        } else {
          ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_SMALL, this, 1.0D, 1.0D, 16);
        }
        ((SymbolEffectProjectile)effect).onCollision(field_70170_p, shootingEntity, mop, this);
      }
    }
    func_70106_y();
  }
  
  public void func_70014_b(NBTTagCompound nbtRoot)
  {
    nbtRoot.func_74777_a("xTile", (short)xTile);
    nbtRoot.func_74777_a("yTile", (short)yTile);
    nbtRoot.func_74777_a("zTile", (short)zTile);
    nbtRoot.func_74774_a("inTile", (byte)Block.func_149682_b(inTile));
    nbtRoot.func_74774_a("inGround", (byte)(inGround ? 1 : 0));
    nbtRoot.func_74782_a("direction", func_70087_a(new double[] { field_70159_w, field_70181_x, field_70179_y }));
    nbtRoot.func_74768_a("EffectID", effectID);
    nbtRoot.func_74768_a("lifetime", lifetime);
    nbtRoot.func_74768_a("effectLevel", effectLevel);
  }
  
  public void func_70037_a(NBTTagCompound nbtRoot)
  {
    xTile = nbtRoot.func_74765_d("xTile");
    yTile = nbtRoot.func_74765_d("yTile");
    zTile = nbtRoot.func_74765_d("zTile");
    inTile = Block.func_149729_e(nbtRoot.func_74771_c("inTile") & 0xFF);
    inGround = (nbtRoot.func_74771_c("inGround") == 1);
    if (nbtRoot.func_74764_b("lifetime")) {
      lifetime = nbtRoot.func_74762_e("lifetime");
    } else {
      lifetime = -1;
    }
    
    if ((nbtRoot.func_74764_b("direction")) && (nbtRoot.func_74764_b("EffectID"))) {
      effectID = nbtRoot.func_74762_e("EffectID");
      setEffectID(effectID);
      
      NBTTagList nbttaglist = nbtRoot.func_150295_c("direction", 6);
      field_70159_w = nbttaglist.func_150309_d(0);
      field_70181_x = nbttaglist.func_150309_d(1);
      field_70179_y = nbttaglist.func_150309_d(2);
    } else {
      func_70106_y();
    }
    effectLevel = Math.max(nbtRoot.func_74762_e("effectLevel"), 1);
  }
  
  public boolean func_70067_L()
  {
    return true;
  }
  
  public float func_70111_Y()
  {
    return 1.0F;
  }
  
  public boolean func_70097_a(DamageSource par1DamageSource, float par2)
  {
    if (func_85032_ar()) {
      return false;
    }
    func_70018_K();
    
    Entity entity = par1DamageSource.func_76346_g();
    boolean canDeflect = (entity != null) && (getEffectID() != 5) && ((entity instanceof EntityPlayer)) && (((EntityPlayer)entity).func_70694_bm() != null) && (((EntityPlayer)entity).func_70694_bm().func_77973_b() == ItemsMYSTIC_BRANCH);
    
    if (canDeflect) {
      Vec3 vec3 = par1DamageSource.func_76346_g().func_70040_Z();
      
      if (vec3 != null) {
        field_70159_w = field_72450_a;
        field_70181_x = field_72448_b;
        field_70179_y = field_72449_c;
        accelerationX = (field_70159_w * 0.1D);
        accelerationY = (field_70181_x * 0.1D);
        accelerationZ = (field_70179_y * 0.1D);
      }
      
      if ((par1DamageSource.func_76346_g() instanceof EntityLivingBase)) {
        shootingEntity = ((EntityLivingBase)par1DamageSource.func_76346_g());
      }
      
      return true;
    }
    return false;
  }
  




  @SideOnly(Side.CLIENT)
  public float func_70053_R()
  {
    return 0.0F;
  }
  
  public float func_70013_c(float par1)
  {
    return 1.0F;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_70070_b(float par1)
  {
    return 15728880;
  }
}
