package com.emoniph.witchery.util;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public abstract class MultiItemBlock extends ItemBlock
{
  private String[] names = null;
  
  public MultiItemBlock(net.minecraft.block.Block block) {
    super(block);
    func_77656_e(0);
    func_77627_a(true);
  }
  
  private String[] internalGetNames() {
    if (names == null) {
      names = getNames();
    }
    return names;
  }
  
  protected abstract String[] getNames();
  
  public int func_77647_b(int par1) {
    return par1;
  }
  
  public String func_77667_c(ItemStack par1ItemStack) {
    int i = par1ItemStack.func_77960_j();
    String[] names = internalGetNames();
    if ((i < 0) || (i >= names.length)) {
      i = 0;
    }
    
    return super.func_77658_a() + "." + names[i];
  }
}
