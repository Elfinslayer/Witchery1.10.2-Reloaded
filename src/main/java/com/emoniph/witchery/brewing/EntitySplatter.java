package com.emoniph.witchery.brewing;

import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.EntityPosition;
import com.emoniph.witchery.util.EntityUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.common.util.ForgeDirection;

public class EntitySplatter extends Entity
{
  private int field_145788_c = -1;
  private int field_145786_d = -1;
  private int field_145787_e = -1;
  private Block field_145785_f;
  protected boolean inGround;
  public int throwableShake;
  private int ticksInGround;
  private int ticksInAir;
  private int effectID;
  private int color;
  private int level;
  
  public EntitySplatter(World world)
  {
    super(world);
    func_70105_a(0.25F, 0.25F);
  }
  
  public EntitySplatter(World world, double x, double y, double z, int effectID, int color, int level) {
    super(world);
    ticksInGround = 0;
    func_70105_a(0.25F, 0.25F);
    func_70107_b(x, y, z);
    field_70129_M = 0.0F;
    this.effectID = effectID;
    this.level = level;
    setColor(color);
    if (effectID == 1) {
      func_70015_d(1000);
    }
  }
  
  protected void func_70088_a()
  {
    field_70180_af.func_75682_a(6, Integer.valueOf(0));
  }
  
  protected void setColor(int color) {
    func_70096_w().func_75692_b(6, Integer.valueOf(color));
  }
  
  public int getColor() {
    return func_70096_w().func_75679_c(6);
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_70112_a(double distSq)
  {
    double d1 = field_70121_D.func_72320_b() * 4.0D;
    d1 *= 64.0D;
    return distSq < d1 * d1;
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
  
  public void func_70016_h(double motionX, double motionY, double motionZ)
  {
    field_70159_w = motionX;
    field_70181_x = motionY;
    field_70179_y = motionZ;
    
    if ((field_70127_C == 0.0F) && (field_70126_B == 0.0F)) {
      float f = MathHelper.func_76133_a(motionX * motionX + motionZ * motionZ);
      field_70126_B = (this.field_70177_z = (float)(Math.atan2(motionX, motionZ) * 180.0D / 3.141592653589793D));
      field_70127_C = (this.field_70125_A = (float)(Math.atan2(motionY, f) * 180.0D / 3.141592653589793D));
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
        
        if (ticksInGround == 1200) {
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
      
      for (int j = 0; j < list.size(); j++) {
        Entity entity1 = (Entity)list.get(j);
        
        if ((entity1.func_70067_L()) && (ticksInAir >= 5) && (!(entity instanceof EntitySplatter))) {
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
    
    if (movingobjectposition != null) {
      if ((field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) && (field_70170_p.func_147439_a(field_72311_b, field_72312_c, field_72309_d) == Blocks.field_150427_aO))
      {

        func_70063_aa();
      } else {
        onImpact(movingobjectposition);
      }
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
  
  protected float getGravityVelocity() {
    return 0.03F;
  }
  
  protected void onImpact(MovingObjectPosition mop) {
    if ((!field_70170_p.field_72995_K) && 
      (mop != null)) {
      Coord coord = new Coord(mop, new EntityPosition(this), true);
      switch (1.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[field_72313_a.ordinal()]) {
      case 1: 
        ForgeDirection side = ForgeDirection.getOrientation(field_72310_e);
        int x = field_72311_b + offsetX;
        int y = field_72312_c + offsetY;
        int z = field_72309_d + offsetZ;
        if (BlockUtil.isReplaceableBlock(field_70170_p, x, y, z, FakePlayerFactory.getMinecraft((WorldServer)field_70170_p)))
        {
          field_70170_p.func_147449_b(x, y, z, Blocks.field_150480_ab);
        }
        if (level - 1 > 0) {
          splatter(field_70170_p, coord, level - 1);
        }
        func_70106_y();
        break;
      
      case 2: 
        if ((field_72308_g instanceof EntityLivingBase)) {
          field_72308_g.func_70015_d(5);
        }
        break;
      case 3: 
        func_70106_y();
      }
      
    }
  }
  
  public static void splatter(World world, Coord coord, int level)
  {
    if (!field_72995_K) {
      for (int i = 0; i < 3 + level; i++) {
        EntitySplatter splatter = new EntitySplatter(world, 0.5D + x, 0.5D + y, 0.5D + z, 1, 10027008, level);
        
        double maxSpeed = 0.1D;
        double doubleSpeed = 0.2D;
        splatter.func_70016_h(field_73012_v.nextDouble() * 0.2D - 0.1D, field_73012_v.nextDouble() * 0.05D + 0.3D, field_73012_v.nextDouble() * 0.2D - 0.1D);
        

        EntityUtil.spawnEntityInWorld(world, splatter);
      }
    }
  }
  
  public void func_70014_b(NBTTagCompound nbtRoot)
  {
    nbtRoot.func_74777_a("xTile", (short)field_145788_c);
    nbtRoot.func_74777_a("yTile", (short)field_145786_d);
    nbtRoot.func_74777_a("zTile", (short)field_145787_e);
    nbtRoot.func_74774_a("inTile", (byte)Block.func_149682_b(field_145785_f));
    nbtRoot.func_74774_a("shake", (byte)throwableShake);
    nbtRoot.func_74774_a("inGround", (byte)(inGround ? 1 : 0));
    nbtRoot.func_74768_a("Color", color);
    nbtRoot.func_74768_a("Level", level);
    nbtRoot.func_74768_a("Effect", effectID);
  }
  
  public void func_70037_a(NBTTagCompound nbtRoot)
  {
    field_145788_c = nbtRoot.func_74765_d("xTile");
    field_145786_d = nbtRoot.func_74765_d("yTile");
    field_145787_e = nbtRoot.func_74765_d("zTile");
    field_145785_f = Block.func_149729_e(nbtRoot.func_74771_c("inTile") & 0xFF);
    throwableShake = (nbtRoot.func_74771_c("shake") & 0xFF);
    inGround = (nbtRoot.func_74771_c("inGround") == 1);
    effectID = nbtRoot.func_74762_e("Effect");
    level = nbtRoot.func_74762_e("Level");
    setColor(nbtRoot.func_74762_e("Color"));
  }
  
  @SideOnly(Side.CLIENT)
  public float func_70053_R()
  {
    return 0.0F;
  }
}
