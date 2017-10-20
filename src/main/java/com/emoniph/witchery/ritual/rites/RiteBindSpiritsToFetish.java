package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.entity.EntityBanshee;
import com.emoniph.witchery.entity.EntityDeath;
import com.emoniph.witchery.entity.EntityPoltergeist;
import com.emoniph.witchery.entity.EntitySpectre;
import com.emoniph.witchery.entity.EntitySpirit;
import com.emoniph.witchery.item.ItemDeathsClothes;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.ritual.RitualStep.SacrificedItem;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class RiteBindSpiritsToFetish extends com.emoniph.witchery.ritual.Rite
{
  private final int radius;
  
  public RiteBindSpiritsToFetish(int radius)
  {
    this.radius = radius;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int intialStage)
  {
    steps.add(new StepSpiritsToFetish(this));
  }
  
  private static class StepSpiritsToFetish extends RitualStep
  {
    private final RiteBindSpiritsToFetish rite;
    
    public StepSpiritsToFetish(RiteBindSpiritsToFetish rite) {
      super();
      this.rite = rite;
    }
    
    public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
    {
      if (ticks % 20L != 0L) {
        return RitualStep.Result.STARTING;
      }
      
      if (!field_72995_K) {
        int r = rite.radius;
        int r2 = r * r;
        
        AxisAlignedBB bb = AxisAlignedBB.func_72330_a(posX - r, posY - r, posZ - r, posX + r, posY + r, posZ + r);
        java.util.List entities = world.func_72872_a(net.minecraft.entity.EntityCreature.class, bb);
        
        ArrayList<EntitySpectre> spectreList = new ArrayList();
        ArrayList<EntitySpirit> spiritList = new ArrayList();
        ArrayList<EntityBanshee> bansheeList = new ArrayList();
        ArrayList<EntityPoltergeist> poltergeistList = new ArrayList();
        
        for (Object obj : entities) {
          if ((obj instanceof EntitySpectre)) {
            spectreList.add((EntitySpectre)obj);
          } else if ((obj instanceof EntityPoltergeist)) {
            poltergeistList.add((EntityPoltergeist)obj);
          } else if ((obj instanceof EntityBanshee)) {
            bansheeList.add((EntityBanshee)obj);
          } else if ((obj instanceof EntitySpirit)) {
            spiritList.add((EntitySpirit)obj);
          }
        }
        
        ItemStack stack = null;
        for (RitualStep.SacrificedItem item : sacrificedItems) {
          if (itemstack.func_77969_a(new ItemStack(BlocksFETISH_SCARECROW))) {
            stack = itemstack;
            break; }
          if (itemstack.func_77969_a(new ItemStack(BlocksFETISH_TREANT_IDOL))) {
            stack = itemstack;
            break; }
          if (itemstack.func_77969_a(new ItemStack(BlocksFETISH_WITCHS_LADDER))) {
            stack = itemstack;
            break;
          }
        }
        
        if (stack == null) {
          return RitualStep.Result.ABORTED_REFUND;
        }
        
        int result = com.emoniph.witchery.infusion.infusions.spirit.InfusedSpiritEffect.tryBindFetish(world, stack, spiritList, spectreList, bansheeList, poltergeistList);
        if (result == 0) {
          return RitualStep.Result.ABORTED_REFUND;
        }
        
        if (result == 2) {
          EntityPlayer deathPlayer = findDeathPlayer(world);
          if (deathPlayer != null) {
            com.emoniph.witchery.item.ItemGeneral.teleportToLocation(world, posX, posY, posZ, field_73011_w.field_76574_g, deathPlayer, true);
            ParticleEffect.INSTANT_SPELL.send(SoundEffect.MOB_WITHER_SPAWN, deathPlayer, 0.5D, 1.5D, 16);
          } else {
            EntityDeath death = new EntityDeath(world);
            death.func_70012_b(0.5D + posX, posY + 0.1D, 0.5D + posZ, 0.0F, 0.0F);
            death.func_110163_bv();
            world.func_72838_d(death);
            ParticleEffect.INSTANT_SPELL.send(SoundEffect.MOB_WITHER_SPAWN, death, 0.5D, 1.5D, 16);
          }
        }
        else {
          EntityItem entity = new EntityItem(world, 0.5D + posX, posY + 1.5D, 0.5D + posZ, stack);
          field_70159_w = 0.0D;
          field_70181_x = 0.3D;
          field_70179_y = 0.0D;
          world.func_72838_d(entity);
          ParticleEffect.SPELL.send(SoundEffect.RANDOM_FIZZ, entity, 0.5D, 1.5D, 16);
        }
      }
      return RitualStep.Result.COMPLETED;
    }
    
    private EntityPlayer findDeathPlayer(World world) {
      for (Object obj : field_73010_i) {
        EntityPlayer player = (EntityPlayer)obj;
        if ((ItemDeathsClothes.isFullSetWorn(player)) && (player.func_71045_bC() != null) && (player.func_71045_bC().func_77973_b() == ItemsDEATH_HAND)) {
          return player;
        }
      }
      return null;
    }
  }
}
