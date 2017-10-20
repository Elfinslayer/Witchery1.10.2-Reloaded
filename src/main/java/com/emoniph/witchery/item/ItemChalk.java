package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.util.BlockSide;
import com.emoniph.witchery.util.SoundEffect;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemChalk extends ItemBase
{
  private static final int MAX_DAMAGE = 64;
  private static final int DAMAGE_PER_USE = 1;
  private Block block;
  
  public ItemChalk(Block block)
  {
    this.block = block;
    
    func_77625_d(64);
    func_77656_e(64);
    setNoRepair();
  }
  
  public int getItemStackLimit(ItemStack stack)
  {
    return stack.func_77951_h() ? 1 : field_77777_bU;
  }
  
  public void setDamage(ItemStack stack, int damage)
  {
    super.setDamage(stack, damage);
  }
  
  public static boolean drawGlyph(World world, int posX, int posY, int posZ, int side, Block block, EntityPlayer player) {
    boolean chalkUsed = false;
    if (block != BlocksCIRCLE) {
      Block overBlock = world.func_147439_a(posX, posY, posZ);
      if (overBlock == block) {
        world.func_72921_c(posX, posY, posZ, field_73012_v.nextInt(12), 3);
        chalkUsed = true;
      } else if ((overBlock == BlocksGLYPH_RITUAL) || (overBlock == BlocksGLYPH_OTHERWHERE) || (overBlock == BlocksGLYPH_INFERNAL)) {
        world.func_147465_d(posX, posY, posZ, block, field_73012_v.nextInt(12), 3);
        world.func_147471_g(posX, posY, posZ);
        chalkUsed = true;
      } else if ((BlockSide.TOP.isEqual(side)) && (BlocksGLYPH_RITUAL.func_149718_j(world, posX, posY + 1, posZ)) && 
        (com.emoniph.witchery.util.BlockUtil.isReplaceableBlock(world, posX, posY + 1, posZ, player))) {
        world.func_147465_d(posX, posY + 1, posZ, block, field_73012_v.nextInt(12), 3);
        world.func_147471_g(posX, posY + 1, posZ);
        chalkUsed = true;
      }
      
    }
    else if ((world.func_147439_a(posX, posY, posZ) != block) && (BlockSide.TOP.isEqual(side)) && (BlocksCIRCLE.func_149718_j(world, posX, posY + 1, posZ))) {
      world.func_147449_b(posX, posY + 1, posZ, block);
      world.func_147471_g(posX, posY + 1, posZ);
      chalkUsed = true;
    }
    
    if (chalkUsed) {
      SoundEffect.WITCHERY_RANDOM_CHALK.playAt(world, posX, posY, posZ, 1.0F, 1.0F);
    }
    return chalkUsed;
  }
  
  public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ)
  {
    if (!field_72995_K) {
      boolean chalkUsed = drawGlyph(world, posX, posY, posZ, side, block, player);
      
      if ((chalkUsed) && (!field_71075_bZ.field_75098_d)) {
        stack.func_77972_a(1, player);
        if (field_77994_a > 1) {
          ItemStack newStack = ItemStack.func_77944_b(stack);
          field_77994_a -= 1;
          newStack.func_77964_b(0);
          if (!field_71071_by.func_70441_a(newStack)) {
            world.func_72838_d(new EntityItem(world, field_70165_t + 0.5D, field_70163_u + 1.5D, field_70161_v + 0.5D, newStack));
          } else if ((player instanceof EntityPlayerMP)) {
            ((EntityPlayerMP)player).func_71120_a(field_71069_bz);
          }
          
          field_77994_a = 1;
        }
        if (!stack.func_77942_o()) {
          stack.func_77982_d(new NBTTagCompound());
        }
        stack.func_77978_p().func_74768_a("PseudoDamage", stack.func_77960_j());
      }
    }
    return false;
  }
}
