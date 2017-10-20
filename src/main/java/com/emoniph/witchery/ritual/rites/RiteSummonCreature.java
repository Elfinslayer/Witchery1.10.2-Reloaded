package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.entity.EntityImp;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RiteRegistry;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TameableUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class RiteSummonCreature extends Rite
{
  private final Class<? extends EntityCreature> creatureToSummon;
  private boolean bindTameable;
  
  public RiteSummonCreature(Class<? extends EntityCreature> creatureToSummon, boolean bindTameable)
  {
    this.creatureToSummon = creatureToSummon;
    this.bindTameable = bindTameable;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int intialStage)
  {
    steps.add(new StepSummonCreature(this));
  }
  
  private static class StepSummonCreature extends RitualStep
  {
    private final RiteSummonCreature rite;
    
    public StepSummonCreature(RiteSummonCreature rite) {
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
        int[][] PATTERN = { { 0, 0, 1, 1, 1, 0, 0 }, { 0, 1, 1, 1, 1, 1, 0 }, { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 2, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1 }, { 0, 1, 1, 1, 1, 1, 0 }, { 0, 0, 1, 1, 1, 0, 0 } };
        







        int obstructions = 0;
        for (int y = posY + 1; y <= posY + 3; y++) {
          int offsetZ = (PATTERN.length - 1) / 2;
          for (int z = 0; z < PATTERN.length - 1; z++) {
            int worldZ = posZ - offsetZ + z;
            int offsetX = (PATTERN[z].length - 1) / 2;
            for (int x = 0; x < PATTERN[z].length; x++) {
              int worldX = posX - offsetX + x;
              int val = PATTERN[(PATTERN.length - 1 - z)][x];
              if (val == 1) {
                Material material = world.func_147439_a(worldX, y, worldZ).func_149688_o();
                if ((material != null) && (material.func_76220_a())) {
                  obstructions++;
                }
              } else if (val == 2) {
                Material material = world.func_147439_a(worldX, y, worldZ).func_149688_o();
                if ((material != null) && (material.func_76220_a())) {
                  obstructions += 100;
                }
              }
            }
          }
        }
        
        int MAX_OBSTRUCTIONS = 1;
        if (obstructions <= 1) {
          try {
            Constructor ctor = rite.creatureToSummon.getConstructor(new Class[] { World.class });
            EntityLiving entity = (EntityLiving)ctor.newInstance(new Object[] { world });
            if ((entity instanceof EntityDemon)) {
              ((EntityDemon)entity).setPlayerCreated(true);
            }
            else {
              if (((entity instanceof EntityImp)) && (covenSize == 0)) {
                EntityPlayer player = ritual.getInitiatingPlayer(world);
                SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
                if (player != null) {
                  ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.coventoosmall", new Object[0]);
                }
                return RitualStep.Result.ABORTED_REFUND; }
              if ((rite.bindTameable) && ((entity instanceof EntityTameable))) {
                ((EntityTameable)entity).func_70903_f(true);
                TameableUtil.setOwner((EntityTameable)entity, ritual.getInitiatingPlayer(world));
              }
            }
            entity.func_70012_b(0.5D + posX, 1.0D + posY, 0.5D + posZ, 1.0F, 0.0F);
            
            world.func_72838_d(entity);
            IEntityLivingData entitylivingData = null;
            entitylivingData = entity.func_110161_a(entitylivingData);
            
            ParticleEffect.PORTAL.send(SoundEffect.RANDOM_FIZZ, entity, 0.5D, 1.0D, 16);
          }
          catch (NoSuchMethodException ex) {}catch (InvocationTargetException ex) {}catch (InstantiationException ex) {}catch (IllegalAccessException ex) {}
        }
        


        ParticleEffect.LARGE_SMOKE.send(SoundEffect.NOTE_SNARE, world, posX, posY, posZ, 0.5D, 2.0D, 16);
        RiteRegistry.RiteError("witchery.rite.obstructedcircle", ritual.getInitiatingPlayerName(), world);
        return RitualStep.Result.ABORTED_REFUND;
      }
      
      return RitualStep.Result.COMPLETED;
    }
  }
}
