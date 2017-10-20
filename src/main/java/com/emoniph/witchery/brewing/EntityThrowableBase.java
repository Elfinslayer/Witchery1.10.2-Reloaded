package com.emoniph.witchery.brewing;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public abstract class EntityThrowableBase extends Entity
{
  private int field_145788_c = -1;
  private int field_145786_d = -1;
  private int field_145787_e = -1;
  private Block field_145785_f;
  protected boolean inGround;
  public int throwableShake;
  private EntityLivingBase thrower;
  private String throwerName;
  private int ticksInGround;
  private int ticksInAir;
  
  public EntityThrowableBase(World world) {
    super(world);
    func_70105_a(0.25F, 0.25F);
  }
  

  protected void func_70088_a() {}
  

  @SideOnly(Side.CLIENT)
  public boolean func_70112_a(double p_70112_1_)
  {
    double d1 = field_70121_D.func_72320_b() * 4.0D;
    d1 *= 64.0D;
    return p_70112_1_ < d1 * d1;
  }
  
  public EntityThrowableBase(World world, EntityLivingBase thrower, float pitchShift) {
    super(world);
    this.thrower = thrower;
    func_70105_a(0.25F, 0.25F);
    func_70012_b(field_70165_t, field_70163_u + thrower.func_70047_e(), field_70161_v, field_70177_z, field_70125_A);
    
    field_70165_t -= MathHelper.func_76134_b(field_70177_z / 180.0F * 3.1415927F) * 0.16F;
    field_70163_u -= 0.10000000149011612D;
    field_70161_v -= MathHelper.func_76126_a(field_70177_z / 180.0F * 3.1415927F) * 0.16F;
    func_70107_b(field_70165_t, field_70163_u, field_70161_v);
    field_70129_M = 0.0F;
    float f = 0.6F;
    field_70159_w = (-MathHelper.func_76126_a(field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(field_70125_A / 180.0F * 3.1415927F) * f);
    
    field_70179_y = (MathHelper.func_76134_b(field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(field_70125_A / 180.0F * 3.1415927F) * f);
    
    field_70181_x = (-MathHelper.func_76126_a((field_70125_A + pitchShift) / 180.0F * 3.1415927F) * f);
    setThrowableHeading(field_70159_w, field_70181_x, field_70179_y, func_70182_d(), 1.0F);
  }
  
  public EntityThrowableBase(World world, double x, double y, double z, float pitchShift) {
    super(world);
    ticksInGround = 0;
    func_70105_a(0.25F, 0.25F);
    func_70107_b(x, y, z);
    field_70129_M = 0.0F;
  }
  
  protected float func_70182_d() {
    return 1.5F;
  }
  
  protected float func_70183_g() {
    return 0.0F;
  }
  
  protected int getMaxAirTicks() {
    return 200;
  }
  
  protected int getMaxGroundTicks() {
    return 1200;
  }
  
  public void setThrowableHeading(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_)
  {
    float f2 = MathHelper.func_76133_a(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
    
    p_70186_1_ /= f2;
    p_70186_3_ /= f2;
    p_70186_5_ /= f2;
    p_70186_1_ += field_70146_Z.nextGaussian() * 0.007499999832361937D * p_70186_8_;
    p_70186_3_ += field_70146_Z.nextGaussian() * 0.007499999832361937D * p_70186_8_;
    p_70186_5_ += field_70146_Z.nextGaussian() * 0.007499999832361937D * p_70186_8_;
    p_70186_1_ *= p_70186_7_;
    p_70186_3_ *= p_70186_7_;
    p_70186_5_ *= p_70186_7_;
    field_70159_w = p_70186_1_;
    field_70181_x = p_70186_3_;
    field_70179_y = p_70186_5_;
    float f3 = MathHelper.func_76133_a(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_);
    field_70126_B = (this.field_70177_z = (float)(Math.atan2(p_70186_1_, p_70186_5_) * 180.0D / 3.141592653589793D));
    field_70127_C = (this.field_70125_A = (float)(Math.atan2(p_70186_3_, f3) * 180.0D / 3.141592653589793D));
    ticksInGround = 0;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_)
  {
    field_70159_w = p_70016_1_;
    field_70181_x = p_70016_3_;
    field_70179_y = p_70016_5_;
    
    if ((field_70127_C == 0.0F) && (field_70126_B == 0.0F)) {
      float f = MathHelper.func_76133_a(p_70016_1_ * p_70016_1_ + p_70016_5_ * p_70016_5_);
      field_70126_B = (this.field_70177_z = (float)(Math.atan2(p_70016_1_, p_70016_5_) * 180.0D / 3.141592653589793D));
      field_70127_C = (this.field_70125_A = (float)(Math.atan2(p_70016_3_, f) * 180.0D / 3.141592653589793D));
    }
  }
  
  public void func_70071_h_()
  {
    field_70142_S = field_70165_t;
    field_70137_T = field_70163_u;
    field_70136_U = field_70161_v;
    super.func_70071_h_();
    
    if (throwableShake > 0) {
      throwableShake -= 1;
    }
    
    if (inGround) {
      if (field_70170_p.func_147439_a(field_145788_c, field_145786_d, field_145787_e) == field_145785_f) {
        ticksInGround += 1;
        
        if (ticksInGround >= getMaxGroundTicks()) {
          if (!field_70170_p.field_72995_K) {
            onSetDead();
          } else {
            onClientSetDead();
          }
          
          func_70106_y();
        }
        
        return;
      }
      
      inGround = false;
      field_70159_w *= field_70146_Z.nextFloat() * 0.2F;
      field_70181_x *= field_70146_Z.nextFloat() * 0.2F;
      field_70179_y *= field_70146_Z.nextFloat() * 0.2F;
      ticksInGround = 0;
      ticksInAir = 0;
    } else {
      ticksInAir += 1;
      
      if (ticksInAir >= getMaxAirTicks()) {
        if (!field_70170_p.field_72995_K) {
          onSetDead();
        } else {
          onClientSetDead();
        }
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
    

    if (!field_70170_p.field_72995_K) {
      Entity entity = null;
      List list = field_70170_p.func_72839_b(this, field_70121_D.func_72321_a(field_70159_w, field_70181_x, field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
      
      double d0 = 0.0D;
      EntityLivingBase entitylivingbase = getThrower();
      
      for (int j = 0; j < list.size(); j++) {
        Entity entity1 = (Entity)list.get(j);
        
        if ((entity1.func_70067_L()) && ((entity1 instanceof EntityLivingBase)) && (
          (entity1 != entitylivingbase) || (ticksInAir >= 5)))
        {
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
    }
    
    if ((movingobjectposition != null) && (
      (field_72313_a != net.minecraft.util.MovingObjectPosition.MovingObjectType.BLOCK) || (field_70170_p.func_147439_a(field_72311_b, field_72312_c, field_72309_d) != net.minecraft.init.Blocks.field_150427_aO)))
    {



      onImpact(movingobjectposition);
    }
    


    field_70165_t += field_70159_w;
    field_70163_u += field_70181_x;
    field_70161_v += field_70179_y;
    float f1 = MathHelper.func_76133_a(field_70159_w * field_70159_w + field_70179_y * field_70179_y);
    field_70177_z = ((float)(Math.atan2(field_70159_w, field_70179_y) * 180.0D / 3.141592653589793D));
    
    field_70125_A = ((float)(Math.atan2(field_70181_x, f1) * 180.0D / 3.141592653589793D));
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
    float f2 = 0.99F;
    float f3 = getGravityVelocity();
    
    if (func_70090_H()) {
      for (int i = 0; i < 4; i++) {
        float f4 = 0.25F;
        field_70170_p.func_72869_a("bubble", field_70165_t - field_70159_w * f4, field_70163_u - field_70181_x * f4, field_70161_v - field_70179_y * f4, field_70159_w, field_70181_x, field_70179_y);
      }
      


      f2 = 0.8F;
    }
    
    field_70159_w *= f2;
    field_70181_x *= f2;
    field_70179_y *= f2;
    field_70181_x -= f3;
    func_70107_b(field_70165_t, field_70163_u, field_70161_v);
  }
  

  protected void onSetDead() {}
  

  @SideOnly(Side.CLIENT)
  protected void onClientSetDead() {}
  

  protected float getGravityVelocity()
  {
    return 0.03F;
  }
  
  protected abstract void onImpact(MovingObjectPosition paramMovingObjectPosition);
  
  public void func_70014_b(NBTTagCompound p_70014_1_)
  {
    p_70014_1_.func_74777_a("xTile", (short)field_145788_c);
    p_70014_1_.func_74777_a("yTile", (short)field_145786_d);
    p_70014_1_.func_74777_a("zTile", (short)field_145787_e);
    p_70014_1_.func_74774_a("inTile", (byte)Block.func_149682_b(field_145785_f));
    p_70014_1_.func_74774_a("shake", (byte)throwableShake);
    p_70014_1_.func_74774_a("inGround", (byte)(inGround ? 1 : 0));
    
    if (((throwerName == null) || (throwerName.length() == 0)) && (thrower != null) && ((thrower instanceof EntityPlayer)))
    {
      throwerName = thrower.func_70005_c_();
    }
    
    p_70014_1_.func_74778_a("ownerName", throwerName == null ? "" : throwerName);
  }
  
  public void func_70037_a(NBTTagCompound p_70037_1_)
  {
    field_145788_c = p_70037_1_.func_74765_d("xTile");
    field_145786_d = p_70037_1_.func_74765_d("yTile");
    field_145787_e = p_70037_1_.func_74765_d("zTile");
    field_145785_f = Block.func_149729_e(p_70037_1_.func_74771_c("inTile") & 0xFF);
    throwableShake = (p_70037_1_.func_74771_c("shake") & 0xFF);
    inGround = (p_70037_1_.func_74771_c("inGround") == 1);
    throwerName = p_70037_1_.func_74779_i("ownerName");
    
    if ((throwerName != null) && (throwerName.length() == 0)) {
      throwerName = null;
    }
  }
  
  @SideOnly(Side.CLIENT)
  public float func_70053_R()
  {
    return 0.0F;
  }
  
  public EntityLivingBase getThrower() {
    if ((thrower == null) && (throwerName != null) && (throwerName.length() > 0)) {
      thrower = field_70170_p.func_72924_a(throwerName);
    }
    
    return thrower;
  }
}
