package com.emoniph.witchery.brewing.action.effect;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.brewing.AltarPower;
import com.emoniph.witchery.brewing.BrewItemKey;
import com.emoniph.witchery.brewing.BrewNamePart;
import com.emoniph.witchery.brewing.EffectLevel;
import com.emoniph.witchery.brewing.ModifiersEffect;
import com.emoniph.witchery.brewing.Probability;
import com.emoniph.witchery.brewing.action.BrewActionEffect;
import com.emoniph.witchery.brewing.potions.WitcheryPotions;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.TimeUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BrewActionSprouting extends BrewActionEffect
{
  public BrewActionSprouting(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, Probability baseProbability, EffectLevel effectLevel)
  {
    super(itemKey, namePart, powerCost, baseProbability, effectLevel);
  }
  

  protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack)
  {
    AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(x, y, z, x + 1, y + 1, z + 1);
    growBranch(x, y, z, world, side.ordinal(), 8 + 2 * modifiers.getStrength(), bounds);
  }
  

  protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack)
  {
    targetEntity.func_70690_d(new PotionEffect(PotionsSPROUTING.field_76415_H, modifiers.getModifiedDuration(TimeUtil.secsToTicks(15)), modifiers.getStrength()));
  }
  
  public static void growBranch(EntityLivingBase entity, int extent)
  {
    Coord coord = new Coord(entity);
    growBranch(x, y - 1, z, field_70170_p, ForgeDirection.UP.ordinal(), extent, field_70121_D.func_72314_b(0.5D, 0.5D, 0.5D));
  }
  

  public static void growBranch(int posX, int posY, int posZ, World world, int sideHit, int extent, AxisAlignedBB boundingBox)
  {
    if (field_72995_K) {
      return;
    }
    
    Block blockID = world.func_147439_a(posX, posY, posZ);
    int j1 = world.func_72805_g(posX, posY, posZ);
    Block logBlock; Block logBlock; if ((blockID == Blocks.field_150364_r) || (blockID == Blocks.field_150344_f) || (blockID == Blocks.field_150345_g) || (blockID == Blocks.field_150362_t))
    {
      logBlock = Blocks.field_150364_r; } else { Block logBlock;
      if ((blockID == BlocksLOG) || (blockID == BlocksPLANKS) || (blockID == BlocksSAPLING) || (blockID == BlocksLEAVES))
      {
        logBlock = BlocksLOG;
      } else {
        logBlock = field_73012_v.nextInt(2) == 0 ? Blocks.field_150364_r : BlocksLOG;
        j1 = field_73012_v.nextInt(Blocks.field_150364_r == logBlock ? 4 : 3);
      } }
    Block leavesBlock = Blocks.field_150364_r == logBlock ? Blocks.field_150362_t : BlocksLEAVES;
    int b0 = 0;
    j1 &= 0x3;
    
    switch (sideHit) {
    case 0: 
    case 1: 
      b0 = 0;
      break;
    case 2: 
    case 3: 
      b0 = 8;
      break;
    case 4: 
    case 5: 
      b0 = 4;
    }
    
    int meta = j1 | b0;
    
    ParticleEffect particleEffect = ParticleEffect.EXPLODE;
    
    int dx = sideHit == 4 ? -1 : sideHit == 5 ? 1 : 0;
    int dy = sideHit == 1 ? 1 : sideHit == 0 ? -1 : 0;
    int dz = sideHit == 2 ? -1 : sideHit == 3 ? 1 : 0;
    
    int sproutExtent = extent;
    
    boolean isInitialBlockSolid = world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a();
    

    for (int i = (sideHit == 1) && (!isInitialBlockSolid) ? 0 : 1; i <= sproutExtent; i++) {
      int x = posX + i * dx;
      int y = posY + i * dy;
      int z = posZ + i * dz;
      if (y >= 255) {
        break;
      }
      
      if (!BlockUtil.setBlockIfReplaceable(world, x, y, z, logBlock, meta)) {
        break;
      }
      int lx = (dx == 0) && (field_73012_v.nextInt(4) == 0) ? field_73012_v.nextInt(3) - 1 : 0;
      int ly = (dy == 0) && (lx == 0) && (field_73012_v.nextInt(4) == 0) ? field_73012_v.nextInt(3) - 1 : 0;
      int lz = (dz == 0) && (lx == 0) && (ly == 0) && (field_73012_v.nextInt(4) == 0) ? field_73012_v.nextInt(3) - 1 : 0;
      
      if ((lx != 0) || (ly != 0) || (lz != 0)) {
        BlockUtil.setBlockIfReplaceable(world, x + lx, y + ly, z + lz, leavesBlock, meta);
      }
    }
    
    if (sideHit == 1) {
      AxisAlignedBB axisalignedbb = boundingBox.func_72314_b(0.0D, 2.0D, 0.0D);
      List<EntityLivingBase> list1 = world.func_72872_a(EntityLivingBase.class, axisalignedbb);
      
      if ((list1 != null) && (!list1.isEmpty())) {
        Iterator<EntityLivingBase> iterator = list1.iterator();
        int x = posX + i * dx;
        int y = Math.min(posY + i * dy, 255);
        int z = posZ + i * dz;
        while (iterator.hasNext()) {
          EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();
          if ((!world.func_147439_a(x, y + 1, z).func_149688_o().func_76220_a()) && (!world.func_147439_a(x, y + 2, z).func_149688_o().func_76220_a()))
          {
            if ((entitylivingbase instanceof net.minecraft.entity.player.EntityPlayer)) {
              entitylivingbase.func_70634_a(0.5D + x, y + 2, 0.5D + z);
            } else {
              entitylivingbase.func_70634_a(0.5D + x, y + 2, 0.5D + z);
            }
          }
        }
      }
    }
  }
}
