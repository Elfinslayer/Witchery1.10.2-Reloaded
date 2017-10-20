package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryCreativeTab;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemCaneSword extends ItemSword
{
  public ItemCaneSword()
  {
    super(Item.ToolMaterial.EMERALD);
    func_77637_a(WitcheryCreativeTab.INSTANCE);
  }
  
  public Item func_77655_b(String itemName)
  {
    com.emoniph.witchery.util.ItemUtil.registerItem(this, itemName);
    return super.func_77655_b(itemName);
  }
  
  @SideOnly(Side.CLIENT)
  public EnumRarity func_77613_e(ItemStack stack)
  {
    return EnumRarity.rare;
  }
  
  public int func_77619_b()
  {
    return Item.ToolMaterial.IRON.func_77995_e();
  }
  
  public Multimap getAttributeModifiers(ItemStack stack) {
    Multimap multimap = HashMultimap.create();
    float damage = isDrawn(stack) ? 4.0F + func_150931_i() : 1.0F;
    multimap.put(SharedMonsterAttributes.field_111264_e.func_111108_a(), new AttributeModifier(field_111210_e, "Weapon modifier", damage, 0));
    
    return multimap;
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
  {
    if (!stack.func_77942_o()) {
      stack.func_77982_d(new NBTTagCompound());
    }
    NBTTagCompound nbtItem = stack.func_77978_p();
    boolean deployed = isDrawn(nbtItem);
    if (player.func_70093_af()) {
      if (!field_72995_K) {
        setDrawn(player, stack, nbtItem, !deployed);
        
        if (deployed) {
          SoundEffect.WITCHERY_RANDOM_SWORD_DRAW.playAtPlayer(world, player, 1.0F, 1.0F);
        } else {
          SoundEffect.WITCHERY_RANDOM_SWORD_SHEATHE.playAtPlayer(world, player, 1.0F, 1.0F);
        }
      }
    }
    else if (deployed) {
      super.func_77659_a(stack, world, player);
    } else {
      ExtendedPlayer playerEx = ExtendedPlayer.get(player);
      if ((playerEx.isVampire()) && (playerEx.isBloodReserveReady()) && (playerEx.getBloodPower() < playerEx.getMaxBloodPower())) {
        ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, world, field_70165_t, field_70163_u + field_70131_O * 0.85D, field_70161_v, 0.5D, 0.5D, 16);
        
        playerEx.useBloodReserve();
      } else {
        SoundEffect.NOTE_SNARE.playOnlyTo(player);
      }
    }
    
    return stack;
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean moreTips)
  {
    String localText = String.format(Witchery.resource(func_77658_a() + ".tip"), new Object[] { Integer.valueOf(ExtendedPlayer.get(player).getBloodReserve()) });
    
    if (localText != null) {
      for (String s : localText.split("\n")) {
        if (!s.isEmpty()) {
          list.add(s);
        }
      }
    }
  }
  
  public boolean isDrawn(EntityLivingBase player) {
    ItemStack heldItem = player.func_70694_bm();
    if ((heldItem != null) && (heldItem.func_77973_b() == this)) {
      return isDrawn(heldItem);
    }
    return false;
  }
  
  private boolean isDrawn(ItemStack stack)
  {
    return isDrawn(stack.func_77978_p());
  }
  
  private boolean isDrawn(NBTTagCompound nbtItem) {
    boolean deployed = (nbtItem != null) && (nbtItem.func_74767_n("WITCBladeDeployed"));
    return deployed;
  }
  
  private void setDrawn(EntityPlayer player, ItemStack stack, boolean deployed) {
    setDrawn(player, stack, stack.func_77978_p(), deployed);
  }
  
  private void setDrawn(EntityPlayer player, ItemStack stack, NBTTagCompound nbtItem, boolean deployed) {
    if ((player != null) && (!field_70170_p.field_72995_K) && (nbtItem != null)) {
      nbtItem.func_74757_a("WITCBladeDeployed", deployed);
      if ((player instanceof EntityPlayerMP)) {
        ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
      }
    }
  }
}
