package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBrewFuel extends ItemBase implements cpw.mods.fml.common.IFuelHandler
{
  @SideOnly(Side.CLIENT)
  protected IIcon itemIconOverlay;
  
  public ItemBrewFuel()
  {
    func_77625_d(64);
    func_77656_e(0);
    func_77627_a(true);
  }
  
  public net.minecraft.item.Item func_77655_b(String itemName)
  {
    GameRegistry.registerFuelHandler(this);
    return super.func_77655_b(itemName);
  }
  
  @SideOnly(Side.CLIENT)
  public boolean hasEffect(ItemStack stack, int pass)
  {
    return pass == 0;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_77623_v()
  {
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(ItemStack stack, int pass)
  {
    if (pass == 0) {
      return itemIconOverlay;
    }
    return field_77791_bV;
  }
  




  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister iconRegister)
  {
    super.func_94581_a(iconRegister);
    itemIconOverlay = iconRegister.func_94245_a("witchery:brew_overlay");
  }
  
  private static final int[] COLORS = { 16754270, 16748088, 16740620, 14702848 };
  
  @SideOnly(Side.CLIENT)
  public int func_82790_a(ItemStack stack, int pass)
  {
    if (pass == 0) {
      int color = Math.min(stack.func_77960_j(), COLORS.length);
      return COLORS[color];
    }
    return super.func_82790_a(stack, pass);
  }
  

  @SideOnly(Side.CLIENT)
  public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean expanded)
  {
    String localText = Witchery.resource("item.witchery:brew.fuel." + Math.min(stack.func_77960_j(), BURN_TIMES.length));
    
    if (localText != null) {
      for (String s : localText.split("\n")) {
        if (!s.isEmpty()) {
          list.add(s);
        }
      }
    }
  }
  
  private static final int[] BURN_TIMES = { 2400, 5000, 10000, 50000 };
  
  public int getBurnTime(ItemStack fuel)
  {
    if (fuel.func_77973_b() == this) {
      int burnTime = BURN_TIMES[Math.min(fuel.func_77960_j(), BURN_TIMES.length)];
      return burnTime;
    }
    return 0;
  }
}
