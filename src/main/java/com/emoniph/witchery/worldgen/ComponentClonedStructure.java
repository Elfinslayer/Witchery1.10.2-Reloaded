package com.emoniph.witchery.worldgen;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.item.ItemDuplicationStaff.Rotation;
import com.emoniph.witchery.item.ItemGeneral;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public abstract class ComponentClonedStructure extends WitcheryComponent
{
  private ItemDuplicationStaff.Rotation rotation;
  
  public ComponentClonedStructure() {}
  
  public ComponentClonedStructure(int direction, Random random, int x, int z, int w, int h, int d)
  {
    super(direction, random, x, z, w, h, d);
    rotation = ItemDuplicationStaff.Rotation.values()[direction];
  }
  
  public boolean addComponentParts(World world, Random random)
  {
    BiomeGenBase biom = world.func_72807_a(func_74865_a(0, 0), func_74873_b(0, 0));
    int groundAvg = calcGroundHeight(world, field_74887_e);
    
    if (groundAvg < 0) {
      return true;
    }
    
    field_74887_e.func_78886_a(0, groundAvg - field_74887_e.field_78894_e + field_74887_e.func_78882_c() - 1, 0);
    
    if ((isWaterBelow(world, 0, -1, 0, field_74887_e)) || (isWaterBelow(world, 0, -1, field_74887_e.func_78880_d() - 1, field_74887_e)) || (isWaterBelow(world, field_74887_e.func_78883_b() - 1, -1, 0, field_74887_e)) || (isWaterBelow(world, field_74887_e.func_78883_b() - 1, -1, field_74887_e.func_78880_d() - 1, field_74887_e)))
    {
      return false;
    }
    
    Block groundID = Blocks.field_150349_c;
    Block undergroundID = Blocks.field_150346_d;
    if ((field_76756_M == field_76769_dfield_76756_M) || (field_76756_M == field_76786_sfield_76756_M) || (field_76756_M == field_76787_rfield_76756_M))
    {
      groundID = Blocks.field_150354_m;
      undergroundID = Blocks.field_150354_m;
    }
    

    NBTTagCompound nbtSchematic = getSchematic(world, random);
    com.emoniph.witchery.item.ItemDuplicationStaff.drawSchematicInWorld(world, field_74887_e.field_78897_a, field_74887_e.field_78895_b, field_74887_e.field_78896_c, rotation, true, nbtSchematic);
    












    for (int i = 0; i < field_74887_e.func_78883_b(); i++) {
      for (int j = 0; j < field_74887_e.func_78880_d(); j++)
      {

        func_151554_b(world, undergroundID, 0, j, 0, i, field_74887_e);
      }
    }
    
    spawnWitches(world, field_74887_e, field_74887_e.func_78883_b() - 3, 1, 3, 1);
    
    return true;
  }
  
  protected abstract NBTTagCompound getSchematic(World paramWorld, Random paramRandom);
  
  private int witchesSpawned = 0;
  
  private void spawnWitches(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6) {
    if (witchesSpawned < par6) {
      for (int i1 = witchesSpawned; i1 < par6; i1++) {
        int j1 = func_74865_a(par3 + i1, par5);
        int k1 = func_74862_a(par4);
        int l1 = func_74873_b(par3 + i1, par5);
        
        if (!par2StructureBoundingBox.func_78890_b(j1, k1, l1)) {
          break;
        }
        
        witchesSpawned += 1;
        spawnInhabitant(par1World, par2StructureBoundingBox);
      }
    }
  }
  



  public static final WeightedRandomChestContent[] shackChestContents = { new WeightedRandomChestContent(Items.field_151069_bo, 0, 1, 1, 10), new WeightedRandomChestContent(Items.field_151025_P, 0, 1, 3, 15), new WeightedRandomChestContent(Items.field_151034_e, 0, 1, 3, 15), new WeightedRandomChestContent(Items.field_151101_aQ, 0, 1, 3, 10), new WeightedRandomChestContent(Item.func_150898_a(Blocks.field_150345_g), 1, 1, 1, 15), new WeightedRandomChestContent(ItemsGENERIC, ItemsGENERIC.itemRowanBerries.damageValue, 1, 2, 10), new WeightedRandomChestContent(Items.field_151037_a, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151035_b, 0, 1, 1, 5) };
  

  private boolean hasMadeChest;
  
  private static final String CHEST_KEY = "WITCShackChest";
  

  protected abstract void spawnInhabitant(World paramWorld, StructureBoundingBox paramStructureBoundingBox);
  

  protected void func_143012_a(NBTTagCompound par1NBTTagCompound)
  {
    super.func_143012_a(par1NBTTagCompound);
    par1NBTTagCompound.func_74757_a("WITCShackChest", hasMadeChest);
    par1NBTTagCompound.func_74768_a("WITCWCount", witchesSpawned);
  }
  

  protected void func_143011_b(NBTTagCompound par1NBTTagCompound)
  {
    super.func_143011_b(par1NBTTagCompound);
    hasMadeChest = par1NBTTagCompound.func_74767_n("WITCShackChest");
    if (par1NBTTagCompound.func_74764_b("WITCWCount")) {
      witchesSpawned = par1NBTTagCompound.func_74762_e("WITCWCount");
    } else {
      witchesSpawned = 0;
    }
  }
}
