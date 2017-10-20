package com.emoniph.witchery.item;

import com.emoniph.witchery.blocks.BlockPoppetShelf.TileEntityPoppetShelf;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemPoppetShelfCompass extends ItemBase
{
  @SideOnly(Side.CLIENT)
  private IIcon[] icons;
  
  public ItemPoppetShelfCompass()
  {
    func_77656_e(0);
  }
  




  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister iconRegister)
  {
    icons = new IIcon[6];
    for (int i = 0; i < icons.length; i++) {
      icons[i] = iconRegister.func_94245_a(func_111208_A() + "_" + i);
    }
    field_77791_bV = icons[0];
  }
  
  public IIcon func_77617_a(int damageValue)
  {
    if ((damageValue > 0) && (damageValue < icons.length)) {
      return icons[damageValue];
    }
    return icons[0];
  }
  

  public void func_77663_a(ItemStack stack, World world, Entity entity, int inventorySlot, boolean isHeldItem)
  {
    if ((field_72995_K) && (field_73012_v.nextInt(20) == 0)) {
      List<TileEntity> list = field_147482_g;
      double closest = Double.MAX_VALUE;
      for (TileEntity tile : list) {
        if ((tile instanceof BlockPoppetShelf.TileEntityPoppetShelf)) {
          double distSq = entity.func_70092_e(field_145851_c, field_70163_u, field_145849_e);
          if (distSq < closest) {
            closest = distSq;
          }
        }
      }
      if (closest < 64.0D) {
        stack.func_77964_b(5);
      } else if (closest < 256.0D) {
        stack.func_77964_b(4);
      } else if (closest < 1024.0D) {
        stack.func_77964_b(3);
      } else if (closest < 4096.0D) {
        stack.func_77964_b(2);
      } else if (closest < 16384.0D) {
        stack.func_77964_b(1);
      } else {
        stack.func_77964_b(0);
      }
    }
  }
  
  public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
  {
    item.func_77964_b(0);
    return super.onDroppedByPlayer(item, player);
  }
}
