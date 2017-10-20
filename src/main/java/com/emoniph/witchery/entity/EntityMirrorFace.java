package com.emoniph.witchery.entity;

import com.emoniph.witchery.util.TimeUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMirrorFace extends EntityLiving
{
  private long ticksAlive = 0L;
  
  public EntityMirrorFace(World world) {
    super(world);
    field_70178_ae = true;
    func_70105_a(0.5F, 0.5F);
    field_70145_X = true;
    field_70714_bg.func_75776_a(1, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F, 0.4F));
  }
  
  protected void func_70088_a()
  {
    super.func_70088_a();
  }
  
  protected int func_70682_h(int air)
  {
    return air;
  }
  
  protected float func_70599_aP()
  {
    return 0.8F;
  }
  
  protected float func_70647_i()
  {
    return 1.0F;
  }
  
  public int func_70627_aG()
  {
    return 80;
  }
  
  protected String func_70639_aQ()
  {
    return null;
  }
  
  protected String func_70621_aR()
  {
    return null;
  }
  
  protected String func_70673_aS()
  {
    return null;
  }
  
  public boolean func_70104_M()
  {
    return false;
  }
  


  protected void func_82167_n(Entity par1Entity) {}
  

  protected void func_85033_bc() {}
  

  protected boolean func_70650_aV()
  {
    return true;
  }
  
  public void func_70636_d()
  {
    super.func_70636_d();
    
    ticksAlive = Math.max(field_70173_aa, ++ticksAlive);
    










    if ((ticksAlive > TimeUtil.secsToTicks(10)) && 
      (!field_70170_p.field_72995_K)) {
      func_70106_y();
    }
  }
  

  public void func_70071_h_()
  {
    super.func_70071_h_();
    
    field_70181_x = 0.0D;
  }
  


  protected void func_70628_a(boolean par1, int par2) {}
  

  protected boolean func_70041_e_()
  {
    return false;
  }
  


  protected void func_70069_a(float par1) {}
  

  protected void func_70064_a(double par1, boolean par3) {}
  

  public boolean func_145773_az()
  {
    return true;
  }
  
  public boolean func_70097_a(DamageSource par1DamageSource, float par2)
  {
    return false;
  }
  
  public void func_70037_a(NBTTagCompound nbtRoot)
  {
    super.func_70037_a(nbtRoot);
    ticksAlive = nbtRoot.func_74763_f("WITCTicksAlive");
  }
  
  public void func_70014_b(NBTTagCompound nbtRoot)
  {
    super.func_70014_b(nbtRoot);
    nbtRoot.func_74772_a("WITCTicksAlive", ticksAlive);
  }
}
