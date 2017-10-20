package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemLeonardsUrn extends ItemBase
{
  @SideOnly(Side.CLIENT)
  private IIcon[] icons;
  
  public ItemLeonardsUrn()
  {
    func_77625_d(1);
    func_77656_e(0);
    func_77627_a(true);
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips)
  {
    if (stack != null) {
      String localText = Witchery.resource(func_77658_a() + ".tip");
      if (localText != null) {
        for (String s : localText.split("\n")) {
          if (!s.isEmpty()) {
            list.add(s);
          }
        }
      }
    }
  }
  



  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int damageValue)
  {
    return icons[Math.min(damageValue, icons.length - 1)];
  }
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister iconRegister)
  {
    icons = new IIcon[4];
    for (int i = 0; i < icons.length; i++) {
      icons[i] = iconRegister.func_94245_a(func_111208_A() + i);
    }
    field_77791_bV = icons[0];
  }
  
  public void func_150895_a(Item item, CreativeTabs tab, List items)
  {
    for (int i = 0; i < 4; i++) {
      items.add(new ItemStack(this, 1, i));
    }
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
  {
    if (!field_72995_K) {
      player.openGui(Witchery.instance, 8, world, 0, 0, 0);
    }
    return stack;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_77636_d(ItemStack stack)
  {
    return true;
  }
  
  public EnumRarity func_77613_e(ItemStack stack)
  {
    return EnumRarity.epic;
  }
  

  public static class InventoryLeonardsUrn
    extends InventoryBasic
  {
    protected String title;
    
    protected EntityPlayer player;
    
    protected ItemStack urnStack;
    protected boolean locked = false;
    
    public InventoryLeonardsUrn(EntityPlayer player) {
      this(player, null);
    }
    
    public InventoryLeonardsUrn(EntityPlayer player, ItemStack stack) {
      super(false, player.func_70694_bm() != null ? player.func_70694_bm().func_77960_j() + 1 : stack != null ? stack.func_77960_j() + 1 : 1);
      
      urnStack = stack;
      this.player = player;
      
      if (!hasInventory()) {
        createInventory();
      }
      
      readFromNBT();
    }
    
    public int func_70297_j_()
    {
      return 1;
    }
    
    public void func_70296_d()
    {
      super.func_70296_d();
      if (!locked) {
        writeToNBT();
      }
    }
    
    public void func_70295_k_()
    {
      readFromNBT();
    }
    
    public void func_70305_f()
    {
      writeToNBT();
    }
    
    public String func_145825_b()
    {
      return title;
    }
    
    protected boolean hasInventory() {
      if (urnStack != null) {
        return (urnStack != null) && (urnStack.func_77942_o()) && (urnStack.func_77978_p().func_74764_b("Inventory"));
      }
      
      return (player.func_70694_bm() != null) && (player.func_70694_bm().func_77942_o()) && (player.func_70694_bm().func_77978_p().func_74764_b("Inventory"));
    }
    

    protected void createInventory()
    {
      title = new String(urnStack != null ? urnStack.func_82833_r() : player.func_70694_bm().func_82833_r());
      
      writeToNBT();
    }
    
    protected void writeToNBT() {
      ItemStack urnStack = this.urnStack != null ? this.urnStack : player.func_70694_bm();
      
      if ((urnStack == null) || (urnStack.func_77973_b() != ItemsLEONARDS_URN)) {
        return;
      }
      
      if (!urnStack.func_77942_o()) {
        urnStack.func_77982_d(new NBTTagCompound());
      }
      
      NBTTagCompound nbtRoot = urnStack.func_77978_p();
      
      NBTTagCompound name = new NBTTagCompound();
      name.func_74778_a("Name", func_145825_b());
      nbtRoot.func_74782_a("display", name);
      
      NBTTagList itemList = new NBTTagList();
      for (int i = 0; i < func_70302_i_(); i++) {
        if (func_70301_a(i) != null) {
          NBTTagCompound slotEntry = new NBTTagCompound();
          slotEntry.func_74774_a("Slot", (byte)i);
          func_70301_a(i).func_77955_b(slotEntry);
          itemList.func_74742_a(slotEntry);
        }
      }
      
      NBTTagCompound inventory = new NBTTagCompound();
      inventory.func_74782_a("Items", itemList);
      nbtRoot.func_74782_a("Inventory", inventory);
    }
    
    protected void readFromNBT() {
      locked = true;
      
      ItemStack bag = urnStack != null ? urnStack : player.func_70694_bm();
      
      if ((bag != null) && (bag.func_77973_b() == ItemsLEONARDS_URN) && (bag.func_77942_o())) {
        NBTTagCompound nbtRoot = bag.func_77978_p();
        
        title = nbtRoot.func_74775_l("display").func_74779_i("Name");
        
        NBTTagList itemList = nbtRoot.func_74775_l("Inventory").func_150295_c("Items", 10);
        
        for (int i = 0; i < itemList.func_74745_c(); i++) {
          NBTTagCompound slotEntry = itemList.func_150305_b(i);
          int j = slotEntry.func_74771_c("Slot") & 0xFF;
          
          if ((j >= 0) && (j < func_70302_i_())) {
            func_70299_a(j, ItemStack.func_77949_a(slotEntry));
          }
        }
      }
      locked = false;
    }
  }
  

  public static class ContainerLeonardsUrn
    extends Container
  {
    private int numRows;
    private ItemStack bag;
    
    public ContainerLeonardsUrn(IInventory playerInventory, IInventory bagInventory, ItemStack bag)
    {
      numRows = 1;
      bagInventory.func_70295_k_();
      int size = bagInventory.func_70302_i_();
      
      int slot = 0;
      
      func_75146_a(new ItemLeonardsUrn.SlotLeonardsUrn(bagInventory, slot++, 80, 22));
      
      if (size >= 2) {
        func_75146_a(new ItemLeonardsUrn.SlotLeonardsUrn(bagInventory, slot++, 80, 70));
      }
      
      if (size >= 3) {
        func_75146_a(new ItemLeonardsUrn.SlotLeonardsUrn(bagInventory, slot++, 103, 46));
      }
      
      if (size >= 4) {
        func_75146_a(new ItemLeonardsUrn.SlotLeonardsUrn(bagInventory, slot++, 56, 46));
      }
      

      for (int row = 0; row < 3; row++) {
        for (int col = 0; col < 9; col++) {
          func_75146_a(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 103 + row * 18));
        }
      }
      

      for (int col = 0; col < 9; col++) {
        func_75146_a(new Slot(playerInventory, col, 8 + col * 18, 161));
      }
      
      this.bag = bag;
    }
    
    public boolean func_75145_c(EntityPlayer player)
    {
      ItemStack itemStack = null;
      if (player.func_71045_bC() != null) {
        itemStack = player.func_71045_bC();
      }
      return (itemStack != null) && (itemStack.func_77973_b() == ItemsLEONARDS_URN);
    }
    
    public ItemStack func_82846_b(EntityPlayer player, int index)
    {
      ItemStack returnStack = null;
      Slot slot = (Slot)field_75151_b.get(index);
      
      if ((slot != null) && (slot.func_75216_d())) {
        ItemStack itemStack = slot.func_75211_c();
        if (!ItemLeonardsUrn.SlotLeonardsUrn.isBrew(itemStack)) {
          return returnStack;
        }
        returnStack = itemStack.func_77946_l();
        
        if (index < numRows * 9) {
          if (!func_75135_a(itemStack, numRows * 9, field_75151_b.size(), true)) {
            return null;
          }
        } else if (!func_75135_a(itemStack, 0, numRows * 9, false)) {
          return null;
        }
        
        if (field_77994_a == 0) {
          slot.func_75215_d((ItemStack)null);
        } else {
          slot.func_75218_e();
        }
      }
      
      return returnStack;
    }
    
    protected boolean func_75135_a(ItemStack stack, int slotCount, int invSize, boolean upper)
    {
      boolean flag1 = false;
      int k = slotCount;
      
      if (upper) {
        k = invSize - 1;
      }
      



      int maxStackSize = upper ? stack.func_77976_d() : 1;
      
      if (stack.func_77985_e())
      {
        while ((field_77994_a > 0) && (((!upper) && (k < invSize)) || ((upper) && (k >= slotCount)))) {
          Slot slot = (Slot)field_75151_b.get(k);
          ItemStack itemstack1 = slot.func_75211_c();
          
          if ((itemstack1 != null) && (itemstack1.func_77973_b() == stack.func_77973_b()) && ((!stack.func_77981_g()) || (stack.func_77960_j() == itemstack1.func_77960_j())) && (ItemStack.func_77970_a(stack, itemstack1)))
          {


            int l = field_77994_a + field_77994_a;
            
            if (l <= maxStackSize) {
              field_77994_a = 0;
              field_77994_a = l;
              slot.func_75218_e();
              flag1 = true;
            } else if (field_77994_a < maxStackSize) {
              field_77994_a -= maxStackSize - field_77994_a;
              field_77994_a = maxStackSize;
              slot.func_75218_e();
              flag1 = true;
            }
          }
          
          if (upper) {
            k--;
          } else {
            k++;
          }
        }
      }
      
      if (field_77994_a > 0) {
        if (upper) {
          k = invSize - 1;
        } else {
          k = slotCount;
        }
        
        while (((!upper) && (k < invSize)) || ((upper) && (k >= slotCount))) {
          Slot slot = (Slot)field_75151_b.get(k);
          ItemStack itemstack1 = slot.func_75211_c();
          
          if (itemstack1 == null) {
            slot.func_75215_d(upper ? stack.func_77946_l() : stack.func_77979_a(1));
            slot.func_75218_e();
            if (upper) {
              field_77994_a = 0;
            }
            flag1 = true;
            break;
          }
          
          if (upper) {
            k--;
          } else {
            k++;
          }
        }
      }
      
      return flag1;
    }
  }
  

  private static class SlotLeonardsUrn
    extends Slot
  {
    public SlotLeonardsUrn(IInventory inventory, int slot, int x, int y)
    {
      super(slot, x, y);
    }
    
    public boolean func_75214_a(ItemStack stack)
    {
      return isBrew(stack);
    }
    
    public static boolean isBrew(ItemStack stack) {
      return (stack != null) && (ItemsBREW == stack.func_77973_b()) && (WitcheryBrewRegistry.INSTANCE.isSplash(stack.func_77978_p()));
    }
  }
}
