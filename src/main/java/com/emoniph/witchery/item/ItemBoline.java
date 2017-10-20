package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryCreativeTab;
import com.emoniph.witchery.util.BlockUtil;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class ItemBoline extends ItemSword
{
  public static final Block[] blocksEffectiveAgainst = { Blocks.field_150344_f, Blocks.field_150342_X, Blocks.field_150344_f, Blocks.field_150486_ae, Blocks.field_150333_U, Blocks.field_150423_aK, Blocks.field_150428_aP };
  
  private float effectiveWeaponDamage;
  
  public ItemBoline()
  {
    super(Item.ToolMaterial.IRON);
    effectiveWeaponDamage = (4.0F + Item.ToolMaterial.WOOD.func_78000_c());
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
    return EnumRarity.uncommon;
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean moreTips)
  {
    String localText = Witchery.resource("item.witchery:boline.tip");
    if (localText != null) {
      for (String s : localText.split("\n")) {
        if (!s.isEmpty()) {
          list.add(s);
        }
      }
    }
  }
  
  public Multimap func_111205_h()
  {
    Multimap multimap = HashMultimap.create();
    multimap.put(SharedMonsterAttributes.field_111264_e.func_111108_a(), new AttributeModifier(field_111210_e, "Weapon modifier", effectiveWeaponDamage, 0));
    
    return multimap;
  }
  
  public float func_150931_i()
  {
    return Item.ToolMaterial.WOOD.func_78000_c();
  }
  

  public boolean func_150894_a(ItemStack stack, World world, Block block, int posX, int posY, int posZ, EntityLivingBase entity)
  {
    if ((block != null) && (block != Blocks.field_150362_t) && (block != Blocks.field_150321_G) && (block != Blocks.field_150329_H) && (block != Blocks.field_150395_bd) && (block != Blocks.field_150473_bD) && (!(block instanceof IShearable)))
    {

      if (block.func_149712_f(world, posX, posY, posZ) != 0.0F) {
        stack.func_77972_a(2, entity);
      }
    }
    
    return true;
  }
  
  public boolean canHarvestBlock(Block par1Block, ItemStack stack)
  {
    return (par1Block == BlocksWEB) || (par1Block == Blocks.field_150321_G) || (par1Block == Blocks.field_150488_af) || (par1Block == Blocks.field_150473_bD);
  }
  
  public float func_150893_a(ItemStack stack, Block block)
  {
    if ((block == BlocksWEB) || (block == Blocks.field_150321_G) || (block == Blocks.field_150362_t))
      return 15.0F;
    if ((block == Blocks.field_150325_L) || (block == BlocksTRAPPED_PLANT)) {
      return 5.0F;
    }
    return super.func_150893_a(stack, block);
  }
  

  public boolean func_111207_a(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity)
  {
    if (field_70170_p.field_72995_K) {
      return false;
    }
    if ((entity instanceof IShearable)) {
      IShearable target = (IShearable)entity;
      if (target.isShearable(itemstack, field_70170_p, (int)field_70165_t, (int)field_70163_u, (int)field_70161_v)) {
        ArrayList<ItemStack> drops = target.onSheared(itemstack, field_70170_p, (int)field_70165_t, (int)field_70163_u, (int)field_70161_v, EnchantmentHelper.func_77506_a(field_77346_sfield_77352_x, itemstack));
        

        Random rand = new Random();
        for (ItemStack stack : drops) {
          EntityItem ent = entity.func_70099_a(stack, 1.0F);
          field_70181_x += rand.nextFloat() * 0.05F;
          field_70159_w += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
          field_70179_y += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
        }
        itemstack.func_77972_a(1, entity);
      }
      return true;
    }
    return false;
  }
  
  public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player)
  {
    if (field_70170_p.field_72995_K) {
      return false;
    }
    
    World world = field_70170_p;
    Block block = BlockUtil.getBlock(world, x, y, z);
    if (block == null)
      return false;
    if (block == Blocks.field_150321_G) {
      world.func_147468_f(x, y, z);
      world.func_72838_d(new EntityItem(world, 0.5D + x, 0.5D + y, 0.5D + z, new ItemStack(block)));
      func_150894_a(itemstack, world, block, x, y, z, player);
      if (field_77994_a == 0) {
        player.func_71028_bD();
      }
      return true; }
    if (block == BlocksTRAPPED_PLANT) {
      int meta = world.func_72805_g(x, y, z);
      world.func_147468_f(x, y, z);
      world.func_72838_d(new EntityItem(world, 0.5D + x, 0.5D + y, 0.5D + z, new ItemStack(block, 1, meta)));
      func_150894_a(itemstack, world, block, x, y, z, player);
      if (field_77994_a == 0) {
        player.func_71028_bD();
      }
      return true; }
    if (block == BlocksBLOOD_ROSE) {
      int meta = world.func_72805_g(x, y, z);
      world.func_147468_f(x, y, z);
      world.func_72838_d(new EntityItem(world, 0.5D + x, 0.5D + y, 0.5D + z, new ItemStack(block, 1, meta)));
      func_150894_a(itemstack, world, block, x, y, z, player);
      if (field_77994_a == 0) {
        player.func_71028_bD();
      }
      return true; }
    if ((block instanceof IShearable)) {
      IShearable target = (IShearable)block;
      if (target.isShearable(itemstack, field_70170_p, x, y, z)) {
        ArrayList<ItemStack> drops = target.onSheared(itemstack, field_70170_p, x, y, z, EnchantmentHelper.func_77506_a(field_77346_sfield_77352_x, itemstack));
        
        Random rand = new Random();
        
        for (ItemStack stack : drops) {
          float f = 0.7F;
          double d = rand.nextFloat() * f + (1.0F - f) * 0.5D;
          double d1 = rand.nextFloat() * f + (1.0F - f) * 0.5D;
          double d2 = rand.nextFloat() * f + (1.0F - f) * 0.5D;
          EntityItem entityitem = new EntityItem(field_70170_p, x + d, y + d1, z + d2, stack);
          field_145804_b = 10;
          field_70170_p.func_72838_d(entityitem);
        }
        
        itemstack.func_77972_a(1, player);
      }
    }
    return false;
  }
}
