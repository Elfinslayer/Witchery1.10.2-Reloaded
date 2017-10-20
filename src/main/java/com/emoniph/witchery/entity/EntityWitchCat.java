package com.emoniph.witchery.entity;

import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.util.TameableUtil;
import java.util.Random;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityWitchCat extends EntityOcelot implements com.emoniph.witchery.familiar.IFamiliar
{
  public EntityWitchCat(World par1World)
  {
    super(par1World);
    func_70661_as().func_75491_a(true);
    func_70661_as().func_75495_e(true);
    
    field_70714_bg.func_85156_a(field_70714_bg.field_75782_a.get(4)).field_75733_a);
    field_70714_bg.func_85156_a(field_70714_bg.field_75782_a.get(1)).field_75733_a);
    



    field_70714_bg.func_75776_a(1, new com.emoniph.witchery.entity.ai.EntityAISitAndStay(this));
    field_70714_bg.func_75776_a(5, new com.emoniph.witchery.entity.ai.EntityAIDimensionalFollowOwner(this, 1.0D, 10.0F, 5.0F));
  }
  
  public void func_70014_b(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70014_b(par1NBTTagCompound);
    
    par1NBTTagCompound.func_74774_a("Familiar", (byte)(isFamiliar() ? 1 : 0));
  }
  
  public void func_70037_a(NBTTagCompound par1NBTTagCompound)
  {
    super.func_70037_a(par1NBTTagCompound);
    
    if (par1NBTTagCompound.func_74764_b("Familiar")) {
      setFamiliar(par1NBTTagCompound.func_74771_c("Familiar") > 0);
    }
  }
  
  public void func_70071_h_()
  {
    field_70178_ae = isFamiliar();
    super.func_70071_h_();
  }
  
  public int func_70658_aO()
  {
    return super.func_70658_aO() + (isFamiliar() ? 5 : 0);
  }
  
  public net.minecraft.entity.EntityLivingBase func_70902_q()
  {
    if ((isFamiliar()) && (!field_70170_p.field_72995_K)) {
      return TameableUtil.getOwnerAccrossDimensions(this);
    }
    return super.func_70902_q();
  }
  

  protected void func_70088_a()
  {
    super.func_70088_a();
    field_70180_af.func_75682_a(26, Byte.valueOf((byte)0));
  }
  
  public boolean func_70097_a(DamageSource par1DamageSource, float par2)
  {
    boolean sitting = func_70906_o();
    boolean result = super.func_70097_a(par1DamageSource, par2);
    if ((sitting) && (isFamiliar())) {
      func_70904_g(true);
    }
    return result;
  }
  
  public boolean isFamiliar()
  {
    return field_70180_af.func_75683_a(26) > 0;
  }
  
  public void setFamiliar(boolean familiar) {
    field_70180_af.func_75692_b(26, Byte.valueOf((byte)(familiar ? 1 : 0)));
  }
  
  public String func_70005_c_()
  {
    if (func_94056_bM()) {
      return func_94057_bL();
    }
    return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.cat.name");
  }
  

  protected int func_70682_h(int par1)
  {
    return par1;
  }
  
  public boolean func_70085_c(EntityPlayer par1EntityPlayer)
  {
    ItemStack itemstack = field_71071_by.func_70448_g();
    
    if (func_70909_n()) {
      if ((TameableUtil.isOwner(this, par1EntityPlayer)) && (isFamiliar()) && (par1EntityPlayer.func_70093_af()) && (func_70906_o()))
      {
        if (!field_70170_p.field_72995_K) {
          com.emoniph.witchery.familiar.Familiar.dismissFamiliar(par1EntityPlayer, this);
        }
        return true;
      }
      
      if (TameableUtil.isOwner(this, par1EntityPlayer)) {
        if ((itemstack == null) || ((!func_70877_b(itemstack)) && (itemstack.func_77973_b() != Items.field_151057_cb) && (itemstack.func_77973_b() != ItemsPOLYNESIA_CHARM) && (itemstack.func_77973_b() != ItemsDEVILS_TONGUE_CHARM)))
        {

          if (!field_70170_p.field_72995_K) {
            func_70904_g(!func_70906_o());
          }
          return true; }
        if ((itemstack != null) && (func_70877_b(itemstack)) && (func_110143_aJ() < func_110138_aP())) {
          if (!field_70170_p.field_72995_K) {
            if (!field_71075_bZ.field_75098_d) {
              field_77994_a -= 1;
            }
            
            func_70691_i(10.0F);
            
            if (field_77994_a <= 0) {
              field_71071_by.func_70299_a(field_71071_by.field_70461_c, (ItemStack)null);
            }
          }
          return true;
        }
      }
    } else if ((itemstack != null) && (itemstack.func_77973_b() == Items.field_151115_aP) && (par1EntityPlayer.func_70068_e(this) < 9.0D)) {
      if (!field_71075_bZ.field_75098_d) {
        field_77994_a -= 1;
      }
      
      if (field_77994_a <= 0) {
        field_71071_by.func_70299_a(field_71071_by.field_70461_c, (ItemStack)null);
      }
      
      if (!field_70170_p.field_72995_K) {
        if (field_70146_Z.nextInt(3) == 0) {
          func_70903_f(true);
          func_70912_b(1 + field_70170_p.field_73012_v.nextInt(3));
          TameableUtil.setOwner(this, par1EntityPlayer);
          func_70908_e(true);
          func_110163_bv();
          func_70904_g(true);
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
  
  public void setMaxHealth(float maxHealth)
  {
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(maxHealth);
    func_70606_j(maxHealth);
    setFamiliar(true);
  }
  
  public void cloneOcelot(EntityOcelot oldCat) {
    if (oldCat.func_94056_bM()) {
      func_94058_c(oldCat.func_94057_bL());
    }
    
    func_70012_b(field_70165_t, field_70163_u, field_70161_v, field_70177_z, field_70125_A);
    TameableUtil.cloneOwner(this, oldCat);
    func_70903_f(true);
    func_70904_g(oldCat.func_70906_o());
    double oldMaxHealth = oldCat.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111126_e();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(oldMaxHealth);
    func_70606_j(oldCat.func_110143_aJ());
  }
  
  public void clearFamiliar()
  {
    setFamiliar(false);
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
    func_70606_j(10.0F);
  }
}
