package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryCreativeTab;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.entity.EntityBolt;
import com.emoniph.witchery.integration.ModHookManager;
import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.InvUtil;
import com.emoniph.witchery.util.ItemUtil;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import com.emoniph.witchery.util.TransformCreature;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashSet;
import java.util.Random;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;


public class ItemHandBow
  extends ItemBow
{
  private static final int TICKS_TO_LOAD = 10;
  private static final String BOLT_TYPE_CURRENT = "WITCBoltTypeCurrent";
  private static final String BOLT_TYPE_PREFERRED = "WITCBoltTypePreferred";
  
  public ItemHandBow()
  {
    func_77656_e(768);
    func_77664_n();
    func_77637_a(WitcheryCreativeTab.INSTANCE);
  }
  
  public Item func_77655_b(String itemName)
  {
    ItemUtil.registerItem(this, itemName);
    return super.func_77655_b(itemName);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
  {
    ItemGeneral.SubItem loadedBoltType = getBoltType("WITCBoltTypeCurrent", stack);
    ExtendedPlayer playerEx = ExtendedPlayer.get(player);
    if (((loadedBoltType != null) || (player.func_70093_af())) && (playerEx.getCreatureType() == TransformCreature.NONE)) {
      player.func_71008_a(stack, func_77626_a(stack));
    }
    
    return stack;
  }
  
  public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
  {
    int elapsed = func_77626_a(stack) - count;
    ItemGeneral.SubItem loadedBoltType = getBoltType("WITCBoltTypeCurrent", stack);
    if ((player.func_70093_af()) && ((elapsed == 5) || (elapsed == 10) || (elapsed == 15))) {
      int boltTypesCount = getBoltTypesInInventory(field_71071_by, loadedBoltType);
      if (boltTypesCount > 0) {
        SoundEffect.WITCHERY_RANDOM_CLICK.playOnlyTo(player, 1.0F, 1.0F);
      }
    }
    super.onUsingTick(stack, player, count);
  }
  
  private int getBoltTypesInInventory(IInventory inventory, ItemGeneral.SubItem typeToIgnore) {
    HashSet<ItemGeneral.BoltType> typesFound = new HashSet();
    for (int slot = 0; slot < inventory.func_70302_i_(); slot++) {
      ItemStack stack = inventory.func_70301_a(slot);
      ItemGeneral.BoltType boltType = ItemGeneral.BoltType.getBolt(stack);
      if ((boltType != null) && (boltType != typeToIgnore)) {
        typesFound.add(boltType);
      }
    }
    return typesFound.size();
  }
  
  public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int count)
  {
    int elapsed = func_77626_a(stack) - count;
    ItemGeneral.SubItem loadedBoltType = getBoltType("WITCBoltTypeCurrent", stack);
    if ((loadedBoltType != null) && ((!player.func_70093_af()) || (elapsed < 10))) {
      if (launchBolt(stack, world, player, elapsed >= TimeUtil.secsToTicks(1) ? 20 : 19)) {
        setBoltType("WITCBoltTypeCurrent", stack, null);
      }
    } else if ((player.func_70093_af()) && (elapsed >= 10)) {
      int boltTypesCount = getBoltTypesInInventory(field_71071_by, loadedBoltType);
      if ((loadedBoltType != null) && (boltTypesCount > 0)) {
        ItemGeneral.SubItem boltTypeToUse = getNextBoltType(loadedBoltType);
        if (!InvUtil.hasItem(field_71071_by, ItemsGENERIC, damageValue)) {
          boltTypeToUse = null;
          ItemGeneral.SubItem currentBoltType = loadedBoltType;
          while ((currentBoltType = getNextBoltType(currentBoltType)) != loadedBoltType) {
            if (InvUtil.hasItem(field_71071_by, ItemsGENERIC, damageValue)) {
              boltTypeToUse = currentBoltType;
            }
          }
        }
        
        if (boltTypeToUse != null) {
          setBoltType("WITCBoltTypeCurrent", stack, boltTypeToUse);
          setBoltType("WITCBoltTypePreferred", stack, boltTypeToUse);
          SoundEffect.WITCHERY_RANDOM_WINDUP.playOnlyTo(player, 1.0F, 1.0F);
          
          if (!field_71075_bZ.field_75098_d) {
            InvUtil.consumeItem(field_71071_by, ItemsGENERIC, damageValue);
            ItemStack unloadedBolt = loadedBoltType.createStack();
            if (!field_71071_by.func_70441_a(unloadedBolt)) {
              EntityUtil.spawnEntityInWorld(world, new EntityItem(world, field_70165_t, field_70163_u, field_70161_v, unloadedBolt));
            }
          }
        }
      } else if ((loadedBoltType == null) && (boltTypesCount > 0)) {
        ItemGeneral.SubItem preferredBoltType = getBoltType("WITCBoltTypePreferred", stack);
        if (preferredBoltType == null) {
          preferredBoltType = ItemsGENERIC.itemBoltStake;
        }
        ItemGeneral.SubItem boltTypeToUse = preferredBoltType;
        if (!InvUtil.hasItem(field_71071_by, ItemsGENERIC, damageValue)) {
          boltTypeToUse = null;
          ItemGeneral.SubItem currentBoltType = preferredBoltType;
          while ((currentBoltType = getNextBoltType(currentBoltType)) != preferredBoltType) {
            if (InvUtil.hasItem(field_71071_by, ItemsGENERIC, damageValue)) {
              boltTypeToUse = currentBoltType;
            }
          }
        }
        
        if (boltTypeToUse != null) {
          SoundEffect.WITCHERY_RANDOM_WINDUP.playOnlyTo(player, 1.0F, 1.0F);
          setBoltType("WITCBoltTypeCurrent", stack, boltTypeToUse);
          if (!field_71075_bZ.field_75098_d) {
            InvUtil.consumeItem(field_71071_by, ItemsGENERIC, damageValue);
          }
        }
      }
    }
  }
  
  private boolean launchBolt(ItemStack stack, World world, EntityPlayer player, int ticks) {
    boolean isInfinite = (EnchantmentHelper.func_77506_a(field_77342_wfield_77352_x, stack) > 0) && (field_73012_v.nextDouble() < 0.25D);
    
    ItemGeneral.SubItem boltType = getBoltType("WITCBoltTypeCurrent", stack);
    
    if (boltType != null) {
      float f = ticks / 20.0F;
      f = (f * f + f * 2.0F) / 3.0F;
      
      if (f < 0.1D) {
        return true;
      }
      
      if (f > 1.0F) {
        f = 1.0F;
      }
      
      int boltID = 0;
      int boltCount = 1;
      float arcStart = 0.0F;
      float arcInc = 0.0F;
      float damage = 2.0F;
      
      if (boltType == ItemsGENERIC.itemBoltSilver) {
        boltID = 4;
      } else if (boltType == ItemsGENERIC.itemBoltHoly) {
        boltID = 3;
      } else if (boltType == ItemsGENERIC.itemBoltAntiMagic) {
        if (ItemHunterClothes.isFullSetWorn(player, false)) {
          boltID = 2;
          Witchery.modHooks.reducePowerLevels(player, 1.0F);
        } else {
          boltID = 1;
        }
      } else if (boltType == ItemsGENERIC.itemBoltSplitting) {
        boltCount = 3;
        arcStart = -20.0F;
        arcInc = 20.0F;
        damage = 1.0F;
      }
      
      for (int i = 0; i < boltCount; i++) {
        EntityBolt bolt = new EntityBolt(world, player, f * 2.0F, arcStart + i * arcInc);
        bolt.setShooter(player);
        bolt.setBoltType(boltID);
        bolt.setDamage(damage);
        canBePickedUp = ((isInfinite) || (field_71075_bZ.field_75098_d) ? 0 : 1);
        
        if (f == 1.0F) {
          bolt.setIsCritical(true);
        }
        
        if (boltType != ItemsGENERIC.itemBoltAntiMagic) {
          int powerBonus = EnchantmentHelper.func_77506_a(field_77345_tfield_77352_x, stack);
          if (powerBonus > 0) {
            bolt.setDamage(bolt.getDamage() + powerBonus * 0.5D + 0.5D);
          }
          
          int knockbackBonus = EnchantmentHelper.func_77506_a(field_77344_ufield_77352_x, stack);
          
          if (knockbackBonus > 0) {
            bolt.setKnockbackStrength(knockbackBonus);
          }
          
          if (EnchantmentHelper.func_77506_a(field_77343_vfield_77352_x, stack) > 0) {
            bolt.func_70015_d(100);
          }
        }
        
        EntityUtil.spawnEntityInWorld(world, bolt);
        EntityUtil.correctProjectileTrackerSync(world, bolt);
      }
      


      stack.func_77972_a(2, player);
      
      world.func_72956_a(player, "random.bow", 1.0F, 1.0F / (field_77697_d.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
      
      if ((isInfinite) && (!field_72995_K)) {
        if ((player instanceof EntityPlayerMP)) {
          ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
        }
        
        return false;
      }
    }
    return true;
  }
  
  public ItemStack func_77654_b(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
  {
    return par1ItemStack;
  }
  
  public int func_77626_a(ItemStack par1ItemStack)
  {
    return 72000;
  }
  
  public EnumAction func_77661_b(ItemStack par1ItemStack)
  {
    return EnumAction.bow;
  }
  
  public int func_77619_b()
  {
    return 1;
  }
  
  public static ItemGeneral.BoltType getLoadedBoltType(ItemStack stack) {
    ItemGeneral.SubItem boltType = getBoltType("WITCBoltTypeCurrent", stack);
    if (boltType != null) {
      return (ItemGeneral.BoltType)boltType;
    }
    return null;
  }
  




  private static ItemGeneral.SubItem getBoltType(String key, ItemStack stack)
  {
    if (!stack.func_77942_o()) {
      return null;
    }
    NBTTagCompound nbtRoot = stack.func_77978_p();
    int boltID = nbtRoot.func_74762_e(key);
    return intToBoltType(boltID);
  }
  
  private void setBoltType(String key, ItemStack stack, ItemGeneral.SubItem boltType) {
    if (!stack.func_77942_o()) {
      stack.func_77982_d(new NBTTagCompound());
    }
    NBTTagCompound nbtRoot = stack.func_77978_p();
    int boltID = boltTypeToInt(boltType);
    nbtRoot.func_74768_a(key, boltID);
  }
  
  private static ItemGeneral.SubItem[] BOLT_TYPES = null;
  
  private static ItemGeneral.SubItem[] getBoltTypes() {
    if (BOLT_TYPES == null) {
      BOLT_TYPES = new ItemGeneral.SubItem[] { ItemsGENERIC.itemBoltStake, ItemsGENERIC.itemBoltAntiMagic, ItemsGENERIC.itemBoltHoly, ItemsGENERIC.itemBoltSplitting, ItemsGENERIC.itemBoltSilver };
    }
    





    return BOLT_TYPES;
  }
  
  private static ItemGeneral.SubItem intToBoltType(int boltID) {
    if ((boltID > 0) && (boltID <= getBoltTypes().length)) {
      return BOLT_TYPES[(boltID - 1)];
    }
    return null;
  }
  
  private int boltTypeToInt(ItemGeneral.SubItem boltType)
  {
    for (int i = 0; i < getBoltTypes().length; i++) {
      if (getBoltTypes()[i] == boltType) {
        return i + 1;
      }
    }
    return 0;
  }
  

  private ItemGeneral.SubItem getNextBoltType(ItemGeneral.SubItem boltType) { return intToBoltType(getNextBoltTypeID(boltTypeToInt(boltType))); }
  
  private int getNextBoltTypeID(int boltID) {
    
    if (boltID > getBoltTypes().length) {
      boltID = 1;
    }
    return boltID;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister par1IconRegister)
  {
    field_77791_bV = par1IconRegister.func_94245_a(func_111208_A());
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_94599_c(int par1)
  {
    return field_77791_bV;
  }
}
