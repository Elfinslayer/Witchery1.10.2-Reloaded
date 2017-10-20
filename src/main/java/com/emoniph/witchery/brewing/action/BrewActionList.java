package com.emoniph.witchery.brewing.action;

import com.emoniph.witchery.brewing.BrewItemKey;
import com.emoniph.witchery.brewing.ModifiersEffect;
import com.emoniph.witchery.brewing.ModifiersRitual;
import com.emoniph.witchery.util.BlockPosition;
import java.util.ArrayList;
import java.util.Hashtable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;



public class BrewActionList
{
  private final NBTTagCompound nbtBrew;
  public final ArrayList<BrewAction> actions = new ArrayList();
  public final ArrayList<ItemStack> items = new ArrayList();
  
  public BrewActionList(NBTTagCompound nbtBrew, Hashtable<BrewItemKey, BrewAction> actionRegistry) {
    this.nbtBrew = nbtBrew;
    if (nbtBrew != null) {
      NBTTagList nbtItems = nbtBrew.func_150295_c("Items", 10);
      int i = 0; for (int count = nbtItems.func_74745_c(); i < count; i++) {
        NBTTagCompound nbtItem = nbtItems.func_150305_b(i);
        ItemStack stack = ItemStack.func_77949_a(nbtItem);
        BrewAction brewAction = (BrewAction)actionRegistry.get(BrewItemKey.fromStack(stack));
        if (brewAction != null) {
          actions.add(brewAction);
          items.add(stack);
        }
      }
    }
  }
  
  public ItemStack getTopItemStack() {
    return size() > 0 ? (ItemStack)items.get(size() - 1) : null;
  }
  
  public BrewAction getTopAction() {
    return size() > 0 ? (BrewAction)actions.get(size() - 1) : null;
  }
  
  public int size() {
    return actions.size();
  }
  
  public NBTTagCompound getTagCompound() {
    return nbtBrew;
  }
  
  public void nullifyItems(BrewAction brewAction, NBTTagList nbtItems) {
    brewAction.processNullifaction(actions, nbtItems);
  }
  
  public void applyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers) {
    for (int i = 0; i < actions.size(); i++) {
      BrewAction action = (BrewAction)actions.get(i);
      if (action.augmentEffectLevels(effectLevel)) {
        action.augmentEffectModifiers(modifiers);
        action.applyToEntity(world, targetEntity, modifiers, (ItemStack)items.get(i));
      }
    }
  }
  
  public void applyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers)
  {
    for (int i = 0; i < actions.size(); i++) {
      BrewAction action = (BrewAction)actions.get(i);
      if (action.augmentEffectLevels(effectLevel)) {
        action.augmentEffectModifiers(modifiers);
        action.applyToBlock(world, x, y, z, side, radius, modifiers, (ItemStack)items.get(i));
      }
    }
  }
  
  public void prepareRitual(World world, int x, int y, int z, ModifiersRitual modifiers) {
    for (int i = 0; i < actions.size(); i++) {
      BrewAction action = (BrewAction)actions.get(i);
      action.prepareRitual(world, x, y, z, modifiers, (ItemStack)items.get(i));
    }
  }
  
  public void applyRitualToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersRitual ritualModifiers, ModifiersEffect modifiers)
  {
    for (int i = 0; i < actions.size(); i++) {
      BrewAction action = (BrewAction)actions.get(i);
      if (action.augmentEffectLevels(effectLevel)) {
        action.augmentEffectModifiers(modifiers);
        action.applyRitualToBlock(world, x, y, z, side, radius, ritualModifiers, modifiers, (ItemStack)items.get(i));
      }
    }
  }
  
  public void applyRitualToEnitity(World world, EntityLivingBase targetEntity, ModifiersRitual ritualModifiers, ModifiersEffect modifiers)
  {
    for (int i = 0; i < actions.size(); i++) {
      BrewAction action = (BrewAction)actions.get(i);
      if (action.augmentEffectLevels(effectLevel)) {
        action.augmentEffectModifiers(modifiers);
        action.applyRitualToEntity(world, targetEntity, ritualModifiers, modifiers, (ItemStack)items.get(i));
      }
    }
  }
  
  public boolean isTargetLocationValid(MinecraftServer server, World world, int x, int y, int z, BlockPosition target, ModifiersRitual modifiers) {
    for (int i = 0; i < actions.size(); i++) {
      BrewAction action = (BrewAction)actions.get(i);
      if (!action.isRitualTargetLocationValid(server, world, x, y, z, target, modifiers)) {
        return false;
      }
    }
    return true;
  }
}
