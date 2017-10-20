package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.common.IPowerSource;
import com.emoniph.witchery.common.ServerTickEvents.ServerTickTask;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemGeneral.SubItem;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.EntityPosition;
import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityItemWaystone extends EntityItem
{
  public EntityItemWaystone(World world)
  {
    super(world);
  }
  
  public EntityItemWaystone(World world, double x, double y, double z) {
    super(world, x, y, z);
  }
  
  public EntityItemWaystone(World world, double x, double y, double z, ItemStack stack) {
    super(world, x, y, z, stack);
  }
  
  public EntityItemWaystone(EntityItem entityItem) {
    super(field_70170_p, field_70165_t, field_70163_u, field_70161_v, entityItem.func_92059_d());
    field_145804_b = field_145804_b;
    field_70159_w = field_70159_w;
    field_70181_x = field_70181_x;
    field_70179_y = field_70179_y;
  }
  
  public void func_70100_b_(EntityPlayer player)
  {
    double minPickupRange = 0.75D;
    double minPickupRangeSq = 0.5625D;
    if (func_70068_e(player) <= 0.5625D) {
      super.func_70100_b_(player);
    }
  }
  
  public void func_70071_h_()
  {
    super.func_70071_h_();
    
    if ((!field_70170_p.field_72995_K) && (field_70292_b > com.emoniph.witchery.util.TimeUtil.secsToTicks(2)) && (field_70292_b % 40 == 0))
      if (ItemsGENERIC.itemWaystone.isMatch(func_92059_d())) {
        Block glyph = BlocksGLYPH_OTHERWHERE;
        Coord center = isTinyBlockCircle(field_70170_p, new Coord(this), glyph);
        if (center != null) {
          int originalStackSize = func_92059_dfield_77994_a;
          int remainingStackSize = 0;
          double R = 2.0D;
          double RSq = 4.0D;
          EntityPosition centerPoint = new EntityPosition(x + 0.5D, y + 0.5D, z + 0.5D);
          AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(x - 2.0D, y - 2.0D, z - 2.0D, x + 2.0D, y + 2.0D, z + 2.0D);
          

          ItemStack boundStone = null;
          EntityLivingBase target = null;
          double targetDistSq = -1.0D;
          List<EntityPlayer> nearbyPlayers = field_70170_p.func_72872_a(EntityPlayer.class, bounds);
          for (EntityPlayer player : nearbyPlayers) {
            double distSq = player.func_70092_e(x, field_70163_u, z);
            if ((distSq <= 4.0D) && (
              (target == null) || (distSq < targetDistSq))) {
              target = player;
              targetDistSq = distSq;
            }
          }
          
          if (target == null) {
            List<EntityLiving> nearbyCreatures = field_70170_p.func_72872_a(EntityLiving.class, bounds);
            for (EntityLiving creature : nearbyCreatures) {
              double distSq = creature.func_70092_e(x, field_70163_u, z);
              if ((distSq <= 4.0D) && (
                (target == null) || (distSq < targetDistSq))) {
                target = creature;
                targetDistSq = distSq;
              }
            }
          }
          

          if (target != null) {
            IPowerSource power = com.emoniph.witchery.common.PowerSources.findClosestPowerSource(field_70170_p, center);
            if (power != null) {
              if (power.consumePower(4000.0F)) {
                int convertableStackSize = Math.min(originalStackSize, 1);
                remainingStackSize = originalStackSize - convertableStackSize;
                boundStone = ItemsGENERIC.itemWaystonePlayerBound.createStack(convertableStackSize);
                
                ItemsTAGLOCK_KIT.setTaglockForEntity(boundStone, null, target, false, Integer.valueOf(1));
              } else {
                ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, field_70170_p, center, 1.0D, 1.0D, 16);
              }
            } else {
              ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, field_70170_p, center, 1.0D, 1.0D, 16);
            }
          } else {
            int convertableStackSize = Math.min(originalStackSize, 8);
            remainingStackSize = originalStackSize - convertableStackSize;
            boundStone = ItemsGENERIC.itemWaystoneBound.createStack(convertableStackSize);
            ItemsGENERIC.bindToLocation(field_70170_p, x, y, z, field_71093_bK, field_70170_p.field_73011_w.func_80007_l(), boundStone);
          }
          

          if (boundStone != null) {
            EntityUtil.spawnEntityInWorld(field_70170_p, new EntityItem(field_70170_p, field_70165_t, field_70163_u, field_70161_v, boundStone));
            if (remainingStackSize > 0) {
              EntityUtil.spawnEntityInWorld(field_70170_p, new EntityItem(field_70170_p, field_70165_t, field_70163_u, field_70161_v, ItemsGENERIC.itemWaystone.createStack(remainingStackSize)));
            }
            

            ParticleEffect.LARGE_EXPLODE.send(SoundEffect.RANDOM_POP, this, 1.0D, 1.0D, 16);
            isInnerTinyBlockCircle(field_70170_p, x, y, z, glyph, true);
            func_70106_y();
          } } } else { Coord center;
        ItemStack usedStone;
        if ((ItemsGENERIC.itemWaystoneBound.isMatch(func_92059_d())) || (ItemsGENERIC.itemWaystonePlayerBound.isMatch(func_92059_d())))
        {
          Block glyph = BlocksGLYPH_OTHERWHERE;
          center = isSmallBlockCircle(field_70170_p, new Coord(this), glyph);
          if (center != null) {
            double R = 4.0D;
            double RSq = 16.0D;
            usedStone = func_92059_d().func_77979_a(1);
            if (func_92059_dfield_77994_a > 0) {
              EntityUtil.spawnEntityInWorld(field_70170_p, new EntityItem(field_70170_p, field_70165_t, field_70163_u, field_70161_v, func_92059_d()));
            }
            
            func_70106_y();
            AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(x + 0.5D - 4.0D, y + 0.5D - 4.0D, z + 0.5D - 4.0D, x + 0.5D + 4.0D, y + 0.5D + 4.0D, z + 0.5D + 4.0D);
            
            List<Entity> list = field_70170_p.func_72872_a(Entity.class, bounds);
            




            for (Entity entity : list) {
              if ((!field_70128_L) && (entity.func_70092_e(0.5D + x, field_70163_u, 0.5D + z) <= 16.0D) && (((entity instanceof EntityLivingBase)) || ((entity instanceof EntityItem))) && (!com.emoniph.witchery.brewing.potions.PotionEnderInhibition.isActive(entity, 1)))
              {

                com.emoniph.witchery.common.ServerTickEvents.TASKS.add(new TeleportTask(field_70170_p, usedStone, entity));
              }
            }
          }
        }
        else if (ItemsGENERIC.itemAttunedStone.isMatch(func_92059_d())) {
          Block glyph = BlocksGLYPH_RITUAL;
          Coord center = isTinyBlockCircle(field_70170_p, new Coord(this), glyph);
          if (center != null) {
            int originalStackSize = func_92059_dfield_77994_a;
            int remainingStackSize = 0;
            double R = 2.0D;
            double RSq = 4.0D;
            EntityPosition centerPoint = new EntityPosition(x + 0.5D, y + 0.5D, z + 0.5D);
            AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(x - 2.0D, y - 2.0D, z - 2.0D, x + 2.0D, y + 2.0D, z + 2.0D);
            


            int convertableStackSize = Math.min(originalStackSize, 1);
            remainingStackSize = originalStackSize - convertableStackSize;
            
            EntityCreature creature = Infusion.spawnCreature(field_70170_p, EntitySpirit.class, (int)field_70165_t, (int)field_70163_u, (int)field_70161_v, null, 0, 0, ParticleEffect.INSTANT_SPELL, null);
            if (creature != null) {
              EntitySpirit spirit = (EntitySpirit)creature;
              creature.func_110163_bv();
              spirit.setTarget("Village", 2);
            }
            
            if (remainingStackSize > 0) {
              EntityUtil.spawnEntityInWorld(field_70170_p, new EntityItem(field_70170_p, field_70165_t, field_70163_u, field_70161_v, ItemsGENERIC.itemAttunedStone.createStack(remainingStackSize)));
            }
            

            ParticleEffect.LARGE_EXPLODE.send(SoundEffect.RANDOM_POP, this, 1.0D, 1.0D, 16);
            isInnerTinyBlockCircle(field_70170_p, x, y, z, glyph, true);
            func_70106_y();
          }
        } else if (ItemsGENERIC.itemSubduedSpirit.isMatch(func_92059_d())) {
          Block glyph = BlocksGLYPH_RITUAL;
          Coord center = isTinyBlockCircle(field_70170_p, new Coord(this), glyph);
          if (center != null) {
            int originalStackSize = func_92059_dfield_77994_a;
            int remainingStackSize = 0;
            double R = 2.0D;
            double RSq = 4.0D;
            EntityPosition centerPoint = new EntityPosition(x + 0.5D, y + 0.5D, z + 0.5D);
            AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(x - 2.0D, y - 2.0D, z - 2.0D, x + 2.0D, y + 2.0D, z + 2.0D);
            


            int convertableStackSize = Math.min(originalStackSize, 1);
            remainingStackSize = originalStackSize - convertableStackSize;
            
            EntityCreature creature = Infusion.spawnCreature(field_70170_p, EntitySpirit.class, (int)field_70165_t, (int)field_70163_u, (int)field_70161_v, null, 0, 0, ParticleEffect.INSTANT_SPELL, null);
            if (creature != null) {
              EntitySpirit spirit = (EntitySpirit)creature;
              creature.func_110163_bv();
              spirit.setTarget("Village", 2);
            }
            
            if (remainingStackSize > 0) {
              EntityUtil.spawnEntityInWorld(field_70170_p, new EntityItem(field_70170_p, field_70165_t, field_70163_u, field_70161_v, ItemsGENERIC.itemSubduedSpirit.createStack(remainingStackSize)));
            }
            

            ParticleEffect.LARGE_EXPLODE.send(SoundEffect.RANDOM_POP, this, 1.0D, 1.0D, 16);
            isInnerTinyBlockCircle(field_70170_p, x, y, z, glyph, true);
            func_70106_y();
          }
        }
      }
  }
  
  private static class TeleportTask extends ServerTickEvents.ServerTickTask {
    ItemStack stone;
    Entity entity;
    
    public TeleportTask(World world, ItemStack stone, Entity entity) { super();
      this.stone = stone;
      this.entity = entity;
    }
    
    public boolean process()
    {
      if (!ItemsGENERIC.teleportToLocation(world, stone, entity, 0, true))
      {

        ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, world, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, 1.0D, 1.0D, 16);
      }
      
      return true;
    }
  }
  
  private static Coord isTinyBlockCircle(World world, Coord coord, Block runeBlock) {
    int x = x;
    int y = y;
    int z = z;
    if (isInnerTinyBlockCircle(world, x, y, z, runeBlock, false)) {
      return coord;
    }
    





    return null;
  }
  
  private static boolean isInnerTinyBlockCircle(World world, int x, int y, int z, Block runeBlock, boolean explode)
  {
    int[][] circle = { { x, z - 1 }, { x + 1, z - 1 }, { x + 1, z }, { x + 1, z + 1 }, { x, z + 1 }, { x - 1, z + 1 }, { x - 1, z }, { x - 1, z - 1 } };
    









    for (int[] coord : circle) {
      if (world.func_147439_a(coord[0], y, coord[1]) != runeBlock) {
        return false;
      }
    }
    if (explode) {
      for (int[] coord : circle) {
        world.func_147468_f(coord[0], y, coord[1]);
        ParticleEffect.EXPLODE.send(SoundEffect.NONE, world, 0.5D + coord[0], y, 0.5D + coord[1], 0.5D, 0.5D, 16);
      }
    }
    return true;
  }
  
























  private static Coord isSmallBlockCircle(World world, Coord coord, Block runeBlock)
  {
    int x = x;
    int z = z;
    int[][] circle = { { 0, 0 }, { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 } };
    

    for (int[] co : circle) {
      if (com.emoniph.witchery.util.CircleUtil.isSmallCircle(world, x + co[0], y, z + co[1], runeBlock)) {
        return new Coord(x - co[0], y, z - co[1]);
      }
    }
    return null;
  }
}
