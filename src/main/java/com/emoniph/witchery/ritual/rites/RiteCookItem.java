package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;

public class RiteCookItem extends com.emoniph.witchery.ritual.Rite
{
  private final float radius;
  private final double burnChance;
  
  public RiteCookItem(float radius, double burnChance)
  {
    this.radius = radius;
    this.burnChance = burnChance;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int intialStage)
  {
    steps.add(new StepCookItem(this));
  }
  
  private static class StepCookItem extends RitualStep
  {
    private final RiteCookItem rite;
    
    public StepCookItem(RiteCookItem rite) {
      super();
      this.rite = rite;
    }
    
    public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
    {
      if (ticks % 20L != 0L) {
        return RitualStep.Result.STARTING;
      }
      
      if (!field_72995_K)
      {
        ArrayList<EntityItem> items = rite.getItemsInRadius(world, posX, posY, posZ, rite.radius);
        int count = 0;
        for (EntityItem item : items) {
          ItemStack cookedStack = FurnaceRecipes.func_77602_a().func_151395_a(item.func_92059_d());
          if ((cookedStack != null) && ((cookedStack.func_77973_b() instanceof ItemFood)) && (func_92059_dfield_77994_a > 0)) {
            int size = func_92059_dfield_77994_a;
            int burnCount = 0;
            for (int i = 0; i < size; i++) {
              if (field_73012_v.nextDouble() < rite.burnChance) {
                burnCount++;
              }
            }
            item.func_70106_y();
            if (size - burnCount > 0) {
              field_77994_a = (size - burnCount);
              EntityItem cookedEntity = new EntityItem(world, posX, posY + 0.05D, posZ, cookedStack);
              field_70159_w = 0.0D;
              field_70179_y = 0.0D;
              world.func_72838_d(cookedEntity);
            }
            if (burnCount > 0) {
              EntityItem burntEntity = new EntityItem(world, posX, posY + 0.05D, posZ, new ItemStack(Items.field_151044_h, burnCount, 1));
              field_70159_w = 0.0D;
              field_70179_y = 0.0D;
              world.func_72838_d(burntEntity);
            }
            count++;
          }
        }
        
        if (count == 0) {
          return RitualStep.Result.ABORTED_REFUND;
        }
        
        ParticleEffect.FLAME.send(SoundEffect.MOB_GHAST_FIREBALL, world, posX, posY, posZ, 3.0D, 2.0D, 16);
      }
      
      return RitualStep.Result.COMPLETED;
    }
  }
}
