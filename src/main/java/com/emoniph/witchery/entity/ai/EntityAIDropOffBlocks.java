package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.entity.EntityGoblin;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class EntityAIDropOffBlocks extends net.minecraft.entity.ai.EntityAIBase
{
  protected final EntityGoblin entity;
  protected final double range;
  private TileEntity targetTile = null;
  
  public EntityAIDropOffBlocks(EntityGoblin entity, double range) {
    this.entity = entity;
    this.range = range;
    func_75248_a(7);
  }
  
  public boolean func_75250_a() {
    if (((entity != null) && (!entity.isWorshipping()) && (entity.func_70694_bm() == null)) || (!entity.func_110167_bD()) || ((entity.func_70694_bm().func_77973_b() instanceof ItemTool))) {
      return false;
    }
    
    if ((targetTile != null) && (!targetTile.func_145837_r()) && (entity.func_70661_as().func_75488_a(targetTile.field_145851_c, targetTile.field_145848_d, targetTile.field_145849_e) != null)) {
      return true;
    }
    targetTile = null;
    

    if (entity.field_70170_p.field_73012_v.nextInt(60) != 0) {
      return false;
    }
    
    setTargetTile();
    
    return targetTile != null;
  }
  
  public void func_75249_e() {}
  
  private void setTargetTile()
  {
    targetTile = null;
    ArrayList<IInventory> chests = new ArrayList();
    double bestDist = Double.MAX_VALUE;
    double RANGE_SQ = range * range;
    for (int i = 0; i < entity.field_70170_p.field_147482_g.size(); i++) {
      try {
        Object te = entity.field_70170_p.field_147482_g.get(i);
        if ((te != null) && ((te instanceof IInventory))) {
          TileEntity tile = (TileEntity)te;
          if ((!tile.func_145837_r()) && (((IInventory)tile).func_70302_i_() >= 27)) {
            double distSq = entity.func_70092_e(field_145851_c, field_145848_d, field_145849_e);
            if ((distSq <= RANGE_SQ) && (entity.func_70661_as().func_75488_a(field_145851_c, field_145848_d, field_145849_e) != null) && 
              (distSq < bestDist)) {
              bestDist = distSq;
              targetTile = tile;
            }
          }
        }
      }
      catch (Throwable e) {}
    }
  }
  

  public boolean func_75253_b()
  {
    return (entity != null) && (!entity.isWorshipping()) && (entity.func_70694_bm() != null) && (entity.func_110167_bD()) && (targetTile != null);
  }
  
  public void func_75246_d() {
    double SPEED = 0.6D;
    if (entity.func_70661_as().func_75500_f()) {
      setTargetTile();
      if (targetTile != null) {
        entity.func_70661_as().func_75492_a(targetTile.field_145851_c, targetTile.field_145848_d, targetTile.field_145849_e, 0.6D);
      }
    }
    double DROP_RANGE = 2.5D;
    double DROP_RANGE_SQ = 6.25D;
    if ((targetTile != null) && 
      (entity.func_70092_e(targetTile.field_145851_c + 0.5D, targetTile.field_145848_d + 0.5D, targetTile.field_145849_e + 0.5D) <= 6.25D)) {
      IInventory inventory = (IInventory)targetTile;
      inventory.func_70295_k_();
      if ((addItemStackToInventory(entity.func_70694_bm(), inventory)) && 
        (entity.func_70694_bm().field_77994_a == 0)) {
        entity.func_70062_b(0, null);
      }
      

      inventory.func_70305_f();
    }
  }
  

  public boolean addItemStackToInventory(final ItemStack par1ItemStack, IInventory inventory)
  {
    if ((par1ItemStack != null) && (field_77994_a != 0) && (par1ItemStack.func_77973_b() != null))
    {
      try
      {
        if (par1ItemStack.func_77951_h()) {
          int i = getFirstEmptyStack(inventory);
          
          if (i >= 0) {
            inventory.func_70299_a(i, ItemStack.func_77944_b(par1ItemStack));
            field_77994_a = 0;
            field_77992_b = 5;
            return true;
          }
          return false;
        }
        int i;
        do {
          i = field_77994_a;
          field_77994_a = storePartialItemStack(par1ItemStack, inventory);
        } while ((field_77994_a > 0) && (field_77994_a < i));
        
        return field_77994_a < i;
      }
      catch (Throwable throwable) {
        CrashReport crashreport = CrashReport.func_85055_a(throwable, "Adding item to inventory");
        CrashReportCategory crashreportcategory = crashreport.func_85058_a("Item being added");
        crashreportcategory.func_71507_a("Item ID", Integer.valueOf(Item.func_150891_b(par1ItemStack.func_77973_b())));
        crashreportcategory.func_71507_a("Item data", Integer.valueOf(par1ItemStack.func_77960_j()));
        crashreportcategory.func_71500_a("Item name", new Callable() {
          private static final String __OBFID = "CL_00001710";
          
          public String call() {
            return par1ItemStack.func_82833_r();
          }
        });
        throw new net.minecraft.util.ReportedException(crashreport);
      }
    }
    return false;
  }
  
  public int getFirstEmptyStack(IInventory inventory)
  {
    for (int i = 0; i < inventory.func_70302_i_(); i++) {
      if (inventory.func_70301_a(i) == null) {
        return i;
      }
    }
    
    return -1;
  }
  
  private int storePartialItemStack(ItemStack par1ItemStack, IInventory inventory) {
    Item item = par1ItemStack.func_77973_b();
    int i = field_77994_a;
    

    if (par1ItemStack.func_77976_d() == 1) {
      int j = getFirstEmptyStack(inventory);
      
      if (j < 0) {
        return i;
      }
      if (inventory.func_70301_a(j) == null) {
        inventory.func_70299_a(j, ItemStack.func_77944_b(par1ItemStack));
      }
      
      return 0;
    }
    
    int j = storeItemStack(par1ItemStack, inventory);
    
    if (j < 0) {
      j = getFirstEmptyStack(inventory);
    }
    
    if (j < 0) {
      return i;
    }
    if (inventory.func_70301_a(j) == null) {
      inventory.func_70299_a(j, new ItemStack(item, 0, par1ItemStack.func_77960_j()));
      
      if (par1ItemStack.func_77942_o()) {
        inventory.func_70301_a(j).func_77982_d((NBTTagCompound)par1ItemStack.func_77978_p().func_74737_b());
      }
    }
    
    int k = i;
    
    if (i > inventory.func_70301_a(j).func_77976_d() - func_70301_afield_77994_a) {
      k = inventory.func_70301_a(j).func_77976_d() - func_70301_afield_77994_a;
    }
    
    if (k > 64 - func_70301_afield_77994_a) {
      k = 64 - func_70301_afield_77994_a;
    }
    
    if (k == 0) {
      return i;
    }
    i -= k;
    func_70301_afield_77994_a += k;
    func_70301_afield_77992_b = 5;
    return i;
  }
  



  private int storeItemStack(ItemStack par1ItemStack, IInventory inventory)
  {
    for (int i = 0; i < inventory.func_70302_i_(); i++)
    {
      if ((inventory.func_70301_a(i) != null) && (inventory.func_70301_a(i).func_77973_b() == par1ItemStack.func_77973_b()) && (inventory.func_70301_a(i).func_77985_e()) && (func_70301_afield_77994_a < inventory.func_70301_a(i).func_77976_d()) && (func_70301_afield_77994_a < 64) && ((!inventory.func_70301_a(i).func_77981_g()) || (inventory.func_70301_a(i).func_77960_j() == par1ItemStack.func_77960_j())) && (ItemStack.func_77970_a(inventory.func_70301_a(i), par1ItemStack)))
      {






        return i;
      }
    }
    
    return -1;
  }
}
