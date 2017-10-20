package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualStep.Result;
import com.emoniph.witchery.ritual.RitualStep.SacrificedItem;
import com.emoniph.witchery.util.Coord;
import java.util.ArrayList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteSummonItem extends com.emoniph.witchery.ritual.Rite
{
  private final ItemStack itemToSummon;
  private final Binding binding;
  
  public static enum Binding
  {
    NONE,  LOCATION,  ENTITY,  COPY_LOCATION,  PLAYER;
    
    private Binding() {}
  }
  
  public RiteSummonItem(ItemStack itemToSummon, Binding binding)
  {
    this.itemToSummon = itemToSummon;
    this.binding = binding;
  }
  
  public void addSteps(ArrayList<RitualStep> steps, int intialStage)
  {
    steps.add(new StepSummonItem(this));
  }
  
  private static class StepSummonItem extends RitualStep
  {
    private final RiteSummonItem rite;
    
    public StepSummonItem(RiteSummonItem rite) {
      super();
      this.rite = rite;
    }
    
    public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
    {
      if (ticks % 20L != 0L) {
        return RitualStep.Result.STARTING;
      }
      
      if (!field_72995_K) {
        ItemStack itemstack = ItemStack.func_77944_b(rite.itemToSummon);
        if (rite.binding == RiteSummonItem.Binding.LOCATION) {
          ItemsGENERIC.bindToLocation(world, posX, posY, posZ, field_73011_w.field_76574_g, field_73011_w.func_80007_l(), itemstack);
        } else if (rite.binding == RiteSummonItem.Binding.ENTITY) {
          int r = 4;
          EntityLivingBase target = null;
          AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - 4, posY, posZ - 4, posX + 4, posY + 1, posZ + 4);
          for (Object obj : world.func_72872_a(EntityPlayer.class, bounds)) {
            EntityPlayer player = (EntityPlayer)obj;
            if (Coord.distance(field_70165_t, field_70163_u, field_70161_v, posX, posY, posZ) <= 4.0D) {
              target = player;
            }
          }
          if (target != null) {
            bounds = AxisAlignedBB.func_72330_a(posX - 4, posY, posZ - 4, posX + 4, posY + 1, posZ + 4);
            for (Object obj : world.func_72872_a(EntityLiving.class, bounds)) {
              EntityLiving entity = (EntityLiving)obj;
              if (Coord.distance(field_70165_t, field_70163_u, field_70161_v, posX, posY, posZ) <= 4.0D) {
                target = entity;
              }
            }
          }
          if (target != null) {
            ItemsTAGLOCK_KIT.setTaglockForEntity(itemstack, null, target, false, Integer.valueOf(1));
          } else {
            return RitualStep.Result.ABORTED_REFUND;
          }
        } else if (rite.binding == RiteSummonItem.Binding.PLAYER) {
          int r = 4;
          EntityLivingBase target = null;
          AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - 4, posY, posZ - 4, posX + 4, posY + 1, posZ + 4);
          for (Object obj : world.func_72872_a(EntityPlayer.class, bounds)) {
            EntityPlayer player = (EntityPlayer)obj;
            if (Coord.distance(field_70165_t, field_70163_u, field_70161_v, posX, posY, posZ) <= 4.0D) {
              target = player;
            }
          }
          if (target != null) {
            NBTTagCompound nbtRoot = new NBTTagCompound();
            nbtRoot.func_74778_a("WITCBoundPlayer", target.func_70005_c_());
            itemstack.func_77982_d(nbtRoot);
          } else {
            return RitualStep.Result.ABORTED_REFUND;
          }
        } else if (rite.binding == RiteSummonItem.Binding.COPY_LOCATION) {
          for (RitualStep.SacrificedItem item : sacrificedItems) {
            if (ItemsGENERIC.hasLocationBinding(itemstack)) {
              ItemsGENERIC.copyLocationBinding(itemstack, itemstack);
              break;
            }
          }
        }
        
        if (itemstack.func_77973_b() == net.minecraft.item.Item.func_150898_a(BlocksCRYSTAL_BALL)) {
          EntityPlayer player = ritual.getInitiatingPlayer(world);
          if (player != null) {
            com.emoniph.witchery.predictions.PredictionManager.instance().setFortuneTeller(player, true);
          }
        }
        
        net.minecraft.entity.item.EntityItem entity = new net.minecraft.entity.item.EntityItem(world, 0.5D + posX, posY + 1.5D, 0.5D + posZ, itemstack);
        field_70159_w = 0.0D;
        field_70181_x = 0.3D;
        field_70179_y = 0.0D;
        world.func_72838_d(entity);
        
        com.emoniph.witchery.util.ParticleEffect.SPELL.send(com.emoniph.witchery.util.SoundEffect.RANDOM_FIZZ, entity, 0.5D, 0.5D, 16);
      }
      return RitualStep.Result.COMPLETED;
    }
  }
}
