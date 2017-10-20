package com.emoniph.witchery.worldgen;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.RegistryNamespaced;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;

public class ComponentVillageBookShop extends StructureVillagePieces.Village
{
  public static ComponentVillageBookShop construct(StructureVillagePieces.Start start, List pieces, Random rand, int p1, int p2, int p3, int p4, int p5)
  {
    StructureBoundingBox bounds = StructureBoundingBox.func_78889_a(p1, p2, p3, 0, 0, 0, 10, 8, 9, p4);
    
    return (func_74895_a(bounds)) && (StructureComponent.func_74883_a(pieces, bounds) == null) ? new ComponentVillageBookShop(start, p5, rand, bounds, p4) : null;
  }
  

  public ComponentVillageBookShop() {}
  

  public ComponentVillageBookShop(StructureVillagePieces.Start start, int componentType, Random rand, StructureBoundingBox bounds, int coordMode)
  {
    super(start, componentType);
    field_74885_f = coordMode;
    field_74887_e = bounds;
  }
  
  public boolean func_74875_a(World world, Random rand, StructureBoundingBox bounds) {
    int height = 8;
    if (field_143015_k < 0) {
      field_143015_k = func_74889_b(world, bounds);
      
      if (field_143015_k < 0) {
        return true;
      }
      
      field_74887_e.func_78886_a(0, field_143015_k - field_74887_e.field_78894_e + 8 - 1, 0);
    }
    
    func_151549_a(world, bounds, 1, 0, 1, 8, 0, 6, Blocks.field_150347_e, Blocks.field_150347_e, false);
    func_151549_a(world, bounds, 2, 0, 2, 7, 0, 5, Blocks.field_150344_f, Blocks.field_150344_f, false);
    
    func_151549_a(world, bounds, 1, 1, 0, 8, 7, 6, Blocks.field_150350_a, Blocks.field_150350_a, false);
    
    func_151549_a(world, bounds, 1, 1, 3, 8, 5, 6, Blocks.field_150347_e, Blocks.field_150347_e, false);
    
    func_151549_a(world, bounds, 1, 6, 4, 8, 6, 5, Blocks.field_150347_e, Blocks.field_150347_e, false);
    
    func_151549_a(world, bounds, 1, 4, 1, 8, 4, 2, Blocks.field_150347_e, Blocks.field_150347_e, false);
    
    func_151549_a(world, bounds, 2, 1, 4, 7, 4, 5, Blocks.field_150350_a, Blocks.field_150350_a, false);
    
    func_151549_a(world, bounds, 2, 1, 3, 7, 3, 3, Blocks.field_150344_f, Blocks.field_150344_f, false);
    
    func_151549_a(world, bounds, 3, 2, 3, 6, 3, 3, Blocks.field_150350_a, Blocks.field_150350_a, false);
    func_151550_a(world, Blocks.field_150350_a, 0, 6, 1, 3, bounds);
    
    func_151550_a(world, Blocks.field_150478_aa, 0, 3, 4, 4, bounds);
    func_151550_a(world, Blocks.field_150478_aa, 0, 6, 4, 4, bounds);
    
    func_151549_a(world, bounds, 1, 2, 4, 1, 4, 5, Blocks.field_150344_f, Blocks.field_150344_f, false);
    func_151549_a(world, bounds, 8, 2, 4, 8, 4, 5, Blocks.field_150344_f, Blocks.field_150344_f, false);
    
    func_151549_a(world, bounds, 2, 2, 6, 7, 4, 6, Blocks.field_150344_f, Blocks.field_150344_f, false);
    
    func_151549_a(world, bounds, 1, 1, 1, 1, 3, 1, Blocks.field_150422_aJ, Blocks.field_150422_aJ, false);
    
    func_151549_a(world, bounds, 8, 1, 1, 8, 3, 1, Blocks.field_150422_aJ, Blocks.field_150422_aJ, false);
    
    int n = func_151555_a(Blocks.field_150476_ad, 3);
    int s = func_151555_a(Blocks.field_150476_ad, 2);
    int w = func_151555_a(Blocks.field_150476_ad, 0);
    int e = func_151555_a(Blocks.field_150476_ad, 1);
    
    func_151550_a(world, Blocks.field_150446_ar, n, 3, 0, 0, bounds);
    func_151550_a(world, Blocks.field_150446_ar, n, 4, 0, 0, bounds);
    func_151550_a(world, Blocks.field_150446_ar, n, 5, 0, 0, bounds);
    func_151550_a(world, Blocks.field_150446_ar, n, 6, 0, 0, bounds);
    
    for (int i = 1; i <= 8; i++) {
      func_151550_a(world, Blocks.field_150476_ad, n, i, 5, 2, bounds);
      func_151550_a(world, Blocks.field_150476_ad, n, i, 6, 3, bounds);
      func_151550_a(world, Blocks.field_150476_ad, n, i, 7, 4, bounds);
      
      func_151550_a(world, Blocks.field_150476_ad, s, i, 5, 7, bounds);
      func_151550_a(world, Blocks.field_150476_ad, s, i, 6, 6, bounds);
      func_151550_a(world, Blocks.field_150476_ad, s, i, 7, 5, bounds);
    }
    
    if (!hasMadeChest) {
      int i = func_74862_a(1);
      int j = func_74865_a(2, 4);
      int k = func_74873_b(2, 4);
      
      if (bounds.func_78890_b(j, i, k)) {
        Log.instance().debug(String.format("Bookshop %d %d %d - dir %d", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k), Integer.valueOf(field_74885_f) }));
        
        if (bookshopChestContents == null) {
          List<WeightedRandomChestContent> list = new ArrayList();
          list.add(new WeightedRandomChestContent(Items.field_151122_aG, 0, 1, 1, 1));
          list.add(new WeightedRandomChestContent(ItemsGENERIC, ItemsGENERIC.itemVampireBookPage.damageValue, 1, 1, 3));
          list.add(new WeightedRandomChestContent(ItemsGENERIC, ItemsGENERIC.itemVampireBookPage.damageValue, 1, 1, 2));
          list.add(new WeightedRandomChestContent(ItemsGENERIC, ItemsGENERIC.itemVampireBookPage.damageValue, 1, 1, 1));
          for (String bonusBook : instancetownBooks) {
            try {
              String name = bonusBook;
              int meta = 0;
              int comma = bonusBook.lastIndexOf(',');
              if (comma >= 0) {
                name = bonusBook.substring(0, comma);
                String metaString = bonusBook.substring(comma + 1);
                meta = Integer.parseInt(metaString);
              }
              Item item = (Item)Item.field_150901_e.func_82594_a(name);
              if (item != null) {
                list.add(new WeightedRandomChestContent(item, meta, 1, 1, 1));
              }
            }
            catch (Throwable ex) {}
          }
          bookshopChestContents = (WeightedRandomChestContent[])list.toArray(new WeightedRandomChestContent[list.size()]);
        }
        
        hasMadeChest = true;
        generateStructureChestContents(world, bounds, rand, 2, 1, 4, bookshopChestContents, 5 + rand.nextInt(6), new ItemStack[] { new ItemStack(ItemsVAMPIRE_BOOK) });
        

        addBookInFrame(world, 3, 3, 6, bookshopChestContentsnextIntbookshopChestContentslengthfield_76297_b.func_77946_l());
        addBookInFrame(world, 4, 3, 6, bookshopChestContentsnextIntbookshopChestContentslengthfield_76297_b.func_77946_l());
        addBookInFrame(world, 5, 3, 6, bookshopChestContentsnextIntbookshopChestContentslengthfield_76297_b.func_77946_l());
        addBookInFrame(world, 6, 3, 6, bookshopChestContentsnextIntbookshopChestContentslengthfield_76297_b.func_77946_l());
      }
    }
    
    for (int j = 1; j < 7; j++) {
      for (int k = 1; k < 7; k++) {
        func_74871_b(world, k, 8, j, bounds);
        func_151554_b(world, Blocks.field_150347_e, 0, k, -1, j, bounds);
      }
    }
    
    return true;
  }
  
  private boolean generateStructureChestContents(World world, StructureBoundingBox bounds, Random rand, int x, int y, int z, WeightedRandomChestContent[] contents, int quantity, ItemStack[] extraItems)
  {
    int i1 = func_74865_a(x, z);
    int j1 = func_74862_a(y);
    int k1 = func_74873_b(x, z);
    
    if ((bounds.func_78890_b(i1, j1, k1)) && (world.func_147439_a(i1, j1, k1) != Blocks.field_150486_ae)) {
      world.func_147465_d(i1, j1, k1, Blocks.field_150486_ae, 0, 2);
      TileEntityChest chest = (TileEntityChest)world.func_147438_o(i1, j1, k1);
      
      if (chest != null) {
        WeightedRandomChestContent.func_76293_a(rand, contents, chest, quantity);
        if (extraItems != null) {
          for (ItemStack stack : extraItems) {
            chest.func_70299_a(rand.nextInt(chest.func_70302_i_()), stack.func_77946_l());
          }
        }
      }
      
      return true;
    }
    return false;
  }
  
  private void addBookInFrame(World world, int x, int y, int z, ItemStack stack)
  {
    int xWorld = func_74865_a(x, z);
    int yWorld = func_74862_a(y);
    int zWorld = func_74873_b(x, z);
    
    int direction = 0;
    switch (field_74885_f) {
    case 0: 
    default: 
      direction = 2;
      break;
    case 1: 
      direction = 3;
      break;
    case 2: 
      direction = 0;
      break;
    case 3: 
      direction = 1;
    }
    
    
    EntityItemFrame frame = new EntityItemFrame(world, xWorld, yWorld, zWorld, direction);
    if ((frame != null) && (frame.func_70518_d()) && 
      (!field_72995_K)) {
      world.func_72838_d(frame);
      frame.func_82334_a(stack);
    }
  }
  

  public static WeightedRandomChestContent[] bookshopChestContents = null;
  private boolean hasMadeChest;
  
  protected void func_143012_a(NBTTagCompound nbtRoot)
  {
    super.func_143012_a(nbtRoot);
    nbtRoot.func_74757_a("Chest", hasMadeChest);
  }
  
  protected void func_143011_b(NBTTagCompound nbtRoot) {
    super.func_143011_b(nbtRoot);
    hasMadeChest = nbtRoot.func_74767_n("Chest");
  }
}
