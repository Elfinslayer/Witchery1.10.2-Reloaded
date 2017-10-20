package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockGrassper.TileEntityGrassper;
import com.emoniph.witchery.blocks.BlockLeechChest.TileEntityLeechChest;
import com.emoniph.witchery.blocks.BlockWitchCrop;
import com.emoniph.witchery.entity.EntityMandrake;
import com.emoniph.witchery.entity.EntityOwl;
import com.emoniph.witchery.entity.EntityParasyticLouse;
import com.emoniph.witchery.entity.EntityToad;
import com.emoniph.witchery.entity.EntityWingedMonkey;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Dye;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Constructor;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ItemMutator extends ItemBase
{
  private static final int MAX_DAMAGE = 15;
  private static final int DAMAGE_PER_USE = 1;
  
  public ItemMutator()
  {
    func_77625_d(1);
    func_77656_e(15);
    func_77664_n();
  }
  
  @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
  public EnumRarity func_77613_e(ItemStack itemstack)
  {
    return EnumRarity.uncommon;
  }
  
  public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
  {
    Block block = world.func_147439_a(x, y, z);
    Material materialAbove = world.func_147439_a(x, y + 1, z).func_149688_o();
    if (block == Blocks.field_150349_c) {
      if (!field_72995_K) {
        world.func_147449_b(x, y, z, Blocks.field_150391_bh);
        ParticleEffect.INSTANT_SPELL.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, 1.0D + y, 0.5D + z, 1.0D, 1.0D, 8);
      }
      stack.func_77972_a(1, player);
    } else if (block == Blocks.field_150391_bh) {
      if (!field_72995_K) {
        world.func_147449_b(x, y, z, Blocks.field_150349_c);
        ParticleEffect.INSTANT_SPELL.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, 1.0D + y, 0.5D + z, 1.0D, 1.0D, 8);
      }
      stack.func_77972_a(1, player);
    } else if ((block == Blocks.field_150346_d) && (materialAbove == Material.field_151586_h)) {
      if (!field_72995_K) {
        ItemGeneral.setBlockToClay(world, x, y, z);
        ItemGeneral.setBlockToClay(world, x + 1, y, z);
        ItemGeneral.setBlockToClay(world, x - 1, y, z);
        ItemGeneral.setBlockToClay(world, x, y, z + 1);
        ItemGeneral.setBlockToClay(world, x, y, z - 1);
      }
      stack.func_77972_a(1, player);
    } else if (isMutatableTrapChest(world, x, y, z)) {
      if (!field_72995_K) {
        world.func_147468_f(x, y, z);
        world.func_147468_f(x + 1, y, z);
        world.func_147468_f(x - 1, y, z);
        world.func_147468_f(x, y, z + 1);
        world.func_147468_f(x, y, z - 1);
        world.func_147449_b(x, y, z, BlocksLEECH_CHEST);
        ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 1.0D, 2.0D, 8);
      }
      stack.func_77972_a(1, player);
    } else if (isMutatableReed(world, x, y, z)) {
      if (!field_72995_K)
      {

        world.func_147468_f(x + 1, y, z);
        world.func_147468_f(x - 1, y, z);
        world.func_147468_f(x, y, z + 1);
        world.func_147468_f(x, y, z - 1);
        
        clearGrassperAt(world, x + 1, y, z + 1);
        clearGrassperAt(world, x + 1, y, z - 1);
        clearGrassperAt(world, x - 1, y, z + 1);
        clearGrassperAt(world, x - 1, y, z - 1);
        
        ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 3.0D, 2.0D, 8);
        
        for (int i = 15; i >= 0; i--) {
          int adjY = y + i;
          if (world.func_147439_a(x, adjY, z) == Blocks.field_150436_aH) {
            world.func_147449_b(x, adjY, z, BlocksBRAMBLE);
            ParticleEffect.SLIME.send(SoundEffect.NONE, world, 0.5D + x, adjY, 0.5D + z, 1.0D, 1.0D, 16);
          }
        }
      }
      stack.func_77972_a(1, player);
    } else if (isMutatableWheat(world, x, y, z)) {
      if (!field_72995_K) {
        world.func_147468_f(x, y, z);
        world.func_147468_f(x + 1, y, z);
        world.func_147468_f(x - 1, y, z);
        world.func_147468_f(x, y, z + 1);
        world.func_147468_f(x, y, z - 1);
        BlockUtil.setBlock(world, x, y, z, BlocksCROP_WORMWOOD, 0, 3);
        
        ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 3.0D, 2.0D, 8);
      }
      stack.func_77972_a(1, player);
    } else if (isMutatableCactus(world, x, y, z)) {
      if (!field_72995_K) {
        world.func_147468_f(x + 1, y, z);
        world.func_147468_f(x - 1, y, z);
        world.func_147468_f(x, y, z + 1);
        world.func_147468_f(x, y, z - 1);
        
        clearGrassperAt(world, x + 1, y, z + 1);
        clearGrassperAt(world, x + 1, y, z - 1);
        clearGrassperAt(world, x - 1, y, z + 1);
        clearGrassperAt(world, x - 1, y, z - 1);
        
        ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 3.0D, 2.0D, 8);
        
        for (int i = 4; i >= 0; i--) {
          int adjY = y + i;
          if (world.func_147439_a(x, adjY, z) == Blocks.field_150434_aF) {
            world.func_147465_d(x, adjY, z, BlocksBRAMBLE, 1, 3);
            ParticleEffect.SLIME.send(SoundEffect.NONE, world, 0.5D + x, adjY, 0.5D + z, 1.0D, 1.0D, 16);
          }
        }
      }
      stack.func_77972_a(1, player);
    } else if (isMutatableChest(world, x, y, z)) {
      if (!field_72995_K) {
        world.func_147468_f(x, y, z);
        world.func_147449_b(x + 1, y, z, BlocksGRASSPER);
        world.func_147449_b(x - 1, y, z, BlocksGRASSPER);
        world.func_147449_b(x, y, z + 1, BlocksGRASSPER);
        world.func_147449_b(x, y, z - 1, BlocksGRASSPER);
        
        ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 3.0D, 2.0D, 8);
      }
      stack.func_77972_a(1, player);
    } else if (isMutatableLeechChest(world, x, y, z)) {
      if (!field_72995_K) {
        world.func_147468_f(x, y, z);
        world.func_147449_b(x + 1, y, z, BlocksBLOOD_ROSE);
        world.func_147449_b(x - 1, y, z, BlocksBLOOD_ROSE);
        world.func_147449_b(x, y, z + 1, BlocksBLOOD_ROSE);
        world.func_147449_b(x, y, z - 1, BlocksBLOOD_ROSE);
        
        ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 1.0D, 2.0D, 8);
      }
      stack.func_77972_a(1, player);
    } else if (isMutatableWeb(world, x, y, z)) {
      if (!field_72995_K) {
        world.func_147468_f(x, y, z);
        world.func_147449_b(x + 1, y, z, BlocksCRITTER_SNARE);
        world.func_147449_b(x - 1, y, z, BlocksCRITTER_SNARE);
        world.func_147449_b(x, y, z + 1, BlocksCRITTER_SNARE);
        world.func_147449_b(x, y, z - 1, BlocksCRITTER_SNARE);
        
        ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 3.0D, 2.0D, 8);
      }
      stack.func_77972_a(1, player);
    } else if (isMutatableToOwl(world, x, y, z)) {
      if (!field_72995_K) {
        world.func_147468_f(x, y, z);
        convertToEntity(world, x + 1, y, z, BlocksCRITTER_SNARE, 1, EntityOwl.class);
        convertToEntity(world, x - 1, y, z, BlocksCRITTER_SNARE, 1, EntityOwl.class);
        convertToEntity(world, x, y, z + 1, BlocksCRITTER_SNARE, 1, EntityOwl.class);
        convertToEntity(world, x, y, z - 1, BlocksCRITTER_SNARE, 1, EntityOwl.class);
        
        clearGrassperAt(world, x + 1, y, z + 1);
        clearGrassperAt(world, x + 1, y, z - 1);
        clearGrassperAt(world, x - 1, y, z + 1);
        clearGrassperAt(world, x - 1, y, z - 1);
        
        ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 3.0D, 2.0D, 8);
      }
      stack.func_77972_a(1, player);
    } else if (isMutatableToToad(world, x, y, z)) {
      if (!field_72995_K) {
        world.func_147468_f(x, y, z);
        convertToEntity(world, x + 1, y, z, BlocksCRITTER_SNARE, 3, EntityToad.class);
        convertToEntity(world, x - 1, y, z, BlocksCRITTER_SNARE, 3, EntityToad.class);
        convertToEntity(world, x, y, z + 1, BlocksCRITTER_SNARE, 3, EntityToad.class);
        convertToEntity(world, x, y, z - 1, BlocksCRITTER_SNARE, 3, EntityToad.class);
        
        clearGrassperAt(world, x + 1, y, z + 1);
        clearGrassperAt(world, x + 1, y, z - 1);
        clearGrassperAt(world, x - 1, y, z + 1);
        clearGrassperAt(world, x - 1, y, z - 1);
        
        ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 3.0D, 2.0D, 8);
      }
      stack.func_77972_a(1, player);
    } else if (isMutatableToMindrake(world, x, y, z)) {
      if (!field_72995_K) {
        world.func_147468_f(x, y, z);
        world.func_147449_b(x + 1, y, z, BlocksCROP_MINDRAKE);
        world.func_147449_b(x - 1, y, z, BlocksCROP_MINDRAKE);
        world.func_147449_b(x, y, z + 1, BlocksCROP_MINDRAKE);
        world.func_147449_b(x, y, z - 1, BlocksCROP_MINDRAKE);
        
        clearGrassperAt(world, x + 1, y, z + 1);
        clearGrassperAt(world, x + 1, y, z - 1);
        clearGrassperAt(world, x - 1, y, z + 1);
        clearGrassperAt(world, x - 1, y, z - 1);
        
        ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 3.0D, 2.0D, 8);
      }
      stack.func_77972_a(1, player);
    } else if (isMutatableToMonkey(world, x, y, z)) {
      if (!field_72995_K) {
        world.func_147468_f(x, y, z);
        world.func_147468_f(x + 1, y, z);
        world.func_147468_f(x - 1, y, z);
        world.func_147468_f(x, y, z + 1);
        world.func_147468_f(x, y, z - 1);
        
        clearGrassperAt(world, x + 1, y, z + 1);
        clearGrassperAt(world, x + 1, y, z - 1);
        clearGrassperAt(world, x - 1, y, z + 1);
        clearGrassperAt(world, x - 1, y, z - 1);
        
        EntityWingedMonkey monkey = new EntityWingedMonkey(world);
        monkey.func_70080_a(x + 0.5D, y, z + 0.5D, 0.0F, 0.0F);
        monkey.func_110163_bv();
        world.func_72838_d(monkey);
        
        ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 3.0D, 2.0D, 8);
      }
      stack.func_77972_a(1, player);
    } else if (isMutatableToLouse(world, x, y, z)) {
      if (!field_72995_K) {
        world.func_147468_f(x + 1, y, z);
        world.func_147468_f(x - 1, y, z);
        world.func_147468_f(x, y, z + 1);
        world.func_147468_f(x, y, z - 1);
        convertToEntity(world, x, y, z, BlocksCRITTER_SNARE, 2, EntityParasyticLouse.class);
        
        clearGrassperAt(world, x + 1, y, z + 1);
        clearGrassperAt(world, x + 1, y, z - 1);
        clearGrassperAt(world, x - 1, y, z + 1);
        clearGrassperAt(world, x - 1, y, z - 1);
        
        ParticleEffect.SLIME.send(SoundEffect.MOB_SLIME_BIG, world, 0.5D + x, y, 0.5D + z, 3.0D, 2.0D, 8);
      }
      stack.func_77972_a(1, player);
    } else {
      return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }
    
    return !field_72995_K;
  }
  
  private boolean isMutatableToLouse(World world, int x, int y, int z) {
    Block block = world.func_147439_a(x, y, z);
    int meta = world.func_72805_g(x, y, z);
    if ((block != BlocksCRITTER_SNARE) || (meta != 2)) {
      return false;
    }
    
    int vineCount = 0;
    vineCount += (world.func_147439_a(x + 1, y, z) == Blocks.field_150392_bi ? 1 : 0);
    vineCount += (world.func_147439_a(x - 1, y, z) == Blocks.field_150392_bi ? 1 : 0);
    vineCount += (world.func_147439_a(x, y, z + 1) == Blocks.field_150392_bi ? 1 : 0);
    vineCount += (world.func_147439_a(x, y, z - 1) == Blocks.field_150392_bi ? 1 : 0);
    
    int watercount = 0;
    watercount += (world.func_147439_a(x + 1, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
    watercount += (world.func_147439_a(x - 1, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
    watercount += (world.func_147439_a(x, y - 1, z + 1) == Blocks.field_150355_j ? 1 : 0);
    watercount += (world.func_147439_a(x, y - 1, z - 1) == Blocks.field_150355_j ? 1 : 0);
    
    int grasperAttunedCount = 0;
    ItemStack pearl = ItemsGENERIC.itemAttunedStoneCharged.createStack();
    grasperAttunedCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
    grasperAttunedCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
    grasperAttunedCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
    grasperAttunedCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
    
    int grasperCount = 0;
    pearl = ItemsGENERIC.itemMutandis.createStack();
    grasperCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
    grasperCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
    grasperCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
    grasperCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
    
    int grasperTongueCount = 0;
    pearl = ItemsGENERIC.itemDogTongue.createStack();
    grasperTongueCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
    grasperTongueCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
    grasperTongueCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
    grasperTongueCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
    
    if ((vineCount < 2) || (watercount < 1) || (grasperCount < 2) || (grasperAttunedCount < 1) || (grasperTongueCount < 1)) {
      return false;
    }
    
    return true;
  }
  
  private boolean isMutatableToToad(World world, int x, int y, int z)
  {
    Block blockID = world.func_147439_a(x, y, z);
    if (blockID != Blocks.field_150321_G) {
      return false;
    }
    
    int vineCount = 0;
    vineCount += ((world.func_147439_a(x + 1, y, z) == BlocksCRITTER_SNARE) && (world.func_72805_g(x + 1, y, z) == 3) ? 1 : 0);
    vineCount += ((world.func_147439_a(x - 1, y, z) == BlocksCRITTER_SNARE) && (world.func_72805_g(x - 1, y, z) == 3) ? 1 : 0);
    vineCount += ((world.func_147439_a(x, y, z + 1) == BlocksCRITTER_SNARE) && (world.func_72805_g(x, y, z + 1) == 3) ? 1 : 0);
    vineCount += ((world.func_147439_a(x, y, z - 1) == BlocksCRITTER_SNARE) && (world.func_72805_g(x, y, z - 1) == 3) ? 1 : 0);
    
    int watercount = 0;
    watercount += (world.func_147439_a(x, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
    

    int grasperAttunedCount = 0;
    ItemStack pearl = ItemsGENERIC.itemAttunedStoneCharged.createStack();
    grasperAttunedCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
    grasperAttunedCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
    grasperAttunedCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
    grasperAttunedCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
    
    int grasperCount = 0;
    pearl = ItemsGENERIC.itemMutandisExtremis.createStack();
    grasperCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
    grasperCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
    grasperCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
    grasperCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
    
    if ((vineCount < 2) || (watercount < 1) || (grasperCount < 3) || (grasperAttunedCount < 1)) {
      return false;
    }
    
    AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(x - 1, y - 1, z - 1, x + 1, y + 2, z + 1);
    List list = world.func_72872_a(EntityOcelot.class, aabb);
    if (list.size() == 0) {
      return false;
    }
    if (!field_72995_K) {
      EntityOcelot ocelot = (EntityOcelot)list.get(0);
      ParticleEffect.SLIME.send(SoundEffect.MOB_OCELOT_DEATH, ocelot, 3.0D, 2.0D, 8);
      ocelot.func_70106_y();
    }
    

    return true;
  }
  
  private boolean isMutatableToMonkey(World world, int x, int y, int z) {
    Block blockID = world.func_147439_a(x, y, z);
    if (blockID != Blocks.field_150321_G) {
      return false;
    }
    
    int vineCount = 0;
    vineCount += ((world.func_147439_a(x + 1, y, z) == Blocks.field_150375_by) && (BlockCocoa.func_149987_c(world.func_72805_g(x + 1, y, z)) == 2) ? 1 : 0);
    vineCount += ((world.func_147439_a(x - 1, y, z) == Blocks.field_150375_by) && (BlockCocoa.func_149987_c(world.func_72805_g(x - 1, y, z)) == 2) ? 1 : 0);
    vineCount += ((world.func_147439_a(x, y, z + 1) == Blocks.field_150375_by) && (BlockCocoa.func_149987_c(world.func_72805_g(x, y, z + 1)) == 2) ? 1 : 0);
    vineCount += ((world.func_147439_a(x, y, z - 1) == Blocks.field_150375_by) && (BlockCocoa.func_149987_c(world.func_72805_g(x, y, z - 1)) == 2) ? 1 : 0);
    
    int watercount = 0;
    watercount += (world.func_147439_a(x, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
    
    int grasperAttunedCount = 0;
    ItemStack pearl = ItemsGENERIC.itemAttunedStoneCharged.createStack();
    grasperAttunedCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
    grasperAttunedCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
    grasperAttunedCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
    grasperAttunedCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
    
    int grasperCount = 0;
    pearl = ItemsGENERIC.itemMutandisExtremis.createStack();
    grasperCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
    grasperCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
    grasperCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
    grasperCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
    
    int grasperTongueCount = 0;
    pearl = new ItemStack(Blocks.field_150328_O);
    grasperTongueCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
    grasperTongueCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
    grasperTongueCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
    grasperTongueCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
    
    if ((vineCount < 4) || (watercount < 1) || (grasperCount < 2) || (grasperAttunedCount < 1) || (grasperTongueCount < 1)) {
      return false;
    }
    
    EntityOwl owl = null;
    AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(x - 1, y - 1, z - 1, x + 1, y + 2, z + 1);
    List<EntityOwl> owlList = world.func_72872_a(EntityOwl.class, aabb);
    if (owlList.size() == 0) {
      return false;
    }
    if (!field_72995_K) {
      owl = (EntityOwl)owlList.get(0);
    }
    

    EntityWolf wolf = null;
    aabb = AxisAlignedBB.func_72330_a(x - 1, y - 1, z - 1, x + 1, y + 2, z + 1);
    List<EntityWolf> wolfList = world.func_72872_a(EntityWolf.class, aabb);
    if (wolfList.size() == 0) {
      return false;
    }
    if (!field_72995_K) {
      wolf = (EntityWolf)wolfList.get(0);
    }
    

    if ((owl != null) && (wolf != null)) {
      ParticleEffect.SLIME.send(SoundEffect.MOB_CREEPER_DEATH, owl, 3.0D, 2.0D, 8);
      owl.func_70106_y();
      
      ParticleEffect.SLIME.send(SoundEffect.MOB_GHAST_DEATH, wolf, 3.0D, 2.0D, 8);
      wolf.func_70106_y();
    } else {
      return false;
    }
    
    return true;
  }
  
  private boolean isMutatableToOwl(World world, int x, int y, int z) {
    Block blockID = world.func_147439_a(x, y, z);
    if (blockID != Blocks.field_150321_G) {
      return false;
    }
    
    int vineCount = 0;
    vineCount += ((world.func_147439_a(x + 1, y, z) == BlocksCRITTER_SNARE) && (world.func_72805_g(x + 1, y, z) == 1) ? 1 : 0);
    vineCount += ((world.func_147439_a(x - 1, y, z) == BlocksCRITTER_SNARE) && (world.func_72805_g(x - 1, y, z) == 1) ? 1 : 0);
    vineCount += ((world.func_147439_a(x, y, z + 1) == BlocksCRITTER_SNARE) && (world.func_72805_g(x, y, z + 1) == 1) ? 1 : 0);
    vineCount += ((world.func_147439_a(x, y, z - 1) == BlocksCRITTER_SNARE) && (world.func_72805_g(x, y, z - 1) == 1) ? 1 : 0);
    
    int watercount = 0;
    watercount += (world.func_147439_a(x, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
    

    int grasperAttunedCount = 0;
    ItemStack pearl = ItemsGENERIC.itemAttunedStoneCharged.createStack();
    grasperAttunedCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
    grasperAttunedCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
    grasperAttunedCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
    grasperAttunedCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
    
    int grasperCount = 0;
    pearl = ItemsGENERIC.itemMutandisExtremis.createStack();
    grasperCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
    grasperCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
    grasperCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
    grasperCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
    
    if ((vineCount < 2) || (watercount < 1) || (grasperCount < 3) || (grasperAttunedCount < 1)) {
      return false;
    }
    
    AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(x - 1, y - 1, z - 1, x + 1, y + 2, z + 1);
    List list = world.func_72872_a(EntityWolf.class, aabb);
    if (list.size() == 0) {
      return false;
    }
    if (!field_72995_K) {
      EntityWolf wolf = (EntityWolf)list.get(0);
      ParticleEffect.SLIME.send(SoundEffect.MOB_WOLF_DEATH, wolf, 3.0D, 2.0D, 8);
      wolf.func_70106_y();
    }
    

    return true;
  }
  
  private void convertToEntity(World world, int x, int y, int z, Block block, int blockMeta, Class<? extends EntityLiving> entityClass) {
    Block foundBlock = world.func_147439_a(x, y, z);
    int foundBlockMeta = world.func_72805_g(x, y, z);
    if ((foundBlock == block) && (foundBlockMeta == blockMeta) && (entityClass != null)) {
      world.func_147468_f(x, y, z);
      try {
        Constructor ctor = entityClass.getConstructor(new Class[] { World.class });
        EntityLiving entity = (EntityLiving)ctor.newInstance(new Object[] { world });
        entity.func_70012_b(0.5D + x, 0.001D + y, 0.5D + z, 1.0F, 0.0F);
        
        world.func_72838_d(entity);
        IEntityLivingData entitylivingData = null;
        entity.func_110163_bv();
        entitylivingData = entity.func_110161_a(entitylivingData);
      }
      catch (Throwable e) {
        Log.instance().warning(e, "Error occurred while mutating a creature with a sprig");
      }
    }
  }
  
  private boolean isMutatableWeb(World world, int x, int y, int z) {
    Block blockID = world.func_147439_a(x, y, z);
    if (blockID != Blocks.field_150321_G) {
      return false;
    }
    
    int vineCount = 0;
    vineCount += ((world.func_147439_a(x + 1, y, z) == BlocksSAPLING) && ((world.func_72805_g(x + 1, y, z) & 0x3) == 1) ? 1 : 0);
    vineCount += ((world.func_147439_a(x - 1, y, z) == BlocksSAPLING) && ((world.func_72805_g(x - 1, y, z) & 0x3) == 1) ? 1 : 0);
    vineCount += ((world.func_147439_a(x, y, z + 1) == BlocksSAPLING) && ((world.func_72805_g(x, y, z + 1) & 0x3) == 1) ? 1 : 0);
    vineCount += ((world.func_147439_a(x, y, z - 1) == BlocksSAPLING) && ((world.func_72805_g(x, y, z - 1) & 0x3) == 1) ? 1 : 0);
    
    int watercount = 0;
    watercount += (world.func_147439_a(x, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
    
    if ((vineCount < 4) || (watercount < 1)) {
      return false;
    }
    
    AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(x - 1, y - 1, z - 1, x + 1, y + 2, z + 1);
    List list = world.func_72872_a(EntityZombie.class, aabb);
    if (list.size() == 0) {
      return false;
    }
    if (!field_72995_K) {
      EntityZombie zombie = (EntityZombie)list.get(0);
      ParticleEffect.SLIME.send(SoundEffect.MOB_ZOMBIE_DEATH, zombie, 3.0D, 2.0D, 8);
      zombie.func_70106_y();
    }
    

    return true;
  }
  
  private boolean isMutatableToMindrake(World world, int x, int y, int z) {
    Block blockID = world.func_147439_a(x, y, z);
    if (blockID != Blocks.field_150321_G) {
      return false;
    }
    
    int vineCount = 0;
    vineCount += ((world.func_147439_a(x + 1, y, z) == BlocksCROP_MANDRAKE) && (world.func_72805_g(x + 1, y, z) == BlocksCROP_MANDRAKE.getNumGrowthStages()) ? 1 : 0);
    vineCount += ((world.func_147439_a(x - 1, y, z) == BlocksCROP_MANDRAKE) && (world.func_72805_g(x - 1, y, z) == BlocksCROP_MANDRAKE.getNumGrowthStages()) ? 1 : 0);
    vineCount += ((world.func_147439_a(x, y, z + 1) == BlocksCROP_MANDRAKE) && (world.func_72805_g(x, y, z + 1) == BlocksCROP_MANDRAKE.getNumGrowthStages()) ? 1 : 0);
    vineCount += ((world.func_147439_a(x, y, z - 1) == BlocksCROP_MANDRAKE) && (world.func_72805_g(x, y, z - 1) == BlocksCROP_MANDRAKE.getNumGrowthStages()) ? 1 : 0);
    
    int watercount = 0;
    watercount += (world.func_147439_a(x, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
    
    int grasperAttunedCount = 0;
    ItemStack pearl = ItemsGENERIC.itemAttunedStoneCharged.createStack();
    grasperAttunedCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
    grasperAttunedCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
    grasperAttunedCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
    grasperAttunedCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
    
    int grasperCount = 0;
    pearl = ItemsGENERIC.itemMutandisExtremis.createStack();
    grasperCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
    grasperCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
    grasperCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
    grasperCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
    
    int grasperTongueCount = 0;
    pearl = ItemsGENERIC.itemFocusedWill.createStack();
    grasperTongueCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
    grasperTongueCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
    grasperTongueCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
    grasperTongueCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
    
    if ((vineCount < 4) || (watercount < 1) || (grasperCount < 2) || (grasperAttunedCount < 1) || (grasperTongueCount < 1)) {
      return false;
    }
    
    EntityCreeper creeper = null;
    AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(x - 1, y - 1, z - 1, x + 1, y + 2, z + 1);
    List list = world.func_72872_a(EntityCreeper.class, aabb);
    if (list.size() == 0) {
      return false;
    }
    if (!field_72995_K) {
      creeper = (EntityCreeper)list.get(0);
    }
    

    EntityMandrake mandrake = null;
    aabb = AxisAlignedBB.func_72330_a(x - 1, y - 1, z - 1, x + 1, y + 2, z + 1);
    list = world.func_72872_a(EntityMandrake.class, aabb);
    if (list.size() == 0) {
      return false;
    }
    if (!field_72995_K) {
      mandrake = (EntityMandrake)list.get(0);
    }
    

    if ((creeper != null) && (mandrake != null)) {
      ParticleEffect.SLIME.send(SoundEffect.MOB_CREEPER_DEATH, creeper, 3.0D, 2.0D, 8);
      creeper.func_70106_y();
      
      ParticleEffect.SLIME.send(SoundEffect.MOB_GHAST_DEATH, mandrake, 3.0D, 2.0D, 8);
      mandrake.func_70106_y();
    } else {
      return false;
    }
    
    return true;
  }
  
  private boolean isMutatableTrapChest(World world, int x, int y, int z) {
    Block blockID = world.func_147439_a(x, y, z);
    if (blockID != Blocks.field_150447_bR) {
      return false;
    }
    
    int vineCount = 0;
    vineCount += (world.func_147439_a(x + 1, y, z) == Blocks.field_150395_bd ? 1 : 0);
    vineCount += (world.func_147439_a(x - 1, y, z) == Blocks.field_150395_bd ? 1 : 0);
    vineCount += (world.func_147439_a(x, y, z + 1) == Blocks.field_150395_bd ? 1 : 0);
    vineCount += (world.func_147439_a(x, y, z - 1) == Blocks.field_150395_bd ? 1 : 0);
    
    int watercount = 0;
    watercount += (world.func_147439_a(x + 1, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
    watercount += (world.func_147439_a(x - 1, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
    watercount += (world.func_147439_a(x, y - 1, z + 1) == Blocks.field_150355_j ? 1 : 0);
    watercount += (world.func_147439_a(x, y - 1, z - 1) == Blocks.field_150355_j ? 1 : 0);
    
    if ((vineCount < 4) || (watercount < 4)) {
      return false;
    }
    
    TileEntity tile = world.func_147438_o(x, y, z);
    if ((tile == null) || (!(tile instanceof TileEntityChest))) {
      return false;
    }
    
    TileEntityChest chest = (TileEntityChest)tile;
    for (int i = 0; i < chest.func_70302_i_(); i++) {
      if (chest.func_70301_a(i) != null) {
        return false;
      }
    }
    
    return true;
  }
  
  private boolean isMutatableWheat(World world, int x, int y, int z) {
    Block block = BlockUtil.getBlock(world, x, y, z);
    if ((block != Blocks.field_150464_aj) || (BlockUtil.getBlockMetadata(world, x, y, z) != 7)) {
      return false;
    }
    
    int vineCount = 0;
    vineCount += (BlockUtil.getBlock(world, x + 1, y, z) == BlocksWISPY_COTTON ? 1 : 0);
    vineCount += (BlockUtil.getBlock(world, x - 1, y, z) == BlocksWISPY_COTTON ? 1 : 0);
    vineCount += (BlockUtil.getBlock(world, x, y, z + 1) == BlocksWISPY_COTTON ? 1 : 0);
    vineCount += (BlockUtil.getBlock(world, x, y, z - 1) == BlocksWISPY_COTTON ? 1 : 0);
    
    int watercount = 0;
    watercount += (BlockUtil.getBlock(world, x + 1, y - 1, z + 1) == Blocks.field_150355_j ? 1 : 0);
    watercount += (BlockUtil.getBlock(world, x + 1, y - 1, z - 1) == Blocks.field_150355_j ? 1 : 0);
    watercount += (BlockUtil.getBlock(world, x - 1, y - 1, z + 1) == Blocks.field_150355_j ? 1 : 0);
    watercount += (BlockUtil.getBlock(world, x - 1, y - 1, z - 1) == Blocks.field_150355_j ? 1 : 0);
    
    if ((vineCount < 4) || (watercount < 4)) {
      return false;
    }
    
    return true;
  }
  
  private boolean isMutatableLeechChest(World world, int x, int y, int z) {
    Block blockID = world.func_147439_a(x, y, z);
    if (blockID != BlocksLEECH_CHEST) {
      return false;
    }
    
    int vineCount = 0;
    vineCount += (world.func_147439_a(x + 1, y, z) == Blocks.field_150328_O ? 1 : 0);
    vineCount += (world.func_147439_a(x - 1, y, z) == Blocks.field_150328_O ? 1 : 0);
    vineCount += (world.func_147439_a(x, y, z + 1) == Blocks.field_150328_O ? 1 : 0);
    vineCount += (world.func_147439_a(x, y, z - 1) == Blocks.field_150328_O ? 1 : 0);
    
    int watercount = 0;
    watercount += (world.func_147439_a(x, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
    

    if ((vineCount < 4) || (watercount < 1)) {
      return false;
    }
    
    TileEntity tile = world.func_147438_o(x, y, z);
    if ((tile == null) || (!(tile instanceof BlockLeechChest.TileEntityLeechChest))) {
      return false;
    }
    
    BlockLeechChest.TileEntityLeechChest chest = (BlockLeechChest.TileEntityLeechChest)tile;
    for (int i = 0; i < chest.func_70302_i_(); i++) {
      if (chest.func_70301_a(i) != null) {
        return false;
      }
    }
    
    return true;
  }
  
  private boolean isMutatableChest(World world, int x, int y, int z) {
    Block blockID = world.func_147439_a(x, y, z);
    if (blockID != Blocks.field_150486_ae) {
      return false;
    }
    
    int vineCount = 0;
    vineCount += (world.func_147439_a(x + 1, y, z) == Blocks.field_150329_H ? 1 : 0);
    vineCount += (world.func_147439_a(x - 1, y, z) == Blocks.field_150329_H ? 1 : 0);
    vineCount += (world.func_147439_a(x, y, z + 1) == Blocks.field_150329_H ? 1 : 0);
    vineCount += (world.func_147439_a(x, y, z - 1) == Blocks.field_150329_H ? 1 : 0);
    
    int watercount = 0;
    watercount += (world.func_147439_a(x, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
    
    if ((vineCount < 4) || (watercount < 1)) {
      return false;
    }
    
    TileEntity tile = world.func_147438_o(x, y, z);
    if ((tile == null) || (!(tile instanceof TileEntityChest))) {
      return false;
    }
    
    TileEntityChest chest = (TileEntityChest)tile;
    for (int i = 0; i < chest.func_70302_i_(); i++) {
      if (chest.func_70301_a(i) != null) {
        return false;
      }
    }
    
    return true;
  }
  
  private boolean isMutatableReed(World world, int x, int y, int z) {
    Block blockID = world.func_147439_a(x, y, z);
    if (blockID != Blocks.field_150436_aH) {
      return false;
    }
    
    int vineCount = 0;
    vineCount += (world.func_147439_a(x + 1, y, z) == BlocksSPANISH_MOSS ? 1 : 0);
    vineCount += (world.func_147439_a(x - 1, y, z) == BlocksSPANISH_MOSS ? 1 : 0);
    vineCount += (world.func_147439_a(x, y, z + 1) == BlocksSPANISH_MOSS ? 1 : 0);
    vineCount += (world.func_147439_a(x, y, z - 1) == BlocksSPANISH_MOSS ? 1 : 0);
    
    int watercount = 0;
    watercount += (world.func_147439_a(x + 1, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
    watercount += (world.func_147439_a(x - 1, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
    watercount += (world.func_147439_a(x, y - 1, z + 1) == Blocks.field_150355_j ? 1 : 0);
    watercount += (world.func_147439_a(x, y - 1, z - 1) == Blocks.field_150355_j ? 1 : 0);
    

    int grasperCount = 0;
    ItemStack pearl = new ItemStack(Items.field_151079_bi);
    grasperCount += (isGrasperWith(world, x + 1, y, z + 1, pearl) ? 1 : 0);
    grasperCount += (isGrasperWith(world, x + 1, y, z - 1, pearl) ? 1 : 0);
    grasperCount += (isGrasperWith(world, x - 1, y, z + 1, pearl) ? 1 : 0);
    grasperCount += (isGrasperWith(world, x - 1, y, z - 1, pearl) ? 1 : 0);
    
    if ((vineCount < 4) || (watercount < 4) || (grasperCount < 4)) {
      return false;
    }
    
    return true;
  }
  
  private boolean isMutatableCactus(World world, int x, int y, int z) {
    Block blockID = world.func_147439_a(x, y, z);
    if (blockID != Blocks.field_150434_aF) {
      return false;
    }
    
    int vineCount = 0;
    vineCount += (world.func_147439_a(x + 1, y, z) == BlocksSPANISH_MOSS ? 1 : 0);
    vineCount += (world.func_147439_a(x - 1, y, z) == BlocksSPANISH_MOSS ? 1 : 0);
    vineCount += (world.func_147439_a(x, y, z + 1) == BlocksSPANISH_MOSS ? 1 : 0);
    vineCount += (world.func_147439_a(x, y, z - 1) == BlocksSPANISH_MOSS ? 1 : 0);
    
    int watercount = 0;
    watercount += (world.func_147439_a(x + 1, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
    watercount += (world.func_147439_a(x - 1, y - 1, z) == Blocks.field_150355_j ? 1 : 0);
    watercount += (world.func_147439_a(x, y - 1, z + 1) == Blocks.field_150355_j ? 1 : 0);
    watercount += (world.func_147439_a(x, y - 1, z - 1) == Blocks.field_150355_j ? 1 : 0);
    

    int boneMeal = 0;
    ItemStack bone = Dye.BONE_MEAL.createStack();
    boneMeal += (isGrasperWith(world, x + 1, y, z + 1, bone) ? 1 : 0);
    boneMeal += (isGrasperWith(world, x + 1, y, z - 1, bone) ? 1 : 0);
    boneMeal += (isGrasperWith(world, x - 1, y, z + 1, bone) ? 1 : 0);
    boneMeal += (isGrasperWith(world, x - 1, y, z - 1, bone) ? 1 : 0);
    
    int blazePowder = 0;
    ItemStack blaze = new ItemStack(Items.field_151065_br);
    blazePowder += (isGrasperWith(world, x + 1, y, z + 1, blaze) ? 1 : 0);
    blazePowder += (isGrasperWith(world, x + 1, y, z - 1, blaze) ? 1 : 0);
    blazePowder += (isGrasperWith(world, x - 1, y, z + 1, blaze) ? 1 : 0);
    blazePowder += (isGrasperWith(world, x - 1, y, z - 1, blaze) ? 1 : 0);
    
    if ((vineCount < 4) || (watercount < 4) || (boneMeal < 2) || (blazePowder < 2)) {
      return false;
    }
    
    return true;
  }
  
  private boolean isGrasperWith(World world, int x, int y, int z, ItemStack stack) {
    Block blockID = world.func_147439_a(x, y, z);
    if (blockID == BlocksGRASSPER) {
      TileEntity tile = world.func_147438_o(x, y, z);
      if ((tile != null) && ((tile instanceof BlockGrassper.TileEntityGrassper))) {
        BlockGrassper.TileEntityGrassper grassperTile = (BlockGrassper.TileEntityGrassper)tile;
        ItemStack foundStack = grassperTile.func_70301_a(0);
        return (foundStack != null) && (foundStack.func_77969_a(stack));
      }
    }
    
    return false;
  }
  
  private void clearGrassperAt(World world, int x, int y, int z) {
    Block blockID = world.func_147439_a(x, y, z);
    if (blockID == BlocksGRASSPER) {
      TileEntity tile = world.func_147438_o(x, y, z);
      if ((tile != null) && ((tile instanceof BlockGrassper.TileEntityGrassper))) {
        BlockGrassper.TileEntityGrassper grassperTile = (BlockGrassper.TileEntityGrassper)tile;
        grassperTile.func_70299_a(0, null);
        ParticleEffect.SLIME.send(SoundEffect.NONE, world, 0.5D + x, y, 0.5D + z, 1.0D, 2.0D, 8);
      }
    }
  }
}
