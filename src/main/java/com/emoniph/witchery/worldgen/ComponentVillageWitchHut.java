package com.emoniph.witchery.worldgen;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.entity.EntityCovenWitch;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces.House1;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;

public class ComponentVillageWitchHut
  extends StructureVillagePieces.House1
{
  private boolean isTallHouse;
  private int tablePosition;
  
  public ComponentVillageWitchHut() {}
  
  public ComponentVillageWitchHut(StructureVillagePieces.Start par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5)
  {
    super(par1ComponentVillageStartPiece, par2, par3Random, par4StructureBoundingBox, par5);
    field_74885_f = par5;
    field_74887_e = par4StructureBoundingBox;
    isTallHouse = true;
    tablePosition = (par3Random.nextInt(2) + 1);
  }
  

  protected void func_143012_a(NBTTagCompound par1NBTTagCompound)
  {
    super.func_143012_a(par1NBTTagCompound);
    par1NBTTagCompound.func_74768_a("T", tablePosition);
    par1NBTTagCompound.func_74757_a("C", isTallHouse);
    par1NBTTagCompound.func_74768_a("WITCWCount", witchesSpawned);
  }
  

  protected void func_143011_b(NBTTagCompound par1NBTTagCompound)
  {
    super.func_143011_b(par1NBTTagCompound);
    tablePosition = par1NBTTagCompound.func_74762_e("T");
    isTallHouse = par1NBTTagCompound.func_74767_n("C");
    witchesSpawned = par1NBTTagCompound.func_74762_e("WITCWCount");
  }
  
  public static ComponentVillageWitchHut buildComponent(StructureVillagePieces.Start par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7)
  {
    StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(par3, par4, par5, 0, 0, 0, 4, 6, 5, par6);
    return (func_74895_a(structureboundingbox)) && (StructureComponent.func_74883_a(par1List, structureboundingbox) == null) ? new ComponentVillageWitchHut(par0ComponentVillageStartPiece, par7, par2Random, structureboundingbox, par6) : null;
  }
  














  public boolean func_74875_a(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
  {
    if (field_143015_k < 0)
    {
      field_143015_k = func_74889_b(par1World, par3StructureBoundingBox);
      
      if (field_143015_k < 0)
      {
        return true;
      }
      
      field_74887_e.func_78886_a(0, field_143015_k - field_74887_e.field_78894_e + 6 - 1, 0);
    }
    
    func_151549_a(par1World, par3StructureBoundingBox, 1, 1, 1, 3, 5, 4, Blocks.field_150350_a, Blocks.field_150350_a, false);
    func_151549_a(par1World, par3StructureBoundingBox, 0, 0, 0, 3, 0, 4, Blocks.field_150347_e, Blocks.field_150347_e, false);
    func_151549_a(par1World, par3StructureBoundingBox, 1, 0, 1, 2, 0, 3, Blocks.field_150347_e, Blocks.field_150347_e, false);
    
    if (!isTallHouse)
    {
      func_151556_a(par1World, par3StructureBoundingBox, 1, 4, 1, 2, 4, 3, Blocks.field_150344_f, 1, Blocks.field_150344_f, 1, false);
    }
    else
    {
      func_151556_a(par1World, par3StructureBoundingBox, 1, 5, 1, 2, 5, 3, Blocks.field_150344_f, 1, Blocks.field_150344_f, 1, false);
    }
    
    func_151550_a(par1World, Blocks.field_150344_f, 1, 1, 4, 0, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150344_f, 1, 2, 4, 0, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150344_f, 1, 1, 4, 4, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150344_f, 1, 2, 4, 4, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150344_f, 1, 0, 4, 1, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150344_f, 1, 0, 4, 2, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150344_f, 1, 0, 4, 3, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150344_f, 1, 3, 4, 1, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150344_f, 1, 3, 4, 2, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150344_f, 1, 3, 4, 3, par3StructureBoundingBox);
    func_151556_a(par1World, par3StructureBoundingBox, 0, 1, 0, 0, 3, 0, Blocks.field_150344_f, 2, Blocks.field_150344_f, 2, false);
    func_151556_a(par1World, par3StructureBoundingBox, 3, 1, 0, 3, 3, 0, Blocks.field_150344_f, 2, Blocks.field_150344_f, 2, false);
    func_151556_a(par1World, par3StructureBoundingBox, 0, 1, 4, 0, 3, 4, Blocks.field_150344_f, 2, Blocks.field_150344_f, 2, false);
    func_151556_a(par1World, par3StructureBoundingBox, 3, 1, 4, 3, 3, 4, Blocks.field_150344_f, 2, Blocks.field_150344_f, 2, false);
    func_151550_a(par1World, Blocks.field_150344_f, 1, 0, 3, 0, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150344_f, 1, 3, 3, 0, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150344_f, 1, 0, 3, 4, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150344_f, 1, 3, 3, 4, par3StructureBoundingBox);
    func_151549_a(par1World, par3StructureBoundingBox, 0, 1, 1, 0, 3, 3, Blocks.field_150344_f, Blocks.field_150344_f, false);
    func_151549_a(par1World, par3StructureBoundingBox, 3, 1, 1, 3, 3, 3, Blocks.field_150344_f, Blocks.field_150344_f, false);
    func_151549_a(par1World, par3StructureBoundingBox, 1, 1, 0, 2, 3, 0, Blocks.field_150344_f, Blocks.field_150344_f, false);
    func_151549_a(par1World, par3StructureBoundingBox, 1, 1, 4, 2, 3, 4, Blocks.field_150344_f, Blocks.field_150344_f, false);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 0, 2, 2, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150410_aZ, 0, 3, 2, 2, par3StructureBoundingBox);
    
    if (tablePosition > 0)
    {


      func_151550_a(par1World, Blocks.field_150383_bp, 3, 1, 1, 3, par3StructureBoundingBox);
      func_151550_a(par1World, BlocksLOG, 0, 2, 1, 3, par3StructureBoundingBox);
      func_151550_a(par1World, Blocks.field_150457_bL, 4, 2, 2, 3, par3StructureBoundingBox);
    }
    
    func_151550_a(par1World, Blocks.field_150350_a, 0, 1, 1, 0, par3StructureBoundingBox);
    func_151550_a(par1World, Blocks.field_150350_a, 0, 1, 2, 0, par3StructureBoundingBox);
    func_74881_a(par1World, par3StructureBoundingBox, par2Random, 1, 1, 0, func_151555_a(Blocks.field_150466_ao, 1));
    
    if ((func_151548_a(par1World, 1, 0, -1, par3StructureBoundingBox) == Blocks.field_150350_a) && (func_151548_a(par1World, 1, -1, -1, par3StructureBoundingBox) != Blocks.field_150350_a))
    {
      func_151550_a(par1World, Blocks.field_150446_ar, func_151555_a(Blocks.field_150446_ar, 3), 1, 0, -1, par3StructureBoundingBox);
    }
    
    for (int i = 0; i < 5; i++)
    {
      for (int j = 0; j < 4; j++)
      {
        func_74871_b(par1World, j, 6, i, par3StructureBoundingBox);
        func_151554_b(par1World, Blocks.field_150347_e, 0, j, -1, i, par3StructureBoundingBox);
      }
    }
    
    spawnWitches(par1World, par3StructureBoundingBox, 1, 1, 2, 1);
    return true;
  }
  
  private int witchesSpawned = 0;
  
  private void spawnWitches(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6) {
    if (witchesSpawned < par6)
    {
      for (int i1 = witchesSpawned; i1 < par6; i1++)
      {
        int j1 = func_74865_a(par3 + i1, par5);
        int k1 = func_74862_a(par4);
        int l1 = func_74873_b(par3 + i1, par5);
        
        if (!par2StructureBoundingBox.func_78890_b(j1, k1, l1)) {
          break;
        }
        

        witchesSpawned += 1;
        EntityCovenWitch entityvillager = new EntityCovenWitch(par1World);
        entityvillager.func_70012_b(j1 + 0.5D, k1, l1 + 0.5D, 0.0F, 0.0F);
        entityvillager.func_110163_bv();
        par1World.func_72838_d(entityvillager);
      }
    }
  }
}
