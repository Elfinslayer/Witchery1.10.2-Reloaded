package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.integration.ModHookManager;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.util.BoltDamageSource;
import com.emoniph.witchery.util.CreatureUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityBolt extends Entity
{
  private int xTile = -1;
  private int yTile = -1;
  private int zTile = -1;
  
  private Block inTile;
  
  private int inData;
  
  private boolean inGround;
  
  public int canBePickedUp;
  
  public int arrowShake;
  
  public Entity shootingEntity;
  private int ticksInGround;
  private int ticksInAir;
  private double damage = 2.0D;
  
  private int knockbackStrength;
  
  public EntityBolt(World par1World)
  {
    super(par1World);
    field_70155_l = 10.0D;
    func_70105_a(0.5F, 0.5F);
  }
  
  public EntityBolt(World par1World, double par2, double par4, double par6) {
    super(par1World);
    field_70155_l = 10.0D;
    func_70105_a(0.5F, 0.5F);
    func_70107_b(par2, par4, par6);
    field_70129_M = 0.0F;
  }
  
  public EntityBolt(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float par4, float par5) {
    super(par1World);
    field_70155_l = 10.0D;
    shootingEntity = par2EntityLivingBase;
    
    if ((par2EntityLivingBase instanceof EntityPlayer)) {
      canBePickedUp = 1;
    }
    
    field_70163_u = (field_70163_u + par2EntityLivingBase.func_70047_e() - 0.10000000149011612D);
    double d0 = field_70165_t - field_70165_t;
    double d1 = field_70121_D.field_72338_b + field_70131_O / 3.0F - field_70163_u;
    double d2 = field_70161_v - field_70161_v;
    double d3 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
    
    if (d3 >= 1.0E-7D) {
      float f2 = (float)(Math.atan2(d2, d0) * 180.0D / 3.141592653589793D) - 90.0F;
      float f3 = (float)-(Math.atan2(d1, d3) * 180.0D / 3.141592653589793D);
      double d4 = d0 / d3;
      double d5 = d2 / d3;
      func_70012_b(field_70165_t + d4, field_70163_u, field_70161_v + d5, f2, f3);
      field_70129_M = 0.0F;
      float f4 = (float)d3 * 0.2F;
      setThrowableHeading(d0, d1 + f4, d2, par4, par5);
    }
  }
  
  public EntityBolt(World par1World, EntityLivingBase par2EntityLivingBase, float par3, float arcStart) {
    super(par1World);
    field_70155_l = 10.0D;
    shootingEntity = par2EntityLivingBase;
    
    if ((par2EntityLivingBase instanceof EntityPlayer)) {
      canBePickedUp = 1;
    }
    
    func_70105_a(0.5F, 0.5F);
    func_70012_b(field_70165_t, field_70163_u + par2EntityLivingBase.func_70047_e(), field_70161_v, field_70177_z, field_70125_A);
    

    field_70177_z += arcStart;
    
    field_70165_t -= MathHelper.func_76134_b(field_70177_z / 180.0F * 3.1415927F) * 0.16F;
    field_70163_u -= 0.30000000149011613D;
    field_70161_v -= MathHelper.func_76126_a(field_70177_z / 180.0F * 3.1415927F) * 0.16F;
    func_70107_b(field_70165_t, field_70163_u, field_70161_v);
    field_70129_M = 0.0F;
    field_70159_w = (-MathHelper.func_76126_a(field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(field_70125_A / 180.0F * 3.1415927F));
    field_70179_y = (MathHelper.func_76134_b(field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(field_70125_A / 180.0F * 3.1415927F));
    field_70181_x = (-MathHelper.func_76126_a(field_70125_A / 180.0F * 3.1415927F));
    setThrowableHeading(field_70159_w, field_70181_x, field_70179_y, par3 * 1.5F, 1.0F);
  }
  
  protected void func_70088_a()
  {
    field_70180_af.func_75682_a(15, "");
    field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
    field_70180_af.func_75682_a(17, Byte.valueOf((byte)0));
  }
  
  public void setShooter(EntityLivingBase entity) {
    if ((!field_70170_p.field_72995_K) && ((entity instanceof EntityPlayer))) {
      field_70180_af.func_75692_b(15, ((EntityPlayer)entity).func_70005_c_());
    }
  }
  
  public String getShooter() {
    String username = field_70180_af.func_75681_e(15);
    if (username == null) {
      return "";
    }
    return username;
  }
  
  public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8)
  {
    float f2 = MathHelper.func_76133_a(par1 * par1 + par3 * par3 + par5 * par5);
    par1 /= f2;
    par3 /= f2;
    par5 /= f2;
    par1 += field_70146_Z.nextGaussian() * (field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
    par3 += field_70146_Z.nextGaussian() * (field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
    par5 += field_70146_Z.nextGaussian() * (field_70146_Z.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
    par1 *= par7;
    par3 *= par7;
    par5 *= par7;
    field_70159_w = par1;
    field_70181_x = par3;
    field_70179_y = par5;
    float f3 = MathHelper.func_76133_a(par1 * par1 + par5 * par5);
    field_70126_B = (this.field_70177_z = (float)(Math.atan2(par1, par5) * 180.0D / 3.141592653589793D));
    field_70127_C = (this.field_70125_A = (float)(Math.atan2(par3, f3) * 180.0D / 3.141592653589793D));
    ticksInGround = 0;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_70056_a(double par1, double par3, double par5, float par7, float par8, int par9)
  {
    func_70107_b(par1, par3, par5);
    func_70101_b(par7, par8);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_70016_h(double par1, double par3, double par5)
  {
    field_70159_w = par1;
    field_70181_x = par3;
    field_70179_y = par5;
    
    if ((field_70127_C == 0.0F) && (field_70126_B == 0.0F)) {
      float f = MathHelper.func_76133_a(par1 * par1 + par5 * par5);
      field_70126_B = (this.field_70177_z = (float)(Math.atan2(par1, par5) * 180.0D / 3.141592653589793D));
      field_70127_C = (this.field_70125_A = (float)(Math.atan2(par3, f) * 180.0D / 3.141592653589793D));
      field_70127_C = field_70125_A;
      field_70126_B = field_70177_z;
      func_70012_b(field_70165_t, field_70163_u, field_70161_v, field_70177_z, field_70125_A);
      ticksInGround = 0;
    }
  }
  
  public void func_70071_h_()
  {
    func_70030_z();
    
    if ((field_70127_C == 0.0F) && (field_70126_B == 0.0F)) {
      float f = MathHelper.func_76133_a(field_70159_w * field_70159_w + field_70179_y * field_70179_y);
      field_70126_B = (this.field_70177_z = (float)(Math.atan2(field_70159_w, field_70179_y) * 180.0D / 3.141592653589793D));
      field_70127_C = (this.field_70125_A = (float)(Math.atan2(field_70181_x, f) * 180.0D / 3.141592653589793D));
    }
    
    Block i = field_70170_p.func_147439_a(xTile, yTile, zTile);
    
    if (i != null) {
      i.func_149719_a(field_70170_p, xTile, yTile, zTile);
      AxisAlignedBB axisalignedbb = i.func_149668_a(field_70170_p, xTile, yTile, zTile);
      
      if ((axisalignedbb != null) && (axisalignedbb.func_72318_a(Vec3.func_72443_a(field_70165_t, field_70163_u, field_70161_v)))) {
        inGround = true;
      }
    }
    
    if (arrowShake > 0) {
      arrowShake -= 1;
    }
    
    if (inGround) {
      Block j = field_70170_p.func_147439_a(xTile, yTile, zTile);
      int k = field_70170_p.func_72805_g(xTile, yTile, zTile);
      
      if ((j == inTile) && (k == inData)) {
        ticksInGround += 1;
        
        if (ticksInGround == 1200) {
          func_70106_y();
        }
      } else {
        inGround = false;
        field_70159_w *= field_70146_Z.nextFloat() * 0.2F;
        field_70181_x *= field_70146_Z.nextFloat() * 0.2F;
        field_70179_y *= field_70146_Z.nextFloat() * 0.2F;
        ticksInGround = 0;
        ticksInAir = 0;
      }
    } else {
      ticksInAir += 1;
      Vec3 vec3 = Vec3.func_72443_a(field_70165_t, field_70163_u, field_70161_v);
      Vec3 vec31 = Vec3.func_72443_a(field_70165_t + field_70159_w, field_70163_u + field_70181_x, field_70161_v + field_70179_y);
      MovingObjectPosition movingobjectposition = field_70170_p.func_147447_a(vec3, vec31, false, true, false);
      vec3 = Vec3.func_72443_a(field_70165_t, field_70163_u, field_70161_v);
      vec31 = Vec3.func_72443_a(field_70165_t + field_70159_w, field_70163_u + field_70181_x, field_70161_v + field_70179_y);
      
      if (movingobjectposition != null) {
        vec31 = Vec3.func_72443_a(field_72307_f.field_72450_a, field_72307_f.field_72448_b, field_72307_f.field_72449_c);
      }
      

      Entity entity = null;
      List list = field_70170_p.func_72839_b(this, field_70121_D.func_72321_a(field_70159_w, field_70181_x, field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
      
      double d0 = 0.0D;
      


      String shooterPlayer = getShooter();
      for (int l = 0; l < list.size(); l++) {
        Entity entity1 = (Entity)list.get(l);
        
        if ((entity1.func_70067_L()) && ((ticksInAir >= 5) || ((entity1 != shootingEntity) && ((!(entity1 instanceof EntityPlayer)) || (!((EntityPlayer)entity1).func_70005_c_().equals(shooterPlayer))))))
        {

          float f1 = 0.3F;
          AxisAlignedBB axisalignedbb1 = field_70121_D.func_72314_b(f1, f1, f1);
          MovingObjectPosition movingobjectposition1 = axisalignedbb1.func_72327_a(vec3, vec31);
          
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
      
      if ((movingobjectposition != null) && (field_72308_g != null) && ((field_72308_g instanceof EntityPlayer))) {
        EntityPlayer entityplayer = (EntityPlayer)field_72308_g;
        
        if ((field_71075_bZ.field_75102_a) || (((shootingEntity instanceof EntityPlayer)) && (!((EntityPlayer)shootingEntity).func_96122_a(entityplayer))))
        {
          movingobjectposition = null;
        }
      }
      



      if (movingobjectposition != null) {
        if (field_72308_g != null) {
          float f2 = MathHelper.func_76133_a(field_70159_w * field_70159_w + field_70181_x * field_70181_x + field_70179_y * field_70179_y);
          int i1 = MathHelper.func_76143_f(f2 * damage);
          
          if (getIsCritical()) {
            i1 += field_70146_Z.nextInt(i1 / 2 + 2);
          }
          
          BoltDamageSource damagesource = new BoltDamageSource(this, shootingEntity != null ? shootingEntity : null);
          
          if ((func_70027_ad()) && (!(field_72308_g instanceof EntityEnderman))) {
            field_72308_g.func_70015_d(5);
          }
          

          if ((isPoweredDraining) && ((field_72308_g instanceof EntityLivingBase))) {
            EntityLivingBase living = (EntityLivingBase)field_72308_g;
            

            Collection activeEffects = living.func_70651_bq();
            ArrayList<Integer> removeIDs = new ArrayList();
            for (Object obj : activeEffects) {
              PotionEffect effect = (PotionEffect)obj;
              if ((effect.func_76456_a() != field_76436_ufield_76415_H) && (effect.func_76456_a() != field_82731_vfield_76415_H) && (effect.func_76456_a() != field_76431_kfield_76415_H))
              {
                removeIDs.add(Integer.valueOf(effect.func_76456_a())); }
            }
            for (Integer id : removeIDs) {
              living.func_82170_o(id.intValue());
            }
            

            Witchery.modHooks.reducePowerLevels(living, 0.5F);
          }
          

          if ((isHoly) && ((CreatureUtil.isUndead(entity)) || (CreatureUtil.isDemonic(entity)))) {
            i1 = (int)(i1 * 1.5D);
          }
          
          if (field_72308_g.func_70097_a(damagesource, i1)) {
            if ((field_72308_g instanceof EntityLivingBase)) {
              EntityLivingBase entitylivingbase = (EntityLivingBase)field_72308_g;
              
              if (!field_70170_p.field_72995_K) {
                entitylivingbase.func_85034_r(entitylivingbase.func_85035_bI() + 1);
              }
              
              if (knockbackStrength > 0) {
                float f3 = MathHelper.func_76133_a(field_70159_w * field_70159_w + field_70179_y * field_70179_y);
                
                if (f3 > 0.0F) {
                  field_72308_g.func_70024_g(field_70159_w * knockbackStrength * 0.6000000238418579D / f3, 0.1D, field_70179_y * knockbackStrength * 0.6000000238418579D / f3);
                }
              }
              

              if ((shootingEntity != null) && ((shootingEntity instanceof EntityLivingBase)))
              {
                EnchantmentHelper.func_151384_a(entitylivingbase, shootingEntity);
                EnchantmentHelper.func_151385_b((EntityLivingBase)shootingEntity, entitylivingbase);
              }
              
              if ((shootingEntity != null) && (field_72308_g != shootingEntity) && ((field_72308_g instanceof EntityPlayer)) && ((shootingEntity instanceof net.minecraft.entity.player.EntityPlayerMP)))
              {

                Witchery.packetPipeline.sendToAllAround(new net.minecraft.network.play.server.S2BPacketChangeGameState(6, 0.0F), shootingEntity.field_70170_p, com.emoniph.witchery.util.TargetPointUtil.from(shootingEntity, 128.0D));
              }
              



              field_70172_ad = 0;
            }
            
            func_85030_a("random.bowhit", 1.0F, 1.2F / (field_70146_Z.nextFloat() * 0.2F + 0.9F));
            
            if (!(field_72308_g instanceof EntityEnderman)) {
              func_70106_y();
            }
          } else {
            field_70159_w *= -0.10000000149011612D;
            field_70181_x *= -0.10000000149011612D;
            field_70179_y *= -0.10000000149011612D;
            field_70177_z += 180.0F;
            field_70126_B += 180.0F;
            ticksInAir = 0;
          }
        } else {
          xTile = field_72311_b;
          yTile = field_72312_c;
          zTile = field_72309_d;
          inTile = field_70170_p.func_147439_a(xTile, yTile, zTile);
          inData = field_70170_p.func_72805_g(xTile, yTile, zTile);
          field_70159_w = ((float)(field_72307_f.field_72450_a - field_70165_t));
          field_70181_x = ((float)(field_72307_f.field_72448_b - field_70163_u));
          field_70179_y = ((float)(field_72307_f.field_72449_c - field_70161_v));
          float f2 = MathHelper.func_76133_a(field_70159_w * field_70159_w + field_70181_x * field_70181_x + field_70179_y * field_70179_y);
          field_70165_t -= field_70159_w / f2 * 0.05000000074505806D;
          field_70163_u -= field_70181_x / f2 * 0.05000000074505806D;
          field_70161_v -= field_70179_y / f2 * 0.05000000074505806D;
          func_85030_a("random.bowhit", 1.0F, 1.2F / (field_70146_Z.nextFloat() * 0.2F + 0.9F));
          inGround = true;
          arrowShake = 7;
          setIsCritical(false);
          
          if (inTile.func_149688_o() != net.minecraft.block.material.Material.field_151579_a) {
            inTile.func_149670_a(field_70170_p, xTile, yTile, zTile, this);
          }
        }
      }
      
      if (getIsCritical()) {
        for (l = 0; l < 4; l++) {
          field_70170_p.func_72869_a("crit", field_70165_t + field_70159_w * l / 4.0D, field_70163_u + field_70181_x * l / 4.0D, field_70161_v + field_70179_y * l / 4.0D, -field_70159_w, -field_70181_x + 0.2D, -field_70179_y);
        }
      }
      

      field_70165_t += field_70159_w;
      field_70163_u += field_70181_x;
      field_70161_v += field_70179_y;
      float f2 = MathHelper.func_76133_a(field_70159_w * field_70159_w + field_70179_y * field_70179_y);
      field_70177_z = ((float)(Math.atan2(field_70159_w, field_70179_y) * 180.0D / 3.141592653589793D));
      
      for (field_70125_A = ((float)(Math.atan2(field_70181_x, f2) * 180.0D / 3.141592653589793D)); field_70125_A - field_70127_C < -180.0F; field_70127_C -= 360.0F) {}
      


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
      float f4 = 0.99F;
      float f1 = 0.05F;
      
      if (func_70090_H()) {
        for (int j1 = 0; j1 < 4; j1++) {
          float f3 = 0.25F;
          field_70170_p.func_72869_a("bubble", field_70165_t - field_70159_w * f3, field_70163_u - field_70181_x * f3, field_70161_v - field_70179_y * f3, field_70159_w, field_70181_x, field_70179_y);
        }
        

        f4 = 0.8F;
      }
      
      field_70159_w *= f4;
      field_70181_x *= f4;
      field_70179_y *= f4;
      field_70181_x -= f1;
      func_70107_b(field_70165_t, field_70163_u, field_70161_v);
      func_145775_I();
    }
  }
  
  public void func_70014_b(NBTTagCompound par1NBTTagCompound)
  {
    par1NBTTagCompound.func_74777_a("xTile", (short)xTile);
    par1NBTTagCompound.func_74777_a("yTile", (short)yTile);
    par1NBTTagCompound.func_74777_a("zTile", (short)zTile);
    par1NBTTagCompound.func_74774_a("inTile", (byte)Block.func_149682_b(inTile));
    par1NBTTagCompound.func_74774_a("inData", (byte)inData);
    par1NBTTagCompound.func_74774_a("shake", (byte)arrowShake);
    par1NBTTagCompound.func_74774_a("inGround", (byte)(inGround ? 1 : 0));
    par1NBTTagCompound.func_74774_a("pickup", (byte)canBePickedUp);
    par1NBTTagCompound.func_74780_a("damage", damage);
    
    par1NBTTagCompound.func_74768_a("boltType", getBoltType());
  }
  

  public void func_70037_a(NBTTagCompound par1NBTTagCompound)
  {
    xTile = par1NBTTagCompound.func_74765_d("xTile");
    yTile = par1NBTTagCompound.func_74765_d("yTile");
    zTile = par1NBTTagCompound.func_74765_d("zTile");
    inTile = Block.func_149729_e(par1NBTTagCompound.func_74771_c("inTile") & 0xFF);
    inData = (par1NBTTagCompound.func_74771_c("inData") & 0xFF);
    arrowShake = (par1NBTTagCompound.func_74771_c("shake") & 0xFF);
    inGround = (par1NBTTagCompound.func_74771_c("inGround") == 1);
    
    if (par1NBTTagCompound.func_74764_b("damage")) {
      damage = par1NBTTagCompound.func_74769_h("damage");
    }
    
    if (par1NBTTagCompound.func_74764_b("pickup")) {
      canBePickedUp = par1NBTTagCompound.func_74771_c("pickup");
    } else if (par1NBTTagCompound.func_74764_b("player")) {
      canBePickedUp = (par1NBTTagCompound.func_74767_n("player") ? 1 : 0);
    }
    
    setBoltType(par1NBTTagCompound.func_74762_e("boltType"));
  }
  
  public void func_70100_b_(EntityPlayer par1EntityPlayer)
  {
    if ((!field_70170_p.field_72995_K) && (inGround) && (arrowShake <= 0)) {
      boolean flag = (canBePickedUp == 1) || ((canBePickedUp == 2) && (field_71075_bZ.field_75098_d));
      
      if (canBePickedUp == 1) {
        net.minecraft.item.ItemStack stack = null;
        if (isDraining()) {
          stack = ItemsGENERIC.itemBoltAntiMagic.createStack();
        } else if (isHolyDamage()) {
          stack = ItemsGENERIC.itemBoltHoly.createStack();
        } else {
          stack = ItemsGENERIC.itemBoltStake.createStack();
        }
        
        if (!field_71071_by.func_70441_a(stack)) {
          flag = false;
        }
      }
      
      if (flag) {
        func_85030_a("random.pop", 0.2F, ((field_70146_Z.nextFloat() - field_70146_Z.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        par1EntityPlayer.func_71001_a(this, 1);
        func_70106_y();
      }
    }
  }
  
  protected boolean func_70041_e_()
  {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public float func_70053_R()
  {
    return 0.0F;
  }
  
  public void setDamage(double par1) {
    damage = par1;
  }
  
  public double getDamage() {
    return damage;
  }
  
  public void setKnockbackStrength(int par1) {
    knockbackStrength = par1;
  }
  
  public void setBoltType(int type) {
    if (!field_70170_p.field_72995_K) {
      field_70180_af.func_75692_b(17, Byte.valueOf((byte)type));
    }
  }
  
  public int getBoltType() {
    byte b0 = field_70180_af.func_75683_a(17);
    return b0;
  }
  
  public boolean isDraining() {
    int b0 = getBoltType();
    return (b0 == 1) || (b0 == 2);
  }
  
  public boolean isPoweredDraining() {
    return getBoltType() == 2;
  }
  
  public boolean isHolyDamage() {
    return getBoltType() == 3;
  }
  
  public boolean isWoodenDamage() {
    int boltType = getBoltType();
    
    return (boltType == 0) || (boltType == 1) || (boltType == 2);
  }
  
  public boolean isSilverDamage() {
    int boltType = getBoltType();
    
    return boltType == 4;
  }
  
  public boolean func_70075_an()
  {
    return false;
  }
  
  public void setIsCritical(boolean par1) {
    byte b0 = field_70180_af.func_75683_a(16);
    
    if (par1) {
      field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 | 0x1)));
    } else {
      field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 & 0xFFFFFFFE)));
    }
  }
  
  public boolean getIsCritical() {
    byte b0 = field_70180_af.func_75683_a(16);
    return (b0 & 0x1) != 0;
  }
}
