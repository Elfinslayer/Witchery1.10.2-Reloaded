package com.emoniph.witchery.brewing;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;

public class ItemBrewBucket extends com.emoniph.witchery.item.ItemBase
{
  @SideOnly(Side.CLIENT)
  private net.minecraft.util.IIcon overlayIcon;
  
  public ItemBrewBucket()
  {
    func_77625_d(1);
    func_77656_e(0);
    registerWithCreativeTab = false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_82790_a(ItemStack stack, int pass) {
    if (pass == 0) {
      net.minecraft.nbt.NBTTagCompound nbtRoot = stack.func_77978_p();
      int color = nbtRoot != null ? nbtRoot.func_74762_e("Color") : -16744448;
      return color;
    }
    return super.func_82790_a(stack, pass);
  }
  




  @SideOnly(Side.CLIENT)
  public void func_94581_a(net.minecraft.client.renderer.texture.IIconRegister iconRegister)
  {
    super.func_94581_a(iconRegister);
    overlayIcon = iconRegister.func_94245_a(func_111208_A() + "_overlay");
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_77623_v() {
    return true;
  }
  
  public net.minecraft.util.IIcon func_77618_c(int damage, int pass) {
    if (pass == 0) {
      return overlayIcon;
    }
    return field_77791_bV;
  }
  

  public boolean hasEffect(ItemStack par1ItemStack, int pass)
  {
    return pass == 0;
  }
}
