package com.emoniph.witchery.entity;

import java.util.Random;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntitySpectre extends EntitySummonedUndead
{
  public EntitySpectre(World par1World)
  {
    super(par1World);
    func_70661_as().func_75491_a(true);
    field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
    field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
    field_70714_bg.func_75776_a(3, new EntityAIMoveTowardsTarget(this, 1.0D, 32.0F));
    field_70714_bg.func_75776_a(4, new EntityAIWander(this, 1.0D));
    field_70714_bg.func_75776_a(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    field_70714_bg.func_75776_a(6, new EntityAILookIdle(this));
    field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false));
    field_70715_bh.func_75776_a(2, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
  }
  

  protected void func_110147_ax()
  {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
  }
  
  protected void func_70088_a()
  {
    super.func_70088_a();
  }
  

  public int func_70658_aO()
  {
    int i = super.func_70658_aO() + 2;
    
    if (i > 20) {
      i = 20;
    }
    
    return i;
  }
  
  protected boolean func_70650_aV()
  {
    return true;
  }
  
  public void func_70636_d()
  {
    super.func_70636_d();
  }
  

  public void func_70071_h_()
  {
    super.func_70071_h_();
  }
  
  public boolean func_70652_k(Entity par1Entity)
  {
    float f = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
    int i = 0;
    
    if ((par1Entity instanceof EntityLivingBase)) {
      EntityLivingBase living = (EntityLivingBase)par1Entity;
      float maxHealth = living.func_110138_aP();
      f = Math.max(maxHealth * 0.15F, 1.0F);
    }
    
    if ((par1Entity instanceof EntityLivingBase))
    {
      f += EnchantmentHelper.func_77512_a(this, (EntityLivingBase)par1Entity);
      i += EnchantmentHelper.func_77507_b(this, (EntityLivingBase)par1Entity);
    }
    
    boolean flag = com.emoniph.witchery.util.EntityUtil.touchOfDeath(par1Entity, this, f);
    

    if (flag)
    {
      if (i > 0)
      {
        par1Entity.func_70024_g(-MathHelper.func_76126_a(field_70177_z * 3.1415927F / 180.0F) * i * 0.5F, 0.1D, MathHelper.func_76134_b(field_70177_z * 3.1415927F / 180.0F) * i * 0.5F);
        field_70159_w *= 0.6D;
        field_70179_y *= 0.6D;
      }
      
      int j = EnchantmentHelper.func_90036_a(this);
      
      if (j > 0)
      {
        par1Entity.func_70015_d(j * 4);
      }
    }
    
    return flag;
  }
  
  protected String func_70639_aQ()
  {
    return "witchery:mob.spectre.spectre_say";
  }
  
  protected String func_70621_aR()
  {
    return "witchery:mob.spectre.spectre_die";
  }
  
  protected String func_70673_aS()
  {
    return "witchery:mob.spectre.spectre_die";
  }
  
  protected void func_82164_bB()
  {
    if (field_70146_Z.nextFloat() < 0.15F * field_70170_p.func_147462_b(field_70165_t, field_70163_u, field_70161_v)) {
      int i = field_70146_Z.nextInt(2);
      float f = field_70170_p.field_73013_u == EnumDifficulty.HARD ? 0.1F : 0.25F;
      
      if (field_70146_Z.nextFloat() < 0.095F) {
        i++;
      }
      
      if (field_70146_Z.nextFloat() < 0.095F) {
        i++;
      }
      
      if (field_70146_Z.nextFloat() < 0.095F) {
        i++;
      }
      
      for (int j = 3; j >= 2; j--) {
        ItemStack itemstack = func_130225_q(j);
        
        if ((j < 3) && (field_70146_Z.nextFloat() < f)) {
          break;
        }
        
        if (itemstack == null) {
          Item item = func_82161_a(j + 1, i);
          
          if (item != null) {
            func_70062_b(j + 1, new ItemStack(item));
          }
        }
      }
    }
  }
  
  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.spectre.name");
  }
  

  public void func_70014_b(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70014_b(par1NBTTagCompound);
  }
  

  public void func_70037_a(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70037_a(par1NBTTagCompound);
  }
  
  public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData)
  {
    Object par1EntityLivingData1 = super.func_110161_a(par1EntityLivingData);
    
    func_82164_bB();
    func_82162_bC();
    
    setObscured(true);
    


    return (IEntityLivingData)par1EntityLivingData1;
  }
}
