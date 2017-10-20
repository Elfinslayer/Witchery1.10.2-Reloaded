package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemEntityLocator extends ItemBase
{
  @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  private IIcon[] icons;
  
  public ItemEntityLocator()
  {
    func_77656_e(0);
    func_77625_d(1);
  }
  



  @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  public void func_94581_a(IIconRegister iconRegister)
  {
    icons = new IIcon[33];
    for (int i = 0; i < icons.length; i++) {
      icons[i] = iconRegister.func_94245_a(func_111208_A() + i);
    }
    field_77791_bV = icons[0];
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advTooltips)
  {
    super.func_77624_a(stack, player, list, advTooltips);
    String entityID = ItemsTAGLOCK_KIT.getBoundEntityDisplayName(stack, Integer.valueOf(1));
    if ((entityID != null) && (!entityID.isEmpty())) {
      list.add(String.format(Witchery.resource("item.witcheryTaglockKit.boundto"), new Object[] { entityID }));
    } else {
      list.add(Witchery.resource("item.witcheryTaglockKit.unbound"));
    }
  }
  
  public IIcon func_77617_a(int damageValue)
  {
    if ((damageValue > 0) && (damageValue < icons.length)) {
      return icons[damageValue];
    }
    return icons[0];
  }
  

  public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
  {
    item.func_77964_b(0);
    return super.onDroppedByPlayer(item, player);
  }
  
  public void func_77663_a(ItemStack stack, World world, Entity player, int inventorySlot, boolean isHeldItem)
  {
    if ((world != null) && (field_72995_K) && (world.func_72820_D() % 10L == 2L)) {
      if (ItemsTAGLOCK_KIT.isTaglockPresent(stack, Integer.valueOf(1))) {
        double d3 = 0.0D;
        EntityLivingBase target = ItemsTAGLOCK_KIT.getBoundEntity(world, null, stack, Integer.valueOf(1));
        if ((target != null) && ((field_71093_bK == field_71093_bK) || ((field_71093_bK == 0) && (field_71093_bK == instancedimensionDreamID))))
        {

          double playerX = field_70165_t;
          double playerZ = field_70161_v;
          double d4 = field_70165_t - playerX;
          double d5 = field_70161_v - playerZ;
          double playerYaw = field_70177_z;
          playerYaw %= 360.0D;
          d3 = -((playerYaw - 90.0D) * 3.141592653589793D / 180.0D - Math.atan2(d5, d4));
        } else {
          d3 = Math.random() * 3.141592653589793D * 2.0D;
        }
        

        int SIZE = icons.length - 1;
        for (int i = (int)((d3 / 6.283185307179586D + 1.0D) * SIZE) % SIZE; i < 0; i = (i + SIZE) % SIZE) {}
        


        stack.func_77964_b(i + 1);
      } else {
        stack.func_77964_b(0);
      }
    }
  }
}
