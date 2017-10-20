package com.emoniph.witchery.brewing;

import com.emoniph.witchery.util.Log;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;



public class BrewItemKey
{
  public final Item ITEM;
  public final int DAMAGE;
  private final int ITEM_HASH;
  
  public BrewItemKey(Item item)
  {
    this(item, 0);
  }
  
  public BrewItemKey(Block block) {
    this(block, 0);
  }
  
  public BrewItemKey(Block block, int damage) {
    this(Item.func_150898_a(block), damage);
  }
  
  public BrewItemKey(Item item, int damage) {
    ITEM = item;
    DAMAGE = damage;
    if (ITEM != null) {
      if (ITEM.func_77658_a() != null) {
        ITEM_HASH = ITEM.func_77658_a().hashCode();
      } else {
        Log.instance().warning(String.format("unlocalizedname=null for item passed to BrewItemKey constructor (another mod cleared it?)", new Object[0]));
        ITEM_HASH = "".hashCode();
      }
    } else {
      Log.instance().warning("item=null passed to BrewItemKey constructor, block to item map must be busted somehow (tweaking blocks?).");
      ITEM_HASH = "".hashCode();
    }
  }
  
  public ItemStack toStack() {
    return new ItemStack(ITEM, 1, DAMAGE);
  }
  
  public int hashCode()
  {
    int result = 17;
    result = 37 * result + ITEM_HASH;
    
    return result;
  }
  
  public boolean equals(Object obj)
  {
    if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    
    if (obj == this) {
      return true;
    }
    
    BrewItemKey other = (BrewItemKey)obj;
    return (ITEM == ITEM) && ((DAMAGE == 32767) || (DAMAGE == 32767) || (DAMAGE == DAMAGE));
  }
  
  public static BrewItemKey fromStack(ItemStack stack) {
    return new BrewItemKey(stack.func_77973_b(), stack.func_77960_j());
  }
}
