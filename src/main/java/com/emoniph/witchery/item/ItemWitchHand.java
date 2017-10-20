package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.infusion.Infusion.Registry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class ItemWitchHand extends ItemBase
{
  public ItemWitchHand()
  {
    func_77625_d(1);
    func_77664_n();
  }
  
  @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  public EnumRarity func_77613_e(ItemStack itemstack)
  {
    return EnumRarity.uncommon;
  }
  
  public void func_77663_a(ItemStack itemstack, World world, Entity entity, int par4, boolean par5)
  {
    if ((entity instanceof EntityPlayer)) {
      Infusion.Registry.instance().get((EntityPlayer)entity).onUpdate(itemstack, world, (EntityPlayer)entity, par4, par5);
    }
  }
  
  public boolean onLeftClickEntity(ItemStack itemstack, EntityPlayer player, Entity entity)
  {
    Infusion.Registry.instance().get(player).onLeftClickEntity(itemstack, field_70170_p, player, entity);
    return true;
  }
  
  public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer player)
  {
    player.func_71008_a(itemstack, func_77626_a(itemstack));
    return itemstack;
  }
  
  public int func_77626_a(ItemStack itemstack)
  {
    return 400;
  }
  
  public void onUsingTick(ItemStack itemstack, EntityPlayer player, int countdown)
  {
    Infusion.Registry.instance().get(player).onUsingItemTick(itemstack, field_70170_p, player, countdown);
  }
  
  public void func_77615_a(ItemStack itemstack, World world, EntityPlayer player, int countdown)
  {
    if ((field_72995_K) || (!Infusion.isOnCooldown(world, itemstack)))
      Infusion.Registry.instance().get(player).onPlayerStoppedUsing(itemstack, world, player, countdown);
  }
  
  public static class EventHooks {
    public EventHooks() {}
    
    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) { if ((!entityLiving.field_70170_p.field_72995_K) && (
        ((entityLiving instanceof EntityWitch)) || ((entityLiving instanceof com.emoniph.witchery.entity.EntityCovenWitch)))) {
        Entity entitySource = source.func_76364_f();
        if ((entitySource != null) && ((entitySource instanceof EntityPlayer))) {
          EntityPlayer player = (EntityPlayer)entitySource;
          boolean hasArthana = (player.func_70694_bm() != null) && (player.func_70694_bm().func_77973_b() == ItemsARTHANA);
          if (field_70170_p.field_73012_v.nextDouble() < (hasArthana ? 0.5D : 0.33D)) {
            ItemStack itemstack = new ItemStack(ItemsWITCH_HAND);
            EntityItem entityItem = new EntityItem(entityLiving.field_70170_p, entityLiving.field_70165_t, entityLiving.field_70163_u, entityLiving.field_70161_v, itemstack);
            
            entityLiving.field_70170_p.func_72838_d(entityItem);
          }
        }
      }
    }
  }
}
