package com.emoniph.witchery.entity;

import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.mojang.authlib.GameProfile;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityAttackBat extends EntityBat
{
  private EntityPlayer ownerPlayer;
  private GameProfile owner;
  
  public EntityAttackBat(World world)
  {
    super(world);
  }
  
  protected void func_85033_bc() {
    List list = field_70170_p.func_72839_b(this, field_70121_D.func_72314_b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
    

    if ((list != null) && (!list.isEmpty())) {
      for (int i = 0; i < list.size(); i++) {
        Entity entity = (Entity)list.get(i);
        
        if (entity.func_70104_M()) {
          func_82167_n(entity);
        }
      }
    }
  }
  
  protected void func_82167_n(Entity entity) {
    if ((!field_70170_p.field_72995_K) && ((entity instanceof EntityLivingBase))) {
      EntityLivingBase target = (EntityLivingBase)entity;
      if (ownerPlayer == null) {
        ownerPlayer = getOwner();
      }
      if ((target != ownerPlayer) && (!(target instanceof EntityBat)) && (!field_70128_L)) {
        target.func_70097_a(net.minecraft.util.DamageSource.func_76354_b(this, ownerPlayer), 4.0F);
        ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, entity, field_70130_N, field_70131_O, 16);
        
        func_70106_y();
      }
    }
  }
  



  protected void func_70619_bc()
  {
    if (!field_70170_p.field_72995_K) {
      boolean done = false;
      if (field_70173_aa > 300) {
        ParticleEffect.SMOKE.send(SoundEffect.NONE, this, field_70130_N, field_70131_O, 16);
        func_70106_y();
      }
      else {
        if (ownerPlayer == null) {
          ownerPlayer = getOwner();
        }
        
        if ((ownerPlayer != null) && (ownerPlayer.field_71093_bK == field_71093_bK)) {
          MovingObjectPosition mop = com.emoniph.witchery.infusion.infusions.InfusionOtherwhere.raytraceEntities(field_70170_p, ownerPlayer, true, 32.0D);
          
          if ((mop != null) && (field_72313_a == net.minecraft.util.MovingObjectPosition.MovingObjectType.ENTITY) && ((field_72308_g instanceof EntityLivingBase)) && (!(field_72308_g instanceof EntityBat)))
          {
            double d0 = field_72308_g.field_70165_t - field_70165_t;
            double d1 = field_72308_g.field_70163_u - field_70163_u;
            double d2 = field_72308_g.field_70161_v - field_70161_v;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            d3 = MathHelper.func_76133_a(d3);
            if (isCourseTraversable(field_72308_g.field_70165_t, field_72308_g.field_70163_u, field_72308_g.field_70161_v, d3))
            {
              field_70159_w += d0 / d3 * 0.1D;
              field_70181_x += d1 / d3 * 0.1D;
              field_70179_y += d2 / d3 * 0.1D;
              float f = (float)(Math.atan2(field_70179_y, field_70159_w) * 180.0D / 3.141592653589793D) - 90.0F;
              float f1 = MathHelper.func_76142_g(f - field_70177_z);
              field_70701_bs = 0.5F;
              field_70177_z += f1;
              done = true;
            }
            
            field_70761_aq = (this.field_70177_z = -(float)Math.atan2(field_70159_w, field_70179_y) * 180.0F / 3.1415927F);
          }
        }
      }
      


      if (!done) {
        super.func_70619_bc();
      }
    }
  }
  
  private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
    double d4 = (par1 - field_70165_t) / par7;
    double d5 = (par3 - field_70163_u) / par7;
    double d6 = (par5 - field_70161_v) / par7;
    
    AxisAlignedBB axisalignedbb = field_70121_D.func_72329_c();
    
    for (int i = 1; i < par7; i++) {
      axisalignedbb.func_72317_d(d4, d5, d6);
      
      if (!field_70170_p.func_72945_a(this, axisalignedbb).isEmpty()) {
        return false;
      }
    }
    
    return true;
  }
  

  public void setOwner(EntityPlayer player)
  {
    owner = player.func_146103_bH();
  }
  
  public EntityPlayer getOwner() {
    return owner != null ? field_70170_p.func_152378_a(owner.getId()) : null;
  }
  
  public void func_70014_b(NBTTagCompound nbtRoot)
  {
    super.func_70014_b(nbtRoot);
    if (owner != null) {
      NBTTagCompound nbttagcompound1 = new NBTTagCompound();
      NBTUtil.func_152460_a(nbttagcompound1, owner);
      nbtRoot.func_74782_a("Owner", nbttagcompound1);
    }
  }
  
  public void func_70020_e(NBTTagCompound nbtRoot)
  {
    super.func_70020_e(nbtRoot);
    if (nbtRoot.func_150297_b("Owner", 10)) {
      owner = NBTUtil.func_152459_a(nbtRoot.func_74775_l("Owner"));
    }
  }
}
