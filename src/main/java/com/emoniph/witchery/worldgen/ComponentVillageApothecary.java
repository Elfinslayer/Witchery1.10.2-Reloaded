package com.emoniph.witchery.worldgen;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.item.ItemPoppet;
import com.emoniph.witchery.item.ItemPoppet.PoppetType;
import com.emoniph.witchery.util.Config;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureVillagePieces.House1;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;

public class ComponentVillageApothecary extends StructureVillagePieces.House1
{
  private int averageGroundLevel = -1;
  
  public ComponentVillageApothecary() {}
  
  public ComponentVillageApothecary(StructureVillagePieces.Start componentVillageStartPiece, int componentType, Random random, StructureBoundingBox structureBoundingBox, int direction)
  {
    super(componentVillageStartPiece, componentType, random, structureBoundingBox, direction);
    field_74885_f = direction;
    field_74887_e = structureBoundingBox;
  }
  
  public static ComponentVillageApothecary buildComponent(StructureVillagePieces.Start startPiece, List list, Random random, int par3, int par4, int par5, int par6, int par7)
  {
    StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(par3, par4, par5, 0, 0, 0, 9, 9, 6, par6);
    return (func_74895_a(structureboundingbox)) && (net.minecraft.world.gen.structure.StructureComponent.func_74883_a(list, structureboundingbox) == null) ? new ComponentVillageApothecary(startPiece, par7, random, structureboundingbox, par6) : null;
  }
  

  public boolean func_74875_a(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
  {
    if (averageGroundLevel < 0) {
      averageGroundLevel = func_74889_b(par1World, par3StructureBoundingBox);
      
      if (averageGroundLevel < 0) {
        return true;
      }
      
      field_74887_e.func_78886_a(0, averageGroundLevel - field_74887_e.field_78894_e + 9 - 1, 0);
    }
    
    func_151549_a(par1World, par3StructureBoundingBox, 1, 1, 1, 7, 5, 4, Blocks.field_150350_a, Blocks.field_150350_a, false);
    func_151549_a(par1World, par3StructureBoundingBox, 0, 0, 0, 8, 0, 5, Blocks.field_150347_e, Blocks.field_150347_e, false);
    func_151549_a(par1World, par3StructureBoundingBox, 0, 5, 0, 8, 5, 5, Blocks.field_150347_e, Blocks.field_150347_e, false);
    func_151549_a(par1World, par3StructureBoundingBox, 0, 6, 1, 8, 6, 4, Blocks.field_150347_e, Blocks.field_150347_e, false);
    func_151549_a(par1World, par3StructureBoundingBox, 0, 7, 2, 8, 7, 3, Blocks.field_150347_e, Blocks.field_150347_e, false);
    int i = func_151555_a(Blocks.field_150476_ad, 3);
    int j = func_151555_a(Blocks.field_150476_ad, 2);
    


    for (int k = -1; k <= 2; k++) {
      for (int l = 0; l <= 8; l++) {
        func_151550_a(par1World, Blocks.field_150476_ad, i, l, 6 + k, k, par3StructureBoundingBox);
        func_151550_a(par1World, Blocks.field_150476_ad, j, l, 6 + k, 5 - k, par3StructureBoundingBox);
      }
    }
    
    func_151549_a(par1World, par3StructureBoundingBox, 0, 1, 0, 0, 1, 5, Blocks.field_150347_e, Blocks.field_150347_e, false);
    func_151549_a(par1World, par3StructureBoundingBox, 1, 1, 5, 8, 1, 5, Blocks.field_150347_e, Blocks.field_150347_e, false);
    func_151549_a(par1World, par3StructureBoundingBox, 8, 1, 0, 8, 1, 4, Blocks.field_150347_e, Blocks.field_150347_e, false);
    func_151549_a(par1World, par3StructureBoundingBox, 2, 1, 0, 7, 1, 0, Blocks.field_150347_e, Blocks.field_150347_e, false);
    func_151549_a(par1World, par3StructureBoundingBox, 0, 2, 0, 0, 4, 0, Blocks.field_150347_e, Blocks.field_150347_e, false);
    func_151549_a(par1World, par3StructureBoundingBox, 0, 2, 5, 0, 4, 5, Blocks.field_150347_e, Blocks.field_150347_e, false);
    func_151549_a(par1World, par3StructureBoundingBox, 8, 2, 5, 8, 4, 5, Blocks.field_150347_e, Blocks.field_150347_e, false);
    func_151549_a(par1World, par3StructureBoundingBox, 8, 2, 0, 8, 4, 0, Blocks.field_150347_e, Blocks.field_150347_e, false);
    func_151549_a(par1World, par3StructureBoundingBox, 0, 2, 1, 0, 4, 4, Blocks.field_150344_f, Blocks.field_150344_f, false);
    func_151549_a(par1World, par3StructureBoundingBox, 1, 2, 5, 7, 4, 5, Blocks.field_150344_f, Blocks.field_150344_f, false);
    func_151549_a(par1World, par3StructureBoundingBox, 8, 2, 1, 8, 4, 4, Blocks.field_150344_f, Blocks.field_150344_f, false);
    func_151549_a(par1World, par3StructureBoundingBox, 1, 2, 0, 7, 4, 0, Blocks.field_150344_f, Blocks.field_150344_f, false);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 4, 2, 0, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 5, 2, 0, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 6, 2, 0, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 4, 3, 0, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 5, 3, 0, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 6, 3, 0, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 0, 2, 2, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 0, 2, 3, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 0, 3, 2, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 0, 3, 3, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 8, 2, 2, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 8, 2, 3, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 8, 3, 2, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 8, 3, 3, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 2, 2, 5, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 3, 2, 5, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 5, 2, 5, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 6, 2, 5, par3StructureBoundingBox);
    
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 2, 3, 5, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 3, 3, 5, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 5, 3, 5, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 6, 3, 5, par3StructureBoundingBox);
    
    func_151549_a(par1World, par3StructureBoundingBox, 1, 4, 1, 7, 4, 1, Blocks.field_150344_f, Blocks.field_150344_f, false);
    func_151549_a(par1World, par3StructureBoundingBox, 1, 4, 4, 7, 4, 4, Blocks.field_150344_f, Blocks.field_150344_f, false);
    
    func_151550_a(par1World, Blocks.field_150383_bp, 3, 7, 1, 1, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150487_bG, func_151555_a(Blocks.field_150476_ad, 3) | 0x4, 5, 1, 3, par3StructureBoundingBox);
    
    func_151550_a(par1World, Blocks.field_150487_bG, func_151555_a(Blocks.field_150476_ad, 2) | 0x4, 5, 1, 1, par3StructureBoundingBox);
    
    func_151550_a(par1World, Blocks.field_150376_bx, 10, 5, 1, 2, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150478_aa, 0, 5, 2, 3, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150457_bL, 9, 5, 2, 1, par3StructureBoundingBox);
    
    if (!hasMadeChest) {
      int ic = func_74862_a(0);
      int jc = func_74865_a(7, 1);
      int kc = func_74873_b(7, 1);
      
      if (par3StructureBoundingBox.func_78890_b(jc, ic, kc))
      {
        hasMadeChest = true;
        func_74879_a(par1World, par3StructureBoundingBox, par2Random, 7, 0, 1, villageApothecaryChestContents, 2 + par2Random.nextInt(4));
      }
    }
    

    func_151550_a(par1World, Blocks.field_150350_a, 0, 1, 1, 0, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150350_a, 0, 1, 2, 0, par3StructureBoundingBox);
    func_74881_a(par1World, par3StructureBoundingBox, par2Random, 1, 1, 0, func_151555_a(Blocks.field_150466_ao, 1));
    
    generateStructureSign(par1World, par3StructureBoundingBox, par2Random, 1, 3, -1, net.minecraft.util.StatCollector.func_74838_a("witchery.structure.apothecary.name"));
    
    if ((func_151548_a(par1World, 1, 0, -1, par3StructureBoundingBox) == Blocks.field_150350_a) && (func_151548_a(par1World, 1, -1, -1, par3StructureBoundingBox) != Blocks.field_150350_a))
    {
      func_151550_a(par1World, Blocks.field_150446_ar, func_151555_a(Blocks.field_150446_ar, 3), 1, 0, -1, par3StructureBoundingBox);
    }
    

    for (int l = 0; l < 6; l++) {
      for (int i1 = 0; i1 < 9; i1++) {
        func_74871_b(par1World, i1, 9, l, par3StructureBoundingBox);
        func_151554_b(par1World, Blocks.field_150347_e, 0, i1, -1, l, par3StructureBoundingBox);
      }
    }
    
    func_74893_a(par1World, par3StructureBoundingBox, 2, 1, 2, 1);
    return true;
  }
  
  protected boolean generateStructureSign(World par1World, StructureBoundingBox par2StructureBoundingBox, Random par3Random, int par4, int par5, int par6, String text)
  {
    int i1 = func_74865_a(par4, par6);
    int j1 = func_74862_a(par5);
    int k1 = func_74873_b(par4, par6);
    
    if ((par2StructureBoundingBox.func_78890_b(i1, j1, k1)) && (par1World.func_147439_a(i1, j1, k1) != Blocks.field_150444_as)) {
      int metadata = 4;
      switch (field_74885_f) {
      case 0: 
        metadata = 2;
        break;
      case 1: 
        metadata = 5;
        break;
      case 2: 
      default: 
        metadata = 3;
        break;
      case 3: 
        metadata = 4;
      }
      
      par1World.func_147465_d(i1, j1, k1, Blocks.field_150444_as, metadata, 2);
      TileEntitySign tileentitysign = (TileEntitySign)par1World.func_147438_o(i1, j1, k1);
      
      if (tileentitysign != null) {
        field_145915_a = new String[] { "", text, "", "" };
      }
      
      return true;
    }
    return false;
  }
  

  public static final WeightedRandomChestContent[] villageApothecaryChestContents = { new WeightedRandomChestContent(Items.field_151045_i, 0, 1, 2, 3), new WeightedRandomChestContent(Items.field_151069_bo, 0, 1, 10, 10), new WeightedRandomChestContent(Items.field_151043_k, 0, 1, 3, 5), new WeightedRandomChestContent(Items.field_151025_P, 0, 1, 3, 15), new WeightedRandomChestContent(Items.field_151034_e, 0, 1, 3, 15), new WeightedRandomChestContent(ItemsGENERIC, ItemsGENERIC.itemBatWool.damageValue, 1, 5, 5), new WeightedRandomChestContent(ItemsGENERIC, ItemsGENERIC.itemDogTongue.damageValue, 1, 5, 5), new WeightedRandomChestContent(ItemsGENERIC, ItemsGENERIC.itemRowanBerries.damageValue, 1, 5, 5), new WeightedRandomChestContent(ItemsGENERIC, ItemsGENERIC.itemSpectralDust.damageValue, 1, 1, 3), new WeightedRandomChestContent(ItemsGENERIC, ItemsGENERIC.itemMutandis.damageValue, 1, 5, 5), new WeightedRandomChestContent(Items.field_151119_aD, 0, 4, 10, 6), new WeightedRandomChestContent(Items.field_151144_bL, 0, 1, 1, 1), new WeightedRandomChestContent(net.minecraft.item.Item.func_150898_a(Blocks.field_150345_g), 0, 3, 7, 5), new WeightedRandomChestContent(ItemsDIVINER_WATER, 0, 1, 1, 1), new WeightedRandomChestContent(ItemsPOPPET, ItemsPOPPET.voodooPoppet.damageValue, 1, 1, 1), new WeightedRandomChestContent(ItemsPOPPET, ItemsPOPPET.firePoppet.damageValue, 1, 1, 1) };
  






  private boolean hasMadeChest;
  






  private static final String CHEST_KEY = "WITCApocChest";
  







  protected void func_143012_a(NBTTagCompound par1NBTTagCompound)
  {
    super.func_143012_a(par1NBTTagCompound);
    par1NBTTagCompound.func_74757_a("WITCApocChest", hasMadeChest);
  }
  
  protected void func_143011_b(NBTTagCompound par1NBTTagCompound)
  {
    super.func_143011_b(par1NBTTagCompound);
    hasMadeChest = par1NBTTagCompound.func_74767_n("WITCApocChest");
  }
  
  protected int func_74888_b(int par1)
  {
    return instanceapothecaryID;
  }
}
