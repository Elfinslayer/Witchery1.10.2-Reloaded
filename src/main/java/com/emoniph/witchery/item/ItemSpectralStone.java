package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.entity.EntityBanshee;
import com.emoniph.witchery.entity.EntityPoltergeist;
import com.emoniph.witchery.entity.EntitySpectre;
import com.emoniph.witchery.entity.EntitySummonedUndead;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemSpectralStone extends ItemBase
{
  @SideOnly(Side.CLIENT)
  IIcon item0;
  @SideOnly(Side.CLIENT)
  IIcon item1;
  @SideOnly(Side.CLIENT)
  IIcon item2;
  @SideOnly(Side.CLIENT)
  IIcon item3;
  private static final int SPECTRE = 1;
  private static final int BANSHEE = 2;
  private static final int POLTERGEIST = 3;
  private static final int TICKS_TO_ACTIVATE = 40;
  
  public ItemSpectralStone()
  {
    func_77625_d(16);
    func_77627_a(true);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_150895_a(Item item, net.minecraft.creativetab.CreativeTabs tab, List itemList)
  {
    itemList.add(new ItemStack(item, 1, 0));
    itemList.add(new ItemStack(item, 1, 17));
    itemList.add(new ItemStack(item, 1, 18));
    itemList.add(new ItemStack(item, 1, 19));
  }
  









  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister iconRegister)
  {
    super.func_94581_a(iconRegister);
    item0 = field_77791_bV;
    item1 = iconRegister.func_94245_a(func_111208_A() + ".spectre");
    item2 = iconRegister.func_94245_a(func_111208_A() + ".banshee");
    item3 = iconRegister.func_94245_a(func_111208_A() + ".poltergeist");
  }
  


  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int damage)
  {
    switch (getBeingFromMeta(damage)) {
    case 0: 
    default: 
      return item0;
    case 1: 
      return item1;
    case 2: 
      return item2;
    }
    return item3;
  }
  

  public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean extraTip)
  {
    int creature = getBeingFromMeta(stack.func_77960_j());
    int quantity = Math.min(getQuantityFromMeta(stack.func_77960_j()), 4);
    switch (creature) {
    case 1: 
      list.add(String.format("%s: %d", new Object[] { Witchery.resource("entity.witchery.spectre.name"), Integer.valueOf(quantity) }));
      break;
    case 2: 
      list.add(String.format("%s: %d", new Object[] { Witchery.resource("entity.witchery.banshee.name"), Integer.valueOf(quantity) }));
      break;
    case 3: 
      list.add(String.format("%s: %d", new Object[] { Witchery.resource("entity.witchery.poltergeist.name"), Integer.valueOf(quantity) }));
    }
    
  }
  
  public boolean func_77636_d(ItemStack stack)
  {
    return true;
  }
  
  public static int metaFromCreature(Class<? extends EntitySummonedUndead> creatureType, int quantity) {
    if (creatureType == EntitySpectre.class)
      return 0x1 | quantity << 4;
    if (creatureType == EntityBanshee.class)
      return 0x2 | quantity << 4;
    if (creatureType == EntityPoltergeist.class) {
      return 0x3 | quantity << 4;
    }
    return 0;
  }
  





  private int getBeingFromMeta(int meta)
  {
    int critter = meta & 0xF;
    if ((critter < 0) || (critter > 15)) {
      critter = 0;
    }
    return critter;
  }
  
  private int getQuantityFromMeta(int meta) {
    int quantity = meta >>> 4 & 0x7;
    if ((quantity < 0) || (quantity >= 8)) {
      quantity = 0;
    }
    return quantity;
  }
  

  @SideOnly(Side.CLIENT)
  public EnumRarity func_77613_e(ItemStack stack)
  {
    return EnumRarity.rare;
  }
  
  public net.minecraft.item.EnumAction func_77661_b(ItemStack stack)
  {
    return net.minecraft.item.EnumAction.bow;
  }
  
  public int func_77626_a(ItemStack stack)
  {
    return com.emoniph.witchery.util.TimeUtil.secsToTicks(20);
  }
  


  public void onUsingTick(ItemStack stack, EntityPlayer player, int countdown)
  {
    World world = field_70170_p;
    int elapsedTicks = func_77626_a(stack) - countdown;
    if (elapsedTicks == 40) {
      SoundEffect.NOTE_PLING.playOnlyTo(player);
    }
  }
  
  public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int countdown)
  {
    int elapsedTicks = func_77626_a(stack) - countdown;
    int creature = getBeingFromMeta(stack.func_77960_j());
    int quantity = Math.min(getQuantityFromMeta(stack.func_77960_j()), 3);
    if ((elapsedTicks >= 40) && (creature > 0) && (quantity > 0)) {
      net.minecraft.util.MovingObjectPosition mop = com.emoniph.witchery.infusion.infusions.InfusionOtherwhere.doCustomRayTrace(world, player, true, 16.0D);
      int[] coords = com.emoniph.witchery.util.BlockUtil.getBlockCoords(world, mop, true);
      Class<? extends EntitySummonedUndead> theClass = null;
      if (coords != null) {
        switch (creature) {
        case 1: 
          theClass = EntitySpectre.class;
          break;
        case 2: 
          theClass = EntityBanshee.class;
          break;
        case 3: 
          theClass = EntityPoltergeist.class;
          break;
        default: 
          SoundEffect.NOTE_SNARE.playOnlyTo(player);
          return;
        }
        
        for (int i = 0; i < quantity; i++) {
          EntitySummonedUndead entity = (EntitySummonedUndead)com.emoniph.witchery.infusion.Infusion.spawnCreature(world, theClass, coords[0], coords[1], coords[2], null, 0, 1, com.emoniph.witchery.util.ParticleEffect.INSTANT_SPELL, null);
          if (entity != null) {
            com.emoniph.witchery.util.CreatureUtil.spawnWithEgg(entity, true);
            entity.setSummoner(player.func_70005_c_());
            com.emoniph.witchery.brewing.potions.PotionEnslaved.setEnslaverForMob(entity, player);
          }
        }
        if (!field_71075_bZ.field_75098_d) {
          if (field_77994_a > 1) {
            ItemStack newStack = stack.func_77979_a(1);
            newStack.func_77964_b(0);
            if (!field_71071_by.func_70441_a(newStack)) {
              if (!field_72995_K) {
                world.func_72838_d(new net.minecraft.entity.item.EntityItem(world, field_70165_t + 0.5D, field_70163_u + 1.5D, field_70161_v + 0.5D, newStack));
              }
            } else if ((player instanceof EntityPlayerMP)) {
              ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
            }
          } else {
            stack.func_77964_b(0);
          }
        }
      } else {
        SoundEffect.NOTE_SNARE.playOnlyTo(player, 1.0F, 1.0F);
      }
    }
    else {
      SoundEffect.NOTE_SNARE.playOnlyTo(player, 1.0F, 1.0F);
    }
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
  {
    player.func_71008_a(stack, func_77626_a(stack));
    return stack;
  }
}
