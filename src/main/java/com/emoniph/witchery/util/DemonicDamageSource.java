package com.emoniph.witchery.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;

public class DemonicDamageSource extends DamageSource
{
  protected Entity damageSourceEntity;
  
  public DemonicDamageSource(Entity par2Entity)
  {
    super("magic");
    damageSourceEntity = par2Entity;
    func_76348_h();
    func_82726_p();
    func_151518_m();
  }
  
  public Entity func_76346_g()
  {
    return damageSourceEntity;
  }
  
  public net.minecraft.util.IChatComponent func_151519_b(EntityLivingBase p_151519_1_)
  {
    ItemStack itemstack = (damageSourceEntity instanceof EntityLivingBase) ? ((EntityLivingBase)damageSourceEntity).func_70694_bm() : null;
    
    String s = "death.attack." + field_76373_n;
    String s1 = s + ".item";
    return (itemstack != null) && (itemstack.func_82837_s()) && (net.minecraft.util.StatCollector.func_94522_b(s1)) ? new ChatComponentTranslation(s1, new Object[] { p_151519_1_.func_145748_c_(), damageSourceEntity.func_145748_c_(), itemstack.func_151000_E() }) : new ChatComponentTranslation(s, new Object[] { p_151519_1_.func_145748_c_(), damageSourceEntity.func_145748_c_() });
  }
  


  public boolean func_76350_n()
  {
    return (damageSourceEntity != null) && ((damageSourceEntity instanceof EntityLivingBase)) && (!(damageSourceEntity instanceof EntityPlayer));
  }
}
