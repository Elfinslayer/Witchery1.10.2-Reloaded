package com.emoniph.witchery.worldgen;

import com.emoniph.witchery.entity.EntityCovenWitch;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.StructureBoundingBox;


public class ComponentCoven
  extends WitcheryComponent
{
  public static final int DIM_X = 11;
  public static final int DIM_Y = 4;
  public static final int DIM_Z = 11;
  
  public ComponentCoven() {}
  
  public ComponentCoven(int direction, Random random, int x, int z)
  {
    super(direction, random, x, z, 11, 4, 11);
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
        if (field_73012_v.nextInt(10) != 0) {
          EntityCovenWitch entityvillager = new EntityCovenWitch(par1World);
          entityvillager.func_110163_bv();
          entityvillager.func_70012_b(j1 + 0.5D, k1, l1 + 0.5D, 0.0F, 0.0F);
          par1World.func_72838_d(entityvillager);
        }
      }
    }
  }
  
  public boolean addComponentParts(World world, Random random) {
    BiomeGenBase biom = world.func_72807_a(func_74865_a(0, 0), func_74873_b(0, 0));
    int groundAvg = calcGroundHeight(world, field_74887_e);
    
    if (groundAvg < 0) {
      return true;
    }
    
    field_74887_e.func_78886_a(0, groundAvg - field_74887_e.field_78894_e + 4 - 1, 0);
    if ((isWaterBelow(world, 0, -1, 0, field_74887_e)) || (isWaterBelow(world, 0, -1, 10, field_74887_e)) || (isWaterBelow(world, 10, -1, 0, field_74887_e)) || (isWaterBelow(world, 10, -1, 10, field_74887_e)))
    {
      return false;
    }
    



    Block stone = Blocks.field_150347_e;
    int stoneMeta = 0;
    Block brick = Blocks.field_150417_aV;
    int brickMeta = 2;
    Block groundID;
    Block undergroundID; if ((field_76756_M == field_76769_dfield_76756_M) || (field_76756_M == field_76786_sfield_76756_M) || (field_76756_M == field_76787_rfield_76756_M)) {
      Block groundID = Blocks.field_150354_m;
      Block undergroundID = Blocks.field_150354_m;
      stone = Blocks.field_150322_A;
      stoneMeta = 0;
      brick = Blocks.field_150322_A;
      brickMeta = 2;
    } else {
      groundID = Blocks.field_150349_c;
      undergroundID = Blocks.field_150346_d;
    }
    
    func_74878_a(world, field_74887_e, 3, 1, 0, 7, 3, 10);
    func_74878_a(world, field_74887_e, 0, 1, 3, 10, 3, 7);
    func_74878_a(world, field_74887_e, 1, 1, 1, 9, 3, 9);
    
    func_151549_a(world, field_74887_e, 3, 0, 0, 7, 0, 10, groundID, groundID, false);
    func_151549_a(world, field_74887_e, 0, 0, 3, 10, 0, 7, groundID, groundID, false);
    func_151549_a(world, field_74887_e, 1, 0, 1, 9, 0, 9, groundID, groundID, false);
    
    int ground = 1;
    


    Block altarBrick = Blocks.field_150417_aV;
    int altarMeta = 3;
    place(altarBrick, 3, 4, ground, 4, field_74887_e, world);
    place(altarBrick, 3, 4, ground, 5, field_74887_e, world);
    place(altarBrick, 3, 4, ground, 6, field_74887_e, world);
    
    place(altarBrick, 3, 5, ground, 4, field_74887_e, world);
    
    place(Blocks.field_150355_j, 0, 5, ground, 5, field_74887_e, world);
    
    setDispenser(5, ground - 1, 5, random, world, 5);
    
    place(altarBrick, 3, 5, ground, 6, field_74887_e, world);
    
    place(altarBrick, 3, 6, ground, 4, field_74887_e, world);
    place(altarBrick, 3, 6, ground, 5, field_74887_e, world);
    place(altarBrick, 3, 6, ground, 6, field_74887_e, world);
    
    String mobType = "Witch";
    setSpawner(4, ground - 1, 4, "Witch", world);
    

    setSpawner(6, ground - 1, 6, "Witch", world);
    


    place(stone, stoneMeta, 1, ground, 2, field_74887_e, world);
    place(stone, stoneMeta, 2, ground, 1, field_74887_e, world);
    
    place(Blocks.field_150321_G, 0, 3, ground, 0, field_74887_e, world);
    
    place(stone, stoneMeta, 4, ground, 0, field_74887_e, world);
    place(stone, stoneMeta, 6, ground, 0, field_74887_e, world);
    
    place(stone, stoneMeta, 8, ground, 1, field_74887_e, world);
    place(stone, stoneMeta, 9, ground, 2, field_74887_e, world);
    
    place(stone, stoneMeta, 10, ground, 4, field_74887_e, world);
    place(stone, stoneMeta, 10, ground, 6, field_74887_e, world);
    
    place(stone, stoneMeta, 9, ground, 8, field_74887_e, world);
    place(stone, stoneMeta, 8, ground, 9, field_74887_e, world);
    
    place(stone, stoneMeta, 6, ground, 10, field_74887_e, world);
    place(stone, stoneMeta, 4, ground, 10, field_74887_e, world);
    
    place(stone, stoneMeta, 2, ground, 9, field_74887_e, world);
    place(stone, stoneMeta, 1, ground, 8, field_74887_e, world);
    
    place(stone, stoneMeta, 0, ground, 4, field_74887_e, world);
    place(stone, stoneMeta, 0, ground, 6, field_74887_e, world);
    
    ground++;
    
    place(brick, brickMeta, 1, ground, 2, field_74887_e, world);
    place(brick, brickMeta, 2, ground, 1, field_74887_e, world);
    
    place(stone, stoneMeta, 4, ground, 0, field_74887_e, world);
    place(stone, stoneMeta, 6, ground, 0, field_74887_e, world);
    
    place(brick, brickMeta, 8, ground, 1, field_74887_e, world);
    place(brick, brickMeta, 9, ground, 2, field_74887_e, world);
    
    place(stone, stoneMeta, 10, ground, 4, field_74887_e, world);
    place(stone, stoneMeta, 10, ground, 6, field_74887_e, world);
    
    place(brick, brickMeta, 9, ground, 8, field_74887_e, world);
    place(brick, brickMeta, 8, ground, 9, field_74887_e, world);
    
    place(stone, stoneMeta, 6, ground, 10, field_74887_e, world);
    place(stone, stoneMeta, 4, ground, 10, field_74887_e, world);
    
    place(brick, brickMeta, 2, ground, 9, field_74887_e, world);
    place(brick, brickMeta, 1, ground, 8, field_74887_e, world);
    
    place(stone, stoneMeta, 0, ground, 4, field_74887_e, world);
    place(stone, stoneMeta, 0, ground, 6, field_74887_e, world);
    
    ground++;
    
    place(stone, stoneMeta, 1, ground, 2, field_74887_e, world);
    place(stone, stoneMeta, 2, ground, 1, field_74887_e, world);
    
    place(brick, brickMeta, 3, ground, 1, field_74887_e, world);
    place(brick, brickMeta, 4, ground, 0, field_74887_e, world);
    place(brick, brickMeta, 5, ground, 0, field_74887_e, world);
    place(brick, brickMeta, 6, ground, 0, field_74887_e, world);
    place(brick, brickMeta, 7, ground, 1, field_74887_e, world);
    
    place(stone, stoneMeta, 8, ground, 1, field_74887_e, world);
    place(stone, stoneMeta, 9, ground, 2, field_74887_e, world);
    
    place(brick, brickMeta, 9, ground, 3, field_74887_e, world);
    place(brick, brickMeta, 10, ground, 4, field_74887_e, world);
    place(brick, brickMeta, 10, ground, 5, field_74887_e, world);
    place(brick, brickMeta, 10, ground, 6, field_74887_e, world);
    place(brick, brickMeta, 9, ground, 7, field_74887_e, world);
    
    place(Blocks.field_150321_G, 0, 10, ground - 1, 7, field_74887_e, world);
    
    place(stone, stoneMeta, 9, ground, 8, field_74887_e, world);
    place(stone, stoneMeta, 8, ground, 9, field_74887_e, world);
    
    place(brick, brickMeta, 7, ground, 9, field_74887_e, world);
    place(brick, brickMeta, 6, ground, 10, field_74887_e, world);
    place(brick, brickMeta, 5, ground, 10, field_74887_e, world);
    place(brick, brickMeta, 4, ground, 10, field_74887_e, world);
    place(brick, brickMeta, 3, ground, 9, field_74887_e, world);
    
    place(stone, stoneMeta, 2, ground, 9, field_74887_e, world);
    place(stone, stoneMeta, 1, ground, 8, field_74887_e, world);
    
    place(Blocks.field_150321_G, 0, 0, ground, 7, field_74887_e, world);
    
    place(brick, brickMeta, 1, ground, 3, field_74887_e, world);
    place(brick, brickMeta, 0, ground, 4, field_74887_e, world);
    place(brick, brickMeta, 0, ground, 5, field_74887_e, world);
    place(brick, brickMeta, 0, ground, 6, field_74887_e, world);
    place(brick, brickMeta, 1, ground, 7, field_74887_e, world);
    
    for (int x = 0; x < 11; x++) {
      for (int z = 0; z < 11; z++) {
        func_151554_b(world, undergroundID, 0, x, 0, z, field_74887_e);
        func_74871_b(world, x, 4, z, field_74887_e);
      }
    }
    
    spawnWitches(world, field_74887_e, 7, 1, 4, 1);
    
    return true;
  }
  
  protected void func_143012_a(NBTTagCompound par1NBTTagCompound)
  {
    super.func_143012_a(par1NBTTagCompound);
    par1NBTTagCompound.func_74768_a("WITCWCount", witchesSpawned);
  }
  

  protected void func_143011_b(NBTTagCompound par1NBTTagCompound)
  {
    super.func_143011_b(par1NBTTagCompound);
    if (par1NBTTagCompound.func_74764_b("WITCWCount")) {
      witchesSpawned = par1NBTTagCompound.func_74762_e("WITCWCount");
    } else {
      witchesSpawned = 0;
    }
  }
}
