package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.entity.EntitySummonedUndead;
import com.emoniph.witchery.item.ItemSpectralStone;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RiteRegistry;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteSummonSpectralStone extends Rite
{
  private final int radius;
  
  public RiteSummonSpectralStone(int radius)
  {
    this.radius = radius;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int intialStage)
  {
    steps.add(new StepSummonItem(this));
  }
  
  private static class StepSummonItem extends RitualStep
  {
    private final RiteSummonSpectralStone rite;
    
    public StepSummonItem(RiteSummonSpectralStone rite) {
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
        java.util.List entities = world.func_72872_a(EntitySummonedUndead.class, bb);
        Class<? extends EntitySummonedUndead> entityType = null;
        int count = 0;
        for (Object obj : entities) {
          EntitySummonedUndead entity = (EntitySummonedUndead)obj;
          if (entity.func_70092_e(0.5D + posX, posY, 0.5D + posZ) <= r2) {
            Class<? extends EntitySummonedUndead> foundType = entity.getClass();
            if (entityType == null) {
              entityType = foundType;
            }
            
            if (entityType == foundType) {
              count++;
              if (!field_72995_K) {
                entity.func_70106_y();
                ParticleEffect.PORTAL.send(SoundEffect.RANDOM_POP, entity, 1.0D, 2.0D, 16);
              }
              if (count >= 3) {
                break;
              }
            }
          }
        }
        
        if (count <= 0) {
          RiteRegistry.RiteError("witchery.rite.missinglivingsacrifice", ritual.getInitiatingPlayerName(), world);
          return RitualStep.Result.ABORTED_REFUND;
        }
        
        ItemStack stack = new ItemStack(ItemsSPECTRAL_STONE, 1, ItemSpectralStone.metaFromCreature(entityType, count));
        EntityItem entity = new EntityItem(world, 0.5D + posX, posY + 1.5D, 0.5D + posZ, stack);
        field_70159_w = 0.0D;
        field_70181_x = 0.3D;
        field_70179_y = 0.0D;
        world.func_72838_d(entity);
        
        ParticleEffect.SPELL.send(SoundEffect.RANDOM_FIZZ, entity, 0.5D, 0.5D, 16);
      }
      return RitualStep.Result.COMPLETED;
    }
  }
}
